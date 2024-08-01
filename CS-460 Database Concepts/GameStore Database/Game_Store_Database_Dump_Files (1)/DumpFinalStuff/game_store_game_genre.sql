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
-- Table structure for table `game_genre`
--

DROP TABLE IF EXISTS `game_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_genre` (
  `game_id` int NOT NULL,
  `genre_id` int NOT NULL,
  PRIMARY KEY (`game_id`,`genre_id`),
  KEY `genre_id_idx` (`genre_id`),
  KEY `game_id_idx` (`game_id`),
  CONSTRAINT `game_id` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `genre_id` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_genre`
--

LOCK TABLES `game_genre` WRITE;
/*!40000 ALTER TABLE `game_genre` DISABLE KEYS */;
INSERT INTO `game_genre` VALUES (1,1),(4,1),(7,1),(10,1),(14,1),(17,1),(20,1),(24,1),(26,1),(28,1),(32,1),(36,1),(42,1),(45,1),(46,1),(47,1),(51,1),(55,1),(58,1),(62,1),(67,1),(71,1),(74,1),(77,1),(81,1),(82,1),(85,1),(88,1),(92,1),(94,1),(96,1),(99,1),(2,2),(5,2),(8,2),(10,2),(15,2),(17,2),(21,2),(24,2),(27,2),(29,2),(33,2),(36,2),(41,2),(45,2),(48,2),(52,2),(55,2),(59,2),(63,2),(68,2),(72,2),(75,2),(77,2),(83,2),(86,2),(89,2),(92,2),(95,2),(96,2),(100,2),(2,3),(6,3),(8,3),(11,3),(15,3),(18,3),(21,3),(24,3),(27,3),(29,3),(33,3),(36,3),(41,3),(44,3),(48,3),(53,3),(59,3),(63,3),(68,3),(72,3),(75,3),(77,3),(83,3),(86,3),(89,3),(92,3),(95,3),(97,3),(2,4),(6,4),(8,4),(11,4),(16,4),(18,4),(22,4),(24,4),(28,4),(30,4),(36,4),(40,4),(43,4),(49,4),(53,4),(56,4),(60,4),(64,4),(69,4),(72,4),(75,4),(78,4),(83,4),(87,4),(89,4),(93,4),(97,4),(3,5),(7,5),(8,5),(11,5),(16,5),(19,5),(23,5),(24,5),(28,5),(31,5),(34,5),(37,5),(40,5),(43,5),(49,5),(54,5),(57,5),(61,5),(65,5),(70,5),(73,5),(76,5),(79,5),(84,5),(87,5),(89,5),(93,5),(3,6),(7,6),(8,6),(12,6),(16,6),(19,6),(23,6),(25,6),(28,6),(32,6),(35,6),(38,6),(40,6),(43,6),(50,6),(54,6),(57,6),(61,6),(66,6),(70,6),(73,6),(76,6),(80,6),(87,6),(90,6),(93,6),(95,6),(98,6),(4,7),(7,7),(9,7),(13,7),(16,7),(20,7),(23,7),(25,7),(28,7),(32,7),(36,7),(38,7),(39,7),(43,7),(50,7),(54,7),(57,7),(61,7),(67,7),(70,7),(73,7),(76,7),(80,7),(84,7),(88,7),(91,7),(93,7),(96,7),(98,7);
/*!40000 ALTER TABLE `game_genre` ENABLE KEYS */;
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
