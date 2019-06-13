package com.yihu.ehr.health.SelfcareAbility.service;

import com.yihu.ehr.health.SelfcareAbility.entity.*;
import com.yihu.ehr.health.SelfcareAbility.dao.*;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SelfcareAbilityService extends BaseJpaService< SelfcareAbility, XSelfcareAbilityRepository> {
    @Autowired
    XSelfcareAbilityRepository selfcareAbilityRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public SelfcareAbility getSelfcareAbility(String Id) {
        SelfcareAbility selfcareAbility = selfcareAbilityRepository.findOne(Id);
        return selfcareAbility;
    }

    /**是
     * 保存个人档案信息
     * @param selfcareAbility 个人档案对象
     * @return
     */
    public SelfcareAbility saveUser(SelfcareAbility selfcareAbility) {
        selfcareAbility.setDeleteState("未删");
        selfcareAbility = selfcareAbilityRepository.save(selfcareAbility);
        return selfcareAbility;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        SelfcareAbility data = selfcareAbilityRepository.findOne(Id);
        data.setDeleteState("已删");
        selfcareAbilityRepository.save(data);
    }


}

