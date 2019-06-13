import Vue from 'vue'
import Router from 'vue-router'

// const _import = require('./_import_' + process.env.NODE_ENV)
// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/

export const constantRouterMap = [
  // {
  //   path: '/',
  //   component: Layout,
  //   redirect: '/login',
  //   name: 'login',
  //   hidden: true
  // },
  {
    path: '',
    component: Layout,
    redirect: '/dashboard/dashboard'
  },
  { path: '/login', component: () => import('@/views/login'), name: '登录公卫系统', hidden: true },
  { path: '/404', component: () => import('@/views/errorPage/404'), hidden: true },
  { path: '/401', component: () => import('@/views/errorPage/401'), hidden: true },
  // 锁屏
  {
    path: '/lock',
    hidden: true,
    name: '锁屏页',
    component: () => import('@/views/common/lock')
  }
  // 报表
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
export const asyncRouterMap = [
  // 健康档案管理
  {
    id: 'PH_HomePage',
    path: '/dashboard',
    component: Layout,
    meta: { title: 'dashboard', icon: 'dashboard' },
    children: [
      {
        id: 'PH_HomePage',
        path: 'dashboard',
        name: '首页',
        component: () => import('@/views/dashboard/dashboard'),
        meta: { title: '首页', icon: 'dashboard' }
      }
    ]
  },
  {
    id: 'PH_HR_Manage',
    path: '/Health_record_management',
    component: Layout,
    meta: { title: '健康档案管理', icon: 'dashboard' },
    children: [
      {
        id: 'PH_HR_Penson',
        path: 'template',
        name: '个人健康档案管理',
        component: () => import('@/views/Health_record_management/template'),
        meta: { title: '个人健康档案管理', icon: 'dashboard' },
        children: [
          {
            id: 'PH_HR_Perinfor',
            path: 'Perinfor',
            name: '个人基本档案',
            component: () => import('@/views/Health_record_management/Perinfor'),
            meta: { title: '个人基本档案', icon: 'dashboard' }
          },
          {
            id: 'PH_HR_HealthCheckups',
            path: 'HealthCheckups',
            name: '个人体检档案',
            component: () => import('@/views/Health_record_management/HealthCheckups'),
            meta: { title: '个人体检档案', icon: 'dashboard' }
          },
          {
            id: 'PH_HR_FileMigrationAudit',
            path: 'fileMigrationAudit ',
            name: '档案移动审核',
            component: () => import('@/views/Health_record_management/fileMigrationAudit'),
            meta: { title: '档案移动审核', icon: 'columnar' }
          }
        ]

      },
      {
        id: 'PH_HR_Family',
        path: 'family',
        name: '家庭健康档案管理',
        component: () => import('@/views/Health_record_management/family'),
        meta: { title: '家庭健康档案管理', icon: 'columnar' }
      }
    ]
  },
  // 健康教育
  {
    id: 'PH_H_Edu',
    path: '/Health_education',
    component: Layout,
    meta: { title: '健康教育', icon: 'dashboard' },
    children: [
      {
        id: 'PH_H_Edu_Follow',
        path: 'SevereHealthPlanFollow',
        name: '健教计划管理',
        component: () => import('@/views/Health_education/SevereHealthPlanFollow'),
        meta: { title: '健教计划管理', icon: 'dashboard' }
      }
    ]
  },
  // 重点人群管理
  {
    id: 'PH_KP_Manage',
    path: '/Key_population_management',
    component: Layout,
    meta: { title: '重点人群管理', icon: 'dashboard' },
    children: [
      {
        id: 'PH_KP_Child',
        path: 'one',
        name: '0~6岁儿童健康管理',
        component: () => import('@/views/Key_population_management/one'),
        meta: { title: '0~6岁儿童健康管理', icon: 'dashboard' },
        children: [
          {
            id: 'PH_KP_NewbornVisit',
            path: 'NewbornVisit',
            name: '新生儿家庭访视记录表',
            component: () => import('@/views/Key_population_management/NewbornVisit'),
            meta: { title: '新生儿家庭访视记录表', icon: 'dashboard' }
          },
          {
            id: 'PH_KP_18Child',
            path: 'OneEightMonthChild',
            name: '1-8月龄儿童健康检查记录表',
            component: () => import('@/views/Key_population_management/OneEightMonthChild'),
            meta: { title: '1-8月龄儿童健康检查记录表', icon: 'dashboard' }
          },
          {
            id: 'PH_KP_812MonthChild',
            path: 'TwelveThsixEightMonthChild',
            name: '12-30月龄儿童健康检查记录表',
            component: () => import('@/views/Key_population_management/TwelveThsixEightMonthChild'),
            meta: { title: '12-30月龄儿童健康检查记录表', icon: 'dashboard' }
          },
          {
            id: 'PH_KP_36YearChild',
            path: 'ThreeSixYearChild',
            name: '3-6岁儿童健康检查记录表',
            component: () => import('@/views/Key_population_management/ThreeSixYearChild'),
            meta: { title: '3-6岁儿童健康检查记录表', icon: 'dashboard' }
          }
        ]
      },
      {
        id: 'PH_KP_Maternal',
        path: 'one',
        component: () => import('@/views/Key_population_management/one'),
        meta: { title: '孕产妇健康管理', icon: 'dashboard' },
        children: [
          {
            id: 'PH_KP_1PrenatalDiagnosis',
            path: 'firstPrenatalDiagnosis',
            component: () => import('@/views/Key_population_management/firstPrenatalDiagnosis'),
            name: '第1次产前检查服务记录表',
            meta: { title: '第1次产前检查服务记录表', icon: 'dashboard' }
          },
          {
            id: 'PH_KP_25PrenatalDiagnosis',
            path: 'scheduleTwoFivePrenatalDiagnosis',
            name: '第2-5次产前随访服务记录表',
            component: () => import('@/views/Key_population_management/scheduleTwoFivePrenatalDiagnosis'),
            meta: { title: '第2-5次产前随访服务记录表', icon: 'dashboard' }
          },
          {
            id: 'PH_KP_PostpartumVisit',
            path: 'postpartumVisit',
            name: '产后访视记录表',
            component: () => import('@/views/Key_population_management/postpartumVisit'),
            meta: { title: '产后访视记录表', icon: 'dashboard' }
          },
          {
            id: 'PH_KP_42PostpartumVisit',
            path: 'postpartumVisitFortytwo',
            name: '产后42天健康检查记录表',
            component: () => import('@/views/Key_population_management/postpartumVisitFortytwo'),
            meta: { title: '产后42天健康检查记录表', icon: 'dashboard' }
          }
        ]
      },
      {
        id: 'PH_KP_SevereHyperFollow',
        path: 'SevereHyperFollow',
        name: '高血压患者健康管理',
        component: () => import('@/views/Key_population_management/SevereHyperFollow'),
        meta: { title: '高血压患者健康管理', icon: 'dashboard' }
      },
      {
        id: 'PH_KP_Diabetes2',
        path: 'diabetes',
        name: '2型糖尿病患者健康管理',
        component: () => import('@/views/Key_population_management/diabetes'),
        meta: { title: '2型糖尿病患者健康管理', icon: 'dashboard' }
      },
      {
        id: 'PH_KP_Aged',
        path: 'one',
        name: '老年人健康管理',
        component: () => import('@/views/Key_population_management/one'),
        meta: { title: '老年人健康管理', icon: 'dashboard' },
        children: [
          {
            id: 'PH_KP_SelfcareAbility',
            path: 'SelfcareAbility',
            name: '老年人生活自理能力评估表',
            component: () => import('@/views/Key_population_management/SelfcareAbility'),
            meta: { title: '老年人生活自理能力评估表', icon: 'dashboard' }
          },
          {
            id: 'PH_KP_Record',
            path: 'HealthManagementEcord',
            name: '老年人中医药健康管理服务记录表',
            component: () => import('@/views/Key_population_management/HealthManagementEcord'),
            meta: { title: '老年人中医药健康管理服务记录表', icon: 'dashboard' }
          }
        ]
      },
      {
        id: 'PH_KP_Mental',
        path: 'one',
        name: '重性精神疾病患者管理',
        component: () => import('@/views/Key_population_management/one'),
        meta: { title: '重性精神疾病患者管理', icon: 'dashboard' },
        children: [
          {
            id: 'PH_KP_SevereMentalSupplement',
            path: 'severeMentalSupplement',
            name: '严重精神障碍患者个人信息补充表',
            component: () => import('@/views/Key_population_management/severeMentalSupplement'),
            meta: { title: '严重精神障碍患者个人信息补充表', icon: 'dashboard' }
          },
          {
            id: 'PH_KP_severeMentalFollow',
            path: 'severeMentalFollow',
            name: '严重精神障碍患者随访服务记录表',
            component: () => import('@/views/Key_population_management/severeMentalFollow'),
            meta: { title: '严重精神障碍患者随访服务记录表', icon: 'dashboard' }
          }
        ]
      },
      {
        id: 'PH_KP_Pulmonary',
        path: 'one',
        name: '肺结核患者健康管理',
        component: () => import('@/views/Key_population_management/one'),
        meta: { title: '肺结核患者健康管理', icon: 'dashboard' },
        children: [
          {
            id: 'PH_KP_FirsthomevisitRecord',
            path: 'firsthomevisitRecord',
            name: '肺结核患者健康管理',
            component: () => import('@/views/Key_population_management/firsthomevisitRecord'),
            meta: { title: '肺结核患者健康管理', icon: 'dashboard' }
          },
          {
            id: 'PH_KP_Followupservicerecord',
            path: 'followupservicerecord',
            name: '肺结核患者随访服务记录表',
            component: () => import('@/views/Key_population_management/followupservicerecord'),
            meta: { title: '肺结核患者随访服务记录表', icon: 'dashboard' }
          }
        ]
      }
    ]
  },
  // 重大公共卫生事件管理
  {
    id: 'PH_Health_Events',
    path: '/healthEvents',
    component: Layout,
    meta: { title: '重大公共卫生事件管理', icon: 'dashboard' },
    children: [
      {
        id: 'PH_Health_Events_Emergencies',
        path: 'emergencies',
        name: '传染病与突发公卫',
        component: () => import('@/views/healthEvents/emergencies'),
        meta: { title: '传染病与突发公卫', icon: 'dashboard' }
      }
    ]
  },
  // 卫生监督协管
  {
    id: 'PH_Health_Supervision',
    path: '/healthSupervision',
    component: Layout,
    meta: { title: '卫生监督协管', icon: 'dashboard' },
    children: [
      {
        id: 'PH_Health_Supervision_F',
        path: 'healthSupervision',
        name: '卫生监督协管',
        component: () => import('@/views/healthSupervision/healthSupervision'),
        meta: { title: '卫生监督协管', icon: 'dashboard' }
      }
    ]
  },
  // 综合查询
  {
    id: 'PH_IQ',
    path: '/Integrated_query',
    component: Layout,
    meta: { title: '综合查询', icon: 'dashboard' },
    children: [
      {
        id: 'PH_IQ_Residents',
        path: 'residents',
        name: '居民查询',
        component: () => import('@/views/Integrated_query/residents'),
        meta: { title: '居民查询', icon: 'dashboard' }
      },
      {
        id: 'PH_IQ_Elderlyhealthy',
        path: 'elderlyhealthy',
        name: '老年人健康查询',
        component: () => import('@/views/Integrated_query/elderlyhealthy'),
        meta: { title: '老年人健康查询', icon: 'dashboard' }
      },
      {
        id: 'PH_IQ_HypertensionPatient',
        path: 'hypertensionPatient',
        name: '高血压患者查询',
        component: () => import('@/views/Integrated_query/hypertensionPatient'),
        meta: { title: '高血压患者查询', icon: 'dashboard' }
      },
      {
        id: 'PH_IQ_TwoDiabetes',
        path: 'twoDiabetes',
        name: '2型糖尿病患者查询',
        component: () => import('@/views/Integrated_query/twoDiabetes'),
        meta: { title: '2型糖尿病患者查询', icon: 'dashboard' }
      },
      {
        id: 'PH_IQ_MentallyPatients',
        path: 'mentallyPatients',
        name: '严重精神病患者查询',
        component: () => import('@/views/Integrated_query/mentallyPatients'),
        meta: { title: '严重精神病患者查询', icon: 'dashboard' }
      },
      {
        id: 'PH_IQ_TuberculosisPatient',
        path: 'tuberculosisPatient',
        name: '肺结核患者查询',
        component: () => import('@/views/Integrated_query/tuberculosisPatient'),
        meta: { title: '肺结核患者查询', icon: 'dashboard' }
      },
      {
        id: 'PH_IQ_ChildrenMangement',
        path: 'youngcHealth',
        name: '婴幼儿健康管理',
        component: () => import('@/views/Integrated_query/ChildrenMangement'),
        meta: { title: '婴幼儿健康管理', icon: 'dashboard' }
      },
      {
        id: 'PH_IQ_MaternalManagement',
        path: 'maternalManagement.vue',
        name: '孕产妇管理',
        component: () => import('@/views/Integrated_query/maternalManagement.vue'),
        meta: { title: '孕产妇管理', icon: 'dashboard' }
      }
    ]
  },
  // 转诊
  {
    id: 'PH_Transfer',
    path: '/Transfer_treatment',
    component: Layout,
    meta: { title: '转诊', icon: 'dashboard' },
    children: [
      {
        id: 'PH_Transfer_Out',
        path: 'out',
        name: '转出',
        component: () => import('@/views/Transfer_treatment/transTreatOut'),
        meta: { title: '转出', icon: 'dashboard' }
      },
      {
        id: 'PH_Transfer_In',
        path: 'into',
        name: '转入',
        component: () => import('@/views/Transfer_treatment/transTreatIn'),
        meta: { title: '转入', icon: 'dashboard' }
      },
      {
        id: 'PH_Transfer_ReferSettings',
        path: 'referralSettings',
        name: '转诊设置',
        component: () => import('@/views/Transfer_treatment/referralSettings'),
        meta: { title: '转诊设置', icon: 'dashboard' }
      }
    ]
  },
  // 统计报表
  {
    id: 'PH_SR',
    path: '/Statistical_reports',
    component: Layout,
    meta: { title: '统计报表', icon: 'dashboard' },
    children: [
      {
        id: 'PH_SR_ResidentHealth',
        path: 'residentHealth',
        name: '居民健康档案统计',
        component: () => import('@/views/Statistical_reports/residentHealth'),
        meta: { title: '居民健康档案统计', icon: 'dashboard' }
      },
      {
        id: 'PH_SR_ChildrenStatistics',
        path: 'childHealth',
        name: '儿童健康档案管理统计',
        component: () => import('@/views/Statistical_reports/ChildrenStatistics'),
        meta: { title: '儿童健康档案管理统计', icon: 'dashboard' }
      },
      {
        id: 'PH_SR_MaternalStatistical',
        path: 'maternalStatistical',
        name: '孕产妇健康管理统计',
        component: () => import('@/views/Statistical_reports/maternalStatistical'),
        meta: { title: '孕产妇健康管理统计', icon: 'dashboard' }
      },
      {
        id: 'PH_SR_ElderlyHealth',
        path: 'elderlyHealth',
        name: '老年人健康管理统计',
        component: () => import('@/views/Statistical_reports/elderlyHealth'),
        meta: { title: '老年人健康管理统计', icon: 'dashboard' }
      },
      {
        id: 'PH_SR_HighBlood',
        path: 'highBlood',
        name: '高血压健康管理统计',
        component: () => import('@/views/Statistical_reports/highBlood'),
        meta: { title: '高血压健康管理统计', icon: 'dashboard' }
      },
      {
        id: 'PH_SR_Totaldiabetes',
        path: 'Totaldiabetes',
        name: '2型糖尿病健康管理统计',
        component: () => import('@/views/Statistical_reports/Totaldiabetes'),
        meta: { title: '2型糖尿病健康管理统计', icon: 'dashboard' }
      },
      {
        id: 'PH_SR_MentalDisorders',
        path: 'mentalDisorders',
        name: '严重精神障碍患者管理统计',
        component: () => import('@/views/Statistical_reports/mentalDisorders'),
        meta: { title: '严重精神障碍患者管理统计', icon: 'dashboard' }
      },
      {
        id: 'PH_SR_TuberculosisHealth',
        path: 'TuberculosisHealth',
        name: '肺结核健康管理统计',
        component: () => import('@/views/Statistical_reports/TuberculosisHealth'),
        meta: { title: '肺结核健康管理统计', icon: 'dashboard' }
      },
      {
        id: 'PH_SR_MedicineHealth',
        path: 'medicineHealth ',
        name: '中医药健康管理统计',
        component: () => import('@/views/Statistical_reports/medicineHealth'),
        meta: { title: '中医药健康管理统计', icon: 'dashboard' }
      }
    ]
  }
]
