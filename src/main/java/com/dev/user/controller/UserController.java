package com.dev.user.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.user.model.User;
import com.dev.user.model.UserResponseModel;
import com.dev.user.service.UserService;
import com.dev.user.shared.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	Environment env;

	@Autowired
	UserService userService;

	@GetMapping("/status/check")
	public String status() {
		return "Working ->" + env.getProperty("local.server.port");
	}

	@PostMapping("/signUp")
	public ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody User user) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto dto = mapper.map(user, UserDto.class);
		
		UserDto createdUser = userService.createUser(dto);
		UserResponseModel responseModel = mapper.map(createdUser,UserResponseModel.class);
		

		userService.createUser(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
	}
}
