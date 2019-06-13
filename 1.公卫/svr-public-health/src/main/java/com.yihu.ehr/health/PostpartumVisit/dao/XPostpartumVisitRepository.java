package com.yihu.ehr.health.PostpartumVisit.dao;

import com.yihu.ehr.health.PostpartumVisit.entity.PostpartumVisit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface XPostpartumVisitRepository  extends PagingAndSortingRepository<PostpartumVisit,String> {

}