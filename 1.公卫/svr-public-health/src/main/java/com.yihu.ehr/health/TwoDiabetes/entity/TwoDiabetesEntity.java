package com.yihu.ehr.health.TwoDiabetes.entity;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
/**
 * Entity - 基类（自动递增）
 * Created by lll  on 2018/9/21.
 */
@Entity
@Table(name = "diabetes")
@Access(value = AccessType.PROPERTY)
public class TwoDiabetesEntity extends BaseAssignedEntity {
  /** 姓名*/
  @Column(name = "name",  nullable = true)
  private String name;
  /** 性别*/
  @Column(name = "sex",  nullable = true)
  private String  sex;
  /** 年龄*/
  @Column(name = "age",  nullable = true)
  private String  age;
  /** 身份证号码*/
  @Column(name = "people_id",  nullable = true)
  private String peopleId;
  /** 民族*/
  @Column(name = "ethnic",  nullable = true)
  private String ethnic;
  /** 联系电话*/
  @Column(name = "phone",  nullable = true)
  private String  phone;
  /** 责任医生*/
  @Column(name = "doctor",  nullable = true)
  private String  doctor;
  /** 生存状态*/
  @Column(name = "live_status",  nullable = true)
  private String liveStatus	;
  /** 区域*/
  @Column(name = "area",  nullable = true)
  private String area	;
  /** 随访日期*/
  @Column(name = "follow_date",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date followDate;
  /** 随访方式 */
  @Column(name = "follow_way",  nullable = true)
  private String followWay;
  /** 随访次数*/
  @Column(name = "follow_count",  nullable = true)
  private String followCount;
  /** 症状 */
  @Column(name = "symptoms",  nullable = true)
  private String symptoms;
  /** 血型 */
  @Column(name = "blood",  nullable = true)
  private String blood;
  /** 血压 */
  @Column(name = "pressure",  nullable = true)
  private String pressure;
  /** 体重 */
  @Column(name = "weight",  nullable = true)
  private String weight;
  /** 体质指数 */
  @Column(name = "body_mass_index",  nullable = true)
  private String bodyMassIndex;
  /** 足背动脉搏动*/
  @Column(name = "foot_artey_pulse",  nullable = true)
  private String footArteyPulse;
  /** 其他 */
  @Column(name = "other",  nullable = true)
  private String other;
  /** 日吸烟量 */
  @Column(name = "daily_smoking",  nullable = true)
  private String dailySmoking;
  /** 日饮酒量 */
  @Column(name = "daily_drinking",  nullable = true)
  private String dailyDrinking;
  /** 运动 */
  @Column(name = "exercise",  nullable = true)
  private String exercise;
  /** 主食 */
  @Column(name = "staple_food",  nullable = true)
  private String stapleFood;
  /** 心理调整 */
  @Column(name = "psychological_adjust",  nullable = true)
  private String psychologicalAdjust;
  /** 遵医行为 */
  @Column(name = "compliance_behavior",  nullable = true)
  private String complianceBehavior;
  /** 空腹血糖值 */
  @Column(name = "fasting_blood",  nullable = true)
  private String fastingBlood;
  /** 糖化血红蛋白 */
  @Column(name = "hemoglobin",  nullable = true)
  private String hemoglobin;
  /** 检查日期 */
  @Column(name = "examination_date",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date examinationDate;
  /** 服药依从性 */
  @Column(name = "diabetescol",  nullable = true)
  private String diabetescol;
  /** 药物不良反应 */
  @Column(name = "drugs_adversed",  nullable = true)
  private String drugsAdversed;
  /** 低血糖反应 */
  @Column(name = "hypoglycemia",  nullable = true)
  private String hypoglycemia;
  /** 此次随访分类 */
  @Column(name = "follow_classification",  nullable = true)
  private String followClassification;
  /** 药物名称 */
  @Column(name = "drug_name",  nullable = true)
  private String drugName;
  /** 用法用量 */
  @Column(name = "usage_dosage",  nullable = true)
  private String usageDosage;
  ///** 胰岛素（种类、用法和用量） */
  //private String insulin;
  ///** 转诊（原因、机构及科别） */
  //private String referral;
   /** 转诊原因 */
   @Column(name = "referral_cause",  nullable = true)
   private String referralCause;
   /** 转诊机构及科别 */
   @Column(name = "referral_institution",  nullable = true)
   private String referralInstitution;
  /** 下次随访日期 */
  @Column(name = "next_follow_date",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date nextFollowDate;
  /** 随访医生签名 */
  @Column(name = "doctor_sign",  nullable = true)
  private String doctorSign;
  /** 伪删除 */
  @Column(name = "deleteState",  nullable = true)
  private String deleteState;

//  /** 创建人 */
//  private String creater;
//  /** 创建时间 */
//  private Date createDate;
//  /** 修改人 */
//  private String modifier;
//  /** 修改时间 */
//  private Date modifyDate;
  public TwoDiabetesEntity(){ }


  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }



    public String getSex() {
        return  sex;
    }
    public void setSex(String  sex) {
        this. sex =  sex;
    }


    public String getAge() {
        return  age;
    }
    public void setAge(String age) {
        this. age =  age;
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


      public String getArea() {
        return area;
      }
      public void setArea(String area) {
        this.area = area;
      }


    public String getEthnic() {
        return ethnic;
    }
    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }


    public String getDoctor	() {
        return doctor;
    }
    public void setDoctor	(String doctor	) {
        this.doctor	 = doctor;
    }


    public String getLiveStatus() {
        return liveStatus;
    }
    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus;
    }

  public Date getFollowDate() {
    return followDate;
  }
  public void setFollowDate(Date followDate) {
    this.followDate = followDate;
  }


  public String getFollowWay() { return followWay; }
  public void setFollowWay(String followWay) {
    this.followWay = followWay;
  }


    public String getFollowCount() {
        return followCount;
    }
    public void setFollowCount(String followCount) {
        this.followCount = followCount;
    }


  public String getSymptoms() {
    return symptoms;
  }
  public void setSymptoms(String symptoms) {
    this.symptoms = symptoms;
  }


  public String getBlood() {
    return blood;
  }
  public void setBlood(String blood) {
    this.blood = blood;
  }


  public String getPressure() {
    return pressure;
  }
  public void setPressure(String pressure) {
    this.pressure = pressure;
  }


  public String getWeight() {
    return weight;
  }
  public void setWeight(String weight) {
    this.weight = weight;
  }


  public String getBodyMassIndex() {
    return bodyMassIndex;
  }
  public void setBodyMassIndex(String bodyMassIndex) {
    this.bodyMassIndex=bodyMassIndex;
  }


  public String getFootArteyPulse() {
    return footArteyPulse;
  }
  public void setFootArteyPulse(String footArteyPulse) {
    this.footArteyPulse = footArteyPulse;
  }

  public String getOther() {
    return other;
  }
  public void setOther(String other) {
    this.other = other;
  }


  public String getDailySmoking() {
    return dailySmoking;
  }
  public void setDailySmoking(String dailySmoking) {
    this.dailySmoking = dailySmoking;
  }


  public String getDailyDrinking() {
    return dailyDrinking;
  }
  public void setDailyDrinking(String dailyDrinking) {
    this.dailyDrinking = dailyDrinking;
  }


  public String getExercise() {
    return exercise;
  }
  public void setExercise(String exercise) {
    this.exercise = exercise;
  }


  public String getStapleFood() {
    return stapleFood;
  }
  public void setStapleFood(String stapleFood) {
    this.stapleFood = stapleFood;
  }


  public String getPsychologicalAdjust() {
    return psychologicalAdjust;
  }
  public void setPsychologicalAdjust(String psychologicalAdjust) {
    this.psychologicalAdjust = psychologicalAdjust;
  }


  public String getComplianceBehavior() {
    return complianceBehavior;
  }
  public void setComplianceBehavior(String complianceBehavior) {
    this.complianceBehavior = complianceBehavior;
  }


  public String getFastingBlood() {
    return fastingBlood;
  }
  public void setFastingBlood(String fastingBlood) {
    this.fastingBlood = fastingBlood;
  }


  public String getHemoglobin() {
    return hemoglobin;
  }
  public void setHemoglobin(String hemoglobin) {
    this.hemoglobin = hemoglobin;
  }


  public Date getExaminationDate() {
    return examinationDate;
  }
  public void setExaminationDate(Date examinationDate) {
    this.examinationDate = examinationDate;
  }


  public String getDiabetescol() {
    return diabetescol;
  }
  public void setDiabetescol(String diabetescol) {
    this.diabetescol = diabetescol;
  }


  public String getDrugsAdversed() { return drugsAdversed; }
  public void setDrugsAdversed(String drugsAdversed) { this.drugsAdversed = drugsAdversed; }


  public String getHypoglycemia() {
    return hypoglycemia;
  }
  public void setHypoglycemia(String hypoglycemia) {
    this.hypoglycemia = hypoglycemia;
  }


  public String getFollowClassification() {
    return followClassification;
  }
  public void setFollowClassification(String followClassification) {
    this.followClassification = followClassification;
  }


  public String getDrugName() {
    return drugName;
  }
  public void setDrugName(String drugName) {
    this.drugName = drugName;
  }


  public String getUsageDosage() {
    return usageDosage;
  }
  public void setUsageDosage(String usageDosage) {
    this.usageDosage = usageDosage;
  }


    public String getReferralCause() { return referralCause; }
    public void setReferralCause(String referralCause) {
        this.referralCause = referralCause;
    }


    public String getReferralInstitution() { return referralInstitution; }
    public void setReferralInstitution(String referralInstitution) {
        this.referralInstitution = referralInstitution;
    }


  public Date getNextFollowDate() {
    return nextFollowDate;
  }
  public void setNextFollowDate(Date nextFollowDate) {
    this.nextFollowDate = nextFollowDate;
  }


  public String getDoctorSign() {
    return doctorSign;
  }
  public void setDoctorSign(String doctorSign) {
    this.doctorSign = doctorSign;
  }

  public String getDeleteState() { return deleteState; }
  public void setDeleteState(String deleteState) { this.deleteState = deleteState; }

}
