package vo;

public class UserVo {
	
	String userId;
	String userPassword;
	String userName;
	String userAddress1;
	String userAddress2;
	String userPhone;
	String userEmail;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAddress1() {
		return userAddress1;
	}
	public void setUserAddress1(String userAddress1) {
		this.userAddress1 = userAddress1;
	}
	public String getUserAddress2() {
		return userAddress2;
	}
	public void setUserAddress2(String userAddress2) {
		this.userAddress2 = userAddress2;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@Override
	public String toString() {
		return "UserVo [userId=" + userId + ", userPassword=" + userPassword + ", userName=" + userName
				+ ", userAddress1=" + userAddress1 + ", userAddress2=" + userAddress2 + ", userPhone=" + userPhone
				+ ", userEmail=" + userEmail + "]";
	}
	
}

/*CREATE TABLE USERS(
		 USER_ID VARCHAR2(20),
		 USER_PASSWORD VARCHAR2(20),
		 USER_NAME VARCHAR2(20),
		 USER_ADDRESS1 VARCHAR2(200),
		 USER_ADDRESS2 VARCHAR2(200),
		 USER_PHONE NUMBER(15),
		 USER_EMAIL VARCHAR2(30),
		 CONSTRAINT USERS_USER_ID_PK PRIMARY KEY (USER_ID)
		)*/