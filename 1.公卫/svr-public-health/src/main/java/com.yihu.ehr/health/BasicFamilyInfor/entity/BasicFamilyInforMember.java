package com.yihu.ehr.health.BasicFamilyInfor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "basic_family_infor_member")
@Access(value = AccessType.PROPERTY)
public class BasicFamilyInforMember extends BaseAssignedEntity {
    /**
     * 户主名称
     */
    @Column(name = "id_num",  nullable = true)
    private String idNum;
    /**
     * 户主名称
     */
    @Column(name = "category",  nullable = true)
    private String category;

    /**
     * 家庭ID
     */
    @Column(name = "familyid",  nullable = true)
    private String familyid;


    /**
     * 主要健康问题
     */
    @Column(name = "major_health_issues",  nullable = true)
    private String majorHealthIssues;

    /**
     * 发生日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @Column(name = "occurrence_date",  nullable = true)
    private Date occurrenceDate;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getidNum() {
        return idNum;
    }

    public void setidNum(String idNum) {
        this.idNum = idNum;
    }

    public String getfamilyid() {
        return familyid;
    }

    public void setfamilyid(String familyid) {
        this.familyid = familyid;
    }

    public String getMajorHealthIssues() {
        return majorHealthIssues;
    }

    public void setMajorHealthIssues(String majorHealthIssues) {
        this.majorHealthIssues = majorHealthIssues;
    }

    public java.sql.Date getOccurrenceDate() {
        return occurrenceDate;
    }

    public void setOccurrenceDate(java.sql.Date occurrenceDate) {
        this.occurrenceDate = occurrenceDate;
    }



}

