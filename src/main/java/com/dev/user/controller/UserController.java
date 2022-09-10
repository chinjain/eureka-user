package com.dev.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.user.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(allowCredentials = "true", exposedHeaders = "*", allowedHeaders = "*", origins = "*")
public class UserController {

	@Autowired
	Environment env;

	@Autowired
	UserService userService;

	@GetMapping("/status/check")
	public String status() {
		return "Working ->" + env.getProperty("local.server.port");
	}

	
}
