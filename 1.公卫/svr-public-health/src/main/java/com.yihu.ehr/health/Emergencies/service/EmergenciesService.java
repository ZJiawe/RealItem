package com.yihu.ehr.health.Emergencies.service;

import com.yihu.ehr.health.Emergencies.dao.XEmergenciesRepository;
import com.yihu.ehr.health.Emergencies.entity.EmergenciesEntity;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmergenciesService extends BaseJpaService<EmergenciesEntity, XEmergenciesRepository> {
    @Autowired
    XEmergenciesRepository emergenciesRepository;

    /**
     * 根据ID获取传染病与突发公卫接口.
     *
     * @param Id 传染病与突发公卫id
     */
    public EmergenciesEntity getEmergencies(String Id) {
        EmergenciesEntity emergencies = emergenciesRepository.findOne(Id);
        return emergencies;
    }

    /**
     * 保存传染病与突发公卫信息
     * @param emergencies 传染病与突发公卫对象
     * @return
     */
    public EmergenciesEntity save(EmergenciesEntity emergencies) {
        emergencies.setDeleteState("未删");
        emergencies = emergenciesRepository.save(emergencies);
        return emergencies;
    }

    /**
     * 删除传染病与突发公卫
     * @param Id 传染病与突发公卫id
     */
    public void delete(String Id) {
        EmergenciesEntity data = emergenciesRepository.findOne(Id);
        data.setDeleteState("已删");
        emergenciesRepository.save(data);
    }


}
