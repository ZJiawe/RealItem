package com.yihu.ehr.health.Followupservicerecord.service;

import com.yihu.ehr.health.Followupservicerecord.dao.XFollowupserviceRepository;
import com.yihu.ehr.health.Followupservicerecord.entity.FollowUpServiceRecordEntity;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FollowUpServiceRecordService extends BaseJpaService<FollowUpServiceRecordEntity, XFollowupserviceRepository> {
    @Autowired
    XFollowupserviceRepository FollowupserviceRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public FollowUpServiceRecordEntity getFollowUpServiceRecordEntity(String Id) {
        FollowUpServiceRecordEntity entity = FollowupserviceRepository.findOne(Id);
        return entity;
    }

    /**
     * 保存个人档案信息
     * @param entity 个人档案对象
     * @return
     */
    public FollowUpServiceRecordEntity saveUser(FollowUpServiceRecordEntity entity) {
        entity.setDeleteState("未删");
        entity = FollowupserviceRepository.save(entity);
        return entity;
    }
    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        FollowUpServiceRecordEntity  entity = FollowupserviceRepository.findOne(Id);
        entity.setDeleteState("已删");
        FollowupserviceRepository.save(entity);
    }
}
