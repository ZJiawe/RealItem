package com.yihu.ehr.health.NewbornVisit.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "newborn_visit")
@Access(value = AccessType.PROPERTY)
public class NewbornVisit extends BaseAssignedEntity {

  /**  姓名*/
  @Column(name = "name",  nullable = true)
  private String name;
  /**  编号*/
  @Column(name = "number",  nullable = true)
  private String number;
  /**  性别*/
  @Column(name = "sex",  nullable = true)
  private String sex;
  /**  出生日期*/
  @Column(name = "birthdate",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date birthdate;
  /**  身份证号*/
  @Column(name = "people_id",  nullable = true)
  private String peopleId;
  /**  地址*/
  @Column(name = "addr",  nullable = true)
  private String addr;
  /**  父亲名字*/
  @Column(name = "father_name",  nullable = true)
  private String fatherName;
  /**  父亲职业*/
  @Column(name = "father_job",  nullable = true)
  private String fatherJob;
  /**  父亲手机号*/
  @Column(name = "father_phone",  nullable = true)
  private String fatherPhone;
  /**  父亲生日*/
  @Column(name = "father_birthdate",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date fatherBirthdate;
  /**  母亲名字*/
  @Column(name = "mother_name",  nullable = true)
  private String motherName;
  /**  母亲职业*/
  @Column(name = "mother_job",  nullable = true)
  private String motherJob;
  /**  母亲手机号*/
  @Column(name = "mother_phone",  nullable = true)
  private String motherPhone;
  /**  母亲生日*/
  @Column(name = "mother_birthdate",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date motherBirthdate;
  /**  出生孕周*/
  @Column(name = "week",  nullable = true)
  private String week;
  /**  母亲状态*/
  @Column(name = "mother_state",  nullable = true)
  private String motherState;
  /**  助产机构*/
  @Column(name = "organization",  nullable = true)
  private String organization;
  /**  出生情况*/
  @Column(name = "state",  nullable = true)
  private String state;
  /**  新生儿窒息*/
  @Column(name = "stifle",  nullable = true)
  private String stifle;
  /**  畸形*/
  @Column(name = "malformation",  nullable = true)
  private String malformation;
  /**  新生儿听力筛查*/
  @Column(name = "listen",  nullable = true)
  private String listen;
  /**  新生儿疾病筛查*/
  @Column(name = "illness",  nullable = true)
  private String illness;
  /**  出生体重*/
  @Column(name = "birth_weight",  nullable = true)
  private String birthWeight;
  /**  目前体重*/
  @Column(name = "weight",  nullable = true)
  private String weight;
  /**  出生身长*/
  @Column(name = "birth_height",  nullable = true)
  private String birthHeight;
  /**  喂养方式*/
  @Column(name = "feed",  nullable = true)
  private String feed;
  /**  吃奶量*/
  @Column(name = "feed_milk",  nullable = true)
  private String feedMilk;
  /**  吃奶次数*/
  @Column(name = "feed_milk_count",  nullable = true)
  private Integer feedMilkCount;
  /**  呕吐*/
  @Column(name = "emesis",  nullable = true)
  private String emesis;
  /**  大便*/
  @Column(name = "shit",  nullable = true)
  private String shit;
  /**  大便次数*/
  @Column(name = "shit_count",  nullable = true)
  private Integer shitCount;
  /**  体温*/
  @Column(name = "temperature",  nullable = true)
  private String temperature;
  /**  心率*/
  @Column(name = "heart",  nullable = true)
  private Integer heart;
  /**  呼吸*/
  @Column(name = "breathe",  nullable = true)
  private Integer breathe;
  /**  面色*/
  @Column(name = "face",  nullable = true)
  private String face;
  /**  黄疸*/
  @Column(name = "yellow",  nullable = true)
  private String yellow;
  /**  前囟宽度*/
  @Column(name = "bregma_width",  nullable = true)
  private String bregmaWidth;
  /**  前囟高度*/
  @Column(name = "bregma_height",  nullable = true)
  private String bregmaHeight;
  /**  前囟*/
  @Column(name = "bregma",  nullable = true)
  private String bregma;
  /**  眼睛*/
  @Column(name = "eye",  nullable = true)
  private String eye;
  /**  四肢活动度*/
  @Column(name = "limb",  nullable = true)
  private String limb;
  /**  耳外观*/
  @Column(name = "ear",  nullable = true)
  private String ear;
  /**  颈部包块*/
  @Column(name = "neck",  nullable = true)
  private String neck;
  /**  鼻*/
  @Column(name = "nose",  nullable = true)
  private String nose;
  /**  皮肤*/
  @Column(name = "skin",  nullable = true)
  private String skin;
  /**  口腔*/
  @Column(name = "oral",  nullable = true)
  private String oral;
  /**  肛门*/
  @Column(name = "anus",  nullable = true)
  private String anus;
  /**  心肺听诊*/
  @Column(name = "heart_lung",  nullable = true)
  private String heartLung;
  /**  胸部*/
  @Column(name = "chest",  nullable = true)
  private String chest;
  /**  腹部触诊*/
  @Column(name = "abdomen",  nullable = true)
  private String abdomen;
  /**  脊柱*/
  @Column(name = "vertebra",  nullable = true)
  private String vertebra;
  /**  脐带*/
  @Column(name = "umbilical",  nullable = true)
  private String umbilical;
  /**  外生殖器*/
  @Column(name = "pudendum",  nullable = true)
  private String pudendum;
  /**  转诊建议*/
  @Column(name = "referral",  nullable = true)
  private String referral;
  /**  原因*/
  @Column(name = "reason",  nullable = true)
  private String reason;
  /**  指导*/
  @Column(name = "guidance",  nullable = true)
  private String guidance;
  /**  本次访视日期*/
  @Column(name = "follow_date",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date followDate;
  /**  下次随访地址*/
  @Column(name = "next_visit_addr",  nullable = true)
  private String nextVisitAddr;
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

  /**  构造函数*/
  public NewbornVisit(){}


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }


  public String getPeopleId() {
    return peopleId;
  }

  public void setPeopleId(String peopleId) {
    this.peopleId = peopleId;
  }


  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }


  public String getFatherName() {
    return fatherName;
  }

  public void setFatherName(String fatherName) {
    this.fatherName = fatherName;
  }


  public String getFatherJob() {
    return fatherJob;
  }

  public void setFatherJob(String fatherJob) {
    this.fatherJob = fatherJob;
  }


  public String getFatherPhone() {
    return fatherPhone;
  }

  public void setFatherPhone(String fatherPhone) {
    this.fatherPhone = fatherPhone;
  }


  public Date getFatherBirthdate() {
    return fatherBirthdate;
  }

  public void setFatherBirthdate(Date fatherBirthdate) {
    this.fatherBirthdate = fatherBirthdate;
  }


  public String getMotherName() {
    return motherName;
  }

  public void setMotherName(String motherName) {
    this.motherName = motherName;
  }


  public String getMotherJob() {
    return motherJob;
  }

  public void setMotherJob(String motherJob) {
    this.motherJob = motherJob;
  }


  public String getMotherPhone() {
    return motherPhone;
  }

  public void setMotherPhone(String motherPhone) {
    this.motherPhone = motherPhone;
  }


  public Date getMotherBirthdate() {
    return motherBirthdate;
  }

  public void setMotherBirthdate(Date motherBirthdate) {
    this.motherBirthdate = motherBirthdate;
  }


  public String getWeek() {
    return week;
  }

  public void setWeek(String week) {
    this.week = week;
  }


  public String getMotherState() {
    return motherState;
  }

  public void setMotherState(String motherState) {
    this.motherState = motherState;
  }


  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  public String getStifle() {
    return stifle;
  }

  public void setStifle(String stifle) {
    this.stifle = stifle;
  }


  public String getMalformation() {
    return malformation;
  }

  public void setMalformation(String malformation) {
    this.malformation = malformation;
  }


  public String getListen() {
    return listen;
  }

  public void setListen(String listen) {
    this.listen = listen;
  }


  public String getIllness() {
    return illness;
  }

  public void setIllness(String illness) {
    this.illness = illness;
  }


  public String getBirthWeight() {
    return birthWeight;
  }

  public void setBirthWeight(String birthWeight) {
    this.birthWeight = birthWeight;
  }


  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }


  public String getBirthHeight() {
    return birthHeight;
  }

  public void setBirthHeight(String birthHeight) {
    this.birthHeight = birthHeight;
  }


  public String getFeed() {
    return feed;
  }

  public void setFeed(String feed) {
    this.feed = feed;
  }


  public String getFeedMilk() {
    return feedMilk;
  }

  public void setFeedMilk(String feedMilk) {
    this.feedMilk = feedMilk;
  }


  public Integer getFeedMilkCount() {
    return feedMilkCount;
  }

  public void setFeedMilkCount(Integer feedMilkCount) {
    this.feedMilkCount = feedMilkCount;
  }


  public String getEmesis() {
    return emesis;
  }

  public void setEmesis(String emesis) {
    this.emesis = emesis;
  }


  public String getShit() {
    return shit;
  }

  public void setShit(String shit) {
    this.shit = shit;
  }


  public Integer getShitCount() {
    return shitCount;
  }

  public void setShitCount(Integer shitCount) {
    this.shitCount = shitCount;
  }


  public String getTemperature() {
    return temperature;
  }

  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }


