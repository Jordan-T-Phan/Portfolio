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
-- Table structure for table `mod_genre`
--

DROP TABLE IF EXISTS `mod_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mod_genre` (
  `mod_id` int NOT NULL,
  `genre_id` int NOT NULL,
  PRIMARY KEY (`mod_id`,`genre_id`),
  KEY `mod_genre_genre_id_idx` (`genre_id`),
  CONSTRAINT `mod_genre_genre_id` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mod_genre_mod_id` FOREIGN KEY (`mod_id`) REFERENCES `mod` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mod_genre`
--

LOCK TABLES `mod_genre` WRITE;
/*!40000 ALTER TABLE `mod_genre` DISABLE KEYS */;
INSERT INTO `mod_genre` VALUES (1,1),(7,1),(16,1),(17,1),(23,1),(33,1),(37,1),(38,1),(45,1),(53,1),(62,1),(66,1),(69,1),(70,1),(77,1),(78,1),(91,1),(93,1),(97,1),(100,1),(104,1),(113,1),(114,1),(124,1),(132,1),(137,1),(140,1),(153,1),(155,1),(161,1),(164,1),(170,1),(181,1),(192,1),(196,1),(199,1),(200,1),(6,2),(13,2),(20,2),(22,2),(28,2),(29,2),(40,2),(43,2),(58,2),(67,2),(73,2),(75,2),(96,2),(98,2),(109,2),(116,2),(133,2),(145,2),(146,2),(148,2),(154,2),(169,2),(174,2),(176,2),(177,2),(188,2),(195,2),(198,2),(200,2),(5,3),(18,3),(20,3),(21,3),(34,3),(39,3),(47,3),(55,3),(68,3),(71,3),(72,3),(76,3),(80,3),(84,3),(87,3),(88,3),(110,3),(111,3),(117,3),(130,3),(131,3),(135,3),(165,3),(171,3),(172,3),(173,3),(194,3),(3,4),(12,4),(14,4),(24,4),(41,4),(42,4),(54,4),(63,4),(72,4),(74,4),(86,4),(90,4),(94,4),(101,4),(106,4),(108,4),(119,4),(121,4),(136,4),(142,4),(150,4),(152,4),(158,4),(160,4),(163,4),(166,4),(168,4),(180,4),(183,4),(189,4),(190,4),(193,4),(197,4),(8,5),(15,5),(25,5),(26,5),(27,5),(35,5),(44,5),(59,5),(61,5),(65,5),(92,5),(102,5),(120,5),(125,5),(126,5),(139,5),(147,5),(151,5),(156,5),(162,5),(178,5),(185,5),(187,5),(199,5),(1,6),(2,6),(4,6),(5,6),(9,6),(10,6),(19,6),(31,6),(32,6),(49,6),(50,6),(57,6),(82,6),(85,6),(95,6),(99,6),(105,6),(127,6),(143,6),(149,6),(157,6),(167,6),(175,6),(182,6),(184,6),(186,6),(199,6),(11,7),(30,7),(36,7),(46,7),(48,7),(51,7),(52,7),(56,7),(60,7),(64,7),(79,7),(81,7),(82,7),(83,7),(85,7),(89,7),(103,7),(107,7),(112,7),(115,7),(118,7),(122,7),(123,7),(128,7),(129,7),(134,7),(138,7),(141,7),(144,7),(152,7),(159,7),(179,7),(191,7);
/*!40000 ALTER TABLE `mod_genre` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-03 18:26:00
