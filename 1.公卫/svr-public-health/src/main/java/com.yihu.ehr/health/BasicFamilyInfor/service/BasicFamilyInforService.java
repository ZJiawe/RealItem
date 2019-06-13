package com.yihu.ehr.health.BasicFamilyInfor.service;
import com.yihu.ehr.health.BasicFamilyInfor.dao.*;
import com.yihu.ehr.health.BasicFamilyInfor.entity.BasicFamilyInfor;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicFamilyInforService extends BaseJpaService<BasicFamilyInfor, XBasicFamilyInforRepository> {
    @Autowired
    XBasicFamilyInforRepository basicFamilyInforRepository;

    public BasicFamilyInfor getBasicFamilyInfor(String Id) {
        BasicFamilyInfor basicfamilyinfor = basicFamilyInforRepository.findOne(Id);
        return basicfamilyinfor;
    }


    public BasicFamilyInfor saveUser(BasicFamilyInfor basicfamilyinfor) {
        basicfamilyinfor.setDeleteState("未删");
        basicfamilyinfor = basicFamilyInforRepository.save(basicfamilyinfor);
        return basicfamilyinfor;
    }


    public void deleteUser(String Id) {

        //basicFamilyInforRepository.delete(Id);
        BasicFamilyInfor data = basicFamilyInforRepository.findOne(Id);
        data.setDeleteState("已删");
        basicFamilyInforRepository.save(data);

    }


    public void delete(String Id) {

       basicFamilyInforRepository.delete(Id);


    }

}
