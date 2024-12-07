package com;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expensetracker.entities.Users;

import etms_users.UserCRUD;

@MultipartConfig
public class UsersCreation extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// Retrieve form parameters
		String name = req.getParameter("name") ;
		String email = req.getParameter("email") ;
		String password = req.getParameter("password") ;
		String confirmPassword = req.getParameter("confirmPassword") ;
		
		Users users = new Users() ;
		users.setName(name);
		users.setEmail(email);
		users.setPassword(password);
		users.setConfirmPassword(confirmPassword);
		
		UserCRUD userCRUD = new UserCRUD() ;
		boolean isSuccess = false;
		
		try {
			isSuccess = userCRUD.createUser(users);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Redirect or forward based on the success of the operation
        if (isSuccess) {
            req.setAttribute("message", "User created successfully!");
            req.getRequestDispatcher("create_success.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Failed to create User.");
            req.getRequestDispatcher("create_error.jsp").forward(req, resp);
        }
		
	}
	
	
}
