import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class main {
	public static UserManager userManager = new UserManager();
	public static BookManager bookManager = new BookManager();
	public static void main(String args[]){
		
		
		LocalDateTime ld = LocalDateTime.now(); //Returns the current date and time
		
	}
	public static UserManager getGlobalUserManager(){
		return userManager;
	}
	public static BookManager getGlobalBookManager(){
		return bookManager;
	}
}
