package com.yihu.ehr.health.SevereMentalSupplement.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "severe_mental_supplement")
@Access(value = AccessType.PROPERTY)
public class SevereMentalSupplement extends BaseAssignedEntity {
  @Column(name = "workId",  nullable = true)
  private String workId;/**  工作编号*/
  @Column(name = "name",  nullable = true)
  private String name;/**  姓名*/
  @Column(name = "peopleId",  nullable = true)
  private String peopleId;/**  身份证号*/
  @Column(name = "area",  nullable = true)
  private String area;/**  地区*/
  @Column(name = "guardianName",  nullable = true)
  private String guardianName;/**  监护人姓名*/
  @Column(name = "relaPatients",  nullable = true)
  private String relaPatients;/**  与患者关系*/
  @Column(name = "guardianAddre",  nullable = true)
  private String guardianAddre;/**  监护人住址*/
  @Column(name = "guardPhone",  nullable = true)
  private String guardPhone;/**  监护人电话*/
  @Column(name = "commitPersonName",  nullable = true)
  private String commitPersonName;/**  辖区村委会联系人*/
  @Column(name = "commitPersonNum",  nullable = true)
  private String commitPersonNum;/**  辖区村委会联系人电话*/
  @Column(name = "accountType",  nullable = true)
  private String accountType;/**  户别*/
  @Column(name = "employSituat",  nullable = true)
  private String employSituat;/**  就业情况*/
  @Column(name = "infConsent",  nullable = true)
  private String infConsent;/**  知情同意*/
  @Column(name = "signatory",  nullable = true)
  private String signatory;/**  签字人*/
  @Column(name = "signatureDate",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date signatureDate;/**  签字日期*/
  @Column(name = "firstOnsetTime",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date firstOnsetTime;/**  初次发病时间*/
  @Column(name = "pastSymptoms",  nullable = true)
  private String pastSymptoms;/**  既往主要症状*/
  @Column(name = "pastLocking",  nullable = true)
  private String pastLocking;/**  既往关锁情况*/
  @Column(name = "outService",  nullable = true)
  private String outService;/**  门诊*/
  @Column(name = "firstAntipsyTreat",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date firstAntipsyTreat;/**  首次抗精神病治疗时间*/
  @Column(name = "inHospital",  nullable = true)
  private String inHospital;/**  住院*/
  @Column(name = "diagnosis",  nullable = true)
  private String diagnosis;/**  诊断*/
  @Column(name = "hospitalDia",  nullable = true)
  private String hospitalDia;/**  确诊医院*/
  @Column(name = "confiDate",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date confiDate;/**  确诊日期*/
  @Column(name = "lastTreatEff",  nullable = true)
  private String lastTreatEff;/**  最近一次治疗效果*/
  @Column(name = "dangerBehav",  nullable = true)
  private String dangerBehav;/**  危险行为*/
  @Column(name = "stateEconomy",  nullable = true)
  private String stateEconomy;/**  经济状况*/
  @Column(name = "speciaOpinion",  nullable = true)
  private String speciaOpinion;/**  专科医生的意见*/
  @Column(name = "dateFill",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date dateFill;/**  填表日期*/
  @Column(name = "docSign",  nullable = true)
  private String docSign;/**  医生签字*/
  @Column(name = "deleteState",  nullable = true)
  private String deleteState; /**  删除状态*/
  @Column(name = "livingCondition",  nullable = true)
  private String livingCondition;//生存状态
  @Column(name = "management",  nullable = true)
  private String management;//规范管理


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


  public String getGuardianName() {
    return guardianName;
  }

  public void setGuardianName(String guardianName) {
    this.guardianName = guardianName;
  }


  public String getRelaPatients() {
    return relaPatients;
  }

  public void setRelaPatients(String relaPatients) {
    this.relaPatients = relaPatients;
  }


  public String getPeopleId() {
    return peopleId;
  }

  public void setPeopleId(String peopleId) {
    this.peopleId = peopleId;
  }


  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }


  public String getGuardianAddre() {
    return guardianAddre;
  }

  public void setGuardianAddre(String guardianAddre) {
    this.guardianAddre = guardianAddre;
  }


  public String getGuardPhone() {
    return guardPhone;
  }

  public void setGuardPhone(String guardPhone) {
    this.guardPhone = guardPhone;
  }


  public String getCommitPersonName() {
    return commitPersonName;
  }

  public void setCommitPersonName(String commitPersonName) {
    this.commitPersonName = commitPersonName;
  }


  public String getCommitPersonNum() {
    return commitPersonNum;
  }

  public void setCommitPersonNum(String commitPersonNum) {
    this.commitPersonNum = commitPersonNum;
  }


  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }


  public String getEmploySituat() {
    return employSituat;
  }

  public void setEmploySituat(String employSituat) {
    this.employSituat = employSituat;
  }


  public String getInfConsent() {
    return infConsent;
  }

  public void setInfConsent(String infConsent) {
    this.infConsent = infConsent;
  }


  public String getSignatory() {
    return signatory;
  }

  public void setSignatory(String signatory) {
    this.signatory = signatory;
  }


  public Date getSignatureDate() {
    return signatureDate;
  }

  public void setSignatureDate(Date signatureDate) {
    this.signatureDate = signatureDate;
  }


  public Date getFirstOnsetTime() {
    return firstOnsetTime;
  }

  public void setFirstOnsetTime(Date firstOnsetTime) {
    this.firstOnsetTime = firstOnsetTime;
  }


  public String getPastSymptoms() {
    return pastSymptoms;
  }

  public void setPastSymptoms(String pastSymptoms) {
    this.pastSymptoms = pastSymptoms;
  }


  public String getPastLocking() {
    return pastLocking;
  }

  public void setPastLocking(String pastLocking) {
    this.pastLocking = pastLocking;
  }


  public String getOutService() {
    return outService;
  }

  public void setOutService(String outService) {
    this.outService = outService;
  }


  public Date getFirstAntipsyTreat() {
    return firstAntipsyTreat;
  }

  public void setFirstAntipsyTreat(Date firstAntipsyTreat) {
    this.firstAntipsyTreat = firstAntipsyTreat;
  }


  public String getInHospital() {
    return inHospital;
  }

  public void setInHospital(String inHospital) {
    this.inHospital = inHospital;
  }


  public String getDiagnosis() {
    return diagnosis;
  }

  public void setDiagnosis(String diagnosis) {
    this.diagnosis = diagnosis;
  }


  public String getHospitalDia() {
    return hospitalDia;
  }

  public void setHospitalDia(String hospitalDia) {
    this.hospitalDia = hospitalDia;
  }


  public Date getConfiDate() {
    return confiDate;
  }

  public void setConfiDate(Date confiDate) {
    this.confiDate = confiDate;
  }


  public String getLastTreatEff() {
    return lastTreatEff;
  }

  public void setLastTreatEff(String lastTreatEff) {
    this.lastTreatEff = lastTreatEff;
  }


  public String getDangerBehav() {
    return dangerBehav;
  }

  public void setDangerBehav(String dangerBehav) {
    this.dangerBehav = dangerBehav;
  }


  public String getStateEconomy() {
    return stateEconomy;
  }

  public void setStateEconomy(String stateEconomy) {
    this.stateEconomy = stateEconomy;
  }


  public String getSpeciaOpinion() {
    return speciaOpinion;
  }

  public void setSpeciaOpinion(String speciaOpinion) {
    this.speciaOpinion = speciaOpinion;
  }


  public Date getDateFill() {
    return dateFill;
  }

  public void setDateFill(Date dateFill) {
    this.dateFill = dateFill;
  }


  public String getDocSign() {
    return docSign;
  }

  public void setDocSign(String docSign) {
    this.docSign = docSign;
  }


  public String getDeleteState() { return deleteState; }

  public void setDeleteState(String deleteState) { this.deleteState = deleteState; }


  public String getLivingCondition() {
    return livingCondition;
  }

  public void setLivingCondition(String livingCondition) {
    this.livingCondition = livingCondition;
  }


  public String getManagement() {
    return management;
  }

  public void setManagement(String management) {
    this.management = management;
  }

}
