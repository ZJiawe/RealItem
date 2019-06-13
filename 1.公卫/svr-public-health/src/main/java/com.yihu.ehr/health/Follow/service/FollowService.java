package com.yihu.ehr.health.Follow.service;

import com.yihu.ehr.health.Follow.dao.FollowRepository;
import com.yihu.ehr.health.Follow.entity.FollowEntity;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FollowService extends BaseJpaService<FollowEntity, FollowRepository> {
    @Autowired
    FollowRepository followRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public FollowEntity getFollow(String Id) {
        FollowEntity follow = followRepository.findOne(Id);
        return follow;
    }

    /**
     * 保存个人档案信息
     * @param follow 个人档案对象
     * @return
     */
    public FollowEntity save(FollowEntity follow) {
        follow = followRepository.save(follow);
        return follow;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void delete(String Id) {
        FollowEntity data = followRepository.findOne(Id);
        followRepository.save(data);

    }


}
