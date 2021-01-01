<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="simple.ejb21.*,simple.ejb3.*,javax.naming.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My EJB WebApp bank</title>
<%      MyService myservice = null;
        try {
			InitialContext ic = new InitialContext();
			Object temp =  ic.lookup("java:comp/env/ejb/MyService");
			myservice = ((MyServiceHome) temp).create();
		} catch (Exception ex) {
			System.out.println("Couldn't create MyService bean. " + ex.getMessage());
		}
		
		MyEjb3Service myejb3service = null;
        try {
			InitialContext ic = new InitialContext();
			Object temp =  ic.lookup("java:comp/env/ejb/MyEjb3Service");
			myejb3service = ((MyEjb3Service) temp);
		} catch (Exception ex) {
			System.out.println("Couldn't create MyEjb3Service bean. " + ex.getMessage());
		}
%>
</head>
<body>
<% Object temp = null; %>
<h3>User Info</h3>
UserPrincipal: <%=request.getUserPrincipal() == null ? "" : request.getUserPrincipal().getClass().getSimpleName()+"["+request.getUserPrincipal().getName()+"]"%><br>
UserRole: <%=request.isUserInRole("bank") ? "bank" : ""%> <%=request.isUserInRole("customer") ? "customer" : ""%><hr>

<h3>EJB2.1</h3>
Calling commonMethod... <br>
<%
    try {
    	temp = myservice.commonMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

Calling userMethod... <br>
<%
    try {
    	temp = myservice.userMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

Calling adminMethod... <br>
<%
    try {
    	temp = myservice.adminMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

Calling noaccessMethod... <br>
<%
    try {
    	temp = myservice.noaccessMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

<%myservice.remove();%>

<h3>EJB3</h3>
Calling commonMethod... <br>
<%
    try {
    	temp = myejb3service.commonMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

Calling userMethod... <br>
<%
    try {
    	temp = myejb3service.userMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

Calling adminMethod... <br>
<%
    try {
    	temp = myejb3service.adminMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

Calling noaccessMethod... <br>
<%
    try {
    	temp = myejb3service.noaccessMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

<%		MyEjb3Service2 myejb3service2 = null;
        try {
			InitialContext ic = new InitialContext();
			temp =  ic.lookup("java:comp/env/ejb/MyEjb3Service2");
			myejb3service2 = ((MyEjb3Service2) temp);
		} catch (Exception ex) {
			System.out.println("Couldn't create MyEjb3Service2 bean. " + ex.getMessage());
		}
%>

<h3>RunAs</h3>
Calling commonMethod... <br>
<%
    try {
    	temp = myejb3service2.commonMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

Calling userMethod... <br>
<%
    try {
    	temp = myejb3service2.userMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

Calling adminMethod... <br>
<%
    try {
    	temp = myejb3service2.adminMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

Calling noaccessMethod... <br>
<%
    try {
    	temp = myejb3service2.noaccessMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

<%		MyEjb3Service3 myejb3service3 = null;
        try {
			InitialContext ic = new InitialContext();
			temp =  ic.lookup("java:comp/env/ejb/MyEjb3Service3");
			myejb3service3 = ((MyEjb3Service3) temp);
		} catch (Exception ex) {
			System.out.println("Couldn't create MyEjb3Service3 bean. " + ex.getMessage());
		}
%>

<h3>Without RunAs</h3>
Calling commonMethod... <br>
<%
    try {
    	temp = myejb3service3.commonMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

Calling userMethod... <br>
<%
    try {
    	temp = myejb3service3.userMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

Calling adminMethod... <br>
<%
    try {
    	temp = myejb3service3.adminMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

Calling noaccessMethod... <br>
<%
    try {
    	temp = myejb3service3.noaccessMethod();
    } catch(Throwable t) {
    	temp = t;
    }
%>
<%=temp%><hr>

</body>
</html>