package com.librarymanagement;

import java.io.IOException;
import java.util.ArrayList;


interface BookManagement {
	
	public ArrayList<Book> AddBook(ArrayList<Book> bookList) throws IOException;
	
	public void ViewBooksList(ArrayList<Book> bookList) throws IOException;
	public void ViewByBookCode(ArrayList<Book> bookList);
	
	public void CheckBookAvailability() throws IOException;
	public ArrayList<Book> DeleteBookByBookCode(ArrayList<Book> bookList);
	
	public void MarkAsIssued() throws IOException;
	public void MarkAsReturned() throws IOException;
	

	public void Exit(ArrayList<Book> bookList,ArrayList<User> userList);

}

