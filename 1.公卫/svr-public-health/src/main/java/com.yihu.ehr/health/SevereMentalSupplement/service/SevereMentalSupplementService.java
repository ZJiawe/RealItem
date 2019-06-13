package com.yihu.ehr.health.SevereMentalSupplement.service;

import com.yihu.ehr.health.MentalStatistical.controller.MentalStatisticalController;
import com.yihu.ehr.health.SevereMentalSupplement.dao.*;
import com.yihu.ehr.health.SevereMentalSupplement.entity.*;
import com.yihu.ehr.query.BaseJpaService;
import net.sf.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SevereMentalSupplementService extends BaseJpaService<SevereMentalSupplement, XSevereMentalSupplementRepository> {
    @Autowired
    XSevereMentalSupplementRepository severeMentalSupplementRepository;
    @Autowired
    MentalStatisticalController mentalStatisticalController;
    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public SevereMentalSupplement getSevereMentalSupplement(String Id) {
        SevereMentalSupplement severeMentalSupplement = severeMentalSupplementRepository.findOne(Id);
        return severeMentalSupplement;
    }

    /**
     * 保存个人档案信息
     * @param severeMentalSupplement 个人档案对象
     * @return
     */
    public SevereMentalSupplement saveUser(SevereMentalSupplement beforeEntity , SevereMentalSupplement severeMentalSupplement) {


        severeMentalSupplement.setDeleteState("未删");
        severeMentalSupplement = severeMentalSupplementRepository.save(severeMentalSupplement);
        return severeMentalSupplement;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        SevereMentalSupplement data = severeMentalSupplementRepository.findOne(Id);
        data.setDeleteState("已删");
        severeMentalSupplementRepository.save(data);
    }


    /**
     * 判断实体是否有修改
     *
     */
    public Boolean checkEntity(SevereMentalSupplement beforeEntity , SevereMentalSupplement entity){
        String beforeStr = JSONSerializer.toJSON(beforeEntity).toString();
        String afterStr = JSONSerializer.toJSON(entity).toString();
        if (beforeStr.equals(afterStr))
            return false;
        else
            return true;
    }
}
