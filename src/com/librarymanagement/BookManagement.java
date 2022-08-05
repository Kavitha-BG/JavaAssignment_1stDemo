package com.librarymanagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


interface BookManagement {
	
	public List<Book> addBook(List<Book> bookList) throws IOException;
	
	public void viewBooksList(List<Book> bookList) throws IOException;
	
	public void viewByBookCode(List<Book> bookList);
	
	public List<Book> deleteBookByBookCode(List<Book> bookList);
	
	public void checkBookAvailability() throws IOException;
	
	public void markAsIssued(List<Book> bookList, List<User> userList1) throws IOException;
	
	public void markAsReturned(List<Book> bookList, List<User> userList1) throws IOException;
	

}

