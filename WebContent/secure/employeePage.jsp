<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="nav.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Employee Page</title>
	</head>
	<body>
		<table id="myTable" class="table table-striped table-hover table-condensed table-bordered table-responsive">
			<thead>
				<tr><th>Id</th><th>Amount</th><th>Submitted</th><th>Resolved<th>Description</th>
				<th>Receipt</th><th>Author</th><th>Resolver</th><th>Status</th><th>Type</th></tr>
			</thead>
			<tbody>
				<c:forEach var="temp" items="${reimb}">
					<tr>
					<td>${temp.reimbId}</td>
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
					<td class="description">
						<c:choose>
							<c:when test="${not empty temp.descript}">
								${temp.descript}
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${empty temp.receipt}">
								-
							</c:when>
							<c:otherwise>
								<form action="receipt.do" method="post" target="_blank">
									<input type="hidden" value="${temp.reimbId}" name="reimbId">
									<button type="submit" class="btn btn-info" name="receipt" value="Receipt">
									   Receipt
									</button>
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
					<td>${temp.type.type}</td></tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
	<script src="../js/table.js" type="text/javascript"></script>
</html>