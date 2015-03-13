package net.mocca.model;

import java.sql.SQLException;

import net.mocca.dao.UserDAO;

public class User {
  
  private String userId;
  private String password;
  private String name;
  private String email;
  
  public User(String userId, String password, String name, String email) {
	  super();
	  this.userId = userId;
	  this.password = password;
	  this.name = name;
	  this.email = email;
  }
  
  public String getName() {
	  return name;
  }

  public String getEmail() {
	  return email;
  }

  public String getUserId() {
	  return userId;
  }
  
  public String getPassword(){
	  return password;
  }
  
  

  @Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((userId == null) ? 0 : userId.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	User other = (User) obj;
	if (email == null) {
		if (other.email != null)
			return false;
	} else if (!email.equals(other.email))
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	if (password == null) {
		if (other.password != null)
			return false;
	} else if (!password.equals(other.password))
		return false;
	if (userId == null) {
		if (other.userId != null)
			return false;
	} else if (!userId.equals(other.userId))
		return false;
	return true;
}

public boolean checkPassword(String _password) {
	  return this.password.equals(_password);
  }

	public static boolean login(String userId, String password) throws UserNotFoundException, PasswordMismatchException {
		User user = null;
		UserDAO userDAO = new UserDAO();
		
		try {
			user = userDAO.select(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (user == null) {
			throw new UserNotFoundException(); 
		} 
		
		if (!(user.checkPassword(password))) {
			throw new PasswordMismatchException();
		}
		
		return true;
	}
  
	public String toString(){
		return this.userId +","+this.name+","+this.email;
	}
}
