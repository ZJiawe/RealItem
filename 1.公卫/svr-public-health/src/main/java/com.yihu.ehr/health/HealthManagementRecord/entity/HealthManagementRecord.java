package com.yihu.ehr.health.HealthManagementRecord.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.yihu.ehr.entity.BaseAssignedEntity;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "health_management_ecord")
@Access(value = AccessType.PROPERTY)
public class HealthManagementRecord extends BaseAssignedEntity {
  @Column(name = "name",  nullable = true)
  private String name;/** 姓名 */

  @Column(name = "peopleId",  nullable = true)
  private String peopleId;/** 身份证号 */

  @Column(name = "workid",  nullable = true)
  private String workid;/** 工作编号*/

  @Column(name = "systemid",  nullable = true)
  private String systemid;/** 系统编号*/

  @Column(name = "area",  nullable = true)
  private String area;/** 地区*/

  @Column(name = "score",  nullable = true)
  private String score;/** 得分 */

  @Column(name = "physicalType",  nullable = true)
  private String physicalType;/** 体质类型 */

  @Column(name = "physicalIdentification",  nullable = true)
  private String physicalIdentification; /** 体质辨识 */

  @Column(name = "chmedicineGuidance",  nullable = true)
  private String chmedicineGuidance; /** 中医药保健指导 */

