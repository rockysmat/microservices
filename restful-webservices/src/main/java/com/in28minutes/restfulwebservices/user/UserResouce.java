package com.in28minutes.restfulwebservices.user;

import java.net.URI;
import java.util.List;

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
@RequestMapping("/api")
@Api(description="User Operations")

public class UserResouce {
	
	@Autowired
	private UserData userData;
	
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "User retrieved successfully")
	})	
	@ApiOperation(value="Retrieve all users")
	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return userData.findAll();
	}
	
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "User retrieved successfully"),
		    @ApiResponse(code = 404, message = "User is not found")
	})	
	@ApiOperation(value="Retrieve a user of a given ID")
	@GetMapping("/users/{id}")
	public User retriveOneUser(@PathVariable int id) {
		User user = userData.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id-"+ id);
		return user;
	}
	
	@ApiOperation(value="save a user")
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userData.save(user);
		
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
		User user = userData.deleteById(id);
		if(user==null)
			throw new UserNotFoundException("id-"+ id);
	}
	
}
