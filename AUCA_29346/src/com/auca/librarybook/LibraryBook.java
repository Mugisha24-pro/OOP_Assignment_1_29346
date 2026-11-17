package com.auca.librarybook;

import com.auca.exceptions.ValidationException;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;

public class LibraryBook implements Identifiable, Registrable {
    private String bookId;
    private String isbn;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;

    public LibraryBook(String bookId, String isbn, String title, String author,
                      int totalCopies, int availableCopies) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    @Override
    public String getId() {
        return bookId;
    }

    @Override
    public String getDisplayName() {
        return title;
    }

    @Override
    public boolean isActive() {
        return bookId != null && !bookId.isBlank();
    }

    @Override
    public void validate() throws ValidationException {
        if (bookId == null || bookId.isBlank()) {
            throw new ValidationException("Book ID is required.");
        }
        if (isbn == null || isbn.isBlank()) {
            throw new ValidationException("ISBN is required.");
        }
        if (title == null || title.trim().length() < 2 || title.trim().length() > 60) {
            throw new ValidationException("Book title must be between 2 and 60 characters.");
        }
        if (author == null || author.trim().length() < 2 || author.trim().length() > 60) {
            throw new ValidationException("Author name must be between 2 and 60 characters.");
        }
        if (totalCopies < 0) {
            throw new ValidationException("Total copies must be nonnegative.");
        }
        if (availableCopies < 0 || availableCopies > totalCopies) {
            throw new ValidationException("Available copies must be between 0 and total copies.");
        }
    }

    @Override
    public void printDetails() {
        System.out.println("LibraryBook: " + title + " by " + author + " (available " + availableCopies + ")");
    }

    @Override
    public String getRegistrationSummary() {
        return "LibraryBook: " + title + " (" + availableCopies + " available)";
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}
