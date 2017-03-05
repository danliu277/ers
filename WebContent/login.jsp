<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
	</head>
	<body>
		<form action="login.do" method="post">
			<div>Username</div><input type="text"  name="user" /><br/>
			<div>Password</div><input type="password" name="pass" /><br/>
			<input type="submit" value="Submit" /><br/>
		</form>
		<c:if test="${not empty please_login}">
			<div style="color: red; font-weight: bolder;">
				<c:out value="${please_login}" />
			</div>
		</c:if>
		<c:choose>
			<c:when test="${not empty val}">
				<div style="color:red; font-weight: bolder;"><c:out value="Username and password does not match" /></div>
			</c:when>
		</c:choose>
	</body>
</html>