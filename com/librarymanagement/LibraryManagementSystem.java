package com.librarymanagement;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LibraryManagementSystem implements BookManagement, UserManagement, Runnable {

	ArrayList<User> userlist = new ArrayList<>();
	ArrayList<Book> booklist = new ArrayList<>();

	Scanner userInput = new Scanner(System.in);

	@Override
	public ArrayList<Book> AddBook(ArrayList<Book> bookList) throws IOException {
		String Book_Code;

		main: while (true) {
			System.out.println("Enter the Book Code: ");
			Book_Code = userInput.nextLine();

			Iterator<Book> itr = bookList.iterator();
			while (itr.hasNext()) {
				Book obj = (Book) itr.next();

				if (Book_Code.equals(obj.getBook_Code())) {
					System.out.println("Duplicate Code. Please enter unique Book-Code");
					System.out.println("-------------------------------------------------------");
					continue main;
				}
			}
			break;
		}

		System.out.print("Enter the Book name : ");
		String book_Name = userInput.nextLine();
		System.out.print("Enter the Author : ");
		String author = userInput.nextLine();
		System.out.print("Enter the Subject : ");
		String subject = userInput.nextLine();
		String issue_Status = "Available";
		String issued_to = "NotIssued";
		LocalDateTime issued_Date = null;
		LocalDateTime return_Date = null;

		Book b = new Book(Book_Code, book_Name, author, subject, issue_Status, issued_to, issued_Date, return_Date);
		bookList.add(b);
		System.out.println(bookList);
		System.out.println(b);
		System.out.println("Book Added Successfully");
		System.out.println("------------------------------------");
		return bookList;
	}

	@Override
	public ArrayList<User> AddUser(ArrayList<User> userList) throws IOException {
		String user_Id;

		System.out.println("Enter the User name: ");
		String name = userInput.nextLine();

		main1: while (true) {
			System.out.println("Enter the User-Id: ");
			user_Id = userInput.nextLine();

			Iterator<User> itr = userList.iterator();
			while (itr.hasNext()) {
				User obj = (User) itr.next();

				if (user_Id.equals(obj.getUser_Id())) {
					System.out.println("Duplicate Code. Please enter unique User-Id");
					System.out.println("-------------------------------------------------------");
					continue main1;
				}
			}
			break;

		}
		String issued_Book_Id = "null";
		LocalDateTime date_Issued = null;
		LocalDateTime return_Date = null;

		User u = new User(name, user_Id, issued_Book_Id, date_Issued, return_Date);
		userList.add(u);
		System.out.println(userList);
		System.out.println(u);
		System.out.println("User Added Successfully");
		System.out.println("-------------------------------------------------------");
		return userList;
	}

	@Override
	public void ViewBooksList(ArrayList<Book> bookList) {
		ViewBooksList();
//			System.out.println( " Book_Code Book_Name  Author  Subject  Issue_Status  Issued_to  Issued_Date  Return_Date ");
		Iterator<Book> itr = bookList.iterator();
		while (itr.hasNext()) {
			Book obj = (Book) itr.next();

			System.out.println(obj.getBook_Code() + "\t" + obj.getBook_Name() + "\t" + obj.getAuthor() + "\t"
					+ obj.getSubject() + "\t" + obj.getIssue_Status() + "\t" + obj.getIssued_to() + "\t"
					+ obj.getIssued_Date() + " \t" + obj.getReturn_Date());
		}
		System.out.println("---------------------------------------");

	}

	public void ViewBooksList() {
		System.out.println(
				"Book_Code\t Book_Name\t   Author\t  Subjectc\t Issue_Status\t  Issued_to\t  Issued_Date\t  Return_Date ");
	}

	@Override
	public void ViewByBookCode(ArrayList<Book> bookList) {
		main3: while (true) {
			System.out.println("Enter the Book Code: ");
			String book_Code = userInput.nextLine();
			int count = 0;

			Iterator<Book> itr = bookList.iterator();
			while (itr.hasNext()) {
				Book obj = (Book) itr.next();

				// boolean search = obj.getBook_Code().equals(book_Code);
				if ((obj.getBook_Code().equals(book_Code)) == true) {
					ViewByBookCode();

					System.out.printf("book_Code = " + obj.getBook_Code() + "\n" + "book_Name = " + obj.getBook_Name()
							+ "\n" + "author = " + obj.getAuthor() + "\n" + "subject = " + obj.getSubject() + "\n"
							+ "issue_Status = " + obj.getIssue_Status() + "\n" + "issued_to = " + obj.getIssued_to()
							+ "\n" + "issued_Date = " + obj.getIssued_Date() + "\n" + "return_Date = "
							+ obj.getReturn_Date() + "\n");
					System.out.println("-----------------------------------------------------------------------------");
					// count++;
					break main3;
				}
			}
			if (count == 0) {
				System.out.println("Book not available");
				System.out.println("-------------------------------------------------------");
			}
		}
	}

	public void ViewByBookCode() {
		System.out.printf("Book Details: \n");
	}

	@Override
	public ArrayList<Book> SortBook(ArrayList bookList) {
		Collections.sort(bookList);
		return null;
	}

	@Override
	public void CheckBookAvailability() throws IOException {
		ArrayList<Book> bookList = retrieveBooksFromFile();
		// System.out.println(bookList);
		System.out.println("Enter book_Code to check availability: ");
		String book_Code = userInput.nextLine();
		String issued_to = "";

		Iterator<Book> itr = bookList.iterator();

		boolean flag = false;
		int count = 0;
//			main:
		while (itr.hasNext()) {
			Book obj = (Book) itr.next();

			if (obj.getBook_Code().equals(book_Code)) {
				if (obj.getIssue_Status().equals("Issued")) {
					book_Code = obj.getBook_Code();
					issued_to = obj.getIssued_to();
					flag = true;
					break;
				}
				count++;
			}
		}

		if (count == 0) {
			System.out.println("Book code is not available");
			System.out.println("-------------------------------------------------------");
		} else {
			System.out.println(book_Code + " available in library");
			System.out.println("-------------------------------------------------------");
		}

		if (flag == true) {
			System.out.println(book_Code + " assigned to " + issued_to);
			System.out.println("-------------------------------------------------------");
			CheckBookAvailability();

		} else {
			System.out.println("Do you want to Check another Book Availability (Y/N) ");
			String a = userInput.nextLine();
			if (a.equalsIgnoreCase("Y")) {
				CheckBookAvailability();
			}
		}
	}

	@Override
	public void MarkAsIssued(List<Book> bookList, ArrayList<User> userList1) throws IOException {

		ArrayList<User> userList = userList1;

		System.out.println("Book_Code" + " " + "Book_name" + " " + "Book_Status ");

		for (Book book : bookList) {
			System.out.println(book);
		}
		System.out.println("-------------------------------------------------------");
		String a;
		boolean issueBook = true;

		while (issueBook) {

			System.out.println("Enter the Book-Code: ");
			String book_Code = userInput.nextLine();
			if (book_Code.equals("")) {
				System.out.println("Enter Valid Book Code");
				continue;
			}

			System.out.println("Enter the User Id: ");
			String user_Id = userInput.nextLine();
			if (user_Id.equals("")) {
				System.out.println("Enter Valid User Id");
				continue;
			}

			User tempUser = null;

			for (User user : userList) {
				if (user.getUser_Id().equalsIgnoreCase(user_Id)) {
					tempUser = user;
				}
			}

			Book tempBook = null;

			for (Book book : bookList) {
				if (book.getBook_Code().equals(book_Code)) {
					tempBook = book;
				}
			}

			if (tempBook == null || tempUser == null) {
				System.out.println(book_Code + " or " + user_Id + " Invalid. ");
				System.out.println("-------------------------------------------------------");
				continue;
			} else {

				tempBook.setIssue_Status("Issued");
				tempBook.setIssued_to(user_Id);
				tempBook.setIssued_Date(LocalDateTime.now());
				tempBook.setReturn_Date(LocalDateTime.now().plusDays(7));

				tempUser.setIssued_Book_Id(book_Code);
				tempUser.setDate_Issued(LocalDateTime.now());
				tempUser.setReturn_Date(LocalDateTime.now().plusDays(7));

				System.out.println("Book " + book_Code + " issued to user " + user_Id + " successfully");
				System.out.println("-------------------------------------------------------");
			}

			System.out.println("Do you want to issue another book(Y/N)");
			a = userInput.nextLine();

			if (a.equalsIgnoreCase("Y")) {
				issueBook = true;
			} else {
				break;
			}

		}

		bookwrite(bookList);
		userwrite(userList);
	}

	@Override
	public void MarkAsReturned(List<Book> bookList, ArrayList<User> userList1) throws IOException {
		ArrayList<User> userList = userList1;

		String a;
		main: while (true) {

			System.out.println("Enter the Book-Code: ");
			String book_Code = userInput.nextLine();

			User tempUser = null;

			Book tempBook = null;
			String user_Id = null;

			for (Book book : bookList) {
				if (book.getBook_Code().equals(book_Code)) {
					tempBook = book;
					user_Id = book.getIssued_to();
				}
			}

			for (User user : userList) {
				if (user.getUser_Id().equalsIgnoreCase(user_Id)) {
					tempUser = user;
				}
			}

			if (tempBook == null) {
				System.out.println("Book code invalid");
				System.out.println("-------------------------------------------------------");
				continue;
			} else if (tempBook != null && tempUser == null) {
				System.out.println("Book is already available in Library");
				System.out.println("-------------------------------------------------------");
			} else {

				tempBook.setIssue_Status("Available");
				tempBook.setIssued_to("NotIssued");
				tempBook.setIssued_Date(null);
				tempBook.setReturn_Date(null);

				tempUser.setIssued_Book_Id(null);
				tempUser.setDate_Issued(null);
				tempUser.setReturn_Date(null);
				System.out.println("Book Returned successfully ");
				System.out.println("-------------------------------------------------------");
			}

			System.out.println("Do you want to return another book(Y/N)");
			a = userInput.nextLine();
			if (a.equalsIgnoreCase("Y")) {
				continue main;
			} else {
				break main;
			}
		}

		bookwrite(bookList);
		userwrite(userList);
	}

	public ArrayList<Book> DeleteBookByBookCode(ArrayList<Book> bookList) {
		if (bookList.isEmpty()) {
			System.out.println("List is Empty ");
			System.out.println("-------------------------------------------------------");
		} else {
			main4: while (true) {
				System.out.println("Enter the Book Code: ");
				String book_Code = userInput.nextLine();
				int count = 0;
				Iterator<Book> itr = bookList.iterator();
				while (itr.hasNext()) {
					Book obj = (Book) itr.next();

					if (obj.getBook_Code().equals(book_Code)) {
						itr.remove();

						System.out.println("Book deleted Successfully");
						System.out.println("-------------------------------------------------------");
						count++;
						break main4;
					}
				}
				if (count == 0) {
					System.out.println("Book code is not available");
					System.out.println("-------------------------------------------------------");
					continue main4;
				}
			}
		}
		return bookList;
	}

	@Override
	public ArrayList<User> DeleteUser(ArrayList<User> userList) {
		if (userList.isEmpty()) {
			System.out.println("List is Empty ");
			System.out.println("-------------------------------------------------------");

		} else {
			main4: while (true) {
				System.out.println("Enter User Id");
				String book_Code = userInput.nextLine();
				Iterator<User> iterator = userList.iterator();
				int count = 0;
				while (iterator.hasNext()) {
					User obj = (User) iterator.next();
					boolean search = obj.getUser_Id().equals(book_Code);
					if (search == true) {
						iterator.remove();
						System.out.println("User deleted Successfully ");
						System.out.println("-------------------------------------------------------");
						count++;
						break main4;
					}
				}
				if (count == 0) {
					System.out.println("User code is not available ");
					System.out.println("-------------------------------------------------------");
					continue main4;
				}
			}
		}
		return userList;
	}

	public void bookwrite(List<Book> bookList) {
		File file = new File(
				"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\Book.txt");
		try {
			FileWriter writer = new FileWriter(file);
			BufferedWriter buffer = new BufferedWriter(writer);
			buffer.write("");
			for (int i = 0; i < bookList.size(); i++) {
				buffer.write(bookList.get(i).toString() + "\n");
			}
			buffer.write("\n");
			buffer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void userwrite(ArrayList<User> userList) {
		File file1 = new File(
				"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\User.txt");
		try {
			FileWriter writer = new FileWriter(file1);
			BufferedWriter buffer = new BufferedWriter(writer);
			buffer.write("");
			for (int i = 0; i < userList.size(); i++) {
				buffer.write(userList.get(i).toString() + "\n");
			}
			buffer.write("\n");
			buffer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void Exit(ArrayList<Book> bookList, ArrayList<User> userList) {
		bookwrite(bookList);
		userwrite(userList);

		System.exit(0);
	}

	public ArrayList<Book> retrieveBooksFromFile() throws FileNotFoundException {

		File file1 = new File(
				"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\Book.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(file1));
		
		try {
			String str = null; // file to string conversion
			
			while ((str = br.readLine()) != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
				String[] split = str.split(",");// split string
				LocalDateTime localDateTime = null;
				LocalDateTime localDateTime1 = null;
				if (split[6].equals("null")) {
					localDateTime = null;
				} else {
					try {
						localDateTime = LocalDateTime.parse(split[6], formatter);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				if (split[7].equals("null")) {
					localDateTime1 = null;
				} else {
					try {
						localDateTime1 = LocalDateTime.parse(split[7], formatter);
					} catch (Exception e) {
						System.out.println(e);
					}

				}
				
				Book b2 = new Book(split[0], split[1], split[2], split[3], split[4], split[5], localDateTime,
						localDateTime1);
				booklist.add(b2);
			}

		} catch (ArrayIndexOutOfBoundsException | IOException e) {
		}
		return booklist;
	}

	public ArrayList<User> read2() throws FileNotFoundException {

		File file1 = new File(
				"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\User.txt");

		BufferedReader br = new BufferedReader(new FileReader(file1));

		try {
			String str = null; // file to string conversion
			while ((str = br.readLine()) != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
				String[] split = str.split(",");// split string
				// System.out.println(split);
				LocalDateTime localDateTime = null;
				LocalDateTime localDateTime1;
				if (split[3].equals("null")) {
					localDateTime = null;
				} else {
					try {
						localDateTime = LocalDateTime.parse(split[3], formatter);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				if (split[4].equals("null")) {
					localDateTime1 = null;

				} else {
					localDateTime1 = LocalDateTime.parse(split[4], formatter);
				}
				User u2 = new User(split[0], split[1], split[2], localDateTime, localDateTime1);
				userlist.add(u2);
			}
		} catch (ArrayIndexOutOfBoundsException | IOException e) {
		}
		return userlist;
	}

	// main function
	public static void main(String[] args) throws IOException, InterruptedException {
		while (true) {

			LibraryManagementSystem liManagementSystem = new LibraryManagementSystem();
			ArrayList<Book> bookList = liManagementSystem.retrieveBooksFromFile();
			ArrayList<User> userList = liManagementSystem.read2();

			List<Book> reportBooksList = new ArrayList<>();

			boolean condition = true;
			while (condition) {

				System.out.println("Menu: \n" + "*************Library Management System************* \n"
						+ "1. Add Book \n" + "2. Add User \n" + "3. View Books List \n" + "4. View by Book-Code \n"
						+ "5. Delete Book by Book-Code \n" + "6. Check Book Availability \n" + "7. Mark as Issued \n"
						+ "8. Mark as Returned \n" + "9. Generate Book Report \n" + "10. Delete User \n" + "11. Exit \n"
						+ "Enter your choice: ");

				Scanner userInput = new Scanner(System.in);
				int choice = userInput.nextInt();

				switch (choice) {

				case 1:
					userInput.nextLine();
					main: while (true) {
						String a;
						bookList = liManagementSystem.AddBook(bookList);

						System.out.println("Do you want to add another book (Y/N)");
						a = userInput.nextLine();

						if (a.equalsIgnoreCase("Y")) {
							continue main;
						} else {
							break main;
						}
					}
					break;
				case 2:
					userInput.nextLine();
					boolean addUser = true;
					while (addUser) {
						String a;
						userList = liManagementSystem.AddUser(userList);

						System.out.println("Do you want to add another user (Y/N)");
						a = userInput.nextLine();

						if (!a.equalsIgnoreCase("Y")) {
							addUser = false;
						}
					}
					break;
				case 3:
					liManagementSystem.ViewBooksList(bookList);
					break;
				case 4:
					liManagementSystem.ViewByBookCode(bookList);
					break;
				case 5:
					userInput.nextLine();
					main: while (true) {
						String a;
						bookList = liManagementSystem.DeleteBookByBookCode(bookList);

						System.out.println("Do you want to delete another book (Y/N)");
						a = userInput.nextLine();

						if (a.equalsIgnoreCase("Y")) {
							continue main;
						} else {
							break main;
						}
					}
					break;
				case 6:
					liManagementSystem.CheckBookAvailability();
					break;
				case 7:
					liManagementSystem.MarkAsIssued(bookList, userList);

					break;
				case 8:
					liManagementSystem.MarkAsReturned(bookList, userList);
					break;
				case 9:
					liManagementSystem.GenerateReport(bookList);
					break;
				case 10:
					liManagementSystem.DeleteUser(userList);
					break;
				case 11:
					liManagementSystem.Exit(bookList, userList);
					break;
				default:
					System.out.println("Invalid Option, please enter a valid option  ");
				}
			}
		}
	}

	public void GenerateReport(List<Book> bookList) throws FileNotFoundException {
		System.out.println("**********Choose Report Type********** ");
		System.out.println("1.	Export All \n" + "2.	Export By Availability \n" + "3.	Export By Issued \n");
		int choice = userInput.nextInt();
		switch (choice) {

		case 1:
			LibraryManagementSystem libraryManagementSystem1 = new LibraryManagementSystem();
			Thread thread1 = new Thread(libraryManagementSystem1);
			thread1.start();

		case 2:

			ArrayList bookList1 = new ArrayList<>();

			for (Book book : bookList) {
				if (book.getIssue_Status().equalsIgnoreCase("Available")) {
					bookList1.add(book);
				}

			}

			LibraryManagementSystem libraryManagementSystem2 = new LibraryManagementSystem();
			Thread thread2 = new Thread(libraryManagementSystem2);
			thread2.start();
			break;

		case 3:

			ArrayList tempBookList = new ArrayList<>();

			for (Book book : bookList) {
				if (book.getIssue_Status().equalsIgnoreCase("Issued")) {
					tempBookList.add(book);
				}

			}

			LibraryManagementSystem libraryManagementSystem3 = new LibraryManagementSystem();
			Thread thread3 = new Thread(libraryManagementSystem3);
			thread3.start();
			break;
		default:
			System.out.println("Please enter a valid choice...");
		}
	}

	@Override
	public void run() {

	}
}

//class Thread1 extends Thread {
//	ArrayList temp = new ArrayList();
//
//	public Thread1(ArrayList bookList) {
//		for (int i = 0; i < bookList.size(); i++) {
//			temp.add(bookList.get(i));
//		}
//	}
//
//	@Override
//	public void run() {
//		for (int i = 0; i < temp.size() / 2; i++) {
//			System.out.println("Thread1 is running..");
//			File file = new File(
//					"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\Report.txt");
//			FileWriter writer = null;
//			try {
//				writer = new FileWriter(file);
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//			BufferedWriter buffer = new BufferedWriter(writer);
//			for (int j = 0; j < temp.size(); j++) {
//				try {
//					buffer.write(temp.get(j).toString() + "\n");
//				} catch (IOException e) {
//					throw new RuntimeException(e);
//				}
//			}
//			try {
//				buffer.write("\n");
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//			try {
//				buffer.close();
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//			try {
//				Thread1.sleep(2000);
//			} catch (InterruptedException e) {
//				throw new RuntimeException(e);
//			}
//		}
//	}
//}
//
//class Thread2 extends Thread {
//	ArrayList temp = new ArrayList();
//
//	public Thread2(ArrayList bookList) {
//		for (int i = 0; i < bookList.size(); i++) {
//			temp.add(bookList.get(i));
//		}
//	}
//
//	@Override
//	public void run() {
//		
//		for (int i = temp.size() / 2; i < temp.size(); i++) {
//			System.out.println("Thread2 is running..");
//			File file = new File(
//					"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\Report.txt");
//
//			FileWriter writer = null;
//			try {
//				writer = new FileWriter(file);
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//			BufferedWriter buffer = new BufferedWriter(writer);
//
//			for (int j = 0; j < temp.size(); j++) {
//				try {
//					buffer.write(temp.get(j).toString() + "\n");
//				} catch (IOException e) {
//					throw new RuntimeException(e);
//				}
//			}
//			try {
//				buffer.write("\n");
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//			try {
//				buffer.close();
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//			try {
//				Thread2.sleep(2000);
//			} catch (InterruptedException e) {
//				throw new RuntimeException(e);
//			}
//		}
//	}
//}