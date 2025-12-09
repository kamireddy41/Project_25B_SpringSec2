package com.telusko.SpringSec1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telusko.SpringSec1.model.User;

public interface UserRepo extends JpaRepository<User, String> {
	
	User findByName(String userName);

}
