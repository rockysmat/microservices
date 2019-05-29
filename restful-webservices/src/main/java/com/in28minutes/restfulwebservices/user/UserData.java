package com.in28minutes.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Component;

@Component
public class UserData {
	private static List<User> users = new ArrayList<>();
	private static int userCount = 2;
	
	static {
		users.add(new User(01, "rotina", new Date(1990, 3, 1)));
		users.add(new User(02, "smalio", new Date()));	//1995, 04, 18
		users.add(new User(03, "konslaught", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user:users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		Iterator<User> itr = users.iterator();
		while(itr.hasNext()){
			User user = itr.next();
			if(user.getId() == id) {
				itr.remove();
				return user;
			}
		}
		return null;
	}
}

