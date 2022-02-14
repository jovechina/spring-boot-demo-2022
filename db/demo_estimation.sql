-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: demo
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `estimation`
--

DROP TABLE IF EXISTS `estimation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estimation` (
  `estimationid` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `roomid` int DEFAULT NULL,
  `interiordecoration` varchar(45) DEFAULT NULL,
  `paintwall` varchar(45) DEFAULT NULL,
  `ceiling` varchar(45) DEFAULT NULL,
  `wires` varchar(45) DEFAULT NULL,
  `cable` varchar(45) DEFAULT NULL,
  `floor` varchar(45) DEFAULT NULL,
  `walltiles` varchar(45) DEFAULT NULL,
  `tile` varchar(45) DEFAULT NULL,
  `kitchencabinet` varchar(45) DEFAULT NULL,
  `rangehood` varchar(45) DEFAULT NULL,
  `waterpipe` varchar(45) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  `updatedate` date DEFAULT NULL,
  PRIMARY KEY (`estimationid`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estimation`
--

LOCK TABLES `estimation` WRITE;
/*!40000 ALTER TABLE `estimation` DISABLE KEYS */;
INSERT INTO `estimation` VALUES (16,1,6,'3','2','3','4','5','9','12',NULL,'14','17','18','2022-02-04','2022-02-08'),(17,1,5,'1','1','3',NULL,'5','6','10',NULL,'14','16','18','2022-02-04','2022-02-08'),(19,1,2,'2','1','3','4','5','9','10','13','14','16','18','2022-02-04','2022-02-08'),(20,1,2,'2','1',NULL,NULL,NULL,'6','10',NULL,'14','16',NULL,'2022-02-04','2022-02-08'),(21,1,3,'2','1','3','4','5','6','11',NULL,'14','17','18','2022-02-04',NULL),(22,1,4,'3','2','3',NULL,NULL,'7','12',NULL,'15','17',NULL,'2022-02-04','2022-02-08'),(23,1,5,'3','2','3',NULL,'5','6','12','13','15','16','18','2022-02-07','2022-02-08'),(24,0,5,'3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-02-07',NULL),(25,1,3,'2','1','3','4',NULL,'9','10','13','14','16','18','2022-02-07','2022-02-08'),(26,1,3,'2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-02-07',NULL),(27,1,3,'2','1',NULL,NULL,NULL,'6','10',NULL,'14','16',NULL,'2022-02-07','2022-02-08'),(30,1,8,'2','1',NULL,NULL,NULL,'6','12',NULL,'14','17','18','2022-02-07',NULL),(31,1,2,'2','1','3',NULL,NULL,'8','10','13','14','16',NULL,NULL,'2022-02-08'),(32,1,4,'2','1','3',NULL,NULL,'6','11',NULL,'14','16',NULL,NULL,'2022-02-08'),(33,1,4,'2','1',NULL,NULL,NULL,'6','10',NULL,'14','16',NULL,NULL,'2022-02-08'),(34,1,3,'2','2','3',NULL,NULL,'6','10',NULL,'14','16',NULL,'2022-02-08',NULL),(35,1,5,'2','1',NULL,NULL,NULL,'6','10',NULL,'15','16',NULL,'2022-02-08',NULL),(36,1,3,'2','1',NULL,NULL,NULL,'6','10',NULL,'14','16','18','2022-02-08',NULL),(37,1,3,'2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-02-08',NULL),(38,1,5,'1','1','3',NULL,NULL,'6','10','13','14','16','18','2022-02-08',NULL),(39,1,5,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-02-08',NULL),(40,1,3,'2','1','3',NULL,NULL,'6','10',NULL,'14','16',NULL,'2022-02-08',NULL),(41,1,5,'1','1',NULL,NULL,NULL,'6','10',NULL,'15','16',NULL,'2022-02-08',NULL);
/*!40000 ALTER TABLE `estimation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-11  7:54:31