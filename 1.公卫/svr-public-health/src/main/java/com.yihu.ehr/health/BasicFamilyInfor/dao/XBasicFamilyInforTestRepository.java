package com.yihu.ehr.health.BasicFamilyInfor.dao;

import com.yihu.ehr.health.BasicFamilyInfor.entity.BasicFamilyInfor;
import com.yihu.ehr.health.BasicFamilyInfor.entity.BasicFamilyInforTest;
import com.yihu.ehr.health.user.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface XBasicFamilyInforTestRepository extends PagingAndSortingRepository<BasicFamilyInforTest,String> {

/*    @Query("select a.id,a.area,a.belong_community,b.disability from basic_family_infor a,perinfor b\n" +
            "where a.area = b.file_id")
    List<BasicFamilyInforTest> getselect();*/
}



