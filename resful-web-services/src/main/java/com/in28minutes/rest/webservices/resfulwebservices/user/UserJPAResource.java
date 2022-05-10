package com.in28minutes.rest.webservices.resfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		List<User> users = userRepository.findAll();
		
		if (users.size() == 0) {
			throw new UserNotFoundException("No users found in the database");
		}
		return users;
	}
	
	// hateoas: we will return not just a user but an EntityModel
	// EntityModel = user + link to other data 	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser (@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		// create EntityModel, create the Link, add the link to the EntityModel
		EntityModel<User> model = EntityModel.of(user.get());
		
		// intead of hardcoding "/users" we get the link to a specific method in the actual class
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		// all-users is the name we want to show
		model.add(linkToUsers.withRel("all-users"));
		return model;
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser (@Valid @RequestBody User user) {
		
//		User alreadyExistingUser = service.findByName(user.getName());
//		
//		if (alreadyExistingUser != null) {
//			throw new ExistingUserException("Already Existing User");
//		}
		
		User savedUser = userRepository.save(user);
		
		//return 201 CREATED and the path as response
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser (@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	
	@GetMapping("/jpa/users/{id}/posts")
	public String retrieveAllPosts (@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return "Here we will have all posts for user-" + id;
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public String postNewPost (@PathVariable int id) {
		
		//User savedUser = service.saveUser(user);
		
		//return 201 CREATED and the path as response
//		URI location = ServletUriComponentsBuilder
//			.fromCurrentRequest()
//			.path("/{id}")
//			.buildAndExpand(savedUser.getId())
//			.toUri();
		
		return "We will get a created response and the path for User-" + id;
		
	}
	
	@GetMapping("/jpa/users/{userId}/posts/{postId}")
	public String retrieveOnePost (@PathVariable int userId, @PathVariable int postId) {
		Optional<User> user = userRepository.findById(userId);
		
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + userId);
		}
		
		return "Here we will see the details for post-" + postId +  " and user-" + userId;
	}
	

}
