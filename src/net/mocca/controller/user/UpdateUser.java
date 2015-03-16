package net.mocca.controller.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mocca.controller.LoginServlet;
import net.mocca.dao.UserDAO;
import net.mocca.model.User;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/user/update")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object object = session.getAttribute(LoginServlet.SESSION_USER_ID);
		
		if(object == null){
			System.out.println("session_null");
			response.sendRedirect("/login.jsp");
			return;
		}
		
		String userId = (String)object;
		UserDAO userDAO = new UserDAO();
		
		try {
			User user = userDAO.select(userId);
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/update_form.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
