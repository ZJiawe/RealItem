package com.yihu.ehr.health.ThreeSixYearChild.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "three_six_year_child")
@Access(value = AccessType.PROPERTY)
public class ThreeSixYearChild extends BaseAssignedEntity {
  /**  身份证号*/
  @Column(name = "people_id",  nullable = true)
  private String peopleId;
  /**  姓名*/
  @Column(name = "name",  nullable = true)
  private String name;
  /**  编号*/
  @Column(name = "number",  nullable = true)
  private String number;
  /**  年龄*/
  @Column(name = "age",  nullable = true)
  private String age;
  /**  随访日期*/
  @Column(name = "follow_date",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date followDate;
  /**  体重*/
  @Column(name = "weight",  nullable = true)
  private String weight;
  /**  身高*/
  @Column(name = "height",  nullable = true)
  private String height;
  /**  身高/体重*/
  @Column(name = "weightheight",  nullable = true)
  private String weightheight;
  /**  体格发育评价*/
  @Column(name = "physical",  nullable = true)
  private String physical;
  /**  牙数*/
  @Column(name = "mouth",  nullable = true)
  private String mouth;
  /**  龋齿数*/
  @Column(name = "bmouth",  nullable = true)
  private String bmouth;
  /**  听力*/
  @Column(name = "hearing",  nullable = true)
  private String hearing;
  /**  视力*/
  @Column(name = "eye",  nullable = true)
  private String eye;
  /**  胸部*/
  @Column(name = "chest",  nullable = true)
  private String chest;
  /**  腹部*/
  @Column(name = "abdomen",  nullable = true)
  private String abdomen;
  /**  血红蛋白值*/
  @Column(name = "hemoglobin",  nullable = true)
  private Integer hemoglobin;
  /**  其他*/
  @Column(name = "otherCheck",  nullable = true)
  private String otherCheck;
  /**  发育评估*/
  @Column(name = "development",  nullable = true)
  private String development;
  /**  肺炎*/
  @Column(name = "pneumonia",  nullable = true)
  private String pneumonia;
  /**  腹泻*/
  @Column(name = "diarrhea",  nullable = true)
  private String diarrhea;
  /**  外伤*/
  @Column(name = "trauma",  nullable = true)
  private String trauma;
  /**  其他*/
  @Column(name = "other",  nullable = true)
  private String other;
  /**  转诊建议*/
  @Column(name = "referral",  nullable = true)
  private String referral;
  /**  转诊理由*/
  @Column(name = "reason",  nullable = true)
  private String reason;
  /**  机构及科室*/
  @Column(name = "insand_dep",  nullable = true)
  private String insandDep;
  /**  指导*/
  @Column(name = "guidance",  nullable = true)
  private String guidance;
  /**  中医指导*/
  @Column(name = "medicine",  nullable = true)
  private String medicine;
  /**  下次随访日期*/
  @Column(name = "next_date",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date nextDate;
  /**  随访医生*/
  @Column(name = "doctor",  nullable = true)
  private String doctor;
  /**  删除状态*/
  @Column(name = "delete_state",  nullable = true)
  private String deleteState;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPeopleId() {
    return peopleId;
  }

  public void setPeopleId(String peopleId) {
    this.peopleId = peopleId;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }


  public Date getFollowDate() {
    return followDate;
  }

  public void setFollowDate(Date followDate) {
    this.followDate = followDate;
  }


  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }


  public String getWeightheight() { return weightheight; }

  public void setWeightheight(String weightheight) {
    this.weightheight = weightheight;
  }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }


  public String getPhysical() {
    return physical;
  }

  public void setPhysical(String physical) {
    this.physical = physical;
  }


  public String getMouth() {
    return mouth;
  }

  public void setMouth(String mouth) {
    this.mouth = mouth;
  }


  public String getHearing() {
    return hearing;
  }

  public void setHearing(String hearing) {
    this.hearing = hearing;
  }

  public String getBmouth() {
    return bmouth;
  }

  public void setBmouth(String bmouth) {
    this.bmouth = bmouth;
  }

  public String getEye() {
    return eye;
  }

  public void setEye(String eye) {
    this.eye = eye;
  }


  public String getChest() {
    return chest;
  }

  public void setChest(String chest) {
    this.chest = chest;
  }


  public String getAbdomen() {
    return abdomen;
  }

  public void setAbdomen(String abdomen) {
    this.abdomen = abdomen;
  }


  public Integer getHemoglobin() {
    return hemoglobin;
  }

  public void setHemoglobin(Integer hemoglobin) {
    this.hemoglobin = hemoglobin;
  }


  public String getOtherCheck() {
    return otherCheck;
  }

  public void setOtherCheck(String otherCheck) {
    this.otherCheck = otherCheck;
  }


  public String getDevelopment() {
    return development;
  }

  public void setDevelopment(String development) {
    this.development = development;
  }


  public String getPneumonia() {
    return pneumonia;
  }

  public void setPneumonia(String pneumonia) {
    this.pneumonia = pneumonia;
  }


  public String getDiarrhea() {
    return diarrhea;
  }

  public void setDiarrhea(String diarrhea) {
    this.diarrhea = diarrhea;
  }


  public String getTrauma() {
    return trauma;
  }

  public void setTrauma(String trauma) {
    this.trauma = trauma;
  }


  public String getOther() {
    return other;
  }

  public void setOther(String other) {
    this.other = other;
  }


  public String getReferral() {
    return referral;
  }

  public void setReferral(String referral) {
    this.referral = referral;
  }


  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }


  public String getInsandDep() {
    return insandDep;
  }

  public void setInsandDep(String insandDep) {
    this.insandDep = insandDep;
  }


  public String getGuidance() {
    return guidance;
  }

  public void setGuidance(String guidance) {
    this.guidance = guidance;
  }


  public Date getNextDate() {
    return nextDate;
  }

  public void setNextDate(Date nextDate) {
    this.nextDate = nextDate;
  }


  public String getMedicine() {
    return medicine;
  }

  public void setMedicine(String medicine) {
    this.medicine = medicine;
  }


  public String getDoctor() {
    return doctor;
  }

  public void setDoctor(String doctor) {
    this.doctor = doctor;
  }


  public String getDeleteState() { return deleteState; }

  public void setDeleteState(String deleteState) {
    this.deleteState = deleteState;
  }
}
