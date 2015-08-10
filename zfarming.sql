/*
Navicat MySQL Data Transfer

Source Server         : localhost1
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : zfarming

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-08-06 09:54:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for main_course
-- ----------------------------
DROP TABLE IF EXISTS `main_course`;
CREATE TABLE `main_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courseinfo` varchar(255) DEFAULT NULL,
  `coursename` varchar(100) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `lecturer_id` int(11) DEFAULT NULL COMMENT '教师ID 参考sys_user的主键',
  `deadline` datetime DEFAULT NULL COMMENT '选课截止日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of main_course
-- ----------------------------
INSERT INTO `main_course` VALUES ('1', null, 'datamining', '1', '5', '2015-08-14 12:37:13');
INSERT INTO `main_course` VALUES ('2', null, 'math', '1', '5', '2015-08-22 12:37:06');
INSERT INTO `main_course` VALUES ('3', null, 'english', '1', '6', '2015-08-21 12:37:01');
INSERT INTO `main_course` VALUES ('4', null, 'physics', '1', '7', '2015-08-21 12:37:54');
INSERT INTO `main_course` VALUES ('5', null, 'ir', '1', '8', '2015-08-21 12:38:36');
INSERT INTO `main_course` VALUES ('6', null, 'history', '1', '9', '2015-08-21 12:39:26');
INSERT INTO `main_course` VALUES ('7', null, 'geography', '1', '9', '2015-08-14 12:41:18');
INSERT INTO `main_course` VALUES ('8', null, 'math', '6', '12', '2015-08-21 12:45:32');
INSERT INTO `main_course` VALUES ('9', null, 'english', '6', '12', '2015-08-20 12:45:29');
INSERT INTO `main_course` VALUES ('10', null, 'physics', '6', '13', '2015-08-20 12:45:25');
INSERT INTO `main_course` VALUES ('11', null, 'ir', '6', '13', '2015-08-21 12:45:20');
INSERT INTO `main_course` VALUES ('12', null, 'history', '6', '13', '2015-08-27 12:45:16');
INSERT INTO `main_course` VALUES ('13', 'us art', 'art', '1', '5', '2015-10-09 12:39:26');
INSERT INTO `main_course` VALUES ('14', 'ShangZhiTianWen', 'tianwen', '1', '5', '2015-10-09 12:39:26');

-- ----------------------------
-- Table structure for main_department
-- ----------------------------
DROP TABLE IF EXISTS `main_department`;
CREATE TABLE `main_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departmentname` varchar(100) DEFAULT NULL,
  `university_id` int(11) DEFAULT NULL,
  `departmentadmin_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of main_department
-- ----------------------------
INSERT INTO `main_department` VALUES ('1', 'computerScience', '1', '3');
INSERT INTO `main_department` VALUES ('2', ' School of Engineering & Electronics', '1', '24');
INSERT INTO `main_department` VALUES ('3', 'Department of Physics', '1', '62');
INSERT INTO `main_department` VALUES ('4', 'Royal Academy Of Arts', '1', '66');
INSERT INTO `main_department` VALUES ('5', 'School of Visual Arts', '1', null);
INSERT INTO `main_department` VALUES ('6', 'computerScience', '2', null);
INSERT INTO `main_department` VALUES ('7', ' School of Engineering & Electronics', '2', null);
INSERT INTO `main_department` VALUES ('8', 'Department of Physics', '2', null);
INSERT INTO `main_department` VALUES ('9', 'Royal Academy Of Arts', '2', null);
INSERT INTO `main_department` VALUES ('10', 'School of Visual Arts', '2', null);
INSERT INTO `main_department` VALUES ('11', 'computerScience', '3', null);
INSERT INTO `main_department` VALUES ('12', ' School of Engineering & Electronics', '3', null);
INSERT INTO `main_department` VALUES ('13', 'Department of Physics', '3', null);
INSERT INTO `main_department` VALUES ('14', 'Royal Academy Of Arts', '3', null);
INSERT INTO `main_department` VALUES ('15', 'School of Visual Arts', '3', null);
INSERT INTO `main_department` VALUES ('16', 'Royal Academy Of Arts', '1', null);
INSERT INTO `main_department` VALUES ('17', 'Mydepartment', '1', null);

-- ----------------------------
-- Table structure for main_student_course
-- ----------------------------
DROP TABLE IF EXISTS `main_student_course`;
CREATE TABLE `main_student_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL COMMENT '学生ID 参考sys_user主键',
  `course_id` int(11) DEFAULT NULL COMMENT '课程ID',
  `select_date` datetime DEFAULT NULL COMMENT '选课时间',
  `score` int(3) DEFAULT NULL COMMENT '成绩',
  `status` int(2) DEFAULT '0' COMMENT '选课状态',
  `excoffice_id` int(11) DEFAULT NULL COMMENT 'exoffice',
  `is_exchange` tinyint(1) DEFAULT '0' COMMENT '是否是交换生',
  `admin_id` int(11) DEFAULT NULL,
  `admin_status` int(1) DEFAULT '0',
  `tutor_id` int(11) DEFAULT NULL,
  `tutor_status` int(1) DEFAULT '0',
  `lecturer_id` int(11) DEFAULT NULL,
  `lecturer_status` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of main_student_course
-- ----------------------------
INSERT INTO `main_student_course` VALUES ('1', '10', '1', '2015-07-26 14:15:09', '1', '0', '1', '0', '3', '1', '4', '1', '5', '1');
INSERT INTO `main_student_course` VALUES ('2', '10', '2', '2015-07-26 14:16:18', null, '0', '1', '0', '3', '1', '4', '1', '5', '1');
INSERT INTO `main_student_course` VALUES ('4', '11', '1', '2015-07-26 14:17:34', null, '0', '1', '1', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('5', '11', '5', '2015-07-26 14:17:52', null, '0', '1', '0', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('6', '11', '5', '2015-07-26 18:54:31', null, '1', '2', '0', '3', '0', '4', '2', '0', '1');
INSERT INTO `main_student_course` VALUES ('7', '11', '3', '2015-07-26 18:54:31', null, '1', '2', '0', '3', '0', '4', '1', '6', '1');
INSERT INTO `main_student_course` VALUES ('8', '11', '4', '2015-07-26 18:55:43', null, '1', '2', '0', '3', '0', '4', '1', '0', '0');
INSERT INTO `main_student_course` VALUES ('9', '11', '2', '2015-07-26 18:55:43', null, '1', '2', '0', '3', '1', '4', '0', null, '0');
INSERT INTO `main_student_course` VALUES ('18', '10', '1', '2015-07-28 22:44:04', null, '1', '2', '0', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('19', '10', '7', '2015-07-28 22:44:04', null, '0', '2', '0', '62', '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('20', '10', '6', '2015-07-28 22:44:04', null, '2', '2', '0', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('25', '15', '10', '2015-07-28 22:58:25', null, '1', '1', '1', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('26', '15', '8', '2015-07-28 22:58:25', null, '1', '1', '1', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('27', '15', '3', '2015-07-28 22:59:36', null, '1', '2', '0', '24', '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('28', '15', '2', '2015-07-28 22:59:36', null, '1', '2', '0', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('29', '21', '3', '2015-07-30 16:37:37', null, '1', '2', '0', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('30', '21', '2', '2015-07-30 16:37:37', null, '1', '2', '0', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('31', '21', '11', '2015-07-30 16:37:59', null, '1', '1', '1', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('32', '21', '9', '2015-07-30 16:37:59', null, '1', '1', '1', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('33', '8', '1', '2015-07-30 17:54:13', null, '0', '2', '1', '3', '1', '4', '0', null, '0');
INSERT INTO `main_student_course` VALUES ('34', '8', '1', '2015-07-30 18:07:05', null, '0', '2', '1', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('35', '10', '1', '2015-07-30 18:59:21', null, '0', '2', '1', null, '1', null, '1', null, '1');
INSERT INTO `main_student_course` VALUES ('36', '10', '13', '2015-08-01 15:52:45', null, '1', '2', '1', null, '1', null, '1', null, '1');
INSERT INTO `main_student_course` VALUES ('37', '10', '10', '2015-08-01 22:10:15', null, '1', '1', '1', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('38', '10', '9', '2015-08-01 22:10:15', null, '1', '1', '1', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('39', '10', '8', '2015-08-01 22:10:15', null, '1', '1', '1', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('40', '63', '8', '2015-08-01 22:27:23', null, '1', '1', '0', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('41', '63', '3', '2015-08-01 22:28:06', null, '1', '2', '1', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('43', '64', '3', '2015-08-01 23:32:08', null, '1', '2', '0', '3', '2', '4', '0', null, '0');
INSERT INTO `main_student_course` VALUES ('44', '64', '2', '2015-08-01 23:32:08', null, '1', '2', '0', '24', '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('45', '64', '1', '2015-08-01 23:32:08', null, '1', '2', '0', null, '1', null, '1', null, '1');
INSERT INTO `main_student_course` VALUES ('46', '64', '10', '2015-08-01 23:36:43', null, '1', '1', '1', null, '0', null, '0', null, '0');
INSERT INTO `main_student_course` VALUES ('47', '64', '8', '2015-08-01 23:36:43', null, '1', '1', '1', null, '1', null, '1', null, '1');

-- ----------------------------
-- Table structure for main_university
-- ----------------------------
DROP TABLE IF EXISTS `main_university`;
CREATE TABLE `main_university` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `universityname` varchar(100) DEFAULT NULL,
  `excoffice_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of main_university
-- ----------------------------
INSERT INTO `main_university` VALUES ('1', 'Peking University', '2');
INSERT INTO `main_university` VALUES ('2', 'Agricultural University of He Bei', '1');
INSERT INTO `main_university` VALUES ('3', 'UCAS', '4');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '菜单父ID',
  `menuname` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `type` int(1) DEFAULT '0' COMMENT '菜单类型0:父菜单 1:子菜单',
  `location` varchar(50) DEFAULT NULL COMMENT '跳转路径',
  `status` tinyint(1) DEFAULT NULL COMMENT '菜单状态',
  `seq` int(3) DEFAULT NULL COMMENT '菜单顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', 'HomePage', '0', 'Welcome.htm', '1', '1');
INSERT INTO `sys_menu` VALUES ('2', '0', 'InCome', '0', '', '1', '2');
INSERT INTO `sys_menu` VALUES ('3', '0', 'OutCome', '0', '', '1', '3');
INSERT INTO `sys_menu` VALUES ('4', '2', 'StudentList', '1', 'StudentList.htm?is_exchange=0', '1', '1');
INSERT INTO `sys_menu` VALUES ('5', '2', 'CourseList', '1', 'CourseList.htm', '1', '2');
INSERT INTO `sys_menu` VALUES ('6', '3', 'StudentList', '1', 'StudentList.htm?is_exchange=1', '1', '1');
INSERT INTO `sys_menu` VALUES ('7', '3', 'CourseList', '1', 'CourseListOutcome.htm', '1', '2');
INSERT INTO `sys_menu` VALUES ('8', '2', 'ModuleChoise', '1', 'AddNewCourses.htm', '1', '3');
INSERT INTO `sys_menu` VALUES ('9', '3', 'ModuleChoise', '1', 'AddNewCoursesOutcome.htm', '1', '3');
INSERT INTO `sys_menu` VALUES ('10', '1', 'HomePage', '1', 'Welcome.htm', '1', '1');
INSERT INTO `sys_menu` VALUES ('11', '2', 'DepartmentList', '1', 'DepartmentList.htm', '1', '3');
INSERT INTO `sys_menu` VALUES ('13', '2', 'TeachingList', '1', 'TeachingList.htm', '1', '4');
INSERT INTO `sys_menu` VALUES ('14', '2', 'LecturerList', '1', 'LecturerList.htm', '1', '5');
INSERT INTO `sys_menu` VALUES ('15', '2', 'TutorList', '1', 'TutorList.htm', '1', '6');
INSERT INTO `sys_menu` VALUES ('16', '2', 'CourseSelectionForm', '1', 'CourseSelectionForm.htm', '1', '4');
INSERT INTO `sys_menu` VALUES ('17', '3', 'CourseSelectionForm', '1', 'CourseSelectionFormOutcome.htm', '1', '4');
INSERT INTO `sys_menu` VALUES ('18', '1', 'EditProfile', '1', 'EditProfile.htm', '1', '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `status` tinyint(1) DEFAULT NULL COMMENT '角色状态',
  `remark` varchar(50) DEFAULT NULL COMMENT '角色描述',
  `level` int(3) DEFAULT NULL COMMENT '角色权重',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '1', 'Administrator', '1');
INSERT INTO `sys_role` VALUES ('2', 'student', '1', 'Students', '2');
INSERT INTO `sys_role` VALUES ('3', 'exchange office', '1', 'ExchangeOffice', '3');
INSERT INTO `sys_role` VALUES ('4', 'department admin', '1', 'DepartmentAdmin', '4');
INSERT INTO `sys_role` VALUES ('5', 'department tutor', '1', 'DepartmentTutor', '5');
INSERT INTO `sys_role` VALUES ('6', 'department lecturer', '1', 'DepartmentLecturer', '6');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  `status` tinyint(1) DEFAULT NULL COMMENT '记录状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4', '1');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '5', '1');
INSERT INTO `sys_role_menu` VALUES ('6', '2', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('7', '2', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('8', '2', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('9', '2', '5', '1');
INSERT INTO `sys_role_menu` VALUES ('10', '2', '7', '1');
INSERT INTO `sys_role_menu` VALUES ('11', '2', '8', '1');
INSERT INTO `sys_role_menu` VALUES ('12', '2', '9', '1');
INSERT INTO `sys_role_menu` VALUES ('13', '2', '10', '1');
INSERT INTO `sys_role_menu` VALUES ('14', '3', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('15', '3', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('16', '3', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('17', '3', '4', '1');
INSERT INTO `sys_role_menu` VALUES ('18', '3', '6', '1');
INSERT INTO `sys_role_menu` VALUES ('19', '3', '11', '1');
INSERT INTO `sys_role_menu` VALUES ('20', '4', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('21', '4', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('22', '4', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('23', '4', '4', '1');
INSERT INTO `sys_role_menu` VALUES ('24', '4', '6', '1');
INSERT INTO `sys_role_menu` VALUES ('25', '5', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('26', '5', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('27', '5', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('28', '5', '4', '1');
INSERT INTO `sys_role_menu` VALUES ('29', '5', '6', '1');
INSERT INTO `sys_role_menu` VALUES ('30', '6', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('31', '6', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('32', '6', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('33', '6', '4', '1');
INSERT INTO `sys_role_menu` VALUES ('34', '6', '6', '1');
INSERT INTO `sys_role_menu` VALUES ('35', '6', '13', '1');
INSERT INTO `sys_role_menu` VALUES ('36', '5', '14', '1');
INSERT INTO `sys_role_menu` VALUES ('37', '4', '15', '1');
INSERT INTO `sys_role_menu` VALUES ('38', '2', '16', '1');
INSERT INTO `sys_role_menu` VALUES ('39', '2', '17', '1');
INSERT INTO `sys_role_menu` VALUES ('40', '2', '18', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(50) NOT NULL COMMENT '用户邮箱',
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `nationality` varchar(50) DEFAULT NULL,
  `homeuniversity` varchar(50) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL COMMENT '用户角色',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IX_a` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '123', '0', null, '1231@123.com', 'w', 'l', null, null, null, '1');
INSERT INTO `sys_user` VALUES ('2', 'exc', '123', '0', null, '1232@123.com', 'w', 'l', null, null, '1', '3');
INSERT INTO `sys_user` VALUES ('3', 'dep', '123', '0', '1990-01-01', '1233@123.com', 'f5sr', 'l234e', '', null, '1', '4');
INSERT INTO `sys_user` VALUES ('4', 'deptutor', '123', '0', '2001-01-01', '1234@123.com', '', 'lsssxxx', 'sss', null, '1', '5');
INSERT INTO `sys_user` VALUES ('5', 'deplec1', '123', '0', null, '1235@123.com', 'w', 'l', null, null, '1', '6');
INSERT INTO `sys_user` VALUES ('6', 'deplec2', '123', '0', null, '1236@123.com', 'w', 'l', null, null, '1', '6');
INSERT INTO `sys_user` VALUES ('7', 'deplec3', '123', '0', null, '1237@123.com', 'w', 'l', null, null, '1', '6');
INSERT INTO `sys_user` VALUES ('8', 'deplec4', '123', '0', null, '1238@123c.om', 'w', 'l', null, null, '1', '6');
INSERT INTO `sys_user` VALUES ('9', 'deplec5', '123', '0', '1990-01-01', '1239@123.com', '', 'l', 'xxx000', '', '1', '6');
INSERT INTO `sys_user` VALUES ('10', '123', '123', '1', null, '12310@123.com', '', 'wangliepng', 'zhongguo ', 'bh', '1', '2');
INSERT INTO `sys_user` VALUES ('11', 'stu2', '123', '0', null, '12311@123.com', 'w', 'l', null, null, '8', '2');
INSERT INTO `sys_user` VALUES ('12', 'depleca1', '123', '0', null, '12312@123.com', 'w', 'l', null, null, '6', '6');
INSERT INTO `sys_user` VALUES ('13', 'depleca2', '123', '0', null, '12313@123.com', 'w', 'l', null, null, '6', '6');
INSERT INTO `sys_user` VALUES ('14', 'stu3', '123', '1', null, '12314@123.com', 'a', 'a', null, null, '10', '2');
INSERT INTO `sys_user` VALUES ('15', 'stu4', '123', '1', null, '12315@123.com', '1', '1', null, null, '1', '2');
INSERT INTO `sys_user` VALUES ('16', 'pengwang', '123456', '1', null, 'vercold1@msn.com', 'peng', 'wang', null, null, '8', '2');
INSERT INTO `sys_user` VALUES ('17', 'vercold', '123456', '1', null, 'vercold2@msn.com', 'peng', 'wang', null, null, '1', '2');
INSERT INTO `sys_user` VALUES ('18', 'peng', '123456', '1', null, 'vercold3@msn.com', 'peng', 'wang', null, null, '4', '2');
INSERT INTO `sys_user` VALUES ('21', '12310@123.com', '123', '0', '2015-07-16', 'wlp@wlp.com', 'a', 'a', 'a', 'a', '1', '2');
INSERT INTO `sys_user` VALUES ('22', 'lect01', '123', '1', '1990-01-01', 'adsf@asdf.com', 'll', 'ww', 'adsf', 'adf', '1', '6');
INSERT INTO `sys_user` VALUES ('23', 'lect02', '123', '1', '1982-02-09', 'sss@ss.com', 'xxx', 'ooo', 'xxx', null, '1', '6');
INSERT INTO `sys_user` VALUES ('24', 'dep2', '123', '1', null, '279801953@qq.com', '', 'l', null, null, null, '4');
INSERT INTO `sys_user` VALUES ('25', 'stu', '123', '0', null, '213131', '', '', null, null, null, '2');
INSERT INTO `sys_user` VALUES ('26', 'stu', '123', '0', null, '279801953', '', '', null, null, null, '2');
INSERT INTO `sys_user` VALUES ('30', 'stu', '123', '0', null, '123123', '', '', null, null, null, '2');
INSERT INTO `sys_user` VALUES ('62', 'dep3', '123', '0', null, '27980195@qq.com', 'f', 'l', null, null, null, '4');
INSERT INTO `sys_user` VALUES ('63', 'aa@123.com', '123', '0', '2012-12-01', 'wanglipeng123@qwe.com', 'lipeng12', 'wang12', 'zhongguo', 'hebei', '9', '2');
INSERT INTO `sys_user` VALUES ('64', 'xiaolipeng', '123', '1', '2015-08-05', '123@123a.com', '', '123', '132', '123', '1', '2');
INSERT INTO `sys_user` VALUES ('65', 'wanglipeng123@qwe.com', '123', '0', '2015-08-05', '123@qwe.com', '', '', '', '', '1', '2');
INSERT INTO `sys_user` VALUES ('66', '123', '123', '1', null, '2798019@qq.com', '', '123', null, null, null, '4');
INSERT INTO `sys_user` VALUES ('67', '', '', '0', '2015-08-29', 'ziven@163.com', '', 'ziven', 'china', '', '8', '2');
INSERT INTO `sys_user` VALUES ('68', '', '123', '0', null, '123@QQ.COM', '', '', '', '', '16', '2');
INSERT INTO `sys_user` VALUES ('70', 'ziven@163.com', '1', '0', null, 'ziven@163.comm', '', '', '', '', null, '2');
INSERT INTO `sys_user` VALUES ('71', 'ziven', 'ziven', '0', null, '111111@163.com', '', '', '', '', null, '2');
INSERT INTO `sys_user` VALUES ('72', 'peng', '1234', '0', '1992-08-08', 'vercold@msn.com', 'peng', 'wang', 'china', 'beijing university', '4', '2');
INSERT INTO `sys_user` VALUES ('74', 'bertram', '123', '0', '2015-08-06', 'a1@a.com', 'q', 'q', 'China', 'beijing', '1', '2');
INSERT INTO `sys_user` VALUES ('75', 'Bertram', '123', '0', '2009-03-13', '123@zxf.com', 'Jone', 'Li', 'China', 'XXX', '1', '2');
