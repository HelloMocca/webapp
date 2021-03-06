package net.mocca.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mocca.model.*;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SESSION_USER_ID = "userId";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter(SESSION_USER_ID);
		String password = request.getParameter("password");
		
		try {
			if (User.login(userId,password)) {
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
			}
			response.sendRedirect("/");
		} catch (UserNotFoundException e){
			PageController.Forward(request, response, "존재하지 않는 사용자!");
			
		} catch (PasswordMismatchException e){
			PageController.Forward(request, response, "잘못된 비밀번호!");
		}
	}

}
