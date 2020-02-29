/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.18 : Database - wishbottle
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wishbottle` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `wishbottle`;

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `manager_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `manager_account` varchar(20) DEFAULT NULL COMMENT '管理员账号',
  `manager_password` varchar(20) DEFAULT NULL COMMENT '管理员密码',
  `manager_type` int(1) DEFAULT NULL COMMENT '管理员类型（0-超级，1-普通）',
  PRIMARY KEY (`manager_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `manager` */

insert  into `manager`(`manager_id`,`manager_account`,`manager_password`,`manager_type`) values (27,'aaaa','ssssss',0),(28,'test111','111111',0),(32,'admin','123456',1),(33,'ssss','123456',0),(34,'ssssssssss','666666',0);

/*Table structure for table `tags` */

DROP TABLE IF EXISTS `tags`;

CREATE TABLE `tags` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `tag_meaning` varchar(20) DEFAULT NULL COMMENT '标签内容',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `tags` */

insert  into `tags`(`tag_id`,`tag_meaning`) values (1,'热情'),(2,'快来呀'),(3,'开心'),(4,'糟糕'),(5,'吐槽'),(6,'游戏'),(7,'学习'),(8,'生活');

/*Table structure for table `treehole` */

DROP TABLE IF EXISTS `treehole`;

CREATE TABLE `treehole` (
  `treehole_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '树洞id',
  `writer_id` int(11) DEFAULT NULL COMMENT '作者id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `treehole_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '树洞内容',
  `treehole_status` int(1) DEFAULT NULL COMMENT '树洞状态（0-正常显示，1-被作者删除，2-被管理员删除）',
  `liked_number` int(11) DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`treehole_id`),
  KEY `writer_id` (`writer_id`),
  CONSTRAINT `treehole_ibfk_1` FOREIGN KEY (`writer_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `treehole` */

insert  into `treehole`(`treehole_id`,`writer_id`,`create_time`,`treehole_content`,`treehole_status`,`liked_number`) values (1,1,'2020-01-02 14:42:24','2哇哇哇哇哇哇哇哇哇哇',0,23),(2,3,'2020-01-06 14:42:57','烦烦烦方法',0,33),(3,1,'2020-02-12 11:38:08','2222',0,23),(4,3,'2020-02-18 11:38:23','日日日日日日日日日',0,22),(5,28,'2020-02-18 19:44:20','我有一个小秘密',0,0),(6,28,'2020-02-18 19:52:41','我有另外一个小秘密我有另外一个小秘密我有另外一个小秘密我有另外一个小秘密我有另外一个小秘密我有另外一个小秘密我有另外一个小秘密我有另外一个小秘密我有另外一个小秘密我有另外一个小秘密我有另外一个小秘密我有另外一个小秘密我有另外一个小秘密',0,4),(7,28,'2020-02-19 12:32:49','实训终于快做完了',0,14),(8,28,'2020-02-24 12:40:11','啦啦啦啦',2,0),(9,28,'2020-02-28 17:58:55','今天是星期五',0,0);

/*Table structure for table `treeholereply` */

DROP TABLE IF EXISTS `treeholereply`;

CREATE TABLE `treeholereply` (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `treehole_id` int(11) DEFAULT NULL COMMENT '树洞id',
  `reply_content` text COMMENT '评论内容',
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  `liked_number` int(11) DEFAULT NULL COMMENT '评论点赞数（备选功能）',
  PRIMARY KEY (`reply_id`),
  KEY `treehole_id` (`treehole_id`),
  CONSTRAINT `treeholereply_ibfk_1` FOREIGN KEY (`treehole_id`) REFERENCES `treehole` (`treehole_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `treeholereply` */

insert  into `treeholereply`(`reply_id`,`treehole_id`,`reply_content`,`create_time`,`liked_number`) values (10,7,'aaaaa','2020-02-20 11:58:50',0),(11,7,'好想出去玩','2020-02-20 13:02:01',0),(12,7,'想出去打篮球','2020-02-20 13:02:13',0),(13,7,'ggg','2020-02-20 13:20:10',0),(14,7,'这样子不好吧','2020-02-20 14:13:08',0),(15,7,'感觉不太好','2020-02-20 14:14:24',0),(16,6,'是不是傻是不是傻','2020-02-20 14:33:23',0),(19,7,'ddddd','2020-02-26 16:27:34',0);

/*Table structure for table `treeholereport` */

DROP TABLE IF EXISTS `treeholereport`;

CREATE TABLE `treeholereport` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '树洞举报id',
  `treehole_id` int(11) DEFAULT NULL COMMENT '树洞id',
  `report_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '举报原因',
  `create_time` datetime DEFAULT NULL COMMENT '举报时间',
  PRIMARY KEY (`report_id`),
  KEY `treehole_id` (`treehole_id`),
  CONSTRAINT `treeholereport_ibfk_1` FOREIGN KEY (`treehole_id`) REFERENCES `treehole` (`treehole_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `treeholereport` */

insert  into `treeholereport`(`report_id`,`treehole_id`,`report_reason`,`create_time`) values (1,1,'内容不好','2020-01-20 14:43:52'),(2,1,'错误','2020-01-02 14:44:24'),(6,4,'帆帆帆帆','2020-02-10 14:02:53'),(7,8,'enenene','2020-02-24 16:20:33');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_openid` varchar(255) NOT NULL COMMENT '用户openid，是唯一标识',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `user_age` int(3) DEFAULT NULL COMMENT '用户年龄',
  `user_gender` int(1) DEFAULT NULL COMMENT '用户性别（0-女，1-男）',
  `user_avatar` varchar(255) DEFAULT NULL COMMENT '用户头像地址',
  `user_city` varchar(20) DEFAULT NULL COMMENT '用户所在城市',
  `user_province` varchar(20) DEFAULT NULL COMMENT '用户所在省份',
  `user_birthday` date DEFAULT NULL COMMENT '用户出生日期',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_openid`,`user_name`,`user_age`,`user_gender`,`user_avatar`,`user_city`,`user_province`,`user_birthday`) values (1,'','ciciciccicici',23,0,'https://image.weilanwl.com/img/square-1.jpg','武汉','湖北','2020-01-16'),(3,'','dd',22,1,'https://image.weilanwl.com/img/square-2.jpg','长沙','长沙','2020-01-01'),(4,'','小米',21,1,'https://image.weilanwl.com/img/square-3.jpg','上海','上海','2020-01-03'),(5,'','詹姆斯',NULL,NULL,'https://image.weilanwl.com/img/square-4.jpg',NULL,'广东',NULL),(28,'o0cmL5RnqcDJ6eyWGGx6D_Gq-p0Q','好好学习天天向上',NULL,1,'http://tmp/wx9860a856c379df42.o6zAJs-XgiB4F6jv5ZCoK1LPRKmU.gb4IsN1w3BqRe4f9ad37eccd5df104dabb10318980c5.png','武汉市','湖北省','2000-01-01');

