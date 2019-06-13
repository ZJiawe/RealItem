package com.yihu.ehr.health.Transtreatin.service;
import com.yihu.ehr.health.Transtreat.entity.Transtreat;
import com.yihu.ehr.health.Transtreatin.dao.*;
import com.yihu.ehr.health.Transtreatin.entity.Transtreatin;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TranstreatinService extends BaseJpaService<Transtreatin, XTranstreatinRepository> {
    @Autowired
    XTranstreatinRepository transtreatinRepository;


    public Transtreatin getTranstreatin(String Id) {
        Transtreatin transtreatin = transtreatinRepository.findOne(Id);
        return transtreatin;
    }


    public Transtreatin saveUser(Transtreatin transtreatin) {
        transtreatin = transtreatinRepository.save(transtreatin);
        transtreatin.setDeleteState("未删");
        return transtreatin;
    }

    public void deleteUser(String Id) {
        Transtreatin data = transtreatinRepository.findOne(Id);
        data.setDeleteState("已删");
        transtreatinRepository.save(data);
    }


}
