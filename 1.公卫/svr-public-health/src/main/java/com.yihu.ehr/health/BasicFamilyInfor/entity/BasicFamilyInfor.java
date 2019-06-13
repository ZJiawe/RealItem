package com.yihu.ehr.health.BasicFamilyInfor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.ehr.entity.BaseAssignedEntity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "basic_family_infor")
@Access(value = AccessType.PROPERTY)
public class BasicFamilyInfor extends BaseAssignedEntity {
    /**
     * 户主名称
     */
    @Column(name = "heade_name",  nullable = true)
    private String headeName;
    /**
     * 责任医生
     */
    @Column(name = "response_doctor",  nullable = true)
    private String responseDoctor;
    /**
     * 归属社区
     */
    @Column(name = "belong_community",  nullable = true)
    private String belongCommunity;
    /**
     * 住址
     */
    @Column(name = "local_place",  nullable = true)
    private String localPlace;
    /**
     * 邮政编码
     */
    @Column(name = "zip_code",  nullable = true)
    private String zipCode;
    /**
     * 家庭人口数
     */
    @Column(name = "family_num",  nullable = true)
    private String familyNum;
    /**
     * 现居住人数
     */
    @Column(name = "live_people",  nullable = true)
    private String livePeople;
    /**
     * 居住面积
     */
    @Column(name = "area",  nullable = true)
    private String area;
    /**
     * 家庭类型
     */
    @Column(name = "family_type",  nullable = true)
    private String familyType;
    /**
     * 饮水
     */
    @Column(name = "drink_water",  nullable = true)
    private String drinkWater;
    /**
     * 厨房排风
     */
    @Column(name = "kitchen_exhaust",  nullable = true)
    private String kitchenExhaust;
    /**
     * 燃料类型
     */
    @Column(name = "fuel_type",  nullable = true)
    private String fuelType;
    /**
     * 厕所
     */
    @Column(name = "toilet",  nullable = true)
    private String toilet;
    /**
     * 建档时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @Column(name = "fb_time",  nullable = true)
    private Date fbTime;
    /**
     * 建档人
     */
    @Column(name = "fb_people",  nullable = true)
    private String fbPeople;
    /**
     * 建档机构
     */
    @Column(name = "fb_organization",  nullable = true)
    private String fbOrganization;
    /**
     * 删除状态
     */
    @Column(name = "delete_state",  nullable = true)
    private String deleteState;
    /**
     * 居委会
     */
    @Column(name = "neighborhood_committee",  nullable = true)
    private String neighborhoodCommittee;

    public String getHeadeName() {
        return headeName;
    }

    public void setHeadeName(String headeName) {
        this.headeName = headeName;
    }

    public String getResponseDoctor() {
        return responseDoctor;
    }
    public void setResponseDoctor(String responseDoctor) {
        this.responseDoctor = responseDoctor;
    }


    public String getBelongCommunity() {
        return belongCommunity;
    }
    public void setBelongCommunity(String belongCommunity) {
        this.belongCommunity = belongCommunity;
    }


    public String getLocalPlace() {
        return localPlace;
    }
    public void setLocalPlace(String localPlace) {
        this.localPlace = localPlace;
    }


    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


    public String getFamilyNum() {
        return familyNum;
    }
    public void setFamilyNum(String familyNum) {
        this.familyNum = familyNum;
    }


    public String getLivePeople() {
        return livePeople;
    }
    public void setLivePeople(String livePeople) {
        this.livePeople = livePeople;
    }


    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }


    public String getFamilyType() {
        return familyType;
    }
    public void setFamilyType(String familyType) {
        this.familyType = familyType;
    }


    public String getDrinkWater() {
        return drinkWater;
    }
    public void setDrinkWater(String drinkWater) {
        this.drinkWater = drinkWater;
    }


    public String getKitchenExhaust() {
        return kitchenExhaust;
    }
    public void setKitchenExhaust(String kitchenExhaust) {
        this.kitchenExhaust = kitchenExhaust;
    }


    public String getFuelType() {
        return fuelType;
    }
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }


    public String getToilet() {
        return toilet;
    }
    public void setToilet(String toilet) {
        this.toilet = toilet;
    }


    public Date getFbTime() {
        return fbTime;
    }
    public void setFbTime(Date fbTime) {
        this.fbTime = fbTime;
    }


    public String getFbPeople() {
        return fbPeople;
    }
    public void setFbPeople(String fbPeople) {
        this.fbPeople = fbPeople;
    }


    public String getFbOrganization() {
        return fbOrganization;
    }
    public void setFbOrganization(String fbOrganization) {
        this.fbOrganization = fbOrganization;
    }


    public String getDeleteState() {
        return deleteState;
    }
    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }


    public String getNeighborhoodCommittee() {
        return neighborhoodCommittee;
    }
    public void setNeighborhoodCommittee(String neighborhoodCommittee) {
        this.neighborhoodCommittee = neighborhoodCommittee;
    }

}
