package leibniz.hu.domain;

public class User {
	//数据库中的字段
	private String userID; //主键ID
	private String userName;  //用户姓名
	private String loginName; //#登录名
	private String loginPswd; //密码#
	private String sex; //性别（例如：男，女）
	private String birthday; //#出生日期
	private String education; //学历（例如：研究生、本科、专科、高中）
	private String telephone; //#电话 
	private String interest; //#兴趣爱好（例如：体育、旅游、逛街）
	private boolean resume; //#是否有简历
	private String remark; //  #备注
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPswd() {
		return loginPswd;
	}
	public void setLoginPswd(String loginPswd) {
		this.loginPswd = loginPswd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public boolean isResume() {
		return resume;
	}
	public void setResume(boolean resume) {
		this.resume = resume;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
