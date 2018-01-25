import java.time.LocalDateTime;
import java.util.Date;

public class Book {
	public static long ISBN;
	public static String title;
	public static String author;
	public static LocalDateTime lastCheckout;
	public static User lastUser;
	public static boolean isCheckedOut;
	public static LocalDateTime dueDate;
	public static boolean isOnHold;
	public static User holdUser;
	
	public static BookManager bookManager = main.getGlobalBookManager();
	public static UserManager userManager = main.getGlobalUserManager();
	
	public Book(long ISBN, String title, String author){
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		
		lastCheckout = null;
		lastUser = null;
		isCheckedOut = false;
		dueDate = null;
		isOnHold = false;
		holdUser = null;
	}
	public User getLastUser() {
		return lastUser;
	}
	public void checkOut(User user){
		lastUser = user;
		isCheckedOut = true;
		bookManager.checkOutBook(this);
		lastCheckout = LocalDateTime.now();
		if(user.isAdmin) {
			dueDate = lastCheckout.plusWeeks(3);
			return;
		}
		else {
			dueDate = lastCheckout.plusWeeks(2);
			return;
		}
	}
	public void checkIn(){
		bookManager.checkInBook(this);
	}
	public long getISBN() {
		return ISBN;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void hold(User user) {
		if(isOnHold == false) { //Make sure the book isn't already on hold
			isOnHold = true;
			holdUser = user;
			System.out.println("Expected hold date: "+dueDate);
			return;
		}
		if(isOnHold == true) {
			System.out.println("Book is already on hold!");
			return;
		}
	}
}
