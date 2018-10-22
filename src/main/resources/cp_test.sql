/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 100212
Source Host           : localhost:3306
Source Database       : cp_test

Target Server Type    : MYSQL
Target Server Version : 100212
File Encoding         : 65001

Date: 2018-10-22 09:55:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for adjacent
-- ----------------------------
DROP TABLE IF EXISTS `adjacent`;
CREATE TABLE `adjacent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `repeat_num` int(11) DEFAULT NULL COMMENT '重复个数',
  `award_num` varchar(200) DEFAULT NULL COMMENT '基础中奖号',
  `period` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11234 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for elevens
-- ----------------------------
DROP TABLE IF EXISTS `elevens`;
CREATE TABLE `elevens` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '原始数据表,用来保存近10天的数据',
  `one` int(11) DEFAULT 0,
  `two` int(11) DEFAULT 0,
  `three` int(11) DEFAULT 0,
  `four` int(11) DEFAULT 0,
  `five` int(11) DEFAULT 0,
  `six` int(11) DEFAULT 0,
  `seven` int(11) DEFAULT 0,
  `eight` int(11) DEFAULT 0,
  `nine` int(11) DEFAULT 0,
  `ten` int(11) DEFAULT 0,
  `eleven` int(11) DEFAULT 0,
  `period` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18481 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for four_group_count
-- ----------------------------
DROP TABLE IF EXISTS `four_group_count`;
CREATE TABLE `four_group_count` (
  `id` int(11) NOT NULL,
  `one_group_percent` varchar(255) DEFAULT NULL,
  `one_group_times` int(11) DEFAULT NULL,
  `one_group_last_period` varchar(255) DEFAULT NULL,
  `one_group_max_times` int(11) DEFAULT NULL,
  `two_group_percent` varchar(255) DEFAULT NULL,
  `two_group_times` int(11) DEFAULT NULL,
  `two_group_last_period` varchar(255) DEFAULT NULL,
  `two_group_max_times` int(11) DEFAULT NULL,
  `three_group_percent` varchar(255) DEFAULT NULL,
  `three_group_times` int(11) DEFAULT NULL,
  `three_group_last_period` varchar(255) DEFAULT NULL,
  `three_group_max_times` int(11) DEFAULT NULL,
  `four_group_percent` varchar(255) DEFAULT NULL,
  `four_group_times` int(11) DEFAULT NULL,
  `four_group_last_period` varchar(255) DEFAULT NULL,
  `four_group_max_times` int(11) DEFAULT NULL,
  `period` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hot_cold_number
-- ----------------------------
DROP TABLE IF EXISTS `hot_cold_number`;
CREATE TABLE `hot_cold_number` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hot_number` varchar(255) DEFAULT NULL,
  `cold_number` varchar(255) DEFAULT NULL,
  `warm_number` varchar(255) DEFAULT NULL,
  `hot_right` int(11) DEFAULT NULL,
  `cold_right` int(11) DEFAULT NULL,
  `warm_right` int(11) DEFAULT NULL,
  `hot_code` varchar(255) DEFAULT NULL,
  `cold_code` varchar(255) DEFAULT NULL,
  `warm_code` varchar(255) DEFAULT NULL,
  `hot_reserve` varchar(255) DEFAULT NULL,
  `warm_reserve` varchar(255) DEFAULT NULL,
  `next_award` varchar(255) DEFAULT NULL,
  `two_number` varchar(255) DEFAULT NULL,
  `another_two` varchar(255) DEFAULT NULL,
  `period` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11739 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for number