  public Integer getHeart() {
    return heart;
  }

  public void setHeart(Integer heart) {
    this.heart = heart;
  }


  public Integer getBreathe() {
    return breathe;
  }

  public void setBreathe(Integer breathe) {
    this.breathe = breathe;
  }


  public String getFace() {
    return face;
  }

  public void setFace(String face) {
    this.face = face;
  }


  public String getYellow() {
    return yellow;
  }

  public void setYellow(String yellow) {
    this.yellow = yellow;
  }


  public String getBregmaWidth() {
    return bregmaWidth;
  }

  public void setBregmaWidth(String bregmaWidth) {
    this.bregmaWidth = bregmaWidth;
  }


  public String getBregmaHeight() {
    return bregmaHeight;
  }

  public void setBregmaHeight(String bregmaHeight) {
    this.bregmaHeight = bregmaHeight;
  }


  public String getBregma() {
    return bregma;
  }

  public void setBregma(String bregma) {
    this.bregma = bregma;
  }


  public String getEye() {
    return eye;
  }

  public void setEye(String eye) {
    this.eye = eye;
  }


  public String getLimb() {
    return limb;
  }

  public void setLimb(String limb) {
    this.limb = limb;
  }


  public String getEar() {
    return ear;
  }

  public void setEar(String ear) {
    this.ear = ear;
  }


