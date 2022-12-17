<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact Management</title>
</head>
<body>
	<center>
		<h1 align="center">PhoneBook</h1>
		<h4>UpdateContact</h4>

		<form action="<%=request.getContextPath()%>/ControllerServlet" method="post">
			<table>
			<td><input type="hidden" name="contactID"
						 value="${contact.contactID}"></td>
						
				<tr>
					<td><label>Name</label></td>
					<td><input type="text" name="Name"
						 value="${contact.contactName}"></td>
				</tr>
				<tr>
					<td><label>Email</label></td>
					<td><input type="email" name="Email"
						 value="${contact.contactEmail}"></td>
				</tr>
				<tr>
					<td><label>Address</label></td>
					<td><input type="text" name="Address"
						 value="${contact.contactAddress}"></td>
				</tr>
				<tr>
					<td><label>Phone</label></td>
					<td><input type="number" name="Phone"
						value="${contact.contactPhone}"></td>
				</tr>
				

	<tr>
			<td>
				<button > <input type="hidden" name="action" value="update"> SUBMIT </button>
					
			</td>
		</tr>
					</table>
		</form>
	</center>
</body>
</html>