<template>
  <section class="app-container">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.peopleId" placeholder="身份证" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers" style="background-color:#4db2ee;border-color:transparent;height:40px" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="findbtn" style="background-color:#4db2ee;border-color:transparent;width: 115px;height:40px" icon="el-icon-search">高级查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button v-if="buttons.KPF_C" type="primary" style="height:40px" @click="handleAdd" icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--输入、编辑-->

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <div id="print">
        <el-form :model="editForm" label-width="120px" :rules="editFormRules" ref="editForm">
          <EasyScrollbar>
            <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
              <el-tabs v-model="activeName">
                <div id="dataPanel">
                  <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" style="padding-left:10px;padding-right:10px;margin-left:10px;margin-right:30px;border-collapse:collapse;">
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
                        <td style="width:140px;word-wrap:break-word;text-align:center;">
                          <el-col :span="12" v-if='false'>
                            <el-form-item label="编号">
                              <el-input v-model="editForm.id"></el-input>
                            </el-form-item>
                          </el-col>
                          <span>*</span>姓名
                        </td>
                        <td colspan="3" class="pad10" style="padding-right: 0px;">
                          <el-form-item label="" label-width="0px" prop="name" ref="name">
                            <el-input  v-model="editForm.name" placeholder="请输入姓名" disabled="false"></el-input>
                          </el-form-item>
                        </td>
                        <td style="width:140px;word-wrap:break-word;text-align:center;"><span>*</span>区域</td>
                        <td colspan="3" class="pad10" style="padding-right: 0px;">
                          <el-form-item label="" label-width="0px" prop="area">
                            <el-cascader :options="options" v-model="editForm.area" style="width: 300px;" disabled="false" placeholder="请输入区域" change-on-select></el-cascader>
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
              </el-tabs>
            </div>
          </EasyScrollbar>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer" center>
        <el-button style="height:40px" @click="conprint()">打印</el-button>
        <el-button style="height:40px" v-if="dialogStatus=='create'" type="primary" @click="check">添加</el-button>
        <el-button style="height:40px" v-else type="primary" v-if="edit" @click="check">新增修改</el-button>
        <el-button style="height:40px" v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button style="height:40px" @click.native="dialogFormVisible=false">取消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center >
      <el-form :model="findForm" label-width="120px" :rules="findFormRules" ref="findForm">
        <el-row>
          <el-col :span="12" >
            <el-form-item   label="姓名">
              <el-input v-model="filters.name" placeholder="姓名" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item  label="随访医生签名">
              <el-input v-model="findForm.doctor" placeholder="随访医生签名"  auto-complete="off" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" >
            <el-form-item  label="机构及科室">
              <el-input v-model="findForm.insandDep" placeholder="机构及科室"  auto-complete="off" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="填表日期">
              <el-row>
                <el-col :span="12" >
                  <el-date-picker type="date" placeholder="开始日期" v-model="findForm.date_s" style="width: 100%;"></el-date-picker>
                </el-col>
                <el-col :span="12" >
                  <el-date-picker type="date" placeholder="结束日期" v-model="findForm.date_e" style="width: 100%;"></el-date-picker>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" v-on:click="getUsers" style="background-color:#4db2ee;border-color:transparent;height:40px" icon="el-icon-search">查询</el-button>
      </div>
    </el-dialog>

    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;" @row-dblclick="handleRowChange">
      <el-table-column type="index" label="序号" width="70">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="80" style="text-align:center">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证" width="180">
      </el-table-column>
      <el-table-column prop="id" v-if='false' label="编号" width="60">
      </el-table-column>
      <el-table-column prop="date" label="填表日期" width="100">
      </el-table-column>
      <el-table-column prop="gestationalWeeks" v-if='false' label="孕周" width="60">
      </el-table-column>
      <el-table-column prop="gravidaAge" v-if='false' label="孕妇年龄" width="100">
      </el-table-column>
      <el-table-column prop="husbandName" v-if='false' label="丈夫姓名" min-width="120">
      </el-table-column>
      <el-table-column prop="husbandAge" v-if='false' label="丈夫年龄" min-width="100">
      </el-table-column>
      <el-table-column prop="husbandPhone" v-if='false' label="丈夫电话" min-width="120">
      </el-table-column>
      <el-table-column prop="gestationalNumber" v-if='false' label="孕次" min-width="60">
      </el-table-column>
      <el-table-column prop="vaginalDelivery" v-if='false' label="阴道分娩次" min-width="60">
      </el-table-column>
      <el-table-column prop="cesareanSection" v-if='false' label="剖宫产次" min-width="60">
      </el-table-column>
      <el-table-column prop="lastMenses" v-if='false' label="末次月经" min-width="100">
      </el-table-column>
      <el-table-column prop="expect" v-if='false' label="预产期" min-width="80">
      </el-table-column>
      <el-table-column prop="pastHistory" v-if='false' label="既往史" min-width="100">
      </el-table-column>
      <el-table-column prop="familyHistory" v-if='false' label="家族史" min-width="120">
      </el-table-column>
      <el-table-column prop="personalHistory" v-if='false' label="个人史" min-width="120">
      </el-table-column>
      <el-table-column prop="abOperation" v-if='false' label="妇产科手术史" min-width="120">
      </el-table-column>
      <el-table-column prop="historyPregnancy" v-if='false' label="孕产史" min-width="120">
      </el-table-column>
      <el-table-column prop="height" v-if='false' label="身高" min-width="60">
      </el-table-column>
      <el-table-column prop="weight" v-if='false' label="体重" min-width="60">
      </el-table-column>
      <el-table-column prop="bodymass" v-if='false' label="体质指数(BMI)" min-width="140">
      </el-table-column>
      <el-table-column prop="blood" v-if='false' label="血压" min-width="60">
      </el-table-column>
      <el-table-column prop="heart" v-if='false' label="听诊" min-width="80">
      </el-table-column>
      <el-table-column prop="lungs" v-if='false' label="肺部" min-width="80">
      </el-table-column>
      <el-table-column prop="vulva" v-if='false' label="外阴" min-width="80">
      </el-table-column>
      <el-table-column prop="vagina" v-if='false' label="阴道" min-width="80">
      </el-table-column>
      <el-table-column prop="cervix" v-if='false' label="宫颈" min-width="80">
      </el-table-column>
      <el-table-column prop="uterus" v-if='false' label="子宫" min-width="80">
      </el-table-column>
      <el-table-column prop="enclosure" v-if='false' label="附件" min-width="80">
      </el-table-column>
      <el-table-column prop="hemoglobin" v-if='false' label="血红蛋白" min-width="100">
      </el-table-column>
      <el-table-column prop="whiteBlood" v-if='false' label="白细胞" min-width="80">
      </el-table-column>
      <el-table-column prop="plateletCount" v-if='false' label="血小板" min-width="80">
      </el-table-column>
      <el-table-column prop="otherBlood" v-if='false' label="其他" min-width="80">
      </el-table-column>
      <el-table-column prop="urineProtein" v-if='false' label="尿蛋白" min-width="80">
      </el-table-column>
      <el-table-column prop="urineSugar" v-if='false' label="尿糖" min-width="80">
      </el-table-column>
      <el-table-column prop="urineKetone" v-if='false' label="尿酮体" min-width="80">
      </el-table-column>
      <el-table-column prop="bld" v-if='false' label="尿潜血" min-width="80">
      </el-table-column>
      <el-table-column prop="otherUrine" v-if='false' label="其他（尿常规）" min-width="80">
      </el-table-column>
      <el-table-column prop="bloodType" v-if='false' label="血型" min-width="80">
      </el-table-column>
      <el-table-column prop="bloodSugar" v-if='false' label="血糖" min-width="80">
      </el-table-column>
      <el-table-column prop="sgpt" v-if='false' label="血清谷丙转氨酶" min-width="160">
      </el-table-column>
      <el-table-column prop="sgot" v-if='false' label="血清谷草转氨酶" min-width="160">
      </el-table-column>
      <el-table-column prop="albumin" v-if='false'  label="白蛋白" min-width="80">
      </el-table-column>
      <el-table-column prop="totalBilirubin" v-if='false'label="总胆红素" min-width="100">
      </el-table-column>
      <el-table-column prop="conjugated" v-if='false' label="结合胆红素" min-width="140">
      </el-table-column>
      <el-table-column prop="scr" v-if='false' label="血清肌酐" min-width="100">
      </el-table-column>
      <el-table-column prop="bloodUrea" v-if='false' label="血尿素" min-width="80">
      </el-table-column>
      <el-table-column prop="vaginalSecretions" v-if='false' label="阴道分泌物" min-width="120">
      </el-table-column>
      <el-table-column prop="vaginalCleanliness" v-if='false' label="阴道清洁度" min-width="120">
      </el-table-column>
      <el-table-column prop="hepatitisB" v-if='false' label="乙型肝炎" min-width="100">
      </el-table-column>
      <el-table-column prop="syphilis" v-if='false' label="梅毒血清学实验" min-width="150">
      </el-table-column>
      <el-table-column prop="hivAntibody" v-if='false' label="HIV抗体检测" min-width="150">
      </el-table-column>
      <el-table-column prop="bMode" v-if='false' label="B超" min-width="80">
      </el-table-column>
      <el-table-column prop="otherSassist" v-if='false' label="其他" min-width="100">
      </el-table-column>
      <el-table-column prop="healthcare" v-if='false' label="保健指导" min-width="120">
      </el-table-column>
      <el-table-column prop="referral" label="转诊" min-width="60">
      </el-table-column>
      <el-table-column prop="reason" v-if='false' label="原因" min-width="120">
      </el-table-column>
      <el-table-column prop="insandDep"  v-if='false'  label="机构及科室" min-width="120">
      </el-table-column>
      <el-table-column prop="nextdate" label="下次随访日期" min-width="120" v-if='false'>
      </el-table-column>
      <el-table-column prop="doctor" label="随访医生签名" min-width="120">
      </el-table-column>
      <el-table-column prop="creator" label="创建人" min-width="100">
      </el-table-column>
      <el-table-column prop="createDate" label="创建时间" min-width="100" :formatter="timeFormat">
      </el-table-column>
      <el-table-column prop="modifier" label="修改人" min-width="100">
      </el-table-column>
      <el-table-column prop="modifyDate" label="修改时间" min-width="120" :formatter="timeFormat">
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <a size="14px" @click="goto(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">查看档案</a>
          <a size="14px" v-if="buttons.KPF_E" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" v-if="buttons.KPF_D" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
        </template>
      </el-table-column>
    </el-table>
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
    batchRemoveData,
    updateData,
    addData,
    checkData as getPerson
  } from '@/api/firstPrenatalDiagnosis'
  import city from '@/api/city.js'
  import {
    getList as getFollow,
    updateData as editFollow,
    addData as addFollow
  } from '@/api/follow'
  import {
    getList as getSearchMemberPage
  } from '@/api/Perinfor'
  export default {
    data() {
      return {
        buttons: {
          KPF_C: false,
          KPF_E: false,
          KPF_D: false
        },
        edit: true,
        activeName: 'first',
        tableHeight: window.innerHeight - 210,
        tablebodyHeight: window.innerHeight - 320,
        options: city.city(),
        options2: city.occurPlace(),
        hepatitisB: [
          { value: '乙型肝炎表面抗原', label: '乙型肝炎表面抗原' },
          { value: '乙型肝炎表面抗体', label: '乙型肝炎表面抗体' },
          { value: '乙型肝炎e抗原', label: '乙型肝炎e抗原' },
          { value: '乙型肝炎e抗体', label: '乙型肝炎e抗体' },
          { value: '乙型肝炎核心抗体', label: '乙型肝炎核心抗体' }
        ],
        healthcare: [
          { value: '生活方式', label: '生活方式' },
          { value: '心理', label: '心理' },
          { value: '营养', label: '营养' },
          { value: '避免致畸因素和疾病对胚胎的不良影响', label: '避免致畸因素和疾病对胚胎的不良影响' },
          { value: '产前筛查宣告通知', label: '产前筛查宣告通知' },
          { value: '其他', label: '其他' }
        ],
        familyHistory: [
          { value: '无', label: '无' },
          { value: '遗传性疾病史', label: '遗传性疾病史' },
          { value: '精神疾病史', label: '精神疾病史' },
          { value: '其他', label: '其他' }
        ],
        dialogStatus: '',
        area: [],
        textMap: {
          update: '编辑',
          create: '添加',
          search: '查询',
          detail: '详情'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          name: '',
          peopleId: null
        },
        ceshi: '',
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [
            { required: true, message: ' ', trigger: 'blur' }
          ],
          peopleId: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: ' ' }
          ],
          area: [
            { required: true, message: ' ', trigger: 'blur' }
          ]
        },
        findFormRules: {
          name: [{ required: true, message: '', trigger: 'blur' }]
        },
        // 编辑界面数据
        editForm: {
          id: '',
          name: '',
          date: null,
          peopleId: '',
          gestationalWeeks: '',
          gravidaAge: '',
          husbandName: '',
          husbandAge: '',
          husbandPhone: '',
          gestationalNumber: '',
          vaginalDelivery: '',
          cesareanSection: '',
          lastMenses: null,
          expect: '',
          pastHistory: [],
          familyHistory: [],
          personalHistory: [],
          abOperation: '',
          historyPregnancy: [],
          height: '',
          weight: '',
          blood: '',
          bodymass: '',
          heart: '',
          lungs: '',
          vulva: '',
          vagina: '',
          cervix: '',
          uterus: '',
          enclosure: '',
          hemoglobin: '',
          whiteBlood: '',
          plateletCount: '',
          otherBlood: '',
          urineProtein: '',
          urineSugar: '',
          urineKetone: '',
          bld: '',
          otherUrine: '',
          bloodType: '',
          bloodSugar: '',
          sgpt: '',
          sgot: '',
          albumin: '',
          totalBilirubin: '',
          conjugated: '',
          scr: '',
          bloodUrea: '',
          vaginalSecretions: [],
          vaginalCleanliness: '',
          hepatitisB: [],
          syphilis: '',
          hivAntibody: '',
          bMode: '',
          otherSassist: '',
          population: '',
          healthcare: [],
          insandDep: '',
          referral: '',
          reason: '',
          nextdate: null,
          doctor: '',
          creator: '',
          createDate: null,
          modifier: '',
          modifyDate: null
        },
        // 查找界面数据
        findForm: {
          name: '',
          date: null,
          insandDep: '',
          doctor: '',
          creator: '',
          createDate: null,
          modifier: '',
          modifyDate: null,
          date_s: null,
          date_e: null
        },
        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      // 性别显示转换
      formatSex: function(row, column) {
        return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
      },
      goto(index, row) {
        this.$router.push({
          name: '首页',
          params: {
            hid: row.peopleId
          }
        })
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
          '\t\t<h2 style="width:794px;text-align:center">第1次产前检查服务记录表</h2>  \n' +
          '\t\t<span style="margin-left:34px;">姓名:{{name}}</span>\n' +
          '\t\t<span style="margin-left:270px;">NO:{{id}}</span> \n' +
          '\t</div>\n' +
          '\t<table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '\t<tbody>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">填表日期</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">{{date}}</td>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">孕周</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">{{gestationalWeeks}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">孕妇年龄</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:center">{{gravidaAge}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">丈夫姓名</td>\n' +
          '\t\t\t<td colspan="1" style="text-align:center">{{husbandName}}</td>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">丈夫年龄</td>\n' +
          '\t\t\t<td colspan="1" style="text-align:center">{{husbandAge}}</td>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">丈夫电话</td>\n' +
          '\t\t\t<td colspan="1" style="text-align:center">{{husbandPhone}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">孕次</td>\n' +
          '\t\t\t<td colspan="1" style="text-align:center">{{gestationalNumber}}</td>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">产次</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:center">{{vaginalDelivery}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">末次月经</td>\n' +
          '\t\t\t<td colspan="1" style="text-align:center">{{lastMenses}}</td>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">预产期</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:center">{{expect}}</td>\n' +
          '\t\t</tr>\n' +
          '\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">既往史</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:center">{{pastHistory}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">家族史</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:center">{{familyHistory}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">个人史</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">{{personalHistory}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">妇产科手术史</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">{{abOperation}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">孕产史</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">{{historyPregnancy}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">身高</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:left">{{height}}cm</td>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">体重</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:left">{{weight}}Kg</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">体质指数（BMI）</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:left">{{bodymass}}kg/m2</td>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">血压</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:left">{{blood}}mmHg</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">听诊</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:left">心脏:{{heart}}</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:left">肺部:{{lungs}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td rowspan="3" style="word-wrap:break-word;text-align:center">妇科</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:left">外阴:{{vulva}}</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:left">阴道:{{vagina}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="text-align:left">宫颈:{{cervix}}</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:left">子宫:{{uterus}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">附件:{{enclosure}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td rowspan="14" colspan="1"style="word-wrap:break-word;text-align:center">辅助检查</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">血常规</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:left">血红蛋白值{{hemoglobin}}g/L&nbsp白细胞计数值{{whiteBlood}}/L&nbsp血小板计数值{{plateletCount}}/L &nbsp其他</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="word-wrap:break-word;text-align:center">尿常规</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:left">尿蛋白{{urineProtein}}&nbsp尿糖{{urineSugar}}&nbsp尿酮体{{urineKetone}}&nbsp尿潜血{{bld}}&nbsp其他</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td rowspan="2" colspan="1" style="text-align:center">血型</td>\n' +
          '\t\t\t<td colspan="1" style="text-align:center">ABO</td>\n' +
          '\t\t\t<td rowspan="2" colspan="3" style="text-align:center">{{bloodType}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="1" style="text-align:center">RH</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">血糖</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:center">{{bloodSugar}}mmol/L</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">肝功能</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:center">血清谷丙转氨酶{{sgpt}}U/L&nbsp&nbsp血清谷草转氨酶{{sgot}}U/L<br/>白蛋白{{albumin}}g/L&nbsp&nbsp总胆红素{{totalBilirubin}}umol/L&nbsp&nbsp结合胆红素{{conjugated}}umol/L</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">肾功能</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:center">血清肌酐{{scr}}umol/L&nbsp&nbsp血尿素{{bloodUrea}}mmol/L</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" rowspan="2" style="text-align:center">阴道分泌物</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:left">{{vaginalSecretions}}:</td>\n' +
          '\t\t\t\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="3" style="text-align:left">阴道清洁度:{{vaginalCleanliness}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" rowspan="2" style="text-align:center">乙型肝炎</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:left">{{hepatitisB}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">梅毒血清学试验</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:center">{{syphilis}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">HIV抗体检测</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:left">{{hivAntibody}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">B超</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:left">{{bMode}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">其他</td>\n' +
          '\t\t\t<td colspan="3" style="text-align:left">{{otherSassist}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">总体评估</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">{{population}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">保健指导</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">{{healthcare}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="6" style="text-align:left">转诊:{{referral}}<br/>原因:{{reason}}<br/>机构及科室{{insandDep}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="text-align:center">下次随访日期</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">{{nextdate}}</td>\n' +
          '\t\t\t<td style="text-align:center">随访医生签名</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">{{doctor}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t</tbody>\n' +
          '\t</table>\n' +
          '</div>\n' +
          '</body>\n' +
          '</html>\n'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{id}}/, this.editForm.id ? this.editForm.id : '')
        str = str.replace(/{{date}}/, this.editForm.date ? this.editForm.date : '')
        str = str.replace(/{{gestationalWeeks}}/, this.editForm.gestationalWeeks ? this.editForm.gestationalWeeks : '')
        str = str.replace(/{{gravidaAge}}/, this.editForm.gravidaAge ? this.editForm.gravidaAge : '')
        str = str.replace(/{{husbandName}}/, this.editForm.husbandName ? this.editForm.husbandName : '')
        str = str.replace(/{{husbandAge}}/, this.editForm.husbandAge ? this.editForm.husbandAge : '')
        str = str.replace(/{{husbandPhone}}/, this.editForm.husbandPhone ? this.editForm.husbandPhone : '')
        str = str.replace(/{{gestationalNumber}}/, this.editForm.gestationalNumber ? this.editForm.gestationalNumber : '')
        str = str.replace(/{{vaginalDelivery}}/, this.editForm.vaginalDelivery ? this.editForm.vaginalDelivery : '')
        str = str.replace(/{{lastMenses}}/, this.editForm.lastMenses ? this.editForm.lastMenses : '')
        str = str.replace(/{{expect}}/, this.editForm.expect ? this.editForm.expect : '')
        str = str.replace(/{{pastHistory}}/, this.editForm.pastHistory ? this.editForm.pastHistory : '')
        str = str.replace(/{{familyHistory}}/, this.editForm.familyHistory ? this.editForm.familyHistory : '')
        str = str.replace(/{{personalHistory}}/, this.editForm.personalHistory ? this.editForm.personalHistory : '')
        str = str.replace(/{{abOperation}}/, this.editForm.abOperation ? this.editForm.abOperation : '')
        str = str.replace(/{{historyPregnancy}}/, this.editForm.historyPregnancy ? this.editForm.historyPregnancy : '')
        str = str.replace(/{{height}}/, this.editForm.height ? this.editForm.height : '')
        str = str.replace(/{{weight}}/, this.editForm.weight ? this.editForm.weight : '')
        str = str.replace(/{{bodymass}}/, this.editForm.bodymass ? this.editForm.bodymass : '')
        str = str.replace(/{{blood}}/, this.editForm.blood ? this.editForm.blood : '')

        str = str.replace(/{{heart}}/, this.editForm.heart ? this.editForm.heart : '')
        str = str.replace(/{{lungs}}/, this.editForm.lungs ? this.editForm.lungs : '')
        str = str.replace(/{{vulva}}/, this.editForm.vulva ? this.editForm.vulva : '')
        str = str.replace(/{{vagina}}/, this.editForm.vagina ? this.editForm.vagina : '')
        str = str.replace(/{{cervix}}/, this.editForm.cervix ? this.editForm.cervix : '')
        str = str.replace(/{{uterus}}/, this.editForm.uterus ? this.editForm.uterus : '')
        str = str.replace(/{{enclosure}}/, this.editForm.enclosure ? this.editForm.enclosure : '')
        str = str.replace(/{{hemoglobin}}/, this.editForm.hemoglobin ? this.editForm.hemoglobin : '')

        str = str.replace(/{{whiteBlood}}/, this.editForm.whiteBlood ? this.editForm.whiteBlood : '')
        str = str.replace(/{{plateletCount}}/, this.editForm.plateletCount ? this.editForm.plateletCount : '')
        str = str.replace(/{{urineProtein}}/, this.editForm.urineProtein ? this.editForm.urineProtein : '')
        str = str.replace(/{{urineSugar}}/, this.editForm.urineSugar ? this.editForm.urineSugar : '')
        str = str.replace(/{{urineKetone}}/, this.editForm.urineKetone ? this.editForm.urineKetone : '')
        str = str.replace(/{{bld}}/, this.editForm.bld ? this.editForm.bld : '')
        str = str.replace(/{{bloodType}}/, this.editForm.bloodType ? this.editForm.bloodType : '')
        str = str.replace(/{{bloodSugar}}/, this.editForm.bloodSugar ? this.editForm.bloodSugar : '')

        str = str.replace(/{{sgpt}}/, this.editForm.sgpt ? this.editForm.sgpt : '')
        str = str.replace(/{{sgot}}/, this.editForm.sgot ? this.editForm.sgot : '')
        str = str.replace(/{{albumin}}/, this.editForm.albumin ? this.editForm.albumin : '')
        str = str.replace(/{{totalBilirubin}}/, this.editForm.totalBilirubin ? this.editForm.totalBilirubin : '')
        str = str.replace(/{{conjugated}}/, this.editForm.conjugated ? this.editForm.conjugated : '')
        str = str.replace(/{{scr}}/, this.editForm.scr ? this.editForm.scr : '')
        str = str.replace(/{{bloodUrea}}/, this.editForm.bloodUrea ? this.editForm.bloodUrea : '')
        str = str.replace(/{{vaginalSecretions}}/, this.editForm.vaginalSecretions ? this.editForm.vaginalSecretions : '')

        str = str.replace(/{{vaginalCleanliness}}/, this.editForm.vaginalCleanliness ? this.editForm.vaginalCleanliness : '')
        str = str.replace(/{{hepatitisB}}/, this.editForm.hepatitisB ? this.editForm.hepatitisB : '')
        str = str.replace(/{{syphilis}}/, this.editForm.syphilis ? this.editForm.syphilis : '')
        str = str.replace(/{{hivAntibody}}/, this.editForm.hivAntibody ? this.editForm.hivAntibody : '')
        str = str.replace(/{{bMode}}/, this.editForm.bMode ? this.editForm.bMode : '')
        str = str.replace(/{{otherSassist}}/, this.editForm.otherSassist ? this.editForm.otherSassist : '')
        str = str.replace(/{{population}}/, this.editForm.population ? this.editForm.population : '')
        str = str.replace(/{{healthcare}}/, this.editForm.healthcare ? this.editForm.healthcare : '')

        str = str.replace(/{{referral}}/, this.editForm.referral ? this.editForm.referral : '')
        str = str.replace(/{{reason}}/, this.editForm.reason ? this.editForm.reason : '')
        str = str.replace(/{{insandDep}}/, this.editForm.insandDep ? this.editForm.insandDep : '')
        str = str.replace(/{{nextdate}}/, this.editForm.nextdate ? this.editForm.nextdate : '')
        str = str.replace(/{{doctor}}/, this.editForm.doctor ? this.editForm.doctor : '')
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
      // 列表时间格式化
      timeFormat: function(row, column) {
        let date = row[column.property]
        date =
          !date || date === ''
            ? ''
            : util.formatDate.format(new Date(date), 'yyyy-MM-dd')
        return date
      },
      findbtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          name: '',
          insandDep: '',
          doctor: ''
        }
      },
      handleRowChange(row, event) {
        this.dialogStatus = 'detail'
        this.dialogFormVisible = true
        this.area = []
        this.editForm = Object.assign({}, row)
        this.editForm.historyPregnancy = this.editForm.historyPregnancy.split(',')
        this.editForm.vaginalSecretions = this.editForm.vaginalSecretions.split(',')
        this.editForm.hepatitisB = this.editForm.hepatitisB.split(',')
        this.editForm.healthcare = this.editForm.healthcare.split(',')
        this.editForm.pastHistory = this.editForm.pastHistory.split(',')
        this.editForm.familyHistory = this.editForm.familyHistory.split(',')
        this.editForm.personalHistory = this.editForm.personalHistory.split(',')
        this.editForm.area = this.editForm.area.split('/')
        this.edit = false
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.area = []
        this.editForm = Object.assign({}, row)
        this.editForm.historyPregnancy = this.editForm.historyPregnancy.split(',')
        this.editForm.vaginalSecretions = this.editForm.vaginalSecretions.split(',')
        this.editForm.hepatitisB = this.editForm.hepatitisB.split(',')
        this.editForm.healthcare = this.editForm.healthcare.split(',')
        this.editForm.pastHistory = this.editForm.pastHistory.split(',')
        this.editForm.familyHistory = this.editForm.familyHistory.split(',')
        this.editForm.personalHistory = this.editForm.personalHistory.split(',')
        this.editForm.area = this.editForm.area.split('/')
        this.edit = true
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.area = []
        this.editForm = {
          id: '',
          name: '',
          date: new Date(),
          peopleId: '',
          gestationalWeeks: '',
          gravidaAge: '',
          husbandName: '',
          husbandAge: '',
          husbandPhone: '',
          gestationalNumber: '',
          vaginalDelivery: '',
          cesareanSection: '',
          lastMenses: new Date(),
          expect: '',
          pastHistory: [],
          familyHistory: [],
          personalHistory: [],
          abOperation: '',
          historyPregnancy: [],
          height: '',
          weight: '',
          blood: '',
          bodymass: '',
          heart: '',
          lungs: '',
          vulva: '',
          vagina: '',
          cervix: '',
          uterus: '',
          enclosure: '',
          hemoglobin: '',
          whiteBlood: '',
          plateletCount: '',
          otherBlood: '',
          otherUrine: '',
          urineProtein: '',
          urineSugar: '',
          urineKetone: '',
          bld: '',
          bloodType: '',
          bloodSugar: '',
          sgpt: '',
          sgot: '',
          albumin: '',
          totalBilirubin: '',
          scr: '',
          bloodUrea: '',
          vaginalSecretions: [],
          vaginalCleanliness: '',
          hepatitisB: [],
          syphilis: '',
          hivAntibody: '',
          bMode: '',
          otherSassist: '',
          population: '',
          healthcare: [],
          insandDep: '',
          referral: '',
          reason: '',
          nextdate: new Date(),
          doctor: '',
          creator: this.$store.getters.name,
          createDate: null,
          modifier: '',
          modifyDate: null
        }
      },
      // 获取用户列表
      getUsers() {
        this.findForm.date_s =
          !this.findForm.date_s || this.findForm.date_s === ''
            ? ''
            : util.formatDate.format(new Date(this.findForm.date_s), 'yyyy-MM-dd')
        this.findForm.date_e =
          !this.findForm.date_e || this.findForm.date_e === ''
            ? ''
            : util.formatDate.format(new Date(this.findForm.date_e), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          size: 20,
          filters: {
            name: this.filters.name,
            date_s: this.findForm.date_s,
            date_e: this.findForm.date_e,
            peopleId: this.filters.peopleId,
            insandDep: this.findForm.insandDep,
            doctor: this.findForm.doctor
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
          insandDep: '',
          doctor: '',
          date_s: '',
          date_e: ''
        }
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
                this.$message.error('未建立个人档案或该居民不是孕妇')
              }
            })
          } else {
            this.$message.error('未建立个人档案或该居民不是孕妇')
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
              this.getUsers()
            })
          })
          .catch(() => {})
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
                para.date =
                  !para.date || para.date === ''
                    ? ''
                    : util.formatDate.format(new Date(para.date), 'yyyy-MM-dd')
                para.lastMenses =
                  !para.lastMenses || para.lastMenses === ''
                    ? ''
                    : util.formatDate.format(new Date(para.lastMenses), 'yyyy-MM-dd')
                para.nextdate =
                  !para.nextdate || para.nextdate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.nextdate), 'yyyy-MM-dd')

                para.historyPregnancy = ''
                for (const itm of this.editForm.historyPregnancy) {
                  if (itm !== '') {
                    para.historyPregnancy += itm
                    para.historyPregnancy += ','
                  }
                }
                para.historyPregnancy = para.historyPregnancy.substr(0, para.historyPregnancy.length - 1)

                para.vaginalSecretions = ''
                for (const itm of this.editForm.vaginalSecretions) {
                  if (itm !== '') {
                    para.vaginalSecretions += itm
                    para.vaginalSecretions += ','
                  }
                }
                para.vaginalSecretions = para.vaginalSecretions.substr(0, para.vaginalSecretions.length - 1)

                para.hepatitisB = ''
                for (const itm of this.editForm.hepatitisB) {
                  if (itm !== '') {
                    para.hepatitisB += itm
                    para.hepatitisB += ','
                  }
                }
                para.hepatitisB = para.hepatitisB.substr(0, para.hepatitisB.length - 1)

                para.healthcare = ''
                for (const itm of this.editForm.healthcare) {
                  if (itm !== '') {
                    para.healthcare += itm
                    para.healthcare += ','
                  }
                }
                para.healthcare = para.healthcare.substr(0, para.healthcare.length - 1)
                para.area = ''
                for (const itm of this.editForm.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                para.pastHistory = ''
                for (const itm of this.editForm.pastHistory) {
                  if (itm !== '') {
                    para.pastHistory += itm
                    para.pastHistory += ','
                  }
                }
                para.pastHistory = para.pastHistory.substr(0, para.pastHistory.length - 1)

                para.familyHistory = ''
                for (const itm of this.editForm.familyHistory) {
                  if (itm !== '') {
                    para.familyHistory += itm
                    para.familyHistory += ','
                  }
                }
                para.familyHistory = para.familyHistory.substr(0, para.familyHistory.length - 1)

                para.personalHistory = ''
                for (const itm of this.editForm.personalHistory) {
                  if (itm !== '') {
                    para.personalHistory += itm
                    para.personalHistory += ','
                  }
                }
                para.personalHistory = para.personalHistory.substr(0, para.personalHistory.length - 1)
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
                    if (follow[0].nextFollow === '' || follow[0].nextFollow === null) {
                      follow[0].nextFollow = para.nextdate
                    } else if (follow[0].nextFollow < para.nextdate || para.nextdate === '' || para.nextdate === null) {
                      follow[0].nextFollow = para.nextdate
                    }
                    editFollow(follow[0]).then(res => {})
                  } else {
                    followData.nextFollow = para.nextdate
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
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.date =
                  !para.date || para.date === ''
                    ? ''
                    : util.formatDate.format(new Date(para.date), 'yyyy-MM-dd')
                para.lastMenses =
                  !para.lastMenses || para.lastMenses === ''
                    ? ''
                    : util.formatDate.format(new Date(para.lastMenses), 'yyyy-MM-dd')
                para.nextdate =
                  !para.nextdate || para.nextdate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.nextdate), 'yyyy-MM-dd')
                para.historyPregnancy = ''
                for (const itm of this.editForm.historyPregnancy) {
                  if (itm !== '') {
                    para.historyPregnancy += itm
                    para.historyPregnancy += ','
                  }
                }
                para.historyPregnancy = para.historyPregnancy.substr(0, para.historyPregnancy.length - 1)
                para.area = ''
                for (const itm of this.editForm.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                para.vaginalSecretions = ''
                for (const itm of this.editForm.vaginalSecretions) {
                  if (itm !== '') {
                    para.vaginalSecretions += itm
                    para.vaginalSecretions += ','
                  }
                }
                para.vaginalSecretions = para.vaginalSecretions.substr(0, para.vaginalSecretions.length - 1)

                para.hepatitisB = ''
                for (const itm of this.editForm.hepatitisB) {
                  if (itm !== '') {
                    para.hepatitisB += itm
                    para.hepatitisB += ','
                  }
                }
                para.hepatitisB = para.hepatitisB.substr(0, para.hepatitisB.length - 1)

                para.healthcare = ''
                for (const itm of this.editForm.healthcare) {
                  if (itm !== '') {
                    para.healthcare += itm
                    para.healthcare += ','
                  }
                }
                para.healthcare = para.healthcare.substr(0, para.healthcare.length - 1)

                para.pastHistory = ''
                for (const itm of this.editForm.pastHistory) {
                  if (itm !== '') {
                    para.pastHistory += itm
                    para.pastHistory += ','
                  }
                }
                para.pastHistory = para.pastHistory.substr(0, para.pastHistory.length - 1)

                para.familyHistory = ''
                for (const itm of this.editForm.familyHistory) {
                  if (itm !== '') {
                    para.familyHistory += itm
                    para.familyHistory += ','
                  }
                }
                para.familyHistory = para.familyHistory.substr(0, para.familyHistory.length - 1)

                para.personalHistory = ''
                for (const itm of this.editForm.personalHistory) {
                  if (itm !== '') {
                    para.personalHistory += itm
                    para.personalHistory += ','
                  }
                }
                para.personalHistory = para.personalHistory.substr(0, para.personalHistory.length - 1)

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
                    if (follow[0].nextFollow === '' || follow[0].nextFollow === null) {
                      follow[0].nextFollow = para.nextdate
                    } else if (follow[0].nextFollow < para.nextdate || para.nextdate === '' || para.nextdate === null) {
                      follow[0].nextFollow = para.nextdate
                    }
                    editFollow(follow[0]).then(res => {})
                  } else {
                    followData.nextFollow = para.nextdate
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
                  this.editForm.pastHistory = user[0].pastHistory
                  this.editForm.pastHistory = this.editForm.pastHistory.split(',')
                  this.getage(user[0].birth)
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

