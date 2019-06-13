package com.yihu.ehr.health.MentalQuery.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;
import java.util.Date;
import javax.persistence.*;

public class MentalQuery {


    private String area;//区域

    private String name;//姓名

    private String sex;//性别

    private Integer age;//年龄

    private String ethnic;//民族

    private String peopleId;//身份证号

    private String phone;//联系电话

    private String management;//规范管理

    private String responseDoctor;//责任医生
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date recentFollow;//最近随访

    private Integer numberFollow;//随访次数

    private String livingCondition;//生存状态

    private String deleteState; /**  删除状态*/

    /**  建档人*/
    private String creator;
    /**  建档时间*/
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date createDate;



    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
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


    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }


    public String getResponseDoctor() {
        return responseDoctor;
    }

    public void setResponseDoctor(String responseDoctor) {
        this.responseDoctor = responseDoctor;
    }



    public Date getRecentFollow() {
        return recentFollow;
    }

    public void setRecentFollow(Date recentFollow) {
        this.recentFollow = recentFollow;
    }


    public Integer getNumberFollow() {
        return numberFollow;
    }

    public void setNumberFollow(Integer numberFollow) {
        this.numberFollow = numberFollow;
    }


    public String getLivingCondition() {
        return livingCondition;
    }

    public void setLivingCondition(String livingCondition) {
        this.livingCondition = livingCondition;
    }


    public String getDeleteState() { return deleteState; }

    public void setDeleteState(String deleteState) { this.deleteState = deleteState; }


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
