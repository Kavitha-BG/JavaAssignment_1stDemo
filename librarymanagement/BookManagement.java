package com.librarymanagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

interface BookManagement {
	
	public ArrayList<Book> AddBook(ArrayList l) throws IOException;
	public void ViewBooksList(ArrayList l) throws IOException;
	
	public void ViewByBookCode(ArrayList l);
	public ArrayList<Book> SortBook(ArrayList l);
	
	public void CheckBookAvailability() throws IOException;
	public void MarkAsIssued() throws IOException;
	public void MarkAsReturned() throws IOException;
	public ArrayList<Book> DeleteBookByBookCode(ArrayList l1);
	
}

