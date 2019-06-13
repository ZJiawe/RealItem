<template>

  <section class="app-container">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-col span="5">
          <el-cascader :options="options" v-model="filters.area" placeholder="区域"  change-on-select></el-cascader>
        </el-col>
          <el-button type="primary" v-on:click="searchs" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search" >查询</el-button>

          <el-button type="primary" v-on:click="clear" style="background-color:#ffffff;color:#909090;border-color: #c1c1c1;font-size: 14px;margin-left:10px;" icon="el-icon-back" >重置</el-button>


      </el-form>
    </el-col>

    <!--查询-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" >
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">

      </el-form>

    </el-dialog>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;">
      <el-table-column type="index" label="序号" width="60">
      </el-table-column>
      <el-table-column prop="area" label="区域" min-width="250">
      </el-table-column>
      <el-table-column prop="year" label="年份" min-width="60">
      </el-table-column>
      <el-table-column prop="permanentPopulation" label="辖区常住人口数" min-width="150">
      </el-table-column>
      <el-table-column prop="totalNumberMental" label="辖区内严重精神障碍患病总人数" min-width="270">
      </el-table-column>
      <el-table-column prop="mentalIllnessYear" label="年度内登记在册的确诊严重精神障碍患者人数" min-width="360">
      </el-table-column>
      <el-table-column prop="mentalRegulated" label="严重精神障碍患者规范管理人数" min-width="260">
      </el-table-column>
      <el-table-column prop="mentalPatients" label="严重精神障碍患者规范管理率" min-width="240">
      </el-table-column>
      <el-table-column prop="newMental" label="年度内新增严重精神障碍患者管理人数" min-width="310">
      </el-table-column>
      <el-table-column prop="movedNumber" label="迁出（死亡)人数" min-width="160">
      </el-table-column>


    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

  </section>
</template>

<script>
  import {
    getUserListPage
  } from '@/api/mentalStatistical'
  import city from '@/api/city.js'

  export default {
    data() {
      return {
        options: city.city(),
        dialogStatus: '',
        tableHeight: window.innerHeight - 220,
        textMap: {
          update: '编辑',
          create: '添加',
          search: '查询'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          area: [],
          permanentPopulation: '',
          totalNumberMental: '',
          mentalIllnessYear: '',
          mentalRegulated: '',
          mentalPatients: '',
          newMental: '',
          movedNumber: ''
        },

        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      clear() {
        this.filters = {
          area: []
        }
      },
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      // 获取用户列表
      getUsers() {
        const para = {
          page: this.page,
          size: 20,
          filters: {
            area: '',
            permanentPopulation: this.filters.permanentPopulation,
            totalNumberMental: this.filters.totalNumberMental,
            mentalIllnessYear: this.filters.mentalIllnessYear,
            mentalRegulated: this.filters.mentalRegulated,
            mentalPatients: this.filters.mentalPatients,
            newMental: this.filters.newMental,
            movedNumber: this.filters.movedNumber
          }
        }
        para.filters.area = ''
        for (const itm of this.filters.area) {
          para.filters.area += itm
          para.filters.area += '/'
        }
        para.filters.area = para.filters.area.substr(0, para.filters.area.length - 1)
        getUserListPage(para).then(res => {
          this.total = res.totalCount
          this.users = res.detailModelList
          if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
            this.page = this.page - 1
            this.getUsers()
          }
        })
        this.dialogFormVisible2 = false
        this.findForm = {
          area: '',
          permanentPopulation: '',
          totalNumberMental: '',
          mentalIllnessYear: '',
          mentalRegulated: '',
          mentalPatients: '',
          newMental: '',
          movedNumber: ''
        }
      },
      // 查询
      searchs() {
        this.page = 1
        this.getUsers()
      },
      // 查询框
      findbtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          area: '',
          permanentPopulation: '',
          totalNumberMental: '',
          mentalIllnessYear: '',
          mentalRegulated: '',
          mentalPatients: '',
          newMental: '',
          movedNumber: ''
        }
      }
    },
    mounted() {
      this.getUsers()
    }
  }
</script>

