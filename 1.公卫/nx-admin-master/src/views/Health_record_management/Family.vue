<template>
  <section class="app-container">
    <el-col :span="4" class="toolbar" style="padding-bottom: 0px;">
      <el-input clearable
        placeholder="输入关键字进行过滤"
        v-model="filterText">
      </el-input>

      <el-tree
        class="filter-tree"
        :data="options"
        :props="defaultProps"
        accordion
        @node-click="handleNodeClick"
        :filter-node-method="filterNode"
        ref="tree2">
      </el-tree>

    </el-col>
    <!--顶部工具栏部分-->
    <el-col :span="20" class="toolbar" style="padding-bottom: 0px;">
      <el-row>
        <el-form :inline="true" :model="filters" @submit.native.prevent :rules="TopbarRules">

          <el-form-item>
            <el-input v-model.number="filters.id" placeholder="请输入档案编号..." clearable style="margin-left: 20px"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" v-on:click="search" style="margin-left: 20px;height: 40px;text-align:right;background-color:#4db2ee;border-color:transparent"
                       icon="el-icon-search">查询
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="height:40px;width: 115px;" v-on:click="findbtn" class="searchbtn"
                       icon="el-icon-search">高级查询
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" v-if="buttons.HRFM_C" style="height:40px;" @click="handleAdd"  icon="el-icon-circle-plus-outline">新增</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="danger" v-if="buttons.HRFM_Merge" style="height:40px;width: 115px;" @click="handlemamberMager"  :disabled="this.sels.length===0||this.sels.length===1">合并档案</el-button>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row>
        <!--列表-->
        <el-table :data="users" :height="tableHeight" border highlight-current-row @selection-change="selsChange"
                  style="width: 100%;margin-left: 20px;margin-top: 20px" @row-click="handRowClick" @row-dblclick="handleRowChange" >

          <!-- 记录勾选控件-->
          <el-table-column type="selection" width="55">
          </el-table-column>

          <el-table-column type="index" label="序号" width="60">
          </el-table-column>

          <el-table-column v-if="false" prop="id" label="档案编号" width="120">
          </el-table-column>

          <el-table-column prop="headeName" label="户主姓名" width="100">
          </el-table-column>

          <el-table-column v-if="false" prop="responseDoctor" label="责任医生" width="120">
          </el-table-column>

          <el-table-column prop="belongCommunity" label="归属社区" width="120">
          </el-table-column>

          <el-table-column v-if="false" prop="neighborhoodCommittee" label="居委会" width="120">
          </el-table-column>

          <el-table-column v-if="false" prop="localPlace" label="家庭住址" width="120">
          </el-table-column>

          <el-table-column v-if="false" prop="zipCode" label="邮政编码" width="120">
          </el-table-column>

          <el-table-column prop="familyNum" v-if="false" label="家庭人口数" width="120">
          </el-table-column>

          <el-table-column prop="livePeople" v-if="false" label="现居住人数" width="120">
          </el-table-column>

          <el-table-column prop="area" v-if="false" label="居住面积" width="120">
          </el-table-column>

          <el-table-column prop="familyType" v-if="false" label="家庭类型" width="120">
          </el-table-column>

          <el-table-column prop="drinkWater" v-if="false" label="饮水" width="120">
          </el-table-column>

          <el-table-column prop="kitchenExhaust" v-if="false" label="厨房排风" width="120">
          </el-table-column>

          <el-table-column prop="fuelType" v-if="false" label="燃料类型" width="120">
          </el-table-column>

          <el-table-column prop="toilet" v-if="false" label="厕所" width="120">
          </el-table-column>

          <el-table-column prop="fbTime" label="建档时间" width="120" :formatter="timeFormat">
          </el-table-column>

          <el-table-column  prop="fbPeople" label="建档人" width="120">
          </el-table-column>

          <el-table-column v-if="false" prop="fbOrganization" label="建档机构" width="120">
          </el-table-column>

          <el-table-column prop="creater" v-if="false" label="创建人" width="120">
          </el-table-column>


          <el-table-column prop="createDate"  v-if="false" label="创建时间" width="120" :formatter="timeFormat">
          </el-table-column>


          <el-table-column prop="modifier" label="修改人" min-width="120">
          </el-table-column>

          <el-table-column prop="modifyDate" label="修改时间" min-width="160" :formatter="timeFormat">
          </el-table-column>


          <el-table-column label="操作" width="300">
            <template slot-scope="scope">
              <a size="14px" v-if="buttons.HRFM_E" @click="handleEdit(scope.$index, scope.row) "
                 class="edit_and_add">编辑</a>
              <a size="14px" v-if="buttons.HRFM_D" @click="checkFamilyDelete(scope.$index, scope.row) "
                 class="delete_and_split">删除</a>
              <a size="14px" v-if="buttons.HRFM_Split" @click="handlemamberSplit(scope.$index, scope.row) "
                 class="delete_and_split">拆分档案</a>
              <a size="14px" v-if="buttons.HRFM_Add_Member" @click="handlemamberAdd(scope.$index, scope.row) "
                 class="edit_and_add">添加成员</a>
              <!--
          <a size="14px" @click="handleAddchengyuan(scope.$index, scope.row) " style="color:#4DB2EE;margin-right:20px;">家庭详情</a>
           -->
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-row>

        <el-pagination layout="prev, pager, next" current-page.sync="page" @current-change="handleCurrentChange"
                       :page-size="20" :total="total"
                       style="float:right;">
        </el-pagination>
      </el-row>
      <el-row>

        <el-col :span="4" v-for="item in usersmember" :key="item.id">
          <el-card shadow="hover" style="margin-left: 20px;">
            <div style="padding: 1px; height: 120px;width: 210px">
              <div class="text item">
                <span>姓名：{{item.name}} ({{item.sex}})</span>
              </div>
              <div class="text item">
                <span>婚姻：{{item.maritalStatus}}</span>
              </div>
              <div class="text item">
                <span>生日：{{item.birth | dateFormat}}</span>
              </div>
              <div class="text item">
                <span>关系：{{item.category}}</span>
              </div>
              <div class="bottom clearfix">
                <div style="padding: 1px; text-align: center;">
                  <el-button-group>
                    <el-button size="mini" round @click="handlemamberDel(item.id)"><i class="el-icon-minus"></i>
                    </el-button>
                    <el-button size="mini" round @click="handlemamberEdit(item)"><i class="el-icon-setting"></i>
                    </el-button>
                  </el-button-group>
                </div>
              </div>
            </div>
          </el-card>
        </el-col>


      </el-row>
    </el-col>
    <!--点击高级查询按钮跳出的小页面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
      <el-form :model="editForm" style="margin: auto;text-align-all: center" label-width="80px" :rules="editFormRules" ref="editForm" >

        <el-row>
          <el-col>
            <el-form-item label="户主姓名"  >
              <el-input v-model="filters.headeName" style="width: 400px" placeholder="姓名"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col>
            <el-form-item label="归档医生">
              <el-input v-model="filters.responseDoctor" style="width: 400px" placeholder="归档医生"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="建档时间">
          <el-col :span="5">
            <el-date-picker type="date" placeholder="选择日期" v-model="filters.fbTime_s"
                            style="width: 100%;"></el-date-picker>
          </el-col>
          <el-col class="line" :span="1" align="center">-</el-col>
          <el-col :span="5">
            <el-date-picker type="date" placeholder="选择日期" v-model="filters.fbTime_e"
                            style="width: 100%;"></el-date-picker>
          </el-col>
        </el-form-item>
      </el-form>


      <div slot="footer" class="dialog-footer">
        <el-button type="primary" v-on:click="search" class="searchbtn"
                   icon="el-icon-search">查询
        </el-button>
      </div>
    </el-dialog>

    <!--编辑家庭基本情况界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <div id="print">
        <el-form :model="editForm" label-width="100px" :rules="editFormRules" ref="editForm">

          <EasyScrollbar>
            <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
              <el-tabs v-model="activeName">

                <!----基本情况小页面--->
                <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" class="family_table">
                  <tbody>
                  <!--<tr>-->
                    <!--<td class="family_table_twoRowcss"><span class="red">*</span>户主名称</td>-->
                    <!--<td colspan="4" style="word-wrap:break-word;text-align:center;padding-right: 0px;"  class="pad10">-->
                      <!--<el-form-item label="" prop="headeName" width label-width="0px">-->
                        <!--<el-input style="width: 100%" placeholder="请输入户主姓名" v-model="editForm.headeName" clearable auto-complete="off"  ></el-input>-->
                      <!--</el-form-item>-->
                    <!--</td>-->

                    <!--<td class="family_table_twoRowcss"><span class="red">*</span>责任医生</td>-->
                    <!--<td colspan="4" style="text-align:center;padding-right: 0px;"  class="pad10">-->
                      <!--<el-form-item label="" prop="responseDoctor"label-width="0px">-->
                        <!--<el-input style="width: 100%;" placeholder="请输入责任医生姓名" v-model="editForm.responseDoctor" clearable auto-complete="off"  ></el-input>-->
                      <!--</el-form-item>-->
                    <!--</td>-->
                    <!--<td colspan="2" style="margin-left: 0px;text-align: left;" >-->
                    <!--<el-form-item label-width="0px"  style="padding-left: 25%;">-->
                      <!--<el-button type="danger" @click="rdoctor()" disabled style="text-align:right;height: 40px;background-color:#4db2ee;border-color:transparent" >查找医生</el-button>-->
                    <!--</el-form-item>-->
                    <!--</td>-->
                  <!--</tr>-->
                  <tr>
                    <td class="family_table_twoRowcss"><span class="red">*</span>户主名称</td>
                    <td colspan="3" style="word-wrap:break-word;text-align:center;padding-right: 0px;"  class="pad10">
                      <el-form-item label="" prop="headeName" width label-width="0px">
                        <el-input style="width: 100%" placeholder="请输入户主姓名" v-model="editForm.headeName" clearable auto-complete="off"  ></el-input>
                      </el-form-item>
                    </td>

                    <td class="family_table_twoRowcss"><span class="red">*</span>责任医生</td>
                    <td colspan="3" style="text-align:center;padding-right: 0px;"  class="pad10">
                      <el-form-item label="" prop="responseDoctor"label-width="0px">
                        <el-input style="width: 100%;" placeholder="请输入责任医生姓名" v-model="editForm.responseDoctor" clearable auto-complete="off"  ></el-input>
                      </el-form-item>
                    </td>
                  </tr>


                  <tr>
                    <td class="family_table_oneRowcss">归属社区</td>
                    <td colspan="7" style="text-align:center;padding-right: 0px;"  class="pad10">
                      <el-form-item label=""label-width="0px">
                        <el-cascader :options="options" style="width: 100%" v-model="belongCommunity"
                                     placeholder="请选择" change-on-select></el-cascader>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td class="family_table_oneRowcss">居委会</td>
                    <td colspan="7" style="text-align:center;padding-bottom: 5px;padding-top: 5px;padding-right: 0px;"  class="pad10">
                      <el-form-item label=""label-width="0px">
                        <el-input style="width: 100%" type="textarea" v-model="editForm.neighborhoodCommittee" clearable  ></el-input>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td class="family_table_oneRowcss">家庭地址</td>
                    <td colspan="7" style="text-align:center;padding-bottom: 5px;padding-top: 5px;padding-right: 0px;"  class="pad10">
                      <el-form-item label=""label-width="0px">
                        <el-input style="width: 100%" type="textarea" v-model="editForm.localPlace" clearable  ></el-input>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td class="family_table_oneRowcss">邮政编码</td>
                    <td colspan="7" style="text-align:center;padding-bottom: 5px;padding-top: 5px;padding-right: 0px;"  class="pad10">
                      <el-form-item label=""label-width="0px">
                        <el-input type="textarea" v-model="editForm.zipCode" clearable  style="width: 100%"></el-input>
                      </el-form-item>
                    </td>
                  </tr>



                  <tr>
                    <td class="family_table_oneRowcss">家庭人口</td>
                    <td colspan="1" style="text-align:center;min-width:100px;padding-right: 0px;"  class="pad10" >
                      <el-form-item label="" label-width="0px" prop="familyNum" >
                        <el-input v-model="editForm.familyNum" style="width: 100%">
                          <template slot="append">人</template>
                        </el-input>
                      </el-form-item>
                    </td>

                    <td class="family_table_oneRowcss">现居人口</td>
                    <td colspan="1" style="text-align:center;min-width:100px;padding-right: 0px;" class="pad10" >
                      <el-form-item label="" label-width="0px"  prop="livePeople" >
                        <el-input v-model="editForm.livePeople" style="width: 100%">
                          <template slot="append">人</template>
                        </el-input>
                      </el-form-item>
                    </td>


                    <td class="family_table_oneRowcss">居住面积</td>
                    <td colspan="1" style="text-align:center;min-width:100px;padding-right: 0px;"  class="pad10">
                      <el-form-item label="" label-width="0px" prop="area"  >
                        <el-input v-model="editForm.area" style="width: 100%">
                          <template slot="append">㎡</template>
                        </el-input>
                      </el-form-item>
                    </td>
                  </tr>


                  <tr>
                    <td class="family_table_oneRowcss">家庭类型</td>
                    <td colspan="7"  style="text-align:left;padding-left: 20px;"   class="pad20">
                      <el-form-item label="">
                        <el-radio-group v-model="editForm.familyType"  style="width: 100%">
                          <el-radio label=空巢家庭>空巢家庭</el-radio>
                          <el-radio label=孤老家庭>孤老家庭</el-radio>
                          <el-radio label=单亲家庭>单亲家庭</el-radio>
                          <el-radio label=其他>其他</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td class="family_table_oneRowcss">饮水类型</td>
                    <td colspan="7" style="text-align:left;padding-left: 20px;"   class="pad20">
                      <el-form-item label="">
                        <el-radio-group v-model="editForm.drinkWater"  style="width: 100%">
                          <el-radio label=自来水>自来水</el-radio>
                          <el-radio label=经净化的水>经净化的水</el-radio>
                          <el-radio label=机井自来水>机井自来水</el-radio>
                          <el-radio label=浅井水>浅井水</el-radio>
                          <el-radio label=河塘水>河塘水</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td class="family_table_oneRowcss">厨房排风</td>
                    <td colspan="7" style="text-align:left;padding-left: 20px;"   class="pad20">
                      <el-form-item label="">
                        <el-radio-group v-model="editForm.kitchenExhaust">
                          <el-radio label=无>无</el-radio>
                          <el-radio label=抽油烟机>抽油烟机</el-radio>
                          <el-radio label=换气扇>换气扇</el-radio>
                          <el-radio label=烟囱>烟囱</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td class="family_table_oneRowcss">燃料类型</td>
                    <td colspan="7" style="text-align:left;padding-left: 20px;"   class="pad20">
                      <el-form-item label="">
                        <el-radio-group v-model="editForm.fuelType">
                          <el-radio label=液化气>液化气</el-radio>
                          <el-radio label=煤>煤</el-radio>
                          <el-radio label=天然气>天然气</el-radio>
                          <el-radio label=沼气>沼气</el-radio>
                          <el-radio label=柴火>柴火</el-radio>
                          <el-radio label=其他>其他</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td class="family_table_oneRowcss">厕所</td>
                    <td colspan="7" style="text-align:left;padding-left: 20px;"  class="pad20">
                      <el-form-item label="">
                        <el-radio-group v-model="editForm.toilet">
                          <el-radio label=楼房卫生厕>楼房卫生厕</el-radio>
                          <el-radio label=公共水冲厕>公共水冲厕</el-radio>
                          <el-radio label=公共旱厕>公共旱厕</el-radio>
                          <el-radio label=私用无害旱厕>私用无害旱厕</el-radio>
                          <el-radio label=简易私厕>简易私厕</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td class="family_table_twoRowcss">建档日期</td>
                    <td colspan="3" style="text-align:center;padding-right: 0px;"  class="pad10">
                      <el-form-item label="" label-width="0px" >
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.fbTime" style="width: 100%"></el-date-picker>
                      </el-form-item>
                    </td>

                    <td class="family_table_twoRowcss">建档人</td>
                    <td colspan="3" style="text-align:center;padding-right: 0px;"  class="pad10">
                      <el-form-item label="" label-width="0px" >
                        <el-select v-model="editForm.fbPeople" placeholder="请选择" style="width: 100%">
                          <el-option key="admin" label="管理员" value="管理员"></el-option>
                          <el-option key="superAdmin" label="超级管理员" value="超级管理员"></el-option>
                          <el-option key="dean" label="院长" value="院长"></el-option>
                        </el-select>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td class="family_table_oneRowcss">建档机构</td>
                    <td colspan="7" style="text-align:center;padding-bottom: 5px;padding-top: 5px;padding-right: 0px;"  class="pad10">
                      <el-form-item label="" label-width="0px" >
                        <el-col >
                          <el-input type="textarea" v-model="editForm.fbOrganization" style="width: 100%"></el-input>
                        </el-col>
                      </el-form-item>
                    </td>
                  </tr>

                  </tbody>
                </table>
              </el-tabs>
            </div>
          </EasyScrollbar>
        </el-form>
      </div>

      <!--编辑小页面右下角按钮-->
      <div slot="footer" class="dialog-footer">
        <el-button @click="conprint()">打印</el-button>
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
        <el-button v-if="dialogStatus=='create'||dialogStatus=='serachRdoctor'" type="primary" @click="createData">添加</el-button>
        <el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
      </div>

    </el-dialog>

    <!--查询责任医生界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="rdoctordialogFormVisible"   center @close="closeDialog">
      <el-form ref="form2" :model="form2"  label-width="80px"  style="height: 30px;">

        <el-col span="40" direction="vertical">
          <el-row>
            <el-col span="35">
              <el-form-item label="机构内码" label-width="90px">
                <el-input v-model="form2.orgId" placeholder="请输入机构内码" clearable></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-col span="30" direction="vertical">
            <el-row>
              <el-col span="18">
                <el-form-item label="医生姓名" label-width="90px">
                  <el-input v-model="form2.searchParm" placeholder="请输入医生姓名糊模查询" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col span="6">
                <el-button type="primary" v-on:click="searchRdoctor()"
                           style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">搜索责任医生
                </el-button>
              </el-col>
            </el-row>
          </el-col>
        </el-col>
      </el-form>

      <el-form :model="form3" label-width="90px"  style="width: 100%;" >
        <template>
      <el-table :data="doctordata2"   @selection-change="selsChange" ref="multipleTable" @row-click="clickRow"  highlight-current-row  height="430" style="width: 1000px ;height: 450px;" border>
              <el-table-column >
                  <el-table-column type="selection"  min-width="130">
                  </el-table-column>
                  <el-table-column type="index" label="序号" min-width="160">
                  </el-table-column>
                  <el-table-column prop="id" label="机构内号" min-width="220">
                  </el-table-column>
                  <el-table-column prop="userName" @change=""  label="医生姓名" min-width="225">
                  </el-table-column>
                  <el-table-column prop="userId" label="医生编号" min-width="220">
                  </el-table-column>
              </el-table-column>
            </el-table>
        </template>
      </el-form>
      <!--查找责任医生界面下方按钮-->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addRdoctor()"  :disabled="this.sels.length!==1">确认</el-button>
        <el-button @click.native="rdoctordialogFormVisible=false">取消</el-button>
      </div>
    </el-dialog>

    <!--家庭成员情况界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisiblechengyuan" :close-on-click-modal="false"
               center>
      <div id="print2222222">
        <el-form :model="editForm" label-width="100px" :rules="editFormRules" ref="editForm">
          <div class="container">

            <el-tabs v-model="activeName">

              <!----家庭成员情况小页面--->

              <el-tab-pane :label="`家庭成员情况`" name="first">
                <el-form-item prop="personID1">
                  <el-col span="18">
                    <el-input v-model.number="editForm.personID1" placeholder="请输入身份证号码" clearable></el-input>
                  </el-col>

                  <el-col span="6">
                    <el-form-item>
                      <el-button type="primary" v-on:click="updateDatafamily"
                                 style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">添加家庭成员
                      </el-button>
                    </el-form-item>
                  </el-col>
                </el-form-item>


                <!--家庭成员列表-->
                <el-table :data="users2" :height="tableHeight"
                          highlight-current-row @selection-change="selsChange" border style="width: 100%;">
                  <el-table-column type="index" label="序号" width="60">
                  </el-table-column>
                  <el-table-column prop="name" label="姓名" width="120">
                  </el-table-column>
                  <el-table-column prop="sex" label="性别" width="120">
                  </el-table-column>
                  <el-table-column prop="birth" label="出生日期" width="120">
                  </el-table-column>
                  <el-table-column prop="tPhoneNum" label="联系电话" width="120">
                  </el-table-column>
                  <el-table-column prop="belongComm" label="所属社区" width="120">
                  </el-table-column>
                  <el-table-column prop="idNum" label="身份证号" min-width="120">
                  </el-table-column>
                  <el-table-column prop="workPlace" label="工作单位" min-width="120">
                  </el-table-column>
                  <el-table-column label="操作" width="150">
                    <template slot-scope="scope">
                      <el-button type="danger" size="small" @click="handleDelMember(scope.$index, scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>


              <el-tab-pane :label="`档案变更情况`" name="fifth">
                <el-table :data="users" highlight-current-row @selection-change="selsChange" style="width: 100%;">
                  <el-table-column type="index" width="60" label="序号">
                  </el-table-column>
                  <el-table-column label="变更日期" width="120"> //字段模板
                  </el-table-column>

                  <el-table-column label="变更事项" width="120">
                  </el-table-column>

                  <el-table-column label="变更内容" width="120">
                  </el-table-column>
                </el-table>
              </el-tab-pane>
            </el-tabs>
          </div>
        </el-form>
      </div>

      <!--家庭详情页面右下角按钮-->
      <div slot="footer" class="dialog-footer">
        <el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button @click.native="dialogFormVisiblechengyuan=false">取消</el-button>
      </div>
    </el-dialog>

    <!--新增家庭成员-->
    <el-dialog title="添加家庭成员" :visible.sync="dialogFormadd" :close-on-click-modal="false" center>
      <el-form ref="form" :model="form" label-width="80px" style="height: 300px;">
        <el-col span="6" direction="vertical">
          <div style="height: 300px;">
            <el-steps direction="vertical" :active="active">
              <el-step title="查询人员档案信息"></el-step>
              <el-step title="确认信息点击添加"></el-step>
              <el-step title="完成家庭成员添加"></el-step>
            </el-steps>
          </div>
        </el-col>
        <el-col span="18" direction="vertical">
          <el-row>
            <el-col span="18">
              <el-form-item label="身份证号码" label-width="90px">
                <el-input  v-model="form.searchId" placeholder="请输入18位或者15位身份证号码"  minlength="15" clearable :disabled="this.active ===2" ></el-input>
              </el-form-item>
            </el-col>
            <el-col span="6">
              <el-button type="primary" v-on:click="searchPerson" :disabled="this.form.searchId.length <15"
                         style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">搜索
              </el-button>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="姓名" prop="name" label-width="90px" disabled>
                <el-input v-model="form.name" auto-complete="off" disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="性别" disabled>
                <el-radio-group v-model="form.sex" disabled>
                  <table>
                    <tr>
                      <td>
                        <el-radio label="男">男</el-radio>
                      </td>
                      <td>
                        <el-radio label="女">女</el-radio>
                      </td>
                      <td>
                        <el-radio label="未知性别">未知性别</el-radio>
                      </td>
                    </tr>
                  </table>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="卡号" label-width="90px">
                <el-input v-model="form.cardNumber" auto-complete="off" disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col span="12">
              <el-form-item label="与户主关系" label-width="90px">
                <el-select v-model="form.region" placeholder="请选择与户主关系">
                  <el-option label="户主" value="户主"></el-option>
                  <el-option label="子" value="子"></el-option>
                  <el-option label="女" value="女"></el-option>
                  <el-option label="孙" value="孙"></el-option>
                  <el-option label="媳" value="媳"></el-option>
                  <el-option label="妻" value="妻"></el-option>
                  <el-option label="父" value="父"></el-option>
                  <el-option label="母" value="母"></el-option>
                  <el-option label="哥" value="哥"></el-option>
                  <el-option label="弟" value="弟"></el-option>
                  <el-option label="姐" value="姐"></el-option>
                  <el-option label="妹" value="妹"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item colspan="5" label="主要健康问题"  label-width="90px" >
                <el-input type="textarea" v-model="form.majorHealthIssues1" auto-complete="off" ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="发生时间"label-width="90px" >
                <el-date-picker v-model="form.occurrenceDate1" auto-complete="off" ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
        </el-col>
      </el-form>
      <!--家庭详情页面右下角按钮-->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="check">添加</el-button>
        <el-button @click.native="dialogFormadd=false">取消</el-button>
      </div>
    </el-dialog>


    <!--编辑家庭成员-->
    <el-dialog title="家庭成员" :visible.sync="dialogFormedit" :close-on-click-modal="false" center>
      <el-form ref="form" :model="form" label-width="80px" style="height: 300px;">
        <table  border="1"  cellspacing="0" bordercolor="#dddddd" style="padding-left:0px;padding-right:0px;margin-left:0px;margin-right:0px;border-collapse:collapse;width: 95%;">
          <tbody>
            <tr>
              <td style="width:200px;word-wrap:break-word;text-align:center;">身份证号码</td>
              <td colspan="5" style="word-wrap:break-word;text-align:center;padding-right: 0px;"  class="pad10">
              <el-form-item label="" label-width="0px">
                <el-input v-model="form.searchId" style="width: 100%" placeholder="身份证号码" clearable disabled></el-input>
              </el-form-item>
              </td>
            </tr>
            <tr>
              <td style="width:200px;word-wrap:break-word;text-align:center;">姓名</td>
              <td colspan="1" style="text-align:center;padding-right: 0px;"  class="pad10">
              <el-form-item label="" prop="name" label-width="0" disabled>
                <el-input v-model="form.name" style="width: 100%;" auto-complete="off" disabled></el-input>
              </el-form-item>
              </td>
              <td style="width:130px;word-wrap:break-word;text-align:center;"><span class="red">*</span>性别</td>
              <td colspan="1" style="text-align:center;padding-right: 0px"  class="pad10">
              <el-form-item label="" disabled label-width="0px">
                <el-radio-group v-model="form.sex" disabled>
                  <table>
                    <tr>
                      <td>
                        <el-radio label="男">男</el-radio>
                      </td>
                      <td>
                        <el-radio label="女">女</el-radio>
                      </td>
                      <td>
                        <el-radio label="未知性别">未知性别</el-radio>
                      </td>
                    </tr>
                  </table>
                </el-radio-group>
              </el-form-item>
              </td>
            </tr>
            <tr>
              <td style="width:200px;word-wrap:break-word;text-align:center;">卡号</td>
              <td colspan="1" style="text-align:center;padding-right: 0px;"  class="pad10">
              <el-form-item label="" label-width="0px">
                <el-input v-model="form.cardNumber" style="width: 100%;" auto-complete="off" disabled></el-input>
              </el-form-item>
              </td>
              <td style="text-align:center;padding-right: 0px;"  class="pad10">与户主关系</td>
              <td colspan="1" width="35%" style="text-align:center;padding-right: 10px;margin-left: 10px;padding-right: 0px"  class="pad10">
              <el-form-item label="" label-width="0px">
                <el-select v-model="form.region" style="width: 100%" placeholder="请选择与户主关系">
                  <el-option label="户主" value="户主"></el-option>
                  <el-option label="子" value="子"></el-option>
                  <el-option label="孙" value="孙"></el-option>
                  <el-option label="媳" value="媳"></el-option>
                  <el-option label="妻" value="妻"></el-option>
                  <el-option label="父" value="父"></el-option>
                  <el-option label="母" value="母"></el-option>
                  <el-option label="哥" value="哥"></el-option>
                  <el-option label="弟" value="弟"></el-option>
                  <el-option label="姐" value="姐"></el-option>
                  <el-option label="妹" value="妹"></el-option>
                </el-select>
              </el-form-item>
              </td>
            </tr>
          <tr>
            <td style="word-wrap:break-word;text-align:center;height: 80px">主要健康问题</td>
            <td  rowspan="8" style="text-align:center;padding-bottom: 5px;padding-top: 5px;padding-right: 0px;"  class="pad10">
              <el-form-item label="" label-width="0px" >
                <el-input type="textarea"  v-model="form.majorHealthIssues1" style="width: 100%" auto-complete="off" ></el-input>
              </el-form-item>
            </td>
            <td style="text-align:center;padding-right: 0px;height: 80px"  class="pad10">发生时间</td>
            <td colspan="1" rowspan="8" style="text-align:center;padding-bottom: 5px;padding-top: 5px;margin-left: 10px;padding-right: 0px;"   class="pad10">
              <el-form-item label=""label-width="0px" >
                <el-date-picker style="width: 100%;height: 100%;" v-model="form.occurrenceDate1" auto-complete="off" ></el-date-picker>
              </el-form-item>
            </td>
          </tr>
          </tbody>
        </table>
      </el-form>
      <!--家庭详情页面右下角按钮-->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editpersonData">修改</el-button>
        <el-button @click.native="dialogFormedit=false">取消</el-button>
      </div>
    </el-dialog>

    <!--拆分家庭成员-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormsplit" :close-on-click-modal="false" center close-on-press-escape title="家庭档案拆分" >
      <el-form :model="value5"  label-width="200px" ref="editForm" style="margin-left: 20%" >
        <el-row  >
          <el-col >
            <el-transfer
              :props="{key: 'id',label: 'name'}"
              :titles="['当前家庭', '拆分家庭']"
              v-model="value5"
              :data="data5">
              <span>{option.label}</span>
            </el-transfer>
          </el-col>
        </el-row>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="splitfamily">拆分</el-button>
        <el-button @click.native="dialogFormsplit=false">取消</el-button>
      </div>

    </el-dialog>



  </section>
