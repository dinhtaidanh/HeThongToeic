CREATE DATABASE  IF NOT EXISTS `toeicsystem` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `toeicsystem`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: toeicsystem
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `dienkhuyet`
--

DROP TABLE IF EXISTS `dienkhuyet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dienkhuyet` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CauHoi` text COLLATE utf8_unicode_ci NOT NULL,
  `DapAn1` text COLLATE utf8_unicode_ci,
  `DapAn2` text COLLATE utf8_unicode_ci,
  `DapAn3` text COLLATE utf8_unicode_ci,
  `DapAn4` text COLLATE utf8_unicode_ci,
  `DapAnDung` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dienkhuyet`
--

LOCK TABLES `dienkhuyet` WRITE;
/*!40000 ALTER TABLE `dienkhuyet` DISABLE KEYS */;
INSERT INTO `dienkhuyet` VALUES (1,'In a formal interview, it is essential to maintain good eye_______with the interviewers.','tough','link','contact','connection','C'),(2,'The grape is the ___________ , juicy fruit of a woody vine.','skin','which is smooth','smooth skin',' smooth-skinned','D'),(3,'Multicolored woodcuts must be printed with as many blocks as _______ colors in the composition.','there are','many','some of','it is','A'),(4,'The greater an objects\'s mass, the more difficult it is ____________ .','to speed it up or slow it down','it speeds up or slows down','than speeding it up or slowing it down','than speeding up or slowing down','A'),(5,'In the second half of the nineteenth century, textiles from the southwestern United state, particularly fabrics woven by theNavajo people, _____________________ .','began to be used as rugs','rugs began to be used','as rugs began to be used','began to used them as rugs','A'),(6,'During adolescence many young people begin to question ------ held by their families.','the values','of the values','the values are','are the values','A'),(7,'In her writing, Elimor Wylie often dealt with her own personality as it was, rather than _________ .','as was defines by others','its definitions by others','other\'s definition','as others defined it','D'),(8,'Congress chartered the first Bank of the United States in 1791 to engage in general commercialbanking and __________ as a fiscal agent of the federal government.','to act','acting','that has acted','having acted','A'),(9,'Essentially, a theory is an abstract, symbolic representation of _____________ reality.','what is conceived','what it is conceived','what is conceived to be','what is being conceived','C'),(10,'The more arid the continent, the less the amount of annual precipitation _______________ .','runs off that','runs it off','that runs it off','that runs off','D');
/*!40000 ALTER TABLE `dienkhuyet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `luyennghe`
--

DROP TABLE IF EXISTS `luyennghe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `luyennghe` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `DapAn` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `LinkAudio` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `LinkPhoto` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `luyennghe`
--

LOCK TABLES `luyennghe` WRITE;
/*!40000 ALTER TABLE `luyennghe` DISABLE KEYS */;
INSERT INTO `luyennghe` VALUES (1,'C','C:/Users/Danh/Desktop/HeThongToeic/src/media/1.mp3','C:/Users/Danh/Desktop/HeThongToeic/src/media/1.jpg'),(2,'C','C:/Users/Danh/Desktop/HeThongToeic/src/media/2.mp3','C:/Users/Danh/Desktop/HeThongToeic/src/media/2.jpg'),(4,'D','C:/Users/Danh/Desktop/HeThongToeic/src/media/3.mp3','C:/Users/Danh/Desktop/HeThongToeic/src/media/3.jpg'),(5,'C','C:/Users/Danh/Desktop/HeThongToeic/src/media/4.mp3','C:/Users/Danh/Desktop/HeThongToeic/src/media/4.jpg'),(6,'D','C:/Users/Danh/Desktop/HeThongToeic/src/media/5.mp3','C:/Users/Danh/Desktop/HeThongToeic/src/media/5.jpg'),(7,'B','C:/Users/Danh/Desktop/HeThongToeic/src/media/6.mp3','C:/Users/Danh/Desktop/HeThongToeic/src/media/6.jpg'),(8,'B','C:/Users/Danh/Desktop/HeThongToeic/src/media/7.mp3','C:/Users/Danh/Desktop/HeThongToeic/src/media/7.jpg'),(9,'D','C:/Users/Danh/Desktop/HeThongToeic/src/media/8.mp3','C:/Users/Danh/Desktop/HeThongToeic/src/media/8.jpg'),(10,'B','C:/Users/Danh/Desktop/HeThongToeic/src/media/9.mp3','C:/Users/Danh/Desktop/HeThongToeic/src/media/9.jpg'),(11,'C','C:/Users/Danh/Desktop/HeThongToeic/src/media/10.mp3','C:/Users/Danh/Desktop/HeThongToeic/src/media/10.jpg');
/*!40000 ALTER TABLE `luyennghe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguphap`
--

DROP TABLE IF EXISTS `nguphap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nguphap` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CauHoi` text COLLATE utf8_unicode_ci NOT NULL,
  `DapAn1` text COLLATE utf8_unicode_ci,
  `DapAn2` text COLLATE utf8_unicode_ci,
  `DapAn3` text COLLATE utf8_unicode_ci,
  `DapAn4` text COLLATE utf8_unicode_ci,
  `DapAnDung` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguphap`
--

LOCK TABLES `nguphap` WRITE;
/*!40000 ALTER TABLE `nguphap` DISABLE KEYS */;
INSERT INTO `nguphap` VALUES (6,'<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font size=\"4\"><span class=\"fontstyle0\">The earth is </span><span class=\"fontstyle1\"><u>the</u> </span><span class=\"fontstyle0\">only planet with a large </span><span class=\"fontstyle1\"><u>number</u> </span><span class=\"fontstyle0\">of </span><span class=\"fontstyle1\"><u>oxygen</u> </span><span class=\"fontstyle0\">in </span><span class=\"fontstyle1\"><u>its</u> </span><span class=\"fontstyle0\">atmosphere.</span>&nbsp;&nbsp;</font><br style=\"font-variant-ligatures: normal; font-variant-east-asian: normal; font-variant-position: normal; line-height: normal; orphans: 2; text-align: -webkit-auto; widows: 2;\"></p></body></html>','the ','number','oxygen','its','C'),(8,'<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font size=\"4\"><span class=\"fontstyle0\">Robert Frost was not well known </span><span class=\"fontstyle1\"><u>as</u> <u>a poet</u> </span><span class=\"fontstyle0\">until he </span><span class=\"fontstyle1\"><u>reached</u> <u>the</u> </span><span class=\"fontstyle0\">forties.</span>&nbsp;&nbsp;</font><br style=\"font-variant-ligatures: normal; font-variant-east-asian: normal; font-variant-position: normal; line-height: normal; orphans: 2; text-align: -webkit-auto; widows: 2;\"></p></body></html>','as','a poet','reached','the','D'),(9,'<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font size=\"4\"><span class=\"fontstyle0\"><u>A painter</u> </span><span class=\"fontstyle1\">who lived </span><span class=\"fontstyle0\"><u>most</u> </span><span class=\"fontstyle1\">of </span><span class=\"fontstyle0\"><u>his life</u> </span><span class=\"fontstyle1\">in the Middle West, Grant wood </span><span class=\"fontstyle0\"><u>has called</u> </span><span class=\"fontstyle1\">America\'s \"Painter of</span>&nbsp;</font><span class=\"fontstyle0\">the Soil.\"</span> </p><br style=\"font-variant-ligatures: normal; font-variant-east-asian: normal; font-variant-position: normal; line-height: normal; orphans: 2; text-align: -webkit-auto; widows: 2;\"></body></html>','A painter','most','his life','has called','D'),(10,'<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font size=\"4\"><span class=\"fontstyle0\"><u>While ancient</u> </span><span class=\"fontstyle1\">times people simply painted inanimate objects, </span><span class=\"fontstyle0\"><u>during</u> </span><span class=\"fontstyle1\">the Renaissance the painting </span><span class=\"fontstyle0\"><u>of</u>&nbsp;</span></font><font size=\"4\"><span class=\"fontstyle1\">\"still life\" </span><span class=\"fontstyle0\"><u>developed as</u> </span><span class=\"fontstyle1\">an accepted art form.</span>&nbsp;&nbsp;</font><br style=\"font-variant-ligatures: normal; font-variant-east-asian: normal; font-variant-position: normal; line-height: normal; orphans: 2; text-align: -webkit-auto; widows: 2;\"></p></body></html>','While ancient','during','of','developed as','A'),(11,'<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font size=\"4\"><span class=\"fontstyle0\">The American </span><span class=\"fontstyle1\"><u>frontiersman</u>, politician, and soldier Davy crockett is one of the <u>most</u> </span><span class=\"fontstyle0\">popular </span><span class=\"fontstyle1\"><u>of</u>&nbsp;</span></font><font size=\"4\"><span class=\"fontstyle0\">American </span><span class=\"fontstyle1\"><u>hero</u>.<br></span></font><br style=\"font-variant-ligatures: normal; font-variant-east-asian: normal; font-variant-position: normal; line-height: normal; orphans: 2; text-align: -webkit-auto; widows: 2;\"></p></body></html>','frontiersman','most','of','hero','D'),(12,'<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font size=\"4\"><span class=\"fontstyle0\">Three months after they have been <u>laid</u>, <u>crocodile</u> </span><span class=\"fontstyle1\">eggs are </span><span class=\"fontstyle0\"><u>ready</u> <u>hatched</u>.</span>&nbsp;&nbsp;</font><br style=\"font-variant-ligatures: normal; font-variant-east-asian: normal; font-variant-position: normal; line-height: normal; orphans: 2; text-align: -webkit-auto; widows: 2;\"></p></body></html>','laid','crocodile','ready','hatched','D'),(13,'<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font size=\"4\"><span class=\"fontstyle0\">Peas require <u>rich</u> </span><span class=\"fontstyle1\">soil, constant </span><span class=\"fontstyle0\"><u>moistures</u>, and a cool <u>growing</u> </span><span class=\"fontstyle1\">season to develop </span><span class=\"fontstyle0\"><u>well</u>.</span>&nbsp;&nbsp;</font><br style=\"font-variant-ligatures: normal; font-variant-east-asian: normal; font-variant-position: normal; line-height: normal; orphans: 2; text-align: -webkit-auto; widows: 2;\"></p></body></html>','rich','moistures','growing','well','B'),(14,'<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font size=\"4\"><span class=\"fontstyle0\">A dolphin locates underwater objects <u>in</u> <u>its</u> </span><span class=\"fontstyle1\">path by </span><span class=\"fontstyle0\"><u>doing</u> </span><span class=\"fontstyle1\">a series of clicking and </span><span class=\"fontstyle0\"><u>whistling</u> </span><span class=\"fontstyle1\">sounds.</span>&nbsp;&nbsp;</font><br style=\"font-variant-ligatures: normal; font-variant-east-asian: normal; font-variant-position: normal; line-height: normal; orphans: 2; text-align: -webkit-auto; widows: 2;\"></p></body></html>','in','its','doing','whistling','D'),(15,'<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font size=\"4\"><span class=\"fontstyle0\">A desert area that </span><span class=\"fontstyle1\"><u>has been</u> <u>without water</u> </span><span class=\"fontstyle0\">for six years will </span><span class=\"fontstyle1\"><u>still</u> </span><span class=\"fontstyle0\">bloom when rain </span><span class=\"fontstyle1\"><u>will come</u>.</span>&nbsp;&nbsp;</font><br style=\"font-variant-ligatures: normal; font-variant-east-asian: normal; font-variant-position: normal; line-height: normal; orphans: 2; text-align: -webkit-auto; widows: 2;\"></p></body></html>','has been','without water','still','will come','D'),(16,'<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font size=\"4\"><span class=\"fontstyle0\">One of the <u>essential</u> </span><span class=\"fontstyle1\">features of the </span><span class=\"fontstyle0\"><u>modern skyscraper</u> <u>is being</u> </span><span class=\"fontstyle1\">the </span><span class=\"fontstyle0\"><u>elevator</u>.</span>&nbsp;&nbsp;</font><br style=\"font-variant-ligatures: normal; font-variant-east-asian: normal; font-variant-position: normal; line-height: normal; orphans: 2; text-align: -webkit-auto; widows: 2;\"></p></body></html>','essential','modern skyscraper','is being','elevator','C'),(17,'<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font size=\"4\"><span class=\"fontstyle0\">A rabbit <u>moves about</u> <u>by hopping</u> </span><span class=\"fontstyle1\">on its hind legs, </span><span class=\"fontstyle0\"><u>which are</u> </span><span class=\"fontstyle1\">much longer and </span><span class=\"fontstyle0\"><u>more strong</u> </span><span class=\"fontstyle1\">than its&nbsp;front legs.</span>&nbsp;&nbsp;</font><br style=\"font-variant-ligatures: normal; font-variant-east-asian: normal; font-variant-position: normal; line-height: normal; orphans: 2; text-align: -webkit-auto; widows: 2;\"></p></body></html>','moves about','by hopping','which are','more strong','D');
/*!40000 ALTER TABLE `nguphap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ten_dang_nhap` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `mat_khau` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `quyen` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ho_ten` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `dia_chi` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `gioi_tinh` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','MTIz','admin','nguyen van a','ou@gmail.com','thon 1','Nam'),(2,'u1','MTIzNDU2','khach','hdfs','user2@gmail.com','thôn xã','Nam');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-05 21:34:12
