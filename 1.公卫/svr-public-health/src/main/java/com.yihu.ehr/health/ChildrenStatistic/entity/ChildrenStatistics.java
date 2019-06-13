package com.yihu.ehr.health.ChildrenStatistic.entity;

import com.yihu.ehr.entity.BaseAssignedEntity;

import javax.persistence.*;

@Entity
@Table(name = "children_statistics")
@Access(value = AccessType.PROPERTY)
public class ChildrenStatistics extends BaseAssignedEntity {
  /**  地区*/
  @Column(name = "area",  nullable = true)
  private String area;
  /**  年份*/
  @Column(name = "year",  nullable = true)
  private Integer year;
  /**  常驻人口*/
  @Column(name = "people_count",  nullable = true)
  private Integer peopleCount;
  /**  新生儿访视数*/
  @Column(name = "newborn_visit",  nullable = true)
  private Integer newbornVisit;
  /**  辖区内活产数*/
  @Column(name = "live_count",  nullable = true)
  private Integer liveCount;
  /**  新生儿访问率0~6岁儿童健康管理人数*/
  @Column(name = "visit_ratio",  nullable = true)
  private String visitRatio;
  /**  0~6岁儿童健康管理人数*/
  @Column(name = "mange_people",  nullable = true)
  private Integer mangePeople;
  /**  辖区0~6岁儿童健康管理人数*/
  @Column(name = "area_mange_people",  nullable = true)
  private Integer areaMangePeople;
  /**  0~6岁儿童健康管理率*/
  @Column(name = "manage_visit_ratio",  nullable = true)
  private String manageVisitRatio;
  /**  年度内新增儿童管理人数*/
  @Column(name = "newborn_year",  nullable = true)
  private Integer newbornYear;
  /**  迁出（死亡）儿童管理人数*/
  @Column(name = "die_people",  nullable = true)
  private Integer diePeople;

  /**  初始化类*/
  public ChildrenStatistics() {}

  public String getArea() {
    return area;
  }

  public void setArea(String area) { this.area=area; }

  public Integer getPeopleCount() {
    return peopleCount;
  }

  public void setPeopleCount(Integer peopleCount) {
    this.peopleCount = peopleCount;
  }

  public Integer getNewbornVisit() {
    return newbornVisit;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Integer getYear() {
    return year;
  }

  public void setNewbornVisit(Integer newbornVisit) {
    this.newbornVisit = newbornVisit;
  }

  public Integer getLiveCount() {
    return liveCount;
  }

  public void setLiveCount(Integer liveCount) {
    this.liveCount = liveCount;
  }

  public String getVisitRatio() {
    return visitRatio;
  }

  public void setVisitRatio(String visitRatio) {
    this.visitRatio = visitRatio;
  }

  public String getManageVisitRatio() {
    return manageVisitRatio;
  }

  public void setManageVisitRatio(String manageVisitRatio) {
    this.manageVisitRatio = manageVisitRatio;
  }


  public Integer getMangePeople() {
    return mangePeople;
  }

  public void setMangePeople(Integer mangePeople) {
    this.mangePeople = mangePeople;
  }


  public Integer getAreaMangePeople() {
    return areaMangePeople;
  }

  public void setAreaMangePeople(Integer areaMangePeople) {
    this.areaMangePeople = areaMangePeople;
  }


  public  Integer getNewbornYear() {
    return newbornYear;
  }

  public void setNewbornYear(Integer newbornYear) {
    this.newbornYear = newbornYear;
  }


  public Integer getDiePeople() {
    return diePeople;
  }

  public void setDiePeople(Integer diePeople) {
    this.diePeople = diePeople;
  }

}
