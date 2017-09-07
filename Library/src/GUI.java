import javax.swing.*;
import java.io.*;
import java.util.*;

public class GUI {
	public BookManager bookManager;
	public UserManager userManager;
	public GUI() {
		bookManager = main.getGlobalBookManager();
		userManager = main.getGlobalUserManager();
	}
	public static void init() {
		
	}
}
