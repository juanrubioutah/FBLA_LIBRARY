import java.time.LocalDateTime;
import java.util.ArrayList;

public class User {
	public int ID;
	public String firstName;
	public String lastName;
	public double fineAmount;
	public ArrayList<Book> checkedOutBooks;
	public boolean isStudent;
	public LocalDateTime checkOutTime;
	
	public User(int ID, String firstName, String lastName, boolean isStudent){
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isStudent = isStudent;
		checkedOutBooks = new ArrayList<Book>();
	}
	
	public boolean isEligible(){ //determine eligibility to check out a book, return true if eligible
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
	
	public void checkOut(User user, Book book){
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
		checkedOutBooks.remove(book);
		if((checkOutTime.plusWeeks(2)).compareTo(checkInTime)<=0) { //Book is returned on time
			
		}
		else if((checkOutTime.plusWeeks(2)).compareTo(checkInTime)>0) { //Book is returned late
			//Determine and assign fine amount
			
		}
	}
	public int getID(){
		return ID;
	}
	public String getName(){
		return firstName+" "+lastName;
	}

}
