package com.yihu.ehr.health.BasicFamilyInfor.dao;

import com.yihu.ehr.health.BasicFamilyInfor.entity.BasicFamilyInfor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface XBasicFamilyInforRepository  extends PagingAndSortingRepository<BasicFamilyInfor,String> {

}