<template>
  <section class="app-container">

    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <table style="width: 100%;">
          <tr >
            <td width="70"><span class="mystyle">区域</span></td>
            <td><el-cascader :options="options" v-model="filters.area"  placeholder="请选择" style="width:100%;margin-right:30px;" change-on-select></el-cascader></td>
            <td width="100" style="padding-left:30px;"><span class="mystyle">年龄</span></td>
            <td><el-input v-model="filters.age"  style="width:100%;" placeholder="请输入年龄" clearable></el-input></td>
            <td width="90" style="padding-left:30px;"><span class="mystyle">姓名</span></td>
            <td width="120"><el-input v-model="filters.name" placeholder="请输入姓名"  style="width:100%;" clearable></el-input></td>
            <td width="100" style="padding-left:30px;"><span class="mystyle">联系电话</span></td>
            <td><el-input v-model="filters.phone" placeholder="请输入联系电话" style="width:100%;" clearable></el-input></td>
          </tr>
          <tr height="16"></tr>
          <tr>
            <td><span class="mystyle">身份证号</span></td>
            <td><el-input v-model="filters.peopleId" placeholder="请输入身份证号" style="width:100%;" clearable></el-input></td>
            <td style="padding-left:30px;"><span class="mystyle">生存状态</span></td>
            <td>
              <el-select v-model="filters.liveState" placeholder="请选择" style="width: 100%;">
                <el-option key="" label="请选择" value=""></el-option>
                <el-option key="存活" label="存活" value="存活"></el-option>
                <el-option key="死亡" label="死亡" value="死亡"></el-option>
              </el-select>
            </td>
            <td style="padding-left:30px;"><span class="mystyle">民族</span></td>
            <td>
              <el-select v-model="filters.nation" placeholder="请选择" style="width:100%;">
                <el-option key="" label="请选择" value=""></el-option>
                <el-option key="汉族" label="汉族" value="汉族"></el-option>
                <el-option key="回族" label="回族" value="回族"></el-option>
                <el-option key="藏族" label="藏族" value="藏族"></el-option>
                <el-option key="维吾尔族" label="维吾尔族" value="维吾尔族"></el-option>
                <el-option key="苗族" label="苗族" value="苗族"></el-option>
                <el-option key="彝族" label="彝族" value="彝族"></el-option>
                <el-option key="壮族" label="壮族" value="壮族"></el-option>
                <el-option key="布依族" label="布依族" value="布依族"></el-option>
                <el-option key="朝鲜族" label="朝鲜族" value="朝鲜族"></el-option>
                <el-option key="满族" label="满族" value="满族"></el-option>
                <el-option key="侗族" label="侗族" value="侗族"></el-option>
                <el-option key="瑶族" label="瑶族" value="瑶族"></el-option>
                <el-option key="白族" label="白族" value="白族"></el-option>
                <el-option key="土家族" label="土家族" value="土家族"></el-option>
                <el-option key="哈尼族" label="哈尼族" value="哈尼族"></el-option>
                <el-option key="哈萨克族" label="哈萨克族" value="哈萨克族"></el-option>
                <el-option key="傣族" label="傣族" value="傣族"></el-option>
                <el-option key="傈僳族" label="傈僳族" value="傈僳族"></el-option>
                <el-option key="黎族" label="黎族" value="黎族"></el-option>
                <el-option key="佤族" label="佤族" value="佤族"></el-option>
                <el-option key="畲族" label="畲族" value="畲族"></el-option>
                <el-option key="高山族" label="高山族" value="高山族"></el-option>
                <el-option key="拉祜族" label="拉祜族" value="拉祜族"></el-option>
                <el-option key="水族" label="水族" value="水族"></el-option>
                <el-option key="东乡族" label="东乡族" value="东乡族"></el-option>
                <el-option key="纳西族" label="纳西族" value="纳西族"></el-option>
                <el-option key="景颇族" label="景颇族" value="景颇族"></el-option>
                <el-option key="柯尔克孜族" label="柯尔克孜族" value="柯尔克孜族"></el-option>
                <el-option key="土族" label="土族" value="土族"></el-option>
                <el-option key="达斡尔族" label="达斡尔族" value="达斡尔族"></el-option>
                <el-option key="仫佬族" label="仫佬族" value="仫佬族"></el-option>
                <el-option key="羌族" label="羌族" value="羌族"></el-option>
                <el-option key="布朗族" label="布朗族" value="布朗族"></el-option>
                <el-option key="撒拉族" label="撒拉族" value="撒拉族"></el-option>
                <el-option key="毛南族" label="毛南族" value="毛南族"></el-option>
                <el-option key="仡佬族" label="仡佬族" value="仡佬族"></el-option>
                <el-option key="锡伯族" label="锡伯族" value="锡伯族"></el-option>
                <el-option key="阿昌族" label="阿昌族" value="阿昌族"></el-option>
                <el-option key="普米族" label="普米族" value="普米族"></el-option>
                <el-option key="怒族" label="怒族" value="怒族"></el-option>
                <el-option key="乌孜别克族" label="乌孜别克族" value="乌孜别克族"></el-option>
                <el-option key="俄罗斯族" label="俄罗斯族" value="俄罗斯族"></el-option>
                <el-option key="鄂温克族" label="鄂温克族" value="鄂温克族"></el-option>
                <el-option key="德昂族" label="德昂族" value="德昂族"></el-option>
                <el-option key="保安族" label="保安族" value="保安族"></el-option>
                <el-option key="裕固族" label="裕固族" value="裕固族"></el-option>
                <el-option key="京族" label="京族" value="京族"></el-option>
                <el-option key="塔塔尔族" label="塔塔尔族" value="塔塔尔族"></el-option>
                <el-option key="独龙族" label="独龙族" value="独龙族"></el-option>
                <el-option key="鄂伦春族" label="鄂伦春族" value="鄂伦春族"></el-option>
                <el-option key="赫哲族" label="赫哲族" value="赫哲族"></el-option>
                <el-option key="门巴族" label="门巴族" value="门巴族"></el-option>
                <el-option key="珞巴族" label="珞巴族" value="珞巴族"></el-option>
                <el-option key="基诺族" label="基诺族" value="基诺族"></el-option>
              </el-select>
            </td>
            <td style="padding-left:30px;"><span class="mystyle">建档人</span></td>
            <td><el-input v-model="filters.documentPeople" placeholder="请输入建档人" style="width:100%;" clearable></el-input></td>
          </tr>
          <tr height="16"></tr>
          <tr>
            <td><span class="mystyle">建档开始</span></td>
            <td><el-date-picker type="date"  v-model="filters.startTime"  style="width:100%;"  placeholder="请输入日期"></el-date-picker></td>
            <td style="padding-left:30px;"><span class="mystyle">建档结束</span></td>
            <td><el-date-picker type="date"  v-model="filters.endTime" style="width:100%;"  placeholder="请输入日期"></el-date-picker></td>
            <td style="padding-left:30px;"><span class="mystyle">性别</span></td>
            <td>
              <el-select v-model="filters.sex" placeholder="请选择" style="width:100%;">
                <el-option key="" label="请选择" value=""></el-option>
                <el-option key="男" label="男" value="男"></el-option>
                <el-option key="女" label="女" value="女"></el-option>
                <el-option key="未知性别" label="未知性别" value="未知性别"></el-option>
              </el-select>
            </td>

            <td colspan="2" style="text-align:right;">
              <el-button type="primary" v-on:click="getDatas()" style="background-color:#4db2ee;margin-right:10px;border-color:transparent;height: 40px" icon="el-icon-search">查询</el-button>
            </td>
          </tr>
        </table>
      </el-form>
    </el-col>
    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" style="width: 100%;" border @row-dblclick="goto">
      <el-table-column type="index" label="序号" width="80">
      </el-table-column>
      <el-table-column prop="id" v-if="false" label="主键" width="80" style="text-align:center">
      </el-table-column>
      <el-table-column prop="deleteState" v-if=false label="删除状态" width="60" >
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120" style="text-align:center">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="70" style="text-align:center">
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="70" style="text-align:center">
      </el-table-column>
      <el-table-column prop="area" label="地区" min-width="200" style="text-align:center">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" width="180" style="text-align:center">
      </el-table-column>
      <el-table-column prop="phone" label="联系电话" min-width="100" style="text-align:center">
      </el-table-column>
      <el-table-column prop="liveState" label="生存状态" min-width="80" style="text-align:center">
      </el-table-column>
      <el-table-column prop="doctor" label="责任医生" min-width="80" style="text-align:center">
      </el-table-column>
      <el-table-column prop="lastVisit" label="最近随访" min-width="120" style="text-align:center" :formatter="timeFormat">
      </el-table-column>
      <el-table-column prop="newbornVisit" label="访问总次数" min-width="120" style="text-align:center" >
      </el-table-column>
      <el-table-column prop="documentPeople" label="建档人" min-width="120" style="text-align:center" >
      </el-table-column>
      <el-table-column prop="documentDate" label="建档时间" min-width="100" style="text-align:center" :formatter="timeFormat">
      </el-table-column>
      <el-table-column label="操作" width="150" v-if="false">
        <template slot-scope="scope">
          <a size="14px" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
        </template>
      </el-table-column>
    </el-table>

    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="130px" :rules="editFormRules" ref="editForm">
        <div class="container" id="print">

          <el-form-item label="主键" v-if="false" prop="id" label-width="128px">
            <el-input v-model="editForm.id" auto-complete="off"></el-input>
          </el-form-item>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="身份证号" prop="peopleId" label-width="128px">
                    <el-input v-model="editForm.peopleId" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="姓名"  label-width="128px" auto-complete="off" prop="name">
                    <el-input v-model="editForm.name" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="性别" label-width="128px">
                    <el-radio-group v-model="editForm.sex">
                      <el-radio label=男>男</el-radio>
                      <el-radio label=女>女</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="年龄" prop="age">
                    <el-input  v-model.number="editForm.age" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12" >
                  <el-form-item label="生存状态" label-width="128px">
                    <el-radio-group v-model="editForm.liveState">
                      <el-radio label=存活>存活</el-radio>
                      <el-radio label=死亡>死亡</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="联系方式">
                    <el-input v-model="editForm.phone" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="地区">
                    <el-cascader :options="options" v-model="area" style="width: 100%" placeholder="请选择"></el-cascader>
                  </el-form-item>
                </el-col>
                <el-col :span="12" >
                  <el-form-item label="民族">
                    <el-input  v-model="editForm.nation" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-form-item label="责任医生" width="100px" prop="doctor" >
                <el-col :span="10">
                  <el-input v-model="editForm.doctor" auto-complete="off"></el-input>
                </el-col>
                <el-col :span="14" >
                  <el-form-item label="建档日期">
                    <el-date-picker type="date"  v-model="editForm.documentDate" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-form-item>

              <el-form-item label="建档人" prop="documentPeople">
                <el-col :span="10">
                  <el-input v-model="editForm.documentPeople" auto-complete="off"></el-input>
                </el-col>
                <el-col :span="14" >
                  <el-form-item label="最近随访" prop="lastVisit">
                    <el-date-picker type="date" placeholder="最近随访日期" v-model="editForm.lastVisit" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-form-item>

              <el-form-item label="第一次产前随访" prop="newbornVisit">
                <el-col :span="10">
                  <el-date-picker type="date"  v-model="editForm.newbornVisit" style="width: 100%;"></el-date-picker>
                </el-col>
                <el-col :span="14" >
                  <el-form-item label="随访总次数" prop="visitAllCount">
                    <el-input v-model.number="editForm.visitAllCount" ></el-input>
                  </el-form-item>
                </el-col>
              </el-form-item>

        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData" style="height: 40px">添加</el-button>
        <el-button v-else type="primary" v-if="edit" @click="createData" style="height: 40px">新增修改</el-button>
        <el-button v-else type="primary" v-if="edit" @click="updateData" style="height: 40px">修改</el-button>
        <el-button @click.native="dialogFormVisible=false" style="height: 40px">取消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
      <el-form :model="findForm" label-width="130px" :rules="editFormRules" ref="editForm">

        <el-form-item label="年龄">
          <el-col :span="10">
            <el-input v-model="findForm.age" placeholder="年龄"></el-input>
          </el-col>
          <el-col :span="14">
            <el-form-item label="性别">
              <el-input v-model="findForm.sex" placeholder="性别"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>

        <el-row>
          <el-col :span="10">
            <el-form-item label="区域">
              <el-input v-model="findForm.area" placeholder="区域"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="14">
            <el-form-item label="生存状态">
              <el-input v-model="findForm.liveState" placeholder="生存状态"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="民族">
          <el-col :span="10">
            <el-input v-model="findForm.nation" placeholder="民族"></el-input>
          </el-col>
          <el-col :span="14">
            <el-form-item label="身份证号">
              <el-input v-model="findForm.peopleId" placeholder="身份证号"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>

        <el-form-item label="联系电话">
            <el-col :span="10">
              <el-input v-model="findForm.phone" placeholder="联系电话"></el-input>
            </el-col>
            <el-col :span="14">
              <el-form-item label="责任医生">
                <el-input v-model="findForm.doctor" placeholder="责任医生"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>

        <el-form-item label="建档日期">
              <el-col :span="10">
                <el-date-picker type="date"  v-model="findForm.documentDate" placeholder="建档日期" style="width: 100%;"></el-date-picker>
              </el-col>
              <el-col :span="14">
                <el-form-item label="建档人">
                  <el-input v-model="findForm.documentPeople" placeholder="建档人"></el-input>
                </el-form-item>
              </el-col>
        </el-form-item>

        <el-form-item>
          <el-button style="height: 40px" type="primary" v-on:click="getDatas">查询</el-button>
        </el-form-item>

      </el-form>
    </el-dialog >

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-button style="height: 40px" type="danger" v-if="false" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
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
    editData,
    addData
  } from '@/api/childrenMangement'
  import city from '@/api/city.js'
  export default {
    area: [],
    name: 'youngcHealth',
    data() {
      return {
        checkPersonId: false,
        options: city.city(),
        edit: true,
        tableHeight: window.innerHeight - 320,
        activeName: 'first',
        dialogStatus: '',
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
          startTime: '',
          endTime: '',
          area: [],
          sex: '',
          age: '',
          nation: '',
          peopleId: '',
          phone: '',
          doctor: '',
          documentDate: '',
          documentPeople: '',
          liveState: ''
        },
        nation: [
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
        findForm: {
          sex: '',
          area: '',
          age: '',
          nation: '',
          peopleId: '',
          phone: '',
          doctor: '',
          documentDate: '',
          documentPeople: '',
          liveState: ''
        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [
            { required: true, message: '输入正确的姓名', trigger: 'blur' },
            { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: '用户名只能为中文'
            }
          ],
          doctor: [
            { required: true, message: '输入正确的姓名', trigger: 'blur' },
            { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: '用户名只能为中文'
            }
          ],
          documentPeople: [
            { required: true, message: '输入正确的姓名', trigger: 'blur' },
            { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: '用户名只能为中文'
            }
          ],
          peopleId: [
            { required: true, message: '请输入身份证号', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '你的身份证格式不正确' }],
          age: [
            { required: true, message: '不能为空', trigger: 'blur' },
            { type: 'number', message: '必须为数字值', trigger: 'blur' }
          ],
          visitAllCount: [
            { required: true, message: '不能为空', trigger: 'blur' },
            { type: 'number', message: '必须为数字值', trigger: 'blur' }
          ]
        },
        // 编辑界面数据
        editForm: {
          area: ''
        },
        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [
            { required: true, message: '输入正确的姓名', trigger: 'blur' },
            { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: '用户名只能为中文'
            }
          ],
          doctor: [
            { required: true, message: '输入正确的姓名', trigger: 'blur' },
            { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: '用户名只能为中文'
            }
          ],
          documentPeople: [
            { required: true, message: '输入正确的姓名', trigger: 'blur' },
            { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: '用户名只能为中文'
            }
          ],
          peopleId: [
            { required: true, message: '请输入身份证号', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '你的身份证格式不正确' }],
          age: [
            { required: true, message: '不能为空', trigger: 'blur' },
            { type: 'number', message: '必须为数字值', trigger: 'blur' }
          ],
          visitAllCount: [
            { required: true, message: '不能为空', trigger: 'blur' },
            { type: 'number', message: '必须为数字值', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      goto(row, event) {
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
        var str = style
        str = str + '<div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
          '  <div id="topContent">\n' +
          '    <h2 style="width:794px;text-align:center"> 1-8月龄儿童健康检查记录表 </h2>  \n' +
          '    <span style="margin-left:34px;">姓    名：{{name}}</span>\n' +
          '    <span style="margin-left:520px;">编号:{{number}}</span> \n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '    <tbody>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">月龄</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">随访日期</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">体重/kg</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">身长/cm</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">头围/cm</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">面色</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">皮肤</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">前囟</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">前囟宽度/cm</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">前囟高度/cm</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">颈部包块</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">眼睛</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">耳</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">听力</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">口腔</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">胸部</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">腹部</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">脐部</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">四肢</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">可疑佝偻病体征</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">可疑佝偻病体针</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">肛门/外生殖器</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">血红蛋白值</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">户外运动</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">服用维生素D</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">发育评估</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">两次随访患病情况</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">转诊建议</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">指导</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">下次随访日期</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">随访医生签名</td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '        <td colspan=\'1\'></td>\n' +
          '      </tr>\n' +
          '      \n' +
          '    </tbody>\n' +
          '  </table>\n' +
          '  <div id="footerContent"> <span style="margin-left:33px;margin-top:-5px">地    址：北京市阜外大街34号 <span style="margin-left:5px"></span>*********** <span style="margin-left:51px"></span>电    话：*********** <span style="margin-left:50px"></span>军    线：*********** </span> </div>\n' +
          '</div>\n' +
          '\n' +
          '</body>'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{number}}/, this.editForm.number ? this.editForm.number : '')
        myWindow.document.write(str)
        myWindow.focus()
        myWindow.document.close()
        myWindow.print()
        return myWindow
      },
      // 性别显示转换
      handleCurrentChange(val) {
        this.page = val
        this.getDatas()
      },
      findbtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          sex: '',
          area: [],
          startTime: '',
          endTime: '',
          age: '',
          ation: '',
          peopleId: '',
          phone: '',
          doctor: '',
          documentDate: '',
          documentPeople: '',
          liveState: ''
        }
      },
      // 获取用户列表
      getDatas() {
        this.dialogFormVisible2 = false

        this.findForm.documentDate =
          !this.findForm.documentDate || this.findForm.documentDate === ''
            ? null
            : util.formatDate.format(new Date(this.findForm.documentDate), 'yyyy-MM-dd')

        this.filters.startTime =
          !this.filters.startTime || this.filters.startTime === ''
            ? null
            : util.formatDate.format(new Date(this.filters.startTime), 'yyyy-MM-dd')

        this.filters.endTime =
          !this.filters.endTime || this.filters.endTime === ''
            ? null
            : util.formatDate.format(new Date(this.filters.endTime), 'yyyy-MM-dd')

        const para = {
          page: this.page,
          size: 20,
          filters: { startTime: this.filters.startTime, endTime: this.filters.endTime, name: this.filters.name,
            sex: this.filters.sex, area: this.filters.area, age: this.filters.age,
            nation: this.filters.nation, peopleId: this.filters.peopleId,
            phone: this.filters.phone, doctor: this.filters.doctor, documentDate: this.findForm.documentDate,
            documentPeople: this.filters.documentPeople, liveState: this.filters.liveState }
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
            this.getDatas()
          }
        })
      },
      search() {
        this.page = 1
        this.getDatas()
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
              this.getDatas()
            })
          })
          .catch(() => {})
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.area = []
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.area = this.editForm.area.split('/')
        this.edit = true
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.area = []
        this.editForm = {
          id: null,
          age: 0,
          visitAllCount: 0,
          lastVisit: 0,
          area: '',
          nation: '汉族',
          documentDate: Date.now(),
          number: '',
          creator: '',
          createDate: null,
          modifier: '',
          modifyDate: null
        }
        this.edit = false
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

                para.nextDate =
                  !para.nextDate || para.nextDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextDate), 'yyyy-MM-dd')

                para.followDate =
                  !para.followDate || para.followDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.followDate), 'yyyy-MM-dd')

                para.documentDate =
                  !para.documentDate || para.documentDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.documentDate), 'yyyy-MM-dd')

                para.newbornVisit =
                  !para.newbornVisit || para.newbornVisit === ''
                    ? null
                    : util.formatDate.format(new Date(para.newbornVisit), 'yyyy-MM-dd')

                para.area = ''
                for (const itm of this.area) {
                  if (itm !== '') {
                    para.area += itm
                    para.area += '/'
                  }
                }
                para.area = para.area.substr(0, para.area.length - 1)

                editData(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.getDatas()
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

                para.nextDate =
                  !para.nextDate || para.nextDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextDate), 'yyyy-MM-dd')

                para.followDate =
                  !para.followDate || para.followDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.followDate), 'yyyy-MM-dd')

                para.documentDate =
                  !para.documentDate || para.documentDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.documentDate), 'yyyy-MM-dd')

                para.newbornVisit =
                  !para.newbornVisit || para.newbornVisit === ''
                    ? null
                    : util.formatDate.format(new Date(para.newbornVisit), 'yyyy-MM-dd')

                para.area = ''
                for (const itm of this.area) {
                  if (itm !== '') {
                    para.area += itm
                    para.area += '/'
                  }
                }
                para.area = para.area.substr(0, para.area.length - 1)

                addData(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.getDatas()
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
              this.getDatas()
            })
          })
          .catch(() => {})
      }
    },
    mounted() {
      this.getDatas()
    }
  }
</script>

<style scoped>
  .mystyle{
    font-size: 14px;
    color: #323232;
  }
</style>


