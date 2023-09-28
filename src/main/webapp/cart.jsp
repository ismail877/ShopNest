<%@ page import="com.shopApp.product.Cart" %>
<%@ page import="com.shopApp.product.Product" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ShopNest Cart</title>
    <link rel="stylesheet" href="styles.css">
    <script>
    function goBack() {
        window.history.back();
    }
</script>
    
</head>
<body>

<%
	String username = (String) session.getAttribute("username"); // Retrieve the username from the session
    Cart cart = (Cart) session.getAttribute("cart");
    List<Product> itemsList = cart.getItems();
    double total = cart.getTotal();
%>

<header>
    <h1>ShopNest Cart</h1>
    <nav>
        <ul>
            <li></li>
        </ul>
    </nav>
</header>

<main>
    <%
        for (Product product : itemsList) {
    %>
    <section class="product">
        <h2><%= product.getPname() %></h2>
        <p><%= product.getPprice() %></p>
        <span class="price">Test</span>
    </section>
    <%
        }
    %>
</main>

<br>

<table>
    <tfoot>
        <tr>
            <td colspan="3">Total:</td>
            <td><h1><%= total %></h1></td>
        </tr>
        <tr>
            <td colspan="4">
                <form action="place-order" method="post">
                    <input type="hidden" name="productIds" value="<%= getProductIdsAsString(itemsList) %>">
                    <button type="submit">Place Order</button>
                </form>
            </td>
        </tr>
    </tfoot>
</table>
<button onclick="goBack()">Back</button>


<%!
    // Define a function to convert the list of product IDs to a comma-separated string
    private String getProductIdsAsString(List<Product> products) {
        StringBuilder productIds = new StringBuilder();
        for (Product product : products) {
            if (productIds.length() > 0) {
                productIds.append(",");
            }
            productIds.append(product.getPid()); // Replace with the actual method to get the product ID
        }
        return productIds.toString();
    }
%>



</body>
</html>
