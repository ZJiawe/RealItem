package com.yihu.ehr.health.BasicFamilyInfor.entity;
import com.yihu.ehr.entity.BaseAssignedEntity;
import javax.persistence.*;

@Entity
@Table(name = "search_r_doctor")
@Access(value = AccessType.PROPERTY)
public class SearchRdoctor extends BaseAssignedEntity {
    /**
     * 机构内码
     */
    @Column(name = "org_id",  nullable = true)
    private String orgId;
    /**
     * 医生姓名
     */
    @Column(name = "search_parm",  nullable = true)
    private String searchParm;

    /**
     * 机构内号
     */

    /**
     * 医生姓名
     */
    @Column(name = "user_name",  nullable = true)
    private String userName;

    /**
     * 医生编号
     */
    @Column(name = "user_id",  nullable = true)
    private String userId;
    /**
     * 删除状态
     */
    @Column(name = "delete_state",  nullable = true)
    private String deleteState;

    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


    public String getSearchParm() {
        return searchParm;
    }
    public void setSearchParm(String searchParm) {
        this.searchParm = searchParm;
    }


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeleteState() {
        return deleteState;
    }
    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }
}
