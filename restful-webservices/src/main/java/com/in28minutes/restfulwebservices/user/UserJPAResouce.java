package com.in28minutes.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.restfulwebservices.exception.UserNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/jpa")
@Api(description="User Operations")

public class UserJPAResouce {
	
	@Autowired
	private UserData userData;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "User retrieved successfully")
	})	
	@ApiOperation(value="Retrieve all users")
	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return userRepository.findAll();
	}
	
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "User retrieved successfully"),
		    @ApiResponse(code = 404, message = "User is not found")
	})	
	@ApiOperation(value="Retrieve a user of a given ID")
	@GetMapping("/users/{id}")
	public Optional<User> retriveOneUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+ id);
		return user;
	}
	
	@ApiOperation(value="save a user")
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();	
		
	}
	
	@ApiResponses(value = {
		    @ApiResponse(code = 404, message = "User is not found")
	})	
	@ApiOperation(value="delete a user of a given ID")
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@ApiOperation(value="Retrieve all of user's posts")
	@GetMapping("/users/{id}/posts")
	public List<Post> retriveAllUserPost(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			 throw new UserNotFoundException("id-"+ id);
		}
		return user.get().getPosts();
	}
	
	@ApiOperation(value="save a user's post")
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			 throw new UserNotFoundException("id-"+ id);
		}
		
		post.setUser(user.get());
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();	
		
	}
	
}
