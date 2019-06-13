package com.yihu.ehr.health.SevereMentalFollow.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "severe_mental_follow")
@Access(value = AccessType.PROPERTY)
public class SevereMentalFollow  extends BaseAssignedEntity {
  @Column(name = "workId",  nullable = true)
  private String workId;/**  工作编号*/
  @Column(name = "name",  nullable = true)
  private String name;/**  姓名*/
  @Column(name = "peopleId",  nullable = true)
  private String peopleId;/**  身份证号*/
  @Column(name = "area",  nullable = true)
  private String area;/**  地区*/
  @Column(name = "follDate",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date follDate;/**  随访日期*/
  @Column(name = "formFollow",  nullable = true)
  private String formFollow;/**  本次随访形式*/
  @Column(name = "lostReason",  nullable = true)
  private String lostReason;/**  失访原因*/
  @Column(name = "dateDeath",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date dateDeath;/**  死亡日期*/
  @Column(name = "causeDeath",  nullable = true)
  private String causeDeath;/**  死亡原因*/
  @Column(name = "riskAssess",  nullable = true)
  private String riskAssess;/**  危险性评估*/
  @Column(name = "currentSymptoms",  nullable = true)
  private String currentSymptoms;/**  目前症状*/
  @Column(name = "selfKnowLedge",  nullable = true)
  private String selfKnowLedge;/**  自知力*/
  @Column(name = "sleepCondition",  nullable = true)
  private String sleepCondition;/**  睡眠情况*/
  @Column(name = "dietCondition",  nullable = true)
  private String dietCondition;/**  饮食情况*/
  @Column(name = "pelLifeCondition",  nullable = true)
  private String pelLifeCondition;/**  个人生活料理*/
  @Column(name = "houseWork",  nullable = true)
  private String houseWork;/**  家务劳动*/
  @Column(name = "proAndWork",  nullable = true)
  private String proAndWork;/**  生产劳动及工作*/
  @Column(name = "abilityLearn",  nullable = true)
  private String abilityLearn;/**  学习能力*/
  @Column(name = "socialCommu",  nullable = true)
  private String socialCommu;/**  社会人际交往*/
  @Column(name = "dangerBeha",  nullable = true)
  private String dangerBeha;/**  危险行为*/
  @Column(name = "twoLockCondition",  nullable = true)
  private String twoLockCondition;/**  两次随访期间关锁情况*/
  @Column(name = "twoInHospCondition",  nullable = true)
  private String twoInHospCondition;/**  两次随访期间住院情况*/
  @Column(name = "outHospitalDate",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date outHospitalDate;/**  末次出院时间*/
  @Column(name = "laborExamin",  nullable = true)
  private String laborExamin;/**  实验室检查*/
  @Column(name = "medCompliance",  nullable = true)
  private String medCompliance;/**  用药依从性*/
  @Column(name = "badDrugReact",  nullable = true)
  private String badDrugReact;/**  药物不良反应*/
  @Column(name = "treatEffect",  nullable = true)
  private String treatEffect;/**  治疗效果*/
  @Column(name = "ifReferral",  nullable = true)
  private String ifReferral;/**  是否转诊*/
  @Column(name = "referralReason",  nullable = true)
  private String referralReason;/**  转诊原因*/
  @Column(name = "refToDepart",  nullable = true)
  private String refToDepart;/**  转诊至机构及科室*/

  @Column(name = "useDrugCondiA",  nullable = true)
  private String useDrugCondiA;/**  用药情况药物1*/
  @Column(name = "useUsageA",  nullable = true)
  private String useUsageA;/**  用药情况用法1*/
  @Column(name = "useMeasurementA",  nullable = true)
  private String useMeasurementA;/**  用药情况计量1*/

  @Column(name = "useDrugCondiB",  nullable = true)
  private String useDrugCondiB;/**  用药情况药物2*/
  @Column(name = "useUsageB",  nullable = true)
  private String useUsageB;/**  用药情况用法2*/
  @Column(name = "useMeasurementB",  nullable = true)
  private String useMeasurementB;/**  用药情况计量2*/

  @Column(name = "useDrugCondiC",  nullable = true)
  private String useDrugCondiC;/**  用药情况药物3*/
  @Column(name = "useUsageC",  nullable = true)
  private String useUsageC;/**  用药情况用法3*/
  @Column(name = "useMeasurementC",  nullable = true)
  private String useMeasurementC;/**  用药情况计量3*/

  @Column(name = "guideUseDrugA",  nullable = true)
  private String guideUseDrugA;/**  用药指导药物1*/
  @Column(name = "guideUsageA",  nullable = true)
  private String guideUsageA;/**  用药指导用法1*/
  @Column(name = "guideMeasurementA",  nullable = true)
  private String guideMeasurementA;/**  用药指导药物1*/

  @Column(name = "guideUseDrugB",  nullable = true)
  private String guideUseDrugB;/**  用药指导药物2*/
  @Column(name = "guideUsageB",  nullable = true)
  private String guideUsageB;/**  用药指导用法2*/
  @Column(name = "guideMeasurementB",  nullable = true)
  private String guideMeasurementB;/**  用药指导药物2*/

  @Column(name = "guideUseDrugC",  nullable = true)
  private String guideUseDrugC;/**  用药指导药物3*/
  @Column(name = "guideUsageC",  nullable = true)
  private String guideUsageC;/**  用药指导用法3*/
  @Column(name = "guideMeasurementC",  nullable = true)
  private String guideMeasurementC;/**  用药指导药物3*/

  @Column(name = "rehaMeasure",  nullable = true)
  private String rehaMeasure;/**  康复措施*/
  @Column(name = "thisClassifi",  nullable = true)
  private String thisClassifi;/**  本次随分类*/
  @Column(name = "nextDate",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date nextDate;/**  下次随访日期*/
  @Column(name = "docSign",  nullable = true)
  private String docSign;/**  随访医生签名*/
  @Column(name = "deleteState",  nullable = true)
  private String deleteState; /**  删除状态*/


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


  public Date getFollDate() {
    return follDate;
  }

  public void setFollDate(Date follDate) {
    this.follDate = follDate;
  }


  public String getFormFollow() {
    return formFollow;
  }

  public void setFormFollow(String formFollow) {
    this.formFollow = formFollow;
  }


  public String getPeopleId() {
    return peopleId;
  }

  public void setPeopleId(String peopleId) {
    this.peopleId = peopleId;
  }


    public String getArea() { return area; }

    public void setArea(String area) {
        this.area = area;
    }


  public String getLostReason() {
    return lostReason;
  }

  public void setLostReason(String lostReason) {
    this.lostReason = lostReason;
  }


  public Date getDateDeath() {
    return dateDeath;
  }

  public void setDateDeath(Date dateDeath) {
    this.dateDeath = dateDeath;
  }


  public String getCauseDeath() {
    return causeDeath;
  }

  public void setCauseDeath(String causeDeath) {
    this.causeDeath = causeDeath;
  }


  public String getRiskAssess() {
    return riskAssess;
  }

  public void setRiskAssess(String riskAssess) {
    this.riskAssess = riskAssess;
  }


  public String getCurrentSymptoms() {
    return currentSymptoms;
  }

  public void setCurrentSymptoms(String currentSymptoms) {
    this.currentSymptoms = currentSymptoms;
  }


  public String getSelfKnowLedge() {
    return selfKnowLedge;
  }

  public void setSelfKnowLedge(String selfKnowLedge) {
    this.selfKnowLedge = selfKnowLedge;
  }


  public String getSleepCondition() {
    return sleepCondition;
  }

  public void setSleepCondition(String sleepCondition) {
    this.sleepCondition = sleepCondition;
  }


  public String getDietCondition() {
    return dietCondition;
  }

  public void setDietCondition(String dietCondition) {
    this.dietCondition = dietCondition;
  }


  public String getPelLifeCondition() {
    return pelLifeCondition;
  }

  public void setPelLifeCondition(String pelLifeCondition) {
    this.pelLifeCondition = pelLifeCondition;
  }


  public String getHouseWork() {
    return houseWork;
  }

  public void setHouseWork(String houseWork) {
    this.houseWork = houseWork;
  }


  public String getAbilityLearn() {
    return abilityLearn;
  }

  public void setAbilityLearn(String abilityLearn) {
    this.abilityLearn = abilityLearn;
  }


  public String getSocialCommu() {
    return socialCommu;
  }

  public void setSocialCommu(String socialCommu) {
    this.socialCommu = socialCommu;
  }


  public String getDangerBeha() {
    return dangerBeha;
  }

  public void setDangerBeha(String dangerBeha) {
    this.dangerBeha = dangerBeha;
  }


  public String getTwoLockCondition() {
    return twoLockCondition;
  }

  public void setTwoLockCondition(String twoLockCondition) {
    this.twoLockCondition = twoLockCondition;
  }


  public String getTwoInHospCondition() {
    return twoInHospCondition;
  }

  public void setTwoInHospCondition(String twoInHospCondition) {
    this.twoInHospCondition = twoInHospCondition;
  }


  public Date getOutHospitalDate() {
    return outHospitalDate;
  }

  public void setOutHospitalDate(Date outHospitalDate) { this.outHospitalDate = outHospitalDate; }


  public String getLaborExamin() {
    return laborExamin;
  }

  public void setLaborExamin(String laborExamin) {
    this.laborExamin = laborExamin;
  }


  public String getMedCompliance() {
    return medCompliance;
  }

  public void setMedCompliance(String medCompliance) {
    this.medCompliance = medCompliance;
  }


  public String getTreatEffect() {
    return treatEffect;
  }

  public void setTreatEffect(String treatEffect) {
    this.treatEffect = treatEffect;
  }


  public String getIfReferral() {
    return ifReferral;
  }

  public void setIfReferral(String ifReferral) {
    this.ifReferral = ifReferral;
  }


  public String getReferralReason() {
    return referralReason;
  }

  public void setReferralReason(String referralReason) {
    this.referralReason = referralReason;
  }


  public String getRefToDepart() {
    return refToDepart;
  }

  public void setRefToDepart(String refToDepart) {
    this.refToDepart = refToDepart;
  }


  public String getUseDrugCondiA() {
    return useDrugCondiA;
  }

  public void setUseDrugCondiA(String useDrugCondiA) {
    this.useDrugCondiA = useDrugCondiA;
  }


  public String getUseUsageA() {
    return useUsageA;
  }

  public void setUseUsageA(String useUsageA) {
    this.useUsageA = useUsageA;
  }


  public String getUseMeasurementA() {
    return useMeasurementA;
  }

  public void setUseMeasurementA(String useMeasurementA) {
    this.useMeasurementA = useMeasurementA;
  }


  public String getUseDrugCondiB() {
    return useDrugCondiB;
  }

  public void setUseDrugCondiB(String useDrugCondiB) {
    this.useDrugCondiB = useDrugCondiB;
  }


  public String getUseUsageB() {
    return useUsageB;
  }

  public void setUseUsageB(String useUsageB) {
    this.useUsageB = useUsageB;
  }


  public String getUseMeasurementB() {
    return useMeasurementB;
  }

  public void setUseMeasurementB(String useMeasurementB) {
    this.useMeasurementB = useMeasurementB;
  }


  public String getUseDrugCondiC() { return useDrugCondiC; }

  public void setUseDrugCondiC(String useDrugCondiC) { this.useDrugCondiC = useDrugCondiC; }


  public String getUseUsageC() { return useUsageC; }

  public void setUseUsageC(String useUsageC) { this.useUsageC = useUsageC; }


  public String getUseMeasurementC() { return useMeasurementC; }

  public void setUseMeasurementC(String useMeasurementC) { this.useMeasurementC = useMeasurementC; }



  public String getGuideUseDrugA() {
    return guideUseDrugA;
  }

  public void setGuideUseDrugA(String guideUseDrugA) {
    this.guideUseDrugA = guideUseDrugA;
  }


  public String getGuideUsageA() {
    return guideUsageA;
  }

  public void setGuideUsageA(String guideUsageA) {
    this.guideUsageA = guideUsageA;
  }


  public String getGuideMeasurementA() {
    return guideMeasurementA;
  }

  public void setGuideMeasurementA(String guideMeasurementA) {
    this.guideMeasurementA = guideMeasurementA;
  }


  public String getGuideUseDrugB() {
    return guideUseDrugB;
  }

  public void setGuideUseDrugB(String guideUseDrugB) { this.guideUseDrugB = guideUseDrugB; }


  public String getGuideUsageB() {
    return guideUsageB;
  }

  public void setGuideUsageB(String guideUsageB) { this.guideUsageB = guideUsageB; }


  public String getGuideMeasurementB() {
    return guideMeasurementB;
  }

  public void setGuideMeasurementB(String guideMeasurementB) { this.guideMeasurementB = guideMeasurementB; }


  public String getGuideUseDrugC() {
    return guideUseDrugC;
  }

  public void setGuideUseDrugC(String guideUseDrugC) {
    this.guideUseDrugC = guideUseDrugC;
  }


  public String getGuideUsageC() {
    return guideUsageC;
  }

  public void setGuideUsageC(String guideUsageC) {
    this.guideUsageC = guideUsageC;
  }


  public String getGuideMeasurementC() {
    return guideMeasurementC;
  }

  public void setGuideMeasurementC(String guideMeasurementC) {
    this.guideMeasurementC = guideMeasurementC;
  }


  public String getRehaMeasure() {
    return rehaMeasure;
  }

  public void setRehaMeasure(String rehaMeasure) {
    this.rehaMeasure = rehaMeasure;
  }


  public String getThisClassifi() {
    return thisClassifi;
  }

  public void setThisClassifi(String thisClassifi) {
    this.thisClassifi = thisClassifi;
  }


  public Date getNextDate() {
    return nextDate;
  }

  public void setNextDate(Date nextDate) {
    this.nextDate = nextDate;
  }


  public String getDocSign() {
    return docSign;
  }

  public void setDocSign(String docSign) {
    this.docSign = docSign;
  }



  public String getBadDrugReact() {
    return badDrugReact;
  }

  public void setBadDrugReact(String badDrugReact) {
    this.badDrugReact = badDrugReact;
  }


  public String getProAndWork() {
    return proAndWork;
  }

  public void setProAndWork(String proAndWork) {
    this.proAndWork = proAndWork;
  }


  public String getDeleteState() { return deleteState; }

  public void setDeleteState(String deleteState) { this.deleteState = deleteState; }

}
