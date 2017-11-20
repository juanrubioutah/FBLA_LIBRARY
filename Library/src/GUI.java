import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class GUI {
	public static BookManager bookManager;
	public static UserManager userManager;
	public GUI() {
		bookManager = main.getGlobalBookManager();
		userManager = main.getGlobalUserManager();
	}
	public static void init() {
		//TODO: have the barcode scanner start listening when on this screen
		JFrame windowFrame = new JFrame();
		JPanel windowPanel = new JPanel();
		JButton addBookButton = new JButton();
		JButton addUserButton = new JButton();
		JButton addHoldButton = new JButton();
		JButton payFineButton = new JButton();
		JLabel testLabel = new JLabel();
		try {
			Book myBook = BookManager.getBookByIndex(0);
			testLabel.setText("TEST BOOK:\n"+myBook.getTitle()+"\n"+myBook.getAuthor()+"\n"+myBook.getIBSN());
		}
		catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		//TODO: this is turning into more of a main options screen. Maybe change the title and purpose?
		addBookButton.setText("Add a Book");
		addBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newBookAdditionWindow();
			}
		});
		addUserButton.setText("Add a User");
		addUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newUserAdditionWindow();
			}
		});
		addHoldButton.setText("Add a Hold");
		addHoldButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addHoldWindow();
			}
		});
		payFineButton.setText("Pay a Fine");
		payFineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				payFineWindow();
			}
		});
		windowPanel.add(testLabel);
		windowPanel.add(addBookButton);
		windowPanel.add(addUserButton);
		windowFrame.add(windowPanel);
		windowFrame.setSize(500,500);
		windowFrame.setVisible(true);
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setTitle("Library"); //TODO: make a better title
	
	}
	public static void newBookAdditionWindow() {
		JFrame addBookFrame = new JFrame();
		JPanel addBookPanel = new JPanel();
		JButton cancelButton = new JButton();
		JLabel bookName = new JLabel();
		bookName.setText("Book Name:");
		JTextField nameTextField = new JTextField();
		JLabel bookAuthor = new JLabel();
		bookAuthor.setText("Book Author:");
		JTextField authorTextField = new JTextField();
		JLabel bookBarcodeNumber = new JLabel();
		bookBarcodeNumber.setText("Barcode Number:");
		JTextField barcodeTextField = new JTextField();
		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addBookFrame.dispose();
			}
		});
		JButton saveButton = new JButton();
		saveButton.setText("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!nameTextField.getText().equals("")&&!authorTextField.getText().equals("")&&!barcodeTextField.getText().equals("")) {
					int barcode = Integer.parseInt(barcodeTextField.getText());
					Book book = new Book(barcode, nameTextField.getText(), authorTextField.getText());
					bookManager.add(book);
					init(); //TODO: remove 
				}
				else {
					//TODO: make an alert telling the user to complete all fields
					
				}
			}
		});
		addBookPanel.add(bookName);
		addBookPanel.add(nameTextField);
		addBookPanel.add(bookAuthor);
		addBookPanel.add(authorTextField);
		addBookPanel.add(bookBarcodeNumber);
		addBookPanel.add(barcodeTextField);
		addBookPanel.add(cancelButton);
		addBookPanel.add(saveButton);
		addBookFrame.add(addBookPanel);
		addBookFrame.setTitle("Add a Book");
		addBookFrame.setSize(500,500);
		addBookFrame.setVisible(true);
		addBookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void newUserAdditionWindow() {
		JFrame addUserFrame = new JFrame();
		JPanel addUserPanel = new JPanel();
		JLabel firstNameLabel = new JLabel();
		firstNameLabel.setText("First Name:");
		JTextField firstNameTextField = new JTextField();
		JLabel lastNameLabel = new JLabel();
		lastNameLabel.setText("Last Name:");
		JTextField lastNameTextField = new JTextField();
		JLabel idLabel = new JLabel();
		idLabel.setText("ID:");
		JTextField idTextField = new JTextField();
		JButton cancelButton = new JButton();
		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addUserFrame.dispose();
			}
		});
		JButton saveButton = new JButton();
		saveButton.setText("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!firstNameTextField.getText().equals("")&&!lastNameTextField.getText().equals("")&&!idTextField.getText().equals("")) {
					//TODO: have some way of asking the user if the new user is admin
					int ID = Integer.parseInt(idTextField.getText());
					User user = new User(ID, firstNameTextField.getText(), lastNameTextField.getText(), false);
					userManager.addUser(user);
				}
				else {
					//TODO: alert the user that the boxes are incomplete
				}
			}
		});
		addUserPanel.add(firstNameLabel);
		addUserPanel.add(firstNameTextField);
		addUserPanel.add(lastNameLabel);
		addUserPanel.add(lastNameTextField);
		addUserPanel.add(idLabel);
		addUserPanel.add(idTextField);
		addUserPanel.add(cancelButton);
		addUserPanel.add(saveButton);
		addUserFrame.add(addUserPanel);
		addUserFrame.setTitle("Add a User");
		addUserFrame.setSize(500,500);
		addUserFrame.setVisible(true);
		addUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void addHoldWindow() {
		
	}
	public static void payFineWindow() {
		
	}
	
}
