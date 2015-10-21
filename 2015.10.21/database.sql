CREATE DATABASE verify CHARACTER SET UTF8;
/*用户表*/
USE DATABASE verify

CREATE TABLE users(
  id VARCHAR(32) PRIMARY KEY,
  NAME VARCHAR(32),
  pwd VARCHAR(32)
);
/*创建角色表*/
CREATE TABLE roles(
  id VARCHAR(32) PRIMARY KEY,
  NAME VARCHAR(32),
  des  VARCHAR(128)
);
/*创建users和roles之间的中间表*/
CREATE TABLE userrole(
  uid VARCHAR(32),
  rid VARCHAR(32),
  CONSTRAINT ru_pk PRIMARY KEY (uid, rid),
  CONSTRAINT ru_fk1 FOREIGN KEY(uid) REFERENCES users(id),
  CONSTRAINT ru_fk2 FOREIGN KEY(rid) REFERENCES roles(id)
);
/*菜单表*/
CREATE TABLE menus(
  id VARCHAR(32) PRIMARY KEY,
  NAME VARCHAR(64),
  url  VARCHAR(128)
);
/*创建menus和roles之间的中间表*/
CREATE TABLE rolemenu(
  MID VARCHAR(32),
  rid VARCHAR(32),
  CONSTRAINT rm_pk PRIMARY KEY(MID,rid),
  CONSTRAINT rm_fk1 FOREIGN KEY(MID) REFERENCES menus(id),
  CONSTRAINT rm_fk2 FOREIGN KEY(rid) REFERENCES roles(id)
)
/*以下插入示范数据*/
INSERT INTO users VALUES('U001','Jucy','1234');
INSERT INTO users VALUES('U002','着裤','4321');
INSERT INTO users VALUES('U003','Cudy','1111');

INSERT INTO roles VALUES('R001','管理员','');
INSERT INTO roles VALUES('R002','教师','');

INSERT INTO userrole VALUES('U001','R001');
INSERT INTO userrole VALUES('U002','R002');

INSERT INTO menus VALUES('M001','系统管理','/sys.jsp');
INSERT INTO menus VALUES('M002','用户管理','/user.jsp');
INSERT INTO menus VALUES('M003','角色管理','/role.jsp');

INSERT INTO rolemenu VALUES('M001','R001');
INSERT INTO rolemenu VALUES('M002','R001');
INSERT INTO rolemenu VALUES('M003','R001');
INSERT INTO rolemenu VALUES('M003','R002');

SELECT u.name, r.name 角色,m.name 管理页面,m.url
FROM users u INNER JOIN userrole ru ON u.id=ru.uid
             INNER JOIN roles r ON ru.rid=r.id
             INNER JOIN rolemenu rm ON r.id=rm.rid
             INNER JOIN menus m ON rm.mid=m.id;