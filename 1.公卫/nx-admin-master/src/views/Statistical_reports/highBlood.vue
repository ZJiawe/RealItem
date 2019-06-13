<template>
  <section class="app-container">


    <!--工具条-->
    <el-col :span="22" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item >
          <el-cascader :options="options" v-model="filters.area"  placeholder="区域" ></el-cascader>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="searchs"  style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="clear" style="background-color:#ffffff;color:#909090;border-color: #c1c1c1;font-size: 14px;margin-left:10px;" icon="el-icon-back" >重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--列表-->
    <el-table :data="users" :height="tableMeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;">
      <el-table-column type="index" label="序号" width="60">
      </el-table-column>
      <el-table-column prop="area" label="区域" width="250">
      </el-table-column>
      <el-table-column prop="year" label="年份" width="60">
      </el-table-column>
      <el-table-column prop="residentsNumber" label="辖区常住人口数" width="140">
      </el-table-column>
      <el-table-column prop="resHighNumber" label="辖区内高血压患者总人数(理论数)" width="260" >
      </el-table-column>
      <el-table-column prop="highNumber" label="高压血患者总人数(人)" width="180">
      </el-table-column>
      <el-table-column prop="highNumberYear" label="年度内高血压患者管理人数(人)" min-width="240">
      </el-table-column>
      <el-table-column prop="yearHighMagNumber" label="年度内高血压患者规范管理人数(人)" min-width="280">
      </el-table-column>
      <el-table-column prop="yearHighMagRate" label="高血压患者规范管理率(%)" min-width="230">
      </el-table-column>
      <el-table-column prop="yearHighNearNumber" label="年度内最近一次随访血压达标的高血压患病管理人数" min-width="390">
      </el-table-column>
      <el-table-column prop="yearHighCtrRate" label="高血压管理人群血压控制率(%)" min-width="250">
      </el-table-column>
      <el-table-column prop="yearHighAddNumber" label="年度内新增高血压管理人数" min-width="230">
      </el-table-column>
      <el-table-column prop="deathsNumber" label="迁出（死亡)人数" min-width="160">
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
  import util from '@/utils/table.js'
  import {
    getUserListPage,
    removeUser,
    batchRemoveUser,
    editUser,
    addUser
  } from '@/api/hyperSum'
  import city from '@/api/city.js'
  export default {
    data() {
      return {
        dialogStatus: '',
        tableMeight: window.innerHeight - 220,
        options: city.city(),
        area: [],
        textMap: {
          update: '编辑',
          create: '新增',
          search: '查询'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          area: [],
          year: '',
          residentsNumber: '',
          resHighNumber: '',
          highNumber: '',
          highNumberYear: '',
          yearHighMagNumber: '',
          yearHighMagRate: '',
          yearHighNearNumber: '',
          yearHighCtrRate: '',
          yearHighAddNumber: '',
          deathsNumber: ''
        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        },
        // 编辑界面数据
        editForm: {
          name: '',
          sex: '',
          age: '',
          peopleId: '',
          phone: ''
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      // 性别显示转换
      formatSex: function(row, column) {
        return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
      },
      handleCurrentChange() {
        this.page = 1
        this.getUsers()
      },
      // 获取用户列表
      getUsers() {
        this.dialogFormVisible2 = false
        const para = {
          page: this.page,
          size: 20,
          filters: { year: this.filters.year, area: this.filters.area, residentsNumber: this.filters.residentsNumber, resHighNumber: this.filters.resHighNumber, highNumber: this.filters.highNumber, highNumberYear: this.filters.highNumberYear, yearHighMagNumber: this.filters.yearHighMagNumber, yearHighMagRate: this.filters.yearHighMagRate, yearHighNearNumber: this.filters.yearHighNearNumber, yearHighCtrRate: this.filters.yearHighCtrRate, yearHighAddNumber: this.filters.yearHighAddNumber, deathsNumber: this.filters.deathsNumber }
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
        })
        if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
          this.page = this.page - 1
          this.getUsers()
        }
      },
      clear() {
        this.filters = {
          area: []
        }
      },

      // 删除
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(() => {
            const para = { id: row.id }
            removeUser(para).then(res => {
              this.$message({
                message: '删除成功',
                type: 'success'
              })
              this.getUsers()
            })
          })
          .catch(() => {})
      },

      findbtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          area: '',
          residentsNumber: '',
          resHighNumber: '',
          highNumber: '',
          highNumberYear: '',
          yearHighMagNumber: '',
          yearHighMagRate: '',
          yearHighNearNumber: '',
          yearHighCtrRate: '',
          yearHighAddNumber: '',
          deathsNumber: ''
        }
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
      },
      searchs() {
        this.page = 1
        this.getUsers()
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.editForm = {
          name: '',
          sex: '',
          age: '',
          peopleId: '',
          phone: ''
        }
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.modifier = '测试人'
                para.docDate =
                  !para.docDate || para.docDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.docDate), 'yyyy-MM-dd')
                para.modifyDate = new Date()
                para.modifyDate =
                  !para.modifyDate || para.modifyDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.modifyDate), 'yyyy-MM-dd')
                editUser(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.getUsers()
                })
              })
              .catch(e => {
                // 打印一下错误
                console.log(e)
              })
          }
        })
      },
      // 新增
      createData: function() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.docDate =
                  !para.docDate || para.docDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.docDate), 'yyyy-MM-dd')
                para.createDate = new Date()
                para.createDate =
                  !para.createDate || para.createDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.createDate), 'yyyy-MM-dd')
                addUser(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.getUsers()
                })
              })
              .catch(e => {
                // 打印一下错误
                console.log(e)
              })
          }
        })
      },
      // 全选单选多选
      selsChange(sels) {
        this.sels = sels
      },
      // 批量删除
      batchRemove() {
        var ids = this.sels.map(item => item.id).toString()
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        })
          .then(() => {
            const para = { ids: ids }
            batchRemoveUser(para).then(res => {
              this.$message({
                message: '删除成功',
                type: 'success'
              })
              this.getUsers()
            })
          })
          .catch(() => {})
      }
    },
    mounted() {
      this.getUsers()
    }
  }
</script>

<style scoped>

</style>

