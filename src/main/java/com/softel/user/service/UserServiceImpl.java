package com.softel.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.softel.user.response.RateServiceResponse;
import com.softel.user.response.UserServiceResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softel.user.entity.User;
import com.softel.user.feignclient.RateServiceClient;
import com.softel.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RateServiceClient rateServiceClient;

	@Override
	public User createUser(User user) {

		logger.info("Create User:UserServiceImpl " + user);

		User newUser = null;

		user.setId(UUID.randomUUID().toString());

		try {
			newUser = userRepository.save(user);
		} catch (Exception e) {
			logger.error(
					"An Error Occurred While Creating User:UserServiceImpl. Exception message is: " + e.getMessage());

			e.printStackTrace();
		}
		return newUser;
	}

	@Override
	public List<UserServiceResponse> getAllUsers() {
		
		List<UserServiceResponse> listOfUsers = new ArrayList<>();

		UserServiceResponse userServiceResponse = new UserServiceResponse();

		logger.info("Get a List of Users:UserServiceImpl");

		List<User> userList = userRepository.findAll();

		for (User users : userList) {
			userServiceResponse.setAbout(users.getAbout());
			userServiceResponse.setEmailId(users.getEmailId());
			userServiceResponse.setId(users.getId());
			userServiceResponse.setName(users.getName());
		}
		
		ResponseEntity<List<RateServiceResponse>> rateServiceResponse = rateServiceClient.getAll();
		userServiceResponse.setRateServiceResponse(rateServiceResponse.getBody());

		listOfUsers.add(userServiceResponse);

		return listOfUsers;
	}

	@Override
	public User getUser(String hotelId) {

		logger.info("Get a Single User:UserServiceImpl " + hotelId);

		return userRepository.findUserByHotelId(hotelId).orElse(new User());
	}

	@Override
	public User updateUser(User user, String userId) {

		User refUser = null;

		User theUser = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("Error: User not updated"));

		logger.info("Update User:UserServiceImpl " + theUser + ", " + userId);

		theUser.setName(user.getName());

		theUser.setEmailId(user.getEmailId());

		theUser.setAbout(user.getAbout());

		try {
			logger.info("Updated User:UserServiceImpl to " + theUser);

			refUser = userRepository.save(theUser);
		} catch (Exception e) {

			logger.error(
					"An Error Occurred While Updating User:UserServiceImpl. Exception message is: " + e.getMessage());

			e.printStackTrace();
		}
		return refUser;
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
