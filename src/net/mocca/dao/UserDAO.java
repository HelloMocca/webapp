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
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			pstmt.executeUpdate();		
		} finally {
			if (pstmt != null) {
				pstmt.close();				
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public User select(String userId) throws SQLException {
		String sql = "SELECT * FROM USERS WHERE userId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(!rs.next()){
				System.out.println("UserDAO: User not found! "+userId);
				return null;
			}
			return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public void delete(String userId) throws SQLException{
		String sql = "DELETE FROM USERS WHERE userId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void update(User user) throws SQLException {
		String sql = "UPDATE USERS set password = ?, name = ?, email = ? where userId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUserId());
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

}
