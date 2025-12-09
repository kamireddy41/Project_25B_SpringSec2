package com.telusko.SpringSec1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusko.SpringSec1.model.User;
import com.telusko.SpringSec1.repo.UserRepo;

@Service
public class UserService 
{
	
	@Autowired
	private UserRepo repo;
	
	public User registerUser(User user)
	{
		
		User us= repo.save(user);
		System.out.println(us +" stored");
		return us;
	}

}
