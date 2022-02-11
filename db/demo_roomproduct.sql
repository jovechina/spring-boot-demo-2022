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
-- Table structure for table `roomproduct`
--

DROP TABLE IF EXISTS `roomproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomproduct` (
  `productid` int NOT NULL,
  `serviceid` int NOT NULL,
  `sortid` int DEFAULT NULL,
  `productname` varchar(45) DEFAULT NULL,
  `productprice` int DEFAULT NULL,
  PRIMARY KEY (`productid`,`serviceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomproduct`
--

LOCK TABLES `roomproduct` WRITE;
/*!40000 ALTER TABLE `roomproduct` DISABLE KEYS */;
INSERT INTO `roomproduct` VALUES (1,1,1,'大白',2000),(2,1,2,'环保漆',3000),(3,2,1,'吊顶',2000),(4,3,1,'更换电线',3000),(5,4,1,'安装网线',3000),(6,5,1,'实木普通地板',20000),(7,5,2,'实木高档地板',50000),(8,5,3,'复合木地板',15000),(9,5,4,'大理石地面',10000),(10,6,2,'中型瓷砖',8000),(11,6,1,'小型瓷砖',6000),(12,6,4,'大型瓷砖',10000),(13,7,1,'大理石地面',10000),(14,8,1,'一体式厨柜',20000),(15,8,2,'实惠型橱柜',10000),(16,9,1,'高档抽油烟机',9000),(17,9,2,'高性价比抽油烟机',6000),(18,10,1,'更换水管',5000);
/*!40000 ALTER TABLE `roomproduct` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-11  7:54:30
