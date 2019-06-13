package com.yihu.ehr.health.ResidentManagement.service;

import com.yihu.ehr.health.ResidentManagement.entity.*;
import com.yihu.ehr.health.ResidentManagement.dao.*;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResidentManagementService extends BaseJpaService<ResidentManagement, XResidentManagementRepository> {
    @Autowired
    XResidentManagementRepository residentManagementRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public ResidentManagement getResidentManagement(String Id) {
        ResidentManagement residentManagement = residentManagementRepository.findOne(Id);
        return residentManagement;
    }

    /**
     * 保存个人档案信息
     * @param residentManagement 个人档案对象
     * @return
     */
    public ResidentManagement saveUser(ResidentManagement residentManagement) {
        residentManagement.setDeleteState("未删");
        residentManagement = residentManagementRepository.save(residentManagement);
        return residentManagement;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        ResidentManagement data = residentManagementRepository.findOne(Id);
        data.setDeleteState("已删");
        residentManagementRepository.save(data);
    }
}
