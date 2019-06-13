package com.yihu.ehr.health.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * 微服务REST API. 此处定义的URL可用于服务对外提供的地址及HTTP客户端请求地址.
 * URL定义遵循健康档案平台REST规范.
 *
 * @author Sand
 * @version 1.0
 * @created 2015.09.09 15:04
 */
public class ServiceApi {

    @Value("${service-gateway.CrossOrigin}")
    private static String crossOrigin;

    public static class Config {
       public static final String CrossOrigin = "http://localhost:9528";
    }

    public static class FirstPrenatalDiagnosis {
        public static final String Check = "/firstPrenatalDiagnosis/check";
        public static final String Save = "/firstPrenatalDiagnosis/save";
        public static final String FirstPrenatalDiagnosis = "/firstPrenatalDiagnosis/list";
        public static final String FirstPrenatalDiagnosisEntiry = "/firstPrenatalDiagnosis/entiry/{userId}";
        public static final String print = "/firstPrenatalDiagnosis/print";
    }
    public static class PostpartumVisit {
        public static final String Save = "/postpartumVisit/save";
        public static final String PostpartumVisit = "/postpartumVisit/list";
        public static final String PostpartumVisitEntiry = "/postpartumVisit/entiry/{userId}";
        public static final String print = "/postpartumVisit/print";
    }

    public static class PostpartumVisitFortytwo {
        public static final String Save = "/postpartumVisitFortytwo/save";
        public static final String PostpartumVisitFortytwo = "/postpartumVisitFortytwo/list";
        public static final String PostpartumVisitFortytwoEntiry = "/postpartumVisitFortytwo/entiry/{userId}";
        public static final String print = "/postpartumVisitFortytwo/print";
    }

    public static class ScheduleTwoFivePrenatalDiagnosis {
        public static final String Save = "/scheduleTwoFivePrenatalDiagnosis/save";
        public static final String ScheduleTwoFivePrenatalDiagnosis = "/scheduleTwoFivePrenatalDiagnosis/list";
        public static final String ScheduleTwoFivePrenatalDiagnosisEntiry = "/scheduleTwoFivePrenatalDiagnosis/entiry/{userId}";
        public static final String print = "/scheduleTwoFivePrenatalDiagnosis/print";
    }
    public static class Users {
        public static final String Save = "/users/save";
        public static final String Users = "/users";
        public static final String UserAdmin = "/users/admin/{userId}";
    }
    public static class Authentication {
        public static final String Save = "/oauth/save";
        public static final String Users = "/oauth";
        public static final String UserAdmin = "/oauth/admin/{userId}";
    }
    public static class MentalQuery {
        public static final String Save = "/mentalQuery/save";
        public static final String MentalQuery = "/mentalQuery/list";
        public static final String MentalQueryEntiry = "/mentalQuery/entiry/{userId}";
    }
    public static class BasicFamilyInfor {
        public static final String Save = "/basicfamilyinfor/save";
        public static final String BasicFamilyInfor = "/basicfamilyinfor/list";
        public static final String BasicFamilyInforEntiry = "/basicfamilyinfor/entiry/{userId}";
        public static final String BasicFamilyInfortest = "/basicfamilyinfortest/list";

    }
    public static class BasicFamilyInformember {
        public static final String Save = "/basicfamilyinformember/save";
        public static final String BasicFamilyInformember = "/basicfamilyinformember/list";
        public static final String BasicFamilyInformemberEntiry = "/basicfamilyinformember/entiry/{userId}";
        public static final String BasicFamilyInformemberManage = "/basicfamilyinformember/managermember";
        public static final String BasicFamilyInformemberSplit = "/basicfamilyinformember/split";
    }
    public static class SearchRdoctor {
        public static final String Save = "/searchrdoctor/save";
        public static final String SearchRdoctor = "/searchrdoctor/list";
        public static final String SearchRdoctorEntiry = "/searchrdoctor/entiry/{userId}";
    }
    public static class Login {
        public static final String login = "/login";
        public static final String logininfo = "/login/info";
        public static final String loginlogout = "/login/logout";
        public static final String menuinfo = "/login/menu";
    }

