package com.yihu.ehr.health.ChildrenMangement.entity;

import com.yihu.ehr.entity.BaseAssignedEntity;
import javax.persistence.*;
import java.util.Date;

public class ChildrenFind {
    public ChildrenFind(){};
    String doctor;
    public String getDoctor() {
        return doctor;
    }
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
