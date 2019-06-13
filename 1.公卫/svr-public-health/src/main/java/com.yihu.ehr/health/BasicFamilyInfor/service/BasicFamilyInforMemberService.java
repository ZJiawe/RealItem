package com.yihu.ehr.health.BasicFamilyInfor.service;

import com.yihu.ehr.health.BasicFamilyInfor.dao.XBasicFamilyInforMemberRepository;
import com.yihu.ehr.health.BasicFamilyInfor.dao.XBasicFamilyInforRepository;
import com.yihu.ehr.health.BasicFamilyInfor.entity.BasicFamilyInfor;
import com.yihu.ehr.health.BasicFamilyInfor.entity.BasicFamilyInforMember;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicFamilyInforMemberService extends BaseJpaService<BasicFamilyInforMember, XBasicFamilyInforMemberRepository> {
    @Autowired
    XBasicFamilyInforMemberRepository basicFamilyInforMemberRepository;

    public BasicFamilyInforMember getBasicFamilyInforMember(String Id) {
        BasicFamilyInforMember basicfamilyinfor = basicFamilyInforMemberRepository.findOne(Id);
        return basicfamilyinfor;
    }

    public BasicFamilyInforMember saveUser(BasicFamilyInforMember basicfamilyinfor) {
        basicfamilyinfor = basicFamilyInforMemberRepository.save(basicfamilyinfor);
        return basicfamilyinfor;
    }
    public void deleteUser(String Id) {
        basicFamilyInforMemberRepository.delete(Id);
    }


}
