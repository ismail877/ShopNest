<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	    <title>Customer Registration</title>
	    <link rel="stylesheet" type="text/css" href="style.css">
	</head>
<body>
	<h2> Register Here </h2>
	<div class="register">
		<form action="reg" method="post">
		<label><b>User Name</b></label> <br>
		<input type="text" name="uname" id="textbox" placeholder="Username"> <br> <br>
		<label><b>Email</b></label> <br>
		<input type="text" name="mail" id="textbox" placeholder="E Mail"> <br> <br>
		<label><b>Password</b></label> <br>
		<input type="password" name="pass" id="textbox" placeholder="Password"> <br> <br>
		<label><b>Gender</b></label> <br>
		<input type="radio" name="gender" value="male"> Male
		<input type="radio" name="gender" value="female"> Female
		<input type="radio" name="gender" value="other"> Others <br> <br>
		<label><b>Address</b></label> <br>
		<textarea rows="2" cols="20" id="textBox" class="form-control" name="address"></textarea>
		<input type="submit" id="sub" value="Register"> <br> <br>
		
		<a href="login.jsp">Already registered? Sign in instead</a>
		
		</form>
	</div>
	<%-- Display registration error message if registration fails --%>
<% String registrationError = (String) request.getAttribute("registrationError"); %>
<% if (registrationError != null) { %>
    <p><%= registrationError %></p>
<% } %>


</body>
</html>