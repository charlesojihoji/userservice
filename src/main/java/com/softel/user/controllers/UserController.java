package com.softel.user.controllers;

import java.util.List;

import com.softel.user.response.HotelServiceResponse;
import com.softel.user.response.RateServiceResponse;
import com.softel.user.response.UserServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softel.user.entity.User;
import com.softel.user.feignclient.HotelServiceClient;
import com.softel.user.feignclient.RateServiceClient;
import com.softel.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private HotelServiceClient hotelServiceClient;

	@Autowired
	private RateServiceClient rateServiceClient;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {

		logger.info("Create a Single User Handler: UserController " + user);

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
	}

	/*
	 * @GetMapping("/{hotelId}") public ResponseEntity<User> getUser(@PathVariable
	 * String hotelId) {
	 * 
	 * logger.info("Get a Single User Handler: UserController " + hotelId);
	 * 
	 * return
	 * ResponseEntity.status(HttpStatus.OK).body(userService.getUser(hotelId)); }
	 */

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable String userId) {

		logger.info("Get a Single User Handler: UserController " + userId);

		return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId));
	}

	@GetMapping
	public ResponseEntity<List<UserServiceResponse>> getAllUsers() {

		logger.info("Get a List of Users Handler: UserController");

		List<UserServiceResponse> userServiceResponse = userService.getAllUsers();

		return ResponseEntity.status(HttpStatus.OK).body(userServiceResponse);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String id) {

		logger.info("Update a Single User Handler: UserController " + user + ", " + id);

		return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {

		logger.info("Delete a Single User Handler: UserController " + id);

		return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
	}

	@GetMapping("/getAllHotels")
	public List<HotelServiceResponse> getListOfHotels() {

		logger.info("Getting all hotels ");

		ResponseEntity<List<HotelServiceResponse>> listOfHotels = hotelServiceClient.getAll();

		List<HotelServiceResponse> listOfHotelServiceResponse = listOfHotels.getBody();

		return listOfHotelServiceResponse; // ResponseEntity.status(HttpStatus.OK).body(listOfHotels);
	}

	@GetMapping("/getHotelsAndRatings")
	public List<HotelServiceResponse> getListOfHotelsAndRatings() {

		logger.info("Getting all hotels and their ratings");

		ResponseEntity<List<HotelServiceResponse>> listOfHotels = hotelServiceClient.getAll();

		List<HotelServiceResponse> listOfHotelServiceResponse = listOfHotels.getBody();
		
		for(HotelServiceResponse hotelServiceResponse: listOfHotelServiceResponse) {
			String hotelId = hotelServiceResponse.getId();
			ResponseEntity<List<RateServiceResponse>> listOfRatings = rateServiceClient.getRatingByHotelId(hotelId);
		}

		return listOfHotelServiceResponse;
	}
}
