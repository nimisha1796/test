package com.cts.signup.dao;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.signup.bean.User;

@Component
public class UserDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(EntityManagerFactory emFactory) {
		this.sessionFactory = emFactory.unwrap(SessionFactory.class);
	}

	@Transactional
	public User getByUserEmail(String email) {
		LOGGER.info("UserDao getByUserEmail() starts ");
		Session session = sessionFactory.openSession();
		User userListOfEmail = (User) session.createQuery("select u from User u where u.email = :email")
				.setParameter("email", email).list();
		LOGGER.info("UserDao getByUserEmail() ends ");
		session.close();
		return userListOfEmail;
	}

	@Transactional
	public void changeStatus(User user) {
		LOGGER.info("UserDao update() starts");
		Session session = sessionFactory.openSession();
		session.createQuery("update User set blacklistStatus = :status where id=:userId")
				.setParameter("status", user.getBlackListStatus()).setParameter("userId", user.getId()).executeUpdate();
		LOGGER.debug("User :{}", user);
		session.close();
		LOGGER.info("UserDao update() ends");

	}

}