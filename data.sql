-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 101.43.255.130    Database: wjxbx
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attribution`
--

DROP TABLE IF EXISTS `attribution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attribution` (
  `parent_id` varchar(36) NOT NULL,
  `child_id` varchar(36) NOT NULL,
  `child_type` varchar(8) DEFAULT NULL,
  `content` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`parent_id`,`child_id`),
  KEY `child_id` (`child_id`),
  CONSTRAINT `attribution_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `attribution_ibfk_2` FOREIGN KEY (`parent_id`) REFERENCES `project` (`id`) ON DELETE CASCADE,
  CONSTRAINT `attribution_ibfk_3` FOREIGN KEY (`parent_id`) REFERENCES `questionnaire` (`id`) ON DELETE CASCADE,
  CONSTRAINT `attribution_ibfk_4` FOREIGN KEY (`parent_id`) REFERENCES `file` (`id`) ON DELETE CASCADE,
  CONSTRAINT `attribution_ibfk_5` FOREIGN KEY (`parent_id`) REFERENCES `attribution` (`child_id`) ON DELETE CASCADE,
  CONSTRAINT `attribution_ibfk_6` FOREIGN KEY (`child_id`) REFERENCES `project` (`id`) ON DELETE CASCADE,
  CONSTRAINT `attribution_ibfk_7` FOREIGN KEY (`child_id`) REFERENCES `questionnaire` (`id`) ON DELETE CASCADE,
  CONSTRAINT `attribution_ibfk_8` FOREIGN KEY (`child_id`) REFERENCES `file` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribution`
--

LOCK TABLES `attribution` WRITE;
/*!40000 ALTER TABLE `attribution` DISABLE KEYS */;
/*!40000 ALTER TABLE `attribution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choice_question`
--

DROP TABLE IF EXISTS `choice_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `choice_question` (
  `question_id` varchar(36) NOT NULL,
  `question_content` varchar(255) NOT NULL,
  `question_type` varchar(20) DEFAULT 'single',
  `questionnaire_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `questionnaire_id` (`questionnaire_id`),
  CONSTRAINT `choice_question_ibfk_1` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaire` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choice_question`
--

LOCK TABLES `choice_question` WRITE;
/*!40000 ALTER TABLE `choice_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `choice_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `id` char(36) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `parent_id` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `file_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `file_ibfk_2` FOREIGN KEY (`parent_id`) REFERENCES `file` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` varchar(36) NOT NULL COMMENT '项目表主键',
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户id',
  `project_name` varchar(36) DEFAULT NULL COMMENT '项目名称',
  `project_content` text COMMENT '项目说明',
  `created_by` varchar(36) DEFAULT NULL COMMENT '创建人',
  `creation_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_updated_by` varchar(36) DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `questionnaire_count` int DEFAULT NULL,
  `star` int DEFAULT '0',
  `deleted` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES ('1779cd18-cc34-47b2-a453-419fcff8df3b','c92ec58b-f098-41f9-99f3-9d50a31270ce','风筝审批','风筝审批风筝审批风筝审批风筝审批','c92ec58b-f098-41f9-99f3-9d50a31270ce','2023-06-02 12:19:58','c92ec58b-f098-41f9-99f3-9d50a31270ce','2023-06-02 12:19:58',23,1,0),('196d1afd-dea1-4f2b-8eb1-b431ff26026b','2','3','4','5','2023-06-27 14:36:49','5','2023-06-27 14:36:49',0,0,0),('1fe4cd6c-f595-432a-aca2-bf105613759d','53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f','测试','测试用项目','53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f','2023-06-27 11:20:11','53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f','2023-06-27 11:20:11',0,0,0),('b2afe984-347a-4beb-8d36-5201ef97923e','df53e091-b45a-4e77-9c27-0cd5db82506c','copy','copyy','df53e091-b45a-4e77-9c27-0cd5db82506c','2023-06-24 19:59:29','df53e091-b45a-4e77-9c27-0cd5db82506c','2023-06-24 19:59:29',3,0,0),('cbd00051-42f9-4bba-a5a2-6bb4d1e15c9c','4b278e0a-4b62-4c02-9ad8-930f1c793529','zxc','zxczxc','4b278e0a-4b62-4c02-9ad8-930f1c793529','2023-06-27 17:39:35','4b278e0a-4b62-4c02-9ad8-930f1c793529','2023-06-27 17:39:35',1,0,0),('cf767b56-9ee7-470b-8e66-8a5d890b63d8','2','3','4','5','2023-06-27 16:42:43','5','2023-06-27 16:42:43',0,0,0),('e5aa7386-51b8-4eef-a344-6c3a3a216fe6','2','3','4','5','2023-06-27 12:03:48','5','2023-06-27 12:03:48',0,0,0),('f4c360e7-6060-4e82-b364-411c9c1200bc','53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f','高校满意度','高校满意度调查项目','53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f','2023-06-27 10:53:41','53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f','2023-06-27 16:38:46',2,1,0);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `user_id` varchar(36) DEFAULT NULL,
  `question_id` varchar(36) DEFAULT NULL,
  `question_content` text,
  KEY `fk_user_id` (`user_id`),
  KEY `question_ibfk_1` (`question_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `questionnaire_question` (`question_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES ('************************************',NULL,'{\"questionId\":\"71b276e7-c501-4016-a92b-b9a0f4f7df56\",\"type\":\"填空题\",\"stem\":\"您的姓名 \\u0026______\\u0026 \",\"related\":\"1\",\"star\":0}'),('************************************',NULL,'{\"questionId\":\"23ed235a-a5f6-494d-b485-bbcad51eaa61\",\"type\":\"单选题\",\"stem\":\"您的年级\",\"options\":[\"大一\",\"大二\",\"大三\",\"大四\"],\"star\":0}'),('************************************',NULL,'{\"questionId\":\"b0e8d746-e1fe-4fa9-9a29-3a203cc42d1d\",\"type\":\"单选题\",\"stem\":\"您的性别\",\"options\":[\"男性\",\"女性\",\"其他\"],\"star\":0}'),('************************************',NULL,'{\"questionId\":\"fce243b9-8746-4abe-8e19-cf156c9eccb7\",\"type\":\"简答题\",\"stem\":\"您对本学校有什么建议\",\"related\":\"200\",\"star\":0}'),('53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f',NULL,'{\"questionId\":\"5bc37825-6ae4-40f2-b95d-26596eea02d3\",\"type\":\"单选题\",\"stem\":\"我是谁\",\"options\":[\"梁朝阳\",\"贾丰哲\",\"陈宇\",\"周鹤洋\"],\"star\":1}'),('53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f',NULL,'{\"questionId\":\"a3acfa9c-7470-41ea-aa81-ee2099b60360\",\"type\":\"多选题\",\"stem\":\"您喜欢玩什么游戏\",\"options\":[\"APEX\",\"星露谷物语\",\"永劫无间\",\"王者荣耀\"],\"star\":1}'),('53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f',NULL,'{\"questionId\":\"42bc0677-7031-4687-bb61-1db1c9e687ea\",\"type\":\"填空题\",\"stem\":\"您期望的实训成绩： \\u0026______\\u0026 \",\"related\":\"1\",\"star\":1}'),('53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f',NULL,'{\"questionId\":\"ad2045af-b4ad-4a97-9114-0aa235a19780\",\"type\":\"判断题\",\"stem\":\"期末周复习压力是否很大\",\"format\":\"是/否\",\"related\":\"是\",\"star\":1}'),('53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f',NULL,'{\"questionId\":\"31fba1b2-fa92-406d-86fa-f6b1c8b46ede\",\"type\":\"评分题\",\"stem\":\"给实训课打分，满分100分\",\"format\":\"百分制\",\"related\":\"60\",\"star\":1}'),('53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f',NULL,'{\"questionId\":\"d4f5966b-7bef-4c9d-b6f2-9b8012cf73c2\",\"type\":\"简答题\",\"stem\":\"对实训有什么建议\",\"related\":\"100\",\"star\":1}');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionnaire`
--

DROP TABLE IF EXISTS `questionnaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionnaire` (
  `id` varchar(36) NOT NULL,
  `questionnaire_name` varchar(255) NOT NULL,
  `questionnaire_description` varchar(255) DEFAULT NULL,
  `creation_date` timestamp NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `question_count` int DEFAULT NULL,
  `answer_count` int DEFAULT NULL,
  `project_id` varchar(36) DEFAULT NULL,
  `star` int NOT NULL DEFAULT '0',
  `deleted` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `project_id` (`project_id`),
  CONSTRAINT `questionnaire_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionnaire`
--

LOCK TABLES `questionnaire` WRITE;
/*!40000 ALTER TABLE `questionnaire` DISABLE KEYS */;
INSERT INTO `questionnaire` VALUES ('0bc55176-339b-42ed-adcf-a5b32a8c260a','3','4','2023-06-27 06:36:25','2023-06-27 14:36:25','2023-06-27 14:36:25',0,0,'1779cd18-cc34-47b2-a453-419fcff8df3b',0,0),('0f294dc6-71ea-4a69-b1fe-0a5ebf653cd0','zxczxc','zxczxczxc','2023-06-27 09:39:46',NULL,'2023-06-27 18:01:43',0,0,'cbd00051-42f9-4bba-a5a2-6bb4d1e15c9c',0,0),('1ddf1c08-d30d-4a70-a237-5057900a1768','大连理工大学满意度','大连理工大学满意度调查问卷','2023-06-27 02:54:37','2023-06-27 12:55:24',NULL,7,0,'f4c360e7-6060-4e82-b364-411c9c1200bc',0,0),('21e84f5a-d6d2-4917-91ed-6f45785f72a2','啊实打实','啊实打实的','2023-06-25 19:08:29','2023-06-26 03:12:47',NULL,0,0,'b2afe984-347a-4beb-8d36-5201ef97923e',0,0),('58fe27c4-99b2-4181-9263-a3a4d2aae385','啊实打实大苏打','撒大苏打实打实大苏打','2023-06-24 12:00:50','2023-06-26 02:44:49',NULL,0,0,'b2afe984-347a-4beb-8d36-5201ef97923e',0,0),('8a314fb1-fb86-4553-9b41-c1212c3c6c81','问卷','啊实打实大大撒撒','2023-06-25 06:27:07',NULL,NULL,0,0,'b2afe984-347a-4beb-8d36-5201ef97923e',0,0),('c700e55b-30ea-49a4-9b15-df3dc74957f7','3','4','2023-06-27 03:42:52','2023-06-27 13:43:28',NULL,7,0,'1779cd18-cc34-47b2-a453-419fcff8df3b',0,0),('ecc1d3e6-1b25-4c47-acf3-e65ce8758b86','东北大学满意度','东北大学满意度调查问卷','2023-06-27 02:54:07','2023-06-27 16:31:23',NULL,9,2,'f4c360e7-6060-4e82-b364-411c9c1200bc',1,0),('ee2addd1-d0d2-46e1-8d6f-cce250251faa','3','4','2023-06-27 08:42:30','2023-06-27 16:42:31','2023-06-27 16:42:31',0,0,'1779cd18-cc34-47b2-a453-419fcff8df3b',0,0);
/*!40000 ALTER TABLE `questionnaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionnaire_answer`
--

DROP TABLE IF EXISTS `questionnaire_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionnaire_answer` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `questionnaire_id` varchar(36) DEFAULT NULL,
  `questionnaire_content` text,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `questionnaire_id` (`questionnaire_id`),
  CONSTRAINT `questionnaire_answer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `questionnaire_answer_ibfk_2` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaire` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionnaire_answer`
--

LOCK TABLES `questionnaire_answer` WRITE;
/*!40000 ALTER TABLE `questionnaire_answer` DISABLE KEYS */;
INSERT INTO `questionnaire_answer` VALUES ('************************************','53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f','ecc1d3e6-1b25-4c47-acf3-e65ce8758b86','[{\"questionId\":\"c321b6af-cf7e-4c64-9fce-365faa65e7de\",\"type\":\"填空题\",\"stem\":\"您的姓名： \\u0026______\\u0026 \",\"related\":\"1\",\"answer\":\"[\\\"贾丰哲\\\"]\",\"star\":0},{\"questionId\":\"3a755d55-3c11-4a09-8851-e350263e40cf\",\"type\":\"单选题\",\"stem\":\"您的年级\",\"options\":[\"大一\",\"大二\",\"大三\",\"大四\",\"研究生\"],\"answer\":\"1\",\"star\":0},{\"questionId\":\"238c1024-3378-49a8-950e-ec117a3b6deb\",\"type\":\"多选题\",\"stem\":\"您喜爱的主食\",\"options\":[\"米饭\",\"面条\",\"馒头\",\"大饼\"],\"answer\":\"[0,3]\",\"star\":1},{\"questionId\":\"4a6b7a92-f2d0-454d-a29e-284f4acfcbcc\",\"type\":\"单选题\",\"stem\":\"您一周在学校食堂就餐几次\",\"options\":[\"每天三次\",\"每天一次\",\"一周偶尔\",\"从不\"],\"answer\":\"1\",\"star\":1},{\"questionId\":\"e0655bae-0643-44ca-9a8a-2a86ec106f20\",\"type\":\"判断题\",\"stem\":\"是否对学校伙食满意\",\"format\":\"是/否\",\"related\":\"是\",\"answer\":\"\\\"否\\\"\",\"star\":1},{\"questionId\":\"8200442d-90d7-433b-8777-9ee9c97859c7\",\"type\":\"简答题\",\"stem\":\"对学校食堂有什么建议\",\"related\":\"50\",\"answer\":\"\\\"无\\\"\",\"star\":1},{\"questionId\":\"f19c2c19-0a15-4893-8c8e-b12b2395aff4\",\"type\":\"评分题\",\"stem\":\"为您的学校打分\",\"format\":\"十分制\",\"related\":\"6\",\"answer\":\"\\\"4\\\"\",\"star\":0},{\"questionId\":\"2fe7269e-f4c6-44de-8b02-cfaefdd6d819\",\"type\":\"简答题\",\"stem\":\"您对学校有什么建议\",\"related\":\"50\",\"answer\":\"\\\"无\\\"\",\"star\":1}]'),('9fc239b0-cf93-4931-9a7d-82db371a4df6','53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f','ecc1d3e6-1b25-4c47-acf3-e65ce8758b86','[{\"questionId\":\"c321b6af-cf7e-4c64-9fce-365faa65e7de\",\"type\":\"填空题\",\"stem\":\"您的姓名： \\u0026______\\u0026 \",\"related\":\"1\",\"answer\":\"[\\\"梁朝阳\\\"]\",\"star\":0},{\"questionId\":\"3a755d55-3c11-4a09-8851-e350263e40cf\",\"type\":\"单选题\",\"stem\":\"您的年级\",\"options\":[\"大一\",\"大二\",\"大三\",\"大四\",\"研究生\"],\"answer\":\"1\",\"star\":0},{\"questionId\":\"238c1024-3378-49a8-950e-ec117a3b6deb\",\"type\":\"多选题\",\"stem\":\"您喜爱的主食\",\"options\":[\"米饭\",\"面条\",\"馒头\",\"大饼\"],\"answer\":\"[1,2]\",\"star\":1},{\"questionId\":\"4a6b7a92-f2d0-454d-a29e-284f4acfcbcc\",\"type\":\"单选题\",\"stem\":\"您一周在学校食堂就餐几次\",\"options\":[\"每天三次\",\"每天一次\",\"一周偶尔\",\"从不\"],\"answer\":\"3\",\"star\":1},{\"questionId\":\"e0655bae-0643-44ca-9a8a-2a86ec106f20\",\"type\":\"判断题\",\"stem\":\"是否对学校伙食满意\",\"format\":\"是/否\",\"related\":\"是\",\"answer\":\"\\\"是\\\"\",\"star\":1},{\"questionId\":\"8200442d-90d7-433b-8777-9ee9c97859c7\",\"type\":\"简答题\",\"stem\":\"对学校食堂有什么建议\",\"related\":\"50\",\"answer\":\"\\\"无\\\"\",\"star\":1},{\"questionId\":\"f19c2c19-0a15-4893-8c8e-b12b2395aff4\",\"type\":\"评分题\",\"stem\":\"为您的学校打分\",\"format\":\"十分制\",\"related\":\"6\",\"answer\":\"\\\"9\\\"\",\"star\":0},{\"questionId\":\"2fe7269e-f4c6-44de-8b02-cfaefdd6d819\",\"type\":\"简答题\",\"stem\":\"您对学校有什么建议\",\"related\":\"50\",\"answer\":\"\\\"无\\\"\",\"star\":1}]');
/*!40000 ALTER TABLE `questionnaire_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionnaire_question`
--

DROP TABLE IF EXISTS `questionnaire_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionnaire_question` (
  `question_id` varchar(36) NOT NULL,
  `questionnaire_id` varchar(36) DEFAULT NULL,
  `sequence` decimal(12,0) DEFAULT NULL,
  `question_content` text,
  `star` int DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `questionnaire_id` (`questionnaire_id`),
  CONSTRAINT `questionnaire_question_ibfk_1` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaire` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionnaire_question`
--

LOCK TABLES `questionnaire_question` WRITE;
/*!40000 ALTER TABLE `questionnaire_question` DISABLE KEYS */;
INSERT INTO `questionnaire_question` VALUES ('057ba71a-6d6d-4d3b-b0ec-dfd62f100e6e','c700e55b-30ea-49a4-9b15-df3dc74957f7',5,'{\"questionId\":\"057ba71a-6d6d-4d3b-b0ec-dfd62f100e6e\",\"type\":\"判断题\",\"stem\":\"这是题干\",\"format\":\"是/否\",\"related\":\"是\",\"star\":0}',0),('0f07feaa-75e6-42a8-a946-0e68d8541996','c700e55b-30ea-49a4-9b15-df3dc74957f7',1,'{\"questionId\":\"0f07feaa-75e6-42a8-a946-0e68d8541996\",\"type\":\"单选题\",\"stem\":\"这是题干\",\"options\":[\"选项1\",\"选项2\",\"选项3\",\"选项4\"],\"star\":0}',0),('1','0f294dc6-71ea-4a69-b1fe-0a5ebf653cd0',1,'{\"questionId\":\"2bd08aa3-85d4-4e17-b98e-8fac9e98cdf6\",\"type\":\"单选题\",\"stem\":\"这是题干zxczxc\",\"options\":[\"zxczxc\",\"zxczxc\",\"zxczxc\",\"zxczxc\"],\"star\":0}',0),('133baf19-27ad-426a-b4a2-f54722ddff51','c700e55b-30ea-49a4-9b15-df3dc74957f7',7,'{\"questionId\":\"133baf19-27ad-426a-b4a2-f54722ddff51\",\"type\":\"简答题\",\"stem\":\"这是题干\",\"related\":\"50\",\"star\":0}',0),('205469a6-e6c7-4e8d-b21f-bea783c4b629','1ddf1c08-d30d-4a70-a237-5057900a1768',3,'{\"questionId\":\"205469a6-e6c7-4e8d-b21f-bea783c4b629\",\"type\":\"填空题\",\"stem\":\"这是题干 \\u0026______\\u0026 这是题干\",\"related\":\"1\",\"star\":0}',0),('23ed235a-a5f6-494d-b485-bbcad51eaa61','ecc1d3e6-1b25-4c47-acf3-e65ce8758b86',2,'{\"questionId\":\"23ed235a-a5f6-494d-b485-bbcad51eaa61\",\"type\":\"单选题\",\"stem\":\"您的年级\",\"options\":[\"大一\",\"大二\",\"大三\",\"大四\"],\"star\":0}',0),('31fba1b2-fa92-406d-86fa-f6b1c8b46ede','ecc1d3e6-1b25-4c47-acf3-e65ce8758b86',8,'{\"questionId\":\"31fba1b2-fa92-406d-86fa-f6b1c8b46ede\",\"type\":\"评分题\",\"stem\":\"给实训课打分，满分100分\",\"format\":\"百分制\",\"related\":\"60\",\"star\":0}',0),('417196ec-a56b-4a40-bad6-e25baa6f28d5','1ddf1c08-d30d-4a70-a237-5057900a1768',4,'{\"questionId\":\"417196ec-a56b-4a40-bad6-e25baa6f28d5\",\"type\":\"排序题\",\"stem\":\"这是题干\",\"options\":[\"语句1\",\"语句2\",\"语句3\",\"语句4\"],\"star\":0}',0),('42bc0677-7031-4687-bb61-1db1c9e687ea','ecc1d3e6-1b25-4c47-acf3-e65ce8758b86',6,'{\"questionId\":\"42bc0677-7031-4687-bb61-1db1c9e687ea\",\"type\":\"填空题\",\"stem\":\"您期望的实训成绩： \\u0026______\\u0026 \",\"related\":\"1\",\"star\":0}',0),('52f2ca34-9600-4204-a034-266d3ef4120c','1ddf1c08-d30d-4a70-a237-5057900a1768',1,'{\"questionId\":\"52f2ca34-9600-4204-a034-266d3ef4120c\",\"type\":\"单选题\",\"stem\":\"这是题干\",\"options\":[\"选项1\",\"选项2\",\"选项3\",\"选项4\"],\"star\":0}',0),('5bc37825-6ae4-40f2-b95d-26596eea02d3','ecc1d3e6-1b25-4c47-acf3-e65ce8758b86',4,'{\"questionId\":\"5bc37825-6ae4-40f2-b95d-26596eea02d3\",\"type\":\"单选题\",\"stem\":\"我是谁\",\"options\":[\"梁朝阳\",\"贾丰哲\",\"陈宇\",\"周鹤洋\"],\"star\":0}',0),('6b8116fe-f254-45b6-bff6-ffae415a90f5','c700e55b-30ea-49a4-9b15-df3dc74957f7',4,'{\"questionId\":\"6b8116fe-f254-45b6-bff6-ffae415a90f5\",\"type\":\"排序题\",\"stem\":\"这是题干\",\"options\":[\"语句1\",\"语句2\",\"语句3\"],\"star\":0}',0),('71b276e7-c501-4016-a92b-b9a0f4f7df56','ecc1d3e6-1b25-4c47-acf3-e65ce8758b86',1,'{\"questionId\":\"71b276e7-c501-4016-a92b-b9a0f4f7df56\",\"type\":\"填空题\",\"stem\":\"您的姓名 \\u0026______\\u0026 \",\"related\":\"1\",\"star\":0}',0),('79ad4ed3-c8f2-4c28-9647-d2b8be8f4295','1ddf1c08-d30d-4a70-a237-5057900a1768',2,'{\"questionId\":\"79ad4ed3-c8f2-4c28-9647-d2b8be8f4295\",\"type\":\"多选题\",\"stem\":\"这是题干\",\"options\":[\"选项1\",\"选项2\",\"选项3\",\"选项4\"],\"star\":0}',0),('8a581031-332b-4809-b036-470f17004a6e','c700e55b-30ea-49a4-9b15-df3dc74957f7',3,'{\"questionId\":\"8a581031-332b-4809-b036-470f17004a6e\",\"type\":\"填空题\",\"stem\":\"这是题干 \\u0026______\\u0026 这是题干\",\"related\":\"1\",\"star\":0}',0),('8b301065-05d2-4c20-ba22-8098df50a72d','c700e55b-30ea-49a4-9b15-df3dc74957f7',2,'{\"questionId\":\"8b301065-05d2-4c20-ba22-8098df50a72d\",\"type\":\"多选题\",\"stem\":\"这是题干\",\"options\":[\"选项1\",\"选项2\",\"选项3\",\"选项4\"],\"star\":0}',0),('9b7f1acf-62cc-417d-91df-983b1f92371e','1ddf1c08-d30d-4a70-a237-5057900a1768',7,'{\"questionId\":\"9b7f1acf-62cc-417d-91df-983b1f92371e\",\"type\":\"简答题\",\"stem\":\"这是题干\",\"related\":\"50\",\"star\":0}',0),('a3acfa9c-7470-41ea-aa81-ee2099b60360','ecc1d3e6-1b25-4c47-acf3-e65ce8758b86',5,'{\"questionId\":\"a3acfa9c-7470-41ea-aa81-ee2099b60360\",\"type\":\"多选题\",\"stem\":\"您喜欢玩什么游戏\",\"options\":[\"APEX\",\"星露谷物语\",\"永劫无间\",\"王者荣耀\"],\"star\":0}',0),('ad2045af-b4ad-4a97-9114-0aa235a19780','ecc1d3e6-1b25-4c47-acf3-e65ce8758b86',7,'{\"questionId\":\"ad2045af-b4ad-4a97-9114-0aa235a19780\",\"type\":\"判断题\",\"stem\":\"期末周复习压力是否很大\",\"format\":\"是/否\",\"related\":\"是\",\"star\":0}',0),('b0e8d746-e1fe-4fa9-9a29-3a203cc42d1d','ecc1d3e6-1b25-4c47-acf3-e65ce8758b86',3,'{\"questionId\":\"b0e8d746-e1fe-4fa9-9a29-3a203cc42d1d\",\"type\":\"单选题\",\"stem\":\"您的性别\",\"options\":[\"男性\",\"女性\",\"其他\"],\"star\":0}',0),('b8e4311d-e36e-42f2-9125-cdd469b37a85','c700e55b-30ea-49a4-9b15-df3dc74957f7',6,'{\"questionId\":\"b8e4311d-e36e-42f2-9125-cdd469b37a85\",\"type\":\"评分题\",\"stem\":\"这是题干\",\"format\":\"百分制\",\"related\":\"60\",\"star\":0}',0),('c44a7b84-2098-4a6a-bc10-bc2f3a24ab3a','1ddf1c08-d30d-4a70-a237-5057900a1768',5,'{\"questionId\":\"c44a7b84-2098-4a6a-bc10-bc2f3a24ab3a\",\"type\":\"判断题\",\"stem\":\"这是题干\",\"format\":\"是/否\",\"related\":\"是\",\"star\":0}',0),('d4f5966b-7bef-4c9d-b6f2-9b8012cf73c2','ecc1d3e6-1b25-4c47-acf3-e65ce8758b86',9,'{\"questionId\":\"d4f5966b-7bef-4c9d-b6f2-9b8012cf73c2\",\"type\":\"简答题\",\"stem\":\"对实训有什么建议\",\"related\":\"100\",\"star\":0}',0),('d9a46a64-2e08-4840-90a7-b9707610c887','1ddf1c08-d30d-4a70-a237-5057900a1768',6,'{\"questionId\":\"d9a46a64-2e08-4840-90a7-b9707610c887\",\"type\":\"评分题\",\"stem\":\"这是题干\",\"format\":\"百分制\",\"related\":\"60\",\"star\":0}',0);
/*!40000 ALTER TABLE `questionnaire_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(36) NOT NULL COMMENT '用户表主键',
  `username` varchar(16) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `stop_time` datetime DEFAULT NULL COMMENT '截止时间（时间戳）',
  `status` int DEFAULT NULL COMMENT '是否启用（1启用，0不启用）',
  `created_by` varchar(16) DEFAULT NULL COMMENT '创建人',
  `creation_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_updated_by` char(36) DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('************************************','public','e10adc3949ba59abbe56e057f20f883e','2023-06-24 19:59:08',NULL,1,'public','2023-06-27 11:04:57','public','2023-06-27 11:04:57'),('12430407-27cc-4c7e-b85f-31878b71c941','admin','6f8e32116616139423e0c553d0cf1751','2023-06-27 13:24:58',NULL,1,'admin','2023-06-27 13:24:58','admin','2023-06-27 13:24:58'),('484fb07d-5bca-4f45-8359-7410f1474b10','konodioda','e10adc3949ba59abbe56e057f20f883e','2023-06-27 20:02:07',NULL,1,'konodioda','2023-06-27 20:02:07','konodioda','2023-06-27 20:02:07'),('4b278e0a-4b62-4c02-9ad8-930f1c793529','333','e10adc3949ba59abbe56e057f20f883e','2023-06-27 17:39:18',NULL,1,'333','2023-06-27 17:39:18','333','2023-06-27 17:39:18'),('53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f','卜部美琴','e10adc3949ba59abbe56e057f20f883e','2023-06-02 12:19:54',NULL,1,'卜部美琴','2023-06-02 12:21:17','卜部美琴','2023-06-02 12:21:52'),('897a0e08-5450-42e7-8388-38e81b483ec3','test','e10adc3949ba59abbe56e057f20f883e','2023-06-20 11:17:49',NULL,1,'admin','2023-06-20 11:17:49','123213','2023-06-20 11:17:49'),('90c8d8ec-18ae-4151-b2ff-880d1d51d991','LS','123','2023-06-27 16:42:40',NULL,1,NULL,'2023-06-27 16:42:40',NULL,'2023-06-27 16:42:40'),('c92ec58b-f098-41f9-99f3-9d50a31270ce','风筝追风','e10adc3949ba59abbe56e057f20f883e','2023-06-02 12:19:58',NULL,1,'风筝追风','2023-06-02 12:21:20','风筝追风','2023-06-02 12:21:53');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-27 20:32:46
