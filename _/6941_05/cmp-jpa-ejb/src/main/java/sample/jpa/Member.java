package sample.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBERS")
public class Member implements Serializable {
	@Id
	private int memberId;
	private String name;
	private int totalBorrowed = 0;
	
	public void serMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	public int getMemberId() {
		return memberId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setTotalBorrowed(int totalBorrowed) {
		this.totalBorrowed = totalBorrowed;
	}
	
	public int getTotalBorrowed() {
		return totalBorrowed;
	}
}
