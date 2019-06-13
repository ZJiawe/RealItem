import request from '@/utils/request'
const apipath = '/api/v1.0'
export function getList(params) {
  return request({
    url: apipath + '/searchrdoctor/list',
    method: 'get',
    params: params
  })
}

