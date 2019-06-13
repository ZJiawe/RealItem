package com.yihu.ehr.health.Transtreatset.dao;
import com.yihu.ehr.health.Transtreatset.entity.Transtreatset;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface XTranstreatsetRepository  extends PagingAndSortingRepository<Transtreatset,String> {

}