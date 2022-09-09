package com.dev.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authManager;
//
//	@Autowired
//	jwtUtils jwtUtils;

//	@PostMapping("/signin")
//	public ResponseEntity<?> singIn(@Valid @RequestBody LoginRequest loginRequest) {
//
//		Authentication authentication = authManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		String jwt = jwtUtils.generateJwtToken(authentication);
//
//		UserDto user = (UserDto) authentication.getPrincipal();
//
//		return ResponseEntity
//				.ok(new UserResponseModel(user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserId()));
//
//	}

}
