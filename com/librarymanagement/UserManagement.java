package com.librarymanagement;

import java.io.IOException;
import java.util.ArrayList;

interface UserManagement extends BookManagement {
	public ArrayList<User> AddUser(ArrayList<User> userList) throws IOException;

	public ArrayList<User> DeleteUser(ArrayList<User> u);

	public void Exit(ArrayList<Book> bookList,ArrayList<User> userList);

	
}
