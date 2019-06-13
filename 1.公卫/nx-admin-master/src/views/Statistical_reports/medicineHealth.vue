<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-cascader :options="options" v-model="filters.area"  placeholder="区域"  style="width: 100%"></el-cascader>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="search" style="background-color: #4db2ee;border-color: transparent" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="clear" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-back" >清除</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--列表-->
    <div id="print">
      <el-table :data="users":height="tableHeight"  highlight-current-row @selection-change="selsChange"border style="width: 100%;">
        <el-table-column type="index" label="序号" width="70">
        </el-table-column>
        <el-table-column prop="area" label="地区" width="120">
        </el-table-column>
        <el-table-column prop="year" label="年份" width="100">
        </el-table-column>
        <el-table-column prop="peopleCount" label="辖区常住人口数" width="140">
        </el-table-column>
        <el-table-column prop="childrenCount" label="0~3岁儿童数量" width="130">
        </el-table-column>
        <el-table-column prop="childrenMangerCount" label="0~3岁儿童管理人数" width="170">
        </el-table-column>
        <el-table-column prop="childrenCountByMedical" label="中医药管理儿童数量" width="170">
        </el-table-column>
        <el-table-column prop="elderCount" label="65岁老人数量" width="150" style="text-align:center">
        </el-table-column>
        <el-table-column prop="elderMangerCount" label="老人管理数量" min-width="60">
        </el-table-column>
        <el-table-column prop="elderCountByMedical" label="中医药老人管理数量" min-width="70">
        </el-table-column>
        <el-table-column prop="elderCountByMedicalRato" label="中医药老人管理率" min-width="100">
        </el-table-column>
        <el-table-column prop="childrenCountByMedicalRato" label="0-36月龄中医药健康管理率" min-width="100">
        </el-table-column>
      </el-table>
    </div>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-pagination layout="prev, pager, next" :current-page="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>


  </section>
</template>

<script>
  import city from '@/api/city.js'
  import {
    getList
  } from '@/api/MedicalStatistics'
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
          peopleCount: '',
          areaPeople: '',
          peoples: '',
          yearPeople: '',
          mangePeople: '',
          mangeRate: '',
          nearPeople: '',
          controlRate: '',
          addPeople: '',
          diePeople: ''
        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      conprint() {
      },
      // 查询部分
      findbtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.filters = {
          area: [],
          peopleCount: '',
          areaPeople: '',
          peoples: '',
          yearPeople: '',
          mangePeople: '',
          mangeRate: '',
          nearPeople: '',
          controlRate: '',
          addPeople: '',
          diePeople: ''
        }
        this.area = []
      },
      // 查询
      search() {
        this.page = 1
        this.getUsers()
      },
      // 获取用户列表
      getUsers() {
        const para = {
          page: this.page,
          size: 20,
          filters: {
            area: '',
            peopleCount: this.filters.peopleCount, areaPeople: this.filters.areaPeople,
            peoples: this.filters.peoples, yearPeople: this.filters.yearPeople, mangePeople: this.filters.mangePeople,
            mangeRate: this.filters.mangeRate, nearPeople: this.filters.nearPeople, controlRate: this.filters.controlRate,
            addPeople: this.filters.addPeople, diePeople: this.filters.diePeople }
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
            this.getUsers()
          }
        })
        this.dialogFormVisible2 = false
      },
      clear() {
        this.filters = {
          area: []
        }
        this.getUsers()
      }
    },
    mounted() {
      this.getUsers()
    }
  }

</script>
<style scoped>

</style>
