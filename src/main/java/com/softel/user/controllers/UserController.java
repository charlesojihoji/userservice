package com.softel.user.controllers;

import java.util.List;

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
import com.softel.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {

		logger.info("Create a Single User Handler: UserController " + user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable String id) {

		logger.info("Get a Single User Handler: UserController " + id);
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {

		logger.info("Get a List of Users Handler: UserController");
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String id) {

		logger.info("Update a Single User Handler: UserController " + user + ", " + id);
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id){
		
		logger.info("Delete a Single User Handler: UserController " + id);
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
	}
}
