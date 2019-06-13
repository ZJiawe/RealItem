<template>
  <section class="app-container">
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;" >
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item label-width="160px">
          <el-cascader :options="options" v-model="filters.area"  placeholder="区域"  change-on-select style="width: 100%"></el-cascader>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers" style="background-color:#4db2ee;border-color:transparent;height:40px;" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="clear" style="background-color:#ffffff;color:#909090;border-color: #c1c1c1;font-size: 14px;margin-left:10px;" icon="el-icon-back" >重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row  border style="width: 100%;" @row-dblclick="handleRowChange">
      <el-table-column type="index" label="序号" width="70">
      </el-table-column>
      <el-table-column prop="area" label="区域" width="250">
      </el-table-column>
      <el-table-column prop="year" label="年份" width="60">
      </el-table-column>
      <el-table-column prop="permanentPopulation" label="区域常住人口数" width="150">
      </el-table-column>
      <el-table-column prop="liveBirthNumber" label="区域内活产数" width="120" style="text-align:center">
      </el-table-column>
      <el-table-column prop="earlyPregnancy" label="早孕建册人数" width="120">
      </el-table-column>
      <el-table-column prop="earlyPregnancyRate" label="早孕建册率" width="120">
      </el-table-column>
      <el-table-column prop="postpartumVisits" label="产后访视产妇数" width="140">
      </el-table-column>
      <el-table-column prop="postpartumVisitsRate" label="产后访视率" min-width="120">
      </el-table-column>
      <el-table-column prop="managementWomen" label="年度内新增产妇管理人数" min-width="200">
      </el-table-column>
      <el-table-column prop="custodyWomen" label="年度内结案产妇管理人数" min-width="200">
      </el-table-column>
      <el-table-column prop="movedNumber" label="迁出（死亡）孕产妇人数" min-width="200">
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
    getList
  } from '@/api/maternalStatistical'
  import city from '@/api/city.js'
  export default {
    data() {
      return {
        options: city.city(),
        area: [],
        tableHeight: window.innerHeight - 220,
        textMap: {
          detail: '详情'
        },
        dialogFormVisible: false,
        filters: {
          area: []
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
            area: this.filters.area
          }
        }
        para.filters.area = ''
        for (const itm of this.filters.area) {
          para.filters.area += itm
          para.filters.area += '/'
        }
        para.filters.area = para.filters.area.substr(0, para.filters.area.length - 1)

        getList(para).then(res => {
          this.total = res.totalCount
          this.users = res.detailModelList
          if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
            this.page = this.page - 1
            this.getDatas()
          }
        })
      },
      clear() {
        this.filters = {
          area: []
        }
        this.getUsers()
      },
      handleRowChange(row, event) {
        this.dialogStatus = 'detail'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
      }
    },
    mounted() {
      this.getUsers()
    }
  }
</script>
