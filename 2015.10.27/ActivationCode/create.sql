//�������ݿ�
create database activation character set utf8;
use activation;
//��������¼�û���Ϣ
create table users(
	id char(32) primary key,
	name varchar(32),
	pwd varchar(16),
	email varchar(32)
);
//��������¼���������û���ϵ����������ʹ�����
create table active (
	uid char(32) primary key,
	accode char(64),
	constraint afk foreign key(uid) references users(id)
);