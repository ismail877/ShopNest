package com.shopApp.customer;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.shopApp.dbHandler.DataInjector;

@WebServlet("/reg")
public class RegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String uname = req.getParameter("uname");
			String mail = req.getParameter("mail");
			String pass = req.getParameter("pass");
			String gender = req.getParameter("gender");
			String address = req.getParameter("address");

	        // Call DataInjector to add the customer to the database
			String status = DataInjector.addCustomer(uname, mail, pass, gender, address);
			if ("Successfully Registered".equals(status)) {
				// Registration successful, redirect to login page
				resp.sendRedirect("login.jsp");
			} else {
				// Registration failed, show an error message on the registration page
				req.setAttribute("registrationError", "Registration failed. Please try again.");
				req.getRequestDispatcher("register.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"An error occurred while processing your request.");
		}
	}
}
