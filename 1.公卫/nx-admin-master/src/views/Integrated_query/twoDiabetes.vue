<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="24" class="toolbar" >
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <table>
          <tr>
            <td width="100px" style="padding-left:30px;"><span class="mystyle">区域</span></td>
            <td>
        <el-cascader :options="options" v-model="filters.area"  style="width: 100%" change-on-select placeholder="区域"></el-cascader>
            </td>
            <td width="100px" style="padding-left:30px;"><span class="mystyle">姓名</span></td>
            <td> <el-input v-model="filters.name" style="width: 100%;" placeholder="姓名"></el-input></td>
            <td width="100px" style="padding-left:30px;"><span class="mystyle">身份证号</span></td>
            <td>  <el-input v-model="filters.peopleId"  style="width: 100%;"  placeholder="身份证号"></el-input></td>
            <td width="100px" style="padding-left:30px;"><span class="mystyle">联系电话</span></td>
           <td> <el-input v-model="filters.phone" style="width: 100%;"  placeholder="联系电话"></el-input></td>
            <td width="100px" style="padding-left:30px;"><span class="mystyle">性别</span></td>
           <td> <el-select v-model="filters.sex" placeholder="性别" style="width: 100%;">
            <el-option key="男" label="男" value="男"></el-option>
          <el-option key="女" label="女" value="女"></el-option>
          <el-option key="未知性别" label="未知性别" value="未知性别"></el-option>
        </el-select>
           </td>
          </tr>
        <tr>
            <td width="100px" style="padding-left:30px;"><span class="mystyle">民族</span></td>
            <td><el-cascader :options="ethnic" v-model="filters.ethnic"  style=" width: 100%;" placeholder="民族"></el-cascader> </td>
            <td width="100px" style="padding-left:30px;"><span class="mystyle">建档人</span></td>
            <td><el-input v-model="filters.creator"  style=" width: 100%;" placeholder="建档人"></el-input></td>
            <td width="100px" style="padding-left:30px;"><span class="mystyle">生存状态</span></td>
            <td>
        <el-select v-model="filters.liveStatus" placeholder="生存状态" style="width: 100%">
          <el-option key="0" label="" value=""></el-option>
          <el-option key="1" label="存活" value="存活"></el-option>
          <el-option key="2" label="死亡" value="死亡"></el-option>
        </el-select>
            </td>
            <td style="padding-left:10px;">
              <el-form-item>
        <el-button type="primary" v-on:click="search" style="background-color:#4db2ee;border-color:transparent;height: 40px;" icon="el-icon-search">查询</el-button>
              </el-form-item>
            </td>
            <td>
            <el-form-item>
        <el-button type="primary" v-on:click="cz" style="background-color: #4db2ee;border-color: transparent;height: 40px;" icon="el-icon-back">清除</el-button>
            </el-form-item>
            </td>
          </tr>
        </table>
      </el-form>
    </el-col>
    <!--列表-->
    <div id="print">
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange"  border style="width: 100%;"@row-dblclick="handleRowChange">
      <el-table-column type="index" label="序号" width="80">
      </el-table-column>
      <el-table-column prop="area" label="区域" min-width="250" align="center">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" min-width="180" align="center">
      </el-table-column>
      <el-table-column prop="name" label="姓名" min-width="80" align="center">
      </el-table-column>
      <el-table-column prop="sex" label="性别" min-width="60"  align="center">
      </el-table-column>
      <el-table-column prop="ethnic" label="民族" min-width="60" align="center">
      </el-table-column>
      <el-table-column prop="age" label="年龄" min-width="60" align="center">
      </el-table-column>
      <el-table-column prop="phone" label="联系电话" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="responDoctor" label="责任医生" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="followVisit" label="最近随访" :formatter="timeFormat"  min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="count" label="随访次数" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="liveStatus" label="生存状态" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="creator" label="建档人" min-width="80" align="center">
      </el-table-column>
      <el-table-column prop="createDate" label="建档日期" :formatter="timeFormat" min-width="100" align="center">
      </el-table-column>
    </el-table>
  </div>
    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-pagination layout="prev, pager, next" :current-page="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <div class="container">
          <el-tabs v-model="message">
            <el-row>
              <el-col :span="12">
              <el-form-item label="姓名" prop="name">
                  <el-input v-model="editForm.name" auto-complete="off"></el-input>
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
              <el-form-item label="性别">
                  <el-radio-group v-model="editForm.sex">
                    <el-radio label=男>男</el-radio>
                    <el-radio label=女>女</el-radio>
                    <el-radio label=未知性别>未知性别</el-radio>
                  </el-radio-group>
              </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="民族">
                    <el-input v-model="editForm.ethnic"></el-input>
                  </el-form-item>
                </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
              <el-form-item label="身份证号">
                  <el-input v-model="editForm.peopleId"></el-input>
              </el-form-item>
              </el-col>
                <el-col :span="12" >
                  <el-form-item label="联系电话">
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
                <el-col :span="12" >
                  <el-form-item label="区域">
                    <el-input v-model="editForm.area"></el-input>
                  </el-form-item>
                </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
              <el-form-item label="随访次数">
                  <el-input v-model="editForm.count" ></el-input>
              </el-form-item>
                </el-col>
                <el-col :span="12" >
                  <el-form-item label="最近随访">
                    <el-date-picker type="date"  v-model="editForm.followVisit" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
            <el-form-item label="生存状态">
              <el-input v-model="editForm.liveStatus"  ></el-input>
            </el-form-item>
              </el-col>
            </el-row>

          </el-tabs>
        </div>
      </el-form>
      <!--<div slot="footer" class="dialog-footer">-->
        <!--<el-button @click.native="dialogFormVisible=false">取消</el-button>-->
        <!--<el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>-->
        <!--<el-button v-else type="primary" @click="updateData">修改</el-button>-->
      <!--</div>-->
    </el-dialog>

  </section>
