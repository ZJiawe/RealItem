package com.yihu.ehr.health.MedicalStatistical.entity;

import com.yihu.ehr.entity.BaseAssignedEntity;

import javax.persistence.*;

@Entity
@Access(value = AccessType.PROPERTY)
public class MedicalStatistical extends BaseAssignedEntity {

  /**  地区*/
  @Column(name = "area",  nullable = true)
  private String area;
  /**  年份*/
  @Column(name = "year",  nullable = true)
  private Integer year;

  /**  辖区常驻人口*/
  @Column(name = "people_count",  nullable = true)
  private Integer peopleCount;

    /**  65岁老人数量*/
  @Column(name = "elder_count",  nullable = true)
  private Integer elderCount;

  /**  0~3岁儿童数量*/
  @Column(name = "children_count",  nullable = true)
  private Integer ChildrenCount;

  /**  0-36月龄儿童管理人数*/
  @Column(name = "children_manger_count",  nullable = true)
  private Integer ChildrenMangerCount;

  /**  0~3岁接受中医药管理儿童数量*/
  @Column(name = "children_count_by_medical",  nullable = true)
  private Integer ChildrenCountByMedical;

  /**  老人管理数量*/
  @Column(name = "elder_manger_count",  nullable = true)
  private Integer ElderMangerCount;

  /**  中医药老人管理数量*/
  @Column(name = "elder_count_by_medical",  nullable = true)
  private Integer ElderCountByMedical;

  /**  中医药老人管理率*/
  @Column(name = "elder_count_by_medical_rato",  nullable = true)
  private String ElderCountByMedicalRato;

  /**  0-36月龄中医药健康管理率(%)*/
  @Column(name = "children_count_by_medical_rato",  nullable = true)
  private String ChildrenCountByMedicalRato;

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

  public Integer getPeopleCount() {
    return peopleCount;
  }

  public void setPeopleCount(Integer peopleCount) {
    this.peopleCount = peopleCount;
  }

  public Integer getChildrenCount() {
    return ChildrenCount;
  }

  public void setChildrenCount(Integer childrenCount) {
    ChildrenCount = childrenCount;
  }

  public Integer getChildrenMangerCount() {
    return ChildrenMangerCount;
  }

  public void setChildrenMangerCount(Integer childrenMangerCount) {
    ChildrenMangerCount = childrenMangerCount;
  }

  public Integer getChildrenCountByMedical() {
    return ChildrenCountByMedical;
  }

  public void setChildrenCountByMedical(Integer childrenCountByMedical) {
    ChildrenCountByMedical = childrenCountByMedical;
  }

  public Integer getElderMangerCount() {
    return ElderMangerCount;
  }

  public void setElderMangerCount(Integer elderMangerCount) {
    ElderMangerCount = elderMangerCount;
  }

  public Integer getElderCountByMedical() {
    return ElderCountByMedical;
  }

  public void setElderCountByMedical(Integer elderCountByMedical) {
    ElderCountByMedical = elderCountByMedical;
  }

  public String getElderCountByMedicalRato() {
    return ElderCountByMedicalRato;
  }

  public void setElderCountByMedicalRato(String elderCountByMedicalRato) {
    ElderCountByMedicalRato = elderCountByMedicalRato;
  }

  public String getChildrenCountByMedicalRato() {
    return ChildrenCountByMedicalRato;
  }

  public void setChildrenCountByMedicalRato(String childrenCountByMedicalRato) {
    ChildrenCountByMedicalRato = childrenCountByMedicalRato;
  }

  public Integer getElderCount() {
    return elderCount;
  }

  public void setElderCount(Integer elderCount) {
    this.elderCount = elderCount;
  }

  /**  初始化类*/
  public MedicalStatistical() {}



}
