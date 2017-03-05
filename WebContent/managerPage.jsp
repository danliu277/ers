<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Manager Page</title>
	</head>
	<body>
		<% int i=1; %>
		<form action="logout.do" type="post">
			<input type="submit" value="Logout" />
		</form>
		<span>Username: </span><c:out value="${username}" /> <br/>
		<span>Name: </span><c:out value="${name}" /> <br/>
		<span>Role: </span><c:out value="${role}" /> <br/>
		<div>
			Filter by:
			<form action="filter.do" method="post">
				<select name="filter">
					<option value="All">All</option>
					<option value="Pending">Pending</option>
					<option value="Approved">Approved</option>
					<option value="Denied">Denied</option>
				</select>
				<input type="submit" value="Submit">
			</form>
		</div>
		<table>
			<tr><th>#</th><th>Amount</th><th>Submitted</th><th>Resolved<th>Description</th>
			<th>Receipt</th><th>Author</th><th>Resolver</th><th>Status</th><th>Type</th><th>Approval</th></tr>
			<c:forEach var="temp" items="${reimb}">
				<tr>
				<td><%= i++ %></td>
				<td><fmt:setLocale value="en_US"/><fmt:formatNumber type="currency" value="${temp.amount}" /></td>
				<td><fmt:formatDate value="${temp.submitted}" pattern="MM/dd/yyyy HH:mm"/></td>
				<td>
					<c:choose>
						<c:when test="${empty temp.resolved}">
							-
						</c:when>
						<c:otherwise>
							<fmt:formatDate value="${temp.resolved}" pattern="MM/dd/yyyy HH:mm"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td>${temp.descript}</td>
				<td>
					<c:choose>
						<c:when test="${empty temp.receipt}">
							-
						</c:when>
						<c:otherwise>
							<form action="receipt.do" method="post" target="_blank">
								<input type="hidden" value="${temp.reimbId}" name="reimbId">
								<input type="submit" value="Receipt" />
							</form>
						</c:otherwise>
					</c:choose>
				</td>
				<td>${temp.author.firstName} ${temp.author.lastName}</td>
				<td>
					<c:choose>
						<c:when test="${not empty temp.resolver.userName}">
							${temp.resolver.firstName} ${temp.resolver.lastName}
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
				<td>${temp.status.status}</td>
				<td>${temp.type.type}</td>
				<td>
					<c:choose>
						<c:when test="${temp.status.status == 'Pending'}">
							<form action="approve.do" method="post">
								<input type="hidden" value="${temp.reimbId}" name="reimbId">
								<input type="submit" name="act" value="Approve"/>
								<input type="submit" name="act" value="Deny" />
							</form>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td></tr>
			</c:forEach>
		</table>
	</body>
</html>