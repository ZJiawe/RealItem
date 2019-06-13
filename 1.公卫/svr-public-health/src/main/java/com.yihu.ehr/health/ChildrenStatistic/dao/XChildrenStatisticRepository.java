package com.yihu.ehr.health.ChildrenStatistic.dao;

import com.yihu.ehr.health.ChildrenStatistic.entity.ChildrenStatistics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Peng
 * Time: 2016/12/16 18:07
 */
public interface XChildrenStatisticRepository extends PagingAndSortingRepository<ChildrenStatistics,String> {


}