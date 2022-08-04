package com.librarymanagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


interface BookManagement {
	
	public ArrayList<Book> AddBook(ArrayList<Book> bookList) throws IOException;
	
	public void ViewBooksList(ArrayList<Book> bookList) throws IOException;
	
	public void ViewByBookCode(ArrayList<Book> bookList);
	
	public void CheckBookAvailability() throws IOException;
	
	public ArrayList<Book> DeleteBookByBookCode(ArrayList<Book> bookList );
	
	public void MarkAsIssued(List<Book> bookList, ArrayList<User> userList1) throws IOException;
	
	public void MarkAsReturned(List<Book> bookList, ArrayList<User> userList1) throws IOException;
	
	public void Exit();

}

