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
	private String resume; //#是否有简历，开始的时候用boolean，但是只能表示有和无两种状态，在模糊查询中，如果不设有否简历的条件无法表示，所以放弃boolean，尝试Boolean依旧失败，使用String吧
	private String remark; //  #备注
	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName
				+ ", loginName=" + loginName + ", loginPswd=" + loginPswd
				+ ", sex=" + sex + ", birthday=" + birthday + ", education="
				+ education + ", telephone=" + telephone + ", interest="
				+ interest + ", resume=" + resume + ", remark=" + remark + "]";
	}
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
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