    public static class HealthSupervision {
        public static final String Save = "/healthsupervision/save";
        public static final String HealthSupervision = "/healthsupervision/list";
        public static final String HealthSupervisionEntiry = "/healthsupervision/entiry/{healthSupervisionId}";
    }
    public static class MentalStatistical {
        public static final String Save = "/mentalStatistical/save";
        public static final String MentalStatistical = "/mentalStatistical/list";
        public static final String MentalStatisticalEntiry = "/mentalStatistical/entiry/{userId}";
    }


    public static class SelfcareAbility {
        public static final String Check = "/selfcareAbility/check";
        public static final String Save = "/selfcareAbility/save";
        public static final String SelfcareAbility = "/selfcareAbility/list";
        public static final String SelfcareAbilityEntiry = "/selfcareAbility/entiry/{userId}";
    }


    public static class HealthManagementRecord {
        public static final String Save = "/HealthManagementRecord/save";
        public static final String HealthManagementRecord = "/HealthManagementRecord/list";
        public static final String HealthManagementRecordEntiry = "/HealthManagementRecord/entiry/{userId}";
    }

    public static class Emergencies {
        public static final String Save = "/emergencies/save";
        public static final String Emergencies = "/emergencies/list";
        public static final String EmergenciesEntiry = "/emergencies/entiry/{emergenciesId}";
    }

    public static class HealthCheckups {
        public static final String Save = "/HealthCheckups/save";
        public static final String HealthCheckups = "/HealthCheckups/list";
        public static final String HealthCheckupsEntiry = "/HealthCheckups/entiry/{userId}";
        public static final String print = "/HealthCheckups/print";
    }


       public static class SevereMentalSupplement {
        public static final String check = "/severeMentalSupplement/check";
        public static final String Save = "/severeMentalSupplement/save";
        public static final String SevereMentalSupplement = "/severeMentalSupplement/list";
        public static final String SevereMentalSupplementEntiry = "/severeMentalSupplement/entiry/{userId}";
    }
    public static class SevereMentalFollow {
        public static final String check = "/severeMentalFollow/check";
        public static final String Save = "/severeMentalFollow/save";
        public static final String SevereMentalFollow = "/severeMentalFollow/list";
        public static final String SevereMentalFollowEntiry = "/severeMentalFollow/entiry/{userId}";
    }

    public static class ThreeSixYearChild {
        public static final String Save = "/threesixyearchild/save";
        public static final String ThreeSixYearChild = "/threesixyearchild/list";
        public static final String ThreeSixYearChildEntiry = "/threesixyearchild/entiry/{userId}";
        public static final String print = "/threesixyearchild/print";
    }

    public static class OneEightMonthChild {
        public static final String check = "/oneeightmonthchild/check";
        public static final String Save = "/oneeightmonthchild/save";
        public static final String OneEightMonthChild = "/oneeightmonthchild/list";
        public static final String OneEightMonthChildEntiry = "/oneeightmonthchild/entiry/{userId}";
        public static final String print = "/oneeightmonthchild/print";
    }

    public static class TwelveThsixEightMonthChild {
        public static final String Save = "/twelvethsixeightmonthchild/save";
        public static final String TwelveThsixEightMonthChild = "/twelvethsixeightmonthchild/list";
        public static final String TwelveThsixEightMonthChildEntiry = "/twelvethsixeightmonthchild/entiry/{userId}";
        public static final String print = "/twelvethsixeightmonthchild/print";
    }

    public static class NewbornVisit {
        public static final String Save = "/newbornvisit/save";
        public static final String NewbornVisit = "/newbornvisit/list";
        public static final String NewbornVisitEntiry = "/newbornvisit/entiry/{userId}";
    }

    public static class ChildrenMangement {
        public static final String Save = "/childrenmangement/save";
        public static final String ChildrenMangement = "/childrenmangement/list";
        public static final String ChildrenMangementEntiry = "/childrenmangement/entiry/{userId}";
        public static final String print = "/childrenmangement/print";
    }

