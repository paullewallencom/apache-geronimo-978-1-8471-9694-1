package sample.ejb3;

import sample.jpa.Book;
import sample.jpa.Member;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Library2Bean implements Library {
	@PersistenceUnit(unitName="LibraryUnit")
	private EntityManagerFactory emf;

    private EntityManager em;

    @PostConstruct
    public void init(){
        em = emf.createEntityManager();
    }

    @PreDestroy
    public void destroy(){
        em.close();
    }

	public void addBook(String name, String author) {
		em.joinTransaction();
		Book book = new Book();
		book.setBookId((int)System.nanoTime());
		book.setName(name);
		book.setAuthor(author);
		em.persist(book);
	}

	public void addMember(String name) {
		em.joinTransaction();
		Member member = new Member();
		member.serMemberId((int)System.nanoTime());
		member.setName(name);
		em.persist(member);
	}

	public void borrowBook(int bookId, int memberId) {
		em.joinTransaction();
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
		em.joinTransaction();
		Book book = em.find(Book.class, bookId);
		em.remove(book);
	}

	public void removeMember(int memberId) {
		em.joinTransaction();
		Member member = em.find(Member.class, memberId);
		em.remove(member);
	}

	public void returnBook(int bookId, int memberId) {
		em.joinTransaction();
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
