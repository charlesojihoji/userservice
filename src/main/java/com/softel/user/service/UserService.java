package com.softel.user.service;

import java.util.List;

import com.softel.user.entity.User;
import com.softel.user.response.UserServiceResponse;


public interface UserService {
	
	public User createUser(User user);
	
	public List<UserServiceResponse> getAllUsers();
	
	public User getUser(String hotelId);
	
	public User updateUser(User user, String userId);
	
	public String deleteUser(String userId);
}
