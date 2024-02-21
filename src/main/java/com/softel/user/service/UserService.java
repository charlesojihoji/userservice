package com.softel.user.service;

import java.util.List;

import com.softel.user.entity.User;


public interface UserService {
	
	public User createUser(User user);
	
	public List<User> getAllUsers();
	
	public User getUser(String id);
	
	public User updateUser(User user, String userId);
	
	public String deleteUser(String userId);
}
