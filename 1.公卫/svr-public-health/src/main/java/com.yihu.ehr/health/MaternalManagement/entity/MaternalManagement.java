package com.yihu.ehr.health.MaternalManagement.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import javax.persistence.*;
import java.util.Date;


public class MaternalManagement extends BaseAssignedEntity {
  private String area;//区域
  private String name;//姓名
  private String sex;//性别
  private Integer age;//年龄
  private String ethnic;//民族
  private String peopleId;//身份证号
  private String phone;//联系电话
  private String earlyPregnancy;//早孕
  private String responDoctor;//责任医生
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date docDate;//建档日期
  private String docPeople;//建档人
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date lastVisit;//最近随访
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date first;//第一次产前随访
  private Integer neonatalVisit;//随访总次数
  private String handle;//处理（转诊or结案）
  private String state;//生存状态
  private String deleteState;    /**  删除状态*/

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
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


  public String getEarlyPregnancy() {
    return earlyPregnancy;
  }

  public void setEarlyPregnancy(String earlyPregnancy) {
    this.earlyPregnancy = earlyPregnancy;
  }

  public String getResponDoctor() {
    return responDoctor;
  }

  public void setResponDoctor(String responDoctor) {
    this.responDoctor = responDoctor;
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


  public Date getLastVisit() {
    return lastVisit;
  }

  public void setLastVisit(Date lastVisit) {
    this.lastVisit = lastVisit;
  }


  public Date getFirst() {
    return first;
  }

  public void setFirst(Date first) {
    this.first = first;
  }


  public Integer getNeonatalVisit() {
    return neonatalVisit;
  }

  public void setNeonatalVisit(Integer neonatalVisit) {
    this.neonatalVisit = neonatalVisit;
  }

  public String getHandle() {
    return handle;
  }

  public void setHandle(String handle) {
    this.handle = handle;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getDeleteState() { return deleteState; }

  public void setDeleteState(String deleteState) { this.deleteState = deleteState; }

}
