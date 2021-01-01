<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>
<%@ page import="java.util.*,com.packtpub.library.*" %>

<html>
<head>
<title>List of Books Lent to Members</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>

<body>
<%
    List<Member> members =  (List<Member>)request.getAttribute("members");
%>
<table border="10">
<tr>
<td>
    <B>Member Id</B>
</td>
<td>
    <B>Member Name</B>
</td>
<td>
    <B>Member Address</B>
</td>
<td>
    <B>Member Email</B>
</td>
<td>
    <B>Book Title</B>
</td>
<td>
    <B>Book ISBN</B>
</td>
<td>
    <B>Book End Date</B>
</td>
<td>
    <B>Book Due Date</B>
</td>
</tr>

<%
    for(Member member:members){
%>
<tr>
<td>
    <%=member.getId()%>
</td>
<td>
    <%=member.getName()%>
</td>
<td>
    <%=member.getAddress()%>
</td>
<td>
    <%=member.getEmail()%>
</td>
<td>
    <%=member.getBook().getTitle()%>
</td>
<td>
    <%=member.getBook().getIsbn()%>
</td>
<td>
    <%=member.getBook().getLent_date()%>
</td>
<td>
    <%=member.getBook().getDue_date()%>
</td>
</tr>
<%}%>
</table>
</body>
</html>