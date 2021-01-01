<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="simple.ejb21.*,simple.ejb3.*,javax.naming.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>my-corba-ejb-web-app home</title>
<%      MyService myservice = null;
        try {
			InitialContext ic = new InitialContext();
			Object temp =  ic.lookup("java:comp/env/ejb/MyService");
			myservice = ((MyServiceHome) temp).create();
		} catch (Exception ex) {
			System.out.println("Couldn't create MyService bean. " + ex.getMessage());
		}

		MyService corbaService = null;
        try {
			InitialContext ic = new InitialContext();
			Object temp =  ic.lookup("java:comp/env/ejb/MyCorbaService");
			corbaService = ((MyServiceHome) javax.rmi.PortableRemoteObject.narrow(temp, MyServiceHome.class)).create();
		} catch (Exception ex) {
			System.out.println("Couldn't create MyCorbaService bean. " + ex.getMessage());
		}
		
		MyService corbaService2 = null;
        try {
			InitialContext ic = new InitialContext();
			Object temp =  ic.lookup("java:comp/env/ejb/MyCorbaService2");
			corbaService2 = ((MyServiceHome) javax.rmi.PortableRemoteObject.narrow(temp, MyServiceHome.class)).create();
		} catch (Exception ex) {
			System.out.println("Couldn't create MyCorbaService2 bean. " + ex.getMessage());
		}
		
%>
</head>
<body>
<% Object temp = null; %>
<h3>User Info</h3>
UserPrincipal: <%=request.getUserPrincipal() == null ? "" : request.getUserPrincipal().getClass().getSimpleName()+"["+request.getUserPrincipal().getName()+"]"%><br>
UserRole: <%=request.isUserInRole("bank") ? "bank" : ""%> <%=request.isUserInRole("customer") ? "customer" : ""%><hr>

<h3>EJB service</h3>
USD <%=request.getParameter("usd")%> = EURO <%=myservice.convertUSD2EURO(new Double(request.getParameter("usd")).doubleValue())%> 
<hr>

<%myservice.remove();%>

<h3>CORBA EJB service</h3>
USD <%=request.getParameter("usd")%> = EURO <%=corbaService.convertUSD2EURO(new Double(request.getParameter("usd")).doubleValue())%> 
<hr>
<%corbaService.remove();%>

<h3>CORBA EJB service with dynamic username</h3>
USD <%=request.getParameter("usd")%> = EURO <%=corbaService2.convertUSD2EURO(new Double(request.getParameter("usd")).doubleValue())%> 
<hr>
<%corbaService2.remove();%>
</body>
</html>