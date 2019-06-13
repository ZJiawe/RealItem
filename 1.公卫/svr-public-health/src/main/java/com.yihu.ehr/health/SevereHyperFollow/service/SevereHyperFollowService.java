package com.yihu.ehr.health.SevereHyperFollow.service;

import com.yihu.ehr.health.SevereHyperFollow.dao.*;
import com.yihu.ehr.health.SevereHyperFollow.entity.SevereHyperFollow;
import com.yihu.ehr.health.SevereHyperSumFollow.controller.SevereHyperSumFollowController;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SevereHyperFollowService extends BaseJpaService<SevereHyperFollow, SevereHyperFollowRepository> {
    @Autowired
    SevereHyperFollowRepository severeHyperFollowRepository;
    @Autowired
    SevereHyperSumFollowController severeHyperSumFollowController;
    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public SevereHyperFollow getSevereHyperFollow(String Id) {
        SevereHyperFollow severeHyperFollow = severeHyperFollowRepository.findOne(Id);
        return severeHyperFollow;
    }
    /**
     * 保存--
     * @param severeHyperFollow 保存实体
     * @return
     */
    public SevereHyperFollow saveEntity(SevereHyperFollow severeHyperFollow) {
        severeHyperFollow = severeHyperFollowRepository.save(severeHyperFollow);
        return severeHyperFollow;
    }

    /**
     * 保存个人档案信息
     * @param severeHyperFollow 个人档案对象
     * @return
     */
    public SevereHyperFollow saveUser(SevereHyperFollow severeHyperFollow) {

        severeHyperFollow.setDeleteState("未删");
        severeHyperFollow = severeHyperFollowRepository.save(severeHyperFollow);
        return severeHyperFollow;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        SevereHyperFollow data = severeHyperFollowRepository.findOne(Id);
        data.setDeleteState("已删");
        severeHyperFollowRepository.save(data);
    }


}