    public static class ChildrenStatistics {
        public static final String Save = "/childrenstatistics/save";
        public static final String ChildrenStatistics = "/childrenstatistics/list";
        public static final String ChildrenStatisticsEntiry = "/childrenstatistics/entiry/{userId}";
    }

    public static class SevereHyperFollow {
        public static final String check = "/SevereHyperFollow/check";
        public static final String Save = "/SevereHyperFollow/save";
        public static final String SevereHyperFollow = "/SevereHyperFollow/list";
        public static final String SevereHyperFollowEntiry = "/SevereHyperFollow/entiry/{userId}";
        public static final String print = "/SevereHyperFollow/print";
    }
    public static class SevereHealthPlanFollow {
        public static final String Save = "/SevereHealthPlanFollow/save";
        public static final String SevereHealthPlanFollow = "/SevereHealthPlanFollow/list";
        public static final String SevereHealthPlanFollowEntiry = "/SevereHealthPlanFollow/entiry/{userId}";
    }
    public static class SevereHyperSelectFollow {
        public static final String Save = "/hyperSelect/save";
        public static final String SevereHyperSelectFollow = "/hyperSelect/list";
        public static final String SevereHyperSelectFollowEntiry = "/hyperSelect/entiry/{userId}";
    }
    public static class SevereHyperSumFollow {
        public static final String Save = "/hyperSum/save";
        public static final String SevereHyperSumFollow = "/hyperSum/list";
        public static final String SevereHyperSumFollowEntiry = "/hyperSum/entiry/{userId}";
    }
    public static class Tuberculosispatients {
        public static final String Save = "/tuberculosispatients/save";
        public static final String Tuberculosispatients = "/tuberculosispatients/list";
        public static final String TuberculosispatientsEntiry = "/tuberculosispatients/entiry/{userId}";
        public static final String print = "/tuberculosispatients/print";
    }
    public static class FirsthomevisitRecord {
        public static final String check = "/firsthomevisitRecord/check";
        public static final String Save = "/firsthomevisitRecord/save";
        public static final String FirsthomevisitRecord = "/firsthomevisitRecord/list";
        public static final String FirsthomevisitRecordEntiry = "/firsthomevisitRecord/entiry/{userId}";
        public static final String print = "/firsthomevisitRecord/print";
    }

    public static class Followupservicerecord {
        public static final String Save = "/followupservicerecord/save";
        public static final String Followupservicerecord = "/followupservicerecord/list";
        public static final String FollowupservicerecordEntiry = "/followupservicerecord/entiry/{userId}";
        public static final String print = "/followupservicerecord/print";
    }

    public static class Tuberculosisfllowup {
        public static final String Save = "/tuberculosisfllowup/save";
        public static final String Tuberculosisfllowup = "/tuberculosisfllowup/list";
        public static final String TuberculosisfllowupEntiry = "/tuberculosisfllowup/entiry/{userId}";
        public static final String print = "/tuberculosisfllowup/print";
    }

    public static class TuberculosisHealth {
        public static final String Save = "/tuberculosishealth/save";
        public static final String TuberculosisHealth = "/tuberculosishealth/list";
        public static final String TuberculosisHealthEntiry = "/tuberculosishealth/entiry/{userId}";
    }
    public static class TuberculosisPatient {
        public static final String Save = "/tuberculosisPatient/save";
        public static final String TuberculosisPatient = "/tuberculosisPatient/list";
        public static final String TuberculosisPatientEntiry = "/tuberculosisPatient/entiry/{userId}";
    }


    public static class Transtreat {
        public static final String Save = "/transtreat/save";
        public static final String Transtreat = "/transtreat/list";
        public static final String TranstreatEntiry = "/transtreat/entiry/{userId}";
        public static final String print = "/transtreat/print";
    }


    public static class Transtreatin {
        public static final String Save = "/transtreatin/save";
        public static final String Transtreatin = "/transtreatin/list";
        public static final String TranstreatinEntiry = "/transtreatin/entiry/{userId}";
        public static final String print = "/transtreatin/print";
    }



