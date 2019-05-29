package com.techprimers.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.techprimers.soap_webservice_example.User;

@Service
public class UserService {
	
	private static final Map<String, User> users = new HashMap<>(); 
	
	@PostConstruct
	public void Initialize() {
		User kevo = new User();
		kevo.setName("Kevo");
		kevo.setEmpId("EK01");
		kevo.setSalary(12000);
		
		User shaz = new User();
		shaz.setName("Shaz");
		shaz.setEmpId("EK01");
		shaz.setSalary(12000);
		
		users.put(kevo.getName(), kevo);
		users.put(shaz.getName(), shaz);
	}
	
	public User getUsers(String name) {
		return users.get(name);
	}
}
