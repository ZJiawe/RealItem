package com.yihu.ehr.health.Transtreatlog.entity;
import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transtreatlog")
@Access(value = AccessType.PROPERTY)
public class Transtreatlog extends BaseAssignedEntity {


    /** 身份证号*/
    private String idNumber;
    /** 姓名*/
    private String name;
    /** 身份证号*/
    private String sex;
    /** 年龄*/
    private Integer age;
    /** 住址*/
    private String address;
    /** 转诊前诊断*/
    private String prediagnosis;
    /** 病情摘要*/
    private String illnessDigest;
    /** 转诊原因*/
    private String referralReason;
    /** 转往的医疗机构、科室*/
    private String facilityAndDepart;
    /** 转诊时间*/
    private Timestamp transTime;
    /** 转诊类型*/
    private String transType;
    /** 患者联系方式*/
    private String contact;
    /** 转诊状态*/
    private String transState;
    /** 删除状态*/
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


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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


    public Timestamp getTransTime() {
        return transTime;
    }

    public void setTransTime(Timestamp transTime) {
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
