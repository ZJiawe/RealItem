package com.yihu.ehr.health.Transtreatlog.service;
import com.yihu.ehr.health.Transtreatlog.dao.*;
import com.yihu.ehr.health.Transtreatlog.entity.Transtreatlog;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TranstreatlogService extends BaseJpaService<Transtreatlog, XTranstreatlogRepository> {
    @Autowired
    XTranstreatlogRepository transtreatlogRepository;

    public Transtreatlog getTranstreatlog(String Id) {
        Transtreatlog transtreatlog = transtreatlogRepository.findOne(Id);
        return transtreatlog;
    }


    public Transtreatlog saveUser(Transtreatlog transtreatlog) {
        transtreatlog = transtreatlogRepository.save(transtreatlog);
        transtreatlog.setDeleteState("未删");
        return transtreatlog;
    }

    public void deleteUser(String Id) {
        // transtreatlogRepository.delete(Id);
        Transtreatlog data = transtreatlogRepository.findOne(Id);
        data.setDeleteState("已删");
        transtreatlogRepository.save(data);

    }


}
