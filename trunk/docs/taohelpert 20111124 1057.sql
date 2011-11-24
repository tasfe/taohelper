-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.67-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema taohelper
--

CREATE DATABASE IF NOT EXISTS taohelper;
USE taohelper;

--
-- Definition of table `budget`
--

DROP TABLE IF EXISTS `budget`;
CREATE TABLE `budget` (
  `id` int(11) NOT NULL auto_increment,
  `user_nick` varchar(100) NOT NULL,
  `budget_num` double NOT NULL default '-1' COMMENT '//-1代表无定义',
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `budget`
--

/*!40000 ALTER TABLE `budget` DISABLE KEYS */;
INSERT INTO `budget` (`id`,`user_nick`,`budget_num`,`timestamp`) VALUES 
 (2,'ysj',20,'2011-07-20 13:13:26'),
 (3,'ysj',40,'2011-11-23 13:13:44'),
 (5,'ysj',40,'2011-11-23 15:12:42'),
 (6,'ysj',40,'2011-11-23 15:13:34');
/*!40000 ALTER TABLE `budget` ENABLE KEYS */;


--
-- Definition of table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `id` int(11) NOT NULL auto_increment,
  `item_id` varchar(50) NOT NULL COMMENT '商品的id',
  `price` double NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `favorite`
--

/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
INSERT INTO `favorite` (`id`,`item_id`,`price`,`date`) VALUES 
 (1,'112233',20,'2011-11-22'),
 (2,'112233',23,'2011-11-23');
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;


--
-- Definition of table `preference`
--

DROP TABLE IF EXISTS `preference`;
CREATE TABLE `preference` (
  `id` int(11) NOT NULL auto_increment,
  `user_nick` varchar(100) NOT NULL,
  `category_id` varchar(50) NOT NULL COMMENT '//the id of category of the products',
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `preference`
--

/*!40000 ALTER TABLE `preference` DISABLE KEYS */;
INSERT INTO `preference` (`id`,`user_nick`,`category_id`,`timestamp`) VALUES 
 (1,'ysj','123344','2011-11-23 23:39:04'),
 (2,'ysj','123344','2011-11-23 23:39:29'),
 (3,'ysj','123345','2011-11-23 23:39:40'),
 (4,'ysj','123346','2011-11-23 23:39:48'),
 (5,'ysj','123346','2011-11-23 23:43:11'),
 (6,'ysj','123346','2011-11-23 23:43:13');
/*!40000 ALTER TABLE `preference` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `nick` varchar(100) NOT NULL,
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
