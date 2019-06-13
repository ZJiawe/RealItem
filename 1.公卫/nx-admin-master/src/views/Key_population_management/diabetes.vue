<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="24" class="toolbar" >
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.peopleId" placeholder="身份证号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="search" style="background-color: #4db2ee;border-color: transparent;height: 40px;" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-if="buttons.KPD_C" @click="handleAdd" style="background-color: #05cc96;border-color: transparent;height: 40px;"  icon="el-icon-circle-plus-outline" >新增</el-button>
        </el-form-item>
      </el-form>

    </el-col>
    <!--列表-->
    <div id="print">
      <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange"  border style="width: 100%;"@row-dblclick="handleRowChange">
        <el-table-column type="index" label="序号"   width="60" align="center">
        </el-table-column>
        <el-table-column prop="peopleId" label="身份证"  min-width="160" align="center">
        </el-table-column>
        <el-table-column prop="name" label="姓名"  min-width="80" align="center">
        </el-table-column>
        <el-table-column prop="id" v-if="false"  label="编号"  min-width="60" align="center">
        </el-table-column>
        <el-table-column prop="Count" v-if="false" label="次数"  min-width="60" align="center">
        </el-table-column>
        <el-table-column prop="symptoms" label="症状"  min-width="80" align="center">
        </el-table-column>
        <el-table-column prop="footArteyPulse" v-if="false" label="足背动脉搏动" min-width="120" align="center">
        </el-table-column>
        <el-table-column prop="pressure" v-if="false" label="血压(mmHg)" min-width="120" align="center">
        </el-table-column>
        <el-table-column prop="weight" v-if="false" label="体重(Kg)" min-width="100" align="center">
        </el-table-column>
        <el-table-column prop="bodyMassIndex" v-if="false" label="体质指数(kg/m²)" min-width="140" align="center">
        </el-table-column>
        <el-table-column prop="dailySmoking" v-if="false" label="日吸烟量(支)" min-width="120" align="center">
        </el-table-column>
        <el-table-column prop="dailyDrinking" v-if="false" label="日饮酒量(两)" min-width="120" align="center">
        </el-table-column>
        <!--<el-col>-->
        <!--<el-table-column prop="exercise1" label="运动(天)" min-width="60" align="center">-->
        <!--</el-table-column>-->
        <!--</el-col>-->
        <!--<el-col>-->
        <!--<el-table-column prop="exercise2" label="运动(天)" min-width="60" align="center">-->
        <!--</el-table-column>-->
        <!--</el-col>-->
        <el-table-column prop="exercise" v-if="false"label="运动" min-width="60" align="center">
        </el-table-column>
        <el-table-column prop="stapleFood" v-if="false" label="主食(克/天)" min-width="120" align="center">
        </el-table-column>
        <el-table-column prop="other" v-if="false" label="其他" min-width="80" align="center">
        </el-table-column>
        <el-table-column prop="psychologicalAdjust" v-if="false" label="心理调整" min-width="100" align="center">
        </el-table-column>
        <el-table-column prop="complianceBehavior" v-if="false" label="遵医行为" min-width="100" align="center">
        </el-table-column>
        <el-table-column prop="fastingBlood" v-if="false"label="空腹血糖值(mol/L)" min-width="160" align="center">
        </el-table-column>
        <el-table-column prop="hemoglobin" v-if="false"label="糖化血红蛋白(%)" min-width="150" align="center">
        </el-table-column>
        <el-table-column prop="examinationDate"v-if="false" label="检查日期" :formatter="timeFormat" min-width="100" align="center">
        </el-table-column>
        <el-table-column prop="diabetescol" label="服药依从性" min-width="110" align="center">
        </el-table-column>
        <el-table-column prop="drugsAdversed" label="药物不良反应" min-width="120" align="center">
        </el-table-column>
        <el-table-column prop="hypoglycemia" label="低血糖反应" min-width="110" align="center">
        </el-table-column>
        <el-table-column prop="followClassification" label="此次随访分类" min-width="120">
        </el-table-column>
        <el-table-column prop="drugName"v-if="false" label="药物名称" min-width="100" align="center">
        </el-table-column>
        <el-table-column prop="usageDosage"v-if="false" label="用法用量" min-width="100" align="center">
        </el-table-column>
        <el-table-column prop="referralCause" label="转诊原因" min-width="100" align="center">
        </el-table-column>
        <el-table-column prop="referralInstitution" label="转诊机构及科别" min-width="140">
        </el-table-column>
        <el-table-column prop="followDate" label="本次随访日期" :formatter="timeFormat" min-width="120" align="center">
        </el-table-column>
        <el-table-column prop="nextFollowDate" v-if="false"  label="下次随访日期" :formatter="timeFormat" min-width="120" align="center">
        </el-table-column>
        <el-table-column prop="followWay" v-if="false"  label="随访方式" min-width="100" align="center">
        </el-table-column>
        <el-table-column prop="doctorSign" label="随访医生签名" min-width="120" align="center">
        </el-table-column>
        <el-table-column label="操作" width="250" align="center">
          <template slot-scope="scope">
            <a size="14px" @click="goto(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">查看档案</a>
            <a  size="14px" v-if="buttons.KPD_E" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
            <a  size="14px" v-if="buttons.KPD_D" @click="handleDel(scope.$index, scope.row)"style="color:#FB4141;margin-right:20px;">删除</a>
            <!--<a size="14px" @click="conprint(scope.$index, scope.row)" style="color:#4DB2EE;">打印</a>-->
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="120px" :rules="editFormRules" ref="editForm">
        <EasyScrollbar>
        <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
        <el-tabs v-model="activeName">
          <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:30px;border-collapse:collapse;">
            <tbody>

            <tr>
              <td style="width:140px;word-wrap:break-word;text-align:center;"><span>*</span>姓名</td>
            <td colspan="3" class="pad10" style="padding-right: 0px;">
            <el-form-item  label=""  label-width="0px" prop="name">
              <el-input v-model="editForm.name" auto-complete="off" disabled placeholder="请输入姓名" style="width: 100%;"></el-input>
            </el-form-item>
            </td>
              <td style="width:140px;word-wrap:break-word;text-align:center;"><span>*</span>身份证号</td>
              <td colspan="3"  class="pad10" style="padding-right: 0px;">
                <el-form-item label=""  label-width="0px" prop="peopleId" >
                  <el-input  v-model="editForm.peopleId" auto-complete="off" placeholder="请输入身份证号"style="width: 100%;" v-on:input="inputFunc1"></el-input>
                </el-form-item>
            </td>
            </tr>
            <!--<tr>-->
              <!--<td style="width:80px;word-wrap:break-word;text-align:center;"><span>*</span>区域</td>-->
              <!--<td colspan="7"  style="text-align:center">-->
                <!--<el-cascader :options="citys" v-model="editForm.area"  style=" width: 100%;" change-on-select></el-cascader>-->
              <!--</td>-->
            <!--</tr>-->
            <tr>
            <td style="word-wrap:break-word;text-align:center">症状</td>
            <td colspan="7" style="text-align:left" class="pad20">
          <el-checkbox-group v-model="editForm.symptoms" style="width: 100%" >
            <el-checkbox v-for="item in symptoms" style="margin-left: 6px" :key="item.label" :name="type" :value="item.value" :label="item.label"></el-checkbox>
          </el-checkbox-group>
            </td>
            </tr>



            <tr>
              <td rowspan="3"style="word-wrap:break-word;width:80px;text-align:center"> 体征</td>
              <td style="word-wrap:break-word;text-align:center">足背动脉搏动</td>
              <td colspan="3"class="pad10" style="padding-right: 0px;">
            <el-form-item label="" label-width="0px" aria-placeholder="请选择">
              <el-cascader :options="options" v-model="footArteyPulse" style="width:100%" ></el-cascader>
            </el-form-item>
              </td>

              <td style="word-wrap:break-word;text-align:center">体质指数</td>
              <td colspan="3" class="pad10" style="padding-right: 0px;">
            <el-form-item label=""  label-width="0px">
              <el-input  v-model="editForm.bodyMassIndex" auto-complete="off"style="width:100%">
                <template slot="append">kg/m²</template>
              </el-input>
            </el-form-item>
              </td>
            </tr>
            <tr>
              <td style="word-wrap:break-word;text-align:center">血压</td>
              <td colspan="3" class="pad10">
              <el-input  v-model="editForm.pressure" auto-complete="off" >
                <template slot="append">mmHg</template>
              </el-input>
              <td style="word-wrap:break-word;text-align:center">体重</td>
              <td colspan="3" class="pad10" >
              <el-input v-model="editForm.weight" auto-complete="off" >
                <template slot="append">Kg</template>
              </el-input>
              </td>
            </tr>

            <tr>
              <td style="word-wrap:break-word;text-align:center">其他</td>
              <td colspan="6" class="pad10">
              <el-input  v-model="editForm.other" auto-complete="off" ></el-input>
              </td>
            </tr>

            <tr>
            <td rowspan="3"style="word-wrap:break-word;width:80px;text-align:center"> 生活方式指导</td>
              <td style="word-wrap:break-word;text-align:center">日吸烟量</td>
              <td colspan="3" class="pad10">

                <el-input v-model="editForm.dailySmoking" auto-complete="off" >
                  <template slot="append">支</template>
                </el-input>
              </td>
              <td style="word-wrap:break-word;text-align:center">日饮酒量</td>
              <td colspan="3" class="pad10">
                <el-input  v-model="editForm.dailyDrinking" auto-complete="off">
                  <template slot="append">两</template>
                </el-input>
              </td>
            </tr>
            <tr>
              <td style="word-wrap:break-word;text-align:center">运动</td>
              <td colspan="3" class="pad10">

                <el-input v-model="editForm.exercise" auto-complete="off">
                  <template slot="append">次/周</template>
                </el-input>
              </td>
              <td style="word-wrap:break-word;text-align:center">主食</td>
              <td colspan="3" class="pad10">

                <el-input v-model="editForm.stapleFood" auto-complete="off" >
                  <template slot="append">克/天</template>
                </el-input>
              </td>
            </tr>
            <tr>
              <td style="word-wrap:break-word;text-align:center">心理调整</td>
              <td colspan="3" style="text-align:left" class="pad10">
              <el-radio-group v-model="editForm.psychologicalAdjust">
                <el-radio label=良好 >良好</el-radio>
                <el-radio label=一般>一般</el-radio>
                <el-radio label=差 >差</el-radio>
              </el-radio-group>
              </td>
              <td style="word-wrap:break-word;text-align:center">遵医行为</td>
              <td colspan="3" style="text-align:left" class="pad10">
              <el-radio-group v-model="editForm.complianceBehavior">
                <el-radio label=良好   >良好</el-radio>
                <el-radio label=一般 >一般</el-radio>
                <el-radio label=差  >差</el-radio>
              </el-radio-group>
              </td>
            </tr>

            <tr>
              <td rowspan="2"style="word-wrap:break-word;width:80px;text-align:center"> 辅助检查</td>
              <td style="word-wrap:break-word;text-align:center">空腹血糖值</td>
              <td colspan="6" class="pad10">
              <el-input v-model="editForm.fastingBlood" >
                <template slot="append">mol/L</template>
              </el-input>
              </td>
            </tr>
            <tr>
              <td style="word-wrap:break-word;text-align:center">其他检查</td>
              <td style="word-wrap:break-word;text-align:center">糖化血红蛋白</td>
              <td colspan="3" class="pad10">
                  <el-input v-model="editForm.hemoglobin" auto-complete="off" >
                    <template slot="append">(%)</template>
                  </el-input>
              </td>
              <td style="word-wrap:break-word;text-align:center">检查日期</td>
              <td colspan="3" class="pad10">
                  <el-date-picker type="date"  v-model="editForm.examinationDate" style="width: 100%;"></el-date-picker>
              </td>
            </tr>

            <tr>
              <td style="word-wrap:break-word;text-align:center">服药依从性</td>
              <td colspan="3"style="text-align:left" >
              <el-radio-group v-model="editForm.diabetescol">
                <el-radio label=规律 style="width: 40px;left: 5px">规律</el-radio>
                <el-radio label=间断 style="width:40px" >间断</el-radio>
                <el-radio label=不服药 style="width: 40px">不服药</el-radio>
              </el-radio-group>
              </td>
              <td style="word-wrap:break-word;text-align:center">药物不良反应</td>
              <td colspan="3" style="text-align:left" >
              <el-radio-group v-model="editForm.drugsAdversed">
                <el-radio label=无  style="width: 40px;left: 5px" >无</el-radio>
                <el-radio label=有  style="width: 40px" >有</el-radio>
              </el-radio-group>
              </td>
            </tr>

            <tr>
              <td style="word-wrap:break-word;text-align:center">低血糖反应</td>
              <td colspan="3" style="text-align:left">
              <el-radio-group v-model="editForm.hypoglycemia">
                <el-radio label=无 style="width: 40px;left: 5px" >无</el-radio>
                <el-radio label=偶尔  style="width: 40px">偶尔</el-radio>
                <el-radio label=频繁  style="width: 40px" >频繁</el-radio>
              </el-radio-group>
              </td>
              <td style="word-wrap:break-word;text-align:center">此次随访分类</td>
              <td colspan="3" style="text-align:left" >
              <el-radio-group v-model="editForm.followClassification">
                <el-radio label=控制满意  style="width: 70px;left: 5px">控制满意</el-radio>
                <el-radio label=控制不满意  style="width: 70px">控制不满意</el-radio>
                <el-radio label=不良反应   style="width: 70px">不良反应</el-radio>
                <el-radio label=并发症 style="width: 70px">并发症</el-radio>
              </el-radio-group>
              </td>
            </tr>

            <tr>
              <td style="word-wrap:break-word;text-align:center"> 用药情况</td>
              <td  style="height:50px;text-align:center">药物名称</td>
              <td colspan="3" class="pad10">
              <el-input v-model="editForm.drugName" auto-complete="off"></el-input>
              </td>
              <td  style="height:50px;text-align:center">用法用量</td>
              <td colspan="3"  class="pad10">
              <el-input  v-model="editForm.usageDosage" auto-complete="off" ></el-input>
              </td>
            </tr>
            <tr>
              <td style="height:50px;text-align:center">转诊</td>
              <td style="text-align:center">转诊原因</td>
              <td colspan="3" class="pad10">
              <el-input v-model="editForm.referralCause" auto-complete="off"></el-input>
              </td>
              <td style="text-align:center">转诊机构及科别</td>
              <td colspan="3" class="pad10">
              <el-input  v-model="editForm.referralInstitution" auto-complete="off" ></el-input>
              </td>
            </tr>
            <tr>
              <td style="word-wrap:break-word;text-align:center">打印数据存放</td>
              <td colspan="7" style="text-align:left" class="pad20">
                <el-radio-group v-model="editForm.followCount">
                  <table>
                    <tr>
                      <td style="padding-right: 5px"><el-radio label=第一列>第一列</el-radio></td>
                      <td style="padding-right: 5px"><el-radio label=第二列>第二列</el-radio></td>
                      <td style="padding-right: 5px"><el-radio label=第三列>第三列</el-radio></td>
                      <td style="padding-right: 5px"><el-radio label=第四列>第四列</el-radio></td>
                    </tr>
                  </table>
                </el-radio-group>
              </td>
            </tr>
            <tr>
              <td style="text-align:center">本次随访日期</td>
              <td colspan="3" class="pad10">
              <el-date-picker type="date"  v-model="editForm.followDate" style="width: 100%;"></el-date-picker>
              </td>
              <td style="text-align:center">本次随访方式</td>
              <td colspan="3"style="text-align:left" class="pad10">
              <el-radio-group v-model="editForm.followWay">
                <el-radio label=门诊 style="width: 40px">门诊</el-radio>
                <el-radio label=家庭 style="width: 40px">家庭</el-radio>
                <el-radio label=电话 style="width: 40px">电话</el-radio>
              </el-radio-group>
              </td>
            </tr>
            <tr>
              <td style="text-align:center">下次随访日期</td>
              <td colspan="3" class="pad10">
              <el-date-picker type="date"  v-model="editForm.nextFollowDate" style="width: 100%;"></el-date-picker>
              </td>
              <td style="text-align:center">随访医生签名</td>
              <td colspan="3" class="pad10">
              <el-input  v-model="editForm.doctorSign" auto-complete="off" ></el-input>
              </td>
            </tr>
            </tbody>
          </table>
        </el-tabs>

        </div>
        </EasyScrollbar>
        <!--</el-tab-pane>-->
        <!--</el-tabs>-->

      </el-form>
      <div slot="footer" class="dialog-footer">
        <!--<el-button @click.native="dialogFormVisible=false">取消</el-button>-->
        <!--<el-button v-if="dialogStatus=='create'" type="primary" @click="createData">新增</el-button>-->
        <!--<el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>-->
        <!--<el-button v-else type="primary" @click="conprint" v-if="edit">打印</el-button>-->
        <el-button @click="conprint()">打印</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="check">添加</el-button>
        <el-button v-else type="primary" v-if="edit" @click="createData">新增修改</el-button>
        <el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
      </div>
    </el-dialog>

  </section>
