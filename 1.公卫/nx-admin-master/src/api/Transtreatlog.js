import request from '@/utils/request'

const apipath = '/api/v1.0'

export function getList2(params) {
  return request({
    url: apipath + '/transtreatlog/list',
    method: 'get',
    params: params
  })
}
export function removeData2(params) {
  return request({
    url: apipath + '/transtreatlog/entiry/' + params,
    method: 'DELETE'
  })
}
export function batchRemoveData2(params) {
  return request({
    url: apipath + '/transtreatlog/batchremove',
    method: 'get',
    params: params
  })
}
export function updateData2(params) {
  return request({
    url: apipath + '/transtreatlog/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'put',
    data: params
  })
}
export function addData2(params) {
  return request({
    url: apipath + '/transtreatlog/list',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    method: 'post',
    data: params
  })
}
