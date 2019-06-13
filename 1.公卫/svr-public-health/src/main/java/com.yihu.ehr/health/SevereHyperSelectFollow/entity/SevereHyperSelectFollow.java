package com.yihu.ehr.health.SevereHyperSelectFollow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
public class SevereHyperSelectFollow extends BaseAssignedEntity {

    private String name;

    private Integer age;

    private String sex;

    private String deleteState;

    private String ismanagement;

    private String peopleId;

    private String responDoctor;

    private String phone;

    private String docPeople;
    @JsonFormat(locale = "zh",timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date lastVisit;

    private Integer neonatalVisit;

    private String total;

    private String ethnic;

    private String area;
    @JsonFormat(locale = "zh",timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date docDate;

    public String getArea() {
        return area;
    }

    public void setArea(String area) { this.area=area; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeleteState() { return deleteState; }

    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIsmanagement() {
        return ismanagement;
    }

    public void setIsmanagement(String ismanagement) {
        this.ismanagement = ismanagement;
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

    public String getResponDoctor() {
        return responDoctor;
    }

    public void setResponDoctor(String responDoctor) {
        this.responDoctor = responDoctor;
    }

    public String getDocPeople() {
        return docPeople;
    }

    public void setDocPeople(String docPeople) {
        this.docPeople = docPeople;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public Integer getNeonatalVisit() {
        return neonatalVisit;
    }

    public void setNeonatalVisit(Integer neonatalVisit) {
        this.neonatalVisit = neonatalVisit;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }
}
