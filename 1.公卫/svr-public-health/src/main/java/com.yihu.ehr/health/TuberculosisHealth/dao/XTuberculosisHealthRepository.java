package com.yihu.ehr.health.TuberculosisHealth.dao;

import com.yihu.ehr.health.FirsthomevisitRecord.entity.FirsthomevisitRecordEntity;
import com.yihu.ehr.health.TuberculosisHealth.entity.TuberculosisHealthEntity;
import com.yihu.ehr.health.user.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Peng
 * Time: 2016/12/16 18:07
 */
public interface XTuberculosisHealthRepository extends PagingAndSortingRepository<TuberculosisHealthEntity,String> {

    @Query("select tuberculosisHealthEntity from TuberculosisHealthEntity tuberculosisHealthEntity where tuberculosisHealthEntity.area = :area or tuberculosisHealthEntity.year = :year")  //添加搜索条件
    TuberculosisHealthEntity findByareaAndYear(@Param("area") String area, @Param("year") Integer year);

}