import java.util.Date;

public class Book {
	public int ISBN;
	public String title;
	public String author;
	public Date lastCheckout;
	public User lastUser;
	public boolean isCheckedOut;
	public Date dueDate;
	public boolean isOnHold;
	public User holdUser;
	
	public Book(int ISBN, String title, String author){
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
	public void checkOut(User user){
		lastUser = user;
		isCheckedOut = true;
		
	}
	public void checkIn(){
		
	}
	
}
