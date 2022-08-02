package com.librarymanagement;

import java.time.LocalDateTime;

public class User {
	
	String Name;
	String User_Id;
	String Issued_Book_Id;
	LocalDateTime Date_Issued;
	LocalDateTime Return_Date;
	
	public User(String Name, String User_Id, String Issued_Book_Id, LocalDateTime Date_Issued, LocalDateTime Return_Date ) {
		this.Name = Name;
		this.User_Id = User_Id;
		this.Issued_Book_Id = Issued_Book_Id;
		this.Date_Issued = Date_Issued;
		this.Return_Date = Return_Date;
			
	}
	

//	public User(String string, String string2, double parseDouble, String string3, LocalDateTime localDateTime,
//			LocalDateTime localDateTime1) {
//		// TODO Auto-generated constructor stub
//	}


	@Override
	public String toString() {
		return "User [Name=" + Name + ", User_Id=" + User_Id + ", Issued_Book_Id=" + Issued_Book_Id + ", Date_Issued="
				+ Date_Issued + ", Return_Date=" + Return_Date + "]";
	}
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getUser_Id() {
		return User_Id;
	}
	public void setUser_Id(String user_Id) {
		this.User_Id = user_Id;
	}
	public String getIssued_Book_Id() {
		return Issued_Book_Id;
	}
	public void setIssued_Book_Id(String issued_Book_Id) {
		this.Issued_Book_Id = issued_Book_Id;
	}
	public LocalDateTime getDate_Issued() {
		return Date_Issued;
	}
	public void setDate_Issued(LocalDateTime date_Issued) {
		this.Date_Issued = date_Issued;
	}
	public LocalDateTime getReturn_Date() {
		return Return_Date;
	}
	public void setReturn_Date(LocalDateTime return_Date) {
		this.Return_Date = return_Date;
	}
	
}
