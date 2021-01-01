package com.packtpub.library;

import java.util.Date;

public class Book {

	private String title;
	private String isbn;
	private Date lent_date;
	private Date due_date;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Date getLent_date() {
		return lent_date;
	}
	public void setLent_date(Date lent_date) {
		this.lent_date = lent_date;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	
}
