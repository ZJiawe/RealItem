package com.yihu.ehr.health.HealthCheckups.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "healthCheckups")
@Access(value = AccessType.PROPERTY)
public class HealthCheckups extends BaseAssignedEntity {
    /**
     * 姓名
    */
    @Column(name = "name",  nullable = true)
    private String name;
    /**
     * 体检日期
     */
    @Column(name = "check_date",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date checkDate;
    /**
     * 体检医生
     */
    @Column(name = "doctor",  nullable = true)
    private String doctor;
    /**
     * 症状
     */
    @Column(name = "symptoms",  nullable = true)
    private String symptoms;
    /**
     * 体温
     */
    @Column(name = "temperature",  nullable = true)
    private String temperature;
    /**
     * 脉率
     */
    @Column(name = "pulse_rate",  nullable = true)
    private String pulseRate;
    /**
     * 呼吸频率
     */
    @Column(name = "breathing",  nullable = true)
    private String breathing;
    /**
     * 身高
     */
    @Column(name = "stature",  nullable = true)
    private String stature;
    /**
     * 体重
     */
    @Column(name = "weight",  nullable = true)
    private String weight;
    /**
     * 腰围
     */
    @Column(name = "waistline",  nullable = true)
    private String waistline;
    /**
     * 体质指数
     */
    @Column(name = "bodymassindex",  nullable = true)
    private String bodymassindex;
    /**
     * 老年人健康状况自我评估
     */
    @Column(name = "self_ssessment",  nullable = true)
    private String selfAssessment;
    /**
     * 老年人生活自理能力自我评估
     */
    @Column(name = "self_care",  nullable = true)
    private String selfCare;
    /**
     * 老年人认知功能
     */
    @Column(name = "cognition",  nullable = true)
    private String cognition;
    /**
     * 老年人情感状态
     */
    @Column(name = "mood",  nullable = true)
    private String mood;
    /**
     * 锻炼频率
     */
    @Column(name = "exercise",  nullable = true)
    private String exercise;
    /**
     * 饮食
     */
    @Column(name = "diet",  nullable = true)
    private String diet;
    /**
     * 吸烟状况
     */
    @Column(name = "smoking",  nullable = true)
    private String smoking;
    /**
     * 饮酒频率
     */
    @Column(name = "drinking",  nullable = true)
    private String drinking;
    /**
     * 职业病危害因素接触史
     */
    @Column(name = "work_diseases",  nullable = true)
    private String workDiseases;
    /**
     * 口腔
     */
    @Column(name = "oral_cavity",  nullable = true)
    private String oralCavity;
    /**
     * 听力
     */
    @Column(name = "listen",  nullable = true)
    private String listen;
    /**
     * 运动功能
     */
    @Column(name = "sport",  nullable = true)
    private String sport;
    /**
     * 眼底
     */
    @Column(name = "bottom_eye",  nullable = true)
    private String bottomEye;
    /**
     * 皮肤
     */
    @Column(name = "skin",  nullable = true)
    private String skin;
    /**
     * 巩膜
     */
    @Column(name = "sclera",  nullable = true)
    private String sclera;
    /**
     * 淋巴结
     */
    @Column(name = "lymph_node",  nullable = true)
    private String lymphNode;
    /**
     * 下肢水肿
     */
    @Column(name = "limb_edema",  nullable = true)
    private String limbEdema;
    /**
     * 足背动脉搏动
     */
    @Column(name = "foot_artey_pulse",  nullable = true)
    private String footArteyPulse;
    /**
     * 肛门指诊
     */
    @Column(name = "anal_diagnosis",  nullable = true)
    private String analDiagnosis;
    /**
     * 乳腺
     */
    @Column(name = "mammary_gland",  nullable = true)
    private String mammaryGland;
    /**
     * 外阴
     */
    @Column(name = "vulva",  nullable = true)
    private String vulva;
    /**
     * 阴道
     */
    @Column(name = "vagina",  nullable = true)
    private String vagina;
    /**
     * 宫颈
     */
    @Column(name = "cervix",  nullable = true)
    private String cervix;
    /**
     * 宫体
     */
    @Column(name = "palace",  nullable = true)
    private String palace;
    /**
     * 空腹血糖
     */
    @Column(name = "fasting_bloodSugar",  nullable = true)
    private String fastingBloodSugar;
    /**
     * 心电图
     */
    @Column(name = "electrocardiogram",  nullable = true)
    private String electrocardiogram;
    /**
     * 尿微量白蛋白
     */
    @Column(name = "urine_microalbumin",  nullable = true)
    private String urineMicroalbumin;
    /**
     * 大便潜血
     */
    @Column(name = "shitblood",  nullable = true)
    private String shitblood;
    /**
     * 糖化血红蛋白
     */
    @Column(name = "glycated_glycemic",  nullable = true)
    private String glycatedGlycemic;
    /**
     * 乙型肝炎表面抗原
     */
    @Column(name = "hepatitisb",  nullable = true)
    private String hepatitisb;
    /**
     * 胸部X片
     */
    @Column(name = "chestxray",  nullable = true)
    private String chestxray;
    /**
     * 腹部B超
     */
    @Column(name = "bsuper",  nullable = true)
    private String bsuper;
    /**
     * 宫颈切片
     */
    @Column(name = "cervical_smear",  nullable = true)
    private String cervicalSmear;
    /**
     * 脑血管疾病
     */
    @Column(name = "cerebrovascular",  nullable = true)
    private String cerebrovascular;
    /**
     * 肾脏疾病
     */
    @Column(name = "kidney_disease",  nullable = true)
    private String kidneyDisease;
    /**
     * 心脏疾病
     */
    @Column(name = "heart_disease",  nullable = true)
    private String heartDisease;
    /***
     * 血管疾病
     */
    @Column(name = "vascular_disease",  nullable = true)
    private String vascularDisease;
    /**
     * 眼部疾病
     */
    @Column(name = "eye_disease",  nullable = true)
    private String eyeDisease;
    /**
     * 神经系统疾病
     */
    @Column(name = "nervous_system",  nullable = true)
    private String nervousSystem;
    /**
     * 其他疾病
     */
    @Column(name = "other_diabetes",  nullable = true)
    private String otherDiabetes;
    /**
     * 健康评价
     */
    @Column(name = "health_evaluation",  nullable = true)
    private String healthEvaluation;
    /**
     * 健康指导
     */
    @Column(name = "health_guidance",  nullable = true)
    private String healthGuidance;
    /**
     * 危险控制
     */
    @Column(name = "riskcontrol",  nullable = true)
    private String riskcontrol;
    /**
     * 血压左侧
     */
    @Column(name = "pressureleft",  nullable = true)
    private String pressureleft;
    /**
     * 血压右侧
     */
    @Column(name = "pressureright",  nullable = true)
    private String pressureright;
    /**
     * 每次锻炼时间
     */
    @Column(name = "exercise_time",  nullable = true)
    private String exerciseTime;
    /**
     * 坚持运动时间
     */
    @Column(name = "stick_time",  nullable = true)
    private String stickTime;
    /**
     * 身份证号
     */
    @Column(name = "people_id",  nullable = true)
    private String peopleId;
    /**
     * 性别
     */
    @Column(name = "sex",  nullable = true)
    private String sex;
    /**
     * 锻炼方式
     */
    @Column(name = "exercisestyle",  nullable = true)
    private String exercisestyle;
    /**
     * 呼吸音
     */
    @Column(name = "breath_sounds",  nullable = true)
    private String breathSounds;
    /**
     * 日吸烟量
     */
    @Column(name = "every_smoking",  nullable = true)
    private String everySmoking;
    /**
     * 开始吸烟年龄
     */
    @Column(name = "smoking_age",  nullable = true)
    private String smokingAge;
    /**
     * 戒烟年龄
     */
    @Column(name = "no_smoking_age",  nullable = true)
    private String noSmokingAge;
    /**
     * 日饮酒量
     */
    @Column(name = "everydrinking",  nullable = true)
    private String everydrinking;
    /**
     * 饮酒种类
     */
    @Column(name = "drinking_types",  nullable = true)
    private String drinkingTypes;
    /**
     * 是否戒酒
     */
    @Column(name = "nodrinking",  nullable = true)
    private String nodrinking;
    /**
     * 近一年内是否曾醉酒
     */
    @Column(name = "drunk",  nullable = true)
    private String drunk;
    /**
     * 开始饮酒年龄
     */
    @Column(name = "drinking_age",  nullable = true)
    private String drinkingAge;
    /**
     * 工种
     */
    @Column(name = "work_type",  nullable = true)
    private String workType;
    /**
     * 工作时间
     */
    @Column(name = "working_time",  nullable = true)
    private String workingTime;
    /**
     * 粉尘
     */
    @Column(name = "dust",  nullable = true)
    private String dust;
    /**
     * 粉尘防护措施
     */
    @Column(name = "dustpro_measures",  nullable = true)
    private String dustproMeasures;
    /**
     * 放射物质
     */
    @Column(name = "fanshe",  nullable = true)
    private String fanshe;
    /**
     * 放射防护措施
     */
    @Column(name = "fanshepro_measures",  nullable = true)
    private String fansheproMeasures;
    /**
     * 物理因素
     */
    @Column(name = "physicalfactors",  nullable = true)
    private String physicalfactors;
    /**
     * 物理防护措施
     */
    @Column(name = "phypro_measures",  nullable = true)
    private String phyproMeasures;
    /**
     * 化学物质
     */
    @Column(name = "chemicals",  nullable = true)
    private String chemicals;
    /**
     * 化学防护措施
     */
    @Column(name = "chepro_measures",  nullable = true)
    private String cheproMeasures;
    /**
     * 其他
     */
    @Column(name = "other1",  nullable = true)
    private String other1;
    /**
     * 其他防护措施
     */
    @Column(name = "other1_pro_measures",  nullable = true)
    private String other1ProMeasures;
    /**
     * 左视力
     */
    @Column(name = "visionz",  nullable = true)
    private String visionz;
    /**
     * 右视力
     */
    @Column(name = "visiony",  nullable = true)
    private String visiony;
    /**
     * 矫正左视力
     */
    @Column(name = "vicorrectz",  nullable = true)
    private String vicorrectz;
    /**
     * 矫正右视力
     */
    @Column(name = "vicorrecty",  nullable = true)
    private String vicorrecty;
    /**
     * 心率
     */
    @Column(name = "heartrate",  nullable = true)
    private String heartrate;
    /**
     * 桶状胸
     */
    @Column(name = "barrelchest",  nullable = true)
    private String barrelchest;
    /**
     * 杂音
     */
    @Column(name = "noise",  nullable = true)
    private String noise;
    /**
     * 心律
     */
    @Column(name = "hythmheart",  nullable = true)
    private String hythmheart;
    /**
     * 压痛
     */
    @Column(name = "tenderness",  nullable = true)
    private String tenderness;
    /**
     * 包块
     */
    @Column(name = "bagpiece",  nullable = true)
    private String bagpiece;
    /**
     * 肝大
     */
    @Column(name = "hepatomegaly",  nullable = true)
    private String hepatomegaly;
    /**
     * 脾大
     */
    @Column(name = "splenomegaly",  nullable = true)
    private String splenomegaly;
    /**
     * 移动性浊音
     */
    @Column(name = "mobilevoiced",  nullable = true)
    private String mobilevoiced;
    /**
     * 罗音
     */
    @Column(name = "rale",  nullable = true)
    private String rale;
    /**
     * 附件
     */
    @Column(name = "attachment",  nullable = true)
    private String attachment;
    /**
     * 妇科其他
     */
    @Column(name = "other2",  nullable = true)
    private String other2;
    /**
     * 血红蛋白
     */
    @Column(name = "hemoglobin",  nullable = true)
    private String hemoglobin;
    /**
     * 白细胞
     */
    @Column(name = "whitebloodcells",  nullable = true)
    private String whitebloodcells;
    /**
     * 血小板
     */
    @Column(name = "platelet",  nullable = true)
    private String platelet;
    /**
     * 其他
     */
    @Column(name = "other3",  nullable = true)
    private String other3;
    /**
     * 尿蛋白
     */
    @Column(name = "urineprotein",  nullable = true)
    private String urineprotein;
    /**
     * 尿糖
     */
    @Column(name = "urinesugar",  nullable = true)
    private String urinesugar;
    /**
     尿酮体     */
    @Column(name = "urineketone",  nullable = true)
    private String urineketone;
    /**
     * 尿潜血
     */
    @Column(name = "ery",  nullable = true)
    private String ery;
    /**
     * 血清谷丙转氨酶
     */
    @Column(name = "serumtransa",  nullable = true)
    private String serumtransa;
    /**
     * 血清谷草转氨酶
     */
    @Column(name = "glutamictransame",  nullable = true)
    private String glutamictransame;
    /**
     * 总胆红素
     */
    @Column(name = "totalbilirubin",  nullable = true)
    private String totalbilirubin;
    /**
     * 结合胆红素
     */
    @Column(name = "boundbilirubin",  nullable = true)
    private String boundbilirubin;
    /**
     * 血尿素
     */
    @Column(name = "bloodurea",  nullable = true)
    private String bloodurea;
    /**
     * 血钾浓度
     */
    @Column(name = "potconcen",  nullable = true)
    private String potconcen;
    /**
     * 血钠浓度
     */
    @Column(name = "sodiumconcen",  nullable = true)
    private String sodiumconcen;
    /**
     * 总胆固醇
     */
    @Column(name = "totalcholestero",  nullable = true)
    private String totalcholestero;
    /**
     * 血清低密度脂蛋白胆固醇
     */
    @Column(name = "ldlcholesterol",  nullable = true)
    private String ldlcholesterol;
    /**
     * 血清高密度脂蛋白胆固醇
     */
    @Column(name = "hdlcholesterol",  nullable = true)
    private String hdlcholesterol;
    /**
     * 其他
     */
    @Column(name = "other6",  nullable = true)
    private String other6;
    /**
     * 入院时间
     */
    @Column(name = "inh_date",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date inhDate;
    /**
     * 出院时间
     */
    @Column(name = "outh_date",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date outhDate;
    /**
     * 医疗机构名称
     */
    @Column(name = "institut_name",  nullable = true)
    private String institutName;
    /**
     * 住院病案号
     */
    @Column(name = "medicalrecordno",  nullable = true)
    private String medicalrecordno;
    /**
     * 住院原因
     */
    @Column(name = "why",  nullable = true)
    private String why;
    /**
     * 建床日期
     */
    @Column(name = "inb_date",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date inbDate;
    /**
     * 撤床日期
     */
    @Column(name = "outb_date",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date outbDate;
    /**
     * 原因
     */
    @Column(name = "why1",  nullable = true)
    private String why1;
    /**
     * 医疗机构名称
     */
    @Column(name = "institut_name1",  nullable = true)
    private String institutName1;
    /**
     * 病床病案号
     */
    @Column(name = "medicalrecordno1",  nullable = true)
    private String medicalrecordno1;
    /**
     * 接种名称
     */
    @Column(name = "vaccination_name",  nullable = true)
    private String vaccinationName;
    /**
     * 接种日期
     */
    @Column(name = "vaccination_date",  nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date vaccinationDate;
    /**
     * 接种机构
     */
    @Column(name = "vaccination_agency",  nullable = true)
    private String vaccinationAgency;
    /**
     * 药物名称
     */
    @Column(name = "drugname",  nullable = true)
    private String drugname;
    /**
     * 用量
     */
    @Column(name = "dosage",  nullable = true)
    private String dosage;
    /**
     * 用药时间
     */
    @Column(name = "duration",  nullable = true)
    private String duration;
    /**
     * 服药依从性
     */
    @Column(name = "medicompliance",  nullable = true)
    private String medicompliance;
    /**
     * 白蛋白
     */
    @Column(name = "albumin",  nullable = true)
    private String albumin;
    /**
     * 用法
     */
    @Column(name = "useing",  nullable = true)
    private String useing;
    /**
     * 删除状态
     */
    @Column(name = "delete_state",  nullable = true)
    private String deleteState;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getCheckDate() {
        return checkDate;
    }
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
    public String getDoctor() {
        return doctor;
    }
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
    public String getSymptoms() {
        return symptoms;
    }
    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
    public String getTemperature() {
        return temperature;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public String getPulseRate() {
        return pulseRate;
    }
    public void setPulseRate(String pulseRate) {
        this.pulseRate = pulseRate;
    }
    public String getBreathing() {
        return breathing;
    }
    public void setBreathing(String breathing) {
        this.breathing = breathing;
    }
    public String getStature() {
        return stature;
    }
    public void setStature(String stature) {
        this.stature = stature;
    }
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getWaistline() {
        return waistline;
    }
    public void setWaistline(String waistline) {
        this.waistline = waistline;
    }
    public String getBodymassindex() {
        return bodymassindex;
    }
    public void setBodymassindex(String bodymassindex) {
        this.bodymassindex = bodymassindex;
    }
    public String getSelfAssessment() {
        return selfAssessment;
    }
    public void setSelfAssessment(String selfAssessment) {
        this.selfAssessment = selfAssessment;
    }
    public String getSelfCare() {
        return selfCare;
    }
    public void setSelfCare(String selfCare) {
        this.selfCare = selfCare;
    }
    public String getCognition() {
        return cognition;
    }
    public void setCognition(String cognition) {
        this.cognition = cognition;
    }
    public String getMood() {
        return mood;
    }
    public void setMood(String mood) {
        this.mood = mood;
    }
    public String getExercise() {
          return exercise;
      }
    public void setExercise(String exercise) {
          this.exercise = exercise;
      }
    public String getDiet() {
        return diet;
    }
    public void setDiet(String diet) {
        this.diet = diet;
    }
    public String getSmoking() {
        return smoking;
    }
    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }
    public String getDrinking() {
        return drinking;
    }
    public void setDrinking(String drinking) {
        this.drinking = drinking;
    }
    public String getWorkDiseases() {
        return workDiseases;
    }
    public void setWorkDiseases(String workDiseases) {
        this.workDiseases = workDiseases;
    }
    public String getOralCavity() {
        return oralCavity;
    }
    public void setOralCavity(String oralCavity) {
        this.oralCavity = oralCavity;
    }
    public String getListen() {
        return listen;
    }
    public void setListen(String listen) {
        this.listen = listen;
    }
    public String getSport() {
        return sport;
    }
    public void setSport(String sport) {
        this.sport = sport;
    }
    public String getBottomEye() {
        return bottomEye;
    }
    public void setBottomEye(String bottomEye) {
        this.bottomEye = bottomEye;
    }
    public String getSkin() {
        return skin;
    }
    public void setSkin(String skin) {
        this.skin = skin;
    }
    public String getSclera() {
        return sclera;
    }
    public void setSclera(String sclera) {
        this.sclera = sclera;
    }
    public String getLymphNode() {
        return lymphNode;
    }
    public void setLymphNode(String lymphNode) {
        this.lymphNode = lymphNode;
    }
    public String getLimbEdema() {
        return limbEdema;
    }
    public void setLimbEdema(String limbEdema) {
        this.limbEdema = limbEdema;
    }
    public String getFootArteyPulse() {
        return footArteyPulse;
    }
    public void setFootArteyPulse(String footArteyPulse) {
        this.footArteyPulse = footArteyPulse;
    }
    public String getAnalDiagnosis() {
        return analDiagnosis;
    }
    public void setAnalDiagnosis(String analDiagnosis) {
        this.analDiagnosis = analDiagnosis;
    }
    public String getMammaryGland() {
        return mammaryGland;
    }
    public void setMammaryGland(String mammaryGland) {
        this.mammaryGland = mammaryGland;
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
    public String getPalace() {
        return palace;
    }
    public void setPalace(String palace) {
        this.palace = palace;
    }
    public String getFastingBloodSugar() {
        return fastingBloodSugar;
    }
    public void setFastingBloodSugar(String fastingBloodSugar) {
        this.fastingBloodSugar = fastingBloodSugar;
    }
    public String getElectrocardiogram() {
        return electrocardiogram;
    }
    public void setElectrocardiogram(String electrocardiogram) {
        this.electrocardiogram = electrocardiogram;
    }
    public String getUrineMicroalbumin() {
        return urineMicroalbumin;
    }
    public void setUrineMicroalbumin(String urineMicroalbumin) {
        this.urineMicroalbumin = urineMicroalbumin;
    }
    public String getShitblood() {
        return shitblood;
    }
    public void setShitblood(String shitblood) {
        this.shitblood = shitblood;
    }
    public String getGlycatedGlycemic() {
        return glycatedGlycemic;
    }
    public void setGlycatedGlycemic(String glycatedGlycemic) {
        this.glycatedGlycemic = glycatedGlycemic;
    }
    public String getHepatitisb() {
        return hepatitisb;
    }
    public void setHepatitisb(String hepatitisb) {
        this.hepatitisb = hepatitisb;
    }
    public String getChestxray() {
        return chestxray;
    }
    public void setChestxray(String chestxray) {
        this.chestxray = chestxray;
    }
    public String getBsuper() {
        return bsuper;
    }
    public void setBsuper(String bsuper) {
        this.bsuper = bsuper;
    }
    public String getCervicalSmear() {
        return cervicalSmear;
    }
    public void setCervicalSmear(String cervicalSmear) {
        this.cervicalSmear = cervicalSmear;
    }
    public String getCerebrovascular() {
        return cerebrovascular;
    }
    public void setCerebrovascular(String cerebrovascular) {
        this.cerebrovascular = cerebrovascular;
    }
    public String getKidneyDisease() {
        return kidneyDisease;
    }
    public void setKidneyDisease(String kidneyDisease) {
        this.kidneyDisease = kidneyDisease;
    }
    public String getHeartDisease() {
        return heartDisease;
    }
    public void setHeartDisease(String heartDisease) {
        this.heartDisease = heartDisease;
    }
    public String getVascularDisease() {
        return vascularDisease;
    }
    public void setVascularDisease(String vascularDisease) {
        this.vascularDisease = vascularDisease;
    }
    public String getEyeDisease() {
        return eyeDisease;
    }
    public void setEyeDisease(String eyeDisease) {
        this.eyeDisease = eyeDisease;
    }
    public String getNervousSystem() {
        return nervousSystem;
    }
    public void setNervousSystem(String nervousSystem) {
        this.nervousSystem = nervousSystem;
    }
    public String getOtherDiabetes() {
        return otherDiabetes;
    }
    public void setOtherDiabetes(String otherDiabetes) {
        this.otherDiabetes = otherDiabetes;
    }
    public String getHealthEvaluation() {
        return healthEvaluation;
    }
    public void setHealthEvaluation(String healthEvaluation) {
        this.healthEvaluation = healthEvaluation;
    }
    public String getHealthGuidance() {
        return healthGuidance;
    }
    public void setHealthGuidance(String healthGuidance) {
        this.healthGuidance = healthGuidance;
    }
    public String getRiskcontrol() {
        return riskcontrol;
    }
    public void setRiskcontrol(String riskcontrol) {
        this.riskcontrol = riskcontrol;
    }
    public String getPressureleft() {
        return pressureleft;
    }
    public void setPressureleft(String pressureleft) {
        this.pressureleft = pressureleft;
    }
    public String getPressureright() {
        return pressureright;
    }
    public void setPressureright(String pressureright) {
        this.pressureright = pressureright;
    }
    public String getExerciseTime() {
        return exerciseTime;
    }
    public void setExerciseTime(String exerciseTime) {
        this.exerciseTime = exerciseTime;
    }
    public String getStickTime() {
        return stickTime;
    }
    public void setStickTime(String stickTime) {
        this.stickTime = stickTime;
    }
    public String getPeopleId() {
        return peopleId;
    }
    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getExercisestyle() {
        return exercisestyle;
    }
    public void setExercisestyle(String exercisestyle) {
        this.exercisestyle = exercisestyle;
    }
    public String getBreathSounds() {
        return breathSounds;
    }
    public void setBreathSounds(String breathSounds) {
        this.breathSounds = breathSounds;
    }
    public String getEverySmoking() {
        return everySmoking;
    }
    public void setEverySmoking(String everySmoking) {
        this.everySmoking = everySmoking;
    }
    public String getSmokingAge() {
        return smokingAge;
    }
    public void setSmokingAge(String smokingAge) {
        this.smokingAge = smokingAge;
    }
    public String getNoSmokingAge() {
        return noSmokingAge;
    }
    public void setNoSmokingAge(String noSmokingAge) {
        this.noSmokingAge = noSmokingAge;
    }
    public String getEverydrinking() {
        return everydrinking;
    }
    public void setEverydrinking(String everydrinking) {
        this.everydrinking = everydrinking;
    }
    public String getDrinkingTypes() {
        return drinkingTypes;
    }
    public void setDrinkingTypes(String drinkingTypes) {
        this.drinkingTypes = drinkingTypes;
    }
    public String getNodrinking() {
        return nodrinking;
    }
    public void setNodrinking(String nodrinking) {
        this.nodrinking = nodrinking;
    }
    public String getDrunk() {
        return drunk;
    }
    public void setDrunk(String drunk) {
        this.drunk = drunk;
    }
    public String getDrinkingAge() {
        return drinkingAge;
    }
    public void setDrinkingAge(String drinkingAge) {
        this.drinkingAge = drinkingAge;
    }
    public String getWorkType() {
        return workType;
    }
    public void setWorkType(String workType) {
        this.workType = workType;
    }
    public String getWorkingTime() {
        return workingTime;
    }
    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }
    public String getDust() {
        return dust;
    }
    public void setDust(String dust) {
        this.dust = dust;
    }
    public String getDustproMeasures() {
        return dustproMeasures;
    }
    public void setDustproMeasures(String dustproMeasures) {
        this.dustproMeasures = dustproMeasures;
    }
    public String getFanshe() {
        return fanshe;
    }
    public void setFanshe(String fanshe) {
        this.fanshe = fanshe;
    }
    public String getFansheproMeasures() {
        return fansheproMeasures;
    }
    public void setFansheproMeasures(String fansheproMeasures) {
        this.fansheproMeasures = fansheproMeasures;
    }
    public String getPhysicalfactors() {
        return physicalfactors;
    }
    public void setPhysicalfactors(String physicalfactors) {
        this.physicalfactors = physicalfactors;
    }
    public String getPhyproMeasures() {
        return phyproMeasures;
    }
    public void setPhyproMeasures(String phyproMeasures) {
        this.phyproMeasures = phyproMeasures;
    }
    public String getChemicals() {
        return chemicals;
    }
    public void setChemicals(String chemicals) {
        this.chemicals = chemicals;
    }
    public String getCheproMeasures() {
        return cheproMeasures;
    }
    public void setCheproMeasures(String cheproMeasures) {
        this.cheproMeasures = cheproMeasures;
    }
    public String getOther1() {
        return other1;
    }
    public void setOther1(String other1) {
        this.other1 = other1;
    }
    public String getOther1ProMeasures() {
        return other1ProMeasures;
    }
    public void setOther1ProMeasures(String other1ProMeasures) {
        this.other1ProMeasures = other1ProMeasures;
    }
    public String getVisionz() {
        return visionz;
    }
    public void setVisionz(String visionz) {
        this.visionz = visionz;
    }
    public String getVisiony() {
        return visiony;
    }
    public void setVisiony(String visiony) {
        this.visiony = visiony;
    }
    public String getVicorrectz() {
        return vicorrectz;
    }
    public void setVicorrectz(String vicorrectz) {
        this.vicorrectz = vicorrectz;
    }
    public String getVicorrecty() {
        return vicorrecty;
    }
    public void setVicorrecty(String vicorrecty) {
        this.vicorrecty = vicorrecty;
    }
    public String getHeartrate() {
        return heartrate;
    }
    public void setHeartrate(String heartrate) {
        this.heartrate = heartrate;
    }
    public String getBarrelchest() {
        return barrelchest;
    }
    public void setBarrelchest(String barrelchest) {
        this.barrelchest = barrelchest;
    }
    public String getNoise() {
        return noise;
    }
    public void setNoise(String noise) {
        this.noise = noise;
    }
    public String getHythmheart() {
        return hythmheart;
    }
    public void setHythmheart(String hythmheart) {
        this.hythmheart = hythmheart;
    }
    public String getTenderness() {
        return tenderness;
    }
    public void setTenderness(String tenderness) {
        this.tenderness = tenderness;
    }
    public String getBagpiece() {
        return bagpiece;
    }
    public void setBagpiece(String bagpiece) {
        this.bagpiece = bagpiece;
    }
    public String getHepatomegaly() {
        return hepatomegaly;
    }
    public void setHepatomegaly(String hepatomegaly) {
        this.hepatomegaly = hepatomegaly;
    }
    public String getSplenomegaly() {
        return splenomegaly;
    }
    public void setSplenomegaly(String splenomegaly) {
        this.splenomegaly = splenomegaly;
    }
    public String getMobilevoiced() {
        return mobilevoiced;
    }
    public void setMobilevoiced(String mobilevoiced) {
        this.mobilevoiced = mobilevoiced;
    }
    public String getRale() {
        return rale;
    }
    public void setRale(String rale) {
        this.rale = rale;
    }
    public String getAttachment() {
        return attachment;
    }
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    public String getOther2() {
        return other2;
    }
    public void setOther2(String other2) {
        this.other2 = other2;
    }
    public String getWhitebloodcells() {
        return whitebloodcells;
    }
    public void setWhitebloodcells(String whitebloodcells) {
        this.whitebloodcells = whitebloodcells;
    }
    public String getHemoglobin() {
        return hemoglobin;
    }
    public void setHemoglobin(String hemoglobin) {
        this.hemoglobin = hemoglobin;
    }
    public String getPlatelet() {
        return platelet;
    }
    public void setPlatelet(String platelet) {
        this.platelet = platelet;
    }
    public String getOther3() {
        return other3;
    }
    public void setOther3(String other3) {
        this.other3 = other3;
    }
    public String getUrineprotein() {
        return urineprotein;
    }
    public void setUrineprotein(String urineprotein) {
        this.urineprotein = urineprotein;
    }
    public String getUrinesugar() {
        return urinesugar;
    }
    public void setUrinesugar(String urinesugar) {
        this.urinesugar = urinesugar;
    }
    public String getUrineketone() {
        return urineketone;
    }
    public void setUrineketone(String urineketone) {
        this.urineketone = urineketone;
    }
    public String getEry() {
        return ery;
    }
    public void setEry(String ery) {
        this.ery = ery;
    }
    public String getSerumtransa() {
        return serumtransa;
    }
    public void setSerumtransa(String serumtransa) {
        this.serumtransa = serumtransa;
    }
    public String getGlutamictransame() {
        return glutamictransame;
    }
    public void setGlutamictransame(String glutamictransame) {
        this.glutamictransame = glutamictransame;
    }
    public String getTotalbilirubin() {
        return totalbilirubin;
    }
    public void setTotalbilirubin(String totalbilirubin) {
        this.totalbilirubin = totalbilirubin;
    }
    public String getBoundbilirubin() {
        return boundbilirubin;
    }
    public void setBoundbilirubin(String boundbilirubin) {
        this.boundbilirubin = boundbilirubin;
    }
    public String getBloodurea() {
        return bloodurea;
    }
    public void setBloodurea(String bloodurea) {
        this.bloodurea = bloodurea;
    }
    public String getPotconcen() {
        return potconcen;
    }
    public void setPotconcen(String potconcen) {
        this.potconcen = potconcen;
    }
    public String getSodiumconcen() {
        return sodiumconcen;
    }
    public void setSodiumconcen(String sodiumconcen) {
        this.sodiumconcen = sodiumconcen;
    }
    public String getTotalcholestero() {
        return totalcholestero;
    }
    public void setTotalcholestero(String totalcholestero) {
        this.totalcholestero = totalcholestero;
    }
    public String getLdlcholesterol() {
        return ldlcholesterol;
    }
    public void setLdlcholesterol(String ldlcholesterol) {
        this.ldlcholesterol = ldlcholesterol;
    }
    public String getHdlcholesterol() {
        return hdlcholesterol;
    }
    public void setHdlcholesterol(String hdlcholesterol) {
        this.hdlcholesterol = hdlcholesterol;
    }
    public String getOther6() {
        return other6;
    }
    public void setOther6(String other6) {
        this.other6 = other6;
    }
    public Date getInhDate() {
        return inhDate;
    }
    public void setInhDate(Date inhDate) {
        this.inhDate = inhDate;
    }
    public Date getOuthDate() {
        return outhDate;
    }
    public void setOuthDate(Date outhDate) {
        this.outhDate = outhDate;
    }
    public String getInstitutName() {
        return institutName;
    }
    public void setInstitutName(String institutName) {
        this.institutName = institutName;
    }
    public String getMedicalrecordno() {
        return medicalrecordno;
    }
    public void setMedicalrecordno(String medicalrecordno) {
        this.medicalrecordno = medicalrecordno;
    }
    public String getWhy() {
        return why;
    }
    public void setWhy(String why) {
        this.why = why;
    }
    public Date getInbDate() {
     return inbDate;
    }
    public void setInbDate(Date inbDate) {
        this.inbDate = inbDate;
    }
    public Date getOutbDate() {
        return outbDate;
    }
    public void setOutbDate(Date outbDate) {
        this.outbDate = outbDate;
    }
    public String getWhy1() {
        return why1;
    }
    public void setWhy1(String why1) {
        this.why1 = why1;
    }
    public String getInstitutName1() {
        return institutName1;
    }
    public void setInstitutName1(String institutName1) {
        this.institutName1 = institutName1;
    }
    public String getMedicalrecordno1() {
        return medicalrecordno1;
    }
    public void setMedicalrecordno1(String medicalrecordno1) {
        this.medicalrecordno1 = medicalrecordno1;
    }
    public String getVaccinationName() {
        return vaccinationName;
    }
    public void setVaccinationName(String vaccinationName) {
        this.vaccinationName = vaccinationName;
    }
    public Date getVaccinationDate() {
        return vaccinationDate;
    }
    public void setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }
    public String getVaccinationAgency() {
        return vaccinationAgency;
    }
    public void setVaccinationAgency(String vaccinationAgency) {
        this.vaccinationAgency = vaccinationAgency;
    }
    public String getDrugname() {
         return drugname;
    }
    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }
    public String getDosage() {
        return dosage;
    }
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getMedicompliance() {
        return medicompliance;
    }
    public void setMedicompliance(String medicompliance) {
        this.medicompliance = medicompliance;
    }
    public String getAlbumin() {
        return albumin;
    }
    public void setAlbumin(String albumin) {
        this.albumin = albumin;
    }
    public String getUseing() {
        return useing;
    }
    public void setUseing(String useing) {
        this.useing = useing;
    }
    public String getDeleteState() {
        return deleteState;
    }
    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }
}


