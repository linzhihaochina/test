/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : test_db

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 17/04/2020 01:30:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `product_name` varchar(50) NOT NULL DEFAULT '',
  `productor` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_product_detail_index` (`user_id`,`product_name`,`productor`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for test_order
-- ----------------------------
DROP TABLE IF EXISTS `test_order`;
CREATE TABLE `test_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(10) DEFAULT NULL,
  `count` int(10) DEFAULT NULL,
  `money` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for test_order2
-- ----------------------------
DROP TABLE IF EXISTS `test_order2`;
CREATE TABLE `test_order2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(10) DEFAULT NULL,
  `count` int(10) DEFAULT NULL,
  `money` decimal(10,2) DEFAULT NULL,
  `fk_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_test_id` (`fk_id`),
  CONSTRAINT `fk_test_id` FOREIGN KEY (`fk_id`) REFERENCES `test_order2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for test_order3
-- ----------------------------
DROP TABLE IF EXISTS `test_order3`;
CREATE TABLE `test_order3` (
  `id` int(10) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  UNIQUE KEY `uk_id` (`id`,`name`,`age`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name_index` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
