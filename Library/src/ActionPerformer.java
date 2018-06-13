import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class ActionPerformer {
	
	public BookManager bookManager = main.getGlobalBookManager();
	public UserManager userManager = main.getGlobalUserManager();
	
	public ActionPerformer() {
		
	}
	public void onActionPerformed() {
		readData();
		saveData();
	}
	public boolean saveData() {
		return false;
	}
	public boolean readData() {
		File bookDataFile = new File("bookdata.txt");
		File userDataFile = new File("userdata.txt");
		
		ArrayList<Book> temporaryBookList = new ArrayList<Book>();
		ArrayList<User> temporaryUserList = new ArrayList<User>();
		
		try {
			List<String> completeBookData = Files.readAllLines(bookDataFile.toPath());
			List<String> completeUserData = Files.readAllLines(userDataFile.toPath());
			
			for(int i=0; i<completeBookData.size(); i++) {
				StringTokenizer tokenizer = new StringTokenizer(completeBookData.get(i), "%");
				long ISBN = Long.parseLong(tokenizer.nextToken());
				String title = tokenizer.nextToken();
				String author = tokenizer.nextToken();
				if(tokenizer.hasMoreTokens()) {
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return false;
	}
	public void performAction() {
		onActionPerformed();
	}
}

