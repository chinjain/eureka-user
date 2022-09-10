package com.dev.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.user.model.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>  {
	UserEntity findByEmail(String email);
}
