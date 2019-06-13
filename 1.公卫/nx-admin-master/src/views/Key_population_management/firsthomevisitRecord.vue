<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="22" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.peopleId" placeholder="身份证查询"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers"style="background-color:#4db2ee;border-color:transparent; height:40px" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button v-if="buttons.KPFR_C"  type="primary" @click="handleAdd"icon="el-icon-circle-plus-outline "  style="height:40px">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="users":height="tableHeight"  highlight-current-row @selection-change="selsChange" border style="width: 100%;"  @row-dblclick="handleRowChange">
      <el-table-column type="index" label="序号" width="80">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="id" label="编号" width="120">
      </el-table-column>
      <el-table-column prop="followTime" label="随访时间" width="120">
      </el-table-column>
      <el-table-column prop="nextFollowtie" label="下次随访时间" min-width="120">
      </el-table-column>
      <el-table-column prop="evaluationDoctorsignature" label="评估医生" min-width="120">
      </el-table-column>

      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <a size="14px" @click="goto(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">查看档案</a>
          <a size="14px"  v-if="buttons.KPFR_E" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" v-if="buttons.KPFR_D" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;" >
      </el-pagination>
    </el-col>

    <!--编辑界面-->

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" :before-close="handleClose" center>
      <EasyScrollbar>
        <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
          <div id="print">
            <el-form :model="editForm" label-width="110px" :rules="editFormRules" ref="editForm">
              <div id="dataPanel">
                <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:30px;border-collapse:collapse;">
                  <tbody>
                  <tr v-if="dialogStatus==='detail'">
                    <td style="word-wrap:break-word;text-align:center;">
                      创建人
                    </td>
                    <td  style="text-align:center" class="pad10">
                      <el-form-item label="" label-width="0px">
                        <el-input v-model="editForm.creator" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                      </el-form-item>
                    </td>
                    <td style="word-wrap:break-word;text-align:center;">
                      创建时间
                    </td>
                    <td colspan="2" style="text-align:center" class="pad10">
                      <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr v-if="dialogStatus==='detail'">
                    <td style="word-wrap:break-word;text-align:center;">
                      修改人
                    </td>
                    <td  style="text-align:center" class="pad10">
                      <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                        <el-input v-model="editForm.modifier" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                      </el-form-item>
                    </td>
                    <td style="word-wrap:break-word;text-align:center;">
                      修改时间
                    </td>
                    <td colspan="2" style="text-align:center" class="pad10">
                      <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td style="width:140px;word-wrap:break-word;text-align:center;">
                      <span>*</span>身份证号
                    </td>
                    <td style="text-align:center; padding-right: 0px" class="pad10">
                      <el-form-item label="" label-width="0px" prop="peopleId">
                        <el-input v-model="editForm.peopleId" auto-complete="off" v-on:input="inputFunc1" placeholder="请输入身份证号" id="peopleId"  ></el-input>
                      </el-form-item>
                    </td>
                    <td style="width:140px;word-wrap:break-word;text-align:center;"><span>*</span>区域</td>
                    <td colspan="2" style="text-align:center; padding-right: 0px" class="pad10">
                      <el-form-item label="" label-width="0px" >
                        <el-input v-model="editForm.area" auto-complete="off"   disabled  ></el-input>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td style=" width:140px;word-wrap:break-word;text-align:center">
                      <span>*</span>姓名
                    </td>
                    <td style="text-align:center; padding-right: 0px " class="pad10">
                      <el-form-item label=""label-width="0px" >
                        <el-input v-model="editForm.name" auto-complete="off" disabled></el-input>
                      </el-form-item>
                    </td>
                    <td style="width:80px;word-wrap:break-word;text-align:center;">责任医生</td>
                    <td colspan="2" style="text-align:center; padding-right: 0px" class="pad10">
                      <el-form-item label="" label-width="0px" >
                        <el-input v-model="editForm.responDoctor" auto-complete="off"></el-input>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td style=" width:140px;word-wrap:break-word;text-align:center;">
                      <span>*</span>规则服药
                    </td>
                    <td style="text-align:left;" class="pad20" >
                      <el-form-item label=""label-width="0px" prop="ruleMedicine">
                        <el-radio-group v-model="editForm.ruleMedicine"  id="ruleMedicine" >
                          <el-radio label=是>是</el-radio>
                          <el-radio label=否>否</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                    <td style="width:80px;word-wrap:break-word;text-align:center;"><span>*</span>生存状态</td>
                    <td colspan="2" style="text-align:left;" class="pad20" >
                      <el-form-item label="" label-width="0px"  prop="lifeState" >
                        <el-radio-group v-model="editForm.lifeState" id="lifeState">
                          <el-radio label=存活>治疗中</el-radio>
                          <el-radio label=完成治疗>完成治疗</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td style=" width:140px;text-align:center">随访时间</td>
                    <td colspan='4'class="pad10" style="padding-right: 0px" >
                      <el-form-item label=""label-width="0px"   >
                        <el-date-picker type="date" placeholder="请输入随访时间" v-model="editForm.followTime" id="followTime" style="width: 100%;"></el-date-picker>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td style="width:140px;word-wrap:break-word;text-align:center">随访方式</td>
                    <td colspan='1'style="text-align:left;" class="pad20" >
                      <el-form-item label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.followMethod">
                          <el-radio label=门诊>门诊</el-radio>
                          <el-radio label=家庭>家庭</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                    <td style="width: 19%;text-align:center">患者类型</td>
                    <td colspan='2' style="text-align:left;" class="pad20" >
                      <el-form-item label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.patienType">
                          <el-radio label=初治>初治</el-radio>
                          <el-radio label=复治>复治</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td style="width:140px;word-wrap:break-word;text-align:center">痰菌情况</td>
                    <td colspan='1' style="text-align:left;" class="pad10" >
                      <el-form-item label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.sputumStatus">
                        <table>
                          <tr>
                            <table>
                              <tr>
                                <td style="padding-right: 5px"><td style="padding-right: 15px"><el-radio label=阳性>阳性</el-radio></td>
                                <td style="padding-right: 5px"><td style="padding-right: 15px"><el-radio label=阴性>阴性</el-radio></td>
                                <td style="padding-right: 5px"><td style="padding-right: 15px"><el-radio label=未查痰>未查痰</el-radio></td>
                              </tr>
                            </table>
                          </tr>
                        </table>
                      </el-radio-group>
                    </el-form-item>
                    </td>
                    <td style="text-align:center;">耐药情况</td>
                    <td colspan='2' style="text-align:left;" class="pad20" >
                      <el-form-item label=""label-width="0px"  >
                      <el-radio-group v-model="editForm.drugStatus">
                        <el-radio label=耐药>耐药</el-radio>
                        <el-radio label=非耐药>非耐药</el-radio>
                        <el-radio clabel=未检测>未检测</el-radio>
                      </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td rowspan='3' style="width:140px;word-wrap:break-word;text-align:center">症状及体征</td>
                    <td colspan='4' style="text-align:left;" class="pad20" >
                      <el-form-item label=""label-width="0px"  >
                        <el-checkbox-group v-model="editForm.symptomsSigns">
                          <table>
                            <tr>
                              <td style="padding-right: 15px"><el-checkbox label="没有症状" name="type"></el-checkbox></td>
                              <td style="padding-right: 15px"><el-checkbox label="咳嗽咳痰" name="type"></el-checkbox></td>
                              <td style="padding-right: 15px"><el-checkbox label="低热盗汗" name="type"></el-checkbox></td>
                              <td style="padding-right: 15px"><el-checkbox label="咯血或血痰" name="type"></el-checkbox></td>
                              <td style="padding-right: 15px"><el-checkbox label="胸痛消廋" name="type"></el-checkbox></td>
                            </tr>
                            <tr>
                              <td style="padding-right: 15px"><el-checkbox label="恶心纳差" name="type"></el-checkbox></td>
                              <td style="padding-right: 15px"><el-checkbox label="头痛失眠" name="type"></el-checkbox></td>
                              <td style="padding-right: 15px"><el-checkbox label="视物模糊" name="type"></el-checkbox></td>
                              <td style="padding-right: 15px"><el-checkbox label="皮肤瘙痒、皮疹" name="type"></el-checkbox></td>
                              <td style="padding-right: 15px"><el-checkbox label="耳鸣、听力下降" name="type"></el-checkbox></td>
                            </tr>
                          </table>
                        </el-checkbox-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <td rowspan='2'style="text-align:center;"  >其他</td>
                  <td rowspan='2' colspan='3' style="text-align:center;padding-right: 0px" class="pad10">
                    <el-form-item label=""label-width="0px" >
                      <el-input v-model="editForm.otherSymptoms" auto-complete="off"></el-input>
                    </el-form-item>
                  </td>
                  <tr></tr>

                  <tr>
                    <td rowspan='3' style="width:140px;word-wrap:break-word;text-align:center">用药</td>
                    <td style="text-align:center;">化疗方案</td>
                    <td colspan='3' class="pad10" style="padding-right: 0px">
                      <el-form-item  label=""label-width="0px"  >
                        <el-input v-model="editForm.chemotherapyRegimen" ></el-input>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td style="text-align:center;">用法</td>
                    <td colspan='3' style="text-align:left;" class="pad20">
                      <el-form-item  label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.usages">
                          <el-radio label=每日>每日</el-radio>
                          <el-radio label=间歇>间歇</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td style="text-align:center;">药品剂型</td>
                    <td colspan='3' style="text-align:left;" class="pad20">
                      <el-form-item  label=""label-width="0px"  >
                        <el-checkbox-group v-model="editForm.drugDosage">
                          <el-checkbox v-for="item in drugDosage" :key="item.value":name="type":label="item.label":value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td style="width:140px;text-align:center">督导人员选择</td>
                    <td colspan='4' style="text-align:left;" class="pad20">
                      <el-form-item  label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.supervisorSelection">
                          <el-radio label=医生>医生</el-radio>
                          <el-radio label=家属>家属</el-radio>
                          <el-radio label=自服药>自服药</el-radio>
                          <el-radio label=其他>其他</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td rowspan='2' style="word-wrap:break-word;width:140px;text-align:center">家庭居住环境评估</td>
                    <td style="text-align:center;">单独的居室</td>
                    <td colspan='3' style="text-align:left;" class="pad20">
                      <el-form-item  label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.separateRoom">
                          <el-radio label=有>有</el-radio>
                          <el-radio label=无>无</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td style="text-align:center;">通风情况</td>
                    <td colspan='3' style="text-align:left;" class="pad20">
                      <el-form-item  label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.ventilation">
                          <el-radio label=良好>良好</el-radio>
                          <el-radio label=一般>一般</el-radio>
                          <el-radio label=差>差</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td rowspan='2' style="word-wrap:break-word;width:140px;text-align:center">生活方式评估</td>
                    <td style="text-align:center;">吸烟</td>
                    <td colspan='3' style="padding-right: 0px" class="pad10">
                      <el-form-item  label=""label-width="0px"   >
                        <el-input  v-model="editForm.smoking"  >
                          <template slot="append">支/天</template>
                        </el-input>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td style="text-align:center;">饮酒</td>
                    <td colspan='3' style="padding-right: 0px" class="pad10" >
                      <el-form-item  label=""label-width="0px"  >
                        <el-input  v-model="editForm.drinking"  >
                          <template slot="append">两/天</template>
                        </el-input>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td rowspan='10' style="word-wrap:break-word;width:140px;text-align:center">健康教育及培训</td>
                    <td colspan='2' style="text-align:center;">取药地点、时间</td>
                    <td  colspan='2'style="text-align:center;" class="pad10">
                      <table align="center" cellspacing="0">
                        <tr>
                        <td style="text-align:right;padding:0px;">地点</td>
                         <td style="padding-right: 0px"class="pad10"><el-input  v-model="editForm.takemedicinePlace" ></el-input></td>
                        </tr>
                        <tr>
                          <td style="text-align:right;padding:0px;">时间</td>
                         <td style="padding-right: 0px" class="pad10"><el-date-picker type="date" v-model="editForm.takemedicineTime" ></el-date-picker></td>
                        </tr>

                      </table>
                    </td>
                  </tr>
                  <tr>
                    <td  colspan='2'style="text-align:center;">服药记录卡的填写</td>
                    <td  colspan='2' style="text-align:left;" class="pad20">
                      <el-form-item  label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.medicationRecordcard">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td  colspan='2' style="text-align:center;">服药方法及药品存放</td>
                    <td  colspan='2' style="text-align:left;" class="pad20">
                      <el-form-item  label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.medicationmethodDrugstorage">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td  colspan='2' style="text-align:center;">肺结核治疗疗程</td>
                    <td  colspan='2' style="text-align:left;" class="pad20">
                      <el-form-item  label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.treatmentTuberculosis">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td  colspan='2' style="text-align:center;">不规律服药危害</td>
                    <td  colspan='2' style="text-align:left;" class="pad20">
                      <el-form-item  label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.irregularMedication">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td  colspan='2' style="text-align:center;">服药后不良反应及处理</td>
                    <td  colspan='2' style="text-align:left;" class="pad20">
                      <el-form-item  label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.adverseReactions">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td  colspan='2' style="text-align:center;">治疗期间复诊查痰</td>
                    <td  colspan='2' style="text-align:left;" class="pad20">
                      <el-form-item  label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.review">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td  colspan='2' style="text-align:center;">外出期间如何坚持服药</td>
                    <td  colspan='2'style="text-align:left;" class="pad20">
                      <el-form-item  label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.howtakeMedicine">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td  colspan='2' style="text-align:center;">生活习惯及注意事项</td>
                    <td  colspan='2' style="text-align:left;" class="pad20">
                      <el-form-item  label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.habitsPrecautions">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td  colspan='2' style="text-align:center;">密切接触者检查</td>
                    <td  colspan='2' style="text-align:left;" class="pad20">
                      <el-form-item  label=""label-width="0px"  >
                        <el-radio-group v-model="editForm.closeCheck">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td style="width:140px;text-align:center">下次随访时间</td>
                    <td colspan='4' style="text-align:center;padding-right: 0px" class="pad10">
                      <el-form-item  label=""label-width="0px"  >
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.nextFollowtie" style="width: 100%;" ></el-date-picker>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td style="width:140px;;text-align:center">评估医生签名</td>
                    <td colspan='4' style="text-align:center;padding-right: 0px" class="pad10">
                      <el-form-item  label=""label-width="0px"  >
                        <el-input  v-model="editForm.evaluationDoctorsignature" auto-complete="off" ></el-input>
                      </el-form-item>
                    </td>
                  </tr>

                  </tbody>
                </table>
              </div>
            </el-form>
          </div>
        </div>
      </EasyScrollbar>
      <div slot="footer" class="dialog-footer">
        <el-button  style="height:40px"@click="conprint()">打印</el-button>
        <el-button   style="height:40px" v-if="dialogStatus=='create'" type="primary" @click="check">添加</el-button>
        <el-button   style="height:40px"v-else type="primary"  @click="createData" v-if="edit">新增修改</el-button>
        <el-button  style="height:40px" v-else type="primary" @click="updateData"  v-if="edit">修改</el-button>
        <el-button  style="height:40px" @click.native="dialogFormVisible=false">取消</el-button>
      </div>
    </el-dialog>

  </section>
