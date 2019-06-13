<template>

  <section class="app-container">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <table>
          <tr>
            <td width="100px" style="padding-left:30px;"><span class="mystyle">区域</span></td>
            <td>
              <el-cascader :options="options" v-model="filters.area" placeholder="请选择区域" style="width:100%" change-on-select ></el-cascader>
            </td>

            <td width="100px" style="padding-left:30px;"><span class="mystyle">姓名</span></td>
            <td><el-input v-model="filters.name" placeholder="请输入姓名" style="width: 100%;"></el-input></td>

            <td width="100px" style="padding-left:30px;"><span class="mystyle">身份证号</span></td>
            <td><el-input v-model="filters.peopleId" placeholder="请输入身份证号" style="width: 100%;"></el-input></td>

            <td width="90px" style="padding-left:30px;"><span class="mystyle">性别</span></td>
            <td>
              <el-select v-model="filters.sex" placeholder="请选择性别" style="width: 100%;">
                <el-option key="1" label="男" value="男"></el-option>
                <el-option key="2" label="女" value="女"></el-option>
              </el-select>
            </td>
          </tr>

          <tr>
            <td width="100px" style="padding-left:30px;"><span class="mystyle">民族</span></td>
            <td>
              <el-select v-model="filters.ethnic" placeholder="请选择民族" style="width: 100%;">
                <el-option key="1" label="汉" value="汉"></el-option>
                <el-option key="2" label="其他" value="其他"></el-option>
              </el-select>
            </td>

            <td width="100px" style="padding-left:30px;"><span class="mystyle">建档人</span></td>
            <td><el-input v-model="filters.creator" placeholder="请输入建档人" style="width: 100%;"></el-input></td>

            <td width="100px" style="padding-left:30px;"><span class="mystyle">生存状态</span></td>
            <td>
              <el-select v-model="filters.livingCondition" placeholder="请选择生存状态" style="width: 100%;">
                <el-option key="1" label="存活" value="存活"></el-option>
                <el-option key="2" label="死亡" value="死亡"></el-option>
              </el-select>
            </td>

            <td style="padding-left:10px;">
              <el-form-item>
                <el-button type="primary" v-on:click="searchs" style="background-color:#4db2ee;border-color:transparent;width: 100%" icon="el-icon-search" >查询</el-button>
              </el-form-item>
            </td>
            <td>
              <el-form-item>
                <el-button type="primary" v-on:click="clear" style="background-color:#ffffff;color:#909090;border-color: #c1c1c1;font-size: 14px;margin-left:10px;" icon="el-icon-back" >重置</el-button>
              </el-form-item>
            </td>
          </tr>
        </table>















      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;" @row-dblclick="handleRowChange">
      <el-table-column type="index" label="序号" width="80">
      </el-table-column>
      <el-table-column prop="area" label="区域" min-width="250">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" min-width="180">
      </el-table-column>
      <el-table-column prop="name" label="姓名" min-width="100">
      </el-table-column>
      <el-table-column prop="sex" label="性别" min-width="100" >
      </el-table-column>
      <el-table-column prop="age" label="年龄" min-width="100" >
      </el-table-column>
      <el-table-column prop="ethnic" label="民族" min-width="150" >
      </el-table-column>
      <el-table-column prop="phone" label="联系电话" min-width="160" v-if="false">
      </el-table-column>
      <el-table-column prop="management" label="规范管理" min-width="160" v-if="false">
      </el-table-column>
      <el-table-column prop="responseDoctor" label="责任医生" min-width="100" >
      </el-table-column>
      <el-table-column prop="recentFollow" label="最近随访" min-width="100" >
      </el-table-column>
      <el-table-column prop="numberFollow" label="随访次数" min-width="100" >
      </el-table-column>
      <el-table-column prop="livingCondition" label="生存状态" min-width="100" >
      </el-table-column>
      <el-table-column prop="creator" label="建档人" min-width="120" align="center"  >
      </el-table-column>
      <el-table-column prop="createDate" label="建档时间" min-width="120" align="center" :formatter="timeFormat">
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <EasyScrollbar>
          <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
            <el-tabs v-model="activeName">
            <div id="print">

                <el-row>
                  <el-col span="12">
                    <el-form-item label="区域" >
                      <el-input v-model="editForm.area"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col span="12">
                    <el-form-item label="姓名" >
                      <el-input v-model="editForm.name"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row>
                  <el-col span="12">
                    <el-form-item label="性别" >
                      <el-input v-model="editForm.sex"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col span="12">
                    <el-form-item label="生存状态" >
                      <el-input v-model="editForm.livingCondition"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row>
                  <el-col span="12">
                    <el-form-item label="民族" >
                      <el-input v-model="editForm.ethnic"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col span="12">
                    <el-form-item label="身份证号">
                      <el-input v-model="editForm.peopleId" ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row>
                  <el-col span="12">
                    <el-form-item label="联系电话" >
                      <el-input v-model="editForm.phone"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col span="12">
                    <el-form-item label="责任医生" >
                      <el-input v-model="editForm.responseDoctor"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row>
                  <el-col span="12">
                    <el-form-item label="随访次数" >
                      <el-input v-model="editForm.numberFollow"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col span="12">
                    <el-form-item label="最近随访" >
                      <el-input v-model="editForm.recentFollow"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
            </div>
            </el-tabs>
          </div>
        </EasyScrollbar>
      </el-form>
    </el-dialog>

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
  } from '@/api/MentalQuery'
  import city from '@/api/city.js'
  export default {
    data() {
      return {
        edit: true,
        activeName: 'first',
        options: city.city(),
        dialogStatus: '',
        tablebodyHeight: window.innerHeight - 320,
        tableHeight: window.innerHeight - 300,
        textMap: {
          update: '编辑',
          create: '添加',
          search: '查询'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          area: [],
          name: '',
          sex: '',
          age: '',
          age_s: '',
          age_e: '',
          ethnic: '',
          peopleId: '',
          phone: '',
          responseDoctor: '',
          creator: '',
          createDate: '',
          livingCondition: '',
          id: '',
          recentFollow_s: '',
          recentFollow_e: ''
        },

        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          peopleId: [
            { required: true, message: '请输入身份证ID', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '你的身份证格式不正确' }
          ]
        },
        // 编辑界面数据
        editForm: {
          area: [],
          name: '',
          sex: '',
          age: '',
          ethnic: '',
          peopleId: '',
          phone: '',
          management: '',
          responseDoctor: '',
          creator: '',
          recentFollow: '',
          numberFollow: '',
          livingCondition: ''
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      handleRowChange(row, event) {
        this.$router.push({
          name: 'dashboard',
          params: {
            hid: row.peopleId
          }
        })
      },
      timeFormat: function(row, column) {
        let date = row[column.property]
        date =
          !date || date === ''
            ? ''
            : util.formatDate.format(new Date(date), 'yyyy-MM-dd')
        return date
      },
      // 性别显示转换
      formatSex: function(row, column) {
        return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
      },
      printf(content, w = null, h = null) {
        // Fixes dual-screen position                         Most browsers      Firefox
        const dualScreenLeft = window.screenLeft !== undefined ? window.screenLeft : screen.left
        const dualScreenTop = window.screenTop !== undefined ? window.screenTop : screen.top
        const width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width
        const height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height
        w = +w === 0 ? width : w
        h = +h === 0 ? height : h
        const left = ((width / 2) - (w / 2)) + dualScreenLeft
        const top = ((height / 2) - (h / 2)) + dualScreenTop
        const myWindow = window.open('', '打印', 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=yes, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left)
        const style = "<style type='text/css'>table.gridtable {font-family: verdana,arial,sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;}table.gridtable th {border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;}table.gridtable td {border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;}</style>"
        myWindow.document.write(content + style)
        myWindow.focus()
        myWindow.document.close()
        myWindow.print()
        return myWindow
      },
      conprint() {
        const windows = this.printf(document.getElementById('print').innerHTML)
        windows.close()
      },

      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      // 获取用户列表
      getUsers() {
        this.filters.createDate =
          !this.filters.createDate || this.filters.createDate === ''
            ? null
            : util.formatDate.format(new Date(this.filters.createDate), 'yyyy-MM-dd')
        this.filters.recentFollow_s =
          !this.filters.recentFollow_s || this.filters.recentFollow_s === ''
            ? null
            : util.formatDate.format(new Date(this.filters.recentFollow_s), 'yyyy-MM-dd')
        this.filters.recentFollow_e =
          !this.filters.recentFollow_e || this.filters.recentFollow_e === ''
            ? null
            : util.formatDate.format(new Date(this.filters.recentFollow_e), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          size: 20,
          filters: {
            area: '',
            name: this.filters.name,
            sex: this.filters.sex,
            age: this.filters.age,
            age_s: this.filters.age_s,
            age_e: this.filters.age_e,
            ethnic: this.filters.ethnic,
            peopleId: this.filters.peopleId,
            phone: this.filters.phone,
            responseDoctor: this.filters.responseDoctor,
            creator: this.filters.creator,
            createDate: this.filters.createDate,
            livingCondition: this.filters.livingCondition,
            id: this.filters.id,
            recentFollow_s: this.filters.recentFollow_s,
            recentFollow_e: this.filters.recentFollow_e
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
          area: [],
          name: '',
          sex: '',
          age: '',
          age_s: '',
          age_e: '',
          ethnic: '',
          peopleId: '',
          phone: '',
          responseDoctor: '',
          creator: '',
          createDate: '',
          livingCondition: '',
          id: '',
          recentFollow_s: '',
          recentFollow_e: ''
        }
      },
      // 删除
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          type: 'warning'
        })
          .then(() => {
            const para = row.id
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
      // 显示编辑界面
      handleEdit(index, row) {
        this.area = []
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.area = this.editForm.area.split('/')
        this.edit = true
      },
      //
      clear() {
        this.filters = {
          area: [],
          name: '',
          sex: '',
          age: '',
          age_s: '',
          age_e: '',
          ethnic: '',
          peopleId: '',
          phone: '',
          responseDoctor: '',
          creator: '',
          createDate: '',
          livingCondition: '',
          id: '',
          recentFollow_s: '',
          recentFollow_e: ''
        }
      },
      // 查询框
      findbtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          area: [],
          name: '',
          sex: '',
          age: '',
          age_s: '',
          age_e: '',
          ethnic: '',
          peopleId: '',
          phone: '',
          responseDoctor: '',
          creator: '',
          createDate: '',
          livingCondition: '',
          id: '',
          recentFollow_s: '',
          recentFollow_e: ''
        }
        this.area = []
      },
      // 查询
      searchs() {
        this.page = 1
        this.getUsers()
      },
      // 显示新增界面
      handleAdd() {
        this.edit = false
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.editForm = {
          area: [],
          name: '',
          sex: '',
          age: '',
          ethnic: '',
          peopleId: '',
          phone: '',
          management: '',
          responseDoctor: '',
          creator: this.$store.getters.name,
          recentFollow: '',
          numberFollow: '',
          livingCondition: ''
        }
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.modifier = this.$store.getters.name
                para.recentFollow =
                  !para.recentFollow || para.recentFollow === ''
                    ? null
                    : util.formatDate.format(new Date(para.recentFollow), 'yyyy-MM-dd')
                para.area = ''
                for (const itm of this.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
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
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            })
              .then(() => {
                this.editForm.id = (parseInt(Math.random() * 100)).toString() // mock a id
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.recentFollow =
                  !para.recentFollow || para.recentFollow === ''
                    ? null
                    : util.formatDate.format(new Date(para.recentFollow), 'yyyy-MM-dd')
                para.area = ''
                for (const itm of this.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)

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
  .mystyle{
    font-size: 14px;
    color: #323232;
  }
</style>
