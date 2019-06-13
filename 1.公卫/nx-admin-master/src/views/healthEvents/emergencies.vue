<template>
  <section class="app-container">
    <!--工具条-->
    <el-col :span="24" class="toolbar col-pad-bottom">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.eventName" placeholder="事件名称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="search" class="search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="searchwindow" class="high-search" icon="el-icon-search">高级查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button v-if="buttons.HEE_C" type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border class="el-table" @row-dblclick="handleRowChange">
      <el-table-column type="index" label="序号" width="70" align="center">
      </el-table-column>
      <el-table-column prop="reportingUnit" label="填报单位" width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="reportingDate" label="填报时间" width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="rapporteur" label="报告人" width="120" align="center">
      </el-table-column>
      <el-table-column prop="phone" label="联系电话" width="120" align="center">
      </el-table-column>
      <el-table-column prop="eventName" label="事件名称" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="messageClass" label="信息类别" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="eventLevel" label="突发事件等级" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="firstDiagnosis" label="初步诊断" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="firstDiagnosisTime" label="初步诊断时间" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="modifyDiagnosis" label="订正诊断" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="modifyDiagnosisTime" label="订正诊断时间" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="confirmGradeTime" label="确认分级时间" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="modifyGradeTime" label="订正分级时间" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="reportingArea" label="报告地区" min-width="240" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="occurrenceArea" label="发生地区" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="detailedLocation" label="详细地点" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="occurPlace" label="事件发生场所" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="messageSource" label="事件信息来源" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="sourceDetails" label="事件信息来源详细" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="sweepRange" label="事件波及的地域范围" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="newCase" label="新报告病例数" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="newMortality" label="新报告死亡数" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="removeCase" label="排除病例数" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="accumulativeCase" label="累计报告病例数" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="accumulativeMortality" label="累计报告死亡数" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="eventTime" label="事件发生时间" min-width="160" :formatter="timeFormat" align="center">
      </el-table-column>
      <el-table-column prop="reportingTime" label="接到报告时间" min-width="160" :formatter="timeFormat" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="firstDiseaseTime" label="首例病人发病时间" min-width="160" :formatter="timeFormat" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="lastDiseaseTime" label="末例病人发病时间" min-width="160" :formatter="timeFormat" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="mainSymptoms" label="主要症状" min-width="200" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="others" label="其他" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="majorSigns" label="主要体征" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="measureEffect" label="主要措施与效果" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="creator" label="创建人" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="createDate" label="创建时间" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="modifier" label="修改人" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column prop="modifyDate" label="修改时间" min-width="120" align="center" v-if='show'>
      </el-table-column>
      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <a v-if="buttons.HEE_E" size="14px" @click="handleEdit(scope.$index, scope.row)" class="edit">编辑</a>
          <a v-if="buttons.HEE_D" size="14px" @click="handleDel(scope.$index, scope.row)" class="delete">删除</a>

        </template>
      </el-table-column>
    </el-table>

    <!-- 查询窗口 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
      <el-form :model="filters" label-width="120px" :rules="editFormRules" ref="filters">
        <div class="container">
          <el-row>
            <el-col :span="12">
              <el-form-item label="起始填报时间">
                <el-date-picker type="date" placeholder="选择起始日期" v-model="filters.reportingDate_s" class="full"></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="结束填报时间">
                <el-date-picker type="date" placeholder="选择结束日期" v-model="filters.reportingDate_e" class="full"></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="起始发生时间">
                <el-date-picker type="date" placeholder="选择起始日期" v-model="filters.eventTime_s" class="full"></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="结束发生时间">
                <el-date-picker type="date" placeholder="选择结束日期" v-model="filters.eventTime_e" class="full"></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="信息类别">
                <el-select v-model="filters.messageClass" placeholder="信息类别" class="full">
                  <el-option v-for="item in messageClass" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="突发事件等级">
                <el-select v-model="filters.eventLevel" placeholder="突发事件等级" class="full">
                  <el-option v-for="item in eventLevel" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="报告人">
                  <el-input v-model="filters.rapporteur"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="事件信息来源">
                <el-select v-model="filters.messageSource" placeholder="事件信息来源" class="full">
                 <el-option v-for="item in messageSource" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item label="发生地区">
                <el-cascader :options="options" v-model="filters.occurrenceArea" class="full" placeholder="请选择" change-on-select></el-cascader>
              </el-form-item>
            </el-col>
          </el-row>
        </div>
      </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" v-on:click="highsearch" class="high-search" icon="el-icon-search">查询</el-button>
        </div>
    </el-dialog>
    <!--工具条-->
    <el-col class="col-page toolbar" :span="24">
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" class="page">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <div id="print">
      <el-form :model="editForm" label-width="0px" :rules="editFormRules" ref="editForm">
        <EasyScrollbar>
          <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
            <el-tabs v-model="activeName">
              <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd">
                <tbody>
                <tr v-if="dialogStatus==='detail'">
                  <td class="pad20">创建人</td>
                  <td class="pad10" colspan="1">
                      <el-input v-model="editForm.creator" class="full" v-if="dialogStatus==='detail'" readonly></el-input>
                  </td>
                  <td class="pad20">创建时间</td>
                  <td class="pad10" colspan="1">
                      <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" class="full" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                  </td>
                </tr>
                <tr v-if="dialogStatus==='detail'">
                  <td class="pad20">修改人</td>
                  <td class="pad10" colspan="1">
                      <el-input v-model="editForm.modifier" class="full" v-if="dialogStatus==='detail'" readonly></el-input>
                  </td>
                  <td class="pad20">修改时间</td>
                  <td class="pad10" colspan="1">
                      <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" class="full" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                  </td>
                </tr>
                <tr>
                  <td class="pad20 td-label">填报单位</td>
                  <td class="pad10 td-input" colspan="1">
                    <el-input v-model="editForm.reportingUnit"></el-input>
                  </td>
                  <td class="pad20 td-label">填报时间</td>
                  <td class="pad10 td-input" colspan="1">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.reportingDate" class="full"></el-date-picker>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">报告人</td>
                  <td class="pad10" colspan="1">
                    <el-input v-model="editForm.rapporteur"></el-input>
                  </td>
                  <td class="pad20">联系电话</td>
                  <td class="pad10" colspan="1">
                    <el-input v-model="editForm.phone"></el-input>
                  </td>
                </tr>
                <tr>
                  <td class="pad20 td-b"><span>*</span>事件名称</td>
                  <td class="pad10" colspan="3" style="text-align:center;padding-right: 0px;">
                    <el-form-item label="" prop="eventName">
                        <el-input v-model="editForm.eventName" width="100%" placeholder="请输入"></el-input>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">事件类别</td>
                  <td class="pad10" colspan="1">
                    <el-select v-model="editForm.messageClass" placeholder="请选择" class="full">
                      <el-option v-for="item in messageClass" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                  </td>
                  <td class="pad20">事件等级</td>
                  <td class="pad10" colspan="1">
                    <el-select v-model="editForm.eventLevel" placeholder="请选择" class="full">
                      <el-option v-for="item in eventLevel" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">初步诊断</td>
                  <td class="pad10" colspan="1">
                    <el-input v-model="editForm.firstDiagnosis"></el-input>
                  </td>
                  <td class="pad20">订正诊断</td>
                  <td class="pad10" colspan="1">
                    <el-input v-model="editForm.modifyDiagnosis"></el-input>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">初步诊断时间</td>
                  <td class="pad10" colspan="1">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.firstDiagnosisTime" class="full"></el-date-picker>
                  </td>
                  <td class="pad20">订正诊断时间</td>
                  <td class="pad10" colspan="1">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDiagnosisTime" class="full"></el-date-picker>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">确定分级时间</td>
                  <td class="pad10" colspan="1">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.confirmGradeTime" class="full"></el-date-picker>
                  </td>
                  <td class="pad20">订正分级时间</td>
                  <td class="pad10" colspan="1">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyGradeTime" class="full"></el-date-picker>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">报告地区</td>
                  <td class="pad10" colspan="1">
                    <el-cascader :options="options" v-model="reportingArea" class="full" placeholder="请选择" change-on-select></el-cascader>
                  </td>
                  <td class="pad20">发生地区</td>
                  <td class="pad10" colspan="1">
                    <el-cascader :options="options" v-model="occurrenceArea" class="full" placeholder="请选择" change-on-select></el-cascader>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">详细地点</td>
                  <td class="pad10" colspan="3">
                    <el-input v-model="editForm.detailedLocation"></el-input>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">事件发生场所</td>
                  <td class="pad10" colspan="3">
                    <el-cascader :options="options2" v-model="occurPlace" class="full" placeholder="请选择"></el-cascader>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">事件信息来源</td>
                  <td class="pad10" colspan="3">
                    <el-select v-model="editForm.messageSource" placeholder="请选择" class="full">
                      <el-option v-for="item in messageSource" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">事件信息来源详细</td>
                  <td class="pad10" colspan="3">
                        <el-input v-model="editForm.sourceDetails"></el-input>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">事件波及地域范围</td>
                  <td class="pad10" colspan="3">
                    <el-input v-model="editForm.sweepRange"></el-input>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">新报告病例数</td>
                  <td class="pad10 td-left" colspan="1">
                    <el-input-number class="el-input-number" v-model="editForm.newCase" :min="0" :max="200"></el-input-number>
                  </td>
                  <td class="pad20">新报告死亡数</td>
                  <td class="pad10 td-left" colspan="1">
                    <el-input-number class="el-input-number" v-model="editForm.newMortality" :min="0" :max="200"></el-input-number>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">排除病例数</td>
                  <td class="pad10 td-left" colspan="1">
                    <el-input-number class="el-input-number" v-model="editForm.removeCase" :min="0" :max="200"></el-input-number>
                  </td>
                  <td class="pad20">累积报告病例数</td>
                  <td class="pad10 td-left" colspan="1">
                    <el-input-number class="el-input-number" v-model="editForm.accumulativeCase" :min="0" :max="200"></el-input-number>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">累积报告死亡数</td>
                  <td class="pad10 td-left" colspan="3">
                    <el-input-number class="el-input-number" v-model="editForm.accumulativeMortality" :min="0" :max="200"></el-input-number>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">事件发生时间</td>
                  <td class="pad10" colspan="1">
                    <el-date-picker v-model="editForm.eventTime" value-format="yyyy-MM-dd HH:mm:ss" type="datetime" placeholder="选择日期时间" class="full"> </el-date-picker>
                  </td>
                  <td class="pad20">接到报告时间</td>
                  <td class="pad10" colspan="1">
                    <el-date-picker v-model="editForm.reportingTime" value-format="yyyy-MM-dd HH:mm:ss" type="datetime" placeholder="选择日期时间" class="full"> </el-date-picker>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">首例病人发病时间</td>
                  <td class="pad10" colspan="1">
                    <el-date-picker v-model="editForm.firstDiseaseTime" value-format="yyyy-MM-dd HH:mm:ss" type="datetime" placeholder="选择日期时间" class="full"> </el-date-picker>
                  </td>
                  <td class="pad20">末例发病时间</td>
                  <td class="pad10" colspan="1">
                    <el-date-picker v-model="editForm.lastDiseaseTime" value-format="yyyy-MM-dd HH:mm:ss" type="datetime" placeholder="选择日期时间" class="full"> </el-date-picker>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">主要症状</td>
                  <td colspan="3" style="padding-left:20px;text-align:left">
                    <el-checkbox-group v-model="mainSymptoms2" class="full">
                      <el-checkbox  v-for="item in mainSymptoms" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                    </el-checkbox-group>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">其他(症状描述)</td>
                  <td class="pad10" colspan="3">
                    <el-input v-model="editForm.others"></el-input>
                  </td>
                </tr>
                <tr>
                  <td class="pad20">主要体征</td>
                  <td class="pad10" colspan="3">
                    <el-input v-model="editForm.majorSigns"></el-input>
                  </td>
                </tr>
                <tr>
                  <td class="pad20" style="word-wrap:break-word;text-align:left;height: 160px">主要措施与效果</td>
                  <td class="pad10" colspan="3">
                    <el-input type="textarea" rows="6" v-model="editForm.measureEffect"></el-input>
                  </td>
                </tr>
                </tbody>
              </table>
            </el-tabs>
          </div>
        </EasyScrollbar>
      </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="printData()">打印</el-button>
        <el-button v-if="dialogStatus==='create'||dialogStatus==='update'" type="primary" @click="addData">添加</el-button>
        <el-button v-if="edit" v-else type="primary" @click="updateData">修改</el-button>
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
  } from '@/api/emergencies'
  import city from '@/api/city.js'
  export default {
    data() {
      return {
        buttons: {
          HEE_C: false,
          HEE_E: false,
          HEE_D: false
        },
        edit: true,
        tableHeight: window.innerHeight - 220,
        tablebodyHeight: window.innerHeight - 320,
        options: city.city(),
        options2: [{
          value: '学校',
          label: '学校',
          children: [
            {
              value: '托管机构',
              label: '托管机构'
            },
            {
              value: '小学',
              label: '小学'
            },
            {
              value: '中学',
              label: '中学'
            },
            {
              value: '大、中专院校',
              label: '大、中专院校'
            },
            {
              value: '综合类学校',
              label: '综合类学校'
            },
            {
              value: '其他',
              label: '其他'
            }
          ]
        },
        {
          value: '医疗卫生机构',
          label: '医疗卫生机构',
          children: [
            {
              value: '公办医疗机构',
              label: '公办医疗机构',
              children: [
                {
                  value: '病房',
                  label: '病房'
                },
                {
                  value: '手术室',
                  label: '手术室'
                },
                {
                  value: '门诊',
                  label: '门诊'
                },
                {
                  value: '化验室',
                  label: '化验室'
                },
                {
                  value: '药房',
                  label: '药房'
                },
                {
                  value: '办公室',
                  label: '办公室'
                },
                {
                  value: '治疗室',
                  label: '治疗室'
                },
                {
                  value: '特殊检查室',
                  label: '特殊检查室'
                },
                {
                  value: '其他场所',
                  label: '其他场所'
                }]
            },
            {
              value: '疾病预防控制机构',
              label: '疾病预防控制机构',
              children: [
                {
                  value: '病房',
                  label: '病房'
                },
                {
                  value: '手术室',
                  label: '手术室'
                },
                {
                  value: '门诊',
                  label: '门诊'
                },
                {
                  value: '化验室',
                  label: '化验室'
                },
                {
                  value: '药房',
                  label: '药房'
                },
                {
                  value: '办公室',
                  label: '办公室'
                },
                {
                  value: '治疗室',
                  label: '治疗室'
                },
                {
                  value: '特殊检查室',
                  label: '特殊检查室'
                },
                {
                  value: '其他场所',
                  label: '其他场所'
                }]
            },
            {
              value: '采供血机构',
              label: '采供血机构',
              children: [
                {
                  value: '病房',
                  label: '病房'
                },
                {
                  value: '手术室',
                  label: '手术室'
                },
                {
                  value: '门诊',
                  label: '门诊'
                },
                {
                  value: '化验室',
                  label: '化验室'
                },
                {
                  value: '药房',
                  label: '药房'
                },
                {
                  value: '办公室',
                  label: '办公室'
                },
                {
                  value: '治疗室',
                  label: '治疗室'
                },
                {
                  value: '特殊检查室',
                  label: '特殊检查室'
                },
                {
                  value: '其他场所',
                  label: '其他场所'
                }]
            },
            {
              value: '检验检疫机构',
              label: '检验检疫机构',
              children: [
                {
                  value: '病房',
                  label: '病房'
                },
                {
                  value: '手术室',
                  label: '手术室'
                },
                {
                  value: '门诊',
                  label: '门诊'
                },
                {
                  value: '化验室',
                  label: '化验室'
                },
                {
                  value: '药房',
                  label: '药房'
                },
                {
                  value: '办公室',
                  label: '办公室'
                },
                {
                  value: '治疗室',
                  label: '治疗室'
                },
                {
                  value: '特殊检查室',
                  label: '特殊检查室'
                },
                {
                  value: '其他场所',
                  label: '其他场所'
                }]
            },
            {
              value: '其他场所',
              label: '其他场所',
              children: [
                {
                  value: '病房',
                  label: '病房'
                },
                {
                  value: '手术室',
                  label: '手术室'
                },
                {
                  value: '门诊',
                  label: '门诊'
                },
                {
                  value: '化验室',
                  label: '化验室'
                },
                {
                  value: '药房',
                  label: '药房'
                },
                {
                  value: '办公室',
                  label: '办公室'
                },
                {
                  value: '治疗室',
                  label: '治疗室'
                },
                {
                  value: '特殊检查室',
                  label: '特殊检查室'
                },
                {
                  value: '其他场所',
                  label: '其他场所'
                }]
            }]
        },
        {
          value: '家庭',
          label: '家庭'
        },
        {
          value: '宾馆饭店写字楼',
          label: '宾馆饭店写字楼'
        },
        {
          value: '餐饮服务单位',
          label: '餐饮服务单位'
        },
        {
          value: '交通运输工具',
          label: '交通运输工具'
        },
        {
          value: '菜场、商场或超市',
          label: '菜场、商场或超市'
        },
        {
          value: '车子、码头或机场',
          label: '车子、码头或机场'
        },
        {
          value: '党政机关办公场所',
          label: '党政机关办公场所'
        },
        {
          value: '企事业单位办公场所',
          label: '企事业单位办公场所'
        },
        {
          value: '大型厂矿企业生产场所',
          label: '大型厂矿企业生产场所'
        },
        {
          value: '中小型厂矿企业生产场所',
          label: '中小型厂矿企业生产场所'
        },
        {
          value: '城市住宅小区',
          label: '城市住宅小区'
        },
        {
          value: '城市其他公共场所',
          label: '城市其他公共场所'
        },
        {
          value: '农村村庄',
          label: '农村村庄'
        },
        {
          value: '农村农田野外',
          label: '农村农田野外'
        },
        {
          value: '其他重要公共场所',
          label: '其他重要公共场所'
        }],
        messageClass: [
          { value: '传染病', label: '传染病' },
          { value: '食物中毒', label: '食物中毒' },
          { value: '职业中毒', label: '职业中毒' },
          { value: '其他中毒事件', label: '其他中毒事件' },
          { value: '环境卫生', label: '环境卫生' },
          { value: '免疫接种', label: '免疫接种' },
          { value: '群体性不明原因疾病', label: '群体性不明原因疾病' },
          { value: '医疗机构内感染', label: '医疗机构内感染' },
          { value: '放射性卫生', label: '放射性卫生' },
          { value: '其他', label: '其他' }
        ],
        eventLevel: [
          { value: '特别重大', label: '特别重大' },
          { value: '重大', label: '重大' },
          { value: '较大', label: '较大' },
          { value: '一般', label: '一般' },
          { value: '未分级', label: '未分级' },
          { value: '非突发事件', label: '非突发事件' },
          { value: '传染病', label: '传染病' }
        ],
        messageSource: [
          { value: '属地医疗机构', label: '属地医疗机构' },
          { value: '外地医疗机构', label: '外地医疗机构' },
          { value: '报纸', label: '报纸' },
          { value: '电视', label: '电视' },
          { value: '特服号电话95120', label: '特服号电话95120' },
          { value: '互联网', label: '互联网' },
          { value: '市民电话报告', label: '市民电话报告' },
          { value: '上门直接报告', label: '上门直接报告' },
          { value: '本系统字段预警产生', label: '本系统字段预警产生' },
          { value: '广播', label: '广播' },
          { value: '填报单位人员目睹', label: '填报单位人员目睹' },
          { value: '其他', label: '其他' }
        ],
        mainSymptoms: [
          { value: '呼吸道症状', label: '呼吸道症状' },
          { value: '胃肠道症状', label: '胃肠道症状' },
          { value: '神经系统症状', label: '神经系统症状' },
          { value: '皮肤粘膜症状', label: '皮肤粘膜症状' },
          { value: '精神症状', label: '精神症状' }
        ],
        reportingArea: [],
        occurrenceArea: [],
        occurPlace: [],
        mainSymptoms2: [],
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '传染病与突发公卫新增',
          search: '查询',
          detail: '详细'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          messageClass: '',
          eventLevel: '',
          eventName: '',
          reportingDate_s: null,
          reportingDate_e: null,
          rapporteur: '',
          occurrenceArea: [],
          occurPlace: '',
          messageSource: '',
          eventTime_s: null,
          eventTime_e: null
        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          eventName: [{ required: true, message: ' ', trigger: 'blur' }]
        },
        // 编辑界面数据
        editForm: {
          reportingUnit: '',
          reportingDate: '',
          rapporteur: '',
          phone: '',
          eventName: '',
          messageClass: '',
          eventLevel: '',
          firstDiagnosis: '',
          firstDiagnosisTime: '',
          modifyDiagnosis: '',
          modifyDiagnosisTime: '',
          confirmGradeTime: '',
          modifyGradeTime: '',
          reportingArea: '',
          occurrenceArea: '',
          detailedLocation: '',
          occurPlace: '',
          messageSource: '',
          sourceDetails: '',
          sweepRange: '',
          newCase: null,
          newMortality: null,
          removeCase: null,
          accumulativeCase: null,
          accumulativeMortality: null,
          eventTime: null,
          reportingTime: null,
          firstDiseaseTime: null,
          lastDiseaseTime: null,
          mainSymptoms: [],
          others: '',
          majorSigns: '',
          measureEffect: '',
          creator: null,
          createDate: null,
          modifier: null,
          modifyDate: null
        },
        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        },
        div: '<div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
          '  <div id="topContent">\n' +
          '    <h2 style="width:794px;text-align:center">突发公共卫生事件相关信息报告卡</h2>  \n' +
          '    <span style="margin-left:510px;">初步报告<input type="checkbox" style="margin-left:0" />进程报告( 次)<input type="checkbox" style="margin-left:0" />结案报告</span> \n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style=" width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '    <tbody>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="width:21%;text-align:center">填报单位</td>\n' +
          '        <td style="width: 29%;text-align:center">{{reportingUnit}}</td>\n' +
          '        <td style="width: 21%;text-align:center">填报日期</td>\n' +
          '        <td style="width: 29%;text-align:center">{{reportingDate}}</td>\n' +
          '      </tr>\n' +
          '\t\t <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">报告人</td>\n' +
          '        <td style="width: 29%;text-align:center">{{rapporteur}}</td>\n' +
          '        <td style="width: 21%;text-align:center">联系电话</td>\n' +
          '        <td style="width: 29%;text-align:center">{{phone}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">事件名称</td>\n' +
          '        <td colspan=\'4\' style="text-align:center">{{eventName}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">信息类别</td>\n' +
          '        <td style="width: 29%;text-align:center">{{messageClass}}</td>\n' +
          '        <td style="width: 21%;text-align:center">公卫突发事件级别</td>\n' +
          '        <td style="width: 19%;text-align:center">{{eventLevel}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">初步诊断</td>\n' +
          '        <td style="width: 29%;text-align:center">{{firstDiagnosis}}</td>\n' +
          '        <td style="width: 21%;text-align:center">初步诊断时间</td>\n' +
          '        <td style="width: 19%;text-align:center">{{firstDiagnosisTime}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">订正诊断</td>\n' +
          '        <td style="width: 29%;text-align:center">{{modifyDiagnosis}}</td>\n' +
          '        <td style="width: 21%;text-align:center">订正诊断时间</td>\n' +
          '        <td style="width: 19%;text-align:center">{{modifyDiagnosisTime}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">确认分级时间</td>\n' +
          '        <td style="width: 29%;text-align:center">{{confirmGradeTime}}</td>\n' +
          '        <td style="width: 21%;text-align:center">订正分级时间</td>\n' +
          '        <td style="width: 19%;text-align:center">{{modifyGradeTime}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">报告地区</td>\n' +
          '        <td style="width: 29%;text-align:center">{{reportingArea}}</td>\n' +
          '        <td style="width: 21%;text-align:center">发生地区</td>\n' +
          '        <td style="width: 19%;text-align:center">{{occurrenceArea}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">详细地点</td>\n' +
          '        <td colspan=\'4 \'style="text-align:center">{{detailedLocation}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">事件发生场所</td>\n' +
          '        <td colspan=\'4\' style="text-align:center">{{occurPlace}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">事件信息来源</td>\n' +
          '        <td colspan=\'4\' style="text-align:center">{{messageSource}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">事件信息来源详情</td>\n' +
          '        <td colspan=\'4\' style="text-align:center">{{sourceDetails}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">事件波及的地域范围</td>\n' +
          '        <td colspan=\'4\' style="text-align:center">{{sweepRange}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">新报告病例数</td>\n' +
          '        <td style="width: 29%;text-align:center">{{newCase}}</td>\n' +
          '        <td style="width: 21%;text-align:center">新报告死亡数</td>\n' +
          '        <td style="width: 19%;text-align:center">{{newMortality}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">累积报告病例数</td>\n' +
          '        <td style="width: 29%;text-align:center">{{accumulativeCase}}</td>\n' +
          '        <td style="width: 21%;text-align:center">累积报告死亡数</td>\n' +
          '        <td style="width: 19%;text-align:center">{{accumulativeMortality}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">排除病例数</td>\n' +
          '        <td colspan=\'4\' style="text-align:center">{{removeCase}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">事件发生时间</td>\n' +
          '        <td colspan=\'4\' style="text-align:center">{{eventTime}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">接到报告时间</td>\n' +
          '        <td colspan=\'4\' style="text-align:center">{{reportingTime}}</td>\n' +
          '      </tr> \n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">首例病人发病时间</td>\n' +
          '        <td colspan=\'4\' style="text-align:center">{{firstDiseaseTime}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">末例病人发病时间</td>\n' +
          '        <td colspan=\'4\' style="text-align:center">{{lastDiseaseTime}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">主要症状</td>\n' +
          '        <td colspan=\'4\' style="text-align:center">{{mainSymptoms}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td colspan=\'4\' style="border-bottom:solid 0px;word-wrap:break-word;width: 21%;text-align:left">其他(对症状的详细描述详填):</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 50px">\n' +
          '        <td VALIGN="top" colspan=\'4\' style="border-top:solid 0px;word-wrap:break-word;width: 21%;text-align:left">{{others}}</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 30px">\n' +
          '        <td style="word-wrap:break-word;width: 21%;text-align:center">主要体征</td>\n' +
          '        <td colspan=\'4\' style="text-align:center">{{majorSigns}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td colspan=\'4\' style="border-bottom:solid 0px;word-wrap:break-word;width: 21%;text-align:left">主要措施与效果:</td>\n' +
          '      </tr>\n' +
          '      <tr style="height: 150px">\n' +
          '        <td VALIGN="top" colspan=\'4\' style="border-top:solid 0px;word-wrap:break-word;width: 21%;text-align:left">{{measureEffect}}</td>\n' +
          '      </tr>     \n' +
          '  </table>\n' +
          '</div>'
      }
    },
    methods: {
      // 日期时间格式化列表用
      timeFormat: function(row, column) {
        let date = row[column.property]
        date =
          !date || date === ''
            ? ''
            : util.formatDate.format(new Date(date), 'yyyy-MM-dd hh:mm:ss')
        return date
      },
      // 日期格式化
      dateFormat: function(date) {
        date =
          !date || date === ''
            ? null
            : util.formatDate.format(new Date(date), 'yyyy-MM-dd')
        return date
      },
      // 日期时间格式化
      dateTimeFormat: function(datetime) {
        datetime =
          !datetime || datetime === ''
            ? null
            : util.formatDate.format(new Date(datetime), 'yyyy-MM-dd hh:mm:ss')
        return datetime
      },
      // 性别显示转换
      formatSex: function(row, column) {
        return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
      },
      handleCurrentChange(val) {
        this.page = val
        this.getList()
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
        const style = "<style type='text/css'>table.gridtable {font-family: verdana,arial,sans-serif;font-size:24px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;}table.gridtable th {border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;}table.gridtable td {border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;}</style>"
        var str = style + this.div
        str = str.replace(/{{reportingUnit}}/, this.editForm.reportingUnit ? this.editForm.reportingUnit : '')
        str = str.replace(/{{reportingDate}}/, this.editForm.reportingDate ? this.editForm.reportingDate : '')
        str = str.replace(/{{rapporteur}}/, this.editForm.rapporteur ? this.editForm.rapporteur : '')
        str = str.replace(/{{phone}}/, this.editForm.phone ? this.editForm.phone : '')
        str = str.replace(/{{eventName}}/, this.editForm.eventName ? this.editForm.eventName : '')
        str = str.replace(/{{messageClass}}/, this.editForm.messageClass ? this.editForm.messageClass : '')
        str = str.replace(/{{eventLevel}}/, this.editForm.eventLevel ? this.editForm.eventLevel : '')
        str = str.replace(/{{firstDiagnosis}}/, this.editForm.firstDiagnosis ? this.editForm.firstDiagnosis : '')
        str = str.replace(/{{firstDiagnosisTime}}/, this.editForm.firstDiagnosisTime ? this.editForm.firstDiagnosisTime : '')
        str = str.replace(/{{modifyDiagnosis}}/, this.editForm.modifyDiagnosis ? this.editForm.modifyDiagnosis : '')
        str = str.replace(/{{modifyDiagnosisTime}}/, this.editForm.modifyDiagnosisTime ? this.editForm.modifyDiagnosisTime : '')
        str = str.replace(/{{confirmGradeTime}}/, this.editForm.confirmGradeTime ? this.editForm.confirmGradeTime : '')
        str = str.replace(/{{modifyGradeTime}}/, this.editForm.modifyGradeTime ? this.editForm.modifyGradeTime : '')
        str = str.replace(/{{reportingArea}}/, this.editForm.reportingArea ? this.editForm.reportingArea : '')
        str = str.replace(/{{occurrenceArea}}/, this.editForm.occurrenceArea ? this.editForm.occurrenceArea : '')
        str = str.replace(/{{detailedLocation}}/, this.editForm.detailedLocation ? this.editForm.detailedLocation : '')
        str = str.replace(/{{occurPlace}}/, this.editForm.occurPlace ? this.editForm.occurPlace : '')
        str = str.replace(/{{phone}}/, this.editForm.phone ? this.editForm.phone : '')
        str = str.replace(/{{messageSource}}/, this.editForm.messageSource ? this.editForm.messageSource : '')
        str = str.replace(/{{sourceDetails}}/, this.editForm.sourceDetails ? this.editForm.sourceDetails : '')
        str = str.replace(/{{sweepRange}}/, this.editForm.sweepRange ? this.editForm.sweepRange : '')
        str = str.replace(/{{newCase}}/, this.editForm.newCase ? this.editForm.newCase : '')
        str = str.replace(/{{newMortality}}/, this.editForm.newMortality ? this.editForm.newMortality : '')
        str = str.replace(/{{removeCase}}/, this.editForm.removeCase ? this.editForm.removeCase : '')
        str = str.replace(/{{accumulativeCase}}/, this.editForm.accumulativeCase ? this.editForm.accumulativeCase : '')
        str = str.replace(/{{accumulativeMortality}}/, this.editForm.accumulativeMortality ? this.editForm.accumulativeMortality : '')
        str = str.replace(/{{eventTime}}/, this.editForm.eventTime ? this.editForm.eventTime : '')
        str = str.replace(/{{reportingTime}}/, this.editForm.reportingTime ? this.editForm.reportingTime : '')
        str = str.replace(/{{firstDiseaseTime}}/, this.editForm.firstDiseaseTime ? this.editForm.firstDiseaseTime : '')
        str = str.replace(/{{lastDiseaseTime}}/, this.editForm.lastDiseaseTime ? this.editForm.lastDiseaseTime : '')
        str = str.replace(/{{mainSymptoms}}/, this.editForm.mainSymptoms ? this.editForm.mainSymptoms : '')
        str = str.replace(/{{others}}/, this.editForm.others ? this.editForm.others : '')
        str = str.replace(/{{majorSigns}}/, this.editForm.majorSigns ? this.editForm.majorSigns : '')
        str = str.replace(/{{measureEffect}}/, this.editForm.measureEffect ? this.editForm.measureEffect : '')
        myWindow.document.write(str)
        myWindow.focus()
        myWindow.document.close()
        myWindow.print()
        return myWindow
      },
      printData() {
        const windows = this.printf(document.getElementById('print').innerHTML)
        windows.close()
      },
      // 查询
      search() {
        this.filters = {
          messageClass: '',
          eventLevel: '',
          reportingDate_s: null,
          reportingDate_e: null,
          rapporteur: '',
          occurrenceArea: [],
          occurPlace: '',
          messageSource: '',
          eventTime_s: null,
          eventTime_e: null,
          eventName: this.filters.eventName
        }
        this.page = 1
        this.getList()
      },
      highsearch() {
        this.page = 1
        this.getList()
      },
      searchwindow() {
        this.filters = {
          messageClass: '',
          eventLevel: '',
          reportingDate_s: null,
          reportingDate_e: null,
          rapporteur: '',
          occurrenceArea: [],
          occurPlace: '',
          messageSource: '',
          eventTime_s: null,
          eventTime_e: null,
          eventName: this.filters.eventName
        }
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
      },
      // 获取用户列表
      getList() {
        this.dialogFormVisible2 = false
        const para = {
          page: this.page,
          size: 20,
          filters: { messageClass: this.filters.messageClass, eventLevel: this.filters.eventLevel, eventName: this.filters.eventName,
            reportingDate_s: this.dateFormat(this.filters.reportingDate_s), reportingDate_e: this.dateFormat(this.filters.reportingDate_e),
            rapporteur: this.filters.rapporteur, messageSource: this.filters.messageSource, eventTime: this.dateFormat(this.filters.eventTime),
            eventTime_s: this.dateFormat(this.filters.eventTime_s), eventTime_e: this.dateFormat(this.filters.eventTime_e) }
        }
        para.filters.occurrenceArea = ''
        for (const itm of this.filters.occurrenceArea) {
          para.filters.occurrenceArea += itm
          para.filters.occurrenceArea += '/'
        }
        para.filters.occurrenceArea = para.filters.occurrenceArea.substr(0, para.filters.occurrenceArea.length - 1)
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
        this.occurPlace = []
        this.occurrenceArea = []
        this.reportingArea = []
        this.mainSymptoms2 = []
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.reportingArea = this.editForm.reportingArea.split('/')
        this.occurrenceArea = this.editForm.occurrenceArea.split('/')
        this.occurPlace = this.editForm.occurPlace.split('/')
        this.mainSymptoms2 = this.editForm.mainSymptoms.split(',')
        this.edit = true
      },
      // 显示详细界面
      handleRowChange(row, event) {
        this.dialogStatus = 'detail'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.edit = false
      },
      // 显示新增界面
      handleAdd() {
        this.occurPlace = []
        this.occurrenceArea = []
        this.reportingArea = []
        this.mainSymptoms2 = []
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.editForm = {
          reportingUnit: '',
          reportingDate: '',
          rapporteur: '',
          phone: '',
          eventName: '',
          messageClass: '',
          eventLevel: '',
          firstDiagnosis: '',
          firstDiagnosisTime: '',
          modifyDiagnosis: '',
          modifyDiagnosisTime: '',
          confirmGradeTime: '',
          modifyGradeTime: '',
          reportingArea: '',
          occurrenceArea: '',
          detailedLocation: '',
          occurPlace: '',
          messageSource: '',
          sourceDetails: '',
          sweepRange: '',
          newCase: null,
          newMortality: null,
          removeCase: null,
          accumulativeCase: null,
          accumulativeMortality: null,
          eventTime: null,
          reportingTime: null,
          firstDiseaseTime: null,
          lastDiseaseTime: null,
          mainSymptoms: [],
          others: '',
          majorSigns: '',
          measureEffect: '',
          creator: this.$store.getters.name,
          modifier: null
        }
        this.edit = false
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认修改吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.modifier = this.$store.getters.name
                para.eventTime = this.dateTimeFormat(para.eventTime)
                para.reportingTime = this.dateTimeFormat(para.reportingTime)
                para.lastDiseaseTime = this.dateTimeFormat(para.lastDiseaseTime)
                para.firstDiseaseTime = this.dateTimeFormat(para.firstDiseaseTime)
                para.reportingDate = this.dateFormat(para.reportingDate)
                para.modifyGradeTime = this.dateFormat(para.modifyGradeTime)
                para.modifyDiagnosisTime = this.dateFormat(para.modifyDiagnosisTime)
                para.confirmGradeTime = this.dateFormat(para.confirmGradeTime)
                para.firstDiagnosisTime = this.dateFormat(para.firstDiagnosisTime)
                para.newCase = para.newCase ? Math.round(para.newCase) : null
                para.newMortality = para.newMortality ? Math.round(para.newMortality) : null
                para.accumulativeCase = para.accumulativeCase ? Math.round(para.accumulativeCase) : null
                para.accumulativeMortality = para.accumulativeMortality ? Math.round(para.accumulativeMortality) : null
                para.removeCase = para.removeCase ? Math.round(para.removeCase) : null
                // 获取级联数据
                para.reportingArea = ''
                for (const itm of this.reportingArea) {
                  para.reportingArea += itm
                  para.reportingArea += '/'
                }
                para.reportingArea = para.reportingArea.substr(0, para.reportingArea.length - 1)
                para.occurrenceArea = ''
                for (const itm of this.occurrenceArea) {
                  para.occurrenceArea += itm
                  para.occurrenceArea += '/'
                }
                para.occurrenceArea = para.occurrenceArea.substr(0, para.occurrenceArea.length - 1)
                para.occurPlace = ''
                for (const itm of this.occurPlace) {
                  para.occurPlace += itm
                  para.occurPlace += '/'
                }
                para.occurPlace = para.occurPlace.substr(0, para.occurPlace.length - 1)
                para.mainSymptoms = ''
                for (const itm of this.mainSymptoms2) {
                  if (itm !== '') {
                    para.mainSymptoms += itm
                    para.mainSymptoms += ','
                  }
                }
                para.mainSymptoms = para.mainSymptoms.substr(0, para.mainSymptoms.length - 1)
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
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.eventTime = this.dateTimeFormat(para.eventTime)
                para.reportingTime = this.dateTimeFormat(para.reportingTime)
                para.lastDiseaseTime = this.dateTimeFormat(para.lastDiseaseTime)
                para.firstDiseaseTime = this.dateTimeFormat(para.firstDiseaseTime)
                para.reportingDate = this.dateFormat(para.reportingDate)
                para.modifyGradeTime = this.dateFormat(para.modifyGradeTime)
                para.modifyDiagnosisTime = this.dateFormat(para.modifyDiagnosisTime)
                para.confirmGradeTime = this.dateFormat(para.confirmGradeTime)
                para.firstDiagnosisTime = this.dateFormat(para.firstDiagnosisTime)
                para.newCase = para.newCase ? Math.round(para.newCase) : null
                para.newMortality = para.newMortality ? Math.round(para.newMortality) : null
                para.accumulativeCase = para.accumulativeCase ? Math.round(para.accumulativeCase) : null
                para.accumulativeMortality = para.accumulativeMortality ? Math.round(para.accumulativeMortality) : null
                para.removeCase = para.removeCase ? Math.round(para.removeCase) : null
                // 获取级联数据
                para.reportingArea = ''
                for (const itm of this.reportingArea) {
                  para.reportingArea += itm
                  para.reportingArea += '/'
                }
                para.reportingArea = para.reportingArea.substr(0, para.reportingArea.length - 1)
                para.occurrenceArea = ''
                for (const itm of this.occurrenceArea) {
                  para.occurrenceArea += itm
                  para.occurrenceArea += '/'
                }
                para.occurrenceArea = para.occurrenceArea.substr(0, para.occurrenceArea.length - 1)
                para.occurPlace = ''
                for (const itm of this.occurPlace) {
                  para.occurPlace += itm
                  para.occurPlace += '/'
                }
                para.occurPlace = para.occurPlace.substr(0, para.occurPlace.length - 1)
                para.mainSymptoms = ''
                for (const itm of this.mainSymptoms2) {
                  para.mainSymptoms += itm
                  para.mainSymptoms += ','
                }
                para.mainSymptoms = para.mainSymptoms.substr(0, para.mainSymptoms.length - 1)
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
      batchRemoveData() {
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
  .pad20{
    padding-left: 20px;
    word-wrap:break-word;
    text-align:left;
  }
  span{
    color:red;
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
  .high-search{
    background-color:#4db2ee;
    border-color:transparent;
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
  .col-page{
    margin-top:30px;
  }
  .page{
    float:right;
  }
  .full{
    width: 100%;
  }
  .td-left{
    text-align: left;
  }
  .el-input-number{
    width: 200px;
  }
  .td-label{
    word-wrap:break-word;
    text-align:left;
    width: 190px;
  }
  .td-input{
    text-align:center;
    width:290px;
  }
  #table{
    margin-left:10px;
    margin-right:30px;
    border-collapse:collapse;
  }
  .td-b{
    word-wrap:break-word;
    text-align:left;
    height: 50px;
  }
</style>

