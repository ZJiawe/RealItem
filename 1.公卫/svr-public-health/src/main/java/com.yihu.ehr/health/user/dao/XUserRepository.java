package com.yihu.ehr.health.user.dao;

import com.yihu.ehr.health.user.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author zdm
 * @version 1.0
 * @created 2018-09-04
 */

public interface XUserRepository extends PagingAndSortingRepository<Users, String> {




    @Query("select user from Users user where user.loginCode = :loginCode or user.telephone = :loginCode")
    List<Users> findUserForLogin(@Param("loginCode") String loginCode);

}
