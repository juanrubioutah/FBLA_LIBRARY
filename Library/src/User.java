import java.time.LocalDateTime;
import java.util.ArrayList;

public class User {
	public int ID;
	public String firstName;
	public String lastName;
	public static double fineAmount;
	public static ArrayList<Book> checkedOutBooks;
	public boolean isAdmin;
	public static LocalDateTime checkOutTime;
	
	public User(int ID, String firstName, String lastName, boolean isAdmin){
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
		checkedOutBooks = new ArrayList<Book>();
	}
	
	public static boolean isEligible(){ //determine eligibility to check out a book, return true if eligible
		if(fineAmount>0){
			return false;
		}
		else{
			if(checkedOutBooks.size()>=4) {
				return false;
			}
			else if(checkedOutBooks.size()<4) {
			return true;
			}
		}
		return false;
	}
	
	public static void checkOut(User user, Book book){
		if(isEligible()==true) {
			checkedOutBooks.add(book);
			book.checkOut(user);
			System.out.println("Success");
			checkOutTime = LocalDateTime.now();
		}
		if(isEligible()==false) {
			System.out.println("Unable to check out. Verify user stats.");
			return;
		}
	}
	public void checkIn(Book book){
		LocalDateTime checkInTime = LocalDateTime.now();
		if(isAdmin==true) {
			checkedOutBooks.remove(book);
			if((checkOutTime.plusWeeks(main.getAdminUserCheckOutWeeks())).compareTo(checkInTime)<=0) { //Book is returned on time
				book.checkIn();
			}
			else if((checkOutTime.plusWeeks(main.getAdminUserCheckOutWeeks())).compareTo(checkInTime)>0) { //Book is returned late
				fineAmount += 5; //TODO: make fine amounts vary based on how late the book is returned
			}
		}
		else if(isAdmin==false) {
			checkedOutBooks.remove(book);
			if((checkOutTime.plusWeeks(main.getNormalUserCheckOutWeeks())).compareTo(checkInTime)<=0) { //Book is returned on time
				book.checkIn();
			}
			else if((checkOutTime.plusWeeks(main.getNormalUserCheckOutWeeks())).compareTo(checkInTime)>0) { //Book is returned late
				fineAmount += 5; //TODO: make fine amounts vary based on how late the book is returned
			}
		}	
	}
	public int getID(){
		return ID;
	}
	public String getName(){
		return firstName+" "+lastName;
	}
	public void payFine(double fineAmount2) { //decrease the fine by 'amountPaid'
		fineAmount = fineAmount-fineAmount2;
	}

}
