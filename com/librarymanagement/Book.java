package com.librarymanagement;

import java.time.LocalDateTime;

public class Book{
	public String book_Code;
	public String book_Name;
	public String author;
	public String subject;
	public String issue_Status;
	public String issued_to;
	public LocalDateTime issued_Date;
	public LocalDateTime return_Date;

	public String getBook_Code() {
		return book_Code;
	}


	public void setBook_Code(String book_Code) {
		this.book_Code = book_Code;
	}


	public String getBook_Name() {
		return book_Name;
	}


	public void setBook_Name(String book_Name) {
		this.book_Name = book_Name;
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


	public String getIssue_Status() {
		return issue_Status;
	}


	public void setIssue_Status(String issue_Status) {
		this.issue_Status = issue_Status;
	}


	public String getIssued_to() {
		return issued_to;
	}


	public void setIssued_to(String issued_to) {
		this.issued_to = issued_to;
	}


	public LocalDateTime getIssued_Date() {
		return issued_Date;
	}


	public void setIssued_Date(LocalDateTime issued_Date) {
		this.issued_Date = issued_Date;
	}


	public LocalDateTime getReturn_Date() {
		return return_Date;
	}


	public void setReturn_Date(LocalDateTime return_Date) {
		this.return_Date = return_Date;
	}
	
	public Book() {}
	
	
	public Book(String book_Code, String book_Name, String author, String subject, String issue_Status, String issued_to, LocalDateTime issued_Date, LocalDateTime return_Date) {
		this.book_Code = book_Code;
		this.book_Name = book_Name;
		this.author = author;
		this.subject = subject;
		this.issue_Status = issue_Status;
		this.issued_to = issued_to;
		this.issued_Date = issued_Date;
		this.return_Date = return_Date;
	}


	@Override
	public String toString() {
		return book_Code +
				"," + book_Name +
				"," + author +
				"," + subject +
				"," + issue_Status +
				"," + issued_to +
				"," + issued_Date +
				"," + return_Date ;	
	}
}
