--创建数据库

CREATE DATABASE seckill;

--使用数据库

use seckill;

--创建秒杀库存表
CREATE TABLE seckill{

'seckill_id' bigint NOT NULL AUTO_INCREMENT,
'name' VARCHAR(120) NOT NULL,
'number' int  NOT NULL,
'start_time' timestamp NOT  NULL,
'end_time' TIMESTAMP  NOT NULL,
'create_time' TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY  KEY  (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)

}ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf-8  COMMENT='秒杀库存表';

--初始化数据
insert into
    seckill(name,number,start_time,end_time)
VALUES
    ('1000元秒杀iphone6',100,'2016-6-01 00:00:00','2016-6-02 00:00:00'),
    ('500元秒杀ipad2',200,'2016-6-01 00:00:00','2016-6-02 00:00:00'),
    ('300元秒杀小米4',300,'2016-6-01 00:00:00','2016-6-02 00:00:00'),
    ('200元秒杀红米note',400,'2016-6-01 00:00:00','2016-6-02 00:00:00');

--秒杀成功明细表
--用户登录认证相关的信息
create table success_killed{
'seckill_id' bigint NOT NULL comment '秒杀商品id',
'user_phone' bigint NOT NULL comment '用户手机号',
'state' tinyint NOT NULL comment '状态：-1:无效  0：成功  1：已付款',
'create_time' TIMESTAMP NOT NULL comment '创建时间',
PRIMARY KEY (seckill_id,user_phone), /*联合主键*/
KEY idx_create_time(create_time)
}ENGINE=InnoDB  DEFAULT CHARSET=utf-8  COMMENT='秒杀成功明细表';
