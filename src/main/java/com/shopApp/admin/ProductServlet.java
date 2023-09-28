package com.shopApp.admin;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopApp.dbHandler.DataInjector;

@WebServlet("/addP")
public class ProductServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
            int pid = Integer.parseInt(req.getParameter("product-id"));
            String pname = req.getParameter("product-name");
            String pdesc = req.getParameter("product-description");
            int pprice = Integer.parseInt(req.getParameter("product-price"));
            String pimg = req.getParameter("product-image");

            DataInjector.addProduct(pid, pname, pdesc, pprice, pimg);
        } catch (NumberFormatException e) {
            // Handle the case where integer parsing fails (invalid input)
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input for product ID or price.");
        }
    }
}
