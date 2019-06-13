import request from '@/utils/request'

const apipath = '/api/v1.0'

export function getList(params) {
  return request({
    url: apipath + '/severeMentalFollow/list',
    method: 'get',
    params: params
  })
}
export function removeData(params) {
  return request({
    url: apipath + '/severeMentalFollow/entiry/' + params,
    method: 'DELETE'
  })
}
export function batchRemoveData(params) {
  return request({
    url: apipath + '/severeMentalFollow/batchremove',
    method: 'get',
    params: params
  })
}
export function updateData(params) {
  return request({
    url: apipath + '/severeMentalFollow/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'put',
    data: params
  })
}
export function addData(params) {
  return request({
    url: apipath + '/severeMentalFollow/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'post',
    data: params
  })
}
export function checkData(params) {
  return request({
    url: apipath + '/severeMentalFollow/check',
    method: 'get',
    params: params
  })
}

