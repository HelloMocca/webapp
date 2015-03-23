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
		//초기화
		User user = new User("test","1234","kim","test@mail.com");
		userDAO.delete(user.getUserId());
		
		//추가 테스트
		userDAO.insert(user);
		User findedUser = userDAO.select(user.getUserId());
		assertEquals(user, findedUser);
		
		//수정 테스트
		User updateUser = new User("test","1234","gim","test@mail.com");
		userDAO.update(updateUser);
		findedUser = userDAO.select(user.getUserId());
		assertEquals(updateUser, findedUser);
		
	}
}
