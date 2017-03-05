<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add Reimbursement</title>
	</head>
	<body>
		<div>
			<form action="add.do" method="post" enctype="multipart/form-data">
				<div>Amount: </div>
				<input type="text" name="amount" />
				<div>Type: </div>
				<select name="type">
					<option value="Lodging">Lodging</option>
					<option value="Travel">Travel</option>
					<option value="Food">Food</option>
					<option value="Other">Other</option>
				</select><br/>
				<div>Description:</div>
				<input type="text" name="description" /><br/>
				<div>Receipt(jpg): </div>
				<input type="file" name="receipt" /><br/>
				<input type="submit" value="submit">
			</form>
		</div>
	</body>
</html>