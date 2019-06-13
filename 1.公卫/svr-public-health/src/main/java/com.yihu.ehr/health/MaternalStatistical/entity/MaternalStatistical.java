package com.yihu.ehr.health.MaternalStatistical.entity;

import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
@Entity
@Access(value = AccessType.PROPERTY)
@Table(name = "maternal_statistical")
public class MaternalStatistical  extends BaseAssignedEntity {
    private String area;//辖区
    private Integer year;//年份
    private Integer permanentPopulation;//辖区常住人口数
    private Integer liveBirthNumber;//辖区内活产数
    private Integer earlyPregnancy;//早孕建册人数
    private String earlyPregnancyRate;//早孕建册率（辖区内孕 13 周之前建册并进行第一次产前检查的产妇人 数/该地该时间段内活产数×100％）
    private Integer postpartumVisits;//产后访视产妇数
    private String postpartumVisitsRate;//产后访视率（辖区内产妇出院后 28 天内接受过产后访视的产妇人数/ 该地该时间内活产数×100％）
    private Integer managementWomen;//年度内新增产妇管理人数
    private Integer custodyWomen;//年度内结案产妇管理人数
    private Integer movedNumber;//迁出（死亡）孕产妇人数
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


    public Integer getLiveBirthNumber() {
        return liveBirthNumber;
    }

    public void setLiveBirthNumber(Integer liveBirthNumber) {
        this.liveBirthNumber = liveBirthNumber;
    }



    public Integer getEarlyPregnancy() {
        return earlyPregnancy;
    }

    public void setEarlyPregnancy(Integer earlyPregnancy) {
        this.earlyPregnancy = earlyPregnancy;
    }

    public String getEarlyPregnancyRate() {
        return earlyPregnancyRate;
    }

    public void setEarlyPregnancyRate(String earlyPregnancyRate) {
        this.earlyPregnancyRate = earlyPregnancyRate;
    }

    public Integer getPostpartumVisits() {
        return postpartumVisits;
    }

    public void setPostpartumVisits(Integer postpartumVisits) {
        this.postpartumVisits = postpartumVisits;
    }

    public String getPostpartumVisitsRate() {
        return postpartumVisitsRate;
    }

    public void setPostpartumVisitsRate(String postpartumVisitsRate) { this.postpartumVisitsRate = postpartumVisitsRate; }


    public Integer getManagementWomen() {
        return managementWomen;
    }

    public void setManagementWomen(Integer managementWomen) {
        this.managementWomen = managementWomen;
    }


    public Integer getCustodyWomen() {
        return custodyWomen;
    }

    public void setCustodyWomen(Integer custodyWomen) {
        this.custodyWomen = custodyWomen;
    }


    public Integer getMovedNumber() {
        return movedNumber;
    }

    public void setMovedNumber(Integer movedNumber) {
        this.movedNumber = movedNumber;
    }

    public String getDeleteState() { return deleteState; }

    public void setDeleteState(String deleteState) { this.deleteState = deleteState; }
}
