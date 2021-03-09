package com.sambproject;


import java.security.InvalidKeyException;

public class Main {
    public static void main(String[] args) throws InvalidKeyException {
        Book[] warAndPiece = new Book[1];
        warAndPiece[0] = new Book("Война и мир. Том 1", "Л.Н. Толстой", "Роман", "A1");
        Library libOne = new Library(warAndPiece);
        libOne.addBook(new Book("Война и мир. Том 2", "Л.Н. Толстой", "Роман", "C1"));
        libOne.addBook(new Book ("Война и мир. Том 3", "Л.Н. Толстой", "Роман", "АА4"));
        libOne.deleteBook(1);
        libOne.addBook(new Book ("Война и мир. Том 4", "Л.Н. Толстой", "Роман", "В1"));
        libOne.moveBook(libOne.getIdByBook("Война и миjgjkgiuр. Том 4", null, null, null), "C1");

        libOne.showAllBooks();
    }
}
