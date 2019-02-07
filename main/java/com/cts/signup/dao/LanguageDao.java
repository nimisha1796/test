package com.cts.signup.dao;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.signup.bean.Language;

@Component
public class LanguageDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(LanguageDao.class);

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(EntityManagerFactory emFactory) {
		this.sessionFactory = emFactory.unwrap(SessionFactory.class);
	}

	@Transactional
	public Language getByLanguage(int id) {
		LOGGER.info("LanguageDao getByLanguage() starts ");
		Session session = sessionFactory.openSession();
		Language userLanguage = (Language) session.createQuery("select l from Langauge l where l.id = :id")
				.setParameter("id", id).list();
		LOGGER.info("LanguageDao getByLanguage() ends ");
		return userLanguage;
	}
}
