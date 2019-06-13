<template>
  <section class="app-container">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;" >
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.peopleId" placeholder="身份证号" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="search" style="background-color:#4db2ee;border-color:transparent;height:40px" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="searchwindow" style="background-color:#4db2ee;border-color:transparent;width:120px;height:40px" icon="el-icon-search">高级查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button v-if="buttons.KPHM_C" type="primary" @click="handleAdd"icon="el-icon-circle-plus-outline" style="height:40px">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false"center>
      <el-form :model="editForm" label-width="140px" :rules="editFormRules" ref="editForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="身份证号">
              <el-input v-model="filters.peopleId" placeholder="身份证号" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="工作编号">
              <el-input v-model="findForm.workid" placeholder="编号" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="起始填报时间" label-width="140px">
              <el-date-picker type="date" placeholder="选择日期" v-model="findForm.healthDate_s" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="结束填报时间" label-width="140px">
              <el-date-picker type="date" placeholder="选择日期" v-model="findForm.healthDate_e" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" v-on:click="highsearch" style="background-color:#4db2ee;border-color:transparent;height:40px" icon="el-icon-search">查询</el-button>
      </div>
    </el-dialog>
    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;margin-top:-20px;" @row-dblclick="handleRowChange">
      <el-table-column type="index" label="序号" width="80">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" width="180">
      </el-table-column>
      <el-table-column prop="workid" v-if='false' label="工作编号" width="120" >
      </el-table-column>
      <el-table-column prop="id"  v-if='false' label="系统编号" width="120" >
      </el-table-column>
      <el-table-column prop="creator"  v-if='true' label="创建人" min-width="120">
      </el-table-column>
      <el-table-column prop="createDate"  v-if='true' label="创建时间" min-width="120" :formatter="timeFormat">
      </el-table-column>
      <!--<el-table-column prop="question" label="(1)您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question1" label="(2)您容易疲乏吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question2" label="(3)您容易气短,呼吸急促,喘不上气吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question3" label="(4)您说话声音软弱无力吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question4" label="(5)您感到焦虑到闷闷不乐,情绪低沉吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question5" label="(6)您容易精神紧张,焦虑不安吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question6" label="(7)您因为生活状态改变而感到孤独失落吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question7" label="(8)您容易感到害怕或受到惊吓吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question8" label="(9)您感到身体超重不轻松吗?[BMI 指数=体重(kg)/身高2(m)]" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question9" label="(10)您眼睛干涩吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question10" label="(11)您手脚发凉吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question11" label="(12)您胃脘部、背部或腰膝部怕冷吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question12" label="(13)您比一般人都受不了寒冷吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question13" label="(14)您容易患感冒吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question14" label="(15)您没有感冒时也会鼻塞、流鼻涕吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question15" label="(16)您有口粘口腻，或睡眠打鼾吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question16" label="(17)您容易过敏吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question17" label="(18)您的皮肤容易起荨麻疹吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question18" label="(19)您的皮肤在不知不觉中会出现青紫瘀斑、皮下出血吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question19" label="(20)您的皮肤一抓就红，并出现抓痕吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question20" label="(21)您皮肤或口唇干吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question21" label="(22)您有肢体麻木或固定部位疼痛的感觉吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question22" label="(23)您面部或鼻部有油腻感或者油亮发光吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question23" label="(24)您面色或目眶晦黯，或出现褐色斑块/斑点吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question24" label="(25)您有皮肤湿疹、疮疖吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question25" label="(26)您感到口干咽燥、总想喝水吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question26" label="(27)您感到口苦或嘴里有异味吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question27" label="(28)您腹部肥大吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question28" label="(29)您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question29" label="(30)您有大便黏滞不爽、解不尽的感觉吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question30" label="(31)您容易大便干燥吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question31" label="(32)您舌苔厚腻或有舌苔厚厚的感觉吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="question32" label="(33)您舌下静脉瘀紫或增粗吗?" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="qideficiency" label="气虚质" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="yangdeficiency" label="阳虚质" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="yingdeficiency" label="阴虚质" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="tandeficiency" label="痰湿质" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="shideficiency" label="湿热质" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="xuedeficiency" label="血瘀质" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="qiyu" label="气郁质" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="specialenamel" label="特禀质" width="120">-->
      <!--</el-table-column>-->
      <!--<el-table-column prop="peace" label="平和质" width="120">-->
      <!--</el-table-column>-->

      <el-table-column prop="score" v-if='false' label="评分" width="120">
      </el-table-column>
      <el-table-column prop="physicalType" v-if='false' label="体质类型" min-width="160">
      </el-table-column>
      <el-table-column prop="physicalIdentification" v-if='false' label="体质类型辨识" min-width="160">
      </el-table-column>
      <el-table-column prop="chmedicineGuidance" label="中医药保健指导" min-width="160">
      </el-table-column>
      <el-table-column prop="doctorSignature" label="医生签名" min-width="160">
      </el-table-column>
      <el-table-column prop="healthDate" label="填表日期" min-width="160">
      </el-table-column>


      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <a size="14px" @click="goto(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">查看档案</a>
          <a size="14px" v-if="buttons.KPHM_E" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" v-if="buttons.KPHM_D" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
      <el-pagination layout="prev, pager, next" :current-page.sync="page"  @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false"center>
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <EasyScrollbar>
          <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
            <div id="print">
              <el-tabs v-model="activeName">
                <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width:97%;">
                  <tbody style="color:#323232;">
                  <tr v-if="dialogStatus==='detail'">
                    <td style="word-wrap:break-word;text-align:center;">
                      创建人
                    </td>
                    <td colspan="3" class="pad10">
                      <el-form-item label="" label-width="0px">
                        <el-input v-model="editForm.creator" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                      </el-form-item>
                    </td>
                    <td style="word-wrap:break-word;text-align:center;">
                      创建时间
                    </td>
                    <td colspan="3" class="pad10">
                      <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr v-if="dialogStatus==='detail'">
                    <td style="word-wrap:break-word;text-align:center;">
                      修改人
                    </td>
                    <td colspan="3" class="pad10">
                      <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                        <el-input v-model="editForm.modifier" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                      </el-form-item>
                    </td>
                    <td style="word-wrap:break-word;text-align:center;">
                      修改时间
                    </td>
                    <td colspan="3" class="pad10">
                      <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td style="width:140px;text-align:center;"><span>*</span>身份证号</td>
                    <td colspan="4" class="pad10" style="padding-right: 0px;">
                      <el-form-item label="" prop="peopleId" label-width="0px">
                        <el-input v-model="editForm.peopleId" style="width: 100%" v-on:input="inputFunc" placeholder="请输入身份证" id="peopleId"></el-input>
                      </el-form-item>
                    </td>
                    <td style="width:140px;word-wrap:break-word;text-align:center;">姓名</td>
                    <td colspan="3" class="pad10">
                      <el-input type="te1" v-model="editForm.name" auto-complete="off" disabled="false"></el-input>
                    </td>
                  </tr>
                  <tr>
                    <td style="word-wrap:break-word;text-align:center">区域</td>
                    <td colspan="4" class="pad10">
                      <el-cascader :options="options" v-model="editForm.area" style="width: 100%" placeholder="请选择" disabled="false"></el-cascader>
                    </td>
                    <td style="word-wrap:break-word;text-align:center;"><span>*</span>医生签名</td>
                    <td colspan="3" class="pad10" style="padding-right: 0px;">
                      <el-form-item label="" prop="doctorSignature" label-width="0px">
                        <el-input v-model="editForm.doctorSignature"  placeholder="请输入医生签名" id="doctorSignature"></el-input>
                      </el-form-item>
                    </td>

                  </tr>
                  <tr>
                    <td style="word-wrap:break-word;text-align:center">工作编号</td>
                    <td colspan="3" class="pad10">
                      <el-input  v-model="editForm.workid"></el-input>
                    </td>
                    <td style="word-wrap:break-word;text-align:center">中医药保健指导</td>
                    <td colspan="4" class="pad10">
                      <el-select v-model="editForm.chmedicineGuidance" placeholder="请选择" style="width:100%;">
                        <el-option key="1.情志调摄" label="1.情志调摄" value="1.情志调摄"></el-option>
                        <el-option key="2.饮食调养" label="2.饮食调养" value="2.饮食调养"></el-option>
                        <el-option key="3.起居调摄" label="3.起居调摄" value="3.起居调摄"></el-option>
                        <el-option key="4.运动保健" label="4.运动保健" value="4.运动保健"></el-option>
                        <el-option key="5.穴位保健" label="5.穴位保健" value="5.穴位保健"></el-option>
                        <el-option key="6.其他" label="6.其他" value="6.其他"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(1)您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗?</td>
                    <td colspan="4" class="pad10">
                      <el-select @change="score" v-model="editForm.question" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(2)您容易疲乏吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question1" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(3)您容易气短，呼吸短促，接不上气吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question2" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(4)您说话声音低弱无力吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question3" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(5)您感到闷闷不乐、情绪低沉吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question4" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(6)您容易精神紧张、焦虑不安吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question5" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(7)您因为生活状态改变而感到孤独、失落吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question6" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(8)您容易感到害怕或受到惊吓吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question7" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(9)您感到身体超重不轻松吗?(感觉身体沉重)BMI 指数=体重(kg)/身高2(m)</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question8" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(BMI<24)(1分)" label="没有(BMI<24)(1分)" value="没有(BMI<24)(1分)"></el-option>
                        <el-option key="很少(24≤BMI<25)(2分)" label="很少(24≤BMI<25)(2分)" value="很少(24≤BMI<25)(2分)"></el-option>
                        <el-option key="有时(25≤BMI<26)(3分)" label="有时(25≤BMI<26)(3分)" value="有时(25≤BMI<26)(3分)"></el-option>
                        <el-option key="时常(26≤BMI<28)(4分)" label="时常(26≤BMI<28)(4分)" value="时常(26≤BMI<28)(4分)"></el-option>
                        <el-option key="总是(BMI≥28)(5分)" label="总是(BMI≥28)(5分)" value="总是(BMI≥28)(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(10)您眼睛干涩吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question9" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(11)您手脚发凉吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question10" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(12)您胃脘部、背部或腰膝部怕冷吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question11" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(13)您比一般人耐受不了寒冷吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question12" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(14)您容易患感冒吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question13" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(15)您没有感冒时也会鼻塞、流鼻涕吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question14" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(一年<2次)(1分)" label="没有(一年<2次)(1分)" value="没有(一年<2次)(1分)"></el-option>
                        <el-option key="很少(一年感冒2-4次)(2分)" label="很少(一年感冒2-4次)(2分)" value="很少(一年感冒2-4次)(2分)"></el-option>
                        <el-option key="有时(一年感冒5-6次)(3分)" label="有时(一年感冒5-6次)(3分)" value="有时(一年感冒5-6次)(3分)"></el-option>
                        <el-option key="时常(一年8次以上)(4分)" label="时常(一年8次以上)(4分)" value="时常(一年8次以上)(4分)"></el-option>
                        <el-option key="总是(几乎每月)(5分)" label="总是(几乎每月)(5分)" value="总是(几乎每月)(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(16)您有口粘口腻，或睡眠打鼾吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question15" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(17)您容易过敏吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question16" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(从来没有)(1分)" label="没有(从来没有)(1分)" value="没有(从来没有)(1分)"></el-option>
                        <el-option key="很少(一年1,2次)(2分)" label="很少(一年1,2次)(2分)" value="很少(一年1,2次)(2分)"></el-option>
                        <el-option key="有时(一年3,4次)(3分)" label="有时(一年3,4次)(3分)" value="有时(一年3,4次)(3分)"></el-option>
                        <el-option key="时常(一年5,6次)(4分)" label="时常(一年5,6次)(4分)" value="时常(一年5,6次)(4分)"></el-option>
                        <el-option key="总是(每次遇到上述原因都过敏)(5分)" label="总是(每次遇到上述原因都过敏)(5分)" value="总是(每次遇到上述原因都过敏)(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(18)您的皮肤容易起荨麻疹吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question17" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(19)您的皮肤在不知不觉中会出现青紫瘀斑、皮下出血吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question18" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(20)您的皮肤一抓就红，并出现抓痕吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question19" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(21)您皮肤或口唇干吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question20" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(22)您有肢体麻木或固定部位疼痛的感觉吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question21" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(23)您面部或鼻部有油腻感或者油亮发光吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question22" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(24)您面色或目眶晦黯，或出现褐色斑块/斑点吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question23" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(25)您有皮肤湿疹、疮疖吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question24" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(26)您感到口干咽燥、总想喝水吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question25" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(27)您感到口苦或嘴里有异味吗?(指口苦或口臭)</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question26" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(28)您腹部肥大吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question27" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(腹围<80cm,相当于2.4尺)(1分)" label="没有(腹围<80cm,相当于2.4尺)(1分)" value="没有(腹围<80cm,相当于2.4尺)(1分)"></el-option>
                        <el-option key="很少(腹围80-85cm,2.4-2.55尺(2分)" label="很少(腹围80-85cm,2.4-2.55尺(2分)" value="很少(腹围80-85cm,2.4-2.55尺(2分)"></el-option>
                        <el-option key="有时(腹围86-90cm,2.56-2.7尺)(3分)" label="有时(腹围86-90cm,2.56-2.7尺)(3分)" value="有时(腹围86-90cm,2.56-2.7尺)(3分)"></el-option>
                        <el-option key="时常(腹围91-105cm,2.71-3.15尺)(4分)" label="时常(腹围91-105cm,2.71-3.15尺)(4分)" value="时常(腹围91-105cm,2.71-3.15尺)(4分)"></el-option>
                        <el-option key="总是(腹围>105cm或3.15尺)(5分)" label="总是(腹围>105cm或3.15尺)(5分)" value="总是(腹围>105cm或3.15尺)(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(29)您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question28" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(30)您有大便黏滞不爽、解不尽的感觉吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question29" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(31)您容易大便干燥吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question30" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(32)您舌苔厚腻或有舌苔厚厚的感觉吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question31" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="word-wrap:break-word;text-align:center">(33)您舌下静脉瘀紫或增粗吗?</td>
                    <td colspan="4" style="text-align:left" class="pad10">
                      <el-select @change="score" v-model="editForm.question32" placeholder="请选择" style="width: 100%">
                        <el-option key="没有(1分)" label="没有(1分)" value="没有(1分)"></el-option>
                        <el-option key="很少(2分)" label="很少(2分)" value="很少(2分)"></el-option>
                        <el-option key="有时(3分)" label="有时(3分)" value="有时(3分)"></el-option>
                        <el-option key="时常(4分)" label="时常(4分)" value="时常(4分)"></el-option>
                        <el-option key="总是(5分)" label="总是(5分)" value="总是(5分)"></el-option>
                      </el-select>
                    </td>
                  </tr>

                  <tr>
                    <td colspan="3" style="word-wrap:break-word;text-align:center">(2),(3),(4),(14)的得分</td>
                    <td colspan="2" style="text-align:left" class="pad10">
                      <el-input v-model="editForm.show1" auto-complete="off" readonly></el-input>
                    </td>
                    <td colspan="2" style="word-wrap:break-word;text-align:center">气质虚</td>
                    <td colspan="3" style="text-align:left" class="pad10">
                      <el-select v-model="editForm.qideficiency" placeholder="请选择" style="width:100%;">
                        <el-option key="是" label="是" value="是"></el-option>
                        <el-option key="倾向是" label="倾向是" value="倾向是"></el-option>
                        <el-option key="否" label="否" value="否"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="3" style="word-wrap:break-word;text-align:center">(11),(12),(13),(29)的得分</td>
                    <td colspan="2" style="text-align:left" class="pad10">
                      <el-input v-model="editForm.show2" auto-complete="off" readonly></el-input>
                    </td>
                    <td colspan="2" style="word-wrap:break-word;text-align:center">阳虚质</td>
                    <td colspan="3" style="text-align:left" class="pad10">
                      <el-select v-model="editForm.yangdeficiency" placeholder="请选择" style="width:100%;">
                        <el-option key="是" label="是" value="是"></el-option>
                        <el-option key="倾向是" label="倾向是" value="倾向是"></el-option>
                        <el-option key="否" label="否" value="否"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="3" style="word-wrap:break-word;text-align:center">(10),(21),(26),(31)的得分</td>
                    <td colspan="2" style="text-align:left" class="pad10">
                      <el-input v-model="editForm.show3" auto-complete="off" readonly></el-input>
                    </td>
                    <td colspan="2" style="word-wrap:break-word;text-align:center">阴虚质</td>
                    <td colspan="3" style="text-align:left" class="pad10">
                      <el-select v-model="editForm.yingdeficiency" placeholder="请选择" style="width:100%;">
                        <el-option key="是" label="是" value="是"></el-option>
                        <el-option key="倾向是" label="倾向是" value="倾向是"></el-option>
                        <el-option key="否" label="否" value="否"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="3" style="word-wrap:break-word;text-align:center">(9),(16),(28),(32)的得分</td>
                    <td colspan="2" style="text-align:left" class="pad10">
                      <el-input v-model="editForm.show4" auto-complete="off" readonly></el-input>
                    </td>
                    <td colspan="2" style="word-wrap:break-word;text-align:center">痰湿质</td>
                    <td colspan="3" style="text-align:left" class="pad10">
                      <el-select v-model="editForm.tandeficiency" placeholder="请选择" style="width:100%;">
                        <el-option key="是" label="是" value="是"></el-option>
                        <el-option key="倾向是" label="倾向是" value="倾向是"></el-option>
                        <el-option key="否" label="否" value="否"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="3" style="word-wrap:break-word;text-align:center">(23),(25),(27),(30)的得分</td>
                    <td colspan="2" style="text-align:left" class="pad10">
                      <el-input v-model="editForm.show5" auto-complete="off" readonly></el-input>
                    </td>
                    <td colspan="2" style="word-wrap:break-word;text-align:center">湿热质</td>
                    <td colspan="3" style="text-align:left" class="pad10">
                      <el-select v-model="editForm.shideficiency" placeholder="请选择" style="width:100%;">
                        <el-option key="是" label="是" value="是"></el-option>
                        <el-option key="倾向是" label="倾向是" value="倾向是"></el-option>
                        <el-option key="否" label="否" value="否"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="3" style="word-wrap:break-word;text-align:center">(19),(22),(24),(33)的得分</td>
                    <td colspan="2" style="text-align:left" class="pad10">
                      <el-input v-model="editForm.show6" auto-complete="off" readonly></el-input>
                    </td>
                    <td colspan="2" style="word-wrap:break-word;text-align:center">血瘀质</td>
                    <td colspan="3" style="text-align:left" class="pad10">
                      <el-select v-model="editForm.xuedeficiency" placeholder="请选择" style="width:100%;">
                        <el-option key="是" label="是" value="是"></el-option>
                        <el-option key="倾向是" label="倾向是" value="倾向是"></el-option>
                        <el-option key="否" label="否" value="否"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="3" style="word-wrap:break-word;text-align:center">(5),(6),(7),(8)的得分</td>
                    <td colspan="2" style="text-align:left" class="pad10">
                      <el-input v-model="editForm.show5" auto-complete="off" readonly></el-input>
                    </td>
                    <td colspan="2" style="word-wrap:break-word;text-align:center">气郁质</td>
                    <td colspan="3" style="text-align:left" class="pad10">
                      <el-select v-model="editForm.qiyu" placeholder="请选择" style="width:100%;">
                        <el-option key="是" label="是" value="是"></el-option>
                        <el-option key="倾向是" label="倾向是" value="倾向是"></el-option>
                        <el-option key="否" label="否" value="否"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="3" style="word-wrap:break-word;text-align:center">(15),(17),(18),(20)的得分</td>
                    <td colspan="2" style="text-align:left" class="pad10">
                      <el-input  v-model="editForm.show6" auto-complete="off" readonly></el-input>
                    </td>
                    <td colspan="2" style="word-wrap:break-word;text-align:center">特禀质</td>
                    <td colspan="3" style="text-align:left" class="pad10">
                      <el-select v-model="editForm.specialenamel" placeholder="请选择" style="width:100%;">
                        <el-option key="是" label="是" value="是"></el-option>
                        <el-option key="倾向是" label="倾向是" value="倾向是"></el-option>
                        <el-option key="否" label="否" value="否"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="3" style="word-wrap:break-word;text-align:center">(1),(2),(4),(5),(13)的得分</td>
                    <td colspan="2" style="text-align:left" class="pad10">
                      <el-input  v-model="editForm.show7" auto-complete="off" readonly></el-input>
                    </td>
                    <td colspan="2" style="word-wrap:break-word;text-align:center">平和质</td>
                    <td colspan="3" style="text-align:left" class="pad10">
                      <el-select v-model="editForm.peace" placeholder="请选择" style="width:100%;">
                        <el-option key="是" label="是" value="是"></el-option>
                        <el-option key="基本是" label="基本是" value="基本是"></el-option>
                        <el-option key="否" label="否" value="否"></el-option>
                      </el-select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="3" style="word-wrap:break-word;text-align:center">填表日期</td>
                    <td colspan="7" style="text-align:left" class="pad10">
                      <el-date-picker type="date" placeholder="填表日期" v-model="editForm.healthDate" style="width: 100%;"></el-date-picker>
                    </td>
                  </tr>
                  </tbody>
                </table>
                <!--<el-col :span="12">-->
                <!--<el-form-item label="系统编号" label-width="140px">-->
                <!--<el-input type="te4111" v-model="editForm.systemid"></el-input>-->
                <!--</el-form-item>-->
                <!--</el-col>-->
                <!--<div class="container" text-align="center"去掉框框>-->
              </el-tabs>
            </div>
          </div>
        </EasyScrollbar>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button style="height:40px" @click="conprint()">打印</el-button>
        <el-button style="height:40px" v-if="dialogStatus=='create'" type="primary" @click="check">添加</el-button>
        <el-button style="height:40px" v-else type="primary" v-if="edit" @click="createData">新增修改</el-button>
        <el-button style="height:40px" v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button style="height:40px" @click.native="dialogFormVisible=false">取消</el-button>
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
    editData,
    addData
  } from '@/api/healthManagementRecord'
  import city from '@/api/city.js'
  import { checkData as getPerson } from '@/api/selfcareAbility'
  import {
    getList as getSearchMemberPage
  } from '@/api/Perinfor'
  export default {
    data() {
      return {
        buttons: {
          KPHM_C: false,
          KPHM_E: false,
          KPHM_D: false
        },
        edit: true,
        dialogStatus: '',
        tableHeight: window.innerHeight - 220,
        tablebodyHeight: window.innerHeight - 320,
        options: city.city(),
        textMap: {
          update: '编辑',
          create: '添加',
          find: '查询',
          detail: '详情'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          name: '',
          peopleId: ''
        },
        findForm: {
          id: '',
          workid: '',
          healthDate_s: '',
          healthDate_e: ''
        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          peopleId: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: ' ' }
          ],
          doctorSignature: [{ required: true, message: ' ', trigger: 'blur' }],
          name: [{ required: true, message: ' ', trigger: 'blur' }]
        },

        // 编辑界面数据
        editForm: {
          name: '',
          id: null,
          peopleId: '',
          workid: '',
          area: [],
          systemid: '',
          question: '',
          score: '',
          physicalType: '',
          physicalIdentification: '',
          chmedicineGuidance: '',
          doctorSignature: '',
          healthDate: '',
          creator: '1',
          createDate: '',
          modifier: '1',
          modifyDate: '',
          question1: '',
          question2: '',
          question3: '',
          question4: '',
          question5: '',
          question6: '',
          question7: '',
          question8: '',
          question9: '',
          question10: '',
          question11: '',
          question12: '',
          question13: '',
          question14: '',
          question15: '',
          question16: '',
          question17: '',
          question18: '',
          question19: '',
          question20: '',
          question21: '',
          question22: '',
          question23: '',
          question24: '',
          question25: '',
          question26: '',
          question27: '',
          question28: '',
          question29: '',
          question30: '',
          question31: '',
          question32: '',
          qideficiency: '',
          yangdeficiency: '',
          yingdeficiency: '',
          tandeficiency: '',
          shideficiency: '',
          xuedeficiency: '',
          qiyu: '',
          specialenamel: '',
          peace: '',
          show1: '',
          show2: '',
          show3: '',
          show4: '',
          show5: '',
          show6: '',
          show7: ''
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      timeFormat: function(row, column) {
        let date = row[column.property]
        date =
          !date || date === ''
            ? ''
            : util.formatDate.format(new Date(date), 'yyyy-MM-dd')
        return date
      },
      goto(index, row) {
        this.$router.push({
          name: '首页',
          params: {
            hid: row.peopleId
          }
        })
      },
      // 计分
      getscore(score) {
        if (score !== '' && score !== null) {
          score = score.substring(score.length - 3, score.length - 2)
          console.log(score)
          return Number(score)
        } else { return 0 }
      },
      score: function() {
        this.editForm.show1 = this.getscore(this.editForm.question1) + this.getscore(this.editForm.question2) + this.getscore(this.editForm.question3) +
          this.getscore(this.editForm.question13)
        this.editForm.show2 = this.getscore(this.editForm.question10) + this.getscore(this.editForm.question11) + this.getscore(this.editForm.question12) + this.getscore(this.editForm.question28)
        this.editForm.show3 = this.getscore(this.editForm.question9) + this.getscore(this.editForm.question20) + this.getscore(this.editForm.question30)
        this.editForm.show4 = this.getscore(this.editForm.question8) + this.getscore(this.editForm.question15) + this.getscore(this.editForm.question27) + this.getscore(this.editForm.question31)
        this.editForm.show5 = this.getscore(this.editForm.question22) + this.getscore(this.editForm.question24) + this.getscore(this.editForm.question26) + this.getscore(this.editForm.question29)
        this.editForm.show6 = this.getscore(this.editForm.question18) + this.getscore(this.editForm.question21) + this.getscore(this.editForm.question23) + this.getscore(this.editForm.question32)
        this.editForm.show7 = 6 - this.getscore(this.editForm.question) + 6 - this.getscore(this.editForm.question1) + 6 - this.getscore(this.editForm.question3) + 6 - this.getscore(this.editForm.question4) + 6 - this.getscore(this.editForm.question12)
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
        var str = style + '<div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
          '  <div id="topContent">\n' +
          '    <h2 style="width:794px;text-align:center">老年人中医药健康管理服务表</h2>  \n' +
          '    <span style="margin-left:34px;">姓名:{{name}}</span>\n' +
          '    <span style="margin-left:340px;">工作编号:{{workid}}</span> \n' +
          '    <span >系统编号:{{systemid}}</span>\n' +
          '</div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '     <tbody id="secondContent">\n' +
          '      <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '            <td colspan=\'5\'style="word-wrap:break-word;width: 10%;text-align:center">请根据近一年的体验和感觉，回答以下问题。</td>\n' +
          '            <td style="text-align:center">没有 (根本不/从来没有)</td>\n' +
          '            <td style="text-align:center">很少 (有一点/偶尔）</td>\n' +
          '            <td style="text-align:center">有时 (有些/少数时间)</td>\n' +
          '            <td style="text-align:center">经常 (相当/多数时间)</td>\n' +
          '              <td style="text-align:center">总是 (非常/每天)</td>\n' +
          '          </tr> \n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\'style="word-wrap:break-word;width: 10%;text-align:center">(1)您精力充沛吗?(指精神头足，乐于做事)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(2)您容易疲乏吗?(指体力如何，是否稍微活动一下或做一点家务劳动 就感到累)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(3)您容易气短，呼吸短促，接不上气吗?</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(4)您说话声音低弱无力吗?(指说话没有力气)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(5)您感到闷闷不乐、情绪低沉吗?(指心情不愉快，情绪低落)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(6)您容易精神紧张、焦虑不安吗?(指遇事是否心情紧张)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        \n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(7)您因为生活状态改变而感到孤独、失落吗?</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(8)您容易感到害怕或受到惊吓吗?</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(9)您感到身体超重不轻松吗?(感觉身体沉重)[BMI指数=体重(kg)/身高2(m)]</td>\n' +
          '          <td style="text-align:center">1<br/>(BMI<24)</td>\n' +
          '              <td style="text-align:center">2<br/>(24≤BMI<25)</td>\n' +
          '              <td style="text-align:center">3<br/>(25≤BMI<26)</td>\n' +
          '              <td style="text-align:center">4<br/>(26≤BMI<28)</td>\n' +
          '              <td style="text-align:center">5<br/>(BMI≥28)</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(10)您眼睛干涩吗?</td>\n' +
          '             <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(11)您手脚发凉吗?(不包含因周围温度低或穿的少导致的手脚发冷)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        \n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(12)您胃脘部、背部或腰膝部怕冷吗?(指上腹部、背部、腰部或膝关 节等，有一处或多处怕冷)</td>\n' +
          '             <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(13)您比一般人耐受不了寒冷吗?(指比别人容易害怕冬天或是夏天的 冷空调、电扇等)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '           </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(14)您容易患感冒吗?(指每年感冒的次数)</td>\n' +
          '              <td style="text-align:center">1<br/>一年<2次</td>\n' +
          '              <td style="text-align:center">2<br/>一年感冒2-4次</td>\n' +
          '              <td style="text-align:center">3<br/>一年感冒5-6次</td>\n' +
          '              <td style="text-align:center">4<br/>一年8次以上</td>\n' +
          '              <td style="text-align:center">5<br/>几乎每月</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(15)您没有感冒时也会鼻塞、流鼻涕吗?</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(16)您有口粘口腻，或睡眠打鼾吗?</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(17)您容易过敏(对药物、食物、气味、花粉或在季节交替、气候变化时) 吗?</td>\n' +
          '              <td style="text-align:center">1<br/>从来没有</td>\n' +
          '              <td style="text-align:center">2<br/>一年1、2次</td>\n' +
          '              <td style="text-align:center">3<br/>一年3、4次</td>\n' +
          '              <td style="text-align:center">4<br/>一年5、6次</td>\n' +
          '              <td style="text-align:center">5<br/>每次遇到上述原因都过敏</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(18)您的皮肤容易起荨麻疹吗? (包括风团、风疹块、风疙瘩)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(19)您的皮肤在不知不觉中会出现青紫瘀斑、皮下出血吗?(指皮肤在没 有外伤的情况下出现青一块紫一块的情况)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(20)您的皮肤一抓就红，并出现抓痕吗?(指被指甲或钝物划过后皮肤的反应)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(21)您皮肤或口唇干吗?</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(22)您有肢体麻木或固定部位疼痛的感觉吗?</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(23)您面部或鼻部有油腻感或者油亮发光吗?(指脸上或鼻子)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(24)您面色或目眶晦黯，或出现褐色斑块/斑点吗?</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(25)您有皮肤湿疹、疮疖吗?</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '           </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(26)您感到口干咽燥、总想喝水吗?</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(27)您感到口苦或嘴里有异味吗?(指口苦或口臭)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(28)您腹部肥大吗?(指腹部脂肪肥厚)</td>\n' +
          '              <td style="text-align:center">1<br/>(腹围 <80cm，相当 于 2.4 尺)</td>\n' +
          '              <td style="text-align:center">2<br/>(腹围 80-85cm， 2.4-2.55 尺)</td>\n' +
          '              <td style="text-align:center">3<br/>(腹围 86-90cm， 2.56-2.7 尺)</td>\n' +
          '              <td style="text-align:center">4<br/>(腹围 91-105cm， 2.71-3.15 尺)</td>\n' +
          '              <td style="text-align:center">5<br/>(腹围>105cm\n' +
          '或 3.15 尺)</td>\n' +
          '            </tr>\n' +
          '       <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(29)您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗?(指不喜 欢吃凉的食物，或吃了凉的食物后会不舒服)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '       <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(30)您有大便黏滞不爽、解不尽的感觉吗?(大便容易粘在马桶或便坑壁上)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '       <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(31)您容易大便干燥吗?</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '       <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(32)您舌苔厚腻或有舌苔厚厚的感觉吗?(如果自我感觉不清楚可由调查 员观察后填写)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '       <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(33)您舌下静脉瘀紫或增粗吗?(可由调查员辅助观察后填写)</td>\n' +
          '              <td style="text-align:center">1</td>\n' +
          '              <td style="text-align:center">2</td>\n' +
          '              <td style="text-align:center">3</td>\n' +
          '              <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '            </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td style="word-wrap:break-word;width: 10%;text-align:center">体质<br/>类型</td>\n' +
          '              <td style="text-align:center">气虚质</td>\n' +
          '              <td style="text-align:center">阳虚质</td>\n' +
          '              <td style="text-align:center">阴虚质</td>\n' +
          '              <td style="text-align:center">痰湿质</td>\n' +
          '              <td style="text-align:center">湿热质</td>\n' +
          '              <td style="text-align:center">血瘀质</td>\n' +
          '              <td style="text-align:center">气郁质</td>\n' +
          '              <td style="text-align:center">特禀质</td>\n' +
          '              <td style="text-align:center">特禀质</td>\n' +
          '            </tr>\n' +
          '             \n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td style="word-wrap:break-word;width: 10%;text-align:center">体质<br/> 辨识</td>\n' +
          '              <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '              <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '              <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '              <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '              <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '              <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '              <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '              <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '              <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '            </tr>\n' +
          '       <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td style="word-wrap:break-word;width: 10%;text-align:center">中医药<br/>保健<br/>指导</td>\n' +
          '              <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '              <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '              <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '              <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '              <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '              <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '              <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '              <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '              <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '            </tr>\n' +
          '             <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'2\' style="word-wrap:break-word;width: 10%;text-align:center">填表日期</td>\n' +
          '              <td colspan=\'3\' style="text-align:center">{{healthDate}}</td>\n' +
          '              <td colspan=\'2\' style="text-align:center">医生签名</td>\n' +
          '              <td colspan=\'3\' style="text-align:center">{{doctorSignature}}</td>\n' +
          '             </tr>\n' +
          '         </tbody>\n' +
          '  </table>\n' +
          '</div>\n'
        str = str.replace(/{{workid}}/, this.editForm.workid ? this.editForm.workid : '')
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{healthDate}}/, this.editForm.healthDate ? this.editForm.healthDate : '')
        str = str.replace(/{{doctorSignature}}/, this.editForm.doctorSignature ? this.editForm.doctorSignature : '')
        str = str.replace(/{{systemid}}/, this.editForm.systemid ? this.editForm.systemid : '')
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
        this.findForm.healthDate_s =
          !this.findForm.healthDate_s || this.findForm.healthDate_s === ''
            ? null
            : util.formatDate.format(new Date(this.findForm.healthDate_s), 'yyyy-MM-dd')
        this.findForm.healthDate_e =
          !this.findForm.healthDate_e || this.findForm.healthDate_e === ''
            ? null
            : util.formatDate.format(new Date(this.findForm.healthDate_e), 'yyyy-MM-dd')
        this.dialogFormVisible2 = false
        const para = {
          page: this.page,
          size: 20,
          filters: {
            name: this.filters.name,
            peopleId: this.filters.peopleId,
            id: this.findForm.id,
            workid: this.findForm.workid,
            healthDate_s: this.findForm.healthDate_s,
            healthDate_e: this.findForm.healthDate_e
          }
        }
        getList(para).then(res => {
          this.total = res.totalCount
          this.users = res.detailModelList
          if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
            this.page = this.page - 1
            this.getUsers()
          }
        })
        this.dialogFormVisible2 = false
        this.findForm = {
          id: '',
          workid: '',
          healthDate_s: '',
          healthDate_e: ''
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
        this.editForm.area = this.editForm.area.split('/')
        this.edit = true
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
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          id: '',
          workid: '',
          healthDate_s: '',
          healthDate_e: ''
        }
      },
      // 显示新增界面
      handleAdd() {
        this.area = []
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.editForm = {
          name: '',
          area: [],
          peopleId: '',
          id: null,
          workid: '',
          systemid: '',
          question: '',
          score: '',
          physicalType: '',
          physicalIdentification: '',
          chmedicineGuidance: '',
          doctorSignature: '',
          healthDate: new Date(),
          creator: this.$store.getters.name,
          createDate: null,
          modifier: '',
          modifyDate: null,
          question1: '',
          question2: '',
          question3: '',
          question4: '',
          question5: '',
          question6: '',
          question7: '',
          question8: '',
          question9: '',
          question10: '',
          question11: '',
          question12: '',
          question13: '',
          question14: '',
          question15: '',
          question16: '',
          question17: '',
          question18: '',
          question19: '',
          question20: '',
          question21: '',
          question22: '',
          question23: '',
          question24: '',
          question25: '',
          question26: '',
          question27: '',
          question28: '',
          question29: '',
          question30: '',
          question31: '',
          question32: '',
          qideficiency: '',
          yangdeficiency: '',
          yingdeficiency: '',
          tandeficiency: '',
          shideficiency: '',
          xuedeficiency: '',
          qiyu: '',
          specialenamel: '',
          peace: ''
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
                para.healthDate =
                  !para.healthDate || para.healthDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.healthDate), 'yyyy-MM-dd')
                para.area = ''
                for (const itm of this.editForm.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                editData(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.$emit('watchChild')
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
      // 身份验证
      check() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            const para = {
              page: 1,
              size: 200,
              filters: { name: '', peopleId: this.editForm.peopleId, cardNumber: '' }
            }
            getPerson(para).then(res => {
              if (res.detailModelList.length !== 0) {
                this.createData()
              } else {
                this.$message.error('未建立个人档案或该居民不是老人')
              }
            })
          } else {
            if (this.editForm.peopleId === '') {
              this.$el.querySelector('#peopleId').scrollIntoView()
              this.$message.error('请填写身份证')
              return false
            }
            let arr = [18 , 15]
            if (arr.indexOf(this.editForm.peopleId.length) === -1) {
              this.$el.querySelector('#peopleId').scrollIntoView()
              this.$message.error('身份证错误')
            }
            if (this.editForm.doctorSignature === '') {
              this.$el.querySelector('#doctorSignature').scrollIntoView()
              this.$message.error('请填写医生签名')
              return false
            }
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
                const para = Object.assign({}, this.editForm)
                para.healthDate =
                  !para.healthDate || para.healthDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.healthDate), 'yyyy-MM-dd')
                // huoqudangqi
                para.healthDate =
                  !para.healthDate || para.healthDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.healthDate), 'yyyy-MM-dd')
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
                  this.$emit('watchChild')
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
              this.getUsers()
            })
          })
          .catch(() => {})
      },
      inputFunc() {
        const para = {
          page: 1,
          size: 200,
          filters: { name: '', peopleId: this.editForm.peopleId, cardNumber: '' }
        }
        if (para.filters !== '') {
          setTimeout(() => {
            this.$nextTick(function() {
              getSearchMemberPage(para).then(res => {
                const user = res.detailModelList
                if (user.length !== 0) {
                  this.editForm.name = user[0].name
                  this.editForm.area = user[0].houseAddress
                  this.editForm.area = this.editForm.area.split('/')
                  this.editForm.doctorSignature = user[0].creator
                  this.active = 2
                }
              })
            })
          }, 200)
        }
      }
    },
    mounted() {
      this.buttons = this.getButton(this.buttons, this.$store.getters.buttons)
      this.getUsers()
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
  }
  .pad20{
    padding-left: 20px;
  }
  span{
    color:red;
  }
</style>
