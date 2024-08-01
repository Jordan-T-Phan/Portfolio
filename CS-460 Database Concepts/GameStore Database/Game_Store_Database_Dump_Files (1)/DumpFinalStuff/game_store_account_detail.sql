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
-- Table structure for table `account_detail`
--

DROP TABLE IF EXISTS `account_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_detail` (
  `id` int NOT NULL,
  `first_name` varchar(25) DEFAULT NULL,
  `last_name` varchar(25) DEFAULT NULL,
  `display_name` varchar(25) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `account_detail_id` FOREIGN KEY (`id`) REFERENCES `account_login` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_detail`
--

LOCK TABLES `account_detail` WRITE;
/*!40000 ALTER TABLE `account_detail` DISABLE KEYS */;
INSERT INTO `account_detail` VALUES (1,'Helga','Scimone','Mat Lam Tam',45.8),(2,'Ali','Biggin','Stringtough',1517.14),(3,'Giacopo','Costall','Asoka',1265.28),(4,'Whitney','Scimone','Lotstring',669.52),(5,'Sheppard','Ennor','Temp',1548.45),(6,'Buiron','Dudmarsh','Redhold',1143.79),(7,'Simon','Lobb','Tresom',1988.41),(8,'Bernie','Kleinmann','Zathin',235.24),(9,'Moss','Triplet','Bitchip',1552.9),(10,'Joane','Caesman','Opela',627.6),(11,'Harmonie','Reyner','Biodex',292.39),(12,'Korella','Bluck','Domainer',1193.23),(13,'Lorraine','Oxenford','Voltsillam',1130.1),(14,'Denny','Gummow','Matsoft',1614.5),(15,'Angelico','Giannassi','Lotstring',30.62),(16,'Elonore','Lempke','Tres-Zap',69.34),(17,'Christi','Edds','Hatity',1387.9),(18,'Rodina','Crookston','Biodex',350.17),(19,'Vito','Ault','Zamit',1886.94),(20,'Ebenezer','Lawson','Matsoft',566.81),(21,'Felipa','Pleavin','Namfix',323.89),(22,'Maia','Coldicott','Cardify',964.17),(23,'Rhianna','Ciciura','Aerified',1237.3),(24,'Carmita','Lytle','Vagram',59.21),(25,'Monika','Cummungs','Span',1486.56),(26,'Justus','Lockless','Toughjoyfax',613.8),(27,'Finlay','Rissom','Flexidy',1730.38),(28,'Jeffrey','Haig','Tres-Zap',336.27),(29,'Demetri','Kieff','Bytecard',1789.32),(30,'Branden','Franzotto','Quo Lux',1871.93),(31,'Mariellen','Lansberry','Gembucket',1904.88),(32,'Cordelia','Brantl','Tampflex',1154.01),(33,'Cati','Roubottom','Span',1322.84),(34,'Philippa','Cowle','Ventosanzap',1202.39),(35,'Kirbie','Heeron','Regrant',1306.04),(36,'Luz','Fuzzey','Tres-Zap',1030.13),(37,'Astrix','Villalta','Zamit',402.31),(38,'Margit','Zelner','Matsoft',1657.27),(39,'Caitrin','O\'Conor','Gembucket',1209),(40,'Evvy','Eyree','Veribet',981.56),(41,'Cos','Jacop','Overhold',1425.38),(42,'Dulcia','Darch','Span',642.37),(43,'Kaleb','Breadon','Opela',1271.98),(44,'Odette','McArthur','Duobam',1510.75),(45,'Cross','Pople','Tin',676.99),(46,'Thedrick','Grewer','Trippledex',1596.68),(47,'Jacklyn','Argontt','Greenlam',837.89),(48,'Karina','Falkner','Andalax',1588.66),(49,'Wolfie','Heardman','Domainer',1287.74),(50,'Ellsworth','Baterip','Gembucket',223.74),(51,'Sheilakathryn','Cadwell','Fintone',580.17),(52,'Kathleen','Adkins','Subin',1078.87),(53,'Eduino','Hartop','It',226.42),(54,'Anallise','Tock','Sonsing',973.35),(55,'Gaylord','Kubec','Quo Lux',1869.81),(56,'Allegra','Gorler','Voltsillam',569.85),(57,'Pearce','Castillo','Regrant',811.41),(58,'Rozella','Blaksland','Biodex',433.37),(59,'Kiley','Fentem','Viva',579.15),(60,'Dian','Poxson','Quo Lux',1845.97),(61,'Tod','Yesenin','Flowdesk',1617.79),(62,'Ira','Bienvenu','Sub-Ex',1137.77),(63,'Ediva','Rolston','Andalax',1224.81),(64,'Loretta','Allred','Kanlam',560.81),(65,'Pearla','Stairs','Alpha',687.51),(66,'Kimble','Evennett','Pannier',1545.21),(67,'Josy','Kinnar','Holdlamis',209.38),(68,'Rheta','Denman','Redhold',1267.52),(69,'Nedi','O\'Roan','Toughjoyfax',270.3),(70,'Coriss','Wadworth','Stringtough',348.95),(71,'Merry','Gissing','Fixflex',61.45),(72,'Marshall','Bernholt','Tresom',781.96),(73,'Tabby','Kinig','Vagram',1517.92),(74,'Sheffield','Seer','Tempsoft',1629),(75,'Gregoor','Perrot','Rank',792.95),(76,'Shae','Alcido','Sub-Ex',575),(77,'Reece','Sivier','Stringtough',1813.88),(78,'Jeanie','Starrs','Opela',971.91),(79,'Rochell','Goodall','Flexidy',1412.26),(80,'Curr','Lymbourne','Stringtough',191.91),(81,'Elayne','Shane','Domainer',1969.06),(82,'Sunny','Wyett','Quo Lux',1800.86),(83,'Zulema','Kosiada','Wrapsafe',838.47),(84,'Nichole','Widdop','Toughjoyfax',1727.76),(85,'Carlene','Chason','Stronghold',1038.8),(86,'Neill','Statham','Alphazap',239.46),(87,'Court','Georgeson','Greenlam',1878.31),(88,'Quintilla','Janout','Flexidy',1643.03),(89,'Sapphira','Kerwood','Pannier',1603.9),(90,'Milo','Blackboro','Cookley',444.72),(91,'Aldridge','McCosh','Cardify',667.9),(92,'Murray','Ouver','Konklux',1603.16),(93,'Teodoor','Apperley','Prodder',1829.72),(94,'Jacinta','Pickering','Sub-Ex',1510.32),(95,'Eimile','Byrch','Solarbreeze',1338.59),(96,'Harlan','Stout','Redhold',1566.51),(97,'Manny','Barday','Zoolab',1497.47),(98,'Linn','O\'Boyle','Home Ing',1757.16),(99,'Tillie','Hembling','Alpha',1214.51),(100,'Clayborn','Howel','Rank',279.36),(101,'Hello','World','Hello World',179),(102,'World','Hello','World Hello',85),(109,NULL,NULL,NULL,NULL),(110,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `account_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-03 18:26:03
