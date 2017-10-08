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
		JFrame windowFrame = new JFrame();
		JPanel windowPanel = new JPanel();
		windowFrame.add(windowPanel);
		windowFrame.setSize(500,500);
		windowFrame.setVisible(true);
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setTitle("Library"); //TODO: make a better title
	}
}
