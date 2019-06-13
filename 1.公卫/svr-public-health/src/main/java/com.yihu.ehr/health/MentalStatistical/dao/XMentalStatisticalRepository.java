package com.yihu.ehr.health.MentalStatistical.dao;

import com.yihu.ehr.health.MentalStatistical.entity.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Peng
 * Time: 2016/12/16 18:07
 */
public interface XMentalStatisticalRepository extends PagingAndSortingRepository<MentalStatistical,String> {
    @Query("select mentalStatistical from MentalStatistical mentalStatistical where mentalStatistical.area = :area or mentalStatistical.year = :year")  //添加搜索条件
    MentalStatistical findByareaAndYear(@Param("area") String area, @Param("year") Integer year);
}