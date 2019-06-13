package com.yihu.ehr.health.TwoDiabetes.service;
import com.yihu.ehr.health.TwoDiabetes.dao.*;
import com.yihu.ehr.health.TwoDiabetes.entity.TwoDiabetesEntity;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TwoDiabetesService extends BaseJpaService<TwoDiabetesEntity, XTwoDiabetesRepository> {
    @Autowired
    XTwoDiabetesRepository XTwoDiabetesRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public TwoDiabetesEntity getTwoDiabetesEntity(String Id) {
        TwoDiabetesEntity twoDiabetesEntity = XTwoDiabetesRepository.findOne(Id);
        return twoDiabetesEntity;
    }

    /**
     * 保存个人档案信息
     * @param twoDiabetesEntity 个人档案对象
     * @return
     */
    public TwoDiabetesEntity saveUser(TwoDiabetesEntity twoDiabetesEntity) {
        twoDiabetesEntity.setDeleteState("未删");
        twoDiabetesEntity = XTwoDiabetesRepository.save(twoDiabetesEntity);
        return twoDiabetesEntity;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        TwoDiabetesEntity twoDiabetesEntity = XTwoDiabetesRepository.findOne(Id);
        twoDiabetesEntity.setDeleteState("已删");
        XTwoDiabetesRepository.save(twoDiabetesEntity);
    }


}
