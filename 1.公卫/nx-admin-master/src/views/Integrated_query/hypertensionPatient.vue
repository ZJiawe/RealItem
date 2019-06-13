<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <table>
          <tr>
            <td width="100px" style="padding-left:30px;"><span class="mystyle">区域</span></td>
            <td>
              <el-cascader :options="options" v-model="filters.area" placeholder="区域" style="width:100%" change-on-select ></el-cascader>
            </td>

            <td width="100px" style="padding-left:30px;"><span class="mystyle">姓名</span></td>
            <td><el-input v-model="filters.name" placeholder="姓名" style="width: 100%;"></el-input></td>

            <td width="100px" style="padding-left:30px;"><span class="mystyle">身份证号</span></td>
            <td><el-input v-model="filters.peopleId" placeholder="身份证号" style="width: 100%;"></el-input></td>

            <td width="90px" style="padding-left:30px;"><span class="mystyle">性别</span></td>
            <td>
              <el-select v-model="filters.sex" placeholder="性别" style="width: 100%;">
                <el-option key="1" label="男" value="男"></el-option>
                <el-option key="2" label="女" value="女"></el-option>
              </el-select>
            </td>
          </tr>

          <tr>
            <td width="100px" style="padding-left:30px;"><span class="mystyle">民族</span></td>
            <td>
              <el-select v-model="filters.ethnic" placeholder="民族" style="width: 100%;">
                <el-option key="1" label="汉" value="汉"></el-option>
                <el-option key="2" label="其他" value="其他"></el-option>
              </el-select>
            </td>

            <td width="100px" style="padding-left:30px;"><span class="mystyle">建档人</span></td>
            <td><el-input v-model="filters.docPeople" placeholder="建档人" style="width: 100%;"></el-input></td>

            <td width="100px" style="padding-left:30px;"><span class="mystyle">生存状态</span></td>
            <td>
              <el-select v-model="filters.total" placeholder="生存状态" style="width: 100%;">
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

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
      <el-form :model="findForm" label-width="100px" :rules="findFormRules" ref="findForm">
        <el-row>
          <el-col :span="12">
        <el-form-item label="性别">
          <el-input v-model="filters.sex" placeholder="性别"></el-input>
        </el-form-item>
          </el-col>
          <el-col :span="12" >
        <el-form-item label="身份证号">
          <el-input v-model="filters.peopleId" placeholder="身份证号"></el-input>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="地区">
              <el-input v-model="filters.area" placeholder="地区" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
        <el-form-item label="联系电话">
          <el-input v-model="filters.phone" placeholder="联系电话"></el-input>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="责任医生">
          <el-input v-model="filters.responDoctor" placeholder="责任医生"></el-input>
        </el-form-item>
          </el-col>
          <el-col :span="12" >
        <el-form-item label="建档人">
          <el-input v-model="filters.docPeople" placeholder="建档人"></el-input>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="生存状态">
              <el-input v-model="filters.total" placeholder="生存状态"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
        <el-form-item label="随访次数">
          <el-input v-model="filters.neonatalVisit" placeholder="随访次数"></el-input>
        </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col :span="12">
            <el-form-item label="最近随访">
              <el-date-picker type="date" placeholder="选择日期" v-model="filters.lastVisit_s" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-date-picker type="date" placeholder="选择日期" v-model="filters.lastVisit_e" style="width: 100%;"></el-date-picker>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="建档日期">
          <el-date-picker type="date" placeholder="选择日期" v-model="filters.docDate_s" style="width: 100%;"></el-date-picker>
        </el-form-item>
          </el-col>
          <el-col :span="12" >
          <el-date-picker type="date" placeholder="选择日期" v-model="filters.docDate_e" style="width: 100%;"></el-date-picker>
          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" v-on:click="searchs" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
      </div>

    </el-dialog>



    <!--列表-->
    <el-table :data="users" :height="tableMeight" highlight-current-row @selection-change="selsChange"  border style="width: 100%;margin-top: -20px;"@row-dblclick="handleRowChange">
      <el-table-column type="index" label="序号" width="80">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="80">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="60" >
      </el-table-column>
      <el-table-column prop="age" v-if='true'label="年龄" width="60">
      </el-table-column>
      <el-table-column prop="ethnic" label="民族" min-width="80">
      </el-table-column>
      <el-table-column prop="area" label="地区" min-width="250">
      </el-table-column>
      <el-table-column prop="ismanagement"  v-if='false' label="是否接受管理" min-width="120">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" width="180">
      </el-table-column>
      <el-table-column prop="phone" label="联系电话" min-width="120">
      </el-table-column>
      <el-table-column prop="responDoctor"  label="责任医生" min-width="120">
      </el-table-column>
      <el-table-column prop="docDate"  v-if='true' label="建档日期" min-width="120">
      </el-table-column>
      <el-table-column prop="docPeople"  v-if='true' label="建档人" min-width="120">
      </el-table-column>
      <el-table-column prop="lastVisit"  v-if='true' label="最近随访" min-width="120">
      </el-table-column>
      <el-table-column prop="neonatalVisit"  v-if='true' label="随访次数" min-width="120">
      </el-table-column>
      <el-table-column prop="total"  v-if='true' label="生存状态" min-width="120">
      </el-table-column>
      <!--<el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <a size="14px" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
        </template>
      </el-table-column>-->
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="100px" :rules="editFormRules" ref="editForm">
        <EasyScrollbar>
          <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
              <el-tabs v-model="activeName">

           <!-- <el-tab-pane :label="`基本信息`" name="first">-->

              <el-row>
                <el-col :span="12">
                  <el-form-item label="姓名" prop="name">
                    <el-input v-model="editForm.name" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="12" >
                  <el-form-item label="性别" prop="">
                    <el-radio-group v-model="editForm.sex">
                      <el-radio label="男"></el-radio>
                      <el-radio label="女"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="生存状态">
                    <el-input v-model="editForm.total" auto-complete="off"></el-input>
                  </el-form-item>
                  <!--<el-form-item label="年龄">
                    <el-input  v-model="editForm.age" auto-complete="off" ></el-input>
                  </el-form-item>-->
                </el-col>

                <el-col :span="12" >
                  <el-form-item label="身份证号" prop="peopleId">
                    <el-input v-model="editForm.peopleId" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="民族">
                    <el-input  v-model="editForm.ethnic" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="12" >
                  <el-form-item label="联系方式" prop="">
                    <el-input v-model="editForm.phone" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="地区">
                    <el-input v-model="editForm.area" auto-complete="off" change-on-select></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="12" >
                  <el-form-item label="建档人" prop="">
                    <el-input  v-model="editForm.docPeople" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

           <!-- </el-tab-pane>

            <el-tab-pane :label="`院方信息`" name="second">-->

              <el-row>
                <el-col :span="12">
                  <el-form-item label="最近随访" prop="">
                    <el-input v-model="editForm.lastVisit" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>



                <el-col :span="12" >
                  <el-form-item label="随访次数" prop="">
                    <el-input v-model="editForm.neonatalVisit" ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="责任医生">
                    <el-input v-model="editForm.responDoctor" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>


              </el-row>

           <!-- </el-tab-pane>-->

          </el-tabs>
        </div>
        </EasyScrollbar>
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
  } from '@/api/hyperSelect'
  import city from '@/api/city.js'
  export default {
    data() {
      return {
        edit: true,
        activeName: 'first',
        dialogStatus: '',
        tableMeight: window.innerHeight - 300,
        tablebodyHeight: window.innerHeight - 320,
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
          ethnic: '',
          age_s: '',
          age_e: '',
          sex: '',
          peopleId: '',
          phone: '',
          responDoctor: '',
          docPeople: '',
          lastVisit: '',
          neonatalVisit: '',
          total: '',
          area: '',
          lastVisit_s: '',
          lastVisit_e: '',
          docDate_s: '',
          docDate_e: ''
        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [
            { required: true, message: '输入正确的姓名', trigger: 'blur' },
            { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: '用户名只能为中文'
            }
          ],
          peopleId: [
            { required: true, message: '请输入身份证ID', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '你的身份证格式不正确' }
          ]
        },
        // 编辑界面数据
        editForm: {
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
          area: [],
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
        this.filters.docDate_s =
          !this.filters.docDate_s || this.filters.docDate_s === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.docDate_s), 'yyyy-MM-dd')
        this.filters.docDate_e =
          !this.filters.docDate_e || this.filters.docDate_e === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.docDate_e), 'yyyy-MM-dd')
        this.filters.lastVisit_s =
          !this.filters.lastVisit_s || this.filters.lastVisit_s === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.lastVisit_s), 'yyyy-MM-dd')
        this.filters.lastVisit_e =
          !this.filters.lastVisit_e || this.filters.lastVisit_e === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.lastVisit_e), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          size: 20,
          filters: { name: this.filters.name, ethnic: this.filters.ethnic, age_s: this.filters.age_s, age_e: this.filters.age_e, sex: this.filters.sex, ismanagement: this.filters.ismanagement, peopleId: this.filters.peopleId, phone: this.filters.phone, responDoctor: this.filters.responDoctor, docPeople: this.filters.docPeople, lastVisit: this.filters.lastVisit, neonatalVisit: this.filters.neonatalVisit, total: this.filters.total, area: this.filters.area, lastVisit_s: this.filters.lastVisit_s, lastVisit_e: this.filters.lastVisit_e, docDate_s: this.filters.docDate_s, docDate_e: this.filters.docDate_e
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
        })
        if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
          this.page = this.page - 1
          this.getUsers()
        }
      },
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
          lastVisit: '',
          neonatalVisit: '',
          total: '',
          area: [],
          ethnic: '',
          lastVisit_s: '',
          lastVisit_e: '',
          docDate_s: '',
          docDate_e: ''
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
          ethnic: '',
          lastVisit_s: '',
          lastVisit_e: '',
          docDate_s: '',
          docDate_e: ''
        }
      },
      handleRowChange(row, event) {
        this.$router.push({
          name: 'dashboard',
          params: {
            hid: row.peopleId
          }
        })
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

