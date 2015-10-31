//创建数据库
create database album character set utf8;
use album;
//建立一个images表用于存储图片信息
create table images(
	id char(32) primary key,
	oldname varchar(128),
	newname varchar(128),
	ip varchar(16),
	crdate char(19),
	descript varchar(128)
);