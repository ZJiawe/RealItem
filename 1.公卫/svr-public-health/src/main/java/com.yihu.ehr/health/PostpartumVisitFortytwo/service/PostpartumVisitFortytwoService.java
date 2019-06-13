package com.yihu.ehr.health.PostpartumVisitFortytwo.service;

import com.yihu.ehr.health.PostpartumVisitFortytwo.dao.*;
import com.yihu.ehr.health.PostpartumVisitFortytwo.entity.PostpartumVisitFortytwo;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostpartumVisitFortytwoService extends BaseJpaService<PostpartumVisitFortytwo, XPostpartumVisitFortytwoRepository> {
    @Autowired
    XPostpartumVisitFortytwoRepository postpartumVisitFortytwoRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public PostpartumVisitFortytwo getPostpartumVisitFortytwo(String Id) {
        PostpartumVisitFortytwo postpartumVisitFortytwo = postpartumVisitFortytwoRepository.findOne(Id);
        return postpartumVisitFortytwo;
    }

    /**
     * 保存
     * @param postpartumVisitFortytwo
     * @return
     */
    public PostpartumVisitFortytwo saveUser(PostpartumVisitFortytwo postpartumVisitFortytwo) {
        postpartumVisitFortytwo.setDeleteState("未删");
        postpartumVisitFortytwo = postpartumVisitFortytwoRepository.save(postpartumVisitFortytwo);
        return postpartumVisitFortytwo;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        PostpartumVisitFortytwo postpartumVisitFortytwo = postpartumVisitFortytwoRepository.findOne(Id);
        postpartumVisitFortytwo.setDeleteState("已删");
        postpartumVisitFortytwoRepository.save(postpartumVisitFortytwo);
    }

}

