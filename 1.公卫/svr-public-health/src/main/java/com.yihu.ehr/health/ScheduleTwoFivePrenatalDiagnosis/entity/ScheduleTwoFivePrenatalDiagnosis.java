package com.yihu.ehr.health.ScheduleTwoFivePrenatalDiagnosis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "ScheduleTwoFivePrenatalDiagnosis", schema = "doc")
public class ScheduleTwoFivePrenatalDiagnosis extends BaseAssignedEntity {

    private String area;//区域
    private String number;//次数
    private String name;//姓名
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date date;//（随访/督促）日期
    private String peopleId;//身份证
    private Integer gestationalWeeks;//孕周
    private String mainSuit;//主诉
    private Integer weight;//体重
    private Integer fundusHeight;//宫底高度
    private Integer abdominal;//腹围
    private String fetalPosition;//胎位
    private Integer fhr;//胎心率
    private Integer blood;//血压
    private Integer hemoglobin;//血红蛋白
    private Integer urineProtein;//尿蛋白
    private String otherSassist;//其他（辅助检查的）
    private String classify;//分类
    private String guidance;//指导
    private String guidances;//指导的其他
    private String insandDep;//机构及科室
    private String referral;//转诊
    private String reason;//原因
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date nextDate;//下次随访日期
    private String doctor;//随访医生
    private String deleteState;//删除状态

    public String getArea() { return area; }

    public void setArea(String area) { this.area = area; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }


    public Integer getGestationalWeeks() {
        return gestationalWeeks;
    }

    public void setGestationalWeeks(Integer gestationalWeeks) {
        this.gestationalWeeks = gestationalWeeks;
    }


    public String getMainSuit() {
        return mainSuit;
    }

    public void setMainSuit(String mainSuit) {
        this.mainSuit = mainSuit;
    }


    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }


    public Integer getFundusHeight() {
        return fundusHeight;
    }

    public void setFundusHeight(Integer fundusHeight) {
        this.fundusHeight = fundusHeight;
    }


    public Integer getAbdominal() {
        return abdominal;
    }

    public void setAbdominal(Integer abdominal) {
        this.abdominal = abdominal;
    }


    public String getFetalPosition() {
        return fetalPosition;
    }

    public void setFetalPosition(String fetalPosition) {
        this.fetalPosition = fetalPosition;
    }


    public Integer getFhr() {
        return fhr;
    }

    public void setFhr(Integer fhr) {
        this.fhr = fhr;
    }


    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }


    public Integer getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(Integer hemoglobin) {
        this.hemoglobin = hemoglobin;
    }


    public Integer getUrineProtein() {
        return urineProtein;
    }

    public void setUrineProtein(Integer urineProtein) {
        this.urineProtein = urineProtein;
    }


    public String getOtherSassist() {
        return otherSassist;
    }

    public void setOtherSassist(String otherSassist) {
        this.otherSassist = otherSassist;
    }


    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }


    public String getGuidance() {
        return guidance;
    }

    public void setGuidance(String guidance) {
        this.guidance = guidance;
    }

    public String getGuidances() {
        return guidances;
    }

    public void setGuidances(String guidances) {
        this.guidances = guidances;
    }

    public String getInsandDep() {
        return insandDep;
    }

    public void setInsandDep(String insandDep) {
        this.insandDep = insandDep;
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

    public void setDeleteState(String deleteState) { this.deleteState = deleteState; }


}
