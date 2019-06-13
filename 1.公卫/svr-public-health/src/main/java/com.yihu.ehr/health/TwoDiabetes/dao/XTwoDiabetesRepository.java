package com.yihu.ehr.health.TwoDiabetes.dao;

import com.yihu.ehr.health.TwoDiabetes.entity.TwoDiabetesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Peng
 * Time: 2016/12/16 18:07
 */
public interface XTwoDiabetesRepository extends PagingAndSortingRepository<TwoDiabetesEntity,String> {

}
