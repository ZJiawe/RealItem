<template>
  <div class="pos app-container">
    <div>
      <!--随访记录列表-->
      <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible1" :close-on-click-modal="false" center>
        <el-table :data="nowFollow" :height="tableHeight" highlight-current-row border style="width: 100%;margin-top:-20px;">
          <el-table-column type="index" label="序号" width="70" align="center">
          </el-table-column>
          <el-table-column prop="personalId" label="编号" width="318" align="center">
          </el-table-column>
          <el-table-column prop="name" label="姓名" width="150" align="center">
          </el-table-column>
          <el-table-column prop="phone" label="联系电话" width="180" align="center">
          </el-table-column>
          <el-table-column prop="lastFollow" label="最后一次随访时间" width="255" align="center">
          </el-table-column>
        </el-table>
        <el-col :span="24" class="toolbar">
          <el-pagination layout="prev, pager, next" :page-size="20" :current-page.sync="nowFollowPage" @current-change="handleCurrentChange1" :total="nowFollowTotal" style="float:right;">
          </el-pagination>
        </el-col>
      </el-dialog>

      <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
        <el-table :data="weekFollow" :height="tableHeight" highlight-current-row border style="width: 100%;margin-top:-20px;">
          <el-table-column type="index" label="序号" width="70" align="center">
          </el-table-column>
          <el-table-column prop="personalId" label="编号" width="318" align="center">
          </el-table-column>
          <el-table-column prop="name" label="姓名" width="150" align="center">
          </el-table-column>
          <el-table-column prop="phone" label="联系电话" width="180" align="center">
          </el-table-column>
          <el-table-column prop="lastFollow" label="最后一次随访时间" width="255" align="center">
          </el-table-column>
        </el-table>
        <el-col :span="24" class="toolbar">
          <el-pagination layout="prev, pager, next" :page-size="20" :current-page.sync="weekFollowPage" @current-change="handleCurrentChange2" :total="weekFollowTotal" style="float:right;">
          </el-pagination>
        </el-col>
      </el-dialog>

      <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible3" :close-on-click-modal="false" center>
        <el-table :data="notFollow" :height="tableHeight" highlight-current-row border style="width: 100%;margin-top:-20px;">
          <el-table-column type="index" label="序号" width="70" align="center">
          </el-table-column>
          <el-table-column prop="personalId" label="编号" width="318" align="center">
          </el-table-column>
          <el-table-column prop="name" label="姓名" width="150" align="center">
          </el-table-column>
          <el-table-column prop="phone" label="联系电话" width="180" align="center">
          </el-table-column>
          <el-table-column prop="lastFollow" label="最后一次随访时间" width="255" align="center">
          </el-table-column>
        </el-table>
        <el-col :span="24" class="toolbar">
          <el-pagination layout="prev, pager, next" :page-size="20" :current-page.sync="notFollowPage" @current-change="handleCurrentChange3" :total="notFollowTotal" style="float:right;">
          </el-pagination>
        </el-col>
      </el-dialog>

      <el-row style="margin-top: -8px">
        <el-col :span="14" class="toolbar" style="padding-bottom: 0px;">
          <el-form :inline="true" :model="filters" @submit.native.prevent >
            <el-form-item prop="id" label="身份证号">
              <el-col :span="4">
                <el-input style="width: 300px" v-model="filters.searchId" placeholder="请输入身份号或读卡号" clearable></el-input>
              </el-col>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" style="width:90px;background-color:#17b3ec;border-color:transparent" icon="el-icon-search" v-on:click="search">查询</el-button>
            </el-form-item>
            <el-form-item>
              <!-- <el-button type="primary" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">保存</el-button>-->
              <!--              <el-button type="primary" @click="updateData" icon="el-icon-circle-plus-outline">修改</el-button>-->
            </el-form-item>
            <!--<el-form-item>
              &lt;!&ndash;<el-button type="primary" style="background-color:#4db2ee;border-color:transparent"
                         icon="el-icon-search">新增保存
              </el-button>&ndash;&gt;
              <el-button type="primary" @click="createData" icon="el-icon-circle-plus-outline">新增保存</el-button>
            </el-form-item>-->
          </el-form>
        </el-col>
        <el-col :span="10" class="toolbar" style="padding-bottom: 0px;" align="right">
          <el-button @click="follow1" style="width: 160px;height:40px">今日随访人数：{{nowFollowTotal}}</el-button>
          <el-button @click="follow2" style="margin-left:20px;width: 160px;height:40px">7日内随访人数：{{weekFollowTotal}}</el-button>
          <el-button @click="follow3" style="margin-left:20px;width: 160px;height:40px">未随访人数：{{notFollowTotal}}</el-button>
        </el-col>
      </el-row>
      <el-row style="margin-top: -8px">
        <!--商品展示-->
        <el-col :span="16">
          <EasyScrollbar>
            <div class="item" v-bind:style="{ height: tableHeight + 'px'}">
              <!--档案封面-->
              <el-form :model="user" label-width="0px" ref="user">
                <div class="bg" style="margin-top: -30px;margin-left: auto;margin-right:auto;width: 800px;height: 760px;" v-if="coverShow">
                  <h2 align="center" style="padding-top:120px;letter-spacing:10px;font-size: 40px;color:#333333;font-weight: bold">居民健康档案</h2>
                  <table align="center" style="font-size:16px;margin-left: 90px;margin-top: 40px">
                    <tr><td class="td2">编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</td><td class="td2" style="width:400px;"><el-input readonly v-model="user.id" class="ad" style= "background-color:transparent;width:100%"></el-input></td></tr>
                    <tr><td class="td2">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</td><td class="td2"><el-input readonly v-model="user.name" class="ad" style= "background-color:transparent;width:100%"></el-input></td></tr>
                    <tr><td class="td2">现&nbsp;&nbsp;住&nbsp;&nbsp;址</td><td class="td2"><el-input readonly v-model="user.local" class="ad" style= "background-color:transparent;width:100%"></el-input></td></tr>
                    <tr><td class="td2">户籍住址</td><td class="td2"><el-input  readonly v-model="user.houseAddress" class="ad" style= "background-color:transparent;width:100%"></el-input></td></tr>
                    <tr><td class="td2">联系电话</td><td class="td2"><el-input  readonly v-model="user.phonerNum" class="ad" style= "background-color:transparent;width:100%"></el-input></td></tr>
                    <tr><td class="td2">乡镇(街道)名称</td><td class="td2"><el-input readonly v-model="user.villageName" class="ad" style= "background-color:transparent;width:100%"></el-input></td></tr>
                    <tr><td class="td2">村(居)委会名称</td><td class="td2"><el-input readonly v-model="user.committees" class="ad" style= "background-color:transparent;width:100%"></el-input></td></tr>
                  </table>
                  <table align="center"  style="font-size:16px;margin-left: 60px">
                    <tr>
                      <td class="td2">建&nbsp;&nbsp;档&nbsp;&nbsp;人</td><td class="td2" style="width: 240px;"><el-input readonly v-model="user.creator" class="ad" style= "background-color:transparent;width:100%"></el-input></td>
                      <td class="td2" style="padding-left: 40px">建档单位</td><td class="td2" style="width: 240px;"><el-input readonly v-model="user.committees" class="ad" style= "background-color:transparent;width:100%"></el-input></td>
                    </tr>
                    <tr>
                      <td class="td2">责任医生</td><td class="td2"><el-input readonly v-model="user.responDoctor" class="ad" style= "background-color:transparent;width:100%"></el-input></td>
                      <td class="td2">建档日期</td><td class="td2"><el-input readonly v-model="user.createDate" class="ad" style= "background-color:transparent;width:100%"></el-input></td>
                    </tr>
                  </table>
                </div>
              </el-form>
              <el-form :model="editForm" label-width="0px" :rules="editFormRules" ref="editForm">
                <!--个人档案-->
                <div id="Perinfor" v-if="perinforShow">
                  <h2 align="center">个人基本档案</h2>
                  <table  border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width:100%">
                    <tbody>
                    <tr>
                      <td width="140px"><span>*</span>姓名</td>
                      <td colspan="2" class="pad10" >
                        <el-input v-model="editForm.name"></el-input>
                      </td>
                      <td width="140px">性别</td>
                      <td colspan="2" class="pad10">
                        <el-radio-group v-model="editForm.sex">
                          <el-radio label="男">男</el-radio>
                          <el-radio label="女">女</el-radio>
                          <el-radio label="未知性别">未知性别</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" class="pad" scope="row"><span>*</span>出生日期</td>
                      <td colspan="2" class="pad10">
                        <el-date-picker  style="width: 100%" type="date" placeholder="选择日期" :picker-options="pickerOptions0" v-model.number="editForm.birth" id="birth"></el-date-picker>
                      </td>
                      <td width="140px" class="pad" scope="row"><span>*</span>身份证号</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.peopleId" style="width: 100%"id="peopleId"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">工作单位</td>
                      <td colspan="2"class="pad10">
                        <el-input  v-model="editForm.workPlace" style="width: 100%"></el-input>
                      </td>
                      <td width="140px" scope="row">本人电话</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.tphoneNum" style="width: 100%"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">联系人姓名</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.phonerName" auto-complete="off"></el-input>
                      </td>
                      <td width="140px" scope="row">联系人电话</td>
                      <td colspan="2"class="pad10">
                        <el-input  v-model="editForm.phonerNum" auto-complete="off" ></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" class="pad" scope="row"><span>*</span>存活状态</td>
                      <td colspan="2"class="pad10" >
                        <el-form-item label=""label-width="0px" prop="livestate">
                          <el-radio-group v-model="editForm.livestate" id="livestate">
                            <el-radio label="死亡">死亡</el-radio>
                            <el-radio label="存活">存活</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                      <td width="140px" class="pad" scope="row"><span>*</span>孕妇</td>
                      <td colspan="2"class="pad10">
                        <el-radio-group v-model="editForm.pregnant" id="pregnant">
                          <el-radio label="是">是</el-radio>
                          <el-radio label="否">否</el-radio>
                        </el-radio-group>
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
                      <td width="140px" class="pad"><span>*</span>户籍地址</td>
                      <td colspan="2" class="pad10">
                        <el-cascader :options="options" v-model="houseAddress_model" style="width: 100%;" placeholder="请选择" change-on-select></el-cascader>
                      </td>
                      <td width="140px" scope="row">卡号</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.cardNumber" auto-complete="off"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">现住地址</td>
                      <td colspan="4"class="pad10">
                        <el-input v-model="editForm.local" auto-complete="off"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">村（居）委会名称</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.committees" auto-complete="off"></el-input>
                      </td>
                      <td width="140px" scope="row">乡镇名称</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.villageName" auto-complete="off"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">责任医生</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.responDoctor" auto-complete="off"></el-input>
                      </td>
                      <td width="140px" scope="row">建档单位</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.inputtingUnit" auto-complete="off"></el-input>
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
                <!--个人体检-->
                <div v-if="medicalShow">
                  <h2 align="center">个人体检档案</h2>
                  <table border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width:100%">
                    <tbody>
                    <tr>
                      <td width="140px" scope="row">体检日期</td>
                      <td colspan="3"class="pad10">
                        <el-date-picker style="width: 100%" type="date" placeholder="选择日期" v-model="editForm.checkDate"></el-date-picker>
                      </td>
                      <td  width="140px" colspan="2" scope="row" class="pad"><span>*</span>体检医生</td>
                      <td colspan="4"class="pad10">
                        <el-input v-model="editForm.doctor" auto-complete="off"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row" class="pad"><span>*</span>姓名</td>
                      <td colspan="3"class="pad10">
                        <el-input v-model="editForm.name" auto-complete="off"></el-input>
                      </td>
                      <td  width="140px" colspan="2" scope="row" class="pad"><span>*</span>身份证号</td>
                      <td colspan="4"class="pad10">
                        <el-input  v-model="editForm.peopleId" auto-complete="off"  v-on:input="inputFunc1"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td  width="140px" scope="row">内容</td>
                      <td colspan="9">检查项目</td>
                    </tr>

                    <tr>
                      <td  width="140px" scope="row">症状</td>
                      <td colspan="9"style="text-align:left" class="pad20">
                        <el-checkbox-group v-model="editForm.symptoms" style="width:100%">
                          <table>
                            <tr>
                              <td style="width: 16%;" ><el-checkbox label="无症状" name="type"></el-checkbox></td>
                              <td style="width: 16%;"><el-checkbox label="头痛" name="type"></el-checkbox></td>
                              <td style="width: 16%;" ><el-checkbox label="头晕" name="type"></el-checkbox></td>
                              <td style="width: 16%;" ><el-checkbox label="心悸" name="type"></el-checkbox></td>
                              <td style="width: 16%;" ><el-checkbox label="胸闷" name="type"></el-checkbox></td>
                              <td style="width: 16%;" ><el-checkbox label="胸痛" name="type"></el-checkbox></td>

                              <td style="width: 16%;" ><el-checkbox label="慢性咳嗽" name="type"></el-checkbox></td>
                            </tr>
                            <tr>
                              <td style="width: 16%;" ><el-checkbox label="咳痰" name="type"></el-checkbox></td>
                              <td style="width: 16%;"><el-checkbox label="呼吸困难" name="type"></el-checkbox></td>
                              <td style="width: 16%;" ><el-checkbox label="多饮" name="type"></el-checkbox></td>
                              <td style="width: 16%;"><el-checkbox label="多尿" name="type"></el-checkbox></td>
                              <td style="width: 16%;" ><el-checkbox label="体重下降" name="type"></el-checkbox></td>

                              <td style="width: 16%;" ><el-checkbox label="乏力" name="type"></el-checkbox></td>

                              <td style="width: 16%;" ><el-checkbox label="关节肿痛" name="type"></el-checkbox></td>
                            </tr>
                            <tr>
                              <td style="width: 16%;" ><el-checkbox label="视力模糊" name="type"></el-checkbox></td>
                              <td style="width: 16%;" ><el-checkbox label="手脚麻木" name="type"></el-checkbox></td>
                              <td style="width: 16%;" ><el-checkbox label="尿急" name="type"></el-checkbox></td>
                              <td style="width: 16%;" ><el-checkbox label="尿痛" name="type"></el-checkbox></td>

                              <td style="width: 16%;" ><el-checkbox label="便秘" name="type"></el-checkbox></td>
                              <td style="width: 16%;" ><el-checkbox label="腹泻" name="type"></el-checkbox></td>

                              <td style="width: 16%;" ><el-checkbox label="眼花" name="type"></el-checkbox></td>
                            </tr>
                            <tr>
                              <td style="width: 16%;" ><el-checkbox label="耳鸣" name="type"></el-checkbox></td>
                              <td style="width: 16%;" ><el-checkbox label="恶心呕吐" name="type"></el-checkbox></td>
                              <td style="width: 16%;" ><el-checkbox label="乳房胀痛" name="type"></el-checkbox></td>

                              <td style="width: 16%;"><el-checkbox label="其他" name="type"></el-checkbox></td>
                            </tr>
                            <tr>
                            </tr>
                          </table>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td  width="140px" rowspan="9" scope="row">一般情况</td>
                      <td width="97">体温</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.temperature">
                          <template slot="append">℃</template>
                        </el-input>
                      </td>
                      <td colspan="2">脉率</td>
                      <td colspan="4"class="pad10">
                        <el-input v-model="editForm.pulseRate" >
                          <template slot="append">次/分钟</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="2">呼吸频率</td>
                      <td colspan="2" rowspan="2"class="pad10">
                        <el-input v-model="editForm.breathing" >
                          <template slot="append">次/分钟</template>
                        </el-input>
                      </td>
                      <td colspan="2" rowspan="2" scope="row">血压</td>
                      <td colspan="2">左侧</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.pressureleft" auto-complete="off">
                          <template slot="append">mmhg</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">右侧</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.pressureright" auto-complete="off">
                          <template slot="append">mmhg</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td>身高</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.stature" >
                          <template slot="append">cm</template>
                        </el-input>
                      </td>
                      <td colspan="2" scope="row">体重</td>
                      <td colspan="4"class="pad10">
                        <el-input v-model="editForm.weight" >
                          <template slot="append">kg</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td>腰围</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.waistline" auto-complete="off">
                          <template slot="append">cm</template>
                        </el-input>
                      </td>
                      <td colspan="2" scope="row">体质指数(BMI)</td>
                      <td colspan="4"class="pad10">
                        <el-input v-model="editForm.bodymassindex" auto-complete="off">
                          <template slot="append">kg/㎡</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="3">老年人健康状态自我评估</td>
                      <td colspan="6" scope="row"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.selfAssessment">
                          <el-radio label="满意">满意</el-radio>
                          <el-radio label="基本满意">基本满意</el-radio>
                          <el-radio label="说不清楚">说不清楚</el-radio>
                          <el-radio label="不太满意">不太满意</el-radio>
                          <el-radio label="不满意">不满意</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="3">老年人生活自理能力自我评估</td>
                      <td colspan="6" scope="row"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.selfCare">
                          <table>
                            <tr>
                              <td ><el-radio label="可自理">可自理</el-radio></td>
                              <td ><el-radio label="轻度依赖">轻度依赖</el-radio></td>
                              <td ><el-radio label="中度依赖">中度依赖</el-radio></td>
                              <td ><el-radio label="不能自理">不能自理</el-radio></td>
                            </tr>
                          </table>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="3">老年人认知功能</td>
                      <td colspan="6" scope="row"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.cognition">
                          <el-radio label="粗筛阴性">粗筛阴性</el-radio>
                          <el-radio label="粗筛阳性">粗筛阳性</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="3">老年人情感状态</td>
                      <td colspan="6" scope="row"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.mood">
                          <el-radio label="粗筛阴性">粗筛阴性</el-radio>
                          <el-radio label="粗筛阳性">粗筛阳性</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td  width="140px" rowspan="19" scope="row">生活方式</td>
                      <td rowspan="3">体育锻炼</td>
                      <td colspan="2">锻炼频率</td>
                      <td colspan="6"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.exercise">
                          <el-radio label="每天">每天</el-radio>
                          <el-radio label="每周一次" >每周一次</el-radio>
                          <el-radio label="偶尔">偶尔</el-radio>
                          <el-radio label="不锻炼">不锻炼</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">每次锻炼时间</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.exerciseTime" >
                          <template slot="append">分钟</template>
                        </el-input>
                      </td>
                      <td colspan="2">坚持锻炼时间</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.stickTime" >
                          <template slot="append">年</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">锻炼方式</td>
                      <td colspan="6"class="pad10">
                        <el-input v-model="editForm.exercisestyle" ></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td>饮食习惯</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-checkbox-group v-model="editForm.diet" style="width:100%">
                          <el-checkbox v-for="item in diet" :key="item.value" :name="type" :label="item.label" :value="item.value">
                          </el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="3">吸烟情况</td>
                      <td colspan="2">吸烟状况</td>
                      <td colspan="6"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.smoking">
                          <el-radio label="从不吸烟">从不吸烟</el-radio>
                          <el-radio label="已戒烟">已戒烟</el-radio>
                        </el-radio-group>
                        <el-radio-group v-model="editForm.smoking">
                          <el-radio label="吸烟">吸烟</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">日吸烟量</td>
                      <td colspan="6"class="pad10">
                        <el-input v-model="editForm.everySmoking">
                          <template slot="append">支</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">开始吸烟年龄</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.smokingAge" > <template slot="append">岁</template></el-input>
                      </td>
                      <td colspan="2">戒烟年龄</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.noSmokingAge" > <template slot="append">岁</template></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="5">饮酒情况</td>
                      <td colspan="2">饮酒频率</td>
                      <td colspan="6"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.drinking">
                          <el-radio label="从不">从不</el-radio>
                          <el-radio label="偶尔">偶尔</el-radio>
                        </el-radio-group>
                        <el-radio-group v-model="editForm.drinking">
                          <el-radio label="经常">经常</el-radio>
                          <el-radio label="每天">每天</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">日饮酒量</td>
                      <td colspan="6"class="pad10">
                        <el-input v-model="editForm.everydrinking" >
                          <template slot="append">瓶</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">是否戒酒</td>
                      <td colspan="6"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.nodrinking">
                          <el-radio label="未戒酒">未戒酒</el-radio>
                          <el-radio label="已戒酒">已戒酒</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">开始饮酒年龄</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.drinkingAge" > <template slot="append">岁</template></el-input>
                      </td>
                      <td colspan="2">近一年内是否醉酒</td>
                      <td colspan="2"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.drunk">
                          <el-radio label="是">是</el-radio>
                          <el-radio label="否">否</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">饮酒种类</td>
                      <td colspan="6"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.drinkingTypes">
                          <el-radio label="白酒">白酒</el-radio>
                          <el-radio label="啤酒">啤酒</el-radio>
                          <el-radio label="红酒">红酒</el-radio>
                          <el-radio label="黄酒">黄酒</el-radio>
                          <el-radio label="其他">其他</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="7">职业病危害因素接触史</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.workDiseases">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">工种</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.workType" ></el-input>
                      </td>
                      <td colspan="2">工作时间</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.workingTime" > <template slot="append">年</template></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">粉尘</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.dust" ></el-input>
                      </td>
                      <td colspan="2">防护措施</td>
                      <td colspan="2"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.dustproMeasures">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">放射物质</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.fanshe" ></el-input>
                      </td>
                      <td colspan="2">防护措施</td>
                      <td colspan="2"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.fansheproMeasures">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">物理因素</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.physicalfactors" ></el-input>
                      </td>
                      <td colspan="2">防护措施</td>
                      <td colspan="2"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.phyproMeasures">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">化学物质</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.chemicals"></el-input>
                      </td>
                      <td colspan="2">防护措施</td>
                      <td colspan="2"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.cheproMeasures">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">其他</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.other1" ></el-input>
                      </td>
                      <td colspan="2">防护措施</td>
                      <td colspan="2"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.other1ProMeasures">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td  width="140px"  rowspan="4" scope="row">脏器功能</td>
                      <td>口腔</td>
                      <td colspan="8"class="pad10">
                        <el-input v-model="editForm.oralCavity" ></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td>视力</td>
                      <td >左眼</td>
                      <td width="74"class="pad10">
                        <el-input v-model="editForm.visionz" ></el-input>
                      </td>
                      <td scope="row" style="width:66px">右眼</td>
                      <td width="74" scope="row"class="pad10">
                        <el-input v-model="editForm.visiony" ></el-input>
                      </td>
                      <td >矫正视力左眼</td>
                      <td width="70"class="pad10">
                        <el-input v-model="editForm.vicorrectz" ></el-input>
                      </td>
                      <td >矫正视力右眼</td>
                      <td width="80"class="pad10">
                        <el-input v-model="editForm.vicorrecty" ></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td>听力</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.listen">
                          <el-radio label="听见">听见</el-radio>
                          <el-radio label="听不清或无法听见">听不清或无法听见</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td>运动功能</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.sport">
                          <el-radio label="可顺利完成">可顺利完成</el-radio>
                          <el-radio label="无法独立完成其中任何一动作">无法独立完成其中任何一动作</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td  width="140px" rowspan="25" scope="row">查体</td>
                      <td>眼底</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.bottomEye">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td>皮肤</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.skin">
                            <el-radio label="正常">正常</el-radio>
                            <el-radio label="潮红">潮红</el-radio>
                            <el-radio label="苍白">苍白</el-radio>
                            <el-radio label="发绀">发绀</el-radio>
                            <el-radio label="黄染">黄染</el-radio>
                            <el-radio label="色素沉着">色素沉着</el-radio>
                            <el-radio label="其他">其他</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>巩膜</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.sclera">
                            <el-radio label="正常">正常</el-radio>
                            <el-radio label="黄染">黄染</el-radio>
                            <el-radio label="充血">充血</el-radio>
                            <el-radio label="其他">其他</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>淋巴结</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.lymphNode">
                            <el-radio label="未触及">未触及</el-radio>
                            <el-radio label="锁骨上">锁骨上</el-radio>
                            <el-radio label="腋窝">腋窝</el-radio>
                            <el-radio label="其他">其他</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="3">肺</td>
                      <td colspan="1">桶状胸</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.barrelchest">
                            <el-radio label="是">是</el-radio>
                            <el-radio label="否">否</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>

                    </tr>
                    <tr>
                      <td colspan="1">呼吸音</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.breathSounds">
                            <el-radio label="正常">正常</el-radio>
                            <el-radio label="异常">异常</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1">罗音</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.rale">
                            <el-radio label="无">无</el-radio>
                            <el-radio label="千罗音">千罗音</el-radio>
                            <el-radio label="湿罗音">湿罗音</el-radio>
                            <el-radio label="其他">其他</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="3">心脏</td>
                      <td colspan="1">心率</td>
                      <td colspan="7"class="pad10">
                        <el-input v-model="editForm.heartrate" auto-complete="off">
                          <template slot="append">次/分钟</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1">心律</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.hythmheart">
                            <el-radio label="齐">齐</el-radio>
                            <el-radio label="不齐">不齐</el-radio>
                            <el-radio label="绝对不齐">绝对不齐</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1">杂音</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.noise">
                            <el-radio label="无">无</el-radio>
                            <el-radio label="有">有</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="5">腹部</td>
                      <td colspan="1">压痛</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.tenderness">
                            <el-radio label="无">无</el-radio>
                            <el-radio label="有">有</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1">包块</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.bagpiece">
                            <el-radio label="无">无</el-radio>
                            <el-radio label="有">有</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1">肝大</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.hepatomegaly">
                            <el-radio label="无">无</el-radio>
                            <el-radio label="有">有</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1">脾大</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.splenomegaly">
                            <el-radio label="无">无</el-radio>
                            <el-radio label="有">有</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1">移动性浊音</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.mobilevoiced">
                            <el-radio label="无">无</el-radio>
                            <el-radio label="有">有</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>下肢水肿</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.limbEdema">
                            <el-radio label="无">无</el-radio>
                            <el-radio label="单侧">单侧</el-radio>
                            <el-radio label="双侧不对称">双侧不对称</el-radio>
                            <el-radio label="双侧对称">双侧对称</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>足背动脉搏动</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.footArteyPulse">
                            <el-radio label="未触及">未触及</el-radio>
                            <el-radio label="触及双侧对称">触及双侧对称</el-radio>
                            <el-radio label="触及左侧弱或消失">触及左侧弱或消失</el-radio>
                            <el-radio label="触及右侧弱或消失">触及右侧弱或消失</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>肛门指诊</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.analDiagnosis">
                            <el-radio label="未见异常">未见异常</el-radio>
                            <el-radio label="触痛">触痛</el-radio>
                            <el-radio label="包块">包块</el-radio>
                            <el-radio label="前列腺异常">前列腺异常</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>乳腺</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.mammaryGland">
                            <el-radio label="未见异常">未见异常</el-radio>
                            <el-radio label="乳房切除">乳房切除</el-radio>
                            <el-radio label="异常分泌">异常分泌</el-radio>
                            <el-radio label="乳腺包块">乳腺包块</el-radio>
                            <el-radio label="其他">其他</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="5">妇科</td>
                      <td colspan="1">外阴</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.vulva">
                            <el-radio label="正常">正常</el-radio>
                            <el-radio label="异常">异常</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1">阴道</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.vagina">
                            <el-radio label="正常">正常</el-radio>
                            <el-radio label="异常">异常</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1">宫颈</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.cervix">
                            <el-radio label="正常">正常</el-radio>
                            <el-radio label="异常">异常</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1">宫体</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.palace">
                            <el-radio label="正常">正常</el-radio>
                            <el-radio label="异常">异常</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1">附件</td>
                      <td colspan="7"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.attachment">
                            <el-radio label="正常">正常</el-radio>
                            <el-radio label="异常">异常</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>其他</td>
                      <td colspan="8"class="pad10">
                        <el-input v-model="editForm.other2" auto-complete="off"type="textarea" ></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td  width="140px"  rowspan="21" scope="row">辅助检查</td>
                      <td rowspan="2">血常规</td>
                      <td colspan="2">血红蛋白</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.hemoglobin" auto-complete="off">
                          <template slot="append">g/l</template>
                        </el-input>
                      </td>
                      <td colspan="2">白细胞</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.whitebloodcells" auto-complete="off">
                          <template slot="append">×10^9/L</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">血小板</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.platelet" auto-complete="off">
                          <template slot="append">×10^9/L</template>
                        </el-input>
                      </td>
                      <td colspan="2">其他</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.other3" auto-complete="off"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="2">尿常规</td>
                      <td colspan="2">尿蛋白</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.urineprotein" auto-complete="off"></el-input>
                      </td>
                      <td colspan="2">尿糖</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.urinesugar" auto-complete="off"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">尿酮体</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.urineketone" auto-complete="off"></el-input>
                      </td>
                      <td colspan="2">尿潜血</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.ery" auto-complete="off"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td>空腹血糖</td>
                      <td colspan="8" class="pad10">
                        <el-input v-model="editForm.fastingBloodSugar" auto-complete="off">
                          <template slot="append">mmol/L或mg/dL</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td>心电图</td>
                      <td colspan="8" style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.electrocardiogram">
                            <el-radio label="正常">正常</el-radio>
                            <el-radio label="异常">异常</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>尿微量白蛋白</td>
                      <td colspan="8"style="text-align:left" class="pad10">
                        <el-input v-model="editForm.urineMicroalbumin" auto-complete="off">
                          <template slot="append">mg/dL</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td>大便潜血</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.shitblood">
                            <el-radio label="阴性">阴性</el-radio>
                            <el-radio label="阳性">阳性</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>糖化血红蛋白</td>
                      <td colspan="8"class="pad10">
                        <el-input v-model="editForm.glycatedGlycemic" auto-complete="off">
                          <template slot="append">%</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td>乙型肝炎表面抗原</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.hepatitisb">
                          <el-radio label="阴性">阴性</el-radio>
                          <el-radio label="阳性">阳性</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="3">肝功能</td>
                      <td colspan="2">血清谷丙转氨酶</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.serumtransa" auto-complete="off">
                          <template slot="append">U/L</template>
                        </el-input>
                      </td>
                      <td colspan="2">血清谷草转氨酶</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.glutamictransame" auto-complete="off">
                          <template slot="append">U/L</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">白蛋白</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.albumin" auto-complete="off">
                          <template slot="append">g/l</template>
                        </el-input>
                      </td>
                      <td colspan="2">总胆红素</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.totalbilirubin" auto-complete="off">
                          <template slot="append">umol/l</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">结合胆红素</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.boundbilirubin" auto-complete="off">
                          <template slot="append">umol/l</template>
                        </el-input>
                      </td>
                      <td colspan="2">&nbsp;</td>
                      <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                      <td rowspan="2">肾功能</td>
                      <td colspan="2">血尿素</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.bloodurea" auto-complete="off">
                          <template slot="append">mmol/l</template>
                        </el-input>
                      </td>
                      <td colspan="2">血钾浓度</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.potconcen" auto-complete="off">
                          <template slot="append">mmol/l</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">血钠浓度</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.sodiumconcen" auto-complete="off">
                          <template slot="append">mmol/l</template>
                        </el-input>
                      </td>
                      <td colspan="2"></td>
                      <td colspan="2"></td>
                    </tr>
                    <tr>
                      <td rowspan="2">血 脂</td>
                      <td colspan="2">总胆固醇</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.totalcholestero" auto-complete="off">
                          <template slot="append">mmol/l</template>
                        </el-input>
                      </td>
                      <td colspan="2">血清低密度脂蛋白胆固醇</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.ldlcholesterol" auto-complete="off">
                          <template slot="append">mmol/l</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">血清高密度脂蛋白胆固醇</td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.hdlcholesterol" auto-complete="off">
                          <template slot="append">mmol/l</template>
                        </el-input>
                      </td>
                      <td colspan="2"></td>
                      <td colspan="2"></td>
                    </tr>
                    <tr>
                      <td height="25">胸部 X 线片</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.chestxray">
                            <el-radio label="正常">正常</el-radio>
                            <el-radio label="异常">异常</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>B 超</td>
                      <td colspan="2">腹部B超</td>
                      <td colspan="6"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.bsuper">
                            <el-radio label="正常">正常</el-radio>
                            <el-radio label="异常">异常</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>宫颈涂片</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px">
                          <el-radio-group v-model="editForm.cervicalSmear">
                            <el-radio label="正常">正常</el-radio>
                            <el-radio label="异常">异常</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>其他</td>
                      <td colspan="8"style="text-align:center;padding-bottom: 5px;padding-top: 5px;"class="pad10">
                        <el-input v-model="editForm.other6" auto-complete="off"type="textarea"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td  width="140px" rowspan="7" scope="row">现存主要  健康问题</td>
                      <td>脑血管疾病</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-checkbox-group v-model="editForm.cerebrovascular" style="width:100%">
                            <el-checkbox v-for="item in cerebrovascular" :key="item.value" :name="type" :label="item.label" :value="item.value">
                            </el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>肾脏疾病</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-checkbox-group v-model="editForm.kidneyDisease" style="width:100%">
                            <el-checkbox v-for="item in kidneyDisease" :key="item.value" :name="type" :label="item.label" :value="item.value">
                            </el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>心脏疾病</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-checkbox-group v-model="editForm.heartDisease" style="width:100%">
                            <el-checkbox v-for="item in heartDisease" :key="item.value" :name="type" :label="item.label" :value="item.value">
                            </el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>血管疾病</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-checkbox-group v-model="editForm.vascularDisease" style="width:100%">
                            <el-checkbox v-for="item in vascularDisease" :key="item.value" :name="type" :label="item.label" :value="item.value">
                            </el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>眼部疾病</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-checkbox-group v-model="editForm.eyeDisease" style="width:100%">
                            <el-checkbox v-for="item in eyeDisease" :key="item.value" :name="type" :label="item.label" :value="item.value">
                            </el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>神经系统疾病</td>
                      <td colspan="8"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.nervousSystem">
                            <el-radio label="未发现">未发现</el-radio>
                            <el-radio label="有">有</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>其他系统疾病</td>
                      <td colspan="8"style="text-align:center;padding-bottom: 5px;padding-top: 5px;"class="pad10">
                        <el-input v-model="editForm.otherDiabetes" auto-complete="off"type="textarea" ></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td   width="140px" rowspan="6" scope="row">住院治疗情况</td>
                      <td rowspan="2">住院史</td>
                      <td colspan="2">入/出院日期</td>
                      <td colspan="2">原 因</td>
                      <td colspan="2">医疗机构名称</td>
                      <td colspan="2">病案号</td>
                    </tr>
                    <tr>
                      <td colspan="2"style="text-align:center;padding-bottom: 5px;padding-top: 5px;"class="pad10">
                        <el-date-picker type="date" placeholder="入院日期" v-model="editForm.inhDate"style="width: 100%"></el-date-picker>
                        <div align="center">/</div>
                        <el-date-picker type="date" placeholder="出院日期" v-model="editForm.outhDate"style="width: 100%"></el-date-picker>
                      </td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.why" type="textarea"clearable ></el-input>
                      </td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.institutName"type="textarea"></el-input>
                      </td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.medicalrecordno"type="textarea"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="2">家庭病床史</td>
                      <td colspan="2">建/撤床日期</td>
                      <td colspan="2">原 因</td>
                      <td colspan="2">医疗机构名称</td>
                      <td colspan="2">病案号</td>
                    </tr>
                    <tr>
                      <td colspan="2"style="text-align:center;padding-bottom: 5px;padding-top: 5px;"class="pad10">
                        <el-date-picker type="date" placeholder="建床日期" v-model="editForm.inbDate" style="width: 100%"></el-date-picker>
                        <div align="center">/</div>
                        <el-date-picker type="date" placeholder="撤床日期" v-model="editForm.outbDate" style="width: 100%"></el-date-picker>
                      </td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.why1"type="textarea" ></el-input>
                      </td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.institutName1" type="textarea"></el-input>
                      </td>
                      <td colspan="2"class="pad10">
                        <el-input v-model="editForm.medicalrecordno1"type="textarea" ></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="2" scope="row">主要用药情况</td>
                      <td colspan="2">药物名称</td>
                      <td colspan="1">用法</td>
                      <td colspan="1">用量</td>
                      <td colspan="1">用药时间</td>
                      <td colspan="3">用药依从性</td>
                    </tr>
                    <tr>
                      <td colspan="2"style="text-align:center;padding-bottom: 5px;padding-top: 5px;"class="pad10">
                        <el-input v-model="editForm.drugname" type="textarea" ></el-input>
                      </td>
                      <td colspan="1"style="text-align:center;padding-bottom: 5px;padding-top: 5px;"class="pad10">
                        <el-input v-model="editForm.useing"type="textarea" ></el-input>
                      </td>
                      <td colspan="1"style="text-align:center;padding-bottom: 5px;padding-top: 5px;"class="pad10">
                        <el-input v-model="editForm.dosage"type="textarea" ></el-input>
                      </td>
                      <td colspan="1"style="text-align:center;padding-bottom: 5px;padding-top: 5px;"class="pad10">
                        <el-input v-model="editForm.duration" type="textarea"></el-input>
                      </td>
                      <td colspan="3"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.medicompliance">
                            <el-radio label="规律">规律</el-radio>
                            <el-radio label="间断" style="margin-left: 10px">间断</el-radio>
                            <el-radio label="不服药" style="margin-left: 10px">不服药</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td  width="140px"  rowspan="2" scope="row">非免疫  规划预防  接种史</td>
                      <td colspan="3">名称</td>
                      <td colspan="3">接种日期</td>
                      <td colspan="3">接种机构</td>
                    </tr>
                    <tr>
                      <td colspan="3"class="pad10">
                        <el-input v-model="editForm.vaccinationName" type="textarea" ></el-input>
                      </td>
                      <td colspan="3"class="pad10">
                        <el-date-picker type="date" placeholder="选择接种日期" v-model="editForm.vaccinationDate"style="width: 100%"></el-date-picker>
                      </td>
                      <td colspan="3"class="pad10" >
                        <el-input v-model="editForm.vaccinationAgency" type="textarea"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td  width="140px" height="49" scope="row">健康评价</td>
                      <td colspan="9"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.healthEvaluation">
                            <el-radio label="体检无异常">体检无异常</el-radio>
                            <el-radio label="有异常">有异常</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td  width="140px" height="47" scope="row">健康指导</td>
                      <td colspan="9"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-checkbox-group v-model="editForm.healthGuidance" style="width:100%">
                            <el-checkbox v-for="item in healthGuidance" :key="item.value" :name="type" :label="item.label" :value="item.value">
                            </el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td  width="140px" scope="row">危险因素控制</td>
                      <td colspan="9"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-checkbox-group v-model="editForm.riskcontrol" style="width:100%">
                            <el-checkbox v-for="item in riskcontrol" :key="item.value" :name="type" :label="item.label" :value="item.value">
                            </el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>

                    </tbody>
                  </table>
                </div>
                <!--产后42天健康检查记录表-->
                <div align="center" v-if="postpartum42Show">
                  <h2>产后42天健康检查记录表</h2>
                  <table border="1"  cellspacing="0" bordercolor="#dddddd" style="font-size:14px;margin-left:10px;margin-right:10px;border-collapse:collapse;width:100%">
                    <tbody style="color:#323232;">
                    <tr v-if="dialogStatus==='detail'">
                      <td style="word-wrap:break-word;text-align:center;width:120px;">
                        创建人
                      </td>
                      <td colspan="3" class="pad10">
                        <el-form-item label="" label-width="0px">
                          <el-input v-model="editForm.creator" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;width:100px;">
                        创建时间
                      </td>
                      <td colspan="3" class="pad10">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr v-if="dialogStatus==='detail'">
                      <td style="word-wrap:break-word;text-align:center;width:100px;">
                        修改人
                      </td>
                      <td colspan="3" class="pad10">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-input v-model="editForm.modifier" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;width:100px;">
                        修改时间
                      </td>
                      <td colspan="3" class="pad10">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1" style="width:140px;text-align:center;">
                        <span>*</span>身份证号
                      </td>
                      <td colspan="2" class="pad10" style="width:320px;text-align:left;padding-right: 0px;">
                        <el-form-item label="" label-width="0px" prop="peopleId">
                          <el-input v-model="editForm.peopleId" style="width: 100%;" v-on:input="inputFunc" placeholder="请输入身份证"></el-input>
                        </el-form-item>
                      </td>
                      <td colspan="1" style="width:140px;text-align:center;">
                        <span>*</span>姓名
                      </td>
                      <td colspan="3" class="pad10" style="width:330px;text-align:left;padding-right: 0px;">
                        <el-form-item label="" label-width="0px" prop="name" ref="name">
                          <el-input  v-model="editForm.name" style="width: 100%;" disabled="false"></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center;width:140px;">
                        <el-form-item label="ID" v-if="false">
                          <el-input v-model="editForm.id"></el-input>
                        </el-form-item>
                        <span>*</span>区域
                      </td>
                      <td colspan="2" class="pad10" style="text-align:left;padding-right: 0px;">
                        <el-form-item label="" label-width="0px"  prop="area">
                          <el-cascader :options="options" v-model="editForm.area" style="width: 100%;" disabled="false" change-on-select></el-cascader>
                        </el-form-item>
                      </td>
                      <td colspan="1" style="text-align:center;width:140px;">
                        随访日期
                      </td>
                      <td colspan="3" class="pad10" style="text-align:left;padding-right: 0px;">
                        <el-form-item label="" label-width="0px">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.date" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center">体温</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.temperature" >
                          <template slot="append">℃</template>
                        </el-input>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;width:140px;">分娩日期</td>
                      <td colspan="3" class="pad10">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.childBirth" style="width: 100%;"></el-date-picker>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center;width: 140px;">出院日期</td>
                      <td colspan="2" class="pad10">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.leaveHospitale" style="width: 100%;"></el-date-picker>
                      </td>
                      <td style="word-wrap:break-word;text-align:center">温度</td>
                      <td colspan="3" style="text-align:center;" class="pad10">
                        <el-input v-model="editForm.temperature">
                          <template slot="append">℃</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center;">一般健康状况</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.healthy"></el-input>
                      </td>
                      <td style="word-wrap:break-word;text-align:center">一般心理状况</td>
                      <td colspan="3" class="pad10">
                        <el-input v-model="editForm.psychology"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1"style="word-wrap:break-word;text-align:center;">血压</td>
                      <td colspan="7" class="pad10">
                        <el-input v-model="editForm.blood">
                          <template slot="append">mmHg</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1" style="word-wrap:break-word;text-align:center;">乳房</td>
                      <td colspan="7"  style="text-align:left;" class="pad10">
                        <el-radio-group v-model="editForm.breast">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">恶露</td>
                      <td colspan="7" style="text-align:left" class="pad10">
                        <el-radio-group v-model="editForm.lochia">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">伤口</td>
                      <td colspan="7" style="text-align:left" class="pad10">
                        <el-radio-group v-model="editForm.wound">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">子宫</td>
                      <td colspan="2" style="text-align:left" class="pad10">
                        <el-radio-group v-model="editForm.uterus">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="word-wrap:break-word;text-align:center">分类</td>
                      <td colspan="2" style="text-align:left">
                        <el-radio-group v-model="editForm.classify" class="pad10">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center;width:90px;">指导</td>
                      <td colspan="7" class="pad20">
                        <el-checkbox-group v-model="editForm.guidance" style="width:100%">
                          <table>
                            <tr>
                              <td style="padding-right: 20px"><el-checkbox label="心理健康" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="性保健和避孕" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="婴儿喂养" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="产妇营养" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="其他" name="type"></el-checkbox></td>
                            </tr>
                          </table>
                          <!--<el-checkbox v-for="item in guidance" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>-->
                        </el-checkbox-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1" style="word-wrap:break-word;text-align:center">其他</td>
                      <td colspan="7" class="pad10">
                        <el-input v-model="editForm.other" style=""></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1" style="word-wrap:break-word;text-align:center">处理</td>
                      <td colspan="7" class="pad10" style="text-align:left;padding-right: 5px;">
                        <table style="width:100%;">
                          <tr>
                            <td>
                              <el-radio-group v-model="editForm.referral">
                                <el-radio class="radio" label="无"></el-radio>
                                <el-radio class="radio" label="有"></el-radio>
                              </el-radio-group>
                            </td>
                            <td>
                              <table style="width: 100%;">
                                <tr>
                                  <td>原因</td>
                                  <td><el-input v-model="editForm.reason" ></el-input></td>
                                  <td style="text-align:center;">机构及科室</td>
                                  <td><el-input v-model="editForm.insandDep" ></el-input></td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>

                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center">随访医生签名</td>
                      <td colspan="7" class="pad10">
                        <el-input v-model="editForm.doctor"></el-input>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
                <!--产后访视检查服务记录表-->
                <div v-if="postpartumShow">
                  <h2 align="center">产后访视检查服务记录表</h2>
                  <table border="1"  cellspacing="0" bordercolor="#dddddd"  style="color:#323232;font-size:14px;margin-left:10px;margin-right:10px;border-collapse:collapse;width:100%">
                    <tbody style="color:#323232;">
                    <tr v-if="dialogStatus==='detail'">
                      <td style="word-wrap:break-word;text-align:center;width:100px;">
                        创建人
                      </td>
                      <td colspan="3" style="padding-left: 10px;">
                        <el-form-item label="" label-width="0px">
                          <el-input v-model="editForm.creator" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;width:100px;">
                        创建时间
                      </td>
                      <td colspan="3" class="pad10">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr v-if="dialogStatus==='detail'">
                      <td style="word-wrap:break-word;text-align:center;width:100px;">
                        修改人
                      </td>
                      <td colspan="3" class="pad10">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-input v-model="editForm.modifier" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;width:100px;">
                        修改时间
                      </td>
                      <td colspan="3" class="pad10">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center;width:150px;">
                        <el-form-item label="ID" v-if="false">
                          <el-input v-model="editForm.id"></el-input>
                        </el-form-item>
                        <span>*</span>区域
                      </td>
                      <td colspan="3" style="text-align:center;padding-left: 10px;padding-right: 0px;width: 300px;" class="pad10">
                        <el-form-item label="" label-width="0px"  prop="area">
                          <el-cascader :options="options" v-model="editForm.area" change-on-select style="width:100%;" disabled="false" change-on-select></el-cascader>
                        </el-form-item>
                      </td>
                      <td colspan="1" style="text-align:center;width:160px;">
                        <span>*</span>姓名
                      </td>
                      <td colspan="3" class="pad10" style="padding-right: 0px;">
                        <el-form-item label="" label-width="0px" prop="name" ref="name">
                          <el-input  v-model="editForm.name" style="width: 100%;" disabled="false"></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1" style="text-align:center;">
                        <span>*</span>身份证号
                      </td>
                      <td colspan="3" class="pad10" style="padding-right: 0px;">
                        <el-form-item label="" label-width="0px" prop="peopleId">
                          <el-input v-model="editForm.peopleId" style="width: 100%;" v-on:input="inputFunc" placeholder="请输入身份证"></el-input>
                        </el-form-item>
                      </td>
                      <td colspan="1" style="text-align:center;width:100px;">
                        随访日期
                      </td>
                      <td colspan="3" class="pad10" style="padding-right: 0px;">
                        <el-form-item label="" label-width="0px">
                          <el-date-picker type="date" v-model="editForm.date" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center">体温</td>
                      <td colspan="1" style="text-align:center;width:160px;"  class="pad10">
                        <el-input v-model="editForm.temperature">
                          <template slot="append">℃</template>
                        </el-input>
                      </td>
                      <td colspan="2" style="word-wrap:break-word;text-align:center;width:140px;">分娩日期</td>
                      <td style="text-align:center"  class="pad10">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.childBirth" style="width: 100%;"></el-date-picker>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;width:140px;">出院日期</td>
                      <td colspan="2" style="text-align:center"  class="pad10">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.leaveHospitale" style="width: 100%;"></el-date-picker>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">血压</td>
                      <td colspan="1" style="text-align:center;"  class="pad10">
                        <el-input v-model="editForm.blood">
                          <template slot="append">mmHg</template>
                        </el-input>
                      </td>
                      <td colspan="2" style="word-wrap:break-word;text-align:center;">一般健康状况</td>
                      <td class="pad10">
                        <el-input v-model="editForm.healthy"></el-input>
                      </td>
                      <td style="word-wrap:break-word;text-align:center">一般心理状况</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.psychology"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1"style="word-wrap:break-word;text-align:center;">乳房</td>
                      <td colspan="7" style="text-align:left"  class="pad10">
                        <el-radio-group v-model="editForm.breast">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">恶露</td>
                      <td colspan="7" style="text-align:left"  class="pad10">
                        <el-radio-group v-model="editForm.lochia">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">伤口</td>
                      <td colspan="7" style="text-align:left"  class="pad10">
                        <el-radio-group v-model="editForm.wound">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">子宫</td>
                      <td colspan="3" style="text-align:left"  class="pad10">
                        <el-radio-group v-model="editForm.uterus">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="word-wrap:break-word;text-align:center">分类</td>
                      <td colspan="3" style="text-align:left"  class="pad10">
                        <el-radio-group v-model="editForm.classify">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1" style="word-wrap:break-word;text-align:center">其他</td>
                      <td colspan="7" style="text-align:left"  class="pad10">
                        <el-input v-model="editForm.other" style=""></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center;width:90px;">指导</td>
                      <td colspan="7" style="text-align:left" class="pad20">
                        <el-checkbox-group v-model="editForm.guidance" style="width:100%">
                          <table>
                            <tr>
                              <td style="padding-right: 20px"><el-checkbox label="个人卫生" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="心理" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="营养" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="母乳喂养" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="新生儿护理与喂养" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="其他" name="type"></el-checkbox></td>
                            </tr>
                          </table>
                          <!-- <el-checkbox v-for="item in guidance" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>-->
                        </el-checkbox-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">转诊</td>
                      <td colspan="1" style="text-align:left" class="pad10">
                        <el-radio-group v-model="editForm.referral">
                          <el-radio class="radio" label="无"></el-radio>
                          <el-radio class="radio" label="有"></el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="6" class="pad10">
                        <table style="width: 100%;">
                          <tr>
                            <td>原因</td>
                            <td><el-input v-model="editForm.reason" ></el-input></td>
                            <td>机构及科室</td>
                            <td><el-input v-model="editForm.insandDep" ></el-input></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center">下次随访日期</td>
                      <td colspan="3" style="text-align:center"  class="pad10">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.nextDate" style="width: 100%;"></el-date-picker>
                      </td>
                      <td style="text-align:center">随访签名</td>
                      <td colspan="2" style="text-align:center"  class="pad10">
                        <el-input v-model="editForm.doctor"></el-input>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
                <!--第2-5次产前-->
                <div v-if="prenatalShow">
                  <h2 align="center">第2-5次产前检查服务记录表</h2>
                  <table border="1"  cellspacing="0" bordercolor="#dddddd" style="font-size:14px;margin-left:10px;margin-right:10px;border-collapse:collapse;width:100%">
                    <tbody style="color:#323232;">
                    <tr>
                      <td style="word-wrap:break-word;text-align:center;width:140px;">
                        <el-form-item label="ID" v-if="false">
                          <el-input v-model="editForm.id"></el-input>
                        </el-form-item>
                        <span>*</span>区域
                      </td>
                      <td colspan="4" class="pad10" style="text-align:left;padding-right: 0px;">
                        <el-form-item label="" label-width="0px" prop="area">
                          <el-cascader :options="options" v-model="editForm.area" style="width: 300px;" disabled="false" placeholder="请选择" change-on-select></el-cascader>
                        </el-form-item>
                      </td>
                      <td colspan="1" style="text-align:center;width:140px;">
                        <span>*</span>姓名
                      </td>
                      <td colspan="2" class="pad10" style="text-align:left;padding-right: 0px;">
                        <el-form-item label="" label-width="0px" prop="name" ref="name">
                          <el-input  v-model="editForm.name" style="width: 100%;" disabled="false"></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1" style="text-align:center;">
                        <span>*</span>身份证号
                      </td>
                      <td colspan="4" class="pad10" style="text-align:left;padding-right: 0px;">
                        <el-form-item label="" label-width="0px" prop="peopleId">
                          <el-input v-model="editForm.peopleId" style="width: 100%;" v-on:input="inputFunc" placeholder="请输入身份证"></el-input>
                        </el-form-item>
                      </td>
                      <td colspan="1" style="text-align:center;width:80px;">
                        <span>*</span>第几次
                      </td>
                      <td colspan="2" style="text-align:left;padding-right: 0px;" class="pad10">
                        <el-form-item label="" label-width="0px" prop="number">
                          <el-radio-group v-model="editForm.number">
                            <el-radio label="第二次">二</el-radio>
                            <el-radio label="第三次">三</el-radio>
                            <el-radio label="第四次">四</el-radio>
                            <el-radio label="第五次">五</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center">随访日期</td>
                      <td colspan="4" class="pad10">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.date" style="width: 100%;"></el-date-picker>
                      </td>
                      <td style="word-wrap:break-word;text-align:center">随访状态</td>
                      <td colspan="2" style="text-align:left;" class="pad10">
                        <el-radio-group v-model="editForm.status">
                          <el-radio class="radio" label="规范"></el-radio>
                          <el-radio class="radio" label="失访"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">孕周</td>
                      <td colspan="1" style=";width:150px;" class="pad10">
                        <el-input-number v-model="editForm.gestationalWeeks" :min="1" :max="20" label="描述文字" style="width:100%;"></el-input-number>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;width:60px;">主诉</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.mainSuit"></el-input>
                      </td>
                      <td style="word-wrap:break-word;text-align:center">体重</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.weight">
                          <template slot="append">Kg</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="4" colspan="1"style="word-wrap:break-word;text-align:center;">产科检查</td>
                      <td style="word-wrap:break-word;text-align:center;">宫底高度</td>
                      <td colspan="6" class="pad10">
                        <el-input v-model="editForm.fundusHeight">
                          <template slot="append">cm</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">腹围</td>
                      <td colspan="6" class="pad10">
                        <el-input v-model="editForm.abdominal">
                          <template slot="append">cm</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">胎位</td>
                      <td colspan="6" class="pad10">
                        <el-input v-model="editForm.fetalPosition"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">胎心率</td>
                      <td colspan="6" style="text-align:left" class="pad10">
                        <el-input v-model="editForm.fetalPosition">
                          <template slot="append">次/分钟</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">血压</td>
                      <td colspan="2" style="text-align:left" class="pad10">
                        <el-input v-model="editForm.blood">
                          <template slot="append">mmHg</template>
                        </el-input>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;width:90px;">血红蛋白</td>
                      <td colspan="2" style="text-align:left" class="pad10">
                        <el-input v-model="editForm.hemoglobin">
                          <template slot="append">g/L</template>
                        </el-input>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;width:100px;">尿蛋白</td>
                      <td colspan="1" style="text-align:left" class="pad10">
                        <el-input v-model="editForm.urineProtein"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">其他辅助检查</td>
                      <td colspan="7" class="pad20">
                        <el-checkbox-group v-model="editForm.otherSassist">
                          <table>
                            <tr>
                              <td style="padding-right: 20px"><el-checkbox label="肝功能" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="乙肝两对半" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="肾功能" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="4B超" name="type"></el-checkbox></td>
                            </tr>
                          </table>
                        </el-checkbox-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">分类</td>
                      <td colspan="7" style="text-align:left" class="pad10">
                        <el-radio-group v-model="editForm.classify">
                          <el-radio  class="radio" label="未见异常"></el-radio>
                          <el-radio  class="radio" label="异常"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center">指导</td>
                      <td colspan="7" class="pad20">
                        <el-checkbox-group v-model="editForm.guidance" style="width:100%">
                          <table>
                            <tr>
                              <td style="padding-right: 20px"><el-checkbox label="生活方式" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="营养" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="心理" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="运动" name="type"></el-checkbox></td>
                              <td style="padding-right: 20px"><el-checkbox label="其他" name="type"></el-checkbox></td>
                            </tr>
                          </table>
                        </el-checkbox-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">转诊</td>
                      <td colspan="1" style="text-align:left" class="pad10">
                        <el-radio-group v-model="editForm.referral">
                          <el-radio class="radio" label="无"></el-radio>
                          <el-radio class="radio" label="有"></el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="6" class="pad10">
                        <table style="width: 100%;">
                          <tr>
                            <td>原因</td>
                            <td><el-input v-model="editForm.reason" ></el-input></td>
                            <td>机构及科室</td>
                            <td><el-input v-model="editForm.insandDep" ></el-input></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center">下次随访日期</td>
                      <td colspan="4" class="pad10">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.nextDate" style="width: 100%;"></el-date-picker>
                      </td>
                      <td style="text-align:center">随访签名</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.doctor"></el-input>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
                <!--新生儿家庭访视记录表-->
                <div v-if="children1Show">
                  <h2 align="center">新生儿家庭访视记录表</h2>
                  <table border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width: 100%">
                    <tbody>
                    <tr>
                      <td style="width:140px;text-align:center" class="pad">
                        <span>*</span>身份证号
                      </td>
                      <td colspan="1" style="padding-right: 0px;width:325px;text-align:center" class="pad10">
                        <el-form-item label="" label-width="0px" prop="peopleId">
                          <el-input v-model="editForm.peopleId" style="width: 100%;" placeholder="请输入身份证" v-on:input="inputFunc"></el-input>
                        </el-form-item>
                      </td>
                      <td style="width:140px;text-align:center;" class="pad"><span>*</span>姓名</td>
                      <td colspan="1" style="padding-right: 0px;width:325px;text-align:center" class="pad10">
                        <el-form-item label="" prop="name" label-width="0px">
                          <el-input v-model="editForm.name" auto-complete="off" disabled="false"></el-input>
                        </el-form-item>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">生日</td>
                      <td colspan="1" style="text-align:center;" class="pad10">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.birthdate" style="width:100%"></el-date-picker>
                      </td>
                      <td style="width:100px;text-align:center">性别</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.sex">
                          <el-radio label=男>男</el-radio>
                          <el-radio label=女>女</el-radio>
                          <el-radio label=未知性别>未知性别</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">父亲姓名</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.fatherName"  auto-complete="off">
                        </el-input>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">父亲职业</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.fatherJob"  auto-complete="off">
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">父亲联系方式</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.fatherPhone"  auto-complete="off">
                        </el-input>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">父亲出生日期</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-date-picker type="date"  v-model="editForm.fatherBirthdate" style="width:100%"></el-date-picker>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">母亲姓名</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.motherName"  auto-complete="off">
                        </el-input>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">母亲职业</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.motherJob"  auto-complete="off">
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">母亲联系方式</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.motherPhone"  auto-complete="off">
                        </el-input>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">母亲出生日期</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-date-picker type="date"  v-model="editForm.motherBirthdate" style="width:100%"></el-date-picker>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">家庭住址</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input  v-model="editForm.addr" auto-complete="off" ></el-input>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">出生孕周</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.week"  auto-complete="off">
                          <template slot="append">周</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">母亲情况</td>
                      <td colspan="5" style="text-align:left" class="pad20">
                        <el-checkbox-group v-model="editForm.motherState"a style="width:100%">
                          <el-checkbox v-for="item in motherState" :key="item.label" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="width:100px;word-wrap:break-word;text-align:center">新生儿听力筛查</td>
                      <td colspan="5" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.listen">
                          <el-radio label=通过>通过</el-radio>
                          <el-radio label=未通过>未通过</el-radio>
                          <el-radio label=未筛查>未筛查</el-radio>
                          <el-radio label=不详>不详</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">新生儿疾病筛查</td>
                      <td colspan="5" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.illness">
                          <el-radio label=未进行>未进行</el-radio>
                          <el-radio label=检查均为阴性>检查均为阴性</el-radio>
                          <el-radio label=甲低>甲低</el-radio>
                          <el-radio label=苯丙酮尿症>苯丙酮尿症</el-radio>
                          <el-radio label=其他遗传代谢病>其他遗传代谢病</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">畸形</td>
                      <td colspan="5" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.malformation">
                          <el-radio label=有>有</el-radio>
                          <el-radio label=无>无</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">出生情况</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-select v-model="editForm.state" placeholder="请选择" style="width: 100%">
                          <el-option key="顺产" label="顺产" value="顺产"></el-option>
                          <el-option key="胎头吸引" label="胎头吸引" value="胎头吸引"></el-option>
                          <el-option key="产钳" label="产钳" value="产钳"></el-option>
                          <el-option key="剖宫" label="剖宫" value="剖宫"></el-option>
                          <el-option key="双头胎" label="双头胎" value="双头胎"></el-option>
                          <el-option key="臀位" label="臀位" value="臀位"></el-option>
                          <el-option key="其他" label="其他" value="其他"></el-option>
                        </el-select>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">新生儿窒息</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.stifle">
                          <el-radio label=有>有</el-radio>
                          <el-radio label=无>无</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="width:100px;word-wrap:break-word;text-align:center">助产机构</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.organization" auto-complete="off"></el-input>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">新生儿体重</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.birthWeight"  auto-complete="off">
                          <template slot="append">kg</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">目前体重</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.weight"  auto-complete="off">
                          <template slot="append">kg</template>
                        </el-input>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">出生身长</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.birthHeight"  auto-complete="off">
                          <template slot="append">cm</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">喂养方式</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-select v-model="editForm.feed" placeholder="请选择" style="width: 100%">
                          <el-option key="纯母乳" label="纯母乳" value="纯母乳"></el-option>
                          <el-option key="混合" label="混合" value="混合"></el-option>
                          <el-option key="人工" label="人工" value="人工"></el-option>
                        </el-select>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">吃奶量</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.feedMilk"  auto-complete="off">
                          <template slot="append" style="width: 20px">mL/次</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">吃奶次数</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.feedMilk"  auto-complete="off">
                          <template slot="append" style="width: 20px">mL/次</template>
                        </el-input>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">呕吐</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.emesis">
                          <el-radio label=无>无</el-radio>
                          <el-radio label=有>有</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">大便</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-select v-model="editForm.shit" placeholder="请选择" style="width: 100%">
                          <el-option key="糊状" label="糊状" value="糊状"></el-option>
                          <el-option key="稀" label="稀" value="稀"></el-option>
                          <el-option key="其他" label="其他" value="其他"></el-option>
                        </el-select>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">大便次数</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.shitCount"  auto-complete="off">
                          <template slot="append">次/天</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">体温</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.temperature"  auto-complete="off">
                          <template slot="append">℃</template>
                        </el-input>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">心率</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.heart"  auto-complete="off">
                          <template slot="append">次/钟</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">呼吸频率</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.breathe"  auto-complete="off" >
                          <template slot="append">次 /钟</template>
                        </el-input>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">前囟宽度</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.bregmaWidth"  auto-complete="off" >
                          <template slot="append">cm</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">前囟高度</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.bregmaHeight"  auto-complete="off" >
                          <template slot="append">cm</template>
                        </el-input>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">面色</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.face">
                          <el-radio label=红润>红润</el-radio>
                          <el-radio label=黄染>黄染</el-radio>
                          <el-radio label=其他>其他</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">黄疸部位</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-select v-model="editForm.yellow" placeholder="请选择"style="width: 100%">
                          <el-option key="无" label="无" value="无"></el-option>
                          <el-option key="面部" label="面部" value="面部"></el-option>
                          <el-option key="躯干" label="躯干" value="躯干"></el-option>
                          <el-option key="四肢" label="四肢" value="四肢"></el-option>
                          <el-option key="手足" label="手足" value="手足"></el-option>
                        </el-select>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">前囟状态</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.bregma">
                          <el-radio label=正常>正常</el-radio>
                          <el-radio label=膨隆>膨隆</el-radio>
                          <el-radio label=凹陷>凹陷</el-radio>
                          <el-radio label=其他>其他</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">眼睛</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.eye">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">四肢活动度</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.limb">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">耳外观</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.ear">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">颈部包块</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.neck">
                          <el-radio label=无>无</el-radio>
                          <el-radio label=有>有</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">鼻</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.nose">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">皮肤</td>
                      <td colspan="1" style="text-align:left" class="pad15">
                        <el-radio-group v-model="editForm.skin">
                          <table>
                            <tr>
                              <td><el-radio label=未见异常>未见异常</el-radio></td>
                              <td><el-radio label=湿疹>湿疹</el-radio></td>
                              <td><el-radio label=糜烂>糜烂</el-radio></td>
                              <td><el-radio label=其他>其他</el-radio></td>
                            </tr>
                          </table>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">口腔</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.oral">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">肛门</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.anus">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">心肺听诊</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.heartLung">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">胸部</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.chest">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">腹部触诊</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.abdomen">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">颈柱</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.vertebra">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">外生殖器</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.getPudendum">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">脐带</td>
                      <td colspan="1" style="text-align:left" class="pad15">
                        <el-radio-group v-model="editForm.getUmbilical">
                          <table>
                            <tr>
                              <td><el-radio label=脐部有渗出>脐部有渗出</el-radio></td>
                              <td><el-radio label=其他>其他</el-radio></td>
                              <td><el-radio label=未脱>未脱</el-radio></td>
                              <td><el-radio label=脱落>脱落</el-radio></td>
                            </tr>
                          </table>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">转诊建议</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.referral">
                          <el-radio class="radio" label="无"></el-radio>
                          <el-radio class="radio" label="有"></el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="6" class="pad10">
                        <table style="width: 100%;">
                          <tr>
                            <td>原因</td>
                            <td><el-input v-model="editForm.reason" ></el-input></td>
                          </tr>
                        </table>
                      </td>
                    </tr>

                    <tr>
                      <td style="text-align:center">指导</td>
                      <td colspan="6" class="pad20" >
                        <el-checkbox-group v-model="editForm.guidance"style="text-align:left">
                          <el-checkbox v-for="item in guidance" :key="item.label"  :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">本次访问日期</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-date-picker type="date"  v-model="editForm.followDate" style="width: 100%"></el-date-picker>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center">下次随访地点</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-input  v-model="editForm.nextVisitAddr" auto-complete="off" style="width: 100%" ></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">下次访问日期</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-date-picker type="date"  v-model="editForm.nextDate" style="width: 100%"></el-date-picker>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center;" class="pad"><span>*</span>随访医生签名</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-form-item label="" label-width="0px" prop="doctor">
                          <el-input  v-model="editForm.doctor" auto-complete="off" style="width: 100%"></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
                <!--1-8月儿童健康检查记录表-->
                <div v-if="children2Show">
                  <h2 align="center">1-8月儿童健康检查记录表</h2>
                  <table border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width:100%">
                    <tbody>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center;width:140px" class="pad10">
                        <span>*</span>身份证
                      </td>
                      <td colspan="2" style="padding-right: 0;text-align:center;" class="pad10">
                        <el-form-item label="" label-width="0px" prop="peopleId">
                          <el-input v-model="editForm.peopleId" style="width: 100%;" placeholder="请输入身份证" v-on:input="inputFunc" ></el-input>
                        </el-form-item>
                      </td>
                      <td style="width:100px;word-wrap:break-word;text-align:center;" class="pad"><span>*</span>姓名</td>
                      <td colspan="1" style="padding-right: 0;text-align:center" class="pad10">
                        <el-form-item label="" label-width="0px" prop="name">
                          <el-input v-model="editForm.name" style="width: 100%;" disabled="false" placeholder="请输入姓名"></el-input>
                        </el-form-item>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center" class="pad"><span>*</span>月龄</td>
                      <td colspan="2" style="text-align:left;" class="pad20">
                        <el-form-item label="" label-width="0px" prop="month">
                          <el-radio-group v-model="editForm.month">
                            <el-radio label=满月>满月</el-radio>
                            <el-radio label=3月龄>3月龄</el-radio>
                            <el-radio label=6月龄>6月龄</el-radio>
                            <el-radio label=8月龄>8月龄</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center">随访日期</td>
                      <td colspan="1" style="text-align:center" class="pad10">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.followDate"  style="width: 100%;" ></el-date-picker>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">体重</td>
                      <td colspan="2" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.weight"  auto-complete="off">
                          <template slot="append">kg</template>
                        </el-input>
                      </td>
                      <td style="word-wrap:break-word;text-align:center">身高</td>
                      <td colspan="1"  style="text-align:center" class="pad10">
                        <el-input  v-model="editForm.height" auto-complete="off" >
                          <template slot="append">cm</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">头围</td>
                      <td colspan="2" style="text-align:center" class="pad10">
                        <el-input  v-model="editForm.head" auto-complete="off" >
                          <template slot="append">cm</template>
                        </el-input>
                      </td>
                      <td style="word-wrap:break-word;text-align:center"></td>
                      <td colspan="1" class="pad20" style="text-align:center">
                      </td>
                    </tr>

                    <tr>
                      <td rowspan="10" colspan="1"style="word-wrap:break-word;text-align:center">体格检查</td>
                      <td colspan="1" style="text-align:center; width: 100px">面色</td>
                      <td colspan="1" class="pad20" style="text-align:left;width:195px">
                        <el-radio-group v-model="editForm.faceColor">
                          <table>
                            <tr>
                              <el-radio label=红润>红润</el-radio>
                              <el-radio label=黄染>黄染</el-radio>
                              <el-radio label=其他>其他</el-radio>
                            </tr>
                          </table>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">皮肤</td>
                      <td colspan="1" class="pad20" style="text-align:left;width:315px">
                        <el-radio-group v-model="editForm.skin">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="word-wrap:break-word;text-align:center;width: 100px">前囟</td>
                      <td colspan="5" style="text-align:left" class="pad20">
                        <table>
                          <tr>
                            <td style="text-align:left;padding:0px;" class="pad20">
                              <el-radio-group v-model="editForm.bregma">
                                <el-radio label=闭合>闭合</el-radio>
                                <el-radio label=未闭>未闭</el-radio>
                              </el-radio-group>
                            </td>
                          </tr>
                          <tr>
                            <td style="text-align:right;padding:0px;" class="pad10">
                              <el-input v-model="editForm.bregmaWidth"  auto-complete="off" >
                                <template slot="append">cm*</template>
                              </el-input>
                            </td>
                            <td style="text-align:right;padding:0px;" class="pad10">
                              <el-input v-model="editForm.bregmaHeight"  auto-complete="off" >
                                <template slot="append">cm</template>
                              </el-input>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">颈部包块</td>
                      <td colspan="1" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.neck" >
                          <el-radio label=有>有</el-radio>
                          <el-radio label=无>无</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">眼睛</td>
                      <td colspan="1" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.eye">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">耳</td>
                      <td colspan="1" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.ear">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">听力</td>
                      <td colspan="1" class="pad10">
                        <el-input v-model="editForm.hearing" auto-complete="off"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">口腔</td>
                      <td colspan="1" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.mouth">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">胸部</td>
                      <td colspan="1" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.chest">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">腹部</td>
                      <td colspan="1" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.abdomen">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">脐部</td>
                      <td colspan="1" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.umbilical1">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">四肢</td>
                      <td colspan="1" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.limb">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;"></td>
                      <td colspan="1" class="pad20">
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">可疑佝偻病症状</td>
                      <td colspan="1" class="pad10">
                        <el-input v-model="editForm.rickets" auto-complete="off"></el-input>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">可疑佝偻病体征</td>
                      <td colspan="1" class="pad10">
                        <el-input v-model="editForm.ricketsBody" auto-complete="off"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">肛门/外生殖器</td>
                      <td colspan="1" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.anusedea">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">血红蛋白值</td>
                      <td colspan="1" class="pad10">
                        <el-input v-model="editForm.hemoglobin" >
                          <template slot="append">g/L</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">户外活动</td>
                      <td colspan="1" class="pad10">
                        <el-input v-model="editForm.outActivities"  auto-complete="off">
                          <template slot="append">小时/日</template>
                        </el-input>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">服用维生素</td>
                      <td colspan="1" class="pad10">
                        <el-input v-model="editForm.vitaminD"  auto-complete="off">
                          <template slot="append">IU/日</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;padding-top: 10px;padding-bottom: 10px;">发育评估</td>
                      <td colspan="5"class="pad10" style=";padding-top: 10px;padding-bottom: 10px;">
                        <el-input type="textarea" rows="5" v-model="editForm.development"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan="2" colspan="1"style="word-wrap:break-word;text-align:center">两次随访期间患病情况</td>
                      <td colspan="2" class="pad10" style="text-align:left;padding-top: 10px;padding-bottom: 10px;">肺炎
                        <el-input v-model="editForm.pneumonia"  auto-complete="off">
                          <template slot="append">次</template>
                        </el-input>
                      </td>
                      <td colspan="2" class="pad10" style="text-align:left;padding-top: 10px;padding-bottom: 10px;">腹泻
                        <el-input v-model="editForm.diarrhea"  auto-complete="off">
                          <template slot="append">次</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="2" class="pad10" style="text-align:left;padding-top: 10px;padding-bottom: 10px;">外伤
                        <el-input v-model="editForm.trauma"  auto-complete="off">
                          <template slot="append">次</template>
                        </el-input>
                      </td>
                      <td colspan="2" style="text-align:left;padding-top: 10px;padding-bottom: 10px;" class="pad10">其他
                        <el-input v-model="editForm.other"  auto-complete="off"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">转诊建议</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.referral">
                          <el-radio class="radio" label="无"></el-radio>
                          <el-radio class="radio" label="有"></el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="6" class="pad10">
                        <table style="width: 100%;">
                          <tr>
                            <td>原因</td>
                            <td><el-input v-model="editForm.reason" ></el-input></td>
                            <td>机构及科室</td>
                            <td><el-input v-model="editForm.insandDep" ></el-input></td>
                          </tr>
                        </table>
                      </td>
                    </tr>

                    <tr>
                      <td style="text-align:center">指导</td>
                      <td colspan="6" style="text-align:left" class="pad20">
                        <el-checkbox-group v-model="editForm.guidance"a style="width:100%">
                          <el-checkbox v-for="item in guidance" :key="item.label"  :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="text-align:center">中医药健康服务</td>
                      <td colspan="6" style="text-align:left" class="pad20">
                        <el-checkbox-group v-model="editForm.medicine"a style="width:100%">
                          <el-checkbox v-for="item in medicine" :key="item.label" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="text-align:center;width: 120px">下次随访日期</td>
                      <td  colspan="2" style="text-align:center" class="pad10">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.nextDate" style="width: 100%" ></el-date-picker>
                      </td>
                      <td style="text-align:center" ><span>*</span>随访医生签名</td>
                      <td colspan="1" class="pad20" style="text-align:center;">
                        <el-form-item label="" label-width="0px" prop="doctor">
                          <el-input v-model="editForm.doctor" placeholder="请输入随访医生签名" ></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
                <!--12-30月儿童健康检查记录表-->
                <div v-if="children3Show">
                  <h2 align="center">12-30月儿童健康检查记录表</h2>
                  <table  border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width:100%">
                    <tbody>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center;width:140px" class="pad10">
                        <span>*</span>身份证
                      </td>
                      <td colspan="2" class="pad10" style="text-align:center;">
                        <el-input v-model="editForm.peopleId" style="width: 100%;" placeholder="请输入身份证" v-on:input="inputFunc"></el-input>
                      </td>
                      <td style="width:80px;word-wrap:break-word;text-align:center;" class="pad"><span>*</span>姓名</td>
                      <td colspan="1" class="pad10" style="text-align:center">
                        <el-input v-model="editForm.name" style="width: 100%;" placeholder="请输入姓名" disabled="false"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center" class="pad"><span>*</span>月龄</td>
                      <td colspan="2" class="pad10" style="text-align:left;">
                        <el-radio-group v-model="editForm.month">
                          <el-radio label=12月龄>12月龄</el-radio>
                          <el-radio label=18月龄>18月龄</el-radio>
                          <el-radio label=24月龄>24月龄</el-radio>
                          <el-radio label=30月龄>30月龄</el-radio>
                        </el-radio-group>
                      </td>
                      <td style="word-wrap:break-word;text-align:center">随访日期</td>
                      <td colspan="1" class="pad10" style="text-align:center">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.followDate"  style="width: 100%;" ></el-date-picker>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">体重</td>
                      <td colspan="2" class="pad10" style="text-align:center">
                        <el-input v-model="editForm.weight"  auto-complete="off">
                          <template slot="append">kg</template>
                        </el-input>
                      </td>
                      <td style="word-wrap:break-word;text-align:center">身长(高)</td>
                      <td colspan="1" class="pad10" style="text-align:center">
                        <el-input  v-model="editForm.height" auto-complete="off" >
                          <template slot="append">cm</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan="10" colspan="1"style="word-wrap:break-word;text-align:center">体格检查</td>
                      <td colspan="1" style="text-align:center; width: 100px">面色</td>
                      <td colspan="1" class="pad20" style="text-align:left;width:185px">
                        <el-radio-group v-model="editForm.faceColor">
                          <el-radio label=红润>红润</el-radio>
                          <el-radio label=其他>其他</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">皮肤</td>
                      <td colspan="1" class="pad20" style="text-align:left;width:325px">
                        <el-radio-group v-model="editForm.skin">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="word-wrap:break-word;text-align:center;width: 100px">前囟</td>
                      <td colspan="5" style="text-align:left" class="pad20">
                        <table>
                          <tr>
                            <td style="text-align:left;padding:0px;" class="pad20">
                              <el-radio-group v-model="editForm.bregma">
                                <el-radio label=闭合>闭合</el-radio>
                                <el-radio label=未闭>未闭</el-radio>
                              </el-radio-group>
                            </td>
                          </tr>
                          <tr>
                            <td style="text-align:right;padding:0px;" class="pad5">
                              <el-input v-model="editForm.bregmaWidth"  auto-complete="off" >
                                <template slot="append">cm*</template>
                              </el-input>
                            </td>
                            <td style="text-align:right;padding:0px;" class="pad5">
                              <el-input v-model="editForm.bregmaHeight"  auto-complete="off" >
                                <template slot="append">cm</template>
                              </el-input>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">眼睛</td>
                      <td colspan="1" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.eye">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">耳外观</td>
                      <td colspan="1" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.ear">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">听力</td>
                      <td colspan="1" class="pad10">
                        <el-input v-model="editForm.hearing" auto-complete="off"></el-input>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px"></td>
                      <td colspan="1" class="pad10">
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">出牙数</td>
                      <td colspan="1" class="pad10">
                        <el-input v-model="editForm.mouth" auto-complete="off" >
                          <template slot="append">颗</template>
                        </el-input>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">龋齿数</td>
                      <td colspan="1" class="pad10">
                        <el-input v-model="editForm.bmouth" auto-complete="off" >
                          <template slot="append">颗</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">胸部</td>
                      <td colspan="1" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.chest">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">腹部</td>
                      <td colspan="1" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.abdomen">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">四肢</td>
                      <td colspan="1" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.limb">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">步态</td>
                      <td colspan="1" class="pad10">
                        <el-input  v-model="editForm.gait" auto-complete="off"  ></el-input>
                      </td>
                    </tr>


                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">可疑佝偻病症状</td>
                      <td colspan="1" class="pad10">
                        <el-select v-model="editForm.ricketsBody" placeholder="请选择" style="width:100%;">
                          <el-option key="无" label="无" value="无"></el-option>
                          <el-option key="肋串珠" label="肋串珠" value="肋串珠"></el-option>
                          <el-option key="肋软骨沟" label="肋软骨沟" value="肋软骨沟"></el-option>
                          <el-option key="鸡胸" label="鸡胸" value="鸡胸"></el-option>
                          <el-option key="手足镯" label="手足镯" value="手足镯"></el-option>
                          <el-option key="“O”型腿" label="“O”型腿" value="“O”型腿"></el-option>
                          <el-option key="“X”型腿" label="“X”型腿" value="“X”型腿"></el-option>
                        </el-select>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">血红蛋白值</td>
                      <td colspan="1" class="pad10">
                        <el-input v-model="editForm.hemoglobin" >
                          <template slot="append">g/L</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">户外活动</td>
                      <td colspan="1" class="pad10">
                        <el-input v-model="editForm.outActivities"  auto-complete="off">
                          <template slot="append">小时/日</template>
                        </el-input>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">服用维生素</td>
                      <td colspan="1" class="pad10">
                        <el-input v-model="editForm.vitaminD"  auto-complete="off">
                          <template slot="append">IU/日</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;">发育评估</td>
                      <td colspan="5" style="text-align:left;" class="pad20">

                        <el-checkbox-group v-model="editForm.development"a style="width:100%;text-align:left;">
                          <table>
                            <tr>
                              <td style="padding-right: 15px"><el-checkbox label="对很大的声音没有反应" name="type"></el-checkbox></td>
                              <td style="padding-right: 15px"><el-checkbox label="呼唤名字无反应" name="type"></el-checkbox></td>
                              <td style="padding-right: 15px"><el-checkbox label="不会模仿“再见”或者“欢迎”动作" name="type"></el-checkbox></td>
                            </tr>
                            <tr>
                              <td style="padding-right: 15px"><el-checkbox label="不会使用拇指对程小物品" name="type"></el-checkbox></td>
                              <td style="padding-right: 15px"><el-checkbox label="不会扶物站立" name="type"></el-checkbox></td>
                            </tr>
                          </table>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan="2" colspan="1"style="word-wrap:break-word;text-align:center">两次随访期间患病情况</td>
                      <td colspan="1"style="word-wrap:break-word;text-align:center">肺炎</td>
                      <td colspan="1" class="pad10" style="text-align:left">
                        <el-input v-model="editForm.pneumonia"  auto-complete="off">
                          <template slot="append">次</template>
                        </el-input>
                      </td>
                      <td colspan="1"style="word-wrap:break-word;text-align:center">腹泻</td>
                      <td colspan="4" class="pad10" style="text-align:left">
                        <el-input v-model="editForm.diarrhea"  auto-complete="off">
                          <template slot="append">次</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1"style="word-wrap:break-word;text-align:center">外伤</td>
                      <td colspan="1" class="pad10" style="text-align:left">
                        <el-input v-model="editForm.trauma"  auto-complete="off">
                          <template slot="append">次</template>
                        </el-input>
                      </td>
                      <td colspan="1"style="word-wrap:break-word;text-align:center">其他</td>
                      <td  colspan="4" class="pad10" style="text-align:left">
                        <el-input v-model="editForm.other"  auto-complete="off">
                        </el-input>
                      </td>

                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">转诊建议</td>
                      <td colspan="1" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.referral">
                          <el-radio class="radio" label="无"></el-radio>
                          <el-radio class="radio" label="有"></el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="6" class="pad10">
                        <table style="width: 100%;">
                          <tr>
                            <td>原因</td>
                            <td><el-input v-model="editForm.reason" ></el-input></td>
                            <td>机构及科室</td>
                            <td><el-input v-model="editForm.insandDep" ></el-input></td>
                          </tr>
                        </table>
                      </td>
                    </tr>

                    <tr>
                      <td style="text-align:center">指导</td>
                      <td colspan="6" class="pad20" style="text-align:left">
                        <el-checkbox-group v-model="editForm.guidance"a style="width:100%">
                          <el-checkbox v-for="item in guidance" :key="item.label"  :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="text-align:center">中医药健康服务</td>
                      <td colspan="6" class="pad20" style="text-align:left">
                        <el-checkbox-group v-model="editForm.medicine"a style="width:100%">
                          <el-checkbox v-for="item in medicine" :key="item.label" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="text-align:center">下次随访日期</td>
                      <td colspan="2" class="pad10" style="text-align:center;">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.nextDate" style="width: 100%;" ></el-date-picker>
                      </td>
                      <td style="text-align:center" class="pad"><span>*</span>随访医生签名</td>
                      <td colspan="1" class="pad10" style="text-align:center;">
                        <el-form-item label="" label-width="0px" prop="doctor">
                          <el-input v-model="editForm.doctor" placeholder="请输入随访医生签名" ></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
                <!--3-6岁月儿童健康检查记录表-->
                <div v-if="children4Show">
                  <h2 align="center">3-6岁月儿童健康检查记录表</h2>
                  <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width:100%">
                    <tbody>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center" class="pad">
                        <span>*</span>身份证
                      </td >
                      <td colspan="3" class="pad10" style="text-align:center;width:140px;">
                        <el-input v-model="editForm.peopleId" style="width: 100%;" placeholder="请输入身份证"  v-on:input="inputFunc"></el-input>
                      </td>
                      <td style="width:140px;word-wrap:break-word;text-align:center;" class="pad"><span>*</span>姓名</td>
                      <td colspan="3" class="pad10" style="text-align:center">
                        <el-input v-model="editForm.name" style="width: 100%;" placeholder="请输入姓名" disabled="false"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center;" class="pad"><span>*</span>年龄</td>
                      <td colspan="3" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.age">
                          <el-radio label=3岁>3岁</el-radio>
                          <el-radio label=4岁>4岁</el-radio>
                          <el-radio label=5岁>5岁</el-radio>
                          <el-radio label=6岁>6岁</el-radio>
                        </el-radio-group>
                      </td>
                      <td style="word-wrap:break-word;text-align:center">随访日期</td>
                      <td colspan="3" class="pad10" style="text-align:center" >
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.followDate"  style="width: 100%;" ></el-date-picker>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">体重</td>
                      <td colspan="3" class="pad10" style="text-align:center">
                        <el-input v-model="editForm.weight"  auto-complete="off">
                          <template slot="append">kg</template>
                        </el-input>
                      </td>
                      <td style="word-wrap:break-word;text-align:center">身高</td>
                      <td colspan="3" class="pad10" style="text-align:center">
                        <el-input  v-model="editForm.height" auto-complete="off" >
                          <template slot="append">cm</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">身高/体重</td>
                      <td colspan="3" class="pad10" style="text-align:center">
                        <el-input v-model="editForm.weightheight"  auto-complete="off"></el-input>
                      </td>
                      <td style="word-wrap:break-word;text-align:center">体格发育评价</td>
                      <td colspan="3" class="pad10" style="text-align:center">
                        <el-select v-model="editForm.physical" placeholder="请选择" style="width: 100%">
                          <el-option key="正常" label="正常" value="正常"></el-option>
                          <el-option key="低体重" label="低体重" value="低体重"></el-option>
                          <el-option key="消瘦" label="消瘦" value="消瘦"></el-option>
                          <el-option key="生长迟缓" label="生长迟缓" value="生长迟缓"></el-option>
                          <el-option key="超重" label="超重" value="超重"></el-option>
                        </el-select>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan="4" colspan="1"style="word-wrap:break-word;text-align:center">体格检查</td>
                      <td colspan="1" style="text-align:center;width: 100px">视力</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.eye"></el-input>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">听力</td>
                      <td colspan="3" class="pad10">
                        <el-input v-model="editForm.hearing"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">出牙数</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.mouth" auto-complete="off" >
                          <template slot="append">颗</template>
                        </el-input>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">龋齿数</td>
                      <td colspan="3" class="pad10">
                        <el-input v-model="editForm.bmouth" auto-complete="off" >
                          <template slot="append">颗</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">胸部</td>
                      <td colspan="2" class="pad20"  style="text-align:left;">
                        <el-radio-group v-model="editForm.chest">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">腹部</td>
                      <td colspan="3" class="pad20"  style="text-align:left;">
                        <el-radio-group v-model="editForm.abdomen">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">血红蛋白值</td>
                      <td colspan="3" class="pad10">
                        <el-input v-model="editForm.hemoglobin" auto-complete="off">
                          <template slot="append" >g/L</template>
                        </el-input>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">其他检查</td>
                      <td colspan="3" class="pad10">
                        <el-input v-model="editForm.otherCheck" auto-complete="off"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;">发育评估</td>
                      <td colspan="6" class="pad20"  style="text-align:left;">
                        <el-checkbox-group v-model="editForm.development"a style="width:100%">
                          <el-checkbox v-for="item in development" :key="item.label" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan="2" colspan="1"style="word-wrap:break-word;text-align:center">两次随访期间患病情况</td>
                      <td colspan="1"style="word-wrap:break-word;text-align:center">肺炎</td>
                      <td colspan="2" class="pad10" style="text-align:left">
                        <el-input v-model="editForm.pneumonia"  auto-complete="off">
                          <template slot="append">次</template>
                        </el-input>
                      </td>
                      <td colspan="1"style="word-wrap:break-word;text-align:center">腹泻</td>
                      <td colspan="4" class="pad10" style="text-align:left">
                        <el-input v-model="editForm.diarrhea"  auto-complete="off">
                          <template slot="append">次</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1"style="word-wrap:break-word;text-align:center">外伤</td>
                      <td colspan="2" class="pad10" style="text-align:left">
                        <el-input v-model="editForm.trauma"  auto-complete="off">
                          <template slot="append">次</template>
                        </el-input>
                      </td>
                      <td colspan="1"style="word-wrap:break-word;text-align:center">其他</td>
                      <td  colspan="4" class="pad10" style="text-align:left">
                        <el-input v-model="editForm.other"  auto-complete="off">
                        </el-input>
                      </td>

                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">转诊建议</td>
                      <td colspan="2" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.referral">
                          <el-radio class="radio" label="无"></el-radio>
                          <el-radio class="radio" label="有"></el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="5" class="pad10">
                        <table style="width: 100%;">
                          <tr>
                            <td>原因</td>
                            <td><el-input v-model="editForm.reason" ></el-input></td>
                            <td>机构及科室</td>
                            <td><el-input v-model="editForm.insandDep" ></el-input></td>
                          </tr>
                        </table>
                      </td>
                    </tr>

                    <tr>
                      <td style="text-align:center">指导</td>
                      <td colspan="6" class="pad20"  style="text-align:left;">
                        <el-checkbox-group v-model="editForm.guidance"a style="width:100%">
                          <el-checkbox v-for="item in guidance" :key="item.label"  :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="text-align:center">中医药健康服务</td>
                      <td colspan="7" class="pad20"  style="text-align:left;">
                        <el-checkbox-group v-model="editForm.medicine"a style="width:100%">
                          <el-checkbox v-for="item in medicine" :key="item.label" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="text-align:center">下次随访日期</td>
                      <td colspan="3" class="pad5" style="text-align:center;;">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.nextDate" style="width: 90%;" ></el-date-picker>
                      </td>
                      <td style="text-align:center" class="pad"><span>*</span>随访医生签名</td>
                      <td colspan="3" class="pad10" style="text-align:center">
                        <el-form-item label="" label-width="0px" prop="doctor">
                          <el-input v-model="editForm.doctor" placeholder="请输入随访医生签名" ></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
                <!--第1次产前检查服务记录表-->
                <div id="yf" v-if="maternalShow">
                  <div id="dataPanel">
                    <h2 align="center">第1次产前检查服务记录表</h2>
                    <table  border="1"  cellspacing="0" bordercolor="#dddddd" style="padding-left:10px;padding-right:10px;margin-left:10px;margin-right:10px;width:100%;border-collapse:collapse;">
                      <tbody style="color:#323232;">
                      <tr>
                        <td style="width:140px;word-wrap:break-word;text-align:center;">
                          <span>*</span>姓名
                        </td>
                        <td colspan="3" class="pad10" style="width:330px;padding-right: 0">
                          <el-form-item label="" label-width="0px" prop="name" ref="name">
                            <el-input  v-model="editForm.name" placeholder="请输入姓名" disabled="false"></el-input>
                          </el-form-item>
                        </td>
                        <td style="width:140px;word-wrap:break-word;text-align:center;"><span>*</span>区域</td>
                        <td colspan="3" class="pad10" style="width:330px;padding-right: 0;">
                          <el-form-item label="" label-width="0px" prop="area">
                            <el-cascader :options="options" v-model="editForm.area" style="width: 100%;" disabled="false" placeholder="请输入区域" change-on-select></el-cascader>
                          </el-form-item>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center;"><span>*</span>身份证</td>
                        <td colspan="3" class="pad10" style="padding-right: 0px;">
                          <el-form-item label="" label-width="0px" prop="peopleId">
                            <el-input v-model="editForm.peopleId" placeholder="请输入身份证" style="width: 100%;" v-on:input="inputFunc"></el-input>
                          </el-form-item>
                        </td>
                        <td style="word-wrap:break-word;text-align:center">填表日期</td>
                        <td colspan="3" class="pad10">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.date"  style="width: 100%;" ></el-date-picker>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center">孕周</td>
                        <td colspan="3" class="pad10">
                          <el-input v-model="editForm.gestationalWeeks" >
                            <template slot="append">周</template>
                          </el-input>
                        </td>
                        <td style="word-wrap:break-word;text-align:center">孕妇年龄</td>
                        <td colspan="3" class="pad10">
                          <el-input v-model="editForm.gravidaAge" >
                            <template slot="append">周岁</template>
                          </el-input>
                        </td>
                      </tr>
                      <tr>
                        <td style="width:60px;word-wrap:break-word;text-align:center">丈夫姓名</td>
                        <td colspan="1" class="pad10" style="width:120px;">
                          <el-input  v-model="editForm.husbandName"></el-input>
                        </td>
                        <td style="width:80px;text-align:center">丈夫年龄</td>
                        <td colspan="1" style="text-align:center" class="pad10">
                          <el-input v-model="editForm.husbandAge">
                            <template slot="append">周岁</template>
                          </el-input>
                        </td>
                        <td style="word-wrap:break-word;text-align:center">丈夫电话</td>
                        <td colspan="1" class="pad10">
                          <el-input v-model="editForm.husbandPhone"></el-input>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center">孕次</td>
                        <td colspan="3"  class="pad10">
                          <el-input v-model="editForm.gestationalNumber"  >
                            <template slot="append">次</template>
                          </el-input>
                        </td>
                        <td style="word-wrap:break-word;text-align:center">产次</td>
                        <td colspan="2"  class="pad10">
                          <el-input v-model="editForm.vaginalDelivery" >
                            <template slot="append">次</template>
                          </el-input>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center">末次月经</td>
                        <td colspan="3"  class="pad10">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.lastMenses" style="width: 100%;" ></el-date-picker>
                        </td>
                        <td style="word-wrap:break-word;text-align:center">预产期</td>
                        <td colspan="2"  class="pad10">
                          <el-input v-model="editForm.expect" ></el-input>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center">既往史</td>
                        <td colspan="5"  class="pad20">
                          <el-checkbox-group v-model="editForm.pastHistory" >
                            <table>
                              <tr>
                                <td style="padding-right: 20px"><el-checkbox label="无" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="心脏病" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="肾脏疾病" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="肝脏疾病" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="高血压" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="贫血" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="糖尿病" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="其他" name="type"></el-checkbox></td>
                              </tr>
                            </table>
                          </el-checkbox-group>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center">家族史</td>
                        <td colspan="5"  class="pad20" style="text-align:left">
                          <el-checkbox-group v-model="editForm.familyHistory" style="width:100%">
                            <table>
                              <tr>
                                <td style="padding-right: 20px"><el-checkbox label="无" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="遗传性疾病史" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="精神疾病史" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="其他" name="type"></el-checkbox></td>
                              </tr>
                            </table>
                            <!--<el-checkbox v-for="item in familyHistory" :key="item.value"  :label="item.label" :value="item.value" ></el-checkbox>-->
                          </el-checkbox-group>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center">个人史</td>
                        <td colspan="5" style="text-align:left" class="pad20">
                          <el-checkbox-group v-model="editForm.personalHistory" >
                            <table>
                              <tr>
                                <td style="padding-right: 20px"><el-checkbox label="无特殊" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="吸烟" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="饮酒" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="服用药物" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="接触有毒有害物质" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="接触放射线" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="其他" name="type"></el-checkbox></td>
                              </tr>
                            </table>
                          </el-checkbox-group>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center">妇产科手术史</td>
                        <td colspan="5" style="text-align:left" class="pad10">
                          <el-radio-group v-model="editForm.abOperation" >
                            <el-radio label="有"></el-radio>
                            <el-radio label="无"></el-radio>
                          </el-radio-group>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center">孕产史</td>
                        <td colspan="5" style="text-align:center" class="pad20">
                          <el-checkbox-group v-model="editForm.historyPregnancy" >
                            <table>
                              <tr>
                                <td style="padding-right: 20px"><el-checkbox label="自然流产" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="人工流产" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="死胎" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="新生儿死亡" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="出生缺陷儿" name="type"></el-checkbox></td>
                              </tr>
                            </table>
                          </el-checkbox-group>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center">身高</td>
                        <td colspan="3" style="text-align:left;width:160px;" class="pad10">
                          <el-input v-model="editForm.height" >
                            <template slot="append">cm</template>
                          </el-input>
                        </td>
                        <td style="word-wrap:break-word;text-align:center;">体重</td>
                        <td colspan="3" style="text-align:left" class="pad10">
                          <el-input v-model="editForm.weight" >
                            <template slot="append">Kg</template>
                          </el-input>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center">体质指数（BMI）</td>
                        <td colspan="3" style="text-align:left" class="pad10">
                          <el-input v-model="editForm.bodymass" >
                            <template slot="append">Kg/m2</template>
                          </el-input>
                        </td>
                        <td style="word-wrap:break-word;text-align:center">血压</td>
                        <td colspan="3" style="text-align:left" class="pad10">
                          <el-input v-model="editForm.blood" >
                            <template slot="append">mmHg</template>
                          </el-input>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center">听诊</td>
                        <td colspan="3" style="text-align:left" class="pad10">心脏:
                          <el-radio-group v-model="editForm.heart">
                            <el-radio label="未见异常"></el-radio>
                            <el-radio label="异常"></el-radio>
                          </el-radio-group>
                        </td>
                        <td colspan="2" style="text-align:left" class="pad10">肺部:
                          <el-radio-group v-model="editForm.lungs" >
                            <el-radio label="未见异常"></el-radio>
                            <el-radio label="异常"></el-radio>
                          </el-radio-group>
                        </td>
                      </tr>
                      <tr>
                        <td rowspan="3" style="word-wrap:break-word;text-align:center">妇科</td>
                        <td colspan="3" style="text-align:left" class="pad10">外阴:
                          <el-radio-group v-model="editForm.vulva" >
                            <el-radio label="未见异常"></el-radio>
                            <el-radio label="异常"></el-radio>
                          </el-radio-group>
                        </td>
                        <td colspan="2" style="text-align:left" class="pad10">阴道:
                          <el-radio-group v-model="editForm.vagina" >
                            <el-radio label="未见异常"></el-radio>
                            <el-radio label="异常"></el-radio>
                          </el-radio-group>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="3" style="text-align:left" class="pad10">宫颈:
                          <el-radio-group v-model="editForm.cervix" >
                            <el-radio label="未见异常"></el-radio>
                            <el-radio label="异常"></el-radio>
                          </el-radio-group>
                        </td>
                        <td colspan="2" style="text-align:left" class="pad10">子宫:
                          <el-radio-group v-model="editForm.uterus" >
                            <el-radio label="未见异常"></el-radio>
                            <el-radio label="异常"></el-radio>
                          </el-radio-group>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="5" style="text-align:left" class="pad10">附件:
                          <el-radio-group v-model="editForm.enclosure" >
                            <el-radio label="未见异常"></el-radio>
                            <el-radio label="异常"></el-radio>
                          </el-radio-group>
                        </td>
                      </tr>
                      <tr>
                        <td rowspan="14" colspan="1"style="word-wrap:break-word;text-align:center">辅助检查</td>
                        <td colspan="2" style="text-align:center;height:100px;">血常规</td>
                        <td colspan="3">
                          <table>
                            <tr>
                              <td style="text-align:right;padding:0px;">
                                血红蛋白值
                              </td>
                              <td class="pad10">
                                <el-input  auto-complete="off" v-model="editForm.hemoglobin" >
                                  <template slot="append">g/L</template>
                                </el-input>
                              </td>
                              <td style="text-align:right;padding:0px;width:100px;">
                                白细胞计数值
                              </td>
                              <td class="pad10">
                                <el-input  auto-complete="off" v-model="editForm.whiteBlood" >
                                  <template slot="append">*10^9/L</template>
                                </el-input>
                              </td>
                            </tr>
                            <tr>
                              <td style="text-align:right;padding:0px;width:100px;">
                                血小板计数值
                              </td>
                              <td class="pad10">
                                <el-input auto-complete="off" v-model="editForm.plateletCount" >
                                  <template slot="append">*10^9/L</template>
                                </el-input>
                              </td>
                              <td style="text-align:right;padding:0px;">
                                其他
                              </td>
                              <td class="pad10">
                                <el-input auto-complete="off" v-model="editForm.otherBlood" ></el-input>
                              </td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" style="word-wrap:break-word;text-align:center">尿常规</td>
                        <td colspan="3" style="text-align:left">
                          <table>
                            <tr>
                              <td style="text-align:right;padding:0px;">
                                尿蛋白
                              </td>
                              <td style="text-align:right;padding:0px;">
                                <el-input auto-complete="off" v-model="editForm.urineProtein"></el-input>
                              </td>
                              <td style="text-align:right;padding:0px;">
                                尿糖
                              </td>
                              <td style="text-align:right;padding:0px;">
                                <el-input auto-complete="off" v-model="editForm.urineSugar"></el-input>
                              </td>
                            </tr>
                            <tr>
                              <td style="text-align:right;padding:0px;width:60px;">
                                尿酮体
                              </td>
                              <td style="text-align:right;padding:0px;">
                                <el-input auto-complete="off" v-model="editForm.urineKetone"></el-input>
                              </td>
                              <td style="text-align:right;padding:0px;width:60px;">
                                尿潜血
                              </td>
                              <td style="text-align:right;padding:0px;">
                                <el-input auto-complete="off" v-model="editForm.urineKetone"></el-input>
                              </td>
                              <td style="text-align:right;padding:0px;width:40px;">
                                其他
                              </td>
                              <td style="text-align:right;padding:0px;">
                                <el-input auto-complete="off" v-model="editForm.otherUrine"></el-input>
                              </td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                      <tr>
                        <td rowspan="2" colspan="1" >血型</td>
                        <td colspan="1" style="text-align:center">ABO</td>
                        <td rowspan="2" colspan="3" class="pad10"><el-input v-model="editForm.bloodType" ></el-input></td>
                      </tr>
                      <tr>
                        <td colspan="1" style="text-align:center">RH</td>
                      </tr>
                      <tr>
                        <td colspan="2" style="text-align:center">血糖</td>
                        <td colspan="3" class="pad10">
                          <el-input v-model="editForm.bloodSugar" >
                            <template slot="append">mmoL</template>
                          </el-input>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" style="text-align:center">肝功能</td>
                        <td colspan="3" style="text-align:center">
                          <table>
                            <tr>
                              <td style="text-align:right;padding:0px;width:120px;">
                                血清谷丙转氨酶
                              </td>
                              <td style="text-align:right;padding:0px;">
                                <el-input auto-complete="off" v-model="editForm.sgpt" >
                                  <template slot="append">U/L</template>
                                </el-input>
                              </td>
                              <td style="text-align:right;padding:0px;width:120px;">
                                血清谷草转氨酶
                              </td>
                              <td style="text-align:right;padding:0px;">
                                <el-input auto-complete="off" v-model="editForm.sgot" >
                                  <template slot="append">U/L</template>
                                </el-input>
                              </td>
                            </tr>
                            <tr>
                              <td style="text-align:right;padding:0px;width:100px;">
                                白蛋白
                              </td>
                              <td style="text-align:right;padding:0px;">
                                <el-input auto-complete="off" v-model="editForm.albumin" >
                                  <template slot="append">g/L</template>
                                </el-input>
                              </td>
                              <td style="text-align:right;padding:0px;">
                                总胆红素
                              </td>
                              <td style="text-align:right;padding:0px;">
                                <el-input auto-complete="off" v-model="editForm.totalBilirubin" >
                                  <template slot="append">μmol/L</template>
                                </el-input>
                              </td>
                            </tr>
                            <tr>
                              <td style="text-align:right;padding:0px;width:100px;">
                                结合胆红素
                              </td>
                              <td style="text-align:right;padding:0px;">
                                <el-input auto-complete="off" v-model="editForm.conjugated" >
                                  <template slot="append">μmol/L</template>
                                </el-input>
                              </td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" style="text-align:center">肾功能</td>
                        <td colspan="3" style="text-align:center" class="pad10">
                          <table>
                            <tr>
                              <td style="text-align:right;padding:0px;width:120px;">
                                血清肌酐
                              </td>
                              <td style="text-align:right;">
                                <el-input auto-complete="off" v-model="editForm.scr" >
                                  <template slot="append">μmol/L</template>
                                </el-input>
                              </td>
                              <td style="text-align:right;padding:0px;width:120px;">
                                血尿素
                              </td>
                              <td style="text-align:right;">
                                <el-input auto-complete="off" v-model="editForm.bloodUrea" >
                                  <template slot="append">mmol/L</template>
                                </el-input>
                              </td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" rowspan="2" style="text-align:center">阴道分泌物</td>
                        <td colspan="3" style="text-align:left" class="pad20">
                          <el-checkbox-group v-model="editForm.vaginalSecretions">
                            <table>
                              <tr>
                                <td style="padding-right: 20px"><el-checkbox label="未见异常" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="滴虫" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="假丝酵母菌" name="type"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="其他" name="type"></el-checkbox></td>
                              </tr>
                            </table>
                          </el-checkbox-group>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="3" style="text-align:left" class="pad20">
                          <table>
                            <tr>
                              <td>阴道清洁度</td>
                              <td><el-select v-model="editForm.vaginalCleanliness" placeholder="请选择" style="width:100%" >
                                <el-option key="I度" label="I度" value="I度"></el-option>
                                <el-option key="II度" label="II度" value="II度"></el-option>
                                <el-option key="III度" label="III度" value="III度"></el-option>
                                <el-option key="IV度" label="IV度" value="IV度"></el-option>
                              </el-select></td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" rowspan="2" style="height:120px;text-align:center">乙型肝炎</td>
                        <td colspan="3" style="height:100px;text-align:left" class="pad20">
                          <el-checkbox-group v-model="editForm.hepatitisB" style="width:100%" >
                            <table>
                              <tr>
                                <td style="padding-right: 20px"><el-checkbox label="乙型肝炎表面抗原"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="乙型肝炎表面抗体"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="乙型肝炎e抗原"></el-checkbox></td>
                              </tr>
                              <tr>
                                <td style="padding-right: 20px"><el-checkbox label="乙型肝炎e抗体"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="乙型肝炎核心抗体"></el-checkbox></td>
                              </tr>
                            </table>
                          </el-checkbox-group>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" style="text-align:center">梅毒血清学试验</td>
                        <td colspan="3" style="text-align:left" class="pad10">
                          <el-radio-group v-model="editForm.syphilis" >
                            <el-radio label="阴性"></el-radio>
                            <el-radio label="阳性"></el-radio>
                          </el-radio-group>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" style="text-align:center">HIV抗体检测</td>
                        <td colspan="3" style="text-align:left" class="pad10">
                          <el-radio-group v-model="editForm.hivAntibody" >
                            <el-radio label="阴性"></el-radio>
                            <el-radio label="阳性"></el-radio>
                          </el-radio-group>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" style="text-align:center">B超</td>
                        <td colspan="3" style="text-align:left" class="pad10">
                          <el-input placeholder="B超" v-model="editForm.bMode" ></el-input>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" style="text-align:center">其他</td>
                        <td colspan="3" style="text-align:left" class="pad10">
                          <el-input placeholder="其他"  v-model="editForm.otherSassist" ></el-input>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center">总体评估</td>
                        <td colspan="5" style="text-align:left" class="pad10">
                          <el-radio-group v-model="editForm.population" >
                            <el-radio label="未见异常"></el-radio>
                            <el-radio label="异常"></el-radio>
                          </el-radio-group>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center">保健指导</td>
                        <td colspan="5" style="text-align:left" class="pad20">
                          <el-checkbox-group v-model="editForm.healthcare" >
                            <table>
                              <tr>
                                <td style="padding-right: 20px"><el-checkbox label="生活方式"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="心理"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="营养"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="避免致畸因素和疾病对胚胎的不良影响"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="产前筛查宣告通知"></el-checkbox></td>
                                <td style="padding-right: 20px"><el-checkbox label="其他"></el-checkbox></td>
                              </tr>
                            </table>
                          </el-checkbox-group>
                        </td>
                      </tr>
                      <tr>
                        <td style="word-wrap:break-word;text-align:center">转诊</td>
                        <td colspan="2" style="text-align:left" class="pad10">
                          <el-radio-group v-model="editForm.referral">
                            <el-radio label="无"></el-radio>
                            <el-radio label="有"></el-radio>
                          </el-radio-group>
                        </td>
                        <td colspan="3" class="pad10">
                          <table style="width: 100%;">
                            <tr>
                              <td>原因</td>
                              <td><el-input v-model="editForm.reason" ></el-input></td>
                              <td>机构及科室</td>
                              <td><el-input v-model="editForm.insandDep" ></el-input></td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                      <tr>
                        <td style="text-align:center">下次随访日期</td>
                        <td colspan="3" style="text-align:center" class="pad10">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.nextdate" style="width: 100%;" ></el-date-picker>
                        </td>
                        <td style="text-align:center">随访医生签名</td>
                        <td colspan="1" style="text-align:center" class="pad10">
                          <el-input v-model="editForm.doctor" ></el-input>
                        </td>
                      </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
                <!--老年人健康管理-->
                <div v-if="elderly1Show">
                  <h2 align="center">老年人中医院健康管理服务</h2>
                  <table  border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width:100%;">
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
                          <el-input v-model="editForm.peopleId" style="width: 100%" v-on:input="inputFunc" placeholder="请输入身份证"></el-input>
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
                          <el-input v-model="editForm.doctorSignature"  placeholder="请输入医生签名"></el-input>
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
                </div>
                <!--老年人评估-->
                <div v-if="elderlyShow">
                  <h2 align="center">老年人生活自理能力评估表</h2>
                  <table border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width: 100%">
                    <tbody style="color:#323232;">
                    <tr v-if="dialogStatus==='detail'">
                      <td style="word-wrap:break-word;text-align:center;">
                        创建人
                      </td>
                      <td colspan="2" class="pad10">
                        <el-form-item label="" label-width="0px">
                          <el-input v-model="editForm.creator" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;">
                        创建时间
                      </td>
                      <td colspan="4"  class="pad10">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr v-if="dialogStatus==='detail'">
                      <td style="word-wrap:break-word;text-align:center;width:100px;">
                        修改人
                      </td>
                      <td colspan="2" class="pad10">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-input v-model="editForm.modifier" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;width:100px;">
                        修改时间
                      </td>
                      <td colspan="4" class="pad10">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center;width:140px;" class="pad"><span>*</span>身份证号</td>
                      <td colspan="2" class="pad10" style="padding-right: 0px;">
                        <el-form-item label="" label-width="0px" prop="peopleId">
                          <el-input v-model="editForm.peopleId" v-on:input="inputFunc" placeholder="请选择"></el-input>
                        </el-form-item>
                      </td>
                      <td style="text-align:center;width:140px;" class="pad"><span>*</span>区域</td>
                      <td colspan="4" class="pad10" style="padding-right: 0px;">
                        <el-form-item label="" label-width="0px" prop="area">
                          <el-cascader :options="options" v-model="editForm.area" style="width: 100%" placeholder="请选择" disabled="false"></el-cascader>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center">工程编号</td>
                      <td colspan="1" class="pad10">
                        <el-input type="te1" v-model="editForm.workid"></el-input>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;width:140px;">姓名</td>
                      <td colspan="1" class="pad10">
                        <el-input type="te1112" v-model="editForm.name" disabled="false"></el-input>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;width:140px;">填表日期</td>
                      <td colspan="2" class="pad10">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.formDate" style="width: 100%;"></el-date-picker>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2" style="word-wrap:break-word;text-align:center">进餐：使用餐具将饭菜送入口、咀嚼、吞咽等活动</td>
                      <td colspan="6" class="pad10">
                        <el-select @change="score" v-model="editForm.meal" placeholder="请选择" style="width:100%;">
                          <el-option key="a" label="(0分)可自理：独立完成" value="可自理(0分)"></el-option>
                          <el-option key="b" label="(0分)轻度依赖" value="轻度依赖(0)分"></el-option>
                          <el-option key="c" label="(3分)中度依赖：需要协助、如切碎、搅拌食物" value="中度依赖(3)分"></el-option>
                          <el-option key="d" label="(5分)不能自理：完全需要帮助" value="不能自理(5)分"></el-option>
                        </el-select>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2" style="word-wrap:break-word;text-align:center;">梳洗：梳头、洗脸、刷牙、剃须、洗澡等活动</td>
                      <td colspan="6" class="pad10">
                        <el-select @change="score" v-model="editForm.washing" placeholder="请选择" style="width:100%;">
                          <el-option key="a1" label="(0分)可自理：独立完成" value="可自理(0分)"></el-option>
                          <el-option key="b1" label="(1分)轻度依赖：能独立洗头、梳头、洗脸、刷牙、剃须等;洗澡需要协助" value="轻度依赖(1)分"></el-option>
                          <el-option key="c1" label="(3分)中度依赖：在协助下和适当的时间内，能完成部分梳洗活动" value="中度依赖(3)分"></el-option>
                          <el-option key="d1" label="(7分)不能自理：完全需要帮助" value="不能自理(7)分"></el-option>
                        </el-select>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2" style="word-wrap:break-word;text-align:center">穿衣：穿衣服、袜子、鞋子等活动</td>
                      <td colspan="6" class="pad10">
                        <el-select @change="score" v-model="editForm.dressing" placeholder="请选择" style="width:100%;">
                          <el-option key="a2" label="(0分)可自理：独立完成" value="可自理(0分)"></el-option>
                          <el-option key="b2" label="(0分)轻度依赖" value="轻度依赖(0)分"></el-option>
                          <el-option key="c2" label="(3分)中度依赖：需要协助，在适当的时间内完成部分穿衣" value="中度依赖(3)分"></el-option>
                          <el-option key="d2" label="(5分)不能自理" value="不能自理(5)分"></el-option>
                        </el-select>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2" style="word-wrap:break-word;text-align:center">如厕：小便、大便等活动及自控</td>
                      <td colspan="6" class="pad10">
                        <el-select @change="score" v-model="editForm.toilet" placeholder="请选择" style="width:100%;">
                          <el-option key="a3" label="(0分)可自理：不需协助，可自控" value="可自理(0分)"></el-option>
                          <el-option key="b3" label="(1分)轻度依赖：偶尔失禁，但基本上能如厕或使用便具" value="轻度依赖(1)分"></el-option>
                          <el-option key="c3" label="(5分)中度依赖：经常失禁，在很多提示和协助下尚能如厕或使用便具" value="中度依赖(5)分"></el-option>
                          <el-option key="d3" label="(10分)不能自理：完全失禁，完全需要帮助" value="不能自理(10)分"></el-option>
                        </el-select>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2" style="word-wrap:break-word;text-align:center">活动：站立、室内行走、上下楼梯、户外活动</td>
                      <td colspan="6" class="pad10">
                        <el-select @change="score" v-model="editForm.activity" placeholder="请选择" style="width:100%;">
                          <el-option key="a4" label="(0分)可自理：独立完成所有活动" value="可自理(0分)"></el-option>
                          <el-option key="b4" label="(1分)轻度依赖：借助较小的外力或辅助装置能完成站立、行走、上下楼梯等" value="轻度依赖(1)分"></el-option>
                          <el-option key="c4" label="(5分)中度依赖：借助较大的外力才能完成站立、行走、不能上下楼梯等" value="中度依赖(5)分"></el-option>
                          <el-option key="d4" label="(10分)不能自理：卧床不起，活动完全需要帮助" value="不能自理(10)分"></el-option>
                        </el-select>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2" style="word-wrap:break-word;text-align:center">总得分</td>
                      <td colspan="6" class="pad10">
                        <el-input v-model="editForm.totalScore" auto-complete="off" readonly></el-input>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
                <!--精神病随访-->
                <div v-if="servicerecordShow">
                  <h2 align="center">严重精神障碍患者随访服务记录表</h2>
                  <table border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width:100%;">
                    <tbody style="color:#323232;">
                    <tr>
                      <td style="width: 140px;text-align:center"><span>*</span>身份证号</td>
                      <td colspan='2' style="text-align: left;padding-right: 0;" class="pad10">
                        <el-form-item label="" label-width="0" prop="peopleId">
                          <el-input v-model="editForm.peopleId" auto-complete="off" id="id1" placeholder="请输入身份证号码..." v-on:input="inputFunc1" style="width: 100%;text-align: left;"></el-input>
                        </el-form-item>
                      </td>
                      <td style="width: 140px;text-align:center"><span>*</span>姓名</td>
                      <td colspan='2'style="text-align: left;padding-right: 0;" class="pad10">
                        <el-form-item label="" label-width="0" prop="name">
                          <el-input v-model="editForm.name" placeholder="请输入姓名..."  disabled="false" style="width: 100%;"></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">随访日期</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.follDate" style="width: 100%;"></el-date-picker>
                      </td>
                      <td style="width: 140px;text-align:center">本次随访形式</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.formFollow" style="width: 300px;">
                          <el-radio label="门诊"></el-radio>
                          <el-radio label="家庭视访"></el-radio>
                          <el-radio label="电话"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">若失访，原因</td>
                      <td colspan='6' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.lostReason" style="width: 100%;">
                          <el-radio label="外出打工"></el-radio>
                          <el-radio label="迁居他地"></el-radio>
                          <el-radio label="走失"></el-radio>
                          <el-radio label="连续3次未到访"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">如死亡，请选择日期</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.dateDeath" style="width: 100%;"></el-date-picker>
                      </td>
                      <td style="width: 140px;text-align:center">如死亡，请选择原因</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-cascader :options="Death" placeholder="请选择" v-model="causeDeath"  style="width: 100%;"></el-cascader>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">危险性评估</td>
                      <td colspan='6' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.riskAssess" >
                          <el-radio label="0级"></el-radio>
                          <el-radio label="1级"></el-radio>
                          <el-radio label="2级"></el-radio>
                          <el-radio label="3级"></el-radio>
                          <el-radio label="4级"></el-radio>
                          <el-radio label="5级"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">目前症状</td>
                      <td colspan='6' class="pad20" style="text-align: left;">
                        <el-checkbox-group v-model="editForm.currentSymptoms" style=" width: 100%;">
                          <table>
                            <tr>
                              <td style="padding-right: 25px"><el-checkbox label="幻觉" name="type"></el-checkbox></td>
                              <td style="padding-right: 25px"><el-checkbox label="交流困难" name="type"></el-checkbox></td>
                              <td style="padding-right: 25px"><el-checkbox label="猜疑" name="type"></el-checkbox></td>
                              <td style="padding-right: 25px"><el-checkbox label="喜怒无常" name="type"></el-checkbox></td>
                              <td style="padding-right: 25px"><el-checkbox label="行为怪异" name="type"></el-checkbox></td>
                              <td style="padding-right: 25px"><el-checkbox label="兴奋话多" name="type"></el-checkbox></td>
                              <td style="padding-right: 25px"><el-checkbox label="伤人伤物" name="type"></el-checkbox></td>
                            </tr>
                            <tr>
                              <td style="padding-right: 25px"><el-checkbox label="悲观厌世" name="type"></el-checkbox></td>
                              <td style="padding-right: 25px"><el-checkbox label="无故外走" name="type"></el-checkbox></td>
                              <td style="padding-right: 25px"><el-checkbox label="自语自笑" name="type"></el-checkbox></td>
                              <td style="padding-right: 25px"><el-checkbox label="孤僻懒散" name="type"></el-checkbox></td>
                              <td style="padding-right: 25px"><el-checkbox label="其他" name="type"></el-checkbox></td>
                            </tr>
                          </table>
                        </el-checkbox-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">自知力</td>
                      <td colspan='6' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.selfKnowLedge" >
                          <el-radio label="1 自知力完全"></el-radio>
                          <el-radio label="2 自知力不全"></el-radio>
                          <el-radio label="3 自知力缺失"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">睡眠情况</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.sleepCondition" style="width: 300px;">
                          <el-radio label="1 良好"></el-radio>
                          <el-radio label="2 一般"></el-radio>
                          <el-radio label="3 较差"></el-radio>
                        </el-radio-group>
                      </td>
                      <td style="width: 140px;text-align:center">饮食情况</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.dietCondition" style="width: 300px;">
                          <el-radio label="1 良好"></el-radio>
                          <el-radio label="2 一般"></el-radio>
                          <el-radio label="3 较差"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">个人生活料理</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.pelLifeCondition" style="width: 300px;">
                          <el-radio label="1 良好"></el-radio>
                          <el-radio label="2 一般"></el-radio>
                          <el-radio label="3 较差"></el-radio>
                        </el-radio-group>
                      </td>
                      <td style="width: 140px;text-align:center">家务劳动</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.houseWork" style="width: 300px;">
                          <el-radio label="1 良好"></el-radio>
                          <el-radio label="2 一般"></el-radio>
                          <el-radio label="3 较差"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">学习能力</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.abilityLearn" style="width: 300px;">
                          <el-radio label="1 良好"></el-radio>
                          <el-radio label="2 一般"></el-radio>
                          <el-radio label="3 较差"></el-radio>
                        </el-radio-group>
                      </td>
                      <td style="width: 140px;text-align:center">社会人际交往</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.socialCommu" style="width: 300px;">
                          <el-radio label="1 良好"></el-radio>
                          <el-radio label="2 一般"></el-radio>
                          <el-radio label="3 较差"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">生产劳动工作</td>
                      <td colspan='6' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.proAndWork" style="width: 300px;">
                          <el-radio label="1 良好"></el-radio>
                          <el-radio label="2 一般"></el-radio>
                          <el-radio label="3 较差"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">危险行为</td>
                      <td colspan='6' class="pad20" style="text-align: left;">
                        <el-checkbox-group v-model="editForm.dangerBeha" style=" width: 100%;">
                          <el-checkbox v-for="item in dangerBeha" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">两次随访间关锁情况</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-select v-model="editForm.twoLockCondition" placeholder="请选择" style="width: 300px;">
                          <el-option key="1" label="1 无关锁" value="无关锁"></el-option>
                          <el-option key="2" label="2 关锁" value="关锁"></el-option>
                          <el-option key="3" label="3 关锁已解除" value="关锁已解除"></el-option>
                        </el-select>
                      </td>
                      <td style="width: 140px;text-align:center">两次随访间住院情况</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-select v-model="editForm.twoInHospCondition" placeholder="请选择" style="width: 100%;">
                          <el-option key="1" label="1 未住院" value="未住院"></el-option>
                          <el-option key="2" label="2 目前正在住院" value="目前正在住院"></el-option>
                          <el-option key="3" label="3 曾住院，现未住院" value="曾住院，现未住院"></el-option>
                        </el-select>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">末次出院时间</td>
                      <td colspan='6' class="pad10" style="text-align: left;">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.outHospitalDate" style="width: 300px;"></el-date-picker>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">实验室检查</td>
                      <td colspan='6' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.laborExamin">
                          <el-radio label="1 无"></el-radio>
                          <el-radio label="2 有">
                            <el-input  placeholder="2 有（请输入详情）"></el-input>
                          </el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">用药依从性</td>
                      <td colspan='6' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.medCompliance" >
                          <el-radio label="1 按医嘱规律用药"></el-radio>
                          <el-radio label="2 间断用药"></el-radio>
                          <el-radio label="3 不用药"></el-radio>
                          <el-radio label="4 医嘱勿需用药"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">药物不良反应</td>
                      <td colspan='6' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.badDrugReact" >
                          <el-radio label="1 无"></el-radio>
                          <el-radio label="2 有">
                            <el-input  placeholder="2 有（请输入详情）"></el-input>
                          </el-radio>
                          <el-radio label="3 此项不适用"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">治疗效果</td>
                      <td colspan='6' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.treatEffect" >
                          <el-radio label="痊愈"></el-radio>
                          <el-radio label="好转"></el-radio>
                          <el-radio label="无变化"></el-radio>
                          <el-radio label="加重"></el-radio>
                          <el-radio label="此项不适用"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">是否转诊</td>
                      <td colspan='6' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.ifReferral" >
                          <el-radio label="否"></el-radio>
                          <el-radio label="是"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">转诊原因</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.referralReason" style="width: 100%;"></el-input>
                      </td>
                      <td style="width: 140px;text-align:center">转诊至机构及科室</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.refToDepart" style="width: 100%;"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center" rowspan="3">用药情况</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.useDrugCondiA" placeholder="药物名称1" style="width: 100%;"></el-input>
                      </td>
                      <td style="width: 140px;text-align:center" rowspan="3">用法用量</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.useMeasurementA" placeholder="每日（月）次数，每次用量（药物1）"style="width: 100%;"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.useDrugCondiB" placeholder="药物名称2" style="width: 100%;"></el-input>
                      </td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.useMeasurementB" placeholder="每日（月）次数，每次用量（药物2）"style="width: 100%;"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.useDrugCondiC" placeholder="药物名称3" style="width: 100%;"></el-input>
                      </td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.useMeasurementC" placeholder="每日（月）次数，每次用量（药物3）"style="width: 100%;"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center" rowspan="3">用药指导</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.guideUseDrugA" placeholder="药物名称1" style="width: 100%;"></el-input>
                      </td>
                      <td style="width: 140px;text-align:center" rowspan="3">用法用量</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.guideMeasurementA" placeholder="每日（月）次数，每次用量（药物1）"style="width: 100%;"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.guideUseDrugB" placeholder="药物名称2" style="width: 100%;"></el-input>
                      </td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.guideMeasurementB" placeholder="每日（月）次数，每次用量（药物2）"style="width: 100%;"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.guideUseDrugC" placeholder="药物名称3" style="width: 100%;"></el-input>
                      </td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.guideMeasurementC" placeholder="每日（月）次数，每次用量（药物3）"style="width: 100%;"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">康复措施</td>
                      <td colspan='6' class="pad20" style="text-align: left;">
                        <el-checkbox-group v-model="editForm.rehaMeasure" style=" width: 100%;">
                          <el-checkbox v-for="item in rehaMeasure" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">本次随访分类</td>
                      <td colspan='6' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.thisClassifi">
                          <el-radio label="不稳定"></el-radio>
                          <el-radio label="基本稳定"></el-radio>
                          <el-radio label="稳定"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">下次随访日期</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.nextDate" style="width: 100%;"></el-date-picker>
                      </td>
                      <td style="width: 140px;text-align:center">医生签字</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.docSign" style="width: 100%;"></el-input>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
                <!--2糖-->
                <div v-if="diabetesShow">
                  <h2 align="center">2型糖尿病患者健康管理</h2>
                  <table border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width:100%">
                    <tbody>

                    <tr>
                      <td style="width:140px;word-wrap:break-word;text-align:center;"><span>*</span>姓名</td>
                      <td colspan="3" class="pad10" style="padding-right: 0px;width:420px">
                        <el-form-item  label=""  label-width="0px" prop="name">
                          <el-input v-model="editForm.name" auto-complete="off" disabled placeholder="请输入姓名" style="width: 100%;"></el-input>
                        </el-form-item>
                      </td>
                      <td style="width:140px;word-wrap:break-word;text-align:center;"><span>*</span>身份证号</td>
                      <td colspan="3"  class="pad10" style="padding-right: 0px;width:420px">
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
                      <td style="word-wrap:break-word;text-align:center">第几次随访</td>
                      <td colspan="7" style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.followCount">
                          <table>
                            <tr>
                              <td style="padding-right: 5px"><el-radio label=第一次>第一次</el-radio></td>
                              <td style="padding-right: 5px"><el-radio label=第二次>第二次</el-radio></td>
                              <td style="padding-right: 5px"><el-radio label=第三次>第三次</el-radio></td>
                              <td style="padding-right: 5px"><el-radio label=第四次>第四次</el-radio></td>
                            </tr>
                          </table>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan="3"style="word-wrap:break-word;width:80px;text-align:center"> 体征</td>
                      <td style="word-wrap:break-word;text-align:center">足背动脉搏动</td>
                      <td colspan="3"class="pad10" style="padding-right: 0px;">
                        <el-form-item label="" label-width="0px" aria-placeholder="请选择">
                          <el-cascader :options="optionsex" v-model="editForm.footArteyPulse" style="width:100%" ></el-cascader>
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
                </div>
                <!--肺结核随访服务-->
                <div v-if="tuberculosisfllowupShow">
                  <h2 align="center">肺结核患者随访记录</h2>
                  <table border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width:100%">
                    <tbody>
                    <tr>
                      <td style="width: 140px;word-wrap:break-word;text-align:center;" class="pad10">
                        <span>*</span>身份证号
                      </td>
                      <td style="text-align:center;" class="pad10">
                        <el-input v-model="editForm.peopleId" auto-complete="off"  v-on:input="inputFunc1" placeholder="请输入身份证号"></el-input>
                      </td>
                      <td style="width:140px;word-wrap:break-word;text-align:center;" class="pad"><span>*</span>姓名</td>
                      <td colspan="2" style="text-align:center;" class="pad10">
                        <el-input v-model="editForm.name" auto-complete="off" disabled></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;width: 140px;text-align:center;" ><span>*</span>随访时间</td>
                      <td  class="pad10">
                        <el-date-picker type="date" placeholder="请输入随访时间" v-model="editForm.flowUpTime" style="width: 100%;"></el-date-picker>
                      </td>
                      <td style="text-align:center">治疗月序</td>
                      <td colspan='2' style="text-align:left" class="pad10">
                        <el-input-number v-model="editForm.treatmentMonth" :min="1" :max="200">
                        </el-input-number>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;width: 140px;text-align:center">督导人员</td>
                      <td colspan='1' style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.superivisor">
                          <table>
                            <tr>
                              <td><el-radio label=医生>医生</el-radio></td>
                              <td><el-radio label=家属>家属</el-radio></td>
                              <td><el-radio label=自服药>自服药</el-radio></td>
                              <td><el-radio label=其他>其他</el-radio></td>
                            </tr>
                          </table>
                        </el-radio-group>
                      </td>
                      <td style="width: 19%;text-align:center">随访方式</td>
                      <td colspan='2' style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.followUpMethods">
                          <el-radio label=门诊>门诊</el-radio>
                          <el-radio label=家庭>家庭</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan='3' style="word-wrap:break-word;width: 140px;text-align:center">症状及体征</td>
                      <td colspan='4' style="text-align:left" class="pad20">
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
                      </td>
                    </tr>
                    <td rowspan='2' style="text-align:center;">其他</td>
                    <td rowspan='2' colspan='3'class="pad10">
                      <el-input v-model="editForm.otherSymptoms" ></el-input>
                    </td>
                    <tr></tr>


                    <tr>
                      <td rowspan='2' style="word-wrap:break-word;width: 140px;text-align:center">生活方式评估</td>
                      <td style="text-align:center;">吸烟</td>
                      <td colspan='3' class="pad10">
                        <el-input  v-model="editForm.smoking"  >
                          <template slot="append">支/天</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center;">饮酒</td>
                      <td colspan='3' class="pad10">
                        <el-input  v-model="editForm.alcohol"  >
                          <template slot="append">两/天</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan='4' style="width: 140px;word-wrap:break-word;text-align:center">用药</td>
                      <td style="text-align:center;" >化疗方案</td>
                      <td colspan='3' class="pad10" >
                        <el-input v-model="editForm.chemotherapyRegimen" ></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td  style="text-align:center;">用法</td>
                      <td colspan='3'style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.usages">
                          <el-radio label=每日>每日</el-radio>
                          <el-radio label=间歇>间歇</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td  style="text-align:center;">药品剂型</td>
                      <td colspan='3' style="text-align:left" class="pad20">
                        <el-checkbox-group v-model="editForm.drugDosage">
                          <el-checkbox v-for="item in drugDosage" :key="item.value":name="type":label="item.label":value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center;">漏服药次数</td>
                      <td colspan='3' style="text-align:center;" class="pad10">
                        <el-input  v-model="editForm.numbermissedMedications"  >
                          <template slot="append">次</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;word-wrap:break-word;text-align:center">药物不良反应</td>
                      <td colspan='1' style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.adverseDrugReactions">
                          <table>
                            <tr>
                              <td><el-radio label=无>无</el-radio></td>
                              <td><el-radio label=有>有</el-radio></td>
                              <td class="pad10"><el-input  v-model="editForm.adverseReactions" placeholder="不良反应" style="width: 100%" ></el-input></td>
                            </tr>
                          </table>
                        </el-radio-group>
                      </td>
                      <td style="width: 19%;text-align:center">并发症或合并症</td>
                      <td colspan='2' style="text-align:left" class="pad20">
                        <el-radio-group v-model="editForm.complicationComorbidities">
                          <table>
                            <tr>
                              <td><el-radio label=无>无</el-radio></td>
                              <td><el-radio label=有>有</el-radio></td>
                              <td class="pad10"><el-input  v-model="editForm.complications" placeholder="并发症或合并症" style="width: 100%" ></el-input></td>
                            </tr>
                          </table>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan='3' style="width: 140px;word-wrap:break-word;text-align:center">转诊</td>
                      <td  style="text-align:center;">科别</td>
                      <td colspan='3' class="pad10">
                        <el-input  v-model="editForm.subjects"  ></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center;">原因</td>
                      <td colspan='3' class="pad10">
                        <el-input  v-model="editForm.causes"  ></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td  style="text-align:center;">2周内随访，随访结果</td>
                      <td colspan='3' class="pad10"style=" padding-top: 5px;padding-bottom: 5px;">
                        <el-input  v-model="editForm.twoweeksResult"  type="textarea" rows="4"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;word-wrap:break-word;text-align:center">处理意见</td>
                      <td colspan='4' style="text-align:center;padding-top: 5px;padding-bottom: 5px;" class="pad10">
                        <el-input  v-model="editForm.treamntOptions"  type="textarea" rows="2" ></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center">下次随访时间</td>
                      <td colspan='1' class="pad10">
                        <el-date-picker type="date"  v-model="editForm.nextFollotime" style="width: 100%;"></el-date-picker>
                      </td>
                      <td style="text-align:center">随访医生签名</td>
                      <td colspan='2' class="pad10">
                        <el-input  v-model="editForm.followdoctorSignature" auto-complete="off" ></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">停止治疗及原因</td>
                      <td colspan='4'  >
                        <table  style="width: 100%; " border="1"   cellspacing="0" border-collapse=collapse; bordercolor="#dddddd">
                          <tr >
                            <td class="pad10"  style=" border-right: 0;padding-top: 10px; text-align:center " >
                              出现停止治疗时间：
                            </td>
                            <td class="pad10" style=" border-right: 0;"  >
                              <el-date-picker type="date"  v-model="editForm.stopTime" style="width: 100%;"></el-date-picker>
                            </td>
                          </tr>
                          <tr >
                            <td class="pad10"  style="text-align:center ; border-right: 0;">
                              停止治疗原因：
                            </td>
                            <td   style=" border-right: 0;text-align:left " class="pad20 ">
                              <el-radio-group v-model="editForm.stopTreatment">
                                <el-radio label=完成治疗>完成治疗</el-radio>
                                <el-radio label=死亡>死亡</el-radio>
                                <el-radio label=丢失>丢失</el-radio>
                                <el-radio label=转入耐多药治疗>转入耐多药治疗</el-radio>
                              </el-radio-group>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center"  rowspan='2' >全程管理情况</td>
                      <td colspan='4'style="text-align:left;padding-top: 5px;padding-bottom: 5px;" class="pad20">
                        <table  style="width: 100%;" >
                          <tr class="pad10" >
                            <td class="pad10"  style="padding-top: 10px;">
                              应访视患者  <el-input style="width:  20.3%"  placeholder="请输入"v-model="editForm.shouldVisit" auto-complete="off" >
                              <template slot="append">次</template>
                            </el-input>，实际访视 <el-input style="width:  20.3%" placeholder="请输入" v-model="editForm.actualVisit" auto-complete="off" >
                              <template slot="append">次</template>
                            </el-input>;
                            </td>
                          </tr>
                          <tr >
                            <td class="pad10" style="padding-top: 20px;padding-bottom: 10px">
                              患者在疗程中，应服药 <el-input style="width: 20.3%" placeholder="请输入" @change="score"v-model="editForm.mnumner"  >
                              <template slot="append">次</template>
                            </el-input>，实际服药<el-input style="width: 20.3%"  placeholder="请输入"@change="score" v-model="editForm.amnumber" auto-complete="off"   >
                              <template slot="append">次</template>
                            </el-input>，服药率  <el-input style="width: 20.3%" placeholder="请输入" v-model="editForm.medicineRate"auto-complete="off">
                              <template slot="append">%</template>
                            </el-input>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center">评估医生签名</td>
                      <td colspan='3'class="pad10" >
                        <el-input  v-model="editForm.evaluationDoctor" auto-complete="off" ></el-input>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
                <!--肺结核-->
                <div v-if="tuberculosispatientsShow">
                  <h2 align="center">肺结核患者健康管理</h2>
                  <table border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width: 100%">
                    <tbody>
                    <tr>
                      <td style="width:140px;word-wrap:break-word;text-align:center;">
                        <span>*</span>身份证号
                      </td>
                      <td style="text-align:center;" class="pad10">
                        <el-input v-model="editForm.peopleId" auto-complete="off" v-on:input="inputFunc1" placeholder="请输入身份证号"  ></el-input>
                      </td>
                      <td style="width:140px;word-wrap:break-word;text-align:center;"><span>*</span>区域</td>
                      <td colspan="2" style="text-align:center;" class="pad10">
                        <el-input v-model="editForm.area" auto-complete="off"   disabled  ></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style=" width:140px;word-wrap:break-word;text-align:center">
                        <span>*</span>姓名
                      </td>
                      <td style="text-align:center; " class="pad10">
                        <el-input v-model="editForm.name" auto-complete="off" disabled></el-input>
                      </td>
                      <td style="width:80px;word-wrap:break-word;text-align:center;">责任医生</td>
                      <td colspan="2" style="text-align:center" class="pad10">
                        <el-input v-model="editForm.responDoctor" auto-complete="off"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style=" width:140px;word-wrap:break-word;text-align:center;">
                        <span>*</span>规则服药
                      </td>
                      <td style="text-align:left;" class="pad20" >
                        <el-radio-group v-model="editForm.ruleMedicine"  >
                          <el-radio label=是>是</el-radio>
                          <el-radio label=否>否</el-radio>
                        </el-radio-group>
                      </td>
                      <td style="width:80px;word-wrap:break-word;text-align:center;"><span>*</span>生存状态</td>
                      <td colspan="2" style="text-align:left;" class="pad20" >
                        <el-radio-group v-model="editForm.lifeState">
                          <el-radio label=存活>治疗中</el-radio>
                          <el-radio label=完成治疗>完成治疗</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style=" width:140px;text-align:center"><span>*</span>随访时间</td>
                      <td colspan='4'class="pad10" >
                        <el-date-picker type="date" placeholder="请输入随访时间" v-model="editForm.followTime" style="width: 100%;"></el-date-picker>
                      </td>
                    </tr>
                    <tr>
                      <td style="width:140px;word-wrap:break-word;text-align:center">随访方式</td>
                      <td colspan='1'style="text-align:left;" class="pad20" >
                        <el-radio-group v-model="editForm.followMethod">
                          <el-radio label=门诊>门诊</el-radio>
                          <el-radio label=家庭>家庭</el-radio>
                        </el-radio-group>
                      </td>
                      <td style="width: 19%;text-align:center">患者类型</td>
                      <td colspan='2' style="text-align:left;" class="pad20" >
                        <el-radio-group v-model="editForm.patienType">
                          <el-radio label=初治>初治</el-radio>
                          <el-radio label=复治>复治</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width:140px;word-wrap:break-word;text-align:center">痰菌情况</td>
                      <td colspan='1' style="text-align:left;" class="pad10" >
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
                      </td>
                      <td style="text-align:center;">耐药情况</td>
                      <td colspan='2' style="text-align:left;" class="pad20" >
                        <el-radio-group v-model="editForm.drugStatus">
                          <el-radio label=耐药>耐药</el-radio>
                          <el-radio label=非耐药>非耐药</el-radio>
                          <el-radio clabel=未检测>未检测</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan='3' style="width:140px;word-wrap:break-word;text-align:center">症状及体征</td>
                      <td colspan='4' style="text-align:left;" class="pad20" >
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
                      </td>
                    </tr>
                    <td rowspan='2'style="text-align:center;"  >其他</td>
                    <td rowspan='2' colspan='3' style="text-align:center;" class="pad10">
                      <el-input v-model="editForm.otherSymptoms" auto-complete="off"></el-input>
                    </td>
                    <tr></tr>

                    <tr>
                      <td rowspan='3' style="width:140px;word-wrap:break-word;text-align:center">用药</td>
                      <td style="text-align:center;">化疗方案</td>
                      <td colspan='3' class="pad10">
                        <el-input v-model="editForm.chemotherapyRegimen" ></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center;">用法</td>
                      <td colspan='3' style="text-align:left;" class="pad20">
                        <el-radio-group v-model="editForm.usages">
                          <el-radio label=每日>每日</el-radio>
                          <el-radio label=间歇>间歇</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center;">药品剂型</td>
                      <td colspan='3' style="text-align:left;" class="pad20">
                        <el-checkbox-group v-model="editForm.drugDosage">
                          <el-checkbox v-for="item in drugDosage" :key="item.value":name="type":label="item.label":value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="width:140px;text-align:center">督导人员选择</td>
                      <td colspan='4' style="text-align:left;" class="pad20">
                        <el-radio-group v-model="editForm.supervisorSelection">
                          <el-radio label=医生>医生</el-radio>
                          <el-radio label=家属>家属</el-radio>
                          <el-radio label=自服药>自服药</el-radio>
                          <el-radio label=其他>其他</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan='2' style="word-wrap:break-word;width:140px;text-align:center">家庭居住环境评估</td>
                      <td style="text-align:center;">单独的居室</td>
                      <td colspan='3' style="text-align:left;" class="pad20">
                        <el-radio-group v-model="editForm.separateRoom">
                          <el-radio label=有>有</el-radio>
                          <el-radio label=无>无</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center;">通风情况</td>
                      <td colspan='3' style="text-align:left;" class="pad20">
                        <el-radio-group v-model="editForm.ventilation">
                          <el-radio label=良好>良好</el-radio>
                          <el-radio label=一般>一般</el-radio>
                          <el-radio label=差>差</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan='2' style="word-wrap:break-word;width:140px;text-align:center">生活方式评估</td>
                      <td style="text-align:center;">吸烟</td>
                      <td colspan='3'>
                        <el-input  v-model="editForm.smoking"  >
                          <template slot="append">支/天</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center;">饮酒</td>
                      <td colspan='3'>
                        <el-input  v-model="editForm.drinking"  >
                          <template slot="append">两/天</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan='10' style="word-wrap:break-word;width:140px;text-align:center">健康教育及培训</td>
                      <td colspan='2' style="text-align:center;">取药地点、时间</td>
                      <td  colspan='2'style="text-align:center;" class="pad10">
                        <table align="center">
                          <tr>
                            <td tyle="text-align:right;padding:0px;">地点</td>
                            <td tyle="text-align:right;padding:0px;"class="pad10"><el-input  v-model="editForm.takemedicinePlace" ></el-input></td>
                          </tr>
                          <tr>
                            <td tyle="text-align:right;padding:0px;">时间</td>
                            <td tyle="text-align:right;padding:0px;" class="pad10"><el-date-picker type="date" v-model="editForm.takemedicineTime" ></el-date-picker></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td  colspan='2'style="text-align:center;">服药记录卡的填写</td>
                      <td  colspan='2' style="text-align:left;" class="pad20">
                        <el-radio-group v-model="editForm.medicationRecordcard">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td  colspan='2' style="text-align:center;">服药方法及药品存放</td>
                      <td  colspan='2' style="text-align:left;" class="pad20">
                        <el-radio-group v-model="editForm.medicationmethodDrugstorage">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td  colspan='2' style="text-align:center;">肺结核治疗疗程</td>
                      <td  colspan='2' style="text-align:left;" class="pad20">
                        <el-radio-group v-model="editForm.treatmentTuberculosis">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td  colspan='2' style="text-align:center;">不规律服药危害</td>
                      <td  colspan='2' style="text-align:left;" class="pad20">
                        <el-radio-group v-model="editForm.irregularMedication">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td  colspan='2' style="text-align:center;">服药后不良反应及处理</td>
                      <td  colspan='2' style="text-align:left;" class="pad20">
                        <el-radio-group v-model="editForm.adverseReactions">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td  colspan='2' style="text-align:center;">治疗期间复诊查痰</td>
                      <td  colspan='2' style="text-align:left;" class="pad20">
                        <el-radio-group v-model="editForm.review">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td  colspan='2' style="text-align:center;">外出期间如何坚持服药</td>
                      <td  colspan='2'style="text-align:left;" class="pad20">
                        <el-radio-group v-model="editForm.howtakeMedicine">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td  colspan='2' style="text-align:center;">生活习惯及注意事项</td>
                      <td  colspan='2' style="text-align:left;" class="pad20">
                        <el-radio-group v-model="editForm.habitsPrecautions">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td  colspan='2' style="text-align:center;">密切接触者检查</td>
                      <td  colspan='2' style="text-align:left;" class="pad20">
                        <el-radio-group v-model="editForm.closeCheck">
                          <el-radio label=掌握>掌握</el-radio>
                          <el-radio label=未掌握>未掌握</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width:140px;text-align:center">下次随访时间</td>
                      <td colspan='4' style="text-align:center;" class="pad10">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.nextFollowtie" style="width: 100%;" ></el-date-picker>
                      </td>
                    </tr>
                    <tr>
                      <td style="width:140px;;text-align:center">评估医生签名</td>
                      <td colspan='4' style="text-align:center;" class="pad10">
                        <el-input  v-model="editForm.evaluationDoctorsignature" auto-complete="off" ></el-input>
                      </td>
                    </tr>

                    </tbody>
                  </table>
                </div>
                <!--精神病补充-->
                <div v-if="personalinformationShow">
                  <h2 align="center">严重精神障碍患者补充信息</h2>
                  <table border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width:100%">
                    <tbody style="color:#323232;">
                    <tr>
                      <td style="width: 140px;text-align:center"><span>*</span>身份证号</td>
                      <td colspan='2' style="text-align: left;padding-right: 0;width: 280px" class="pad10">
                        <el-form-item label="" label-width="0px" prop="peopleId">
                          <el-input v-model="editForm.peopleId" auto-complete="off" placeholder="请输入身份证号码..."  style="width: 100%;" v-on:input="inputFunc1" ></el-input>
                        </el-form-item>
                      </td>
                      <td style="width: 140px;text-align:center"><span>*</span>姓名</td>
                      <td colspan='2' class="pad10" style="text-align: left;padding-right: 0;width: 280px">
                        <el-form-item label="" label-width="0px" prop="name">
                          <el-input v-model="editForm.name" auto-complete="off" placeholder="请输入姓名..."  style="width: 100%;" disabled = 'false'></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">监护人姓名</td>
                      <td colspan='2' class="pad10" style="text-align: left;"><el-input v-model="editForm.guardianName" style="width: 100%;"></el-input></td>
                      <td style="width: 140px;text-align:center">与患者关系</td>
                      <td colspan='2' class="pad10" style="text-align: left;"><el-input v-model="editForm.relaPatients" style="width: 100%;"></el-input></td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">监护人住址</td>
                      <td colspan='2' class="pad10" style="text-align: left;"><el-input v-model="editForm.guardianAddre" style="width: 100%;"></el-input></td>
                      <td style="width: 140px;text-align:center">监护人电话</td>
                      <td colspan='2' class="pad10" style="text-align: left;"><el-input v-model="editForm.guardPhone" style="width: 100%;"></el-input></td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">辖区村(居)委会联系人</td>
                      <td colspan='2' class="pad10" style="text-align: left;"><el-input v-model="editForm.commitPersonName" style="width: 100%;"></el-input></td>
                      <td style="width: 140px;text-align:center">村(居)委会联系人电话</td>
                      <td colspan='2' class="pad10" style="text-align: left;"><el-input v-model="editForm.commitPersonNum" style="width: 100%;"></el-input></td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">户别</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.accountType" style="width: 100%;">
                          <el-radio label="城镇"></el-radio>
                          <el-radio label="农村"></el-radio>
                        </el-radio-group>
                      </td>
                      <td style="width: 140px;text-align:center">就业情况</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-select v-model="editForm.employSituat" placeholder="请选择" style="width: 100%">
                          <el-option key="1" label="在岗工人" value="在岗工人"></el-option>
                          <el-option key="2" label="在岗管理者" value="在岗管理者"></el-option>
                          <el-option key="3" label="农民" value="农民"></el-option>
                          <el-option key="4" label="下岗或无业" value="下岗或无业"></el-option>
                          <el-option key="5" label="在校学生" value="在校学生"></el-option>
                          <el-option key="6" label="退休" value="退休"></el-option>
                          <el-option key="7" label="专业技术人员" value="专业技术人员"></el-option>
                          <el-option key="8" label="其他" value="其他"></el-option>
                          <el-option key="9" label="不详" value="不详"></el-option>
                        </el-select>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">知情同意</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.infConsent" style="width: 100%;">
                          <el-radio label="同意参加管理"></el-radio>
                          <el-radio label="不同意参加管理"></el-radio>
                        </el-radio-group>
                      </td>
                      <td style="width: 140px;text-align:center">签字人</td>
                      <td colspan='2' class="pad10" style="text-align: left;"><el-input v-model="editForm.signatory" auto-complete="off" style="width: 100%;"></el-input></td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">签字时间</td>
                      <td colspan='2' class="pad10" style="text-align: left;"><el-date-picker type="date" placeholder="选择日期" v-model="editForm.signatureDate" style="width: 100%;"></el-date-picker></td>
                      <td style="width: 140px;text-align:center">初次发病时间</td>
                      <td colspan='2' class="pad10" style="text-align: left;"><el-date-picker type="date" placeholder="选择日期" v-model="editForm.firstOnsetTime" style="width: 100%;"></el-date-picker></td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">既往主要症状</td>
                      <td colspan='5'class="pad20" style="text-align: left;">
                        <el-checkbox-group v-model="editForm.pastSymptoms" style=" width: 100%;">
                          <table>
                            <tr>
                              <td style="padding-right: 35px"><el-checkbox label="幻觉" name="type"></el-checkbox></td>
                              <td style="padding-right: 35px"><el-checkbox label="交流困难" name="type"></el-checkbox></td>
                              <td style="padding-right: 35px"><el-checkbox label="猜疑" name="type"></el-checkbox></td>
                              <td style="padding-right: 35px"><el-checkbox label="喜怒无常" name="type"></el-checkbox></td>
                              <td style="padding-right: 35px"><el-checkbox label="行为怪异" name="type"></el-checkbox></td>
                              <td style="padding-right: 35px"><el-checkbox label="兴奋话多" name="type"></el-checkbox></td>
                            </tr>
                            <tr>
                              <td style="padding-right: 35px"><el-checkbox label="伤人伤物" name="type"></el-checkbox></td>
                              <td style="padding-right: 35px"><el-checkbox label="悲观厌世" name="type"></el-checkbox></td>
                              <td style="padding-right: 35px"><el-checkbox label="无故外走" name="type"></el-checkbox></td>
                              <td style="padding-right: 35px"><el-checkbox label="自语自笑" name="type"></el-checkbox></td>
                              <td style="padding-right: 35px"><el-checkbox label="孤僻懒散" name="type"></el-checkbox></td>
                              <td style="padding-right: 35px"><el-checkbox label="其他" name="type"></el-checkbox></td>
                            </tr>
                          </table>
                        </el-checkbox-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">既往关锁情况</td>
                      <td colspan='5' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.pastLocking" style="width: 100%;">
                          <el-radio label="无关锁"></el-radio>
                          <el-radio label="关锁"></el-radio>
                          <el-radio label="关锁已解除"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">门诊</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-select v-model="editForm.outService" placeholder="请选择" style="width: 100%;">
                          <el-option key="1" label="未治疗" value="未治疗"></el-option>
                          <el-option key="2" label="间断门诊治疗" value="间断门诊治疗"></el-option>
                          <el-option key="3" label="连续门诊治疗" value="连续门诊治疗"></el-option>
                        </el-select>
                      </td>
                      <td style="width: 140px;text-align:center">首次抗精神治疗时间</td>
                      <td colspan='2' class="pad10" style="text-align: left;"> <el-date-picker type="date" placeholder="选择日期" v-model="editForm.firstAntipsyTreat" style="width: 100%;"></el-date-picker></td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">曾住精神医院次数</td>
                      <td colspan='5' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.inHospital" style="width: 100%;">
                          <template slot="append">次</template>
                        </el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">目前整段情况诊断</td>
                      <td colspan='5' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.diagnosis" placeholder="诊断" style="width: 100px;" ></el-input>
                        <el-input v-model="editForm.hospitalDia"  placeholder="确诊医院" style="width: 100px;"></el-input>
                        <el-date-picker type="date" placeholder="确诊日期" v-model="editForm.confiDate" style="width: 100px;"></el-date-picker>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">最近一次治疗效果</td>
                      <td colspan='5' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.lastTreatEff" style="width: 100%;">
                          <el-radio label="临床痊愈"></el-radio>
                          <el-radio label="好转"></el-radio>
                          <el-radio label="无变化"></el-radio>
                          <el-radio label="加重"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">危险行为</td>
                      <td colspan='5' class="pad20" style="text-align: left;">
                        <el-checkbox-group v-model="editForm.dangerBehav" style="width:100%">
                          <el-checkbox v-for="item in dangerBeha" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">经济状况</td>
                      <td colspan='5' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.stateEconomy" style="width:100%">
                          <el-radio label="贫困，在当地平困线标准以下"></el-radio>
                          <el-radio label="非贫困"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">专科医生的建议(如果有请记录)</td>
                      <td colspan='5' class="pad10" style="height: 130px;text-align: left;">
                        <el-input type="textarea" rows="5" v-model="editForm.speciaOpinion"></el-input>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">填表日期</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.dateFill" style="width: 100%;"></el-date-picker>
                      </td>
                      <td style="width: 140px;text-align:center">医生签字</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.docSign" style="width: 100%;"></el-input>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
                <!--高血压-->
                <div id="SevereHyperFollow" v-if="hypertensiveShow">
                  <h2 align="center">高血压患者健康管理</h2>
                  <table border="1"  cellspacing="0" bordercolor="#dddddd" style="width:100%;margin-left:10px;margin-right:10px;border-collapse:collapse;"  width = "80%">
                    <tbody>
                    <tr>
                      <td style="width: 140px;text-align:center"><span>*</span>身份证号</td>
                      <td colspan='2' style="padding-right: 0;" class="pad10">
                        <el-form-item label="" label-width="0px" prop="peopleId" >
                          <el-input v-model="editForm.peopleId"  placeholder="请输入身份证号" style="width: 100%;" v-on:input="inputFunc1"></el-input>
                        </el-form-item>
                      </td>
                      <td style="width: 140px;text-align:center">姓名</td>
                      <td colspan='2' class="pad10" style="padding-right: 0;text-align: left;">
                        <el-form-item label="" label-width="0px">
                          <el-input v-model="editForm.name" style="width: 100%;" auto-complete="off" disabled = 'false'></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">编号</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input  v-model="editForm.id" style="width: 100%;"auto-complete="off"></el-input>
                      </td>
                      <td style="width: 140px;text-align:center">随访日期</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.follUpdate" style="width: 100%;"></el-date-picker>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;text-align:center">此次随访分类</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-select v-model="editForm.accessType" style="width: 100%;">
                          <el-option key="1" label="控制满意" value="控制满意"></el-option>
                          <el-option key="2" label="控制不满意" value="控制不满意"></el-option>
                          <el-option key="3" label="不良反应" value="不良反应"></el-option>
                          <el-option key="4" label="并发症" value="并发症"></el-option>
                        </el-select>
                      </td>

                      <td style="width: 140px;text-align:center">随访方式</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-select v-model="editForm.follType" style="width: 100%;">
                          <el-option key="1" label="门诊" value="门诊"></el-option>
                          <el-option key="2" label="家庭" value="家庭"></el-option>
                          <el-option key="3" label="电话" value="电话"></el-option>
                        </el-select>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">症状</td>
                      <td colspan='4' class="pad20" style="text-align: left;">
                        <el-checkbox-group v-model="editForm.symptoms">
                          <table>
                            <tr>
                              <td style="padding-right: 5px"><el-checkbox label="无症状" name="type"></el-checkbox></td>
                              <td style="padding-right: 5px"><el-checkbox label="头疼头晕" name="type"></el-checkbox></td>
                              <td style="padding-right: 5px"><el-checkbox label="恶心呕吐" name="type"></el-checkbox></td>
                              <td style="padding-right: 5px"><el-checkbox label="眼花耳鸣" name="type"></el-checkbox></td>
                              <td style="padding-right: 5px"><el-checkbox label="心悸胸闷" name="type"></el-checkbox></td>
                              <td style="padding-right: 5px"><el-checkbox label="鼻鲤出血不止" name="type"></el-checkbox></td>
                              <td style="padding-right: 5px"><el-checkbox label="四肢发麻" name="type"></el-checkbox></td>
                              <td style="padding-right: 5px"><el-checkbox label="下肢肿胀" name="type"></el-checkbox></td>
                            </tr>
                          </table>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">体重(kg)</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model.number="editForm.weight" style="width: 100%;">
                          <template slot="append">(kg)</template>
                        </el-input>
                      <td style="width: 140px;text-align:center">其他</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.otherPhy" style="width: 100%;"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">血压</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model.number="editForm.bloodPressure" style="width: 100%;">
                          <template slot="append">mmHg</template>
                        </el-input>
                      <td style="width: 140px;text-align:center">心率</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model.number="editForm.heartRate" style="width: 100%;">
                          <template slot="append">次/分钟</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">日吸烟量</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model.number="editForm.smokePer" style="width: 100%;">
                          <template slot="append">(支)</template>
                        </el-input>
                      <td style="width: 140px;text-align:center">日饮酒量</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model.number="editForm.drinkPer" style="width: 100%;">
                          <template slot="append">(两)</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">体质指数</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model.number="editForm.bmi" style="width: 100%;">
                          <template slot="append">(BMI)kg/m^2</template>
                        </el-input>
                      <td style="width: 140px;text-align:center">运动</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input placeholder="周/次   分钟/次" v-model="editForm.sport" style="width: 100%;"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">摄盐情况</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input placeholder="轻/中/重   轻/中/重" v-model="editForm.saltSituation" style="width: 100%;"></el-input>
                      <td style="width: 140px;text-align:center">心理调整</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.psyAdjust" style="width: 100%;">
                          <el-radio label="良好"></el-radio>
                          <el-radio label="一般"></el-radio>
                          <el-radio label="差"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">遵医行为</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.obeyMedical" style="width: 300px;">
                          <el-radio label="良好"></el-radio>
                          <el-radio label="一般"></el-radio>
                          <el-radio label="差"></el-radio>
                        </el-radio-group>
                      <td style="width: 140px;text-align:center">药物不良反应</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.adverdrugReact" style="width: 300px;">
                          <el-radio label="无"></el-radio>
                          <el-radio label="有"></el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">服药依从性</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-radio-group v-model="editForm.mediCompli" style="width: 100%;">
                          <el-radio label="规律"></el-radio>
                          <el-radio label="间断"></el-radio>
                          <el-radio label="不服药"></el-radio>
                        </el-radio-group>
                      <td style="width: 140px;text-align:center">打印数据存放</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-select v-model="editForm.number" style="width: 100%;">
                          <el-option key="1" label="第一列" value="第一列"></el-option>
                          <el-option key="2" label="第二列" value="第二列"></el-option>
                          <el-option key="3" label="第三列" value="第三列"></el-option>
                          <el-option key="4" label="第四列" value="第四列"></el-option>
                        </el-select></td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">辅助检查</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.auxExamina" style="width: 100%;"></el-input>
                      <td style="width: 140px;text-align:center">区域</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-cascader :options="options" v-model="editForm.area" style="width: 100%;" placeholder="请选择" disabled = 'false' change-on-select></el-cascader>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">药物名称1</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.drugName" style="width: 100%;"></el-input>
                      <td style="width: 140px;text-align:center">用法用量1</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input placeholder="每日_次   每次_" v-model="editForm.drugUsage" style="width: 100%;"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">药物名称2</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.othDrugName" style="width: 100%;"></el-input>
                      <td style="width: 140px;text-align:center">用法用量2</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input placeholder="每日_次   每次_" v-model="editForm.otherDrugU" style="width: 100%;"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">药物名称3</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.othDrugNameSan" style="width: 100%;"></el-input>
                      <td style="width: 140px;text-align:center">用法用量3</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input placeholder="每日_次   每次_" v-model="editForm.otherDrugUSan" style="width: 100%;"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">药物名称4</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.othDrugNameSi" style="width: 100%;"></el-input>
                      <td style="width: 140px;text-align:center">用法用量4</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input placeholder="每日_次   每次_" v-model="editForm.otherDrugUSi" style="width: 100%;"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">原因</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.referralReason" style="width: 100%;"></el-input>
                      <td style="width: 140px;text-align:center">机构及科别</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.organAndDiv" style="width: 100%;"></el-input>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center">下次随访日期</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.dateNextFollow" style="width: 100%;"></el-date-picker>
                      <td style="width: 140px;text-align:center">随访医生签名</td>
                      <td colspan='2' class="pad10" style="text-align: left;">
                        <el-input v-model="editForm.docSignature" style="width: 100%;"></el-input>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
              </el-form>
            </div>
          </EasyScrollbar>
        </el-col>

        <el-col style="margin-top: -15px;" :span="8" class="pos-order" id="order-list">
          <EasyScrollbar>
            <div v-contextmenu:contextmenu class="item" v-bind:style="{ height: tableHeight + 'px'}" style="width:520px">
              <v-contextmenu v-if="buttonShow" style="width: 400px" ref="contextmenu">
                <v-contextmenu-item v-if="buttons.P_C" @click="Add('medical')">新增个人体检档案</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPN_C" @click="Add('children1')">新增新生儿家庭访视记录表</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPO_C" @click="Add('children2')">新增1-8月儿童健康检查记录表</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPT_C" @click="Add('children3')">新增12-30月儿童健康检查记录表</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPTS_C" @click="Add('children4')">新增3-6岁月儿童健康检查记录表</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPF_C" @click="Add('maternal')">新增第1次产前检查服务记录表</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPS_C" @click="Add('prenatal')">新增第2-5次产前检查服务记录表</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPP_C" @click="Add('postpartum')">新增产后访视检查服务记录表</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPPV_C" @click="Add('postpartum42')">新增产后42天健康检查记录表</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPSH_C" @click="Add('hypertensive')">新增高血压患者健康管理</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPD_C" @click="Add('diabetes')">新增2型糖尿病患者健康管理</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPSMS_C" @click="Add('personalinformation')">新增严重精神障碍患者补充信息</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPSMF_C" @click="Add('servicerecord')">新增严重精神障碍患者随访记录</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPFR_C" @click="Add('tuberculosispatients')">新增肺结核患者健康管理</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPFV_C" @click="Add('tuberculosisfllowup')">新增肺结核患者随访记录</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPSA_C" @click="Add('elderly')">新增老年人生活自理能力评估表</v-contextmenu-item>
                <v-contextmenu-item v-if="buttons.KPHM_C" @click="Add('elderly1')">新增老年人中医院健康管理服务</v-contextmenu-item>
              </v-contextmenu>
              <v-contextmenu v-else="true" disabled="true" ref="contextmenu"></v-contextmenu>
              <el-collapse accordion style="width: 524px;border-left:1px solid #dddddd;">
                <el-menu default-active="2"  class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" v-bind:style="{ height: tableHeight-2 + 'px'}">
                  <el-submenu index="1" v-if="generalData1">
                    <template slot="title">
                      <span id="1" class="el-icon-remove" style="color: deepskyblue"></span><span style="margin-left: 10px;color: black;">个人健康</span>
                    </template>
                    <el-menu-item index="2" class="box-card">
                      <i class="el-icon-document" style="color: deepskyblue;margin-right: 0px;"></i><a @click="addOrderList(user, 'cover')"><span style="color: black;">档案封面</span></a>
                    </el-menu-item>
                    <el-menu-item index="3" class="box-card">
                      <i class="el-icon-document" style="color: deepskyblue;margin-right: 0px;"></i><a @click="addOrderList(user, 'perinfor')"><span style="color: black;">个人基本档案</span></a>
                    </el-menu-item>
                    <el-submenu index="4" v-if="medicalData">
                      <template slot="title">
                        <span id="4" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">个人体检档案</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="5" size="small" v-for="goods in medical" :key="goods.id" @click="addOrderList(goods, 'medical')"><i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                  </el-submenu>

                  <el-submenu index="6" v-if="generalData2">
                    <template slot="title">
                      <span id="6" class="el-icon-circle-plus" style="color: deepskyblue"></span><span style="margin-left: 10px;color: black;">新生儿</span>
                    </template>
                    <el-submenu index="7" v-if="children1Data">
                      <template slot="title">
                        <span id="7" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">新生儿家庭访视记录表</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('children1')">添加</el-button>-->
                      </template>
                      <el-menu-item index="8" size="small" v-for="goods in children1" :key="goods.id" @click="addOrderList(goods, 'children1')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                    <el-submenu index="9" v-if="children2Data">
                      <template slot="title">
                        <span id="9" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">1-8月儿童健康检查记录表</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="10" size="small" v-for="goods in children2" :key="goods.id" @click="addOrderList(goods, 'children2')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                    <el-submenu index="11" v-if="children3Data">
                      <template slot="title">
                        <span id="11" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">12-30月儿童健康检查记录表</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="12" size="small" v-for="goods in children3" :key="goods.id" @click="addOrderList(goods, 'children3')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                    <el-submenu index="13" v-if="children4Data">
                      <template slot="title">
                        <span id="13" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">3-6岁月儿童健康检查记录表</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="14" size="small" v-for="goods in children4" :key="goods.id" @click="addOrderList(goods, 'children4')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                  </el-submenu>

                  <el-submenu index="15" v-if="generalData3">
                    <template slot="title">
                      <span id="15" class="el-icon-circle-plus" style="color: deepskyblue"></span><span style="margin-left: 10px;color: black;">孕产妇</span>
                    </template>
                    <el-submenu index="16" v-if="maternalData">
                      <template slot="title">
                        <span id="16" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">第1次产前检查服务记录表</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="17" size="small" v-for="goods in maternal" :key="goods.id" @click="addOrderList(goods, 'maternal')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                    <el-submenu index="18" v-if="prenatalData">
                      <template slot="title">
                        <span id="18" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">第2-5次产前检查服务记录表</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="19" size="small" v-for="goods in prenatal" :key="goods.id" @click="addOrderList(goods, 'prenatal')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                    <el-submenu index="20" v-if="postpartumData">
                      <template slot="title">
                        <span id="20" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">产后访视检查服务记录表</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="21" size="small" v-for="goods in postpartum" :key="goods.id" @click="addOrderList(goods, 'postpartum')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                    <el-submenu index="22" v-if="postpartum42Data">
                      <template slot="title">
                        <span id="22" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">产后42天健康检查记录表</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="23" size="small" v-for="goods in postpartum42" :key="goods.id" @click="addOrderList(goods, 'postpartum42')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                  </el-submenu>

                  <el-submenu index="24" v-if="generalData4">
                    <template slot="title">
                      <span id="24" class="el-icon-circle-plus" style="color: deepskyblue"></span><span style="margin-left: 10px;color: black;">慢病</span>
                    </template>
                    <el-submenu index="25" v-if="hypertensiveData">
                      <template slot="title">
                        <span id="25" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">高血压患者健康管理</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="26" size="small" v-for="goods in hypertensive" :key="goods.id" @click="addOrderList(goods, 'hypertensive')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                    <el-submenu index="27" v-if="diabetesData">
                      <template slot="title">
                        <span id="27" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">2型糖尿病患者健康管理</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="28" size="small" v-for="goods in diabetes" :key="goods.id" @click="addOrderList(goods, 'diabetes')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                    <el-submenu index="29" v-if="personalinformationData">
                      <template slot="title">
                        <span id="29" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">严重精神障碍患者补充信息</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="30" size="small" v-for="goods in personalinformation" :key="goods.id" @click="addOrderList(goods, 'personalinformation')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                    <el-submenu index="31" v-if="servicerecordData">
                      <template slot="title">
                        <span id="31" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">严重精神障碍患者随访记录</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="32" size="small" v-for="goods in servicerecord" :key="goods.id" @click="addOrderList(goods, 'servicerecord')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                    <el-submenu index="33" v-if="tuberculosispatientsData">
                      <template slot="title">
                        <span id="33" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">肺结核患者健康管理</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="34" size="small" v-for="goods in tuberculosispatients" :key="goods.id" @click="addOrderList(goods, 'tuberculosispatients')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                    <el-submenu index="35" v-if="tuberculosisfllowupData">
                      <template slot="title">
                        <span id="35" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">肺结核患者随访记录</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="36" size="small" v-for="goods in tuberculosisfllowup" :key="goods.id" @click="addOrderList(goods, 'tuberculosisfllowup')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                  </el-submenu>

                  <el-submenu index="37" v-if="generalData5">
                    <template slot="title">
                      <span id="37" class="el-icon-circle-plus" style="color: deepskyblue"></span><span style="margin-left: 10px;color: black;">老年人</span>
                    </template>
                    <el-submenu index="38" v-if="elderlyData">
                      <template slot="title">
                        <span id="38" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">老年人生活自理能力评估表</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="39" size="small" v-for="goods in elderly" :key="goods.id" @click="addOrderList(goods, 'elderly')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                    <el-submenu index="40" v-if="elderly1Data">
                      <template slot="title">
                        <span id="40" class="el-icon-circle-plus" style="margin-left: 10px;color: deepskyblue"></span><span style="margin-left: 10px;color: black;">老年人中医院健康管理服务</span>
                        <!--                        <el-button style="float: right; padding: 3px 0" type="text" circle v-on:click="Add('medical')">添加</el-button>-->
                      </template>
                      <el-menu-item index="41" size="small" v-for="goods in elderly1" :key="goods.id" @click="addOrderList(goods, 'elderly1')">
                        <i class="el-icon-document" style="color: deepskyblue;margin-right: -5px;"></i>
                        <span style="color: black;">{{goods.createDate | dateFormat}}</span>
                      </el-menu-item>
                    </el-submenu>
                  </el-submenu>
                </el-menu>
              </el-collapse>
            </div>
          </EasyScrollbar>
        </el-col>
      </el-row>
    </div>
    <hypertensive v-if="hypertensiveVisible" ref="hypertensive" @watchChild="getdate"></hypertensive>
    <personalinformation v-if="personalinformationVisible" ref="personalinformation" @watchChild="getdate"></personalinformation>
    <servicerecord v-if="servicerecordVisible" ref="servicerecord" @watchChild="getdate"></servicerecord>
    <diabetes v-if="diabetesVisible" ref="diabetes" @watchChild="getdate"></diabetes>
    <tuberculosisfllowup v-if="tuberculosisfllowupVisible" ref="tuberculosisfllowup" @watchChild="getdate"></tuberculosisfllowup>
    <tuberculosispatients v-if="tuberculosispatientsVisible" ref="tuberculosispatients" @watchChild="getdate"></tuberculosispatients>
    <maternal v-if="maternalVisible" ref="maternal" @watchChild="getdate"></maternal>
    <postpartum v-if="postpartumVisible" ref="postpartum" @watchChild="getdate"></postpartum>
    <postpartum42 v-if="postpartum42Visible" ref="postpartum42" @watchChild="getdate"></postpartum42>
    <prenatal v-if="prenatalVisible" ref="prenatal" @watchChild="getdate"></prenatal>
    <children1 v-if="children1Visible" ref="children1" @watchChild="getdate"></children1>
    <children2 v-if="children2Visible" ref="children2" @watchChild="getdate"></children2>
    <children3 v-if="children3Visible" ref="children3" @watchChild="getdate"></children3>
    <children4 v-if="children4Visible" ref="children4" @watchChild="getdate"></children4>
    <elderly v-if="elderlyVisible" ref="elderly" @watchChild="getdate"></elderly>
    <elderly1 v-if="elderly1Visible" ref="elderly1" @watchChild="getdate"></elderly1>
    <medical v-if="medicalVisible" ref="medical" @watchChild="getdate"></medical>
  </div>
