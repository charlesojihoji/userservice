package com.softel.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softel.user.entity.User;
import com.softel.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {

		user.setId(UUID.randomUUID().toString());

		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	@Override
	public User getUser(String id) {

		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: User not found"));
	}

	@Override
	public User updateUser(User user, String userId) {

		User theUser = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("Error: User not updated"));

		theUser.setName(user.getName());

		theUser.setEmailId(user.getEmailId());

		theUser.setAbout(user.getAbout());

		return userRepository.save(theUser);
	}

	@Override
	public String deleteUser(String userId) {

		try {
			userRepository.deleteById(userId);
			return "The user has been successfully deleted";
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		return "The user has not been deleted";
	}

}
