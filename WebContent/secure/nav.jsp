<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script type="text/javascript" language="javascript" src="https://code.jquery.com/jquery-1.12.4.js">
		</script>
		<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js">
		</script>
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	</head>
	<body>
		<c:if test="${not empty role}">
			<nav class="navbar navbar-inverse"> 
				<div class="container-fluid"> 
					<div class="navbar-header"> 
						<button type="button" class="collapsed navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-9" aria-expanded="false"> 
							<span class="sr-only">Toggle navigation</span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span> 
						</button> 
					<a href="/ers/secure/employeePage.jsp" class="navbar-brand">ERS</a> 
					</div> 
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-9"> 
						<ul class="nav navbar-nav"> 
							<li><a style="color:white;">Welcome <c:out value="${name}" /></a></li>
							<li><a style="color:white;">Role: <c:out value="${role}" /></a>
						</ul> 
						<ul class="nav navbar-nav navbar-right">
							<li><a href="employeePage.jsp">Home</a></li>
							<c:if test="${role.equals('Employee')}">
								<li><a href="toAdd.jsp">Add Reimbursement</a></li> 
							</c:if> 
							<li><a href="logout.jsp">Log Out</a></li>
						</ul>
					</div> 
				</div> 
			</nav>
		</c:if>
	</body>
</html>