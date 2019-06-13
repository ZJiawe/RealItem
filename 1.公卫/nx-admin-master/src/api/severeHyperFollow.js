import request from '@/utils/request'

const apipath = '/api/v1.0'

export function checkData(params) {
  return request({
    url: apipath + '/SevereHyperFollow/check',
    method: 'get',
    params: params
  })
}
export function getList(params) {
  return request({
    url: apipath + '/SevereHyperFollow/list',
    method: 'get',
    params: params
  })
}
export function removeData(params) {
  return request({
    url: apipath + '/SevereHyperFollow/entiry/' + params,
    method: 'DELETE'
  })
}
export function batchRemoveData(params) {
  return request({
    url: apipath + '/SevereHyperFollow/batchremove',
    method: 'get',
    params: params
  })
}
export function editData(params) {
  return request({
    url: apipath + '/SevereHyperFollow/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'put',
    data: params
  })
}
export function addData(params) {
  return request({
    url: apipath + '/SevereHyperFollow/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'post',
    data: params
  })
}
export function print(params) {
  return request({
    url: apipath + '/SevereHyperFollow/print',
    method: 'get',
    params: params
  })
}
