package com.yihu.ehr.health.Transtreatset.service;
import com.yihu.ehr.health.Transtreatin.entity.Transtreatin;
import com.yihu.ehr.health.Transtreatset.dao.*;
import com.yihu.ehr.health.Transtreatset.entity.Transtreatset;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TranstreatsetService extends BaseJpaService<Transtreatset, XTranstreatsetRepository> {
    @Autowired
    XTranstreatsetRepository transtreatsetRepository;

    public Transtreatset getTranstreatset(String Id) {
        Transtreatset transtreatset = transtreatsetRepository.findOne(Id);
        return transtreatset;
    }

    public Transtreatset saveUser(Transtreatset transtreatset) {
        transtreatset.setDeleteState("未删");
        transtreatset = transtreatsetRepository.save(transtreatset);
        return transtreatset;
    }

    public void deleteUser(String Id) {
        Transtreatset data = transtreatsetRepository.findOne(Id);
        data.setDeleteState("已删");
        transtreatsetRepository.save(data);
    }


}
