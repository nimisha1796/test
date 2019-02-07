package com.cts.signup.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.signup.bean.ArticleStatus;
import com.cts.signup.bean.AuthenticateUser;
import com.cts.signup.bean.Language;
import com.cts.signup.bean.SignupStatus;
import com.cts.signup.bean.User;
import com.cts.signup.service.UserService;

@RestController
@RequestMapping("/signup")
public class UserRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/user/{userId}")
	public User signupUser(@PathVariable("userId") int userId) {

		LOGGER.info("UserController signupUser() start");
		LOGGER.debug("User : {}", userId);
		return userService.getAllUser(userId);

	}

	@GetMapping("/blacklist/{userName}")
	public User UserBlacklistStatus(@PathVariable("email") String email) {
		LOGGER.info("UserController BlacklistStatus() starts");
		LOGGER.debug("User Email : {}", email);
		User user = userService.getByUserEmail(email);
		user.setBlackListStatus(!user.getBlackListStatus());
		userService.saveBlacklistStatus(user);
		LOGGER.info("UserController BlacklistStatus() ends");
		return user;
	}

	@PostMapping("/login")
	public AuthenticateUser login(@RequestBody User user) {
		LOGGER.info("UserController Login() Start");
		LOGGER.debug("User : {}", user);
		LOGGER.info("UserController Login() End");
		return userService.loginUser(user);
	}

	@PostMapping("/save")
	public SignupStatus saveUserOnSignup(@RequestBody User user) {
		LOGGER.info("UserController saveUserOnSignup() Start");
		LOGGER.debug("User Status : {} ", user);
		SignupStatus status = new SignupStatus();
		status = userService.saveUser(user);
		LOGGER.debug("Status: {} ", status);
		LOGGER.info("UserController saveUserOnSignup() End");
		return status;
	}

	@PostMapping("/saveUserStatus")
	public void saveUserStatus(@RequestBody User user) {
		LOGGER.info("UserController saveUserStatus() starts ");
		userService.saveBlacklistStatus(user);
		LOGGER.info("UserController saveUserStatus() ends");

	}

	@PostMapping("/search")
	public ArticleStatus addUserFavArticle(@RequestBody User user) {
		LOGGER.info("UserController addUserFavArticle() starts ");
		ArticleStatus articleStatus =userService.addArticle(user);
		LOGGER.info("UserController addUserFavArticle() ends");
		return articleStatus;
	}

	@GetMapping("/language")
	public List<Language> getLanguages() {
		LOGGER.info("UserController getLanguages() starts ");
		List<Language> languageList = userService.getLanguage();
		LOGGER.info("UserController getLanguages() ends ");
		return languageList;
	}

	@GetMapping("/favorite/{email}")
	public User getUserFavArticles(@PathVariable("email") String email) {
		LOGGER.info("UserController getUserFavArticles() starts ");
		User articleList = userService.showFavoriteArticle(email);
		LOGGER.info("UserController getUserFavArticles() ends ");
		return articleList;
	}

	@PostMapping("/remove")
	public void deleteArticleForUser(@RequestBody User user) {
		LOGGER.info("UserController deleteArticleForUser() starts ");
		userService.deleteArticle(user);
		LOGGER.info("UserController deleteArticleForUser() ends ");

	}

	@GetMapping("/search-analyst/{email}")
	public User searchAnalyst(@PathVariable("email") String email) {
		LOGGER.info("UserController searchAnalyst() starts ");
		return userService.searchNewsAnalyst(email);

	}
}
