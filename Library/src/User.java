import java.util.ArrayList;

public class User {
	public int ID;
	public String firstName;
	public String lastName;
	public double fineAmount;
	public ArrayList<Book> checkedOutBooks;
	public boolean isStudent;
	
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
			return true;
		}
	}
	
	public void checkOut(Book book){
		
	}
	public void checkIn(Book book){
		
	}
	public int getID(){
		return ID;
	}
	public String getName(){
		return firstName+" "+lastName;
	}

}
