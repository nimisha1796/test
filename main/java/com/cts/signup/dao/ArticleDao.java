package com.cts.signup.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.signup.bean.Article;

@Component
public class ArticleDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleDao.class);

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(EntityManagerFactory emFactory) {
		this.sessionFactory = emFactory.unwrap(SessionFactory.class);
	}

	@Transactional
	public List<Article> getByArticle(int id) {
		LOGGER.info("ArticleDao getByarticle() starts ");
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Article> userFavArticle = (List<Article>) session.createQuery("select a from Article a where a.id = :id")
				.setParameter("id", id).list();
		LOGGER.info("ArticleDao getByarticle() ends ");
		return userFavArticle;
	}

}
