package com.yihu.ehr.health.MentalStatistical.entity;

import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "mental_statistics")
@Access(value = AccessType.PROPERTY)
public class MentalStatistical  extends BaseAssignedEntity {
    @Column(name = "area",  nullable = true)
    private String area;//辖区
    @Column(name = "year",  nullable = true)
    private Integer year;//年度
    @Column(name = "permanent_population",  nullable = true)
    private Integer permanentPopulation;//辖区常住人口数
    @Column(name = "total_number_mental",  nullable = true)
    private Integer totalNumberMental;//辖区内严重精神障碍患病总人数(理论数)
    @Column(name = "mental_illness_year",  nullable = true)
    private Integer mentalIllnessYear;//年度内登记在册的确诊严重精神障碍患者人数(人)
    @Column(name = "mental_regulated",  nullable = true)
    private Integer mentalRegulated;//年度内严重精神障碍患者规范管理人数(人)
    @Column(name = "mental_patients",  nullable = true)
    private String mentalPatients;//年度内严重精神障碍患者规范管理率(%)
    @Column(name = "new_mental",  nullable = true)
    private Integer newMental;//年度内新增严重精神障碍患者管理人数
    @Column(name = "moved_number",  nullable = true)
    private Integer movedNumber;//迁出（死亡)人数



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


    public Integer getTotalNumberMental() {
        return totalNumberMental;
    }

    public void setTotalNumberMental(Integer totalNumberMental) {
        this.totalNumberMental = totalNumberMental;
    }



    public Integer getMentalIllnessYear() {
        return mentalIllnessYear;
    }

    public void setMentalIllnessYear(Integer mentalIllnessYear) {
        this.mentalIllnessYear = mentalIllnessYear;
    }


    public Integer getMentalRegulated() {
        return mentalRegulated;
    }

    public void setMentalRegulated(Integer mentalRegulated) {
        this.mentalRegulated = mentalRegulated;
    }


    public String getMentalPatients() {
        return mentalPatients;
    }

    public void setMentalPatients(String mentalPatients) {
        this.mentalPatients = mentalPatients;
    }


    public Integer getNewMental() {
        return newMental;
    }

    public void setNewMental(Integer newMental) {
        this.newMental = newMental;
    }


    public Integer getMovedNumber() {
        return movedNumber;
    }

    public void setMovedNumber(Integer movedNumber) {
        this.movedNumber = movedNumber;
    }

}
