<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="javax.security.auth.Subject" %>
<%@ page import="javax.security.jacc.PolicyContext" %>
<%@ page import="org.apache.geronimo.security.realm.providers.GeronimoPasswordCredential" %>
<%@ page import="org.apache.geronimo.security.jaas.NamedUsernamePasswordCredential" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin JSP</title>
</head>
<body>
<h1>MyWebApp: Admin JSP</h1>
<br>Welcome <b><%=request.getUserPrincipal().getName() %></b> !!
<br>
<br>You are <%=request.isUserInRole("admin") ? "in" : "not in"%> 'admin' role.
<br>You are <%=request.isUserInRole("user") ? "in" : "not in"%> 'user' role.
<br>
<%  // This works for any JEE Server using JACC
Subject subject = (Subject)PolicyContext.getContext("javax.security.auth.Subject.container");
%>
<br><%=subject.getPrincipals().size()%> Principals
<%for(java.security.Principal p:subject.getPrincipals()) { %>
    <br>* <%=p.getClass().getName()%> : <%=p.getName()%>
<%}%>
<hr>
<br><%=subject.getPrivateCredentials().size()%> Private Credentials
<%for(Object c:subject.getPrivateCredentials()) { %>
    <br><%=c%>
<%if(c instanceof GeronimoPasswordCredential) {%>
    <br>Username: <%=((GeronimoPasswordCredential)c).getUserName()%>
    <br>Password: <%=new String(((GeronimoPasswordCredential)c).getPassword())%>
<%}%>
<%if(c instanceof NamedUsernamePasswordCredential) {%>
    <br>Credential Name: <%=((NamedUsernamePasswordCredential)c).getName()%>
    <br>Username: <%=((NamedUsernamePasswordCredential)c).getUsername()%>
    <br>Password: <%=new String(((NamedUsernamePasswordCredential)c).getPassword())%>
<%}%>
<hr>
<%}%>
<br><%=subject.getPublicCredentials().size()%> Public Credentials
<%for(Object c:subject.getPublicCredentials()) { %>
    <br><%=c%>
<%}%>
<br>
<br>Click <a href="<%=request.getContextPath()%>/user/user.jsp">User</a> to access User page.
<br>
<br>Click <a href="<%=request.getContextPath()%>/logout.jsp">here</a> to logout.
</body>
</html>