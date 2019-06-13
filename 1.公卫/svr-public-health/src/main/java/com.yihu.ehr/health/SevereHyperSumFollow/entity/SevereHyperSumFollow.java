package com.yihu.ehr.health.SevereHyperSumFollow.entity;

import com.yihu.ehr.entity.BaseAssignedEntity;

import javax.persistence.*;

@Entity
@Table(name = "hyper_sum")
@Access(value = AccessType.PROPERTY)
public class SevereHyperSumFollow extends BaseAssignedEntity {

    /**  地区*/
    @Column(name = "area",  nullable = true)
    private String area;
    @Column(name = "year",  nullable = true)
    private Integer year;
    @Column(name = "deathsNumber",  nullable = true)
    private Integer deathsNumber;/**  迁出（死亡)人数*/
    @Column(name = "residentsNumber",  nullable = true)
    private Integer residentsNumber;/**  辖区常住人口数*/
    @Column(name = "highNumberYear",  nullable = true)
    private Integer highNumberYear;/**  年度内高血压患者管理人数(人)*/
    @Column(name = "yearHighNearNumber",  nullable = true)
    private Integer yearHighNearNumber;/**  年度内最近一次随访血压达标的高血压患病管理人数*/
    @Column(name = "yearHighAddNumber",  nullable = true)
    private Integer yearHighAddNumber;/**  年度内新增高血压管理人数*/
    @Column(name = "resHighNumber",  nullable = true)
    private Integer resHighNumber;/**  辖区内高血压患者总人数(理论数)*/
    @Column(name = "yearHighCtrRate",  nullable = true)
    private String yearHighCtrRate;/**  高血压管理人群血压控制率(%)*/
    @Column(name = "yearHighMagRate",  nullable = true)
    private String yearHighMagRate;/**  高血压患者规范管理率(%)*/
    @Column(name = "yearHighMagNumber",  nullable = true)
    private Integer yearHighMagNumber;/**  年度内高血压患者规范管理人数(人)*/
    @Column(name = "highNumber",  nullable = true)
    private Integer highNumber;/**  高压血患者总人数(人)*/


    public String getArea() {
        return area;
    }

    public void setArea(String area) { this.area=area; }

    public Integer getResidentsNumber() {
        return residentsNumber;
    }

    public void setResidentsNumber(Integer residentsNumber) {
        this.residentsNumber = residentsNumber;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getResHighNumber() {
        return resHighNumber;
    }

    public void setResHighNumber(Integer resHighNumber) {
        this.resHighNumber = resHighNumber;
    }

    public Integer getHighNumber() {
        return highNumber;
    }

    public void setHighNumber(Integer highNumber) {
        this.highNumber = highNumber;
    }

    public Integer getHighNumberYear() {
        return highNumberYear;
    }

    public void setHighNumberYear(Integer highNumberYear) {
        this.highNumberYear = highNumberYear;
    }

    public Integer getYearHighMagNumber() {
        return yearHighMagNumber;
    }

    public void setYearHighMagNumber(Integer yearHighMagNumber) {
        this.yearHighMagNumber = yearHighMagNumber;
    }

    public String getYearHighMagRate() {
        return yearHighMagRate;
    }

    public void setYearHighMagRate(String yearHighMagRate) {
        this.yearHighMagRate = yearHighMagRate;
    }

    public Integer getYearHighNearNumber() {
        return yearHighNearNumber;
    }

    public void setYearHighNearNumber(Integer yearHighNearNumber) {
        this.yearHighNearNumber = yearHighNearNumber;
    }

    public String getYearHighCtrRate() {
        return yearHighCtrRate;
    }

    public void setYearHighCtrRate(String yearHighCtrRate) {
        this.yearHighCtrRate = yearHighCtrRate;
    }

    public Integer getYearHighAddNumber() {
        return yearHighAddNumber;
    }

    public void setYearHighAddNumber(Integer yearHighAddNumber) {
        this.yearHighAddNumber = yearHighAddNumber;
    }

    public Integer getDeathsNumber() {
        return deathsNumber;
    }

    public void setDeathsNumber(Integer deathsNumber) {
        this.deathsNumber = deathsNumber;
    }
}
