package com.yihu.ehr.health.MentalQuery.entity;

import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
public class MentalFind  extends BaseAssignedEntity {
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
