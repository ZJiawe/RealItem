package com.yihu.ehr.health.FirsthomevisitRecord.service;

import com.yihu.ehr.health.FirsthomevisitRecord.dao.XFirsthomevisitRecordRepository;
import com.yihu.ehr.health.FirsthomevisitRecord.entity.FirsthomevisitRecordEntity;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FirsthomevisitRecordService extends BaseJpaService<FirsthomevisitRecordEntity, XFirsthomevisitRecordRepository> {
    @Autowired
    XFirsthomevisitRecordRepository FirsthomevisitRecordRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public FirsthomevisitRecordEntity getFirsthomevisitRecordEntity(String Id) {
        FirsthomevisitRecordEntity entity = FirsthomevisitRecordRepository.findOne(Id);
        return entity;
    }

    /**
     * 保存个人档案信息
     * @param entity 个人档案对象
     * @return
     */
    public FirsthomevisitRecordEntity saveUser(FirsthomevisitRecordEntity entity) {
        entity.setDeleteState("未删");
        entity = FirsthomevisitRecordRepository.save(entity);
        return entity;
    }
    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        FirsthomevisitRecordEntity  entity = FirsthomevisitRecordRepository.findOne(Id);
        entity.setDeleteState("已删");
        FirsthomevisitRecordRepository.save(entity);
    }
}
