package com.librarymanagement;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Book implements Serializable{
	public String bookCode;
	public String bookName;
	public String author;
	public String subject;
	public String issueStatus;
	public String issuedto;
	public LocalDateTime issuedDate;
	public LocalDateTime returnDate;

	public String getBookCode() {
		return bookCode;
	}


	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getIssueStatus() {
		return issueStatus;
	}


	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}


	public String getIssuedto() {
		return issuedto;
	}


	public void setIssuedto(String issuedto) {
		this.issuedto = issuedto;
	}


	public LocalDateTime getIssuedDate() {
		return issuedDate;
	}


	public void setIssuedDate(LocalDateTime issuedDate) {
		this.issuedDate = issuedDate;
	}


	public LocalDateTime getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}
	
	public Book() {}
	
	
	public Book(String bookCode, String bookName, String author, String subject, String issueStatus, String issuedto, LocalDateTime issuedDate, LocalDateTime returnDate) {
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.author = author;
		this.subject = subject;
		this.issueStatus = issueStatus;
		this.issuedto = issuedto;
		this.issuedDate = issuedDate;
		this.returnDate = returnDate;
	}


	@Override
	public String toString() {
		return bookCode +
				"," + bookName +
				"," + author +
				"," + subject +
				"," + issueStatus +
				"," + issuedto +
				"," + issuedDate +
				"," + returnDate ;	
	}
}
