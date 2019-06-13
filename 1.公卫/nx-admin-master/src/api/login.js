import request from '@/utils/request'

const apipath = '/api/v1.0'

export function login(username, password) {
  return request({
    url: apipath + '/login',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: 'username=' + username + '&' + 'password=' + password
  })
}

export function getInfo(token) {
  return request({
    url: apipath + '/login/info',
    method: 'post',
    params: { token }
  })
}

export function getMenu(token, userId) {
  return request({
    url: apipath + '/login/menu',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: 'token=' + token + '&' + 'user_id=' + userId
  })
}

export function logout() {
  return request({
    url: apipath + '/login/logout',
    method: 'post'
  })
}

export function hello() {
  return request({
    url: apipath + '/springboot',
    method: 'get'
  })
}
