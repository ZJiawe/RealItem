package com.yihu.ehr.health.PostpartumVisit.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "PostpartumVisit")
public class PostpartumVisit  extends BaseAssignedEntity {

    private String name;//姓名
    private String peopleId;//身份证
    private String area;//区域
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date date;//随访日期
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date childBirth;//分娩日期
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date leaveHospitale;//出院日期
    private Integer temperature;//体温
    private String healthy;//一般健康情况
    private String psychology;//一般心理状况
    private Integer blood;//血压
    private String breast;//乳房
    private String lochia;//恶露
    private String wound;//伤口
    private String uterus;//子宫
    private String other;//其他
    private String classify;//分类
    private String guidance;//指导
    private String insandDep;//机构及科室
    private String referral;//转诊
    private String reason;//原因
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date nextDate;//下次随访日期
    private String doctor;//随访医生
    private String deleteState;//删除状态

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() { return area; }

    public void setArea(String area) { this.area = area; }

    public String getPeopleId() { return peopleId; }

    public void setPeopleId(String peopleId) { this.peopleId = peopleId; }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Date getChildBirth() {
        return childBirth;
    }

    public void setChildBirth(Date childBirth) {
        this.childBirth = childBirth;
    }


    public Date getLeaveHospitale() {
        return leaveHospitale;
    }

    public void setLeaveHospitale(Date leaveHospitale) {
        this.leaveHospitale = leaveHospitale;
    }


    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }


    public String getHealthy() {
        return healthy;
    }

    public void setHealthy(String healthy) {
        this.healthy = healthy;
    }


    public String getPsychology() {
        return psychology;
    }

    public void setPsychology(String psychology) {
        this.psychology = psychology;
    }


    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }


    public String getBreast() {
        return breast;
    }

    public void setBreast(String breast) {
        this.breast = breast;
    }


    public String getLochia() {
        return lochia;
    }

    public void setLochia(String lochia) {
        this.lochia = lochia;
    }


    public String getWound() {
        return wound;
    }

    public void setWound(String wound) {
        this.wound = wound;
    }


    public String getUterus() {
        return uterus;
    }

    public void setUterus(String uterus) {
        this.uterus = uterus;
    }


    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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


    public String getInsandDep() {
        return insandDep;
    }

    public void setInsandDep(String insandDep) {
        this.insandDep = insandDep;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
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

    public void setDoctor(String doctor) { this.doctor = doctor; }

    public String getDeleteState() { return deleteState; }

    public void setDeleteState(String deleteState) { this.deleteState = deleteState; }
}

