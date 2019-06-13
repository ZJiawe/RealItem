package com.yihu.ehr.health.FirstPrenatalDiagnosis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Access(value = AccessType.PROPERTY)
@Table(name = "first_prenatal_diagnosis")
public class FirstPrenatalDiagnosis  extends BaseAssignedEntity {


    private String name;//姓名

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date  date;//填表日期

    private String area;//身份证

    private String peopleId;//身份证

    private Integer gestationalWeeks;// 孕周

    private String gravidaAge;//孕妇年龄

    private String husbandName;//丈夫姓名

    private String husbandAge;//丈夫年龄

    private String husbandPhone;//丈夫电话

    private Integer gestationalNumber;//孕次

    private Integer vaginalDelivery;//阴道分娩次

    private Integer cesareanSection;//剖宫产次


    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date lastMenses;//末次月经

    private String expect;//预产期

    private String pastHistory;//既往史

    private String familyHistory;//家族史

    private String personalHistory;//个人史

    private String abOperation;//妇产科手术史

    private String historyPregnancy;//孕产史

    private Integer height;//身高

    private Integer weight;//体重

    private Integer blood;//血压

    private Integer bodymass;//体质指数

    private String heart;//心脏

    private String lungs;//肺部

    private String vulva;//外阴

    private String vagina;//阴道

    private String cervix;//宫颈

    private String uterus;//子宫

    private String enclosure;//附件

    private Integer hemoglobin;//血红蛋白值

    private Integer whiteBlood;//白细胞计数值

    private Integer plateletCount;//血小板计数值

    private Integer otherBlood;//其他（血常规的）

    private Integer urineProtein;//尿蛋白

    private String urineSugar;//尿糖

    private String urineKetone;//尿酮体

    private String bld;//尿潜血

    private String otherUrine;//其他（尿常规的）

    private String bloodType;//血型

    private Integer bloodSugar;//血糖

    private Integer sgpt;//血清谷丙转氨酶

    private Integer sgot;//血清谷草转氨酶

    private Integer albumin;//白蛋白

    private Integer totalBilirubin;//总胆红素

    private Integer conjugated;//结合胆红素

    private Integer scr;//血清肌酐

    private Integer bloodUrea;//血尿素

    private String vaginalSecretions;//阴道分泌物

    private String vaginalCleanliness;//阴道清洁度

    private String hepatitisB;//乙型肝炎

    private String syphilis;//梅毒血清学试验

    private String hivAntibody;//HIV抗体检测

    private String bMode;//B超

    private String otherSassist;//其他（辅助检查的）

    private String population;//总体评估

    private String healthcare;//保健指导

    private String insandDep;//机构及科室

    private String referral;//转诊

    private String reason;//原因

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date nextdate;//下次随访日期

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date firstDate;//第一次随访日期

    private String doctor;//随访医生

    private String deleteState;    /**  删除状态*/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date  getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPeopleId() { return peopleId; }

    public void setPeopleId(String peopleId) { this.peopleId = peopleId; }

    public Integer getGestationalWeeks() {
        return gestationalWeeks;
    }

    public void setGestationalWeeks(Integer gestationalWeeks) {
        this.gestationalWeeks = gestationalWeeks;
    }


    public String getGravidaAge() {
        return gravidaAge;
    }

    public void setGravidaAge(String gravidaAge) { this.gravidaAge = gravidaAge; }


    public String getHusbandName() {
        return husbandName;
    }

    public void setHusbandName(String husbandName) {
        this.husbandName = husbandName;
    }


    public String getHusbandAge() {
        return husbandAge;
    }

    public void setHusbandAge(String husbandAge) {
        this.husbandAge = husbandAge;
    }

    public String getHusbandPhone() {
        return husbandPhone;
    }

    public void setHusbandPhone(String husbandPhone) { this.husbandPhone = husbandPhone; }

    public Integer getGestationalNumber() {
        return gestationalNumber;
    }

    public void setGestationalNumber(Integer gestationalNumber) {
        this.gestationalNumber = gestationalNumber;
    }


    public Integer getVaginalDelivery() {
        return vaginalDelivery;
    }

    public void setVaginalDelivery(Integer vaginalDelivery) {
        this.vaginalDelivery = vaginalDelivery;
    }


    public Integer getCesareanSection() {
        return cesareanSection;
    }

    public void setCesareanSection(Integer cesareanSection) {
        this.cesareanSection = cesareanSection;
    }


    public Date getLastMenses() {
        return lastMenses;
    }

    public void setLastMenses(Date lastMenses) {
        this.lastMenses = lastMenses;
    }


    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
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


    public String getPersonalHistory() {
        return personalHistory;
    }

    public void setPersonalHistory(String personalHistory) {
        this.personalHistory = personalHistory;
    }


    public String getAbOperation() {
        return abOperation;
    }

    public void setAbOperation(String abOperation) {
        this.abOperation = abOperation;
    }


    public String getHistoryPregnancy() {
        return historyPregnancy;
    }

