package com.yihu.ehr.health.TwelveThsixEightMonth.service;

import com.yihu.ehr.health.TwelveThsixEightMonth.dao.XTwelveThsixEightMonthChildRepository;
import com.yihu.ehr.health.TwelveThsixEightMonth.entity.TwelveThsixEightMonthChild;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TwelveThsixEightMonthChildService extends BaseJpaService<TwelveThsixEightMonthChild, XTwelveThsixEightMonthChildRepository> {
    @Autowired
    XTwelveThsixEightMonthChildRepository twelveThsixEightMonthChildRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public TwelveThsixEightMonthChild getTwelveThsixEightMonthChild(String Id) {
        TwelveThsixEightMonthChild entity = twelveThsixEightMonthChildRepository.findOne(Id);
        return entity;
    }

    /**
     * 保存个人档案信息
     * @param entity 个人档案对象
     * @return
     */
    public TwelveThsixEightMonthChild saveUser(TwelveThsixEightMonthChild entity) {
        entity.setDeleteState("未删");
        entity = twelveThsixEightMonthChildRepository.save(entity);
        return entity;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        TwelveThsixEightMonthChild entity = twelveThsixEightMonthChildRepository.findOne(Id);
        entity.setDeleteState("已删");
        twelveThsixEightMonthChildRepository.save(entity);
    }

}
