package com.yihu.ehr.health.Emergencies.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;

@Entity
@Access(value = AccessType.PROPERTY)
@Table(name = "emergencies")
public class EmergenciesEntity extends BaseAssignedEntity {
    private static final long serialVersionUID = -67188388306700736L;
    public EmergenciesEntity() { }

    /**  填报单位*/
    @Column(name = "reportingUnit",  nullable = true)
    private String reportingUnit;
    /**  填报时间*/
    @Column(name = "reportingDate",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date reportingDate;
    /**  报告人*/
    @Column(name = "rapporteur",  nullable = true)
    private String rapporteur;
    /**  联系电话*/
    @Column(name = "phone",  nullable = true)
    private String phone;
    /**  事件名称*/
    @Column(name = "eventName",  nullable = true)
    private String eventName;
    /**  信息类别*/
    @Column(name = "messageClass",  nullable = true)
    private String messageClass;
    /**  公共卫生突发事件等级*/
    @Column(name = "eventLevel",  nullable = true)
    private String eventLevel;
    /**  初步诊断*/
    @Column(name = "firstDiagnosis",  nullable = true)
    private String firstDiagnosis;
    /**  初步诊断时间*/
    @Column(name = "firstDiagnosisTime",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date firstDiagnosisTime;
    /**  订正诊断*/
    @Column(name = "modifyDiagnosis",  nullable = true)
    private String modifyDiagnosis;
    /**  订正诊断时间*/
    @Column(name = "modifyDiagnosisTime",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date modifyDiagnosisTime;
    /**  确认分级时间*/
    @Column(name = "confirmGradeTime",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date confirmGradeTime;
    /**  订正分级时间*/
    @Column(name = "modifyGradeTime",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date modifyGradeTime;
    /**  报告地区*/
    @Column(name = "reportingArea",  nullable = true)
    private String reportingArea;
    /**  发生地区*/
    @Column(name = "occurrenceArea",  nullable = true)
    private String occurrenceArea;
    /**  详细地点*/
    @Column(name = "detailedLocation",  nullable = true)
    private String detailedLocation;
    /**  事件发生场所*/
    @Column(name = "occurPlace",  nullable = true)
    private String occurPlace;
    /**  事件信息来源*/
    @Column(name = "messageSource",  nullable = true)
    private String messageSource;
    /**  事件信息来源详细*/
    @Column(name = "sourceDetails",  nullable = true)
    private String sourceDetails;
    /**  事件波及的地域范围*/
    @Column(name = "sweepRange",  nullable = true)
    private String sweepRange;
    /**  新报告病例数*/
    @Column(name = "newCase",  nullable = true)
    private Integer newCase;
    /**  新报告死亡数*/
    @Column(name = "newMortality",  nullable = true)
    private Integer newMortality;
    /**  排除病例数*/
    @Column(name = "removeCase",  nullable = true)
    private Integer removeCase;
    /**  累计报告病例数*/
    @Column(name = "accumulativeCase",  nullable = true)
    private Integer accumulativeCase;
    /**  累计报告死亡数*/
    @Column(name = "accumulativeMortality",  nullable = true)
    private Integer accumulativeMortality;
    /**  事件发生时间*/
    @Column(name = "eventTime",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date eventTime;
    /**  接到报告时间*/
    @Column(name = "reportingTime",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date reportingTime;
    /**  首例病人发病时间*/
    @Column(name = "firstDiseaseTime",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date firstDiseaseTime;
    /**  末例病人发病时间*/
    @Column(name = "lastDiseaseTime",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastDiseaseTime;
    /**  主要症状*/
    @Column(name = "mainSymptoms",  nullable = true)
    private String mainSymptoms;
    /**  其他(对症状的详细描述详填)*/
    @Column(name = "others",  nullable = true)
    private String others;
    /**  主要体征*/
    @Column(name = "majorSigns",  nullable = true)
    private String majorSigns;
    /**  主要措施与效果*/
    @Column(name = "measureEffect",  nullable = true)
    private String measureEffect;
    /**  删除状态*/
    @Column(name = "deleteState",  nullable = true)
    private String deleteState;

    public String getDeleteState() { return deleteState; }

    public void setDeleteState(String deleteState) { this.deleteState = deleteState; }

    @Basic
    @Column(name = "reporting_unit")
    public String getReportingUnit() {
        return reportingUnit;
    }

    public void setReportingUnit(String reportingUnit) {
        this.reportingUnit = reportingUnit;
    }

    @Basic
    @Column(name = "reporting_date")
    public Date getReportingDate() {
        return reportingDate;
    }

    public void setReportingDate(Date reportingDate) {
        this.reportingDate = reportingDate;
    }

    @Basic
    @Column(name = "rapporteur")
    public String getRapporteur() {
        return rapporteur;
    }

    public void setRapporteur(String rapporteur) {
        this.rapporteur = rapporteur;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "event_name")
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Basic
    @Column(name = "message_class")
    public String getMessageClass() {
        return messageClass;
    }

    public void setMessageClass(String messageClass) {
        this.messageClass = messageClass;
    }

    @Basic
    @Column(name = "event_level")
    public String getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }

    @Basic
    @Column(name = "first_diagnosis")
    public String getFirstDiagnosis() {
        return firstDiagnosis;
    }

    public void setFirstDiagnosis(String firstDiagnosis) {
        this.firstDiagnosis = firstDiagnosis;
    }

    @Basic
    @Column(name = "first_diagnosis_time")
    public Date getFirstDiagnosisTime() {
        return firstDiagnosisTime;
    }

    public void setFirstDiagnosisTime(Date firstDiagnosisTime) {
        this.firstDiagnosisTime = firstDiagnosisTime;
    }

    @Basic
    @Column(name = "modify_diagnosis")
    public String getModifyDiagnosis() {
        return modifyDiagnosis;
    }

    public void setModifyDiagnosis(String modifyDiagnosis) {
        this.modifyDiagnosis = modifyDiagnosis;
    }

    @Basic
    @Column(name = "modify_diagnosis_time")
    public Date getModifyDiagnosisTime() {
        return modifyDiagnosisTime;
    }

    public void setModifyDiagnosisTime(Date modifyDiagnosisTime) {
        this.modifyDiagnosisTime = modifyDiagnosisTime;
    }

    @Basic
    @Column(name = "confirm_grade_time")
    public Date getConfirmGradeTime() {
        return confirmGradeTime;
    }

    public void setConfirmGradeTime(Date confirmGradeTime) {
        this.confirmGradeTime = confirmGradeTime;
    }

    @Basic
    @Column(name = "modify_grade_time")
    public Date getModifyGradeTime() {
        return modifyGradeTime;
    }

    public void setModifyGradeTime(Date modifyGradeTime) {
        this.modifyGradeTime = modifyGradeTime;
    }

    @Basic
    @Column(name = "reporting_area")
    public String getReportingArea() {
        return reportingArea;
    }

    public void setReportingArea(String reportingArea) {
        this.reportingArea = reportingArea;
    }

    @Basic
    @Column(name = "occurrence_area")
    public String getOccurrenceArea() {
        return occurrenceArea;
    }

    public void setOccurrenceArea(String occurrenceArea) {
        this.occurrenceArea = occurrenceArea;
    }

    @Basic
    @Column(name = "detailed_location")
    public String getDetailedLocation() {
        return detailedLocation;
    }

    public void setDetailedLocation(String detailedLocation) {
        this.detailedLocation = detailedLocation;
    }

    @Basic
    @Column(name = "occur_place")
    public String getOccurPlace() {
        return occurPlace;
    }

    public void setOccurPlace(String occurPlace) {
        this.occurPlace = occurPlace;
    }

    @Basic
    @Column(name = "message_source")
    public String getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(String messageSource) {
        this.messageSource = messageSource;
    }

    @Basic
    @Column(name = "source_details")
    public String getSourceDetails() {
        return sourceDetails;
    }

    public void setSourceDetails(String sourceDetails) {
        this.sourceDetails = sourceDetails;
    }

    @Basic
    @Column(name = "sweep_range")
    public String getSweepRange() {
        return sweepRange;
    }

    public void setSweepRange(String sweepRange) {
        this.sweepRange = sweepRange;
    }

    @Basic
    @Column(name = "new_case")
    public Integer getNewCase() {
        return newCase;
    }

    public void setNewCase(Integer newCase) {
        this.newCase = newCase;
    }

    @Basic
    @Column(name = "new_mortality")
    public Integer getNewMortality() {
        return newMortality;
    }

    public void setNewMortality(Integer newMortality) {
        this.newMortality = newMortality;
    }

    @Basic
    @Column(name = "remove_case")
    public Integer getRemoveCase() {
        return removeCase;
    }

    public void setRemoveCase(Integer removeCase) {
        this.removeCase = removeCase;
    }

    @Basic
    @Column(name = "accumulative_case")
    public Integer getAccumulativeCase() {
        return accumulativeCase;
    }

    public void setAccumulativeCase(Integer accumulativeCase) {
        this.accumulativeCase = accumulativeCase;
    }

    @Basic
    @Column(name = "accumulative_mortality")
    public Integer getAccumulativeMortality() {
        return accumulativeMortality;
    }

    public void setAccumulativeMortality(Integer accumulativeMortality) {
        this.accumulativeMortality = accumulativeMortality;
    }

    @Basic
    @Column(name = "event_time")
    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    @Basic
    @Column(name = "reporting_time")
    public Date getReportingTime() {
        return reportingTime;
    }

    public void setReportingTime(Date reportingTime) {
        this.reportingTime = reportingTime;
    }

    @Basic
    @Column(name = "first_disease_time")
    public Date getFirstDiseaseTime() {
        return firstDiseaseTime;
    }

    public void setFirstDiseaseTime(Date firstDiseaseTime) {
        this.firstDiseaseTime = firstDiseaseTime;
    }

    @Basic
    @Column(name = "last_disease_time")
    public Date getLastDiseaseTime() {
        return lastDiseaseTime;
    }

    public void setLastDiseaseTime(Date lastDiseaseTime) {
        this.lastDiseaseTime = lastDiseaseTime;
    }

    @Basic
    @Column(name = "main_symptoms")
    public String getMainSymptoms() {
        return mainSymptoms;
    }

    public void setMainSymptoms(String mainSymptoms) {
        this.mainSymptoms = mainSymptoms;
    }

    @Basic
    @Column(name = "others")
    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    @Basic
    @Column(name = "major_signs")
    public String getMajorSigns() {
        return majorSigns;
    }

    public void setMajorSigns(String majorSigns) {
        this.majorSigns = majorSigns;
    }

    @Basic
    @Column(name = "measure_effect")
    public String getMeasureEffect() {
        return measureEffect;
    }

    public void setMeasureEffect(String measureEffect) {
        this.measureEffect = measureEffect;
    }

}
