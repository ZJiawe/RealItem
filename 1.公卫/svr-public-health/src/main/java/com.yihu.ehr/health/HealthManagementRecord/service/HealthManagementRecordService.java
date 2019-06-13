package com.yihu.ehr.health.HealthManagementRecord.service;

import com.yihu.ehr.health.HealthManagementRecord.entity.*;
import com.yihu.ehr.health.HealthManagementRecord.dao.*;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HealthManagementRecordService extends BaseJpaService<HealthManagementRecord, XHealthManagementRecordRepository> {
    @Autowired
    XHealthManagementRecordRepository healthManagementEcordRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public HealthManagementRecord getHealthManagementRecord(String Id) {
        HealthManagementRecord healthManagementRecord = healthManagementEcordRepository.findOne(Id);
        return healthManagementRecord;
    }

    /**
     * 保存个人档案信息
     * @param healthManagementRecord 个人档案对象
     * @return
     */
    public HealthManagementRecord saveUser(HealthManagementRecord healthManagementRecord) {
        healthManagementRecord.setDeleteState("未删");
        healthManagementRecord = healthManagementEcordRepository.save(healthManagementRecord);
        return healthManagementRecord;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        HealthManagementRecord data = healthManagementEcordRepository.findOne(Id);
        data.setDeleteState("已删");
        healthManagementEcordRepository.save(data);
    }


}
