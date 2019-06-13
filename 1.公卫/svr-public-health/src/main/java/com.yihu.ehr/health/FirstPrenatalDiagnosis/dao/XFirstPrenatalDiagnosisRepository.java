package com.yihu.ehr.health.FirstPrenatalDiagnosis.dao;

import com.yihu.ehr.health.FirstPrenatalDiagnosis.entity.FirstPrenatalDiagnosis;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface XFirstPrenatalDiagnosisRepository  extends PagingAndSortingRepository<FirstPrenatalDiagnosis,String> {

}