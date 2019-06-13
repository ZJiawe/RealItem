package com.yihu.ehr.health.Perinfor.service;

import com.google.common.collect.Lists;
import com.yihu.ehr.health.ChildrenStatistic.service.ChildrenStatisticesService;
import com.yihu.ehr.health.ElderlyStatistical.controller.ElderlyStatisticalEndPoint;
import com.yihu.ehr.health.FirstPrenatalDiagnosis.controller.FirstPrenatalDiagnosisEndPoint;
import com.yihu.ehr.health.FirsthomevisitRecord.controller.FirsthomevisitRecordController;
import com.yihu.ehr.health.Followupservicerecord.controller.FollowUpServiceController;
import com.yihu.ehr.health.HealthCheckups.controller.HealthCheckupsEndPoint;
import com.yihu.ehr.health.HealthManagementRecord.controller.HealthManagementRecordController;
import com.yihu.ehr.health.MaternalStatistical.controller.MaternalStatisticalEndPoint;
import com.yihu.ehr.health.Perinfor.dao.XPerinforRepository;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.PostpartumVisit.controller.PostpartumVisitEndPoint;
import com.yihu.ehr.health.PostpartumVisitFortytwo.controller.PostpartumVisitFortytwoEndPoint;
import com.yihu.ehr.health.ResidentsStatistical.controller.ResidentsStatisticalEndPoint;
import com.yihu.ehr.health.ScheduleTwoFivePrenatalDiagnosis.controller.ScheduleTwoFivePrenatalDiagnosisEndPoint;
import com.yihu.ehr.health.SelfcareAbility.controller.SelfcareAbilityController;
import com.yihu.ehr.health.SevereHyperFollow.controller.SevereHyperFollowController;
import com.yihu.ehr.health.SevereHyperSumFollow.controller.SevereHyperSumFollowController;
import com.yihu.ehr.health.SevereMentalFollow.controller.SevereMentalFollowController;
import com.yihu.ehr.health.SevereMentalSupplement.controller.SevereMentalSupplementController;
import com.yihu.ehr.health.Totaldiabetes.controller.TotaldiabetesController;
import com.yihu.ehr.health.TuberculosisHealth.controller.TuberculosisHealthController;
import com.yihu.ehr.health.TwoDiabetes.controller.TwoDiabetesController;
import com.yihu.ehr.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class PerinforService extends BaseJpaService<Perinfor, XPerinforRepository> {
    @Autowired
    XPerinforRepository perinforRepository;
    @Autowired
    ResidentsStatisticalEndPoint residentsStatisticalEndPoint;
    @Autowired
    ChildrenStatisticesService childrenStatisticesService;
    @Autowired
    TuberculosisHealthController tuberculosisHealthController;
    @Autowired
    SevereHyperSumFollowController severeHyperSumFollowController;
    @Autowired
    TotaldiabetesController totaldiabetesController;
    @Autowired
    MaternalStatisticalEndPoint maternalStatisticalEndPoint;
    @Autowired
    ElderlyStatisticalEndPoint elderlyStatisticalEndPoint;
    @Autowired
    SevereMentalSupplementController severeMentalSupplementController;
    @Autowired
    SevereMentalFollowController severeMentalFollowController;
	@Autowired
    FirstPrenatalDiagnosisEndPoint firstPrenatalDiagnosisEndPoint;
    @Autowired
    ScheduleTwoFivePrenatalDiagnosisEndPoint scheduleTwoFivePrenatalDiagnosisEndPoint;
    @Autowired
    PostpartumVisitEndPoint postpartumVisitEndPoint;
    @Autowired
    PostpartumVisitFortytwoEndPoint postpartumVisitFortytwoEndPoint;
    @Autowired
    SevereHyperFollowController severeHyperFollowController;
    @Autowired
    FirsthomevisitRecordController firsthomevisitRecordController;
    @Autowired
    FollowUpServiceController followUpServiceController;
    @Autowired
    SelfcareAbilityController selfcareAbilityController;
    @Autowired
    HealthManagementRecordController healthManagementRecordController;
    @Autowired
    TwoDiabetesController twoDiabetesController;
    @Autowired
    HealthCheckupsEndPoint healthCheckupsEndPoint;

    public List<Perinfor> getselect() {
        Iterator<Perinfor> myIterator = perinforRepository.findAll().iterator();
        List<Perinfor> basicfamilyinfor =  Lists.newArrayList(myIterator);;
        return basicfamilyinfor;
    }

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public Perinfor getPerinfor(String Id) {
        Perinfor entity = perinforRepository.findOne(Id);
        return entity;
    }

    /**
     * 保存个人档案信息
     * @param entity 个人档案对象
     * @return
     */
    public Perinfor saveUser(Perinfor beforeEntity , Perinfor entity) {
        entity.setDeleteState("未删");
        entity = perinforRepository.save(entity);//不能放上面
        return entity;

    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        Perinfor entity = perinforRepository.findOne(Id);

        //添加服务
        try{
            childrenStatisticesService.deleteChildrenVisit(Id);
            severeMentalSupplementController.totalDelate(entity);
            severeMentalFollowController.totalDelate(entity);
            severeHyperFollowController.totalDelate(entity);
            firstPrenatalDiagnosisEndPoint.totalDelate(entity);
			scheduleTwoFivePrenatalDiagnosisEndPoint.totalDelate(entity);
			postpartumVisitEndPoint.totalDelate(entity);
			postpartumVisitFortytwoEndPoint.totalDelate(entity);
            firsthomevisitRecordController.totalDelate(entity);
            followUpServiceController.totalDelate(entity);
            selfcareAbilityController.totalDelate(entity);
            healthManagementRecordController.totalDelate(entity);
            twoDiabetesController.totalDelate(entity);
            healthCheckupsEndPoint.totalDelate(entity);
        }
        finally{
            entity.setDeleteState("已删");
            perinforRepository.save(entity);
        }

    }


}
