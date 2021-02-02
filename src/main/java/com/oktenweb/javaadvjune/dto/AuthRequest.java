package com.oktenweb.javaadvjune.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthRequest {

  private String username;
  private String password;

}
