package com.yihu.ehr.health.Elderlyhealthy.entity;

import com.yihu.ehr.entity.BaseAssignedEntity;
import javax.persistence.*;
import java.util.Date;


@Entity
public class ElderlyFindEntity extends BaseAssignedEntity {
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
