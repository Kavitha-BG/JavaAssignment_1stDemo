package com.librarymanagement;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LibraryManagementSystem implements BookManagement, UserManagement {

	Scanner sc = new Scanner(System.in);

	@Override
	public ArrayList<Book> AddBook(ArrayList<Book> bookList) throws IOException {
		String Book_Code;
		
		main:
		while(true) {
			System.out.println("Enter the Book Code: ");
			Book_Code = sc.nextLine();
			
			Iterator<Book> itr=bookList.iterator(); 
			  while(itr.hasNext()){  
				    Book obj =(Book)itr.next(); 
			    
				    if (Book_Code.equals( obj.getBook_Code() )) {
				    	System.out.println("Duplicate Code. Please enter unique Book-Code");
				    	continue main;
				    }
			  }  
			  break;
		}
		
		System.out.print("Enter the Book name : ");
		String book_Name = sc.nextLine();
		System.out.print("Enter the Author : ");
		String author= sc.nextLine();
		System.out.print("Enter the Subject : ");
		String subject= sc.nextLine();
		String issue_Status= "Available";
		String issued_to = "NotIssued";
		LocalDateTime issued_Date= null; 
		LocalDateTime return_Date= null;
		
		Book b = new Book(Book_Code, book_Name, author, subject, issue_Status, issued_to, issued_Date ,return_Date);
		bookList.add(b);
		System.out.println(bookList);
		System.out.println(b);
		System.out.println("Book Added Successfully");
		
		return bookList;
	}
	
	
	@Override
	public ArrayList<User> AddUser(ArrayList<User> userList) throws IOException {
		String user_Id;
		
		System.out.println("Enter the User name: ");
		String name = sc.nextLine();
			
		main1:
		while(true) {
			System.out.println("Enter the User-Id: ");
			user_Id = sc.nextLine();
			
			Iterator<User> itr = userList.iterator(); 
			  while(itr.hasNext()){  
				    User obj =(User)itr.next(); 
				    
				    if (user_Id.equals(obj.getUser_Id())) {
				    	System.out.println("Duplicate Code. Please enter unique User-Id");
				    	continue main1;
				    }
			  }  
			  break;
			
		}
		String issued_Book_Id = "null";
		LocalDateTime date_Issued = null ;
		LocalDateTime return_Date = null;
		
		User u = new User(name, user_Id, issued_Book_Id, date_Issued, return_Date );
		userList.add(u);
		System.out.println(userList);
		System.out.println(u);
		System.out.println("User Added Successfully");
		
		return userList;
	}
	
	@Override
	public void ViewBooksList (ArrayList bookList ) {
		//ViewBooksList();
		System.out.println( " Book_Code Book_Name  Author  Subject  Issue_Status  Issued_to  Issued_Date  Return_Date ");
		Iterator itr = bookList.iterator(); 
			  while(itr.hasNext()){  
				    Book obj =(Book)itr.next(); 
				    
				    System.out.println(obj.getBook_Code() + " " +  obj.getBook_Name() + " " + obj.getAuthor() + " " + obj.getSubject() + " " + obj.getIssue_Status() + " " + obj.getIssued_to() + " " + obj.getIssued_Date()+ " " + obj.getReturn_Date());
			  }  
	}
	
//	public void ViewBooksList () {
//		System.out.printf( " Book_Code "," Book_Name "," Author "," Subject "," Issue_Status "," Issued_to "," Issued_Date "," Return_Date ");
//	}
	
	@Override
	public void ViewByBookCode (ArrayList bookList) {
		main3:		
		while(true) {
			System.out.println("Enter the Book Code: ");
			String book_Code = sc.nextLine();
			int count = 0;
			
			Iterator itr=bookList.iterator(); 
			  while(itr.hasNext()){  
				    Book obj =(Book)itr.next(); 
				    
				    //boolean search = obj.getBook_Code().equals(book_Code);
				    if ( (obj.getBook_Code().equals(book_Code)) == true ) {
				    	ViewByBookCode();
				    	
				    	System.out.printf(" book_Code = " + obj.getBook_Code()+ "\n"+
				    			"book_Name = " + obj.getBook_Name() + "\n"+
				    			"author = " + obj.getAuthor() + "\n" +
				    			"subject = " + obj.getSubject() + "\n" +
				    			"issue_Status = " + obj.getIssue_Status() + "\n" +
				    			"issued_to = " + obj.getIssued_to() + "\n" +
				    			"issued_Date = " + obj.getIssued_Date() + "\n" +
				    			"return_Date = " + obj.getReturn_Date() );
				    	//count++;
				    	break main3;
				    }
			  }  
			  if (count == 0 ) {
				  System.out.println("Book not available");
			  }
		}
	}
	
	
	public void ViewByBookCode() {
		System.out.printf("Book Details: ");
	}
	
	@Override
	public ArrayList<Book> SortBook(ArrayList bookList) {
		Collections.sort(bookList);
		return null;
	}
	
	
	
	@Override
	public void CheckBookAvailability() throws IOException {
		ArrayList bookList = retrieveBooksFromFile();
		//System.out.println(bookList);
		System.out.println(" Enter book_Code to check availability: ");
		String book_Code = sc.nextLine();
		String issued_to = "";
		
		Iterator itr = bookList.iterator();
		
		boolean flag = false;
		int count = 0;
		main:
		while (itr.hasNext()) {
			Book obj = (Book) itr.next();
			
			if(obj.getBook_Code().equals(book_Code)) {
				if(obj.getIssue_Status().equals("Issued")) {
					book_Code = obj.getBook_Code();
					issued_to = obj.getIssued_to();
					flag = true;
					break;
				}
				count++;
			}
		}
		
		if(count == 0) {
			System.out.println("Book not available");
		} else {
			System.out.println(book_Code + " available in library");
		}
		
		if(flag == true) {
			System.out.println(book_Code + " assigned to " + issued_to);
			CheckBookAvailability();
		}else {
			System.out.println(" Do you want to Check another Book Availability (Y/N) ");
			String a = sc.nextLine();
			if(a.equals("Y")) {
				CheckBookAvailability();
			}
		}
		
		File file = new File("C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\Book.txt");
		
		FileWriter writer = new FileWriter(file);
		BufferedWriter buffer = new BufferedWriter(writer);
		for(int i=0; i < bookList.size(); i++) {
			buffer.write(bookList.get(i).toString() + "\n");
		}
		buffer.write("\n");
		buffer.close();
	}
	
	@Override
	public void MarkAsIssued() throws IOException {
		ArrayList bookList = retrieveBooksFromFile();
		ArrayList userList = read2();
		System.out.printf("Book_Code", "Book_name","Status");
		
		Iterator itr = bookList.iterator();
		while(itr.hasNext()){  
		    Book obj =(Book)itr.next(); 
		    System.out.printf(obj.getBook_Code() , obj.getBook_Name(), obj.getIssue_Status());
		}
		
		String a;
		main:
		while(true) {
			System.out.println("Enter the Book-Code: ");
			String book_Code = sc.nextLine();
			System.out.println("Enter the User Id: ");
			String user_Id = sc.nextLine();
			
			int count =0;
			int count1 = 0;
			Iterator itr3 = bookList.iterator();
			Iterator itr1 = userList.iterator();
			
			main1:
			while(itr3.hasNext()) {
				Book obj = (Book) itr3.next();
				if(obj.getBook_Code().equals(book_Code)) {
					User u =(User) itr1.next();
					if(u.getUser_Id().equals(user_Id)) {
						count1++;
						if(obj.getIssue_Status().equals("Available")) {
							obj.setIssue_Status("Issued");
							obj.setIssued_to(user_Id);
							obj.setIssued_Date(LocalDateTime.now());
							obj.setReturn_Date(LocalDateTime.now().plusDays(7));
							count++;
							break main1;
						}else {
							System.out.println("Book is already issued on " + obj.getIssued_Date() + " to "+ obj.getIssued_to());
							System.out.println(("Try other books.."));
							count++;
							continue main;
						}
					}
					count++;
				}	
	
			}
			if(count1 ==0) {
				System.out.println("Please enter valid user id..");
				continue main;
			}
			if(count == 0) {
				System.out.println("Please enter valid book id..");
				continue main;
			}
			
			main3:
				while(itr1.hasNext()) {
					User u = (User) itr1.next();
					if (u.getUser_Id().equals(user_Id)) {
					u.setIssued_Book_Id(book_Code);
					u.setDate_Issued(LocalDateTime.now());
					u.setReturn_Date(LocalDateTime.now().plusDays(7));
					System.out.println("Book " + book_Code + "issued to user " + user_Id + " successfully");
					break main3;
				}
			}
			System.out.println("Do you want to issue another book(Y/N)");
			a = sc.nextLine();
			if (a.equals("Y")) {
				continue main;
			} else {
				break main;
			}
		}

		File file = new File(
				"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\User.txt");

		FileWriter writer = new FileWriter(file);
		BufferedWriter buffer = new BufferedWriter(writer);
		for (int i = 0; i < userList.size(); i++) {
			buffer.write(userList.get(i).toString() + "\n");
		}
		buffer.write("\n");
		buffer.close();

		// write to the Book.txt
		File file2 = new File(
				"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\Book.txt");

		FileWriter writer2 = new FileWriter(file);
		BufferedWriter buffer2 = new BufferedWriter(writer2);
		for (int i = 0; i < bookList.size(); i++) {
			buffer2.write(bookList.get(i).toString() + "\n");
		}
		buffer2.write("\n");
		buffer2.close();
	}

	@Override
	public void MarkAsReturned() throws IOException {
		ArrayList bookList = retrieveBooksFromFile();
		ArrayList userList = read2();

		String a;
		main: while (true) {
			System.out.println("Enter the Book-Code: ");
			String book_Code = sc.nextLine();

			Iterator itr3 = bookList.iterator();

			int count = 0;
			boolean flag = false;

			main1: while (itr3.hasNext()) {
				Book obj = (Book) itr3.next();
				if (obj.getBook_Code().equals(book_Code)) {
					if (obj.getIssue_Status().equals("Issued")) {
						obj.setIssue_Status("Available");
						obj.setIssued_to("NotIssued");
						obj.setIssued_Date(null);
						obj.setReturn_Date(null);
						count++;
						break main1;
					}
					count++;
					flag = true;
				}
			}

			if (flag == true) {
				System.out.println("Book is already returned");
				System.out.println("Try other books..");
				MarkAsReturned();
			}
			if (count == 0) {
				System.out.println("Please enter the valid book code");
				MarkAsReturned();
			}

			Iterator itr1 = userList.iterator();
			main3: while (itr1.hasNext()) {
				User u = (User) itr1.next();
				if (u.getIssued_Book_Id().equals(book_Code)) {
					u.setDate_Issued(null);
					u.setReturn_Date(null);
					System.out.println("Book Returned successfully ");
					break main3;
				}
			}

			System.out.println("Do you want to return another book(Y/N)");
			a = sc.nextLine();
			if (a.equals("Y")) {
				continue main;
			} else {
				break main;
			}
		}

		File file = new File(
				"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\User.txt");

		FileWriter writer = new FileWriter(file);
		BufferedWriter buffer = new BufferedWriter(writer);
		for (int i = 0; i < userList.size(); i++) {
			buffer.write(userList.get(i).toString() + "\n");
		}
		buffer.write("\n");
		buffer.close();

		// write to the Book.txt
		File file2 = new File(
				"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\Book.txt");

		FileWriter writer2 = new FileWriter(file);
		BufferedWriter buffer2 = new BufferedWriter(writer2);
		for (int i = 0; i < bookList.size(); i++) {
			buffer2.write(bookList.get(i).toString() + "\n");
		}
		buffer2.write("\n");
		buffer2.close();
	}

	public ArrayList<Book> DeleteBookByBookCode(ArrayList bookList) {
		if (bookList.isEmpty()) {
			System.out.println(" List is Empty ");
		} else {
			main4: while (true) {
				System.out.println("Enter the Book Code: ");
				String book_Code = sc.nextLine();
				int count = 0;
				Iterator itr = bookList.iterator();
				while (itr.hasNext()) {
					Book obj = (Book) itr.next();

					boolean search = obj.getBook_Code().equals(book_Code);
					if (search == true) {
						itr.remove();

						System.out.println("Book deleted Successfully");
						count++;
						break main4;
					}
				}
				if (count == 0) {
					System.out.println("Book code is not available");
					continue main4;
				}
			}
		}
		return bookList;
	}

	@Override
	public ArrayList<User> DeleteUser(ArrayList userList) {
		if (userList.isEmpty()) {
			System.out.println(" List is Empty ");

		} else {
			main4: while (true) {
				System.out.println("Enter User_Code");
				String book_Code = sc.nextLine();
				Iterator iterator = userList.iterator();
				int count = 0;
				while (iterator.hasNext()) {
					User obj = (User) iterator.next();
					boolean search = obj.getUser_Id().equals(book_Code);
					if (search == true) {
						iterator.remove();
						System.out.println(" User deleted Successfully ");
						count++;
						break main4;
					}
				}
				if (count == 0) {
					System.out.println(" User code is not available ");
					continue main4;
				}
			}
		}
		return userList;
	}

	public void bookwrite(ArrayList<Book> bookList) {
		File file = new File(
				"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\Book.txt");
		try {
			FileWriter writer = new FileWriter(file);
			BufferedWriter buffer = new BufferedWriter(writer);
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
//	  public void Exit(ArrayList<Book> bookList) 

//	  public void Exit(ArrayList<User> userList){
		bookwrite(bookList);
		userwrite(userList);

		System.exit(0);
	}

	public ArrayList<Book> retrieveBooksFromFile() throws FileNotFoundException {
		ArrayList<Book> booklist = new ArrayList<>();

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
		ArrayList<User> userlist = new ArrayList<>();

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

			boolean condition = true;
			while (condition) {
				System.out.println("Menu: \n" + "*************Library Management System************* \n"
						+ "1. Add Book \n" + 
						"2. Add User \n" + 
						"3. View Books List \n" + 
						"4. View by Book-Code \n" +
						"5. Delete Book by Book-Code \n" + 
						"6. Check Book Availability \n" + 
						"7. Mark as Issued \n"+ 
						"8. Mark as Returned \n" + 
						"9. Generate Book Report \n" + 
						"10. Exit \n" +
						"Enter your choice: ");

				Scanner sc = new Scanner(System.in);
				int ch = sc.nextInt();

				switch (ch) {

				case 1:
					sc.nextLine();
					main: while (true) {
						String a;
						bookList = liManagementSystem.AddBook(bookList);

						System.out.println(" Do you want to add another book (Y/N)");
						a = sc.nextLine();

						if (a.equals("Y")) {
							continue main;
						} else {
							break main;
						}
					}
					break;
				case 2:
					sc.nextLine();
					boolean addUser = true;
					while (addUser) {
						String a;
						userList = liManagementSystem.AddUser(userList);

						System.out.println(" Do you want to add another user (Y/N)");
						a = sc.nextLine();

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
					sc.nextLine();
					main: while (true) {
						String a;
						bookList = liManagementSystem.DeleteBookByBookCode(bookList);

						File file = new File(
								"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\Book.txt");

						FileWriter writer = new FileWriter(file);
						BufferedWriter buffer = new BufferedWriter(writer);
						for (int i = 0; i < bookList.size(); i++) {
							buffer.write(bookList.get(i).toString() + "\n");
						}
						buffer.write("\n");
						buffer.close();

						System.out.println(" Do you want to delete another book (Y/N)");
						a = sc.nextLine();

						if (a.equals("Y")) {
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
					liManagementSystem.MarkAsIssued();
					break;
				case 8:
					liManagementSystem.MarkAsReturned();
					break;
				case 9:
					liManagementSystem.GenerateReport();
					break;
				case 10:
					liManagementSystem.Exit(bookList, userList);
					// s.Exit(bookList);
					// s.Exit(userList);
//					condition = false;
					break;
				default:
					System.out.println("Invalid Option, please enter a valid option  ");
				}
			}
		}
	}

	public void GenerateReport() throws FileNotFoundException {
		System.out.println("**********Choose Report Type********** ");
		System.out.println("1.	Export All \n" + "2.	Export By Availability \n" + "3.	Export By Issued \n");
		int ch = sc.nextInt();
		switch (ch) {
		case 1:
			ArrayList temp = new ArrayList<>();
			ArrayList bookList = retrieveBooksFromFile();

			Iterator itr = bookList.iterator();
			while (itr.hasNext()) {
				Book obj = (Book) itr.next();
				temp.add(obj);
			}
			Thread1 e = new Thread1(temp);
			e.start();
			Thread2 v = new Thread2(temp);
			v.start();
			break;
		case 2:
			ArrayList temp1 = new ArrayList<>();
			ArrayList bookList1 = retrieveBooksFromFile();

			Iterator itr1 = bookList1.iterator();
			while (itr1.hasNext()) {
				Book obj = (Book) itr1.next();
				if (obj.getIssue_Status().equals("Available")) {
					temp1.add(obj);
				}
			}
			Thread1 t1 = new Thread1(temp1);
			t1.start();
			Thread2 t2 = new Thread2(temp1);
			t2.start();
			break;
		case 3:
			ArrayList temp2 = new ArrayList<>();
			ArrayList bookList2 = retrieveBooksFromFile();

			Iterator itr2 = bookList2.iterator();
			while (itr2.hasNext()) {
				Book obj = (Book) itr2.next();
				if (obj.getIssue_Status().equals("Issued")) {
					temp2.add(obj);
				}
			}
			Thread1 t11 = new Thread1(temp2);
			t11.start();
			Thread2 t22 = new Thread2(temp2);
			t22.start();
			break;
		default:
			System.out.println("Please enter a valid choice...");
		}
	}
}

class Thread1 extends Thread {
	ArrayList temp = new ArrayList();

	public Thread1(ArrayList bookList) {
		for (int i = 0; i < bookList.size(); i++) {
			temp.add(bookList.get(i));
		}
	}

	@Override
	public void run() {
		for (int i = 0; i < temp.size() / 2; i++) {
			System.out.println("Thread1 is running..");
			File file = new File(
					"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\Report.txt");

			FileWriter writer = null;
			try {
				writer = new FileWriter(file);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			BufferedWriter buffer = new BufferedWriter(writer);
			for (int j = 0; j < temp.size(); j++) {
				try {
					buffer.write(temp.get(j).toString() + "\n");
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			try {
				buffer.write("\n");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			try {
				buffer.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			try {
				Thread1.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}

class Thread2 extends Thread {
	ArrayList temp = new ArrayList();

	public Thread2(ArrayList bookList) {
		for (int i = 0; i < bookList.size(); i++) {
			temp.add(bookList.get(i));
		}
	}

	@Override
	public void run() {
		for (int i = temp.size() / 2; i < temp.size(); i++) {
			System.out.println("Thread2 is running..");
			File file = new File(
					"C:\\Users\\Kavitha_BG\\eclipse-workspace\\LibraryManagementSystem\\src\\com\\librarymanagement\\Report.txt");

			FileWriter writer = null;
			try {
				writer = new FileWriter(file);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			BufferedWriter buffer = new BufferedWriter(writer);
			for (int j = 0; j < temp.size(); j++) {
				try {
					buffer.write(temp.get(j).toString() + "\n");
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			try {
				buffer.write("\n");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			try {
				buffer.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			try {
				Thread2.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}

//	private ArrayList read() throws IOException { 
//		
//		try {  
//	         // create a new file with an ObjectOutputStream  
//	         FileOutputStream outstream = new FileOutputStream("Book.txt");  
//	         FileOutputStream outstream1 = new FileOutputStream("User.txt");
//	         ObjectOutputStream objoutstream = new ObjectOutputStream(outstream);  
//	  
//	         // write something in the file  
//	         objoutstream.writeUTF(s);  
//	         objoutstream.flush();  
//	  
//	         // create an ObjectInputStream for the file we created before  
//	         ObjectInputStream objinstream = new ObjectInputStream(new FileInputStream("Book.txt"));  
//	         ObjectInputStream objinstream1 = new ObjectInputStream(new FileInputStream("User.txt"));
//	  
//	         // read and print the whole content  
//	         byte[] bt = new byte[22];  
//	         objinstream.readFully(bt);  
//	         objinstream1.readFully(bt);
//	         String arr = new String(bt);  
//	         System.out.println("" + arr);  
//	      } catch (Exception ex) {  
//	         ex.printStackTrace();  
//	      }  
//	}
//}
