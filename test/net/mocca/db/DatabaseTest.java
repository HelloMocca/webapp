package net.mocca.db;

import static org.junit.Assert.*;
import net.mocca.model.User;

import org.junit.Test;



public class DatabaseTest {

	@Test
	public void addAndFind() {
		User user = new User("uid","password","name","email");
		Database.addUser(user);
		
		User dbUser = Database.findByUserId(user.getUserId());
		
		assertEquals(user, dbUser);
	}
	
	

}
