/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.40 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `blog`;

/*Table structure for table `files` */

DROP TABLE IF EXISTS `files`;

CREATE TABLE `files` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `suffix` varchar(8) DEFAULT NULL COMMENT '资源类型',
  `name` varchar(32) DEFAULT NULL COMMENT '资源名',
  `real_url` varchar(256) DEFAULT NULL COMMENT '物理路径',
  `url` varchar(128) DEFAULT NULL COMMENT '相对路径',
  `update` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `up_user` varchar(32) DEFAULT NULL COMMENT '上传人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `files` */

insert  into `files`(`id`,`suffix`,`name`,`real_url`,`url`,`update`,`up_user`) values (2,'jpg','008.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\0\\8\\b4c49cee-3aa3-4856-8f58-8297d5fb455b_008.jpg','http://www.snailblog.cn/upload/0/8/b4c49cee-3aa3-4856-8f58-8297d5fb455b_008.jpg','2016-05-29 22:57:40','admin'),(3,'jpg','010.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\0\\8\\927ff23d-5bb2-4d65-9061-ac953c9851b1_010.jpg','http://www.snailblog.cn/upload/0/8/927ff23d-5bb2-4d65-9061-ac953c9851b1_010.jpg','2016-05-29 22:57:40','admin'),(6,'jpg','016.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\4\\13\\08e1b8f8-71e0-42d0-884e-3d1e452df4c8_016.jpg','http://www.snailblog.cn/upload/4/13/08e1b8f8-71e0-42d0-884e-3d1e452df4c8_016.jpg','2016-05-29 22:57:41','admin'),(7,'jpg','018.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\11\\10\\986525bc-3bde-48de-a9b4-22ab1d77a3f3_018.jpg','http://www.snailblog.cn/upload/11/10/986525bc-3bde-48de-a9b4-22ab1d77a3f3_018.jpg','2016-05-29 22:57:41','admin'),(8,'jpg','019.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\4\\8\\82086211-02d8-4703-838e-c43251b96e77_019.jpg','http://www.snailblog.cn/upload/4/8/82086211-02d8-4703-838e-c43251b96e77_019.jpg','2016-05-29 22:57:41','admin'),(14,'jpg','110.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\15\\2\\13e6c1a5-b467-4d83-8a4e-4b08436b57b7_110.jpg','http://www.snailblog.cn/upload/15/2/13e6c1a5-b467-4d83-8a4e-4b08436b57b7_110.jpg','2016-05-29 22:59:02','admin'),(15,'jpg','011.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\6\\0\\fb88d995-e4f2-4bde-9ca5-c15a9bdf08e7_011.jpg','http://www.snailblog.cn/upload/6/0/fb88d995-e4f2-4bde-9ca5-c15a9bdf08e7_011.jpg','2016-05-29 23:06:51','admin'),(16,'jpg','013.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\9\\8\\ec50e415-a188-47c9-8af9-3efba9e9795c_013.jpg','http://www.snailblog.cn/upload/9/8/ec50e415-a188-47c9-8af9-3efba9e9795c_013.jpg','2016-05-29 23:06:51','admin'),(17,'jpg','016.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\5\\14\\82f3b6fd-1ec7-4747-831d-30eb2462eb20_016.jpg','http://www.snailblog.cn/upload/5/14/82f3b6fd-1ec7-4747-831d-30eb2462eb20_016.jpg','2016-05-29 23:06:52','admin'),(18,'jpg','018.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\2\\4\\0a3f7973-c1a8-44b5-aa87-775d65cfe08b_018.jpg','http://www.snailblog.cn/upload/2/4/0a3f7973-c1a8-44b5-aa87-775d65cfe08b_018.jpg','2016-05-29 23:06:52','admin'),(19,'jpg','018.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\9\\6\\021308f7-f57b-4cb6-97fe-12f52372408e_018.jpg','http://www.snailblog.cn/upload/9/6/021308f7-f57b-4cb6-97fe-12f52372408e_018.jpg','2016-05-29 23:10:16','admin'),(20,'jpg','028.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\1\\8\\b1195d6c-bf03-4c4c-b230-96200b0572f3_028.jpg','http://www.snailblog.cn/upload/1/8/b1195d6c-bf03-4c4c-b230-96200b0572f3_028.jpg','2016-05-29 23:10:36','admin'),(21,'jpg','066.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\14\\10\\ccc8f480-9ca9-42fb-b1bb-d3b76d5e8826_066.jpg','http://www.snailblog.cn/upload/14/10/ccc8f480-9ca9-42fb-b1bb-d3b76d5e8826_066.jpg','2016-05-29 23:11:20','admin'),(22,'jpg','026.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\1\\13\\6b76bfe0-38b0-4851-9b6c-3f767439c6e5_026.jpg','http://www.snailblog.cn/upload/1/13/6b76bfe0-38b0-4851-9b6c-3f767439c6e5_026.jpg','2016-05-29 23:12:12','admin'),(23,'jpg','018.jpg','D:\\sdk\\apache-tomcat-7.0.69\\wtpwebapps\\blog\\WEB-INF\\upload\\5\\3\\f74d8312-c7dd-4a78-b0ba-f2a75852edba_018.jpg','http://www.snailblog.cn/upload/5/3/f74d8312-c7dd-4a78-b0ba-f2a75852edba_018.jpg','2016-05-29 23:12:29','admin');

