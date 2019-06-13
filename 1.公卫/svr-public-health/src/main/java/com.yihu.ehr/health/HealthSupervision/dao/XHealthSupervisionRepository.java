package com.yihu.ehr.health.HealthSupervision.dao;

import com.yihu.ehr.health.HealthSupervision.entity.HealthSupervisionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Peng
 * Time: 2016/12/16 18:07
 */
public interface XHealthSupervisionRepository extends PagingAndSortingRepository<HealthSupervisionEntity,String> {

}