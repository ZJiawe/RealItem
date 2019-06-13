package com.yihu.ehr.health.Perinfor.dao;

import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Peng
 * Time: 2016/12/16 18:07
 */
public interface XPerinforRepository extends PagingAndSortingRepository<Perinfor,String> {

}