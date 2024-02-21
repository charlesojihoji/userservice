package com.softel.user.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable String id) {

		return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {

		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String id) {

		return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id){
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
	}
}
