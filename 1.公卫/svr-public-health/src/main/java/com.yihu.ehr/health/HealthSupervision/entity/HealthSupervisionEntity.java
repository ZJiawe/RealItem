package com.yihu.ehr.health.HealthSupervision.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "health_supervision")
@Access(value = AccessType.PROPERTY)
public class HealthSupervisionEntity extends BaseAssignedEntity {
    /**  机构名称*/
    @Column(name = "organization",  nullable = true)
    private String organization;
    /**  发现时间*/
    @Column(name = "find_time",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date findTime;
    /**  信息类别*/
    @Column(name = "inform_type",  nullable = true)
    private String informType;
    /**  信息内容*/
    @Column(name = "inform_content",  nullable = true)
    private String informContent;
    /**  报告时间*/
    @Column(name = "reporte_time",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date reporteTime;
    /**  报告人*/
    @Column(name = "reporter",  nullable = true)
    private String reporter;
    /**  删除状态*/
    @Column(name = "delete_state",  nullable = true)
    private String deleteState;

    public String getDeleteState() { return deleteState; }

    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }

    public String getOrganization() { return organization; }

    public void setOrganization(String organization) {
        this.organization = organization;
    }


    public Date getFindTime() {
        return findTime;
    }

    public void setFindTime(Date findTime) {
        this.findTime = findTime;
    }


    public String getInformType() {
        return informType;
    }

    public void setInformType(String informType) {
        this.informType = informType;
    }


    public String getInformContent() {
        return informContent;
    }

    public void setInformContent(String informContent) {
        this.informContent = informContent;
    }


    public Date getReporteTime() {
        return reporteTime;
    }

    public void setReporteTime(Date reporteTime) {
        this.reporteTime = reporteTime;
    }


    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }


}
