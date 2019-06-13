import request from '@/utils/request'

const apipath = '/api/v1.0'

export function getList(params) {
  return request({
    url: apipath + '/followupservicerecord/list',
    method: 'get',
    params: params
  })
}
export function removeData(params) {
  return request({
    url: apipath + '/followupservicerecord/entiry/' + params,
    method: 'DELETE'
  })
}
export function batchRemoveUser(params) {
  return request({
    url: apipath + '/followupservicerecord/batchremove',
    method: 'get',
    params: params
  })
}
export function updateData(params) {
  return request({
    url: apipath + '/followupservicerecord/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'put',
    data: params
  })
}
export function addData(params) {
  return request({
    url: apipath + '/followupservicerecord/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'post',
    data: params
  })
}

export function printData(params) {
  return request({
    url: apipath + '/followupservicerecord/print',
    method: 'get',
    params: params
  })
}
