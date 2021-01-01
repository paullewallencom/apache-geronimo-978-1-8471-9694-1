package sample.ejb3;

import sample.jpa.Book;
import sample.jpa.Member;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateless
public class LibraryBean implements Library {
    @PersistenceContext(type=PersistenceContextType.TRANSACTION)    
    private EntityManager em;
    
	public void addBook(String name, String author) {
		Book book = new Book();
		book.setBookId((int)System.nanoTime());
		book.setName(name);
		book.setAuthor(author);
		em.persist(book);
	}

	public void addMember(String name) {
		Member member = new Member();
		member.serMemberId((int)System.nanoTime());
		member.setName(name);
		em.persist(member);
	}

	public void borrowBook(int bookId, int memberId) {
		Book book = em.find(Book.class, bookId);
		Member member = em.find(Member.class, memberId);
		if(book.getBorrowerId() != -1) {
			book.setBorrowerId(member.getMemberId());
			member.setTotalBorrowed(member.getTotalBorrowed() + 1);
		} else {
			throw new RuntimeException("Book not available");
		}
	}

	public void removeBook(int bookId) {
		Book book = em.find(Book.class, bookId);
		em.remove(book);
	}

	public void removeMember(int memberId) {
		Member member = em.find(Member.class, memberId);
		em.remove(member);
	}

	public void returnBook(int bookId, int memberId) {
		Book book = em.find(Book.class, bookId);
		Member member = em.find(Member.class, memberId);
		if(book.getBorrowerId() != memberId) {
			throw new RuntimeException("Book "+book+" not borrowed by "+member);
		} else {
			book.setBorrowerId(-1);
			member.setTotalBorrowed(member.getTotalBorrowed()-1);
		}
	}
}
