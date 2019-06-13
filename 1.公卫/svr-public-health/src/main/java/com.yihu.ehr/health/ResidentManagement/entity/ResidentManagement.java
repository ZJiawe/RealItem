package com.yihu.ehr.health.ResidentManagement.entity;



import com.yihu.ehr.entity.BaseAssignedEntity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "resident_management")
@Access(value = AccessType.PROPERTY)
public class ResidentManagement extends BaseAssignedEntity {

  private String area;//区域
  private String name;//姓名
  private String sex;//性别
  private Integer age;//年龄
  private String peopleId;//身份证
  private String ethnic;//民族
  private String phone;//联系方式
  private String electronicRecord;//电子健康档案
  private String activeFiles;//健康档案动态使用
  private String crowd;//人群分类
  private String responDoctor;//责任医生
  private Date docDate;//建档日期
  private String docPeople;//建档人
  private String state;//生存状态
  private String deleteState; /**  删除状态*/


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


  public String getPeopleId() {
    return peopleId;
  }

  public void setPeopleId(String peopleId) {
    this.peopleId = peopleId;
  }


  public String getEthnic() {
    return ethnic;
  }

  public void setEthnic(String ethnic) {
    this.ethnic = ethnic;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getElectronicRecord() {
    return electronicRecord;
  }

  public void setElectronicRecord(String electronicRecord) {
    this.electronicRecord = electronicRecord;
  }


  public String getActiveFiles() {
    return activeFiles;
  }

  public void setActiveFiles(String activeFiles) {
    this.activeFiles = activeFiles;
  }


  public String getCrowd() {
    return crowd;
  }

  public void setCrowd(String crowd) {
    this.crowd = crowd;
  }


  public Date getDocDate() {
    return docDate;
  }

  public void setDocDate(Date docDate) {
    this.docDate = docDate;
  }


  public String getDocPeople() {
    return docPeople;
  }

  public void setDocPeople(String docPeople) {
    this.docPeople = docPeople;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getResponDoctor() {
    return responDoctor;
  }

  public void setResponDoctor(String responDoctor) {
    this.responDoctor = responDoctor;
  }


  public String getDeleteState() { return deleteState; }

  public void setDeleteState(String deleteState) { this.deleteState = deleteState; }

}
