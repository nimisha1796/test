package com.cts.signup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.signup.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findById(int id);

	User findByEmail(String email);

	User fetchEmailForArticles(@Param("email") String email);

}
