package com.techprimers.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.techprimers.service.UserService;
import com.techprimers.soap_webservice_example.GetUserRequest;
import com.techprimers.soap_webservice_example.GetUserResponse;

@Endpoint
public class UserEndpoint {
	
	private static final String namespace_uri = "http://techprimers.com/soap-webservice-example";
	
	private UserService userService;
	
	@Autowired
	private UserEndpoint(UserService userService) {
		this.userService = userService;
	}
	
	@PayloadRoot(namespace = namespace_uri, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUserRequest(@RequestPayload GetUserRequest request) {
		GetUserResponse response = new GetUserResponse();
		response.setUser(userService.getUsers(request.getName()));
		return response;
	}
}
