package com.yihu.ehr.health.BasicFamilyInfor.service;

import com.yihu.ehr.health.BasicFamilyInfor.dao.XBasicFamilyInforMemberRepository;
import com.yihu.ehr.health.BasicFamilyInfor.dao.XSearchRdoctorRepository;
import com.yihu.ehr.health.BasicFamilyInfor.entity.BasicFamilyInfor;
import com.yihu.ehr.health.BasicFamilyInfor.entity.BasicFamilyInforMember;
import com.yihu.ehr.health.BasicFamilyInfor.entity.SearchRdoctor;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SearchRdoctorService extends BaseJpaService<SearchRdoctor, XSearchRdoctorRepository> {
    @Autowired
    XSearchRdoctorRepository searchRdoctorRepository;

    public SearchRdoctor getSearchRdoctor(String Id) {
        SearchRdoctor searchRdoctor = searchRdoctorRepository.findOne(Id);
        return searchRdoctor;
    }

    public SearchRdoctor saveUser(SearchRdoctor searchRdoctor) {
        searchRdoctor = searchRdoctorRepository.save(searchRdoctor);
        return searchRdoctor;
    }
}
