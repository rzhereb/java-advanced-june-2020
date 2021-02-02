package com.oktenweb.javaadvjune.service;

import com.oktenweb.javaadvjune.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

  String createUser(User user);

}
