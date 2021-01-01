<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login JSP</title>
</head>
<body>
<h1>My Ejb Web App: Login JSP</h1>
<form action=j_security_check method=post>
Username:<input type=text name=j_username><br>
Password:<input type=text name=j_password><br>
<input type=submit value="Login">
</form>
</body>
</html>