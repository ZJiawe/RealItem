package com.yihu.ehr.health.PostpartumVisitFortytwo.dao;

import com.yihu.ehr.health.PostpartumVisitFortytwo.entity.PostpartumVisitFortytwo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface XPostpartumVisitFortytwoRepository extends PagingAndSortingRepository<PostpartumVisitFortytwo,String> {

}