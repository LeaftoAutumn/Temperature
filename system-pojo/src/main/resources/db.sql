-- 乒乓球培训管理系统数据库创建脚本
-- 生成时间: 2025年
-- 数据库: MySQL 8.0+

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 创建数据库
CREATE DATABASE IF NOT EXISTS `table_tennis_system`
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE `table_tennis_system`;

-- 设置数据库变量
SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

-- 数据库注释
ALTER DATABASE `table_tennis_system`
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 数据库创建SQL
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 校区表
CREATE TABLE `campus`
(
    `campus_id`   CHAR(36)     NOT NULL COMMENT '校区ID',
    `name`        VARCHAR(255) NOT NULL COMMENT '校区名称',
    `address`     VARCHAR(500) DEFAULT NULL COMMENT '地址',
    `phone`       VARCHAR(50)  DEFAULT NULL COMMENT '电话',
    `center`      TINYINT(1)   DEFAULT '0' COMMENT '是否为中心校区',
    `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     TINYINT(1)   DEFAULT '0' COMMENT '逻辑删除标志',
    PRIMARY KEY (`campus_id`),
    INDEX `idx_name` (`name`),
    INDEX `idx_center` (`center`),
    INDEX `idx_create_time` (`create_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='校区信息表';

-- 用户表
CREATE TABLE `user`
(
    `id`            CHAR(36)                         NOT NULL COMMENT '用户ID',
    `username`      VARCHAR(100)                     NOT NULL COMMENT '用户名',
    `password_hash` VARCHAR(255)                     NOT NULL COMMENT '密码哈希',
    `name`          VARCHAR(100)                     NOT NULL COMMENT '真实姓名',
    `gender`        ENUM ('MALE','FEMALE','OTHER') DEFAULT NULL COMMENT '性别',
    `birth_date`    DATE                           DEFAULT NULL COMMENT '出生日期',
    `phone`         VARCHAR(20)                    DEFAULT NULL COMMENT '手机号',
    `email`         VARCHAR(100)                   DEFAULT NULL COMMENT '邮箱',
    `campus_id`     CHAR(36)                       DEFAULT NULL COMMENT '所属校区ID',
    `role`          ENUM ('STUDENT','COACH','ADMIN') NOT NULL COMMENT '用户角色',
    `create_time`   DATETIME                       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   DATETIME                       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`       TINYINT(1)                     DEFAULT '0' COMMENT '逻辑删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
    UNIQUE KEY `uk_email` (`email`),
    UNIQUE KEY `uk_phone` (`phone`),
    INDEX `idx_campus_id` (`campus_id`),
    INDEX `idx_role` (`role`),
    INDEX `idx_create_time` (`create_time`),
    CONSTRAINT `fk_user_campus` FOREIGN KEY (`campus_id`) REFERENCES `campus` (`campus_id`) ON DELETE SET NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户信息表';

-- 教练表
CREATE TABLE `coach`
(
    `user_id`      CHAR(36) NOT NULL COMMENT '用户ID',
    `level`        VARCHAR(50)    DEFAULT NULL COMMENT '教练等级',
    `hourly_rate`  DECIMAL(10, 2) DEFAULT '0.00' COMMENT '时薪',
    `photo_url`    VARCHAR(500)   DEFAULT NULL COMMENT '照片URL',
    `awards`       TEXT           DEFAULT NULL COMMENT '获奖情况',
    `max_students` INT            DEFAULT '10' COMMENT '最大学生数',
    `is_approved`  TINYINT(1)     DEFAULT '0' COMMENT '是否审核通过',
    `create_time`  DATETIME       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  DATETIME       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      TINYINT(1)     DEFAULT '0' COMMENT '逻辑删除标志',
    PRIMARY KEY (`user_id`),
    INDEX `idx_level` (`level`),
    INDEX `idx_is_approved` (`is_approved`),
    INDEX `idx_create_time` (`create_time`),
    CONSTRAINT `fk_coach_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='教练信息表';

-- 学生表
CREATE TABLE `student`
(
    `user_id`           CHAR(36) NOT NULL COMMENT '用户ID',
    `balance`           DECIMAL(10, 2) DEFAULT '0.00' COMMENT '账户余额',
    `max_coaches`       INT            DEFAULT '3' COMMENT '最大教练数',
    `cancel_count`      INT            DEFAULT '0' COMMENT '取消次数',
    `last_cancel_month` INT            DEFAULT NULL COMMENT '最后取消月份',
    `create_time`       DATETIME       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`       DATETIME       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`           TINYINT(1)     DEFAULT '0' COMMENT '逻辑删除标志',
    PRIMARY KEY (`user_id`),
    INDEX `idx_balance` (`balance`),
    INDEX `idx_create_time` (`create_time`),
    CONSTRAINT `fk_student_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='学生信息表';

-- 教练学生匹配表
CREATE TABLE `coach_student_match`
(
    `id`          CHAR(36) NOT NULL COMMENT '匹配ID',
    `student_id`  CHAR(36) NOT NULL COMMENT '学生ID',
    `coach_id`    CHAR(36) NOT NULL COMMENT '教练ID',
    `status`      ENUM ('ACTIVE','INACTIVE','PENDING') DEFAULT 'PENDING' COMMENT '匹配状态',
    `create_time` DATETIME                             DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME                             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     TINYINT(1)                           DEFAULT '0' COMMENT '逻辑删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_student_coach` (`student_id`, `coach_id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_coach_id` (`coach_id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_create_time` (`create_time`),
    CONSTRAINT `fk_match_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_match_coach` FOREIGN KEY (`coach_id`) REFERENCES `coach` (`user_id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='教练学生匹配关系表';

-- 课程表
CREATE TABLE `course`
(
    `id`           CHAR(36) NOT NULL COMMENT '课程ID',
    `coach_id`     CHAR(36) NOT NULL COMMENT '教练ID',
    `student_id`   CHAR(36) NOT NULL COMMENT '学生ID',
    `start_time`   DATETIME NOT NULL COMMENT '开始时间',
    `end_time`     DATETIME NOT NULL COMMENT '结束时间',
    `price`        DECIMAL(10, 2)                                           DEFAULT '0.00' COMMENT '价格',
    `status`       ENUM ('SCHEDULED','IN_PROGRESS','COMPLETED','CANCELLED') DEFAULT 'SCHEDULED' COMMENT '课程状态',
    `table_number` INT                                                      DEFAULT NULL COMMENT '球桌号',
    `create_time`  DATETIME                                                 DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  DATETIME                                                 DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      TINYINT(1)                                               DEFAULT '0' COMMENT '逻辑删除标志',
    PRIMARY KEY (`id`),
    INDEX `idx_coach_id` (`coach_id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_start_time` (`start_time`),
    INDEX `idx_status` (`status`),
    INDEX `idx_create_time` (`create_time`),
    CONSTRAINT `fk_course_coach` FOREIGN KEY (`coach_id`) REFERENCES `coach` (`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_course_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`user_id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='课程信息表';

-- 预约表
CREATE TABLE `reservation`
(
    `id`          CHAR(36)                        NOT NULL COMMENT '预约ID',
    `course_id`   CHAR(36)                        NOT NULL COMMENT '课程ID',
    `student_id`  CHAR(36)                        NOT NULL COMMENT '学生ID',
    `coach_id`    CHAR(36)                        NOT NULL COMMENT '教练ID',
    `action`      ENUM ('BOOK','CANCEL','MODIFY') NOT NULL COMMENT '预约动作',
    `status`      ENUM ('PENDING','CONFIRMED','CANCELLED') DEFAULT 'PENDING' COMMENT '预约状态',
    `create_time` DATETIME                                 DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME                                 DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     TINYINT(1)                               DEFAULT '0' COMMENT '逻辑删除标志',
    PRIMARY KEY (`id`),
    INDEX `idx_course_id` (`course_id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_coach_id` (`coach_id`),
    INDEX `idx_action` (`action`),
    INDEX `idx_status` (`status`),
    INDEX `idx_create_time` (`create_time`),
    CONSTRAINT `fk_reservation_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_reservation_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_reservation_coach` FOREIGN KEY (`coach_id`) REFERENCES `coach` (`user_id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='课程预约表';

-- 评价表
CREATE TABLE `evaluation`
(
    `id`           CHAR(36) NOT NULL COMMENT '评价ID',
    `course_id`    CHAR(36) NOT NULL COMMENT '课程ID',
    `from_user_id` CHAR(36) NOT NULL COMMENT '评价人ID',
    `to_user_id`   CHAR(36) NOT NULL COMMENT '被评价人ID',
    `content`      TEXT       DEFAULT NULL COMMENT '评价内容',
    `rating`       INT        DEFAULT '5' COMMENT '评分(1-5)',
    `create_time`  DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      TINYINT(1) DEFAULT '0' COMMENT '逻辑删除标志',
    PRIMARY KEY (`id`),
    INDEX `idx_course_id` (`course_id`),
    INDEX `idx_from_user_id` (`from_user_id`),
    INDEX `idx_to_user_id` (`to_user_id`),
    INDEX `idx_rating` (`rating`),
    INDEX `idx_create_time` (`create_time`),
    CONSTRAINT `fk_evaluation_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_evaluation_from_user` FOREIGN KEY (`from_user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_evaluation_to_user` FOREIGN KEY (`to_user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='课程评价表';

-- 教练更换申请表
CREATE TABLE `coach_change_request`
(
    `id`               CHAR(36) NOT NULL COMMENT '申请ID',
    `student_id`       CHAR(36) NOT NULL COMMENT '学生ID',
    `current_coach_id` CHAR(36) NOT NULL COMMENT '当前教练ID',
    `new_coach_id`     CHAR(36) NOT NULL COMMENT '新教练ID',
    `reason`           TEXT                                   DEFAULT NULL COMMENT '更换原因',
    `status`           ENUM ('PENDING','APPROVED','REJECTED') DEFAULT 'PENDING' COMMENT '申请状态',
    `create_time`      DATETIME                               DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      DATETIME                               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`          TINYINT(1)                             DEFAULT '0' COMMENT '逻辑删除标志',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_current_coach_id` (`current_coach_id`),
    INDEX `idx_new_coach_id` (`new_coach_id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_create_time` (`create_time`),
    CONSTRAINT `fk_change_request_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_change_request_current_coach` FOREIGN KEY (`current_coach_id`) REFERENCES `coach` (`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_change_request_new_coach` FOREIGN KEY (`new_coach_id`) REFERENCES `coach` (`user_id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='教练更换申请表';

-- 支付表
CREATE TABLE `payment`
(
    `id`             CHAR(36)                                      NOT NULL COMMENT '支付ID',
    `user_id`        CHAR(36)                                      NOT NULL COMMENT '用户ID',
    `related_id`     CHAR(36)                              DEFAULT NULL COMMENT '关联业务ID',
    `amount`         DECIMAL(10, 2)                                NOT NULL COMMENT '金额',
    `type`           ENUM ('TOPUP','COURSE','TOURNAMENT','REFUND') NOT NULL COMMENT '支付类型',
    `method`         ENUM ('WECHAT','ALIPAY','OFFLINE')            NOT NULL COMMENT '支付方式',
    `status`         ENUM ('PENDING','COMPLETED','FAILED') DEFAULT 'PENDING' COMMENT '支付状态',
    `order_no`       VARCHAR(100)                                  NOT NULL COMMENT '订单号',
    `qr_code_url`    VARCHAR(500)                          DEFAULT NULL COMMENT '二维码URL',
    `transaction_id` VARCHAR(100)                          DEFAULT NULL COMMENT '交易ID',
    `operator_id`    CHAR(36)                              DEFAULT NULL COMMENT '操作员ID',
    `remark`         VARCHAR(500)                          DEFAULT NULL COMMENT '备注',
    `create_time`    DATETIME                              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    DATETIME                              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`        TINYINT(1)                            DEFAULT '0' COMMENT '逻辑删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_related_id` (`related_id`),
    INDEX `idx_type` (`type`),
    INDEX `idx_method` (`method`),
    INDEX `idx_status` (`status`),
    INDEX `idx_create_time` (`create_time`),
    CONSTRAINT `fk_payment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='支付记录表';

-- 通知表
CREATE TABLE `notification`
(
    `id`           CHAR(36)     NOT NULL COMMENT '通知ID',
    `user_id`      CHAR(36)     NOT NULL COMMENT '用户ID',
    `title`        VARCHAR(200) NOT NULL COMMENT '标题',
    `content`      TEXT        DEFAULT NULL COMMENT '内容',
    `type`         VARCHAR(50) DEFAULT NULL COMMENT '通知类型',
    `related_id`   CHAR(36)    DEFAULT NULL COMMENT '关联业务ID',
    `related_type` VARCHAR(50) DEFAULT NULL COMMENT '关联业务类型',
    `is_read`      TINYINT(1)  DEFAULT '0' COMMENT '是否已读',
    `create_time`  DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      TINYINT(1)  DEFAULT '0' COMMENT '逻辑删除标志',
    `metadata`     JSON        DEFAULT NULL COMMENT '元数据(JSON格式)',
    PRIMARY KEY (`id`),
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_type` (`type`),
    INDEX `idx_related` (`related_id`, `related_type`),
    INDEX `idx_is_read` (`is_read`),
    INDEX `idx_create_time` (`create_time`),
    CONSTRAINT `fk_notification_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统通知表';

-- 系统日志表
CREATE TABLE `system_log`
(
    `id`          CHAR(36)     NOT NULL COMMENT '日志ID',
    `user_id`     CHAR(36)     DEFAULT NULL COMMENT '用户ID',
    `user_name`   VARCHAR(100) DEFAULT NULL COMMENT '用户名',
    `user_role`   VARCHAR(50)  DEFAULT NULL COMMENT '用户角色',
    `action`      VARCHAR(200) NOT NULL COMMENT '操作内容',
    `action_type` VARCHAR(50)  DEFAULT NULL COMMENT '操作类型',
    `ip_address`  VARCHAR(50)  DEFAULT NULL COMMENT 'IP地址',
    `user_agent`  VARCHAR(500) DEFAULT NULL COMMENT '用户代理',
    `details`     JSON         DEFAULT NULL COMMENT '详细信息(JSON格式)',
    `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     TINYINT(1)   DEFAULT '0' COMMENT '逻辑删除标志',
    PRIMARY KEY (`id`),
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_user_role` (`user_role`),
    INDEX `idx_action_type` (`action_type`),
    INDEX `idx_ip_address` (`ip_address`),
    INDEX `idx_create_time` (`create_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统操作日志表';

-- 比赛表
CREATE TABLE `tournament`
(
    `id`          CHAR(36)     NOT NULL COMMENT '比赛ID',
    `name`        VARCHAR(200) NOT NULL COMMENT '比赛名称',
    `event_date`  DATE         NOT NULL COMMENT '比赛日期',
    `group_type`  ENUM ('A','B','C')                      DEFAULT 'A' COMMENT '分组类型',
    `format`      ENUM ('ROUND_ROBIN','KNOCKOUT')         DEFAULT 'ROUND_ROBIN' COMMENT '赛制',
    `status`      ENUM ('UPCOMING','ONGOING','COMPLETED') DEFAULT 'UPCOMING' COMMENT '比赛状态',
    `entry_fee`   DECIMAL(10, 2)                          DEFAULT '0.00' COMMENT '报名费',
    `create_time` DATETIME                                DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME                                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     TINYINT(1)                              DEFAULT '0' COMMENT '逻辑删除标志',
    PRIMARY KEY (`id`),
    INDEX `idx_event_date` (`event_date`),
    INDEX `idx_group_type` (`group_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_create_time` (`create_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='比赛信息表';

-- 比赛对阵表
CREATE TABLE `tournament_match`
(
    `id`             CHAR(36) NOT NULL COMMENT '对阵ID',
    `tournament_id`  CHAR(36) NOT NULL COMMENT '比赛ID',
    `group_name`     VARCHAR(50)                                  DEFAULT NULL COMMENT '组别名称',
    `player1_id`     CHAR(36) NOT NULL COMMENT '选手1ID',
    `player2_id`     CHAR(36) NOT NULL COMMENT '选手2ID',
    `scheduled_time` DATETIME NOT NULL COMMENT '预定时间',
    `result`         VARCHAR(100)                                 DEFAULT NULL COMMENT '比赛结果',
    `winner_id`      CHAR(36)                                     DEFAULT NULL COMMENT '胜者ID',
    `status`         ENUM ('SCHEDULED','IN_PROGRESS','COMPLETED') DEFAULT 'SCHEDULED' COMMENT '比赛状态',
    `create_time`    DATETIME                                     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    DATETIME                                     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`        TINYINT(1)                                   DEFAULT '0' COMMENT '逻辑删除标志',
    PRIMARY KEY (`id`),
    INDEX `idx_tournament_id` (`tournament_id`),
    INDEX `idx_player1_id` (`player1_id`),
    INDEX `idx_player2_id` (`player2_id`),
    INDEX `idx_winner_id` (`winner_id`),
    INDEX `idx_scheduled_time` (`scheduled_time`),
    INDEX `idx_status` (`status`),
    INDEX `idx_create_time` (`create_time`),
    CONSTRAINT `fk_match_tournament` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_match_player1` FOREIGN KEY (`player1_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_match_player2` FOREIGN KEY (`player2_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_match_winner` FOREIGN KEY (`winner_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='比赛对阵表';

-- 比赛报名表
CREATE TABLE `tournament_registration`
(
    `id`            CHAR(36) NOT NULL COMMENT '报名ID',
    `student_id`    CHAR(36) NOT NULL COMMENT '学生ID',
    `tournament_id` CHAR(36) NOT NULL COMMENT '比赛ID',
    `fee_paid`      TINYINT(1) DEFAULT '0' COMMENT '是否已支付报名费',
    `create_time`   DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`       TINYINT(1) DEFAULT '0' COMMENT '逻辑删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_student_tournament` (`student_id`, `tournament_id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_tournament_id` (`tournament_id`),
    INDEX `idx_fee_paid` (`fee_paid`),
    INDEX `idx_create_time` (`create_time`),
    CONSTRAINT `fk_registration_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_registration_tournament` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='比赛报名表';

SET FOREIGN_KEY_CHECKS = 1;

-- 恢复原始设置
SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

-- 数据库创建完成
SELECT '数据库 table_tennis_system 创建成功!' AS '执行结果';

-- 显示所有表
SHOW TABLES;

-- 显示表结构统计
SELECT TABLE_NAME    AS '表名',
       TABLE_COMMENT AS '表注释',
       TABLE_ROWS    AS '行数',
       DATA_LENGTH   AS '数据大小(B)',
       INDEX_LENGTH  AS '索引大小(B)',
       CREATE_TIME   AS '创建时间'
FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'table_tennis_system'
ORDER BY TABLE_NAME;
