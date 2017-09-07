import java.util.ArrayList;

public class BookManager {
	public ArrayList<Book> books;
	public BookManager() {
		books = new ArrayList<Book>();
	}
	public void add(Book book) {
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
}