/*Table structure for table `userliketreehole` */

DROP TABLE IF EXISTS `userliketreehole`;

CREATE TABLE `userliketreehole` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞表id',
  `treehole_id` int(11) DEFAULT NULL COMMENT '树洞id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `like_status` int(1) DEFAULT NULL COMMENT '用户点赞状态（0-未点赞，1-点赞）',
  PRIMARY KEY (`id`),
  KEY `treehole_id` (`treehole_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `userliketreehole_ibfk_1` FOREIGN KEY (`treehole_id`) REFERENCES `treehole` (`treehole_id`),
  CONSTRAINT `userliketreehole_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `userliketreehole` */

insert  into `userliketreehole`(`id`,`treehole_id`,`user_id`,`like_status`) values (1,7,28,0),(2,6,28,1),(3,4,28,0),(4,5,28,0),(5,9,28,0);

/*Table structure for table `wish` */

DROP TABLE IF EXISTS `wish`;

CREATE TABLE `wish` (
  `wish_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '心愿id',
  `writer_id` int(11) DEFAULT NULL COMMENT '作者id',
  `tag_id` int(11) DEFAULT NULL COMMENT '标签id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `wish_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '心愿内容',
  `wish_status` int(1) DEFAULT '0' COMMENT '心愿状态（0-正常，1-被捞取，2-被作者删除，3-被捡到的人仍回去，4-被管理员删除）',
  `picker_id` int(11) DEFAULT NULL COMMENT '捡到该心愿的用户id',
  PRIMARY KEY (`wish_id`),
  KEY `writer_id` (`writer_id`),
  KEY `tag_id` (`tag_id`),
  KEY `picker_id` (`picker_id`),
  CONSTRAINT `wish_ibfk_1` FOREIGN KEY (`writer_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `wish_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`tag_id`),
  CONSTRAINT `wish_ibfk_3` FOREIGN KEY (`picker_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `wish` */

insert  into `wish`(`wish_id`,`writer_id`,`tag_id`,`create_time`,`wish_content`,`wish_status`,`picker_id`) values (7,1,1,'2020-01-02 14:35:39','11111',4,28),(8,3,2,'2020-01-08 14:36:25','w哇哇哇哇哇哇哇哇哇哇哇哇wawaawawa',4,28),(9,4,1,'2020-02-11 12:56:22','222',4,28),(10,5,1,'2020-02-12 12:56:44','333',1,28),(11,28,2,'2020-02-17 12:15:19','wwww',0,1),(16,28,5,'2020-02-17 19:08:13','想早点回学校想早点回学校想早点',4,3),(17,28,7,'2020-02-18 10:11:25','什么时候才可以回学校啊啊啊啊啊啊啊',0,4),(18,28,1,'2020-02-18 20:01:30','我有好多话想说有好多话想说',0,5),(19,3,6,'2020-02-23 19:14:15','想回学校上班了，在家里坐不住了',1,1),(20,4,3,'2020-02-23 19:15:46','在家太无聊了',3,28);

/*Table structure for table `wishreply` */

DROP TABLE IF EXISTS `wishreply`;

CREATE TABLE `wishreply` (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '心愿评论id',
  `wish_id` int(11) DEFAULT NULL COMMENT '心愿id',
  `replyer_id` int(11) DEFAULT NULL COMMENT '回复者id',
  `reply_content` text COMMENT '回复内容',
  `create_time` datetime DEFAULT NULL COMMENT '回复时间',
  `status` int(1) DEFAULT '2' COMMENT '评论状态（2-被创建，1-被通知，0-被阅读）',
  PRIMARY KEY (`reply_id`),
  KEY `wish_id` (`wish_id`),
  KEY `replyer_id` (`replyer_id`),
  CONSTRAINT `wishreply_ibfk_1` FOREIGN KEY (`wish_id`) REFERENCES `wish` (`wish_id`),
  CONSTRAINT `wishreply_ibfk_2` FOREIGN KEY (`replyer_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `wishreply` */

insert  into `wishreply`(`reply_id`,`wish_id`,`replyer_id`,`reply_content`,`create_time`,`status`) values (1,10,28,'你想要说什么?','2020-02-21 14:08:42',0),(5,9,28,'aaa','2020-02-24 12:07:02',0),(6,20,28,'aaaa','2020-02-24 12:07:19',0),(7,20,28,'是吗，我也觉得','2020-02-24 12:10:25',0),(10,20,4,'是的','2020-02-27 15:55:32',1),(11,10,28,'今天是星期五','2020-02-28 17:11:41',2);

/*Table structure for table `wishreport` */

DROP TABLE IF EXISTS `wishreport`;

CREATE TABLE `wishreport` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '心愿举报id',
  `wish_id` int(11) DEFAULT NULL COMMENT '心愿id',
  `report_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '举报原因',
  `create_time` datetime DEFAULT NULL COMMENT '举报时间',
  PRIMARY KEY (`report_id`),
  KEY `wish_id` (`wish_id`),
  CONSTRAINT `wishreport_ibfk_1` FOREIGN KEY (`wish_id`) REFERENCES `wish` (`wish_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `wishreport` */

insert  into `wishreport`(`report_id`,`wish_id`,`report_reason`,`create_time`) values (3,9,'e呃呃呃呃','2020-02-11 16:20:29'),(6,10,'我不喜欢','2020-02-24 16:13:14'),(7,10,'就是不喜欢','2020-02-24 16:15:27'),(8,10,'说点什么好呢','2020-02-24 16:16:33'),(9,10,'涉嫌违规','2020-02-28 17:12:03');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
