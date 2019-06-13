package com.yihu.ehr.health.user.service;

import com.yihu.ehr.health.user.dao.*;
import com.yihu.ehr.health.user.entity.Users;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户管理接口实现类.
 *
 * @author zdm
 * @version 1.0
 * @updated 2018-09-04
 * 接口示例
 */

@Service
@Transactional
public class UserService extends BaseJpaService<Users, XUserRepository> {

    @Autowired
    private XUserRepository userRepository;


    /**
     * 根据ID获取用户接口.
     *
     * @param userId 用户id
     */
    public Users getUser(String userId) {
        Users user = userRepository.findOne(userId);
        return user;
    }

    /**
     * 保存用户信息
     * @param user 用户对象
     * @return
     */
    public Users saveUser(Users user) {
        user = userRepository.save(user);
        return user;
    }

    /**
     * 删除用户
     * @param userId 用户id
     */
    public void deleteUser(String userId) {
        userRepository.delete(userId);
    }

}