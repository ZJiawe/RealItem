package com.yihu.ehr.health.HealthCheckups.dao;

import com.yihu.ehr.health.HealthCheckups.entity.HealthCheckups;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Peng
 * Time: 2016/12/16 18:07
 */
public interface XHealthCheckupsRepository extends PagingAndSortingRepository<HealthCheckups,String> {

}