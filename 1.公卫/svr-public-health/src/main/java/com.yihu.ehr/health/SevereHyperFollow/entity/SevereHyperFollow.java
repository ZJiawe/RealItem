package com.yihu.ehr.health.SevereHyperFollow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "hyper_manag")
@Access(value = AccessType.PROPERTY)
public class SevereHyperFollow extends BaseAssignedEntity {
  public SevereHyperFollow(){ }
  @Column(name = "name",  nullable = true)
  private String name;//高血压患者姓名
  @Column(name = "workId",  nullable = true)
  private String workId;//高血压患者姓名
  /**  身份证号*/
  @Column(name = "area",  nullable = true)
  private String area;
  @Column(name = "people_id",  nullable = true)
  private String peopleId;
  @Column(name = "total",  nullable = true)
  private String total;
  @Column(name = "ismanagement",  nullable = true)
  private String ismanagement;
  @JsonFormat(locale = "zh",timezone = "GMT+8", pattern = "yyyy-MM-dd")
  private Date follUpdate;//随访日期
  @Column(name = "foll_type",  nullable = true)
  private String follType;//随访方式
  @Column(name = "symptoms",  nullable = true)
  private String symptoms;//症状
  @Column(name = "number",  nullable = true)
  private String number;
  @Column(name = "delete_state",  nullable = true)
  private String deleteState;
  @Column(name = "blood_pressure",  nullable = true)
  private Integer bloodPressure;//血压
  @Column(name = "weight",  nullable = true)
  private Integer weight;//体重
  @Column(name = "bmi",  nullable = true)
  private Integer bmi;//体质指数
  @Column(name = "heart_rate",  nullable = true)
  private Integer heartRate;//心率
  @Column(name = "other_phy",  nullable = true)
  private String otherPhy;//其他（体征的）
  @Column(name = "smoke_per",  nullable = true)
  private String smokePer;//日吸烟量
  @Column(name = "drink_per",  nullable = true)
  private String drinkPer;//日饮酒量
  @Column(name = "sport",  nullable = true)
  private String sport;//运动
  @Column(name = "salt_situation",  nullable = true)
  private String saltSituation;//摄盐情况
  @Column(name = "psy_adjust",  nullable = true)
  private String psyAdjust;//心理调整
  @Column(name = "obey_medical",  nullable = true)
  private String obeyMedical;//遵医行为
  @Column(name = "aux_examina",  nullable = true)
  private String auxExamina;//辅助检查
  @Column(name = "medi_compli",  nullable = true)
  private String mediCompli;//服药依从性
  @Column(name = "adverdrug_react",  nullable = true)
  private String adverdrugReact;//药物不良反应（+有）
  @Column(name = "access_type",  nullable = true)
  private String accessType;//此次随访分类
  @Column(name = "drug_name",  nullable = true)
  private String drugName;//药物名称
  @Column(name = "drug_usage",  nullable = true)
  private String drugUsage;//用法用量
  @Column(name = "oth_drug_name",  nullable = true)
  private String othDrugName;//其他药物名
  @Column(name = "other_drugu",  nullable = true)
  private String otherDrugU;//其他药物用法用量
  @Column(name = "oth_drug_name_san",  nullable = true)
  private String othDrugNameSan;//药物名3
  @Column(name = "other_drugusan",  nullable = true)
  private String otherDrugUSan;//药物用法用量3
  @Column(name = "oth_drug_name_si",  nullable = true)
  private String othDrugNameSi;//药物名4
  @Column(name = "other_drugusi",  nullable = true)
  private String otherDrugUSi;//药物用法用量4
  @Column(name = "referral_reason",  nullable = true)
  private String referralReason;//转诊原因
  @Column(name = "organ_and_div",  nullable = true)
  private String organAndDiv;//机构及科别
  @JsonFormat(locale = "zh",timezone = "GMT+8", pattern = "yyyy-MM-dd")
  private Date dateNextFollow;//下次随访日期
  @Column(name = "doc_signature",  nullable = true)
  private String docSignature;//随访医生签名


  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }

  public String getIsmanagement() {
    return ismanagement;
  }

  public void setIsmanagement(String ismanagement) {
    this.ismanagement = ismanagement;
  }

  public String getWorkId() {
    return workId;
  }

  public void setWorkId(String workId) {
    this.workId = workId;
  }

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

  public Date getFollUpdate() {
    return follUpdate;
  }

  public void setFollUpdate(Date follUpdate) {
    this.follUpdate = follUpdate;
  }


  public String getFollType() {
    return follType;
  }

  public void setFollType(String follType) {
    this.follType = follType;
  }


  public String getSymptoms() {
    return symptoms;
  }

  public void setSymptoms(String symptoms) {
    this.symptoms = symptoms;
  }


  public Integer getBloodPressure() {
    return bloodPressure;
  }

  public void setBloodPressure(Integer bloodPressure) {
    this.bloodPressure = bloodPressure;
  }


  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }


  public Integer getBmi() {
    return bmi;
  }

  public void setBmi(Integer bmi) {
    this.bmi = bmi;
  }


  public Integer getHeartRate() {
    return heartRate;
  }

  public void setHeartRate(Integer heartRate) {
    this.heartRate = heartRate;
  }


  public String getOtherPhy() {
    return otherPhy;
  }

  public void setOtherPhy(String otherPhy) {
    this.otherPhy = otherPhy;
  }


  public String getSmokePer() {
    return smokePer;
  }

  public void setSmokePer(String smokePer) {
    this.smokePer = smokePer;
  }


  public String getDrinkPer() {
    return drinkPer;
  }

  public void setDrinkPer(String drinkPer) {
    this.drinkPer = drinkPer;
  }


  public String getSport() {
    return sport;
  }

  public void setSport(String sport) {
    this.sport = sport;
  }


  public String getSaltSituation() {
    return saltSituation;
  }

  public void setSaltSituation(String saltSituation) {
    this.saltSituation = saltSituation;
  }


  public String getPsyAdjust() {
    return psyAdjust;
  }

  public void setPsyAdjust(String psyAdjust) {
    this.psyAdjust = psyAdjust;
  }


  public String getObeyMedical() {
    return obeyMedical;
  }

  public void setObeyMedical(String obeyMedical) {
    this.obeyMedical = obeyMedical;
  }


  public String getAuxExamina() {
    return auxExamina;
  }

  public void setAuxExamina(String auxExamina) {
    this.auxExamina = auxExamina;
  }


  public String getMediCompli() {
    return mediCompli;
  }

  public void setMediCompli(String mediCompli) {
    this.mediCompli = mediCompli;
  }


  public String getAdverdrugReact() {
    return adverdrugReact;
  }

  public void setAdverdrugReact(String adverdrugReact) {
    this.adverdrugReact = adverdrugReact;
  }


  public String getAccessType() {
    return accessType;
  }

  public void setAccessType(String accessType) {
    this.accessType = accessType;
  }


  public String getDrugName() {
    return drugName;
  }

  public void setDrugName(String drugName) {
    this.drugName = drugName;
  }


  public String getDrugUsage() {
    return drugUsage;
  }

  public void setDrugUsage(String drugUsage) {
    this.drugUsage = drugUsage;
  }


  public String getOthDrugName() {
    return othDrugName;
  }

  public void setOthDrugName(String othDrugName) {
    this.othDrugName = othDrugName;
  }


  public String getOtherDrugU() {
    return otherDrugU;
  }

  public void setOtherDrugU(String otherDrugU) {
    this.otherDrugU = otherDrugU;
  }

  public String getOthDrugNameSan() {
    return othDrugNameSan;
  }

  public void setOthDrugNameSan(String othDrugNameSan) {
    this.othDrugNameSan = othDrugNameSan;
  }


  public String getOtherDrugUSan() {
    return otherDrugUSan;
  }

  public void setOtherDrugUSan(String otherDrugUSan) {
    this.otherDrugUSan = otherDrugUSan;
  }

  public String getOthDrugNameSi() {
    return othDrugNameSi;
  }

  public void setOthDrugNameSi(String othDrugNameSi) {
    this.othDrugNameSi = othDrugNameSi;
  }


  public String getOtherDrugUSi() {
    return otherDrugUSi;
  }

  public void setOtherDrugUSi(String otherDrugUSi) {
    this.otherDrugUSi = otherDrugUSi;
  }

  public String getReferralReason() {
    return referralReason;
  }

  public void setReferralReason(String referralReason) {
    this.referralReason = referralReason;
  }


  public String getOrganAndDiv() {
    return organAndDiv;
  }

  public void setOrganAndDiv(String organAndDiv) {
    this.organAndDiv = organAndDiv;
  }


  public Date getDateNextFollow() {
    return dateNextFollow;
  }

  public void setDateNextFollow(Date dateNextFollow) {
    this.dateNextFollow = dateNextFollow;
  }


  public String getDocSignature() {
    return docSignature;
  }

  public void setDocSignature(String docSignature) {
    this.docSignature = docSignature;
  }


    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }

    public String getDeleteState() {
        return deleteState;
    }
}
