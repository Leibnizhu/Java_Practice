//�������ݿ�
create database album character set utf8;
use album;
//����һ��images�����ڴ洢ͼƬ��Ϣ
create table images(
	id char(32) primary key,
	oldname varchar(128),
	newname varchar(128),
	ip varchar(16),
	crdate char(19),
	descript varchar(128)
);