</template>

<script>
  import util from '@/utils/table.js'
  import { getToken } from '@/utils/auth'
  import {
    getList as getSearchRdoctor
  } from '@/api/searchRdoctor'
  import {
    getList,
    removeData,
    batchRemoveData,
    updateData,
    addData,
    updateHeadname
  } from '@/api/basicFamilyInfor'
  import {
    getUserListPage as getUserListMemberPage,
    addUser as addMember,
    editUser as editMember,
    managerMember,
    removeUser as removeMember,
    splitMember
  } from '@/api/basicFamilyInforMember'
  import {
    getList as getSearchMemberPage
  } from '@/api/Perinfor'
  import city from '@/api/city.js'
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
    data: function(name) {
      return {
        buttons: {
          HRFM_C: false,
          HRFM_E: false,
          HRFM_D: false,
          HRFM_Merge: false,
          HRFM_Add_Member: false,
          HRFM_Split: false
        },
        data5: [],
        value5: [],
        count:0,
        filterMethod(query, item) {
          return item.pinyin.indexOf(query) > -1
        },
        splitmember: [],
        currentDate: new Date(),
        tablebodyHeight: window.innerHeight - 320,
        active: 0,
        filterText: '',
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        edit: true,
        options: city.city(),
        dialogStatus: '',
        tableHeight: window.innerHeight / 2 - 120,
        activeName: 'first', // 最先显示第几个页面
        textMap: {
          update: '家庭健康档案编辑',
          create: '家庭健康档案添加',
          search: '家庭健康档案查询',
          find: '家庭健康档案高级查找',
          detail: '家庭健康档案详情',
          serachRdoctor: '家庭档案查找责任医生'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        dialogFormVisiblechengyuan: false,
        rdoctordialogFormVisible: false,
        CurrFamilyId: '',
        dialogFormadd: false,
        dialogFormedit: false,
        dialogFormsplit: false,
        dialogFormmager: false,
        filters: {
          id: null,
          headeName: '',
          belongCommunity: '',
          responseDoctor: '',
          fbTime_s: null,
          fbTime_e: null,
          orgId: '',
          searchParm: ''
        },
        users: [],
        users2: [],
        doctordata2: [],
        usersmember: [],
        name: [],
        multipleTable: [],
        belongCommunity: [],
        total: 1000,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          headeName: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: ' ' }
          ],
          responseDoctor: [
            { required: true, message: ' ', trigger: 'blur' },
            { min: 2, max: 5, message: ' ', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: ' ' }
          ],
          familyNum: [{ pattern: /^[1-9]\d*|0$/, message: ' ' }],
          livePeople: [{ pattern: /^[1-9]\d*|0$/, message: ' ' }],
          area: [{ pattern: /^[1-9]\d*|0$/, message: ' ' }]
        },
        TopbarRules:
          {
            id: [
              { type: 'number', message: ' ', trigger: 'blur' }
            ]
          },
        form: {
          id: '',
          searchId: '',
          cardNumber: '',
          name: '',
          sex: '',
          region: '',
          idNum: '',
          majorHealthIssues1: '',
          occurrenceDate1: null
        },
        form2: {
          orgId: '',
          searchParm: '',
          size: '999',
          page: '1'
        },
        form3: {
          id: '',
          userName: '',
          userId: ''
        },
        // 编辑界面数据
        editForm: {
          headeName: '',
          responseDoctor: '',
          belongCommunity: '',
          localPlace: '',
          neighborhoodCommittee: '',
          zipCode: '',
          familyNum: '',
          livePeople: '',
          area: '',
          familyType: '',
          drinkWater: '',
          kitchenExhaust: '',
          fuelType: '',
          toilet: '',
          fbTime: '',
          fbPeople: '',
          fbOrganization: '',
          creater: '',
          createDate: null,
          modifier: '',
          modifyDate: null
        },
        editForm0: {
          headname: ''
        },
        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          headeName: [{ required: true, message: '请输入户主姓名', trigger: 'blur' }],
          responseDoctor: [{ required: true, message: '请填写责任医生姓名', trigger: 'blur' }],
          familyNum: [{ pattern: /^[1-9]\d*|0$/, message: '只能为大于0的整数' }],
          livePeople: [{ pattern: /^[1-9]\d*|0$/, message: '只能为大于0的整数' }],
          area: [{ pattern: /^[1-9]\d*|0$/, message: '只能为大于0的整数' }]
        }
      }
    },
    watch: {
      filterText(val) {
        this.$refs.tree2.filter(val)
      }
    },
    methods: {
      clickRow(row, event, column) {
        this.$refs.multipleTable.toggleRowSelection(row)
      },
      handleNodeClick(data) {
        this.filters.belongCommunity = data['value']
        this.page = 1
        this.getUsers()
      },
      filterNode(value, data) {
        if (!value) return true
        return data.label.indexOf(value) !== -1
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
      // 双击列表某条记录跳出详情页
      handleRowChange(row, event) {
        this.dialogStatus = 'detail'
        this.dialogFormVisible = true
        this.belongCommunity = []
        this.editForm = Object.assign({}, row)
        this.belongCommunity = this.editForm.belongCommunity.split('/')
        this.edit = false
      },
      // 单击选中列表显示家庭成员
      handRowClick(row, event) {
        if (row.id !== '') {
          this.CurrFamilyId = row.id
          this.getmember(this.CurrFamilyId)
        }
      },
      // 获得该家庭id的家庭成员
      getmember(data) {
        const para = {
          page: 1,
          size: 80,
          filters: { familyid: data, idNum: '', category: '' }
        }
        getUserListMemberPage(para).then(res => {
          this.usersmember = res.detailModelList
        })
      },
      // 确认拆分家庭
      splitfamily() {
        if (this.data5.length !== 0) {
          if (this.value5.length !== 0) {
            this.$confirm('拆分选中的档案吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            })
              .then(() => {
                const para = this.value5
                splitMember(para).then(res => {
                  this.$message({
                    message: '拆分成功，请检查之后，手动更新成员关系',
                    type: 'success'
                  })
                  this.value5 = []
                  this.dialogFormsplit = false
                  this.getUsers()
                  this.getmember(this.CurrFamilyId)
                })
              })
          }
        }
      },
      // 获取到家庭成员进行拆分
      getmember2(data) {
        const para = {
          page: 1,
          size: 80,
          filters: { familyid: data, idNum: '', category: '' }
        }
        getUserListMemberPage(para).then(res => {
          this.data5 = res.detailModelList
          for (const itm of this.data5) {
            itm.name.replace(/^[\s\n\t]+/g, ' ')
            for (let i = 0; i < 15 - itm.name.length; i++) {
              itm.name = itm.name + '\xa0'
            }
          }
        })
      },
      // 选中家庭添加家庭成员
      handlemamberAdd(row, event) {
        if (this.para !== '') {
          this.dialogFormadd = true
          this.active = 0
          this.form.cardNumber = ''
          this.form.searchId = ''
          this.form.name = ''
          this.form.sex = ''
          this.form.region = ''
          this.form.occurrenceDate1 = null
          this.form.majorHealthIssues1 = ''
        } else {
          this.$message.error('请先选择家庭档案')
        }
      },
      // 成员删除
      handlemamberDel(id, event) {
        this.$confirm('确认删除该家庭成员吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(() => {
            const para = id
            removeMember(para).then(res => {
              this.$message({
                message: '删除成功',
                type: 'success'
              })
              this.getmember(this.CurrFamilyId)
              this.getUsers()
            })
          })
          .catch(() => {
          })
      },
      // 修改
      handlemamberEdit(row) {
        this.form.id = row.id
        this.form.sex = row.sex
        this.form.name = row.name
        this.form.searchId = row.peopleId
        this.form.cardNumber = row.cardNumber
        this.form.region = row.category
        this.form.occurrenceDate1 = row.occurrenceDate
        this.form.majorHealthIssues1 = row.majorHealthIssues
        this.dialogFormedit = true
      },
      // 合并档案
      handlemamberMager(row, event) {
        var ids = this.sels.map(item => item.id).toString()
        this.$confirm('合并选中档案吗？', '提示', {
          type: 'warning'
        })
          .then(() => {
            const para = { ids: ids }
            managerMember(para).then(res => {
              this.$message({
                message: '合并成功，请检查之后，手动删除空白家庭并更新关系',
                type: 'success'
              })
              this.getUsers()
            })
          })
          .catch(() => {

          })
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
        const style = "<style type='text/css'>table.gridtable {font-family: verdana,arial,sans-serif;font-size:24px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;}table.gridtable th {border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;}table.gridtable td {border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;}</style>"
        var str = style
        str = str +
          '<div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
          '\t<div id="topContent">\n' +
          '\t\t<h2 style="width:794px;text-align:center">家庭健康档案基本信息表</h2>  \n' +
          '\t\t<span style="margin-left:430px;">编号:{{id}}</span> \n' +
          '\t</div>\n' +
          '\t<table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;height=100px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  >\n' +
          '\t<tbody>\n' +
          '\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="width: 15%;text-align:center">户主姓名</td>\n' +
          '\t\t\t<td style="width: 35%;text-align:center">{{headeName}}</td>\n' +
          '\t\t\t<td colspan="2" style="width: 15%;text-align:center">责任医生</td>\n' +
          '\t\t\t<td style="width: 35%;text-align:center">{{responseDoctor}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="width: 60%;text-align:center">归属社区</td>\n' +
          '\t\t\t<td colspan="4" style="width: 35%;text-align:center">{{belongCommunity}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="width: 60%;text-align:center">家庭地址</td>\n' +
          '\t\t\t<td colspan="4" style="width: 35%;text-align:center">{{localPlace}}</td>\n' +
          '\t\t</tr>\n' +
          '\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="width: 60%;text-align:center">居委会</td>\n' +
          '\t\t\t<td colspan="4" style="width: 35%;text-align:center">{{neighborhoodCommittee}}</td>\n' +
          '\t\t</tr>\n' +
          '\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="width: 60%;text-align:center">邮政编码</td>\n' +
          '\t\t\t<td colspan="4" style="width: 35%;text-align:center">{{zipCode}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\n' +
          '\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="width: 15%;text-align:center">家庭人口数</td>\n' +
          '\t\t\t<td style="width: 35%;text-align:center">{{familyNum}}</td>\n' +
          '\t\t\t<td colspan="2" style="width: 15%;text-align:center">现居人口数</td>\n' +
          '\t\t\t<td style="width: 35%;text-align:center">{{livePeople}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="width: 60%;text-align:center">居住面积</td>\n' +
          '\t\t\t<td colspan="4" style="width: 35%;text-align:center">{{area}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="width: 15%;text-align:center">家庭类型</td>\n' +
          '\t\t\t<td style="width: 35%;text-align:center">{{familyType}}</td>\n' +
          '\t\t\t<td colspan="2" style="width: 15%;text-align:center">饮水类型</td>\n' +
          '\t\t\t<td style="width: 35%;text-align:center">{{drinkWater}}</td>\n' +
          '\t\t</tr>\n' +
          '\n' +
          '\t<tr>\n' +
          '\t\t\t<td colspan="2" style="width: 15%;text-align:center">厨房排风类型</td>\n' +
          '\t\t\t<td style="width: 35%;text-align:center">{{kitchenExhaust}}</td>\n' +
          '\t\t\t<td colspan="2" style="width: 15%;text-align:center">燃料类型</td>\n' +
          '\t\t\t<td style="width: 35%;text-align:center">{{fuelType}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="width: 60%;text-align:center">厕所类型</td>\n' +
          '\t\t\t<td colspan="4" style="width: 35%;text-align:center">{{toilet}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="width: 15%;text-align:center">建档日期</td>\n' +
          '\t\t\t<td style="width: 35%;text-align:center">{{fbTime}}</td>\n' +
          '\t\t\t<td colspan="2" style="width: 15%;text-align:center">建档人</td>\n' +
          '\t\t\t<td style="width: 35%;text-align:center">{{fbPeople}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t\n' +
          '\t\t</tbody>\n' +
          '\t</table>\n' +
          '</div>'
        str = str.replace(/{{headeName}}/, this.editForm.headeName ? this.editForm.headeName : '')
        str = str.replace(/{{id}}/, this.editForm.id ? this.editForm.id : '')
        str = str.replace(/{{responseDoctor}}/, this.editForm.responseDoctor ? this.editForm.responseDoctor : '')
        str = str.replace(/{{belongCommunity}}/, this.editForm.belongCommunity ? this.editForm.belongCommunity : '')
        str = str.replace(/{{localPlace}}/, this.editForm.localPlace ? this.editForm.localPlace : '')
        str = str.replace(/{{neighborhoodCommittee}}/, this.editForm.neighborhoodCommittee ? this.editForm.neighborhoodCommittee : '')
        str = str.replace(/{{zipCode}}/, this.editForm.zipCode ? this.editForm.zipCode : '')
        str = str.replace(/{{familyNum}}/, this.editForm.familyNum ? this.editForm.familyNum : '')
        str = str.replace(/{{livePeople}}/, this.editForm.livePeople ? this.editForm.livePeople : '')
        str = str.replace(/{{area}}/, this.editForm.area ? this.editForm.area : '')
        str = str.replace(/{{familyType}}/, this.editForm.familyType ? this.editForm.familyType : '')
        str = str.replace(/{{drinkWater}}/, this.editForm.drinkWater ? this.editForm.drinkWater : '')
        str = str.replace(/{{kitchenExhaust}}/, this.editForm.kitchenExhaust ? this.editForm.kitchenExhaust : '')
        str = str.replace(/{{fuelType}}/, this.editForm.fuelType ? this.editForm.fuelType : '')
        str = str.replace(/{{toilet}}/, this.editForm.toilet ? this.editForm.toilet : '')
        str = str.replace(/{{fbTime}}/, this.editForm.fbTime ? this.editForm.fbTime : '')
        str = str.replace(/{{fbPeople}}/, this.editForm.fbPeople ? this.editForm.fbPeople : '')
        str = str.replace(/{{fbOrganization}}/, this.editForm.fbOrganization ? this.editForm.fbOrganization : '')
        myWindow.document.write(str)
        // myWindow.document.write(str)
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
      // 查询
      search() {
        this.page = 1
        this.getUsers()
        this.usersmember = ''
        this.dialogFormVisible2 = false
      },
      // 获取用户列表
      getUsers() {
        this.filters.fbTime_s =
          !this.filters.fbTime_s || this.filters.fbTime_s === ''
            ? null
            : util.formatDate.format(new Date(this.filters.fbTime_s), 'yyyy-MM-dd')

        this.filters.fbTime_e =
          !this.filters.fbTime_e || this.filters.fbTime_e === ''
            ? null
            : util.formatDate.format(new Date(this.filters.fbTime_e), 'yyyy-MM-dd')

        const para = {
          page: this.page,
          size: 20,
          filters: {
            belongCommunity: this.filters.belongCommunity,
            id: this.filters.id,
            headeName: this.filters.headeName,
            responseDoctor: this.filters.responseDoctor,
            fbTime_s: this.filters.fbTime_s,
            fbTime_e: this.filters.fbTime_e
          }
        }
        // 获取家庭档案列表信息
        getList(para).then(res => {
          this.total = res.detailModelList.length
          this.users = res.detailModelList
        })
        this.dialogFormVisible2 = false
        this.dialogFormVisiblechengyuan = false
        this.filters = {
          id: this.filters.id,
          headeName: '',
          responseDoctor: '',
          fbTime_s: '',
          fbTime_e: ''
        }
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.belongCommunity = []
        this.editForm = Object.assign({}, row)
        this.belongCommunity = this.editForm.belongCommunity.split('/')
        this.edit = true
      },
      // 点击家庭档案拆分跳出弹窗页面
      handlemamberSplit(index, row) {
        this.dialogFormsplit = true
        this.CurrFamilyId = row.id
        this.getmember2(this.CurrFamilyId)
      },
      // 高级查找界面
      findbtn() {
        this.dialogStatus = 'find'
        this.dialogFormVisible2 = true
        this.filters = {
          id: null,
          headeName: '',
          responseDoctor: '',
          fbTime_s: '',
          fbTime_e: ''
        }
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.belongCommunity = []
        this.editForm = {
          id: null,
          headeName: '',
          responseDoctor: '',
          belongCommunity: '',
          localPlace: '',
          neighborhoodCommittee: '',
          zipCode: '',
          familyNum: '',
          livePeople: '',
          area: '',
          familyType: '',
          drinkWater: '',
          kitchenExhaust: '',
          fuelType: '',
          toilet: '',
          fbTime: new Date(),
          fbPeople: this.$store.getters.name,
          fbOrganization: '',
          creater: this.$store.getters.name,
          createDate: null,
          modifier: '',
          modifyDate: null,
          name: ''
        }
      },
      // 显示责任医生界面
      rdoctor() {
        this.page = 1
        this.dialogStatus = 'serachRdoctor'
        this.rdoctordialogFormVisible = true
        this.$refs.multipleTable.clearSelection()
        this.form2 = {
          orgId: '',
          searchParm: '',
          size: '999',
          page: '1'
        }
        this.doctordata2 = []
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

                if (para.fbTime != null) {
                  para.fbTime =
                    !para.fbTime || para.fbTime === ''
                      ? null
                      : util.formatDate.format(new Date(para.fbTime), 'yyyy-MM-dd')
                }
                para.belongCommunity = ''
                for (const itm of this.belongCommunity) {
                  para.belongCommunity += itm
                  para.belongCommunity += '/'
                }
                para.belongCommunity = para.belongCommunity.substr(0, para.belongCommunity.length - 1)

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
      closeDialog() {
        this.sels.length = 0
      },
      //
      check() {
        if (this.active === 2) {
          if (this.form.searchId !== '') {
            const para = {
              page: 1,
              size: 200,
              filters: { familyid: '', idNum: this.form.searchId, category: '' }
            }
            getUserListMemberPage(para).then(res => {
              if (res.detailModelList.length === 0) {
                if (this.form.region === '户主') {
                  const para = {
                    page: 1,
                    size: 200,
                    filters: { familyid: this.CurrFamilyId, idNum: '', category: this.form.region }
                  }
                  getUserListMemberPage(para).then(res => {
                    if (res.detailModelList.length === 0) {
                      const para11 = {
                        page: 1,
                        size: 200,
                        filters: { name: '', peopleId: this.form.searchId, cardNumber: '' }
                      }
                      // 给家庭档案更新户主姓名
                      getSearchMemberPage(para11).then(res => {
                        const user = res.detailModelList
                        this.newHeadname = user[0].name
                        this.id = this.CurrFamilyId
                        const para22 = {
                          newHeadname: this.newHeadname,
                          id: this.id
                        }
                        updateHeadname(para22).then(res => {
                          this.getUsers()
                        })
                      })
                      this.addpersonData()
                    } else {
                      this.$message.error('该家庭档案已经存在户主')
                    }
                  })
                } else {
                  this.addpersonData()
                }
              } else {
                this.$message.error('该成员已存在某个家庭档案中')
              }
            })
          } else {
            this.$message.error('身份证不能为空')
          }
        } else {
          this.$message.error('请先查询人员档案')
        }
      },
      addRdoctor(row, event) {
        this.editForm.responseDoctor = this.multipleTable[0].userName
        this.rdoctordialogFormVisible = false
      },
      checkFamilyDelete(index, row) {
        const para = {
          page: 1,
          size: 200,
          filters: { familyid: this.CurrFamilyId, idNum: '', category: '' }
        }
        getUserListMemberPage(para).then(res => {
          if (res.detailModelList.length === 0) {
            this.handleDel(index, row)
          } else {
            this.$message.error('不可直接删除该家庭档案，该档案中还存在成员！')
          }
        })
      },
      // 确认添加家庭成员
      addpersonData() {
        this.$refs.form.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            }).then(() => {
              const para = {
                familyid: this.CurrFamilyId,
                idNum: this.form.searchId,
                category: this.form.region,
                majorHealthIssues: this.form.majorHealthIssues1,
                occurrenceDate: this.form.occurrenceDate1
              }
              addMember(para).then(res => {
                this.$message({
                  message: '提交成功',
                  type: 'success'
                })
                this.getmember(this.CurrFamilyId)
                this.dialogFormadd = false
              })
            })
              .catch(e => {
                // 打印一下错误
                console.log(e)
              })
          }
        })
      },
      // 编辑家庭成员信息
      editpersonData() {
        this.$refs.form.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            }).then(() => {
              if (this.form.region === '户主') {
                const para = {
                  page: 1,
                  size: 200,
                  filters: { familyid: this.CurrFamilyId, idNum: '', category: this.form.region }
                }
                getUserListMemberPage(para).then(res => {
                  if (res.detailModelList.length === 0) {
                    const para1 = {
                      id: this.form.id,
                      familyid: this.CurrFamilyId,
                      idNum: this.form.searchId,
                      category: this.form.region,
                      majorHealthIssues: this.form.majorHealthIssues1,
                      occurrenceDate: this.form.occurrenceDate1
                    }
                    editMember(para1).then(res => {
                      this.$message({
                        message: '提交成功',
                        type: 'success'
                      })
                      this.getmember(this.CurrFamilyId)
                      this.dialogFormedit = false
                    })
                    const para11 = {
                      page: 1,
                      size: 200,
                      filters: { name: '', peopleId: this.form.searchId, cardNumber: '' }
                    }
                    // 给家庭档案更新户主姓名
                    getSearchMemberPage(para11).then(res => {
                      const user = res.detailModelList
                      this.newHeadname = user[0].name
                      this.id = this.CurrFamilyId
                      const para22 = {
                        newHeadname: this.newHeadname,
                        id: this.id
                      }
                      updateHeadname(para22).then(res => {
                        this.getUsers()
                      })
                    })
                  } else {
                    this.$message.error('该家庭档案已经存在户主')
                  }
                })
              } else {
                const para2 = {
                  id: this.form.id,
                  familyid: this.CurrFamilyId,
                  idNum: this.form.searchId,
                  category: this.form.region,
                  majorHealthIssues: this.form.majorHealthIssues1,
                  occurrenceDate: this.form.occurrenceDate1
                }
                editMember(para2).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.getmember(this.CurrFamilyId)
                  this.dialogFormedit = false
                })
              }
            })
              .catch(e => {
                // 打印一下错误
                console.log(e)
              })
          }
        })
      },
      // 添加成员页面中的搜索按钮：根据身份证查找到个人信息
      searchPerson() {
        if (this.form.searchId !== '') {
          const para = {
            page: 1,
            size: 200,
            filters: { familyid: '', idNum: this.form.searchId, category: '' }
          }
          getUserListMemberPage(para).then(res => {
            if (res.detailModelList.length === 0) {
              const para1 = {
                page: 1,
                size: 200,
                filters: { name: '', peopleId: this.form.searchId, cardNumber: '' }
              }
              // 查找此人是否已建档
              getSearchMemberPage(para1).then(res => {
                const user = res.detailModelList
                if (user.length !== 0) {
                  this.form.name = user[0].name
                  this.form.sex = user[0].sex
                  this.form.cardNumber = user[0].cardNumber
                  this.active = 2
                } else {
                  this.form.name = ''
                  this.form.sex = ''
                  this.form.cardNumber = ''
                  this.$message.error('未找到人员档案，请先建立个人档案')
                }
              })
            } else {
              this.$message.error('该成员已存在某个家庭档案中')
            }
          })
        } else {
          this.$message.error('请输入所要查询人员的身份证')
        }
      },
      searchRdoctor() {
        if (this.form2.searchParm !== '') {
          const para = {
            page: 1,
            size: 200,
            token: getToken(),
            filters: { orgId: this.form2.orgId, searchParm: this.form2.searchParm }
          }
          getSearchRdoctor(para).then(res => {
            this.total = res.detailModelList.length
            this.doctordata2 = res.detailModelList
          })
        } else {
          const para = {
            page: 1,
            size: 200,
            token: getToken(),
            filters: { orgId: this.form2.orgId, searchParm: '' }
          }
          getSearchRdoctor(para).then(res => {
            this.total = res.detailModelList.length
            this.doctordata2 = res.detailModelList
          })
        }
      },
      // 添加一个家庭成员
      updateDatafamily() {
        if (this.active === 2) {
          this.$refs.editForm.validate(valid => {
            if (valid) {
              this.$confirm('确认提交吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消'
              })
                .then(() => {
                  const para = Object.assign({}, this.editForm)
                  para.belongCommunity = ''
                  for (const itm of this.belongCommunity) {
                    para.belongCommunity += itm
                    para.belongCommunity += '/'
                  }
                  para.belongCommunity = para.belongCommunity.substr(0, para.belongCommunity.length - 1)
                  updateData(para).then(res => {
                    this.$message({
                      message: '提交成功',
                      type: 'success'
                    })
                    this.$refs['editForm'].resetFields()
                    this.getUsers()
                  })
                })
                .catch(e => {
                  // 打印一下错误
                  console.log(e)
                })
            }
          })
        }
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

                para.fbTime =
                  !para.fbTime || para.fbTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.fbTime), 'yyyy-MM-dd')

                para.belongCommunity = ''
                for (const itm of this.belongCommunity) {
                  para.belongCommunity += itm
                  para.belongCommunity += '/'
                }
                para.belongCommunity = para.belongCommunity.substr(0, para.belongCommunity.length - 1)

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
        const self = this
        self.multipleTable = sels
        this.sels = sels
      },
      // 删除该成员的家庭id
      handleDelMember(index, row) {
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
              this.CurrFamilyId = ''
              this.getmember(this.CurrFamilyId)
              this.getUsers()
            })
          })
          .catch(() => {
          })
      },
      // 删除该家庭档案
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
      // 批量删除
      batchRemove() {
        var ids = this.sels.map(item => item.id).toString()
        this.$confirm('合并选中档案吗？', '提示', {
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
          .catch(() => {
          })
      }
    },
    mounted() {
      this.buttons = this.getButton(this.buttons, this.$store.getters.buttons)
      this.getUsers()
    }
  }
</script>


<style>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>


<style scoped>
  .searchbtn{
    text-align:right;
    height: 40px;
    background-color:#4db2ee;
    border-color:transparent
  }
  .edit_and_add{
    color:#4DB2EE;
    margin-right:10px;
  }
  .delete_and_split{
    color:#FB4141;
    margin-right:10px;
  }
  .family_table{
    padding-left:10px;
    padding-right:0px;
    margin-left:10px;
    margin-right:0px;
    border-collapse:collapse;
    width: 95%;
  }
  .family_table_twoRowcss{
    width:100px;
    word-wrap:break-word;
    text-align:center;
  }
  .family_table_oneRowcss{
    width:100px;
    word-wrap:break-word;
    text-align:center
  }
  .el-table--fit {
    margin-top: 2px;
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
  .red{
    color:red;
  }
</style>
