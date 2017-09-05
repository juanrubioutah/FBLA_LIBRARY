import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class main {
	public UserManager userManager = new UserManager();
	public BookManager bookManager = new BookManager();
	public static void main(String args[]){
		
		
		LocalDateTime ld = LocalDateTime.now(); //Returns the current date and time
		
	}
	public UserManager getGlobalUserManager(){
		return userManager;
	}
	public BookManager getGlobalBookManager(){
		return bookManager;
	}
}
