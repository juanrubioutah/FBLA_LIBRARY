import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
		JPanel windowPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton addBookButton = new JButton();
		addBookButton.setFont(new Font("Arial", Font.BOLD, 15));
		addBookButton.setPreferredSize(new Dimension(175,75));
		JButton addUserButton = new JButton();
		addUserButton.setFont(new Font("Arial", Font.BOLD, 15));
		addUserButton.setPreferredSize(new Dimension(175,75));
		JButton addHoldButton = new JButton();
		addHoldButton.setFont(new Font("Arial", Font.BOLD, 15));
		addHoldButton.setPreferredSize(new Dimension(175,75));
		JButton payFineButton = new JButton();
		payFineButton.setFont(new Font("Arial", Font.BOLD, 15));
		payFineButton.setPreferredSize(new Dimension(175,75));
		JButton checkOutButton = new JButton();
		checkOutButton.setFont(new Font("Arial", Font.BOLD, 15));
		checkOutButton.setPreferredSize(new Dimension(175,75));
		JButton checkInButton = new JButton();
		checkInButton.setFont(new Font("Arial", Font.BOLD, 15));
		checkInButton.setPreferredSize(new Dimension(175,75));
		JButton reportCreationButton = new JButton();
		reportCreationButton.setFont(new Font("Arial", Font.BOLD, 15));
		reportCreationButton.setPreferredSize(new Dimension(175,75));
		JLabel testLabel = new JLabel();
		JLabel bookLookupLabel = new JLabel();
		bookLookupLabel.setFont(new Font("Arial", Font.BOLD, 25));
		bookLookupLabel.setText("Book Lookup:");
		JLabel barcodeNumLabel = new JLabel();
		barcodeNumLabel.setFont(new Font("Arial", Font.BOLD, 15));
		barcodeNumLabel.setText("Barcode Number");
		JButton searchButton = new JButton();
		searchButton.setFont(new Font("Arial", Font.BOLD, 15));
		searchButton.setPreferredSize(new Dimension(175,75));
		JTextField barcodeTextField = new JTextField(10);
		searchButton.setText("Find");
		searchButton.setFont(new Font("Arial", Font.BOLD, 15));
		searchButton.setPreferredSize(new Dimension(175,75));
		JLabel bookInfoLabel = new JLabel();
		JLabel userInfoLabel = new JLabel();
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
		reportCreationButton.setText("Create a Report");
		reportCreationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reportCreationWindow();
			}
		});
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long barcodeNum = Long.parseLong(barcodeTextField.getText().toString());
				try {
					Book book = bookManager.getBook(barcodeNum);
					if(book.isCheckedOut) {
						bookInfoLabel.setText(book.title+", "+book.author);
						bookInfoLabel.setFont(new Font("Arial", Font.BOLD, 15));
						bookInfoLabel.setPreferredSize(new Dimension(175,75));
						userInfoLabel.setText("Currently checked out by: "+book.getLastUser().firstName+" "+book.getLastUser().lastName+", "+book.getLastUser().getID());
						userInfoLabel.setFont(new Font("Arial", Font.BOLD, 15));
						userInfoLabel.setPreferredSize(new Dimension(175,75));
						c.fill = GridBagConstraints.HORIZONTAL;
						c.gridx = 1;
						c.gridy = 5;
						c.insets = new Insets(5,5,5,5);
						windowPanel.add(bookInfoLabel, c);
						c.fill = GridBagConstraints.HORIZONTAL;
						c.gridx = 1;
						c.gridy = 6;
						windowPanel.add(userInfoLabel, c);
						windowFrame.setVisible(false);
						windowFrame.setVisible(true);
					}
					else if(book.isOnHold){
						bookInfoLabel.setText(book.title+", "+book.author);
						bookInfoLabel.setFont(new Font("Arial", Font.BOLD, 9));
						bookInfoLabel.setPreferredSize(new Dimension(175,75));
						userInfoLabel.setText("Currently on hold by: "+book.holdUser.firstName+" "+book.holdUser.lastName+", "+book.holdUser.getID());
						userInfoLabel.setFont(new Font("Arial", Font.BOLD, 9));
						userInfoLabel.setPreferredSize(new Dimension(175,75));
						c.fill = GridBagConstraints.HORIZONTAL;
						c.gridx = 1;
						c.gridy = 5;
						c.insets = new Insets(5,5,5,5);
						windowPanel.add(bookInfoLabel, c);
						c.fill = GridBagConstraints.HORIZONTAL;
						c.gridx = 1;
						c.gridy = 6;
						windowPanel.add(userInfoLabel, c);
						windowFrame.setVisible(false);
						windowFrame.setVisible(true);
					}
					else {
						bookInfoLabel.setText(book.title+", "+book.author);
						bookInfoLabel.setFont(new Font("Arial", Font.BOLD, 9));
						bookInfoLabel.setPreferredSize(new Dimension(175,75));
						userInfoLabel.setText("Currently available.");
						userInfoLabel.setFont(new Font("Arial", Font.BOLD, 9));
						userInfoLabel.setPreferredSize(new Dimension(175,75));
						c.fill = GridBagConstraints.HORIZONTAL;
						c.gridx = 1;
						c.gridy = 5;
						c.insets = new Insets(5,5,5,5);
						windowPanel.add(bookInfoLabel, c);
						c.fill = GridBagConstraints.HORIZONTAL;
						c.gridx = 1;
						c.gridy = 6;
						windowPanel.add(userInfoLabel, c);
						windowFrame.setVisible(false);
						windowFrame.setVisible(true);
					}
				}catch(Exception f) {
					f.printStackTrace();
					bookInfoLabel.setText("Book not found!");
					bookInfoLabel.setFont(new Font("Arial", Font.BOLD, 9));
					bookInfoLabel.setPreferredSize(new Dimension(175,75));
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 1;
					c.gridy = 5;
					c.insets = new Insets(10,10,10,10);
					windowPanel.add(bookInfoLabel, c);
					windowFrame.setVisible(false);
					windowFrame.setVisible(true);
				}
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10,10,10,10);
		windowPanel.add(addBookButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		windowPanel.add(addUserButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		windowPanel.add(addHoldButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		windowPanel.add(payFineButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		windowPanel.add(checkOutButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		windowPanel.add(checkInButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		windowPanel.add(reportCreationButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		windowPanel.add(bookLookupLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		windowPanel.add(barcodeNumLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		windowPanel.add(barcodeTextField, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 4;
		windowPanel.add(searchButton, c);
		windowFrame.add(windowPanel);
		windowFrame.setSize(775, 1000);
		windowFrame.setVisible(true);
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setTitle("Library"); //TODO: make a better title
	}
	public static void newBookAdditionWindow() {
		JFrame addBookFrame = new JFrame();
		JPanel addBookPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton cancelButton = new JButton();
		cancelButton.setFont(new Font("Arial", Font.BOLD, 15));
		cancelButton.setPreferredSize(new Dimension(125,50));
		JLabel bookName = new JLabel();
		bookName.setFont(new Font("Arial", Font.BOLD, 15));
		bookName.setPreferredSize(new Dimension(125,50));
		bookName.setText("Book Name:");
		JTextField nameTextField = new JTextField(10);
		nameTextField.setFont(new Font("Arial", Font.BOLD, 15));
		nameTextField.setPreferredSize(new Dimension(125,50));
		JLabel bookAuthor = new JLabel();
		bookAuthor.setFont(new Font("Arial", Font.BOLD, 15));
		bookAuthor.setPreferredSize(new Dimension(125,50));
		bookAuthor.setText("Book Author:");
		JTextField authorTextField = new JTextField(10);
		authorTextField.setFont(new Font("Arial", Font.BOLD, 15));
		authorTextField.setPreferredSize(new Dimension(125,50));
		JLabel bookBarcodeNumber = new JLabel();
		bookBarcodeNumber.setFont(new Font("Arial", Font.BOLD, 15));
		bookBarcodeNumber.setPreferredSize(new Dimension(125,50));
		bookBarcodeNumber.setText("Barcode Number:");
		JTextField barcodeTextField = new JTextField(10);
		barcodeTextField.setFont(new Font("Arial", Font.BOLD, 15));
		barcodeTextField.setPreferredSize(new Dimension(125,50));
		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addBookFrame.dispose();
			}
		});
		JButton saveButton = new JButton();
		saveButton.setFont(new Font("Arial", Font.BOLD, 15));
		saveButton.setPreferredSize(new Dimension(125,50));
		saveButton.setText("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!nameTextField.getText().equals("")&&!authorTextField.getText().equals("")&&!barcodeTextField.getText().equals("")) {
					long barcode = Long.parseLong(barcodeTextField.getText());
					Book book = new Book(barcode, nameTextField.getText(), authorTextField.getText());
					bookManager.add(book);
					JOptionPane.showMessageDialog(addBookFrame, "Book Added Successfully!");
					addBookFrame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(addBookFrame, "Please Complete All Fields");
				}
			}
		});
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		addBookPanel.add(bookName, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		addBookPanel.add(nameTextField, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		addBookPanel.add(bookAuthor, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		addBookPanel.add(authorTextField, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		addBookPanel.add(bookBarcodeNumber, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		addBookPanel.add(barcodeTextField, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		addBookPanel.add(cancelButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		addBookPanel.add(saveButton, c);
		addBookFrame.add(addBookPanel);
		addBookFrame.setTitle("Add a Book");
		addBookFrame.setSize(750,750);
		addBookFrame.setVisible(true);
		addBookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void newUserAdditionWindow() {
		JFrame addUserFrame = new JFrame();
		JPanel addUserPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel firstNameLabel = new JLabel();
		firstNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
		firstNameLabel.setPreferredSize(new Dimension(125,50));
		firstNameLabel.setText("First Name:");
		JTextField firstNameTextField = new JTextField(10);
		firstNameTextField.setFont(new Font("Arial", Font.BOLD, 15));
		firstNameTextField.setPreferredSize(new Dimension(125,50));
		JLabel lastNameLabel = new JLabel();
		lastNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lastNameLabel.setPreferredSize(new Dimension(125,50));
		lastNameLabel.setText("Last Name:");
		JTextField lastNameTextField = new JTextField(10);
		lastNameTextField.setFont(new Font("Arial", Font.BOLD, 15));
		lastNameTextField.setPreferredSize(new Dimension(125,50));
		JLabel idLabel = new JLabel();
		idLabel.setFont(new Font("Arial", Font.BOLD, 15));
		idLabel.setPreferredSize(new Dimension(125,50));
		idLabel.setText("ID:");
		JTextField idTextField = new JTextField(10);
		idTextField.setFont(new Font("Arial", Font.BOLD, 15));
		idTextField.setPreferredSize(new Dimension(125,50));
		JButton cancelButton = new JButton();
		cancelButton.setFont(new Font("Arial", Font.BOLD, 15));
		cancelButton.setPreferredSize(new Dimension(125,50));
		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addUserFrame.dispose();
			}
		});
		JButton saveButton = new JButton();
		saveButton.setFont(new Font("Arial", Font.BOLD, 15));
		saveButton.setPreferredSize(new Dimension(125,50));
		saveButton.setText("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!firstNameTextField.getText().equals("")&&!lastNameTextField.getText().equals("")&&!idTextField.getText().equals("")) {
					//TODO: have some way of asking the user if the new user is admin
					int ID = Integer.parseInt(idTextField.getText());
					User user = new User(ID, firstNameTextField.getText(), lastNameTextField.getText(), false);
					userManager.addUser(user);
					JOptionPane.showMessageDialog(addUserFrame, "User Added Successfully!");
					addUserFrame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(addUserFrame, "Please Complete All Fields");
				}
			}
		});
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		addUserPanel.add(firstNameLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		addUserPanel.add(firstNameTextField, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		addUserPanel.add(lastNameLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		addUserPanel.add(lastNameTextField, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		addUserPanel.add(idLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		addUserPanel.add(idTextField, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		addUserPanel.add(cancelButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		addUserPanel.add(saveButton, c);
		addUserFrame.add(addUserPanel);
		addUserFrame.setTitle("Add a User");
		addUserFrame.setSize(750,750);
		addUserFrame.setVisible(true);
		addUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void addHoldWindow() {
		JFrame addHoldFrame = new JFrame();
		JPanel addHoldPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel bookLabel = new JLabel();
		bookLabel.setFont(new Font("Arial", Font.BOLD, 15));
		bookLabel.setPreferredSize(new Dimension(125,50));
		bookLabel.setText("Book Barcode:");
		JTextField bookTextField = new JTextField(10);
		bookTextField.setFont(new Font("Arial", Font.BOLD, 15));
		bookTextField.setPreferredSize(new Dimension(125,50));
		JLabel userLabel = new JLabel();
		userLabel.setFont(new Font("Arial", Font.BOLD, 15));
		userLabel.setPreferredSize(new Dimension(125,50));
		userLabel.setText("Hold User ID: ");
		JTextField userTextField = new JTextField(10);
		userTextField.setFont(new Font("Arial", Font.BOLD, 15));
		userTextField.setPreferredSize(new Dimension(125,50));
		JButton cancelButton = new JButton();
		cancelButton.setFont(new Font("Arial", Font.BOLD, 15));
		cancelButton.setPreferredSize(new Dimension(125,50));
		cancelButton.setText("Cancel");
		JButton holdButton = new JButton();
		holdButton.setFont(new Font("Arial", Font.BOLD, 15));
		holdButton.setPreferredSize(new Dimension(125,50));
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
				if(!userTextField.getText().equals("")&&!bookTextField.getText().equals("")) {
					try {
						User myUser = UserManager.getUserById(Integer.parseInt(userTextField.getText()));
						Book myBook = BookManager.getBook(Long.parseLong(bookTextField.getText()));
						myBook.hold(myUser);
						JOptionPane.showMessageDialog(addHoldFrame, "Hold Placed Successfully!");
						addHoldFrame.dispose();
					}
					catch(Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(addHoldFrame, "Error Placing Hold. Please check you user and barcode numbers.");
					}
				}
				else {
					JOptionPane.showMessageDialog(addHoldFrame, "Please Complete All Fields.");
				}
			}
		});
		addHoldFrame.setTitle("Place a Hold");
		addHoldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addHoldFrame.setSize(750, 750);
		addHoldFrame.setVisible(true);
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		addHoldPanel.add(bookLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		addHoldPanel.add(bookTextField, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		addHoldPanel.add(userLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		addHoldPanel.add(userTextField, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		addHoldPanel.add(cancelButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		addHoldPanel.add(holdButton, c);
		addHoldFrame.add(addHoldPanel);
	}
	public static void payFineWindow() {
		JFrame fineFrame = new JFrame();
		JPanel finePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel userLabel = new JLabel();
		userLabel.setFont(new Font("Arial", Font.BOLD, 15));
		userLabel.setPreferredSize(new Dimension(125,50));
		userLabel.setText("User ID: ");
		JTextField userTextField = new JTextField(10);
		userTextField.setFont(new Font("Arial", Font.BOLD, 15));
		userTextField.setPreferredSize(new Dimension(125,50));
		JButton fineLookupButton = new JButton();
		fineLookupButton.setFont(new Font("Arial", Font.BOLD, 15));
		fineLookupButton.setPreferredSize(new Dimension(125,50));
		fineLookupButton.setText("Look Up");
		JLabel fineLabel = new JLabel();
		fineLabel.setFont(new Font("Arial", Font.BOLD, 15));
		fineLabel.setPreferredSize(new Dimension(125,50));
		fineLabel.setText("Fine: ");
		fineLookupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					User myUser = UserManager.getUserById(Integer.parseInt(userTextField.getText()));
					double fineAmount = myUser.fineAmount;
					fineLabel.setText("Fine: $"+fineAmount);
				}catch(Exception f) {
					f.printStackTrace();
					JOptionPane.showMessageDialog(fineFrame, "User Not Found!");
				}
			}
		});
		JButton cancelButton = new JButton();
		cancelButton.setFont(new Font("Arial", Font.BOLD, 15));
		cancelButton.setPreferredSize(new Dimension(125,50));
		cancelButton.setText("Cancel");
		JButton payFinesButton = new JButton();
		payFinesButton.setFont(new Font("Arial", Font.BOLD, 15));
		payFinesButton.setPreferredSize(new Dimension(125,50));
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
				JOptionPane.showMessageDialog(fineFrame, "Fines Paid Successfully!");
				fineFrame.dispose();
			}
		});
		fineFrame.setTitle("Pay Fines");
		fineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fineFrame.setSize(750, 750);
		fineFrame.setVisible(true);
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		finePanel.add(userLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		finePanel.add(userTextField, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		finePanel.add(fineLookupButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		finePanel.add(fineLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		finePanel.add(cancelButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		finePanel.add(payFinesButton, c);
		fineFrame.add(finePanel);
	}
	public static void checkOutWindow() {
		JFrame checkOutFrame = new JFrame();
		JPanel checkOutPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel IDlabel = new JLabel();
		IDlabel.setFont(new Font("Arial", Font.BOLD, 15));
		IDlabel.setPreferredSize(new Dimension(125,50));
		JTextField IDtextField = new JTextField(10);
		IDtextField.setFont(new Font("Arial", Font.BOLD, 15));
		IDtextField.setPreferredSize(new Dimension(125,50));
		JLabel bookLabel = new JLabel();
		bookLabel.setFont(new Font("Arial", Font.BOLD, 15));
		bookLabel.setPreferredSize(new Dimension(125,50));
		JTextField bookTextField = new JTextField(10);
		bookTextField.setFont(new Font("Arial", Font.BOLD, 15));
		bookTextField.setPreferredSize(new Dimension(125,50));
		IDlabel.setText("User ID:");
		bookLabel.setText("Book Barcode:");
		JButton checkOutButton = new JButton();
		checkOutButton.setFont(new Font("Arial", Font.BOLD, 15));
		checkOutButton.setPreferredSize(new Dimension(125,50));
		JButton cancelButton = new JButton();
		cancelButton.setFont(new Font("Arial", Font.BOLD, 15));
		cancelButton.setPreferredSize(new Dimension(125,50));
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
		checkOutFrame.setSize(750, 750);
		checkOutFrame.setVisible(true);
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		checkOutPanel.add(IDlabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		checkOutPanel.add(IDtextField, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		checkOutPanel.add(bookLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		checkOutPanel.add(bookTextField, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		checkOutPanel.add(cancelButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		checkOutPanel.add(checkOutButton, c);
		checkOutFrame.add(checkOutPanel);
	}
	public static void checkInWindow() {
		JFrame checkInFrame = new JFrame();
		JPanel checkInPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel checkInLabel = new JLabel();
		checkInLabel.setFont(new Font("Arial", Font.BOLD, 15));
		checkInLabel.setPreferredSize(new Dimension(125,50));
		JTextField checkInTextField = new JTextField(10);
		checkInTextField.setFont(new Font("Arial", Font.BOLD, 15));
		checkInTextField.setPreferredSize(new Dimension(125,50));
		JButton cancelButton = new JButton();
		cancelButton.setFont(new Font("Arial", Font.BOLD, 15));
		cancelButton.setPreferredSize(new Dimension(125,50));
		JButton checkInButton = new JButton();
		checkInButton.setFont(new Font("Arial", Font.BOLD, 15));
		checkInButton.setPreferredSize(new Dimension(125,50));
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
					checkInFrame.dispose();
				}catch(Exception f) {
					f.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error Checking In");
				}
			}
			
		});
		checkInFrame.setTitle("Check In");
		checkInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		checkInFrame.setSize(750, 750);
		checkInFrame.setVisible(true);
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		checkInPanel.add(checkInLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		checkInPanel.add(checkInTextField, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		checkInPanel.add(cancelButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		checkInPanel.add(checkInButton, c);
		checkInFrame.add(checkInPanel);
	}
	
	public static void reportCreationWindow() {
		JFrame reportCreationFrame = new JFrame();
		JPanel reportCreationPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton bookReportCreationButton = new JButton();
		bookReportCreationButton.setFont(new Font("Arial", Font.BOLD, 15));
		bookReportCreationButton.setPreferredSize(new Dimension(125,50));
		JButton userReportCreationButton = new JButton();
		userReportCreationButton.setFont(new Font("Arial", Font.BOLD, 15));
		userReportCreationButton.setPreferredSize(new Dimension(125,50));
		bookReportCreationButton.setText("Book Report");
		userReportCreationButton.setText("User Report");
		bookReportCreationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportCreator creator = new ReportCreator("book");
				if(creator.create()) {
					JOptionPane.showMessageDialog(null, "Report Created Successfully!");
					reportCreationFrame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Error Generating Report");
					reportCreationFrame.dispose();
				}
			}
		});
		userReportCreationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportCreator creator = new ReportCreator("users");
				if(creator.create()) {
					JOptionPane.showMessageDialog(null, "Report Created Successfully");
					reportCreationFrame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Error Generating Report");
					reportCreationFrame.dispose();
				}
			}
		});
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		reportCreationPanel.add(userReportCreationButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		reportCreationPanel.add(bookReportCreationButton, c);
		reportCreationFrame.setTitle("Create a Report");
		reportCreationFrame.setVisible(true);
		reportCreationFrame.setSize(750, 750);
		reportCreationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		reportCreationFrame.add(reportCreationPanel);
	}
	
}
