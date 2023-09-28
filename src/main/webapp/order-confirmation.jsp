<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
    <title>Order Confirmation</title>
</head>
<body>
    <h2>Order Confirmation</h2>
    <p>Your order has been successfully placed.</p>
    <p>Order ID: <%= getOrderID() %></p>
    <p>Order Date: <%= getCurrentDate() %></p>
    <p>Products Ordered: <%= request.getAttribute("productsOrdered") %></p>

    <%!
        // Define a function to generate a unique order ID
        private String getOrderID() {
            return "ORD-" + System.currentTimeMillis();
        }

        // Define a function to get the current date and time
        private String getCurrentDate() {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            return formatter.format(date);
        }
    %>
</body>
</html>
