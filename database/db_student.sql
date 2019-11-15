/*
 Navicat Premium Data Transfer

 Source Server         : 113.62.127.199
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 113.62.127.199:3306
 Source Schema         : db_student

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 15/11/2019 21:14:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dtproperties
-- ----------------------------
DROP TABLE IF EXISTS `dtproperties`;
CREATE TABLE `dtproperties` (
  `id` int(11) NOT NULL,
  `objectid` int(11) DEFAULT NULL,
  `property` varchar(64) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `uvalue` varchar(255) DEFAULT NULL,
  `lvalue` longblob,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`,`property`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student_table
-- ----------------------------
DROP TABLE IF EXISTS `student_table`;
CREATE TABLE `student_table` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `cardId` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_table
-- ----------------------------
BEGIN;
INSERT INTO `student_table` VALUES ('4028818a0b8d9b9b010b8d9b9f280001', 'tomclus', '200512345', 33);
COMMIT;

-- ----------------------------
-- Table structure for tb_classinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_classinfo`;
CREATE TABLE `tb_classinfo` (
  `classID` varchar(10) NOT NULL,
  `gradeID` varchar(10) DEFAULT NULL,
  `className` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`classID`),
  KEY `FK_TB_CLASS_REFERENCE_TB_GRADE` (`gradeID`),
  CONSTRAINT `FK_TB_CLASS_REFERENCE_TB_GRADE` FOREIGN KEY (`gradeID`) REFERENCES `tb_gradeinfo` (`gradeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_classinfo
-- ----------------------------
BEGIN;
INSERT INTO `tb_classinfo` VALUES ('0101', '01', '一年一班');
INSERT INTO `tb_classinfo` VALUES ('0201', '02', '二年一班');
INSERT INTO `tb_classinfo` VALUES ('0301', '03', '三年一班');
COMMIT;

-- ----------------------------
-- Table structure for tb_examkinds
-- ----------------------------
DROP TABLE IF EXISTS `tb_examkinds`;
CREATE TABLE `tb_examkinds` (
  `kindID` varchar(10) NOT NULL,
  `kindName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`kindID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_examkinds
-- ----------------------------
BEGIN;
INSERT INTO `tb_examkinds` VALUES ('01', '期中考试');
INSERT INTO `tb_examkinds` VALUES ('02', '期末考试');
INSERT INTO `tb_examkinds` VALUES ('03', '月考');
COMMIT;

-- ----------------------------
-- Table structure for tb_gradeinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_gradeinfo`;
CREATE TABLE `tb_gradeinfo` (
  `gradeID` varchar(10) NOT NULL,
  `gradeName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`gradeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_gradeinfo
-- ----------------------------
BEGIN;
INSERT INTO `tb_gradeinfo` VALUES ('01', '一年');
INSERT INTO `tb_gradeinfo` VALUES ('02', '二年');
INSERT INTO `tb_gradeinfo` VALUES ('03', '三年');
COMMIT;

-- ----------------------------
-- Table structure for tb_gradeinfo_sub
-- ----------------------------
DROP TABLE IF EXISTS `tb_gradeinfo_sub`;
CREATE TABLE `tb_gradeinfo_sub` (
  `stuid` varchar(10) NOT NULL,
  `stuname` varchar(50) DEFAULT NULL,
  `kindID` varchar(10) NOT NULL,
  `code` varchar(10) NOT NULL,
  `grade` double DEFAULT NULL,
  `examdate` datetime DEFAULT NULL,
  PRIMARY KEY (`stuid`,`kindID`,`code`),
  KEY `FK_TB_EXAMI_REFERENCE_TB_EXAMK` (`kindID`),
  KEY `FK_TB_EXAMI_REFERENCE_TB_SUBJE` (`code`),
  CONSTRAINT `FK_TB_EXAMI_REFERENCE_TB_EXAMK` FOREIGN KEY (`kindID`) REFERENCES `tb_examkinds` (`kindid`),
  CONSTRAINT `FK_TB_EXAMI_REFERENCE_TB_STUDE` FOREIGN KEY (`stuid`) REFERENCES `tb_studentinfo` (`stuid`),
  CONSTRAINT `FK_TB_EXAMI_REFERENCE_TB_SUBJE` FOREIGN KEY (`code`) REFERENCES `tb_subject` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_gradeinfo_sub
-- ----------------------------
BEGIN;
INSERT INTO `tb_gradeinfo_sub` VALUES ('010101', '明明', '01', '02', 80, '2011-05-18 00:00:00');
INSERT INTO `tb_gradeinfo_sub` VALUES ('010101', '明明', '01', '03', 50, '2011-05-18 00:00:00');
INSERT INTO `tb_gradeinfo_sub` VALUES ('010101', '明明', '01', '04', 50, '2011-05-18 00:00:00');
INSERT INTO `tb_gradeinfo_sub` VALUES ('010101', '明明', '01', '05', 40, '2011-05-18 00:00:00');
INSERT INTO `tb_gradeinfo_sub` VALUES ('010101', '明明', '01', '06', 96, '2011-05-18 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for tb_studentinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_studentinfo`;
CREATE TABLE `tb_studentinfo` (
  `stuid` varchar(10) NOT NULL,
  `classID` varchar(10) DEFAULT NULL,
  `stuname` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `addr` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`stuid`),
  KEY `FK_TB_STUDE_REFERENCE_TB_CLASS` (`classID`),
  CONSTRAINT `FK_TB_STUDE_REFERENCE_TB_CLASS` FOREIGN KEY (`classID`) REFERENCES `tb_classinfo` (`classid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_studentinfo
-- ----------------------------
BEGIN;
INSERT INTO `tb_studentinfo` VALUES ('010101', '0101', '明明', '男', 25, '长春市南关区', '11111111111');
INSERT INTO `tb_studentinfo` VALUES ('020101', '0201', '丽丽', '男', 26, '长春', '22222222');
INSERT INTO `tb_studentinfo` VALUES ('030101', '0301', '平平', '女', 27, '长春', '55');
COMMIT;

-- ----------------------------
-- Table structure for tb_subject
-- ----------------------------
DROP TABLE IF EXISTS `tb_subject`;
CREATE TABLE `tb_subject` (
  `code` varchar(10) NOT NULL,
  `subject` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_subject
-- ----------------------------
BEGIN;
INSERT INTO `tb_subject` VALUES ('02', '数学');
INSERT INTO `tb_subject` VALUES ('03', '英语');
INSERT INTO `tb_subject` VALUES ('04', '物理');
INSERT INTO `tb_subject` VALUES ('05', '语文');
INSERT INTO `tb_subject` VALUES ('06', '化学');
COMMIT;

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher` (
  `teaid` varchar(10) NOT NULL,
  `classID` varchar(10) DEFAULT NULL,
  `teaname` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `knowledge` varchar(20) DEFAULT NULL,
  `knowlevel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`teaid`),
  KEY `FK_TB_TEACH_REFERENCE_TB_CLASS` (`classID`),
  CONSTRAINT `FK_TB_TEACH_REFERENCE_TB_CLASS` FOREIGN KEY (`classID`) REFERENCES `tb_classinfo` (`classid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
BEGIN;
INSERT INTO `tb_teacher` VALUES ('01', '0101', '小华', '男', '高级教师', '高级');
INSERT INTO `tb_teacher` VALUES ('02', '0201', '小李', '女', '高级教师', '中级');
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `userid` varchar(50) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `pass` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES ('00', '之', '000000');
INSERT INTO `tb_user` VALUES ('mr', '明日科技', 'mrsoft');
INSERT INTO `tb_user` VALUES ('明明', '明明', '000000');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
