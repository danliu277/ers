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
		<div class="container">
			<form action="add.do" method="POST" enctype="multipart/form-data" >
				<div class="form-group">
					<label for="amount" class="col-2 col-form-label">Amount:</label>
					<div class="col-10">
						<input class="form-control" type="number" id="amount" name="amount" placeholder="0.00" min="0.01" max="999999999.99" step="0.01">
					</div>
				</div>
				<div class="form-group">
					<label for="type" class="col-2 col-form-label">Type:</label>
					<select class="form-control" id="type" name="type">
				      	<option value="Lodging">Lodging</option>
						<option value="Travel">Travel</option>
						<option value="Food">Food</option>
						<option value="Other">Other</option>
				    </select>
				</div>
				<div class="form-group">
			    	<label for="description" class="col-2 col-form-label">Description:</label>
			    	<textarea class="form-control" id="description" name="description" rows="3" placeholder="Description"></textarea>
			  	</div>
			  	<div class="form-group">
				    <label for="receipt">Receipt</label>
				    <input type="file" class="form-control-file" name="receipt" id="receipt" aria-describedby="fileHelp">
					<small id="fileHelp" class="form-text text-muted">Jpg/Png</small>
				</div>
				<input type="submit" class="btn btn-primary" value="submit">
			</form>
		</div>
	</body>
</html>