    public void setHistoryPregnancy(String historyPregnancy) {
        this.historyPregnancy = historyPregnancy;
    }


    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }


    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }


    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }


    public Integer getBodymass() {
        return bodymass;
    }

    public void setBodymass(Integer bodymass) {
        this.bodymass = bodymass;
    }


    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }


    public String getLungs() {
        return lungs;
    }

    public void setLungs(String lungs) {
        this.lungs = lungs;
    }


    public String getVulva() {
        return vulva;
    }

    public void setVulva(String vulva) {
        this.vulva = vulva;
    }


    public String getVagina() {
        return vagina;
    }

    public void setVagina(String vagina) {
        this.vagina = vagina;
    }


    public String getCervix() {
        return cervix;
    }

    public void setCervix(String cervix) {
        this.cervix = cervix;
    }


    public String getUterus() {
        return uterus;
    }

    public void setUterus(String uterus) {
        this.uterus = uterus;
    }


    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }


    public Integer getHemoglobin() { return hemoglobin; }

    public void setHemoglobin(Integer hemoglobin) {
        this.hemoglobin = hemoglobin;
    }


    public Integer getWhiteBlood() {
        return whiteBlood;
    }

    public void setWhiteBlood(Integer whiteBlood) {
        this.whiteBlood = whiteBlood;
    }


    public Integer getPlateletCount() {
        return plateletCount;
    }

    public void setPlateletCount(Integer plateletCount) {
        this.plateletCount = plateletCount;
    }


    public Integer getOtherBlood() {
        return otherBlood;
    }

    public void setOtherBlood(Integer otherBlood) {
        this.otherBlood = otherBlood;
    }


    public Integer getUrineProtein() {
        return urineProtein;
    }

    public void setUrineProtein(Integer urineProtein) {
        this.urineProtein = urineProtein;
    }


    public String getUrineSugar() {
        return urineSugar;
    }

    public void setUrineSugar(String urineSugar) {
        this.urineSugar = urineSugar;
    }


    public String getUrineKetone() {
        return urineKetone;
    }

    public void setUrineKetone(String urineKetone) {
        this.urineKetone = urineKetone;
    }


    public String getBld() {
        return bld;
    }

    public void setBld(String bld) {
        this.bld = bld;
    }


    public String getOtherUrine() {
        return otherUrine;
    }

    public void setOtherUrine(String otherUrine) {
        this.otherUrine = otherUrine;
    }


    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }


    public Integer getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(Integer bloodSugar) {
        this.bloodSugar = bloodSugar;
    }


    public Integer getSgpt() {
        return sgpt;
    }

    public void setSgpt(Integer sgpt) {
        this.sgpt = sgpt;
    }


    public Integer getSgot() {
        return sgot;
    }

    public void setSgot(Integer sgot) {
        this.sgot = sgot;
    }


    public Integer getAlbumin() {
        return albumin;
    }

    public void setAlbumin(Integer albumin) {
        this.albumin = albumin;
    }


    public Integer getTotalBilirubin() {
        return totalBilirubin;
    }

    public void setTotalBilirubin(Integer totalBilirubin) {
        this.totalBilirubin = totalBilirubin;
    }


    public Integer getConjugated() {
        return conjugated;
    }

    public void setConjugated(Integer conjugated) {
        this.conjugated = conjugated;
    }


    public Integer getScr() {
        return scr;
    }

    public void setScr(Integer scr) {
        this.scr = scr;
    }


    public Integer getBloodUrea() {
        return bloodUrea;
    }

    public void setBloodUrea(Integer bloodUrea) {
        this.bloodUrea = bloodUrea;
    }


    public String getVaginalSecretions() {
        return vaginalSecretions;
    }

    public void setVaginalSecretions(String vaginalSecretions) {
        this.vaginalSecretions = vaginalSecretions;
    }


    public String getVaginalCleanliness() {
        return vaginalCleanliness;
    }

    public void setVaginalCleanliness(String vaginalCleanliness) {
        this.vaginalCleanliness = vaginalCleanliness;
    }


    public String getHepatitisB() {
        return hepatitisB;
    }

    public void setHepatitisB(String hepatitisB) {
        this.hepatitisB = hepatitisB;
    }


    public String getSyphilis() {
        return syphilis;
    }

    public void setSyphilis(String syphilis) {
        this.syphilis = syphilis;
    }


    public String getHivAntibody() {
        return hivAntibody;
    }

    public void setHivAntibody(String hivAntibody) {
        this.hivAntibody = hivAntibody;
    }


    public String getBMode() {
        return bMode;
    }

    public void setBMode(String bMode) {
        this.bMode = bMode;
    }


    public String getOtherSassist() {
        return otherSassist;
    }

    public void setOtherSassist(String otherSassist) {
        this.otherSassist = otherSassist;
    }


    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }


    public String getHealthcare() {
        return healthcare;
    }

    public void setHealthcare(String healthcare) {
        this.healthcare = healthcare;
    }


    public String getInsandDep() {
        return insandDep;
    }

    public void setInsandDep(String insandDep) {
        this.insandDep = insandDep;
    }


    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getNextdate() {
        return nextdate;
    }

    public void setNextdate(Date nextdate) {
        this.nextdate = nextdate;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDeleteState() { return deleteState; }

    public void setDeleteState(String deleteState) { this.deleteState = deleteState; }

}
