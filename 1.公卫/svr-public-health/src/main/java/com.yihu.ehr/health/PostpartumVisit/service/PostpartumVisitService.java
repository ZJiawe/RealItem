package com.yihu.ehr.health.PostpartumVisit.service;


import com.yihu.ehr.health.PostpartumVisit.dao.*;
import com.yihu.ehr.health.PostpartumVisit.entity.PostpartumVisit;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostpartumVisitService extends BaseJpaService<PostpartumVisit, XPostpartumVisitRepository> {
    @Autowired
    XPostpartumVisitRepository postpartumVisitRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public PostpartumVisit getPostpartumVisit(String Id) {
        PostpartumVisit postpartumVisit = postpartumVisitRepository.findOne(Id);
        return postpartumVisit;
    }

    /**
     * 保存
     * @param postpartumVisit
     * @return
     */
    public PostpartumVisit saveUser(PostpartumVisit postpartumVisit) {
        postpartumVisit.setDeleteState("未删");
        postpartumVisit = postpartumVisitRepository.save(postpartumVisit);
        return postpartumVisit;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        PostpartumVisit postpartumVisit = postpartumVisitRepository.findOne(Id);
        postpartumVisit.setDeleteState("已删");
        postpartumVisitRepository.save(postpartumVisit);
    }

}