  @Column(name = "healthDate",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private  Date healthDate;/** 填报日期 */

  @Column(name = "doctorSignature",  nullable = true)
  private String doctorSignature;/** 医生签名 */

  @Column(name = "question",  nullable = true)
  private String question; /** 问题 */

  @Column(name = "question1",  nullable = true)
  private String question1;  /** 问题2 */

  @Column(name = "question2",  nullable = true)
  private String question2;/** 问题3 */

  @Column(name = "question3",  nullable = true)
  private String question3;  /** 问题4 */

  @Column(name = "question4",  nullable = true)
  private String question4;/** 问题5 */

  @Column(name = "question5",  nullable = true)
  private String question5;/** 问题6 */

  @Column(name = "question6",  nullable = true)
  private String question6;  /** 问题7 */

  @Column(name = "question7",  nullable = true)
  private String question7;/** 问题8 */

  @Column(name = "question8",  nullable = true)
  private String question8; /** 问题9 */

  @Column(name = "question9",  nullable = true)
  private String question9;/** 问题10 */

  @Column(name = "question10",  nullable = true)
  private String question10;/** 问题11 */

  @Column(name = "question11",  nullable = true)
  private String question11; /** 问题12 */

  @Column(name = "question12",  nullable = true)
  private String question12; /** 问题13 */

  @Column(name = "question13",  nullable = true)
  private String question13; /** 问题14 */

  @Column(name = "question14",  nullable = true)
  private String question14;/** 问题15 */

  @Column(name = "question15",  nullable = true)
  private String question15; /** 问题16 */

  @Column(name = "question16",  nullable = true)
  private String question16;/** 问题17 */

  @Column(name = "question17",  nullable = true)
  private String question17; /** 问题18 */

  @Column(name = "question18",  nullable = true)
  private String question18;/** 问题19 */

  @Column(name = "question19",  nullable = true)
  private String question19; /** 问题20 */

  @Column(name = "question20",  nullable = true)
  private String question20;/** 问题21 */

  @Column(name = "question21",  nullable = true)
  private String question21;/** 问题22 */

  @Column(name = "question22",  nullable = true)
  private String question22;/** 问题23 */

  @Column(name = "question23",  nullable = true)
  private String question23;/** 问题24 */

  @Column(name = "question24",  nullable = true)
  private String question24;/** 问题25 */

  @Column(name = "question25",  nullable = true)
  private String question25;/** 问题26 */

  @Column(name = "question26",  nullable = true)
  private String question26; /** 问题27 */

  @Column(name = "question27",  nullable = true)
  private String question27;/** 问题28 */

  @Column(name = "question28",  nullable = true)
  private String question28;/** 问题29 */

  @Column(name = "question29",  nullable = true)
  private String question29;/** 问题30 */

  @Column(name = "question30",  nullable = true)
  private String question30;/** 问题31 */

  @Column(name = "question31",  nullable = true)
  private String question31;  /** 问题32 */

  @Column(name = "question32",  nullable = true)
  private String question32; /** 问题33 */

  @Column(name = "question33",  nullable = true)
  private String question33; /** 问题34 */

  @Column(name = "qideficiency",  nullable = true)
  private String qideficiency;/** 气虚质 */

  @Column(name = "yangdeficiency",  nullable = true)
  private String yangdeficiency;/** 阳虚质 */

  @Column(name = "yingdeficiency",  nullable = true)
  private String yingdeficiency;/** 阴虚质 */

  @Column(name = "tandeficiency",  nullable = true)
  private String tandeficiency; /** 痰湿质 */

  @Column(name = "shideficiency",  nullable = true)
  private String shideficiency;  /** 湿热质 */

  @Column(name = "xuedeficiency",  nullable = true)
  private String xuedeficiency;/** 血瘀质 */

  @Column(name = "qiyu",  nullable = true)
  private String qiyu;/** 气郁质 */

  @Column(name = "specialenamel",  nullable = true)
  private String specialenamel;/** 特禀质 */

  @Column(name = "peace",  nullable = true)
  private String peace;/** 平和质 */

  @Column(name = "deleteState",  nullable = true)
  private String deleteState;/** 删除用的 */


  //必须要有构造函数
  public HealthManagementRecord() {
  }

  public String getDeleteState() {
    return deleteState;
  }

  public void setPeopleId(String peopleId) { this.peopleId = peopleId; }

  public String getPeopleId() {
    return peopleId;
  }

  public void setDeleteState(String deleteState) { this.deleteState = deleteState; }

  public String getWorkid() {
    return workid;
  }

  public void setWorkid(String workid) { this.workid = workid; }


  public String getSystemid() {
    return systemid;
  }

  public void setSystemid(String systemid) {
    this.systemid = systemid;
  }


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


  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }


  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }


  public String getPhysicalType() {
    return physicalType;
  }

  public void setPhysicalType(String physicalType) {
    this.physicalType = physicalType;
  }


  public String getPhysicalIdentification() {
    return physicalIdentification;
  }

  public void setPhysicalIdentification(String physicalIdentification) { this.physicalIdentification = physicalIdentification; }


  public String getChmedicineGuidance() {
    return chmedicineGuidance;
  }

  public void setChmedicineGuidance(String chmedicineGuidance) {
    this.chmedicineGuidance = chmedicineGuidance;
  }


  public Date getHealthDate() {
    return healthDate;
  }

  public void setHealthDate(Date healthDate) {
    this.healthDate = healthDate;
  }


  public String getDoctorSignature() {
    return doctorSignature;
  }

  public void setDoctorSignature(String doctorSignature) {
    this.doctorSignature = doctorSignature;
  }



  public String getQuestion1() {
    return question1;
  }

  public void setQuestion1(String question1) {
    this.question1 = question1;
  }


  public String getQuestion2() {
    return question2;
  }

  public void setQuestion2(String question2) {
    this.question2 = question2;
  }


  public String getQuestion3() {
    return question3;
  }

  public void setQuestion3(String question3) {
    this.question3 = question3;
  }


  public String getQuestion4() {
    return question4;
  }

  public void setQuestion4(String question4) {
    this.question4 = question4;
  }


  public String getQuestion5() {
    return question5;
  }

  public void setQuestion5(String question5) {
    this.question5 = question5;
  }


  public String getQuestion6() {
    return question6;
  }

  public void setQuestion6(String question6) {
    this.question6 = question6;
  }


  public String getQuestion7() {
    return question7;
  }

  public void setQuestion7(String question7) {
    this.question7 = question7;
  }


  public String getQuestion8() {
    return question8;
  }

  public void setQuestion8(String question8) {
    this.question8 = question8;
  }


  public String getQuestion9() {
    return question9;
  }

  public void setQuestion9(String question9) {
    this.question9 = question9;
  }


  public String getQuestion10() {
    return question10;
  }

  public void setQuestion10(String question10) {
    this.question10 = question10;
  }


  public String getQuestion11() {
    return question11;
  }

  public void setQuestion11(String question11) {
    this.question11 = question11;
  }


  public String getQuestion12() {
    return question12;
  }

  public void setQuestion12(String question12) {
    this.question12 = question12;
  }


  public String getQuestion13() {
    return question13;
  }

  public void setQuestion13(String question13) {
    this.question13 = question13;
  }


  public String getQuestion14() {
    return question14;
  }

  public void setQuestion14(String question14) {
    this.question14 = question14;
  }


  public String getQuestion15() {
    return question15;
  }

  public void setQuestion15(String question15) {
    this.question15 = question15;
  }


  public String getQuestion16() {
    return question16;
  }

  public void setQuestion16(String question16) {
    this.question16 = question16;
  }


  public String getQuestion17() {
    return question17;
  }

  public void setQuestion17(String question17) {
    this.question17 = question17;
  }


  public String getQuestion18() {
    return question18;
  }

  public void setQuestion18(String question18) {
    this.question18 = question18;
  }


  public String getQuestion19() {
    return question19;
  }

  public void setQuestion19(String question19) {
    this.question19 = question19;
  }


  public String getQuestion20() {
    return question20;
  }

  public void setQuestion20(String question20) {
    this.question20 = question20;
  }


  public String getQuestion21() {
    return question21;
  }

  public void setQuestion21(String question21) {
    this.question21 = question21;
  }


  public String getQuestion22() {
    return question22;
  }

  public void setQuestion22(String question22) {
    this.question22 = question22;
  }


  public String getQuestion23() {
    return question23;
  }

  public void setQuestion23(String question23) {
    this.question23 = question23;
  }


  public String getQuestion24() {
    return question24;
  }

  public void setQuestion24(String question24) {
    this.question24 = question24;
  }


  public String getQuestion25() {
    return question25;
  }

  public void setQuestion25(String question25) {
    this.question25 = question25;
  }


  public String getQuestion26() {
    return question26;
  }

  public void setQuestion26(String question26) {
    this.question26 = question26;
  }


  public String getQuestion27() {
    return question27;
  }

  public void setQuestion27(String question27) {
    this.question27 = question27;
  }


  public String getQuestion28() {
    return question28;
  }

  public void setQuestion28(String question28) {
    this.question28 = question28;
  }


  public String getQuestion29() {
    return question29;
  }

  public void setQuestion29(String question29) {
    this.question29 = question29;
  }


  public String getQuestion30() {
    return question30;
  }

  public void setQuestion30(String question30) {
    this.question30 = question30;
  }


  public String getQuestion31() {
    return question31;
  }

  public void setQuestion31(String question31) {
    this.question31 = question31;
  }


  public String getQuestion32() {
    return question32;
  }

  public void setQuestion32(String question32) {
    this.question32 = question32;
  }


  public String getQuestion33() {
    return question33;
  }

  public void setQuestion33(String question33) {
    this.question33 = question33;
  }


  public String getQideficiency() {
    return qideficiency;
  }

  public void setQideficiency(String qideficiency) {
    this.qideficiency = qideficiency;
  }


  public String getYangdeficiency() {
    return yangdeficiency;
  }

  public void setYangdeficiency(String yangdeficiency) {
    this.yangdeficiency = yangdeficiency;
  }


  public String getYingdeficiency() {
    return yingdeficiency;
  }

  public void setYingdeficiency(String yingdeficiency) {
    this.yingdeficiency = yingdeficiency;
  }


  public String getTandeficiency() {
    return tandeficiency;
  }

  public void setTandeficiency(String tandeficiency) {
    this.tandeficiency = tandeficiency;
  }


  public String getShideficiency() {
    return shideficiency;
  }

  public void setShideficiency(String shideficiency) {
    this.shideficiency = shideficiency;
  }


  public String getXuedeficiency() {
    return xuedeficiency;
  }

  public void setXuedeficiency(String xuedeficiency) {
    this.xuedeficiency = xuedeficiency;
  }


  public String getQiyu() {
    return qiyu;
  }

  public void setQiyu(String qiyu) {
    this.qiyu = qiyu;
  }


  public String getSpecialenamel() {
    return specialenamel;
  }

  public void setSpecialenamel(String specialenamel) {
    this.specialenamel = specialenamel;
  }


  public String getPeace() {
    return peace;
  }

  public void setPeace(String peace) {
    this.peace = peace;
  }




}
