package com.yihu.ehr.health.ChildrenMangement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
public class ChildrenMangement  {
  /**  姓名*/
  private String name;
  /**  性别*/
  private String sex;
  /**  年龄*/
  private String age;
  /**  身份证号*/
  private String peopleId;
  /**  联系方式*/
  private String phone;
  /**  民族*/
  private String nation;
  /**  责任医生*/
  private String doctor;
  /**  建档人*/
  private String documentPeople;
  /**  建档时间*/
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date documentDate;
  /**  最近访问*/
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date lastVisit;
  /**  新生儿访视*/
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date newbornVisit;
  /**  总次数*/
  private Integer visitAllCount;
  /**  地区*/
  private String area;
  /**  生存状态*/
  private String liveState;
  
  
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


  public String getNation() {
    return nation;
  }

  public void setNation(String nation) {
    this.nation = nation;
  }


  public String getDoctor() {
    return doctor;
  }

  public void setDoctor(String doctor) {
    this.doctor = doctor;
  }


  public String getDocumentPeople() {
    return documentPeople;
  }

  public void setDocumentPeople(String documentPeople) {
    this.documentPeople = documentPeople;
  }


  public Date getDocumentDate() {
    return documentDate;
  }

  public void setDocumentDate(Date documentDate) {
    this.documentDate = documentDate;
  }


  public Date getLastVisit() {
    return lastVisit;
  }

  public void setLastVisit(Date lastVisit) {
    this.lastVisit = lastVisit;
  }


  public Date getNewbornVisit() {
    return newbornVisit;
  }

  public void setNewbornVisit(Date newbornVisit) {
    this.newbornVisit = newbornVisit;
  }


  public Integer getVisitAllCount() {
    return visitAllCount;
  }

  public void setVisitAllCount(Integer visitAllCount) {
    this.visitAllCount = visitAllCount;
  }


  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }


  public String getLiveState() {
    return liveState;
  }

  public void setLiveState(String liveState) {
    this.liveState = liveState;
  }

}
