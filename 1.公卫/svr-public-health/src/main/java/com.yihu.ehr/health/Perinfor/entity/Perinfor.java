package com.yihu.ehr.health.Perinfor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "perinfor")
@Access(value = AccessType.PROPERTY)
public class Perinfor extends BaseAssignedEntity{
  /**
   * 档案编号
   */
  @Column(name = "file_id",  nullable = true)
  private long fileId;
  /**
   * 档案状态
   */
  @Column(name = "file_status",  nullable = true)
  private String fileStatus;
  /**
   * 存活状态
   */
  @Column(name = "livestate",  nullable = true)
  private String livestate;
  /**
   * 死亡日期
   */
  @Column(name = "death_date",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date deathDate;
  /**
   * 姓名
   */
  @Column(name = "name",  nullable = true)
  private String name;
  /**
   * 性别
   */
  @Column(name = "sex",  nullable = true)
  private String sex;
  /**
   * 生日
   */
  @Column(name = "birth",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date birth;
  /**
   * 所属社区
   */
  @Column(name = "belong_comm",  nullable = true)
  private String belongComm;
  /**
   * 证件类型
   */
  @Column(name = "docu_type",  nullable = true)
  private String docuType;
  /**
   * 身份证号
   */
  @Column(name = "people_id",  nullable = true)
  private String peopleId;
    /**
     * 卡号
     */
    @Column(name = "card_number",  nullable = true)
    private String cardNumber;
  /**
   * 工作单位
   */
  @Column(name = "work_place",  nullable = true)
  private String workPlace;
  /**
   * 本人电话
   */
  @Column(name = "tphone_num",  nullable = true)
  private String tPhoneNum;
  /**
   * 联系人姓名
   */
  @Column(name = "phoner_name",  nullable = true)
  private String phonerName;
  /**
   * 联系人电话
   */
  @Column(name = "phoner_num",  nullable = true)
  private String phonerNum;
  /**
   * 现住址
   */
  @Column(name = "local",  nullable = true)
  private String local;
  /**
   * 医保卡号
   */
  @Column(name = "medical_num",  nullable = true)
  private String medicalNum;
  /**
   * 常住类型
   */
  @Column(name = "residence_place",  nullable = true)
  private String residencePlace;
  /**
   * 民族
   */
  @Column(name = "ethnic",  nullable = true)
  private String ethnic;
  /**
   * 户口所在地
   */
  @Column(name = "local_place",  nullable = true)
  private String localPlace;
  /**
   * 户籍地址
   */
  @Column(name = "house_address",  nullable = true)
  private String houseAddress;
  /**
   * 内部编号
   */
  @Column(name = "internal_num",  nullable = true)
  private String internalNum;
  /**
   * 高血压编号
   */
  @Column(name = "hyper_num",  nullable = true)
  private String hyperNum;
  /**
   * 糖尿病编号
   */
  @Column(name = "diabetes_num",  nullable = true)
  private String diabetesNum;
  /**
   * 签约号
   */
  @Column(name = "sign_num",  nullable = true)
  private String signNum;
  /**
   * 血型
   */
  @Column(name = "blood_type",  nullable = true)
  private String bloodType;
  /**
   * 文化程度
   */
  @Column(name = "educational",  nullable = true)
  private String educational;
  /**
   * 职业
   */
  @Column(name = "occupation",  nullable = true)
  private String occupation;
  /**
   * 婚姻状况
   */
  @Column(name = "marital_status",  nullable = true)
  private String maritalStatus;
  /**
   * 医疗费用支付方式
   */
  @Column(name = "expenses_pay",  nullable = true)
  private String expensesPay;
  /**
   * 药物过敏史
   */
  @Column(name = "drug_allergy_history",  nullable = true)
  private String drugAllergyHistory;
  /**
   * 暴露史
   */
  @Column(name = "exposure_history",  nullable = true)
  private String exposureHistory;
  /**
   * 既往病史
   */
  @Column(name = "past_history",  nullable = true)
  private String pastHistory;
  /**
   * 家族病史
   */
  @Column(name = "family_history",  nullable = true)
  private String familyHistory;
  /**
   * 父亲病史
   */
  @Column(name = "father_history",  nullable = true)
  private String fatherHistory;
  /**
   * 母亲病史
   */
  @Column(name = "mather_history",  nullable = true)
  private String matherHistory;
  /**
   * 兄弟姐妹病史
   */
  @Column(name = "borther_history",  nullable = true)
  private String bortherHistory;
  /**
   * 子女病史
   */
  @Column(name = "son_history",  nullable = true)
  private String sonHistory;
  /**
   * 遗传病史
   */
  @Column(name = "genetic_history",  nullable = true)
  private String geneticHistory;
  /**
   * 有无残疾
   */
  @Column(name = "disability")
  private String disability;
  /**
   * 厨房排风情况
   */
  @Column(name = "kitchen_exhaust")
  private String kitchenExhaust;
  /**
   * 燃料类型
   */
  @Column(name = "fuel")
  private String fuel;
  /**
   * 饮水
   */
  @Column(name = "water",  nullable = true)
  private String water;
  /**
   * 厕所
   */
  @Column(name = "toilet",  nullable = true)
  private String toilet;
  /**
   * 禽畜栏
   */
  @Column(name = "livestock_bar",  nullable = true)
  private String livestockBar;
  /**
   * 手术
   */
  @Column(name = "operation",  nullable = true)
  private String operation;
  /**
   * 手术名称1
   */
  @Column(name = "operation1",  nullable = true)
  private String operation1;
  /**
   * 手术时间1
   */
  @Column(name = "o_time1",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date oTime1;
  /**
   * 手术名称2
   */
  @Column(name = "operation2",  nullable = true)
  private String operation2;
  /**
   * 手术时间2
   */
  @Column(name = "o_time2",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date oTime2;
  /**
   * 外伤
   */
  @Column(name = "trauma",  nullable = true)
  private String trauma;
  /**
   * 外伤名称1
   */
  @Column(name = "trauma1",  nullable = true)
  private String trauma1;
  /**
   * 外伤时间1
   */
  @Column(name = "t_time1",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date tTime1;
  /**
   * 外伤名称2
   */
  @Column(name = "trauma2",  nullable = true)
  private String trauma2;
  /**
   * 外伤时间2
   */
  @Column(name = "t_time2",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date tTime2;
  /**
   * 输血
   */
  @Column(name = "transfusion",  nullable = true)
  private String transfusion;
  /**
   * 输血时间
   */
  @Column(name = "transfusion_time",  nullable = true)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
  private Date transfusionTime;
  /**
   * 删除状态
   */
  @Column(name = "delete_state",  nullable = true)
  private String deleteState;
  /**
   * 孕妇
   */
  @Column(name = "pregnant",  nullable = true)
  private String pregnant;
  /**
   * 责任医生
   */
  @Column(name = "respon_doctor",  nullable = true)
  private String responDoctor;
  /**
   * 乡镇名称
   */
  @Column(name = "village_name",  nullable = true)
  private String villageName;
  /**
   *  村(居）委会名称
   */
  @Column(name = "committees",  nullable = true)
  private String committees;
  /**
   *  建档单位
   */
  @Column(name = "inputting_unit",  nullable = true)
  private String inputtingUnit;

   public String getCardNumber() {
        return cardNumber;
    }

   public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

  public long getFileId() {
     return fileId;
   }

  public void setFileId(long fileId) {
     this.fileId = fileId;
   }

  public String getFileStatus() {
    return fileStatus;
  }

  public void setFileStatus(String fileStatus) {
    this.fileStatus = fileStatus;
  }

  public String getLivestate() {
    return livestate;
  }
  public void setLivestate(String livestate) {
    this.livestate = livestate;
  }

  public Date getDeathDate() {
    return deathDate;
  }

  public void setDeathDate(Date deathDate) {
    this.deathDate = deathDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Date getBirth() {
    return birth;
  }

  public void setBirth(Date birth) {
    this.birth = birth;
  }


  public String getBelongComm() {
    return belongComm;
  }

  public void setBelongComm(String belongComm) {
    this.belongComm = belongComm;
  }


  public String getDocuType() {
    return docuType;
  }

  public void setDocuType(String docuType) {
    this.docuType = docuType;
  }


  public String getPeopleId() {
    return peopleId;
  }

  public void setPeopleId(String peopleId) {
    this.peopleId = peopleId;
  }


  public String getWorkPlace() {
    return workPlace;
  }

  public void setWorkPlace(String workPlace) {
    this.workPlace = workPlace;
  }


  public String getTPhoneNum() {
    return tPhoneNum;
  }

  public void setTPhoneNum(String tPhoneNum) {
    this.tPhoneNum = tPhoneNum;
  }


  public String getPhonerName() {
    return phonerName;
  }

  public void setPhonerName(String phonerName) {
    this.phonerName = phonerName;
  }


  public String getPhonerNum() {
    return phonerNum;
  }

  public void setPhonerNum(String phonerNum) {
    this.phonerNum = phonerNum;
  }


  public String getLocal() {
    return local;
  }

  public void setLocal(String local) {
    this.local = local;
  }


  public String getMedicalNum() {
    return medicalNum;
  }

  public void setMedicalNum(String medicalNum) {
    this.medicalNum = medicalNum;
  }


  public String getResidencePlace() {
    return residencePlace;
  }

  public void setResidencePlace(String residencePlace) {
    this.residencePlace = residencePlace;
  }


  public String getEthnic() {
    return ethnic;
  }

  public void setEthnic(String ethnic) {
    this.ethnic = ethnic;
  }


  public String getLocalPlace() {
    return localPlace;
  }

  public void setLocalPlace(String localPlace) {
    this.localPlace = localPlace;
  }


  public String getHouseAddress() {
    return houseAddress;
  }

  public void setHouseAddress(String houseAddress) {
    this.houseAddress = houseAddress;
  }


  public String getInternalNum() {
    return internalNum;
  }

  public void setInternalNum(String internalNum) {
    this.internalNum = internalNum;
  }


  public String getHyperNum() {
    return hyperNum;
  }

  public void setHyperNum(String hyperNum) {
    this.hyperNum = hyperNum;
  }


  public String getDiabetesNum() {
    return diabetesNum;
  }

  public void setDiabetesNum(String diabetesNum) {
    this.diabetesNum = diabetesNum;
  }


  public String getSignNum() {
    return signNum;
  }

  public void setSignNum(String signNum) {
    this.signNum = signNum;
  }


  public String getBloodType() {
    return bloodType;
  }

  public void setBloodType(String bloodType) {
    this.bloodType = bloodType;
  }

  public String getEducational() {
    return educational;
  }

  public void setEducational(String educational) {
    this.educational = educational;
  }


  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }


  public String getMaritalStatus() {
    return maritalStatus;
  }

  public void setMaritalStatus(String maritalStatus) {
    this.maritalStatus = maritalStatus;
  }


  public String getExpensesPay() {
    return expensesPay;
  }

  public void setExpensesPay(String expensesPay) {
    this.expensesPay = expensesPay;
  }


  public String getDrugAllergyHistory() {
    return drugAllergyHistory;
  }

  public void setDrugAllergyHistory(String drugAllergyHistory) {
    this.drugAllergyHistory = drugAllergyHistory;
  }


  public String getExposureHistory() {
    return exposureHistory;
  }

  public void setExposureHistory(String exposureHistory) {
    this.exposureHistory = exposureHistory;
  }


  public String getPastHistory() {
    return pastHistory;
  }

  public void setPastHistory(String pastHistory) {
    this.pastHistory = pastHistory;
  }


  public String getFamilyHistory() {
    return familyHistory;
  }

  public void setFamilyHistory(String familyHistory) {
    this.familyHistory = familyHistory;
  }

  public String getSonHistory() {
    return sonHistory;
  }

  public void setSonHistory(String sonHistory) {
    this.sonHistory = sonHistory;
  }

  public String getBortherHistory() {
    return bortherHistory;
  }

  public void setBortherHistory(String bortherHistory) {
    this.bortherHistory = bortherHistory;
  }

  public String getMatherHistory() {
    return matherHistory;
  }

  public void setMatherHistory(String matherHistory) {
    this.matherHistory = matherHistory;
  }

  public String getFatherHistory() {
    return fatherHistory;
  }

  public void setFatherHistory(String fatherHistory) {
    this.fatherHistory = fatherHistory;
  }


  public String getGeneticHistory() {
    return geneticHistory;
  }

  public void setGeneticHistory(String geneticHistory) {
    this.geneticHistory = geneticHistory;
  }


  public String getDisability() {
    return disability;
  }

  public void setDisability(String disability) {
    this.disability = disability;
  }


  public String getKitchenExhaust() {
    return kitchenExhaust;
  }

  public void setKitchenExhaust(String kitchenExhaust) {
    this.kitchenExhaust = kitchenExhaust;
  }


  public String getFuel() {
    return fuel;
  }

  public void setFuel(String fuel) {
    this.fuel = fuel;
  }


  public String getWater() {
    return water;
  }

  public void setWater(String water) {
    this.water = water;
  }

  public String getToilet() {
    return toilet;
  }

  public void setToilet(String toilet) {
    this.toilet = toilet;
  }


  public String getLivestockBar() {
    return livestockBar;
  }

  public void setLivestockBar(String livestockBar) {
    this.livestockBar = livestockBar;
  }


  public String getOperation() {
     return operation;
   }

  public void setOperation(String operation) {
    this.operation = operation;
  }


  public String getOperation1() {
     return operation1;
   }

  public void setOperation1(String operation1) {
        this.operation1 = operation1;
    }


  public String getOperation2() { return operation2; }

  public void setOperation2(String operation2) {
        this.operation2 = operation2;
    }


  public Date getoTime1() {
        return oTime1;
    }

  public void setoTime1(Date oTime1) {
     this.oTime1 = oTime1;
   }


  public Date getoTime2() {
     return oTime2;
   }

  public void setoTime2(Date oTime2) {
     this.oTime2 = oTime2;

   }

  public String getTrauma() {
     return trauma;
   }

  public void setTrauma(String trauma) {
        this.trauma = trauma;
    }
  public String getTrauma1() {
     return trauma1;
   }

  public void setTrauma1(String trauma1) {
        this.trauma1 = trauma1;
    }
  public String getTrauma2() {
     return trauma2;
   }

  public void setTrauma2(String trauma2) {
        this.trauma2 = trauma2;
    }

  public Date gettTime1() {
     return tTime1;
   }

  public void settTime1(Date tTime1) {
     this.tTime1 = tTime1;
   }

  public Date gettTime2() {
     return tTime2;
   }
  public void settTime2(Date tTime2) {
     this.tTime2 = tTime2;
   }

  public String getTransfusion() {
     return transfusion;
   }

  public void setTransfusion(String transfusion) {
        this.transfusion = transfusion;
    }


  public Date getTransfusionTime() {
     return transfusionTime;
   }

  public void setTransfusionTime(Date transfusionTime) {
     this.transfusionTime = transfusionTime;
   }

  public String getDeleteState() {
     return deleteState;
   }

  public void setDeleteState(String deleteState) {
     this.deleteState = deleteState;
   }

  public String getPregnant() {
    return pregnant;
  }

  public void setPregnant(String pregnant) {
    this.pregnant = pregnant;
  }

  public String getResponDoctor() {
    return responDoctor;
  }

  public void setResponDoctor(String responDoctor) {
    this.responDoctor = responDoctor;
  }

  public String getVillageName() {
    return villageName;
  }

  public void setVillageName(String villageName) {
    this.villageName = villageName;
  }

  public String getCommittees() {
    return committees;
  }

  public void setCommittees(String committees) {
    this.committees = committees;
  }

  public String getInputtingUnit() {
    return inputtingUnit;
  }

  public void setInputtingUnit(String inputtingUnit) {
    this.inputtingUnit = inputtingUnit;
  }

}

