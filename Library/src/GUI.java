import javax.swing.*;

import java.awt.GridBagLayout;
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
		JButton checkOutButton = new JButton();
		JButton checkInButton = new JButton();
		JLabel testLabel = new JLabel();
		JLabel bookLookupLabel = new JLabel();
		bookLookupLabel.setText("Book Lookup");
		JLabel barcodeNumLabel = new JLabel();
		barcodeNumLabel.setText("Barcode Number");
		JButton searchButton = new JButton();
		JTextField barcodeTextField = new JTextField(10);
		searchButton.setText("Find");
		JLabel bookInfoLabel = new JLabel();
		try {
			Book myBook = BookManager.getBookByIndex(0);
			testLabel.setText("TEST BOOK:\n"+myBook.getTitle()+"\n"+myBook.getAuthor()+"\n"+myBook.getISBN());
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
		checkOutButton.setText("Check Out");
		checkOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkOutWindow();
			}
		});
		checkInButton.setText("Check In");
		checkInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkInWindow();
			}
		});
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long barcodeNum = Long.parseLong(barcodeTextField.getText().toString());
				try {
					Book book = bookManager.getBook(barcodeNum);
					if(book.isCheckedOut) {
						bookInfoLabel.setText(book.title+", "+book.author+"\nCurrently checked out by: "+book.getLastUser().firstName+" "+book.getLastUser().lastName+", "+book.getLastUser().getID());
						windowPanel.add(bookInfoLabel);
						windowFrame.setVisible(false);
						windowFrame.setVisible(true);
					}
					else if(book.isOnHold){
						bookInfoLabel.setText(book.title+", "+book.author+"\nCurrently on hold by: "+book.holdUser.firstName+" "+book.holdUser.lastName+", "+book.holdUser.getID());
						windowPanel.add(bookInfoLabel);
						windowFrame.setVisible(false);
						windowFrame.setVisible(true);
					}
					else {
						bookInfoLabel.setText(book.title+", "+book.author+"\nCurrently available.");
						windowPanel.add(bookInfoLabel);
						windowFrame.setVisible(false);
						windowFrame.setVisible(true);
					}
				}catch(Exception f) {
					f.printStackTrace();
					bookInfoLabel.setText("Book not found!");
					windowPanel.add(bookInfoLabel);
					windowFrame.setVisible(false);
					windowFrame.setVisible(true);
				}
			}
		});
		
		windowPanel.add(testLabel);
		windowPanel.add(addBookButton);
		windowPanel.add(addUserButton);
		windowPanel.add(addHoldButton);
		windowPanel.add(payFineButton);
		windowPanel.add(checkOutButton);
		windowPanel.add(checkInButton);
		windowPanel.add(bookLookupLabel);
		windowPanel.add(barcodeNumLabel);
		windowPanel.add(barcodeTextField);
		windowPanel.add(searchButton);
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
		GridBagLayout gbc = new GridBagLayout();
		JLabel bookName = new JLabel();
		bookName.setText("Book Name:");
		JTextField nameTextField = new JTextField(10);
		JLabel bookAuthor = new JLabel();
		bookAuthor.setText("Book Author:");
		JTextField authorTextField = new JTextField(10);
		JLabel bookBarcodeNumber = new JLabel();
		bookBarcodeNumber.setText("Barcode Number:");
		JTextField barcodeTextField = new JTextField(10);
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
					long barcode = Long.parseLong(barcodeTextField.getText());
					Book book = new Book(barcode, nameTextField.getText(), authorTextField.getText());
					bookManager.add(book);
					addBookFrame.dispose();
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
		JTextField firstNameTextField = new JTextField(10);
		JLabel lastNameLabel = new JLabel();
		lastNameLabel.setText("Last Name:");
		JTextField lastNameTextField = new JTextField(10);
		JLabel idLabel = new JLabel();
		idLabel.setText("ID:");
		JTextField idTextField = new JTextField(10);
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
					addUserFrame.dispose();
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
		JFrame addHoldFrame = new JFrame();
		JPanel addHoldPanel = new JPanel();
		JLabel bookLabel = new JLabel();
		bookLabel.setText("Book Barcode Number: ");
		JTextField bookTextField = new JTextField(10);
		JLabel userLabel = new JLabel();
		userLabel.setText("Hold User ID: ");
		JTextField userTextField = new JTextField(10);
		JButton cancelButton = new JButton();
		cancelButton.setText("Cancel");
		JButton holdButton = new JButton();
		holdButton.setText("Place Hold");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addHoldFrame.dispose();
			}
		});
		holdButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User myUser = UserManager.getUserById(Integer.parseInt(userTextField.getText()));
				Book myBook = BookManager.getBook(Long.parseLong(bookTextField.getText()));
				myBook.hold(myUser);
				addHoldFrame.dispose();
			}
		});
		addHoldFrame.setTitle("Place a Hold");
		addHoldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addHoldFrame.setSize(500, 500);
		addHoldFrame.setVisible(true);
		addHoldPanel.add(bookLabel);
		addHoldPanel.add(bookTextField);
		addHoldPanel.add(userLabel);
		addHoldPanel.add(userTextField);
		addHoldPanel.add(cancelButton);
		addHoldPanel.add(holdButton);
		addHoldFrame.add(addHoldPanel);
	}
	public static void payFineWindow() {
		JFrame fineFrame = new JFrame();
		JPanel finePanel = new JPanel();
		JLabel userLabel = new JLabel();
		userLabel.setText("User ID: ");
		JTextField userTextField = new JTextField(10);
		JButton fineLookupButton = new JButton();
		fineLookupButton.setText("Look Up Fines");
		JLabel fineLabel = new JLabel();
		fineLabel.setText("Fine: ");
		fineLookupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User myUser = UserManager.getUserById(Integer.parseInt(userTextField.getText()));
				double fineAmount = myUser.fineAmount;
				fineLabel.setText("Fine: "+fineAmount);
			}
		});
		JButton cancelButton = new JButton();
		cancelButton.setText("Cancel");
		JButton payFinesButton = new JButton();
		payFinesButton.setText("Pay Fines");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fineFrame.dispose();
			}
		});
		payFinesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User myUser = UserManager.getUserById(Integer.parseInt(userTextField.getText()));
				myUser.payFine(myUser.fineAmount);
				fineFrame.dispose();
			}
		});
		fineFrame.setTitle("Pay Fines");
		fineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fineFrame.setSize(500, 500);
		fineFrame.setVisible(true);
		finePanel.add(userLabel);
		finePanel.add(userTextField);
		finePanel.add(fineLookupButton);
		finePanel.add(fineLabel);
		finePanel.add(cancelButton);
		finePanel.add(payFinesButton);
		fineFrame.add(finePanel);
	}
	public static void checkOutWindow() {
		JFrame checkOutFrame = new JFrame();
		JPanel checkOutPanel = new JPanel();
		JLabel IDlabel = new JLabel();
		JTextField IDtextField = new JTextField(10);
		JLabel bookLabel = new JLabel();
		JTextField bookTextField = new JTextField(10);
		IDlabel.setText("User ID:");
		bookLabel.setText("Book Barcode:");
		JButton checkOutButton = new JButton();
		JButton cancelButton = new JButton();
		checkOutButton.setText("Check Out");
		cancelButton.setText("Cancel");
		checkOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!(bookManager.getBook(Long.parseLong(bookTextField.getText().toString()))==null)){
						Book book = bookManager.getBook(Long.parseLong(bookTextField.getText().toString()));
						User.checkOut(userManager.getUserById(Integer.parseInt(IDtextField.getText().toString())), book);
						JOptionPane.showMessageDialog(null, "Book checked out successfully!");
						checkOutFrame.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Book not found!");
					}
				}catch(Exception f) {
					JOptionPane.showMessageDialog(null, "Input error. Try again.");
					f.printStackTrace();
				}
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkOutFrame.dispose();
			}
		});
		checkOutFrame.setTitle("Check Out");
		checkOutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		checkOutFrame.setSize(500, 500);
		checkOutFrame.setVisible(true);
		checkOutPanel.add(IDlabel);
		checkOutPanel.add(IDtextField);
		checkOutPanel.add(bookLabel);
		checkOutPanel.add(bookTextField);
		checkOutPanel.add(cancelButton);
		checkOutPanel.add(checkOutButton);
		checkOutFrame.add(checkOutPanel);
	}
	public static void checkInWindow() {
		JFrame checkInFrame = new JFrame();
		JPanel checkInPanel = new JPanel();
		JLabel checkInLabel = new JLabel();
		JTextField checkInTextField = new JTextField();
		JButton cancelButton = new JButton();
		JButton checkInButton = new JButton();
		checkInLabel.setText("Book Barcode:");
		cancelButton.setText("Cancel");
		checkInButton.setText("Check In");
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				checkInFrame.dispose();
			}
			
		});
		checkInButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Book book = bookManager.getBook(Long.parseLong(checkInTextField.getText().toString()));
					User checkInUser = book.getLastUser();
					checkInUser.checkIn(book);
					JOptionPane.showMessageDialog(null, "Book Returned Successfully!");
				}catch(Exception f) {
					f.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error Checking In");
				}
			}
			
		});
		checkInFrame.setTitle("Check In");
		checkInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		checkInFrame.setSize(500, 500);
		checkInFrame.setVisible(true);
		checkInPanel.add(checkInLabel);
		checkInPanel.add(checkInTextField);
		checkInPanel.add(cancelButton);
		checkInPanel.add(checkInButton);
		checkInFrame.add(checkInPanel);
	}
	
}
