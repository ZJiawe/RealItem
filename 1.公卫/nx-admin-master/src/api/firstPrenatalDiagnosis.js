import request from '@/utils/request'

const apipath = '/api/v1.0'

export function getList(params) {
  return request({
    url: apipath + '/firstPrenatalDiagnosis/list',
    method: 'get',
    params: params
  })
}
export function removeData(params) {
  return request({
    url: apipath + '/firstPrenatalDiagnosis/entiry/' + params,
    method: 'DELETE'
  })
}
export function batchRemoveData(params) {
  return request({
    url: apipath + '/firstPrenatalDiagnosis/batchremove',
    method: 'get',
    params: params
  })
}
export function updateData(params) {
  return request({
    url: apipath + '/firstPrenatalDiagnosis/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'put',
    data: params
  })
}
export function addData(params) {
  return request({
    url: apipath + '/firstPrenatalDiagnosis/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'post',
    data: params
  })
}
export function checkData(params) {
  return request({
    url: apipath + '/firstPrenatalDiagnosis/check',
    method: 'get',
    params: params
  })
}

