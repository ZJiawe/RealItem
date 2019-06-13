package com.yihu.ehr.health.MaternalStatistical.dao;

import com.yihu.ehr.health.MaternalManagement.entity.MaternalManagement;
import com.yihu.ehr.health.MaternalStatistical.entity.MaternalStatistical;
import com.yihu.ehr.health.user.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface XMaternalStatisticalRepository extends PagingAndSortingRepository<MaternalStatistical,String>{

    @Query("select maternalStatistical from MaternalStatistical maternalStatistical where maternalStatistical.area = :area or maternalStatistical.year = :year")  //添加搜索条件
    MaternalStatistical findByareaAndYear(@Param("area") String area);
}
