package test.java;

import com.sambproject.Book;
import com.sambproject.Library;
import org.junit.Test;

import java.security.InvalidKeyException;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private void assertBookEquals(Book expectedBook, Book actualBook) {
        assertEquals(expectedBook.getName(), actualBook.getName());
        assertEquals(expectedBook.getAuthor(), actualBook.getAuthor());
        assertEquals(expectedBook.getGenre(), actualBook.getGenre());
        assertEquals(expectedBook.getShelf(), actualBook.getShelf());
    }

    @Test
    public void ShouldAddBook() throws InvalidKeyException {
        Library testLib = new Library();
        int bookId = testLib.addBook("Война и мир. Том 1", "Л.Н. Толстой", "Роман", "A1");
        assertBookEquals(testLib.getBookById(bookId),
                new Book("Война и мир. Том 1", "Л.Н. Толстой", "Роман", "A1"));
    }

    @Test
    public void ShouldMoveBook() throws InvalidKeyException {
        Library testLib = new Library();
        int bookId = testLib.addBook("Война и мир. Том 1", "Л.Н. Толстой", "Роман", "A1");
        testLib.moveBook(bookId, "B2");
        assertBookEquals(testLib.getBookById(bookId),
                new Book("Война и мир. Том 1", "Л.Н. Толстой", "Роман", "B2"));
    }

    @Test
    public void ShouldChangeBook() throws InvalidKeyException {
        Library testLib = new Library();
        int bookId = testLib.addBook("Война и мир. Том 1", "Л.Н. Толстой", "Роман", "A1");
        testLib.changeBook(bookId, "New Name", null, "New Genre");
        assertBookEquals(testLib.getBookById(bookId),
                new Book("New Name", "Л.Н. Толстой", "New Genre", "A1"));
    }

    @Test
    public void ShouldThrowShelfException() {
        assertThrows(InvalidKeyException.class, () -> {
            Library testLib = new Library();
            int bookId = testLib.addBook("Война и мир. Том 1", "Л.Н. Толстой", "Роман", "A1");
            testLib.moveBook(bookId, "InvalidShelf228");
        });
    }

    @Test
    public void ShouldDeleteBook() {
        assertThrows(InvalidKeyException.class, () -> {
            Library testLib = new Library();
            int bookId = testLib.addBook("Война и мир. Том 1", "Л.Н. Толстой", "Роман", "A1");
            testLib.deleteBook(bookId);
            testLib.getBookById(bookId);
        });
    }

    @Test
    public void ShouldThrowsInvalidId() {
        assertThrows(InvalidKeyException.class, () -> {
            Library testLib = new Library();
            int bookId = testLib.addBook("Война и мир. Том 1", "Л.Н. Толстой", "Роман", "A1");
            testLib.getBookById(77);
        });
    }

    @Test
    public void ShouldFindBook() throws InvalidKeyException {
        Library testLib = new Library();
        int bookId1 = testLib.addBook("Война и мир. Том 1", "Л.Н. Толстой", "Роман", "A1");
        int bookId2 = testLib.addBook("Война и мир. Том 2", "Л.Н. Толстой", "Роман", "B2");
        int bookId3 = testLib.addBook("Война и мир. Том 3", "Л.Н. Толстой", "Роман", "B2");
        int bookId4 = testLib.addBook("Капитанская дочка", "А.Н. Пушкин", "Роман", "C1");
        assertEquals(3, testLib.findBook("Война и мир", null, null, null).size());
        assertEquals(2, testLib.findBook("Война и мир", null, null, "B2").size());
        assertEquals(1, testLib.findBook("тан", null, "Роман", null).size());
        assertEquals(4, testLib.findBook(null, null, "Роман", null).size());
        assertEquals(4, testLib.findBook(null, null, null, null).size());
        assertEquals(0, testLib.findBook("Мертвые души. 2 том", null, null, null).size());
    }
}
