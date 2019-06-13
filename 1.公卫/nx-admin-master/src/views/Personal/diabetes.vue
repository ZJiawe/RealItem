<template xmlns:el-col="http://www.w3.org/1999/html">

  <div class="app-container" >
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-date"></i> 表单</el-breadcrumb-item>
        <el-breadcrumb-item>2型糖尿病患者随访服务记录表</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="form-box" >
        <el-form ref="form" :model="form" label-width="120px">.

          <el-form-item label="姓名">
            <el-col :span="9">
            <el-input v-model="form.name" auto-complete="off"></el-input>
            </el-col>
            <el-col :span="15">
              <el-form-item label="编号">
                <el-input v-model="form.number"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>
         <!-- <el-form-item label="随访方式" >
              <el-select v-model="form.region" placeholder="请选择" >
                <el-option key="mz" label="门诊" value="mz"></el-option>
                <el-option key="jt" label="家庭" value="jt"></el-option>
                <el-option key="dh" label="电话" value="dh"></el-option>
              </el-select>
          </el-form-item>
          <el-form-item label="随访日期">
            <el-col :span="9">
              <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2">—</el-col>
            <el-col :span="10">
              <el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
            </el-col>
          </el-form-item>-->


          <el-form-item label="症状">
            <el-col :span="9">
            <el-select v-model="form.region1" placeholder="请选择">
              <el-option key="wzz" label="无症状" value="wzz"></el-option>
              <el-option key="dy" label="多饮" value="dy"></el-option>
              <el-option key="ds" label="多食" value="ds"></el-option>
              <el-option key="dn" label="多尿" value="dn"></el-option>
              <el-option key="slmh" label="视力模糊" value="slmh"></el-option>
              <el-option key="gr" label="感染" value="gr"></el-option>
              <el-option key="sjmm" label="手脚麻木" value="sjmm"></el-option>
              <el-option key="xzfz" label="下肢浮肿" value="xzfz"></el-option>
              <el-option key="tz" label="体重明显下降" value="tz"></el-option>
              <el-option key="qt" label="其他" value="qt"></el-option>
            </el-select>
            </el-col>
            <el-col :span="15">
              <el-form-item label="足背动脉搏动" aria-placeholder="请选择">
                <el-cascader :options="options" v-model="form.options"></el-cascader>
              </el-form-item>
            </el-col>
          </el-form-item>
          <el-form-item label="血压(mmHg)">
            <el-col :span="9">
              <el-input v-model="form.blood"></el-input>
            </el-col>
            <el-col :span="15">
              <el-form-item label="体重(kg)" >
                <el-input v-model="form.weight"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>
          <el-form-item label="体质指数(kg/m²)">
            <el-col :span="9">
              <el-input v-model="form.tzzs"></el-input>
            </el-col>
            <el-col :span="15">
              <el-form-item label="其他">
                <el-input v-model="form.other"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>

          <el-form-item label="日吸烟量(支)">
            <el-col :span="9">
              <el-input v-model="form.smoke"></el-input>
            </el-col>
            <el-col :span="15">
              <el-form-item label="日饮酒量(两)">
                <el-input v-model="form.drink"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>
          <el-form-item label="运动">
            <el-col :span="4">
              <el-input  v-model="form.exercise1" placeholder="周/次"></el-input>
            </el-col>
            <el-col :span="5">
              <el-input  v-model="form.exercise2" placeholder="分钟/次"></el-input>
            </el-col>
            <el-col :span="15">
              <el-form-item label="主食(克/天)">
                <el-input v-model="form.eat"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>

          <el-form-item label="心理调整">
            <el-col :span="9">
              <el-select v-model="form.region2" placeholder="请选择">
                <el-option key="lh" label="良好" value="lh"></el-option>
                <el-option key="yb" label="一般" value="yb"></el-option>
                <el-option key="c" label="差" value="c"></el-option>
              </el-select>
            </el-col>
            <el-col :span="15">
              <el-form-item label="遵医行为">
                <el-select v-model="form.region3" placeholder="请选择">
                  <el-option key="lh" label="良好" value="lh"></el-option>
                  <el-option key="yb" label="一般" value="yb"></el-option>
                  <el-option key="c" label="差" value="c"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-form-item>

          <el-form-item label="空腹血糖值">
            <el-col :span="9">
              <el-input v-model="form.blood"></el-input>
            </el-col>
            <el-col :span="15">
              <el-form-item label="其他检查">
                <el-input v-model="form.weight"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>


          <el-form-item label="服药依从性">
            <el-col :span="9">
              <el-select v-model="form.region4" placeholder="请选择">
                <el-option key="gl" label="规律" value="gl"></el-option>
                <el-option key="jd" label="间断" value="jd"></el-option>
                <el-option key="bfy" label="不服药" value="bfy"></el-option>
              </el-select>
            </el-col>
            <el-col :span="15">
              <el-form-item label="药物不良反应">
                <el-select v-model="form.region5" placeholder="请选择">
                  <el-option key="w" label="无" value="lh"></el-option>
                  <el-option key="y" label="有" value="y"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-form-item>

          <el-form-item label="低血糖反应">
            <el-col :span="9">
              <el-select v-model="form.region6" placeholder="请选择">
                <el-option key="w" label="无" value="w"></el-option>
                <el-option key="oe" label="偶尔" value="oe"></el-option>
                <el-option key="pf" label="频繁" value="pf"></el-option>
              </el-select>
            </el-col>
            <el-col :span="15">
              <el-form-item label="此次随访分类">
                <el-select v-model="form.region7" placeholder="请选择">
                  <el-option key="kzmy" label="控制满意" value="kzmy"></el-option>
                  <el-option key="kzbmy" label="控制不满意" value="kzbmy"></el-option>
                  <el-option key="blfy" label="不良反应" value="blfy"></el-option>
                  <el-option key="bfz" label="并发症" value="bfz"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-form-item>

          <el-form-item label="药物名称1">
            <el-col :span="9">
              <el-input v-model="form.yw1"></el-input>
            </el-col>
            <el-col :span="15">
              <el-form-item label="用法用量">
                <el-col :span="12">
                  <el-input  v-model="form.mr1" placeholder="每日/次"></el-input>
                </el-col>
                <el-col :span="12">
                  <el-input  v-model="form.mc1" placeholder="/次"></el-input>
                </el-col>
              </el-form-item>
            </el-col>
          </el-form-item>

          <el-form-item label="药物名称2">
            <el-col :span="9">
              <el-input v-model="form.yw2"></el-input>
            </el-col>
            <el-col :span="15">
              <el-form-item label="用法用量">
                <el-col :span="12">
                  <el-input  v-model="form.mr2" placeholder="每日/次"></el-input>
                </el-col>
                <el-col :span="12">
                  <el-input  v-model="form.mc2" placeholder="/次"></el-input>
                </el-col>
              </el-form-item>
            </el-col>
          </el-form-item>

          <el-form-item label="药物名称3">
            <el-col :span="9">
              <el-input v-model="form.yw3"></el-input>
            </el-col>
            <el-col :span="15">
              <el-form-item label="用法用量">
                <el-col :span="12">
                  <el-input  v-model="form.mr3" placeholder="每日/次"></el-input>
                </el-col>
                <el-col :span="12">
                  <el-input  v-model="form.mc3" placeholder="/次"></el-input>
                </el-col>
              </el-form-item>
            </el-col>
          </el-form-item>

          <el-form-item label="胰岛素">
            <el-col :span="9">
              <el-input v-model="form.yds" placeholder="种类"></el-input>
            </el-col>
            <el-col :span="15">
              <el-form-item label="用法和用量">
                <el-col :span="12">
                  <el-input  v-model="form.yd1" placeholder="每日/次"></el-input>
                </el-col>
                <el-col :span="12">
                  <el-input  v-model="form.yd2" placeholder="/次"></el-input>
                </el-col>
              </el-form-item>
            </el-col>
          </el-form-item>

          <el-form-item label="原因">
            <el-col :span="9">
              <el-input v-model="form.yy"></el-input>
            </el-col>
            <el-col :span="15">
              <el-form-item label="机构及科别">
                <el-input v-model="form.jg" ></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>




          <el-form-item label="随访日期">
            <el-col :span="9">
              <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2">—</el-col>
            <el-col :span="10">
              <el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="随访日期">
            <el-col :span="9">
              <el-date-picker type="date" placeholder="选择日期" v-model="form.date3" style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2">—</el-col>
            <el-col :span="10">
              <el-time-picker placeholder="选择时间" v-model="form.date4" style="width: 100%;"></el-time-picker>
            </el-col>
          </el-form-item>

          <el-form-item label="随访方式" >
            <el-col :span="9">
              <el-select v-model="form.region" placeholder="请选择" >
                <el-option key="mz" label="门诊" value="mz"></el-option>
                <el-option key="jt" label="家庭" value="jt"></el-option>
                <el-option key="dh" label="电话" value="dh"></el-option>
              </el-select>
            </el-col>
            <el-col :span="9">
              <el-form-item label="随访医生签名" style="width: 300px">
                <el-input ></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="onSubmit">表单提交</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>

</template>

<script>
  export default {
    name: 'baseform',
    data: function() {
      return {
        options: [
          {
            value: 'cjzc',
            label: '触及正常'
          },
          {
            value: 'jr',
            label: '减弱',
            children: [
              {
                value: 'sc',
                label: '双侧'
              },
              {
                value: 'zc',
                label: '左侧'
              },
              {
                value: 'yc',
                label: '右侧'
              }
            ]
          },
          {
            value: 'xs',
            label: '消失',
            children: [
              {
                value: 'sc',
                label: '双侧'
              },

              {
                value: 'zc',
                label: '左侧'
              },
              {
                value: 'yc',
                label: '右侧'
              }

            ]
          }
        ],
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: true,
          type: ['步步高'],
          resource: '小天才',
          desc: '',
          options: []
        }
      }
    },
    methods: {
      onSubmit() {
        this.$message.success('提交成功！')
      }
    }
  }
</script>
