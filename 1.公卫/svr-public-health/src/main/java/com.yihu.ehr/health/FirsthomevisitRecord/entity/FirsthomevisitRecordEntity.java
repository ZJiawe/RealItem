package com.yihu.ehr.health.FirsthomevisitRecord.entity;

import com.yihu.ehr.entity.BaseAssignedEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Access(value = AccessType.PROPERTY)
@Table(name = "firsthomevisit_record", schema = "doc", catalog = "")
public class FirsthomevisitRecordEntity extends BaseAssignedEntity {
  @Column(name = "name",  nullable = true)
  private String name;//姓名

  @Column(name = "sex",  nullable = true)
  private String sex;//性别

  @Column(name = "age",  nullable = true)
  private Integer age;//年龄

  @Column(name = "ethnic",  nullable = true)
  private String ethnic;//民族

  @Column(name = "area",  nullable = true)
  private String area;//区域

  @Column(name = "inputDate",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date inputDate;//建档时间

  @Column(name = "inputPerson",  nullable = true)
  private String inputPerson;//建档人

  @Column(name = "peopleId",  nullable = true)
  private String peopleId;//身份证号

  @Column(name = "phone",  nullable = true)
  private String phone;//联系电话

  @Column(name = "responDoctor",  nullable = true)
  private String responDoctor;//责任医生

  @Column(name = "recentVisit",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date recentVisit;//最近随访

  @Column(name = "lifeState",  nullable = true)
  private String lifeState;//生存状态

  @Column(name = "headeName",  nullable = true)
  private String fNumber;//随访次数

  @Column(name = "management",  nullable = true)
  private String management;//已被管理

  @Column(name = "ruleMedicine",  nullable = true)
  private String ruleMedicine;//规则服药


  @Column(name = "followTime",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date followTime;//随访时间

  @Column(name = "followMethod",  nullable = true)
  private String followMethod;//随访方式

  @Column(name = "patienType",  nullable = true)
  private String patienType;//患者类型

  @Column(name = "sputumStatus",  nullable = true)
  private String sputumStatus;//痰情况菌

  @Column(name = "drugStatus",  nullable = true)
  private String drugStatus;//耐药情况

  @Column(name = "symptomsSigns",  nullable = true)
  private String symptomsSigns;//症状及体征

  @Column(name = "otherSymptoms",  nullable = true)
  private String otherSymptoms;//其他症状

  @Column(name = "chemotherapyRegimen",  nullable = true)
  private String chemotherapyRegimen;//化疗方案

  @Column(name = "usages",  nullable = true)
  private String usages;//用法

  @Column(name = "drugDosage",  nullable = true)
  private String drugDosage;//药品剂型

  @Column(name = "supervisorSelection",  nullable = true)
  private String supervisorSelection;//督导人选择

  @Column(name = "separateRoom",  nullable = true)
  private String separateRoom;//单独的居室

  @Column(name = "ventilation",  nullable = true)
  private String ventilation;//通风情况

  @Column(name = "smoking",  nullable = true)
  private String smoking;//吸烟

  @Column(name = "drinking",  nullable = true)
  private String drinking;//饮酒

  @Column(name = "takemedicineTime",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date takemedicineTime;//取药时间

  @Column(name = "takemedicinePlace",  nullable = true)
  private String takemedicinePlace;//取药地点

  @Column(name = "medicationRecordcard",  nullable = true)
  private String medicationRecordcard;//服药记录卡的填写

  @Column(name = "medicationmethodDrugstorage",  nullable = true)
  private String medicationmethodDrugstorage;//服药方法及药品存放

  @Column(name = "treatmentTuberculosis",  nullable = true)
  private String treatmentTuberculosis;//肺结核治疗疗程

  @Column(name = "irregularMedication",  nullable = true)
  private String irregularMedication;//不规律服药危害

  @Column(name = "adverseReactions",  nullable = true)
  private String adverseReactions;//服药后不良反应及处理

  @Column(name = "review",  nullable = true)
  private String review;//治疗期间复诊查痰

  @Column(name = "howtakeMedicine",  nullable = true)
  private String howtakeMedicine;//外出期间如何坚持服药

  @Column(name = "habitsPrecautions",  nullable = true)
  private String habitsPrecautions;//生活习惯及注意事项

  @Column(name = "closeCheck",  nullable = true)
  private String closeCheck;//密切接触者检查

  @Column(name = "nextFollowtie",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date nextFollowtie;//下次随访时间

  @Column(name = "evaluationDoctorsignature",  nullable = true)
  private String evaluationDoctorsignature;//评估医生签名

  /**  删除状态*/
  @Column(name = "delete_state",  nullable = true)
  private String deleteState;



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


  public Date getFollowTime() {
    return followTime;
  }

  public void setFollowTime(Date followTime) {
    this.followTime = followTime;
  }


  public String getFollowMethod() {
    return followMethod;
  }

  public void setFollowMethod(String followMethod) {
    this.followMethod = followMethod;
  }


  public String getPatienType() {
    return patienType;
  }

  public void setPatienType(String patienType) {
    this.patienType = patienType;
  }


  public String getSputumStatus() {
    return sputumStatus;
  }

  public void setSputumStatus(String sputumStatus) {
    this.sputumStatus = sputumStatus;
  }


  public String getDrugStatus() {
    return drugStatus;
  }

  public void setDrugStatus(String drugStatus) {
    this.drugStatus = drugStatus;
  }


  public String getSymptomsSigns() {
    return symptomsSigns;
  }

  public void setSymptomsSigns(String symptomsSigns) {
    this.symptomsSigns = symptomsSigns;
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


  public String getSupervisorSelection() {
    return supervisorSelection;
  }

  public void setSupervisorSelection(String supervisorSelection) {
    this.supervisorSelection = supervisorSelection;
  }


  public String getSeparateRoom() {
    return separateRoom;
  }

  public void setSeparateRoom(String separateRoom) {
    this.separateRoom = separateRoom;
  }


  public String getVentilation() {
    return ventilation;
  }

  public void setVentilation(String ventilation) {
    this.ventilation = ventilation;
  }


  public String getSmoking() {
    return smoking;
  }

  public void setSmoking(String smoking) {
    this.smoking = smoking;
  }


  public String getDrinking() {
    return drinking;
  }

  public void setDrinking(String drinking) {
    this.drinking = drinking;
  }


  public Date getTakemedicineTime() {
    return takemedicineTime;
  }

  public void setTakemedicineTime(Date takemedicineTime) {
    this.takemedicineTime = takemedicineTime;
  }


  public String getMedicationRecordcard() {
    return medicationRecordcard;
  }

  public void setMedicationRecordcard(String medicationRecordcard) {
    this.medicationRecordcard = medicationRecordcard;
  }


  public String getMedicationmethodDrugstorage() {
    return medicationmethodDrugstorage;
  }

  public void setMedicationmethodDrugstorage(String medicationmethodDrugstorage) {
    this.medicationmethodDrugstorage = medicationmethodDrugstorage;
  }


  public String getTreatmentTuberculosis() {
    return treatmentTuberculosis;
  }

  public void setTreatmentTuberculosis(String treatmentTuberculosis) {
    this.treatmentTuberculosis = treatmentTuberculosis;
  }


  public String getIrregularMedication() {
    return irregularMedication;
  }

  public void setIrregularMedication(String irregularMedication) {
    this.irregularMedication = irregularMedication;
  }


  public String getAdverseReactions() {
    return adverseReactions;
  }

  public void setAdverseReactions(String adverseReactions) {
    this.adverseReactions = adverseReactions;
  }


  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }


  public String getHowtakeMedicine() {
    return howtakeMedicine;
  }

  public void setHowtakeMedicine(String howtakeMedicine) {
    this.howtakeMedicine = howtakeMedicine;
  }


  public String getHabitsPrecautions() {
    return habitsPrecautions;
  }

  public void setHabitsPrecautions(String habitsPrecautions) {
    this.habitsPrecautions = habitsPrecautions;
  }


  public String getCloseCheck() {
    return closeCheck;
  }

  public void setCloseCheck(String closeCheck) {
    this.closeCheck = closeCheck;
  }


  public Date getNextFollowtie() {
    return nextFollowtie;
  }

  public void setNextFollowtie(Date nextFollowtie) {
    this.nextFollowtie = nextFollowtie;
  }


  public String getEvaluationDoctorsignature() {
    return evaluationDoctorsignature;
  }

  public void setEvaluationDoctorsignature(String evaluationDoctorsignature) {
    this.evaluationDoctorsignature = evaluationDoctorsignature;
  }



  public String getOtherSymptoms() {
    return otherSymptoms;
  }
  public void setOtherSymptoms(String otherSymptoms) {
    this.otherSymptoms = otherSymptoms;
  }

  public String getTakemedicinePlace() {
    return takemedicinePlace;
  }
  public void setTakemedicinePlace(String takemedicinePlace) {
    this.takemedicinePlace = takemedicinePlace;
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
  public void setAge(int age) {
    this.age = age;
  }

  public String getEthnic() {
    return ethnic;
  }
  public void setEthnic(String ethnic) {
    this.ethnic = ethnic;
  }

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

  public String getInputPerson() {
    return inputPerson;
  }
  public void setInputPerson(String inputPerson) {
    this.inputPerson = inputPerson;
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

  public Date getRecentVisit() {
    return recentVisit;
  }
  public void setRecentVisit(Date recentVisit) {
    this.recentVisit = recentVisit;
  }

  public String getLifeState() {
    return lifeState;
  }
  public void setLifeState(String lifeState) {
    this.lifeState = lifeState;
  }

  public String getfNumber() {
    return fNumber;
  }
  public void setfNumber(String fNumber) {
    this.fNumber = fNumber;
  }

  public String getManagement() {
    return management;
  }
  public void setManagement(String management) {
    this.management = management;
  }

  public String getRuleMedicine() {
    return ruleMedicine;
  }
  public void setRuleMedicine(String ruleMedicine) {
    this.ruleMedicine = ruleMedicine;
  }
}
