package com.yihu.ehr.health.BasicFamilyInfor.dao;

import com.yihu.ehr.health.BasicFamilyInfor.entity.BasicFamilyInfor;
import com.yihu.ehr.health.BasicFamilyInfor.entity.BasicFamilyInforMember;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface XBasicFamilyInforMemberRepository extends PagingAndSortingRepository<BasicFamilyInforMember,String> {

}