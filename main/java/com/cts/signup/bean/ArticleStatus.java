package com.cts.signup.bean;

public class ArticleStatus {
	private boolean articleExist;
	private String message;

	public ArticleStatus(boolean articleExist, String messege) {
		super();
		this.articleExist = articleExist;
		this.message = messege;
	}

	public boolean isArticleExist() {
		return articleExist;
	}

	public void setArticleExist(boolean articleExist) {
		this.articleExist = articleExist;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArticleStatus() {
		super();
	}

	@Override
	public String toString() {
		return "ArticleStatus [articleExist=" + articleExist + ", message=" + message + "]";
	}

}
