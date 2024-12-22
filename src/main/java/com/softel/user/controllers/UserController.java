package com.softel.user.controllers;

import java.util.ArrayList;
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
	@GetMapping("/getListOfHotelsBasedOnLocation/{place}")
	public List<HotelServiceResponse> getListOfHotelsBasedOnLocation(@PathVariable String place) {

		logger.info("Getting all hotels ");

		ResponseEntity<List<HotelServiceResponse>> listOfHotels = hotelServiceClient.getListOfHotelsBasedOnLocation(place);

		List<HotelServiceResponse> listOfHotelServiceResponse = listOfHotels.getBody();

		return listOfHotelServiceResponse;
	}
	@GetMapping("/getListOfHotelsByName/{name}")
	public List<HotelServiceResponse> getListOfHotelsByName(@PathVariable String name) {

		logger.info("Getting all hotels ");

		ResponseEntity<List<HotelServiceResponse>> listOfHotels = hotelServiceClient.getListOfHotelsByName(name);

		List<HotelServiceResponse> listOfHotelServiceResponse = listOfHotels.getBody();

		return listOfHotelServiceResponse;
	}


	@GetMapping("/getHotelsAndRatings")
	public List<HotelServiceResponse> getListOfHotelsAndRatings() {

		logger.info("Getting all hotels and their ratings");

		ResponseEntity<List<HotelServiceResponse>> listOfHotels = hotelServiceClient.getAll();

		List<HotelServiceResponse> listOfHotelServiceResponse = listOfHotels.getBody();
		List<HotelServiceResponse> listOfHotelServiceResponseList = new ArrayList<>();
		
		for(HotelServiceResponse hotelServiceResponse: listOfHotelServiceResponse) {
			String hotelId = hotelServiceResponse.getId();
			ResponseEntity<List<RateServiceResponse>> listOfRatings = rateServiceClient.getRatingByHotelId(hotelId);
			HotelServiceResponse hotelServiceResponse1 = new HotelServiceResponse();
			hotelServiceResponse1.setAbout(hotelServiceResponse.getAbout());
			hotelServiceResponse1.setId(hotelServiceResponse.getId());
			hotelServiceResponse1.setLocation(hotelServiceResponse.getLocation());
			hotelServiceResponse1.setName(hotelServiceResponse.getName());
			for(RateServiceResponse rateServiceResponse : listOfRatings.getBody()){
				hotelServiceResponse1.setRating(rateServiceResponse.getRating());
				hotelServiceResponse1.setFeedback(rateServiceResponse.getFeedback());
				listOfHotelServiceResponseList.add(hotelServiceResponse1);
			}
		}

		return listOfHotelServiceResponseList;
	}
	@GetMapping("/getListOfHotelIdsBasedOnRating/{rating}")
	public List<HotelServiceResponse> getListOfHotelIdsBasedOnRating(@PathVariable String rating) {

		logger.info("Getting all hotels and their ratings");

		ResponseEntity<List<RateServiceResponse>> listOfRatings = rateServiceClient.getListOfHotelIdsBasedOnRating(rating);
		List<HotelServiceResponse> listOfHotelServiceResponseList = new ArrayList<>();
		for(RateServiceResponse rateServiceResponse:listOfRatings.getBody()){
			HotelServiceResponse hotelServiceResponse = new HotelServiceResponse();
			ResponseEntity<HotelServiceResponse> hotels = hotelServiceClient.getHotelById(rateServiceResponse.getHotelId());
			hotelServiceResponse.setRating(rateServiceResponse.getRating());
			hotelServiceResponse.setFeedback(rateServiceResponse.getFeedback());
			hotelServiceResponse.setName(hotels.getBody().getName());
			hotelServiceResponse.setLocation(hotels.getBody().getLocation());
			hotelServiceResponse.setAbout(hotels.getBody().getAbout());
			hotelServiceResponse.setId(hotels.getBody().getId());
			listOfHotelServiceResponseList.add(hotelServiceResponse);
		}
		return listOfHotelServiceResponseList;
	}

}
