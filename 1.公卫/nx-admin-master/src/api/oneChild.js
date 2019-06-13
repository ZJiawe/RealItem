import request from '@/utils/request'

export function getUserListPage(params) {
  return request({
    url: '/healthsupervision/list',
    method: 'get',
    params: params
  })
}
export function removeUser(params) {
  return request({
    url: '/user/remove',
    method: 'get',
    params: params
  })
}
export function batchRemoveUser(params) {
  return request({
    url: '/user/batchremove',
    method: 'get',
    params: params
  })
}
export function editUser(params) {
  return request({
    url: '/healthsupervision/edit',
    method: 'get',
    params: params
  })
}
export function addUser(params) {
  return request({
    url: '/healthsupervision/add',
    method: 'get',
    params: params
  })
}
