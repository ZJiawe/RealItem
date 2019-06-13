<template>
  <section class="app-container">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.meal" placeholder="进餐..."></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="search" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="searchwindow" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">高级查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd"icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

 <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false"center>
   <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
     <el-row>
       <el-col :span="12">
         <el-form-item label="进餐">
           <el-input v-model="filters.meal" placeholder="进餐"></el-input>
         </el-form-item>
       </el-col>
     </el-row>
     <el-row>
       <el-col :span="12">
         <el-form-item label="梳洗">
           <el-input v-model="filters.washing" placeholder="梳洗"></el-input>
         </el-form-item>
       </el-col>
     </el-row>
   </el-form>
   <div slot="footer" class="dialog-footer">
     <el-button type="primary" v-on:click="highsearch" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
   </div>
 </el-dialog>





    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;margin-top:-20px;" @row-dblclick="handleRowChange">
      <!--<el-table-column type="selection" width="55">
      </el-table-column> -->
      <el-table-column type="index" width="60">
      </el-table-column>
      <el-table-column prop="id" label="工程编号" width="120">
      </el-table-column>
      <el-table-column prop="systemid" label="系统编号" width="120">
      </el-table-column>
      <el-table-column prop="meal" label="进餐" width="120">
      </el-table-column>
      <el-table-column prop="washing" label="梳洗" width="120" >
      </el-table-column>
      <el-table-column prop="dressing" label="穿衣" width="120">
      </el-table-column>
      <el-table-column prop="toilet" label="如厕" width="120">
      </el-table-column>
      <el-table-column prop="activity" label="活动" min-width="160">
      </el-table-column>
      <el-table-column prop="totalScore" label="总得分" min-width="160">
      </el-table-column>
      <el-table-column prop="formDate" label="填表日期" min-width="160">
      </el-table-column>

      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <a size="14px" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false"center>
      <div id="print">
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <div class="container">
          <el-tabs v-model="message">
            <el-row>
              <el-col :span="12">
              <el-form-item label="进餐">
                <el-select @change="score" v-model="editForm.meal" placeholder="请选择">
                  <el-option key="a" label="可自理(0分)" value="可自理(0分)"></el-option>
                  <el-option key="b" label="轻度依赖(0)分" value="轻度依赖(0)分"></el-option>
                  <el-option key="c" label="中度依赖(3)分" value="中度依赖(3)分"></el-option>
                  <el-option key="d" label="不能自理(5)分" value="不能自理(5)分"></el-option>
                </el-select>
              </el-form-item>
              </el-col>
              <el-col :span="12">
              <el-form-item label="梳洗">
                <el-select @change="score" v-model="editForm.washing" placeholder="请选择">
                  <el-option key="a1" label="可自理(0分)" value="可自理(0分)"></el-option>
                  <el-option key="b1" label="轻度依赖(1)分" value="轻度依赖(1)分"></el-option>
                  <el-option key="c1" label="中度依赖(3)分" value="中度依赖(3)分"></el-option>
                  <el-option key="d1" label="不能自理(7)分" value="不能自理(7)分"></el-option>
                </el-select>
              </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
              <el-form-item label="穿衣">
                <el-select @change="score" v-model="editForm.dressing" placeholder="请选择">
                  <el-option key="a2" label="可自理(0分)" value="可自理(0分)"></el-option>
                  <el-option key="b2" label="轻度依赖(0)分" value="轻度依赖(0)分"></el-option>
                  <el-option key="c2" label="中度依赖(3)分" value="中度依赖(3)分"></el-option>
                  <el-option key="d2" label="不能自理(5)分" value="不能自理(5)分"></el-option>
                </el-select>
              </el-form-item>
              </el-col>
              <el-col :span="12">
              <el-form-item label="如厕">
                <el-select @change="score" v-model="editForm.toilet" placeholder="请选择">
                  <el-option key="a3" label="可自理(0分)" value="可自理(0分)"></el-option>
                  <el-option key="b3" label="轻度依赖(1)分" value="轻度依赖(1)分"></el-option>
                  <el-option key="c3" label="中度依赖(5)分" value="中度依赖(5)分"></el-option>
                  <el-option key="d3" label="不能自理(10)分" value="不能自理(10)分"></el-option>
                </el-select>
              </el-form-item>
              </el-col>
            </el-row>



            <el-row>
              <el-col :span="12">
              <el-form-item label="活动">
                <el-select @change="score" v-model="editForm.activity" placeholder="请选择">
                  <el-option key="a4" label="可自理(0分)" value="可自理(0分)"></el-option>
                  <el-option key="b4" label="轻度依赖(1)分" value="轻度依赖(1)分"></el-option>
                  <el-option key="c4" label="中度依赖(5)分" value="中度依赖(5)分"></el-option>
                  <el-option key="d4" label="不能自理(10)分" value="不能自理(10)分"></el-option>
                </el-select>
              </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="总得分">
                  <el-input v-model="editForm.totalScore" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="工程编号">
                  <el-input type="te1" v-model="editForm.id"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="系统编号">
                  <el-input v-model="editForm.systemid" ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="姓名">
                  <el-input type="te1112" v-model="editForm.name"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="填表日期">
                  <el-date-picker type="date" placeholder="选择日期" v-model="editForm.formDate" style="width: 100%"></el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>

          </el-tabs>
        </div>
      </el-form>
      </div>

      <div slot="footer" class="dialog-footer">

        <el-button @click="conprint()">打印</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
        <el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
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
  } from '@/api/selfcareability'

  export default {
    data() {
      return {
        edit: true,
        tableHeight: window.innerHeight - 220,
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
          meal: '',
          washing: ''
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
          id: '0',
          meal: '',
          washing: '',
          dressing: '',
          activity: '',
          toilet: '',
          totalScore: '',
          creater: '',
          createDate: '',
          modifier: '',
          modifyDate: '',
          name: '',
          systemid: '',
          formDate: ''
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      // 计分
      getscore(score) {
        if (score !== '' && score !== null) {
          score = score.substring(score.length - 3, score.length - 2)
          return Number(score)
        } else { return 0 }
      },
      score: function() {
        this.editForm.totalScore = this.getscore(this.editForm.washing) + this.getscore(this.editForm.dressing) + this.getscore(this.editForm.meal) +
        this.getscore(this.editForm.activity) + this.getscore(this.editForm.toilet)
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
        var str = style + '<div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
        '  <div id="topContent">\n' +
        '     <h2 style="width:794px;text-align:center">老年人生活自理能力评估表</h2>  \n' +
        '       <span style="margin-left:34px;">姓名:{{name}}</span>' +
          '     <span style="margin-left:410px;">工作编号:{{id}}</span>            ' +
          '     <span>系统编号:{{systemid}}</span>' +
          '</div>       \n' +
        '   <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%"> \n' +
        '    <tbody>\n' +
        '<tr>\n' +
        '      <td rowspan="2" style="word-wrap:break-word;width: 10%;text-align:center">评估事项,内容与评分</td>\n' +
        '      <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">程度等级</td>\n' +
        '    </tr>\n' +
        '    <tr>\n' +
        '      <td style="width: 20%;text-align:center">可自理</td>\n' +
        '      <td style="width: 20%;text-align:center">轻度依赖</td>\n' +
        '      <td style="width: 20%;text-align:center">中度依赖</td>\n' +
        '      <td style="width: 20%;text-align:center">不能自理</td>\n' +
        '      <td style="width: 20%;text-align:center">判断评分</td>\n' +
        '    </tr>\n' +
        '</tbody>\n' +
        '    <tbody id="secondContent">\n' +
        '    <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
        '      <td style="word-wrap:break-word;width: 10%;text-align:center">进餐:使用餐具将饭送入口,咀嚼,吞咽等活动<br/>评分</td>\n' +
        '      <td style="text-align:center">独立完成<br/><br/>0</td>\n' +
        '      <td style="text-align:center">-<br/><br/>0</td>\n' +
        '      <td style="text-align:center">需要协助,如切碎,搅拌事物等<br/>3</td>\n' +
        '      <td style="text-align:center">完全需要帮助<br/><br/>5</td>\n' +
        '      <td style="text-align:center">{{meal}}</td>\n' +
        '    </tr>\n' +
        '    <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
        '      <td style="word-wrap:break-word;width: 10%;text-align:center">梳洗:梳头,洗脸,刷牙,剃须,洗澡等<br/>评分</td>\n' +
        '      <td style="text-align:center">独立完成<br/><br/>0</td>\n' +
        '      <td style="text-align:center">能独立地洗头,梳头,洗脸,刷牙,剃须等;洗澡需要协助<br/><br/>1</td>\n' +
        '      <td style="text-align:center">在协助下和适当的时间内,能完成部分梳洗活动<br/>3</td>\n' +
        '      <td style="text-align:center">完全需要帮助<br/><br/>7</td>\n' +
        '      <td style="text-align:center">{{washing}}</td>\n' +
        '    </tr>\n' +
        '\n' +
        '    <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
        '      <td style="word-wrap:break-word;width: 10%;text-align:center">穿衣:穿衣裤,袜子,鞋子等活动<br/>评分</td>\n' +
        '      <td style="text-align:center">独立完成<br/><br/>0</td>\n' +
        '      <td style="text-align:center">-<br/><br/>0</td>\n' +
        '      <td style="text-align:center">需要协助,在适当时间内完成部分穿衣<br/>3</td>\n' +
        '      <td style="text-align:center">完全需要帮助<br/><br/>5</td>\n' +
        '      <td style="text-align:center">{{dressing}}</td>\n' +
        '    </tr>\n' +
        '    <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
        '      <td style="word-wrap:break-word;width: 10%;text-align:center">如厕:小便,大便等活动及自控<br/>评分</td>\n' +
        '      <td style="text-align:center">独立完成<br/><br/>0</td>\n' +
        '      <td style="text-align:center">不需要协助,可自控<br/><br/></td>\n' +
        '      <td style="text-align:center">偶尔失禁,但基本上能如厕或使用便具<br/>5</td>\n' +
        '      <td style="text-align:center">完全失禁,完全需要帮助<br/><br/>10</td>\n' +
        '      <td style="text-align:center">{{toilet}}</td>\n' +
        '    </tr>\n' +
        '    <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
        '      <td style="word-wrap:break-word;width: 10%;text-align:center">活动:站立,室内行走,上下楼梯,户外活动<br/>评分</td>\n' +
        '      <td style="text-align:center">独立完成所有活动<br/><br/>0</td>\n' +
        '      <td style="text-align:center">借助较小的外力或辅助装置能完成站立,行走,不能上下楼梯<br/><br/>5</td>\n' +
        '      <td style="text-align:center">偶尔失禁,但基本上能如厕或使用便具<br/>5</td>\n' +
        '      <td style="text-align:center">卧床不起,活动完全需要帮助<br/><br/>10</td>\n' +
        '      <td style="text-align:center">{{activity}}</td>\n' +
        '    </tr>\n' +
        '    <td style="word-wrap:break-word;text-align:center" colspan=\'5\'>总得分</td>\n' +
        '    <td style="text-align:center">{{totalScore}}</td>\n' +
        '\n' +
        '    </tbody>\n' +
        '  </table>\n' +
          '  <div id="Content">\n' +
          '     <span style="margin-left:34px;">填表日期:{{formDate}}</span>            ' +
          '</div>       \n' +
          '</div>'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{id}}/, this.editForm.id ? this.editForm.id : '')
        str = str.replace(/{{systemid}}/, this.editForm.systemid ? this.editForm.systemid : '')
        str = str.replace(/{{meal}}/, this.editForm.meal ? this.editForm.meal : '')
        str = str.replace(/{{washing}}/, this.editForm.washing ? this.editForm.washing : '')
        str = str.replace(/{{dressing}}/, this.editForm.dressing ? this.editForm.dressing : '')
        str = str.replace(/{{toilet}}/, this.editForm.toilet ? this.editForm.toilet : '')
        str = str.replace(/{{activity}}/, this.editForm.activity ? this.editForm.activity : '')
        str = str.replace(/{{totalScore}}/, this.editForm.totalScore ? this.editForm.totalScore : '')
        str = str.replace(/{{formDate}}/, this.editForm.formDate ? this.editForm.formDate : '')
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
        this.getUsers()
      },
      // 获取用户列表
      getUsers() {
        const para = {
          page: this.page,
          meal: this.filters.meal,
          washing: this.filters.washing
        }
        // 点击关键弹窗
        this.dialogFormVisible2 = false
        getUserListPage(para).then(res => {
          this.total = res.data.total
          this.users = res.data.hhh
          if (this.page > 1 && (this.page - 1) * 10 >= this.total) {
            this.page = this.page - 1
            this.getUsers()
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
      handleRowChange(row, event) {
        this.dialogStatus = 'detail'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.edit = false
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
        this.edit = false
        this.editForm = {
          id: '0',
          meal: '',
          washing: '',
          dressing: '',
          activity: '',
          toilet: '',
          totalScore: '',
          creater: '1',
          createDate: '',
          modifier: '1',
          modifyDate: '',
          name: '',
          systemid: '',
          formDate: ''
        }
      },
      // 查询
      search() {
        this.page = 1
        this.getUsers()
      },
      highsearch() {
        this.page = 1
        this.getUsers()
      },
      searchwindow() {
        this.findForm = {
          meal: '',
          washing: ''
        }
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
      },
      findbtn() {
        this.dialogStatus = 'find'
        this.dialogFormVisible2 = true
        this.findForm = {
          meal: '',
          washing: ''
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
                para.modifier = '测试人'
                para.createDate = new Date()
                para.createDate =
                  !para.createDate || para.createDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.createDate), 'yyyy-MM-dd')
                para.modifyDate =
                  !para.modifyDate || para.modifyDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.modifyDate), 'yyyy-MM-dd')
                para.formDate = new Date()
                para.formDate =
                  !para.formDate || para.formDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.formDate), 'yyyy-MM-dd')
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
                para.createDate =
                  !para.createDate || para.createDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.createDate), 'yyyy-MM-dd')
                para.formDate = new Date()
                para.formDate =
                  !para.formDate || para.formDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.formDate), 'yyyy-MM-dd')
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
