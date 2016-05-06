CREATE DATABASE  IF NOT EXISTS `importexport` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `importexport`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: importexport
-- ------------------------------------------------------
-- Server version	5.7.11-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Kunde` varchar(56) DEFAULT NULL,
  `AccountID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `column_configuration`
--

DROP TABLE IF EXISTS `column_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `column_configuration` (
  `columnConfigID` int(11) NOT NULL,
  `jobID` int(11) DEFAULT NULL,
  `key` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `column` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orgColumn` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `length` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL,
  `lastModified` timestamp NULL DEFAULT NULL,
  `userName` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isDeleted` varchar(3) COLLATE utf8_unicode_ci DEFAULT 'no',
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`columnConfigID`),
  UNIQUE KEY `isDeleted_UNIQUE` (`isDeleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `column_configuration`
--

LOCK TABLES `column_configuration` WRITE;
/*!40000 ALTER TABLE `column_configuration` DISABLE KEYS */;
/*!40000 ALTER TABLE `column_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `column_transformations`
--

DROP TABLE IF EXISTS `column_transformations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `column_transformations` (
  `transformationID` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `transformation` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rule` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL,
  `lastModified` timestamp NULL DEFAULT NULL,
  `isDeleted` varchar(3) COLLATE utf8_unicode_ci DEFAULT 'no',
  `userName` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`transformationID`),
  UNIQUE KEY `isDeleted_UNIQUE` (`isDeleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `column_transformations`
--

LOCK TABLES `column_transformations` WRITE;
/*!40000 ALTER TABLE `column_transformations` DISABLE KEYS */;
/*!40000 ALTER TABLE `column_transformations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_configuration`
--

DROP TABLE IF EXISTS `job_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_configuration` (
  `jobID` int(11) NOT NULL AUTO_INCREMENT,
  `jobTyp` varchar(100) NOT NULL,
  `jobDescription` varchar(100) NOT NULL,
  `table` varchar(100) NOT NULL COMMENT 'Kann eine TabelleName in der DB oder eine Dateinpath+Dateiname sein (C:....xxx.csv).',
  `schema` varchar(100) NOT NULL COMMENT 'Kann eine TabelleName in der DB oder eine Dateinpath+Dateiname sein (C:....xxx.csv).',
  `startTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `endTime` datetime DEFAULT '2081-11-30 16:00:00',
  `scheduler` char(100) DEFAULT NULL COMMENT 'Eine Liste mit dem Werte: Daily, Weekly, 1st of the Month,15th of the Month',
  `interval` varchar(100) DEFAULT NULL,
  `fileExtension` varchar(10) DEFAULT NULL,
  `separator` char(1) NOT NULL DEFAULT ';',
  `encoding` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `hasHeader` varchar(3) NOT NULL DEFAULT 'yes',
  `type` varchar(100) DEFAULT NULL,
  `partner` varchar(100) DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `lastModified` datetime DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `isDeleted` varchar(3) NOT NULL DEFAULT 'no',
  `userName` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`jobID`),
  KEY `job_config_job_id_index` (`jobID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_configuration`
--

LOCK TABLES `job_configuration` WRITE;
/*!40000 ALTER TABLE `job_configuration` DISABLE KEYS */;
INSERT INTO `job_configuration` VALUES (1,'dd','dd','dd','dfasdf','2081-11-30 16:00:00','2081-11-30 16:00:00','dd','33','33','3','33','333','yes','dd','dd','2016-05-06 14:11:24','2016-05-06 15:09:01','a','ddd',11),(2,'dd','dd','dd','adfasdfa','2081-11-30 16:00:00','2081-11-30 16:00:00','dd','33','33','3','33','333','yes','dd','dd','2016-05-06 14:11:24','2016-05-06 15:09:01','a','ddd',11);
/*!40000 ALTER TABLE `job_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `map_result`
--

DROP TABLE IF EXISTS `map_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `map_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ergebnis` varchar(100) DEFAULT NULL,
  `ermittlungscode` varchar(20) DEFAULT NULL,
  `mexxonCode` varchar(10) NOT NULL,
  `created` timestamp NULL DEFAULT NULL,
  `is_deleted` int(11) DEFAULT NULL,
  `last_modified` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `map_result`
--

LOCK TABLES `map_result` WRITE;
/*!40000 ALTER TABLE `map_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `map_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `map_status`
--

DROP TABLE IF EXISTS `map_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `map_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(11) NOT NULL,
  `Text` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `map_status`
--

LOCK TABLES `map_status` WRITE;
/*!40000 ALTER TABLE `map_status` DISABLE KEYS */;
INSERT INTO `map_status` VALUES (1,0,'Eintrag aktiv'),(2,1,'Eintrag deaktiviert'),(3,2,'Eintrag erfordert manuellen Eingriff');
/*!40000 ALTER TABLE `map_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `ClientOrder` varchar(100) NOT NULL,
  `POI` varchar(100) DEFAULT NULL,
  `ProductID` varchar(100) DEFAULT NULL,
  `ClientAccountID` varchar(100) DEFAULT NULL,
  `AccountID` varchar(100) DEFAULT NULL,
  `Gender` varchar(100) DEFAULT NULL,
  `LastName` varchar(100) DEFAULT NULL,
  `MaidenName` varchar(100) DEFAULT NULL,
  `FirstName` varchar(100) DEFAULT NULL,
  `Street` varchar(100) DEFAULT NULL,
  `House` varchar(100) DEFAULT NULL,
  `HouseADD` varchar(100) DEFAULT NULL,
  `ZIP` varchar(100) DEFAULT NULL,
  `City` varchar(100) DEFAULT NULL,
  `Country` varchar(100) DEFAULT NULL,
  `DOB` varchar(100) DEFAULT NULL,
  `Phone` varchar(100) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ClientOrder`),
  UNIQUE KEY `ClientOrder_UNIQUE` (`ClientOrder`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES ('100272935','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Diana Magdalena','Curt-Querner-Str.','1','','41812','Erkelenz','DEU','','128851','test@test-mexxon.com\r'),('1004655414','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Oliver','Kreuzmoor','24','','32429','MINDEN','DEU','01.11.1991','128890','test@test-mexxon.com\r'),('101418627','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Katica','Bruno-Taut-Ring','110','','31234','Edemissen','DEU','08.08.1991','128912','test@test-mexxon.com\r'),('1024448235','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Oskar','Karl-Barth-Str.','43','','99099','Erfurt','DEU','20.12.1975','128768','test@test-mexxon.com\r'),('1045933743','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Christiane','Bebelstr.','25','','47137','Duisburg','DEU','09.11.1988','128888','test@test-mexxon.com\r'),('1048026022','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Stefanie','Leipziger Str.','10','','51145','KÃ¶ln','DEU','23.06.1975','128802','test@test-mexxon.com\r'),('1058258580','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Steven','Landskronastr.','33','','52391','KÃ¶ln','DEU','22.04.1983','128771','test@test-mexxon.com\r'),('1066812259','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Anke','Graf-von-Stauffenberg-Str.','10','','38170','SchÃ¶ppenstedt','DEU','18.02.1993','128783','test@test-mexxon.com\r'),('1084564749','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Elija','Zum Wiegele','20','','47608','Geldern','DEU','04.04.1984','128760','test@test-mexxon.com\r'),('1088190557','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Mandy','Kleine Siedlung','66A','','34127','Kassel','DEU','24.04.1984','128842','test@test-mexxon.com\r'),('1101368165','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Marie Denise','Ascherslebener Str.','40','','40231','DÃ¼sseldorf','DEU','09.05.1972','128877','test@test-mexxon.com\r'),('1119996021','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Alejandro','Pirolstr.','11','','89077','Ulm','DEU','27.12.1985','128791','test@test-mexxon.com\r'),('1125978400','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jacqueline','Freiberger Str.','99','','19300','Grabow','DEU','04.03.1989','128891','test@test-mexxon.com\r'),('1136241344','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Annemarie','Hasselbrookstr.','29','','2977','HOYERSWERDA','DEU','22.03.1953','128920','test@test-mexxon.com\r'),('1142810900','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jennifer','Grundstr.','164','','32699','Extertal','DEU','07.12.1977','128856','test@test-mexxon.com\r'),('1169337604','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Holger','Scheffelstr.','23','','1558','GroÃenhain','DEU','23.06.1984','128831','test@test-mexxon.com\r'),('1170165239','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sonja','Am KÃ¶rgraben','2C','','33154','Salzkotten','DEU','24.09.1993','128769','test@test-mexxon.com\r'),('1174517536','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Christian','Buttmannstr.','6','','22159','HAMBURG','DEU','08.10.1942','128868','test@test-mexxon.com\r'),('117679747','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Benjamin','GebrÃ¼der-Grimm-Str.','1','','22399','Hamburg','DEU','29.08.1982','128923','test@test-mexxon.com\r'),('1182175641','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Manuel','Hauptstr.','89','','29582','Hanstedt','DEU','11.10.1985','128907','test@test-mexxon.com\r'),('1224698643','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Andreas','TÃ¼ckingstr.','8','','79618','Rheinfelden (Baden)','DEU','15.10.1972','128846','test@test-mexxon.com\r'),('1224887443','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Stefan','Remigiusplatz','8','','89173','LONSEE','DEU','30.08.1988','128874','test@test-mexxon.com\r'),('1244917472','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','DANIEL','Kofferer Str.','39','','44866','Bochum','DEU','16.04.1987','128818','test@test-mexxon.com\r'),('1263234003','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Nancy','Engelburgergasse','12','','51069','KÃ¶ln','DEU','09.10.1985','128930','test@test-mexxon.com\r'),('1268383412','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sebastian','Hauptstr.','18','','6869','Coswig','DEU','05.01.1987','128898','test@test-mexxon.com\r'),('1277930619','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sabine','ZwiestÃ¤dter Str.','8','','35039','Marburg','DEU','28.06.1983','128889','test@test-mexxon.com\r'),('1285218400','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sarah','Kornblumenstr.','51','','27211','BASSUM','DEU','27.04.1977','128774','test@test-mexxon.com\r'),('1290653839','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Roland','Linnicher Str.','9','','10315','Berlin','DEU','23.12.1980','128862','test@test-mexxon.com\r'),('1301684405','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Tobias','Hildesheimer Str.','135','','58135','Hagen','DEU','15.03.1985','128863','test@test-mexxon.com\r'),('1309520568','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Franziska','Eduard-Bernstein-Str.','20','','42781','Haan','DEU','24.07.1960','128834','test@test-mexxon.com\r'),('134108275','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Ronny','HÃ¼hlen','2','','97528','Sulzdorf','DEU','30.07.1974','128886','test@test-mexxon.com\r'),('1344193359','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Ibrahim','Basilikastr.','7','','7551','Gera','DEU','17.06.1953','128899','test@test-mexxon.com\r'),('134592525','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Tanja','Am Moorweg','17','','34346','Hann. muenden','DEU','25.09.1975','128858','test@test-mexxon.com\r'),('1389676026','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Vanessa','Auangerstr.','10','','55124','Mainz','DEU','27.05.1987','128810','test@test-mexxon.com\r'),('1392417487','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Nils','Hammacherstr.','37','','38518','Gifhorn','DEU','01.07.1971','128921','test@test-mexxon.com\r'),('1394497808','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Dennis','Thomastr.','6','','40764','Langenfeld','DEU','','128798','test@test-mexxon.com\r'),('1409439604','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Gabriele','Kirchendamm','6','','41236','MÃ¶nchengladbach','DEU','27.05.1975','128892','test@test-mexxon.com\r'),('1412199688','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Alexander','Lindhorstweg','83','','44628','HERNE','DEU','02.08.1978','128864','test@test-mexxon.com\r'),('1427892588','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Anja','Krahestr.','36','','92699','Bechtsrieth','DEU','13.10.1985','128870','test@test-mexxon.com\r'),('1448040480','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jacqueline','Heiligenpesch','73','','21698','Brest','DEU','07.03.1989','128835','test@test-mexxon.com\r'),('1465849793','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Matthias','Macherner Str.','1','','1099','DRESDEN','DEU','10.08.1988','128875','test@test-mexxon.com\r'),('1468151863','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Carola','Langgasse','5','','28357','BREMEN','DEU','04.08.1989','128805','test@test-mexxon.com\r'),('1473938695','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Alexander','Mauerstr.','1','','6112','Halle (Saale)','DEU','13.09.1982','128902','test@test-mexxon.com\r'),('1474629689','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Patricia','Gerkerather MÃ¼hle','44','','63820','Elsenfeld','DEU','14.05.1994','128847','test@test-mexxon.com\r'),('1484341904','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Alexandra','Clara-Wieck-Str.','10','','76553','Emsstetten','DEU','30.10.1957','128879','test@test-mexxon.com\r'),('1489327590','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Monika','Grenzhausener Str.','49','','58089','Hagen','DEU','29.12.1982','128928','test@test-mexxon.com\r'),('1505208792','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Isolde','Catenhorner Str.','41','','44789','Bochum','DEU','05.01.1981','128894','test@test-mexxon.com\r'),('1507395594','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Gabriele','Gitschiner Str.','78','','59425','Unna','DEU','07.07.1981','128913','test@test-mexxon.com\r'),('1525118306','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Yvonne','Im GeiÃhorn','4','','63674','Altenstadt','DEU','27.09.1972','128895','test@test-mexxon.com\r'),('1542873237','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Wanda','Glockenlandstr.','10','','54516','Wittlich','DEU','23.05.1979','128826','test@test-mexxon.com\r'),('1546075426','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Mandy','Forsthofstr.','17','','31860','Emmerthal','DEU','27.02.1968','128855','test@test-mexxon.com\r'),('155476787','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Ralf','GrÃ¼nauer Str.','12','','58089','Hagen','DEU','25.11.1961','128853','test@test-mexxon.com\r'),('1560645027','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Mandy','Waldstr.','10','','19055','Schwerin','DEU','10.12.1983','128911','test@test-mexxon.com\r'),('1561852741','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Andr','Siegburger Str.','45','','14727','Premnitz','DEU','28.06.1971','128832','test@test-mexxon.com\r'),('1572068339','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Irina','Eichenhainstr.','18','','6842','Dessau','DEU','07.05.1973','128786','test@test-mexxon.com\r'),('1574949253','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Marc','Gesmolder Str.','1','','19300','Grabow','DEU','31.01.1981','128873','test@test-mexxon.com\r'),('1588557399','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Mandy','SchÃ¶nbergstr.','12','','13403','Berlin','DEU','17.06.1960','128845','test@test-mexxon.com\r'),('1589985354','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jessica','Stiftsstr.','43','','66123','SaarbrÃ¼cken','DEU','30.05.1974','128785','test@test-mexxon.com\r'),('1597182240','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Walter Johann','An der UntermÃ¼hle','84','','41836','HUECKELHOVEN','DEU','10.07.1979','128914','test@test-mexxon.com\r'),('1610468056','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Nicole','HaberswÃ¶hr','3','','32756','Detmold','DEU','30.06.1962','128761','test@test-mexxon.com\r'),('1625294631','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Chris','Criesbacher Str.','1','','26844','Jemgum','DEU','26.06.1989','128915','test@test-mexxon.com\r'),('1629978790','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jessica','Westerholter Weg','43','','86154','Augsburg','DEU','23.02.1992','128772','test@test-mexxon.com\r'),('1642156311','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Thorsten','Zeppelinstr.','5','','6886','Wittenberg','DEU','19.08.1990','128795','test@test-mexxon.com\r'),('1668089131','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Maria','Sandstr.','47','','27283','Verden','DEU','25.01.1993','128854','test@test-mexxon.com\r'),('1692661386','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Christian','Burgstr.','1','','29339','Wathlingen','DEU','25.11.1995','128782','test@test-mexxon.com\r'),('1712845116','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Nicole','Sonnenwiechser Str.','15','','14532','Kleinmachnow','DEU','03.11.1965','128765','test@test-mexxon.com\r'),('1713466883','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Philipp','Sprenger Str.','10','','48599','GRONAU','DEU','23.03.1979','128905','test@test-mexxon.com\r'),('1715164479','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Joanna Evi','Roggenstr.','6','','42115','Wuppertal','DEU','05.03.1958','128843','test@test-mexxon.com\r'),('1730518111','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sven','Am Burgwall','43','','44869','Bochum','DEU','05.07.1980','128779','test@test-mexxon.com\r'),('1753434179','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Necla','Lerchenstr.','22','','18528','Bergen Auf RÃ¼gen','DEU','26.06.1992','128763','test@test-mexxon.com\r'),('1759814762','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Ali','Karl-Marx-Str.','37','','46045','Oberhausen','DEU','02.07.1988','128848','test@test-mexxon.com\r'),('177373811','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Kathrin','Mareschstr.','4','','58644','Iserlohn','DEU','12.06.1990','128824','test@test-mexxon.com\r'),('1774716850','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Melanie','Kirchplatz','3','','21075','Hamburg','DEU','13.08.1957','128833','test@test-mexxon.com\r'),('1780058114','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sandra','Fahnenstr.','15','','89619','Unterstadion','DEU','06.10.1988','128811','test@test-mexxon.com\r'),('1803272585','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Philipp','In den BruchgÃ¤rten','9','','41363','JÃ¼chen','DEU','01.10.1983','128787','test@test-mexxon.com\r'),('1806193077','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sandra','Rigaer Str.','84','','73257','KÃ¶ngen','DEU','01.08.1983','128880','test@test-mexxon.com\r'),('1824668683','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Andreas','Wasserstr.','1','','39124','Magdeburg','DEU','01.05.1984','128823','test@test-mexxon.com\r'),('1842790897','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Rainer','Hindenburgstr.','283','','46117','Oberhausen','DEU','13.03.1990','128820','test@test-mexxon.com\r'),('1845721744','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Gezina','HÃ¶lscherweg','21','','9126','Chemnitz','DEU','05.05.1986','128803','test@test-mexxon.com\r'),('1848722554','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jens','Niederhofstr.','17','','60435','Frankfurt','DEU','06.04.1953','128908','test@test-mexxon.com\r'),('1872191478','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Renate','Gerhart-Hauptmann-Str.','6','','67269','GrÃ¼nstadt','DEU','18.03.1973','128872','test@test-mexxon.com\r'),('1900930660','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Nico','Metzer Str.','5','','26169','FRIESOYTHE','DEU','11.12.1974','128812','test@test-mexxon.com\r'),('1902068451','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Hans-Georg','RÃ¼gener Ring','58','','65385','RÃ¼desheim Am Rhein','DEU','16.01.1979','128800','test@test-mexxon.com\r'),('1906524215','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Angelika','Im Rottfeld','4','','58285','Gevelsberg','DEU','17.10.1986','128909','test@test-mexxon.com\r'),('1923192312','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Halil','Dresdener Str.','3','','59929','Brilon','DEU','11.06.1985','128784','test@test-mexxon.com\r'),('1924829805','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Cimberly','KroogblÃ¶cke','35A','','44652','Herne','DEU','09.10.1970','128883','test@test-mexxon.com\r'),('1927665183','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sebastian','Johann-Meyer-Str.','14','','66687','Wadern','DEU','31.01.1983','128866','test@test-mexxon.com\r'),('1941021836','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jennifer','Kantstr.','24','','21423','Winsen  (Luhe)','DEU','19.05.1990','128841','test@test-mexxon.com\r'),('195079955','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Benjamin','Grabbestr.','14','','90574','RoÃtal','DEU','22.10.1985','128884','test@test-mexxon.com\r'),('1951749224','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Lisa','Von-Stauffenberg-Str.','19','','21244','Buchholz','DEU','10.03.1979','128828','test@test-mexxon.com\r'),('1964778984','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Maria','KroogblÃ¶cke','34B','','23566','LUEBECK','DEU','09.08.1989','128814','test@test-mexxon.com\r'),('1966789931','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Ronny','Brunnenstr.','49','','97618','HOHENROTH','DEU','19.10.1969','128924','test@test-mexxon.com\r'),('1999867761','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jennifer','Bahnhofstr.','81','','81241','MÃ¼nchen','DEU','04.11.1973','128881','test@test-mexxon.com\r'),('2000276544','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sven','Im Siekerfelde','3a','','98701','Neustadt','DEU','10.12.1983','128925','test@test-mexxon.com\r'),('2030615311','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Daniela','Hauptstr.','33A','','42651','Solingen','DEU','02.12.1986','128792','test@test-mexxon.com\r'),('203275192','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jonathan','Kornblumenweg','24','','10777','Berlin','DEU','16.01.1981','128762','test@test-mexxon.com\r'),('2037154767','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sebastian','Belziger Str.','18','','16792','Zehdenick','DEU','11.05.1989','128867','test@test-mexxon.com\r'),('2043141029','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','GÃ¶khan','Lambertus-Kirchplatz','13','','29331','Lachendorf','DEU','30.06.1986','128778','test@test-mexxon.com\r'),('2051111867','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jennifer','Pfeilstr.','11','','13627','Berlin','DEU','17.09.1937','128813','test@test-mexxon.com\r'),('2051553892','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jessica','Amalisweg Etage7re','10','','56428','Dernbach','DEU','31.05.1988','128776','test@test-mexxon.com\r'),('2061108202','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','STEVE','LÃ¼chtenburger Weg','17','','12619','Berlin','DEU','03.12.1965','128896','test@test-mexxon.com\r'),('2067685694','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Birgit','Damer Str.','58','','6295','Lutherstadt Eisleben','DEU','27.01.1989','128922','test@test-mexxon.com\r'),('2074963544','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Patryk','Alte Stuttgarter Str.','28','','47053','Duisburg','DEU','18.09.1984','128793','test@test-mexxon.com\r'),('2129050362','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','GÃ¼lsÃ¼n','Birkenauer Talstr.','57/1','','58239','Schwerte','DEU','23.12.1978','128857','test@test-mexxon.com\r'),('2131392658','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Milica','Joseph-Haydn-Str.','11','','7545','Gera','DEU','02.07.1994','128794','test@test-mexxon.com\r'),('216030771','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Thomas','Bergstr.','8','','89362','OFFINGEN','DEU','26.08.1976','128838','test@test-mexxon.com\r'),('231820966','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Andrea','MÃ¶hringer Str.','37','','52525','Heinsberg','DEU','13.02.1986','128917','test@test-mexxon.com\r'),('233270852','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Mario','Berliner Str.','42','','55118','Mainz','DEU','21.05.1994','128817','test@test-mexxon.com\r'),('240226087','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Mohamed','BrÃ¼nestr.','25','','19322','Wittenberge','DEU','24.11.1984','128918','test@test-mexxon.com\r'),('24340073','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Horst','Glockenblumenweg','52B','','37120','Bovenden','DEU','06.12.1952','128829','test@test-mexxon.com\r'),('249994518','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Kai','Ernst-Lemmer-Str.','40','','41812','Erkelenz','DEU','26.04.1991','128788','test@test-mexxon.com\r'),('254625127','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sebastian','Bandelstr.','18','','45968','Gladbeck','DEU','11.03.1989','128797','test@test-mexxon.com\r'),('277155536','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','BjÃ¶rn Wolfgang','Krebaer Str.','66','','52355','DÃ¼ren','DEU','18.08.1980','128865','test@test-mexxon.com\r'),('29209118','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sascha','Westl Krl-Friedr-Str.','','','6124','Halle (Saale)','DEU','22.09.1980','128796','test@test-mexxon.com\r'),('325105451','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Gabi','Friedrich-Ebert-Str.','281','','34537','BAD WILDUNG.-HUNDSDORF','DEU','02.10.1987','128799','test@test-mexxon.com\r'),('326496104','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Manfred','Scharnhorststr.','5','','52525','Heinsberg','DEU','23.07.1981','128806','test@test-mexxon.com\r'),('329274757','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Tim','Mitscherlichweg','4','','47179','Duisburg','DEU','05.09.1972','128903','test@test-mexxon.com\r'),('338092162','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Markus','MÃ¼hlrather MÃ¼hle','2','','40822','Mettmann','DEU','11.09.1977','128885','test@test-mexxon.com\r'),('345730602','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Denis','Kaarster Str.','232A','','53937','Schleiden','DEU','07.01.1995','128764','test@test-mexxon.com\r'),('349398761','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Marion','Robinienstr.','23','','57555','Mudersbach','DEU','02.07.1990','128758','test@test-mexxon.com\r'),('352454328','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Natasa','Allensteiner Str.','1','','16278','AngermÃ¼nde','DEU','26.03.1984','128839','test@test-mexxon.com\r'),('374846760','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Maik','Victor-Jara-Str.','8','','91058','Erlangen','DEU','04.12.1967','128919','test@test-mexxon.com\r'),('378436','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Anna','Zur BurgmÃ¼hle','40','','79215','Elzach','DEU','26.02.1982','128840','test@test-mexxon.com\r'),('389545088','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Torsten','Illinger Str.','93A','','77830','BÃ¼hlertal','DEU','21.02.1986','128789','test@test-mexxon.com\r'),('393212538','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Enrico','Finkenstr.','21','','94239','Ruhmannsfelden','DEU','03.01.1966','128897','test@test-mexxon.com\r'),('414422301','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Janosch','Zingster Str.','21','','76474','Au','DEU','03.08.1948','128775','test@test-mexxon.com\r'),('444529853','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Samantha','RÃ¶merpfad','57 c','','16225','EBERSWALDE','DEU','05.10.1984','128931','test@test-mexxon.com'),('462848068','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Knut','Gerhart-Hauptmann-Str.','18','','99987','Wertheim','DEU','25.05.1957','128901','test@test-mexxon.com\r'),('47614289','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Patrick','Briener Str.','23','','73230','Kirchheim','DEU','06.05.1987','128900','test@test-mexxon.com\r'),('496088613','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Mirsad','Robert-Koch-Str.','50','','70619','Stuttgart','DEU','05.05.1965','128821','test@test-mexxon.com\r'),('499527286','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Christian','Boxlund','8A','','23970','Wismar','DEU','26.04.1989','128926','test@test-mexxon.com\r'),('506855914','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jessica','Tannenweg','1','','26524','Berumbur','DEU','24.07.1988','128927','test@test-mexxon.com\r'),('518779394','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Frank','Bergstr.','6','','17126','Jarmen','DEU','28.08.1988','128887','test@test-mexxon.com\r'),('532848873','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','BjÃ¶rn','Ernst-ThÃ¤lmann-Str.','51','','39130','Magdeburg','DEU','22.04.1986','128773','test@test-mexxon.com\r'),('556839019','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Marco','Am HeiligenhÃ¤uschen','23','','76316','Malsch','DEU','08.05.1952','128869','test@test-mexxon.com\r'),('558368790','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','RÃ¼diger','An der Alten Eiche','1','','46537','Dinslaken','DEU','10.07.1965','128827','test@test-mexxon.com\r'),('573751593','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Hans-Joachim','Heinrich-Hildebrand-Str.','18','','95131','Schwarzenbach','DEU','07.05.1958','128836','test@test-mexxon.com\r'),('573941817','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Lorena','Herweghring','28','','70469','Stuttgart','DEU','14.02.1987','128801','test@test-mexxon.com\r'),('597020048','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jessica','PÃ¤tzer Str.','15','','12049','Berlin','DEU','06.10.1981','128804','test@test-mexxon.com\r'),('620807018','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','BjÃ¶rn','SteinhÃ¤uÃerstr.','11','','90461','NÃ¼rnberg','DEU','06.02.1987','128850','test@test-mexxon.com\r'),('643603070','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Bilal Resul','Lichtenstein','29','','6132','Halle','DEU','20.07.1985','128893','test@test-mexxon.com\r'),('64528913','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Katharina','Schlesierweg','14','','57072','SIEGEN','DEU','09.05.1995','128808','test@test-mexxon.com\r'),('654885987','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jasmin','SÃ¼dring','20A','','45770','Marl','DEU','20.03.1993','128859','test@test-mexxon.com\r'),('668238766','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Chris','Danziger Str.','9','','50967','KÃ¶ln','DEU','16.10.1982','128837','test@test-mexxon.com\r'),('695803332','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Ute Melanie','Bahnhofstr.','17','','17235','Neustrelitz','DEU','03.09.1983','128809','test@test-mexxon.com\r'),('718078502','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Fabian','Rheinauer Str.','26','','73730','Esslingen am Neckar','DEU','19.01.1976','128766','test@test-mexxon.com\r'),('718403693','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Nadine','Feuerwehrstr.','26','','21107','Hamburg','DEU','','128767','test@test-mexxon.com\r'),('720101159','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jens','Horneburg','29','','50226','Frechen','DEU','26.11.1965','128860','test@test-mexxon.com\r'),('720244815','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Rudi','Duddenhauser Str.','6','','6493','KÃ¶nigerode','DEU','18.09.1985','128844','test@test-mexxon.com\r'),('738132828','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Reiner','Strang','9','','47169','Duisburg','DEU','17.10.1963','128822','test@test-mexxon.com\r'),('752937497','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Toni','Determeyerstr.','79','','17166','Teterow','DEU','22.06.1984','128904','test@test-mexxon.com\r'),('753260465','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Thorsten','HaÃlocher Str.','24','','44534','LÃ¼nen','DEU','17.09.1979','128770','test@test-mexxon.com\r'),('758223972','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Johanna','Lorscher Str.','6','','32351','Stemwede','DEU','20.02.1961','128871','test@test-mexxon.com\r'),('765459942','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Nadine','Eschenweg','19','','42287','Wuppertal','DEU','18.06.1988','128780','test@test-mexxon.com\r'),('767244685','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Derya','Bassenheimer Str.','83','','55450','Langenlonsheim','DEU','14.12.1984','128878','test@test-mexxon.com\r'),('786058105','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Angelo','Parkstr.','2','','21335','LÃ¼neburg','DEU','16.06.1950','128807','test@test-mexxon.com\r'),('789397993','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Diana','Unterglinder Weg','49','','12435','Berlin','DEU','25.02.1973','128906','test@test-mexxon.com\r'),('801753628','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Hans-Christian','Neuwiedenthaler Str.','40','','6386','Osternienburger Land','DEU','10.07.1971','128929','test@test-mexxon.com\r'),('80317627','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Silvia','Belforter Str.','13','','49090','OsnabrÃ¼ck','DEU','21.10.1984','128849','test@test-mexxon.com\r'),('811058247','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Irena','Gneisenaustr.','27','','6333','WELFESHOLZ','DEU','11.05.1977','128882','test@test-mexxon.com\r'),('819503834','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Werner','Ringenwalder Str.','12','','45219','ESSEN','DEU','02.09.1958','128861','test@test-mexxon.com\r'),('827052510','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jones','Patt','27','','6237','LEUNA','DEU','27.03.1979','128852','test@test-mexxon.com\r'),('844340179','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Martin','Mecklenburgische Str.','7','','47829','Krefeld','DEU','19.03.1983','128816','test@test-mexxon.com\r'),('847521778','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sabrina','Jaspertstr.','29','','41812','Erkelenz','DEU','19.04.1990','128916','test@test-mexxon.com\r'),('875298955','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Bernd','Grabenstr.','1','','59199','BÃ¶nen','DEU','04.08.1972','128777','test@test-mexxon.com\r'),('906036987','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sevgi','JÃ¤gerstr.','33','','4420','MarkranstÃ¤dt','DEU','24.08.1971','128830','test@test-mexxon.com\r'),('908054030','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Michael','Goethestr.','8','','66121','SaarbrÃ¼cken','DEU','01.03.1990','128819','test@test-mexxon.com\r'),('926027371','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Christina','Stettiner Str.','7','','33378','RHEDA-WIEDENBRUECK','DEU','24.04.1972','128790','test@test-mexxon.com\r'),('926251011','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Andreas','Wildenbruchstr.','24','','66903','Altenkirchen','DEU','20.01.1986','128910','test@test-mexxon.com\r'),('951413308','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Jens','Amtsstr.','19','','99867','Gotha','DEU','02.05.1986','128825','test@test-mexxon.com\r'),('97553655','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Sandra','GleiÃhammerstr.','53','','41236','MÃ¶nchengladbach','DEU','07.01.1988','128759','test@test-mexxon.com\r'),('978497555','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Teresa Vera','Rendsburger Landstr.','57','','45891','Gelsenkirchen','DEU','04.03.1982','128781','test@test-mexxon.com\r'),('98347530','ABI','201601','81501','815','FEMALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Heinz-Dieter','Kamenzer Str.','10A','','78176','Blumberg','DEU','03.07.1987','128815','test@test-mexxon.com\r'),('986331568','ABI','201601','81501','815','MALE','XXXXXXXX','mm,Ã¼pklhÃ¶mh','Tania','Leineweberstr.','49','','36391','Sinntal','DEU','02.01.1983','128876','test@test-mexxon.com\r'),('Client_Order','POI','ProductID','ClientAccountID','AccountID','Gender','LastName','MaidenName','firstName','Street','House','HouseADD','ZIP','City','Country','DOB','Phone','Email\r');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provider` (
  `id` int(11) NOT NULL,
  `Name` varchar(55) DEFAULT NULL,
  `Stufe` varchar(255) DEFAULT NULL,
  `SubID` int(11) NOT NULL,
  `PriceIn` decimal(10,4) DEFAULT NULL,
  `PriceOut` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`id`,`SubID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'importexport'
--

--
-- Dumping routines for database 'importexport'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-06 16:05:00
