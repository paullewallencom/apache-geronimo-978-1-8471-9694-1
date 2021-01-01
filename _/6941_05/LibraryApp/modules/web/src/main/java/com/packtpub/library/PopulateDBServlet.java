package com.packtpub.library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class PopulateDBServlet extends HttpServlet {

	@Resource(name="jdbc/DataSource")
	private DataSource ds;

	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		Connection conn = null;
		Statement stmt = null;
		try{
			conn = ds.getConnection();			
			stmt = conn.createStatement();
			String dropSQL = "DROP TABLE BOOKS";			
			System.out.println("Dropping BOOKS table: "+dropSQL);
			stmt.executeUpdate(dropSQL);
			dropSQL = "DROP TABLE MEMBERS";			
			System.out.println("Dropping MEMBERS table: "+dropSQL);
			stmt.executeUpdate(dropSQL);
		}catch(Exception ex){
			System.out.println("Drop failed. Either the tables don't exist or there is some database issue."+ex);
		}finally {
			closeConnection(conn);			
		}
		try {
			System.out.println("Started..............");
			conn = ds.getConnection();			
			stmt = conn.createStatement();
			String createSQL = "CREATE TABLE BOOKS(Title char(50),ISBN char(20),lent_date date,due_date date,PRIMARY KEY (ISBN))";			
			System.out.println("Creating BOOKS table: "+createSQL);
			stmt.executeUpdate(createSQL);
			createSQL = "CREATE TABLE MEMBERS(Id char(4),Name char(50),Address char(150),email char(50),books_isbn char(20) references BOOKS(ISBN),PRIMARY KEY (Id))";
			System.out.println("Creating MEMBERS table: "+createSQL);
			stmt.executeUpdate(createSQL);
			String insertSQL = "INSERT INTO BOOKS VALUES ('To Kill A MockingBird', 'ISBN9780899668581', '10/10/2008', '10/11/2008')";
			System.out.println("Inserting a record into the table BOOKS: "+insertSQL);
			stmt.executeUpdate(insertSQL);
			insertSQL = "INSERT INTO BOOKS VALUES ('Let Sleeping Vets Lie', 'ISBN9780330443548', '04/04/2009', '05/05/2009')";
			System.out.println("Inserting a record into the table BOOKS: "+insertSQL);
			stmt.executeUpdate(insertSQL);
			insertSQL = "INSERT INTO BOOKS VALUES ('Summer Lightning', 'ISBN9997520556', '03/03/2009', '04/04/2010')";
			System.out.println("Inserting a record into the table BOOKS: "+insertSQL);
			stmt.executeUpdate(insertSQL);
			insertSQL = "INSERT INTO BOOKS VALUES ('Five Little Pigs', 'ISBN0007274564', '01/01/2009', '01/08/2010')";
			System.out.println("Inserting a record into the table BOOKS: "+insertSQL);
			stmt.executeUpdate(insertSQL);
			insertSQL = "INSERT INTO MEMBERS VALUES ('0001', 'Tom', 'Majestic', 'tom@gmail.com', 'ISBN9780330443548')";
			System.out.println("Inserting a record into the table MEMBERS: "+insertSQL);
			stmt.executeUpdate(insertSQL);
			insertSQL = "INSERT INTO MEMBERS VALUES ('0002', 'Dick', 'Millers Road', 'dick@yahoo.com', 'ISBN9997520556')";
			System.out.println("Inserting a record into the table MEMBERS: "+insertSQL);
			stmt.executeUpdate(insertSQL);
			insertSQL = "INSERT INTO MEMBERS VALUES ('0003', 'Harry', 'CunningHam Road', 'harry@aol.com', 'ISBN9780899668581')";
			System.out.println("Inserting a record into the table MEMBERS: "+insertSQL);
			stmt.executeUpdate(insertSQL);
			insertSQL = "INSERT INTO MEMBERS VALUES ('0004', 'Sally', 'Dickenson Road', 'sally@msn.com', 'ISBN0007274564')";
			System.out.println("Inserting a record into the table MEMBERS: "+insertSQL);
			stmt.executeUpdate(insertSQL);
			System.out.println("Ended Successfully..............");
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
            //closeConnection(conn);
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