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
-- Table structure for table `account_login`
--

DROP TABLE IF EXISTS `account_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_login` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(25) NOT NULL,
  `admin_access` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_login`
--

LOCK TABLES `account_login` WRITE;
/*!40000 ALTER TABLE `account_login` DISABLE KEYS */;
INSERT INTO `account_login` VALUES (1,'cmottram0','oY5=$96EbGJXd{',0),(2,'hichangenew','new',0),(3,'ephiller2','aP1|lak|U7HM%',0),(4,'jpopham3','bW5\'\"9~Q4R\"',0),(5,'ccannavan4','cG8}_q0o,~jq',0),(6,'aritchie5','sD7\'FqXkb(j\'{R$o',0),(7,'lpoulsen6','dE5@P}_2+)F{W',0),(8,'mmenure7','bY6|K\"\"\"g/SZJ8&/\"',0),(9,'pblest8','fO2/AoWhbERA}*%6',0),(10,'ctowne9','oB1<4BZK',0),(11,'mfulksa','yW5#L/T81jlO8~!',0),(12,'fspiniellob','qZ3\\FIyENL',0),(13,'opinillac','lA0(YswX0',0),(14,'wtwinborned','qF7<$xYr6',0),(15,'gconneelye','bD8_m*/2mJ\'B\"t8\"',0),(16,'jhendersonf','wT4\'2/t?=`kE',0),(17,'lrameg','bM9`|$fe',0),(18,'ccruessh','aD4!DLrAB',0),(19,'kshutleri','dQ7{T@}/Bl1rMMd',0),(20,'jbrannonj','aM8>6o#U(cS9Y~',0),(21,'jtipplek','hF2(Y_Od',0),(22,'bbernardottil','gY8{H6OQZ|CSJE5\'',1),(23,'bcourtonnem','zY7?CsK\'k',0),(24,'nmangamn','yQ2=>}7b',0),(25,'bmckeano','eN5\'`k\\R(9G\\JK',0),(26,'kletfordp','xE2`qC8q,P',0),(27,'vblunnq','dC9*1l3FrP6ja',0),(28,'jtamsr','aZ4_/RG{T4',0),(29,'aessebys','qG6|pP=6J@C@',0),(30,'plenscht','pF8_{\"g#~L\"',0),(31,'gpointinu','tZ0=\\C3nN,)<',0),(32,'mbennallckv','vW9%_}YjTE<R\\',0),(33,'dwilloughbyw','tX7*eq=0tmRi\')F',1),(34,'mplaidx','lI1(Ut2*ny),\\\'!=',0),(35,'cborresy','lW6>*ZErwP1D',0),(36,'wdunkerleyz','bI9%$z7?*!4OC',0),(37,'atopliss10','uN3/K17QO',0),(38,'bauld11','gK8><.wmM',0),(39,'dhenrionot12','oQ3*3<\\!',1),(40,'jhenrie13','lX7}dQ4@aGlu',0),(41,'fbuckerfield14','bW5!fDK\"9~Zc\"',0),(42,'tholton15','iD7>B&9~5l',0),(43,'gitzhayek16','fZ6%y+ZIL',0),(44,'ftomlinson17','xS3.Y4/oa_WfUU',0),(45,'fdoyley18','hX0%g`fYoPm',0),(46,'chalms19','aK8|z}~DgoL*nL',0),(47,'sstollenbeck1a','cL4$c{Zb4A\\ii',0),(48,'cduffield1b','wE3*$bVW',0),(49,'epeasgood1c','aM9$>/nC$p=/',0),(50,'rrobic1d','vX4{BGOEt<b',0),(51,'llamar1e','vA4,fY\"Y>56lrcC\"',0),(52,'lbunch1f','qQ8_=_FFxSaLS_',0),(53,'rgier1g','aR7.G\\#*|(m$Ll',0),(54,'itissiman1h','wU1|s~*5C71$C`i',0),(55,'jelloit1i','uM5>m{9SN',0),(56,'kgareisr1j','tM8$SYpp&O@',0),(57,'vdellar1k','yM0)RuD1H',0),(58,'lveltman1l','lY2|(|T@.\'N}',0),(59,'kyancey1m','mG7=PO|R\"un\"',0),(60,'grooney1n','lX2+ew?J#',0),(61,'mgalpen1o','cT7%w_G(Ry}7}',0),(62,'yheustice1p','zE4>DCPRbse.8',0),(63,'ewilman1q','yZ5?Vx|X\'',0),(64,'adaudray1r','oS9&/+8wN%D9,',0),(65,'gbrunel1s','lU5,B)o42F3)|b/_',0),(66,'ewittey1t','bA6}y_7ySB+iG',0),(67,'fblundel1u','gO0\\$m!C',0),(68,'pleivesley1v','eI4{hVM7rB8H',0),(69,'mgolborn1w','qH8)dE~ok',0),(70,'atams1x','zK6>baa\'f',0),(71,'khastings1y','yU0\"Y9Z\\1%xGH\"',1),(72,'scloney1z','mA4(9!Yl$YmSB&@\'',0),(73,'wlipgens20','vG6\"qIeYsCN3&\"',0),(74,'mpitcaithly21','wQ1{\"GaNDA\"',0),(75,'ppiddletown22','aM7#Utv/XL0G#0A',0),(76,'mblancowe23','dX2&_|*q',0),(77,'mellgood24','tI2#r=H`)hfWmpSH',0),(78,'griceards25','wO8#b}|(Xz<A3B\"\"',0),(79,'aspikings26','rB9\"}Gzr#jh0\"',0),(80,'gfierman27','eU0@i%Y#k28<&}',0),(81,'oevert28','gO4}\"z?6aHT\"',0),(82,'mgallally29','eW1<.Y/,<O%T',0),(83,'tminghetti2a','hK7{pTvcg!',0),(84,'milyuchyov2b','sH0?6flLq!eh1B',0),(85,'acleator2c','mQ0>buT>',0),(86,'arableau2d','nB0%tFqX/*Zk',0),(87,'dalford2e','cM1{D1$&6',0),(88,'pgillooly2f','gC1=c&ws<~1,&~!\\',0),(89,'epolgreen2g','lR3\'o%_EW3D6/&',0),(90,'cbackshaw2h','eS6!xDA|WEEC|QZ#',0),(91,'jchalker2i','nZ6\'xC.5~ta1f\\H',0),(92,'mfulkes2j','xZ4#+4%T7H',0),(93,'bbandiera2k','hE9=3JKD%$\'98F',0),(94,'bsiddeley2l','hM8}>LjE2vt&Za',0),(95,'msyncke2m','uW5#8@uMp+3',1),(96,'sattle2n','xP0\"JlCG_Iw!c\"',0),(97,'zallchorn2o','xG3\'NqBABN.1C',1),(98,'rmagovern2p','cI0*$P8,gn',0),(99,'mbrockington2q','lR5@&)F3KL',0),(100,'gbohike2r','fL6=A!\'d2Th',0),(101,'Hello','World',0),(102,'World','Hello',1),(109,'Heyo','Aiyo',0),(110,'veasdkfa;sdfj','sk;ldfajsf',0);
/*!40000 ALTER TABLE `account_login` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-03 18:26:02
