package com.telusko.SpringSec1.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.SpringSec1.model.User;
import com.telusko.SpringSec1.service.JwtService;
import com.telusko.SpringSec1.service.UserService;

@RestController
public class UserController 
{
	@Autowired
	private UserService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder(12);
	
	@PostMapping("/adduser")
	public User register(@RequestBody User user)
	{
		System.out.println(user);
//		String password=user.getPassword();
//		user.setPassword(bcrypt.encode(password));
		
		user.setPassword(bcrypt.encode(user.getPassword()));
		return service.registerUser(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user)
	{
		Authentication authentication= authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
	if(authentication.isAuthenticated())
	{
		String token=jwtService.generateToken(user.getName());
		return token;
	}
	else
	{
		return "Invalid credentials";
	}
	
	}

}
