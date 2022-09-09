package com.dev.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dev.user.shared.UserDto;


public interface UserService  extends UserDetailsService {

 public UserDto createUser(UserDto user);
}
