package com.yihu.ehr.health.SevereMentalFollow.service;

import com.yihu.ehr.health.SevereMentalFollow.entity.*;
import com.yihu.ehr.health.SevereMentalFollow.dao.*;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SevereMentalFollowService extends BaseJpaService< SevereMentalFollow, XSevereMentalFollowRepository> {
    @Autowired
    XSevereMentalFollowRepository severeMentalFollowRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public SevereMentalFollow getSevereMentalFollow(String Id) {
        SevereMentalFollow severeMentalFollow = severeMentalFollowRepository.findOne(Id);
        return severeMentalFollow;
    }

    /**
     * 保存个人档案信息
     * @param severeMentalFollow 个人档案对象
     * @return
     */
    public SevereMentalFollow saveUser(SevereMentalFollow severeMentalFollow) {
        severeMentalFollow.setDeleteState("未删");
        severeMentalFollow = severeMentalFollowRepository.save(severeMentalFollow);
        return severeMentalFollow;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        SevereMentalFollow data = severeMentalFollowRepository.findOne(Id);
        data.setDeleteState("已删");
        severeMentalFollowRepository.save(data);
    }


}
