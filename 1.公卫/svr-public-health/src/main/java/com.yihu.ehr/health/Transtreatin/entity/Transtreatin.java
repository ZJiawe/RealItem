package com.yihu.ehr.health.Transtreatin.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "transtreat", schema = "doc")
@Access(value = AccessType.PROPERTY)
public class Transtreatin extends BaseAssignedEntity {
  @Column(name = "id_number",  nullable = true)
  private String idNumber;
  @Column(name = "name",  nullable = true)
  private String name;
  @Column(name = "sex",  nullable = true)
  private String sex;
  @Column(name = "age",  nullable = true)
  private String age;
  @Column(name = "address",  nullable = true)
  private String address;
  @Column(name = "prediagnosis",  nullable = true)
  private String prediagnosis;
  @Column(name = "illness_digest",  nullable = true)
  private String illnessDigest;
  @Column(name = "referral_reason",  nullable = true)
  private String referralReason;
  @Column(name = "facility_and_depart",  nullable = true)
  private String facilityAndDepart;
  @Column(name = "trans_time",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date transTime;
  @Column(name = "trans_type",  nullable = true)
  private String transType;
  @Column(name = "contact",  nullable = true)
  private String contact;
  @Column(name = "trans_state",  nullable = true)
  private String transState;
  @Column(name = "delete_state",  nullable = true)
  private String deleteState;



  public String getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }



  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getPrediagnosis() {
    return prediagnosis;
  }

  public void setPrediagnosis(String prediagnosis) {
    this.prediagnosis = prediagnosis;
  }


  public String getIllnessDigest() {
    return illnessDigest;
  }

  public void setIllnessDigest(String illnessDigest) {
    this.illnessDigest = illnessDigest;
  }


  public String getReferralReason() {
    return referralReason;
  }

  public void setReferralReason(String referralReason) {
    this.referralReason = referralReason;
  }


  public String getFacilityAndDepart() {
    return facilityAndDepart;
  }

  public void setFacilityAndDepart(String facilityAndDepart) {
    this.facilityAndDepart = facilityAndDepart;
  }


  public Date getTransTime() {
    return transTime;
  }

  public void setTransTime(Date transTime) {
    this.transTime = transTime;
  }


  public String getTransType() {
    return transType;
  }

  public void setTransType(String transType) {
    this.transType = transType;
  }


  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }


  public String getTransState() {
    return transState;
  }

  public void setTransState(String transState) {
    this.transState = transState;
  }

  public String getDeleteState() {
    return deleteState;
  }

  public void setDeleteState(String deleteState) {
    this.deleteState = deleteState;
  }

}
