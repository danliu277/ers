<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	</head>
	<body style="background: grey;">
		<div class="container" style="padding-top: 1%; width: 50%;">
			<form class="form-signin" action="login.do" method="post">
				<h2 class="form-signin-heading">Please Log In</h2>
				<input type="text"  name="user" class="form-control" placeholder="Username" required autofocus/>
				<input type="password" name="pass" class="form-control" placeholder="Password" required /><br/>
				<input type="submit" value="Log In" class="btn btn-lg btn-primary btn-block" /><br/>
			</form>
		</div>
		<c:if test="${not empty please_login}">
			<div style="color: red; font-weight: bolder;">
				<c:out value="${please_login}" />
			</div>
		</c:if>
		<c:if test="${not empty val}">
			<div style="color:red; font-weight: bolder;">
				<c:out value="Username and password does not match" />
			</div>
		</c:if>
	</body>
</html>