/*Table structure for table `sequence` */

DROP TABLE IF EXISTS `sequence`;

CREATE TABLE `sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='序列表，命名s_[table_name]';

/*Data for the table `sequence` */

insert  into `sequence`(`name`,`current_value`,`increment`) values ('sys_resource',59,1);

/*Table structure for table `sys_organization` */

DROP TABLE IF EXISTS `sys_organization`;

CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_sys_organization_parent_id` (`parent_id`),
  KEY `idx_sys_organization_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `sys_organization` */

insert  into `sys_organization`(`id`,`name`,`parent_id`,`parent_ids`,`available`) values (1,'总公司',0,'0/',1),(2,'分公司1',1,'0/1/',1),(3,'分公司2',1,'0/1/',1),(4,'分公司11',2,'0/1/2/',1);

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  `isRoot` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`parent_id`),
  KEY `idx_sys_resource_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`id`,`name`,`type`,`url`,`parent_id`,`parent_ids`,`permission`,`available`,`isRoot`) values (1,'系统管理','menu','',0,'0/','',1,1),(11,'组织机构管理','menu','/backend/organization',1,'0/1/','organization:*',1,0),(12,'组织机构新增','button','',11,'0/1/11/','organization:create',1,0),(13,'组织机构修改','button','',11,'0/1/11/','organization:update',1,0),(14,'组织机构删除','button','',11,'0/1/11/','organization:delete',1,0),(15,'组织机构查看','button','',11,'0/1/11/','organization:view',1,0),(21,'用户管理','menu','/backend/user',1,'0/1/','user:*',1,0),(22,'用户新增','button','',21,'0/1/21/','user:create',1,0),(23,'用户修改','button','',21,'0/1/21/','user:update',1,0),(24,'用户删除','button','',21,'0/1/21/','user:delete',1,0),(25,'用户查看','button','',21,'0/1/21/','user:view',1,0),(31,'资源管理','menu','/backend/resource',1,'0/1/','resource:*',1,0),(32,'资源新增','button','',31,'0/1/31/','resource:create',1,0),(33,'资源修改','button','',31,'0/1/31/','resource:update',1,0),(34,'资源删除','button','',31,'0/1/31/','resource:delete',1,0),(35,'资源查看','button','',31,'0/1/31/','resource:view',1,0),(41,'角色管理','menu','/backend/role',1,'0/1/','role:*',1,0),(42,'角色新增','button','',41,'0/1/41/','role:create',1,0),(43,'角色修改','button','',41,'0/1/41/','role:update',1,0),(44,'角色删除','button','',41,'0/1/41/','role:delete',1,0),(45,'角色查看','button','',41,'0/1/41/','role:view',1,0),(59,'相册管理','menu',NULL,59,'59/',NULL,1,1),(60,'相册预览',NULL,'/backend/gallery',59,'59/59/','gallery:*',1,0),(61,'图片管理',NULL,'/backend/picmanage',59,'59/59/','picmanage:*',1,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `resource_ids` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_sys_role_resource_ids` (`resource_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role`,`description`,`resource_ids`,`available`) values (1,'admin','超级管理员','11,21,31,41,60,61,',1);

/*Table structure for table `sys_url_filter` */

DROP TABLE IF EXISTS `sys_url_filter`;

CREATE TABLE `sys_url_filter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `roles` varchar(100) DEFAULT NULL,
  `permissions` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_url_filter_url` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_url_filter` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `organization_id` bigint(20) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `role_ids` varchar(100) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_user_username` (`username`),
  KEY `idx_sys_user_organization_id` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`organization_id`,`username`,`password`,`salt`,`role_ids`,`locked`) values (1,1,'admin','0c743ac4eaae25a411643ea5622eacbf','19021b8a54eda6bf043afb40a785bfc0','1',0);

/* Function  structure for function  `currval` */

/*!50003 DROP FUNCTION IF EXISTS `currval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS int(11)
    READS SQL DATA
    DETERMINISTIC
BEGIN  
  
DECLARE VALUE INTEGER;  
  
SET VALUE = 0;  
  
SELECT current_value INTO VALUE FROM sequence WHERE NAME = seq_name;  
  
RETURN VALUE;  
  
END */$$
DELIMITER ;

/* Function  structure for function  `nextval` */

/*!50003 DROP FUNCTION IF EXISTS `nextval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `nextval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN  
  
UPDATE sequence SET current_value = current_value + increment WHERE NAME = seq_name;  
  
RETURN currval(seq_name);  
  
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
