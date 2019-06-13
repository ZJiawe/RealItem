package com.yihu.ehr.health.user.entity;

import com.yihu.ehr.entity.BaseAssignedEntity;

import javax.persistence.*;

/**
 * @author zdm
 * @version 1.0
 * @updated 2018-09-04
 */
@Entity
@Table(name = "users")
@Access(value = AccessType.PROPERTY)
public class Users extends BaseAssignedEntity {
    /**  账号*/
    private String loginCode;
    /**  姓名*/
    private String realName;
    /**  生失效*/
    private Boolean activated;
    /**  联系方式*/
    private String telephone;
    /**  性别*/
    private String gender;


    public Users() {
    }


    @Column(name = "login_code",  nullable = false)
    public String getLoginCode() {
        return loginCode;
    }
    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    @Column(name = "real_name",  nullable = false)
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Column(name = "activated",  nullable = true)
    public Boolean getActivated() {
        return activated;
    }
    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    @Column(name = "telephone",  nullable = true)
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(name = "gender",  nullable = true)
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}