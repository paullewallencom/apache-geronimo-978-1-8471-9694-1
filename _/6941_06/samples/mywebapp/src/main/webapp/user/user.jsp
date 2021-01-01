<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User JSP</title>
</head>
<body>
<h1>MyWebApp: User JSP</h1>
<br>Welcome <b><%=request.getUserPrincipal().getName() %></b> !!
<br>
<br>You are <%=request.isUserInRole("admin") ? "in" : "not in"%> 'admin' role.
<br>You are <%=request.isUserInRole("user") ? "in" : "not in"%> 'user' role.
<br>
<br>Click <a href="<%=request.getContextPath()%>/admin/admin.jsp">Admin</a> to access Admin page.
<br>
<br>Click <a href="<%=request.getContextPath()%>/logout.jsp">here</a> to logout.
</body>
</html>