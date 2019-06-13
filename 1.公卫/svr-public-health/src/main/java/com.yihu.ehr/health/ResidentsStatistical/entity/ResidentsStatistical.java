package com.yihu.ehr.health.ResidentsStatistical.entity;
import com.yihu.ehr.entity.BaseAssignedEntity;
import javax.persistence.*;

@Entity
@Access(value = AccessType.PROPERTY)
@Table(name = "residents_statistical")
public class ResidentsStatistical extends BaseAssignedEntity {


    private String area;//辖区
    private Integer year;//年度
    private Integer permanentPopulation;//辖区常住人口数
    private Integer numberSetters;//建档人数
    private String healthBuildRate;//健康档案建档率（建档人数/辖区内常住居民数×100％）
    private Integer electronicHealth;//电子健康档案建档数
    private String electronicRate;//电子健康档案建档率（建立电子健康档案人数/辖区内常住居民数× 100％
    private Integer healthDynamic;//健康档案动态使用数量
    private String healthUtilization;//健康档案动态使用率（档案中有动态记录的档案份数/档案总份数×100％。）
    private Integer newFiles;//新增档案数量
    private Integer deaths;//死亡人数
    private Integer floatingPopulation;//流动人口数
    private String deleteState;    /**  删除状态*/



    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }



    public Integer getPermanentPopulation() {
        return permanentPopulation;
    }

    public void setPermanentPopulation(Integer permanentPopulation) {
        this.permanentPopulation = permanentPopulation;
    }


    public Integer getNumberSetters() {
        return numberSetters;
    }

    public void setNumberSetters(Integer numberSetters) {
        this.numberSetters = numberSetters;
    }


    public String getHealthBuildRate() {
        return healthBuildRate;
    }

    public void setHealthBuildRate(String healthBuildRate) {
        this.healthBuildRate = healthBuildRate;
    }


    public String getElectronicRate() {
        return electronicRate;
    }

    public void setElectronicRate(String electronicRate) { this.electronicRate = electronicRate; }


    public Integer getElectronicHealth() {
        return electronicHealth;
    }

    public void setElectronicHealth(Integer electronicHealth) {
        this.electronicHealth = electronicHealth;
    }


    public Integer getHealthDynamic() {
        return healthDynamic;
    }

    public void setHealthDynamic(Integer healthDynamic) {
        this.healthDynamic = healthDynamic;
    }


    public String getHealthUtilization() {
        return healthUtilization;
    }

    public void setHealthUtilization(String healthUtilization) {
        this.healthUtilization = healthUtilization;
    }


    public Integer getNewFiles() {
        return newFiles;
    }

    public void setNewFiles(Integer newFiles) {
        this.newFiles = newFiles;
    }


    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }


    public Integer getFloatingPopulation() {
        return floatingPopulation;
    }

    public void setFloatingPopulation(Integer floatingPopulation) {
        this.floatingPopulation = floatingPopulation;
    }

    public String getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }

}
