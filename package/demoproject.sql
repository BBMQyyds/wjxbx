/*
 Navicat Premium Data Transfer

 Source Server         : my
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : questionnaire

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 09/27/2020 22:47:21 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `project_info`
-- ----------------------------
DROP TABLE IF EXISTS `project_info`;
CREATE TABLE `project_info` (
  `id` varchar(50) NOT NULL COMMENT '项目表主键',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id（没有用）',
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `project_content` text COMMENT '项目说明',
  `created_by` char(32) DEFAULT NULL COMMENT '创建人',
  `creation_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_updated_by` char(32) DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `project_info`
-- ----------------------------
BEGIN;
INSERT INTO `project_info` VALUES ('283dcf241cf245aea824dc10bbb3d680', '8ceeee2995f3459ba1955f85245dc7a5', '第一个项目', '第一个项目描述 问问', 'admin', '2020-09-23 20:27:42', 'admin', '2020-09-23 20:27:59'), ('4cd6ccb65c894eafaa70b12330f8c2f8', '8ceeee2995f3459ba1955f85245dc7a5', '第一个项目', '第一个项目 的方法v', 'admin', '2020-09-27 16:23:20', 'admin', '2020-09-27 16:23:20');
COMMIT;

-- ----------------------------
--  Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` varchar(50) NOT NULL COMMENT '用户表主键',
  `username` varchar(10) DEFAULT NULL COMMENT '用户名',
  `password` varchar(10) DEFAULT NULL COMMENT '密码',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `stop_time` datetime DEFAULT NULL COMMENT '截止时间（时间戳）',
  `status` varchar(2) DEFAULT NULL COMMENT '是否启用（1启用，0不启用）',
  `created_by` char(32) DEFAULT NULL COMMENT '创建人',
  `creation_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_updated_by` char(32) DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
