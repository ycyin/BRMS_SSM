/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-03-21 13:48:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) NOT NULL COMMENT '书名',
  `book_price` decimal(10,2) NOT NULL COMMENT '单价',
  `book_author` varchar(255) NOT NULL COMMENT '作者',
  `book_repertorySize` int(11) NOT NULL COMMENT '库存数量',
  `book_pub` int(11) NOT NULL COMMENT '出版社',
  `book_category` int(11) NOT NULL COMMENT '图书种类',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_book_index` (`book_name`) COMMENT '书名的唯一性',
  KEY `book_pub` (`book_pub`),
  KEY `book_category` (`book_category`),
  CONSTRAINT `t_book_ibfk_1` FOREIGN KEY (`book_pub`) REFERENCES `t_publisher` (`id`),
  CONSTRAINT `t_book_ibfk_2` FOREIGN KEY (`book_category`) REFERENCES `t_book_category_meta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=410 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('343', 'Java从入门到精通', '99.00', '尹以操', '21', '3', '1');
INSERT INTO `t_book` VALUES ('344', '测试1000', '10.00', '罗以健', '35', '3', '1');
INSERT INTO `t_book` VALUES ('345', '测试1001', '10.00', '罗以健', '21', '3', '6');
INSERT INTO `t_book` VALUES ('346', '测试1002', '10.00', '罗以健', '21', '3', '1');
INSERT INTO `t_book` VALUES ('347', '测试1003', '10.00', '罗以健', '21', '4', '1');
INSERT INTO `t_book` VALUES ('349', '测试1005', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('350', '测试1006', '10.00', '罗以健', '20', '1', '1');
INSERT INTO `t_book` VALUES ('351', '测试1007', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('352', '测试1008', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('353', '测试1009', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('354', '测试1010', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('355', '测试1011', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('356', '测试1012', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('357', '测试1013', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('358', '测试1014', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('359', '测试1015', '10.00', '罗以健', '21', '2', '1');
INSERT INTO `t_book` VALUES ('360', '测试1016', '10.00', '罗以健', '21', '3', '1');
INSERT INTO `t_book` VALUES ('361', '测试1017', '10.00', '罗以健', '21', '4', '1');
INSERT INTO `t_book` VALUES ('362', '测试1018', '10.00', '罗以健', '21', '5', '1');
INSERT INTO `t_book` VALUES ('363', '测试1019', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('364', '测试1020', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('365', '测试1023', '23.00', '张山', '9', '1', '1');
INSERT INTO `t_book` VALUES ('366', '测试1024', '23.00', '张山', '9', '2', '1');
INSERT INTO `t_book` VALUES ('367', '测试1025', '99.00', '爱笔笔', '9', '1', '1');
INSERT INTO `t_book` VALUES ('368', '测试1026', '78.00', '李国国', '100', '4', '1');
INSERT INTO `t_book` VALUES ('369', '测试1027', '99.00', '尹以操', '20', '5', '1');
INSERT INTO `t_book` VALUES ('370', 'Java从入门到精通1', '99.00', '尹以操', '20', '1', '1');
INSERT INTO `t_book` VALUES ('371', '测试10228', '10.00', '罗以健', '21', '3', '1');
INSERT INTO `t_book` VALUES ('372', '测试10229', '10.00', '罗以健', '21', '4', '1');
INSERT INTO `t_book` VALUES ('373', '测试10230', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('374', '测试10231', '10.00', '罗以健', '21', '5', '1');
INSERT INTO `t_book` VALUES ('375', '测试10232', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('376', '测试10233', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('377', '测试10234', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('378', '测试10235', '10.00', '罗以健', '21', '3', '1');
INSERT INTO `t_book` VALUES ('379', '测试10236', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('380', '测试10237', '10.00', '罗以健', '21', '3', '1');
INSERT INTO `t_book` VALUES ('381', '测试10238', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('382', '测试10239', '10.00', '罗以健', '21', '2', '1');
INSERT INTO `t_book` VALUES ('383', '测试10240', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('384', '测试10241', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('385', '测试10242', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('386', '测试10243', '10.00', '罗以健', '21', '1', '1');
INSERT INTO `t_book` VALUES ('387', '测试10244', '10.00', '罗以健', '21', '5', '1');
INSERT INTO `t_book` VALUES ('388', '测试10245', '10.00', '罗以健', '21', '4', '1');
INSERT INTO `t_book` VALUES ('389', '测试10246', '10.00', '罗以健', '21', '4', '1');
INSERT INTO `t_book` VALUES ('390', '测试10247', '10.00', '罗以健', '21', '3', '1');
INSERT INTO `t_book` VALUES ('391', '测试10248', '101.00', '罗以健', '21', '2', '1');
INSERT INTO `t_book` VALUES ('392', '测试10251', '23.00', '张山', '9', '2', '1');
INSERT INTO `t_book` VALUES ('393', '测试10252', '23.00', '张山', '9', '1', '1');
INSERT INTO `t_book` VALUES ('394', '测试10253', '99.00', '啊和', '9', '5', '1');
INSERT INTO `t_book` VALUES ('395', '测试10254', '78.00', '李国国', '100', '1', '1');
INSERT INTO `t_book` VALUES ('396', '测试10255', '99.00', '尹以操', '20', '2', '1');
INSERT INTO `t_book` VALUES ('397', '大学英语四级手册', '12.70', '彪三', '6', '1', '1');
INSERT INTO `t_book` VALUES ('399', '测试', '123.00', '你猜', '4', '3', '1');
INSERT INTO `t_book` VALUES ('400', '测试数据', '22.36', '张玲玲', '997', '2', '1');
INSERT INTO `t_book` VALUES ('401', '大数据开发入门', '99.99', '王振山', '27', '5', '1');
INSERT INTO `t_book` VALUES ('402', '大学不迷茫', '39.90', '佚名', '430', '4', '4');
INSERT INTO `t_book` VALUES ('403', '地理从入门到放弃', '89.76', '张雯', '7', '2', '8');
INSERT INTO `t_book` VALUES ('406', '大学不迷茫2', '20.00', '张三', '1', '1', '4');
INSERT INTO `t_book` VALUES ('407', 'qweqweqw', '20.00', '张三', '22', '4', '2');
INSERT INTO `t_book` VALUES ('408', '请问请问', '12.00', '张伟', '0', '1', '1');
INSERT INTO `t_book` VALUES ('409', '请问', '123.00', '诚实', '16', '3', '4');

-- ----------------------------
-- Table structure for t_book_category_meta
-- ----------------------------
DROP TABLE IF EXISTS `t_book_category_meta`;
CREATE TABLE `t_book_category_meta` (
  `id` int(11) NOT NULL,
  `category_name` varchar(50) NOT NULL COMMENT '图书类别',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书类别元数据';

-- ----------------------------
-- Records of t_book_category_meta
-- ----------------------------
INSERT INTO `t_book_category_meta` VALUES ('1', '计算机', '计算机类别');
INSERT INTO `t_book_category_meta` VALUES ('2', '英语', '英语类别');
INSERT INTO `t_book_category_meta` VALUES ('3', '生活', '生活类');
INSERT INTO `t_book_category_meta` VALUES ('4', '人文', '人文类');
INSERT INTO `t_book_category_meta` VALUES ('5', '科技', '科技类');
INSERT INTO `t_book_category_meta` VALUES ('6', '数学', '数学类');
INSERT INTO `t_book_category_meta` VALUES ('7', '美术', '美术类');
INSERT INTO `t_book_category_meta` VALUES ('8', '地理', '地理类');

-- ----------------------------
-- Table structure for t_disorder
-- ----------------------------
DROP TABLE IF EXISTS `t_disorder`;
CREATE TABLE `t_disorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dis_no` int(11) NOT NULL COMMENT '分销商编号',
  `book_no` int(11) NOT NULL COMMENT '图书编号',
  `ord_datetime` datetime DEFAULT NULL COMMENT '订单开始时间',
  `ord_number` int(11) NOT NULL COMMENT '订单数量',
  `ord_operateuser` int(11) DEFAULT '1' COMMENT '订单操作员（保留字段）',
  `ord_price` decimal(10,2) NOT NULL COMMENT '订单单价',
  `ord_totalprice` decimal(10,2) NOT NULL COMMENT '订单总价',
  `ord_status` varchar(50) NOT NULL COMMENT '订单状态（完成，未完成，进行中）',
  `ord_enddatetime` datetime DEFAULT NULL COMMENT '订单结束时间',
  `isCancel` int(11) NOT NULL DEFAULT '0' COMMENT '是否取消该订单',
  PRIMARY KEY (`id`),
  KEY `pk_dis` (`dis_no`),
  KEY `pk_book` (`book_no`),
  CONSTRAINT `pk_book` FOREIGN KEY (`book_no`) REFERENCES `t_book` (`id`),
  CONSTRAINT `pk_dis` FOREIGN KEY (`dis_no`) REFERENCES `t_distributors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COMMENT='分销订单表';

-- ----------------------------
-- Records of t_disorder
-- ----------------------------
INSERT INTO `t_disorder` VALUES ('8', '1', '343', '2019-12-23 16:46:25', '2', '1', '99.00', '198.00', '已完成', '2019-12-30 08:21:41', '0');
INSERT INTO `t_disorder` VALUES ('9', '1', '343', '2019-12-23 16:48:44', '1', '1', '99.00', '99.00', '已取消', '2019-12-30 08:21:41', '1');
INSERT INTO `t_disorder` VALUES ('10', '1', '343', '2019-12-23 16:54:59', '10', '1', '99.00', '990.00', '已取消', '2019-12-30 08:21:41', '1');
INSERT INTO `t_disorder` VALUES ('11', '1', '343', '2019-12-24 16:56:09', '2', '1', '99.00', '198.00', '已完成', '2019-12-30 08:21:41', '0');
INSERT INTO `t_disorder` VALUES ('12', '1', '343', '2019-12-25 16:56:41', '1', '1', '99.00', '99.00', '已取消', '2019-12-30 08:21:41', '1');
INSERT INTO `t_disorder` VALUES ('13', '1', '343', '2019-12-25 15:06:47', '5', '1', '99.00', '495.00', '已完成', '2019-12-30 08:21:41', '0');
INSERT INTO `t_disorder` VALUES ('14', '1', '343', '2019-12-26 17:21:12', '10', '1', '99.00', '990.00', '已完成', '2019-12-30 08:21:41', '0');
INSERT INTO `t_disorder` VALUES ('15', '1', '344', '2019-12-26 17:22:06', '1', '1', '10.00', '10.00', '已取消', '2019-12-30 08:21:41', '1');
INSERT INTO `t_disorder` VALUES ('16', '1', '351', '2019-12-26 14:14:37', '3', '1', '10.00', '30.00', '已取消', '2019-12-30 08:21:41', '1');
INSERT INTO `t_disorder` VALUES ('17', '1', '350', '2019-12-27 14:41:58', '1', '1', '10.00', '10.00', '已完成', '2019-12-30 08:21:41', '0');
INSERT INTO `t_disorder` VALUES ('18', '1', '345', '2019-12-27 09:07:10', '2', '1', '10.00', '20.00', '已取消', '2019-12-30 08:21:41', '1');
INSERT INTO `t_disorder` VALUES ('19', '1', '400', '2019-12-27 16:17:44', '10', '1', '22.36', '223.60', '已取消', '2019-12-30 08:21:41', '1');
INSERT INTO `t_disorder` VALUES ('20', '2', '400', '2019-12-28 20:52:41', '1', '1', '22.36', '22.36', '已完成', '2019-12-30 08:21:41', '0');
INSERT INTO `t_disorder` VALUES ('21', '2', '402', '2019-12-28 22:38:28', '14', '1', '39.90', '558.60', '已完成', '2019-12-30 08:21:41', '0');
INSERT INTO `t_disorder` VALUES ('22', '2', '400', '2019-12-28 19:41:21', '1', '1', '22.36', '22.36', '已完成', '2019-12-30 08:21:41', '0');
INSERT INTO `t_disorder` VALUES ('23', '2', '403', '2019-12-29 21:15:51', '99', '1', '89.76', '8886.24', '已完成', '2019-12-30 08:21:41', '0');
INSERT INTO `t_disorder` VALUES ('24', '2', '403', '2019-12-29 21:16:58', '1', '1', '89.76', '89.76', '进行中', '2019-12-30 08:21:41', '0');
INSERT INTO `t_disorder` VALUES ('25', '1', '403', '2019-12-29 21:17:57', '1', '1', '89.76', '89.76', '进行中', '2019-12-30 08:21:41', '0');
INSERT INTO `t_disorder` VALUES ('26', '1', '407', '2019-12-30 10:44:11', '1', '1', '20.00', '20.00', '已完成', '2020-02-21 18:21:12', '0');
INSERT INTO `t_disorder` VALUES ('27', '2', '408', '2020-02-21 18:22:33', '1', '1', '12.00', '12.00', '进行中', null, '0');
INSERT INTO `t_disorder` VALUES ('28', '1', '409', '2020-03-11 10:23:20', '10', '1', '123.00', '1230.00', '进行中', null, '0');

-- ----------------------------
-- Table structure for t_distributors
-- ----------------------------
DROP TABLE IF EXISTS `t_distributors`;
CREATE TABLE `t_distributors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dis_name` varchar(255) NOT NULL COMMENT '分销商名字',
  `dis_addr` varchar(255) NOT NULL COMMENT '地址',
  `dis_phone` varchar(11) NOT NULL COMMENT '联系电话',
  `dis_count` int(11) DEFAULT '0' COMMENT '分配的图书',
  `dis_rank` int(11) NOT NULL COMMENT '分销商等级',
  `dis_parent` int(11) DEFAULT NULL COMMENT '上级编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='分销商信息';

-- ----------------------------
-- Records of t_distributors
-- ----------------------------
INSERT INTO `t_distributors` VALUES ('1', '重庆巴南区分销总部', '重庆市巴南区红光大道69号', '17602338780', '0', '5', '0');
INSERT INTO `t_distributors` VALUES ('2', '重庆理工大学分销商', '重庆市巴南区红光大道69号', '17602338788', '0', '5', '1');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(255) NOT NULL COMMENT '权限',
  `name` varchar(50) DEFAULT NULL COMMENT '权限名',
  `url` varchar(255) NOT NULL COMMENT '权限URL',
  `available` bit(1) NOT NULL COMMENT '是否有效',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'book:get', '查询图书', '/book/getBookList,/book/getBookCategoryData,/book/getBookPressData,/book/getBookCategoryMetaValueAndLabel', '', '获取图书列表（导出）,获取图书类别图表信息,获取图书出版社图表信息,获取图书类别元信息');
INSERT INTO `t_permission` VALUES ('2', 'book:add', '添加图书', '/book/addBook,/book/importBookListData', '', '添加/导入图书');
INSERT INTO `t_permission` VALUES ('3', 'book:remove', '删除图书', '/book/removeBook', '', '删除图书');
INSERT INTO `t_permission` VALUES ('4', 'book:modify', '修改图书', '/book/modifyBook', '', '修改图书');
INSERT INTO `t_permission` VALUES ('5', 'publisher:get', '查询出版社', '/publisher/getPublisherServiceSelectValueAndLabel', '', '获取出版社元信息');
INSERT INTO `t_permission` VALUES ('6', 'distributor:get', '查询分销商', '/distributor/getDistributorSelectValueAndLabel', '', '获取分销商元信息');
INSERT INTO `t_permission` VALUES ('7', 'disOrder:add', '添加订单', '/disOrder/addOrder', '', '添加订单');
INSERT INTO `t_permission` VALUES ('8', 'disOrder:get', '查询订单', '/disOrder/getOrderList,/disOrder/getDisOrderInterval7DayData', '', '获取订单列表,获取近7天订单数据');
INSERT INTO `t_permission` VALUES ('9', 'disOrder:cancel', '取消订单', '/disOrder/cancelOrder', '', '取消订单');
INSERT INTO `t_permission` VALUES ('10', 'disOrder:modify', '修改订单', '/disOrder/modifyOrderStatus', '', '修改订单状态');
INSERT INTO `t_permission` VALUES ('11', 'permiss:get', '查询权限信息', '/permission/getAllPermission/,/permission/getPermissionsByRoleId', '', '获取所有权限信息,根据角色id获取权限');
INSERT INTO `t_permission` VALUES ('12', 'user:get', '查询用户信息', '/user/getUserInfoById,/user/getUserList', '', '根据ID获取用户信息，获取用户信息列表');
INSERT INTO `t_permission` VALUES ('13', 'user:modify', '修改用户信息', '/user/updateUser', '', '修改用户信息');
INSERT INTO `t_permission` VALUES ('14', 'user:add', '添加用户信息', '/user/addUser', '', '添加用户信息');
INSERT INTO `t_permission` VALUES ('15', 'user:remove', '删除用户信息', '/user/deleteUser', '', '删除用户信息');
INSERT INTO `t_permission` VALUES ('16', 'role:get', '查询角色信息', '/role/getRoleList', '', '获取角色列表');
INSERT INTO `t_permission` VALUES ('17', 'role:add', '添加角色权限', '/role/addOrUpdateRoleAndPermission', '', '添加角色和角色权限');

-- ----------------------------
-- Table structure for t_publisher
-- ----------------------------
DROP TABLE IF EXISTS `t_publisher`;
CREATE TABLE `t_publisher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pub_name` varchar(255) NOT NULL COMMENT '出版社名称',
  `pub_addr` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `pub_phone` varchar(11) NOT NULL COMMENT '联系电话',
  `pub_count` int(11) DEFAULT NULL COMMENT '图书本书',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='出版社信息';

-- ----------------------------
-- Records of t_publisher
-- ----------------------------
INSERT INTO `t_publisher` VALUES ('1', '重庆电子工业出版社', '重庆市渝中区红海社区一栋1号', '18808880765', '8000');
INSERT INTO `t_publisher` VALUES ('2', '江苏南京出版社', '江苏省南京区林林大道77号', '17703654177', '6666');
INSERT INTO `t_publisher` VALUES ('3', '重庆博文出版社', '重庆市沙坪坝区小龙坎正街77号', '02365583555', '7770');
INSERT INTO `t_publisher` VALUES ('4', '重庆理工大学出版社', '重庆市巴南区红光大道69号', '17603338789', '99999');
INSERT INTO `t_publisher` VALUES ('5', '北京合作共赢出版社', '北京市房山区银音街道中段78号', '01065534888', '199658');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL COMMENT '角色名',
  `available` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可用',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', '\0', '管理员');
INSERT INTO `t_role` VALUES ('2', 'user', '', '普通用户');
INSERT INTO `t_role` VALUES ('3', 'test', '', '测试账号');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `pk_permission_id` (`permission_id`),
  CONSTRAINT `pk_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`),
  CONSTRAINT `t_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '1');
INSERT INTO `t_role_permission` VALUES ('2', '1');
INSERT INTO `t_role_permission` VALUES ('1', '2');
INSERT INTO `t_role_permission` VALUES ('1', '3');
INSERT INTO `t_role_permission` VALUES ('1', '4');
INSERT INTO `t_role_permission` VALUES ('1', '5');
INSERT INTO `t_role_permission` VALUES ('2', '5');
INSERT INTO `t_role_permission` VALUES ('1', '6');
INSERT INTO `t_role_permission` VALUES ('2', '6');
INSERT INTO `t_role_permission` VALUES ('1', '7');
INSERT INTO `t_role_permission` VALUES ('1', '8');
INSERT INTO `t_role_permission` VALUES ('2', '8');
INSERT INTO `t_role_permission` VALUES ('1', '9');
INSERT INTO `t_role_permission` VALUES ('1', '10');
INSERT INTO `t_role_permission` VALUES ('1', '11');
INSERT INTO `t_role_permission` VALUES ('2', '11');
INSERT INTO `t_role_permission` VALUES ('1', '12');
INSERT INTO `t_role_permission` VALUES ('2', '12');
INSERT INTO `t_role_permission` VALUES ('1', '13');
INSERT INTO `t_role_permission` VALUES ('1', '14');
INSERT INTO `t_role_permission` VALUES ('1', '15');
INSERT INTO `t_role_permission` VALUES ('1', '16');
INSERT INTO `t_role_permission` VALUES ('2', '16');
INSERT INTO `t_role_permission` VALUES ('1', '17');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL COMMENT '密码盐值',
  `state` bit(1) DEFAULT NULL COMMENT '状态',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_index` (`username`) USING BTREE COMMENT 'user_name唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '管理员', 'bb4ddf91bd2d054a266564e84a683694', '43442676c74ae59f219c2d87fd6bad52', '', 'admin');
INSERT INTO `t_user` VALUES ('2', '普通用户', 'a735b96199226d9d40a8f6b27d305aa8', 'e6ec8e78f1d07cc4a687be4a0c3b8400', '', 'user');
INSERT INTO `t_user` VALUES ('3', '普通用户2', 'b31d340fb4dcfd1c96b6fa6de6f3a772', '877007b5b8d6918ac2ebc38ab2898086', '\0', 'user1');
INSERT INTO `t_user` VALUES ('4', '测试账户', 'f73d30aa6583dfaac9234d96f89dc29f', '60cd54a928cbbcbb6e7b5595bab46a9e', '\0', 'test');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `pk_user_id` (`user_id`),
  CONSTRAINT `pk_role_id` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `pk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1');
INSERT INTO `t_user_role` VALUES ('2', '2');
INSERT INTO `t_user_role` VALUES ('3', '3');
INSERT INTO `t_user_role` VALUES ('3', '4');

-- ----------------------------
-- View structure for view_book_category_publisher
-- ----------------------------
DROP VIEW IF EXISTS `view_book_category_publisher`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_book_category_publisher` AS select `book`.`id` AS `id`,`book`.`book_name` AS `book_name`,`book`.`book_price` AS `book_price`,`book`.`book_author` AS `book_author`,`book`.`book_repertorySize` AS `book_repertorySize`,`pub`.`pub_name` AS `pub_name`,`bcm`.`category_name` AS `category_name`,`book`.`book_pub` AS `book_pub`,`book`.`book_category` AS `book_category` from ((`t_book` `book` join `t_book_category_meta` `bcm` on((`bcm`.`id` = `book`.`book_category`))) join `t_publisher` `pub` on((`pub`.`id` = `book`.`book_pub`))) order by `book`.`id` desc ;

-- ----------------------------
-- View structure for view_disorder_book_distributor
-- ----------------------------
DROP VIEW IF EXISTS `view_disorder_book_distributor`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_disorder_book_distributor` AS select `dis`.`id` AS `id`,`distri`.`dis_name` AS `dis_name`,`book`.`book_name` AS `book_name`,`user`.`name` AS `nickname`,`dis`.`ord_number` AS `ord_number`,`dis`.`ord_price` AS `ord_price`,`dis`.`ord_totalprice` AS `ord_totalprice`,`dis`.`ord_status` AS `ord_status`,`dis`.`ord_datetime` AS `ord_datetime`,`dis`.`ord_enddatetime` AS `ord_enddatetime` from (((`t_disorder` `dis` join `t_book` `book` on((`book`.`id` = `dis`.`book_no`))) join `t_distributors` `distri` on((`distri`.`id` = `dis`.`dis_no`))) join `t_user` `user` on((`user`.`id` = `dis`.`ord_operateuser`))) order by `dis`.`ord_datetime` desc ;