    public static class Transtreatset {
        public static final String Save = "/transtreatset/save";
        public static final String Transtreatset = "/transtreatset/list";
        public static final String TranstreatsetEntiry = "/transtreatset/entiry/{userId}";
        public static final String print = "/transtreatset/print";
    }



    public static class Transtreatlog {
        public static final String Save = "/transtreatlog/save";
        public static final String Transtreatlog = "/transtreatlog/list";
        public static final String TranstreatlogEntiry = "/transtreatlog/entiry/{userId}";
        public static final String print = "/transtreatlog/print";
    }

    public static class MaternalManagement {
        public static final String Save = "/maternalManagement/save";
        public static final String MaternalManagement = "/maternalManagement/list";
        public static final String MaternalManagementEntiry = "/maternalManagement/entiry/{userId}";
    }
    public static class MaternalStatistical {
        public static final String Save = "/maternalStatistical/save";
        public static final String MaternalStatistical = "/maternalStatistical/list";
        public static final String MaternalStatisticalEntiry = "/maternalManagement/entiry/{userId}";
    }

    public static class TwoDiabetes {
        public static final String Check = "/TwoDiabetes/check";
        public static final String Save = "/TwoDiabetes/save";
        public static final String TwoDiabetes = "/TwoDiabetes/list";
        public static final String TwoDiabetesEntiry = "/TwoDiabetes/entiry/{userId}";
        public static final String print = "/TwoDiabetes/print";
        public static final String printData = "/TwoDiabetes/print";
    }
    public static class TwoDiabetesSelect {
        public static final String Save = "/twoDiabetesSelect/save";
        public static final String Check = "/twoDiabetesSelect/check";
        public static final String TwoDiabetesSelect = "/twoDiabetesSelect/list";
        public static final String TwoDiabetesSelectEntiry = "/twoDiabetesSelect/entiry/{userId}";
    }
    public static class Totaldiabetes {
        public static final String Save = "/totaldiabetes/save";
        public static final String Totaldiabetes = "/totaldiabetes/list";
        public static final String TotaldiabetesEntiry = "/totaldiabetes/entiry/{userId}";
    }

    public static class Perinfor {
        public static final String Save = "/Perinfor/save";
        public static final String Perinfor = "/Perinfor/list";
        public static final String PerinforPage = "/Perinfor/listPage";
        public static final String PerinforEntiry = "/Perinfor/entiry/{userId}";
        public static final String print = "/Perinfor/print";
    }

    public static class PerinforFileEmigrate {
        public static final String Save = "/PerinforFileEmigrate/save";
        public static final String PerinforFileEmigrate = "/PerinforFileEmigrate/list";
        public static final String PerinforFileEmigrateEntiry = "/PerinforFileEmigrate/entiry/{userId}";
        public static final String print = "/PerinforFileEmigrate/print";
    }

    public static class ResidentManagement {
        public static final String Save = "/residentManagement/save";
        public static final String ResidentManagement = "/residentManagement/list";
        public static final String ResidentManagementEntiry = "/residentManagement/entiry/{userId}";
    }

    public static class ResidentsStatistical {
        public static final String ResidentsStatistical = "/residentsStatistical/list";
    }

    public static class ElderlyStatistical {
        public static final String Save = "/elderlyStatistical/save";
        public static final String ElderlyStatistical = "/elderlyStatistical/list";
        public static final String ElderlyStatisticalEntiry = "/elderlyStatistical/entiry/{userId}";
    }
    public static class Elderlyhealthy {
        public static final String Save = "/elderlyhealthy/save";
        public static final String Elderlyhealthy = "/elderlyhealthy/list";
        public static final String ElderlyhealthyEntiry = "/elderlyhealthy/entiry/{userId}";
    }
    public static class Follow {
        public static final String Save = "/follow/save";
        public static final String Follow = "/follow/list";
        public static final String FollowEntiry = "/follow/entiry/{followId}";
    }

    public static class MedicalStatistical {
        public static final String Save = "/medicalStatistical/save";
        public static final String MedicalStatistical = "/medicalStatistical/list";
        public static final String MedicalStatisticalEntiry = "/medicalStatistical/entiry/{userId}";
    }


}
