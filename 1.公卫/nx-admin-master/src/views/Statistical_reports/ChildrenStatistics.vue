<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item label-width="160px">
          <el-cascader :options="options" v-model="filters.area"  placeholder="区域"  change-on-select style="width: 100%"></el-cascader>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="search" style="background-color: #4db2ee;border-color: transparent;height: 40px" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="clear" style="background-color:#4db2ee;border-color:transparent;height: 40px" icon="el-icon-back" >清除</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--列表-->
    <div id="print">
      <el-table :data="users":height="tableHeight"  highlight-current-row @selection-change="selsChange"border style="width: 100%;">
        <el-table-column type="index" label="序号" width="60">
        </el-table-column>
        <el-table-column prop="deleteState" v-if=false label="删除状态" width="60" >
        </el-table-column>
        <el-table-column prop="area" label="辖区" width="250">
        </el-table-column>
        <el-table-column prop="year" label="年份" width="60">
        </el-table-column>
        <el-table-column prop="peopleCount" label="辖区常住人口" width="120">
        </el-table-column>
        <el-table-column prop="newbornVisit" label="新生儿访视人数" width="140">
        </el-table-column>
        <el-table-column prop="liveCount" label="辖区内活产数" width="140">
        </el-table-column>
        <el-table-column prop="visitRatio" label="新生儿访问率" width="120">
        </el-table-column>
        <el-table-column prop="mangePeople" label="0-6岁儿童健康管理人数" min-width="120">
        </el-table-column>
        <el-table-column prop="areaMangePeople" label="辖区0~6岁儿童数" min-width="120">
        </el-table-column>
        <el-table-column prop="manageVisitRatio" label="0~6岁儿童健康管理率" min-width="110">
        </el-table-column>
        <el-table-column prop="newbornYear" label="年度内新增儿童管理人数" min-width="120">
        </el-table-column>
        <el-table-column prop="diePeople" label="迁出（死亡）儿童管理人数" min-width="130">
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
  } from '@/api/childrenStatistics'
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
        // Print({
        //   printable: 'print',
        //   type: 'html',
        //   targetStyles: ['*']
      //  })
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
