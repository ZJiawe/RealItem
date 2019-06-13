package com.yihu.ehr.health.Totaldiabetes.entity;

import org.hibernate.annotations.GenericGenerator;

import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "total_diabetes")
@Access(value = AccessType.PROPERTY)
public class Totaldiabetes extends BaseAssignedEntity {
    /**地区*/
    @Column(name = "area",  nullable = true)
    private String area;
    /**辖区常住人口数*/
    @Column(name = "people_count",  nullable = true)
    private Integer peopleCount;
    /**年份*/
    @Column(name = "year",  nullable = true)
    private Integer year;
    /**辖区内2型糖尿病患者总人数*/
    @Column(name = "area_people",  nullable = true)
    private Integer areaPeople;
    /**2型糖尿病患者总人数*/
    @Column(name = "peoples",  nullable = true)
    private Integer peoples;
    /**年度内2型糖尿病患者管理人数*/
    @Column(name = "year_people",  nullable = true)
    private Integer yearPeople;
    /**年度内2型糖尿病患者规范管理人数*/
    @Column(name = "mange_people",  nullable = true)
    private Integer mangePeople;
    /**2型糖尿病患者规范管理率(%)*/
    @Column(name = "mange_rate",  nullable = true)
    private String mangeRate;
    /**年度内最近一次随访血糖达标的糖尿病患病管理人数*/
    @Column(name = "near_people",  nullable = true)
    private Integer nearPeople;
    /**2型糖尿病管理人群血糖控制率(%)*/
    @Column(name = "control_rate",  nullable = true)
    private String controlRate;
    /**年度内新增糖尿病管理人数*/
    @Column(name = "add_people",  nullable = true)
    private Integer addPeople;
    /**迁出（死亡)人数*/
    @Column(name = "die_people",  nullable = true)
    private Integer diePeople;


    public Totaldiabetes() {
    }
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public Integer getPeopleCount() {
        return peopleCount;
    }


    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }


    public Integer getAreaPeople() {
        return areaPeople;
    }

    public void setAreaPeople(Integer areaPeople) {
        this.areaPeople = areaPeople;
    }


    public Integer getPeoples() {
        return peoples;
    }

    public void setPeoples(Integer peoples) {
        this.peoples = peoples;
    }


    public Integer getYearPeople() {
        return yearPeople;
    }

    public void setYearPeople(Integer yearPeople) {
        this.yearPeople = yearPeople;
    }


    public Integer getMangePeople() {
        return mangePeople;
    }

    public void setMangePeople(Integer mangePeople) {
        this.mangePeople = mangePeople;
    }


    public String getMangeRate() {
        return mangeRate;
    }

    public void setMangeRate(String mangeRate) {
        this.mangeRate = mangeRate;
    }


    public Integer getNearPeople() {
        return nearPeople;
    }

    public void setNearPeople(Integer nearPeople) {
        this.nearPeople = nearPeople;
    }


    public String getControlRate() {
        return controlRate;
    }

    public void setControlRate(String controlRate) {
        this.controlRate = controlRate;
    }


    public Integer getAddPeople() {
        return addPeople;
    }

    public void setAddPeople(Integer addPeople) {
        this.addPeople = addPeople;
    }


    public Integer getDiePeople() {
        return diePeople;
    }

    public void setDiePeople(Integer diePeople) {
        this.diePeople = diePeople;
    }
}
