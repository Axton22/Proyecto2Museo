-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: museodb
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `coleccion`
--

DROP TABLE IF EXISTS `coleccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coleccion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idSala` int DEFAULT NULL,
  `nombreColeccion` varchar(50) DEFAULT NULL,
  `siglo` varchar(50) DEFAULT NULL,
  `descripcionColeccion` text,
  PRIMARY KEY (`id`),
  KEY `idSala` (`idSala`),
  CONSTRAINT `coleccion_ibfk_1` FOREIGN KEY (`idSala`) REFERENCES `sala` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coleccion`
--

LOCK TABLES `coleccion` WRITE;
/*!40000 ALTER TABLE `coleccion` DISABLE KEYS */;
INSERT INTO `coleccion` VALUES (1,11,'Instrumentos musicales históricos y exóticos','XVI-XXI','Desde laúd barroco hasta sintetizadores modernos, esta colección recorre la evolución musical con instrumentos históricos, partituras, vestimenta de artistas y dispositivos de reproducción.'),(2,10,'Colección de fósiles ','XIX','Fósiles de dinosaurios, minerales, restos humanos y dioramas de ecosistemas pasados trazan la evolución de la vida y del planeta.'),(5,20,'Fotografías de animales extintos y en peligro','XIX-XXI','Esta colección exhibe animales extintos y en peligro mediante réplicas, fotografías, ADN conservado y objetos confiscados del tráfico ilegal.'),(6,12,'Registros climáticos históricos','XVII-XXI','Un compendio de instrumentos meteorológicos, registros históricos y simulaciones modernas que explican los fenómenos climáticos y el impacto humano sobre el planeta.'),(7,19,'Prototipos y modelos funcionales','XV-XXI',' La innovación humana a través de los siglos: incluye desde bocetos de Da Vinci hasta inteligencia artificial contemporánea. Un homenaje al ingenio y la creatividad.'),(8,17,'Fragmentos de meteoritos y réplicas lunares','XX-XXI','Explora el cosmos a través de meteoritos reales, modelos del sistema solar, tecnología espacial y simulaciones astronómicas. Celebración del espíritu de exploración.'),(9,18,'Réplicas de artefactos arqueológicos','IV a.C. – XV d.C','Desde jeroglíficos egipcios hasta arte maya, esta colección reúne artefactos arqueológicos, arquitectura antigua y rituales olvidados de culturas milenarias.');
/*!40000 ALTER TABLE `coleccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comisiontarjetas`
--

DROP TABLE IF EXISTS `comisiontarjetas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comisiontarjetas` (
  `tipoTarjeta` varchar(50) NOT NULL,
  `comision` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`tipoTarjeta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comisiontarjetas`
--

LOCK TABLES `comisiontarjetas` WRITE;
/*!40000 ALTER TABLE `comisiontarjetas` DISABLE KEYS */;
INSERT INTO `comisiontarjetas` VALUES ('American express',0.08),('Dinner club',0.05),('Master Card',0.10),('Union pay',0.05),('VISA',0.10);
/*!40000 ALTER TABLE `comisiontarjetas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrada`
--

DROP TABLE IF EXISTS `entrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrada` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idSala` int DEFAULT NULL,
  `nombreVisitante` varchar(50) DEFAULT NULL,
  `fechaVisita` date DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `comision` decimal(10,2) DEFAULT NULL,
  `IVA` decimal(10,2) DEFAULT NULL,
  `totalPagar` decimal(10,2) DEFAULT NULL,
  `autorizada` tinyint(1) DEFAULT NULL,
  `nombreTarjeta` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idSala` (`idSala`),
  CONSTRAINT `entrada_ibfk_1` FOREIGN KEY (`idSala`) REFERENCES `sala` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrada`
--

