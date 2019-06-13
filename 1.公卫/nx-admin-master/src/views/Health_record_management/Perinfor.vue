<template xmlns:el-col="http://www.w3.org/1999/html">
  <section class="app-container">

    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.peopleId" placeholder="身份证号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.cardNumber" placeholder="医保卡号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="search" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-if="buttons.C_HA" @click="handleAdd" icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-if="buttons.C_Out" @click="handleMOVE" icon="el-icon-circle-plus-outline">档案迁出</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--列表-->
    <el-table :data="users" :height="tableMeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;"center  @row-dblclick="handleRowChange">
      <el-table-column type="index" label="序号" width="60">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="120">
      </el-table-column>
      <el-table-column prop="birth" label="出生日期" width="120">
      </el-table-column>
      <el-table-column prop="tphoneNum" label="联系电话" width="120">
      </el-table-column>
      <el-table-column prop="local" label="现住址" width="120">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" min-width="120">
      </el-table-column>
      <el-table-column prop="workPlace" label="工作单位" min-width="120">
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <a size="14px" v-if="buttons.P_Edit" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" v-if="buttons.P_Out" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
        </template>
      </el-table-column>
    </el-table>


    <!--工具条-->

    <el-col :span="24" class="toolbar">
      <!-- <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
      <el-pagination layout="prev, pager, next"  :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>


    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <div id="printData">
        <el-form :model="editForm" label-width="90px" :rules="editFormRules" ref="editForm">
          <EasyScrollbar>
            <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
              <el-tabs v-model="activeName">
                <div id="dataPanel">
                  <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:30px;border-collapse:collapse;">
                    <tbody>
                    <tr v-if="dialogStatus==='detail'">
                      <td style="word-wrap:break-word;text-align:center;">
                        创建人
                      </td>
                      <td colspan="3" style="text-align:center">
                        <el-form-item label="" label-width="0px">
                          <el-input v-model="editForm.creator" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;">
                        创建时间
                      </td>
                      <td colspan="3" style="text-align:center">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr v-if="dialogStatus==='detail'">
                      <td style="word-wrap:break-word;text-align:center;">
                        修改人
                      </td>
                      <td colspan="3" style="text-align:center">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-input v-model="editForm.modifier" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;">
                        修改时间
                      </td>
                      <td colspan="3" style="text-align:center">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" class="pad" scope="row"><span>*</span>姓名</td>
                      <td colspan="2" class="pad10" >
                        <el-form-item label=""label-width="0px" prop="name">
                          <el-input v-model="editForm.name" style="width: 100%"id="name"></el-input>
                        </el-form-item>
                      </td>
                      <td width="140px" scope="row">性别</td>
                      <td colspan="2"class="pad10">
                        <el-radio-group v-model="editForm.sex">
                          <table>
                            <tr>
                              <td><el-radio label="男">男</el-radio></td>
                              <td><el-radio label="女">女</el-radio></td>
                              <td><el-radio label="未知性别">未知性别</el-radio></td>
                            </tr>
                          </table>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" class="pad" scope="row"><span>*</span>出生日期</td>
                      <td colspan="2"class="pad10" >
                        <el-form-item label="" label-width="0px"prop="birth">
                          <el-date-picker  style="width: 100%"type="date" placeholder="选择日期" :picker-options="pickerOptions0" v-model.number="editForm.birth" id="birth"></el-date-picker>
                        </el-form-item>
                      </td>
                      <td width="140px" class="pad" scope="row"><span>*</span>身份证号</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label="" label-width="0px"prop="peopleId">
                          <el-input v-model="editForm.peopleId" style="width: 100%"id="peopleId"></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">工作单位</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label=""label-width="0px">
                          <el-input  v-model="editForm.workPlace" style="width: 100%"></el-input>
                        </el-form-item>
                      </td>
                      <td width="140px" scope="row">本人电话</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label=""label-width="0px" >
                          <el-input v-model="editForm.tphoneNum" style="width: 100%"></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">联系人姓名</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label="" label-width="0px">
                          <el-input v-model="editForm.phonerName" auto-complete="off"></el-input>
                        </el-form-item>
                      </td>
                      <td width="140px" scope="row">联系人电话</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label="" label-width="0px">
                          <el-input  v-model="editForm.phonerNum" auto-complete="off" ></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" class="pad" scope="row"><span>*</span>存活状态</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label=""label-width="0px" prop="livestate">
                          <el-radio-group v-model="editForm.livestate" id="livestate">
                            <el-radio label="死亡">死亡</el-radio>
                            <el-radio label="存活">存活</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                      <td width="140px" class="pad" scope="row"><span>*</span>孕妇</td>
                      <td colspan="2"class="pad10" >
                        <el-form-item label=""label-width="0px" prop="pregnant" style="margin-left:20px;">
                          <el-radio-group v-model="editForm.pregnant" id="pregnant">
                            <el-radio label="是">是</el-radio>
                            <el-radio label="否">否</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">常住类型</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.residencePlace">
                            <el-radio label="户籍">户籍</el-radio>
                            <el-radio label="非户籍">非户籍</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                      <td width="140px" scope="row">民族</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.ethnic">
                            <el-radio label="汉族">汉族</el-radio>
                            <el-radio label="其他">其他</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" class="pad" scope="row"><span>*</span>户籍地址</td>
                      <td colspan="2"class="pad10" >
                        <el-form-item label="" label-width="0px" prop="houseAddress">
                          <el-cascader :options="options"   v-model="editForm.houseAddress" style="width: 100%;" placeholder="请选择"  id="houseAddress" change-on-select></el-cascader>
                        </el-form-item>
                      </td>
                      <td width="140px"scope="row">卡号</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label="" label-width="0px">
                          <el-input v-model="editForm.cardNumber" auto-complete="off"></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">现住地址</td>
                      <td colspan="4"class="pad10">
                        <el-form-item label="" label-width="0px" >
                          <el-input v-model="editForm.local" auto-complete="off"></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">村（居）委会名称</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label="" label-width="0px" >
                          <el-input v-model="editForm.committees" auto-complete="off"></el-input>
                        </el-form-item>
                      </td>
                      <td width="140px" scope="row">乡镇名称</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label="" label-width="0px">
                          <el-input v-model="editForm.villageName" auto-complete="off"></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">责任医生</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label="" label-width="0px" >
                          <el-input v-model="editForm.responDoctor" auto-complete="off"></el-input>
                        </el-form-item>
                      </td>
                      <td width="140px" scope="row">建档单位</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label="" label-width="0px">
                          <el-input v-model="editForm.inputtingUnit" auto-complete="off"></el-input>
                        </el-form-item>
                      </td>
                    </tr>

                    <tr>
                      <td width="140px" scope="row">文化程度</td>
                      <td colspan="5"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.educational">
                            <table>
                              <tr>
                                <td ><el-radio label="研究生">研究生</el-radio></td>
                                <td ><el-radio label="大学本科">大学本科</el-radio></td>
                                <td ><el-radio label="大学专科和专科学校">大学专科和专科学校</el-radio></td>
                                <td ><el-radio label="中等专业学校">中等专业学校</el-radio></td>
                                <td ><el-radio label="技工学校">技工学校</el-radio></td>
                              </tr>
                              <tr>
                                <td ><el-radio label="高中">高中</el-radio></td>
                                <td ><el-radio label="小学">小学</el-radio></td>
                                <td ><el-radio label="文盲或半文盲">文盲或半文盲</el-radio></td>
                                <td ><el-radio label="不详">不详</el-radio></td>
                              </tr>
                            </table>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>

                    <tr>
                      <td width="140px" scope="row">血型</td>
                      <td colspan="5"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.bloodType">
                            <table>
                              <tr>
                                <td style="padding-right: 10px"><el-radio label="A型">A型</el-radio></td>
                                <td style="padding-right: 10px"><el-radio label="B型">B型</el-radio></td>
                                <td style="padding-right: 10px">><el-radio label="O型">O型</el-radio></td>
                                <td style="padding-right: 10px">><el-radio label="AB型">AB型</el-radio></td>
                                <td style="padding-right: 10px">><el-radio label="不详">不详</el-radio></td>
                                <td style="padding-right: 10px">><el-radio label="RH阴性">RH阴性</el-radio></td>
                                <td style="padding-right: 10px">><el-radio label="RH阳性">RH阳性</el-radio></td>
                                <td style="padding-right: 10px"><el-radio label="RH不详">RH不详</el-radio></td>
                              </tr>
                            </table>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">职业</td>
                      <td colspan="5"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.occupation">
                            <table style="text-align:left">
                              <tr>
                                <td width="30%">
                                  <el-radio label="国家机关、党群组织、企业、事业单位负责人">国家机关、党群组织、企业、事业单位负责人</el-radio>
                                </td>
                                <td width="30%">
                                  <el-radio label="专业技术人员">专业技术人员</el-radio>
                                </td>
                                <td width="30%">
                                  <el-radio label="办事人员和有关人员">办事人员和有关人员</el-radio>
                                </td>
                              </tr>
                              <tr>
                                <td width="30%">
                                  <el-radio label="生产、运输设备操作人员及有关人员">生产、运输设备操作人员及有关人员</el-radio>
                                </td>
                                <td width="30%">
                                  <el-radio label="商业、服务业人员">商业、服务业人员</el-radio>
                                </td>
                                <td width="30%">
                                  <el-radio label="农、林、牧、渔、水利业生产人员">农、林、牧、渔、水利业生产人员</el-radio>
                                </td>
                              </tr>
                              <tr>
                                <td width="30%">
                                  <el-radio label="不便分类的其他从业人员">不便分类的其他从业人员</el-radio>
                                </td>
                                <td width="30%">
                                  <el-radio label="军人">军人</el-radio>
                                </td>
                                <td width="30%">
                                  <el-radio label="无职业">无职业</el-radio>
                                </td>
                              </tr>
                            </table>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">婚姻状况</td>
                      <td colspan="5"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.maritalStatus">
                            <el-radio label="未婚">未婚</el-radio>
                            <el-radio label="已婚">已婚</el-radio>
                            <el-radio label="丧偶">丧偶</el-radio>
                            <el-radio label="离婚">离婚</el-radio>
                            <el-radio label="未说明的婚姻状况">未说明的婚姻状况</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">医疗费用支付方式</td>
                      <td colspan="5" height="80px"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.expensesPay">
                            <table style="text-align:left">
                              <tr>
                                <td width="20%">
                                  <el-radio label="城镇职工基本医疗保险">城镇职工基本医疗保险</el-radio>
                                </td>
                                <td width="20%">
                                  <el-radio label="城镇居民医疗保险">城镇居民医疗保险</el-radio>
                                </td>
                                <td width="20%">
                                  <el-radio label="新型农村合作医疗">新型农村合作医疗</el-radio>
                                </td>
                                <td width="20%">
                                  <el-radio label="贫困救助">贫困救助</el-radio>
                                </td>
                                <td width="20%">
                                  <el-radio label="商业医疗保险">商业医疗保险</el-radio>
                                </td>
                              </tr>
                              <tr>
                                <td width="20%">
                                  <el-radio label="全公费">全公费</el-radio>
                                </td>
                                <td width="20%">
                                  <el-radio label="全自费">全自费</el-radio>
                                </td>
                                <td width="20%">
                                  <el-radio label="其他">其他</el-radio></td>
                              </tr>
                            </table>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">暴 露 史</td>
                      <td colspan="5"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-checkbox-group v-model="exposureHistory_model" style="width:100%">
                            <el-checkbox v-for="item in exposureHistory" :key="item.value" :label="item.label" :value="item.value">
                            </el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" rowspan="6" scope="row">既往史</td>
                      <td>疾病</td>
                      <td colspan="4"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-checkbox-group v-model="pastHistory_model" style="width:100%">
                            <table style="text-align:left">
                              <tr>
                                <td style="width: 16%"><el-checkbox label="无" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="高血压" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="糖尿病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="冠心病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="慢性阻塞性肺疾病" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="恶性肿瘤" name="type"></el-checkbox></td>
                              </tr>
                              <tr>
                                <td style="width: 16%"><el-checkbox label="脑卒中" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="严重精神障碍" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="结核病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="其他法定传染病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="职业病" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="其他" name="type"></el-checkbox></td>
                              </tr>

                            </table>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="2">手术</td>
                      <td colspan="1" rowspan="2"class="pad10">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.operation">
                            <table>
                              <tr>
                                <td>
                                  <el-radio label="无">无</el-radio>
                                </td>
                                <td>
                                  <el-radio label="有">有</el-radio>
                                </td>
                              </tr>
                            </table>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                      <td colspan="3"class="pad10">
                        <table>
                          <tr>
                            <td>
                              <el-form-item label="手术名称" label-width="80px">
                                <el-input  v-model="editForm.operation1"></el-input>
                              </el-form-item>
                            </td>
                            <td>
                              <el-form-item label="手术日期"label-width="80px">
                                <el-date-picker style="width: 100%" type="date" placeholder="选择日期" v-model="editForm.oTime1"></el-date-picker>
                              </el-form-item>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="3"class="pad10">
                        <table>
                          <tr>
                            <td>
                              <el-form-item label="手术名称"label-width="80px">
                                <el-input  v-model="editForm.operation2"  ></el-input>
                              </el-form-item>
                            </td>
                            <td>
                              <el-form-item label="手术日期"label-width="80px">
                                <el-date-picker style="width: 100%" type="date" placeholder="选择日期" v-model="editForm.oTime2"></el-date-picker>
                              </el-form-item>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="2">外伤</td>
                      <td colspan="1" rowspan="2"class="pad10">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.trauma">
                            <table>
                              <tr>
                                <td>
                                  <el-radio label="无">无</el-radio>
                                </td>
                                <td>
                                  <el-radio label="有">有</el-radio>
                                </td>
                              </tr>
                            </table>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                      <td colspan="3"class="pad10">
                        <table>
                          <tr>
                            <td>
                              <el-form-item label="外伤名称"label-width="80px">
                                <el-input  v-model="editForm.trauma1"  ></el-input>
                              </el-form-item>
                            </td>
                            <td>
                              <el-form-item label="受伤日期"label-width="80px">
                                <el-date-picker style="width: 100%" type="date" placeholder="选择日期" v-model="editForm.tTime1"></el-date-picker>
                              </el-form-item>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="3"class="pad10">
                        <table>
                          <tr>
                            <td>
                              <el-form-item label="外伤名称"label-width="80px">
                                <el-input  v-model="editForm.trauma2"  ></el-input>
                              </el-form-item>
                            </td>
                            <td>
                              <el-form-item label="受伤日期"label-width="80px">
                                <el-date-picker style="width: 100%" type="date" placeholder="选择日期" v-model="editForm.tTime2"></el-date-picker>
                              </el-form-item>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="1">输血</td>
                      <td colspan="1" rowspan="1"class="pad10">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.transfusion">
                            <table>
                              <tr>
                                <td>
                                  <el-radio label="无">无</el-radio>
                                </td>
                                <td>
                                  <el-radio label="有">有</el-radio>
                                </td>
                              </tr>
                            </table>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                      <td colspan="3"class="pad10">
                        <el-form-item label="输血时间" label-width="80px">
                          <el-date-picker style="width: 100%" type="date" placeholder="选择日期" v-model="editForm.transfusionTime"></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>

                    <tr>
                      <td width="140px" rowspan="7" scope="row">家族史</td>
                    </tr>
                    <tr>
                      <td width="106" scope="row">父亲</td>
                      <td colspan="5"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-checkbox-group v-model="fatherHistory_model" style="width:100%">
                            <table>
                              <tr>
                                <td style="width: 16%"><el-checkbox label="无" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="高血压" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="糖尿病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="冠心病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="慢性阻塞性肺疾病" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="恶性肿瘤" name="type"></el-checkbox></td>
                              </tr>
                              <tr>
                                <td style="width: 16%"><el-checkbox label="脑卒中" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="严重精神障碍" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="结核病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="其他法定传染病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="职业病" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="其他" name="type"></el-checkbox></td>
                              </tr>
                            </table>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="106" scope="row">母亲</td>
                      <td colspan="5"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-checkbox-group v-model="matherHistory_model" style="width:100%">
                            <table>
                              <tr>
                                <td style="width: 16%"><el-checkbox label="无" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="高血压" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="糖尿病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="冠心病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="慢性阻塞性肺疾病" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="恶性肿瘤" name="type"></el-checkbox></td>
                              </tr>
                              <tr>
                                <td style="width: 16%"><el-checkbox label="脑卒中" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="严重精神障碍" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="结核病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="其他法定传染病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="职业病" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="其他" name="type"></el-checkbox></td>
                              </tr>
                            </table>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="106" scope="row">兄弟姐妹</td>
                      <td colspan="5"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-checkbox-group v-model="bortherHistory_model" style="width:100%">
                            <table>
                              <tr>
                                <td style="width: 16%"><el-checkbox label="无" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="高血压" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="糖尿病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="冠心病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="慢性阻塞性肺疾病" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="恶性肿瘤" name="type"></el-checkbox></td>
                              </tr>
                              <tr>
                                <td style="width: 16%"><el-checkbox label="脑卒中" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="严重精神障碍" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="结核病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="其他法定传染病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="职业病" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="其他" name="type"></el-checkbox></td>
                              </tr>
                            </table>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="106" scope="row">子女</td>
                      <td colspan="5"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-checkbox-group v-model="sonHistory_model" style="width:100%">
                            <table>
                              <tr>
                                <td style="width: 16%"><el-checkbox label="无" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="高血压" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="糖尿病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="冠心病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="慢性阻塞性肺疾病" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="恶性肿瘤" name="type"></el-checkbox></td>
                              </tr>
                              <tr>
                                <td style="width: 16%"><el-checkbox label="脑卒中" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="严重精神障碍" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="结核病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="其他法定传染病" name="type"></el-checkbox></td>
                                <td style="width: 16%"><el-checkbox label="职业病" name="type"></el-checkbox></td>
                                <td style="width: 16%"> <el-checkbox label="其他" name="type"></el-checkbox></td>
                              </tr>
                            </table>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="106" scope="row">遗传病史</td>
                      <td colspan="5"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.geneticHistory">
                            <el-radio label="无">无</el-radio>
                            <el-radio label="有">有</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="106" scope="row">残疾情况</td>
                      <td colspan="5" style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-checkbox-group v-model="disability_model" style="width:100%">
                          <table>
                              <tr>
                                <td ><el-checkbox label="无" name="type"></el-checkbox></td>
                                <td ><el-checkbox label="视力残疾" name="type"></el-checkbox></td>
                                <td ><el-checkbox label="听力残疾" name="type"></el-checkbox></td>
                                <td ><el-checkbox label="言语残疾" name="type"></el-checkbox></td>
                                <td ><el-checkbox label="肢体残疾" name="type"></el-checkbox></td>
                                <td ><el-checkbox label="智力残疾" name="type"></el-checkbox></td>
                                <td ><el-checkbox label="精神残疾" name="type"></el-checkbox></td>
                                <td ><el-checkbox label="其他残疾" name="type"></el-checkbox></td>
                              </tr>
                            </table>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" rowspan="5" scope="row">生活环境</td>
                      <td>厨房排风设施</td>
                      <td colspan="4"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-checkbox-group v-model="kitchenExhaust_model" style="width:100%" >
                            <el-checkbox v-for="item in kitchenExhaust" :key="item.value" :label="item.label" :value="item.value"></el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>燃料类型</td>
                      <td colspan="4"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-checkbox-group v-model="fuel_model" style="width:100%">
                            <el-checkbox v-for="item in fuel" :key="item.value" :label="item.label" :value="item.value">
                            </el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>饮水 </td>
                      <td colspan="4"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-checkbox-group v-model="water_model" style="width:100%">
                            <el-checkbox v-for="item in water" :key="item.value" :label="item.label" :value="item.value">
                            </el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>厕所</td>
                      <td colspan="4"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-checkbox-group v-model="toilet_model" style="width:100%">
                           <table>
                              <tr>
                                <el-checkbox label="卫生厕所" name="type"></el-checkbox>
                                <el-checkbox label="一格或二格粪池式" name="type"></el-checkbox>
                                <el-checkbox label="马桶" name="type"></el-checkbox>
                                <el-checkbox label="露天粪坑" name="type"></el-checkbox>
                              <el-checkbox label="简易棚厕" name="type"></el-checkbox>
                              </tr>
                            </table>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>禽畜栏</td>
                      <td colspan="4"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-checkbox-group v-model="livestockBar_model" style="width:100%">
                            <el-checkbox v-for="item in livestockBar" :key="item.value" :label="item.label" :value="item.value">
                            </el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>

                    </tbody>
                  </table>
                </div>
              </el-tabs>
            </div>
          </EasyScrollbar>
        </el-form>

      </div>
      <div slot="footer" class="dialog-footer"center>
        <el-button @click="conprint()">打印</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="check">添加</el-button>
        <el-button v-else type="primary" v-if="edit" @click="check">新增修改</el-button>
        <el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
      </div>
    </el-dialog>


    <!--档案迁出申请页面-->
    <!--档案迁出编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisibleMOVE" :close-on-click-modal="false"center>
      <el-form :model="editForm" label-width="110px" :rules="editFormRules" ref="editForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="身份证号" prop="peopleId" >
              <el-input v-model="editForm.peopleId" auto-complete="off"  placeholder="请输入身份证号码..."  v-on:input="inputFunc1"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12" on-if="false">
            <el-form-item label="户籍迁出地" >
              <el-input  type="textarea" v-model="editForm.houseAddress1"  disabled ></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="户籍迁入地" prop="houseAddress2">
              <el-cascader :options="options" v-model="houseAddress2" style="width: 100%;" placeholder="请选择"  ></el-cascader>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col>
            <el-form-item label="申请迁出原因">
              <el-input type="textarea"  v-model="editForm.migrationReason"   clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer"center>
        <el-button  type="primary" @click="check2()" >申请迁出</el-button>
        <el-button @click.native="dialogFormVisibleMOVE=false">取消</el-button>
      </div>
    </el-dialog>

  </section>
