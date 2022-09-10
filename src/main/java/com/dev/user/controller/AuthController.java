package com.dev.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.user.jwtConfig.JwtUtils;
import com.dev.user.model.LoginRequest;
import com.dev.user.model.User;
import com.dev.user.model.UserResponseModel;
import com.dev.user.service.UserService;
import com.dev.user.shared.UserDto;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

	Logger log = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> singIn(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = this.authManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmail());

		String jwt = this.jwtUtils.generatToken(userDetails);
		log.info("<----------- Jwt token is generated----------------->");

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("token_type", "Bearer");
		response.put("token", jwt);

		return ResponseEntity.ok().body(response);

	}
	
	@PostMapping("/signUp")
	public ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody User user) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto dto = mapper.map(user, UserDto.class);

		UserDto createdUser = userService.createUser(dto);
		UserResponseModel responseModel = mapper.map(createdUser, UserResponseModel.class);

		userService.createUser(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
	}

}
