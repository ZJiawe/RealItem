package com.yihu.ehr.health.MentalQuery.service;

import com.yihu.ehr.health.MentalQuery.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MentalQueryService  {


    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public void getMentalQuery(String Id) {

    }

    /**
     * 保存个人档案信息
     * @param mentalQuery 个人档案对象
     * @return
     */
    public void saveUser(MentalQuery mentalQuery) {

    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {

    }
}
