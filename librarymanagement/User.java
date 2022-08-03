package com.librarymanagement;

import java.time.LocalDateTime;

public class User {
	
	String name;
	String user_Id;
	String issued_Book_Id;
	LocalDateTime date_Issued;
	LocalDateTime return_Date;

	public User(String name, String user_Id, String issued_Book_Id, LocalDateTime date_Issued, LocalDateTime return_Date) {
		this.name = name;
		this.user_Id = user_Id;
		this.issued_Book_Id = issued_Book_Id;
		this.date_Issued = date_Issued;
		this.return_Date = return_Date;
	}

	@Override
	public String toString() {
		return name +
				"," + user_Id +
				"," + issued_Book_Id +
				"," + date_Issued +
				"," + return_Date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public String getIssued_Book_Id() {
		return issued_Book_Id;
	}

	public void setIssued_Book_Id(String issued_Book_Id) {
		this.issued_Book_Id = issued_Book_Id;
	}

	public LocalDateTime getDate_Issued() {
		return date_Issued;
	}

	public void setDate_Issued(LocalDateTime date_Issued) {
		this.date_Issued = date_Issued;
	}

	public LocalDateTime getReturn_Date() {
		return return_Date;
	}

	public void setReturn_Date(LocalDateTime return_Date) {
		this.return_Date = return_Date;
	}
	
}
