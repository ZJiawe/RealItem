package com.yihu.ehr.health.FirstPrenatalDiagnosis.service;
import com.yihu.ehr.health.FirstPrenatalDiagnosis.dao.*;
import com.yihu.ehr.health.FirstPrenatalDiagnosis.entity.FirstPrenatalDiagnosis;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FirstPrenatalDiagnosisService extends BaseJpaService<FirstPrenatalDiagnosis, XFirstPrenatalDiagnosisRepository> {
    @Autowired
    XFirstPrenatalDiagnosisRepository firstPrenatalDiagnosisRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public FirstPrenatalDiagnosis getFirstPrenatalDiagnosis(String Id) {
        FirstPrenatalDiagnosis firstPrenatalDiagnosis = firstPrenatalDiagnosisRepository.findOne(Id);
        return firstPrenatalDiagnosis;
    }

    /**
     * 保存
     * @param firstPrenatalDiagnosis
     * @return
     */
    public FirstPrenatalDiagnosis saveUser(FirstPrenatalDiagnosis firstPrenatalDiagnosis) {
        firstPrenatalDiagnosis.setDeleteState("未删");
        firstPrenatalDiagnosis = firstPrenatalDiagnosisRepository.save(firstPrenatalDiagnosis);
        return firstPrenatalDiagnosis;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        FirstPrenatalDiagnosis firstPrenatalDiagnosis = firstPrenatalDiagnosisRepository.findOne(Id);
        firstPrenatalDiagnosis.setDeleteState("已删");
        firstPrenatalDiagnosisRepository.save(firstPrenatalDiagnosis);
    }

}
