package com.cts.signup.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.signup.bean.Article;
import com.cts.signup.bean.ArticleStatus;
import com.cts.signup.bean.AuthenticateUser;
import com.cts.signup.bean.Language;
import com.cts.signup.bean.Role;
import com.cts.signup.bean.SignupStatus;
import com.cts.signup.bean.User;
import com.cts.signup.dao.UserDao;
import com.cts.signup.repository.ArticleRepository;
import com.cts.signup.repository.LanguageRepository;
import com.cts.signup.repository.RoleRepository;
import com.cts.signup.repository.UserRepository;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private LanguageRepository languageRepository;

	@Transactional
	public User getAllUser(int userId) {
		LOGGER.info("UserService addUserDetails() starts ");
		LOGGER.info("End");
		return userRepository.findById(userId);
	}

	@Transactional
	public SignupStatus saveUser(User user) {
		LOGGER.info("UserService saveUser() starts");

		LOGGER.info("User {} ", user);
		SignupStatus status = new SignupStatus();
		User oldUserList = userRepository.findByEmail(user.getEmail());
		if (oldUserList == null) {
			Role role = roleRepository.findById(2);
			user.setRole(role);
			Language language = languageRepository.getOne(user.getLanguage().getId());
			user.setLanguage(language);
			userRepository.save(user);
			System.out.println("User " + user);
			status.setEmailExist(false);
			status.setMessege("Email Id not found and User Details saved successfully.");
			status.setSignupStatus(true);
			LOGGER.debug("final status is : {} ", status);
		} else {
			status.setEmailExist(true);
			status.setMessege("Email Id already exists and User Details cannot be saved.");
			status.setSignupStatus(false);
			LOGGER.debug("final status is : {} ", status);
		}

		LOGGER.info("UserService saveUser() ends");

		return status;
	}

	@Transactional
	public User getByUserEmail(String email) {
		LOGGER.info("UserService saveUser() starts");
		LOGGER.debug("User Email :{}", email);
		LOGGER.info("UserService saveUser() ends");
		return userRepository.findByEmail(email);
	}

	@Transactional
	public AuthenticateUser loginUser(User user) {
		LOGGER.info(" UserService AuthenticateUser() starts");
		LOGGER.debug("User is {}", user);
		AuthenticateUser userStatus = new AuthenticateUser();
		User newUser = userRepository.findByEmail(user.getEmail());
		if (newUser != null) {
			if (newUser.getPassword().equals(user.getPassword())) {
				userStatus.setStatus(true);
				userStatus.setUser(newUser);
				LOGGER.debug("Status Of User: {}", userStatus);
				return userStatus;
			}
		}
		userStatus.setStatus(false);
		return userStatus;
	}

	/*
	 * @Transactional public User checkUserStatus(String email) { User user =
	 * (User) userDao.getByUserEmail(email); LOGGER.info("email"); if
	 * (user.getRole().getId() == 2) { if (user.getBlackListStatus()) {
	 * user.setBlackListStatus(false); } else { user.setBlackListStatus(true); }
	 * userRepository.save(user); return (User) userDao.getByUserEmail(email); }
	 * else return null; }
	 */

	/*
	 * @Transactional public void addArticle(User user) {
	 * LOGGER.info("UserService addArticle() starts "); ArticleStatus
	 * articleStatus = new ArticleStatus(); List<Article> articles = new
	 * ArrayList<Article>(); List<Article> newArticles = new
	 * ArrayList<Article>(); newArticles = user.getArticle(); user =
	 * userRepository.findByEmail(user.getEmail()); articles =
	 * user.getArticle(); System.out.println("old articles : " + articles);
	 * System.out.println("Incoming articles : " + newArticles); for (Article
	 * articleList : newArticles) {
	 * articleList.setLanguage(languageRepository.getOne(articleList.getLanguage
	 * ().getId())); if (articleRepository.findByTitle(articleList.getTitle())
	 * == null) { articleStatus.setArticleExist(false);
	 * articleRepository.save(articleList); } articleList =
	 * articleRepository.findByTitle(articleList.getTitle());
	 * articles.add(articleList); } user.setArticle(articles);
	 * LOGGER.debug("User favourite article{}", user);
	 * userRepository.save(user); LOGGER.info("UserService addArticle() ends ");
	 * }
	 */

	public ArticleStatus addArticle(User user) {
		LOGGER.info("UserService addArticle() starts ");
		ArticleStatus articleStatus = new ArticleStatus();
		User oldUser = userRepository.findByEmail(user.getEmail());
		LOGGER.debug("User is  : {} ", oldUser);
		Article article = user.getArticle().get(0);
		LOGGER.debug("Article is  : {} ", article);

		Article oldArticle = articleRepository.findByTitle(article.getTitle());
		if (oldArticle == null) {
			article.setLanguage(languageRepository.getOne(article.getLanguage().getId()));
			articleRepository.save(article);
			Article savedArticle = articleRepository.findByTitle(article.getTitle());
			LOGGER.info("saving new article");
			articleStatus.setArticleExist(false);
			oldUser.getArticle().add(savedArticle);

		} else {
			articleStatus.setArticleExist(true);
			boolean isExists = false;
			for (Article a : oldUser.getArticle()) {
				if (a.getTitle().equals(user.getArticle().get(0).getTitle())) {
					isExists = true;
				}
			}
			if (!isExists) {
				oldUser.getArticle().add(oldArticle);
				articleStatus.setArticleExist(false);

			}
		}
		if (article.getTitle() != null) {
			userRepository.save(oldUser);
		}
		LOGGER.debug("Article is  : {} ", articleStatus);
		LOGGER.info("UserService addArticle() ends ");
		return articleStatus;
	}

	@Transactional
	public void saveBlacklistStatus(User user) {
		LOGGER.info("UserService saveBlacklistUser() starts");
		LOGGER.debug("User :{}", user);
		if (user.getRole().getId() == 1) {
			userDao.changeStatus(user);
		} else {
			user.setBlackListStatus(false);
		}

		LOGGER.info("UserService saveBlacklistUser() ends");

	}

	@Transactional
	public List<Language> getLanguage() {
		LOGGER.info("UserService getLanguage() starts");
		List<Language> languageList = languageRepository.findAll();
		LOGGER.info("UserService getLanguage() ends");
		return languageList;

	}

	@Transactional
	public User showFavoriteArticle(String email) {
		LOGGER.info("UserService showFavoriteArticle() starts ");
		User articles = userRepository.fetchEmailForArticles(email);
		LOGGER.info("UserService showFavoriteArticle() ends ");
		return articles;
	}

	@Transactional
	public void deleteArticle(User user) {
		LOGGER.info("UserService deleteArticle() Start");

		User previousUser = userRepository.findByEmail(user.getEmail());
		LOGGER.debug("actual user is  : {} ", previousUser);
		Article article = user.getArticle().get(0);
		LOGGER.debug("actual article is  : {} ", article);

		for (int i = 0; i < previousUser.getArticle().size(); i++) {
			if (previousUser.getArticle().get(i).getTitle().equals(article.getTitle())) {
				previousUser.getArticle().remove(previousUser.getArticle().get(i));
			}
		}

	}

	@Transactional
	public User searchNewsAnalyst(String email) {
		LOGGER.info("UserService getByUserRole() starts");
		User user = userRepository.findByEmail(email);
		if (user.getRole().getId() == 2) {
			LOGGER.debug("User Email :{}", email);
			LOGGER.info("UserService getByUserRole() ends");
			return user;
		} else {
			LOGGER.info("You are Admin");
		}
		return null;
	}

}
