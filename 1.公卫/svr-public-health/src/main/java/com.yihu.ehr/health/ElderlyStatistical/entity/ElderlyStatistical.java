package com.yihu.ehr.health.ElderlyStatistical.entity;

import com.yihu.ehr.entity.BaseAssignedEntity;

import javax.persistence.*;


@Entity
@Table(name = "elderly_statistics")
@Access(value = AccessType.PROPERTY)
public class ElderlyStatistical extends BaseAssignedEntity {


    @Column(name = "area",  nullable = true)
    private String area;  /**  地区*/

    @Column(name = "year",  nullable = true)
    private Integer year;  /**  年份*/

    @Column(name = "people_count",  nullable = true)
    private Integer peopleCount;  /**  辖区常驻人口*/

    @Column(name = "areasf_people",  nullable = true)
    private Integer areasfPeople;  /**  辖区65岁及以上常居数*/

    @Column(name = "elderly_count",  nullable = true)
    private Integer elderlyCount;  /**  老年人总数*/

    @Column(name = "manage_people",  nullable = true)
    private Integer managePeople;  /**  健康管理人数*/

    @Column(name = "year_area_manageradio",  nullable = true)
    private String yearAreaManageradio;  /**  老年人健康管理率*/

    @Column(name = "year_newcount",  nullable = true)
    private Integer yearNewcount;  /**  年内新增老人管理人数*/

    @Column(name = "die_people",  nullable = true)
    private Integer diePeople;  /**  迁出(死亡)人数*/

    /**  初始化类*/
    public ElderlyStatistical() {}

    public String getArea() {
        return area;
    }
    public void setArea(String area) { this.area=area; }

    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getYear() { return year; }

    public Integer getPeopleCount() {
        return peopleCount;
    }
    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Integer getAreasfPeople() {
        return areasfPeople;
    }
    public void setAreasfPeople(Integer areasfPeople) {
        this.areasfPeople = areasfPeople;
    }

    public Integer getElderlyCount() {
        return elderlyCount;
    }
    public void setElderlyCount(Integer elderlyCount) {
        this.elderlyCount = elderlyCount;
    }

    public Integer getManagePeople() {
        return managePeople;
    }
    public void setManagePeople(Integer managePeople) {
        this.managePeople = managePeople;
    }

    public String getYearAreaManageradio() {
        return yearAreaManageradio;
    }
    public void setYearAreaManageradio(String yearAreaManageradio) {
        this.yearAreaManageradio = yearAreaManageradio;
    }


    public Integer getYearNewcount() {
        return yearNewcount;
    }
    public void setYearNewcount(Integer yearNewcount) {
        this.yearNewcount = yearNewcount;
    }

    public Integer getDiePeople() {
        return diePeople;
    }
    public void setDiePeople(Integer diePeople) {
        this.diePeople = diePeople;
    }


}
