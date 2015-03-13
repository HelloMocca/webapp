package net.mocca.db;

import java.util.HashMap;
import java.util.Map;

import net.mocca.model.User;

public class Database {
	public static Map<String, User> users = new HashMap<String, User>();
	  
	public static void addUser(User user) {
		  users.put(user.getUserId(), user);
		  System.out.println("CREATE USER VALUE("+user.toString()+")");
	}

	public static User findByUserId(String userId) {
		return users.get(userId);
	}
}
