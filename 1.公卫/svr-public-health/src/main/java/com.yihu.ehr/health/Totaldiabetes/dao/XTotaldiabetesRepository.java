
package com.yihu.ehr.health.Totaldiabetes.dao;

        import com.yihu.ehr.health.Totaldiabetes.entity.Totaldiabetes;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.PagingAndSortingRepository;
        import org.springframework.data.repository.query.Param;

/**
 * Created by Peng
 * Time: 2016/12/16 18:07
 */
public interface XTotaldiabetesRepository extends PagingAndSortingRepository<Totaldiabetes,String> {

    @Query("select totaldiabetesEntity from Totaldiabetes totaldiabetesEntity where totaldiabetesEntity.area = :area or totaldiabetesEntity.year = :year")  //添加搜索条件
    Totaldiabetes findByareaAndYear(@Param("area") String area, @Param("year") Integer year);

}
