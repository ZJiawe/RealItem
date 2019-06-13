package com.yihu.ehr.health.HealthCheckups.service;

import com.yihu.ehr.health.HealthCheckups.dao.XHealthCheckupsRepository;
import com.yihu.ehr.health.HealthCheckups.entity.HealthCheckups;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HealthCheckupsService extends BaseJpaService<HealthCheckups, XHealthCheckupsRepository> {
    @Autowired
    XHealthCheckupsRepository healthCheckupsRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public HealthCheckups getHealthCheckups(String Id) {
        HealthCheckups entity = healthCheckupsRepository.findOne(Id);
        return entity;
    }

    /**
     * 保存个人档案信息
     * @param entity 个人档案对象
     * @return
     */
    public HealthCheckups saveUser(HealthCheckups entity) {
        entity.setDeleteState("未删");
        entity = healthCheckupsRepository.save(entity);
        return entity;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        HealthCheckups entity = healthCheckupsRepository.findOne(Id);
        entity.setDeleteState("已删");
        healthCheckupsRepository.save(entity);
    }

}
