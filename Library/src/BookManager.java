import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BookManager {
	public static ArrayList<Book> books;
	public BookManager() {
		books = new ArrayList<Book>();
	}
	public static void add(Book book) {
		books.add(book);
	}
	public static Book getBook(long ISBN) {
		for(int i = 0; i<books.size(); i++) {
			if(books.get(i).getISBN()==ISBN) {
				return books.get(i);
			}
		}
		try {
			throw new FileNotFoundException();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; //book not found
	}
	public static Book getBook(String query) { //search for books by title or author
		ArrayList<Book> searchResults = new ArrayList<Book>();
		for(int i = 0; i<books.size(); i++) {
			if(books.get(i).getTitle().contains(query)) {
				searchResults.add(books.get(i));
			}
			if(books.get(i).getAuthor().contains(query)) {
				searchResults.add(books.get(i));
			}
		}
		return null; //no results
	}
	public static Book getBookByIndex(int index) {
		return books.get(index);
	}
	public static void checkOutBook(Book book) {
		if(book.isCheckedOut==false) {
			book.isCheckedOut = true;
		}
		else {
			System.out.println("Unable to find book to check out!");
		}
	}
	public static void checkInBook(Book book) {
		books.add(book);
	}

}
