import request from '@/utils/request'

const apipath = '/api/v1.0'

export function getList(params) {
  return request({
    url: apipath + '/threesixyearchild/list',
    method: 'get',
    params: params
  })
}
export function removeData(params) {
  return request({
    url: apipath + '/threesixyearchild/entiry/' + params,
    method: 'DELETE'
  })
}
export function batchRemoveData(params) {
  return request({
    url: apipath + '/threesixyearchild/batchremove',
    method: 'get',
    params: params
  })
}
export function editData(params) {
  return request({
    url: apipath + '/threesixyearchild/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'put',
    data: params

  })
}
export function addData(params) {
  return request({
    url: apipath + '/threesixyearchild/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'post',
    data: params
  })
}

export function printData(params) {
  return request({
    url: apipath + '/threesixyearchild/print',
    method: 'get',
    params: params
  })
}
