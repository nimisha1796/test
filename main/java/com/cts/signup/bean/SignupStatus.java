package com.cts.signup.bean;

public class SignupStatus {
	private boolean emailExist;
	private boolean signupStatus;
	private String messege;

	public SignupStatus(boolean emailExist, boolean signupStatus, String messege) {
		super();
		this.emailExist = emailExist;
		this.signupStatus = signupStatus;
		this.messege = messege;
	}

	public SignupStatus() {
		super();
	}

	public boolean isEmailExist() {
		return emailExist;
	}

	public void setEmailExist(boolean emailExist) {
		this.emailExist = emailExist;
	}

	public boolean isSignupStatus() {
		return signupStatus;
	}

	public void setSignupStatus(boolean signupStatus) {
		this.signupStatus = signupStatus;
	}

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}

	@Override
	public String toString() {
		return "SignupStatus [emailExist=" + emailExist + ", signupStatus=" + signupStatus + ", messege=" + messege
				+ "]";
	}

}
