/*
 Navicat Premium Data Transfer

 Source Server         : ZhengJiaWei
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : localhost:3306
 Source Schema         : doc10

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 13/06/2019 12:38:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for basic_family_infor
-- ----------------------------
DROP TABLE IF EXISTS `basic_family_infor`;
CREATE TABLE `basic_family_infor`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `belong_community` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drink_water` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `family_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `family_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fb_organization` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fb_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fb_time` date NULL DEFAULT NULL,
  `fuel_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `heade_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `kitchen_exhaust` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `live_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `local_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `response_doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `toilet` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zip_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of basic_family_infor
-- ----------------------------
INSERT INTO `basic_family_infor` VALUES ('303900035c062d57f153660bb080d756', '', '', '2018-12-04', NULL, '', '', '', '', '', NULL, '', '大大大', '', '', '', '', '2018-12-04', '阿萨德', '', '', NULL, '已删');
INSERT INTO `basic_family_infor` VALUES ('303900035c062d87f153660bb080d758', NULL, NULL, '2018-12-04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '我', NULL, NULL, NULL, NULL, '2018-12-04', NULL, NULL, NULL, NULL, '已删');

-- ----------------------------
-- Table structure for basic_family_infor_member
-- ----------------------------
DROP TABLE IF EXISTS `basic_family_infor_member`;
CREATE TABLE `basic_family_infor_member`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `familyid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_num`(`id_num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of basic_family_infor_member
-- ----------------------------
INSERT INTO `basic_family_infor_member` VALUES ('303900035c062d82f153660bb080d757', '303900035c062d87f153660bb080d758', '111111111111111111', '户主', NULL, '2018-12-04', NULL, '2018-12-04');

-- ----------------------------
-- Table structure for children_mangement
-- ----------------------------
DROP TABLE IF EXISTS `children_mangement`;
CREATE TABLE `children_mangement`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `document_date` date NULL DEFAULT NULL,
  `document_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_visit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `live_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `newborn_visit` int(11) NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `visit_all_count` int(11) NULL DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `modifier` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for children_statistics
-- ----------------------------
DROP TABLE IF EXISTS `children_statistics`;
CREATE TABLE `children_statistics`  (
  `id` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `modifier` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `area` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year` int(11) NULL DEFAULT NULL,
  `people_count` int(11) NULL DEFAULT NULL,
  `newborn_visit` int(11) NULL DEFAULT NULL,
  `live_count` int(11) NULL DEFAULT NULL,
  `visit_ratio` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mange_people` int(11) NULL DEFAULT NULL,
  `area_mange_people` int(11) NULL DEFAULT NULL,
  `manage_visit_ratio` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `newborn_year` int(11) NULL DEFAULT NULL,
  `die_people` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of children_statistics
-- ----------------------------
INSERT INTO `children_statistics` VALUES ('303900035c052d9af153661cb4045406', NULL, '2018-12-04', NULL, '2018-12-03', '河北省/秦皇岛市/北戴河区', 2018, 1, 0, 1, '0.00%', 3, 1, '0.00%', 1, 0);
INSERT INTO `children_statistics` VALUES ('303900035c05f469f153660f00869773', NULL, '2018-12-04', NULL, '2018-12-04', '北京市/崇文区', 2018, 1, 0, 1, '0.00%', 3, 1, '0.00%', 1, 0);

-- ----------------------------
-- Table structure for diabetes
-- ----------------------------
DROP TABLE IF EXISTS `diabetes`;
CREATE TABLE `diabetes`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `blood` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ethnic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `body_mass_index` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `compliance_behavior` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `daily_drinking` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `daily_smoking` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `diabetescol` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor_sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drug_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drugs_adversed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `examination_date` date NULL DEFAULT NULL,
  `exercise` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fasting_blood` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `follow_classification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `follow_count` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `follow_date` date NULL DEFAULT NULL,
  `follow_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `foot_artey_pulse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hemoglobin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hypoglycemia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `live_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `next_follow_date` date NULL DEFAULT NULL,
  `other` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pressure` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `psychological_adjust` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `referral_cause` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `referral_institution` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `staple_food` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `symptoms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `usage_dosage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of diabetes
-- ----------------------------
INSERT INTO `diabetes` VALUES ('303900035c05e5e8f153661550d3ee18', '我', '', NULL, '', NULL, '1', '良好', '2018-12-04', '1', '1', '间断', '', '1', '1', '', '无', '2018-12-17', '1', '1', '控制满意', '第一次', '2018-12-10', '门诊', '触及正常', '1', '偶尔', NULL, NULL, 'admin', '2018-12-04', '2018-12-04', '1', '1', '良好', '1', '1', '', '1', '多食', '1', '1', 'admin', '未删', '111111111111111111');

-- ----------------------------
-- Table structure for diabetes_select
-- ----------------------------
DROP TABLE IF EXISTS `diabetes_select`;
CREATE TABLE `diabetes_select`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `age` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ethnic` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `area` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `people_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `respon_doctor` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `follow_visit` date NULL DEFAULT NULL,
  `count` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `live_status` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `follow_date` date NULL DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `modifier` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `delete_state` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '2糖查询' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for elderly_healthy
-- ----------------------------
DROP TABLE IF EXISTS `elderly_healthy`;
CREATE TABLE `elderly_healthy`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(255) NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ismanagement` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `respon_doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doc_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_visit` date NULL DEFAULT NULL,
  `neonatal_visit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ethnic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doc_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for elderly_healthy_entity
-- ----------------------------
DROP TABLE IF EXISTS `elderly_healthy_entity`;
CREATE TABLE `elderly_healthy_entity`  (
  `id` int(11) NOT NULL,
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doc_date` date NULL DEFAULT NULL,
  `doc_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ethnic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_visit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `management` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `neonatal_visit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `respon_doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for elderly_statistics
-- ----------------------------
DROP TABLE IF EXISTS `elderly_statistics`;
CREATE TABLE `elderly_statistics`  (
  `id` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `modifier` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `area` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
  `year` int(11) NULL DEFAULT NULL COMMENT '年份',
  `people_count` int(11) NULL DEFAULT NULL COMMENT '常驻人口',
  `areasf_people` int(11) NULL DEFAULT NULL COMMENT '辖区65岁及以上常居数',
  `elderly_count` int(11) NULL DEFAULT NULL COMMENT '老年人总数',
  `manage_people` int(11) NULL DEFAULT NULL COMMENT '健康管理人数',
  `year_area_manageradio` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老年人健康管理率',
  `year_newcount` int(11) NULL DEFAULT NULL COMMENT '年内新增老人管理人数',
  `die_people` int(11) NULL DEFAULT NULL COMMENT '迁出(死亡)人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for emergencies
-- ----------------------------
DROP TABLE IF EXISTS `emergencies`;
CREATE TABLE `emergencies`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `accumulative_case` int(11) NULL DEFAULT NULL,
  `accumulative_mortality` int(11) NULL DEFAULT NULL,
  `confirm_grade_time` date NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detailed_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `event_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `event_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `event_time` datetime(0) NULL DEFAULT NULL,
  `first_diagnosis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `first_diagnosis_time` date NULL DEFAULT NULL,
  `first_disease_time` datetime(0) NULL DEFAULT NULL,
  `last_disease_time` datetime(0) NULL DEFAULT NULL,
  `main_symptoms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `major_signs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `measure_effect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `modify_diagnosis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_diagnosis_time` date NULL DEFAULT NULL,
  `modify_grade_time` date NULL DEFAULT NULL,
  `new_case` int(11) NULL DEFAULT NULL,
  `new_mortality` int(11) NULL DEFAULT NULL,
  `occur_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `occurrence_area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `others` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rapporteur` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remove_case` int(11) NULL DEFAULT NULL,
  `reporting_area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reporting_date` date NULL DEFAULT NULL,
  `reporting_time` datetime(0) NULL DEFAULT NULL,
  `reporting_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `source_details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sweep_range` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for first_prenatal_diagnosis
-- ----------------------------
DROP TABLE IF EXISTS `first_prenatal_diagnosis`;
CREATE TABLE `first_prenatal_diagnosis`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bmode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ab_operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `albumin` int(11) NULL DEFAULT NULL,
  `bld` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `blood` int(11) NULL DEFAULT NULL,
  `blood_sugar` int(11) NULL DEFAULT NULL,
  `blood_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `blood_urea` int(11) NULL DEFAULT NULL,
  `bodymass` int(11) NULL DEFAULT NULL,
  `cervix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cesarean_section` int(11) NULL DEFAULT NULL,
  `conjugated` int(11) NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enclosure` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `family_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gestational_number` int(11) NULL DEFAULT NULL,
  `gestational_weeks` int(11) NULL DEFAULT NULL,
  `gravida_age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `healthcare` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `heart` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `height` int(11) NULL DEFAULT NULL,
  `hemoglobin` int(11) NULL DEFAULT NULL,
  `hepatitisb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `history_pregnancy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hiv_antibody` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `husband_age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `husband_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `husband_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `insand_dep` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_menses` date NULL DEFAULT NULL,
  `lungs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nextdate` date NULL DEFAULT NULL,
  `other_blood` int(11) NULL DEFAULT NULL,
  `other_sassist` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other_urine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `past_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `personal_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `platelet_count` int(11) NULL DEFAULT NULL,
  `population` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `referral` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scr` int(11) NULL DEFAULT NULL,
  `sgot` int(11) NULL DEFAULT NULL,
  `sgpt` int(11) NULL DEFAULT NULL,
  `syphilis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total_bilirubin` int(11) NULL DEFAULT NULL,
  `urine_ketone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `urine_protein` int(11) NULL DEFAULT NULL,
  `urine_sugar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uterus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vagina` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vaginal_cleanliness` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vaginal_delivery` int(11) NULL DEFAULT NULL,
  `vaginal_secretions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vulva` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weight` int(11) NULL DEFAULT NULL,
  `white_blood` int(11) NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `first_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of first_prenatal_diagnosis
-- ----------------------------
INSERT INTO `first_prenatal_diagnosis` VALUES ('303900035c052c1ef153661cb4045402', '321', '有', 1, '2', 2, 1, '1', 1, 2, '异常', NULL, 1, '2018-12-03', 'admin', '2018-12-03', '111', '异常', '1', '无', 1, 1, '1', '心理', '未见异常', 2, 2, '乙型肝炎表面抗原', '新生儿死亡', '阳性', '1', '1', '1', '1', '2018-12-03', '未见异常', 'admin', '2018-12-04', '我', '2018-12-03', 2, '1', '1', '高血压,心脏病', '饮酒', 2, '异常', '1', '有', 1, 1, 2, '阴性', 1, '13', 2, '1', '未见异常', '未见异常', 'I度', 1, '假丝酵母菌', '未见异常', 2, 2, '未删', '111111111111111111', '北京市/东城区', NULL);
INSERT INTO `first_prenatal_diagnosis` VALUES ('303900035c053c78f153661cb404540b', NULL, '', NULL, '', NULL, NULL, '', NULL, NULL, '', NULL, NULL, '2018-12-03', 'admin', '2018-12-03', '', '', '', '', NULL, 2, '0', '', '', NULL, NULL, '', '', '', '', '', '', '', '2018-12-03', '', '', '2018-12-03', '我', '2018-12-03', NULL, '', '', '高血压', '', NULL, '', '', '', NULL, NULL, NULL, '', NULL, '', NULL, '', '', '', '', NULL, '', '', NULL, NULL, '未删', '111111111111111111', '北京市/东城区', NULL);

-- ----------------------------
-- Table structure for firsthomevisit_record
-- ----------------------------
DROP TABLE IF EXISTS `firsthomevisit_record`;
CREATE TABLE `firsthomevisit_record`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `adverse_reactions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chemotherapy_regimen` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `close_check` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drinking` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drug_dosage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drug_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ethnic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `evaluation_doctorsignature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `follow_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `follow_time` date NULL DEFAULT NULL,
  `habits_precautions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `howtake_medicine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_date` date NULL DEFAULT NULL,
  `input_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `irregular_medication` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `life_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `medication_recordcard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `medicationmethod_drugstorage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_followtie` date NULL DEFAULT NULL,
  `other_symptoms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `patien_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recent_visit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `respon_doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `review` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `separate_room` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `smoking` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sputum_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `supervisor_selection` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `symptoms_signs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `takemedicine_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `takemedicine_time` date NULL DEFAULT NULL,
  `treatment_tuberculosis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `usages` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ventilation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `management` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rule_medicine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of firsthomevisit_record
-- ----------------------------
INSERT INTO `firsthomevisit_record` VALUES ('303900035c05605cf153661cb4045419', '掌握', 0, '北京市/东城区', '1', '掌握', '1', ',固定剂量复合制剂', '非耐药', '', '1', NULL, '门诊', '2018-12-12', '掌握', '掌握', NULL, '', '掌握', '完成治疗', '掌握', '掌握', '我', '2018-12-04', '1', '初治', '111111111111111111', '', '2018-12-04 00:00:00', '1', '掌握', '有', NULL, '1', '未查痰', '自服药', ',低热盗汗', '1', '2018-12-04', '掌握', '每日', '良好', NULL, '是', '未删', 'admin', '2018-12-04', NULL, '2018-12-04');

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `personal_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '个人编号',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户id',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '联系电话',
  `last_follow` date NULL DEFAULT NULL COMMENT '最后一次随访时间',
  `next_follow` date NULL DEFAULT NULL COMMENT '下次随访时间',
  `modifier` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `people_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of follow
-- ----------------------------
INSERT INTO `follow` VALUES ('0', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('1', '1', '1', '1', '1', '2018-12-09', '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('11', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('12', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('123', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('13', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('14', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('15', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('16', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('17', NULL, NULL, NULL, NULL, NULL, '2018-12-12', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('18', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('19', NULL, NULL, NULL, NULL, NULL, '2018-12-13', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('2', NULL, NULL, NULL, NULL, NULL, '2018-12-09', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('20', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('21', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('3', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('303900035c0d23f4f153664c14b1e1b7', '303900035c06a636f15366068c0adc4c', '0dae0003590016e5b3865e377b2f8615', '萨达萨达', '1', '2018-12-19', '2018-12-17', NULL, '2018-12-09', NULL, '2018-12-09', '111111111111111111');
INSERT INTO `follow` VALUES ('4', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('5', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('6', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('7', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('8', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `follow` VALUES ('9', NULL, NULL, NULL, NULL, NULL, '2018-12-08', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for follow_up_service_record
-- ----------------------------
DROP TABLE IF EXISTS `follow_up_service_record`;
CREATE TABLE `follow_up_service_record`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `adverse_drug_reactions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adverse_reactions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `alcohol` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `causes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chemotherapy_regimen` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `complication_comorbidities` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `complications` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drug_dosage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `evaluation_doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flow_up_time` date NULL DEFAULT NULL,
  `follow_up_methods` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `followdoctor_signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `medicine_rate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mnumner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_follotime` date NULL DEFAULT NULL,
  `numbermissed_medications` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other_symptoms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `should_visit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `smoking` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stop_time` date NULL DEFAULT NULL,
  `stop_treatment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `subjects` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `superivisor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `symptoms_signs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `treamnt_options` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `treatment_month` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `twoweeks_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `usages` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `actual_visit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of follow_up_service_record
-- ----------------------------
INSERT INTO `follow_up_service_record` VALUES ('303900035c05612ef153661cb404541a', '无', '', '1', '1', '1', '1', '无', '', ',固定剂量复合制剂', '1', '2018-12-04', '门诊', '1', '100.00', '1', '我', '2018-12-04', '1', '1', '1', '1', '2018-12-04', '死亡', '1', '自服药', ',咯血或血痰,皮肤瘙痒、皮疹', '1', '1', '1', '每日', '1', '未删', '111111111111111111', 'admin', '2018-12-04', NULL, '2018-12-04');

-- ----------------------------
-- Table structure for health_checkups
-- ----------------------------
DROP TABLE IF EXISTS `health_checkups`;
CREATE TABLE `health_checkups`  (
  `name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `check_date` date NULL DEFAULT NULL COMMENT '体检时间',
  `doctor` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '责任医生',
  `symptoms` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '症状',
  `temperature` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '体温',
  `pulse_rate` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '脉率',
  `breathing` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '呼吸频率',
  `stature` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身高',
  `weight` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '体重',
  `waistline` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '腰围',
  `bodymassindex` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '体质指数',
  `self_assessment` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老年人健康状态自我评估',
  `self_care` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老年人生活自理能力自我评估',
  `cognition` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老年人认知能力',
  `mood` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老年人情感状态',
  `exercise` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '体育锻炼',
  `diet` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '饮食习惯',
  `smoking` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '吸烟情况',
  `drinking` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '喝酒情况',
  `work_diseases` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业病危害因素接触史',
  `oral_cavity` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '口腔',
  `listen` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '听力',
  `sport` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运动功能',
  `bottom_eye` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '眼底',
  `skin` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '皮肤',
  `sclera` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巩膜',
  `lymph_node` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '淋巴结',
  `limb_edema` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下肢水肿',
  `foot_artey_pulse` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '足背动脉搏动',
  `anal_diagnosis` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '肛门指诊',
  `mammary_gland` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '乳腺',
  `vulva` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外阴',
  `vagina` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阴道',
  `cervix` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宫颈',
  `palace` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宫体',
  `fasting_blood_sugar` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '空腹血糖值',
  `electrocardiogram` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '心电图',
  `urine_microalbumin` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '尿微量白蛋白',
  `shitblood` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大便潜血',
  `glycated_glycemic` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '糖化血糖蛋白',
  `hepatitisb` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '乙肝肝炎表面抗原',
  `chestxray` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '胸部X线片',
  `bsuper` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'B超',
  `cervical_smear` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宫颈涂片',
  `cerebrovascular` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '脑血管疾病',
  `kidney_disease` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '肾脏疾病',
  `heart_disease` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '心脏疾病',
  `vascular_disease` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '血管疾病',
  `eye_disease` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '眼部疾病',
  `nervous_system` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '神经系统疾病',
  `other_diabetes` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他系统疾病',
  `health_evaluation` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '健康评价',
  `health_guidance` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '健康指导',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` date NULL DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_date` date NULL DEFAULT NULL COMMENT '修改时间',
  `riskcontrol` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pressureleft` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pressureright` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exercise_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stick_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exercisestyle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `breath_sounds` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `every_smoking` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `smoking_age` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `no_smoking_age` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `everydrinking` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drinking_types` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nodrinking` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drunk` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drinking_age` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `work_type` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `working_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dust` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dustpro_measures` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fanshe` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fanshepro_measures` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `physicalfactors` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phypro_measures` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chemicals` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chepro_measures` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other1` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other1pro_measures` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `visionz` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `visiony` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vicorrectz` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vicorrecty` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `heartrate` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `barrelchest` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `noise` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hythmheart` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenderness` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bagpiece` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hepatomegaly` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `splenomegaly` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobilevoiced` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rale` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attachment` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other2` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `whitebloodcells` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `platelet` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other3` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `urineprotein` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `urinesugar` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `urineketone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ery` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `serumtransa` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `glutamictransame` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `totalbilirubin` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `boundbilirubin` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bloodurea` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `potconcen` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sodiumconcen` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `totalcholestero` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ldlcholesterol` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hdlcholesterol` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other6` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inh_date` date NULL DEFAULT NULL,
  `outh_date` date NULL DEFAULT NULL,
  `institut_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `medicalrecordno` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inb_date` date NULL DEFAULT NULL,
  `outb_date` date NULL DEFAULT NULL,
  `why1` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `institut_name1` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `medicalrecordno1` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vaccination_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vaccination_date` date NULL DEFAULT NULL,
  `vaccination_agency` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drugname` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dosage` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `duration` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `medicompliance` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `albumin` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `useing` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `why` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hemoglobin` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '健康体检表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of health_checkups
-- ----------------------------
INSERT INTO `health_checkups` VALUES ('1', '303900035c063050d6005d1a28b9ef7a', '2018-12-03', '1', '', '1', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, '2018-12-04', NULL, '2018-12-04', '', '', '', '', '', '已删', '350125199712020362', NULL, '', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `health_checkups` VALUES ('2', '303900035c063241d6005d1a28b9ef7b', NULL, '医生', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '苍白', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, '2018-12-04', NULL, '2018-12-04', '', '', '', '', '', '未删', '350121997120203622', NULL, '', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `health_checkups` VALUES ('kk', '303900035c068271d6005d1a28b9ef7c', '2018-12-05', 'kk', '手脚麻木', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, '2018-12-04', NULL, '2018-12-04', '', '', '', '', '', '已删', '350125199712020362', NULL, '', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `health_checkups` VALUES ('柯梦迁', '303900035c0686cfd6005d1a28b9ef7d', '2018-12-05', '柯医生', '胸闷', '36', '100', '1', '1', '1', '1', '1', '基本满意', '轻度依赖', '粗筛阴性', '粗筛阴性', '每周一次', '嗜盐', '从不吸烟', '偶尔', '有', '1', '听不清或无法听见', '无法独立完成其中任何一动作', '正常', '潮红', '黄染', '锁骨上', '双侧不对称', '触及左侧弱或消失', '触痛', '乳房切除', '正常', '正常', '正常', '正常', '1', '异常', '1', '阳性', '1', '阳性', '正常', '正常', '异常', '蛛网膜下腔出血', '肾功能衰竭', '其他', '动脉闭塞性疾病', '视乳头水肿', '有', '1', '有异常', '减体重', NULL, '2018-12-04', NULL, '2018-12-04', '建议复诊', '1', '', '', '', '未删', '350125199712020362', NULL, '', '正常', '1', '1', '1', '1', '红酒', '未戒酒', '是', '1', '1', '1', '1', '有', '1', '无', '1', '有', '1', '无', '1', NULL, '1', '1', '1', '1', '1', '是', '无', '齐', '无', '无', '无', '无', '无', '千罗音', '正常', '1', '1', '1', '1', '1', '1', '1', '1', '1', NULL, '1', '1', '11', NULL, '1', '1', '1', '1', '1', '2018-12-26', '2018-12-28', '1', '1', '2018-12-18', '2018-12-27', '1', '1', '1', '1', '2018-12-27', '1', '1', '1', '1', '规律', '1', '1', '1', '1');
INSERT INTO `health_checkups` VALUES ('阿萨德', '303900035c06a6e6f15366068c0adc51', '2018-12-01', '大大', '头痛,乏力', '2', '2', '2', '2', '1', '2', '2', '基本满意', '可自理', '粗筛阴性', '粗筛阴性', '每周一次', '荤食为主', '从不吸烟', '每天', '无', '2', '听见', '可顺利完成', '异常', '发绀', '黄染', '锁骨上', '双侧不对称', '触及左侧弱或消失', '触痛', '异常分泌', '正常', '正常', '正常', '正常', '2', '正常', '2', '阴性', '2', '阴性', '正常', '正常', '异常', '蛛网膜下腔出血', '糖尿病肾病', '心绞痛', '夹层动脉瘤', '视网膜出血或渗出', '未发现', '2', '体检无异常', '锻炼', NULL, '2018-12-05', NULL, '2018-12-05', '纳入慢性病患者健康管理', '2', '2', '2', '2', '未删', '111111111111111111', NULL, '2', '正常', '2', '2', '2', '2', '啤酒', '未戒酒', '是', '2', '2', '2', '2', '无', '2', '有', '2', '无', '2', '有', '2', '无', '2', '2', '2', '2', '2', '是', '有', '齐', '有', '无', '有', '无', '无', '无', '正常', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2018-12-04', '2018-12-12', '2', '2', '2018-12-02', '2018-12-03', '2', '2', '2', '2', '2018-12-11', '2', '2', '2', '2', '规律', '2', '2', '2', '2');

-- ----------------------------
-- Table structure for health_management_ecord
-- ----------------------------
DROP TABLE IF EXISTS `health_management_ecord`;
CREATE TABLE `health_management_ecord`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chmedicine_guidance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor_signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `health_date` date NULL DEFAULT NULL,
  `workid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `physical_identification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `physical_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question6` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question7` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question8` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question9` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question10` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question11` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question12` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question13` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question14` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question15` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question16` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question17` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question18` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question19` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question20` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question21` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question22` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question23` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question24` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question25` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question26` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question27` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question28` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question29` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question30` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question31` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question32` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question33` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qideficiency` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `yangdeficiency` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `yingdeficiency` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tandeficiency` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shideficiency` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `xuedeficiency` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qiyu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `specialenamel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `peace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `show1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `show2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `show3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `show4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `show5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `show6` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `show7` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `systemid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of health_management_ecord
-- ----------------------------
INSERT INTO `health_management_ecord` VALUES ('303900035c056dfff153661cb404541c', '111111111111111111', '我', '1.情志调摄', '2018-12-04', 'admin', '得到', '2018-12-04', '1', 'admin', '2018-12-04', '', '', '有时(3分)', '总是(5分)', '有时(3分)', '没有(1分)', '没有(1分)', '有时(3分)', '时常(4分)', '有时(3分)', '有时(25≤BMI<26)(3分)', '有时(3分)', '有时(3分)', '很少(2分)', '有时(3分)', '很少(2分)', '没有(一年<2次)(1分)', '有时(3分)', '有时(一年3,4次)(3分)', '很少(2分)', '很少(2分)', '没有(1分)', '时常(4分)', '很少(2分)', '没有(1分)', '没有(1分)', '很少(2分)', '有时(3分)', '时常(4分)', '有时(腹围86-90cm,2.56-2.7尺)(3分)', '很少(2分)', '没有(1分)', '很少(2分)', '有时(3分)', '有时(3分)', NULL, '倾向是', '否', '否', '否', '是', '倾向是', '否', '是', '是', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '未删', '北京市/东城区');

-- ----------------------------
-- Table structure for health_plan
-- ----------------------------
DROP TABLE IF EXISTS `health_plan`;
CREATE TABLE `health_plan`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `health_goal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(255) NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `credit_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pan_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `institutions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `penple` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `orgpenple` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pan_time` date NULL DEFAULT NULL,
  `health_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for health_supervision
-- ----------------------------
DROP TABLE IF EXISTS `health_supervision`;
CREATE TABLE `health_supervision`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `find_time` date NULL DEFAULT NULL,
  `inform_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inform_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `organization` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reporte_time` date NULL DEFAULT NULL,
  `reporter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of health_supervision
-- ----------------------------
INSERT INTO `health_supervision` VALUES ('303900035c0b9d77f15366540875d9f6', '2018-12-08', NULL, NULL, '', '', '', '2018-12-08', '的', NULL, '', '未删');
INSERT INTO `health_supervision` VALUES ('303900035c0b9de4f15366540875d9f7', '2018-12-08', '', NULL, '', '', '', '2018-12-08', '', NULL, '', '未删');
INSERT INTO `health_supervision` VALUES ('303900035c0b9e08f15366540875d9f8', '2018-12-08', '运营维护', NULL, '', '', '', '2018-12-08', '', NULL, '', '未删');

-- ----------------------------
-- Table structure for hyper_manag
-- ----------------------------
DROP TABLE IF EXISTS `hyper_manag`;
CREATE TABLE `hyper_manag`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `work_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `foll_update` date NULL DEFAULT NULL,
  `ismanagement` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `foll_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `symptoms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `blood_pressure` int(255) NULL DEFAULT NULL,
  `weight` int(255) NULL DEFAULT NULL,
  `bmi` int(255) NULL DEFAULT NULL,
  `heart_rate` int(255) NULL DEFAULT NULL,
  `other_phy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `smoke_per` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drink_per` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `sport` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salt_situation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `psy_adjust` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `obey_medical` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `aux_examina` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `medi_compli` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adverdrug_react` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drug_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drug_usage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oth_drug_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other_drugu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oth_drug_name_san` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other_drugusan` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oth_drug_name_si` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other_drugusi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `referral_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organ_and_div` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date_next_follow` date NULL DEFAULT NULL,
  `doc_signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hyper_manag
-- ----------------------------
INSERT INTO `hyper_manag` VALUES ('303900035c0525d0f153661cb40453f8', '303900035c0525d0f153661cb40453f9', '我', '', '111111111111111111', NULL, NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-03', '1', '2018-12-03', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c052bc5f153661cb40453fe', '303900035c052bc5f153661cb40453ff', '我', '', '111111111111111111', NULL, NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-03', '1', '2018-12-03', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c052beaf153661cb4045400', '303900035c052beaf153661cb4045401', '我', '', '111111111111111111', NULL, NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-03', '1', '2018-12-03', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c052c88f153661cb4045403', '303900035c052c88f153661cb4045404', '我', '', '111111111111111111', NULL, NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-03', '1', '2018-12-03', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c054de6f153661cb404540c', '303900035c054de6f153661cb404540d', '我', '', '111111111111111111', NULL, NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-03', '1', '2018-12-03', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c054e8ef153661cb404540e', '303900035c054e8ef153661cb404540f', '我', '', '111111111111111111', NULL, NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-03', '1', '2018-12-03', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c054f31f153661cb4045410', '303900035c054f31f153661cb4045411', '我', '北京市/东城区', '111111111111111111', NULL, NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-03', '1', '2018-12-03', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c054f46f153661cb4045412', '303900035c054f46f153661cb4045413', '我', '北京市/东城区', '111111111111111111', '2018-12-03', NULL, NULL, '未删', '家庭', '头疼头晕,恶心呕吐', '第一次', 2, 22, 2, 2, '2', '2', '2', 'admin', '2018-12-03', 'admin', '2018-12-04', '2', '轻', '良好', '良好', '2', '规律', '无', '不良反应', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2019-01-18', '2', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c055e56f153661cb4045417', '303900035c055e56f153661cb4045418', '我', '北京市/东城区', '111111111111111111', NULL, NULL, NULL, '未删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-04', '1', '2018-12-04', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d1d05f1536633505153dc', '303900035c0d1d05f1536633505153dd', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-09', NULL, NULL, '已删', '', '', '第一次', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-10', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d1d41f1536633505153de', '303900035c0d1d41f1536633505153df', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-09', NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-10', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d1ddbf1536633505153e0', '303900035c0d1ddbf1536633505153e1', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-10', NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-11', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d1e1af1536633505153e2', '303900035c0d1e1af1536633505153e3', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-09', NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-10', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d1e5ff1536633505153e4', '303900035c0d1e5ff1536633505153e5', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-09', NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-10', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d1ec0f1536633505153e6', '303900035c0d1ec0f1536633505153e7', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-09', NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-10', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d1f4df1536633505153e8', '303900035c0d1f4df1536633505153e9', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-11', NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-11', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d20fdf1536633505153ec', '303900035c0d20fdf1536633505153ed', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-09', NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-10', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d2193f1536633505153ee', '303900035c0d2193f1536633505153ef', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-09', NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-10', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d222df1536633505153f0', '303900035c0d222df1536633505153f1', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-09', NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-10', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d228cf1536633505153f2', '303900035c0d228cf1536633505153f3', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-09', NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-10', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d22aef1536633505153f4', '303900035c0d22aef1536633505153f5', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-09', NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-10', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d234ef153664c14b1e1b3', '303900035c0d234ef153664c14b1e1b4', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-11', NULL, NULL, '未删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', 'admin', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-10', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d23f4f153664c14b1e1b5', '303900035c0d23f4f153664c14b1e1b6', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-02', NULL, NULL, '未删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', 'admin', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-10', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d25f1f153664c14b1e1b8', '303900035c0d25f1f153664c14b1e1b9', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-12', NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-13', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d265af153664c14b1e1ba', '303900035c0d265af153664c14b1e1bb', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-12', NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-13', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d26adf153664c14b1e1bc', '303900035c0d26adf153664c14b1e1bd', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-11', NULL, NULL, '已删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', '1', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-22', '', NULL);
INSERT INTO `hyper_manag` VALUES ('303900035c0d2707f153664c14b1e1be', '303900035c0d2707f153664c14b1e1bf', '萨达萨达', '天津市/河东区', '111111111111111111', '2018-12-19', NULL, NULL, '未删', '', '', '', NULL, NULL, NULL, NULL, '', '', '', 'admin', '2018-12-09', 'admin', '2018-12-09', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-12-17', '', NULL);

-- ----------------------------
-- Table structure for hyper_select
-- ----------------------------
DROP TABLE IF EXISTS `hyper_select`;
CREATE TABLE `hyper_select`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(255) NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ismanagement` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `respon_doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doc_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_visit` date NULL DEFAULT NULL,
  `neonatal_visit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ethnic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doc_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for hyper_sum
-- ----------------------------
DROP TABLE IF EXISTS `hyper_sum`;
CREATE TABLE `hyper_sum`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `residents_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `res_high_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `high_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `high_number_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year_high_mag_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year_high_mag_rate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year_high_near_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year_high_ctr_rate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year_high_add_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deaths_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hyper_sum
-- ----------------------------
INSERT INTO `hyper_sum` VALUES ('303900035c052579f153661cb40453f4', '河北省/秦皇岛市/青龙满族自治县', '2018', '1', NULL, '1', '1', '1', '1', '100.0%', '1', '100.0%', '1', '2', NULL, '2018-12-03', NULL, '2018-12-03');
INSERT INTO `hyper_sum` VALUES ('303900035c052d9af153661cb4045407', '河北省/秦皇岛市/北戴河区', '2018', '1', NULL, '0', '1', '1', '0', '0.0%', '0', '0%', '0', '0', NULL, '2018-12-03', NULL, '2018-12-03');
INSERT INTO `hyper_sum` VALUES ('303900035c05f469f153660f00869774', '北京市/崇文区', '2018', '3', NULL, '1', '1', '1', '1', '100.0%', '1', '100.0%', '1', '0', NULL, '2018-12-04', NULL, '2018-12-04');
INSERT INTO `hyper_sum` VALUES ('303900035c06a637f15366068c0adc4d', '天津市/河东区', '2018', '3', NULL, '1', '1', '1', '1', '100.0%', '1', '100.0%', '1', '2', NULL, '2018-12-05', NULL, '2018-12-05');

-- ----------------------------
-- Table structure for maternal_management
-- ----------------------------
DROP TABLE IF EXISTS `maternal_management`;
CREATE TABLE `maternal_management`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doc_date` date NULL DEFAULT NULL,
  `doc_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `early_pregnancy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ethnic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `first` date NULL DEFAULT NULL,
  `handle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_visit` date NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `neonatal_visit` int(11) NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `respon_doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for maternal_statistical
-- ----------------------------
DROP TABLE IF EXISTS `maternal_statistical`;
CREATE TABLE `maternal_statistical`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `year` int(11) NULL DEFAULT NULL,
  `permanent_population` int(11) NULL DEFAULT NULL,
  `live_birth_number` int(11) NULL DEFAULT NULL,
  `early_pregnancy` int(11) NULL DEFAULT NULL,
  `early_pregnancy_rate` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `postpartum_visits` int(11) NULL DEFAULT NULL,
  `postpartum_visits_rate` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `management_women` int(11) NULL DEFAULT NULL,
  `custody_women` int(11) NULL DEFAULT NULL,
  `moved_number` int(11) NULL DEFAULT NULL,
  `delete_state` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of maternal_statistical
-- ----------------------------
INSERT INTO `maternal_statistical` VALUES ('303900035c052bb7f153661cb40453fb', '2018-12-03', NULL, '2018-12-07', NULL, '北京市/东城区', 2018, 2, 1, 2, '200.00%', 0, '0.00%', 1, 0, 1, NULL);
INSERT INTO `maternal_statistical` VALUES ('303900035c052d9af153661cb4045409', '2018-12-03', NULL, '2018-12-05', NULL, '河北省/秦皇岛市/北戴河区', 2018, 1, 0, 0, '0.0%', 0, '0.0%', 2, 0, 0, NULL);
INSERT INTO `maternal_statistical` VALUES ('303900035c05f469f153660f00869776', '2018-12-04', NULL, '2018-12-05', NULL, '北京市/崇文区', 2018, 1, 1, 0, '0.00%', 0, '0.00%', 2, 0, 0, NULL);
INSERT INTO `maternal_statistical` VALUES ('303900035c06a637f15366068c0adc4f', '2018-12-05', NULL, '2018-12-05', NULL, '天津市/河东区', 2018, 1, 0, 0, '0.0%', 0, '0.0%', 1, 0, 1, NULL);

-- ----------------------------
-- Table structure for mental_query
-- ----------------------------
DROP TABLE IF EXISTS `mental_query`;
CREATE TABLE `mental_query`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `ethnic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `management` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `response_doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recent_follow` date NULL DEFAULT NULL,
  `number_follow` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `living_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mental_statistics
-- ----------------------------
DROP TABLE IF EXISTS `mental_statistics`;
CREATE TABLE `mental_statistics`  (
  `id` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `modifier` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `area` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year` int(11) NULL DEFAULT NULL,
  `permanent_population` int(11) NULL DEFAULT NULL,
  `total_number_mental` int(11) NULL DEFAULT NULL,
  `mental_illness_year` int(11) NULL DEFAULT NULL,
  `mental_regulated` int(11) NULL DEFAULT NULL,
  `mental_patients` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `new_mental` int(11) NULL DEFAULT NULL,
  `moved_number` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mental_statistics
-- ----------------------------
INSERT INTO `mental_statistics` VALUES ('303900035c0557b8f153661cb4045416', NULL, '2018-12-04', NULL, '2018-12-04', '北京市/东城区', 2018, 1, 1, 1, 1, '100.0%', 1, 1);

-- ----------------------------
-- Table structure for newborn_visit
-- ----------------------------
DROP TABLE IF EXISTS `newborn_visit`;
CREATE TABLE `newborn_visit`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `abdomen` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `anus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birth_height` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birth_weight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthdate` date NULL DEFAULT NULL,
  `breathe` int(11) NULL DEFAULT NULL,
  `bregma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bregma_height` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bregma_width` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chest` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ear` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emesis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eye` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `face` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `father_birthdate` date NULL DEFAULT NULL,
  `father_job` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `father_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `father_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `feed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `feed_milk` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `feed_milk_count` int(11) NULL DEFAULT NULL,
  `follow_date` date NULL DEFAULT NULL,
  `guidance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `heart` int(11) NULL DEFAULT NULL,
  `heart_lung` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `illness` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `limb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `listen` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `malformation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `mother_birthdate` date NULL DEFAULT NULL,
  `mother_job` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mother_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mother_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mother_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `neck` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_date` date NULL DEFAULT NULL,
  `next_visit_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nose` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oral` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organization` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pudendum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `referral` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shit_count` int(11) NULL DEFAULT NULL,
  `skin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stifle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `temperature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `umbilical` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vertebra` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `week` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `yellow` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of newborn_visit
-- ----------------------------
INSERT INTO `newborn_visit` VALUES ('303900035c0632baf1536611bc29e4fb', '异常', '1', '未见异常', '1', '1', '2018-12-01', 1, '正常', '1', '1', '未见异常', '2018-12-04', 'admin', '未删', 'admin', '未见异常', '无', '未见异常', '红润', '2018-12-03', '的', '1', '1', '混合', '1', 0, '2018-12-10', '喂养指导', 1, '异常', '检查均为阴性', '未见异常', '通过', '有', 'admin', '2018-12-04', '2018-12-03', '1', '1', '1', '无', '我的', '无', '2018-12-20', '1', '未见异常', '303900035c0632baf1536611bc29e4fc', '未见异常', '1', '111111111111111111', NULL, '1', '无', '男', '稀', 1, '湿疹', '双头胎', '有', '1', NULL, '未见异常', '1', '1', '躯干');
INSERT INTO `newborn_visit` VALUES ('303900035cc28a18d27dc53aa024c226', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, '2019-04-26', 'DemoUser', '未删', 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '13015902843', NULL, NULL, 0, NULL, '', 0, NULL, NULL, NULL, NULL, NULL, '', '2019-04-26', NULL, NULL, NULL, '13015902843', '', '萨达萨达', NULL, NULL, NULL, NULL, '303900035cc28a18d27dc53aa024c227', NULL, NULL, '111111111111111111', NULL, NULL, NULL, '男', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for one_eight_month_child
-- ----------------------------
DROP TABLE IF EXISTS `one_eight_month_child`;
CREATE TABLE `one_eight_month_child`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `abdomen` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `anusedea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bregma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bregma_height` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bregma_width` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chest` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `development` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `diarrhea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ear` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eye` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `face_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `follow_date` date NULL DEFAULT NULL,
  `guidance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `head` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hearing` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `height` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hemoglobin` int(11) NULL DEFAULT NULL,
  `insand_dep` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `limb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `medicine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `month` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mouth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `neck` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_date` date NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `out_activities` int(11) NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pneumonia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `referral` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rickets` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rickets_body` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `skin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trauma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `umbilical` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vitamind` int(11) NULL DEFAULT NULL,
  `weight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of one_eight_month_child
-- ----------------------------
INSERT INTO `one_eight_month_child` VALUES ('303900035c0635dbf1536611bc29e4fd', '异常', '未见异常', '闭合', '1', '1', '未见异常', '2018-12-04', 'admin', '未删', '1', '1', 'admin', '异常', '未见异常', '红润', '2018-12-04', '喂养指导', '1', '1', '1', 1, '1', '异常', '中医饮食调养指导', 'admin', '2018-12-04', '满月', '异常', '我的', '有', '2018-12-04', '303900035c0635dbf1536611bc29e4fe', '1', 1, '111111111111111111', '1', '1', '有', '1', '1', '未见异常', '1', NULL, 1, '1');
INSERT INTO `one_eight_month_child` VALUES ('303900035cbfef70d27dc5225016e6f8', NULL, NULL, NULL, NULL, NULL, NULL, '2019-04-24', 'DemoUser', '未删', NULL, NULL, 'DemoUser', NULL, NULL, NULL, '2019-04-24', '', NULL, NULL, NULL, 0, NULL, NULL, '', '', '2019-04-24', '3月龄', NULL, '测试一', NULL, NULL, '303900035cbfef70d27dc5225016e6f9', NULL, 0, '123456789098765', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `one_eight_month_child` VALUES ('303900035cc28a73d27dc53aa024c228', NULL, NULL, NULL, NULL, NULL, NULL, '2019-04-26', 'DemoUser', '未删', NULL, NULL, 'admin', NULL, NULL, NULL, '2019-04-26', '', NULL, NULL, NULL, 0, NULL, NULL, '', '', '2019-04-26', '3月龄', NULL, '萨达萨达', NULL, NULL, '303900035cc28a73d27dc53aa024c229', NULL, 0, '111111111111111111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);

-- ----------------------------
-- Table structure for perinfor
-- ----------------------------
DROP TABLE IF EXISTS `perinfor`;
CREATE TABLE `perinfor`  (
  `file_id` int(20) NULL DEFAULT NULL COMMENT '档案编号',
  `file_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '档案状态',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birth` date NULL DEFAULT NULL COMMENT '生日',
  `belong_comm` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属社区',
  `docu_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件类型',
  `people_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `work_place` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作单位',
  `tphone_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本人电话',
  `phoner_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `phoner_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `local` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现住址',
  `medical_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医保卡号',
  `residence_place` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '常住类型',
  `ethnic` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `local_place` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户口所在地',
  `house_address` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍地址',
  `internal_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内部编号',
  `hyper_num` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '高血压编号',
  `diabetes_num` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '糖尿病编号',
  `sign_num` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签约号',
  `blood_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '血型',
  `educational` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文化程度',
  `occupation` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  `marital_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '婚姻状况',
  `expenses_pay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医疗费用支付方式',
  `drugAllergy_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '药物过敏史',
  `exposure_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '暴露史',
  `past_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '既往病史',
  `Family_History` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家族病史',
  `genetic_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '遗传病史',
  `disability` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有无残疾',
  `Kitchen_exhaust` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厨房排风情况',
  `fuel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '燃料类型',
  `water` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '饮水',
  `toilet` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厕所',
  `livestock_bar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '禽畜栏',
  `creator` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` date NULL DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_date` date NULL DEFAULT NULL COMMENT '修改时间',
  `borther_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `death_date` date NULL DEFAULT NULL,
  `drug_allergy_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `father_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `livestate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mather_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `son_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `o_time1` date NULL DEFAULT NULL,
  `o_time2` date NULL DEFAULT NULL,
  `operation1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_time1` date NULL DEFAULT NULL,
  `t_time2` date NULL DEFAULT NULL,
  `transfusion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `transfusion_time` date NULL DEFAULT NULL,
  `trauma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trauma1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trauma2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `card_number` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pregnant` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `respon_doctor` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `village_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `committees` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inputting_unit` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '个人基本档案' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of perinfor
-- ----------------------------
INSERT INTO `perinfor` VALUES (0, '', 'kk', '', '2018-12-03', '', '', '350125111665975', '', '', '', '', '', NULL, '', '', '', '河北省/秦皇岛市/北戴河区', '', '', '', '', '', '', '', '', '', NULL, '', '', '', '', '', '', '', '', '', '', 'admin', '2018-12-03', '', '2018-12-04', '', NULL, '', '', '存活', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '已删', '303900035c054eda988e1d13105928ee', '1', '否', NULL, NULL, NULL, NULL);
INSERT INTO `perinfor` VALUES (0, '', '柯', '男', '2010-02-02', '', '', '350125199712020362', '1', '1', '1', '1', '1', NULL, '', '', '', '北京市/东城区', '', '', '', '', '', '技工学校', '', '', '', NULL, '', '', '', '', '', '', '', '', '', '', 'admin', '2018-12-04', 'admin', '2018-12-07', '', NULL, '', '', '存活', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '未删', '303900035c0625a8d6005d1f70b53d71', '', '是', NULL, NULL, NULL, NULL);
INSERT INTO `perinfor` VALUES (0, '', 'kk', '女', '2018-12-04', '', '', '350127786678667', '', '', '', '', '', NULL, '', '汉族', '', '北京市/西城区', '', '', '', '', '', '', '', '', '', NULL, '', '', '', '', '', '', '', '', '', '', 'admin', '2018-12-04', '', '2018-12-04', '', NULL, '', '', '存活', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '已删', '303900035c062cfbd6005d1a28b9ef70', '', '是', NULL, NULL, NULL, NULL);
INSERT INTO `perinfor` VALUES (0, '', '柯梦迁', '女', '2010-01-04', '', '', '350121997120203622', 'gg', '1', '1', '1', '1', NULL, '户籍', '汉族', '', '北京市/东城区', '', '', '', '', 'B型', '大学专科和专科学校', '专业技术人员', '丧偶', '全公费', NULL, '化学品', '慢性阻塞性肺疾病', '', '无', '言语残疾', '油烟机', '煤', '经净化过滤的水', '马桶', '单设', 'admin', '2018-12-04', '', '2018-12-04', '冠心病', NULL, '', '糖尿病', '存活', '糖尿病', '有', '冠心病', '2018-12-31', '2018-12-24', '1', '1', NULL, '2018-12-09', '有', '2018-12-31', '有', '1', '1', '未删', '303900035c062f73d6005d1a28b9ef75', '1', '否', '1', '1', '1', '1');
INSERT INTO `perinfor` VALUES (0, '', '萨达萨达', '男', '2018-12-03', '', '', '111111111111111111', '1', '1', '1', '1', '1', NULL, '户籍', '汉族', '', '天津市/河东区', '', '', '', '', 'O型', '大学本科', '国家机关、党群组织、企业、事业单位负责人', '已婚', '城镇居民医疗保险', NULL, '无', '高血压,慢性阻塞性肺疾病', '', '无', '言语残疾', '油烟机', '煤', '经净化过滤的水', '一格或二格粪池式', '单设', 'admin', '2018-12-05', 'admin', '2018-12-05', '糖尿病', NULL, '', '糖尿病', '死亡', '糖尿病', '有', '糖尿病', '2018-12-02', '2018-12-17', '1', '1', '2018-12-02', '2018-12-02', '有', '2018-12-04', '有', '1', '1', '未删', '303900035c06a636f15366068c0adc4c', '1', '是', '1', '1', '1', '1');
INSERT INTO `perinfor` VALUES (0, '', '郑嘉伟', '男', '2019-04-24', '', '', '35030119980216145X', '', '', '', '', '', NULL, '', '', '', '信州区/水南街道办事处/上滩头社区居民委员会', '', '', '', '', '', '', '', '', '', NULL, '', '', '', '', '', '', '', '', '', '', 'DemoUser', '2019-04-24', '', '2019-04-24', '', NULL, '', '', '存活', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '未删', '303900035cbfe452d27dc530101eed80', '', '否', NULL, NULL, NULL, NULL);
INSERT INTO `perinfor` VALUES (0, '', '测试一', '男', '2019-04-20', '', '', '123456789098765', '', '', '', '', '', NULL, '', '', '', '信州区/水南街道办事处/上滩头社区居民委员会', '', '', '', '', '', '', '', '', '', NULL, '', '', '', '', '', '', '', '', '', '', 'DemoUser', '2019-04-24', 'DemoUser', '2019-04-24', '', NULL, '', '', '存活', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '未删', '303900035cbfe551d27dc530101eed81', '', '否', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for perinforfileemigrate
-- ----------------------------
DROP TABLE IF EXISTS `perinforfileemigrate`;
CREATE TABLE `perinforfileemigrate`  (
  `File_id` int(20) NULL DEFAULT NULL COMMENT '档案编号',
  `File_Status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '档案状态',
  `Name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `Sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `Birth` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `Belong_Comm` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属社区',
  `docu_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件类型',
  `people_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `Work_Place` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作单位',
  `TPhone_Num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本人电话',
  `Phoner_Name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `Phoner_Num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `Local` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现住址',
  `Medical_Num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医保卡号',
  `Residence_Place` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '常住类型',
  `Ethnic` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `Local_Place` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户口所在地',
  `House_Address` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍地址',
  `House_Address1` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍迁出地',
  `House_Address2` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍迁入地',
  `Internal_Num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内部编号',
  `Hyper_Num` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '高血压编号',
  `Diabetes_Num` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '糖尿病编号',
  `Sign_Num` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签约号',
  `Blood_Type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '血型',
  `Educational` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文化程度',
  `Occupation` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  `Marital_Status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '婚姻状况',
  `Expenses_Pay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医疗费用支付方式',
  `DrugAllergy_History` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '药物过敏史',
  `Exposure_History` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '暴露史',
  `Past_History` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '既往病史',
  `Family_History` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家族病史',
  `Genetic_History` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '遗传病史',
  `dIsability` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有无残疾',
  `Kitchen_exhaust` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厨房排风情况',
  `fuel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '燃料类型',
  `water` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '饮水',
  `toilet` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厕所',
  `Livestock_Bar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '禽畜栏',
  `creator` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `borther_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `death_date` date NULL DEFAULT NULL,
  `drug_allergy_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `father_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `livestate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mather_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `son_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `o_time1` date NULL DEFAULT NULL,
  `o_time2` date NULL DEFAULT NULL,
  `operation1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_time1` date NULL DEFAULT NULL,
  `t_time2` date NULL DEFAULT NULL,
  `transfusion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `transfusion_time` date NULL DEFAULT NULL,
  `trauma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trauma1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trauma2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `card_number` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `migration_reason` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍迁移原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of perinforfileemigrate
-- ----------------------------
INSERT INTO `perinforfileemigrate` VALUES (0, NULL, NULL, NULL, NULL, NULL, NULL, '350125199712020362', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '北京市/东城区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-12-07 12:44:26', NULL, '2018-12-07 12:45:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '已删', '303900035c09faaa7a6be621d08eba0e', NULL, '350125199712020362');
INSERT INTO `perinforfileemigrate` VALUES (0, NULL, NULL, NULL, NULL, NULL, NULL, '350125199712020362', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '河北省/保定市/清苑县', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-12-07 12:45:00', NULL, '2018-12-07 12:48:12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '已删', '303900035c09facb7a6be621d08eba0f', NULL, '350125199712020362');
INSERT INTO `perinforfileemigrate` VALUES (0, NULL, NULL, NULL, NULL, NULL, NULL, '350125199712020362', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '天津市/河东区', '北京市/东城区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-12-07 12:47:52', NULL, '2018-12-07 12:48:15', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '已删', '303900035c09fb777a6be621d08eba10', NULL, '350125199712020362');

-- ----------------------------
-- Table structure for postpartum_visit
-- ----------------------------
DROP TABLE IF EXISTS `postpartum_visit`;
CREATE TABLE `postpartum_visit`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `blood` int(11) NULL DEFAULT NULL,
  `breast` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `child_birth` date NULL DEFAULT NULL,
  `classify` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guidance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `healthy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `insand_dep` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `leave_hospitale` date NULL DEFAULT NULL,
  `lochia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_date` date NULL DEFAULT NULL,
  `other` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `psychology` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `referral` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `temperature` int(11) NULL DEFAULT NULL,
  `uterus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wound` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of postpartum_visit
-- ----------------------------
INSERT INTO `postpartum_visit` VALUES ('303900035c05ef7ff153661550d3ee1a', 2, '未见异常', '2018-12-04', '未见异常', '2018-12-04', 'admin', '2018-12-04', '1', '个人卫生', '1', '1', '2018-12-04', '异常', 'admin', '2018-12-04', '我', '2018-12-04', '1', '1', '1', '有', 23, '异常', '未见异常', '111111111111111111', '未删', '北京市/东城区');

-- ----------------------------
-- Table structure for postpartum_visit_forty_two
-- ----------------------------
DROP TABLE IF EXISTS `postpartum_visit_forty_two`;
CREATE TABLE `postpartum_visit_forty_two`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `blood` int(11) NULL DEFAULT NULL,
  `breast` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `child_birth` date NULL DEFAULT NULL,
  `classify` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guidance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `handle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `healthy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `insand_dep` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `leave_hospitale` date NULL DEFAULT NULL,
  `lochia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `psychology` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `temperature` int(11) NULL DEFAULT NULL,
  `uterus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wound` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of postpartum_visit_forty_two
-- ----------------------------
INSERT INTO `postpartum_visit_forty_two` VALUES ('303900035c05f077f153661550d3ee1b', 1, '未见异常', '2018-12-04', '未见异常', '2018-12-04', 'admin', '2018-12-04', '1', '产妇营养,婴儿喂养,性保健和避孕,心理健康', '', '1', '1', '2018-12-04', '未见异常', 'admin', '2018-12-04', '我', '1', '1', '1', 222, '异常', '未见异常', '北京市/东城区', '未删', '111111111111111111');

-- ----------------------------
-- Table structure for resident_management
-- ----------------------------
DROP TABLE IF EXISTS `resident_management`;
CREATE TABLE `resident_management`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ethnic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `electronic_record` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `active_files` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crowd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `respon_doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doc_date` date NULL DEFAULT NULL,
  `doc_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for residents_statistical
-- ----------------------------
DROP TABLE IF EXISTS `residents_statistical`;
CREATE TABLE `residents_statistical`  (
  `id` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `modifier` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year` int(11) NULL DEFAULT NULL,
  `permanent_population` int(11) NULL DEFAULT NULL,
  `number_setters` int(11) NULL DEFAULT NULL,
  `health_build_rate` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `electronic_health` int(11) NULL DEFAULT NULL,
  `electronic_rate` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `health_dynamic` int(11) NULL DEFAULT NULL,
  `health_utilization` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `new_files` int(11) NULL DEFAULT NULL,
  `deaths` int(11) NULL DEFAULT NULL,
  `floating_population` int(11) NULL DEFAULT NULL,
  `delete_state` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of residents_statistical
-- ----------------------------
INSERT INTO `residents_statistical` VALUES ('303900035c052bb7f153661cb40453fc', NULL, '2018-12-07', NULL, '2018-12-03', '北京市/东城区', 2018, 2, 2, '100.00%', 3, '150.00%', NULL, NULL, 2, 1, NULL, NULL);
INSERT INTO `residents_statistical` VALUES ('303900035c052d9af153661cb404540a', NULL, '2018-12-03', NULL, '2018-12-03', '河北省/秦皇岛市/北戴河区', 2018, 1, 1, '100.00%', 1, '100.00%', NULL, NULL, 1, 0, NULL, NULL);
INSERT INTO `residents_statistical` VALUES ('303900035c05f469f153660f00869777', NULL, '2018-12-04', NULL, '2018-12-04', '北京市/崇文区', 2018, 2, 2, '100.00%', 4, '133.33%', NULL, NULL, 2, 0, NULL, NULL);

-- ----------------------------
-- Table structure for schedule_two_five_prenatal_diagnosis
-- ----------------------------
DROP TABLE IF EXISTS `schedule_two_five_prenatal_diagnosis`;
CREATE TABLE `schedule_two_five_prenatal_diagnosis`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `abdominal` int(11) NULL DEFAULT NULL,
  `blood` int(11) NULL DEFAULT NULL,
  `classify` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fetal_position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fhr` int(11) NULL DEFAULT NULL,
  `fundus_height` int(11) NULL DEFAULT NULL,
  `gestational_weeks` int(11) NULL DEFAULT NULL,
  `guidance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hemoglobin` int(11) NULL DEFAULT NULL,
  `insand_dep` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `main_suit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_date` date NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other_sassist` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `referral` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `urine_protein` int(11) NULL DEFAULT NULL,
  `weight` int(11) NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guidances` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of schedule_two_five_prenatal_diagnosis
-- ----------------------------
INSERT INTO `schedule_two_five_prenatal_diagnosis` VALUES ('303900035c05ed01f153661550d3ee19', 1, 2, '未见异常', '2018-12-04', 'admin', '2018-12-11', '1', '222', 12, 1, 2, '心理', 21, '1', '2', 'admin', '2018-12-04', '我', '2018-12-10', '第二次', '肝功能', '111111111111111111', '1', '无', 21, 1, '北京市/东城区', '失访', '未删', '');

-- ----------------------------
-- Table structure for self_care_ability
-- ----------------------------
DROP TABLE IF EXISTS `self_care_ability`;
CREATE TABLE `self_care_ability`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `workid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `systemid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `form_date` date NULL DEFAULT NULL,
  `activity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dressing` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `meal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `toilet` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total_score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `washing` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of self_care_ability
-- ----------------------------
INSERT INTO `self_care_ability` VALUES ('303900035c056c9ff153661cb404541b', '111111111111111111', '21', '', '北京市/东城区', '我', '2018-12-04', '中度依赖(5)分', '轻度依赖(0)分', '轻度依赖(0)分', '2018-12-04', 'admin', 'admin', '2018-12-04', '轻度依赖(1)分', '13', '不能自理(7)分', '未删');

-- ----------------------------
-- Table structure for severe_mental_follow
-- ----------------------------
DROP TABLE IF EXISTS `severe_mental_follow`;
CREATE TABLE `severe_mental_follow`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `work_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `foll_date` date NULL DEFAULT NULL,
  `form_follow` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lost_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date_death` date NULL DEFAULT NULL,
  `cause_death` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `risk_assess` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `current_symptoms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `self_know_ledge` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sleep_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `diet_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pel_life_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `house_work` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pro_and_work` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ability_learn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `social_commu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `danger_beha` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `two_lock_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `two_in_hosp_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `out_hospital_date` date NULL DEFAULT NULL,
  `labor_examin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `med_compliance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bad_drug_react` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `treat_effect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `if_referral` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `referral_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ref_to_depart` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_drug_condia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_usagea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_measurementa` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_drug_condib` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_usageb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_measurementb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_drug_condic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_usagec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_measurementc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guide_use_druga` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guide_usagea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guide_measurementa` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guide_use_drugb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guide_usageb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guide_measurementb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guide_use_drugc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guide_usagec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guide_measurementc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reha_measure` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `this_classifi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_date` date NULL DEFAULT NULL,
  `doc_sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `creater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of severe_mental_follow
-- ----------------------------
INSERT INTO `severe_mental_follow` VALUES ('303900035c05f1a4f153660f00869770', '303900035c05f1a4f153660f00869771', '我', '111111111111111111', '', '2018-12-03', '门诊', '迁居他地', '2018-12-01', '自杀', '2级', '猜疑,自语自笑', '1 自知力完全', '3 较差', '1 良好', '3 较差', '1 良好', '2 一般', '3 较差', '1 良好', '肇祸', '无关锁', '目前正在住院', '2018-12-03', '1 无', '2 间断用药', '1 无', '痊愈', '否', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '职业训练', '不稳定', '2018-12-01', '1', '未删', 'admin', '2018-12-04', 'admin', '2018-12-04', NULL);

-- ----------------------------
-- Table structure for severe_mental_supplement
-- ----------------------------
DROP TABLE IF EXISTS `severe_mental_supplement`;
CREATE TABLE `severe_mental_supplement`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `work_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guardian_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rela_patients` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guardian_addre` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guard_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commit_person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commit_person_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `employ_situat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inf_consent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `signatory` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `signature_date` date NULL DEFAULT NULL,
  `first_onset_time` date NULL DEFAULT NULL,
  `past_symptoms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `past_locking` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `out_service` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `first_antipsy_treat` date NULL DEFAULT NULL,
  `in_hospital` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `diagnosis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hospital_dia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `confi_date` date NULL DEFAULT NULL,
  `last_treat_eff` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `danger_behav` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state_economy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `specia_opinion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date_fill` date NULL DEFAULT NULL,
  `doc_sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `living_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `management` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of severe_mental_supplement
-- ----------------------------
INSERT INTO `severe_mental_supplement` VALUES ('303900035c0557b8f153661cb4045414', '303900035c0557b8f153661cb4045415', '我', '111111111111111111', '北京市/东城区', '我', '1', '1', '1', '1', '1', '农村', '在岗工人', '同意参加管理', '1', '2018-12-03', '2018-12-02', '交流困难,悲观厌世', '无关锁', '未治疗', '2018-12-03', '1', '1', '1', '2018-12-03', '无变化', '其他危险行为', '非贫困', '1', '2018-12-02', '1', '未删', '死亡', '是', 'admin', '2018-12-04', 'admin', '2018-12-04');

-- ----------------------------
-- Table structure for three_six_year_child
-- ----------------------------
DROP TABLE IF EXISTS `three_six_year_child`;
CREATE TABLE `three_six_year_child`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `abdomen` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bmouth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chest` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `development` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `diarrhea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eye` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `follow_date` date NULL DEFAULT NULL,
  `guidance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hearing` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `height` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hemoglobin` int(11) NULL DEFAULT NULL,
  `insand_dep` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `medicine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `mouth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_date` date NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other_check` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `physical` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pneumonia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `referral` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trauma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weightheight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of three_six_year_child
-- ----------------------------
INSERT INTO `three_six_year_child` VALUES ('303900035c06380bf1536611bc29e501', '未见异常', '3岁', '1', '未见异常', '2018-12-04', 'admin', '未删', '不能按要求等待或轮流', '1', 'admin', '1', '2018-12-04', '喂养指导', '1', '1', 1, '1', '中医饮食调养指导', 'admin', '2018-12-04', '1', '我的', '2018-12-12', '303900035c06380bf1536611bc29e502', '1', '1', '111111111111111111', '低体重', '1', '1', '有', '1', '1', '1');
INSERT INTO `three_six_year_child` VALUES ('303900035c06387af1536611bc29e503', NULL, '3岁', NULL, NULL, '2018-12-04', 'admin', '未删', '', NULL, 'admin', NULL, '2018-12-04', '', NULL, NULL, 0, NULL, '', 'admin', '2018-12-04', NULL, '我的', NULL, '303900035c06387af1536611bc29e504', NULL, NULL, '111111111111111111', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for total_diabetes
-- ----------------------------
DROP TABLE IF EXISTS `total_diabetes`;
CREATE TABLE `total_diabetes`  (
  `delete_state` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `people_count` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `area` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `year` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `area_people` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `peoples` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `year_people` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mange_people` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mange_rate` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `near_people` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `control_rate` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `add_people` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `die_people` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `creator` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_date` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modifier` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_date` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '2型糖尿病统计' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of total_diabetes
-- ----------------------------
INSERT INTO `total_diabetes` VALUES (NULL, '1', '河北省/秦皇岛市/青龙满族自治县', '2018', '0', '0', '0', NULL, NULL, NULL, NULL, '0', '1', '303900035c052579f153661cb40453f5', NULL, '2018-12-03 20:45:45.375', NULL, '2018-12-03 20:45:45.375');
INSERT INTO `total_diabetes` VALUES (NULL, '1', '北京市/东城区', '2018', '0', '0', '0', NULL, NULL, NULL, NULL, '0', '1', '303900035c052bb7f153661cb40453fd', NULL, '2018-12-03 21:12:23.228', NULL, '2018-12-03 21:12:23.228');
INSERT INTO `total_diabetes` VALUES (NULL, '1', '河北省/秦皇岛市/北戴河区', '2018', '0', '0', '0', NULL, NULL, NULL, NULL, '0', '0', '303900035c052d9af153661cb4045408', NULL, '2018-12-03 21:20:26.733', NULL, '2018-12-03 21:20:26.733');
INSERT INTO `total_diabetes` VALUES (NULL, '1', '北京市/崇文区', '2018', '0', '0', '0', NULL, NULL, NULL, NULL, '0', '0', '303900035c05f469f153660f00869775', NULL, '2018-12-04 11:28:41.821', NULL, '2018-12-04 11:28:41.821');
INSERT INTO `total_diabetes` VALUES (NULL, '1', '天津市/河东区', '2018', '0', '0', '0', NULL, NULL, NULL, NULL, '0', '1', '303900035c06a637f15366068c0adc4e', NULL, '2018-12-05 00:07:19.135', NULL, '2018-12-05 00:07:19.135');

-- ----------------------------
-- Table structure for transtreat
-- ----------------------------
DROP TABLE IF EXISTS `transtreat`;
CREATE TABLE `transtreat`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `facility_and_depart` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `illness_digest` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `prediagnosis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `referral_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trans_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trans_time` datetime(0) NULL DEFAULT NULL,
  `trans_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transtreatlog
-- ----------------------------
DROP TABLE IF EXISTS `transtreatlog`;
CREATE TABLE `transtreatlog`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `facility_and_depart` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `illness_digest` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `prediagnosis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `referral_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trans_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trans_time` datetime(0) NULL DEFAULT NULL,
  `trans_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transtreatset
-- ----------------------------
DROP TABLE IF EXISTS `transtreatset`;
CREATE TABLE `transtreatset`  (
  `id` int(11) NOT NULL,
  `message_set` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `charge_people` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人姓名',
  `contact` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `referral_information` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转诊报到处信息',
  `trans_apartment_num` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '允许转入的门诊科室数量',
  `trans_inspect_num` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '允许转入的检查项目数量',
  `trans_hospitalization_num` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '允许转入的住院资源数量',
  `trans_recovery` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否对合作机构开放转康复合作',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tuberculosishealth
-- ----------------------------
DROP TABLE IF EXISTS `tuberculosishealth`;
CREATE TABLE `tuberculosishealth`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `modifier` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `area` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `year` int(11) NULL DEFAULT NULL,
  `tu_number` int(11) NULL DEFAULT NULL,
  `tum_number` int(11) NULL DEFAULT NULL,
  `tre_number` int(11) NULL DEFAULT NULL,
  `rule_number` int(11) NULL DEFAULT NULL,
  `add_number` int(11) NULL DEFAULT NULL,
  `out_number` int(11) NULL DEFAULT NULL,
  `tum_rate` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `rule_rate` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tuberculosishealth
-- ----------------------------
INSERT INTO `tuberculosishealth` VALUES ('303900035c052bb7f153661cb40453fa', NULL, '2018-12-07', NULL, '2018-12-03', '北京市/东城区', 2018, 5, 1, 1, 1, 6, 4, '20.00%', '100.00%');
INSERT INTO `tuberculosishealth` VALUES ('303900035c068adcf1536624b0d0ae78', NULL, '2018-12-04', NULL, '2018-12-04', '北京市/崇文区', 2018, 2, 0, 0, 0, 2, 0, '0.00%', '0%');

-- ----------------------------
-- Table structure for twelve_thsix_eight_month_child
-- ----------------------------
DROP TABLE IF EXISTS `twelve_thsix_eight_month_child`;
CREATE TABLE `twelve_thsix_eight_month_child`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `abdomen` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bmouth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bregma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bregma_height` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bregma_width` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chest` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `delete_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `development` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `diarrhea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ear` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eye` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `face_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `follow_date` date NULL DEFAULT NULL,
  `gait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guidance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hearing` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `height` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hemoglobin` int(11) NULL DEFAULT NULL,
  `insand_dep` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `limb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `medicine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `month` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mouth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_date` date NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `out_activities` int(11) NULL DEFAULT NULL,
  `people_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pneumonia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `referral` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rickets_body` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `skin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trauma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vitamind` int(11) NULL DEFAULT NULL,
  `weight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of twelve_thsix_eight_month_child
-- ----------------------------
INSERT INTO `twelve_thsix_eight_month_child` VALUES ('303900035c0636f7f1536611bc29e4ff', '未见异常', '12', '闭合', '12', '12', '未见异常', '2018-12-04', '未删', '呼唤名字无反应', '21', 'admin', '未见异常', '未见异常', '红润', '2018-12-04', '21', '喂养指导', '12', '12', 12, '21', '未见异常', '中医饮食调养指导', 'admin', '2018-12-04', '18月龄', '12', '我的', '2018-12-18', '303900035c0636f7f1536611bc29e500', '12', 12, '111111111111111111', '21', '21', '无', '手足镯', '未见异常', '21', 12, '12', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
