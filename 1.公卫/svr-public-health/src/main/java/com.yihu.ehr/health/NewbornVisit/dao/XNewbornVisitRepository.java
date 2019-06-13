package com.yihu.ehr.health.NewbornVisit.dao;

import com.yihu.ehr.health.NewbornVisit.entity.NewbornVisit;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Peng
 * Time: 2016/12/16 18:07
 */
public interface XNewbornVisitRepository extends PagingAndSortingRepository<NewbornVisit,String> {

}