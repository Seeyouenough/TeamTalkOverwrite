/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.20 : Database - teamtalk
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`teamtalk` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `teamtalk`;

/*Table structure for table `IMAudio` */

DROP TABLE IF EXISTS `IMAudio`;

CREATE TABLE `IMAudio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fromId` int(11) unsigned NOT NULL COMMENT '发送者Id',
  `toId` int(11) unsigned NOT NULL COMMENT '接收者Id',
  `path` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '语音存储的地址',
  `size` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '文件大小',
  `duration` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '语音时长',
  `created` int(11) unsigned NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_fromId_toId` (`fromId`,`toId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `IMAudio` */

/*Table structure for table `IMDepart` */

DROP TABLE IF EXISTS `IMDepart`;

CREATE TABLE `IMDepart` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `departName` varchar(64) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '部门名称',
  `priority` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '显示优先级',
  `parentId` int(11) unsigned NOT NULL COMMENT '上级部门id',
  `status` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '状态',
  `created` int(11) unsigned NOT NULL COMMENT '创建时间',
  `updated` int(11) unsigned NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_departName` (`departName`),
  KEY `idx_priority_status` (`priority`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `IMDepart` */

insert  into `IMDepart`(`id`,`departName`,`priority`,`parentId`,`status`,`created`,`updated`) values (31,'科创中心',2,35,0,0,0),(32,'车辆中心',2,31,0,0,0),(36,'技术中心',1,0,0,0,0);

/*Table structure for table `IMDiscovery` */

DROP TABLE IF EXISTS `IMDiscovery`;

CREATE TABLE `IMDiscovery` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `itemName` varchar(64) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '名称',
  `itemUrl` varchar(64) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT 'URL',
  `itemPriority` int(11) unsigned NOT NULL COMMENT '显示优先级',
  `status` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '状态',
  `created` int(11) unsigned NOT NULL COMMENT '创建时间',
  `updated` int(11) unsigned NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_itemName` (`itemName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `IMDiscovery` */

insert  into `IMDiscovery`(`id`,`itemName`,`itemUrl`,`itemPriority`,`status`,`created`,`updated`) values (1,'朋友圈','www.baidu.com',3,0,0,0),(2,'必应','cn.bing.com',1,0,0,0);

/*Table structure for table `IMGroup` */

DROP TABLE IF EXISTS `IMGroup`;

CREATE TABLE `IMGroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '群名称',
  `avatar` varchar(256) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '群头像',
  `creator` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者用户id',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '群组类型，1-固定;2-临时群',
  `userCnt` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '成员人数',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否删除,0-正常，1-删除',
  `version` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '群版本号',
  `lastChated` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '最后聊天时间',
  `updated` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`(191)),
  KEY `idx_creator` (`creator`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='IM群信息';

/*Data for the table `IMGroup` */

insert  into `IMGroup`(`id`,`name`,`avatar`,`creator`,`type`,`userCnt`,`status`,`version`,`lastChated`,`updated`,`created`) values (1,'哈哈组','',20,0,7,0,6,1509566378,1509517048,1509517048);

/*Table structure for table `IMGroupMember` */

DROP TABLE IF EXISTS `IMGroupMember`;

CREATE TABLE `IMGroupMember` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) unsigned NOT NULL COMMENT '群Id',
  `userId` int(11) unsigned NOT NULL COMMENT '用户id',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '是否退出群，0-正常，1-已退出',
  `created` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  `updated` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_groupId_userId_status` (`groupId`,`userId`,`status`),
  KEY `idx_userId_status_updated` (`userId`,`status`,`updated`),
  KEY `idx_groupId_updated` (`groupId`,`updated`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户和群的关系表';

/*Data for the table `IMGroupMember` */

insert  into `IMGroupMember`(`id`,`groupId`,`userId`,`status`,`created`,`updated`) values (1,1,17,0,1509517048,1509566393),(2,1,19,0,1509517048,1509566393),(3,1,20,0,1509517048,1509517048),(4,1,18,0,1509566338,1509566393);

/*Table structure for table `IMGroupMessage_0` */

DROP TABLE IF EXISTS `IMGroupMessage_0`;

CREATE TABLE `IMGroupMessage_0` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `userId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '消息内容',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '2' COMMENT '群消息类型,101为群语音,2为文本',
  `status` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '消息状态',
  `updated` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_groupId_status_created` (`groupId`,`status`,`created`),
  KEY `idx_groupId_msgId_status_created` (`groupId`,`msgId`,`status`,`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='IM群消息表';

/*Data for the table `IMGroupMessage_0` */

/*Table structure for table `IMGroupMessage_1` */

DROP TABLE IF EXISTS `IMGroupMessage_1`;

CREATE TABLE `IMGroupMessage_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `userId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '消息内容',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '2' COMMENT '群消息类型,101为群语音,2为文本',
  `status` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '消息状态',
  `updated` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_groupId_status_created` (`groupId`,`status`,`created`),
  KEY `idx_groupId_msgId_status_created` (`groupId`,`msgId`,`status`,`created`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='IM群消息表';

/*Data for the table `IMGroupMessage_1` */

insert  into `IMGroupMessage_1`(`id`,`groupId`,`userId`,`msgId`,`content`,`type`,`status`,`updated`,`created`) values (1,1,20,1,'/8Ih3rh+Y9uExtTXcn/vBQ==',16,0,1509566283,1509566283),(2,1,20,2,'KehgicD+sKYBXHylln1cuA==',16,0,1509566288,1509566288),(3,1,20,3,'x+BP5hap9tYKicueil9+4w==',16,0,1509566305,1509566305),(4,1,20,4,'yPi1udbJkJvn+R1KCyeZuXWXrZFfV+UBo37vcxL/NUY=',16,0,1509566309,1509566309),(5,1,20,5,'8RDWtQSsZdICixIU9k95mg==',16,0,1509566332,1509566332),(6,1,20,6,'6yFUE4nmm4dze3VpOZxE8Q==',16,0,1509566378,1509566378);

/*Table structure for table `IMGroupMessage_2` */

DROP TABLE IF EXISTS `IMGroupMessage_2`;

CREATE TABLE `IMGroupMessage_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `userId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '消息内容',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '2' COMMENT '群消息类型,101为群语音,2为文本',
  `status` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '消息状态',
  `updated` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_groupId_status_created` (`groupId`,`status`,`created`),
  KEY `idx_groupId_msgId_status_created` (`groupId`,`msgId`,`status`,`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='IM群消息表';

/*Data for the table `IMGroupMessage_2` */

/*Table structure for table `IMGroupMessage_3` */

DROP TABLE IF EXISTS `IMGroupMessage_3`;

CREATE TABLE `IMGroupMessage_3` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `userId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '消息内容',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '2' COMMENT '群消息类型,101为群语音,2为文本',
  `status` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '消息状态',
  `updated` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_groupId_status_created` (`groupId`,`status`,`created`),
  KEY `idx_groupId_msgId_status_created` (`groupId`,`msgId`,`status`,`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='IM群消息表';

/*Data for the table `IMGroupMessage_3` */

/*Table structure for table `IMGroupMessage_4` */

DROP TABLE IF EXISTS `IMGroupMessage_4`;

CREATE TABLE `IMGroupMessage_4` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `userId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '消息内容',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '2' COMMENT '群消息类型,101为群语音,2为文本',
  `status` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '消息状态',
  `updated` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_groupId_status_created` (`groupId`,`status`,`created`),
  KEY `idx_groupId_msgId_status_created` (`groupId`,`msgId`,`status`,`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='IM群消息表';

/*Data for the table `IMGroupMessage_4` */

/*Table structure for table `IMGroupMessage_5` */

DROP TABLE IF EXISTS `IMGroupMessage_5`;

CREATE TABLE `IMGroupMessage_5` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `userId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '消息内容',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '2' COMMENT '群消息类型,101为群语音,2为文本',
  `status` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '消息状态',
  `updated` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_groupId_status_created` (`groupId`,`status`,`created`),
  KEY `idx_groupId_msgId_status_created` (`groupId`,`msgId`,`status`,`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='IM群消息表';

/*Data for the table `IMGroupMessage_5` */

/*Table structure for table `IMGroupMessage_6` */

DROP TABLE IF EXISTS `IMGroupMessage_6`;

CREATE TABLE `IMGroupMessage_6` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `userId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '消息内容',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '2' COMMENT '群消息类型,101为群语音,2为文本',
  `status` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '消息状态',
  `updated` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_groupId_status_created` (`groupId`,`status`,`created`),
  KEY `idx_groupId_msgId_status_created` (`groupId`,`msgId`,`status`,`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='IM群消息表';

/*Data for the table `IMGroupMessage_6` */

/*Table structure for table `IMGroupMessage_7` */

DROP TABLE IF EXISTS `IMGroupMessage_7`;

CREATE TABLE `IMGroupMessage_7` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `userId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '消息内容',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '2' COMMENT '群消息类型,101为群语音,2为文本',
  `status` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '消息状态',
  `updated` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_groupId_status_created` (`groupId`,`status`,`created`),
  KEY `idx_groupId_msgId_status_created` (`groupId`,`msgId`,`status`,`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='IM群消息表';

/*Data for the table `IMGroupMessage_7` */

/*Table structure for table `IMMessage_0` */

DROP TABLE IF EXISTS `IMMessage_0`;

CREATE TABLE `IMMessage_0` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relateId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `fromId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `toId` int(11) unsigned NOT NULL COMMENT '接收用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin DEFAULT '' COMMENT '消息内容',
  `type` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '消息类型',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0正常 1被删除',
  `updated` int(11) unsigned NOT NULL COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_relateId_status_created` (`relateId`,`status`,`created`),
  KEY `idx_relateId_status_msgId_created` (`relateId`,`status`,`msgId`,`created`),
  KEY `idx_fromId_toId_created` (`fromId`,`toId`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `IMMessage_0` */

/*Table structure for table `IMMessage_1` */

DROP TABLE IF EXISTS `IMMessage_1`;

CREATE TABLE `IMMessage_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relateId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `fromId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `toId` int(11) unsigned NOT NULL COMMENT '接收用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin DEFAULT '' COMMENT '消息内容',
  `type` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '消息类型',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0正常 1被删除',
  `updated` int(11) unsigned NOT NULL COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_relateId_status_created` (`relateId`,`status`,`created`),
  KEY `idx_relateId_status_msgId_created` (`relateId`,`status`,`msgId`,`created`),
  KEY `idx_fromId_toId_created` (`fromId`,`toId`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `IMMessage_1` */

insert  into `IMMessage_1`(`id`,`relateId`,`fromId`,`toId`,`msgId`,`content`,`type`,`status`,`updated`,`created`) values (1,1,15,13,1,'ccnCMU4VFwASFoImfkOKTg==',0,0,1509515740,1509515740),(2,1,15,13,2,'VC1rTaiXQX1dpjyhglMo6w==',0,0,1509515778,1509515778),(3,1,15,13,3,'umZZiYWVoybJtpNFKm2B3g==',0,0,1509515785,1509515785),(4,1,15,13,4,'ARZRQa5HLj21WrzFNYiREA==',0,0,1509515789,1509515789),(5,1,15,13,5,'ufHzSpbDbLpOl4G/mkj8oA==',0,0,1509515793,1509515793),(6,1,15,13,6,'FioUzIMk2g4W/g3tMxqW0A==',0,0,1509515796,1509515796),(7,1,15,13,7,'iyLPHO3dk79RAwuBpAzD3A==',0,0,1509515798,1509515798);

/*Table structure for table `IMMessage_2` */

DROP TABLE IF EXISTS `IMMessage_2`;

CREATE TABLE `IMMessage_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relateId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `fromId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `toId` int(11) unsigned NOT NULL COMMENT '接收用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin DEFAULT '' COMMENT '消息内容',
  `type` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '消息类型',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0正常 1被删除',
  `updated` int(11) unsigned NOT NULL COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_relateId_status_created` (`relateId`,`status`,`created`),
  KEY `idx_relateId_status_msgId_created` (`relateId`,`status`,`msgId`,`created`),
  KEY `idx_fromId_toId_created` (`fromId`,`toId`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `IMMessage_2` */

/*Table structure for table `IMMessage_3` */

DROP TABLE IF EXISTS `IMMessage_3`;

CREATE TABLE `IMMessage_3` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relateId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `fromId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `toId` int(11) unsigned NOT NULL COMMENT '接收用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin DEFAULT '' COMMENT '消息内容',
  `type` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '消息类型',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0正常 1被删除',
  `updated` int(11) unsigned NOT NULL COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_relateId_status_created` (`relateId`,`status`,`created`),
  KEY `idx_relateId_status_msgId_created` (`relateId`,`status`,`msgId`,`created`),
  KEY `idx_fromId_toId_created` (`fromId`,`toId`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `IMMessage_3` */

/*Table structure for table `IMMessage_4` */

DROP TABLE IF EXISTS `IMMessage_4`;

CREATE TABLE `IMMessage_4` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relateId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `fromId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `toId` int(11) unsigned NOT NULL COMMENT '接收用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin DEFAULT '' COMMENT '消息内容',
  `type` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '消息类型',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0正常 1被删除',
  `updated` int(11) unsigned NOT NULL COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_relateId_status_created` (`relateId`,`status`,`created`),
  KEY `idx_relateId_status_msgId_created` (`relateId`,`status`,`msgId`,`created`),
  KEY `idx_fromId_toId_created` (`fromId`,`toId`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `IMMessage_4` */

/*Table structure for table `IMMessage_5` */

DROP TABLE IF EXISTS `IMMessage_5`;

CREATE TABLE `IMMessage_5` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relateId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `fromId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `toId` int(11) unsigned NOT NULL COMMENT '接收用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin DEFAULT '' COMMENT '消息内容',
  `type` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '消息类型',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0正常 1被删除',
  `updated` int(11) unsigned NOT NULL COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_relateId_status_created` (`relateId`,`status`,`created`),
  KEY `idx_relateId_status_msgId_created` (`relateId`,`status`,`msgId`,`created`),
  KEY `idx_fromId_toId_created` (`fromId`,`toId`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `IMMessage_5` */

/*Table structure for table `IMMessage_6` */

DROP TABLE IF EXISTS `IMMessage_6`;

CREATE TABLE `IMMessage_6` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relateId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `fromId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `toId` int(11) unsigned NOT NULL COMMENT '接收用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin DEFAULT '' COMMENT '消息内容',
  `type` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '消息类型',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0正常 1被删除',
  `updated` int(11) unsigned NOT NULL COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_relateId_status_created` (`relateId`,`status`,`created`),
  KEY `idx_relateId_status_msgId_created` (`relateId`,`status`,`msgId`,`created`),
  KEY `idx_fromId_toId_created` (`fromId`,`toId`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `IMMessage_6` */

/*Table structure for table `IMMessage_7` */

DROP TABLE IF EXISTS `IMMessage_7`;

CREATE TABLE `IMMessage_7` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relateId` int(11) unsigned NOT NULL COMMENT '用户的关系id',
  `fromId` int(11) unsigned NOT NULL COMMENT '发送用户的id',
  `toId` int(11) unsigned NOT NULL COMMENT '接收用户的id',
  `msgId` int(11) unsigned NOT NULL COMMENT '消息ID',
  `content` varchar(4096) COLLATE utf8mb4_bin DEFAULT '' COMMENT '消息内容',
  `type` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '消息类型',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0正常 1被删除',
  `updated` int(11) unsigned NOT NULL COMMENT '更新时间',
  `created` int(11) unsigned NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_relateId_status_created` (`relateId`,`status`,`created`),
  KEY `idx_relateId_status_msgId_created` (`relateId`,`status`,`msgId`,`created`),
  KEY `idx_fromId_toId_created` (`fromId`,`toId`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `IMMessage_7` */

/*Table structure for table `IMRecentSession` */

DROP TABLE IF EXISTS `IMRecentSession`;

CREATE TABLE `IMRecentSession` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) unsigned NOT NULL COMMENT '用户id',
  `peerId` int(11) unsigned NOT NULL COMMENT '对方id',
  `type` tinyint(1) unsigned DEFAULT '0' COMMENT '类型，1-用户,2-群组',
  `status` tinyint(1) unsigned DEFAULT '0' COMMENT '用户:0-正常, 1-用户A删除,群组:0-正常, 1-被删除',
  `created` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  `updated` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_userId_peerId_status_updated` (`userId`,`peerId`,`status`,`updated`),
  KEY `idx_userId_peerId_type` (`userId`,`peerId`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `IMRecentSession` */

insert  into `IMRecentSession`(`id`,`userId`,`peerId`,`type`,`status`,`created`,`updated`) values (1,15,13,0,0,1509515740,1509515798),(2,13,15,0,0,1509515740,1509515798),(3,20,1,1,0,1509566283,1509566378),(4,17,1,1,1,1509566284,1509566357),(5,19,1,1,1,1509566284,1509566357);

/*Table structure for table `IMRelationShip` */

DROP TABLE IF EXISTS `IMRelationShip`;

CREATE TABLE `IMRelationShip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `smallId` int(11) unsigned NOT NULL COMMENT '用户A的id',
  `bigId` int(11) unsigned NOT NULL COMMENT '用户B的id',
  `status` tinyint(1) unsigned DEFAULT '0' COMMENT '用户:0-正常, 1-用户A删除,群组:0-正常, 1-被删除',
  `created` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  `updated` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_smallId_bigId_status_updated` (`smallId`,`bigId`,`status`,`updated`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `IMRelationShip` */

insert  into `IMRelationShip`(`id`,`smallId`,`bigId`,`status`,`created`,`updated`) values (1,13,15,0,1509515740,1509515740);

/*Table structure for table `IMUser` */

DROP TABLE IF EXISTS `IMUser`;

CREATE TABLE `IMUser` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `sex` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '1男2女0未知',
  `name` varchar(32) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '用户名',
  `domain` varchar(32) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '拼音',
  `nick` varchar(32) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '花名,绰号等',
  `password` varchar(32) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(4) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '混淆码',
  `phone` varchar(11) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '手机号码',
  `email` varchar(64) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT 'email',
  `avatar` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '自定义用户头像',
  `departId` int(11) unsigned NOT NULL COMMENT '所属部门Id',
  `status` tinyint(2) unsigned DEFAULT '0' COMMENT '1. 试用期 2. 正式 3. 离职 4.实习',
  `created` int(11) unsigned NOT NULL COMMENT '创建时间',
  `updated` int(11) unsigned NOT NULL COMMENT '更新时间',
  `push_shield_status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0关闭勿扰 1开启勿扰',
  `sign_info` varchar(128) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '个性签名',
  PRIMARY KEY (`id`),
  KEY `idx_domain` (`domain`),
  KEY `idx_name` (`name`),
  KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `IMUser` */

insert  into `IMUser`(`id`,`sex`,`name`,`domain`,`nick`,`password`,`salt`,`phone`,`email`,`avatar`,`departId`,`status`,`created`,`updated`,`push_shield_status`,`sign_info`) values (17,1,'ww','0','ww','2ef4f4a5da51beae2d1ce7cb5f0d296b','3508','ww','ww','',36,0,0,0,1,''),(18,1,'wx','0','wx','413aaa276608944764e68dbe938403ad','3943','wx','wx','',31,0,0,0,1,''),(28,1,'qwe','0','qwe','44f2707d73480ad9fa4bc3cef5d12d37','2009','qwe','qwe','',32,0,0,0,1,''),(29,1,'12','0','12','af6cd54fc46f40b46c5a40b9533b2c92','4374','12','12','',31,0,0,0,1,'');

/*Table structure for table `manager_info` */

DROP TABLE IF EXISTS `manager_info`;

CREATE TABLE `manager_info` (
  `manager_id` mediumint(6) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `username` varchar(40) NOT NULL COMMENT '管理员姓名',
  `password` char(32) NOT NULL COMMENT '管理员密码',
  `token` varchar(40) NOT NULL COMMENT '管理员token',
  `introduction` varchar(40) NOT NULL COMMENT '管理员介绍',
  `avatar` varchar(100) NOT NULL COMMENT '管理员头像',
  PRIMARY KEY (`manager_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='管理员信息表';

/*Data for the table `manager_info` */

insert  into `manager_info`(`manager_id`,`username`,`password`,`token`,`introduction`,`avatar`) values (1,'admin','21232f297a57a5a743894a0e4a801fc3','admin','超级管理员','static/pic/gg.gif'),(2,'member','21232f297a57a5a743894a0e4a801fc3','member','用户管理员','static/pic/gg.gif'),(5,'group','21232f297a57a5a743894a0e4a801fc3','group','群组管理员','static/pic/gg.gif'),(6,'depart','21232f297a57a5a743894a0e4a801fc3','depart','部门管理员','static/pic/gg.gif');

/*Table structure for table `manager_role_info` */

DROP TABLE IF EXISTS `manager_role_info`;

CREATE TABLE `manager_role_info` (
  `id` mediumint(6) NOT NULL AUTO_INCREMENT COMMENT '管理员-角色关系ID',
  `manager_id` mediumint(6) NOT NULL COMMENT '管理员ID',
  `role_id` mediumint(6) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COMMENT='管理员-角色关系信息表';

/*Data for the table `manager_role_info` */

insert  into `manager_role_info`(`id`,`manager_id`,`role_id`) values (13,2,2),(18,5,4),(19,1,1),(20,1,3),(21,1,4);

/*Table structure for table `power_info` */

DROP TABLE IF EXISTS `power_info`;

CREATE TABLE `power_info` (
  `power_id` mediumint(6) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `power_name` varchar(40) NOT NULL COMMENT '权限名',
  `power_url` varchar(100) NOT NULL COMMENT '权限URL',
  `parent_id` mediumint(6) DEFAULT NULL COMMENT '父权限ID',
  PRIMARY KEY (`power_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='权限信息表';

/*Data for the table `power_info` */

insert  into `power_info`(`power_id`,`power_name`,`power_url`,`parent_id`) values (1,'用户 ','/layout/Layout',0),(2,'部门','/layout/Layout',0),(3,'群组','/layout/Layout',0),(4,'发现','/layout/Layout',0),(5,'管理员权限设置','/layout/Layout',0),(6,'用户管理','/function/Member',1),(7,'部门管理','/function/Depart',2),(8,'群组管理','/function/Group',3),(9,'发现管理','/function/Discovery',4),(10,'管理员设置','/management/Admin',5),(11,'角色设置','/management/Role',5),(12,'权限设置','/management/Power',5);

/*Table structure for table `role_info` */

DROP TABLE IF EXISTS `role_info`;

CREATE TABLE `role_info` (
  `role_id` mediumint(6) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(40) NOT NULL COMMENT '角色名',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='角色信息表';

/*Data for the table `role_info` */

insert  into `role_info`(`role_id`,`role_name`) values (1,'超级管理员'),(2,'用户管理员'),(3,'部门管理员'),(4,'群组管理员'),(5,'发现管理员'),(6,'权限总管理员'),(7,'管理员设置'),(8,'管理员角色设置'),(9,'管理员权限设置');

/*Table structure for table `role_power_info` */

DROP TABLE IF EXISTS `role_power_info`;

CREATE TABLE `role_power_info` (
  `id` mediumint(6) NOT NULL AUTO_INCREMENT COMMENT '角色-权限关系ID',
  `role_id` mediumint(6) NOT NULL COMMENT '角色ID',
  `power_id` mediumint(6) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COMMENT='角色-权限关系信息表';

/*Data for the table `role_power_info` */

insert  into `role_power_info`(`id`,`role_id`,`power_id`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,2,1),(14,3,2),(15,4,3),(16,4,8),(17,5,4),(18,5,9),(19,2,6),(20,3,7),(21,6,5),(22,6,10),(23,6,11),(24,6,12),(25,7,5),(26,7,10),(27,8,5),(28,8,11),(39,9,5),(40,9,12);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
