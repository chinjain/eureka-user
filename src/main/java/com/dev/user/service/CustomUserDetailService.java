package com.dev.user.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.user.model.UserEntity;
import com.dev.user.repository.UserRepository;
import com.dev.user.shared.UserDto;

@Service
public class CustomUserDetailService implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	

	@Override
	public UserDto createUser(UserDto user) {
		user.setUserId(UUID.randomUUID().toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserEntity entity = mapper.map(user, UserEntity.class);
		userRepository.save(entity);

		UserDto userDto = mapper.map(entity, UserDto.class);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(username);
		if(userEntity == null) {
			throw new UsernameNotFoundException("User Not Found!!");
		}
		return new User(userEntity.getEmail(),userEntity.getEncryptPass(),true,true,true,true,null);
		
	}


}
