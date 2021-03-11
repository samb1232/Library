package com.sambproject;

import java.security.InvalidKeyException;
import java.util.HashMap;

public class Library {
/**    Вариант 21 -- библиотека
 *
 * Хранит информацию о книгах и позволяет их искать. Для каждой книги хранится название, автор и жанр, а также
 * код полки, на которой она лежит (например, А3 или Г4.
 * Вы можете сами придумать реалистичную систему кодирования полок).
 *
 * Операции: конструктор, добавить/удалить книгу, изменить существующую книгу,
 * переместить книгу на другую полку, поиск книг по разным признакам (по автору, по названию,
 * по словам из названия, по жанру, по коду полки, по комбинации этих признаков).
 */


    private final HashMap<Integer, Book> lib = new HashMap<>();

    private int id = 0;

    public Library(){}
    public Library(Book[] books) {
        for (Book book : books) {
            addBook(book);
        }
    }

    public int addBook(Book book) {
        /* Добавляет книгу в библиотеку */
        // Возвращает int для того чтобы после того как добавить книгу, не искать её id отдельно
        if (book == null) {
            throw new IllegalArgumentException("Book is not defined");
        }
        lib.put(id, book);
        book.changeId(id);
        id++;
        return book.getId();
    }

    public int addBook(String name, String author, String genre, String shelf) throws InvalidKeyException {
        /* Создает и добавляет книгу в библиотеку */
        return addBook(new Book(name, author, genre, shelf));
    }

    public void moveBook(int id, String newShelf) throws InvalidKeyException {
        /* Перемещает книгу на другую полку */
        getBookById(id).changeShelf(newShelf);
    }

    public void changeBook(int id, String newName, String newAuthor, String newGenre) throws InvalidKeyException {
        /* Изменяет описание книги */
        Book bookToChange = getBookById(id);
        if (newName != null) bookToChange.changeName(newName);
        if (newAuthor != null) bookToChange.changeAuthor(newAuthor);
        if (newGenre != null) bookToChange.changeGenre(newGenre);
    }

    public void deleteBook(int id) throws InvalidKeyException {
        /* Удаляет книгу по id */
        if (!lib.containsKey(id)) {
            throw new InvalidKeyException("Book with id " + id + " is not found");
        }
        System.out.println("Книга \"" + getBookById(id).getName() + "\" удалена\n");
        lib.remove(id);
    }

    public void showAllBooks() {
        /* Отображает все книги в библиотеке */
        for (int key : lib.keySet()) {
            lib.get(key).showBook();
            System.out.println();
        }
    }

    public int getIdByBook(String name, String author, String genre, String shelf) throws InvalidKeyException {
        /* Возвращает id книги */
        HashMap<Integer, Book> foundedBooks = findBook(name, author, genre, shelf);
        if (foundedBooks.size() != 1) {
            throw new InvalidKeyException("Book with such properties is not found");
        }
        return foundedBooks.keySet().iterator().next();
    }

    public Book getBookById(int id) throws InvalidKeyException {
        if (!lib.containsKey(id)) {
            throw new InvalidKeyException("Book with id " + id + " is not found");
        }
        return lib.get(id);
    }

    public HashMap<Integer, Book> findBook (String name, String author, String genre, String shelf) {
        //Возвращает ассациативный массив книг по выбранным критериям
        return findByName(name, findByAuthor(author, findByGenre(genre, findByShelf(shelf, lib))));
    }

    private HashMap<Integer, Book> findByAuthor(String author, HashMap<Integer, Book> lib) {
        if (author == null) return lib;
        HashMap<Integer, Book> localLib = new HashMap<>();
        for (int keyBook : lib.keySet()) {
            Book bookInstance = lib.get(keyBook);
            if (bookInstance.getAuthor().equals(author)) {
                localLib.put(bookInstance.getId(), bookInstance);
            }
        }
        return localLib;
    }

    private HashMap<Integer, Book> findByGenre(String genre, HashMap<Integer, Book> lib) {
        if (genre == null) return lib;
        HashMap<Integer, Book> localLib = new HashMap<>();
        for (int keyBook : lib.keySet()) {
            Book bookInstance = lib.get(keyBook);
            if (bookInstance.getGenre().equals(genre)) {
                localLib.put(bookInstance.getId(), bookInstance);
            }
        }
        return localLib;
    }
    private HashMap<Integer, Book> findByShelf(String shelf, HashMap<Integer, Book> lib) {
        if (shelf == null) return lib;
        HashMap<Integer, Book> localLib = new HashMap<>();
        for (int keyBook : lib.keySet()) {
            Book bookInstance = lib.get(keyBook);
            if (bookInstance.getShelf().equals(shelf)) {
                localLib.put(bookInstance.getId(), bookInstance);
            }
        }
        return localLib;
    }

    private HashMap<Integer, Book> findByName(String name, HashMap<Integer, Book> lib) {
        if (name == null) return lib;
        HashMap<Integer, Book> localLib = new HashMap<>();
        for (int keyBook : lib.keySet()) {
            Book bookInstance = lib.get(keyBook);
            if (bookInstance.getName().contains(name)) {
                localLib.put(bookInstance.getId(), bookInstance);
            }
        }
        return localLib;
    }

}
