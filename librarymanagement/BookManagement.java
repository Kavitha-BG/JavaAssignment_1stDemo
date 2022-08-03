package com.librarymanagement;

import java.io.IOException;
import java.util.ArrayList;


interface BookManagement {
	
	//public ArrayList<Book> AddBook(ArrayList l) throws IOException;
	public void ViewBooksList(ArrayList l) throws IOException;
	
	public void ViewByBookCode(ArrayList l);
	public ArrayList<Book> SortBook(ArrayList l);
	
	public void CheckBookAvailability() throws IOException;
	public void MarkAsIssued() throws IOException;
	public void MarkAsReturned() throws IOException;
	public ArrayList<Book> DeleteBookByBookCode(ArrayList<Book> l1);

	public ArrayList<Book> AddBook(ArrayList<Book> bookList) throws IOException;
	//public void Exit(ArrayList<Book> bookList);
	public void Exit(ArrayList<Book> bookList,ArrayList<User> userList);

	public ArrayList<User> DeleteUser(ArrayList userList);
	
}

