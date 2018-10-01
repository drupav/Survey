-- MySQL dump 10.13  Distrib 5.5.48, for Win64 (x86)
--
-- Host: localhost    Database: survey
-- ------------------------------------------------------
-- Server version	5.5.48

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
-- Table structure for table `awc`
--

DROP TABLE IF EXISTS `awc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `awc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `awc_code` varchar(50) DEFAULT NULL,
  `awc_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `awc`
--

LOCK TABLES `awc` WRITE;
/*!40000 ALTER TABLE `awc` DISABLE KEYS */;
INSERT INTO `awc` VALUES (1,'ac1','awc_1');
/*!40000 ALTER TABLE `awc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `block`
--

DROP TABLE IF EXISTS `block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `block` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `block_code` int(20) DEFAULT NULL,
  `district_id` bigint(20) DEFAULT NULL,
  `block_name` varchar(140) DEFAULT NULL,
  `total_samples` bigint(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dist_fk` (`district_id`),
  CONSTRAINT `dist_fk` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block`
--

LOCK TABLES `block` WRITE;
/*!40000 ALTER TABLE `block` DISABLE KEYS */;
INSERT INTO `block` VALUES (5,11,1,'ARWAL',125),(6,12,1,'KALER',125),(7,13,1,'KARPI',125),(8,14,1,'KURTHA',125),(9,15,1,'SONBHADRA_BANSHI_SURYAPUR',125),(10,21,2,'BARHARA',125),(11,22,2,'BIHIYA',125),(12,23,2,'CHARPOKHARI',125),(13,24,2,'JAGDISHPUR',125),(14,25,2,'KOILWAR',125),(15,31,3,'GHOSHI',125),(16,32,3,'KAKO',125),(17,33,3,'MAKHDUMPUR',125),(18,34,3,'MODANGANJ',125),(19,35,3,'RATNI_FARIDPUR',125),(20,41,4,'BIND',125),(21,42,4,'ISLAMPUR',125),(22,43,4,'RAJGIR',125),(23,44,4,'SILAO',125),(24,45,4,'THARTHARI',125),(25,51,5,'AMAS',125),(26,52,5,'BELAGANJ',125),(27,53,5,'GURARU',125),(28,54,5,'MOHRA',125),(29,55,5,'NEEMCHAK_BATHANI',125),(30,61,6,'BARUN',125),(31,62,6,'HASPURA',125),(32,63,6,'KUTUMBA',125),(33,64,6,'MADANPUR',125),(34,65,6,'OBRA',125),(45,71,7,'BRAHMPUR',125),(46,72,7,'BUXAR',125),(47,73,7,'CHOUGAIN',125),(48,74,7,'DUMRAON',125),(49,75,7,'NAWANAGAR',125),(50,81,8,'BARBIGHA',125),(51,82,8,'CHEWARA',125),(52,83,8,'GHATKUSHUMBA',125),(53,84,8,'SHEIKHOPUR_SARAI',125),(54,85,8,'SHEIKHPURA',125),(55,91,9,'FORBESGANJ',125),(56,92,9,'JOKIHAT',125),(57,93,9,'NARPATGANJ',125),(58,94,9,'RANIGANJ',125),(59,95,9,'SIKTI',125),(60,101,10,'BANKA',125),(61,102,10,'BARAHAT',125),(62,103,10,'BELHAR',125),(63,104,10,'FULLIDUMER',125),(64,105,10,'SHAMBHUGANJ',125),(65,111,11,'BARAUNI',125),(66,112,11,'BEGUSARAI',125),(67,113,11,'BIRPUR',125),(68,114,11,'CHHOURAHI',125),(69,115,11,'KHODABANDPUR',125),(70,121,12,'GORADIH',125),(71,122,12,'ISMAILPUR',125),(72,123,12,'JAGDISHPUR',125),(73,124,12,'SANHAULA',125),(74,125,12,'SHAHKUND',125),(75,131,13,'ALINAGAR',125),(76,132,13,'BENIPUR',125),(77,133,13,'KIRATPUR',125),(78,134,13,'SINGHWARA',125),(79,135,13,'TARDIH',125),(80,141,14,'BANJARIYA',125),(81,142,14,'DHAKA',125),(82,143,14,'HARSIDDHI',125),(83,144,14,'KESARIA',125),(84,145,14,'PIPRAKOTHI',125),(85,151,15,'GOPALGANJ',125),(86,152,15,'PANCHDEVARI',125),(87,153,15,'PHULWARIA',125),(88,154,15,'UCHKAGAON',125),(89,155,15,'VIJAIPUR',125),(90,161,16,'BARHAT',125),(91,162,16,'JAMUI',125),(92,163,16,'JHAJHA',125),(93,164,16,'LAXMIPUR',125),(94,165,16,'SONO',125),(95,171,17,'CHAINPUR',125),(96,172,17,'DURGAWATI',125),(97,173,17,'KUDRA',125),(98,174,17,'MOHANIA',125),(99,175,17,'NUAON',125),(100,181,18,'BARARI',125),(101,182,18,'DANDKHORA',125),(102,183,18,'FALKA',125),(103,184,18,'HASANGANJ',125),(104,185,18,'PRANPUR',125),(105,191,19,'BELDAUR',125),(106,192,19,'GOGARI',125),(107,193,19,'KHAGARIA',125),(108,194,19,'MANSI',125),(109,195,19,'PARBATTA',125),(110,201,20,'BAHADURGANJ',125),(111,202,20,'DIGHALBANK',125),(112,203,20,'KOCHADHAMAN',125),(113,204,20,'TERAGACHH',125),(114,205,20,'THAKURGANJ',125),(115,211,21,'HALSI',125),(116,212,21,'LAKHISARAI',125),(117,213,21,'PIPARIYA',125),(118,214,21,'RAMGARH_CHOWK',125),(119,215,21,'SURYAGARHA',125),(120,221,22,'BIHARIGANJ',125),(121,222,22,'GAMHARIYA',125),(122,223,22,'KUMARKHAND',125),(123,224,22,'PURANI',125),(124,225,22,'SINGHESWAR',125),(125,231,23,'BASOPATTI',125),(126,232,23,'LAUKAHA_KHUTAUNA',125),(127,233,23,'LAUKAHI',125),(128,234,23,'MADHEPUR',125),(129,235,23,'PANDAUL',125),(130,241,24,'DHARHARA',125),(131,242,24,'MUNGER',125),(132,243,24,'SANGRAMPUR',125),(133,244,24,'TARAPUR',125),(134,245,24,'TETIYABAMBAR',125),(135,251,25,'AURAI',125),(136,252,25,'KATRA',125),(137,253,25,'KURHANI',125),(138,254,25,'MARWAN',125),(139,255,25,'SAKRA',125),(140,261,26,'GOVINDPUR',125),(141,262,26,'KAUAKOLE',125),(142,263,26,'NARDIGANJ',125),(143,264,26,'NARHAT',125),(144,265,26,'RAJAULI',125),(145,271,27,'ATHMALGOLA',125),(146,272,27,'BAKHTIARPUR',125),(147,273,27,'BIHTA',125),(148,274,27,'DHANARUA',125),(149,275,27,'PALIGANJ',125),(150,281,28,'DAGARUA',125),(151,282,28,'DHAMDAHA',125),(152,283,28,'JALALGARH',125),(153,284,28,'KRITYANAND_NAGAR',125),(154,285,28,'RUPAULI',125),(155,291,29,'BIKRAMGANJ',125),(156,292,29,'DINARA',125),(157,293,29,'KOCHAS',125),(158,294,29,'NASRIGANJ',125),(159,295,29,'NAUHATTA',125),(160,301,30,'BANMA_ITAHRI',125),(161,302,30,'SALKHUA',125),(162,303,30,'SAUR_BAZAR',125),(163,304,30,'SIMRI_BAKHTIYARPUR',125),(164,305,30,'SONBARSA',125),(165,311,31,'BIBHUTIPUR',125),(166,312,31,'HASANPUR',125),(167,313,31,'KHANPUR',125),(168,314,31,'SINGHIA',125),(169,315,31,'TAJPUR',125),(170,321,32,'DARIYAPUR',125),(171,322,32,'MAKER',125),(172,323,32,'PANAPUR',125),(173,324,32,'REVILGANJ',125),(174,325,32,'TARAIYA',125),(175,331,33,'DUMARI_KATSARAI',125),(176,332,33,'PIPRAHI',125),(177,333,33,'PURNAHIYA',125),(178,334,33,'SHEOHAR',125),(179,335,33,'TARIYANI',125),(180,341,34,'DUMRA',125),(181,342,34,'NANPUR',125),(182,343,34,'RIGA',125),(183,344,34,'SUPPI',125),(184,345,34,'SURSAND',125),(185,351,35,'BARHARIYA',125),(186,352,35,'DARAUNDHA',125),(187,353,35,'HASANPURA',125),(188,354,35,'HUSSAINGANJ',125),(189,355,35,'RAGHUNATHPUR',125),(190,361,36,'BASANTPUR',125),(191,362,36,'PIPRA',125),(192,363,36,'PRATAPGANJ',125),(193,364,36,'SARAIGARH_BHAPT',125),(194,365,36,'TRIVENIGANJ',125),(195,371,37,'BHAGWANPUR',125),(196,372,37,'CHEHRAKALA',125),(197,373,37,'HAJIPUR',125),(198,374,37,'MAHUA',125),(199,375,37,'PATEPUR',125),(200,381,38,'BAGHA_I',125),(201,382,38,'BAGHA_II',125),(202,383,38,'GAUNAHA',125),(203,384,38,'MADHUBANI',125),(204,385,38,'SIKTA',125);
/*!40000 ALTER TABLE `block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choices`
--

DROP TABLE IF EXISTS `choices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `choices` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(40) DEFAULT NULL,
  `poll_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1i68hpih40n447wqx4lpef6ot` (`poll_id`),
  CONSTRAINT `FK1i68hpih40n447wqx4lpef6ot` FOREIGN KEY (`poll_id`) REFERENCES `polls` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choices`
--

LOCK TABLES `choices` WRITE;
/*!40000 ALTER TABLE `choices` DISABLE KEYS */;
/*!40000 ALTER TABLE `choices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `district` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `region_id` bigint(20) DEFAULT NULL,
  `district_code` int(20) NOT NULL,
  `district_name` varchar(140) DEFAULT NULL,
  `total_samples` bigint(50) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `region_fk` (`region_id`),
  CONSTRAINT `region_fk` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (1,1,1,'ARWAL',600,NULL,NULL,NULL,NULL),(2,1,2,'BHOJPUR',600,NULL,NULL,NULL,NULL),(3,1,4,'NALANDA',600,NULL,NULL,NULL,NULL),(4,1,3,'JEHANABAD',600,NULL,NULL,NULL,NULL),(5,1,5,'GAYA',600,NULL,NULL,NULL,NULL),(6,1,6,'AURANGABAD',600,NULL,NULL,NULL,NULL),(7,1,7,'BUXAR',600,NULL,NULL,NULL,NULL),(8,1,8,'SHEIKHPURA',600,NULL,NULL,NULL,NULL),(9,1,9,'ARARIA',600,NULL,NULL,NULL,NULL),(10,1,10,'BANKA',600,NULL,NULL,NULL,NULL),(11,1,11,'BEGUSARAI',600,NULL,NULL,NULL,NULL),(12,1,12,'BHAGALPUR',600,NULL,NULL,NULL,NULL),(13,1,13,'DARBHANGA',600,NULL,NULL,NULL,NULL),(14,1,14,'EAST_CHAMPARAN',600,NULL,NULL,NULL,NULL),(15,1,15,'GOPALGANJ',600,NULL,NULL,NULL,NULL),(16,1,16,'JAMUI',600,NULL,NULL,NULL,NULL),(17,1,17,'KAIMUR',600,NULL,NULL,NULL,NULL),(18,1,18,'KATIHAR',600,NULL,NULL,NULL,NULL),(19,1,19,'KHAGARIA',600,NULL,NULL,NULL,NULL),(20,1,20,'KISHANGANJ',600,NULL,NULL,NULL,NULL),(21,1,21,'LAKHISARAI',600,NULL,NULL,NULL,NULL),(22,1,22,'MADHEPURA',600,NULL,NULL,NULL,NULL),(23,1,23,'MADHUBANI',600,NULL,NULL,NULL,NULL),(24,1,24,'MUNGER',600,NULL,NULL,NULL,NULL),(25,1,25,'MUZAFFARPUR',600,NULL,NULL,NULL,NULL),(26,1,26,'NAWADA',600,NULL,NULL,NULL,NULL),(27,1,27,'PATNA',600,NULL,NULL,NULL,NULL),(28,1,28,'PURNIA',600,NULL,NULL,NULL,NULL),(29,1,29,'ROHTAS',600,NULL,NULL,NULL,NULL),(30,1,30,'SAHARSA',600,NULL,NULL,NULL,NULL),(31,1,31,'SAMASTIPUR',600,NULL,NULL,NULL,NULL),(32,1,32,'SARAN',600,NULL,NULL,NULL,NULL),(33,1,33,'SHEOHAR',600,NULL,NULL,NULL,NULL),(34,1,34,'SITAMARHI',600,NULL,NULL,NULL,NULL),(35,1,35,'SIWAN',600,NULL,NULL,NULL,NULL),(36,1,36,'SUPAUL',600,NULL,NULL,NULL,NULL),(37,1,37,'VAISHALI',600,NULL,NULL,NULL,NULL),(38,1,38,'WEST_CHAMPARAN',600,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_upload`
--

DROP TABLE IF EXISTS `file_upload`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file_upload` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(140) DEFAULT NULL,
  `upload_date` datetime DEFAULT NULL,
  `file_location` varchar(140) DEFAULT NULL,
  `upload_type` varchar(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `comments` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_upload`
--

LOCK TABLES `file_upload` WRITE;
/*!40000 ALTER TABLE `file_upload` DISABLE KEYS */;
INSERT INTO `file_upload` VALUES (1,'mwra final_WIDE.csv','2018-09-24 00:00:00','C:\\Users\\home\\.survey\\Documents\\75h5nluge63rr5\\mwra final_WIDE.csv','ALL_SUBMISSION','2018-09-24 17:27:10','2018-09-24 17:27:10',1,1,'undefined');
/*!40000 ALTER TABLE `file_upload` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interviewer`
--

DROP TABLE IF EXISTS `interviewer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interviewer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `interviewer_name` varchar(140) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interviewer`
--

LOCK TABLES `interviewer` WRITE;
/*!40000 ALTER TABLE `interviewer` DISABLE KEYS */;
INSERT INTO `interviewer` VALUES (1,'Rajesh'),(2,'vinod');
/*!40000 ALTER TABLE `interviewer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `polls`
--

DROP TABLE IF EXISTS `polls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `polls` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `expiration_date_time` datetime NOT NULL,
  `question` varchar(140) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `polls`
--

LOCK TABLES `polls` WRITE;
/*!40000 ALTER TABLE `polls` DISABLE KEYS */;
/*!40000 ALTER TABLE `polls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_check`
--

DROP TABLE IF EXISTS `quality_check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_check` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `interviewer_name` varchar(140) DEFAULT NULL,
  `district_id` bigint(20) DEFAULT NULL,
  `block_id` bigint(20) DEFAULT NULL,
  `village_name` varchar(140) DEFAULT NULL,
  `ward_no` varchar(50) DEFAULT NULL,
  `ward_name` varchar(140) DEFAULT NULL,
  `awc_no` bigint(20) DEFAULT NULL,
  `awc_name` varchar(140) DEFAULT NULL,
  `accompaniment` char(20) DEFAULT NULL,
  `spot_check` char(1) DEFAULT '0',
  `back_check` char(1) DEFAULT '0',
  `audio_check` char(1) DEFAULT '0',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `duration` bigint(20) DEFAULT NULL,
  `submission_date` date DEFAULT NULL,
  `ur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `quality_district_fk` (`district_id`),
  KEY `quality_block_fk` (`block_id`),
  KEY `FK77gwtn8dsfnr64x0mslc4wxsq` (`ur_id`),
  CONSTRAINT `FK77gwtn8dsfnr64x0mslc4wxsq` FOREIGN KEY (`ur_id`) REFERENCES `ur_code` (`id`),
  CONSTRAINT `FKhs6vlyxhec8s9k33dsr7afa29` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`),
  CONSTRAINT `FKkvf0nt4k2ouvou2rshjv76bho` FOREIGN KEY (`block_id`) REFERENCES `block` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quality_check`
--

LOCK TABLES `quality_check` WRITE;
/*!40000 ALTER TABLE `quality_check` DISABLE KEYS */;
INSERT INTO `quality_check` VALUES (1,'2018-01-04 14:46:00','2018-01-04 14:50:00','Ritu',2,NULL,'Araila','','Anita devi',3,'Sarita devi','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(2,'2018-01-04 12:10:00','2018-01-04 17:40:00','Sudha kumari',2,NULL,'Nause tath mathiya','','Nause tath mathiya',33,'Hari paswan','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(3,'2018-01-04 15:37:00','2018-01-04 17:40:00','Arti kumari',2,NULL,'Nause tath mathiya','','Nause tath mathiya',33,'Shatrughan ray','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(4,'2018-01-04 17:10:00','2018-01-04 17:41:00','Poonam kushwah',2,NULL,'Ghagha','','Ghagha',34,'Vighana mahto','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(5,'2018-01-04 17:21:00','2018-01-04 17:41:00','Poonam kushwah',2,NULL,'Kuwrdah','','Kuwrdah',30,'Ramjanam ray','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(6,'2018-01-04 14:44:00','2018-01-04 18:08:00','Annu devi',2,NULL,'Basauna','','Anganwadi kendra Basauna',30,'Sarwar ali','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(7,'2018-01-04 10:12:00','2018-01-04 21:32:00','Soni kumar 1',3,NULL,'Rural','','Bebi kumari',61,'Mewa chodhri','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(8,'2018-01-04 12:15:00','2018-01-04 21:33:00','Sangita kumari',3,NULL,'Diha','','Bebi kumari',61,'Dhuri rbidas','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(9,'2018-01-04 14:47:00','2018-01-04 21:33:00','Soni kumari 2',3,NULL,'Rurl','','Renu devi',104,'Ashok kumar','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(10,'2018-01-04 18:06:00','2018-01-04 21:33:00','Nisha kumari',3,NULL,'Rurl','','Anita devi',68,'Munilal yady','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(11,'2018-01-04 13:30:00','2018-01-04 21:37:00','Kumari kamlesh',2,NULL,'Narayanpur','','Awc narayanpur',31,'Rajdev rai','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(12,'2018-01-04 14:49:00','2018-01-04 21:38:00','Jyoti yadav',2,NULL,'Narayanpur','','Awc narayanpur',31,'Mandarika prasad','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(13,'2018-01-04 21:36:00','2018-01-04 21:42:00','Nisha kumari',3,NULL,'Salempur','','Lalmani kumari',105,'Ram prakash shrama','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(14,'2018-01-04 09:54:00','2018-01-04 21:44:00','Hemlata',1,NULL,'Rampur waina west','','Rampur waina west',114,'Rajendar singh','N','N','Y','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(15,'2018-01-04 15:37:00','2018-01-04 21:44:00','Hemlata',1,NULL,'Rampur waina west','','Rampur waina west',114,'Sunil singh','N','N','Y','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(16,'2018-01-04 09:32:00','2018-01-04 22:33:00',NULL,NULL,NULL,'','','',NULL,'','N','N','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,NULL),(17,'2018-01-04 14:52:00','2018-01-04 22:33:00','Kumari ragini',2,NULL,'Araila paschmi bhag','','Araila paschmi bhag',4,'Sunil shigh','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(18,'2018-01-04 14:37:00','2018-01-04 22:38:00','Kumari Ragni',2,NULL,'Araila','','Anita devi',3,'Santosh Kumar ram','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(19,'2018-01-04 15:36:00','2018-01-04 22:58:00','Puspanjali puja',2,NULL,'Saraya lal ka tola','','Saraya lal ka tola',4,'Sribhavan yadav','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(20,'2018-01-04 22:36:00','2018-01-04 23:02:00',NULL,NULL,NULL,'','','',NULL,'','N','N','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,NULL),(21,'2018-01-04 13:07:00','2018-01-04 13:26:00','Chalchal kumari',1,NULL,'Repura','','Repura',58,'Rameswer yadaw','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(22,'2018-01-04 13:27:00','2018-01-04 23:13:00','Chanlchanl',1,NULL,'Repura','','Repura',58,'Rameshwer yadaw','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(23,'2018-01-04 09:12:00','2018-01-04 23:14:00','Bibha kumari',1,NULL,'Samahariya','','Samahriya',85,'Sachidananad ray','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(24,'2018-01-04 17:33:00','2018-01-04 23:15:00','Bibha kumari',1,NULL,'Samahriya','','Samahriya',85,'Guru dev ray','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(25,'2018-01-04 21:26:00','2018-01-04 23:18:00','Richa rachana',1,NULL,'Wardali bigha','','Wardali bigha',86,'Arjun paswan','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(26,'2018-01-04 21:42:00','2018-01-04 23:18:00','Bibha kumari',1,NULL,'Wardali bigha','','Wardali bigha',86,'Ambika singh','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(27,'2018-01-04 13:26:00','2018-01-04 23:27:00','Ruby devi',1,NULL,'Koni','','Koni',2,'Mukesh das','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(28,'2018-01-04 12:14:00','2018-01-05 00:05:00','Rubi',3,NULL,'Shree nagar','','Savita',14,'Krishna shahu','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(29,'2018-01-04 12:45:00','2018-01-05 00:06:00','Rubi',3,NULL,'Shree nagar','','Savita',14,'Naval singh','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(30,'2018-01-04 15:34:00','2018-01-05 00:05:00','Rubi',3,NULL,'Mukundan bigha','','Baby kumari',74,'Sudama prasad','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(31,'2018-01-04 17:03:00','2018-01-05 00:05:00','Rubi',3,NULL,'Mukundan bigha','','Baby kumari',74,'Guru prasad','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(32,'2018-01-04 11:10:00','2018-01-04 21:37:00','Kalyani kumari',1,NULL,'Manepakar','','Manepakar',28,'Sudeshwar manjhi','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(33,'2018-01-04 17:47:00','2018-01-04 21:37:00','Renu kumari',1,NULL,'Pinjrawan','','Fahmina khatun',32,'Rubi khatun','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,2),(34,'2018-01-04 14:10:00','2018-01-05 09:44:00','Aaditi satya',4,NULL,'Bramharshi nagar','6','',NULL,'Shahikant kumar','N','Y','N','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,1),(35,'2018-01-04 15:48:00','2018-01-05 09:45:00','Suman',4,NULL,'Bramharshi nagar','6','',NULL,'Sugriv sharma','N','N','Y','N','2018-09-17 18:14:35','2018-09-17 18:14:35',1,1,NULL,NULL,1);
/*!40000 ALTER TABLE `quality_check` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `region_name` varchar(140) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (1,'Telangana');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respondent`
--

DROP TABLE IF EXISTS `respondent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `respondent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `district_id` bigint(20) DEFAULT NULL,
  `block_id` bigint(20) DEFAULT NULL,
  `awc_code` varchar(50) DEFAULT NULL,
  `village_name` varchar(140) DEFAULT NULL,
  `interviewer_name` varchar(50) DEFAULT NULL,
  `respondent_name` varchar(50) DEFAULT NULL,
  `submission_date` datetime DEFAULT NULL,
  `sample_num` bigint(20) DEFAULT NULL,
  `hsc_name` varchar(50) DEFAULT NULL,
  `ward_id` bigint(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `contact_num` varchar(50) DEFAULT NULL,
  `result_status` varchar(50) DEFAULT NULL,
  `audio` text,
  `start_time` datetime DEFAULT NULL,
  `ur_id` bigint(20) DEFAULT NULL,
  `duration` bigint(50) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `awc_name` varchar(50) DEFAULT NULL,
  `serial_num` bigint(50) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `interviewer_code` varchar(50) DEFAULT NULL,
  `household_rel` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1uw4duwvwriog1yo3ore6co9v` (`interviewer_name`),
  KEY `FKegwp5ko00nxd3nfgr1knit8xt` (`awc_code`),
  KEY `FKlnsvy88oj0y79tnr1snyy8pai` (`district_id`),
  KEY `FKvpj3p0v9x7d0gj6egpwlokvo` (`block_id`),
  KEY `ward_fk` (`ward_id`),
  KEY `FKq3e4jgrw6ts50el136yj874sk` (`ur_id`),
  CONSTRAINT `FKlnsvy88oj0y79tnr1snyy8pai` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`),
  CONSTRAINT `FKq3e4jgrw6ts50el136yj874sk` FOREIGN KEY (`ur_id`) REFERENCES `ur_code` (`id`),
  CONSTRAINT `FKvpj3p0v9x7d0gj6egpwlokvo` FOREIGN KEY (`block_id`) REFERENCES `block` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respondent`
--

LOCK TABLES `respondent` WRITE;
/*!40000 ALTER TABLE `respondent` DISABLE KEYS */;
/*!40000 ALTER TABLE `respondent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'ROLE_ADMIN'),(1,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ur_code`
--

DROP TABLE IF EXISTS `ur_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ur_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ur_code` bigint(50) DEFAULT NULL,
  `ur_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ur_code`
--

LOCK TABLES `ur_code` WRITE;
/*!40000 ALTER TABLE `ur_code` DISABLE KEYS */;
INSERT INTO `ur_code` VALUES (1,1,'Urban'),(2,2,'Rural');
/*!40000 ALTER TABLE `ur_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (5,1),(1,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  `is_active` tinyint(20) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2018-07-28 16:54:06','2018-07-28 16:54:06','vinod2k11@gmail.com','vinod pothapogu','$2a$10$k8mCXygX7dAI/O5U4jllsegPRHhgJkH7eoTfgZxA2ocWYu1CbMkm2','test',1),(5,'2018-09-17 18:15:10','2018-09-17 18:15:10','kiran@gmail.com','kiran','$2a$10$jME7dsTmoiCIiZ9Txw.ntelEAUFpX0xCTNx1cO93/U4sNvvHOym1u','kiran',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `votes`
--

DROP TABLE IF EXISTS `votes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `votes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `choice_id` bigint(20) NOT NULL,
  `poll_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8um9h2wxsdjrgx3rjjwvny676` (`poll_id`,`user_id`),
  KEY `FKomskymhxde3qq9mcukyp1puio` (`choice_id`),
  KEY `FKli4uj3ic2vypf5pialchj925e` (`user_id`),
  CONSTRAINT `FK7trt3uyihr4g13hva9d31puxg` FOREIGN KEY (`poll_id`) REFERENCES `polls` (`id`),
  CONSTRAINT `FKli4uj3ic2vypf5pialchj925e` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKomskymhxde3qq9mcukyp1puio` FOREIGN KEY (`choice_id`) REFERENCES `choices` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `votes`
--

LOCK TABLES `votes` WRITE;
/*!40000 ALTER TABLE `votes` DISABLE KEYS */;
/*!40000 ALTER TABLE `votes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ward`
--

DROP TABLE IF EXISTS `ward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ward` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ward_name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward`
--

LOCK TABLES `ward` WRITE;
/*!40000 ALTER TABLE `ward` DISABLE KEYS */;
INSERT INTO `ward` VALUES (1,'ward1'),(2,'ward7');
/*!40000 ALTER TABLE `ward` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-24 23:54:18
