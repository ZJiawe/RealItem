import { asyncRouterMap, constantRouterMap } from '@/router'
import { getMenu } from '@/api/login'
import store from '@/store'
/**
 * 通过meta.role判断是否与当前用户权限匹配
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.id) {
    return roles.some(role => route.id.indexOf(role) >= 0)
  } else {
    return true
  }
}

/**
 * 递归过滤异步路由表，返回符合用户角色权限的路由表
 * @param asyncRouterMap
 * @param roles
 */
function filterAsyncRouter(asyncRouterMap, roles) {
  const accessedRouters = asyncRouterMap.filter(route => {
    if (hasPermission(roles, route)) {
      if (route.children && route.children.length) {
        route.children = filterAsyncRouter(route.children, roles)
      }
      return true
    }
    return false
  })
  return accessedRouters
}

const permission = {
  state: {
    routers: constantRouterMap,
    addRouters: [],
    buttons: []
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
    },
    SET_BUTTONS: (state, buttons) => {
      state.buttons = buttons
    }
  },
  actions: {
    // 获取菜单信息
    GenerateRoutes({ commit }, data) {
      return new Promise((resolve, reject) => {
        let accessedRouters
        getMenu(store.getters.token, store.getters.id).then(response => {
          const data = response.obj
          const roles = []
          const menu1 = data.detailModelList
          for (const itm of menu1) {
            roles.push(itm.code)
          }
          accessedRouters = filterAsyncRouter(asyncRouterMap, roles)
          commit('SET_ROUTERS', accessedRouters)
          commit('SET_BUTTONS', roles)
          resolve()
        }).catch(error => {
          console.log(error)
        })
        /*      const { roles } = data
        let accessedRouters
        if (roles.indexOf('admin') >= 0) {
          console.log('admin>=0')
          accessedRouters = asyncRouterMap
        } else {
          console.log('admin<0')
          accessedRouters = filterAsyncRouter(asyncRouterMap, roles)
          // accessedRouters = ''
          // accessedRouters = asyncRouterMap
        }
        console.log('accessedRouters', accessedRouters)
        commit('SET_ROUTERS', accessedRouters)
        resolve()*/
      })
    }
  }
}

export default permission