</template>

<script>
  import util from '@/utils/table.js'
  import {
    getList,
    removeData,
    batchRemoveUser,
    updateData,
    addData,
    checkData as getPerson
  } from '@/api/FirsthomevisitRecord'
  import {
    getList as getSearchMemberPage
  } from '@/api/Perinfor'
  import {
    getList as getFollow,
    updateData as editFollow,
    addData as addFollow
  } from '@/api/follow'
  import city from '@/api/city.js'
  import Tabs from '../table/Tabs'

  export default {
    components: { Tabs },
    data() {
      return {
        buttons: {
          KPFR_C: false,
          KPFR_E: false,
          KPFR_D: false
        },
        edit: true,
        options: city.city(),
        tablebodyHeight: window.innerHeight - 320,
        symptomsSigns: [
          { value: '没有症状', label: '没有症状' },
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
        tableHeight: window.innerHeight - 220,

        textMap: {
          update: '编辑',
          create: '添加',
          detail: '详情'
        },
        dialogFormVisible: false,
        filters: {
          name: '',
          followTime_s: null,
          followTime_e: null,
          inputDate_s: null,
          inputDate_e: null,
          area: '',
          ethnic: '',
          phone: '',
          responDoctor: '',
          inputPerson: '',
          lifeState: '',
          age: '',
          peopleId: ''

        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          area: [{ required: true, message: ' ', trigger: 'blur' }],
          name: [{ required: true, message: ' ', trigger: 'blur' }],
          peopleId: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: ' ' }
          ],
          followTime: [{ required: true, message: ' ', trigger: 'blur' }],
          age: [{ type: 'number', message: ' ', trigger: 'blur' }],
          ruleMedicine: [{ required: true, message: ' ', trigger: 'blur' }],
          management: [{ required: true, message: ' ', trigger: 'blur' }],
          lifeState: [{ required: true, message: ' ', trigger: 'blur' }]

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
          drinking: '',
          takemedicineTime: null,
          takemedicinePlace: '',
          medicationRecordcard: '',
          medicationmethodDrugstorage: '',
          treatmentTuberculosis: '',
          irregularMedication: '',
          adverseReactions: '',
          review: '',
          howtakeMedicine: '',
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
          area: [{ required: true, message: ' ', trigger: 'blur' }],
          name: [{ required: true, message: ' ', trigger: 'blur' }],
          peopleId: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: ' ' }
          ],
          inputDate: [{ required: true, message: ' ', trigger: 'blur' }],
          age: [{ type: 'number', message: ' ', trigger: 'blur' }],
          ruleMedicine: [{ required: true, message: ' ', trigger: 'blur' }],
          management: [{ required: true, message: ' ', trigger: 'blur' }],
          lifeState: [{ required: true, message: ' ', trigger: 'blur' }]
        }
      }
    },
    methods: {
      // 根据身份证赋值
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
                  this.editForm.responDoctor = user[0].responDoctor
                  this.editForm.area = user[0].houseAddress
                  this.editForm.area = this.editForm.houseAddress.split('/')

                  this.active = 2
                }
              })
            })
          }, 200)
        }
      },

      // 性别显示转换
      formatSex: function(row, column) {
        return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
      },
      handleCurrentChange(val) {
        this.page = val
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
          '        <td colspan=\'4\'style="height: 50px" >{{symptomsSigns}}</td>   \n' +
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
          '\t\t<td colspan=\'3\'>{{drinking}} 两/天</td> \n' +
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
          '\t\t<td  colspan=\'2\'>{{howtakeMedicine}}</td> \n' +
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
        str = str.replace(/{{drinking}}/, this.editForm.drinking ? this.editForm.drinking : '')
        str = str.replace(/{{takemedicineTime}}/, this.editForm.takemedicineTime ? this.editForm.takemedicineTime : '')
        str = str.replace(/{{takemedicinePlace}}/, this.editForm.takemedicinePlace ? this.editForm.takemedicinePlace : '')
        str = str.replace(/{{medicationRecordcard}}/, this.editForm.medicationRecordcard ? this.editForm.medicationRecordcard : '')
        str = str.replace(/{{medicationmethodDrugstorage}}/, this.editForm.medicationmethodDrugstorage ? this.editForm.medicationmethodDrugstorage : '')
        str = str.replace(/{{treatmentTuberculosis}}/, this.editForm.treatmentTuberculosis ? this.editForm.treatmentTuberculosis : '')
        str = str.replace(/{{irregularMedication}}/, this.editForm.irregularMedication ? this.editForm.irregularMedication : '')
        str = str.replace(/{{adverseReactions}}/, this.editForm.adverseReactions ? this.editForm.adverseReactions : '')
        str = str.replace(/{{review}}/, this.editForm.review ? this.editForm.review : '')
        str = str.replace(/{{howtakeMedicine}}/, this.editForm.howtakeMedicine ? this.editForm.howtakeMedicine : '')
        str = str.replace(/{{habitsPrecautions}}/, this.editForm.habitsPrecautions ? this.editForm.habitsPrecautions : '')
        str = str.replace(/{{closeCheck}}/, this.editForm.closeCheck ? this.editForm.closeCheck : '')
        str = str.replace(/{{nextFollowtie}}/, this.editForm.nextFollowtie ? this.editForm.nextFollowtie : '')
        str = str.replace(/{{evaluationDoctorsignature}}/, this.editForm.evaluationDoctorsignature ? this.editForm.evaluationDoctorsignature : '')
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
            area: this.filters.area,
            ethnic: this.filters.ethnic,
            phone: this.filters.phone,
            responDoctor: this.filters.responDoctor,
            inputPerson: this.filters.inputPerson,
            lifeState: this.filters.lifeState,
            age: this.filters.age,
            peopleId: this.filters.peopleId }
        }
        getList(para).then(res => {
          this.total = res.totalCount
          this.users = res.detailModelList
          if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
            this.page = this.page - 1
            this.getUsers()
          }
        })
      },
      // 判断个人档案
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
                this.$message.error('未建立个人档案或非肺结核居民')
              }
            })
          } else {
            if (this.editForm.peopleId === ''||this.editForm.ruleMedicine === ''||this.editForm.lifeState === '') {

              this.$message.error('您还有必填项未填')
              return false
            } else {
              this.$message.error('未建立个人档案或非肺结核居民')
            }
          }
        })
      },

      // 删除
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          confirmButtonText: ' 确定',
          cancelButtonText: ' 取消',
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
      // 查看档案
      goto(index, row) {
        this.$router.push({
          name: '首页',
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
        this.edit = false
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.drugDosage = this.editForm.drugDosage.split(',')
        this.editForm.symptomsSigns = this.editForm.symptomsSigns.split(',')
        this.edit = true
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.editForm = {
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
          drinking: '',
          takemedicineTime: null,
          takemedicinePlace: '',
          medicationRecordcard: '',
          medicationmethodDrugstorage: '',
          treatmentTuberculosis: '',
          irregularMedication: '',
          adverseReactions: '',
          review: '',
          howtakeMedicine: '',
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
          lifeState: '',
          creator: this.$store.getters.name
        }
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: ' 确定',
              cancelButtonText: ' 取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.followTime =
                  !para.followTime || para.followTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.followTime), 'yyyy-MM-dd')
                para.takemedicineTime =
                para.takemedicineTime =
                  !para.takemedicineTime || para.takemedicineTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.takemedicineTime), 'yyyy-MM-dd')
                para.nextFollowtie =
                para.nextFollowtie =
                  !para.nextFollowtie || para.nextFollowtie === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextFollowtie), 'yyyy-MM-dd')
                para.drugDosage = ''
                for (const itm of this.editForm.drugDosage) {
                  para.drugDosage += itm
                  para.drugDosage += ','
                }
                para.drugDosage = para.drugDosage.substr(0, para.drugDosage.length - 1)

                para.symptomsSigns = ''
                for (const itm of this.editForm.symptomsSigns) {
                  para.symptomsSigns += itm
                  para.symptomsSigns += ','
                }
                para.symptomsSigns = para.symptomsSigns.substr(0, para.symptomsSigns.length - 1)
                para.inputDate =
                  !para.inputDate || para.inputDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.inputDate), 'yyyy-MM-dd')

                para.recentVisit =
                para.recentVisit =
                  !para.recentVisit || para.recentVisit === ''
                    ? null
                    : util.formatDate.format(new Date(para.recentVisit), 'yyyy-MM-dd')

                updateData(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.$emit('watchChild')
                  this.getUsers()
                })
                // 随访记录
                const para2 = {
                  page: 1,
                  size: 200,
                  filters: { nextFollow_s: '', nextFollow_e: '', peopleId: para.peopleId, userId: '' }
                }
                const followData = {
                  peopleId: para.peopleId,
                  nextFollow: '',
                  lastFollow: '',
                  userId: this.$store.getters.id
                }
                getFollow(para2).then(res => {
                  const follow = res.detailModelList
                  if (follow.length > 0) {
                    if (follow[0].lastFollow < para.followTime) {
                      follow[0].lastFollow = para.followTime
                    }
                    if (follow[0].nextFollow === '' || follow[0].nextFollow === null) {
                      follow[0].nextFollow = para.nextFollowtie
                    } else if (follow[0].nextFollow < para.nextFollowtie || para.nextFollowtie === '' || para.nextFollowtie === null) {
                      follow[0].nextFollow = para.nextFollowtie
                    }
                    editFollow(follow[0]).then(res => {})
                  } else {
                    followData.lastFollow = para.followTime
                    followData.nextFollow = para.nextFollowtie
                    addFollow(followData).then(res => {})
                  }
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
              confirmButtonText: ' 确定',
              cancelButtonText: ' 取消'
            })
              .then(() => {
                this.editForm.id = (parseInt(Math.random() * 100)).toString() // mock a id
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.followTime =
                  !para.followTime || para.followTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.followTime), 'yyyy-MM-dd')
                para.takemedicineTime =
                para.takemedicineTime =
                  !para.takemedicineTime || para.takemedicineTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.takemedicineTime), 'yyyy-MM-dd')
                para.nextFollowtie =
                para.nextFollowtie =
                  !para.nextFollowtie || para.nextFollowtie === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextFollowtie), 'yyyy-MM-dd')
                para.drugDosage = ''
                for (const itm of this.editForm.drugDosage) {
                  para.drugDosage += itm
                  para.drugDosage += ','
                }
                para.drugDosage = para.drugDosage.substr(0, para.drugDosage.length - 1)

                para.symptomsSigns = ''
                for (const itm of this.editForm.symptomsSigns) {
                  para.symptomsSigns += itm
                  para.symptomsSigns += ','
                }
                para.symptomsSigns = para.symptomsSigns.substr(0, para.symptomsSigns.length - 1)

                para.inputDate =
                  !para.inputDate || para.inputDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.inputDate), 'yyyy-MM-dd')

                para.recentVisit =
                  para.recentVisit =
                  !para.recentVisit || para.recentVisit === ''
                    ? null
                    : util.formatDate.format(new Date(para.recentVisit), 'yyyy-MM-dd')

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

                // 随访记录
                const para2 = {
                  page: 1,
                  size: 200,
                  filters: { nextFollow_s: '', nextFollow_e: '', peopleId: para.peopleId, userId: '' }
                }
                const followData = {
                  peopleId: para.peopleId,
                  nextFollow: '',
                  lastFollow: '',
                  userId: this.$store.getters.id
                }
                getFollow(para2).then(res => {
                  const follow = res.detailModelList
                  if (follow.length > 0) {
                    if (follow[0].lastFollow < para.followTime) {
                      follow[0].lastFollow = para.followTime
                    }
                    if (follow[0].nextFollow === '' || follow[0].nextFollow === null) {
                      follow[0].nextFollow = para.nextFollowtie
                    } else if (follow[0].nextFollow < para.nextFollowtie || para.nextFollowtie === '' || para.nextFollowtie === null) {
                      follow[0].nextFollow = para.nextFollowtie
                    }
                    editFollow(follow[0]).then(res => {})
                  } else {
                    followData.lastFollow = para.followTime
                    followData.nextFollow = para.nextFollowtie
                    addFollow(followData).then(res => {})
                  }
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
