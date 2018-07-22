DROP TABLE IF EXISTS `sbp_user`;
CREATE TABLE `sbp_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机号码',
  `create_at` bigint(20) NOT NULL,
  `update_at` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `sbp_product`;
CREATE TABLE `sbp_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '产品代码',
  `type` int(5) DEFAULT '0' COMMENT '产品类型',
  `full_name` varchar(64) NOT NULL DEFAULT '' COMMENT '产品全名',
  `alias_name` varchar(32) NOT NULL DEFAULT '' COMMENT '产品别名',
  `original_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '原价',
  `vip_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'vip价格',
  `storage` int(11) DEFAULT '0' COMMENT '库存',
  `create_at` bigint(20) NOT NULL DEFAULT '0',
  `update_at` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;