</template>

<script>
  import util from '@/utils/table.js'
  import {
    getList,
    removeData,
    batchRemoveData,
    editData,
    addData
  } from '@/api/diabetesSelect'
  import Print from 'print-js'
  import city from '@/api/city.js'
  export default {
    data() {
      return {
        edit: true,
        options: city.city(),
        dialogStatus: '',
        tableHeight: window.innerHeight - 300,
        textMap: {
          update: '编辑',
          create: '添加',
          search: '查询',
          detail: '详情'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          name: '',
          age: '',
          startTime: '',
          endTime: '',
          peopleId: '',
          liveStatus: '',
          ethnic: [],
          doctor: '',
          sex: '',
          phone: '',
          area: [],
          creator: ''
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
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        },
        // 编辑界面数据
        editForm: {
          id: '',
          name: '',
          sex: '',
          age: '',
          area: [],
          peopleId: '',
          phone: '',
          doctor: '',
          liveStatus: '',
          followVisit: '',
          count: '',
          modifier: '',
          modifyDate: '',
          creator: '',
          ethnic: [],
          createDate: ''

        },
        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      // 日期转换
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
      // conprint() {
      //   const windows = this.printf(document.getElementById('print').innerHTML)
      //   windows.close()
      // },
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      goto(index, row) {
        this.$router.push({
          name: 'dashboard',
          params: {
            hid: row.peopleId
          }
        })
      },
      goto2() {
        this.$router.push({
          name: 'dashboard',
          params: {
            hid: name
          }
        })
      },
      conprint() {
        Print({
          printable: 'print',
          type: 'html',
          targetStyles: ['*']
        })
      },
      city: function() {
        return this.options
      },
      // 查询部分
      findbtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.filters = {
          name: '',
          age: '',
          peopleId: '',
          startTime: '',
          endTime: '',
          liveStatus: '',
          doctor: '',
          ethnic: [],
          sex: '',
          phone: '',
          area: [],
          creator: ''
        }
        this.area = []
      },
      // 查询
      search() {
        this.page = 1
        this.getUsers()
      },
      // 重置
      cz() {
        this.filters = {
          name: '',
          age: '',
          startTime: '',
          endTime: '',
          peopleId: '',
          liveStatus: '',
          doctor: '',
          ethnic: [],
          sex: '',
          phone: '',
          area: [],
          creator: ''
        }
        this.filters.ethnic = []
        this.area = []
        this.page = 1
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
          filters: {
            name: this.filters.name,
            sex: this.filters.sex,
            liveStatus: this.filters.liveStatus,
            doctor: this.filters.doctor,
            peopleId: this.filters.peopleId,
            age: this.filters.age,
            phone: this.filters.phone,
            startTime: this.filters.startTime,
            endTime: this.filters.endTime,
            creator: this.filters.creator,
            area: '', ethnic: ''
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
        // this.filters = {
        //   name: '',
        //   age: '',
        //   startTime: '',
        //   endTime: '',
        //   peopleId: '',
        //   liveStatus: '',
        //   doctor: '',
        //   ethnic: [],
        //   sex: '',
        //   phone: '',
        //   area: [],
        //   creator: ''
        // }
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
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.area = []
        this.filters.ethnic = []
        this.editForm = Object.assign({}, row)
        this.area = this.editForm.area.split('/')
        this.editForm.ethnic = this.editForm.ethnic.split('/')
        this.edit = true
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.editForm = {
          id: '',
          name: '',
          sex: '',
          age: '',
          peopleId: '',
          area: '',
          phone: '',
          doctor: '',
          liveStatus: '',
          followVisit: '',
          creator: this.$store.getters.name,
          createDate: '',
          modifier: '',
          ethnic: [],
          count: '',
          modifyDate: ''
        }
        this.area = []
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.modifier = this.$store.getters.name
                para.modifyDate = new Date()
                para.modifyDate =
                  !para.modifyDate || para.modifyDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.modifyDate), 'yyyy-MM-dd')
                para.createDate =
                  !para.createDate || para.createDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.createDate), 'yyyy-MM-dd')
                para.followVisit =
                  !para.followVisit || para.followVisit === ''
                    ? ''
                    : util.formatDate.format(new Date(para.followVisit), 'yyyy-MM-dd')
                para.area = ''
                for (const itm of this.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                para.ethnic = ''
                for (const itm of this.editForm.ethnic) {
                  para.ethnic += itm
                }
                editData(para).then(res => {
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
                para.createDate = new Date()
                para.createDate =
                  !para.createDate || para.createDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.createDate), 'yyyy-MM-dd')
                para.followVisit =
                  !para.followVisit || para.followVisit === ''
                    ? ''
                    : util.formatDate.format(new Date(para.followVisit), 'yyyy-MM-dd')
                para.area = ''
                for (const itm of this.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                para.ethnic = ''
                for (const itm of this.editForm.ethnic) {
                  para.ethnic += itm
                }
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
          type: 'warning'
        })
          .then(() => {
            const para = { ids: ids }
            batchRemoveData(para).then(res => {
              this.$message({
                message: '删除成功',
                type: 'success'
              })
              this.page = 1
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
