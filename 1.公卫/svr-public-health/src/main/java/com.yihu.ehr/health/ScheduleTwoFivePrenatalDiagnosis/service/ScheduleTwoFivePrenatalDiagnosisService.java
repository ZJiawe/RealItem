package com.yihu.ehr.health.ScheduleTwoFivePrenatalDiagnosis.service;

import com.yihu.ehr.health.ScheduleTwoFivePrenatalDiagnosis.dao.*;
import com.yihu.ehr.health.ScheduleTwoFivePrenatalDiagnosis.entity.ScheduleTwoFivePrenatalDiagnosis;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScheduleTwoFivePrenatalDiagnosisService extends BaseJpaService<ScheduleTwoFivePrenatalDiagnosis, XScheduleTwoFivePrenatalDiagnosisRepository> {
    @Autowired
    XScheduleTwoFivePrenatalDiagnosisRepository scheduleTwoFivePrenatalDiagnosisRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public ScheduleTwoFivePrenatalDiagnosis getScheduleTwoFivePrenatalDiagnosis(String Id) {
        ScheduleTwoFivePrenatalDiagnosis scheduleTwoFivePrenatalDiagnosis = scheduleTwoFivePrenatalDiagnosisRepository.findOne(Id);
        return scheduleTwoFivePrenatalDiagnosis;
    }

    /**
     * 保存
     * @param scheduleTwoFivePrenatalDiagnosis
     * @return
     */
    public ScheduleTwoFivePrenatalDiagnosis saveUser(ScheduleTwoFivePrenatalDiagnosis scheduleTwoFivePrenatalDiagnosis) {
        scheduleTwoFivePrenatalDiagnosis.setDeleteState("未删");
        scheduleTwoFivePrenatalDiagnosis = scheduleTwoFivePrenatalDiagnosisRepository.save(scheduleTwoFivePrenatalDiagnosis);
        return scheduleTwoFivePrenatalDiagnosis;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        ScheduleTwoFivePrenatalDiagnosis scheduleTwoFivePrenatalDiagnosis = scheduleTwoFivePrenatalDiagnosisRepository.findOne(Id);
        scheduleTwoFivePrenatalDiagnosis.setDeleteState("已删");
        scheduleTwoFivePrenatalDiagnosisRepository.save(scheduleTwoFivePrenatalDiagnosis);
    }

}

