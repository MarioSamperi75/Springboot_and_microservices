package com.in28minutes.rest.webservices.resfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static int usersCount = 3;
	
	private static List<User> users = new ArrayList<>();
	
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}

	public List<User> findAll() {
		return users;
	}
	
	public User saveUser (User user) {
		if (user.getId()==null){
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for (User user:users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for (User user:users) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}
	
	//Trying to remove items using a for loop or a for-each loop would not work correctly 
	//because the collection is changing size at the same time that the code is trying to loop.
	public User deleteById(int id) {
		
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
			iterator.remove();
			return user;
			}
		}
		return null;
	}

}