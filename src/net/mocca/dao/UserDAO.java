package net.mocca.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.mocca.model.*;

public class UserDAO {
	
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/mocca";
		String id = "root";
		String password = "";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url,id,password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void insert(User user) throws SQLException {
		String sql = "INSERT INTO USERS VALUES(?,?,?,?)";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		
		pstmt.executeUpdate();
	}

	public User select(String userId) throws SQLException {
		String sql = "SELECT * FROM USERS WHERE userId = ?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1, userId);
		
		ResultSet rs = pstmt.executeQuery();
		
		//Null Exception
		if(!rs.next()){
			return null;
		}
		
		return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
	}

}
