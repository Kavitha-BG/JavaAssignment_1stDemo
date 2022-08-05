package com.librarymanagement;

import java.io.IOException;
import java.util.List;

interface UserManagement extends BookManagement {
	
	public List<User> addUser(List<User> userList) throws IOException;

	public List<User> deleteUser(List<User> userList);

	public void exit(List<Book> bookList,List<User> userList);
	
}
