<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-cascader :options="options" v-model="filters.area"  placeholder="辖区"  change-on-select></el-cascader>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers"style="background-color:#4db2ee;border-color:transparent;height:40px" icon="el-icon-search">查询</el-button>
        </el-form-item>

      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="users":height="tableHeight"  highlight-current-row @selection-change="selsChange"border style="width: 100%;">
      <el-table-column  v-if="false" type="selection" width="55">
      </el-table-column>
      <el-table-column type="index" label="序号" width="60">
      </el-table-column>
      <el-table-column prop="area" label="辖区" width="200">
      </el-table-column>
      <el-table-column prop="year" label="年份" width="60">
      </el-table-column>
      <el-table-column prop="tuNumber" label="肺结核患者人数" width="150" >
      </el-table-column>
      <el-table-column prop="tumNumber" label="肺结核患者管理人数" width="170">
      </el-table-column>
      <el-table-column prop="tumRate" label="肺结核患者管理率" width="150">
      </el-table-column>
      <el-table-column prop="treNumber" label="已完成治疗的肺结核患者人数" width="250">
      </el-table-column>
      <el-table-column prop="ruleNumber" label="规则服药的肺结核患者人数" min-width="230">
      </el-table-column>
      <el-table-column prop="ruleRate" label="肺结核患者规则服药率" min-width="210">
      </el-table-column>
      <el-table-column prop="addNumber" label="年度新增肺结核病管理人数" min-width="230">
      </el-table-column>
      <el-table-column prop="outNumber" label="迁出（死亡）人数" min-width="170">
      </el-table-column>
      </el-table>

      <!--工具条-->
    <el-col :span="24" class="toolbar">
   <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
   </el-pagination>
 </el-col>


  </section>
</template>

<script>
  import util from '@/utils/table.js'
  import {
    getList,
    removeData,
    batchRemoveUser,
    updateData,
    addeData
  } from '@/api/TuberculosisHealth'
  import city from '@/api/city.js'

  export default {
    data() {
      return {
        options: city.city(),
        dialogStatus: '',
        tableHeight: window.innerHeight - 220,
        textMap: {
          update: '编辑',
          create: '新增'
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
        },
        // 编辑界面数据
        editForm: {

          area: ''
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
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      // 获取用户列表
      getUsers() {
        this.filters.startTime =
          !this.filters.startTime || this.filters.startTime === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.startTime), 'yyyy-MM-dd')

        this.filters.endTime =
          !this.filters.endTime || this.filters.endTime === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.endTime), 'yyyy-MM-dd')

        const para = {
          page: this.page,
          size: 20,
          filters: { area: '' }
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
      },
      // 删除
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          type: 'warning'
        })
          .then(() => {
            const para = row.id
            removeData(para).then(res => {
              this.$message({
                message: '删除成功',
                type: 'success'
              })
              this.getUsers()
            })
          })
          .catch(() => {})
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.editForm = {
          id: '0',
          name: '',
          sex: 1,
          age: 0,
          birth: '',
          area: []
        }
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                const para = Object.assign({}, this.editForm)

                para.area = ''
                for (const itm of this.editForm.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                updateData(para).then(res => {
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
                this.editForm.id = (parseInt(Math.random() * 100)).toString() // mock a id
                const para = Object.assign({}, this.editForm)
                console.log(para)

                para.birth =
                  !para.birth || para.birth === ''
                    ? ''
                    : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd')

                para.area = ''
                for (const itm of this.editForm.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                addeData(para).then(res => {
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
