package com.cts.signup.bean;

public class AuthenticateUser {	
	
	private boolean status;
	private User user;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "AuthenticationStatus [status=" + status + ", user=" + user + "]";
	}
}
