package com.shopApp.customer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopApp.dbHandler.DataInjector;

@WebServlet("/place-order")
public class PlaceOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Get the session, and check if the user is logged in
            HttpSession session = request.getSession(false);
            if (session != null) {
                String username = (String) session.getAttribute("username");
                if (username != null) {
                    String productIds = request.getParameter("productIds"); // Assuming you receive a list of product IDs as a comma-separated string
              
                    String orderDate = getCurrentDate(); // Get the current date and time

                    // Call DataInjector to place the order
                    boolean orderPlaced = DataInjector.placeOrder(username, productIds, orderDate);

                    if (orderPlaced) {
                        // Order placed successfully, set order details as attributes and redirect to the order confirmation page
                        request.setAttribute("orderID", generateOrderID());
                        request.setAttribute("orderDate", orderDate);
                        request.setAttribute("productsOrdered", productIds);
                        request.getRequestDispatcher("order-confirmation.jsp").forward(request, response);
                    } else {
                        // Order placement failed, show an error message
                        response.getWriter().println("Order placement failed. Please try again.");
                    }
                } else {
                    // User is not logged in, handle this case (e.g., redirect to the login page)
                    response.sendRedirect("login.jsp");
                }
            } else {
                // Session doesn't exist, handle this case (e.g., redirect to the login page)
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    private String generateOrderID() {
        return "ORD-" + System.currentTimeMillis();
    }
}
