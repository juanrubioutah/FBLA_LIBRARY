import java.util.ArrayList;

public class BookManager {
	public static ArrayList<Book> books;
	public BookManager() {
		books = new ArrayList<Book>();
	}
	public static void add(Book book) {
		books.add(book);
	}
	public Book getBook(int IBSN) {
		for(int i = 0; i<books.size(); i++) {
			if(books.get(i).getIBSN()==IBSN) {
				return books.get(i);	
			}
		}
		return null; //book not found
	}
	public Book getBook(String query) { //search for books by title or author
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
		for(int i = 0; i<books.size(); i++) {
			if(books.get(i)==book){
				books.remove(i);
				return;
			}
		}
		System.out.println("Unable to find book to check out!");
	}
	public static void checkInBook(Book book) {
		books.add(book);
	}

}
