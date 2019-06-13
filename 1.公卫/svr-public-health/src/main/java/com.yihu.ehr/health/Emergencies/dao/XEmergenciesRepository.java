package com.yihu.ehr.health.Emergencies.dao;

import com.yihu.ehr.health.Emergencies.entity.EmergenciesEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Peng
 * Time: 2016/12/16 18:07
 */
public interface XEmergenciesRepository extends PagingAndSortingRepository<EmergenciesEntity,String> {

}