package com.yihu.ehr.health.ResidentsStatistical.entity;

import org.hibernate.annotations.GenericGenerator;

import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
public class ResidentsFind extends BaseAssignedEntity {
    Integer year;
    String area;

    public Integer getYear() { return year; }

    public void setYear(Integer year) { this.year = year; }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
