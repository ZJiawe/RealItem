<template>
  <section class="app-container">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;" >
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="输入名字..."></el-input>
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
      <el-form :model="editForm" label-width="140px" :rules="editFormRules" ref="editForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名字">
              <el-input v-model="filters.name" placeholder="名字"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="编号">
              <el-input v-model="filters.id" placeholder="编号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="起始填报时间" label-width="140px">
              <el-date-picker type="date" placeholder="选择日期" v-model="filters.healthDate_s" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="结束填报时间" label-width="140px">
              <el-date-picker type="date" placeholder="选择日期" v-model="filters.healthDate_e" style="width: 100%;"></el-date-picker>
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
      </el-table-column>-->
      <el-table-column type="index" width="60">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="id" label="编号" width="120" >
      </el-table-column>
      <el-table-column prop="question" label="(1)您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗?" width="120">
      </el-table-column>
      <el-table-column prop="question1" label="(2)您容易疲乏吗?" width="120">
      </el-table-column>
      <el-table-column prop="question2" label="(3)您容易气短,呼吸急促,喘不上气吗?" width="120">
      </el-table-column>
      <el-table-column prop="question3" label="(4)您说话声音软弱无力吗?" width="120">
      </el-table-column>
      <el-table-column prop="question4" label="(5)您感到焦虑到闷闷不乐,情绪低沉吗?" width="120">
      </el-table-column>
      <el-table-column prop="question5" label="(6)您容易精神紧张,焦虑不安吗?" width="120">
      </el-table-column>
      <el-table-column prop="question6" label="(7)您因为生活状态改变而感到孤独失落吗?" width="120">
      </el-table-column>
      <el-table-column prop="question7" label="(8)您容易感到害怕或受到惊吓吗?" width="120">
      </el-table-column>
      <el-table-column prop="question8" label="(9)您感到身体超重不轻松吗?[BMI 指数=体重(kg)/身高2(m)]" width="120">
      </el-table-column>
      <el-table-column prop="question9" label="(10)您眼睛干涩吗?" width="120">
      </el-table-column>
      <el-table-column prop="question10" label="(11)您手脚发凉吗?" width="120">
      </el-table-column>
      <el-table-column prop="question11" label="(12)您胃脘部、背部或腰膝部怕冷吗?" width="120">
      </el-table-column>
      <el-table-column prop="question12" label="(13)您比一般人都受不了寒冷吗?" width="120">
      </el-table-column>
      <el-table-column prop="question13" label="(14)您容易患感冒吗?" width="120">
      </el-table-column>
      <el-table-column prop="question14" label="(15)您没有感冒时也会鼻塞、流鼻涕吗?" width="120">
      </el-table-column>
      <el-table-column prop="question15" label="(16)您有口粘口腻，或睡眠打鼾吗?" width="120">
      </el-table-column>
      <el-table-column prop="question16" label="(17)您容易过敏吗?" width="120">
      </el-table-column>
      <el-table-column prop="question17" label="(18)您的皮肤容易起荨麻疹吗?" width="120">
      </el-table-column>
      <el-table-column prop="question18" label="(19)您的皮肤在不知不觉中会出现青紫瘀斑、皮下出血吗?" width="120">
      </el-table-column>
      <el-table-column prop="question19" label="(20)您的皮肤一抓就红，并出现抓痕吗?" width="120">
      </el-table-column>
      <el-table-column prop="question20" label="(21)您皮肤或口唇干吗?" width="120">
      </el-table-column>
      <el-table-column prop="question21" label="(22)您有肢体麻木或固定部位疼痛的感觉吗?" width="120">
      </el-table-column>
      <el-table-column prop="question22" label="(23)您面部或鼻部有油腻感或者油亮发光吗?" width="120">
      </el-table-column>
      <el-table-column prop="question23" label="(24)您面色或目眶晦黯，或出现褐色斑块/斑点吗?" width="120">
      </el-table-column>
      <el-table-column prop="question24" label="(25)您有皮肤湿疹、疮疖吗?" width="120">
      </el-table-column>
      <el-table-column prop="question25" label="(26)您感到口干咽燥、总想喝水吗?" width="120">
      </el-table-column>
      <el-table-column prop="question26" label="(27)您感到口苦或嘴里有异味吗?" width="120">
      </el-table-column>
      <el-table-column prop="question27" label="(28)您腹部肥大吗?" width="120">
      </el-table-column>
      <el-table-column prop="question28" label="(29)您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗?" width="120">
      </el-table-column>
      <el-table-column prop="question29" label="(30)您有大便黏滞不爽、解不尽的感觉吗?" width="120">
      </el-table-column>
      <el-table-column prop="question30" label="(31)您容易大便干燥吗?" width="120">
      </el-table-column>
      <el-table-column prop="question31" label="(32)您舌苔厚腻或有舌苔厚厚的感觉吗?" width="120">
      </el-table-column>
      <el-table-column prop="question32" label="(33)您舌下静脉瘀紫或增粗吗?" width="120">
      </el-table-column>
      <el-table-column prop="qideficiency" label="气虚质" width="120">
      </el-table-column>
      <el-table-column prop="yangdeficiency" label="阳虚质" width="120">
      </el-table-column>
      <el-table-column prop="yingdeficiency" label="阴虚质" width="120">
      </el-table-column>
      <el-table-column prop="tandeficiency" label="痰湿质" width="120">
      </el-table-column>
      <el-table-column prop="shideficiency" label="湿热质" width="120">
      </el-table-column>
      <el-table-column prop="xuedeficiency" label="血瘀质" width="120">
      </el-table-column>
      <el-table-column prop="qiyu" label="气郁质" width="120">
      </el-table-column>
      <el-table-column prop="specialenamel" label="特禀质" width="120">
      </el-table-column>
      <el-table-column prop="peace" label="平和质" width="120">
      </el-table-column>

      <el-table-column prop="score" label="评分" width="120">
      </el-table-column>
      <el-table-column prop="physicalType" label="体质类型" min-width="160">
      </el-table-column>
      <el-table-column prop="physicalIdentification" label="体质类型辨识" min-width="160">
      </el-table-column>
      <el-table-column prop="chmedicineGuidance" label="中医药保健指导" min-width="160">
      </el-table-column>
      <el-table-column prop="doctorSignature" label="医生签名" min-width="160">
      </el-table-column>
      <el-table-column prop="healthDate" label="填表日期" min-width="160">
      </el-table-column>


      <el-table-column label="操作" width="250">
        <template slot-scope="scope">
          <a size="14px" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a> 
          <a size="14px" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
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
      <div id="print">
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <div class="container">
          <el-tabs v-model="message">


            <el-row >
              <el-form-item label="(1)您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗?" label-width="380x"></el-form-item>
            </el-row>
            <el-row>
              <el-select @change="score" v-model="editForm.question" placeholder="请选择" style="width:100px">
                <el-option key="a1" label="没有(1分)" value="没有(1分)"></el-option>
                <el-option key="b1" label="很少(2分)" value="很少(2分)"></el-option>
                <el-option key="c1" label="有时(3分)" value="有时(3分)"></el-option>
                <el-option key="d1" label="时常(4分)" value="时常(4分)"></el-option>
                <el-option key="e1" label="总是(5分)" value="总是(5分)"></el-option>
              </el-select>
            </el-row>
            <el-row >
              <el-form-item label="(2)您容易疲乏吗?" label-width="120px" ></el-form-item>
            </el-row>
            <el-row>
              <el-select @change="score" v-model="editForm.question1" placeholder="请选择" style="width:100px">
                <el-option key="a2" label="没有(1分)" value="没有(1分)"></el-option>
                <el-option key="b2" label="很少(2分)" value="很少(2分)"></el-option>
                <el-option key="c2" label="有时(3分)" value="有时(3分)"></el-option>
                <el-option key="d2" label="时常(4分)" value="时常(4分)"></el-option>
                <el-option key="e2" label="总是(5分)" value="总是(5分)"></el-option>
              </el-select>
            </el-row>
            <el-row></el-row>
            <el-row>
              <el-form-item label="(3)您容易气短，呼吸短促，接不上气吗?" label-width="260px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question2" placeholder="请选择">
              <el-option key="a3" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b3" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c3" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d3" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e3" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(4)您说话声音低弱无力吗?" label-width="180px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question3" placeholder="请选择">
              <el-option key="a4" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b4" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c4" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d4" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e4" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(5)您感到闷闷不乐、情绪低沉吗?" label-width="220px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question4" placeholder="请选择">
              <el-option key="a5" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b5" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c5" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d5" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e5" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(6)您容易精神紧张、焦虑不安吗?" label-width="220px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question5" placeholder="请选择">
              <el-option key="a6" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b6" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c6" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d6" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e6" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(7)您因为生活状态改变而感到孤独、失落吗?" label-width="293px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question6" placeholder="请选择">
              <el-option key="a7" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b7" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c7" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d7" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e7" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(8)您容易感到害怕或受到惊吓吗?" label-width="220px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question7" placeholder="请选择">
              <el-option key="a8" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b8" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c8" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d8" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e8" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(9)您感到身体超重不轻松吗?(感觉身体沉重)BMI 指数=体重(kg)/身高2(m)" label-width="467px"> </el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question8" placeholder="请选择">
              <el-option key="a9" label="没有(BMI<24)(1分)" value="没有(BMI<24)(1分)"></el-option>
              <el-option key="b9" label="很少(24≤BMI<25)(2分)" value="很少(24≤BMI<25)(2分)"></el-option>
              <el-option key="c9" label="有时(25≤BMI<26)(3分)" value="有时(25≤BMI<26)(3分)"></el-option>
              <el-option key="d9" label="时常(26≤BMI<28)(4分)" value="时常(26≤BMI<28)(4分)"></el-option>
              <el-option key="e9" label="总是(BMI≥28)(5分)" value="总是(BMI≥28)(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(10)您眼睛干涩吗?" label-width="130px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question9" placeholder="请选择">
              <el-option key="a10" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b10" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c10" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d10" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e10" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(11)您手脚发凉吗?" label-width="133px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question10" placeholder="请选择">
              <el-option key="a11" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b11" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c11" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d11" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e11" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(12)您胃脘部、背部或腰膝部怕冷吗?" label-width="240px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question11" placeholder="请选择">
              <el-option key="a12" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b12" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c12" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d12" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e12" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(13)您比一般人耐受不了寒冷吗?" label-width="215px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question12" placeholder="请选择">
              <el-option key="a13" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b13" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c13" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d13" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e13" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(14)您容易患感冒吗?" label-width="145px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question13" placeholder="请选择">
              <el-option key="a14" label="没有(1)" value="没有(1分)"></el-option>
              <el-option key="b14" label="很少(2)" value="很少(2分)"></el-option>
              <el-option key="c14" label="有时(3)" value="有时(3分)"></el-option>
              <el-option key="d14" label="时常(4)" value="时常(4分)"></el-option>
              <el-option key="e14" label="总是(5)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(15)您没有感冒时也会鼻塞、流鼻涕吗?" label-width="260px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question14" placeholder="请选择">
              <el-option key="a15" label="没有(一年<2次)(1分)" value="没有(一年<2次)(1分)"></el-option>
              <el-option key="b15" label="很少(一年感冒2-4次)(2分)" value="很少(一年感冒2-4次)(2分)"></el-option>
              <el-option key="c15" label="有时(一年感冒5-6次)(3分)" value="有时(一年感冒5-6次)(3分)"></el-option>
              <el-option key="d15" label="时常(一年8次以上)(4分)" value="时常(一年8次以上)(4分)"></el-option>
              <el-option key="e15" label="总是(几乎每月)(5分)" value="总是(几乎每月)(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(16)您有口粘口腻，或睡眠打鼾吗?" label-width="230px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question15" placeholder="请选择">
              <el-option key="a16" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b16" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c16" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d16" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e16" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(17)您容易过敏吗?" label-width="130px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question16" placeholder="请选择">
              <el-option key="a17" label="没有(从来没有)(1分)" value="没有(从来没有)(1分)"></el-option>
              <el-option key="b17" label="很少(一年1,2次)(2分)" value="很少(一年1,2次)(2分)"></el-option>
              <el-option key="c17" label="有时(一年3,4次)(3分)" value="有时(一年3,4次)(3分)"></el-option>
              <el-option key="d17" label="时常(一年5,6次)(4分)" value="时常(一年5,6次)(4分)"></el-option>
              <el-option key="e17" label="总是(每次遇到上述原因都过敏)(5分)" value="总是(每次遇到上述原因都过敏)(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(18)您的皮肤容易起荨麻疹吗?" label-width="205px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question17" placeholder="请选择">
              <el-option key="a18" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b18" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c18" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d18" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e18" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(19)您的皮肤在不知不觉中会出现青紫瘀斑、皮下出血吗?" label-width="370px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question18" placeholder="请选择">
              <el-option key="a19" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b19" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c19" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d19" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e19" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(20)您的皮肤一抓就红，并出现抓痕吗?" label-width="260px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question19" placeholder="请选择">
              <el-option key="a20" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b20" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c20" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d20" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e20" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(21)您皮肤或口唇干吗?" label-width="160px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question20" placeholder="请选择">
              <el-option key="a21" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b21" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c21" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d21" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e21" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(22)您有肢体麻木或固定部位疼痛的感觉吗?" label-width="287px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question21" placeholder="请选择">
              <el-option key="a22" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b22" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c22" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d22" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e22" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(23)您面部或鼻部有油腻感或者油亮发光吗?" label-width="285px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question22" placeholder="请选择">
              <el-option key="a23" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b23" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c23" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d23" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e23" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(24)您面色或目眶晦黯，或出现褐色斑块/斑点吗?" label-width="320px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question23" placeholder="请选择">
              <el-option key="a24" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b24" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c24" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d24" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e24" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(25)您有皮肤湿疹、疮疖吗?" label-width="190px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question24" placeholder="请选择">
              <el-option key="a25" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b25" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c25" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d25" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e25" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(26)您感到口干咽燥、总想喝水吗?" label-width="230px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question25" placeholder="请选择">
              <el-option key="a26" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b26" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c26" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d26" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e26" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(27)您感到口苦或嘴里有异味吗?(指口苦或口臭)" label-width="310px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question26" placeholder="请选择">
              <el-option key="a27" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b27" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c27" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d27" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e27" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(28)您腹部肥大吗?" label-width="135px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question27" placeholder="请选择">
              <el-option key="a28" label="没有(腹围<80cm,相当于2.4尺)(1分)" value="没有(腹围<80cm,相当于2.4尺)(1分)"></el-option>
              <el-option key="b28" label="很少(腹围80-85cm,2.4-2.55尺(2分)" value="很少(腹围80-85cm,2.4-2.55尺(2分)"></el-option>
              <el-option key="c28" label="有时(腹围86-90cm,2.56-2.7尺)(3分)" value="有时(腹围86-90cm,2.56-2.7尺)(3分)"></el-option>
              <el-option key="d28" label="时常(腹围91-105cm,2.71-3.15尺)(4分)" value="时常(腹围91-105cm,2.71-3.15尺)(4分)"></el-option>
              <el-option key="e28" label="总是(腹围>105cm或3.15尺)(5分)" value="总是(腹围>105cm或3.15尺)(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(29)您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗?" label-width="390px"> </el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question28" placeholder="请选择">
              <el-option key="a29" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b29" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c29" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d29" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e29" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(30)您有大便黏滞不爽、解不尽的感觉吗?" label-width="268px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question29" placeholder="请选择">
              <el-option key="a30" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b30" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c30" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d30" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e30" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(31)您容易大便干燥吗?" label-width="160px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question30" placeholder="请选择">
              <el-option key="a31" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b31" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c31" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d31" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e31" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(32)您舌苔厚腻或有舌苔厚厚的感觉吗?" label-width="260px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question31" placeholder="请选择">
              <el-option key="a32" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b32" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c32" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d32" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e32" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>
            <el-row>
              <el-form-item label="(33)您舌下静脉瘀紫或增粗吗?" label-width="210px"></el-form-item>
            </el-row>
            <el-select @change="score" v-model="editForm.question32" placeholder="请选择">
              <el-option key="a33" label="没有(1分)" value="没有(1分)"></el-option>
              <el-option key="b33" label="很少(2分)" value="很少(2分)"></el-option>
              <el-option key="c33" label="有时(3分)" value="有时(3分)"></el-option>
              <el-option key="d33" label="时常(4分)" value="时常(4分)"></el-option>
              <el-option key="e33" label="总是(5分)" value="总是(5分)"></el-option>
            </el-select>

            <el-row>
              <el-col :span="12">
                <el-form-item label="(2),(3),(4),(14)题的得分" label-width="170px">
                  <el-input type="te7" v-model="editForm.show1" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="气质虚">
                  <el-select v-model="editForm.qideficiency" placeholder="请选择">
                    <el-option key="aa1" label="是" value="是"></el-option>
                    <el-option key="bb1" label="倾向是" value="倾向是"></el-option>
                    <el-option key="cc1" label="否" value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="(11),(12),(13),(29)的得分" label-width="170px">
                  <el-input type="te71" v-model="editForm.show2" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="阳虚质">
                  <el-select v-model="editForm.yangdeficiency" placeholder="请选择">
                    <el-option key="aa2" label="是" value="是"></el-option>
                    <el-option key="bb2" label="倾向是" value="倾向是"></el-option>
                    <el-option key="cc2" label="否" value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="(10),(21),(26),(31)的得分" label-width="170px">
                  <el-input type="te72" v-model="editForm.show3" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="阴虚质">
                  <el-select v-model="editForm.yingdeficiency" placeholder="请选择">
                    <el-option key="aa3" label="是" value="是"></el-option>
                    <el-option key="bb3" label="倾向是" value="倾向是"></el-option>
                    <el-option key="cc3" label="否" value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="(9),(16),(28),(32)的得分" label-width="170px">
                  <el-input type="te73" v-model="editForm.show4" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="痰湿质">
                  <el-select v-model="editForm.tandeficiency" placeholder="请选择">
                    <el-option key="aa4" label="是" value="是"></el-option>
                    <el-option key="bb4" label="倾向是" value="倾向是"></el-option>
                    <el-option key="cc4" label="否" value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="(23),(25),(27),(30)的得分" label-width="170px">
                  <el-input type="te74" v-model="editForm.show5" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="湿热质">
                  <el-select v-model="editForm.shideficiency" placeholder="请选择">
                    <el-option key="aa5" label="是" value="是"></el-option>
                    <el-option key="bb5" label="倾向是" value="倾向是"></el-option>
                    <el-option key="cc5" label="否" value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="(19),(22),(24),(33)的得分" label-width="170px">
                  <el-input type="te75" v-model="editForm.show6" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="血瘀质">
                  <el-select v-model="editForm.xuedeficiency" placeholder="请选择">
                    <el-option key="aa6" label="是" value="是"></el-option>
                    <el-option key="bb6" label="倾向是" value="倾向是"></el-option>
                    <el-option key="cc6" label="否" value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="(5),(6),(7),(8)题的得分" label-width="170px">
                  <el-input type="te76" v-model="editForm.show5" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="气郁质">
                  <el-select v-model="editForm.qiyu" placeholder="请选择">
                    <el-option key="aa7" label="是" value="是"></el-option>
                    <el-option key="bb7" label="倾向是" value="倾向是"></el-option>
                    <el-option key="cc7" label="否" value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="(15),(17),(18),(20)的得分" label-width="170px">
                  <el-input type="te77" v-model="editForm.show6" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="特禀质">
                  <el-select v-model="editForm.specialenamel" placeholder="请选择">
                    <el-option key="aa8" label="是" value="是"></el-option>
                    <el-option key="bb8" label="倾向是" value="倾向是"></el-option>
                    <el-option key="cc8" label="否" value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="(1),(2),(4),(5),(13)的得分" label-width="170px">
                  <el-input type="te78" v-model="editForm.show7" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="平和质">
                  <el-select v-model="editForm.peace" placeholder="请选择">
                    <el-option key="aa9" label="是" value="是"></el-option>
                    <el-option key="bb9" label="基本是" value="基本是"></el-option>
                    <el-option key="cc9" label="否" value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="姓名" label-width="140px">
                  <el-input type="te1" v-model="editForm.name" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="编号">
                  <el-input type="te2" v-model="editForm.id"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="评分" label-width="140px">
                  <el-input type="te3" v-model="editForm.score"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="编号">
                  <el-input type="te4" v-model="editForm.id"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="体质类型辨识" label-width="140px">
                  <el-input type="te5" v-model="editForm.physicalIdentification"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="中医药保健指导">
                  <el-select v-model="editForm.chmedicineGuidance" placeholder="请选择">
                    <el-option key="aaaa32" label="1.情志调摄" value="1.情志调摄"></el-option>
                    <el-option key="bbbb32" label="2.饮食调养" value="2.饮食调养"></el-option>
                    <el-option key="cccc32" label="3.起居调摄" value="3.起居调摄"></el-option>
                    <el-option key="dddd32" label="4.运动保健" value="4.运动保健"></el-option>
                    <el-option key="eeee32" label="5.穴位保健" value="5.穴位保健"></el-option>
                    <el-option key="eeee32" label="6.其他" value="6.其他"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="医生签名" label-width="140px">
                  <el-input type="te7" v-model="editForm.doctorSignature"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="填表日期">
                  <el-date-picker type="date"  v-model="editForm.healthDate" style="width: 100%;"></el-date-picker>
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
  } from '@/api/healthmanagementecord'

  export default {
    data() {
      return {
        dialogStatus: '',
        tableHeight: window.innerHeight - 220,
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
          id: '',
          healthDate_s: '',
          healthDate_e: ''
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
          id: null,
          question: '',
          score: '',
          physicalType: '',
          physicalIdentification: '',
          chmedicineGuidance: '',
          doctorSignature: '',
          healthDate: '',
          creater: '1',
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
          '    <span style="margin-left:510px;">编号:{{id}}</span> \n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '     <tbody id="secondContent">\n' +
          '      <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\'style="word-wrap:break-word;width: 10%;text-align:center">请根据近一年的体验和感觉，回答以下问题。</td>\n' +
          '  \t          <td style="text-align:center">没有 (根本不/从来没有)</td>\n' +
          '  \t          <td style="text-align:center">很少 (有一点/偶尔）</td>\n' +
          '  \t          <td style="text-align:center">有时 (有些/少数时间)</td>\n' +
          '  \t          <td style="text-align:center">经常 (相当/多数时间)</td>\n' +
          '              <td style="text-align:center">总是 (非常/每天)</td>\n' +
          '  \t        </tr> \n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\'style="word-wrap:break-word;width: 10%;text-align:center">(1)您精力充沛吗?(指精神头足，乐于做事)</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(2)您容易疲乏吗?(指体力如何，是否稍微活动一下或做一点家务劳动 就感到累)</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(3)您容易气短，呼吸短促，接不上气吗?</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(4)您说话声音低弱无力吗?(指说话没有力气)</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(5)您感到闷闷不乐、情绪低沉吗?(指心情不愉快，情绪低落)</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(6)您容易精神紧张、焦虑不安吗?(指遇事是否心情紧张)</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        \n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(7)您因为生活状态改变而感到孤独、失落吗?</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(8)您容易感到害怕或受到惊吓吗?</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(9)您感到身体超重不轻松吗?(感觉身体沉重)[BMI指数=体重(kg)/身高2(m)]</td>\n' +
          '          <td style="text-align:center">1<br/>(BMI<24)</td>\n' +
          '  \t          <td style="text-align:center">2<br/>(24≤BMI<25)</td>\n' +
          '  \t          <td style="text-align:center">3<br/>(25≤BMI<26)</td>\n' +
          '  \t          <td style="text-align:center">4<br/>(26≤BMI<28)</td>\n' +
          '              <td style="text-align:center">5<br/>(BMI≥28)</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(10)您眼睛干涩吗?</td>\n' +
          '  \t         <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(11)您手脚发凉吗?(不包含因周围温度低或穿的少导致的手脚发冷)</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        \n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(12)您胃脘部、背部或腰膝部怕冷吗?(指上腹部、背部、腰部或膝关 节等，有一处或多处怕冷)</td>\n' +
          '  \t         <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(13)您比一般人耐受不了寒冷吗?(指比别人容易害怕冬天或是夏天的 冷空调、电扇等)</td>\n' +
          '  \t         <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(14)您容易患感冒吗?(指每年感冒的次数)</td>\n' +
          '  \t           <td style="text-align:center">1<br/>一年<2次</td>\n' +
          '  \t          <td style="text-align:center">2<br/>一年感冒2-4次</td>\n' +
          '  \t          <td style="text-align:center">3<br/>一年感冒5-6次</td>\n' +
          '  \t          <td style="text-align:center">4<br/>一年8次以上</td>\n' +
          '              <td style="text-align:center">5<br/>几乎每月</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(15)您没有感冒时也会鼻塞、流鼻涕吗?</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(16)您有口粘口腻，或睡眠打鼾吗?</td>\n' +
          '  \t         <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(17)您容易过敏(对药物、食物、气味、花粉或在季节交替、气候变化时) 吗?</td>\n' +
          '  \t          <td style="text-align:center">1<br/>从来没有</td>\n' +
          '  \t          <td style="text-align:center">2<br/>一年1、2次</td>\n' +
          '  \t          <td style="text-align:center">3<br/>一年3、4次</td>\n' +
          '  \t          <td style="text-align:center">4<br/>一年5、6次</td>\n' +
          '              <td style="text-align:center">5<br/>每次遇到上述原因都过敏</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(18)您的皮肤容易起荨麻疹吗? (包括风团、风疹块、风疙瘩)</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(19)您的皮肤在不知不觉中会出现青紫瘀斑、皮下出血吗?(指皮肤在没 有外伤的情况下出现青一块紫一块的情况)</td>\n' +
          '  \t         <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(20)您的皮肤一抓就红，并出现抓痕吗?(指被指甲或钝物划过后皮肤的反应)</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(21)您皮肤或口唇干吗?</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(22)您有肢体麻木或固定部位疼痛的感觉吗?</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(23)您面部或鼻部有油腻感或者油亮发光吗?(指脸上或鼻子)</td>\n' +
          '  \t         <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(24)您面色或目眶晦黯，或出现褐色斑块/斑点吗?</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(25)您有皮肤湿疹、疮疖吗?</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(26)您感到口干咽燥、总想喝水吗?</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(27)您感到口苦或嘴里有异味吗?(指口苦或口臭)</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(28)您腹部肥大吗?(指腹部脂肪肥厚)</td>\n' +
          '  \t          <td style="text-align:center">1<br/>(腹围 <80cm，相当 于 2.4 尺)</td>\n' +
          '  \t          <td style="text-align:center">2<br/>(腹围 80-85cm， 2.4-2.55 尺)</td>\n' +
          '  \t          <td style="text-align:center">3<br/>(腹围 86-90cm， 2.56-2.7 尺)</td>\n' +
          '  \t          <td style="text-align:center">4<br/>(腹围 91-105cm， 2.71-3.15 尺)</td>\n' +
          '              <td style="text-align:center">5<br/>(腹围>105cm\n' +
          '或 3.15 尺)</td>\n' +
          '  \t        </tr>\n' +
          '       <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(29)您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗?(指不喜 欢吃凉的食物，或吃了凉的食物后会不舒服)</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '       <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(30)您有大便黏滞不爽、解不尽的感觉吗?(大便容易粘在马桶或便坑壁上)</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '       <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(31)您容易大便干燥吗?</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '       <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(32)您舌苔厚腻或有舌苔厚厚的感觉吗?(如果自我感觉不清楚可由调查 员观察后填写)</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '       <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">(33)您舌下静脉瘀紫或增粗吗?(可由调查员辅助观察后填写)</td>\n' +
          '  \t          <td style="text-align:center">1</td>\n' +
          '  \t          <td style="text-align:center">2</td>\n' +
          '  \t          <td style="text-align:center">3</td>\n' +
          '  \t          <td style="text-align:center">4</td>\n' +
          '              <td style="text-align:center">5</td>\n' +
          '  \t        </tr>\n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td style="word-wrap:break-word;width: 10%;text-align:center">体质<br/>类型</td>\n' +
          '  \t          <td style="text-align:center">气虚质</td>\n' +
          '  \t          <td style="text-align:center">阳虚质</td>\n' +
          '  \t          <td style="text-align:center">阴虚质</td>\n' +
          '  \t          <td style="text-align:center">痰湿质</td>\n' +
          '              <td style="text-align:center">湿热质</td>\n' +
          '              <td style="text-align:center">血瘀质</td>\n' +
          '              <td style="text-align:center">气郁质</td>\n' +
          '              <td style="text-align:center">特禀质</td>\n' +
          '              <td style="text-align:center">特禀质</td>\n' +
          '  \t        </tr>\n' +
          '             \n' +
          '        <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td style="word-wrap:break-word;width: 10%;text-align:center">体质<br/> 辨识</td>\n' +
          '  \t          <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '  \t          <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '  \t          <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '  \t          <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '              <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '              <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '              <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '              <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '              <td style="text-align:center">1.得分<br/>2.是<br/>3.倾向是</td>\n' +
          '  \t        </tr>\n' +
          '       <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td style="word-wrap:break-word;width: 10%;text-align:center">中医药<br/>保健<br/>指导</td>\n' +
          '  \t          <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '  \t          <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '  \t          <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '  \t          <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '              <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '              <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '              <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '              <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '              <td style="text-align:center">1.情志调摄<br/>2.饮食调养<br/>3.起居调摄<br/>4.运动保健<br/>5.穴位保健<br/>6.其他:</td>\n' +
          '  \t        </tr>\n' +
          '             <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '              <td colspan=\'2\' style="word-wrap:break-word;width: 10%;text-align:center">填表日期</td>\n' +
          '  \t          <td colspan=\'3\' style="text-align:center">{{healthDate}}</td>\n' +
          '  \t          <td colspan=\'2\' style="text-align:center">医生签名</td>\n' +
          '  \t          <td colspan=\'3\' style="text-align:center">{{doctorSignature}}</td>\n' +
          '  \t         </tr>\n' +
          '         </tbody>\n' +
          '  </table>\n' +
          '</div>\n'
        str = str.replace(/{{id}}/, this.editForm.id ? this.editForm.id : '')
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{healthDate}}/, this.editForm.healthDate ? this.editForm.healthDate : '')
        str = str.replace(/{{doctorSignature}}/, this.editForm.doctorSignature ? this.editForm.doctorSignature : '')
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
        this.filters.healthDate_s =
          !this.filters.healthDate_s || this.filters.healthDate_s === ''
            ? null
            : util.formatDate.format(new Date(this.filters.healthDate_s), 'yyyy-MM-dd')
        this.filters.healthDate_e =
          !this.filters.healthDate_e || this.filters.healthDate_e === ''
            ? null
            : util.formatDate.format(new Date(this.filters.healthDate_e), 'yyyy-MM-dd')
        this.dialogFormVisible2 = false
        const para = {
          page: this.page,
          name: this.filters.name,
          id: this.filters.id,
          healthDate_s: this.filters.healthDate_s,
          healthDate_e: this.filters.healthDate_e
        }
        getUserListPage(para).then(res => {
          this.total = res.data.total
          this.users = res.data.h2
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
          name: '',
          id: '',
          healthDate_s: '',
          healthDate_e: ''
        }
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
      },
      findbtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          name: '',
          id: '',
          healthDate_s: '',
          healthDate_e: ''
        }
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.editForm = {
          name: '',
          id: null,
          question: '',
          score: '',
          physicalType: '',
          physicalIdentification: '',
          chmedicineGuidance: '',
          doctorSignature: '',
          healthDate: '',
          creater: '1',
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
                para.healthDate =
                  !para.healthDate || para.healthDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.healthDate), 'yyyy-MM-dd')
                para.createDate =
                  !para.createDate || para.createDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.createDate), 'yyyy-MM-dd')
                para.modifyDate =
                  !para.modifyDate || para.modifyDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.modifyDate), 'yyyy-MM-dd')
                para.modifyDate = new Date()
                para.modifyDate =
                  !para.modifyDate || para.modifyDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.modifyDate), 'yyyy-MM-dd')
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
                const para = Object.assign({}, this.editForm)
                // huoqudangqi
                para.healthDate =
                  !para.healthDate || para.healthDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.healthDate), 'yyyy-MM-dd')
                para.modifyDate = para.createDate
                para.createDate = new Date()
                para.createDate =
                  !para.createDate || para.createDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.createDate), 'yyyy-MM-dd')
                para.modifyDate = para.createDate
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
