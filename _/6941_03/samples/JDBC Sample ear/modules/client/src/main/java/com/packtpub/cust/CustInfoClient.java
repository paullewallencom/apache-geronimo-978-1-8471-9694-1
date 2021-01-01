package com.packtpub.cust;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class CustInfoClient {

	@Resource(name="jdbc/DataSource")
	public static DataSource ds;
	
	public static void main(String[] args){
		CustInfoClient cic = new CustInfoClient();
		//cic.createAndInsertData();
		cic.printDBInfo();
	}
	
	private void createAndInsertData(){
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			String createSQL = "CREATE TABLE CUSTOMERINFO(Name char(50),Address char(50),Phone char(20),Email char(50))";
			stmt.executeUpdate(createSQL);
			String insertSQL = "INSERT INTO CUSTOMERINFO VALUES('Tom','Chapel Hill','234567','tom@chphilltown.org')";
			stmt.executeUpdate(insertSQL);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			closeConnection(conn);
		} finally{
			closeConnection(conn);
		}
	}
	
	private void printDBInfo(){
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();

			String selectSQL = "SELECT * FROM CUSTOMERINFO";
			ResultSet rs = stmt.executeQuery(selectSQL);
			System.out.println("Running a query: "+selectSQL);			
			while (rs.next()) {
				System.out.print("Name:");
				System.out.println(rs.getString(1));
				System.out.print("Address:");
				System.out.println(rs.getString(2));
				System.out.print("Phone:");
				System.out.println(rs.getString(3));
				System.out.print("Email:");
				System.out.println(rs.getString(4));
			}

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
            closeConnection(conn);
		} finally {
			closeConnection(conn);			
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
