package com.yihu.ehr.health.ThreeSixYearChild.service;

import com.yihu.ehr.health.ThreeSixYearChild.dao.XThreeSixYearChildRepository;
import com.yihu.ehr.health.ThreeSixYearChild.entity.ThreeSixYearChild;
import com.yihu.ehr.health.ThreeSixYearChild.dao.XThreeSixYearChildRepository;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ThreeSixYearChildService extends BaseJpaService<ThreeSixYearChild, XThreeSixYearChildRepository> {
    @Autowired
    XThreeSixYearChildRepository threeSixYearChildRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public ThreeSixYearChild getThreeSixYearChild(String Id) {
        ThreeSixYearChild entity = threeSixYearChildRepository.findOne(Id);
        return entity;
    }

    /**
     * 保存个人档案信息
     * @param entity 个人档案对象
     * @return
     */
    public ThreeSixYearChild saveUser(ThreeSixYearChild entity) {
        entity.setDeleteState("未删");
        entity = threeSixYearChildRepository.save(entity);
        return entity;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        ThreeSixYearChild entity = threeSixYearChildRepository.findOne(Id);
        entity.setDeleteState("已删");
        threeSixYearChildRepository.save(entity);
    }

}
