package com.yihu.ehr.health.BasicFamilyInfor.dao;

import com.yihu.ehr.health.BasicFamilyInfor.entity.BasicFamilyInforMember;
import com.yihu.ehr.health.BasicFamilyInfor.entity.SearchRdoctor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface XSearchRdoctorRepository  extends PagingAndSortingRepository<SearchRdoctor,String> {
}
