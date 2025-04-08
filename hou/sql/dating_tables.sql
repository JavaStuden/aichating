-- ----------------------------
-- 用户个人资料表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_profile`;
CREATE TABLE `sys_user_profile` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `nickname` varchar(30) DEFAULT NULL COMMENT '昵称',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `gender` char(1) DEFAULT '0' COMMENT '性别（0男 1女）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `photos` varchar(1000) DEFAULT NULL COMMENT '照片列表，以逗号分隔',
  `tags` varchar(500) DEFAULT NULL COMMENT '标签，以逗号分隔',
  `bio` varchar(500) DEFAULT NULL COMMENT '个人简介',
  `latitude` decimal(10,6) DEFAULT NULL COMMENT '纬度',
  `longitude` decimal(10,6) DEFAULT NULL COMMENT '经度',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户个人资料表';

-- ----------------------------
-- 用户匹配关系表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_match`;
CREATE TABLE `sys_user_match` (
  `match_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '匹配ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `matched_user_id` bigint(20) NOT NULL COMMENT '匹配用户ID',
  `status` char(1) DEFAULT '0' COMMENT '匹配状态（0未匹配 1已匹配）',
  `last_message` varchar(500) DEFAULT NULL COMMENT '最后消息内容',
  `last_message_time` varchar(50) DEFAULT NULL COMMENT '最后消息时间',
  `unread_count` int(11) DEFAULT '0' COMMENT '未读消息数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`match_id`),
  UNIQUE KEY `idx_user_matched` (`user_id`,`matched_user_id`) COMMENT '用户匹配唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='用户匹配关系表';

-- ----------------------------
-- 用户聊天消息表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_message`;
CREATE TABLE `sys_user_message` (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `match_id` bigint(20) NOT NULL COMMENT '匹配ID',
  `from_user_id` bigint(20) NOT NULL COMMENT '发送用户ID',
  `to_user_id` bigint(20) NOT NULL COMMENT '接收用户ID',
  `content` varchar(1000) DEFAULT NULL COMMENT '消息内容',
  `type` char(1) DEFAULT '0' COMMENT '消息类型（0文本 1图片 2语音）',
  `is_read` char(1) DEFAULT '0' COMMENT '是否已读（0未读 1已读）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`message_id`),
  KEY `idx_match_id` (`match_id`) COMMENT '匹配关系索引',
  KEY `idx_to_user` (`to_user_id`,`is_read`) COMMENT '接收用户索引'
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='用户聊天消息表';

-- ----------------------------
-- 用户动态表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_moment`;
CREATE TABLE `sys_user_moment` (
  `moment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '动态ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `content` varchar(1000) DEFAULT NULL COMMENT '动态内容',
  `images` varchar(1000) DEFAULT NULL COMMENT '图片列表，以逗号分隔',
  `like_count` int(11) DEFAULT '0' COMMENT '点赞数',
  `comment_count` int(11) DEFAULT '0' COMMENT '评论数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`moment_id`),
  KEY `idx_user_id` (`user_id`) COMMENT '用户ID索引',
  KEY `idx_create_time` (`create_time`) COMMENT '创建时间索引'
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='用户动态表';

-- ----------------------------
-- 用户点赞关系表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_like`;
CREATE TABLE `sys_user_like` (
  `like_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '点赞ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `target_user_id` bigint(20) NOT NULL COMMENT '被点赞用户ID',
  `type` char(1) DEFAULT '0' COMMENT '点赞类型（0用户 1动态）',
  `moment_id` bigint(20) DEFAULT NULL COMMENT '动态ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`like_id`),
  UNIQUE KEY `idx_user_moment` (`user_id`,`moment_id`) COMMENT '用户动态点赞唯一索引',
  UNIQUE KEY `idx_user_target` (`user_id`,`target_user_id`,`type`) COMMENT '用户点赞唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='用户点赞关系表'; 