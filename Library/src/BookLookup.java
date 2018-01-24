
public class BookLookup { //looks up a book using a string query, which can either be the name or author of the book
	public Book lookUp(String query) {
		return BookManager.getBook(query);
	}
}
