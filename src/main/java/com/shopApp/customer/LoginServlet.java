package com.shopApp.customer;

import javax.servlet.http.HttpServlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/log")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            String uname = req.getParameter("uname");
            String pass = req.getParameter("pass");
            System.out.println("Debug: Username = " + uname);
            System.out.println("Debug: Password = " + pass);
            boolean val = Validator.isValid(uname, pass);
            
            if (val && uname.equals("admin")) {
                HttpSession session = req.getSession(); // Create a new session
                session.setAttribute("username", uname); // Store the username in the session
                res.sendRedirect("admin.jsp");
            } else if (val) {
                HttpSession session = req.getSession(); // Create a new session
                session.setAttribute("username", uname); // Store the username in the session
                res.sendRedirect("home.jsp");
            } else {
                res.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }
}