</template>

<script>
  import util from '@/utils/table.js'
  import {
    getList,
    getListPage,
    removeData,
    updateData,
    addData
  } from '@/api/Perinfor'
  import city from '@/api/city.js'
  import {
    addData0
  } from '@/api/PerinforFileEmigrate'
  import {
    getList as getSearchMemberPage
  } from '@/api/Perinfor'
  export default {
    name: 'Perinfor',
    data() {
      return {
        buttons: {
          C_HA: false,
          C_Out: false,
          P_Edit: false,
          P_Out: false
        },
        isAble: false,
        options: city.city(),
        tableHeight: window.innerHeight - 220,
        tablebodyHeight: window.innerHeight - 320,
        // 多选框
        exposureHistory_model: [],
        drugAllergyHistory_model: [],
        pastHistory_model: [],
        fatherHistory_model: [],
        matherHistory_model: [],
        bortherHistory_model: [],
        sonHistory_model: [],
        disability_model: [],
        kitchenExhaust_model: [],
        fuel_model: [],
        water_model: [],
        toilet_model: [],
        livestockBar_model: [],
        pickerOptions0: {
          disabledDate(time) {
            return time.getTime() > Date.now()
          }
        },
        // 字典
        disability: [
          { value: '无', label: '无' },
          { value: '视力残疾', label: '视力残疾' },
          { value: '听力残疾', label: '听力残疾' },
          { value: '言语残疾', label: '言语残疾' },
          { value: '肢体残疾', label: '肢体残疾' },
          { value: '智力残疾', label: '智力残疾' },
          { value: '精神残疾', label: '精神残疾' },
          { value: '其他残疾', label: '其他残疾' }
        ],
        kitchenExhaust: [
          { value: '无', label: '无' },
          { value: '油烟机', label: '油烟机' },
          { value: '换气扇', label: '换气扇' },
          { value: '烟囱', label: '烟囱' },
          { value: '其他', label: '其他' }
        ],
        fuel: [
          { value: '液化气', label: '液化气' },
          { value: '煤', label: '煤' },
          { value: '天然气', label: '天然气' },
          { value: '沼气', label: '沼气' },
          { value: '柴火', label: '柴火' },
          { value: '其他', label: '其他' }
        ],
        water: [
          { value: '自来水', label: '自来水' },
          { value: '经净化过滤的水', label: '经净化过滤的水' },
          { value: '井水', label: '井水' },
          { value: '河湖水', label: '河湖水' },
          { value: '塘水', label: '塘水' },
          { value: '其他', label: '其他' }
        ],
        toilet: [
          { value: '卫生厕所', label: '卫生厕所' },
          { value: '一格或二格粪池式', label: '一格或二格粪池式' },
          { value: '马桶', label: '马桶' },
          { value: '露天粪坑', label: '露天粪坑' },
          { value: '简易棚厕', label: '简易棚厕' }
        ],
        livestockBar: [
          { value: '无', label: '无' },
          { value: '单设', label: '单设' },
          { value: '室内', label: '室内' },
          { value: '室外', label: '室外' }
        ],
        fatherHistory: [
          { value: '无', label: '无' },
          { value: '高血压', label: '高血压' },
          { value: '糖尿病', label: '糖尿病' },
          { value: '冠心病', label: '冠心病' },
          { value: '慢性阻塞性肺炎', label: '慢性阻塞性肺炎' },
          { value: '恶性肿瘤', label: '恶性肿瘤' },
          { value: '脑卒中', label: '脑卒中' },
          { value: '严重精神障碍', label: '严重精神障碍' },
          { value: '结核病', label: '结核病' },
          { value: '肝炎', label: '肝炎' },
          { value: '先天畸形', label: '先天畸形' },
          { value: '其他', label: '其他' }],
        matherHistory: [
          { value: '无', label: '无' },
          { value: '高血压', label: '高血压' },
          { value: '糖尿病', label: '糖尿病' },
          { value: '冠心病', label: '冠心病' },
          { value: '慢性阻塞性肺炎', label: '慢性阻塞性肺炎' },
          { value: '恶性肿瘤', label: '恶性肿瘤' },
          { value: '脑卒中', label: '脑卒中' },
          { value: '严重精神障碍', label: '严重精神障碍' },
          { value: '结核病', label: '结核病' },
          { value: '肝炎', label: '肝炎' },
          { value: '先天畸形', label: '先天畸形' },
          { value: '其他', label: '其他' }],
        bortherHistory: [
          { value: '无', label: '无' },
          { value: '高血压', label: '高血压' },
          { value: '糖尿病', label: '糖尿病' },
          { value: '冠心病', label: '冠心病' },
          { value: '慢性阻塞性肺炎', label: '慢性阻塞性肺炎' },
          { value: '恶性肿瘤', label: '恶性肿瘤' },
          { value: '脑卒中', label: '脑卒中' },
          { value: '严重精神障碍', label: '严重精神障碍' },
          { value: '结核病', label: '结核病' },
          { value: '肝炎', label: '肝炎' },
          { value: '先天畸形', label: '先天畸形' },
          { value: '其他', label: '其他' }],
        sonHistory: [
          { value: '无', label: '无' },
          { value: '高血压', label: '高血压' },
          { value: '糖尿病', label: '糖尿病' },
          { value: '冠心病', label: '冠心病' },
          { value: '慢性阻塞性肺炎', label: '慢性阻塞性肺炎' },
          { value: '恶性肿瘤', label: '恶性肿瘤' },
          { value: '脑卒中', label: '脑卒中' },
          { value: '严重精神障碍', label: '严重精神障碍' },
          { value: '结核病', label: '结核病' },
          { value: '肝炎', label: '肝炎' },
          { value: '先天畸形', label: '先天畸形' },
          { value: '其他', label: '其他' }],
        pastHistory: [
          { value: '无', label: '无' },
          { value: '高血压', label: '高血压' },
          { value: '糖尿病', label: '糖尿病' },
          { value: '冠心病', label: '冠心病' },
          { value: '慢性阻塞性肺疾病', label: '慢性阻塞性肺疾病' },
          { value: '恶性肿瘤', label: '恶性肿瘤' },
          { value: '脑卒中', label: '脑卒中' },
          { value: '严重精神障碍', label: '严重精神障碍' },
          { value: '结核病', label: '结核病' },
          { value: '其他法定传染病', label: '其他法定传染病' },
          { value: '职业病', label: '职业病' },
          { value: '其他', label: '其他' }],
        drugAllergyHistory: [
          { value: '无', label: '无' },
          { value: '青霉素', label: '青霉素' },
          { value: '磺胺', label: '磺胺' },
          { value: '链霉素', label: '链霉素' },
          { value: '其他', label: '其他' }
        ],
        exposureHistory: [
          { value: '无', label: '无' },
          { value: '化学品', label: '化学品' },
          { value: '毒物', label: '毒物' },
          { value: '射线', label: '射线' }
        ],
        tableMeight: window.innerHeight - 220,
        activeName: 'first',
        dialogStatus: '',
        houseAddress1: [],
        houseAddress2: [],
        houseAddress: [],
        migrationReason: '',
        textMap: {
          update: '编辑',
          create: '新增',
          detail: '详情'
        },
        dialogFormVisibleMOVE: false,
        dialogFormVisible: false,
        filters: {
          name: '',
          peopleId: '',
          cardNumber: ''
        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          livestate: [{ required: true, message: '请填写存活状态', trigger: 'blur' }],
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          birth: [{ required: true, message: '请选择出生日期', trigger: 'blur' }],
          pregnant: [{ required: true, message: '判断是否为孕妇', trigger: 'blur' }],
          houseAddress: [{ required: true, message: '请选择地址', trigger: 'blur' }],
          peopleId: [{ required: true, message: '请输入身份证号', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '你的身份证格式不正确' }]
        },
        // 编辑界面数据
        editForm: {
          id: 0,
          houseAddress: [],
          houseAddress1: [],
          houseAddress2: [],
          migrationReason: '',
          exposureHistory: '',
          drugAllergyHistory: '',
          pastHistory: '',
          fatherHistory: '',
          matherHistory: '',
          bortherHistory: '',
          sonHistory: '',
          disability: '',
          kitchenExhaust: '',
          fuel: '',
          water: '',
          toilet: '',
          livestockBar: '',
          cardNumber: '',
          livestate: '',
          creator: '',
          createDate: null,
          modifier: '',
          modifyDate: null
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          birth: [{ required: true, message: '请选择出生日期', trigger: 'blur' }],
          pregnant: [{ required: true, message: '判断是否为孕妇', trigger: 'blur' }],
          houseAddress: [{ required: true, message: '请选择地址', trigger: 'blur' }],
          peopleId: [{ required: true, message: '请输入身份证号码', trigger: 'blur' }]
        }
      }
    },
    methods: {
      // 性别显示转换
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
        // myWindow.document.write(content + style)
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
          '\t<div id="topContent">\n' +
          '\t\t<h2 style="width:794px;text-align:center">个人基本信息表</h2>\n' +
        '\t\t<span style="margin-left:34px;">姓名:{{name}}</span>\n' +
        '\t\t<span style="margin-left:270px;">编号:{{fileId}}</span> \n' +
        '\t</div>\n' +
        '\t<table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
        '\t<tbody>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td width="123" style="word-wrap:break-word;text-align:center">性别</td>\n' +
        '\t\t\t<td colspan="2" style="text-align:center">{{sex}}</td>\n' +
        '\t\t\t<td width="146" style="word-wrap:break-word;text-align:center">出生日期</td>\n' +
        '\t\t\t<td colspan="2" style="text-align:center">{{birth}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td style="word-wrap:break-word;text-align:center">身份证号</td>\n' +
        '\t\t\t<td colspan="2" style="text-align:center">{{peopleId}}</td>\n' +
        '\t\t\t<td style="word-wrap:break-word;text-align:center">工作单位</td>\n' +
        '\t\t\t<td colspan="2" style="text-align:center">{{workPlace}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td style="word-wrap:break-word;text-align:center">本人电话</td>\n' +
        '\t\t\t<td width="143" colspan="1" style="text-align:center">{{tphoneNum}}</td>\n' +
        '\t\t\t<td width="102" style="word-wrap:break-word;text-align:center">联系人电话</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:center">{{phonerName}}</td>\n' +
        '\t\t\t<td width="57" style="word-wrap:break-word;text-align:center">联系人姓名</td>\n' +
        '\t\t\t<td width="133" colspan="1" style="text-align:center">{{phonerNum}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td style="word-wrap:break-word;text-align:center">常住类型</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:center">{{residencePlace}}</td>\n' +
        '\t\t\t<td style="word-wrap:break-word;text-align:center">民族</td>\n' +
        '\t\t\t<td colspan="3" style="text-align:center">{{ethnic}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t\n' +
        '\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td style="word-wrap:break-word;text-align:center">血型</td>\n' +
        '\t\t\t<td colspan="5" style="text-align:center">{{bloodType}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td style="word-wrap:break-word;text-align:center">文化程度</td>\n' +
        '\t\t\t<td colspan="5" style="text-align:center">{{educational}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td style="word-wrap:break-word;text-align:center">职业</td>\n' +
        '\t\t\t<td colspan="5" style="text-align:left">{{occupation}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td style="word-wrap:break-word;text-align:center">婚姻状况</td>\n' +
        '\t\t\t<td colspan="5" style="text-align:left">{{maritalStatus}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td style="word-wrap:break-word;text-align:center">医疗费支付方式</td>\n' +
        '\t\t\t<td colspan="5" style="text-align:left">{{expensesPay}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td style="word-wrap:break-word;text-align:center">药物过敏史</td>\n' +
        '\t\t\t<td colspan="5" style="text-align:left">{{drugAllergyHistory}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td style="word-wrap:break-word;text-align:center">暴露史</td>\n' +
        '\t\t\t<td colspan="5" style="text-align:left">{{exposureHistory}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td rowspan="4" style="word-wrap:break-word;text-align:center">既往史</td>\n' +
        '\t\t\t<td colspan="2" style="text-align:left">疾病</td>\n' +
        '\t\t\t<td colspan="3" style="text-align:left">{{pastHistory}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">手术{{operation}}</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">名称：{{operation1}}</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">时间：{{oTime1}}</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">名称：{{operation2}}</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">时间：{{oTime2}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">外伤{{trauma}}</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">名称：{{trauma1}}</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">时间：{{tTime1}}</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">名称：{{trauma2}}</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">时间：{{tTime2}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">输血</td>\n' +
        '\t\t\t<td colspan="2" style="text-align:left">原因：{{transfusion}}</td>\n' +
        '\t\t\t<td colspan="2" style="text-align:left">时间：{{transfusionTime}}</td>\n' +

        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td rowspan="3" style="word-wrap:break-word;text-align:center">家族史</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">父亲：</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">{{fatherHistory}}</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">母亲：</td>\n' +
        '\t\t\t<td colspan="2" style="text-align:left">{{matherHistory}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">兄弟姐妹：</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">{{bortherHistory}}</td>\n' +
        '\t\t\t<td colspan="1" style="text-align:left">子女：</td>\n' +
        '\t\t\t<td colspan="2" style="text-align:left">{{sonHistory}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td colspan="5" style="text-align:left">&nbsp;</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td style="word-wrap:break-word;text-align:center">遗传病史</td>\n' +
        '\t\t\t<td colspan="5" style="text-align:left">{{geneticHistory}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td style="word-wrap:break-word;text-align:center">残疾情况</td>\n' +
        '\t\t\t<td colspan="5" style="text-align:left">{{disability}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td rowspan="5" style="word-wrap:break-word;text-align:center">生活环境</td>\n' +
        '\t\t\t<td colspan="2" style="text-align:left">厨房排风设施</td>\n' +
        '\t\t\t<td colspan="3" style="text-align:left">{{kitchenExhaust}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td colspan="2" style="text-align:left">燃料类型</td>\n' +
        '\t\t\t<td colspan="3" style="text-align:left">{{fuel}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td colspan="2" style="text-align:left">饮水</td>\n' +
        '\t\t\t<td colspan="3" style="text-align:left">{{water}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td colspan="2" style="text-align:left">厕所</td>\n' +
        '\t\t\t<td colspan="3" style="text-align:left">{{toilet}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t<tr>\n' +
        '\t\t\t<td colspan="2" style="text-align:left">禽畜栏</td>\n' +
        '\t\t\t<td colspan="3" style="text-align:left">{{livestockBar}}</td>\n' +
        '\t\t</tr>\n' +
        '\t\t</tbody>\n' +
        '\t</table>\n' +
        '</div>\n' +
        '</body>\n' +
        '</html>\n'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{fileId}}/, this.editForm.id ? this.editForm.id : '')
        str = str.replace(/{{fileStatus}}/, this.editForm.fileStatus ? this.editForm.fileStatus : '')
        str = str.replace(/{{sex}}/, this.editForm.sex ? this.editForm.sex : '')
        str = str.replace(/{{birth}}/, this.editForm.birth ? this.editForm.birth : '')
        str = str.replace(/{{belongComm}}/, this.editForm.belongComm ? this.editForm.belongComm : '')
        str = str.replace(/{{docuType}}/, this.editForm.docuType ? this.editForm.docuType : '')
        str = str.replace(/{{peopleId}}/, this.editForm.peopleId ? this.editForm.peopleId : '')
        str = str.replace(/{{workPlace}}/, this.editForm.workPlace ? this.editForm.workPlace : '')
        str = str.replace(/{{tphoneNum}}/, this.editForm.tphoneNum ? this.editForm.tphoneNum : '')
        str = str.replace(/{{phonerName}}/, this.editForm.phonerName ? this.editForm.phonerName : '')
        str = str.replace(/{{phonerNum}}/, this.editForm.phonerNum ? this.editForm.phonerNum : '')
        str = str.replace(/{{local}}/, this.editForm.local ? this.editForm.local : '')
        str = str.replace(/{{residencePlace}}/, this.editForm.residencePlace ? this.editForm.residencePlace : '')
        str = str.replace(/{{ethnic}}/, this.editForm.ethnic ? this.editForm.ethnic : '')
        str = str.replace(/{{localPlace}}/, this.editForm.localPlace ? this.editForm.localPlace : '')
        str = str.replace(/{{houseAddress}}/, this.editForm.houseAddress ? this.editForm.houseAddress : '')
        str = str.replace(/{{internalNum}}/, this.editForm.internalNum ? this.editForm.internalNum : '')
        str = str.replace(/{{hyperNum}}/, this.editForm.hyperNum ? this.editForm.hyperNum : '')
        str = str.replace(/{{diabetesNum}}/, this.editForm.diabetesNum ? this.editForm.diabetesNum : '')
        str = str.replace(/{{signNum}}/, this.editForm.signNum ? this.editForm.signNum : '')
        str = str.replace(/{{bloodType}}/, this.editForm.bloodType ? this.editForm.bloodType : '')
        str = str.replace(/{{educational}}/, this.editForm.educational ? this.editForm.educational : '')
        str = str.replace(/{{occupation}}/, this.editForm.occupation ? this.editForm.occupation : '')
        str = str.replace(/{{maritalStatus}}/, this.editForm.maritalStatus ? this.editForm.maritalStatus : '')
        str = str.replace(/{{expensesPay}}/, this.editForm.expensesPay ? this.editForm.expensesPay : '')
        str = str.replace(/{{drugAllergyHistory}}/, this.editForm.drugAllergyHistory ? this.editForm.drugAllergyHistory : '')
        str = str.replace(/{{exposureHistory}}/, this.editForm.exposureHistory ? this.editForm.exposureHistory : '')
        str = str.replace(/{{pastHistory}}/, this.editForm.pastHistory ? this.editForm.pastHistory : '')
        str = str.replace(/{{familyHistory}}/, this.editForm.familyHistory ? this.editForm.familyHistory : '')
        str = str.replace(/{{fatherHistory}}/, this.editForm.fatherHistory ? this.editForm.fatherHistory : '')
        str = str.replace(/{{matherHistory}}/, this.editForm.matherHistory ? this.editForm.matherHistory : '')
        str = str.replace(/{{bortherHistory}}/, this.editForm.bortherHistory ? this.editForm.bortherHistory : '')
        str = str.replace(/{{sonHistory}}/, this.editForm.sonHistory ? this.editForm.sonHistory : '')
        str = str.replace(/{{geneticHistory}}/, this.editForm.geneticHistory ? this.editForm.geneticHistory : '')
        str = str.replace(/{{disability}}/, this.editForm.disability ? this.editForm.disability : '')
        str = str.replace(/{{kitchenExhaust}}/, this.editForm.kitchenExhaust ? this.editForm.kitchenExhaust : '')
        str = str.replace(/{{fuel}}/, this.editForm.fuel ? this.editForm.fuel : '')
        str = str.replace(/{{water}}/, this.editForm.water ? this.editForm.water : '')
        str = str.replace(/{{toilet}}/, this.editForm.toilet ? this.editForm.toilet : '')
        str = str.replace(/{{livestockBar}}/, this.editForm.livestockBar ? this.editForm.livestockBar : '')
        str = str.replace(/{{operation}}/, this.editForm.operation ? this.editForm.operation : '')
        str = str.replace(/{{operation1}}/, this.editForm.operation1 ? this.editForm.operation1 : '')
        str = str.replace(/{{operation2}}/, this.editForm.operation2 ? this.editForm.operation2 : '')
        str = str.replace(/{{oTime1}}/, this.editForm.oTime1 ? this.editForm.oTime1 : '')
        str = str.replace(/{{oTime2}}/, this.editForm.oTime2 ? this.editForm.oTime2 : '')
        str = str.replace(/{{trauma}}/, this.editForm.trauma ? this.editForm.trauma : '')
        str = str.replace(/{{trauma1}}/, this.editForm.trauma1 ? this.editForm.trauma1 : '')
        str = str.replace(/{{trauma2}}/, this.editForm.trauma2 ? this.editForm.trauma2 : '')
        str = str.replace(/{{tTime1}}/, this.editForm.tTime1 ? this.editForm.tTime1 : '')
        str = str.replace(/{{tTime2}}/, this.editForm.tTime2 ? this.editForm.tTime2 : '')
        str = str.replace(/{{transfusion}}/, this.editForm.transfusion ? this.editForm.transfusion : '')
        str = str.replace(/{{transfusionTime}}/, this.editForm.transfusionTime ? this.editForm.transfusionTime : '')
        str = str.replace(/{{pregnant}}/, this.editForm.pregnant ? this.editForm.pregnant : '')
        myWindow.document.write(str)

        // myWindow.document.write(content + style)
        myWindow.focus()
        myWindow.document.close()
        myWindow.print()
        return myWindow
      },
      conprint() {
        const windows = this.printf(document.getElementById('printData').innerHTML)
        windows.close()
      },
      check() {
        this.$refs.editForm.validate(valid => {
          if (!valid) {
            this.$nextTick(function() {
              this.$el.querySelector('#houseAddress').scrollIntoView()
            })
          }
          if (valid) {
            const para = {
              page: 1,
              size: 200,
              filters: { name: '', peopleId: this.editForm.peopleId, cardNumber: '' }
            }
            getList(para).then(res => {
              if (res.detailModelList.length === 0) {
                this.createData()
              } else {
                this.$message.error('无法建立相同身份证的个人档案')
              }
            })
          }
        })
      },
      // 获取用户列表
      getUsers() {
        const para = {
          page: this.page,
          size: 20,
          filters: { name: this.filters.name, peopleId: this.filters.peopleId, cardNumber: this.filters.cardNumber }
        }
        getListPage(para).then(res => {
          this.total = res.totalCount
          this.users = res.detailModelList
          if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
            this.page = this.page - 1
            this.getUsers()
          }
        })
      },
      search() {
        this.page = 1
        this.getUsers()
      },
      // 删除
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          type: 'warning',
          confirmButtonText: ' 确定',
          cancelButtonText: ' 取消'
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
                  this.editForm.houseAddress1 = user[0].houseAddress
                  this.editForm.houseAddress1 = this.editForm.houseAddress.split('/')
                }
              })
            })
          }, 200)
        }
      },
      handleRowChange(row, event) {
        this.houseAddress = []
        this.exposureHistory_model = []
        this.drugAllergyHistory_model = []
        this.pastHistory_model = []
        this.fatherHistory_model = []
        this.matherHistory_model = []
        this.bortherHistory_model = []
        this.sonHistory_model = []
        this.disability_model = []
        this.kitchenExhaust_model = []
        this.fuel_model = []
        this.water_model = []
        this.toilet_model = []
        this.livestockBar_model = []
        this.dialogStatus = 'detail'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.exposureHistory_model = this.editForm.exposureHistory.split(',')
        this.drugAllergyHistory_model = this.editForm.drugAllergyHistory.split(',')
        this.pastHistory_model = this.editForm.pastHistory.split(',')
        this.fatherHistory_model = this.editForm.fatherHistory.split(',')
        this.matherHistory_model = this.editForm.matherHistory.split(',')
        this.bortherHistory_model = this.editForm.bortherHistory.split(',')
        this.sonHistory_model = this.editForm.sonHistory.split(',')
        this.disability_model = this.editForm.disability.split(',')
        this.kitchenExhaust_model = this.editForm.kitchenExhaust.split(',')
        this.fuel_model = this.editForm.fuel.split(',')
        this.water_model = this.editForm.water.split(',')
        this.toilet_model = this.editForm.toilet.split(',')
        this.livestockBar_model = this.editForm.livestockBar.split(',')
        this.editForm.houseAddress = this.editForm.houseAddress.split('/')
        this.edit = false
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.isAble = true
        this.exposureHistory_model = []
        this.drugAllergyHistory_model = []
        this.pastHistory_model = []
        this.fatherHistory_model = []
        this.matherHistory_model = []
        this.bortherHistory_model = []
        this.sonHistory_model = []
        this.disability_model = []
        this.kitchenExhaust_model = []
        this.fuel_model = []
        this.water_model = []
        this.toilet_model = []
        this.livestockBar_model = []
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.edit = true
        this.editForm = Object.assign({}, row)
        this.exposureHistory_model = this.editForm.exposureHistory.split(',')
        this.drugAllergyHistory_model = this.editForm.drugAllergyHistory.split(',')
        this.pastHistory_model = this.editForm.pastHistory.split(',')
        this.fatherHistory_model = this.editForm.fatherHistory.split(',')
        this.matherHistory_model = this.editForm.matherHistory.split(',')
        this.bortherHistory_model = this.editForm.bortherHistory.split(',')
        this.sonHistory_model = this.editForm.sonHistory.split(',')
        this.disability_model = this.editForm.disability.split(',')
        this.kitchenExhaust_model = this.editForm.kitchenExhaust.split(',')
        this.fuel_model = this.editForm.fuel.split(',')
        this.water_model = this.editForm.water.split(',')
        this.toilet_model = this.editForm.toilet.split(',')
        this.livestockBar_model = this.editForm.livestockBar.split(',')
        this.editForm.houseAddress = this.editForm.houseAddress.split('/')
      },
      // 显示新增界面
      handleAdd() {
        // this.editForm.houseAddress = []
        this.isAble = false
        this.houseAddress = []
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.exposureHistory_model = []
        this.drugAllergyHistory_model = []
        this.pastHistory_model = []
        this.fatherHistory_model = []
        this.matherHistory_model = []
        this.bortherHistory_model = []
        this.sonHistory_model = []
        this.disability_model = []
        this.kitchenExhaust_model = []
        this.fuel_model = []
        this.water_model = []
        this.toilet_model = []
        this.livestockBar_model = []
        this.editForm = {
          houseAddress: [],
          cardNumber: '',
          id: 0,
          fileId: 0,
          fileStatus: '',
          name: '',
          sex: '',
          birth: '',
          belongComm: '',
          docuType: '',
          peopleId: '',
          workPlace: '',
          tphoneNum: '',
          phonerName: '',
          phonerNum: '',
          local: '',
          residencePlace: '',
          ethnic: '',
          localPlace: '',
          houseAddress1: [],
          houseAddress2: [],
          migrationReason: '',
          internalNum: '',
          hyperNum: '',
          diabetesNum: '',
          signNum: '',
          bloodType: '',
          educational: '',
          occupation: '',
          maritalStatus: '',
          expensesPay: '',
          drugAllergyHistory: '',
          exposureHistory: '',
          pastHistory: '',
          familyHistory: '',
          fatherHistory: '',
          matherHistory: '',
          bortherHistory: '',
          sonHistory: '',
          geneticHistory: '',
          disability: '',
          kitchenExhaust: '',
          fuel: '',
          water: '',
          toilet: '',
          livestockBar: '',
          creator: this.$store.getters.name,
          createDate: null,
          modifier: '',
          modifyDate: null,
          operation: []
        }
      },
      // 显示新档案迁出
      handleMOVE() {
        this.houseAddress1 = []
        this.houseAddress2 = []
        this.migrationReason = ''
        this.dialogStatus = 'move'
        this.dialogFormVisibleMOVE = true
        this.editForm = {
          peopleId: '',
          houseAddress1: '',
          houseAddress2: '',
          migrationReason: ''
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
                para.modifier = this.$store.getters.name
                para.birth =
                  !para.birth || para.birth === ''
                    ? ''
                    : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd')

                para.oTime1 =
                  !para.oTime1 || para.oTime1 === ''
                    ? ''
                    : util.formatDate.format(new Date(para.oTime1), 'yyyy-MM-dd')

                para.oTime2 =
                  !para.oTime2 || para.oTime2 === ''
                    ? ''
                    : util.formatDate.format(new Date(para.oTime2), 'yyyy-MM-dd')

                para.tTime1 =
                  !para.tTime1 || para.tTime1 === ''
                    ? ''
                    : util.formatDate.format(new Date(para.tTime1), 'yyyy-MM-dd')

                para.tTime2 =
                  !para.tTime2 || para.tTime2 === ''
                    ? ''
                    : util.formatDate.format(new Date(para.tTime2), 'yyyy-MM-dd')
                para.transfusionTime =
                  !para.transfusionTime || para.transfusionTime === ''
                    ? ''
                    : util.formatDate.format(new Date(para.transfusionTime), 'yyyy-MM-dd')

                // 字典的数据处理
                // 残疾和环境
                para.disability = ''
                for (const itm of this.disability_model) {
                  if (itm !== '') {
                    para.disability += itm
                    para.disability += ','
                  }
                }
                para.disability = para.disability.substr(0, para.disability.length - 1)

                para.kitchenExhaust = ''
                for (const itm of this.kitchenExhaust_model) {
                  if (itm !== '') {
                    para.kitchenExhaust += itm
                    para.kitchenExhaust += ','
                  }
                }
                para.kitchenExhaust = para.kitchenExhaust.substr(0, para.kitchenExhaust.length - 1)

                para.fuel = ''
                for (const itm of this.fuel_model) {
                  if (itm !== '') {
                    para.fuel += itm
                    para.fuel += ','
                  }
                }
                para.fuel = para.fuel.substr(0, para.fuel.length - 1)

                para.water = ''
                for (const itm of this.water_model) {
                  if (itm !== '') {
                    para.water += itm
                    para.water += ','
                  }
                }
                para.water = para.water.substr(0, para.water.length - 1)

                para.toilet = ''
                for (const itm of this.toilet_model) {
                  if (itm !== '') {
                    para.toilet += itm
                    para.toilet += ','
                  }
                }
                para.toilet = para.toilet.substr(0, para.toilet.length - 1)

                para.livestockBar = ''
                for (const itm of this.livestockBar_model) {
                  if (itm !== '') {
                    para.livestockBar += itm
                    para.livestockBar += ','
                  }
                }
                para.livestockBar = para.livestockBar.substr(0, para.livestockBar.length - 1)
                // 家族病史
                para.fatherHistory = ''
                for (const itm of this.fatherHistory_model) {
                  if (itm !== '') {
                    para.fatherHistory += itm
                    para.fatherHistory += ','
                  }
                }
                para.fatherHistory = para.fatherHistory.substr(0, para.fatherHistory.length - 1)

                para.matherHistory = ''
                for (const itm of this.matherHistory_model) {
                  if (itm !== '') {
                    para.matherHistory += itm
                    para.matherHistory += ','
                  }
                }
                para.matherHistory = para.matherHistory.substr(0, para.matherHistory.length - 1)

                para.bortherHistory = ''
                for (const itm of this.bortherHistory_model) {
                  if (itm !== '') {
                    para.bortherHistory += itm
                    para.bortherHistory += ','
                  }
                }
                para.bortherHistory = para.bortherHistory.substr(0, para.bortherHistory.length - 1)

                para.sonHistory = ''
                for (const itm of this.sonHistory_model) {
                  if (itm !== '') {
                    para.sonHistory += itm
                    para.sonHistory += ','
                  }
                }
                para.sonHistory = para.sonHistory.substr(0, para.sonHistory.length - 1)

                // 既往病史
                para.pastHistory = ''
                for (const itm of this.pastHistory_model) {
                  if (itm !== '') {
                    para.pastHistory += itm
                    para.pastHistory += ','
                  }
                }
                para.pastHistory = para.pastHistory.substr(0, para.pastHistory.length - 1)
                // 过敏病史
                para.drugAllergyHistory = ''
                for (const itm of this.drugAllergyHistory_model) {
                  if (itm !== '') {
                    para.drugAllergyHistory += itm
                    para.drugAllergyHistory += ','
                  }
                }
                para.drugAllergyHistory = para.drugAllergyHistory.substr(0, para.drugAllergyHistory.length - 1)
                // 暴露病史
                para.exposureHistory = ''
                for (const itm of this.exposureHistory_model) {
                  if (itm !== '') {
                    para.exposureHistory += itm
                    para.exposureHistory += ','
                  }
                }
                para.exposureHistory = para.exposureHistory.substr(0, para.exposureHistory.length - 1)

                //
                para.operation = ''
                for (const itm of this.editForm.operation) {
                  if (itm !== '') {
                    para.operation += itm
                    para.operation += ','
                  }
                }
                para.operation = para.operation.substr(0, para.operation.length - 1)
                para.houseAddress = ''
                for (const itm of this.editForm.houseAddress) {
                  para.houseAddress += itm
                  para.houseAddress += '/'
                }
                para.houseAddress = para.houseAddress.substr(0, para.houseAddress.length - 1)

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
              confirmButtonText: ' 确定',
              cancelButtonText: ' 取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.creater = this.$store.getters.name
                para.birth =
                  !para.birth || para.birth === ''
                    ? ''
                    : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd')

                para.oTime1 =
                  !para.oTime1 || para.oTime1 === ''
                    ? ''
                    : util.formatDate.format(new Date(para.oTime1), 'yyyy-MM-dd')

                para.oTime2 =
                  !para.oTime2 || para.oTime2 === ''
                    ? ''
                    : util.formatDate.format(new Date(para.oTime2), 'yyyy-MM-dd')

                para.tTime1 =
                  !para.tTime1 || para.tTime1 === ''
                    ? ''
                    : util.formatDate.format(new Date(para.tTime1), 'yyyy-MM-dd')

                para.tTime2 =
                  !para.tTime2 || para.tTime2 === ''
                    ? ''
                    : util.formatDate.format(new Date(para.tTime2), 'yyyy-MM-dd')

                para.transfusionTime =
                  !para.transfusionTime || para.transfusionTime === ''
                    ? ''
                    : util.formatDate.format(new Date(para.transfusionTime), 'yyyy-MM-dd')
                // 字典的数据处理
                // 残疾和环境
                para.disability = ''
                for (const itm of this.disability_model) {
                  if (itm !== '') {
                    para.disability += itm
                    para.disability += ','
                  }
                }
                para.disability = para.disability.substr(0, para.disability.length - 1)

                para.kitchenExhaust = ''
                for (const itm of this.kitchenExhaust_model) {
                  if (itm !== '') {
                    para.kitchenExhaust += itm
                    para.kitchenExhaust += ','
                  }
                }
                para.kitchenExhaust = para.kitchenExhaust.substr(0, para.kitchenExhaust.length - 1)

                para.fuel = ''
                for (const itm of this.fuel_model) {
                  if (itm !== '') {
                    para.fuel += itm
                    para.fuel += ','
                  }
                }
                para.fuel = para.fuel.substr(0, para.fuel.length - 1)

                para.water = ''
                for (const itm of this.water_model) {
                  if (itm !== '') {
                    para.water += itm
                    para.water += ','
                  }
                }
                para.water = para.water.substr(0, para.water.length - 1)

                para.toilet = ''
                for (const itm of this.toilet_model) {
                  if (itm !== '') {
                    para.toilet += itm
                    para.toilet += ','
                  }
                }
                para.toilet = para.toilet.substr(0, para.toilet.length - 1)

                para.livestockBar = ''
                for (const itm of this.livestockBar_model) {
                  if (itm !== '') {
                    para.livestockBar += itm
                    para.livestockBar += ','
                  }
                }
                para.livestockBar = para.livestockBar.substr(0, para.livestockBar.length - 1)
                // 家族病史
                para.fatherHistory = ''
                for (const itm of this.fatherHistory_model) {
                  if (itm !== '') {
                    para.fatherHistory += itm
                    para.fatherHistory += ','
                  }
                }
                para.fatherHistory = para.fatherHistory.substr(0, para.fatherHistory.length - 1)

                para.matherHistory = ''
                for (const itm of this.matherHistory_model) {
                  if (itm !== '') {
                    para.matherHistory += itm
                    para.matherHistory += ','
                  }
                }
                para.matherHistory = para.matherHistory.substr(0, para.matherHistory.length - 1)

                para.bortherHistory = ''
                for (const itm of this.bortherHistory_model) {
                  if (itm !== '') {
                    para.bortherHistory += itm
                    para.bortherHistory += ','
                  }
                }
                para.bortherHistory = para.bortherHistory.substr(0, para.bortherHistory.length - 1)

                para.sonHistory = ''
                for (const itm of this.sonHistory_model) {
                  if (itm !== '') {
                    para.sonHistory += itm
                    para.sonHistory += ','
                  }
                }
                para.sonHistory = para.sonHistory.substr(0, para.sonHistory.length - 1)

                // 既往病史
                para.pastHistory = ''
                for (const itm of this.pastHistory_model) {
                  if (itm !== '') {
                    para.pastHistory += itm
                    para.pastHistory += ','
                  }
                }
                para.pastHistory = para.pastHistory.substr(0, para.pastHistory.length - 1)
                // 过敏病史
                para.drugAllergyHistory = ''
                for (const itm of this.drugAllergyHistory_model) {
                  if (itm !== '') {
                    para.drugAllergyHistory += itm
                    para.drugAllergyHistory += ','
                  }
                }
                para.drugAllergyHistory = para.drugAllergyHistory.substr(0, para.drugAllergyHistory.length - 1)
                // 暴露病史
                para.exposureHistory = ''
                for (const itm of this.exposureHistory_model) {
                  if (itm !== '') {
                    para.exposureHistory += itm
                    para.exposureHistory += ','
                  }
                }
                para.exposureHistory = para.exposureHistory.substr(0, para.exposureHistory.length - 1)

                //
                para.operation = ''
                for (const itm of this.editForm.operation) {
                  if (itm !== '') {
                    para.operation += itm
                    para.operation += ','
                  }
                }
                para.operation = para.operation.substr(0, para.operation.length - 1)
                para.houseAddress = ''
                for (const itm of this.editForm.houseAddress) {
                  para.houseAddress += itm
                  para.houseAddress += '/'
                }
                para.houseAddress = para.houseAddress.substr(0, para.houseAddress.length - 1)
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
      // 新增迁出档案
      FileEmigration: function(iit) {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: ' 确定',
              cancelButtonText: ' 取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                console.log(para)

                para.houseAddress2 = ''
                for (const itm of this.houseAddress2) {
                  para.houseAddress2 += itm
                  para.houseAddress2 += '/'
                }
                para.houseAddress2 = para.houseAddress2.substr(0, para.houseAddress2.length - 1)
                addData0(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisibleMOVE = false
                })
              })
              .catch(e => {
                // 打印一下错误
                console.log(e)
              })
          }
        })
      },
      check2() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            const para = {
              page: 1,
              size: 200,
              filters: { name: '', peopleId: this.editForm.peopleId, cardNumber: '' }
            }
            getList(para).then(res => {
              if (res.detailModelList.length !== 0) {
                this.FileEmigration()
              } else {
                this.$message.error('请先建立个人档案')
              }
            })
          }
        })
      },
      // 全选单选多选
      selsChange(sels) {
        this.sels = sels
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
