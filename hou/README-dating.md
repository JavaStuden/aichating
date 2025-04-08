# 交友匹配系统后端说明

本文档介绍了基于若依管理系统扩展的交友匹配小程序后端系统。

## 系统架构

系

## 主要功能模块

### 1. 用户个人资料管理
- 个人资料设置与修改
- 用户推荐算法
- 用户照片管理

### 2. 匹配系统
- 用户间匹配机制
- 喜欢/不喜欢操作
- 匹配列表管理

### 3. 聊天系统
- 用户间消息收发
- 未读消息提醒
- 消息历史查询

### 4. 动态系统（朋友圈）
- 发布动态
- 点赞与评论
- 动态流展示

## API接口

### 用户匹配相关API
- `GET /api/dating/recommend` - 获取推荐用户列表
- `GET /api/dating/profile/{userId}` - 获取用户详情
- `POST /api/dating/profile` - 更新用户资料
- `POST /api/dating/like` - 喜欢用户
- `POST /api/dating/dislike` - 不喜欢用户
- `GET /api/dating/matches` - 获取匹配列表
- `GET /api/dating/messages/{matchId}` - 获取聊天消息
- `POST /api/dating/message` - 发送消息

### 动态系统API
- `GET /api/dating/moments` - 获取动态列表
- `GET /api/dating/moments/user/{userId}` - 获取指定用户的动态
- `POST /api/dating/moments` - 发布动态
- `POST /api/dating/moments/like/{momentId}` - 点赞动态
- `POST /api/dating/moments/unlike/{momentId}` - 取消点赞

## 数据模型

### 数据表设计
1. `sys_user_profile` - 用户个人资料表
2. `sys_user_match` - 用户匹配关系表
3. `sys_user_message` - 用户消息表
4. `sys_user_moment` - 用户动态表
5. `sys_user_like` - 用户点赞表

## 开发说明

本系统扩展了若依框架，主要修改内容包括：

1. 在system模块中添加了匹配交友系统所需的实体类
2. 在mapper层添加了相关查询方法
3. 在service层实现了业务逻辑
4. 在controller层提供了API接口

## 配置部署

1. 创建数据库表（参考SQL文件）
2. 修改数据库连接配置
3. 部署应用服务器

## 接口安全

系统使用JWT令牌进行身份验证，同时结合Shiro权限框架进行权限控制。 