package com.in28minutes.rest.webservices.resfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		List<User> users = service.findAll();
		
		if (users.size() == 0) {
			throw new UserNotFoundException("No users found in the database");
		}
		return users;
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUser (@PathVariable int id) {
		User user = service.findOne(id);
		
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> retrieveUser (@RequestBody User user) {
		
		User alreadyExistingUser = service.findByName(user.getName());
		
		if (alreadyExistingUser != null) {
			throw new ExistingUserException("Already Existing User");
		}
		
		User savedUser = service.saveUser(user);
		
		//return 201 CREATED and the path as response
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
		
		
	}
	

}
