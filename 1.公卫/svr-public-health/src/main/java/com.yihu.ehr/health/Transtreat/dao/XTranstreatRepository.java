package com.yihu.ehr.health.Transtreat.dao;
import com.yihu.ehr.health.Transtreat.entity.Transtreat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface XTranstreatRepository  extends PagingAndSortingRepository<Transtreat,String> {

}