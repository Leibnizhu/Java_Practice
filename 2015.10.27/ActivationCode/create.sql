//创建数据库
create database activation character set utf8;
use activation;
//创建表，记录用户信息
create table users(
	id char(32) primary key,
	name varchar(32),
	pwd varchar(16),
	email varchar(32)
);
//创建表，记录激活码与用户关系，及激活码使用情况
create table active (
	uid char(32) primary key,
	accode char(64),
	constraint afk foreign key(uid) references users(id)
);