</template>

<script>
  import util from '@/utils/table.js'
  // import { getList as getPerson } from '@/api/Perinfor'
  import {
    getList,
    removeData,
    batchRemoveData,
    editData,
    addData,
    printData,
    checkData as getPerson
  } from '@/api/diabetes'
  import city from '@/api/city.js'
  import {
    getList as getSearchMemberPage
  } from '@/api/Perinfor'

  export default {
    data() {
      return {
        buttons: {
          KPD_C: false,
          KPD_D: false,
          KPD_E: false
        },
        edit: true,
        activeName: 'first',
        dialogStatus: '',
        citys: city.city(),
        tableHeight: window.innerHeight - 220,
        tablebodyHeight: window.innerHeight - 320,
        textMap: {
          update: '编辑',
          create: '新增',
          detail: '详情'
        },
        dialogFormVisible: false,
        filters: {
          name: '',
          age: '',
          startTime: '',
          endTime: '',
          startTime1: '',
          endTime1: '',
          peopleId: '',
          liveStatus: '',
          ethnic: [],
          doctor: '',
          sex: '',
          phone: '',
          area: [],
          creator: ''
        },
        users: [],
        printItem: [],
        printItem1: [],
        printItem2: [],
        printItem3: [],
        printItem4: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [{ required: true, message: ' ', trigger: 'blur' }],
          peopleId: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: ' ' }
          ]
        },
        // 编辑界面数据
        editForm: {
          id: null,
          name: '',
          sex: '',
          age: '',
          peopleId: '',
          phone: '',
          doctor: '',
          livestatus: '',
          followWay: '',
          followDate: '',
          followCount: '',
          symptoms: [],
          blood: '',
          weight: '',
          bodyMassIndex: '',
          footArteyPulse: '',
          other: '',
          dailySmoking: '',
          dailyDrinking: '',
          exercise: '',
          stapleFood: '',
          psychologicalAdjust: '',
          complianceBehavior: '',
          fastingBlood: '',
          hemoglobin: '',
          examinationDate: '',
          diabetescol: '',
          drugsAdversed: '',
          pressure: '',
          hypoglycemia: '',
          followClassification: '',
          usageDosage: '',
          drugName: '',
          nextFollowDate: '',
          referralInstitution: '',
          referralCause: '',
          startTime: '',
          endTime: '',
          startTime1: '',
          endTime1: '',
          doctorSign: '',
          modifier: null,
          modifyDate: null,
          creator: null,
          creatdate: null
        },
        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: ' ', trigger: 'blur' }]
        },
        symptoms: [
          { value: '无症状', label: '无症状' },
          { value: '多饮', label: '多饮' },
          { value: '多食', label: '多食' },
          { value: '多屎', label: '多屎' },
          { value: '视力模糊', label: '视力模糊' },
          { value: '感染', label: '感染' },
          { value: '手脚麻木', label: '手脚麻木' },
          { value: '下肢浮肿', label: '下肢浮肿' },
          { value: '体重明显下降', label: '体重明显下降' }],
        options: [
          {
            value: '触及正常',
            label: '触及正常'
          },
          {
            value: '减弱',
            label: '减弱',
            children: [
              {
                value: '双侧',
                label: '双侧'
              },
              {
                value: '左侧',
                label: '左侧'
              },
              {
                value: '右侧',
                label: '右侧'
              }
            ]
          },
          {
            value: '消失',
            label: '消失',
            children: [
              {
                value: '双侧',
                label: '双侧'
              },

              {
                value: '左侧',
                label: '左侧'
              },
              {
                value: '右侧',
                label: '右侧'
              }

            ]
          }
        ],
        footArteyPulse: []
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
        var str = style
        str = str + ' <div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
          '      <div id="topContent">\n' +
          '        <h2 style="width:794px;text-align:center">2型糖尿病患者随访服务记录表</h2>\n' +
          '        <span style="margin-left:50px;">姓   名：{{name}}</span>\n' +
          '      </div>\n' +
          '      <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '        <tbody>\n' +
          '        <tr>\n' +
          '          <td colspan="2"style="width: 20%; text-align:center">随访日期</td>\n' +
          '          <td style="width: 15%;text-align:center">{{followDate1}}</td>\n' +
          '          <td style="width: 15%;text-align:center">{{followDate2}}</td>\n' +
          '          <td style="width: 15%;text-align:center">{{followDate3}}</td>\n' +
          '          <td style="width: 15%;text-align:center">{{followDate4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2" style="word-wrap:break-word;text-align:center">随访方式</td>\n' +
          '          <td style="width: 15%;text-align:center"> {{followWay1}} </td>\n' +
          '          <td style="width: 15%;text-align:center"> {{followWay2}} </td>\n' +
          '          <td style="width: 15%;text-align:center"> {{followWay3}} </td>\n' +
          '          <td style="width: 15%;text-align:center"> {{followWay4}} </td>\n' +
          '         \n' +
          '        <tr>\n' +
          '          <td rowspan="4"style="word-wrap:break-word;width:80px;text-align:center"> 症状</td>\n' +
          '          <td rowspan="4"style="word-wrap:break-word;width:80px;text-align:left">\n' +
          '            1.症状<br>\n' +
          '            2.多食<br>\n' +
          '            3.多饮<br>\n' +
          '            4.多屎<br>\n' +
          '            5.视力模糊<br>\n' +
          '            6.感染<br>\n' +
          '            7.手脚麻木<br>\n' +
          '            8.下肢浮肿<br>\n' +
          '            9.体重明显下降<br>\n' +
          '          </td>\n' +
          '          <td rowspan="4"style="word-wrap:break-word;width:80px;text-align:center">{{symptoms1}}\n' +
          '          </td>\n' +
          '          <td rowspan="4"style="word-wrap:break-word;width:80px;text-align:center">{{symptoms2}}\n' +
          '\n' +
          '          </td>\n' +
          '          <td rowspan="4"style="word-wrap:break-word;width:80px;text-align:center">{{symptoms3}}\n' +
          '\n' +
          '          </td>\n' +
          '          <td rowspan="4"style="word-wrap:break-word;width:80px;text-align:center">{{symptoms4}}\n' +
          '\n' +
          '          </td>\n' +
          '        </tr>\n' +
          '        <tr></tr><tr></tr><tr></tr><tr></tr>\n' +
          '        <tr>\n' +
          '          <td rowspan="5"style="word-wrap:break-word;width:80px;text-align:center"> 体征</td>\n' +
          '          <td  style="width: 15%;text-align:center">血压(mmHg)</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{pressure1}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{pressure2}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{pressure3}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{pressure4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td  style="width: 15%;text-align:center">体重(Kg)</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{weight1}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{weight2}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{weight3}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{weight4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td  style="width: 15%;text-align:center">体质指数(Kg/m²)</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{bodyMassIndex1}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{bodyMassIndex2}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{bodyMassIndex3}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{bodyMassIndex4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td  style="width: 15%;text-align:center">足背动脉搏动</td>\n' +
          '        \t\t  <td  style="width: 15%;text-align:center">{{footArteyPulse1}}</td>\n' +
          '                  <td  style="width: 15%;text-align:center">{{footArteyPulse2}}</td>\n' +
          '                  <td  style="width: 15%;text-align:center">{{footArteyPulse3}}</td>\n' +
          '                  <td  style="width: 15%;text-align:center">{{footArteyPulse4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td  style="width: 15%;text-align:center">其他</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{other1}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{other2}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{other3}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{other4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td rowspan="6"style="word-wrap:break-word;width:80px;text-align:center"> 生活方式指导</td>\n' +
          '          <td style="text-align:center">日吸烟量</td>\n' +
          '          <td style="text-align:center">{{dailySmoking1}}/支</td>\n' +
          '          <td style="text-align:center">{{dailySmoking2}}/支</td>\n' +
          '          <td style="text-align:center">{{dailySmoking3}}/支</td>\n' +
          '          <td style="text-align:center">{{dailySmoking4}}/支</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td style="text-align:center">日饮酒量</td>\n' +
          '          <td style="text-align:center">{{dailyDrinking1}}/两</td>\n' +
          '          <td style="text-align:center">{{dailyDrinking2}}/两</td>\n' +
          '          <td style="text-align:center">{{dailyDrinking3}}/两</td>\n' +
          '          <td style="text-align:center">{{dailyDrinking4}}/两</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td  style="text-align:center">运动</td>\n' +
          '          <td style="text-align:center">{{exercise1}}次/周</td>\n' +
          '          <td style="text-align:center">{{exercise2}}次/周</td>\n' +
          '          <td style="text-align:center">{{exercise3}}次/周</td>\n' +
          '          <td style="text-align:center">{{exercise4}}次/周</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td style="text-align:center">主食(克/天)</td>\n' +
          '          <td style="text-align:center">{{stapleFood1}}</td>\n' +
          '          <td style="text-align:center">{{stapleFood2}}</td>\n' +
          '          <td style="text-align:center">{{stapleFood3}}</td>\n' +
          '          <td style="text-align:center">{{stapleFood4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td style="height:50px;text-align:center">心理调整</td>\n' +
          '           <td style="text-align:center">{{psychologicalAdjust1}}</td>\n' +
          '           <td style="text-align:center">{{psychologicalAdjust2}}</td>\n' +
          '           <td style="text-align:center">{{psychologicalAdjust3}}</td>\n' +
          '           <td style="text-align:center">{{psychologicalAdjust4}}</td>\n' +
          '          \n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td style="height:50px;text-align:center">遵医行为</td>\n' +
          '  \t\t\t <td style="text-align:center">{{complianceBehavior1}}</td>\n' +
          '             <td style="text-align:center">{{complianceBehavior2}}</td>\n' +
          '             <td style="text-align:center">{{complianceBehavior3}}</td>\n' +
          '             <td style="text-align:center">{{complianceBehavior4}}</td>\n' +
          '        </tr>\n' +
          '\n' +
          '        <tr>\n' +
          '          <td rowspan="2"style="word-wrap:break-word;width:80px;text-align:center"> 辅助检查</td>\n' +
          '          <td  style="height:50px;text-align:center">空腹血糖值</td>\n' +
          '          <td style="text-align:center">{{fastingBlood1}}mol/L</td>\n' +
          '          <td style="text-align:center">{{fastingBlood2}}mol/L</td>\n' +
          '          <td style="text-align:center">{{fastingBlood3}}mol/L</td>\n' +
          '          <td style="text-align:center">{{fastingBlood4}}mol/L</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td  style="height:50px;text-align:center">其他检查</td>\n' +
          '          <td style="text-align:center">糖化血红蛋白:{{hemoglobin1}}%<br>检查日期：{{examinationDate1}}</td>\n' +
          '          <td style="text-align:center">糖化血红蛋白:{{hemoglobin2}}%<br>检查日期：{{examinationDate2}}</td>\n' +
          '          <td style="text-align:center">糖化血红蛋白:{{hemoglobin3}}%<br>检查日期：{{examinationDate3}}</td>\n' +
          '          <td style="text-align:center">糖化血红蛋白:{{hemoglobin4}}%<br>检查日期：{{examinationDate4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2"  style="word-wrap:break-word;width:80px;text-align:center"> 服药依从性</td>\n' +
          '           \t <td style="text-align:center">{{diabetescol1}}</td>\n' +
          '             <td style="text-align:center">{{diabetescol2}}</td>\n' +
          '             <td style="text-align:center">{{diabetescol3}}</td>\n' +
          '             <td style="text-align:center">{{diabetescol4}}</td>\n' +
          '\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2"  style="word-wrap:break-word;width:80px;text-align:center"> 药物不良反应</td>\n' +
          '           \t <td style="text-align:center">{{drugsAdversed1}}</td>\n' +
          '             <td style="text-align:center">{{drugsAdversed2}}</td>\n' +
          '             <td style="text-align:center">{{drugsAdversed3}}</td>\n' +
          '             <td style="text-align:center">{{drugsAdversed4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2"  style="word-wrap:break-word;width:80px;text-align:center"> 低血糖反应</td>\n' +
          '          \t <td style="text-align:center">{{hypoglycemia1}}</td>\n' +
          '             <td style="text-align:center">{{hypoglycemia2}}</td>\n' +
          '             <td style="text-align:center">{{hypoglycemia3}}</td>\n' +
          '             <td style="text-align:center">{{hypoglycemia4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2"  style="word-wrap:break-word;width:80px;text-align:center"> 此次随访分类</td>\n' +
          '             <td style="text-align:center">{{followClassification1}}</td>\n' +
          '             <td style="text-align:center">{{followClassification2}}</td>\n' +
          '             <td style="text-align:center">{{followClassification3}}</td>\n' +
          '             <td style="text-align:center">{{followClassification4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td rowspan="2"style="word-wrap:break-word;width:80px;text-align:center"> 用药情况</td>\n' +
          '          <td  style="height:50px;text-align:center">药物名称</td>\n' +
          '  \t\t\t <td style="text-align:center">{{drugName1}}</td>\n' +
          '             <td style="text-align:center">{{drugName2}}</td>\n' +
          '             <td style="text-align:center">{{drugName3}}</td>\n' +
          '             <td style="text-align:center">{{drugName4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td style="height:50px;text-align:center">用法用量</td>\n' +
          '  \t\t\t <td style="text-align:center">{{usageDosage1}}</td>\n' +
          '             <td style="text-align:center">{{usageDosage2}}</td>\n' +
          '             <td style="text-align:center">{{usageDosage3}}</td>\n' +
          '             <td style="text-align:center">{{usageDosage4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td rowspan="2" style="height:50px;text-align:center">转诊</td>\n' +
          '          \t <td style="text-align:left">转诊原因</td>\n' +
          '           \t <td style="text-align:center">{{referralCause1}}</td>\n' +
          '             <td style="text-align:center">{{referralCause2}}</td>\n' +
          '             <td style="text-align:center">{{referralCause3}}</td>\n' +
          '             <td style="text-align:center">{{referralCause4}}</td>\n' +
          '          \n' +
          '        </tr>\n' +
          '         <tr>\n' +
          '          \t <td style="text-align:left">转诊机构及科别</td>\n' +
          '           \t <td style="text-align:center">{{referralInstitution1}}</td>\n' +
          '             <td style="text-align:center">{{referralInstitution2}}</td>\n' +
          '             <td style="text-align:center">{{referralInstitution3}}</td>\n' +
          '             <td style="text-align:center">{{referralInstitution4}}</td>\n' +
          '          \n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2" style="text-align:center">下次随访日期</td>\n' +
          '          <td style="text-align:center">{{nextFollowDate1}}</td>\n' +
          '          <td style="text-align:center">{{nextFollowDate2}}</td>\n' +
          '          <td style="text-align:center">{{nextFollowDate3}}</td>\n' +
          '          <td style="text-align:center">{{nextFollowDate4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2" style="text-align:center">随访医生签名</td>\n' +
          '          <td style="text-align:center">{{doctorSign1}}</td>\n' +
          '          <td style="text-align:center">{{doctorSign2}}</td>\n' +
          '          <td style="text-align:center">{{doctorSign3}}</td>\n' +
          '          <td style="text-align:center">{{doctorSign4}}</td>\n' +
          '        </tr>\n' +
          '        </tbody>\n' +
          '      </table>\n' +
          '    </div>'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{followCount1}}/, this.printItem1.followCount ? this.printItem1.followCount : '')
        str = str.replace(/{{followCount2}}/, this.printItem2.followCount ? this.printItem2.followCount : '')
        str = str.replace(/{{followCount3}}/, this.printItem3.followCount ? this.printItem3.followCount : '')
        str = str.replace(/{{followCount4}}/, this.printItem4.followCount ? this.printItem4.followCount : '')

        str = str.replace(/{{followWay1}}/, this.printItem1.followWay ? this.printItem1.followWay : '')
        str = str.replace(/{{followWay2}}/, this.printItem2.followWay ? this.printItem2.followWay : '')
        str = str.replace(/{{followWay3}}/, this.printItem3.followWay ? this.printItem3.followWay : '')
        str = str.replace(/{{followWay4}}/, this.printItem4.followWay ? this.printItem4.followWay : '')

        str = str.replace(/{{symptoms1}}/, this.printItem1.symptoms ? this.printItem1.symptoms : '')
        str = str.replace(/{{symptoms2}}/, this.printItem2.symptoms ? this.printItem2.symptoms : '')
        str = str.replace(/{{symptoms3}}/, this.printItem3.symptoms ? this.printItem3.symptoms : '')
        str = str.replace(/{{symptoms4}}/, this.printItem4.symptoms ? this.printItem4.symptoms : '')

        str = str.replace(/{{pressure1}}/, this.printItem1.pressure ? this.printItem1.pressure : '')
        str = str.replace(/{{pressure2}}/, this.printItem2.pressure ? this.printItem2.pressure : '')
        str = str.replace(/{{pressure3}}/, this.printItem3.pressure ? this.printItem3.pressure : '')
        str = str.replace(/{{pressure4}}/, this.printItem4.pressure ? this.printItem4.pressure : '')

        str = str.replace(/{{weight1}}/, this.printItem1.weight ? this.printItem1.weight : '')
        str = str.replace(/{{weight2}}/, this.printItem2.weight ? this.printItem2.weight : '')
        str = str.replace(/{{weight3}}/, this.printItem3.weight ? this.printItem3.weight : '')
        str = str.replace(/{{weight4}}/, this.printItem4.weight ? this.printItem4.weight : '')

        str = str.replace(/{{bodyMassIndex1}}/, this.printItem1.bodyMassIndex ? this.printItem1.bodyMassIndex : '')
        str = str.replace(/{{bodyMassIndex2}}/, this.printItem2.bodyMassIndex ? this.printItem2.bodyMassIndex : '')
        str = str.replace(/{{bodyMassIndex3}}/, this.printItem3.bodyMassIndex ? this.printItem3.bodyMassIndex : '')
        str = str.replace(/{{bodyMassIndex4}}/, this.printItem4.bodyMassIndex ? this.printItem4.bodyMassIndex : '')

        str = str.replace(/{{footArteyPulse1}}/, this.printItem1.footArteyPulse ? this.printItem1.footArteyPulse : '')
        str = str.replace(/{{footArteyPulse2}}/, this.printItem2.footArteyPulse ? this.printItem2.footArteyPulse : '')
        str = str.replace(/{{footArteyPulse3}}/, this.printItem3.footArteyPulse ? this.printItem3.footArteyPulse : '')
        str = str.replace(/{{footArteyPulse4}}/, this.printItem4.footArteyPulse ? this.printItem4.footArteyPulse : '')

        str = str.replace(/{{other1}}/, this.printItem1.other ? this.printItem1.other : '')
        str = str.replace(/{{other2}}/, this.printItem2.other ? this.printItem2.other : '')
        str = str.replace(/{{other3}}/, this.printItem3.other ? this.printItem3.other : '')
        str = str.replace(/{{other4}}/, this.printItem4.other ? this.printItem4.other : '')

        str = str.replace(/{{dailySmoking1}}/, this.printItem1.dailySmoking ? this.printItem1.dailySmoking : '')
        str = str.replace(/{{dailySmoking2}}/, this.printItem2.dailySmoking ? this.printItem2.dailySmoking : '')
        str = str.replace(/{{dailySmoking3}}/, this.printItem3.dailySmoking ? this.printItem3.dailySmoking : '')
        str = str.replace(/{{dailySmoking4}}/, this.printItem4.dailySmoking ? this.printItem4.dailySmoking : '')

        str = str.replace(/{{dailyDrinking1}}/, this.printItem1.dailyDrinking ? this.printItem1.dailyDrinking : '')
        str = str.replace(/{{dailyDrinking2}}/, this.printItem2.dailyDrinking ? this.printItem2.dailyDrinking : '')
        str = str.replace(/{{dailyDrinking3}}/, this.printItem3.dailyDrinking ? this.printItem3.dailyDrinking : '')
        str = str.replace(/{{dailyDrinking4}}/, this.printItem4.dailyDrinking ? this.printItem4.dailyDrinking : '')

        str = str.replace(/{{exercise1}}/, this.printItem1.exercise ? this.printItem1.exercise : '')
        str = str.replace(/{{exercise2}}/, this.printItem2.exercise ? this.printItem2.exercise : '')
        str = str.replace(/{{exercise3}}/, this.printItem3.exercise ? this.printItem3.exercise : '')
        str = str.replace(/{{exercise4}}/, this.printItem4.exercise ? this.printItem4.exercise : '')

        str = str.replace(/{{stapleFood1}}/, this.printItem1.stapleFood ? this.printItem1.stapleFood : '')
        str = str.replace(/{{stapleFood2}}/, this.printItem2.stapleFood ? this.printItem2.stapleFood : '')
        str = str.replace(/{{stapleFood3}}/, this.printItem3.stapleFood ? this.printItem3.stapleFood : '')
        str = str.replace(/{{stapleFood4}}/, this.printItem4.stapleFood ? this.printItem4.stapleFood : '')

        str = str.replace(/{{psychologicalAdjust1}}/, this.printItem1.psychologicalAdjust ? this.printItem1.psychologicalAdjust : '')
        str = str.replace(/{{psychologicalAdjust2}}/, this.printItem2.psychologicalAdjust ? this.printItem2.psychologicalAdjust : '')
        str = str.replace(/{{psychologicalAdjust3}}/, this.printItem3.psychologicalAdjust ? this.printItem3.psychologicalAdjust : '')
        str = str.replace(/{{psychologicalAdjust4}}/, this.printItem4.psychologicalAdjust ? this.printItem4.psychologicalAdjust : '')

        str = str.replace(/{{complianceBehavior1}}/, this.printItem1.complianceBehavior ? this.printItem1.complianceBehavior : '')
        str = str.replace(/{{complianceBehavior2}}/, this.printItem2.complianceBehavior ? this.printItem2.complianceBehavior : '')
        str = str.replace(/{{complianceBehavior3}}/, this.printItem3.complianceBehavior ? this.printItem3.complianceBehavior : '')
        str = str.replace(/{{complianceBehavior4}}/, this.printItem4.complianceBehavior ? this.printItem4.complianceBehavior : '')

        str = str.replace(/{{footArteyPulse1}}/, this.printItem1.footArteyPulse ? this.printItem1.footArteyPulse : '')
        str = str.replace(/{{footArteyPulse2}}/, this.printItem2.footArteyPulse ? this.printItem2.footArteyPulse : '')
        str = str.replace(/{{footArteyPulse3}}/, this.printItem3.footArteyPulse ? this.printItem3.footArteyPulse : '')
        str = str.replace(/{{footArteyPulse4}}/, this.printItem4.footArteyPulse ? this.printItem4.footArteyPulse : '')

        str = str.replace(/{{fastingBlood1}}/, this.printItem1.fastingBlood ? this.printItem1.fastingBlood : '')
        str = str.replace(/{{fastingBlood2}}/, this.printItem2.fastingBlood ? this.printItem2.fastingBlood : '')
        str = str.replace(/{{fastingBlood3}}/, this.printItem3.fastingBlood ? this.printItem3.fastingBlood : '')
        str = str.replace(/{{fastingBlood4}}/, this.printItem4.fastingBlood ? this.printItem4.fastingBlood : '')

        str = str.replace(/{{hemoglobin1}}/, this.printItem1.hemoglobin ? this.printItem1.hemoglobin : '')
        str = str.replace(/{{hemoglobin2}}/, this.printItem2.hemoglobin ? this.printItem2.hemoglobin : '')
        str = str.replace(/{{hemoglobin3}}/, this.printItem3.hemoglobin ? this.printItem3.hemoglobin : '')
        str = str.replace(/{{hemoglobin4}}/, this.printItem4.hemoglobin ? this.printItem4.hemoglobin : '')

        str = str.replace(/{{examinationDate1}}/, this.printItem1.examinationDate ? this.printItem1.examinationDate : '')
        str = str.replace(/{{examinationDate2}}/, this.printItem2.examinationDate ? this.printItem2.examinationDate : '')
        str = str.replace(/{{examinationDate3}}/, this.printItem3.examinationDate ? this.printItem3.examinationDate : '')
        str = str.replace(/{{examinationDate4}}/, this.printItem4.examinationDate ? this.printItem4.examinationDate : '')

        str = str.replace(/{{diabetescol1}}/, this.printItem1.diabetescol ? this.printItem1.diabetescol : '')
        str = str.replace(/{{diabetescol2}}/, this.printItem2.diabetescol ? this.printItem2.diabetescol : '')
        str = str.replace(/{{diabetescol3}}/, this.printItem3.diabetescol ? this.printItem3.diabetescol : '')
        str = str.replace(/{{diabetescol4}}/, this.printItem4.diabetescol ? this.printItem4.diabetescol : '')

        str = str.replace(/{{drugsAdversed1}}/, this.printItem1.drugsAdversed ? this.printItem1.drugsAdversed : '')
        str = str.replace(/{{drugsAdversed2}}/, this.printItem2.drugsAdversed ? this.printItem2.drugsAdversed : '')
        str = str.replace(/{{drugsAdversed3}}/, this.printItem3.drugsAdversed ? this.printItem3.drugsAdversed : '')
        str = str.replace(/{{drugsAdversed4}}/, this.printItem4.drugsAdversed ? this.printItem4.drugsAdversed : '')

        str = str.replace(/{{hypoglycemia1}}/, this.printItem1.hypoglycemia ? this.printItem1.hypoglycemia : '')
        str = str.replace(/{{hypoglycemia2}}/, this.printItem2.hypoglycemia ? this.printItem2.hypoglycemia : '')
        str = str.replace(/{{hypoglycemia3}}/, this.printItem3.hypoglycemia ? this.printItem3.hypoglycemia : '')
        str = str.replace(/{{hypoglycemia4}}/, this.printItem4.hypoglycemia ? this.printItem4.hypoglycemia : '')

        str = str.replace(/{{followDate1}}/, this.printItem1.followDate ? this.printItem1.followDate : '')
        str = str.replace(/{{followDate2}}/, this.printItem2.followDate ? this.printItem2.followDate : '')
        str = str.replace(/{{followDate3}}/, this.printItem3.followDate ? this.printItem3.followDate : '')
        str = str.replace(/{{followDate4}}/, this.printItem4.followDate ? this.printItem4.followDate : '')

        str = str.replace(/{{followClassification1}}/, this.printItem1.followClassification ? this.printItem1.followClassification : '')
        str = str.replace(/{{followClassification2}}/, this.printItem2.followClassification ? this.printItem2.followClassification : '')
        str = str.replace(/{{followClassification3}}/, this.printItem3.followClassification ? this.printItem3.followClassification : '')
        str = str.replace(/{{followClassification4}}/, this.printItem4.followClassification ? this.printItem4.followClassification : '')

        str = str.replace(/{{drugName1}}/, this.printItem1.drugName ? this.printItem1.drugName : '')
        str = str.replace(/{{drugName2}}/, this.printItem2.drugName ? this.printItem2.drugName : '')
        str = str.replace(/{{drugName3}}/, this.printItem3.drugName ? this.printItem3.drugName : '')
        str = str.replace(/{{drugName4}}/, this.printItem4.drugName ? this.printItem4.drugName : '')

        str = str.replace(/{{usageDosage1}}/, this.printItem1.usageDosage ? this.printItem1.usageDosage : '')
        str = str.replace(/{{usageDosage2}}/, this.printItem2.usageDosage ? this.printItem2.usageDosage : '')
        str = str.replace(/{{usageDosage3}}/, this.printItem3.usageDosage ? this.printItem3.usageDosage : '')
        str = str.replace(/{{usageDosage4}}/, this.printItem4.usageDosage ? this.printItem4.usageDosage : '')

        str = str.replace(/{{referralCause1}}/, this.printItem1.referralCause ? this.printItem1.referralCause : '')
        str = str.replace(/{{referralCause2}}/, this.printItem2.referralCause ? this.printItem2.referralCause : '')
        str = str.replace(/{{referralCause3}}/, this.printItem3.referralCause ? this.printItem3.referralCause : '')
        str = str.replace(/{{referralCause4}}/, this.printItem4.referralCause ? this.printItem4.referralCause : '')

        str = str.replace(/{{referralInstitution1}}/, this.printItem1.referralInstitution ? this.printItem1.referralInstitution : '')
        str = str.replace(/{{referralInstitution2}}/, this.printItem2.referralInstitution ? this.printItem2.referralInstitution : '')
        str = str.replace(/{{referralInstitution3}}/, this.printItem3.referralInstitution ? this.printItem3.referralInstitution : '')
        str = str.replace(/{{referralInstitution4}}/, this.printItem4.referralInstitution ? this.printItem4.referralInstitution : '')

        str = str.replace(/{{nextFollowDate1}}/, this.printItem1.nextFollowDate ? this.printItem1.nextFollowDate : '')
        str = str.replace(/{{nextFollowDate2}}/, this.printItem2.nextFollowDate ? this.printItem2.nextFollowDate : '')
        str = str.replace(/{{nextFollowDate3}}/, this.printItem3.nextFollowDate ? this.printItem3.nextFollowDate : '')
        str = str.replace(/{{nextFollowDate4}}/, this.printItem4.nextFollowDate ? this.printItem4.nextFollowDate : '')

        str = str.replace(/{{doctorSign1}}/, this.printItem1.doctorSign ? this.printItem1.doctorSign : '')
        str = str.replace(/{{doctorSign2}}/, this.printItem2.doctorSign ? this.printItem2.doctorSign : '')
        str = str.replace(/{{doctorSign3}}/, this.printItem3.doctorSign ? this.printItem3.doctorSign : '')
        str = str.replace(/{{doctorSign4}}/, this.printItem4.doctorSign ? this.printItem4.doctorSign : '')

        str = str.replace(/{{followDate1}}/, this.printItem1.followDate ? this.printItem1.followDate : '')
        str = str.replace(/{{followDate2}}/, this.printItem2.followDate ? this.printItem2.followDate : '')
        str = str.replace(/{{followDate3}}/, this.printItem3.followDate ? this.printItem3.followDate : '')
        str = str.replace(/{{followDate4}}/, this.printItem4.followDate ? this.printItem4.followDate : '')
        myWindow.document.write(str)
        myWindow.focus()
        myWindow.document.close()
        myWindow.print()
        return myWindow
      },
      conprint() {
        const para = {
          page: this.page,
          size: 20,
          filters: { peopleId: this.editForm.peopleId }
        }
        printData(para).then(res => {
          this.printItem = res.detailModelList
          this.printItem1 = this.printItem[0]
          this.printItem2 = this.printItem[1]
          this.printItem3 = this.printItem[2]
          this.printItem4 = this.printItem[3]
          const windows = this.printf(document.getElementById('print').innerHTML)
          windows.close()
        })
      },
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      printUsers() {
        const para = {
          page: this.page,
          size: 20,
          filters: { peopleId: this.editForm.peopleId }
        }
        printData(para).then(res => {
          this.printItem = res.detailModelList
          this.printItem1 = this.printItem[0]
          this.printItem2 = this.printItem[1]
          this.printItem3 = this.printItem[2]
          this.printItem4 = this.printItem[3]
        })
      },
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
                this.$message.error('请先建立个人档案或者不是糖尿病患者')
              }
            })
          } else {
            this.$message.error('请先建立个人档案或者不是糖尿病患者')
          }
        })
      },
      city: function() {
        return this.citys
      },
      goto(index, row) {
        this.$router.push({
          name: '首页',
          params: {
            hid: row.peopleId
          }
        })
      },
      inputFunc1() {
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
                  this.active = 2
                }
              })
            })
          }, 200)
        }
      },
      // 获取用户列表
      getUsers() {
        this.filters.examinationDate =
          !this.filters.examinationDate || this.filters.examinationDate === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.examinationDate), 'yyyy-MM-dd')
        this.filters.nextFollowDate =
          !this.filters.nextFollowDate || this.filters.nextFollowDate === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.nextFollowDate), 'yyyy-MM-dd')
        this.filters.followDate =
          !this.filters.followDate || this.filters.followDate === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.followDate), 'yyyy-MM-dd')
        this.filters.endTime =
          !this.filters.endTime || this.filters.endTime === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.endTime), 'yyyy-MM-dd')
        this.filters.startTime =
          !this.filters.startTime || this.filters.startTime === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.startTime), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          size: 20,
          filters: {
            name: this.filters.name, sex: this.filters.sex, liveStatus: this.filters.liveStatus, creator: this.filters.creator,
            doctor: this.filters.doctor, peopleId: this.filters.peopleId, age: this.filters.age, phone: this.filters.phone,
            startTime: this.filters.startTime, endTime: this.filters.endTime, area: '', ethnic: '' }
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
          // this.total = res.data.total
          // this.users = res.data.hhh
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
        //   startTime1: '',
        //   endTime1: '',
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
          ethnic: '',
          sex: '',
          phone: '',
          area: [],
          creator: ''
        }
        this.area = []
        this.page = 1
        this.getUsers()
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
        this.dialogStatus = 'detai'
        this.dialogFormVisible = 'true'
        this.editForm = Object.assign({}, row)
        this.footArteyPulse = this.editForm.footArteyPulse.split('/')
        this.editForm.symptoms = this.editForm.symptoms.split('/')
        if (this.editForm.symptoms == null) this.editForm.symptoms = this.editForm.symptoms.split('/')
        this.edit = false
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm.symptoms = []
        this.editForm = Object.assign({}, row)
        this.footArteyPulse = this.editForm.footArteyPulse.split('/')
        this.editForm.symptoms = this.editForm.symptoms.split('/')
        if (this.editForm.symptoms == null) this.editForm.symptoms = this.editForm.symptoms.split('/')
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
          phone: '',
          doctor: '',
          peopleId: '',
          followWay: '',
          followDate: '',
          Count: '',
          followCount: '',
          symptoms: [],
          blood: '',
          weight: '',
          bodyMassIndex: '',
          footArteyPulse: '',
          other: '',
          dailySmoking: '',
          dailyDrinking: '',
          exercise: '',
          stapleFood: '',
          psychologicalAdjust: '',
          complianceBehavior: '',
          fastingBlood: '',
          hemoglobin: '',
          examinationDate: '',
          diabetescol: '',
          drugsAdversed: '',
          pressure: '',
          hypoglycemia: '',
          followClassification: '',
          usageDosage: '',
          drugName: '',
          nextFollowDate: '',
          referralInstitution: '',
          referralCause: '',
          doctorSign: '',
          creator: this.$store.getters.name
        }
        this.footArteyPulse = []
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.modifier = this.$store.getters.name
                para.examinationDate =
                  !para.examinationDate || para.examinationDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.examinationDate), 'yyyy-MM-dd')
                para.nextFollowDate =
                  !para.nextFollowDate || para.nextFollowDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.nextFollowDate), 'yyyy-MM-dd')
                para.followDate =
                  !para.followDate || para.followDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.followDate), 'yyyy-MM-dd')
                para.footArteyPulse = ''
                for (const itm of this.footArteyPulse) {
                  para.footArteyPulse += itm
                  para.footArteyPulse += '/'
                }
                para.footArteyPulse = para.footArteyPulse.substr(0, para.footArteyPulse.length - 1)
                para.symptoms = ''
                for (const itm of this.editForm.symptoms) {
                  if (itm !== '') {
                    para.symptoms += itm
                    para.symptoms += '/'
                  }
                }
                para.symptoms = para.symptoms.substr(0, para.symptoms.length - 1)
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
      // 新增
      createData: function() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.examinationDate =
                  !para.examinationDate || para.examinationDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.examinationDate), 'yyyy-MM-dd')
                para.nextFollowDate =
                  !para.nextFollowDate || para.nextFollowDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.nextFollowDate), 'yyyy-MM-dd')
                para.followDate =
                  !para.followDate || para.followDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.followDate), 'yyyy-MM-dd')
                para.footArteyPulse = ''
                for (const itm of this.footArteyPulse) {
                  para.footArteyPulse += itm
                  para.footArteyPulse += '/'
                }
                para.footArteyPulse = para.footArteyPulse.substr(0, para.footArteyPulse.length - 1)
                para.symptoms = ''
                for (const itm of this.editForm.symptoms) {
                  para.symptoms += itm
                  para.symptoms += '/'
                }
                para.symptoms = para.symptoms.substr(0, para.symptoms.length - 1)
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