LOCK TABLES `entrada` WRITE;
/*!40000 ALTER TABLE `entrada` DISABLE KEYS */;
INSERT INTO `entrada` VALUES (1,10,' cxdsc<','2025-06-01',NULL,NULL,NULL,NULL,0,'VISA'),(2,12,'Axton','2025-06-08',4500.00,1299.50,1495.00,12995.00,0,'Mastercard'),(3,10,'Axton','2025-06-01',7000.00,791.00,910.00,7910.00,0,'Dinner Club'),(4,10,'Axton','2025-06-01',7000.00,791.00,910.00,7910.00,0,'American Express'),(5,10,'Axton','2025-06-01',7000.00,791.00,910.00,7910.00,0,'Union Pay'),(6,10,'Axton','2025-06-01',7000.00,632.80,910.00,7910.00,0,'Mastercard'),(7,10,'Axton','2025-06-01',7000.00,791.00,910.00,7910.00,0,'American Express'),(8,10,'Axton','2025-07-06',7000.00,791.00,910.00,7910.00,0,'VISA'),(9,10,'Axton','2025-06-16',6000.00,678.00,780.00,6780.00,0,'American Express'),(10,20,'Axton','2025-07-06',6000.00,678.00,780.00,6780.00,0,'Union Pay'),(11,10,'Axton','2025-06-01',7000.00,791.00,910.00,7910.00,0,'VISA'),(12,10,'Axton','2025-07-06',14500.00,1469.00,1885.00,16385.00,0,'Union Pay'),(13,10,'Axton ','2025-06-01',7000.00,791.00,910.00,7910.00,0,'Dinner Club'),(14,12,'Axton ','2025-06-16',7000.00,791.00,910.00,7910.00,0,'VISA'),(15,19,'Axton ','2025-06-21',7000.00,791.00,910.00,7910.00,0,'Dinner Club'),(16,10,'Axton','2025-05-28',6000.00,542.40,780.00,6780.00,0,'VISA'),(17,10,'Axton','2025-06-22',7000.00,632.80,910.00,7910.00,0,'Mastercard'),(18,11,'Axton','2025-06-22',7000.00,632.80,910.00,7910.00,0,'Mastercard'),(19,20,'Axton','2025-06-09',5500.00,621.50,715.00,6215.00,0,'Union Pay'),(20,19,'Axton','2025-06-01',5500.00,621.50,715.00,6215.00,0,'Dinner Club'),(21,10,'Axton','2025-06-01',7000.00,791.00,910.00,7910.00,0,'Mastercard'),(22,12,'Axton','2025-06-16',7000.00,791.00,910.00,7910.00,0,'Union Pay'),(23,10,'Axton','2025-06-01',7000.00,791.00,910.00,7910.00,0,'VISA'),(24,19,'Axton','2025-06-23',7000.00,791.00,910.00,7910.00,0,'American Express'),(25,10,'Axton','2025-06-01',7000.00,791.00,910.00,7910.00,0,'Union Pay'),(26,11,'Alejandro','2025-06-22',4000.00,226.00,520.00,4520.00,0,'VISA'),(27,11,'Alejandro','2025-06-29',4000.00,226.00,520.00,4520.00,0,'American Express'),(28,10,'Alejandro','2025-06-23',9500.00,858.80,1235.00,10735.00,0,'Union pay'),(29,11,'Alejandro','2025-06-23',9500.00,858.80,1235.00,10735.00,0,'Union pay'),(30,10,'Alejandro','2025-06-26',20500.00,2022.70,2665.00,23165.00,0,'Union pay'),(31,19,'Alejandro','2025-06-24',20500.00,2022.70,2665.00,23165.00,0,'Union pay'),(32,11,'Alejandro','2025-06-23',20500.00,2022.70,2665.00,23165.00,0,'Union pay'),(33,10,'Alejandro','2025-06-22',20500.00,2022.70,2665.00,23165.00,0,'Union pay'),(34,19,'Alejandro','2025-06-22',20500.00,2022.70,2665.00,23165.00,0,'Union pay'),(35,12,'Alejandro','2025-06-23',20500.00,2022.70,2665.00,23165.00,0,'Union pay'),(36,19,'Alejandro','2025-06-22',20500.00,2022.70,2665.00,23165.00,0,'Union pay'),(37,11,'Alejandro','2025-06-21',3500.00,395.50,455.00,3955.00,0,'VISA'),(38,11,'Alejandro','2025-06-21',11000.00,1163.90,1430.00,12430.00,0,'Dinner club'),(39,11,'Alejandro','2025-06-23',11000.00,1163.90,1430.00,12430.00,0,'Dinner club'),(40,11,'Alejandro','2025-06-22',11000.00,1163.90,1430.00,12430.00,0,'Dinner club'),(41,10,'Alejandro','2025-06-22',11000.00,1163.90,1430.00,12430.00,0,'Dinner club');
/*!40000 ALTER TABLE `entrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especies`
--

DROP TABLE IF EXISTS `especies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idColeccion` int DEFAULT NULL,
  `nombreCientifico` varchar(50) DEFAULT NULL,
  `nombreComun` varchar(50) DEFAULT NULL,
  `fechaExtioncion` date DEFAULT NULL,
  `epocaVivio` varchar(50) DEFAULT NULL,
  `peso` decimal(10,2) DEFAULT NULL,
  `tamanio` decimal(10,2) DEFAULT NULL,
  `caracteristicas` text,
  PRIMARY KEY (`id`),
  KEY `idColeccion` (`idColeccion`),
  CONSTRAINT `especies_ibfk_1` FOREIGN KEY (`idColeccion`) REFERENCES `coleccion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especies`
--

LOCK TABLES `especies` WRITE;
/*!40000 ALTER TABLE `especies` DISABLE KEYS */;
INSERT INTO `especies` VALUES (4,5,'Thylacinus cynocephalus','Tigre de Tasmania','1936-11-10','Del 1880 al 1936 aproximadamente',30.00,1.80,'Carnívoro descendiente de los marsupiales'),(5,5,'Diceros bicornis longipes','Rinoceronte negro occidental','2011-07-05','Del 1930 hasta el 2011 aproximadamente',1400.00,3.50,'mamífero hervívoro con 2 cuernos de queratina'),(6,2,'Tyrannosaurus rex','T-rex','1003-05-10','Hace 66 millones de años',9000.50,12.00,'Depredador bípedo con poderosa mandíbula; uno de los dinosaurios más conocidos del mundo'),(7,2,'Mammuthus primigenius','Mamut lanudo','1800-05-10','Hace 4,000 años',6000.50,3.50,'Adaptado al frío extremo; colmillos curvados y pelaje denso; antepasado del elefante actual');
/*!40000 ALTER TABLE `especies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `museo`
--

DROP TABLE IF EXISTS `museo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `museo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `ubicacion` varchar(80) DEFAULT NULL,
  `fechaFundacion` date DEFAULT NULL,
  `nombreDirector` varchar(50) DEFAULT NULL,
  `sitioWeb` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `museo`
--

LOCK TABLES `museo` WRITE;
/*!40000 ALTER TABLE `museo` DISABLE KEYS */;
INSERT INTO `museo` VALUES (1,'Museo Americano de Historia Natural','Historia Natural','Nueva York','1869-04-06','Nombre del Ellen V. Futter','www.mahn.com');
/*!40000 ALTER TABLE `museo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precio`
--

DROP TABLE IF EXISTS `precio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idSala` int DEFAULT NULL,
  `precioLunesSabado` decimal(10,2) DEFAULT NULL,
  `precioDomingo` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idSala` (`idSala`),
  CONSTRAINT `precio_ibfk_1` FOREIGN KEY (`idSala`) REFERENCES `sala` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precio`
--

LOCK TABLES `precio` WRITE;
/*!40000 ALTER TABLE `precio` DISABLE KEYS */;
INSERT INTO `precio` VALUES (3,10,6000.00,7000.00),(4,11,3500.00,4000.00),(5,12,4000.00,4500.00),(6,17,5000.00,5500.00),(7,18,5000.00,5500.00),(8,19,4000.00,4500.00),(9,20,5500.00,6000.00);
/*!40000 ALTER TABLE `precio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sala` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idMuseo` int DEFAULT NULL,
  `nombreSala` varchar(50) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`id`),
  KEY `idMuseo` (`idMuseo`),
  CONSTRAINT `sala_ibfk_1` FOREIGN KEY (`idMuseo`) REFERENCES `museo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
INSERT INTO `sala` VALUES (10,1,'Sala de Historia Natural','Con fósiles, minerales y una mirada a la evolución de la vida.'),(11,1,'Sala de música','Donde los visitantes puedan explorar instrumentos, géneros y artistas históricos.'),(12,1,'Sala del Tiempo y el Clima','Sobre fenómenos meteorológicos, cambio climático e historia climática.'),(17,1,'Sala de espacio exterior','Con planetas, estrellas, misiones espaciales y simulaciones astronómicas.'),(18,1,'Sala de civilizaciones perdidas','Viaje por culturas antiguas como los mayas, egipcios o sumerios.'),(19,1,'Sala de los Inventos','Exhibe grandes ideas y sus creadores, desde la rueda hasta la inteligencia artificial'),(20,1,'Sala de Especies en Extinción','Para crear conciencia sobre la biodiversidad en peligro.');
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tematica`
--

DROP TABLE IF EXISTS `tematica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tematica` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idSala` int DEFAULT NULL,
  `nombreTematica` varchar(50) NOT NULL,
  `caracteristicas` text,
  `epocaTematica` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idSala` (`idSala`),
  CONSTRAINT `tematica_ibfk_1` FOREIGN KEY (`idSala`) REFERENCES `sala` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tematica`
--

LOCK TABLES `tematica` WRITE;
/*!40000 ALTER TABLE `tematica` DISABLE KEYS */;
INSERT INTO `tematica` VALUES (3,11,'Instrumentos musicales del mundo antiguo','Esta sección explora instrumentos musicales originados en civilizaciones antiguas como Egipto, Mesopotamia, Grecia, India y China.','Aproximadamente desde el 3000 a.C. hasta el siglo III d.C.'),(4,12,'Fenómenos climáticos extremos del planeta','Esta sección explora los eventos meteorológicos más impactantes de la Tierra: huracanes, tornados, tormentas eléctricas, olas de calor y nevadas extremas.','Desde el siglo XIX hasta la actualidad (con proyecciones hacia el futuro).'),(5,17,'La conquista de la Luna','Esta sección celebra el histórico logro de llevar al ser humano más allá de nuestro planeta.','1961–1972 (aunque su influencia continúa hasta hoy)'),(6,18,'El misterio de los pueblos desaparecidos','Esta sección se centra en culturas que florecieron y colapsaron dejando tras de sí enigmas sin resolver.','Desde el 3000 a.C. hasta el siglo VI d.C. (dependiendo de la civilización)');
/*!40000 ALTER TABLE `tematica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valoracionsala`
--

DROP TABLE IF EXISTS `valoracionsala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valoracionsala` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idSala` int DEFAULT NULL,
  `valoracion` int DEFAULT NULL,
  `observacion` text,
  PRIMARY KEY (`id`),
  KEY `idSala` (`idSala`),
  CONSTRAINT `valoracionsala_ibfk_1` FOREIGN KEY (`idSala`) REFERENCES `sala` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valoracionsala`
--

LOCK TABLES `valoracionsala` WRITE;
/*!40000 ALTER TABLE `valoracionsala` DISABLE KEYS */;
/*!40000 ALTER TABLE `valoracionsala` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-23  0:31:17
