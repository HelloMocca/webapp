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
	public void insertTest() throws SQLException{
		userDAO.insert(new User("test","apapps","kim","mail@mail.com"));
	}
	
	@Test
	public void selectTest() throws SQLException{
		User user = userDAO.select("test");
		assertEquals(new User("test","apapps","kim","mail@mail.com"),user);
	}
}
