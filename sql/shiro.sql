# Host: localhost  (Version: 5.7.3-m13)
# Date: 2018-03-04 15:55:04
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "permission"
#

CREATE TABLE `permission` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Data for table "permission"
#

REPLACE INTO `permission` VALUES (1,'yellow:read'),(2,'green:read'),(3,'red:read'),(4,'blue:read'),(5,'black:read');

#
# Structure for table "role"
#

CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Data for table "role"
#

REPLACE INTO `role` VALUES (1,'vip'),(2,'svip'),(3,'yellow'),(4,'green'),(5,'red'),(6,'blue'),(7,'black');

#
# Structure for table "role_permission"
#

CREATE TABLE `role_permission` (
  `rpid` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL DEFAULT '0',
  `pid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`rpid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

#
# Data for table "role_permission"
#

REPLACE INTO `role_permission` VALUES (1,1,1),(2,1,2),(3,2,1),(4,2,2),(5,2,3),(6,2,4),(7,2,5),(8,3,1),(9,4,2),(10,5,3),(11,6,4),(12,7,5);

#
# Structure for table "user"
#

CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#


#
# Structure for table "user_role"
#

CREATE TABLE `user_role` (
  `urid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0',
  `rid` int(11) NOT NULL DEFAULT '0',
  `expire_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`urid`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

#
# Data for table "user_role"
#

