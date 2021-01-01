package sample.ejb3;

import javax.ejb.Remote;

@Remote
public interface Library {
    void addMember(String name);
    void addBook(String name, String author);
    void removeMember(int memberId);
    void removeBook(int bookId);
    void borrowBook(int bookId, int memberId);
    void returnBook(int bookId, int memberId);
}
