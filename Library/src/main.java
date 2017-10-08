import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class main {
	public static UserManager userManager = new UserManager();
	public static BookManager bookManager = new BookManager();
	public static final int normalUserCheckOutWeeks = 2;
	public static final int adminUserCheckOutWeeks = 3;
	public static void main(String args[]){
		
		
		LocalDateTime ld = LocalDateTime.now(); //Returns the current date and time
		GUI gui = new GUI();
		gui.init();
		
	}
	public static UserManager getGlobalUserManager(){
		return userManager;
	}
	public static BookManager getGlobalBookManager(){
		return bookManager;
	}
	public static int getNormalUserCheckOutWeeks() {
		return normalUserCheckOutWeeks;
	}
	public static int getAdminUserCheckOutWeeks() {
		return adminUserCheckOutWeeks;
	}
}
