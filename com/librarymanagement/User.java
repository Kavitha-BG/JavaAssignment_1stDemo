package com.librarymanagement;

import java.time.LocalDateTime;

public class User {
	
	String name;
	String userId;
	String issuedBookId;
	LocalDateTime dateIssued;
	LocalDateTime returnDate;
	
	public User() {
		
	}

	public User(String name, String userId, String issuedBookId, LocalDateTime dateIssued, LocalDateTime returnDate) {
		this.name = name;
		this.userId = userId;
		this.issuedBookId = issuedBookId;
		this.dateIssued = dateIssued;
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return name +
				"," + userId +
				"," + issuedBookId +
				"," + dateIssued +
				"," + returnDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIssuedBookId() {
		return issuedBookId;
	}

	public void setIssuedBookId(String issuedBookId) {
		this.issuedBookId = issuedBookId;
	}

	public LocalDateTime getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(LocalDateTime dateIssued) {
		this.dateIssued = dateIssued;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}
	
}
