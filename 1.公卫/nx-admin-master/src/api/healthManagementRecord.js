import request from '@/utils/request'

const apipath = '/api/v1.0'

export function getList(params) {
  return request({
    url: apipath + '/HealthManagementRecord/list',
    method: 'get',
    params: params
  })
}
export function removeData(params) {
  return request({
    url: apipath + '/HealthManagementRecord/entiry/' + params,
    method: 'DELETE'
  })
}
export function batchRemoveData(params) {
  return request({
    url: apipath + '/HealthManagementRecord/batchremove',
    method: 'get',
    params: params
  })
}
export function editData(params) {
  return request({
    url: apipath + '/HealthManagementRecord/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'put',
    data: params
  })
}
export function addData(params) {
  return request({
    url: apipath + '/HealthManagementRecord/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'post',
    data: params
  })
}

export function getListPage2(params) {
  return request({
    url: apipath + '/Perinfor/list',
    method: 'get',
    params: params
  })
}
export function removeData2(params) {
  return request({
    url: '/Perinfor/remove',
    method: 'get',
    params: params
  })
}

export function batchremoveData2(params) {
  return request({
    url: '/Perinfor/batchremove',
    method: 'get',
    params: params
  })
}
export function editData2(params) {
  return request({
    url: '/Perinfor/edit',
    method: 'get',
    params: params
  })
}

export function addData2(params) {
  return request({
    url: '/Perinfor/add',
    method: 'get',
    params: params
  })
}
