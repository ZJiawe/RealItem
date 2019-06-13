package com.yihu.ehr.health.SevereHyperSumFollow.dao;

import com.yihu.ehr.health.SevereHyperSumFollow.entity.SevereHyperSumFollow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Peng
 * Time: 2016/12/16 18:07
 */
public interface XSevereHyperSumFollowRepository extends PagingAndSortingRepository<SevereHyperSumFollow,String> {

            @Query("select severeHyperSumFollow from SevereHyperSumFollow severeHyperSumFollow where severeHyperSumFollow.area = :area or severeHyperSumFollow.year = :year")  //添加搜索条件
            SevereHyperSumFollow findByareaAndYear(@Param("area") String area, @Param("year") Integer year);

}