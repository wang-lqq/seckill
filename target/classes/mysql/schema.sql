-- ��ʼ�����ݿ�ű�

-- �������ݿ�
create database seckill;
-- ʹ�����ݿ�
use seckill;
--������ɱ����
create table seckill(
seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '��Ʒ���ID',
name varchar(120) NOT NULL COMMENT '��Ʒ����',
number int NOT NULL COMMENT '�������',
start_time timestamp NOT NULL COMMENT '��ɱ��ʼʱ��',
end_time timestamp NOT NULL COMMENT '��ɱ����ʱ��',
create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ÿһ����¼����ʱ��',
PRIMARY KEY (seckill_id),
key	idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT '��ɱ����'

-- ��ʼ������
insert into
	seckill(name,number,start_time,end_time)
values
	('1000Ԫ��ɱiphone6',100,'2018-4-29 00:00:00','2018-4-30 00:00:00'),
	('500Ԫ��ɱipad2',200,'2018-4-29 00:00:00','2018-4-30 00:00:00'),
	('300Ԫ��ɱС��4',300,'2018-4-29 00:00:00','2018-4-30 00:00:00'),
	('200Ԫ��ɱ����note',400,'2018-4-29 00:00:00','2018-4-30 00:00:00');
	
-- ��ɱ�ɹ���ϸ��
-- �û���¼��֤�ɹ���Ϣ
create table success_killed(
seckill_id bigint NOT NULL COMMENT '��ɱ��Ʒid',
user_phone bigint NOT NULL COMMENT '�û��ֻ���',
state tinyint NOT NULL DEFAULT -1 COMMENT '״̬��־:-1��Ч  0:�ɹ� 1���Ѹ���',
create_time timestamp NOT NULL COMMENT '����ʱ��',
PRIMARY KEY(seckill_id,user_phone),/*��������*/
key idx_create_time(create_time)
)