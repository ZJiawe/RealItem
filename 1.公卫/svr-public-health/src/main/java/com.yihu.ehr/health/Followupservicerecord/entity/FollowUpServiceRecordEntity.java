package com.yihu.ehr.health.Followupservicerecord.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import javax.persistence.*;
import java.util.Date;
@Entity
@Access(value = AccessType.PROPERTY)
@Table(name = "follow_up_service_record", schema = "doc", catalog = "")

public class FollowUpServiceRecordEntity extends BaseAssignedEntity {

  @Column(name = "name",  nullable = true)
  private String name;//姓名

  @Column(name = "flowUpTime",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date flowUpTime;//随访时间

  @Column(name = "treatmentMonth",  nullable = true)
  private String treatmentMonth;//治疗月序

  @Column(name = "superivisor",  nullable = true)
  private String superivisor;//督导人员

  @Column(name = "followUpMethods",  nullable = true)
  private String followUpMethods;//随访方式

  @Column(name = "symptomsSigns",  nullable = true)
  private String symptomsSigns;//症状及体征

  @Column(name = "otherSymptoms",  nullable = true)
  private String otherSymptoms;//其他症状

  @Column(name = "smoking",  nullable = true)
  private String smoking;//吸烟

  @Column(name = "alcohol",  nullable = true)
  private String alcohol;//喝酒

  @Column(name = "chemotherapyRegimen",  nullable = true)
  private String chemotherapyRegimen;//化疗方案

  @Column(name = "usages",  nullable = true)
  private String usages;//用法

  @Column(name = "drugDosage",  nullable = true)
  private String drugDosage;//药品剂型

  @Column(name = "numbermissedMedications",  nullable = true)
  private String numbermissedMedications;//漏服药次数

  @Column(name = "adverseDrugReactions",  nullable = true)
  private String adverseDrugReactions;//药物不良反应

  @Column(name = "adverseReactions",  nullable = true)
  private String adverseReactions;//不良反应

  @Column(name = "complicationComorbidities",  nullable = true)
  private String complicationComorbidities;//有无并发症或合并症

  @Column(name = "complications",  nullable = true)
  private String complications;//并发症

  @Column(name = "subjects",  nullable = true)
  private String subjects;//科别

  @Column(name = "causes",  nullable = true)
  private String causes;//原因

  @Column(name = "twoweeksResult",  nullable = true)
  private String twoweeksResult;//2周内随访，随访结果

  @Column(name = "treamntOptions",  nullable = true)
  private String treamntOptions;//处理意见

  @Column(name = "nextFollotime",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date nextFollotime;//下次随访时间

  @Column(name = "followdoctorSignature",  nullable = true)
  private String followdoctorSignature;//随访医生签名

  @Column(name = "stopTime",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date stopTime;//停止治疗时间

  @Column(name = "stopTreatment",  nullable = true)
  private String stopTreatment;//停止治疗原因

  @Column(name = "shouldVisit",  nullable = true)
  private String shouldVisit;//应访视患者次数

  @Column(name = "mnumner",  nullable = true)
  private String mnumner;//应服药次数

  @Column(name = "actualVisit",  nullable = true)
  private String actualVisit;//实际访问次数

  @Column(name = "amnumber",  nullable = true)
  private String amnumber;//实际服药次数

  @Column(name = "medicineRate",  nullable = true)
  private String medicineRate;//服药率

  @Column(name = "evaluationDoctor",  nullable = true)
  private String evaluationDoctor;//评估医生

  @Column(name = "peopleId",  nullable = true)
  private String peopleId;//身份证号

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

  public String getDeleteState() { return deleteState; }

  public void setDeleteState(String deleteState) {
    this.deleteState = deleteState;
  }


  public Date getFlowUpTime() {
    return flowUpTime;
  }

  public void setFlowUpTime(Date flowUpTime) {
    this.flowUpTime = flowUpTime;
  }


  public String getTreatmentMonth() {
    return treatmentMonth;
  }

  public void setTreatmentMonth(String treatmentMonth) {
    this.treatmentMonth = treatmentMonth;
  }


  public String getSuperivisor() {
    return superivisor;
  }

  public void setSuperivisor(String superivisor) {
    this.superivisor = superivisor;
  }


  public String getFollowUpMethods() {
    return followUpMethods;
  }

  public void setFollowUpMethods(String followUpMethods) {
    this.followUpMethods = followUpMethods;
  }


  public String getSymptomsSigns() {
    return symptomsSigns;
  }

  public void setSymptomsSigns(String symptomsSigns) {
    this.symptomsSigns = symptomsSigns;
  }


  public String getSmoking() {
    return smoking;
  }

  public void setSmoking(String smoking) {
    this.smoking = smoking;
  }


  public String getAlcohol() {
    return alcohol;
  }

  public void setAlcohol(String alcohol) {
    this.alcohol = alcohol;
  }


  public String getChemotherapyRegimen() {
    return chemotherapyRegimen;
  }

  public void setChemotherapyRegimen(String chemotherapyRegimen) {
    this.chemotherapyRegimen = chemotherapyRegimen;
  }


  public String getUsages() {
    return usages;
  }

  public void setUsages(String usages) {
    this.usages = usages;
  }


  public String getDrugDosage() {
    return drugDosage;
  }

  public void setDrugDosage(String drugDosage) {
    this.drugDosage = drugDosage;
  }


  public String getNumbermissedMedications() {
    return numbermissedMedications;
  }

  public void setNumbermissedMedications(String numbermissedMedications) {
    this.numbermissedMedications = numbermissedMedications;
  }


  public String getAdverseDrugReactions() {
    return adverseDrugReactions;
  }

  public void setAdverseDrugReactions(String adverseDrugReactions) {
    this.adverseDrugReactions = adverseDrugReactions;
  }


  public String getComplicationComorbidities() {
    return complicationComorbidities;
  }

  public void setComplicationComorbidities(String complicationComorbidities) {
    this.complicationComorbidities = complicationComorbidities;
  }


  public String getSubjects() {
    return subjects;
  }

  public void setSubjects(String subjects) {
    this.subjects = subjects;
  }


  public String getCauses() {
    return causes;
  }

  public void setCauses(String causes) {
    this.causes = causes;
  }


  public String getTwoweeksResult() {
    return twoweeksResult;
  }

  public void setTwoweeksResult(String twoweeksResult) {
    this.twoweeksResult = twoweeksResult;
  }


  public String getTreamntOptions() {
    return treamntOptions;
  }

  public void setTreamntOptions(String treamntOptions) {
    this.treamntOptions = treamntOptions;
  }


  public Date getNextFollotime() {
    return nextFollotime;
  }

  public void setNextFollotime(Date nextFollotime) {
    this.nextFollotime = nextFollotime;
  }


  public String getFollowdoctorSignature() {
    return followdoctorSignature;
  }

  public void setFollowdoctorSignature(String followdoctorSignature) {
    this.followdoctorSignature = followdoctorSignature;
  }


  public String getStopTreatment() {
    return stopTreatment;
  }

  public void setStopTreatment(String stopTreatment) {
    this.stopTreatment = stopTreatment;
  }

  public String getActualVisit() {
    return actualVisit;
  }

  public void setActualVisit(String actualVisit) {
    this.actualVisit = actualVisit;
  }



  public String getMnumner() {
    return mnumner;
  }
  public void setMnumner(String mnumner) {
    this.mnumner = mnumner;
  }

  public Date getStopTime() {
    return stopTime;
  }
  public void setStopTime(Date stopTime) {
    this.stopTime = stopTime;
  }

  public String getShouldVisit() {
    return shouldVisit;
  }
  public void setShouldVisit(String shouldVisit) {
    this.shouldVisit = shouldVisit;
  }

  public String getAmnumber() {
    return amnumber;
  }
  public void setAmnumber(String amnumber) {
    this.amnumber = amnumber;
  }

  public String getMedicineRate() {
    return medicineRate;
  }
  public void setMedicineRate(String medicineRate) {
    this.medicineRate = medicineRate;
  }

  public String getEvaluationDoctor() {
    return evaluationDoctor;
  }
  public void setEvaluationDoctor(String evaluationDoctor) {
    this.evaluationDoctor = evaluationDoctor;
  }

  public String getAdverseReactions() {
    return adverseReactions;
  }
  public void setAdverseReactions(String adverseReactions) {
    this.adverseReactions = adverseReactions;
  }

  public String getComplications() {
    return complications;
  }
  public void setComplications(String complications) {
    this.complications = complications;
  }

  public String getOtherSymptoms() {
    return otherSymptoms;
  }
  public void setOtherSymptoms(String otherSymptoms) {
    this.otherSymptoms = otherSymptoms;
  }
}
