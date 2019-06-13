package com.yihu.ehr.health.OneEightMonthchild.service;

import com.yihu.ehr.health.ChildrenStatistic.dao.XChildrenStatisticRepository;
import com.yihu.ehr.health.OneEightMonthchild.dao.XOneEightMonthChildRepository;
import com.yihu.ehr.health.OneEightMonthchild.entity.OneEightMonthChild;
import com.yihu.ehr.health.Perinfor.dao.XPerinforRepository;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class OneEightMonthChildService extends BaseJpaService<OneEightMonthChild, XOneEightMonthChildRepository> {
    @Autowired
    XOneEightMonthChildRepository oneEightMonthChildRepository;
    @Autowired
    XChildrenStatisticRepository childrenStatisticRepository;
    @Autowired
    XPerinforRepository perinforRepository;
    @Autowired
    PerinforService perinforService;
    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public OneEightMonthChild getOneEightMonthChild(String Id) {
        OneEightMonthChild entity = oneEightMonthChildRepository.findOne(Id);
        return entity;
    }

    /**
     * 保存个人档案信息
     * @param entity 个人档案对象
     * @return
     */
    public OneEightMonthChild saveData(OneEightMonthChild entity) {
        entity.setDeleteState("未删");
        entity = oneEightMonthChildRepository.save(entity);
        return entity;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteData(String Id) {
        OneEightMonthChild entity = oneEightMonthChildRepository.findOne(Id);
        entity.setDeleteState("已删");
        oneEightMonthChildRepository.save(entity);
    }

    public Integer getYear(Date date )     //获取时间年份
    {
        String test=date.toString().substring(0,4);
        Integer year=Integer.parseInt(test);
        return year;
    }

}
