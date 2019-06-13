import request from '@/utils/request'

const apipath = '/api/v1.0'

export function getList(params) {
  return request({
    url: apipath + '/residentsStatistical/list',
    method: 'get',
    params: params
  })
}