</template>
<script>
  import util from '@/utils/table.js'
  import city from '@/api/city.js'
  import {
    getList,
    updateData,
    addData as addUser
  } from '@/api/Perinfor'
  import { getList as getFollow } from '@/api/follow'
  import { getList as getMaternal } from '@/api/firstPrenatalDiagnosis'
  import { getList as getPostpartum } from '@/api/postpartumVisit'
  import { getList as getPostpartum42 } from '@/api/postpartumVisitFortytwo'
  import { getList as getPrenatal } from '@/api/scheduleTwoFivePrenatalDiagnosis'
  import { getList as getTuberculosisfllowup } from '@/api/Followupservicerecord'
  import { getList as getTuberculosispatients } from '@/api/FirsthomevisitRecord'
  import { getList as getSevereMentalFollow } from '@/api/severeMentalFollow'
  import { getList as getDiabetes } from '@/api/diabetes'
  import { getList as getSevereMentalSupplement } from '@/api/severeMentalSupplement'
  import { getList as getMedical } from '@/api/HealthCheckups'
  import { getList as gethighbood } from '@/api/SevereHyperFollow'
  import { getList as OneEightMonthChild } from '@/api/oneEightMonthChild'
  import { getList as TwelveThsixEightMonthChild } from '@/api/twelveThsixEightMonthChild'
  import { getList as ThreeSixYearChild } from '@/api/threeSixYearChild'
  import { getList as selfcareability } from '@/api/selfcareability'
  import { getList as healthManagementRecord } from '@/api/healthManagementRecord'
  import { getList as getNewbornVisit } from '@/api/newbornVisit'

  import hypertensive from '@/views/Key_population_management/SevereHyperFollow'
  import personalinformation from '@/views/Key_population_management/severeMentalSupplement'
  import servicerecord from '@/views/Key_population_management/severeMentalFollow'
  import maternal from '@/views/Key_population_management/firstPrenatalDiagnosis'
  import postpartum from '@/views/Key_population_management/postpartumVisit'
  import postpartum42 from '@/views/Key_population_management/postpartumVisitFortytwo'
  import prenatal from '@/views/Key_population_management/scheduleTwoFivePrenatalDiagnosis'
  import children1 from '@/views/Key_population_management/newbornVisit'
  import children2 from '@/views/Key_population_management/oneEightMonthChild'
  import children3 from '@/views/Key_population_management/twelveThsixEightMonthChild'
  import children4 from '@/views/Key_population_management/threeSixYearChild'
  import tuberculosisfllowup from '@/views/Key_population_management/followupservicerecord'
  import tuberculosispatients from '@/views/Key_population_management/firsthomevisitRecord'
  import diabetes from '@/views/Key_population_management/diabetes'
  import elderly from '@/views/Key_population_management/SelfcareAbility'
  import elderly1 from '@/views/Key_population_management/HealthManagementEcord'
  import medical from '@/views/Health_record_management/HealthCheckups'
  export default {
    filters: {
      dateFormat: function(value) {
        value =
          !value || value === ''
            ? null
            : util.formatDate.format(new Date(value), 'yyyy-MM-dd')
        return value
      }
    },
    components: {
      medical,
      hypertensive,
      servicerecord,
      personalinformation,
      diabetes,
      tuberculosisfllowup,
      tuberculosispatients,
      maternal,
      postpartum,
      postpartum42,
      prenatal,
      children1,
      children2,
      children3,
      children4,
      elderly,
      elderly1
    },
    name: 'Pos',
    data() {
      return {
        buttonShow: true,
        buttons: {
          P_C: false,
          KPN_C: false,
          KPO_C: false,
          KPT_C: false,
          KPTS_C: false,
          KPF_C: false,
          KPS_C: false,
          KPP_C: false,
          KPPV_C: false,
          KPSH_C: false,
          KPD_C: false,
          KPSMS_C: false,
          KPSMF_C: false,
          KPFR_C: false,
          KPFV_C: false,
          KPSA_C: false,
          KPHM_C: false
        },
        options: city.city(),
        peopleId: '',
        name: '',
        // 模型
        area: [],
        causeDeath: [],
        // 窗体
        medicalVisible: false,
        hypertensiveVisible: false,
        servicerecordVisible: false,
        personalinformationVisible: false,
        diabetesVisible: false,
        tuberculosisfllowupVisible: false,
        tuberculosispatientsVisible: false,
        maternalVisible: false,
        postpartumVisible: false,
        postpartum42Visible: false,
        prenatalVisible: false,
        children1Visible: false,
        children2Visible: false,
        children3Visible: false,
        children4Visible: false,
        elderlyVisible: false,
        elderly1Visible: false,
        // 数据栏
        generalData1: true,
        generalData2: false,
        generalData3: false,
        generalData4: false,
        generalData5: false,
        medicalData: false,
        hypertensiveData: false,
        servicerecordData: false,
        personalinformationData: false,
        diabetesData: false,
        tuberculosisfllowupData: false,
        tuberculosispatientsData: false,
        maternalData: false,
        postpartumData: false,
        postpartum42Data: false,
        prenatalData: false,
        children1Data: false,
        children2Data: false,
        children3Data: false,
        children4Data: false,
        elderlyData: false,
        elderly1Data: false,
        // 中间部分显示
        perinforShow: false,
        medicalShow: false,
        hypertensiveShow: false,
        servicerecordShow: false,
        personalinformationShow: false,
        diabetesShow: false,
        tuberculosisfllowupShow: false,
        tuberculosispatientsShow: false,
        maternalShow: false,
        postpartumShow: false,
        postpartum42Show: false,
        prenatalShow: false,
        children1Show: false,
        children2Show: false,
        children3Show: false,
        children4Show: false,
        elderlyShow: false,
        elderly1Show: false,
        coverShow: true,
        // 数据
        medical: [],
        hypertensive: [],
        servicerecord: [],
        personalinformation: [],
        diabetes: [],
        tuberculosisfllowup: [],
        tuberculosispatients: [],
        maternal: [],
        postpartum: [],
        postpartum42: [],
        prenatal: [],
        children1: [],
        children2: [],
        children3: [],
        children4: [],
        elderly: [],
        elderly1: [],
        // 字典
        riskcontrol: [
          { value: '定期随访', label: '定期随访' },
          { value: '纳入慢性病患者健康管理', label: '纳入慢性病患者健康管理' },
          { value: '建议复诊', label: '建议复诊' },
          { value: '其他', label: '其他' }
        ],
        // 健康指导
        healthGuidance: [
          { value: '戒烟', label: '戒烟' },
          { value: '健康饮酒', label: '健康饮酒' },
          { value: '饮食', label: '饮食' },
          { value: '锻炼', label: '锻炼' },
          { value: '减体重', label: '减体重' },
          { value: '建议接种疫苗', label: '建议接种疫苗' },
          { value: '其他', label: '其他' }
        ],
        // 眼部疾病
        eyeDisease: [
          { value: '未发现', label: '未发现' },
          { value: '视网膜出血或渗出', label: '视网膜出血或渗出' },
          { value: '视乳头水肿', label: '视乳头水肿' },
          { value: '白内障', label: '白内障' },
          { value: '其他', label: '其他' }
        ],
        // 血管疾病
        vascularDisease: [
          { value: '未发现', label: '未发现' },
          { value: '夹层动脉瘤', label: '夹层动脉瘤' },
          { value: '动脉闭塞性疾病', label: '动脉闭塞性疾病' },
          { value: '其他', label: '其他' }
        ],
        // 心脏疾病
        heartDisease: [
          { value: '未发现', label: '未发现' },
          { value: '心肌梗死', label: '心肌梗死' },
          { value: '心绞痛', label: '心绞痛' },
          { value: '冠状动脉血运重建', label: '冠状动脉血运重建' },
          { value: '充血性心力衰竭', label: '充血性心力衰竭' },
          { value: '心前区疼痛', label: '心前区疼痛' },
          { value: '其他', label: '其他' }
        ],
        // 肾脏疾病
        kidneyDisease: [
          { value: '未发现', label: '未发现' },
          { value: '糖尿病肾病', label: '糖尿病肾病' },
          { value: '肾功能衰竭', label: '肾功能衰竭' },
          { value: '急性肾炎', label: '急性肾炎' },
          { value: '慢性肾炎', label: '慢性肾炎' },
          { value: '其他', label: '其他' }
        ],
        // 脑血管疾病
        cerebrovascular: [
          { value: '未发现', label: '未发现' },
          { value: '缺血性卒中', label: '缺血性卒中' },
          { value: '脑出血', label: '脑出血' },
          { value: '蛛网膜下腔出血', label: '蛛网膜下腔出血' },
          { value: '短暂性脑缺血发作', label: '短暂性脑缺血发作' },
          { value: '其他', label: '其他' }
        ],
        // 饮食
        diet: [
          { value: '荤素均衡', label: '荤素均衡' },
          { value: '荤食为主', label: '荤食为主' },
          { value: '嗜盐', label: '嗜盐' },
          { value: '嗜糖', label: '嗜糖' },
          { value: '嗜油', label: '嗜油' }
        ],
        familyHistory: [
          { value: '无', label: '无' },
          { value: '遗传性疾病史', label: '遗传性疾病史' },
          { value: '精神疾病史', label: '精神疾病史' },
          { value: '其他', label: '其他' }
        ],
        development: [
          { value: '对很大的声音没有反应', label: '对很大的声音没有反应' },
          { value: '呼唤名字无反应', label: '呼唤名字无反应' },
          { value: '不会模仿“再见”或者“欢迎”动作', label: '不会模仿“再见”或者“欢迎”动作' },
          { value: '不会使用拇指对程小物品', label: '不会使用拇指对程小物品' },
          { value: '不会扶物站立', label: '不会扶物站立' }
        ],
        medicine: [
          { value: '中医饮食调养指导', label: '中医饮食调养指导' },
          { value: '中医起居调摄指导', label: '中医起居调摄指导' },
          { value: '传授摩腹', label: '传授摩腹' }
        ],
        guidancePregnant: [
          { value: '个人卫生', label: '个人卫生' },
          { value: '心理', label: '心理' },
          { value: '营养', label: '营养' },
          { value: '母乳喂养', label: '母乳喂养' },
          { value: '新生儿护理与喂养', label: '新生儿护理与喂养' },
          { value: '其他', label: '其他' }
        ],
        guidancePregnant2: [
          { value: '心理健康', label: '心理健康' },
          { value: '性保健和避孕', label: '性保健和避孕' },
          { value: '婴儿喂养', label: '婴儿喂养' },
          { value: '产妇营养', label: '产妇营养' },
          { value: '其他', label: '其他' }
        ],
        drugDosage: [
          { value: '固定剂量复合制剂', label: '固定剂量复合制剂' },
          { value: '散装药', label: '散装药' },
          { value: '板式组合药', label: '板式组合药' }
        ],
        motherState: [
          { value: '无', label: '无' },
          { value: '糖尿病', label: '糖尿病' },
          { value: '妊娠高血压', label: '妊娠高血压' },
          { value: '其他', label: '其他' }
        ],
        guidance: [
          { value: '喂养指导', label: '喂养指导' },
          { value: '发育指导', label: '发育指导' },
          { value: '防病指导', label: '防病指导' },
          { value: '预防伤害指导', label: '预防伤害指导' },
          { value: '口腔保健指导', label: '口腔保健指导' },
          { value: '其他', label: '其他' }
        ],
        Death: [
          { value: '躯体疾病', label: '躯体疾病',
            children: [
              { value: '传染病和寄生虫病', label: '传染病和寄生虫病' },
              { value: '肿瘤', label: '肿瘤' },
              { value: '心脏病', label: '心脏病' },
              { value: '脑血管病', label: '脑血管病' },
              { value: '呼吸系统疾病', label: '呼吸系统疾病' },
              { value: '消化系统疾病', label: '消化系统疾病' },
              { value: '其他疾病', label: '其他疾病' },
              { value: '不详', label: '不详' }
            ]
          },
          { value: '自杀', label: '自杀' },
          { value: '他杀', label: '他杀' },
          { value: '意外', label: '意外' },
          { value: '精神疾病相关并发症', label: '精神疾病相关并发症' },
          { value: '其他', label: '其他' }
        ],
        selectedOptions2: [],
        currentSymptoms: [
          { value: '幻觉', label: '幻觉' },
          { value: '交流困难', label: '交流困难' },
          { value: '猜疑', label: '猜疑' },
          { value: '喜怒无常', label: '喜怒无常' },
          { value: '行为怪异', label: '行为怪异' },
          { value: '兴奋话多', label: '兴奋话多' },
          { value: '伤人伤物', label: '伤人伤物' },
          { value: '悲观厌世', label: '悲观厌世' },
          { value: '无故外走', label: '无故外走' },
          { value: '自语自笑', label: '自语自笑' },
          { value: '孤僻懒散', label: '孤僻懒散' },
          { value: '其他', label: '其他' }
        ],
        dangerBeha: [
          { value: '轻度滋事', label: '轻度滋事' },
          { value: '肇事', label: '肇事' },
          { value: '肇祸', label: '肇祸' },
          { value: '其他危险行为', label: '其他危险行为' },
          { value: '自杀', label: '自杀' },
          { value: '自杀未遂', label: '自杀未遂' },
          { value: '无', label: '无' }
        ],
        rehaMeasure: [
          { value: '生活劳动能力', label: '生活劳动能力' },
          { value: '职业训练', label: '职业训练' },
          { value: '学习能力', label: '学习能力' },
          { value: '社会交往能力', label: '社会交往能力' },
          { value: '其他', label: '其他' }
        ],
        optionsex: [
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
        kitchenExhaust: [
          { value: '无', label: '无' },
          { value: '油烟机', label: '油烟机' },
          { value: '换气扇', label: '换气扇' },
          { value: '烟囱', label: '烟囱' },
          { value: '其他', label: '其他' }
        ],
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
        edit: true,
        activeName: 'first',
        tableHeight: window.innerHeight - 220,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '添加',
          search: '查询',
          detail: '详细'
        },
        dialogFormVisible: false,
        dialogFormVisible1: false,
        dialogFormVisible2: false,
        dialogFormVisible3: false,
        filters: {
          searchId: ''
        },
        user_id: [],
        user_crad: [],
        nowFollow: [],
        nowFollowTotal: 0,
        nowFollowPage: 1,
        weekFollow: [],
        weekFollowTotal: 0,
        weekFollowPage: 1,
        notFollow: [],
        notFollowTotal: 0,
        notFollowPage: 1,
        user: {},
        total: 0,
        page: 1,
        sels: [], // 列表选中列
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
        houseAddress_model: [],
        // 编辑界面数据
        editForm: {
          id: '',
          exposureHistory: [],
          drugAllergyHistory: [],
          pastHistory: [],
          fatherHistory: [],
          matherHistory: [],
          bortherHistory: [],
          sonHistory: [],
          disability: [],
          kitchenExhaust: [],
          fuel: [],
          water: [],
          toilet: [],
          livestockBar: [],
          area: []
        },
        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
        }
      }
    },
    created() {
      this.getParams()
    },
    methods: {
      handleOpen(key, keyPath) {
        document.getElementById(key).className = 'el-icon-remove'
      },
      handleClose(key, keyPath) {
        document.getElementById(key).className = 'el-icon-circle-plus'
      },
      getParams() {
        this.filters.searchId = this.$route.params.hid
        if (this.filters.searchId && this.filters.searchId.trim().length !== 0) {
          this.search()
        }
      },
      search() {
        this.coverShow = true
        this.hypertensiveShow = false
        this.servicerecordShow = false
        this.personalinformationShow = false
        this.diabetesShow = false
        this.tuberculosisfllowupShow = false
        this.tuberculosispatientsShow = false
        this.maternalShow = false
        this.postpartumShow = false
        this.postpartum42Show = false
        this.prenatalShow = false
        this.children1Show = false
        this.children2Show = false
        this.children3Show = false
        this.children4Show = false
        this.elderlyShow = false
        this.elderly1Show = false
        this.medicalShow = false
        this.perinforShow = false
        this.generalData1 = true
        this.generalData2 = false
        this.generalData3 = false
        this.generalData4 = false
        this.generalData5 = false
        this.medicalData = false
        this.hypertensiveData = false
        this.servicerecordData = false
        this.personalinformationData = false
        this.diabetesData = false
        this.tuberculosisfllowupData = false
        this.tuberculosispatientsData = false
        this.maternalData = false
        this.postpartumData = false
        this.postpartum42Data = false
        this.prenatalData = false
        this.children1Data = false
        this.children2Data = false
        this.children3Data = false
        this.children4Data = false
        this.elderlyData = false
        this.elderly1Data = false
        this.hypertensive = []
        this.servicerecord = []
        this.personalinformation = []
        this.diabetes = []
        this.tuberculosisfllowup = []
        this.tuberculosispatients = []
        this.maternal = []
        this.postpartum = []
        this.postpartum42 = []
        this.prenatal = []
        this.children2 = []
        this.children3 = []
        this.children4 = []
        this.editForm = {}
        this.user = {}
        this.peopleId = ''
        this.name = ''
        if (this.filters.searchId && this.filters.searchId.trim().length !== 0) {
          let para = {
            page: 1,
            size: 200,
            filters: { name: '', peopleId: this.filters.searchId, cardNumber: '' }
          }
          getList(para).then(res => {
            this.user_id = res.detailModelList
            if (this.user_id.length !== 0) {
              this.peopleId = this.user_id[0].peopleId
              this.name = this.user_id[0].name
              this.user = this.user_id[0]
              this.getdate()
            }
          }).then(() => {
            if (this.user_id.length === 0) {
              para = {
                page: 1,
                size: 200,
                filters: { name: '', peopleId: '', cardNumber: this.filters.searchId }
              }
              getList(para).then(res => {
                this.user_crad = res.detailModelList
                if (this.user_crad.length !== 0) {
                  this.peopleId = this.user_crad[0].peopleId
                  this.name = this.user_crad[0].name
                  this.user = this.user_crad[0]
                  this.getdate()
                } else {
                  this.$message.error('未找到相关信息')
                }
              })
            }
          })
        }
      },
      getdate() {
        this.handleEdit(this.user)
        // 高血压
        let para = {
          page: 1,
          size: 200,
          filters: {
            follType: '', smokePer: '', sport: '', saltSituation: '', psyAdjust: '', obeyMedical: '', mediCompli: '',
            adverdrugReact: '', accessType: '', drugName: '', organAndDiv: '', follUpdate_s: '', follUpdate_e: '',
            dateNextFollow_s: '', dateNextFollow_e: '', peopleId: this.peopleId, name: ''
          }
        }
        gethighbood(para).then(res => {
          this.hypertensive = res.detailModelList
          if (this.hypertensive.length !== 0) {
            for (const item of this.hypertensive) {
              item.area = item.area.split('/')
              item.symptoms = item.symptoms.split(',')
            }
            this.hypertensiveData = true
            this.generalData4 = true
          }
        })
        // 精神病随访
        para = {
          page: 1,
          size: 200,
          filters: {
            peopleId: this.peopleId, name: '', formFollow: '', lostReason: '', riskAssess: '', ifReferral: '',
            thisClassifi: '', docSign: '', follDate: '', nextDate: '', follDate_s: '', follDate_e: ''
          }
        }
        getSevereMentalFollow(para).then(res => {
          this.servicerecord = res.detailModelList
          if (this.servicerecord.length !== 0) {
            for (const item of this.servicerecord) {
              item.currentSymptoms = item.currentSymptoms.split(',')
              item.dangerBeha = item.dangerBeha.split(',')
              item.rehaMeasure = item.rehaMeasure.split(',')
              item.causeDeath = item.causeDeath.split('/')
              item.area = item.area.split('/')
            }
            this.servicerecordData = true
            this.generalData4 = true
          }
        })
        // 精神病补充
        para = {
          page: 1,
          size: 200,
          filters: {
            peopleId: this.peopleId, name: '', guardianName: '', guardianAddre: '', guardPhone: '',
            commitPersonName: '', commitPersonNum: '', accountType: '', outService: '', firstOnsetTime: '',
            firstAntipsyTreat: '', firstOnsetTime_s: '', firstOnsetTime_e: ''
          }
        }
        getSevereMentalSupplement(para).then(res => {
          this.personalinformation = res.detailModelList
          if (this.personalinformation.length !== 0) {
            for (const item of this.personalinformation) {
              item.dangerBehav = item.dangerBehav.split(',')
              item.pastSymptoms = item.pastSymptoms.split(',')
              item.area = item.area.split('/')
            }
            this.personalinformationData = true
            this.generalData4 = true
          }
        })
        // 2糖
        para = {
          page: 1,
          size: 200,
          filters: {
            name: '', age: '', startTime: '', endTime: '', peopleId: this.peopleId, liveStatus: '',
            ethnic: '', doctor: '', sex: '', phone: '', area: '', creator: ''
          }
        }
        getDiabetes(para).then(res => {
          this.diabetes = res.detailModelList
          if (this.diabetes.length !== 0) {
            for (const item of this.diabetes) {
              item.footArteyPulse = item.footArteyPulse.split('/')
              item.symptoms = item.symptoms.split('/')
            }
            this.diabetesData = true
            this.generalData4 = true
          }
        })
        // 肺结核患者健康管理
        para = {
          page: 1,
          size: 200,
          filters: {
            name: '', followTime_s: '', followTime_e: '', inputDate_s: '', inputDate_e: '', area: '',
            ethnic: '', phone: '', responDoctor: '', inputPerson: '', lifeState: '', age: '', peopleId: this.peopleId
          }
        }
        getTuberculosispatients(para).then(res => {
          this.tuberculosispatients = res.detailModelList
          if (this.tuberculosispatients.length !== 0) {
            for (const item of this.tuberculosispatients) {
              item.drugDosage = item.drugDosage.split(',')
              item.symptomsSigns = item.symptomsSigns.split(',')
            }
            this.tuberculosispatientsData = true
            this.generalData4 = true
          }
        })
        // 肺结核患者随访记录
        para = {
          page: 1,
          size: 200,
          filters: {
            name: '', flowUpTime_s: '', flowUpTime_e: '', peopleId: this.peopleId
          }
        }
        getTuberculosisfllowup(para).then(res => {
          this.tuberculosisfllowup = res.detailModelList
          if (this.tuberculosisfllowup.length !== 0) {
            for (const item of this.tuberculosisfllowup) {
              item.drugDosage = item.drugDosage.split(',')
              item.symptomsSigns = item.symptomsSigns.split(',')
            }
            this.tuberculosisfllowupData = true
            this.generalData4 = true
          }
        })
        // 第1次产前检查服务记录表
        para = {
          page: 1,
          size: 200,
          filters: { name: '', date_s: '', date_e: '', peopleId: this.peopleId, insandDep: '', doctor: '', date: '' }
        }
        getMaternal(para).then(res => {
          this.maternal = res.detailModelList
          if (this.maternal.length !== 0) {
            for (const item of this.maternal) {
              item.historyPregnancy = item.historyPregnancy.toString().split(',')
              item.vaginalSecretions = item.vaginalSecretions.toString().split(',')
              item.hepatitisB = item.hepatitisB.toString().split(',')
              item.healthcare = item.healthcare.toString().split(',')
              item.pastHistory = item.pastHistory.toString().split(',')
              item.familyHistory = item.familyHistory.toString().split(',')
              item.personalHistory = item.personalHistory.toString().split(',')
              item.area = item.area.toString().split('/')
            }
            this.maternalData = true
            this.generalData3 = true
          }
        })
        // 第2-5次产前检查服务记录表
        para = {
          page: 1,
          size: 200,
          filters: { name: '', date_s: '', date_e: '', peopleId: this.peopleId, insandDep: '', doctor: '', date: '' }
        }
        getPrenatal(para).then(res => {
          this.prenatal = res.detailModelList
          if (this.prenatal.length !== 0) {
            for (const item of this.prenatal) {
              item.guidance = item.guidance.split(',')
              item.otherSassist = item.otherSassist.split(',')
              item.area = item.area.split('/')
            }
            this.prenatalData = true
            this.generalData3 = true
          }
        })
        // 产后访视检查服务记录表
        para = {
          page: 1,
          size: 200,
          filters: { name: '', date: '', peopleId: this.peopleId, insandDep: '', doctor: '', leaveHospitale_s: '', leaveHospitale_e: '' }
        }
        getPostpartum(para).then(res => {
          this.postpartum = res.detailModelList
          if (this.postpartum.length !== 0) {
            for (const item of this.postpartum) {
              item.guidance = item.guidance.split(',')
              item.area = item.area.split('/')
            }
            this.postpartumData = true
            this.generalData3 = true
          }
        })
        // 产后42天健康检查记录表
        para = {
          page: 1,
          size: 200,
          filters: { name: '', date_s: '', date_e: '', peopleId: this.peopleId, insandDep: '', doctor: '', leaveHospitale_s: '', leaveHospitale_e: '' }
        }
        getPostpartum42(para).then(res => {
          this.postpartum42 = res.detailModelList
          if (this.postpartum42.length !== 0) {
            for (const item of this.postpartum42) {
              item.guidance = item.guidance.split(',')
              item.area = item.area.split('/')
            }
            this.postpartum42Data = true
            this.generalData3 = true
          }
        })
        // 儿童
        para = {
          page: 1,
          size: 200,
          filters: { name: '', startTime: '', endTime: '', area: '', peopleId: this.peopleId }
        }
        getNewbornVisit(para).then(res => {
          this.children1 = res.detailModelList
          if (this.children1.length !== 0) {
            for (const item of this.children1) {
              item.motherState = item.motherState.split(',')
              item.guidance = item.guidance.split(',')
            }
            this.children1Data = true
            this.generalData2 = true
          }
        })
        para = {
          page: 1,
          size: 200,
          filters: { name: '', startTime: '', endTime: '', area: '', peopleId: this.peopleId }
        }
        OneEightMonthChild(para).then(res => {
          this.children2 = res.detailModelList
          if (this.children2.length !== 0) {
            for (const item of this.children2) {
              item.guidance = item.guidance.split(',')
              item.medicine = item.medicine.split(',')
            }
            this.children2Data = true
            this.generalData2 = true
          }
        })
        para = {
          page: 1,
          size: 200,
          filters: { name: '', startTime: '', endTime: '', area: '', peopleId: this.peopleId }
        }
        TwelveThsixEightMonthChild(para).then(res => {
          this.children3 = res.detailModelList
          if (this.children3.length !== 0) {
            for (const item of this.children3) {
              item.guidance = item.guidance.split(',')
              item.development = item.development.split(',')
              item.medicine = item.medicine.split(',')
            }
            this.children3Data = true
            this.generalData2 = true
          }
        })
        para = {
          page: 1,
          size: 200,
          filters: { name: '', startTime: '', endTime: '', area: '', peopleId: this.peopleId }
        }
        ThreeSixYearChild(para).then(res => {
          this.children4 = res.detailModelList
          if (this.children4.length !== 0) {
            for (const item of this.children4) {
              item.guidance = item.guidance.split(',')
              item.development = item.development.split(',')
              item.medicine = item.medicine.split(',')
            }
            this.children4Data = true
            this.generalData2 = true
          }
        })
        para = {
          page: 1,
          size: 200,
          filters: {
            name: '',
            meal: '',
            washing: '',
            peopleId: this.peopleId,
            formDate_s: '',
            formDate_e: ''
          }
        }
        // 老年人
        selfcareability(para).then(res => {
          this.elderly = res.detailModelList
          if (this.elderly.length !== 0) {
            for (const item of this.elderly) {
              item.area = item.area.split('/')
            }
            this.elderlyData = true
            this.generalData5 = true
          }
        })
        para = {
          page: 1,
          size: 200,
          filters: {
            name: '',
            peopleId: this.peopleId,
            id: '',
            workid: '',
            healthDate_s: '',
            healthDate_e: ''
          }
        }
        healthManagementRecord(para).then(res => {
          this.elderly1 = res.detailModelList
          if (this.elderly1.length !== 0) {
            for (const item of this.elderly1) {
              item.area = item.area.split('/')
            }
            this.elderly1Data = true
            this.generalData5 = true
          }
        })
        para = {
          page: 1,
          size: 200,
          filters: {
            name: '',
            peopleId: this.peopleId
          }
        }
        // 体检
        getMedical(para).then(res => {
          this.medical = res.detailModelList
          if (this.medical.length !== 0) {
            this.medicalData = true
            this.generalData1 = true
            for (const item of this.medical) {
              item.symptoms = item.symptoms.split(',')
              item.diet = item.diet.split(',')
              item.cerebrovascular = item.cerebrovascular.split(',')
              item.kidneyDisease = item.kidneyDisease.split(',')
              item.heartDisease = item.heartDisease.split(',')
              item.vascularDisease = item.vascularDisease.split(',')
              item.eyeDisease = item.eyeDisease.split(',')
              item.healthGuidance = item.healthGuidance.split(',')
              item.riskcontrol = item.riskcontrol.split(',')
            }
          }
        })
      },
      addOrderList(goods, obj) {
        this.coverShow = false
        this.hypertensiveShow = false
        this.servicerecordShow = false
        this.personalinformationShow = false
        this.diabetesShow = false
        this.tuberculosisfllowupShow = false
        this.tuberculosispatientsShow = false
        this.maternalShow = false
        this.postpartumShow = false
        this.postpartum42Show = false
        this.prenatalShow = false
        this.children1Show = false
        this.children2Show = false
        this.children3Show = false
        this.children4Show = false
        this.elderlyShow = false
        this.elderly1Show = false
        this.medicalShow = false
        this.perinforShow = false
        this[obj + 'Show'] = true
        this.editForm = goods
      },
      getage: function(birthday) {
        var birthdays = new Date(birthday.replace(/-/g, '/'))
        var d = new Date()
        var age =
          d.getFullYear() -
          birthdays.getFullYear() -
          (d.getMonth() < birthdays.getMonth() ||
          (d.getMonth() === birthdays.getMonth() &&
            d.getDate() < birthdays.getDate())
            ? 1
            : 0)
        this.editForm.gravidaAge = age
      },
      Add(obj) {
        this[obj + 'Visible'] = true
        this.$nextTick(() => {
          this.$refs[obj].handleAdd()
          const para = {
            page: 1,
            size: 200,
            filters: { name: '', peopleId: this.peopleId, cardNumber: '' }
          }
          getList(para).then(res => {
            const user = res.detailModelList
            if (user.length !== 0) {
              this.$refs[obj].editForm.peopleId = this.peopleId
              this.$refs[obj].editForm.name = user[0].name
              this.$refs[obj].editForm.area = user[0].houseAddress.split('/')
              this.$refs[obj].editForm.pastHistory = user[0].pastHistory.split(',')
              this.$refs[obj].getage(user[0].birth)
            }
          })
        })
      },
      // 显示编辑界面
      handleEdit(obj) {
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
        this.editForm = obj
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
        this.houseAddress_model = this.editForm.houseAddress.split('/')
        obj.createDate = !obj.createDate || obj.createDate === ''
          ? ''
          : util.formatDate.format(new Date(obj.createDate), 'yyyy-MM-dd')
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.birth =
                  !para.birth || para.birth === ''
                    ? ''
                    : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd')

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
                // 居住地
                para.houseAddress = ''
                for (const itm of this.houseAddress_model) {
                  if (itm !== '') {
                    para.houseAddress += itm
                    para.houseAddress += ','
                  }
                }
                para.houseAddress = para.houseAddress.substr(0, para.houseAddress.length - 1)

                //
                para.operation = ''
                for (const itm of this.editForm.operation) {
                  if (itm !== '') {
                    para.operation += itm
                    para.operation += ','
                  }
                }
                para.operation = para.operation.substr(0, para.operation.length - 1)

                updateData(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  const para = {
                    page: 1,
                    size: 200,
                    filters: { name: '', peopleId: this.peopleId, cardNumber: '' }
                  }
                  getList(para).then(res => {
                    this.user_id = res.detailModelList
                    if (this.user_id.length !== 0) {
                      this.peopleId = this.user_id[0].peopleId
                      this.name = this.user_id[0].name
                      this.user = this.user_id[0]
                      this.getdate()
                      return
                    }
                  })
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

                para.birth =
                  !para.birth || para.birth === ''
                    ? ''
                    : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd')

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
      /* 随访*/
      follow1() {
        this.nowFollowPage = 1
        this.getFollow()
        this.dialogFormVisible1 = true
      },
      follow2() {
        this.weekFollowPage = 1
        this.getFollow()
        this.dialogFormVisible2 = true
      },
      follow3() {
        this.notFollowPage = 1
        this.getFollow()
        this.dialogFormVisible3 = true
      },
      getFollow() {
        var time = new Date()
        var time2 = new Date(time)
        var time3 = new Date(time)
        time2.setDate(time2.getDate() + 7)
        time3.setDate(time3.getDate() - 1)
        time =
          !time || time === ''
            ? ''
            : util.formatDate.format(new Date(time), 'yyyy-MM-dd')
        time2 =
          !time2 || time2 === ''
            ? ''
            : util.formatDate.format(new Date(time2), 'yyyy-MM-dd')
        time3 =
          !time3 || time3 === ''
            ? ''
            : util.formatDate.format(new Date(time3), 'yyyy-MM-dd')
        // 当天随访
        var para = {
          page: this.nowFollowPage,
          size: 20,
          filters: { nextFollow_s: time, nextFollow_e: time, peopleId: '', userId: this.$store.getters.id }
        }
        getFollow(para).then(res => {
          this.nowFollowTotal = res.totalCount
          this.nowFollow = res.detailModelList
        })
        // 未来7天随访
        para = {
          page: this.weekFollowPage,
          size: 20,
          filters: { nextFollow_s: time, nextFollow_e: time2, peopleId: '', userId: this.$store.getters.id }
        }
        getFollow(para).then(res => {
          this.weekFollowTotal = res.totalCount
          this.weekFollow = res.detailModelList
        })
        // 未随访
        para = {
          page: this.notFollowPage,
          size: 20,
          filters: { nextFollow_s: '', nextFollow_e: time3, peopleId: '', userId: this.$store.getters.id }
        }
        getFollow(para).then(res => {
          this.notFollowTotal = res.totalCount
          this.notFollow = res.detailModelList
        })
      },
      handleCurrentChange1(val) {
        this.nowFollowPage = val
        this.getFollow()
      },
      handleCurrentChange2(val) {
        this.weekFollowPage = val
        this.getFollow()
      },
      handleCurrentChange3(val) {
        this.notFollowPage = val
        this.getFollow()
      }
    },
    watch: {
      '$route': 'getParams',
      'this.$refs.hypertensive.editForm.dialogFormVisible': function() {
        this.$refs.hypertensive.editForm.dialogFormVisible = true
      }
    },
    mounted() {
      this.buttonShow = false
      this.buttons = this.getButton(this.buttons, this.$store.getters.buttons)
      for (const i in this.buttons) {
        if (this.buttons[i] === true) {
          this.buttonShow = true
          break
        }
      }
      this.getFollow()
    },
    computed: {}
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .td2{
    text-align: right;
    padding-right: 15px;
    padding-top: 9px;
    padding-bottom: 9px;
  }
  span{
    color:red;
  }
  .pos {
    font-size: 12px;
  }

  #order-list {
    height: 800px;
  }

  .pos-order {
    background-color: #ffffff;
    /* border-right: 10px solid #C0CCDA; */
    /*    padding-right: 20px;*/
    padding-left: 15px;
  }

  .order-btn {
    margin-top: 10px;
    text-align: center;
  }

  .title {
    height: 20px;
    border-bottom: 1px solid #d3dce6;
    background-color: #ffffff;
    padding: 20px;
  }

  .often-goods-list ul li {
    list-style: none;
    float: left;
    border: 1px solid #e5e9f2;
    padding: 20px;
    margin: 5px;
    background-color: #fff;
    cursor: pointer;
  }

  .goods-type {
    clear: both;
    padding: 20px;
  }

  .o-price {
    color: #58b7ff;
  }

  .often-goods-list {
    border-bottom: 1px solid #c0ccda;
    height: auto;
    overflow: hidden;
    padding-bottom: 10px;
    background-color: #f9fafc;
  }

  .cookList li {
    list-style: none;
    width: 23%;
    border: 1px solid #e5e9f2;
    height: auot;
    overflow: hidden;
    background-color: #fff;
    padding: 2px;
    float: left;
    margin: 2px;
    cursor: pointer;
  }

  .cookList li span {
    display: block;
    float: left;
  }

  .foodImg {
    width: 40%;
  }

  .foodName {
    font-size: 18px;
    padding-left: 10px;
    color: brown;
  }

  .foodPrice {
    font-size: 16px;
    padding-left: 10px;
    padding-top: 10px;
  }

  .totalDiv {
    height: auot;
    overflow: hidden;
    text-align: right;
    font-size: 16px;
    background-color: #fff;
    border-bottom: 1px solid #e5e9f2;
    padding: 10px;
  }
  i{
    margin-left: 10px;
    margin-right: 20px;
  }
  .item {
    margin-top: 10px;
    margin-right: 40px;
  }
  /*  .ad {
      width: 220px;
      border: none 0;

      outline: medium;

      background: transparent;

      -webkit-appearance: none;

      -moz-appearance: none;

      -webkit-tap-highlight-color: rgba(0, 0, 0, 0);

      -moz-tap-highlight-color: transparent;
      background-color:transparent;
      border-bottom: 1px solid #ccc;
    }*/
  .ad{
    height:40px;
    width:400px;
  }
  .bg{
    background-size: cover;
    background-image:url(5.png);
  }
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
</style>
