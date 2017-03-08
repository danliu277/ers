<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="nav.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add Reimbursement</title>
	</head>
	<body>
		<div>
			<!-- enctype="multipart/form-data" -->
			<form action="add.do" method="POST" enctype="multipart/form-data" >
				<div>Amount: </div>
				<input type="text" id="amount" name="amount" />
				<div>Type: </div>
				<select id="type" name="type">
					<option value="Lodging">Lodging</option>
					<option value="Travel">Travel</option>
					<option value="Food">Food</option>
					<option value="Other">Other</option>
				</select><br/>
				<div>Description:</div>
				<input type="text" id="dexcription" name="description" /><br/>
				<div>Receipt(jpg): </div>
				<input type="file" name="receipt" /><br/>
				<input type="submit" value="submit">
			</form>
		</div>
	</body>
</html>