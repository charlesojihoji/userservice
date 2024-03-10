package com.softel.user.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softel.user.entity.User;
import com.softel.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {

		logger.info("Create User:UserServiceImpl " + user);

		User newUser = null;

		user.setId(UUID.randomUUID().toString());

		try {
			newUser = userRepository.save(user);
		} catch (Exception e) {

			logger.error(
					"An Error Occurred While Creatting User:UserServiceImpl. Exception message is: " + e.getMessage());

			e.printStackTrace();
		}
		return newUser;
	}

	@Override
	public List<User> getAllUsers() {

		logger.info("Get a List of Users:UserServiceImpl");

		return userRepository.findAll();
	}

	@Override
	public User getUser(String id) {

		logger.info("Get a Single User:UserServiceImpl " + id);

		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: User not found"));
	}

	@Override
	public User updateUser(User user, String userId) {

		User theUser = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("Error: User not updated"));

		logger.info("Update User:UserServiceImpl " + theUser);

		theUser.setName(user.getName());

		theUser.setEmailId(user.getEmailId());

		theUser.setAbout(user.getAbout());

		try {
			logger.info("Updated User:UserServiceImpl to " + theUser);

			theUser = userRepository.save(theUser);
		} catch (Exception e) {

			logger.error(
					"An Error Occurred While Updating User:UserServiceImpl. Exception message is: " + e.getMessage());

			e.printStackTrace();
		}
		return theUser;
	}

	@Override
	public String deleteUser(String userId) {

		try {
			logger.info("Deleted User:UserServiceImpl " + userId);

			userRepository.deleteById(userId);

			return "The user has been successfully deleted";
		} catch (Exception e) {

			logger.error(
					"An Error Occurred While Deleting User:UserServiceImpl. Exception message is: " + e.getMessage());

			e.printStackTrace();
		}
		return "The user has not been deleted";
	}

}
