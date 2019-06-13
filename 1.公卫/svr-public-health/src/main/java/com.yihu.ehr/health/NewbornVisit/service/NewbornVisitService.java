package com.yihu.ehr.health.NewbornVisit.service;

import com.yihu.ehr.health.NewbornVisit.dao.XNewbornVisitRepository;
import com.yihu.ehr.health.NewbornVisit.entity.NewbornVisit;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NewbornVisitService extends BaseJpaService<NewbornVisit, XNewbornVisitRepository> {
    @Autowired
    XNewbornVisitRepository newbornVisitRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public NewbornVisit getNewbornVisit(String Id) {
        NewbornVisit entity = newbornVisitRepository.findOne(Id);
        return entity;
    }

    /**
     * 保存个人档案信息
     * @param entity 个人档案对象
     * @return
     */
    public NewbornVisit saveUser(NewbornVisit entity) {
        entity.setDeleteState("未删");
        entity = newbornVisitRepository.save(entity);
        return entity;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        NewbornVisit entity = newbornVisitRepository.findOne(Id);
        entity.setDeleteState("已删");
        newbornVisitRepository.save(entity);
    }

}
