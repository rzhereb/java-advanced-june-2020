package com.oktenweb.javaadvjune.dao;

import com.oktenweb.javaadvjune.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserDao extends JpaRepository<User, Integer> {

//  @Query("Select u from User where u.username like :username")
  User findByUsername(String username);
}
