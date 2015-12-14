CREATE TABLE S_User(
	userID VARCHAR(32) PRIMARY KEY, #主键ID
	userName VARCHAR(32)   NULL,  #用户姓名
	loginName VARCHAR(32)   NULL, #登录名
	loginPswd VARCHAR(32)  NULL,   #密码#
	sex CHAR(2)  NULL,        #性别（例如：男，女）
	birthday CHAR(19) NULL,       #出生日期
	education VARCHAR(4)  NULL,    #学历（例如：研究生、本科、专科、高中）
	telephone VARCHAR(16)  NULL,  #电话 
	interest VARCHAR(20)  NULL,      #兴趣爱好（例如：体育、旅游、逛街）
	resume BOOLEAN  FALSE,     #上传路径（path路径）
	remark VARCHAR(500)  NULL   #备注
) ;

#初始化数据：默认用户名和密码是admin
INSERT INTO s_user (userID,userName,logonName,logonPwd) VALUES ('128349819f99c8890e09a9b90928c3a1','超级管理员','admin','admin');