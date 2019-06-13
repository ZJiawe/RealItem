package com.yihu.ehr.health.SelfcareAbility.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "self_care_ability")
@Access(value = AccessType.PROPERTY)
public class SelfcareAbility extends BaseAssignedEntity {


    @Column(name = "name",  nullable = true)
    private String name;/**  姓名*/

    @Column(name = "peopleId",  nullable = true)
    private String peopleId;/**  身份证号*/

    @Column(name = "area",  nullable = true)
    private String area;/**  地区*/

    @Column(name = "workid",  nullable = true)
    private String workid;/**  工作编号*/

    @Column(name = "systemid",  nullable = true)
    private String systemid;/**  系统编号*/


    @Column(name = "formDate",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date formDate;/**  填表日期*/

    @Column(name = "meal",  nullable = true)
    private String meal; /** 进餐 */

    @Column(name = "washing",  nullable = true)
    private String washing;/** 洗漱 */

    @Column(name = "dressing",  nullable = true)
    private String dressing;/** 穿衣 */

    @Column(name = "toilet",  nullable = true)
    private String toilet; /** 如厕 */

    @Column(name = "activity",  nullable = true)
    private String activity;/** 活动 */

    @Column(name = "totalScore",  nullable = true)
    private String totalScore;/** 总得分 */

    @Column(name = "deleteState",  nullable = true)
    private String deleteState;/** dshanchu */



  //必须要有构造函数
  public SelfcareAbility() {
  }

    public String getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }

  public String getName() {
        return name;
    }

  public void setName(String name) {
        this.name = name;
    }


  public String getPeopleId() {
        return peopleId;
    }

  public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

  public String getArea() {
        return area;
    }

  public void setArea(String area) {
        this.area = area;
    }


  public String getWorkid() {
        return workid;
    }

  public void setWorkid(String workid) {
        this.workid = workid;
    }


  public String getSystemid() {
        return systemid;
    }

  public void setSystemid(String systemid) {
        this.systemid = systemid;
    }

  public Date getFormDate() {
        return formDate;
    }

  public void setFormDate(Date formDate) { this.formDate = formDate; }


  public String getMeal() {
    return meal;
  }

  public void setMeal(String meal) {
    this.meal = meal;
  }





  public String getWashing() {
    return washing;
  }

  public void setWashing(String washing) {
    this.washing = washing;
  }




  public String getDressing() {
    return dressing;
  }

  public void setDressing(String dressing) {
    this.dressing = dressing;
  }


  public String getToilet() {
    return toilet;
  }

  public void setToilet(String toilet) {
    this.toilet = toilet;
  }


  public String getActivity() {
    return activity;
  }

  public void setActivity(String activity) {
    this.activity = activity;
  }


 // public String getSelfcareAbilitycol() {
  //  return selfcareAbilitycol;
  //}

  //public void setSelfcareAbilitycol(String selfcareAbilitycol) {
  //  this.selfcareAbilitycol = selfcareAbilitycol;
 // }


  public String getTotalScore() {
    return totalScore;
  }

  public void setTotalScore(String totalScore) {
    this.totalScore = totalScore;
  }



}
