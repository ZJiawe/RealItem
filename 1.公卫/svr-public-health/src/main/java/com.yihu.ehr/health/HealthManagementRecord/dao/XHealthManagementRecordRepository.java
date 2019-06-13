package com.yihu.ehr.health.HealthManagementRecord.dao;

import com.yihu.ehr.health.HealthManagementRecord.entity.*;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface XHealthManagementRecordRepository extends PagingAndSortingRepository<HealthManagementRecord,String> {

}
