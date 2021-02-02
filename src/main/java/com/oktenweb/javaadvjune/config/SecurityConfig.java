package com.oktenweb.javaadvjune.config;

import com.oktenweb.javaadvjune.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserService userDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user").password("user").roles("USER")
        .and().withUser("admin").password("admin").roles("ADMIN")
        .and()
        .and()
        .authenticationProvider(daoAuthenticationProvider());
//        .userDetailsService(userDetailsService);
  }

  // DaoAuthenticationProvider - провайдер, який автентифікує юзерів використовуючи базу даних.
  // userDetailsService - механізм пошуку юзерів.
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); //password decoder
    return daoAuthenticationProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
//    return NoOpPasswordEncoder.getInstance();
    return new BCryptPasswordEncoder();
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // antmatchers мають йти від НАЙБІЛЬШ КОНКРЕТНОГО до БІЛЬШ ЗАГАЛЬНОГО
    // POST /users є більш конкретним, ніж просто /POST, тому має йти найвище
    http.cors().disable().csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/users").anonymous()
        .antMatchers(HttpMethod.POST).hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/directors").hasRole("ADMIN")
        .anyRequest().anonymous()
        .and()
        .httpBasic();
  }
}
