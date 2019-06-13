package com.yihu.ehr.health.ResidentsStatistical.dao;
import com.yihu.ehr.health.ResidentsStatistical.entity.ResidentsStatistical;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface XResidentsStatisticalRepository extends PagingAndSortingRepository<ResidentsStatistical,String>{

    @Query("select residentsStatistical from ResidentsStatistical residentsStatistical where residentsStatistical.area = :area or residentsStatistical.year = :year")  //添加搜索条件
    ResidentsStatistical findByareaAndYear(@Param("area") String area, @Param("year") Integer year);
}