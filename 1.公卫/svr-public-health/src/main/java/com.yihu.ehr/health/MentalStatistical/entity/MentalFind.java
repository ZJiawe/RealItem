package com.yihu.ehr.health.MentalStatistical.entity;

import com.yihu.ehr.entity.BaseAssignedEntity;

public class MentalFind extends BaseAssignedEntity {
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
