package com.yihu.ehr.health.SevereHealthPlanFollow.service;

import com.yihu.ehr.health.SevereHealthPlanFollow.dao.XSevereHealthPlanFollowRepository;
import com.yihu.ehr.health.SevereHealthPlanFollow.entity.*;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SevereHealthPlanFollowService extends BaseJpaService<SevereHealthPlanFollow, XSevereHealthPlanFollowRepository> {
    @Autowired
    XSevereHealthPlanFollowRepository severeHealthPlanFollowRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public SevereHealthPlanFollow getSevereHealthPlanFollow(String Id) {
        SevereHealthPlanFollow severeHealthPlanFollow = severeHealthPlanFollowRepository.findOne(Id);
        return severeHealthPlanFollow;
    }
    /**
     * 保存--
     * @param severeHealthPlanFollow 保存实体
     * @return
     */
    public SevereHealthPlanFollow saveEntity(SevereHealthPlanFollow severeHealthPlanFollow) {
        severeHealthPlanFollow = severeHealthPlanFollowRepository.save(severeHealthPlanFollow);
        return severeHealthPlanFollow;
    }
    /**
     * 保存个人档案信息
     * @param severeHealthPlanFollow 个人档案对象
     * @return
     */
    public SevereHealthPlanFollow saveUser(SevereHealthPlanFollow severeHealthPlanFollow) {
        severeHealthPlanFollow.setDeleteState("未删");
        severeHealthPlanFollow = severeHealthPlanFollowRepository.save(severeHealthPlanFollow);
        return severeHealthPlanFollow;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        SevereHealthPlanFollow data = severeHealthPlanFollowRepository.findOne(Id);
        data.setDeleteState("已删");
        severeHealthPlanFollowRepository.save(data);
    }


}
