import java.util.Date;

public class Book {
	public int IBSN;
	public String title;
	public String author;
	public Date lastCheckout;
	public User lastUser;
	public boolean isCheckedOut;
	public Date dueDate;
	public boolean isOnHold;
	public User holdUser;
	
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
		
	}
	public void checkIn(){
		
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
