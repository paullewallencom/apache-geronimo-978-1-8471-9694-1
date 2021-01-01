package sample.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOOKSBMP")
public class Book implements Serializable {
	
	@Id
	private int bookId = -1;
	private String name;
	private String author;
	private int borrowerId =  -1;

    public void setBookId(int bookId) {
    	this.bookId = bookId;
    }
    
    public int getBookId() {
    	return bookId;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setAuthor(String author) {
    	this.author = author;
    }
    
    public String getAuthor() {
    	return author;
    }
    
    public void setBorrowerId(int borrowerId) {
    	this.borrowerId = borrowerId;
    }
    
    public int getBorrowerId() {
    	return borrowerId;
    }
}
