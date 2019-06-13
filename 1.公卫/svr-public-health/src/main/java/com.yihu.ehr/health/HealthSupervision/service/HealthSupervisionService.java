package com.yihu.ehr.health.HealthSupervision.service;

import com.yihu.ehr.health.HealthSupervision.dao.XHealthSupervisionRepository;
import com.yihu.ehr.health.HealthSupervision.entity.HealthSupervisionEntity;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HealthSupervisionService extends BaseJpaService<HealthSupervisionEntity, XHealthSupervisionRepository> {
    @Autowired
    XHealthSupervisionRepository healthSupervisionRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public HealthSupervisionEntity getHealthSupervision(String Id) {
        HealthSupervisionEntity healthSupervision = healthSupervisionRepository.findOne(Id);
        return healthSupervision;
    }

    /**
     * 保存个人档案信息
     * @param healthsupervision 个人档案对象
     * @return
     */
    public HealthSupervisionEntity save(HealthSupervisionEntity healthsupervision) {
        healthsupervision.setDeleteState("未删");
        healthsupervision = healthSupervisionRepository.save(healthsupervision);
        return healthsupervision;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void delete(String Id) {
        HealthSupervisionEntity data = healthSupervisionRepository.findOne(Id);
        data.setDeleteState("已删");
        healthSupervisionRepository.save(data);

    }


}
