package com.cts.signup.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "us_id", "us_name", "us_password", "us_email" }) })
@NamedQueries({
		@NamedQuery(name = "User.fetchEmailForArticles", query = "select distinct u from User u left join fetch u.article a where u.email=:email"),
		})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "us_id")
	private Integer id;

	@NotNull(message = "Name cannot be empty")
	@Size(min = 3, max = 20, message = "Name must be 3 to 20 characters")
	@Column(name = "us_name")
	private String name;

	@NotNull(message = "Email cannot be empty")
	@Size(min = 8, max = 50, message = "email must be 8 to 50 characters")
	@Column(name = "us_email")
	private String email;

	@NotNull(message = "Password cannot be empty")
	@Size(min = 3, max = 20, message = "Password must be 3 to 20 characters")
	@Column(name = "us_password")
	private String password;

	@Column(name = "us_is_blacklisted")
	private Boolean blackListStatus;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "us_language")
	private Language language;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "us_role")
	private Role role;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "article_favorite", joinColumns = { @JoinColumn(name = "af_us_id") }, inverseJoinColumns = {
			@JoinColumn(name = "af_ar_id") })
	private List<Article> article;

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getBlackListStatus() {
		return blackListStatus;
	}

	public void setBlackListStatus(Boolean blackListStatus) {
		this.blackListStatus = blackListStatus;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Article> getArticle() {
		return article;
	}

	public void setArticle(List<Article> article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", blackListStatus=" + blackListStatus + ", language=" + language + ", role=" + role + ", article="
				+ article + "]";
	}

}
