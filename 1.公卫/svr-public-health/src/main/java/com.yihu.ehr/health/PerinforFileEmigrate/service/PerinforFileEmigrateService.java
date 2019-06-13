package com.yihu.ehr.health.PerinforFileEmigrate.service;

import com.google.common.collect.Lists;
import com.yihu.ehr.health.PerinforFileEmigrate.dao.XPerinforFileEmigrateRepository;
import com.yihu.ehr.health.PerinforFileEmigrate.entity.PerinforFileEmigrate;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class PerinforFileEmigrateService extends BaseJpaService<PerinforFileEmigrate, XPerinforFileEmigrateRepository> {
    @Autowired
    XPerinforFileEmigrateRepository perinforFileEmigrateRepository;
    
    public List<PerinforFileEmigrate> getselect() {

        Iterator<PerinforFileEmigrate> myIterator = perinforFileEmigrateRepository.findAll().iterator();
        List<PerinforFileEmigrate> basicfamilyinfor =  Lists.newArrayList(myIterator);;

        return basicfamilyinfor;
    }

    public PerinforFileEmigrate getPerinforFileEmigrate(String Id) {
        PerinforFileEmigrate entity = perinforFileEmigrateRepository.findOne(Id);
        return entity;
    }

    public PerinforFileEmigrate saveUser(PerinforFileEmigrate entity) {
        entity.setDeleteState("未删");
        entity = perinforFileEmigrateRepository.save(entity);
        return entity;
    }

    public void deleteUser(String Id) {
        PerinforFileEmigrate entity = perinforFileEmigrateRepository.findOne(Id);
        entity.setDeleteState("已删");
        perinforFileEmigrateRepository.save(entity);
    }

}
