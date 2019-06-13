<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
            <el-cascader :options="options" v-model="filters.area"  placeholder="辖区"  change-on-select  style="width: 100%"></el-cascader>
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
        <el-table-column type="index" label="序号" width="60">
        </el-table-column>
        <el-table-column prop="area" label="辖区" min-width="150">
        </el-table-column>
        <el-table-column prop="year" label="年份" min-width="60">
        </el-table-column>
        <el-table-column prop="peopleCount" label="辖区常住人口数" min-width="120">
        </el-table-column>
        <el-table-column prop="areaPeople" label="辖区内2型糖尿病患者总人数" min-width="120" >
        </el-table-column>
        <el-table-column prop="peoples" label="2型糖尿病患者总人数" min-width="120">
        </el-table-column>
        <el-table-column prop="yearPeople" label="年度内2型糖尿病患者管理人数" min-width="120">
        </el-table-column>
        <el-table-column prop="mangePeople" label="年度内2型糖尿病患者规范管理人数" min-width="120">
        </el-table-column>
        <el-table-column prop="mangeRate" label="2型糖尿病患者规范管理率(%)" min-width="120">
        </el-table-column>
        <el-table-column prop="nearPeople" label="年度内最近一次随访血糖达标的糖尿病患病管理人数" min-width="120">
        </el-table-column>
        <el-table-column prop="controlRate" label="2型糖尿病管理人群血糖控制率(%)" min-width="120">
        </el-table-column>
        <el-table-column prop="addPeople" label="年度内新增糖尿病管理人数" min-width="120">
        </el-table-column>
        <el-table-column prop="diePeople" label="迁出（死亡)人数" min-width="120">
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
    getUserListPage
  } from '@/api/totalDiabetes'
  import Print from 'print-js'
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
        Print({
          printable: 'print',
          type: 'html',
          targetStyles: ['*']
        })
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
        getUserListPage(para).then(res => {
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
