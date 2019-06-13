package com.yihu.ehr.health.Transtreatset.entity;
import org.hibernate.annotations.GenericGenerator;
import com.yihu.ehr.entity.BaseAssignedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "transtreatset", schema = "doc")
public class Transtreatset extends BaseAssignedEntity {

  @Column(name = "message_set",  nullable = true)
  private String messageSet;

  @Column(name = "delete_state",  nullable = true)
  private String deleteState;

  @Column(name = "charge_people",  nullable = true)
  private String chargePeople;
  @Column(name = "contact",  nullable = true)
  private String contact;
  @Column(name = "referral_information",  nullable = true)
  private String referralInformation;
  @Column(name = "trans_apartment_num",  nullable = true)
  private String transApartmentNum;
  @Column(name = "trans_inspect_num",  nullable = true)
  private String transInspectNum;
  @Column(name = "trans_hospitalization_num",  nullable = true)
  private String transHospitalizationNum;
  @Column(name = "trans_recovery",  nullable = true)
  private String transRecovery;


  public String getMessageSet() {
    return messageSet;
  }

  public void setMessageSet(String messageSet) {
    this.messageSet = messageSet;
  }


  public String getDeleteState() {
    return deleteState;
  }

  public void setDeleteState(String deleteState) {
    this.deleteState = deleteState;
  }

  public String getChargePeople() {
    return chargePeople;
  }

  public void setChargePeople(String chargePeople) {
    this.chargePeople = chargePeople;
  }


  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }


  public String getReferralInformation() {
    return referralInformation;
  }

  public void setReferralInformation(String referralInformation) {
    this.referralInformation = referralInformation;
  }


    public String getTransApartmentNum() {
        return transApartmentNum;
    }

    public void setTransApartmentNum(String transApartmentNum) {
        this.transApartmentNum = transApartmentNum;
    }


    public String getTransInspectNum() {
        return transInspectNum;
    }

    public void setTransInspectNum(String transInspectNum) {
        this.transInspectNum = transInspectNum;
    }


    public String getTransHospitalizationNum() {
        return transHospitalizationNum;
    }

    public void setTransHospitalizationNum(String transHospitalizationNum) {
        this.transHospitalizationNum = transHospitalizationNum;
    }


    public String getTransRecovery() {
        return transRecovery;
    }

    public void setTransRecovery(String transRecovery) {
        this.transRecovery = transRecovery;
    }

}
