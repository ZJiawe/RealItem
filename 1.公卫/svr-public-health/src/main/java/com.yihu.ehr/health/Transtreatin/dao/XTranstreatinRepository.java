package com.yihu.ehr.health.Transtreatin.dao;
import com.yihu.ehr.health.Transtreatin.entity.Transtreatin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface XTranstreatinRepository  extends PagingAndSortingRepository<Transtreatin,String> {

}