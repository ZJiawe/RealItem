package com.yihu.ehr.health.TuberculosisPatient.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;
import java.util.Date;
import javax.persistence.*;

public class TuberEntity {


    private String area;//区域

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date inputDate;//建档时间

    private String name;//姓名

    private String sex;//性别

    private String age;//年龄

    private String ethnic;//民族

    private String peopleId;//身份证号

    private String phone;//联系电话

    private String management;//规范管理

    private String responDoctor;//责任医生
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date recentFollow;//最近随访


   private Integer fNumber;//随访次数


    private String lifeState;//生存状态

    private String deleteState; /**  删除状态*/

    /**  建档人*/
    private String inputPerson;
    /**  建档时间*/
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date createDate;



    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
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


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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


    public String getResponDoctor() {
        return responDoctor;
    }

    public void setResponDoctor(String responDoctor) {
        this.responDoctor = responDoctor;
    }



    public Date getRecentFollow() {
        return recentFollow;
    }

    public void setRecentFollow(Date recentFollow) {
        this.recentFollow = recentFollow;
    }


    public Integer getfNumber() {
        return fNumber;
    }
    public void setfNumber(Integer fNumber) {
        this.fNumber = fNumber;
    }

    public String getLifeState() {
        return lifeState;
    }

    public void setLifeState(String lifeState) {
        this.lifeState = lifeState;
    }


    public String getDeleteState() { return deleteState; }

    public void setDeleteState(String deleteState) { this.deleteState = deleteState; }


    public String getInputPerson() {
        return inputPerson;
    }

    public void setInputPerson(String inputPerson) {
        this.inputPerson = inputPerson;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
