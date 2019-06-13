import request from '@/utils/request'

const apipath = '/api/v1.0'

export function getUserListPage(params) {
  return request({
    url: apipath + '/hyperSelect/list',
    method: 'get',
    params: params
  })
}
export function removeUser(params) {
  return request({
    url: apipath + '/hyperSelect/entiry/' + params,
    method: 'DELETE'
  })
}
export function batchRemoveUser(params) {
  return request({
    url: apipath + '/hyperSelect/batchremove',
    method: 'get',
    params: params
  })
}
export function editUser(params) {
  return request({
    url: apipath + '/hyperSelect/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'put',
    data: params
  })
}
export function addUser(params) {
  return request({
    url: apipath + '/hyperSelect/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'post',
    data: params
  })
}

export function getUserListPage2(params) {
  return request({
    url: apipath + '/Perinfor/list',
    method: 'get',
    params: params
  })
}
export function removeUser2(params) {
  return request({
    url: '/Perinfor/remove',
    method: 'get',
    params: params
  })
}

export function batchRemoveUser2(params) {
  return request({
    url: '/Perinfor/batchremove',
    method: 'get',
    params: params
  })
}
export function editUser2(params) {
  return request({
    url: '/Perinfor/edit',
    method: 'get',
    params: params
  })
}

export function addUser2(params) {
  return request({
    url: '/Perinfor/add',
    method: 'get',
    params: params
  })
}
