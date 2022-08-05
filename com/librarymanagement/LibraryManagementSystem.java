package com.librarymanagement;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LibraryManagementSystem implements BookManagement, UserManagement, Runnable {

	List<User> userlist = new ArrayList<>();
	List<Book> booklist = new ArrayList<>();
	List<Book> reportBookList = new ArrayList<>();

	Scanner userInput = new Scanner(System.in);

	@Override
	public List<Book> addBook(List<Book> bookList) throws IOException {
		String bookCode;

		main: while (true) {
			System.out.println("Enter the Book Code: ");
			bookCode = userInput.nextLine();

			Iterator<Book> itr = bookList.iterator();
			while (itr.hasNext()) {
				Book obj = (Book) itr.next();

				if (bookCode.equals(obj.getBookCode())) {
					System.out.println("Duplicate Code. Please enter unique Book-Code");
					System.out.println("-------------------------------------------------------");
					continue main;
				}
			}

			break;
		}

		System.out.print("Enter the Book name : ");
		String bookName = userInput.nextLine();

		System.out.print("Enter the Author : ");
		String author = userInput.nextLine();

		System.out.print("Enter the Subject : ");
		String subject = userInput.nextLine();

		String issueStatus = "Available";
		String issuedto = "NotIssued";
		LocalDateTime issuedDate = null;
		LocalDateTime returnDate = null;

		Book b = new Book(bookCode, bookName, author, subject, issueStatus, issuedto, issuedDate, returnDate);
		bookList.add(b);

//		System.out.println(bookList);
//		System.out.println(b);

		System.out.println("Book Added Successfully");
		System.out.println("------------------------------------------------");

		return bookList;
	}

	@Override
	public List<User> addUser(List<User> userList) throws IOException {
		String userId;

		System.out.println("Enter the User name: ");
		String name = userInput.nextLine();

		main1: while (true) {
			System.out.println("Enter the User-Id: ");
			userId = userInput.nextLine();

			Iterator<User> itr = userList.iterator();
			while (itr.hasNext()) {
				User obj = (User) itr.next();

				if (userId.equals(obj.getUserId())) {
					System.out.println("Duplicate Code. Please enter unique User-Id");
					System.out.println("-------------------------------------------------------");
					continue main1;
				}
			}

			break;
		}

		String issuedBookId = "null";
		LocalDateTime dateIssued = null;
		LocalDateTime returnDate = null;

		User u = new User(name, userId, issuedBookId, dateIssued, returnDate);
		userList.add(u);

//		System.out.println(userList);
//		System.out.println(u);

		System.out.println("User Added Successfully");
		System.out.println("-------------------------------------------------------");

		return userList;
	}

	@Override
	public void viewBooksList(List<Book> bookList) {
		viewBooksList();

		Iterator<Book> itr = bookList.iterator();
		while (itr.hasNext()) {
			Book obj = (Book) itr.next();

			System.out.printf("%s %11s %10s %10s %15s %20s %30s %30s", obj.getBookCode(), obj.getBookName(),
					obj.getAuthor(), obj.getSubject(), obj.getIssueStatus(), obj.getIssuedto(), obj.getIssuedDate(),
					obj.getReturnDate()+ "\n");
			
		}

		System.out.println(
				"\n--------------------------------------------------------------------------------------------------------------------------------------------");
	}

	public void viewBooksList() {
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%s %10s %10s %10s %15s %20s %20s %20s", "Book Code", "Book Name", "Author", "Subject",
				"Issue Status", "Issued to", "Issued Date", "Return Date");
		System.out.println(
				"\n--------------------------------------------------------------------------------------------------------------------------------------------");
	}

	@Override
	public void viewByBookCode(List<Book> bookList) {
		main3: while (true) {
			System.out.println("Enter the Book Code: ");
			String bookCode = userInput.nextLine();

			int count = 0;

			Iterator<Book> itr = bookList.iterator();
			while (itr.hasNext()) {
				Book obj = (Book) itr.next();

				if ((obj.getBookCode().equals(bookCode)) == true) {
					viewByBookCode();

					System.out.printf("Book Code = " + obj.getBookCode() + "\n" + "Book Name = " + obj.getBookName()
							+ "\n" + "Author = " + obj.getAuthor() + "\n" + "Subject = " + obj.getSubject() + "\n"
							+ "Issue Status = " + obj.getIssueStatus() + "\n" + "Issued to = " + obj.getIssuedto()
							+ "\n" + "Issued Date = " + obj.getIssuedDate() + "\n" + "Return Date = "
							+ obj.getReturnDate() + "\n");
					System.out.println("--------------------------------------------------------");
					// count++;
					break main3;
				}
			}

			if (count == 0) {
				System.out.println("Book not available");
				System.out.println("--------------------------------------------------------");
			}
		}
	}

	public void viewByBookCode() {
		System.out.printf("Book Details: \n");
	}

	@Override
	public void checkBookAvailability() throws IOException {
		List<Book> bookList = retrieveBooksFromFile();
		// System.out.println(bookList);

		System.out.println("Enter Book Code to check availability: ");

		String bookCode = userInput.nextLine();
		String issuedto = "";

		Iterator<Book> itr = bookList.iterator();

		boolean flag = false;
		int count = 0;

		while (itr.hasNext()) {
			Book obj = (Book) itr.next();

			if (obj.getBookCode().equals(bookCode)) {
				if (obj.getIssueStatus().equals("Issued")) {
					bookCode = obj.getBookCode();
					issuedto = obj.getIssuedto();
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
			System.out.println(bookCode + " available in library");
			System.out.println("-------------------------------------------------------");
		}

		if (flag == true) {
			System.out.println(bookCode + " assigned to " + issuedto);
			System.out.println("-------------------------------------------------------");
			checkBookAvailability();

		} else {
			System.out.println("Do you want to Check another Book Availability (Y/N) ");
			String a = userInput.nextLine();
			if (a.equalsIgnoreCase("Y")) {
				checkBookAvailability();
			}
		}
	}

	@Override
	public void markAsIssued(List<Book> bookList, List<User> userList1) throws IOException {

		List<User> userList = userList1;

		System.out.println("Book Code" + "\t" + "Book Name" + "\t" + "Book Status ");

		for (Book book : bookList) {
			System.out.println(book.getBookCode() + "\t \t" + book.getBookName() + "\t \t" + book.getIssueStatus());
		}

		System.out.println("---------------------------------------------------------");

		String a;
		boolean issueBook = true;

		while (issueBook) {
			System.out.println("Enter the Book Code: ");
			String bookCode = userInput.nextLine();

			if (bookCode.equals("")) {
				System.out.println("Enter Valid Book Code");
				continue;
			}

			System.out.println("Enter the User Id: ");
			String userId = userInput.nextLine();

			if (userId.equals("")) {
				System.out.println("Enter Valid User Id");
				continue;
			}

			User tempUser = null;

			for (User user : userList) {
				if (user.getUserId().equalsIgnoreCase(userId)) {
					tempUser = user;
				}
			}

			Book tempBook = null;

			for (Book book : bookList) {
				if (book.getBookCode().equals(bookCode)) {
					tempBook = book;
				}
			}

			if (tempBook == null || tempUser == null) {
				System.out.println(bookCode + " or " + userId + " Invalid. ");
				System.out.println("-----------------------------------------------------------");
				continue;
			} else {

				if (tempBook.getIssueStatus().equalsIgnoreCase("Available")) {

					tempBook.setIssueStatus("Issued");
					tempBook.setIssuedto(userId);
					tempBook.setIssuedDate(LocalDateTime.now());
					tempBook.setReturnDate(LocalDateTime.now().plusDays(7));

					tempUser.setIssuedBookId(bookCode);
					tempUser.setDateIssued(LocalDateTime.now());
					tempUser.setReturnDate(LocalDateTime.now().plusDays(7));

					System.out.println("Book " + bookCode + " issued to user " + userId + " successfully");
					System.out.println("---------------------------------------------------------");
				} else {

					System.out.println(
							"Book is already issued on " + tempBook.getIssuedDate() + " to " + tempBook.getIssuedto());
					System.out.println("---------------------------------------------------------");
					continue;
				}
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
	public void markAsReturned(List<Book> bookList, List<User> userList1) throws IOException {
		List<User> userList = userList1;

		String a;
		main: while (true) {

			System.out.println("Enter the Book Code: ");
			String bookCode = userInput.nextLine();

			User tempUser = null;

			Book tempBook = null;
			String userId = null;

			for (Book book : bookList) {
				if (book.getBookCode().equals(bookCode)) {
					tempBook = book;
					userId = book.getIssuedto();
				}
			}

			for (User user : userList) {
				if (user.getUserId().equalsIgnoreCase(userId)) {
					tempUser = user;
				}
			}

			if (tempBook == null) {
				System.out.println("Book code invalid");
				System.out.println("---------------------------------------------------------");
				continue;
			} else if (tempBook != null && tempUser == null) {
				System.out.println("Book is already available in Library");
				System.out.println("---------------------------------------------------------");
			} else {

				tempBook.setIssueStatus("Available");
				tempBook.setIssuedto("NotIssued");
				tempBook.setIssuedDate(null);
				tempBook.setReturnDate(null);

				tempUser.setIssuedBookId(null);
				tempUser.setDateIssued(null);
				tempUser.setReturnDate(null);
				System.out.println("Book Returned successfully ");
				System.out.println("---------------------------------------------------------");
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

	public List<Book> deleteBookByBookCode(List<Book> bookList) {

		List<Integer> tempBookList = new ArrayList<>();
		if (bookList.isEmpty()) {
			System.out.println("List is Empty ");
			System.out.println("---------------------------------------------------------");
		} else {
			main4: while (true) {
				System.out.println("Enter the Book Code: ");
				String bookCode = userInput.nextLine();

				int count = 0;

				for (Book book : bookList) {
					if (book.getBookCode().equalsIgnoreCase(bookCode)) {

						if (book.getIssueStatus().equalsIgnoreCase("Available")) {
							count++;
							System.out.println("Book deleted Successfully");
							System.out.println("---------------------------------------------------------");
//							tempBookList.add(bookList.indexOf(book));
							bookList.remove(book);
							break main4;

						} else {
							count++;
							System.out.println("Book Assigned to " + book.getIssuedto() + " . Can't delete the Book.");
						}

					}
				}

				if (count == 0) {
					System.out.println("Book code is not available");
					System.out.println("---------------------------------------------------------");
					continue main4;
				}
			}
		}
//		for(int index : tempBookList) {
//			bookList.remove(index);
//		}
		
		return bookList;
	}

	@Override
	public List<User> deleteUser(List<User> userList) {
		if (userList.isEmpty()) {
			System.out.println("List is Empty ");
			System.out.println("-------------------------------------------------------");

		} else {
			main4: while (true) {
				System.out.println("Enter User Id");
				String bookCode = userInput.nextLine();

				int count = 0;

				Iterator<User> iterator = userList.iterator();

				while (iterator.hasNext()) {
					User obj = (User) iterator.next();

					if (obj.getUserId().equals(bookCode) == true) {
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

	public void userwrite(List<User> userList) {
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
	public void exit(List<Book> bookList, List<User> userList) {
		bookwrite(bookList);
		userwrite(userList);

		System.exit(0);
	}

	public List<Book> retrieveBooksFromFile() throws FileNotFoundException {

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

	public List<User> read2() throws FileNotFoundException {

		File file1 = new File(
				"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\User.txt");

		BufferedReader br = new BufferedReader(new FileReader(file1));

		try {
			String str = null; // file to string conversion

			while ((str = br.readLine()) != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
				String[] split = str.split(",");// split string

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

	public void generateReport(List<Book> bookList) throws FileNotFoundException {
		System.out.println("**********Choose Report Type********** ");
		System.out.println("1.	Export All \n" + "2.	Export By Availability \n" + "3.	Export By Issued \n");

		int choice = userInput.nextInt();

		switch (choice) {
		case 1:
			reportBookList = bookList;
			run();
			break;

		case 2:

			List<Book> bookList1 = new ArrayList<>();

			for (Book book : bookList) {
				if (book.getIssueStatus().equalsIgnoreCase("Available")) {
					bookList1.add(book);
				}
			}

			reportBookList = bookList1;
			run();
			break;

		case 3:

			List<Book> tempBookList = new ArrayList<>();

			for (Book book : bookList) {
				if (book.getIssueStatus().equalsIgnoreCase("Issued")) {
					tempBookList.add(book);
				}
			}
			reportBookList = tempBookList;
			run();
			break;

		default:
			System.out.println("Please enter a valid choice...");
		}

	}

	@Override
	public void run() {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss a");
		LocalDateTime now = LocalDateTime.now();
		String date = dateTimeFormatter.format(now);

		String filePath = "C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\Report-"
				+ (date.replace(" ", "")) + ".txt";

		File report = new File(filePath);
		try {
			FileWriter fwrite = new FileWriter(filePath);

			if (report.exists()) {
				report.delete();
			}
			List<Book> bookList = reportBookList;
			if (!bookList.isEmpty()) {

			}
			for (Book book : bookList) {

				fwrite.write("----------------------------------------------\n");
				fwrite.write("Book Code = " + book.getBookCode() + "\n");
				fwrite.write("Book Name = " + book.getBookName() + "\n");
				fwrite.write("Author = " + book.getAuthor() + "\n");
				fwrite.write("Subject = " + book.getSubject() + "\n");
				fwrite.write("Issue Status = " + book.getIssueStatus() + "\n");
				fwrite.write("Issued to = " + book.getIssuedto() + "\n");
				fwrite.write("Issued Date = " + book.getIssuedDate() + "\n");
				fwrite.write("Return Date = " + book.getReturnDate() + "\n");
				fwrite.write("----------------------------------------------\n");
			}

			System.out.println("Report Generated Successfully");

			fwrite.close();

		} catch (IOException e) {

			e.printStackTrace();
			System.out.println("Library report error");

		}

	}

	public static void main(String[] args) throws IOException, InterruptedException {

		LibraryManagementSystem liManagementSystem = new LibraryManagementSystem();
		List<Book> bookList = liManagementSystem.retrieveBooksFromFile();
		List<User> userList = liManagementSystem.read2();
//		List<Book> reportBooksList = new ArrayList<>();

		while (true) {
			boolean condition = true;
			Scanner userInput = new Scanner(System.in);
			while (condition) {

				System.out.println("Menu: \n" + "*************Library Management System************* \n"
						+ "1. Add Book \n" + "2. Add User \n" + "3. View Books List \n" + "4. View by Book-Code \n"
						+ "5. Delete Book by Book-Code \n" + "6. Check Book Availability \n" + "7. Mark as Issued \n"
						+ "8. Mark as Returned \n" + "9. Generate Book Report \n" + "10. Delete User \n" + "11. Exit \n"
						+ "Enter your choice: ");


				int choice = 0;

				try {
					choice = userInput.nextInt();
				} catch (Exception e) {
					System.out.println("Please Enter Valid Option");
					continue;
				}

				switch (choice) {

				case 1:
					userInput.nextLine();

					main: while (true) {
						String addAnotherBook;
						bookList = liManagementSystem.addBook(bookList);

						System.out.println("Do you want to add another book (Y/N)");
						addAnotherBook = userInput.nextLine();

						if (addAnotherBook.equalsIgnoreCase("Y")) {
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
						userList = liManagementSystem.addUser(userList);

						System.out.println("Do you want to add another user (Y/N)");
						a = userInput.nextLine();

						if (!a.equalsIgnoreCase("Y")) {
							addUser = false;
						}
					}
					break;

				case 3:
					liManagementSystem.viewBooksList(bookList);
					break;

				case 4:
					liManagementSystem.viewByBookCode(bookList);
					break;

				case 5:
					userInput.nextLine();

					main: while (true) {
						String a;

						bookList = liManagementSystem.deleteBookByBookCode(bookList);

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
					liManagementSystem.checkBookAvailability();
					break;

				case 7:
					liManagementSystem.markAsIssued(bookList, userList);
					break;

				case 8:
					liManagementSystem.markAsReturned(bookList, userList);
					break;

				case 9:
					liManagementSystem.generateReport(bookList);
					break;

				case 10:
					liManagementSystem.deleteUser(userList);
					break;

				case 11:
					liManagementSystem.exit(bookList, userList);
					break;

				default:
					System.out.println("Invalid Option, please enter a valid option ");
				}

				 userInput.close();
			}
		}
	}
}
