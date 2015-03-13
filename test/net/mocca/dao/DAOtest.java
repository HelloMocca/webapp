package net.mocca.dao;

import static org.junit.Assert.*;

import org.junit.*;

import java.sql.*;

import net.mocca.model.*;

public class DAOtest {
	private UserDAO userDAO;
	
	@Before
	public void setup() {
		userDAO = new UserDAO();
	}
	
	@Test
	public void connection(){
		Connection conn = userDAO.getConnection();
		assertNotNull(conn);
	}
	
	@Test
	public void CRUDTest() throws SQLException{
		User user = new User("test","1234","kim","test@mail.com");
		userDAO.delete(user.getUserId());
		userDAO.insert(user);
		User findedUser = userDAO.select(user.getUserId());
		
		assertEquals(user,findedUser);
	}
}
