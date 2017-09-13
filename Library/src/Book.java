import java.util.Date;

public class Book {
	public static int IBSN;
	public static String title;
	public static String author;
	public static Date lastCheckout;
	public static User lastUser;
	public static boolean isCheckedOut;
	public static Date dueDate;
	public static boolean isOnHold;
	public static User holdUser;
	
	public static BookManager bookManager = main.getGlobalBookManager();
	public static UserManager userManager = main.getGlobalUserManager();
	
	public Book(int IBSN, String title, String author){
		this.IBSN = IBSN;
		this.title = title;
		this.author = author;
		
		lastCheckout = null;
		lastUser = null;
		isCheckedOut = false;
		dueDate = null;
		isOnHold = false;
		holdUser = null;
	}
	public void checkOut(User user){
		lastUser = user;
		isCheckedOut = true;
		bookManager.checkOutBook(this);
	}
	public void checkIn(){
		bookManager.checkInBook(this);
	}
	public int getIBSN() {
		return IBSN;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
}
