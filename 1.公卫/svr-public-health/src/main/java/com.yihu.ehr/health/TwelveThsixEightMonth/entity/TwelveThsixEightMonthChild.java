package com.yihu.ehr.health.TwelveThsixEightMonth.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TwelveThsixEightMonthChild")
@Access(value = AccessType.PROPERTY)
public class TwelveThsixEightMonthChild extends BaseAssignedEntity {
  /**  身份证号*/
  @Column(name = "people_id",  nullable = true)
  private String peopleId;
  /**  姓名*/
  @Column(name = "name",  nullable = true)
  private String name;
  /**  编号*/
  @Column(name = "number",  nullable = true)
  private String number;
  /**  月龄*/
  @Column(name = "month",  nullable = true)
  private String month;
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
  /**  面色*/
  @Column(name = "face_color",  nullable = true)
  private String faceColor;
  /**  皮肤*/
  @Column(name = "skin",  nullable = true)
  private String skin;
  /**  前囟*/
  @Column(name = "bregma",  nullable = true)
  private String bregma;
  /**  前囟高度*/
  @Column(name = "bregma_height",  nullable = true)
  private String bregmaHeight;
  /**  前囟宽度*/
  @Column(name = "bregma_width",  nullable = true)
  private String bregmaWidth;
  /**  视力*/
  @Column(name = "eye",  nullable = true)
  private String eye;
  /**  耳外观*/
  @Column(name = "ear",  nullable = true)
  private String ear;
  /**  听力*/
  @Column(name = "hearing",  nullable = true)
  private String hearing;
  /**  出牙数*/
  @Column(name = "mouth",  nullable = true)
  private String mouth;
  /**  龋齿数*/
  @Column(name = "bmouth",  nullable = true)
  private String bmouth;
  /**  胸部*/
  @Column(name = "chest",  nullable = true)
  private String chest;
  /**  腹部*/
  @Column(name = "abdomen",  nullable = true)
  private String abdomen;
  /**  四肢*/
  @Column(name = "limb",  nullable = true)
  private String limb;
  /**  步态*/
  @Column(name = "gait",  nullable = true)
  private String gait;
  /**  可疑佝偻病体征*/
  @Column(name = "ricketsBody",  nullable = true)
  private String ricketsBody;
  /**  血红蛋白值*/
  @Column(name = "hemoglobin",  nullable = true)
  private Integer hemoglobin;
  /**  户外活动*/
  @Column(name = "out_activities",  nullable = true)
  private Integer outActivities;
  /**  服用维生素D*/
  @Column(name = "vitamind",  nullable = true)
  private Integer vitaminD;
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
  /**  理由*/
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


  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
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


  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }


  public String getFaceColor() {
    return faceColor;
  }

  public void setFaceColor(String faceColor) {
    this.faceColor = faceColor;
  }


  public String getSkin() {
    return skin;
  }

  public void setSkin(String skin) {
    this.skin = skin;
  }


  public String getBregma() {
    return bregma;
  }

  public void setBregma(String bregma) {
    this.bregma = bregma;
  }


  public String getBregmaHeight() {
    return bregmaHeight;
  }

  public void setBregmaHeight(String bregmaHeight) {
    this.bregmaHeight = bregmaHeight;
  }


  public String getBregmaWidth() {
    return bregmaWidth;
  }

  public void setBregmaWidth(String bregmaWidth) {
    this.bregmaWidth = bregmaWidth;
  }


  public String getEye() {
    return eye;
  }

  public void setEye(String eye) {
    this.eye = eye;
  }


  public String getEar() {
    return ear;
  }

  public void setEar(String ear) {
    this.ear = ear;
  }


  public String getHearing() {
    return hearing;
  }

  public void setHearing(String hearing) {
    this.hearing = hearing;
  }


  public String getMouth() {
    return mouth;
  }

  public void setMouth(String mouth) {
    this.mouth = mouth;
  }

  public String getBmouth() {
    return bmouth;
  }

  public void setBmouth(String bmouth) {
    this.bmouth = bmouth;
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


  public String getLimb() {
    return limb;
  }

  public void setLimb(String limb) {
    this.limb = limb;
  }


  public String getGait() {
    return gait;
  }

  public void setGait(String gait) {
    this.gait = gait;
  }


  public String getRicketsBody() {
    return ricketsBody;
  }

  public void setRicketsBody(String ricketsBody) {
    this.ricketsBody = ricketsBody;
  }


  public Integer getHemoglobin() {
    return hemoglobin;
  }

  public void setHemoglobin(Integer hemoglobin) {
    this.hemoglobin = hemoglobin;
  }


  public Integer getOutActivities() {
    return outActivities;
  }

  public void setOutActivities(Integer outActivities) {
    this.outActivities = outActivities;
  }


  public Integer getVitaminD() {
    return vitaminD;
  }

  public void setVitaminD(Integer vitaminD) {
    this.vitaminD = vitaminD;
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


  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
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


  public String getMedicine() {
    return medicine;
  }

  public void setMedicine(String medicine) {
    this.medicine = medicine;
  }

  public Date getNextDate() {
    return nextDate;
  }

  public void setNextDate(Date nextDate) {
    this.nextDate = nextDate;
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