-- ----------------------------
DROP TABLE IF EXISTS `number`;
CREATE TABLE `number` (
  `id` int(11) NOT NULL,
  `num` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for number_group
-- ----------------------------
DROP TABLE IF EXISTS `number_group`;
CREATE TABLE `number_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ji_group` varchar(255) DEFAULT NULL,
  `ou_group` varchar(255) DEFAULT NULL,
  `zhi_group` varchar(255) DEFAULT NULL,
  `he_group` varchar(255) DEFAULT NULL,
  `da_group` varchar(255) DEFAULT NULL,
  `xiao_group` varchar(255) DEFAULT NULL,
  `ji_amount` int(11) DEFAULT NULL,
  `ou_amount` int(11) DEFAULT NULL,
  `zhi_amount` int(11) DEFAULT NULL,
  `he_amount` int(11) DEFAULT NULL,
  `da_amount` int(11) DEFAULT NULL,
  `xiao_amount` int(11) DEFAULT NULL,
  `period` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7691 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for repeat
-- ----------------------------
DROP TABLE IF EXISTS `repeat`;
CREATE TABLE `repeat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `repeat_num` varchar(20) NOT NULL,
  `current_num` varchar(50) NOT NULL,
  `next_num` varchar(50) NOT NULL,
  `period` varchar(30) NOT NULL,
  `intersect` varchar(20) DEFAULT '' COMMENT '重复出现的结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for repeat_times
-- ----------------------------
DROP TABLE IF EXISTS `repeat_times`;
CREATE TABLE `repeat_times` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `one_times` int(11) DEFAULT NULL,
  `two_times` int(11) DEFAULT NULL,
  `three_times` int(11) DEFAULT NULL,
  `four_times` int(11) DEFAULT NULL,
  `five_times` int(11) DEFAULT NULL,
  `six_times` int(11) DEFAULT NULL,
  `seven_times` int(11) DEFAULT NULL,
  `eight_times` int(11) DEFAULT NULL,
  `nine_times` int(11) DEFAULT NULL,
  `ten_times` int(11) DEFAULT NULL,
  `period` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26641 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ten_number
-- ----------------------------
DROP TABLE IF EXISTS `ten_number`;
CREATE TABLE `ten_number` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '统计近10天的连续10期彩票的出现的数据情况',
  `one_num` int(11) DEFAULT NULL,
  `two_num` int(11) DEFAULT NULL,
  `three_num` int(11) DEFAULT NULL,
  `four_num` int(11) DEFAULT NULL,
  `five_num` int(11) DEFAULT NULL,
  `six_num` int(11) DEFAULT NULL,
  `seven_num` int(11) DEFAULT NULL,
  `eight_num` int(11) DEFAULT NULL,
  `nine_num` int(11) DEFAULT NULL,
  `ten_num` int(11) DEFAULT NULL,
  `eleven_num` int(11) DEFAULT NULL,
  `sort` varchar(50) DEFAULT NULL,
  `period` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14251 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ten_repeat
-- ----------------------------
DROP TABLE IF EXISTS `ten_repeat`;
CREATE TABLE `ten_repeat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `repeat_num` int(11) DEFAULT NULL COMMENT '重复个数',
  `award_num` varchar(200) DEFAULT NULL COMMENT '基础中奖号',
  `period` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8924 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ten_three_period
-- ----------------------------
DROP TABLE IF EXISTS `ten_three_period`;
CREATE TABLE `ten_three_period` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `one` int(11) DEFAULT NULL,
  `two` int(11) DEFAULT NULL,
  `three` int(11) DEFAULT NULL,
  `four` int(11) DEFAULT NULL,
  `five` int(11) DEFAULT NULL,
  `six` int(11) DEFAULT NULL,
  `seven` int(11) DEFAULT NULL,
  `eight` int(11) DEFAULT NULL,
  `nine` int(11) DEFAULT NULL,
  `ten` int(11) DEFAULT NULL,
  `eleven` int(11) DEFAULT NULL,
  `sort` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ten_times
-- ----------------------------
DROP TABLE IF EXISTS `ten_times`;
CREATE TABLE `ten_times` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '10次的一个统计',
  `one_ten` int(11) DEFAULT 0,
  `two_ten` int(11) DEFAULT 0,
  `three_ten` int(11) DEFAULT 0,
  `four_ten` int(11) DEFAULT 0,
  `five_ten` int(11) DEFAULT 0,
  `six_ten` int(11) DEFAULT 0,
  `seven_ten` int(11) DEFAULT 0,
  `eight_ten` int(11) DEFAULT 0,
  `nine_ten` int(11) DEFAULT 0,
  `ten_ten` int(11) DEFAULT 0,
  `eleven_ten` int(11) DEFAULT 0,
  `period` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15571 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ten_tong_ji
-- ----------------------------
DROP TABLE IF EXISTS `ten_tong_ji`;
CREATE TABLE `ten_tong_ji` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '该表统计10次中重要三次出现的次数(只有一组数据)',
  `one` int(11) DEFAULT NULL,
  `two` int(11) DEFAULT NULL,
  `three` int(11) DEFAULT NULL,
  `four` int(11) DEFAULT NULL,
  `five` int(11) DEFAULT NULL,
  `six` int(11) DEFAULT NULL,
  `seven` int(11) DEFAULT NULL,
  `eight` int(11) DEFAULT NULL,
  `nine` int(11) DEFAULT NULL,
  `ten` int(11) DEFAULT NULL,
  `eleven` int(11) DEFAULT NULL,
  `sort` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1511 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for three_period
-- ----------------------------
DROP TABLE IF EXISTS `three_period`;
CREATE TABLE `three_period` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '该表统计,三次出现的次数',
  `one_num` int(11) DEFAULT NULL,
  `two_num` int(11) DEFAULT NULL,
  `three_num` int(11) DEFAULT NULL,
  `four_num` int(11) DEFAULT NULL,
  `five_num` int(11) DEFAULT NULL,
  `six_num` int(11) DEFAULT NULL,
  `seven_num` int(11) DEFAULT NULL,
  `eight_num` int(11) DEFAULT NULL,
  `nine_num` int(11) DEFAULT NULL,
  `ten_num` int(11) DEFAULT NULL,
  `eleven_num` int(11) DEFAULT NULL,
  `sort_num` varchar(50) DEFAULT NULL,
  `period` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14251 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tong_ji
-- ----------------------------
DROP TABLE IF EXISTS `tong_ji`;
CREATE TABLE `tong_ji` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用来统计累计10次的出现次数(1-11)',
  `one` int(11) unsigned zerofill NOT NULL DEFAULT 00000000000,
  `two` int(11) unsigned zerofill NOT NULL DEFAULT 00000000000,
  `three` int(11) unsigned zerofill NOT NULL DEFAULT 00000000000,
  `four` int(11) NOT NULL DEFAULT 0,
  `five` int(11) unsigned NOT NULL DEFAULT 0,
  `six` int(11) unsigned NOT NULL DEFAULT 0,
  `seven` int(11) unsigned NOT NULL DEFAULT 0,
  `eight` int(11) unsigned NOT NULL DEFAULT 0,
  `nine` int(11) unsigned NOT NULL DEFAULT 0,
  `ten` int(11) unsigned NOT NULL DEFAULT 0,
  `eleven` int(11) unsigned NOT NULL DEFAULT 0,
  `period` varchar(100) NOT NULL DEFAULT '' COMMENT '从大到小排序组成字符串(11位)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1460 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;

-- 20181022----------------------------------------------

-- ----------------------------
-- Table structure for change_numbers 记录发生变化的数据的统计
-- ----------------------------
CREATE TABLE `change_numbers` (
  `id` int(11) NOT NULL,
  `five_times_number` varchar(255) DEFAULT NULL,
  `five_times_happen` varchar(255) DEFAULT NULL,
  `three_times_number` varchar(255) DEFAULT NULL,
  `three_times_happen` varchar(255) DEFAULT NULL,
  `four_times_number` varchar(255) DEFAULT NULL,
  `four_times_happen` varchar(255) DEFAULT NULL,
  `six_times_number` varchar(255) DEFAULT NULL,
  `six_times_happen` varchar(255) DEFAULT NULL,
  `period` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
