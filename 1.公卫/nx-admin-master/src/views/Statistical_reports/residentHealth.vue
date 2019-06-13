<template>
  <section class="app-container">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;" >
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item label-width="160px">
          <el-cascader :options="options" v-model="filters.area"  placeholder="区域"  change-on-select style="width: 100%"></el-cascader>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers" style="background-color:#4db2ee;border-color:transparent;height:40px;" icon="el-icon-search" >查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="clear" style="background-color:#ffffff;color:#909090;border-color: #c1c1c1;font-size: 14px;margin-left:10px;" icon="el-icon-back" >重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;">
      <el-table-column type="index" label="序号" width="70">
      </el-table-column>
      <el-table-column prop="area" label="区域" min-width="250">
      </el-table-column>
      <el-table-column prop="year" label="年份" width="60">
      </el-table-column>
      <el-table-column prop="permanentPopulation" label="辖区常住人口数" width="140">
      </el-table-column>
      <el-table-column prop="numberSetters" label="建档人数" min-width="100">
      </el-table-column>
      <el-table-column prop="healthBuildRate" label="健康档案建档率" width="160">
      </el-table-column>
      <el-table-column prop="electronicHealth" label="电子健康档案建档数" min-width="180">
      </el-table-column>
      <el-table-column prop="electronicRate" label="电子健康档案建档率" width="180">
      </el-table-column>
      <el-table-column prop="healthDynamic" label="健康档案动态使用数量" width="200" v-if='false'>
      </el-table-column>
      <el-table-column prop="healthUtilization" label="健康档案动态使用率" width="190" v-if='false'>
      </el-table-column>
      <el-table-column prop="newFiles" label="新增档案数量" width="140">
      </el-table-column>
      <el-table-column prop="deaths" label="死亡人数" width="140">
      </el-table-column>
      <el-table-column prop="floatingPopulation" label="流动人口数" width="140" v-if='false'>
      </el-table-column>


    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="10" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

  </section>
</template>

<script>
  import {
    getList
  } from '@/api/residentsStatistical'
  import city from '@/api/city.js'
  export default {
    data() {
      return {
        options: city.city(),
        area: [],
        dialogStatus: '',
        tableHeight: window.innerHeight - 260,
        textMap: {
          update: '编辑',
          create: '添加',
          search: '查询'
        },
        filters: {
          area: []
        },
        users: [],
        total: 0,
        page: 1,
        sels: []
      }
    },
    methods: {
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      // 获取用户列表
      getUsers() {
        const para = {
          page: this.page,
          size: 20,
          filters: {
            area: this.filters.area
          }
        }
        para.filters.area = ''
        for (const itm of this.filters.area) {
          para.filters.area += itm
          para.filters.area += '/'
        }
        para.filters.area = para.filters.area.substr(0, para.filters.area.length - 1)

        getList(para).then(res => {
          this.total = res.detailModelList.length
          this.users = res.detailModelList
        })
      },
      clear() {
        this.filters = {
          area: []
        }
        this.getUsers()
      }
    },
    mounted() {
      this.getUsers()
    }
  }
</script>

