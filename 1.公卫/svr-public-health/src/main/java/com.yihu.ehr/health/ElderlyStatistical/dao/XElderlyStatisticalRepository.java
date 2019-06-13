package com.yihu.ehr.health.ElderlyStatistical.dao;


import com.yihu.ehr.health.ElderlyStatistical.entity.ElderlyStatistical;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface XElderlyStatisticalRepository extends PagingAndSortingRepository<ElderlyStatistical,String> {


}
