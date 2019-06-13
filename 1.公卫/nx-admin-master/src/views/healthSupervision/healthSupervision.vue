<template>
  <section class="app-container">
    <!--工具条-->
    <el-col :span="24" class="toolbar col-pad-bottom">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-col span="3" class="col-mar-bottom">
          <el-form-item>
            <el-input v-model="filters.informContent" placeholder="信息内容"></el-input>
          </el-form-item>
        </el-col>
        <el-form-item>
          <el-input v-model="filters.reporter" placeholder="报告人"></el-input>
        </el-form-item>
        <el-form-item>
          <el-date-picker type="date" v-model="filters.findTime_s" placeholder="起始发现时间"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-date-picker type="date" v-model="filters.findTime_e" placeholder="结束发现时间"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="search" class="search" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button v-if="buttons.HSH_C" id='ces' type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>


    <el-table :data="users" :height="tableHeight" highlight-current-row border @selection-change="selsChange" class="el-table" @row-dblclick="handleRowChange">
      <el-table-column type="index" label="序号" width="70" align="center">
      </el-table-column>
      <el-table-column prop="organization" label="机构名称" width="150" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="findTime" label="发现时间" width="120" align="center">
      </el-table-column>
      <el-table-column prop="informType" label="信息类别" width="150" align="center">
      </el-table-column>
      <el-table-column prop="informContent" label="信息内容" width="240" align="center">
      </el-table-column>
      <el-table-column prop="reporter" label="报告人" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="reporteTime" label="报告时间" width="120" align="center">
      </el-table-column>
      <el-table-column prop="creator" label="创建人" width="120" align="center">
      </el-table-column>
      <el-table-column prop="createDate" label="创建时间" width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="modifier" label="修改人" width="120" align="center">
      </el-table-column>
      <el-table-column prop="modifyDate" label="修改时间" width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <a v-if="buttons.HSH_E" class="edit" size="14px" @click="handleEdit(scope.$index, scope.row)">编辑</a>
          <a v-if="buttons.HSH_D" class="delete" size="14px" @click="handleDel(scope.$index, scope.row)">删除</a>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar col-page">
      <el-pagination class="page" layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <div id="print">
      <el-form :model="editForm" label-width="0px" :rules="editFormRules" ref="editForm" aria-readonly="true">
        <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd">
          <tbody>
          <tr v-if="dialogStatus==='detail'">
            <td class="tdb">创建人</td>
            <td colspan="1" class="pad10">
                <el-input v-model="editForm.creator" class="full" v-if="dialogStatus==='detail'" readonly></el-input>
            </td>
            <td class="tdb">创建时间</td>
            <td colspan="1" class="pad10">
                <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" class="full" v-if="dialogStatus==='detail'" readonly></el-date-picker>
            </td>
          </tr>
          <tr v-if="dialogStatus==='detail'">
            <td class="tdb">修改人</td>
            <td class="pad10" colspan="1">
                <el-input v-model="editForm.modifier" class="full" v-if="dialogStatus==='detail'" readonly></el-input>
            </td>
            <td class="tdb">修改时间</td>
            <td class="pad10" colspan="1">
                <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" class="full" v-if="dialogStatus==='detail'" readonly></el-date-picker>
            </td>
          </tr>
          <tr>
            <td class="tdb">机构名称</td>
            <td class="pad10" colspan="3"><el-input v-model="editForm.organization"></el-input></td>
          </tr>
          <tr>
            <td class="informType">发现时间</td>
            <td class="pad10 td-width" colspan="1">
              <el-date-picker type="date" placeholder="选择日期" v-model="editForm.findTime" class="full"></el-date-picker>
            </td>
            <td class="informType">信息类别</td>
            <td class="pad10 td-width" colspan="1">
                <el-select v-model="editForm.informType" placeholder="请选择" class="full">
                  <el-option key="syxjb" label="食源性疾病" value="食源性疾病"></el-option>
                  <el-option key="yysws" label="饮用水卫生" value="用水卫生"></el-option>
                  <el-option key="xxws" label="学校卫生" value="学校卫生"></el-option>
                  <el-option key="ffxy" label="非法行医(采供血)" value="非法行医(采供血)"></el-option>
                  <el-option key="jhsy" label="计划生育" value="计划生育"></el-option>
                </el-select>
            </td>
          </tr>
          <tr>
            <td class="informContent"><span>*</span>信息内容</td>
            <td class="pad10 td-informContent" colspan="3">
              <el-form-item label="" prop="informContent">
                <el-input type="textarea" rows="6" v-model="editForm.informContent" placeholder="请输入"></el-input>
              </el-form-item>
            </td>
          </tr>
          <tr>
            <td class="tdb">报告人</td>
            <td class="pad10" colspan="1">
              <el-input v-model="editForm.reporter" class="full"></el-input>
            </td>
            <td class="tdb">报告时间</td>
            <td class="pad10" colspan="1">
              <el-date-picker type="date" placeholder="选择日期" v-model="editForm.reporteTime" class="full"></el-date-picker>
            </td>
          </tr>
          </tbody>
        </table>
      </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="conprint()">打印</el-button>
        <el-button v-if="dialogStatus==='create'||dialogStatus==='update'" type="primary" @click="addData">添加</el-button>
        <el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
  import util from '@/utils/table.js'
  import {
    getList,
    removeData,
    batchRemoveData,
    updateData,
    addData
  } from '@/api/healthSupervision'
  export default {
    data() {
      return {
        edit: true,
        dialogStatus: '',
        tableHeight: window.innerHeight - 220,
        textMap: {
          update: '编辑',
          create: '卫生监督协管新增',
          detail: '详细'
        },
        dialogFormVisible: false,
        filters: {
          informContent: '',
          findTime_s: null,
          findTime_e: null,
          reporter: ''
        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          informContent: [{ required: true, message: ' ', trigger: 'blur' }],
          findTime: [{ required: true, message: '请输入发现时间', trigger: 'blur' }],
          reporteTime: [{ required: true, message: '请输入报告时间', trigger: 'blur' }]
        },
        // 编辑界面数据
        editForm: {
          organization: '',
          findTime: '',
          informType: '',
          informContent: '',
          reporteTime: null,
          reporter: '',
          creator: '',
          createDate: null,
          modifier: '',
          modifyDate: null
        },
        buttons: {
          HSH_C: false,
          HSH_E: false,
          HSH_D: false
        },
        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          findTime: [{ required: true, message: '请输入发现时间', trigger: 'blur' }],
          reporteTime: [{ required: true, message: '请输入报告时间', trigger: 'blur' }]
        },
        div: '<div id="dataPrint" style="height:100%; margin-left:13px;margin-right:15px;display:none;"> </div>\n' +
          '<div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
          '<div id="topContent">\n' +
          '    <h2 style="width:794px;text-align:center"> 卫生计生监督协管信息报告登记表</h2>\n' +
          '    <br>\n' +
          '    <br>\n' +
          '    <span style="margin-left:34px;">机构名称:</span><span style="margin-left:0px;">{{organization}}</span> </div>\n' +
          '  <table id="table"  border="1"  style="width:730px;margin-left:30px;margin-top:5px;" cellspacing="0" bordercolor="#000000" width = "80%"style="border-collapse:collapse;">\n' +
          '    <tbody>\n' +
          '      <tr>\n' +
          '        <td style="width:7%;text-align:center;height:60px">序号</td>\n' +
          '        <td style="width: 13%;text-align:center">发现时间</td>\n' +
          '        <td style="width: 13%;text-align:center">信息类别</td>\n' +
          '        <td style="width: 30%;text-align:center">信息内容</td>\n' +
          '        <td style="width: 13%;text-align:center">报告时间</td>\n' +
          '        <td style="width: 13%;text-align:center">报告人</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width:7%;text-align:center;height:60px">1</td>\n' +
          '        <td style="width: 13%;text-align:center">{{findTime}}</td>\n' +
          '        <td style="width: 13%;text-align:center">{{informType}}</td>\n' +
          '        <td style="width: 30%;text-align:center">{{informContent}}</td>\n' +
          '        <td style="width: 13%;text-align:center">{{reporteTime}}</td>\n' +
          '        <td style="width: 13%;text-align:center">{{reporter}}</td>\n' +
          '      </tr> \n' +
          '      <tr>\n' +
          '        <td style="width:7%;text-align:center;height:60px"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '        <td style="width: 40%;text-align:center"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '      </tr> \n' +
          '      <tr>\n' +
          '        <td style="width:7%;text-align:center;height:60px"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '        <td style="width: 40%;text-align:center"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '      </tr> \n' +
          '      <tr>\n' +
          '        <td style="width:7%;text-align:center;height:60px"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '        <td style="width: 40%;text-align:center"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '      </tr> \n' +
          '      <tr>\n' +
          '        <td style="width:7%;text-align:center;height:60px"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '        <td style="width: 40%;text-align:center"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '        <td style="width: 13%;text-align:center"></td>\n' +
          '      </tr> \n' +
          '  </table>\n' +
          '  <div id="footerContent"> <span style="margin-left:33px;margin-top:-5px">注： <span style="margin-left:5px"></span>信息类别：食源性疾病、饮用水卫生、学校卫生、非法行医(采供血)、计划生育 <span style="margin-left:51px"></span><br><span style="margin-left:74px"></span>信息内容：注明发现问题(隐患)的地点、内容等有关情况简单描述</span></div>\n' +
          '</div>'
      }
    },
    methods: {
      // 性别显示转换
      formatSex: function(row, column) {
        return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
      },
      printf(content, w = null, h = null) {
        // Fixes d ual-screen position                         Most browsers      Firefox
        const dualScreenLeft = window.screenLeft !== undefined ? window.screenLeft : screen.left
        const dualScreenTop = window.screenTop !== undefined ? window.screenTop : screen.top

        const width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width
        const height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height
        w = +w === 0 ? width : w
        h = +h === 0 ? height : h
        const left = ((width / 2) - (w / 2)) + dualScreenLeft
        const top = ((height / 2) - (h / 2)) + dualScreenTop
        const myWindow = window.open('', '打印', 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=yes, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left)
        const style = "<style type='text/css'>table.gridtable {font-family: verdana,arial,sans-serif;font-size:24px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;}table.gridtable th {border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;}table.gridtable td {border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;}</style>"
        var str = style
        str = str + this.div
        str = str.replace(/{{organization}}/, this.editForm.organization ? this.editForm.organization : '')
        str = str.replace(/{{findTime}}/, this.editForm.findTime ? this.editForm.findTime : '')
        str = str.replace(/{{informType}}/, this.editForm.informType ? this.editForm.informType : '')
        str = str.replace(/{{informContent}}/, this.editForm.informContent ? this.editForm.informContent : '')
        str = str.replace(/{{reporteTime}}/, this.editForm.reporteTime ? this.editForm.reporteTime : '')
        str = str.replace(/{{reporter}}/, this.editForm.reporter ? this.editForm.reporter : '')
        myWindow.document.write(str)
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
        this.getList()
      },
      // 查询
      search() {
        this.page = 1
        this.getList()
      },
      // 获取用户列表
      getList() {
        this.filters.findTime_s =
          !this.filters.findTime_s || this.filters.findTime_s === ''
            ? null
            : util.formatDate.format(new Date(this.filters.findTime_s), 'yyyy-MM-dd')
        this.filters.findTime_e =
          !this.filters.findTime_e || this.filters.findTime_e === ''
            ? null
            : util.formatDate.format(new Date(this.filters.findTime_e), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          size: 20,
          filters: { informContent: this.filters.informContent, findTime_s: this.filters.findTime_s, findTime_e: this.filters.findTime_e, reporter: this.filters.reporter }
        }
        getList(para).then(res => {
          this.total = res.totalCount
          this.users = res.detailModelList
          if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
            this.page = this.page - 1
            this.getList()
          }
        })
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
              this.getList()
            })
          })
          .catch(() => {})
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.edit = true
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.editForm = {
          organization: '',
          findTime: '',
          informType: '',
          informContent: '',
          reporteTime: null,
          reporter: '',
          creator: this.$store.getters.name,
          createDate: null,
          modifier: '',
          modifyDate: null
        }
        this.edit = false
      },
      // 显示详细界面
      handleRowChange(row, event) {
        this.dialogStatus = 'detail'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.edit = false
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
                if (para.findTime != null) {
                  para.findTime =
                    !para.findTime || para.findTime === ''
                      ? null
                      : util.formatDate.format(new Date(para.findTime), 'yyyy-MM-dd')
                }
                para.reporteTime =
                  !para.reporteTime || para.reporteTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.reporteTime), 'yyyy-MM-dd')
                updateData(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.getList()
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
      addData: function() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认修改吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.findTime =
                  !para.findTime || para.findTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.findTime), 'yyyy-MM-dd')
                para.reporteTime =
                  !para.reporteTime || para.reporteTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.reporteTime), 'yyyy-MM-dd')
                addData(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.getList()
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
              this.getList()
            })
          })
          .catch(() => {})
      }
    },
    mounted() {
      this.buttons = this.getButton(this.buttons, this.$store.getters.buttons)
      this.getList()
    }
  }
</script>

<style scoped>
  td{
    height: 50px;
  }
  .pad10{
    padding-left: 10px;
    padding-right: 10px;
    text-align:center;
  }
  .col-pad-bottom{
    padding-bottom: 0;
  }
  .col-mar-bottom{
    margin-bottom: 0;
  }
  .search{
    background-color:#4db2ee;
    border-color:transparent;
    width: 90px;
  }
  .el-table{
    width: 100%;
    margin-top:30px;
  }
  .edit{
    color:#4DB2EE;
    margin-right:20px;
  }
  .delete{
    color:#FB4141;
  }
  .page{
    float:right;
  }
  #table{
    margin-left:10px;
    margin-right:10px;
    border-collapse:collapse;
  }
  .tdb{
    word-wrap:break-word;
    text-align:center;
  }
  .full{
    width: 100%;
  }
  .col-page{
    margin-top:30px;
  }
  .td-width{
    width:360px;
  }
  .informContent{
    height:160px;
    word-wrap:break-word;
    text-align:center;
  }
  .td-informContent{
    height:160px;
    text-align:center;
    padding-right:0;
  }
  .informType{
    word-wrap:break-word;
    text-align:center;
    width:140px;
  }
  span{
    color:red;
  }
</style>
