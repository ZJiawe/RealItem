<template xmlns:el-col="http://www.w3.org/1999/html">
  <section class="app-container">

    <!--左边工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;" >
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <table style="width: 100%;">
          <tr >
            <td width="70"><span class="mystyle">区域</span></td>
            <td><el-cascader :options="options" v-model="filters.area"  placeholder="请选择" style="width:100%;margin-right:30px;" change-on-select></el-cascader></td>
            <td width="100" style="padding-left:30px;"><span class="mystyle">责任医生</span></td>
            <td><el-input v-model="filters.responDoctor"  style="width:100%;" placeholder="请输入责任医生" clearable></el-input></td>
            <td width="90" style="padding-left:30px;"><span class="mystyle">姓名</span></td>
            <td width="120"><el-input v-model="filters.name" placeholder="请输入姓名"  style="width:100%;" clearable></el-input></td>
            <td width="90" style="padding-left:30px;"><span class="mystyle">联系电话</span></td>
            <td><el-input v-model="filters.phone" placeholder="请输入联系电话" style="width:100%;" clearable></el-input></td>
          </tr>
          <tr height="16"></tr>
          <tr>
            <td><span class="mystyle">身份证号</span></td>
            <td><el-input v-model="filters.peopleId" placeholder="请输入身份证号" style="width:100%;" clearable></el-input></td>
            <td style="padding-left:30px;"><span class="mystyle">性别</span></td>
            <td>
              <el-select v-model="filters.sex" placeholder="请选择" style="width:100%;">
                <el-option key="" label="请选择" value=""></el-option>
                <el-option key="男" label="男" value="男"></el-option>
                <el-option key="女" label="女" value="女"></el-option>
                <el-option key="未知性别" label="未知性别" value="未知性别"></el-option>
              </el-select>
            </td>
            <td style="padding-left:30px;"><span class="mystyle">民族</span></td>
            <td><el-cascader :options="ethnic" v-model="filters.ethnic"  style="width:100%;" placeholder="请选择"></el-cascader></td>
            <td style="padding-left:30px;"><span class="mystyle">建档人</span></td>
            <td><el-input v-model="filters.docPeople" placeholder="请输入建档人" style="width:100%;" clearable></el-input></td>
          </tr>
          <tr height="16"></tr>
          <tr>
            <td><span class="mystyle">建档开始</span></td>
            <td><el-date-picker type="date"  v-model="filters.docDate_s"  style="width:100%;"  placeholder="请输入日期"></el-date-picker></td>
            <td style="padding-left:30px;"><span class="mystyle">建档结束</span></td>
            <td><el-date-picker type="date"  v-model="filters.docDate_e" style="width:100%;"  placeholder="请输入日期"></el-date-picker></td>
            <td style="padding-left:30px;"><span class="mystyle">生存状态</span></td>
            <td>
              <el-select v-model="filters.state" placeholder="请选择">
                <el-option key="" label="请选择" value=""></el-option>
                <el-option key="存活" label="存活" value="存活"></el-option>
                <el-option key="死亡" label="死亡" value="死亡"></el-option>
              </el-select>
            </td>
            <td colspan="2" style="text-align:right;">
              <el-button type="primary" v-on:click="getUsers" style="background-color:#4db2ee;margin-right:10px;border-color:transparent;height:40px;" icon="el-icon-search">查询</el-button>
              <el-button type="primary" v-on:click="clear" style="background-color:#ffffff;color:#909090;border-color: #c1c1c1;font-size: 14px;margin-left:10px;height:40px;" icon="el-icon-back" >重置</el-button>
            </td>
          </tr>
        </table>
      </el-form>
    </el-col>
    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;" @row-dblclick="goto">
      <el-table-column type="index" label="序号" width="80">
      </el-table-column>
      <el-table-column prop="area" label="区域" width="250" style="text-align:center">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="80" style="text-align:center">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="60">
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="60">
      </el-table-column>
      <el-table-column prop="ethnic" label="民族" width="60">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" width="180">
      </el-table-column>
      <el-table-column prop="phone" label="联系电话" min-width="120">
      </el-table-column>
      <el-table-column prop="earlyPregnancy" label="早孕" min-width="120">
      </el-table-column>
      <el-table-column prop="responDoctor" label="责任医生" min-width="120">
      </el-table-column>
      <el-table-column prop="docDate" label="建档日期" min-width="120">
      </el-table-column>
      <el-table-column prop="docPeople" label="建档人" min-width="80">
      </el-table-column>
      <el-table-column prop="lastVisit" label="最近随访" min-width="100">
      </el-table-column>
      <el-table-column prop="first" label="第一次产前随访" min-width="140">
      </el-table-column>
      <el-table-column prop="neonatalVisit" label="随访次数" min-width="100">
      </el-table-column>
      <el-table-column prop="state" label="生存状态" min-width="120">
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">

      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="130px" :rules="editFormRules" ref="editForm">
        <EasyScrollbar>
          <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
            <el-tabs v-model="activeName">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="区域" prop="area">
                    <el-cascader :options="options" v-model="editForm.area" style="width: 100%" placeholder="请选择"></el-cascader>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="身份证号" prop="peopleId">
                    <el-input v-model="editForm.peopleId" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="姓名" prop="name">
                    <el-input v-model="editForm.name" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="年龄">
                    <el-input  v-model="editForm.age" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="性别">
                    <el-radio-group v-model="editForm.sex">
                      <el-radio label="男"></el-radio>
                      <el-radio label="女"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="早孕" prop="earlyPregnancy">
                    <el-radio-group v-model="editForm.earlyPregnancy">
                      <el-radio label="是"></el-radio>
                      <el-radio label="否"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12" v-if="false">
                  <el-form-item label="编号">
                    <el-input  v-model="editForm.id" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>

              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="民族">
                    <el-input  v-model="editForm.ethnic" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="联系方式">
                    <el-input v-model="editForm.phone" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="责任医生">
                    <el-input v-model="editForm.responDoctor" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="建档日期" prop="docDate">
                    <el-date-picker type="date"  v-model="editForm.docDate" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="建档人">
                    <el-input v-model="editForm.docPeople" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="最近随访">
                    <el-date-picker type="date"  v-model="editForm.lastVisit" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="第一次产前随访">
                    <el-date-picker type="date"  v-model="editForm.first" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="随访总次数">
                    <el-input v-model="editForm.neonatalVisit" ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="处理" prop="handle">
                    <el-radio-group v-model="editForm.handle">
                      <el-radio label="转诊"></el-radio>
                      <el-radio label="结案"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="生存状态" prop="state">
                    <el-radio-group v-model="editForm.state">
                      <el-radio label="存活"></el-radio>
                      <el-radio label="死亡"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-tabs>
          </div>
        </EasyScrollbar>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
        <el-button v-else type="primary" v-if="edit" @click="createData">新增修改</el-button>
        <el-button v-else type="primary" v-if="edit" @click="updateData">修改</el-button>
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
      </div>
    </el-dialog>
    <!--查找界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
      <el-form :model="findForm" label-width="120px" :rules="findFormRules" ref="findForm">
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" v-on:click="getUsers" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
  import util from '@/utils/table.js'
  import city from '@/api/city.js'
  import {
    getList,
    removeData,
    batchRemoveData,
    updateData,
    addData
  } from '@/api/maternalManagement'

  export default {
    data() {
      return {
        options: city.city(),
        area: [],
        edit: true,
        activeName: 'first',
        tableHeight: window.innerHeight - 320,
        tablebodyHeight: window.innerHeight - 320,
        options2: city.occurPlace(),
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '添加',
          search: '查询',
          detail: '详情'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          responDoctor: '',
          area: [],
          name: '',
          peopleId: '',
          age: null,
          sex: null,
          ethnic: [],
          phone: null,
          docPeople: null,
          state: null,
          docDate_s: null,
          docDate_e: null
        },
        ethnic: [
          { value: '汉族', label: '汉族' },
          { value: '蒙古族', label: '蒙古族' },
          { value: '回族', label: '回族' },
          { value: '藏族', label: '藏族' },
          { value: '维吾尔族', label: '维吾尔族' },
          { value: '苗族', label: '苗族' },
          { value: '苗族', label: '彝族' },
          { value: '苗族', label: '壮族' },
          { value: '布依族', label: '布依族' },
          { value: '朝鲜族', label: '朝鲜族' },
          { value: '满族', label: '满族' },
          { value: '侗族', label: '侗族' },
          { value: '瑶族', label: '瑶族' },
          { value: '白族', label: '白族' },
          { value: '土家族', label: '土家族' },
          { value: '哈尼族', label: '哈尼族' },
          { value: '哈萨克族', label: '哈萨克族' },
          { value: '傣族', label: '傣族' },
          { value: '傈僳族', label: '傈僳族' },
          { value: '黎族', label: '黎族' },
          { value: '佤族', label: '佤族' },
          { value: '畲族', label: '畲族' },
          { value: '高山族', label: '高山族' },
          { value: '拉祜族', label: '拉祜族' },
          { value: '水族', label: '水族' },
          { value: '东乡族', label: '东乡族' },
          { value: '纳西族', label: '纳西族' },
          { value: '景颇族', label: '景颇族' },
          { value: '柯尔克孜族', label: '柯尔克孜族' },
          { value: '土族', label: '土族' },
          { value: '达斡尔族', label: '达斡尔族' },
          { value: '仫佬族', label: '仫佬族' },
          { value: '羌族', label: '羌族' },
          { value: '布朗族', label: '布朗族' },
          { value: '撒拉族', label: '撒拉族' },
          { value: '毛南族', label: '毛南族' },
          { value: '仡佬族', label: '仡佬族' },
          { value: '锡伯族', label: '锡伯族' },
          { value: '阿昌族', label: '阿昌族' },
          { value: '普米族', label: '普米族' },
          { value: '塔吉克族', label: '塔吉克族' },
          { value: '怒族', label: '怒族' },
          { value: '乌孜别克族', label: '乌孜别克族' },
          { value: '俄罗斯族', label: '俄罗斯族' },
          { value: '鄂温克族', label: '鄂温克族' },
          { value: '德昂族', label: '德昂族' },
          { value: '保安族', label: '保安族' },
          { value: '裕固族', label: '裕固族' },
          { value: '京族', label: '京族' },
          { value: '塔塔尔族', label: '塔塔尔族' },
          { value: '独龙族', label: '独龙族' },
          { value: '鄂伦春族', label: '鄂伦春族' },
          { value: '赫哲族', label: '赫哲族' },
          { value: '门巴族', label: '门巴族' },
          { value: '珞巴族', label: '珞巴族' },
          { value: '基诺族', label: '基诺族' }
        ],
        ceshi: '',
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          area: [
            { required: true, message: '必填', trigger: 'blur' }
          ],
          peopleId: [
            { required: true, message: '请输入身份证ID', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '你的身份证格式不正确' }
          ],
          name: [
            { required: true, message: '输入正确的姓名', trigger: 'blur' }
          ],
          handle:
            [
              { required: true, message: '必填', trigger: 'blur' }
            ],
          docDate: [
            { required: true, message: '必填', trigger: 'blur' }
          ],
          earlyPregnancy: [
            { required: true, message: '必填', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '必填', trigger: 'blur' }
          ]
        },
        findFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        },
        // 编辑界面数据
        editForm: {
          area: '',
          id: '',
          name: '',
          sex: '',
          age: '',
          ethnic: '',
          peopleId: '',
          earlyPregnancy: '',
          responDoctor: '',
          docDate: null,
          docPeople: '',
          lastVisit: null,
          first: null,
          neonatalVisit: 0,
          handle: '',
          state: ''
        },
        // 查找界面数据

        addFormVisible: false // 新增界面是否显示
      }
    },
    methods: {
      clear() {
        this.filters = {
          responDoctor: '',
          area: [],
          name: '',
          peopleId: '',
          age: null,
          sex: null,
          ethnic: [],
          phone: null,
          docPeople: null,
          state: null,
          docDate_s: null,
          docDate_e: null
        }
        this.getUsers()
      },
      // 性别显示转换
      formatSex: function(row, column) {
        return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
      },
      goto(row, event) {
        this.$router.push({
          name: 'dashboard',
          params: {
            hid: row.peopleId
          }
        })
      },
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      search() {
        this.page = 1
        this.getUsers()
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.editForm.area = []
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.area = this.editForm.area.split('/')
        this.edit = true
      },
      // 显示新增界面
      handleAdd() {
        this.editForm.area = []
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.editForm = {
          id: '',
          name: ''
        }
        this.edit = false
      },
      // 获取用户列表
      getUsers() {
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
          filters: {
            area: this.filters.area,
            name: this.filters.name,
            peopleId: this.filters.peopleId,
            responDoctor: this.filters.responDoctor,
            docDate_s: this.filters.docDate_s,
            docDate_e: this.filters.docDate_e,
            age: this.filters.age,
            sex: this.filters.sex,
            ethnic: this.filters.ethnic,
            phone: this.filters.phone,
            docPeople: this.filters.docPeople,
            state: this.filters.state
          }
        }
        para.filters.area = ''
        for (const itm of this.filters.area) {
          para.filters.area += itm
          para.filters.area += '/'
        }
        para.filters.area = para.filters.area.substr(0, para.filters.area.length - 1)
        para.filters.ethnic = ''
        for (const itm of this.filters.ethnic) {
          para.filters.ethnic += itm
          para.filters.ethnic += '/'
        }
        para.filters.ethnic = para.filters.ethnic.substr(0, para.filters.ethnic.length - 1)
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
      // 删除
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
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
                if (para.neonatalVisit == null) {
                  para.neonatalVisit = 0
                }
                // 获取级联数据
                para.area = ''
                for (const itm of this.editForm.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                para.docDate =
                  !para.docDate || para.docDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.docDate), 'yyyy-MM-dd')
                para.lastVisit =
                  !para.lastVisit || para.lastVisit === ''
                    ? ''
                    : util.formatDate.format(new Date(para.lastVisit), 'yyyy-MM-dd')
                para.first =
                  !para.first || para.first === ''
                    ? ''
                    : util.formatDate.format(new Date(para.first), 'yyyy-MM-dd')
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
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                if (para.neonatalVisit == null) {
                  para.neonatalVisit = 0
                }
                if (para.lastVisit == null) {
                  para.lastVisit = new Date()
                }
                console.log(para)
                para.area = ''
                for (const itm of this.editForm.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                para.docDate =
                  !para.docDate || para.docDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.docDate), 'yyyy-MM-dd')
                para.lastVisit =
                  !para.lastVisit || para.lastVisit === ''
                    ? ''
                    : util.formatDate.format(new Date(para.lastVisit), 'yyyy-MM-dd')
                para.first =
                  !para.first || para.first === ''
                    ? ''
                    : util.formatDate.format(new Date(para.first), 'yyyy-MM-dd')
                addData(para).then(res => {
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
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(() => {
            const para = { ids: ids }
            batchRemoveData(para).then(res => {
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


