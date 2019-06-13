package com.yihu.ehr.health.PerinforFileEmigrate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;

import javax.persistence.*;

@Entity
@Table(name = "perinforfileemigrate")
@Access(value = AccessType.PROPERTY)
public class PerinforFileEmigrate extends BaseAssignedEntity{
    /**
     * 档案编号
     */
    private long fileId;
    /**
     * 档案状态
     */
    private String fileStatus;
    /**
     * 存活状态
     */
    private String livestate;
    /**
     * 死亡日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private java.sql.Date deathDate;
    /**
     * 姓名
     */
    @Column(name = "name",  nullable = true)
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private java.sql.Date birth;
    /**
     * 所属社区
     */
    private String belongComm;
    /**
     * 证件类型
     */
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
    private String workPlace;
    /**
     * 本人电话
     */
    private String tPhoneNum;
    /**
     * 联系人姓名
     */
    private String phonerName;
    /**
     * 联系人电话
     */
    private String phonerNum;
    /**
     * 现住址
     */
    private String local;
    /**
     * 医保卡号
     */
    private String medicalNum;
    /**
     * 常住类型
     */
    private String residencePlace;
    /**
     * 民族
     */
    private String ethnic;
    /**
     * 户口所在地
     */
    private String localPlace;
    /**
     * 户籍地址
     */
    private String houseAddress;
    /**
     * 户籍迁出地址
     */
    private String houseAddress1;
    /**
     * 户籍迁入地址
     */
    private String houseAddress2;
    /**
     * 户籍迁移原因
     */
    private String migrationReason;
    /**
     * 内部编号
     */
    private String internalNum;
    /**
     * 高血压编号
     */
    private String hyperNum;
    /**
     * 糖尿病编号
     */
    private String diabetesNum;
    /**
     * 签约号
     */
    private String signNum;
    /**
     * 血型
     */
    private String bloodType;
    /**
     * 文化程度
     */
    private String educational;
    /**
     * 职业
     */
    private String occupation;
    /**
     * 婚姻状况
     */
    private String maritalStatus;
    /**
     * 医疗费用支付方式
     */
    private String expensesPay;
    /**
     * 药物过敏史
     */
    private String drugAllergyHistory;
    /**
     * 暴露史
     */
    private String exposureHistory;
    /**
     * 既往病史
     */
    private String pastHistory;
    /**
     * 家族病史
     */
    private String familyHistory;
    /**
     * 父亲病史
     */
    private String fatherHistory;
    /**
     * 母亲病史
     */
    private String matherHistory;
    /**
     * 兄弟姐妹病史
     */
    private String bortherHistory;
    /**
     * 子女病史
     */
    private String sonHistory;
    /**
     * 遗传病史
     */
    private String geneticHistory;
    /**
     * 有无残疾
     */
    @Column(name = "dIsability")
    private String dIsability;
    /**
     * 厨房排风情况
     */
    @Column(name = "Kitchen_exhaust")
    private String kitchenExhaust;
    /**
     * 燃料类型
     */
    @Column(name = "fuel")
    private String fuel;
    /**
     * 饮水
     */
    private String water;
    /**
     * 厕所
     */
    private String toilet;
    /**
     * 禽畜栏
     */
    private String livestockBar;
    /**
     * 手术
     */
    private String operation;
    /**
     * 手术名称1
     */
    private String operation1;
    /**
     * 手术时间1
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private java.sql.Date oTime1;
    /**
     * 手术名称2
     */
    private String operation2;
    /**
     * 手术时间2
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private java.sql.Date oTime2;
    /**
     * 外伤
     */
    private String trauma;
    /**
     * 外伤名称1
     */
    private String trauma1;
    /**
     * 外伤时间1
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private java.sql.Date tTime1;
    /**
     * 外伤名称2
     */
    private String trauma2;
    /**
     * 外伤时间2
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private java.sql.Date tTime2;
    /**
     * 输血
     */
    private String transfusion;
    /**
     * 输血时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private java.sql.Date transfusionTime;
    /**
     * 删除状态
     */
    private String deleteState;


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getFileId() { return fileId; }

    public void setFileId(long fileId) { this.fileId = fileId; }


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
    public java.sql.Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(java.sql.Date deathDate) {
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


    public java.sql.Date getBirth() {
        return birth;
    }

    public void setBirth(java.sql.Date birth) {
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


    public String getHouseAddress1() {
        return houseAddress1;
    }

    public void setHouseAddress1(String houseAddress1) {
        this.houseAddress1 = houseAddress1;
    }


    public String getHouseAddress2() {
        return houseAddress2;
    }

    public void setHouseAddress2(String houseAddress2) {
        this.houseAddress2 = houseAddress2;
    }


    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }


    public String getMigrationReason() {
        return migrationReason;
    }

    public void setMigrationReason(String migrationReason) {
        this.migrationReason = migrationReason;
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


    public String getDIsability() {
        return dIsability;
    }

    public void setDIsability(String dIsability) {
        this.dIsability = dIsability;
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


    public String getOperation() { return operation; }

    public void setOperation(String operation) {
        this.operation = operation;
    }


    public String getOperation1() { return operation1; }

    public void setOperation1(String operation1) {
        this.operation1 = operation1;
    }


    public String getOperation2() { return operation2; }

    public void setOperation2(String operation2) {
        this.operation2 = operation2;
    }


    public java.sql.Date getoTime1() {
        return oTime1;
    }

    public void setoTime1(java.sql.Date oTime1) { this.oTime1 = oTime1; }


    public java.sql.Date getoTime2() { return oTime2; }

    public void setoTime2(java.sql.Date oTime2) { this.oTime2 = oTime2; }



    public String getTrauma() { return trauma; }

    public void setTrauma(String trauma) {
        this.trauma = trauma;
    }


    public String getTrauma1() { return trauma1; }

    public void setTrauma1(String trauma1) {
        this.trauma1 = trauma1;
    }


    public String getTrauma2() { return trauma2; }

    public void setTrauma2(String trauma2) {
        this.trauma2 = trauma2;
    }


    public java.sql.Date gettTime1() { return tTime1; }

    public void settTime1(java.sql.Date tTime1) { this.tTime1 = tTime1; }


    public java.sql.Date gettTime2() { return tTime2; }

    public void settTime2(java.sql.Date tTime2) { this.tTime2 = tTime2; }


    public String getTransfusion() { return transfusion; }

    public void setTransfusion(String transfusion) {
        this.transfusion = transfusion;
    }


    public java.sql.Date getTransfusionTime() { return transfusionTime; }

    public void setTransfusionTime(java.sql.Date transfusionTime) { this.transfusionTime = transfusionTime; }

    public String getDeleteState() { return deleteState; }

    public void setDeleteState(String deleteState) { this.deleteState = deleteState; }

}
