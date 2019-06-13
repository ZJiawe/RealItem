
package com.yihu.ehr.health.ScheduleTwoFivePrenatalDiagnosis.dao;

import com.yihu.ehr.health.ScheduleTwoFivePrenatalDiagnosis.entity.ScheduleTwoFivePrenatalDiagnosis;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface XScheduleTwoFivePrenatalDiagnosisRepository extends PagingAndSortingRepository<ScheduleTwoFivePrenatalDiagnosis,String> {

}