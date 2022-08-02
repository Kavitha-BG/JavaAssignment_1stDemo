package com.librarymanagement;

import java.io.IOException;
import java.util.ArrayList;

interface UserManagement extends BookManagement {
	public ArrayList AddUser(ArrayList u) throws IOException;

	public ArrayList<User> DeleteUser(ArrayList u);

	public void Exit();
}
