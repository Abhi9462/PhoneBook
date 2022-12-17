<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UserProfile</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/index.jsp"
		method="post">

		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>PhoneNumber</th>
			</tr>



		 	
				<tr>

					 <td>${user1.userID}</td>
					<td>${user1.userName}</td>
					<td>${user1.userEmail}</td>
					<td>${user1.userPhone}</td>
					</tr>
                 
					
			

		</table>
	</form>
</body>
</html>