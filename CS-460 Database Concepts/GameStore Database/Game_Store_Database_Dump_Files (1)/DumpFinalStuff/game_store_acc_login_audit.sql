-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: game_store
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acc_login_audit`
--

DROP TABLE IF EXISTS `acc_login_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acc_login_audit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `acc_id` int DEFAULT NULL,
  `usernameBefore` varchar(45) DEFAULT NULL,
  `usernameAfter` varchar(45) DEFAULT NULL,
  `passwordBefore` varchar(45) DEFAULT NULL,
  `passwordAfter` varchar(45) DEFAULT NULL,
  `modified_user` varchar(45) DEFAULT NULL,
  `modified_date` timestamp(6) NULL DEFAULT NULL,
  `action` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_login_audit`
--

LOCK TABLES `acc_login_audit` WRITE;
/*!40000 ALTER TABLE `acc_login_audit` DISABLE KEYS */;
INSERT INTO `acc_login_audit` VALUES (4,106,NULL,'steinfield',NULL,'yes','root@localhost','2023-11-30 16:43:03.000000','I'),(5,107,NULL,'s;lkajf;safdas',NULL,'GA','root@localhost','2023-12-03 19:32:10.000000','I'),(6,107,'s;lkajf;safdas','s;lkajf;safdas','GA','GA','root@localhost','2023-12-03 19:32:13.000000','U'),(7,108,NULL,'kd;jfa',NULL,'u','root@localhost','2023-12-03 19:33:38.000000','I'),(8,109,NULL,'Heyo',NULL,'Aiyo','root@localhost','2023-12-03 20:32:05.000000','I'),(9,108,'kd;jfa',NULL,'u',NULL,'root@localhost','2023-12-03 20:33:25.000000','D'),(10,107,'s;lkajf;safdas',NULL,'GA',NULL,'root@localhost','2023-12-03 20:33:25.000000','D'),(11,106,'steinfield',NULL,'yes',NULL,'root@localhost','2023-12-03 20:33:25.000000','D'),(12,110,NULL,'ask;ldnfa;sdf',NULL,'sk;ldfajsf','root@localhost','2023-12-03 21:55:31.000000','I'),(13,111,NULL,'vnaksd;fasdf',NULL,'adk;ljfasl;kdf','root@localhost','2023-12-03 22:03:10.000000','I'),(14,111,'vnaksd;fasdf',NULL,'adk;ljfasl;kdf',NULL,'root@localhost','2023-12-03 22:03:41.000000','D'),(15,110,'ask;ldnfa;sdf','veasdkfa;sdfj','sk;ldfajsf','sk;ldfajsf','root@localhost','2023-12-03 22:04:06.000000','U'),(16,112,NULL,'dlksa;fj;laskf',NULL,'cvaks;ljd;fadf','root@localhost','2023-12-03 22:05:10.000000','I'),(17,112,'dlksa;fj;laskf','7ujm8ik,','cvaks;ljd;fadf','cvaks;ljd;fadf','root@localhost','2023-12-03 22:09:31.000000','U'),(18,112,'7ujm8ik,',NULL,'cvaks;ljd;fadf',NULL,'root@localhost','2023-12-03 22:10:01.000000','D');
/*!40000 ALTER TABLE `acc_login_audit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-03 18:26:01
