CREATE DATABASE  IF NOT EXISTS `enade` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `enade`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: enade
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `tbProva`
--

DROP TABLE IF EXISTS `tbProva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbProva` (
  `idProva` bigint NOT NULL AUTO_INCREMENT,
  `dataProva` date NOT NULL,
  PRIMARY KEY (`idProva`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbProva`
--

LOCK TABLES `tbProva` WRITE;
/*!40000 ALTER TABLE `tbProva` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbProva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbprova_has_tbquestao`
--

DROP TABLE IF EXISTS `tbprova_has_tbquestao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbprova_has_tbquestao` (
  `tbProva_idProva` bigint NOT NULL,
  `tbQuestao_idQuestao` bigint NOT NULL,
  KEY `FKktmmhacuvq0fqyse9fp22y4pg` (`tbQuestao_idQuestao`),
  KEY `FKeh4bywe3berup46urb4rd2m70` (`tbProva_idProva`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbprova_has_tbquestao`
--

LOCK TABLES `tbprova_has_tbquestao` WRITE;
/*!40000 ALTER TABLE `tbprova_has_tbquestao` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbprova_has_tbquestao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbquestao`
--

DROP TABLE IF EXISTS `tbquestao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbquestao` (
  `idQuestao` bigint NOT NULL AUTO_INCREMENT,
  `alternativaA` varchar(255) DEFAULT NULL,
  `alternativaB` varchar(255) DEFAULT NULL,
  `alternativaC` varchar(255) DEFAULT NULL,
  `alternativaD` varchar(255) DEFAULT NULL,
  `alternativaE` varchar(255) DEFAULT NULL,
  `descricaoQuestao` varchar(255) NOT NULL,
  `estadoQuestao` varchar(255) NOT NULL,
  `questaoCorreta` char(1) DEFAULT NULL,
  `resposta` varchar(255) NOT NULL,
  `tbTipoQuestao_idTipoQuestao` bigint NOT NULL,
  PRIMARY KEY (`idQuestao`),
  KEY `FK8tuswhsthodujsvrvaqrd6we5` (`tbTipoQuestao_idTipoQuestao`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbquestao`
--

LOCK TABLES `tbquestao` WRITE;
/*!40000 ALTER TABLE `tbquestao` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbquestao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbresultado`
--

DROP TABLE IF EXISTS `tbresultado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbresultado` (
  `idResultado` int NOT NULL AUTO_INCREMENT,
  `valorObtido` double NOT NULL,
  `tbProva_idProva` bigint NOT NULL,
  `tbUsuario_idUsuario` bigint NOT NULL,
  PRIMARY KEY (`idResultado`),
  KEY `FKll0pq0jc5w07lupx4vtvu2dxj` (`tbProva_idProva`),
  KEY `FKg781k3cm98av1wt3n0dapc005` (`tbUsuario_idUsuario`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbresultado`
--

LOCK TABLES `tbresultado` WRITE;
/*!40000 ALTER TABLE `tbresultado` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbresultado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbTipoQuestao`
--

DROP TABLE IF EXISTS `tbTipoQuestao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbTipoQuestao` (
  `idTipoQuestao` bigint NOT NULL AUTO_INCREMENT,
  `nomeTipoQuestaocol` varchar(255) NOT NULL,
  PRIMARY KEY (`idTipoQuestao`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbTipoQuestao`
--

LOCK TABLES `tbTipoQuestao` WRITE;
/*!40000 ALTER TABLE `tbTipoQuestao` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbTipoQuestao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbTipoUsuario`
--

DROP TABLE IF EXISTS `tbTipoUsuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbTipoUsuario` (
  `idTipoUsuario` bigint NOT NULL,
  `nomeTipoUsuario` varchar(255) NOT NULL,
  PRIMARY KEY (`idTipoUsuario`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbTipoUsuario`
--

LOCK TABLES `tbTipoUsuario` WRITE;
/*!40000 ALTER TABLE `tbTipoUsuario` DISABLE KEYS */;
INSERT INTO `tbTipoUsuario` VALUES (1,'Aluno'),(2,'Professor');
/*!40000 ALTER TABLE `tbTipoUsuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbusuario`
--

DROP TABLE IF EXISTS `tbusuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbusuario` (
  `idUsuario` bigint NOT NULL AUTO_INCREMENT,
  `emailUsuario` varchar(255) NOT NULL,
  `nomeUsuario` varchar(255) NOT NULL,
  `senhaUsuario` varchar(255) NOT NULL,
  `tbTipoUsuario_idTipoUsuario` bigint NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `FKj3ot8a2cqa1xmt42jxp6uq0mv` (`tbTipoUsuario_idTipoUsuario`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbusuario`
--

LOCK TABLES `tbusuario` WRITE;
/*!40000 ALTER TABLE `tbusuario` DISABLE KEYS */;
INSERT INTO `tbusuario` VALUES (1,'ze@ze.com.br','Zé','123',1),(2,'mario@mario.com.br','Mário','123',2);
/*!40000 ALTER TABLE `tbusuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-15 20:28:58
