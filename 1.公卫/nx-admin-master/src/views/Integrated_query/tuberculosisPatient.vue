<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-cascader :options="options" v-model="filters.area"  placeholder="区域"  style="width: 20%;margin-bottom: 20px;" change-on-select></el-cascader>
        <el-input v-model="filters.peopleId" placeholder="身份证号" style="width: 20%;"></el-input>
        <el-date-picker style="width: 20%;" type="date" v-model="filters.inputDate_s" placeholder="建档起始时间"></el-date-picker>
        <el-date-picker style="width: 20%;"  type="date" v-model="filters.inputDate_e" placeholder="建档结束时间"></el-date-picker>
        <el-input v-model="filters.name" placeholder="姓名" style="width: 18%;"></el-input>
        <el-select v-model="filters.ethnic" placeholder="民族" style="width: 20%">
          <el-option key="1" label="汉族" value="汉族"></el-option>
          <el-option key="2" label="其他" value="其他"></el-option>
        </el-select>
        <el-input v-model="filters.phone" placeholder="联系电话" style="width: 20%;"></el-input>
       <el-select v-model="filters.lifeState" placeholder="生存状态" style="width: 20%">
         <el-option key="1" label="存活" value="存活"></el-option>
         <el-option key="2" label="死亡" value="死亡"></el-option>
       </el-select>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers"style="background-color:#4db2ee;border-color:transparent ;height:40px" icon="el-icon-search">查询</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;"@row-dblclick="goto">
      <el-table-column type="index" label="序号" width="80">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="90">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="60" :formatter="formatSex">
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="60">
      </el-table-column>
      <el-table-column prop="area" label="区域" min-width="250" style="text-align:center">
      </el-table-column>
      <el-table-column prop="ethnic" label="民族" width="60">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" width="180">
      </el-table-column>
      <el-table-column prop="phone" label="联系电话" min-width="120">
      </el-table-column>
      <el-table-column prop="responDoctor" label="责任医生" min-width="120">
       </el-table-column>
       <el-table-column prop="inputDate" label="建档日期" min-width="120">
       </el-table-column>
      <el-table-column prop="recentFollow" label="最近随访" min-width="120" >
      </el-table-column>
      <el-table-column prop="fNumber" label="随访次数" min-width="120">
      </el-table-column>
      <el-table-column prop="lifeState" label="生存状态" min-width="120">
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
     addData
   } from '@/api/TuberculosisPatient'
   import city from '@/api/city.js'

   export default {
     data() {
       return {
         edit: false,
         options: city.city(),
         symptomsSigns: [
           { value: '没有症状', label: '没有症状TuberculosisPatient' },
           { value: '咳嗽咳痰', label: '咳嗽咳痰' },
           { value: '低热盗汗', label: '低热盗汗' },
           { value: '咯血或血痰', label: '咯血或血痰' },
           { value: '胸痛消廋', label: '胸痛消廋' },
           { value: '恶心纳差', label: '恶心纳差' },
           { value: '头痛失眠', label: '头痛失眠' },
           { value: '视物模糊', label: '视物模糊' },
           { value: '皮肤瘙痒、皮疹', label: '皮肤瘙痒、皮疹' },
           { value: '耳鸣、听力下降', label: '耳鸣、听力下降' }
         ],
         drugDosage: [
           { value: '固定剂量复合制剂', label: '固定剂量复合制剂' },
           { value: '散装药', label: '散装药' },
           { value: '板式组合药', label: '板式组合药' }
         ],
         dialogStatus: '',
         tableHeight: window.innerHeight - 320,
         textMap: {
           update: '编辑',
           create: '新增',
           find: '高级查询'

         },
         dialogFormVisible: false,
         dialogFormVisible2: false,
         filters: {

           name: '',
           followTime_s: '',
           followTime_e: '',
           inputDate_s: '',
           inputDate_e: '',
           area: [],
           ethnic: '',
           phone: '',
           responDoctor: '',
           inputPerson: '',
           lifeState: '',

           peopleId: ''
         },
         users: [],
         total: 0,
         page: 1,
         sels: [], // 列表选中列
         editFormRules: {
           area: [{ required: true, message: '请输入区域', trigger: 'blur' }],
           name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
           peopleId: [
             { required: true, message: '请输入身份证ID', trigger: 'blur' },
             { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '你的身份证格式不正确' }
           ],
           inputDate: [{ required: true, message: '建档日期不能为空', trigger: 'blur' }],
           age: [{ type: 'number', message: '年龄必须为数字值', trigger: 'blur' }],
           ruleMedicine: [{ required: true, message: '请输入是否规则服药', trigger: 'blur' }],
           management: [{ required: true, message: '请输入是否被管理', trigger: 'blur' }],
           lifeState: [{ required: true, message: '请输入生存状态', trigger: 'blur' }]

         },

         // 编辑界面数据
         editForm: {
           name: '',
           followTime: null,
           followMethod: '',
           patienType: '',
           sputumStatus: '',
           drugStatus: '',
           symptomsSigns: [],
           otherSymptoms: '',
           chemotherapyRegimen: '',
           usages: '',
           drugDosage: [],
           supervisorSelection: '',
           separateRoom: '',
           ventilation: '',
           smoking: '',
           Drinking: '',
           takemedicineTime: null,
           takemedicinePlace: '',
           medicationRecordcard: '',
           medicationmethodDrugstorage: '',
           treatmentTuberculosis: '',
           irregularMedication: '',
           adverseReactions: '',
           review: '',
           howtakeMedicin: '',
           habitsPrecautions: '',
           closeCheck: '',
           nextFollowtie: null,
           evaluationDoctorsignature: '',
           age: null,
           ethnic: '',
           area: [],
           followTime_s: null,
           followTime_e: null,
           inputDate_s: null,
           inputDate_e: null,
           inputPerson: '',
           peopleId: '',
           phone: '',
           responDoctor: '',
           lifeState: ''

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
       // 查询
       search() {
         this.page = 1
         this.getUsers()
       },
       // 打印
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
         var str = style
         str = str + '<html>\n' +
           '<head>\n' +
           '<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />\n' +
           '<title></title>\n' +
           '<style media="print">\n' +
           '@page {\n' +
           ' size: auto;  /* auto is the initial value */\n' +
           ' margin: 0mm; /* this affects the margin in the printer settings */\n' +
           '}\n' +
           '</style>\n' +
           '</head>\n' +
           '<body>\n' +
           '\n' +
           '<div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
           '  <div id="topContent">\n' +
           '    <h2 style="width:794px;text-align:center"> 肺结核患者第一次入户随访记录表</h2> \n' +
           '    <span style="margin-left:34px;">姓名:</span>\n' +
           '    <span style="margin-left:34px;">{{name}}</span>\n' +
           '\t<span style="margin-left:250px;">编号:</span>\n' +
           '    <span style="margin-left:5px;">{{id}}</span> \n' +
           '    </div>\n' +
           '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
           '    <tbody>\n' +
           '      <tr>\n' +
           '        <td style="width: 10%;text-align:center">随访时间</td>\n' +
           '        <td colspan=\'4\'>{{followTime}}</td>\n' +
           '      </tr>\n' +
           '      <tr>\n' +
           '        <td style="word-wrap:break-word;width: 10%;text-align:center">随访方式</td>\n' +
           '        <td colspan=\'1\'>{{followMethod}}</td>\n' +
           '        <td style="width: 19%;text-align:center">患者类型</td>\n' +
           '        <td colspan=\'2\'>{{patienType}}</td>\n' +
           '      </tr>\n' +
           '      <tr>\n' +
           '        <td style="word-wrap:break-word;width: 10%;text-align:center">痰菌情况</td>\n' +
           '        <td colspan=\'1\'>{{sputumStatus}}</td>\n' +
           '        <td style="width: 19%;text-align:center">耐药情况</td>\n' +
           '        <td colspan=\'2\'>{{drugStatus}}</td>\n' +
           '      </tr>\n' +
           '\t  \n' +
           '\t   <tr>\n' +
           '        <td rowspan=\'3\' style="word-wrap:break-word;width: 10%;text-align:center">症状及体征</td>\n' +
           '        <td colspan=\'4\'>{{symptomsSigns}}</td>   \n' +
           '      </tr>\n' +
           '\t \n' +
           '\t<td rowspan=\'2\'>其他</td>\n' +
           '\t<td rowspan=\'2\' colspan=\'3\'>{{otherSymptoms}}</td>\n' +
           '\t <tr></tr>\n' +
           '\t  \n' +
           '     <tr>\n' +
           '        <td rowspan=\'3\' style="word-wrap:break-word;width: 10%;text-align:center">用药</td>\n' +
           '        <td >化疗方案</td>\n' +
           '        <td colspan=\'3\'>{{chemotherapyRegimen}}</td>\n' +
           '      </tr>\n' +
           '\t  <tr>\n' +
           '\t\t<td >用法</td>\n' +
           '\t\t<td colspan=\'3\'>{{usages}}</td> \n' +
           '\t  </tr>\n' +
           '\t  <tr>\n' +
           '\t\t<td >药品剂型</td>\n' +
           '\t\t<td colspan=\'3\'>{{drugDosage}}</td> \n' +
           '\t  </tr>\n' +
           '\t  \n' +
           '\t  <tr>\n' +
           '        <td style="width: 10%;text-align:center">督导人员选择</td>\n' +
           '        <td colspan=\'4\'>{{supervisorSelection}}</td>\n' +
           '      </tr>\n' +
           '\t  \n' +
           '\t  <tr>\n' +
           '        <td rowspan=\'2\' style="word-wrap:break-word;width: 10%;text-align:center">家庭居住环境评估</td>\n' +
           '        <td >单独的居室</td>\n' +
           '        <td colspan=\'3\'>{{separateRoom}}</td>\n' +
           '      </tr>\n' +
           '\t  <tr>\n' +
           '\t\t<td >通风情况</td>\n' +
           '\t\t<td colspan=\'3\'>{{ventilation}}</td> \n' +
           '\t  </tr>\n' +
           '\t  \n' +
           '\t   <tr>\n' +
           '        <td rowspan=\'2\' style="word-wrap:break-word;width: 10%;text-align:center">生活方式评估</td>\n' +
           '        <td >吸烟</td>\n' +
           '        <td colspan=\'3\'>{{smoking}} 支/天</td>\n' +
           '      </tr>\n' +
           '\t  <tr>\n' +
           '\t\t<td >饮酒</td>\n' +
           '\t\t<td colspan=\'3\'>{{Drinking}} 两/天</td> \n' +
           '\t  </tr>\n' +
           '\t  \n' +
           '\t  <tr>\n' +
           '        <td rowspan=\'10\' style="word-wrap:break-word;width: 10%;text-align:center">健康教育及培训</td>\n' +
           '        <td colspan=\'2\' >取药地点、时间</td>\n' +
           '        <td  colspan=\'2\'>地点：{{takemedicinePlace}}</br>时间：{{takemedicineTime}}</td>\n' +
           '      </tr>\n' +
           '\t  <tr>\n' +
           '\t\t<td  colspan=\'2\'>服药记录卡的填写</td>\n' +
           '\t\t<td  colspan=\'2\'>{{medicationRecordcard}}</td> \n' +
           '\t  </tr>\n' +
           '\t  \n' +
           '\t   <tr>\n' +
           '        <td  colspan=\'2\'>服药方法及药品存放</td>\n' +
           '        <td  colspan=\'2\'>{{medicationmethodDrugstorage}}</td>\n' +
           '      </tr>\n' +
           '\t  <tr>\n' +
           '\t\t<td  colspan=\'2\'>肺结核治疗疗程</td>\n' +
           '\t\t<td  colspan=\'2\'>{{treatmentTuberculosis}}</td> \n' +
           '\t  </tr>\n' +
           '\t   <tr>\n' +
           '\t\t<td  colspan=\'2\'>不规律服药危害</td>\n' +
           '\t\t<td  colspan=\'2\'>{{irregularMedication}}</td> \n' +
           '\t  </tr>\n' +
           '\t   <tr>\n' +
           '\t\t<td  colspan=\'2\'>服药后不良反应及处理</td>\n' +
           '\t\t<td  colspan=\'2\'>{{adverseReactions}}</td> \n' +
           '\t  </tr>\n' +
           '\t   <tr>\n' +
           '\t\t<td  colspan=\'2\'>治疗期间复诊查痰</td>\n' +
           '\t\t<td  colspan=\'2\'>{{review}}</td> \n' +
           '\t  </tr>\n' +
           '\t   <tr>\n' +
           '\t\t<td  colspan=\'2\'>外出期间如何坚持服药</td>\n' +
           '\t\t<td  colspan=\'2\'>{{howtakeMedicin}}</td> \n' +
           '\t  </tr>\n' +
           '\t   <tr>\n' +
           '\t\t<td  colspan=\'2\'>生活习惯及注意事项</td>\n' +
           '\t\t<td  colspan=\'2\'>{{habitsPrecautions}}</td> \n' +
           '\t  </tr>\n' +
           '\t   <tr>\n' +
           '\t\t<td  colspan=\'2\'>密切接触者检查</td>\n' +
           '\t\t<td  colspan=\'2\'>{{closeCheck}}</td> \n' +
           '\t  </tr>\n' +
           '\t    <tr>\n' +
           '        <td style="width: 10%;text-align:center">下次随访时间</td>\n' +
           '        <td colspan=\'4\'>{{nextFollowtie}}</td>\n' +
           '      </tr> \n' +
           '\t  <tr>\n' +
           '        <td style="width: 10%;text-align:center">评估医生签名</td>\n' +
           '        <td colspan=\'4\'>{{evaluationDoctorsignature}}</td>\n' +
           '      </tr>\n' +
           '\t  \n' +
           '    </tbody>\n' +
           '   \n' +
           '  \n' +
           '  </table>\n' +
           '</div>\n' +
           '</body>\n' +
           '</html>\n'
         str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
         str = str.replace(/{{id}}/, this.editForm.id ? this.editForm.id : '')
         str = str.replace(/{{followTime}}/, this.editForm.followTime ? this.editForm.followTime : '')
         str = str.replace(/{{followMethod}}/, this.editForm.followMethod ? this.editForm.followMethod : '')
         str = str.replace(/{{patienType}}/, this.editForm.patienType ? this.editForm.patienType : '')
         str = str.replace(/{{sputumStatus}}/, this.editForm.sputumStatus ? this.editForm.sputumStatus : '')
         str = str.replace(/{{drugStatus}}/, this.editForm.drugStatus ? this.editForm.drugStatus : '')
         str = str.replace(/{{symptomsSigns}}/, this.editForm.symptomsSigns ? this.editForm.symptomsSigns : '')
         str = str.replace(/{{otherSymptoms}}/, this.editForm.otherSymptoms ? this.editForm.otherSymptoms : '')
         str = str.replace(/{{chemotherapyRegimen}}/, this.editForm.chemotherapyRegimen ? this.editForm.chemotherapyRegimen : '')
         str = str.replace(/{{usages}}/, this.editForm.usages ? this.editForm.usages : '')
         str = str.replace(/{{drugDosage}}/, this.editForm.drugDosage ? this.editForm.drugDosage : '')
         str = str.replace(/{{supervisorSelection}}/, this.editForm.supervisorSelection ? this.editForm.supervisorSelection : '')
         str = str.replace(/{{separateRoom}}/, this.editForm.separateRoom ? this.editForm.separateRoom : '')
         str = str.replace(/{{ventilation}}/, this.editForm.ventilation ? this.editForm.ventilation : '')
         str = str.replace(/{{smoking}}/, this.editForm.smoking ? this.editForm.smoking : '')
         str = str.replace(/{{Drinking}}/, this.editForm.Drinking ? this.editForm.Drinking : '')
         str = str.replace(/{{takemedicineTime}}/, this.editForm.takemedicineTime ? this.editForm.takemedicineTime : '')
         str = str.replace(/{{takemedicinePlace}}/, this.editForm.takemedicinePlace ? this.editForm.takemedicinePlace : '')
         str = str.replace(/{{medicationRecordcard}}/, this.editForm.medicationRecordcard ? this.editForm.medicationRecordcard : '')
         str = str.replace(/{{medicationmethodDrugstorage}}/, this.editForm.medicationmethodDrugstorage ? this.editForm.medicationmethodDrugstorage : '')
         str = str.replace(/{{treatmentTuberculosis}}/, this.editForm.treatmentTuberculosis ? this.editForm.treatmentTuberculosis : '')
         str = str.replace(/{{irregularMedication}}/, this.editForm.irregularMedication ? this.editForm.irregularMedication : '')
         str = str.replace(/{{adverseReactions}}/, this.editForm.adverseReactions ? this.editForm.adverseReactions : '')
         str = str.replace(/{{review}}/, this.editForm.review ? this.editForm.review : '')
         str = str.replace(/{{howtakeMedicin}}/, this.editForm.howtakeMedicin ? this.editForm.howtakeMedicin : '')
         str = str.replace(/{{habitsPrecautions}}/, this.editForm.habitsPrecautions ? this.editForm.habitsPrecautions : '')
         str = str.replace(/{{closeCheck}}/, this.editForm.closeCheck ? this.editForm.closeCheck : '')
         str = str.replace(/{{nextFollowtie}}/, this.editForm.nextFollowtie ? this.editForm.nextFollowtie : '')
         str = str.replace(/{{evaluationDoctorsignature}}/, this.editForm.evaluationDoctorsignature ? this.editForm.evaluationDoctorsignature : '')

         myWindow.document.write(str)

         // myWindow.document.write(content + style)
         myWindow.focus()
         myWindow.document.close()
         myWindow.print()
         return myWindow
       },
       conprint() {
         const windows = this.printf(document.getElementById('print').innerHTML)
         windows.close()
       },
       // 获取用户列表
       getUsers() {
         this.filters.followTime_s =
           !this.filters.followTime_s || this.filters.followTime_s === ''
             ? ''
             : util.formatDate.format(new Date(this.filters.followTime_s), 'yyyy-MM-dd')
         this.filters.followTime_e =
           !this.filters.followTime_e || this.filters.followTime_e === ''
             ? ''
             : util.formatDate.format(new Date(this.filters.followTime_e), 'yyyy-MM-dd')

         this.filters.inputDate_s =
           !this.filters.inputDate_s || this.filters.inputDate_s === ''
             ? ''
             : util.formatDate.format(new Date(this.filters.inputDate_s), 'yyyy-MM-dd')
         this.filters.inputDate_e =
           !this.filters.inputDate_e || this.filters.inputDate_e === ''
             ? ''
             : util.formatDate.format(new Date(this.filters.inputDate_e), 'yyyy-MM-dd')

         const para = {
           page: this.page,
           size: 20,
           filters: { name: this.filters.name,
             followTime_s: this.filters.followTime_s,
             followTime_e: this.filters.followTime_e,
             inputDate_s: this.filters.inputDate_s,
             inputDate_e: this.filters.inputDate_e,
             area: '',
             ethnic: this.filters.ethnic,
             phone: this.filters.phone,
             responDoctor: this.filters.responDoctor,
             inputPerson: this.filters.inputPerson,
             lifeState: this.filters.lifeState,
             peopleId: this.filters.peopleId }

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
         this.filters = {
           name: '',
           followTime_s: '',
           followTime_e: '',
           inputDate_s: '',
           inputDate_e: '',
           area: [],
           ethnic: '',
           phone: '',
           responDoctor: '',
           inputPerson: '',
           lifeState: '',
           age: '',
           peopleId: ''
         }
       },
       // 删除
       handleDel(index, row) {
         this.$confirm('确认删除该记录吗?', '提示', {
           type: 'warning'
         })
           .then(() => {
             const para = { id: row.id }
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

       // 高级查找界面
       findbtn() {
         this.dialogStatus = 'find'
         this.dialogFormVisible2 = true
         this.filters = {
           name: '',
           followTime_s: '',
           followTime_e: '',
           inputDate_s: '',
           inputDate_e: '',
           area: [],
           ethnic: '',
           phone: '',
           responDoctor: '',
           inputPerson: '',
           lifeState: '',
           peopleId: ''
         }
       },
       // 查看档案
       goto(row, event) {
         this.dialogStatus = 'detail'
         this.dialogFormVisible = true
         this.editForm = Object.assign({}, row)
         this.$router.push({
           name: 'dashboard',
           params: {
             hid: row.peopleId
           }
         })
       },
       // 显示详情界面
       handleRowChange(row, event) {
         this.dialogStatus = 'detail'
         this.dialogFormVisible = true
         this.editForm = Object.assign({}, row)
         this.editForm.drugDosage = this.editForm.drugDosage.split(',')
         this.editForm.symptomsSigns = this.editForm.symptomsSigns.split(',')
         this.editForm.area = this.editForm.area.split('/')
         this.edit = false
       },
       // 显示编辑界面
       handleEdit(index, row) {
         this.area = []
         this.dialogStatus = 'update'
         this.dialogFormVisible = true
         this.editForm = Object.assign({}, row)
         this.editForm.area = this.editForm.area.split('/')
       },
       // 显示新增界面
       handleAdd() {
         this.area = []
         this.dialogStatus = 'create'
         this.dialogFormVisible = true
         this.editForm = {
           name: '',
           sex: '',
           age: null,
           ethnic: '',
           area: [],
           inputDate: null,
           inputPerson: '',
           peopleId: '',
           phone: '',
           responDoctor: '',
           recentVisit: null,
           lifeState: '',
           fNumber: ''

         }
       },
       // 编辑
       updateData() {
         this.$refs.editForm.validate(valid => {
           if (valid) {
             this.$confirm('确认提交吗？', '提示', {})
               .then(() => {
                 const para = Object.assign({}, this.editForm)
                 para.inputDate =
                   !para.inputDate || para.inputDate === ''
                     ? null
                     : util.formatDate.format(new Date(para.inputDate), 'yyyy-MM-dd')

                 para.recentVisit = new Date()
                 para.recentVisit =
                   !para.recentVisit || para.recentVisit === ''
                     ? null
                     : util.formatDate.format(new Date(para.recentVisit), 'yyyy-MM-dd')

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

                 para.inputDate =
                   !para.inputDate || para.inputDate === ''
                     ? null
                     : util.formatDate.format(new Date(para.inputDate), 'yyyy-MM-dd')

                 para.recentVisit = new Date()
                 para.recentVisit =
                   !para.recentVisit || para.recentVisit === ''
                     ? null
                     : util.formatDate.format(new Date(para.recentVisit), 'yyyy-MM-dd')

                 para.area = ''
                 for (const itm of this.editForm.area) {
                   para.area += itm
                   para.area += '/'
                 }
                 para.area = para.area.substr(0, para.area.length - 1)
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
