<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PhoneBook</title>
</head>


<div><p>${msg}</p></div>

<body>
<h4>Welcome To PhoneBook</h4>

UserName: ${user1.userName} <br>
UserID: ${user1.userID}


<br><br>
<form action="./ContactForm.jsp">
<button> <input type="hidden" ></input>AddContact</button>
</form>
<br>
<form action="./ControllerServlet ">
<button> <input type="hidden" name="action" value="list"></input>ContactList</button>
</form>
<br>
<form action="./UserProfile.jsp">
<button> <input type="hidden" ></input>Profile</button>

</form>

<a href="ControllerServlet?action=logout">Logout</a>

</body>
</html>