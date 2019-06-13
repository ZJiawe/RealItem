import request from '@/utils/request'

const apipath = '/api/v1.0'

export function getList(params) {
  return request({
    url: apipath + '/scheduleTwoFivePrenatalDiagnosis/list',
    method: 'get',
    params: params
  })
}
export function removeData(params) {
  return request({
    url: apipath + '/scheduleTwoFivePrenatalDiagnosis/entiry/' + params,
    method: 'DELETE'
  })
}
export function batchRemoveData(params) {
  return request({
    url: apipath + '/scheduleTwoFivePrenatalDiagnosis/batchremove',
    method: 'get',
    params: params
  })
}
export function updateData(params) {
  return request({
    url: apipath + '/scheduleTwoFivePrenatalDiagnosis/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'put',
    data: params
  })
}
export function addData(params) {
  return request({
    url: apipath + '/scheduleTwoFivePrenatalDiagnosis/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'post',
    data: params
  })
}
export function printData(params) {
  return request({
    url: apipath + '/scheduleTwoFivePrenatalDiagnosis/print',
    method: 'get',
    params: params
  })
}

