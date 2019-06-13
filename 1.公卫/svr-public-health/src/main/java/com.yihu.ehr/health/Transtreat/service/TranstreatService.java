package com.yihu.ehr.health.Transtreat.service;
import com.yihu.ehr.health.Transtreat.dao.*;
import com.yihu.ehr.health.Transtreat.entity.Transtreat;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TranstreatService extends BaseJpaService<Transtreat, XTranstreatRepository> {
    @Autowired
    XTranstreatRepository transtreatRepository;

    public Transtreat getTranstreat(String Id) {
        Transtreat transtreat = transtreatRepository.findOne(Id);
        return transtreat;
    }

    public Transtreat saveUser(Transtreat transtreat) {
        transtreat = transtreatRepository.save(transtreat);
        transtreat.setDeleteState("未删");
        return transtreat;
    }

    public void deleteUser(String Id) {
        Transtreat data = transtreatRepository.findOne(Id);
        data.setDeleteState("已删");
        transtreatRepository.save(data);

    }


}
