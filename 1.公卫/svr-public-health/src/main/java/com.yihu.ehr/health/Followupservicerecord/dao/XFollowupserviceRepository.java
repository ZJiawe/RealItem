package com.yihu.ehr.health.Followupservicerecord.dao;
        import com.yihu.ehr.health.Followupservicerecord.entity.FollowUpServiceRecordEntity;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
        import org.springframework.data.repository.PagingAndSortingRepository;

        import java.util.List;

/**
 * Created by Peng
 * Time: 2016/12/16 18:07
 */

public interface XFollowupserviceRepository  extends PagingAndSortingRepository<FollowUpServiceRecordEntity,String> {

}

