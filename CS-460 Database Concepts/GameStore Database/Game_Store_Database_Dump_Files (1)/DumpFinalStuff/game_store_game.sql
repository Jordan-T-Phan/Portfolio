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
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL,
  `publisher` text NOT NULL,
  `orig_price` double NOT NULL,
  `salePercent` double NOT NULL,
  `userRating` double NOT NULL,
  `downloads` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1,'Zontrax','Quitzon, Rogahn and Bosco',59.67,0.82,1.53,774919),(2,'Viva','Carter and Sons',112.28,0.75,4.86,2943823),(3,'Bitchip','Jones-Schimmel',103.1,0.75,3.8,8459410),(4,'Cardguard','Hills and Sons',54.66,1,1.05,8462849),(5,'Otcom','Kessler-Heaney',188.51,0.8,2.02,1376728),(6,'Cookley','Spencer-Kozey',134.38,0.82,4.82,9425897),(7,'Alpha','Denesik, Kerluke and Jakubowski',107.22,0.95,4.79,5139382),(8,'It','Ritchie-Murazik',15.86,0.9,2.11,1466431),(9,'Daltfresh','Lowe and Sons',6.93,0.71,2.66,303273),(10,'Opela','Hudson-Schultz',74.64,0.81,4.53,5271953),(11,'Biodex','Welch-Block',121.13,0.95,4.97,638506),(12,'Y-Solowarm','Parisian-Jakubowski',111.83,0.9,2.36,191071),(13,'Voltsillam','Doyle-Schuster',139.45,0.92,4.86,9025474),(14,'Aerified','Lynch, Lueilwitz and Weissnat',100.11,0.95,1.99,9427087),(15,'Aerified','Gerlach Inc',128.74,0.99,2.97,7154861),(16,'Matsoft','Armstrong-Cummings',60.96,0.72,1.03,3825034),(17,'Asoka','Weissnat, Beer and Dooley',137.25,0.87,1.19,7460437),(18,'Daltfresh','Quitzon-Ebert',52.67,0.99,4.12,9899424),(19,'Voyatouch','Leuschke Group',95.52,0.76,2.89,2324343),(20,'Viva','Cassin-Abshire',54.63,0.94,1.88,5657192),(21,'Bamity','Roob Group',14.8,0.92,4.04,8390666),(22,'Rank','Bode-Lowe',86.26,0.72,3.71,7881876),(23,'Y-find','Reichel Group',11.48,0.94,4.18,804015),(24,'Tin','Bailey-Stiedemann',44.06,0.91,4.22,434037),(25,'Aerified','Ortiz, Lind and Gislason',156.61,0.9,1.85,1182160),(26,'Veribet','Zieme and Sons',72.94,0.72,3.73,3142410),(27,'Daltfresh','Rice, Skiles and Yost',109.58,0.97,3.83,7128920),(28,'It','Metz-Herzog',199.63,0.92,3.85,1394661),(29,'Fix San','Stiedemann-VonRueden',192.85,0.89,2.21,7687718),(30,'Stronghold','Reinger-Langworth',116.04,0.78,2.18,3852935),(31,'Zathin','Kemmer Inc',90.06,0.79,4.15,8958151),(32,'Daltfresh','Langworth, Feest and Stanton',161.62,0.8,1.43,9614702),(33,'Transcof','Botsford-Crooks',56.45,0.91,4.29,82916),(34,'Prodder','Metz and Sons',42.86,0.95,1.89,9258341),(35,'Konklab','Hirthe, Littel and Marquardt',87.43,0.96,1.5,4803190),(36,'Konklux','Sipes-Cruickshank',100.37,0.72,3.48,1433462),(37,'Fintone','Koelpin, Daniel and Morissette',144.54,0.77,2.15,1586546),(38,'Sonair','Mayert, Ortiz and Kuphal',23.6,0.77,3.1,392839),(39,'Holdlamis','Gutmann-Rempel',58.29,0.71,2.85,8199261),(40,'Voyatouch','Conroy-Gerlach',125.39,0.78,4.09,6269576),(41,'Temp','Hagenes Inc',100.16,0.9,4.43,876728),(42,'Ventosanzap','Wolf-Hessel',90.3,0.78,4.87,9782962),(43,'Zamit','Feeney Inc',178.87,1,3.56,8238566),(44,'Tresom','Kautzer-Klein',43.96,0.88,4.86,4954898),(45,'Tempsoft','Prohaska-Kuhn',60.65,0.99,3.7,7792374),(46,'Holdlamis','Wolf, Funk and Kemmer',101.38,0.98,1.16,6345507),(47,'Quo Lux','Zboncak, McClure and Hagenes',139.84,1,1.03,4197132),(48,'Stronghold','Gutmann-Herman',5.45,0.87,1.77,4974465),(49,'Y-Solowarm','Sanford Group',179.59,0.87,1.17,3098299),(50,'Konklab','Dooley, Rohan and Heaney',78.72,0.87,1.53,6365567),(51,'Asoka','Wilkinson LLC',115.61,0.82,3.29,3634779),(52,'Tampflex','Beer, Brakus and Mayert',124.48,0.9,4.56,8257983),(53,'Bitwolf','Wisoky, Brekke and Bins',54.08,0.97,2.94,4518718),(54,'Namfix','Howe and Sons',24.68,0.9,2.01,4903442),(55,'Hatity','Johnston, Konopelski and Stroman',121.21,0.93,1.29,9544608),(56,'Zontrax','Windler LLC',125.53,0.75,1.09,292404),(57,'Fintone','Hamill Group',120.97,0.8,3.36,8492269),(58,'Daltfresh','Weimann, Bode and Graham',191.28,0.89,1.15,593461),(59,'Duobam','McCullough, Russel and Mohr',43.58,0.75,4.51,3294623),(60,'Lotstring','Dickens-D\'Amore',144.86,0.72,1.77,9700172),(61,'Stronghold','Lowe Inc',82.2,0.89,3.76,5335532),(62,'Zoolab','Flatley and Sons',44.59,0.74,2.36,2191097),(63,'Regrant','Wolf Group',168.38,0.82,3.05,7938414),(64,'Fintone','Mertz-Crist',49.26,0.82,4.75,7801867),(65,'Otcom','McGlynn-Greenholt',122.35,0.85,1.68,6972806),(66,'Daltfresh','Ziemann-Weissnat',92.39,0.83,3.32,2808777),(67,'Hatity','Streich-Mosciski',15.05,0.78,2.76,4946674),(68,'Regrant','Streich Inc',4.62,0.78,2.91,5255311),(69,'Zathin','Bernhard, Langworth and Ryan',105.34,0.99,4.12,7064344),(70,'Tampflex','Will-Bashirian',91.07,0.74,4.5,4882160),(71,'Ronstring','Paucek-Lowe',105.57,0.89,3.35,5669896),(72,'Sonair','VonRueden-McLaughlin',150.79,0.99,3.98,4134850),(73,'Asoka','Okuneva-Kuvalis',72.48,0.74,2.3,1512104),(74,'Bytecard','Feest-Yundt',162.05,0.75,4.82,7525923),(75,'Duobam','Thiel-Hermann',120.39,0.72,1.24,5877529),(76,'Bitwolf','Anderson-Moen',82.32,0.8,4.23,7040404),(77,'Vagram','Lynch-Fay',11.59,0.7,3.69,4929145),(78,'Latlux','Flatley LLC',51.37,0.92,4.96,115213),(79,'Cardguard','Krajcik-Walter',25.34,0.92,3.78,3982284),(80,'Subin','Lynch Inc',170.62,0.75,1.76,8410445),(81,'Fintone','Jast-Upton',189.45,0.94,2.03,7329755),(82,'Tres-Zap','Schroeder, Stokes and Lehner',50.4,0.9,4.01,6038827),(83,'Redhold','Moen, Goldner and Reichel',1.42,0.78,2.25,7380540),(84,'Lotlux','Smith, Swaniawski and Kemmer',47.27,0.73,3.36,2949672),(85,'Tampflex','Runolfsdottir, Aufderhar and Kerluke',79.82,0.96,1.25,1612613),(86,'Latlux','Marks-Wintheiser',20.51,0.73,2.81,9960708),(87,'Prodder','Mraz, Moore and Koss',59.51,0.95,2.02,8535647),(88,'Transcof','Torp-Block',162.22,0.82,3.22,2280153),(89,'Quo Lux','Upton-Wilderman',78.06,0.96,1.17,9737719),(90,'Fintone','Barton and Sons',78.4,0.82,2.95,979443),(91,'Prodder','Mann-McClure',197.42,0.77,2.94,7485521),(92,'Home Ing','Lemke Inc',21.2,0.84,3.74,6513292),(93,'Opela','Greenfelder Inc',157.53,0.8,1.82,589856),(94,'Fintone','Boyer Group',140.57,0.73,3.58,8376304),(95,'Asoka','Ledner Group',64.04,0.71,3.16,2905352),(96,'Biodex','Christiansen-Luettgen',36.85,0.79,2.05,2883002),(97,'Vagram','Oberbrunner-Funk',136.55,0.95,1.49,8701238),(98,'Zontrax','Strosin, O\'Kon and Purdy',53.02,0.97,4.8,6172664),(99,'Job','Brown-Wilkinson',78.39,0.75,1.62,7515186),(100,'Non Repeatable Read 2','Donnelly-Grimes',59.19,0.99,3.11,8194928),(101,'Non Repeatable Read 2','phantomread 2',0.9900000000000002,0.99,4.6,0),(102,'Non Repeatable Read 2','phantomread 2',1.9900000000000002,0.99,4.6,0),(103,'Non Repeatable Read 2','phantomread 2',2.99,0.99,4.6,0),(104,'phantomread 1','phantomread 2',2.99,0.99,4.6,0);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-03 18:26:04
