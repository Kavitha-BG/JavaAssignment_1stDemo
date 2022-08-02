package com.librarymanagement;

import java.time.LocalDateTime;

public class Book{
	public String Book_Code;
	public String Book_Name;
	public String Author;
	public String Subject;
	public String Issue_Status;
	public String Issued_to;
	public LocalDateTime Issued_Date;
	public LocalDateTime Return_Date;
	
	
	public String getBook_Code() {
		return Book_Code;
	}


	public void setBook_Code(String book_Code) {
		this.Book_Code = book_Code;
	}


	public String getBook_Name() {
		return Book_Name;
	}


	public void setBook_Name(String book_Name) {
		this.Book_Name = book_Name;
	}


	public String getAuthor() {
		return Author;
	}


	public void setAuthor(String author) {
		this.Author = author;
	}


	public String getSubject() {
		return Subject;
	}


	public void setSubject(String subject) {
		this.Subject = subject;
	}


	public String getIssue_Status() {
		return Issue_Status;
	}


	public void setIssue_Status(String issue_Status) {
		this.Issue_Status = issue_Status;
	}


	public String getIssued_to() {
		return Issued_to;
	}


	public void setIssued_to(String issued_to) {
		this.Issued_to = issued_to;
	}


	public LocalDateTime getIssued_Date() {
		return Issued_Date;
	}


	public void setIssued_Date(LocalDateTime issued_Date) {
		this.Issued_Date = issued_Date;
	}


	public LocalDateTime getReturn_Date() {
		return Return_Date;
	}


	public void setReturn_Date(LocalDateTime return_Date) {
		Return_Date = return_Date;
	}


	public Book(String Book_Code, String Book_Name, String Author, String Subject, String Issue_Status, String Issued_to, LocalDateTime Issued_Date, LocalDateTime Return_Date) {
		this.Book_Code = Book_Code;
		this.Book_Name = Book_Name;
		this.Author = Author;
		this.Subject = Subject;
		this.Issue_Status = Issue_Status;
		this.Issued_to = Issued_to;
		this.Issued_Date = Issued_Date;
		this.Return_Date = Return_Date;		
	}


	@Override
	public String toString() {
		return "Book [Book_Code=" + Book_Code + ", Book_Name=" + Book_Name + ", Author=" + Author + ", Subject="
				+ Subject + ", Issue_Status=" + Issue_Status + ", Issued_to=" + Issued_to + ", Issued_Date="
				+ Issued_Date + ", Return_Date=" + Return_Date + "] \n";
	}
	
	
}
