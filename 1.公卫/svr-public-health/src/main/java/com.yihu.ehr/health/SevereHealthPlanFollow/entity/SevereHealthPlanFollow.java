package com.yihu.ehr.health.SevereHealthPlanFollow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "health_plan")
@Access(value = AccessType.PROPERTY)
public class SevereHealthPlanFollow extends BaseAssignedEntity {
  @Column(name = "health_goal",  nullable = true)
  private String healthGoal;
  @Column(name = "name",  nullable = true)
  private String name;
  @Column(name = "area",  nullable = true)
  private String area;
  @Column(name = "delete_state",  nullable = true)
  private String deleteState;
  @Column(name = "age",  nullable = true)
  private Integer age;
  @Column(name = "sex",  nullable = true)
  private String sex;
  @Column(name = "people",  nullable = true)
  private String people;
  @Column(name = "people_id",  nullable = true)
  private String peopleId;
  @Column(name = "credit_id",  nullable = true)
  private String creditId;
  @Column(name = "phone",  nullable = true)
  private String phone;
  @Column(name = "pan_people",  nullable = true)
  private String panPeople;
  @Column(name = "institutions",  nullable = true)
  private String institutions;
  @Column(name = "place",  nullable = true)
  private String place;
  @Column(name = "note",  nullable = true)
  private String note;
  @Column(name = "penple",  nullable = true)
  private String penple;
  @Column(name = "orgpenple",  nullable = true)
  private String orgpenple;
  @JsonFormat(locale = "zh",timezone = "GMT+8", pattern = "yyyy-MM-dd")
  private Date panTime;
  @JsonFormat(locale = "zh",timezone = "GMT+8", pattern = "yyyy-MM-dd")
  private Date healthTime;

  public String getHealthGoal() {
    return healthGoal;
  }

  public void setHealthGoal(String healthGoal) {
    this.healthGoal = healthGoal;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPeople() {
    return people;
  }

  public void setPeople(String people) {
    this.people = people;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getDeleteState() { return deleteState; }

  public void setDeleteState(String deleteState) {
    this.deleteState = deleteState;
  }

  public String getOrgpenple() {
    return orgpenple;
  }

  public void setOrgpenple(String orgpenple) {
    this.orgpenple = orgpenple;
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


  public String getPeopleId() {
    return peopleId;
  }

  public void setPeopleId(String peopleId) {
    this.peopleId = peopleId;
  }


  public String getCreditId() {
    return creditId;
  }

  public void setCreditId(String creditId) {
    this.creditId = creditId;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getPanPeople() {
    return panPeople;
  }

  public void setPanPeople(String panPeople) {
    this.panPeople = panPeople;
  }


  public String getInstitutions() {
    return institutions;
  }

  public void setInstitutions(String institutions) {
    this.institutions = institutions;
  }


  public String getPenple() {
    return penple;
  }

  public void setPenple(String penple) {
    this.penple = penple;
  }


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }


  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }


  public Date getHealthTime() {
    return healthTime;
  }

  public void setHealthTime(Date healthTime) {
    this.healthTime = healthTime;
  }


  public Date getPanTime() {
    return panTime;
  }

  public void setPanTime(Date panTime) {
    this.panTime = panTime;
  }


}
