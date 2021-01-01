package com.packtpub.library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

public class ListUtility {

	public static void sendMail(DataSource ds, Session mailSession)
			throws AddressException, MessagingException {
		ArrayList<Member> members = list(ds);
		for (Member member : members) {
			Transport transport;

			transport = mailSession.getTransport("smtp");
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress("librarian@library.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					member.getEmail()));
			message.setSubject("Due Dates For the Book You Borrowed");
			String text = "Hi \n Your Book " + member.getBook().getTitle()
					+ " is due on " + member.getBook().getDue_date()
					+ "\n Regards \n Librarian";
			message.setText(text);
			transport.connect();
			transport.send(message);

		}

	}

	public static ArrayList<Member> list(DataSource ds) {
		Connection conn = null;
		Statement stmt = null;
		ArrayList<Member> members = null;
		try {
			System.out.println("Started..............");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			String selectSQL = "SELECT ID,NAME,ADDRESS,EMAIL,TITLE,ISBN,LENT_DATE,DUE_DATE FROM MEMBERS,BOOKS WHERE MEMBERS.books_isbn = BOOKS.isbn";
			System.out.println("Creating BOOKS table: " + selectSQL);
			ResultSet rs = stmt.executeQuery(selectSQL);
			members = new ArrayList<Member>(4);
			while (rs.next()) {
				Member member = new Member();
				Book book = new Book();
				member.setBook(book);
				member.setId(rs.getString("ID"));
				member.setName(rs.getString("NAME"));
				member.setAddress(rs.getString("ADDRESS"));
				member.setEmail(rs.getString("EMAIL"));
				book.setTitle(rs.getString("TITLE"));
				book.setIsbn(rs.getString("ISBN"));
				book.setLent_date(rs.getDate("LENT_DATE"));
				book.setDue_date(rs.getDate("DUE_DATE"));
				members.add(member);
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			// closeConnection(conn);
		} finally {
			closeConnection(conn);
			System.out.println("Ended..............");
		}
		return members;
	}

	private static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Cannot close connection");
			e.printStackTrace();
		}
	}
}