  public String getNeck() {
    return neck;
  }

  public void setNeck(String neck) {
    this.neck = neck;
  }


  public String getNose() {
    return nose;
  }

  public void setNose(String nose) {
    this.nose = nose;
  }


  public String getSkin() {
    return skin;
  }

  public void setSkin(String skin) {
    this.skin = skin;
  }


  public String getOral() {
    return oral;
  }

  public void setOral(String oral) {
    this.oral = oral;
  }


  public String getAnus() {
    return anus;
  }

  public void setAnus(String anus) {
    this.anus = anus;
  }


  public String getHeartLung() {
    return heartLung;
  }

  public void setHeartLung(String heartLung) {
    this.heartLung = heartLung;
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


  public String getVertebra() {
    return vertebra;
  }

  public void setVertebra(String vertebra) {
    this.vertebra = vertebra;
  }


  public String getUmbilical() {
    return umbilical;
  }

  public void setUmbilical(String umbilical) {
    this.umbilical = umbilical;
  }


  public String getPudendum() {
    return pudendum;
  }

  public void setPudendum(String pudendum) {
    this.pudendum = pudendum;
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


  public String getGuidance() {
    return guidance;
  }

  public void setGuidance(String guidance) {
    this.guidance = guidance;
  }


  public Date getFollowDate() {
    return followDate;
  }

  public void setFollowDate(Date followDate) {
    this.followDate = followDate;
  }


  public String getNextVisitAddr() {
    return nextVisitAddr;
  }

  public void setNextVisitAddr(String nextVisitAddr) {
    this.nextVisitAddr = nextVisitAddr;
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
