package com.packtpub.cust;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.sql.DataSource;
import javax.annotation.Resource;

public class CustomerInfoServlet extends HttpServlet {

	@Resource(name="jdbc/DataSource")
	private DataSource ds;

	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		try {
			System.out.println("Started..............");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			String createSQL = "CREATE TABLE CUSTOMERINFO(Name char(50),Address char(50),Phone char(20),Email char(50))";
			System.out.println("Creating a table: "+createSQL);
			stmt.executeUpdate(createSQL);			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		try {	
			String insertSQL = "INSERT INTO CUSTOMERINFO VALUES('Tom','Chapel Hill','234567','tom@chphilltown.org')";
			System.out.println("Inserting a record into the table: "+insertSQL);
			stmt.executeUpdate(insertSQL);
			String selectSQL = "SELECT * FROM CUSTOMERINFO";
			ResultSet rs = stmt.executeQuery(selectSQL);
			System.out.println("Running a query: "+selectSQL);
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			out.print("<html>");
			out.print("<head><title>Hello World Application</title></head>");
			out.println("<body>");
			out.println("<table>");
			out.println("<tr>");
			out.print("<td><b>Name</b></td>");
			out.print("<td><b>Address</b></td>");
			out.print("<td><b>Phone</b></td>");
			out.print("<td><b>Email</b></td>");
			out.println("</tr>");
			while (rs.next()) {
				out.println("<tr>");
				out.print("<td>");
				out.print(rs.getString(1));
				out.print("</td>");
				out.print("<td>");
				out.print(rs.getString(2));
				out.print("</td>");
				out.print("<td>");
				out.print(rs.getString(3));
				out.print("</td>");
				out.print("<td>");
				out.print(rs.getString(4));
				out.print("</td>");
				out.println("</tr>");
			}

			out.print("</body>");
			out.print("</html>");
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
            closeConnection(conn);
		} finally {
			closeConnection(conn);
			System.out.println("Ended..............");
		}
	}
	
	private void closeConnection(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Cannot close connection");
			e.printStackTrace();
		}
	}
}