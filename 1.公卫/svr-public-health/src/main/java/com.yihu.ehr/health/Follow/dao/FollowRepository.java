package com.yihu.ehr.health.Follow.dao;

import com.yihu.ehr.health.Follow.entity.FollowEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Peng
 * Time: 2016/12/16 18:07
 */
public interface FollowRepository extends PagingAndSortingRepository<FollowEntity,String> {

}