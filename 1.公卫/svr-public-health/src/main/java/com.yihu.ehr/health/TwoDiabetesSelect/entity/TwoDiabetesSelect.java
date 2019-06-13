package com.yihu.ehr.health.TwoDiabetesSelect.entity;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
/**
 * Entity - 基类（自动递增）
 * Created by lll  on 2018/9/21.
 */
@Entity
@Table(name = "diabetes")
@Access(value = AccessType.PROPERTY)
public class TwoDiabetesSelect extends BaseAssignedEntity {
    /** 姓名*/
    @Column(name = "name",  nullable = true)
    private String name;
    /** 性别*/
    @Column(name = "sex",  nullable = true)
    private String  sex;
    /** 年龄*/
    @Column(name = "age",  nullable = true)
    private String  age;
    /** 身份证号码*/
    @Column(name = "people_id",  nullable = true)
    private String peopleId;
    /** 地区*/
    @Column(name = "area",  nullable = true)
    private String area;
    /** 民族*/
    @Column(name = "ethnic",  nullable = true)
    private String ethnic;
    /** 联系电话*/
    @Column(name = "phone",  nullable = true)
    private String  phone;
    /** 责任医生*/
    @Column(name = "respon_doctor",  nullable = true)
    private String  responDoctor;
    /** 最近随访*/
    @Column(name = "follow_visit",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date  followVisit;
    /** 随访次数*/
    @Column(name = "count",  nullable = true)
    private Integer count	;
    /** 生存状态*/
    @Column(name = "live_status",  nullable = true)
    private String liveStatus	;
    /** 伪删除 */
    @Column(name = "deleteState",  nullable = true)
    private String deleteState;

    //  /** 创建人 */
//  private String creater;
//  /** 创建时间 */
//  private Date createDate;
//  /** 修改人 */
//  private String modifier;
//  /** 修改时间 */
//  private Date modifyDate;
    public TwoDiabetesSelect(){ }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    public String getSex() {
        return  sex;
    }
    public void setSex(String  sex) {
        this. sex =  sex;
    }


    public String getAge() {
        return  age;
    }
    public void setAge(String age) {
        this. age =  age;
    }

    public String getPeopleId() {
        return peopleId;
    }
    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }


    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }


    public String getEthnic() {
        return ethnic;
    }
    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }


    public String getResponDoctor	() {
        return responDoctor;
    }
    public void setResponDoctor	(String responDoctor	) {
        this.responDoctor	 = responDoctor;
    }

    public Date getFollowVisit	() {
        return followVisit;
    }
    public void setFollowVisit	(Date followVisit	) {
        this.followVisit	 = followVisit;
    }

    public Integer getCount	() {
        return count;
    }
    public void setCount	(Integer count	) {
        this.count	 = count;
    }

    public String getLiveStatus	() {
        return liveStatus;
    }
    public void setLiveStatus	(String liveStatus	) {
        this.liveStatus	 = liveStatus;
    }


    public String getDeleteState() { return deleteState; }
    public void setDeleteState(String deleteState) { this.deleteState = deleteState; }

}
