package com.sambproject;

import java.security.InvalidKeyException;
import java.util.regex.Pattern;

public class Book {
    private String name;
    private String author;
    private String genre;
    private String shelf;
    private int id;

    public Book (String name, String author, String genre, String shelf) throws InvalidKeyException {
        String shelfRegex = "^[A-ZА-ЯЁ]+\\d+$";
        if (!Pattern.matches(shelfRegex, shelf)) {
            throw new InvalidKeyException("Invalid shelf name");
        }
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.shelf = shelf;
    }

    public void showBook() {
        System.out.println("Книга №" + id + "\n" + name + "\nЖанр: " + genre + "\nАвтор: " +
                author + "\nПолка: " + shelf);
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public String getShelf() {
        return shelf;
    }

    public int getId() {
        return id;
    }

    public void changeName(String newName) {
        name = newName;
    }

    public void changeAuthor(String newAuthor) {
        author = newAuthor;
    }

    public void changeGenre(String newGenre) {
        genre = newGenre;
    }

    public void changeShelf(String newShelf) throws InvalidKeyException {
        String shelfRegex = "^[A-ZА-ЯЁ]+\\d+$";
        if (!Pattern.matches(shelfRegex, newShelf)) {
            throw new InvalidKeyException("Invalid shelf name");
        }
        shelf = newShelf;
    }

    public void changeId(int newId) {
        id = newId;
    }
}
