package com.yihu.ehr.health.Follow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "follow")
@Access(value = AccessType.PROPERTY)
public class FollowEntity extends BaseAssignedEntity {
    /**  身份证号*/
    @Column(name = "people_id",  nullable = true)
    private String peopleId;
    /**  个人编号*/
    @Column(name = "personal_id",  nullable = true)
    private String personalId;
    /**  用户编号*/
    @Column(name = "user_id",  nullable = true)
    private String userId;
    /**  姓名*/
    @Column(name = "name",  nullable = true)
    private String name;
    /**  联系电话*/
    @Column(name = "phone",  nullable = true)
    private String phone;
    /**  最后一次随访时间*/
    @Column(name = "last_follow",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date lastFollow;
    /**  下次随访时间*/
    @Column(name = "next_follow",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date nextFollow;

    public String getPeopleId() { return peopleId; }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getPersonalId() { return personalId; }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLastFollow() { return lastFollow; }

    public void setLastFollow(Date lastFollow) {
        this.lastFollow = lastFollow;
    }

    public Date getNextFollow() { return nextFollow; }

    public void setNextFollow(Date nextFollow) {
        this.nextFollow = nextFollow;
    }

}
