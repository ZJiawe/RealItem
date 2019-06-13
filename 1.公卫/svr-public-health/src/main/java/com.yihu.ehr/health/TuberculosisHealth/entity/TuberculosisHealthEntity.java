package com.yihu.ehr.health.TuberculosisHealth.entity;

import com.yihu.ehr.entity.BaseAssignedEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tuberculosishealth")
@Access(value = AccessType.PROPERTY)
public class TuberculosisHealthEntity extends BaseAssignedEntity  {
    @Column(name = "area",  nullable = true)
    private String area;//辖区

    @Column(name = "year",  nullable = true)
    private Integer year;//年份

    @Column(name = "tu_number",  nullable = true)
    private Integer tuNumber;//肺结核患者人数

    @Column(name = "tum_number",  nullable = true)
    private Integer tumNumber;//肺结核患者管理人数

    @Column(name = "tre_number",  nullable = true)
    private Integer treNumber;//已完成治疗的肺结核患者人数

    @Column(name = "rule_number",  nullable = true)
    private Integer ruleNumber;//规则服药的肺结核患者人数

    @Column(name = "add_number",  nullable = true)
    private Integer addNumber;//年度新增肺结核病管理人数

    @Column(name = "out_number",  nullable = true)
    private Integer outNumber;//迁出（死亡）人数

    @Column(name = "tum_rate",  nullable = true)
    private String tumRate;//肺结核患者管理率

    @Column(name = "rule_rate",  nullable = true)
    private String ruleRate;//规则服药率


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public String getTumRate() {
        return tumRate;
    }
    public void setTumRate(String tumRate) {
        this.tumRate = tumRate;
    }
    
    public String getRuleRate() {
        return ruleRate;
    }

    public void setRuleRate(String ruleRate) {
        this.ruleRate = ruleRate;
    }



    public Integer getTuNumber() {
        return tuNumber;
    }

    public void setTuNumber(Integer tuNumber) {
        this.tuNumber = tuNumber;
    }


    public Integer getTumNumber() {
        return tumNumber;
    }

    public void setTumNumber(Integer tumNumber) {
        this.tumNumber = tumNumber;
    }


    public Integer getTreNumber() {
        return treNumber;
    }

    public void setTreNumber(Integer treNumber) {
        this.treNumber = treNumber;
    }


    public Integer getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(Integer ruleNumber) {
        this.ruleNumber = ruleNumber;
    }


    public Integer getAddNumber() {
        return addNumber;
    }

    public void setAddNumber(Integer addNumber) {
        this.addNumber = addNumber;
    }


    public Integer getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(Integer outNumber) {
        this.outNumber = outNumber;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

}
