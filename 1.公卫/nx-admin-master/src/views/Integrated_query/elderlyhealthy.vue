<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <table style="width: 100%;">
          <tr >
            <td width="80"><span class="mystyle">姓名</span></td>
            <td width="200"><el-input v-model="filters.name" placeholder="姓名" style="width :100%;"></el-input></td>
            <td width="100" style="padding-left:30px;"><span class="mystyle">身份证号</span></td>
            <td><el-input v-model="filters.peopleId" style="width :100%;"></el-input></td>
            <td width="100" style="padding-left:30px;"><span class="mystyle">生存状态</span></td>
            <td width="120">
              <el-select @change="score" v-model="filters.total" placeholder="生存状态" style="width: 100%;">
                <el-option key="a1" label="存活" value="存活"></el-option>
                <el-option key="b1" label="死亡" value="死亡"></el-option>
              </el-select>
            </td>
            <td width="80" style="padding-left:30px;"><span class="mystyle">性别</span></td>
            <td>
              <el-select v-model="filters.sex" placeholder="请选择" style="width:100%;">
                <el-option key="" label="请选择" value=""></el-option>
                <el-option key="男" label="男" value="男"></el-option>
                <el-option key="女" label="女" value="女"></el-option>
                <el-option key="未知性别" label="未知性别" value="未知性别"></el-option>
              </el-select>
            </td>
          </tr>
          <tr height="16"></tr>
          <tr>
            <td><span class="mystyle">区域</span></td>
            <td><el-cascader :options="options" v-model="filters.area"  placeholder="请选择" style="width:100%;margin-right:30px;" change-on-select></el-cascader></td>
            <td style="padding-left:30px;"><span class="mystyle">联系电话</span></td>
            <td><el-input v-model="filters.phone" placeholder="联系电话" style="width :100%;"></el-input></td>
            <td style="padding-left:30px;"><span class="mystyle">责任医生</span></td>
            <td> <el-input v-model="filters.responDoctor" placeholder="责任医生" style="width :100%;"></el-input></td>
            <td style="padding-left:30px;"><span class="mystyle">建档人</span></td>
            <td><el-input v-model="filters.docPeople" placeholder="建档人" style="width :100%;"> </el-input></td>
          </tr>
          <tr height="16"></tr>
          <tr>
            <td><span class="mystyle">随访开始</span></td>
            <td><el-date-picker type="date" placeholder="随访开始日期" v-model="filters.lastVisit_s" style="width: 100%;"></el-date-picker></td>
            <td style="padding-left:30px;"><span class="mystyle">随访结束</span></td>
            <td><el-date-picker type="date" placeholder="随访结束日期" v-model="filters.lastVisit_e" style="width: 100%;"></el-date-picker></td>
            <td colspan="2"></td>
            <td colspan="2" style="text-align:right;">
              <el-button type="primary" v-on:click="searchs" style="background-color:#4db2ee;margin-right:10px;border-color:transparent;height:40px;" icon="el-icon-search">查询</el-button>
              <el-button type="primary" v-on:click="clear" style="background-color:#ffffff;color:#909090;border-color: #c1c1c1;font-size: 14px;margin-left:10px;height:40px;" icon="el-icon-back" >重置</el-button>
            </td>
          </tr>
        </table>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="users" :height="tableMeight" highlight-current-row @selection-change="selsChange" style="width: 100%;" border @row-dblclick="goto" >
      <el-table-column type="index" label="序号" width="80">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="80">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="60" >
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="60">
      </el-table-column>
      <el-table-column prop="ethnic" label="民族" min-width="120">
      </el-table-column>
      <el-table-column prop="area" label="地区" min-width="250">
      </el-table-column>
      <!--<el-table-column prop="ismanagement"  v-if='false' label="是否接受管理" min-width="120">-->
      <!--</el-table-column>-->
      <el-table-column prop="peopleId" label="身份证号" width="180">
      </el-table-column>
      <el-table-column prop="phone" label="联系电话" min-width="120">
      </el-table-column>
      <el-table-column prop="responDoctor" label="责任医生" min-width="120">
      </el-table-column>
      <el-table-column prop="total"  label="生存状态" min-width="120">
      </el-table-column>
      <el-table-column prop="docPeople"  label="建档人" min-width="120">
      </el-table-column>
      <el-table-column prop="docDate"  label="建档日期" min-width="120">
      </el-table-column>
      <el-table-column prop="lastVisit"  label="最近随访" min-width="120">
      </el-table-column>
      <el-table-column prop="neonatalVisit"  label="随访次数" min-width="120">
      </el-table-column>
      <!--<el-table-column prop="creator" label="创建人" width="120" align="center"  >-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="createDate" label="创建时间" width="120" align="center" :formatter="timeFormat">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="modifier" label="修改人" width="120" align="center"  >-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="modifyDate" label="修改时间" width="120" align="center" :formatter="timeFormat">-->
      <!--</el-table-column>-->
      <el-table-column label="操作" width="150" v-if='false'>
        <template slot-scope="scope">
          <a size="14px" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="100px" :rules="editFormRules" ref="editForm">
        <div class="container">
          <el-tabs v-model="activeName">
            <el-row>
              <el-col :span="12">
                <el-form-item label="姓名" prop="name">
                  <el-input v-model="editForm.name" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="编号">
                  <el-input  v-model="editForm.id" auto-complete="off" ></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="性别" prop="">
                  <el-radio-group v-model="editForm.sex">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="年龄">
                  <el-input  v-model="editForm.age" auto-complete="off" ></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="身份证号" prop="">
                  <el-input v-model="editForm.peopleId" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="民族">
                  <el-input  v-model="editForm.ethnic" auto-complete="off" ></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="联系方式" prop="">
                  <el-input v-model="editForm.phone" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="地区">
                  <el-cascader :options="options" v-model="area" style="width: 100%" placeholder="请选择"></el-cascader>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="建档人" prop="">
                  <el-input  v-model="editForm.docPeople" auto-complete="off" ></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="最近随访">
                  <el-date-picker type="date" placeholder="最近随访" v-model="editForm.lastVisit" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="随访次数" prop="">
                  <el-input v-model="editForm.neonatalVisit" ></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="生存状态">
                  <el-select v-model="editForm.total" placeholder="生存状态" style="width: 100%;">
                    <el-option key="1" label="存活" value="存活"></el-option>
                    <el-option key="2" label="死亡" value="死亡"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="是否规范管理" prop="">
                  <el-radio-group v-model="editForm.ismanagement">
                    <el-radio label="是"></el-radio>
                    <el-radio label="否"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="责任医生">
                  <el-input v-model="editForm.responDoctor" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

          </el-tabs>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
        <el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
      </div>
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
  } from '@/api/elderlyhealthy'
  import city from '@/api/city.js'
  export default {
    data() {
      return {
        edit: true,
        activeName: 'first',
        dialogStatus: '',
        tableMeight: window.innerHeight - 320,
        options: city.city(),
        area: [],
        textMap: {
          update: '编辑',
          create: '新增',
          search: '查询',
          detail: '详情'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          name: '',
          age_s: '',
          age_e: '',
          sex: '',
          peopleId: '',
          phone: '',
          responDoctor: '',
          docPeople: '',
          lastVisit_s: '',
          lastVisit_e: '',
          total: '',
          area: [],
          docDate_s: '',
          docDate_e: ''
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
          creator: '1',
          createDate: '',
          modifier: '1',
          modifyDate: '',
          peopleId: '',
          phone: '',
          docPeople: '',
          responDoctor: '',
          lastVisit: '',
          neonatalVisit: '',
          total: '',
          area: '',
          ethnic: '',
          docDate: ''
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      clear() {
        this.filters = {
          name: '',
          age_s: '',
          age_e: '',
          sex: '',
          peopleId: '',
          phone: '',
          responDoctor: '',
          docPeople: '',
          lastVisit_s: '',
          lastVisit_e: '',
          total: '',
          area: [],
          docDate_s: '',
          docDate_e: ''
        }
        this.getUsers()
      },
      goto(row, event) {
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
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      // 获取用户列表
      getUsers() {
        this.dialogFormVisible2 = false
        this.filters.lastVisit_s =
          !this.filters.lastVisit_s || this.filters.lastVisit_s === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.lastVisit_s), 'yyyy-MM-dd')
        this.filters.lastVisit_e =
          !this.filters.lastVisit_e || this.filters.lastVisit_e === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.lastVisit_e), 'yyyy-MM-dd')

        this.filters.docDate_s =
          !this.filters.docDate_s || this.filters.docDate_s === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.docDate_s), 'yyyy-MM-dd')
        this.filters.docDate_e =
          !this.filters.docDate_e || this.filters.docDate_e === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.docDate_e), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          size: 20,
          filters: { name: this.filters.name,
            sex: this.filters.sex,
            peopleId: this.filters.peopleId,
            area: this.filters.area,
            phone: this.filters.phone,
            docPeople: this.filters.docPeople,
            total: this.filters.total,
            lastVisit_s: this.filters.lastVisit_s,
            lastVisit_e: this.filters.lastVisit_e
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
        if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
          this.page = this.page - 1
          this.getUsers()
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

      findbtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          name: '',
          age: '',
          sex: '',
          peopleId: '',
          phone: '',
          responDoctor: '',
          docPeople: '',
          lastVisit: '',
          neonatalVisit: '',
          total: '',
          area: '',
          docDate_s: '',
          docDate_e: '',
          lastVisit_s: '',
          lastVisit_e: ''
        }
      },
      handleRowChange(row, event) {
        this.dialogStatus = 'datail'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.edit = false
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
      searchs() {
        this.page = 1
        this.getUsers()
      },
      // 显示新增界面
      handleAdd() {
        this.area = []
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.editForm = {
          name: '',
          sex: '',
          age: '',
          ismanagement: '',
          peopleId: '',
          phone: '',
          docPeople: '',
          responDoctor: '',
          lastVisit: '',
          neonatalVisit: '',
          total: '',
          area: '',
          ethnic: '',
          docDate: ''
        }
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.modifier = this.$store.getters.name
                para.lastVisit =
                  !para.lastVisit || para.lastVisit === ''
                    ? ''
                    : util.formatDate.format(new Date(para.lastVisit), 'yyyy-MM-dd')
                para.area = ''
                for (const itm of this.editForm.area) {
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
              cancelButtonText: '取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.lastVisit =
                  !para.lastVisit || para.lastVisit === ''
                    ? ''
                    : util.formatDate.format(new Date(para.lastVisit), 'yyyy-MM-dd')
                para.docDate = new Date()
                para.docDate =
                  !para.docDate || para.docDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.docDate), 'yyyy-MM-dd')
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

