package net.mocca.model;

import static org.junit.Assert.*;
import net.mocca.db.Database;

import org.junit.Test;

public class UserTest {

	@Test
	public void checkPassword(){
		User user = new User("uid","password","name","email");
		boolean result = user.checkPassword("password");
		
		assertTrue(result);
	}
	
	@Test
	public void loginCheck() throws Exception{
		User member = new User("user1", "password", "name", "email");
		Database.addUser(member);	
		assertTrue(User.login(member.getUserId(), member.getPassword())) ;
	}
	
	@Test(expected=UserNotFoundException.class)
	public void loginWhenNotExistedUser() throws Exception{
		User member = new User("user2", "password", "name", "email");
		 	
		User.login(member.getUserId(), member.getPassword());
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void loginWhenPasswordMismatch() throws Exception{
		User member = new User("user2", "password", "name", "email");
		Database.addUser(member); 	 
		User.login(member.getUserId(), "잘못된패스워드");
	}
}