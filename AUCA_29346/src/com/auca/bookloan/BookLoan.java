package com.auca.bookloan;

import com.auca.exceptions.ValidationException;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.librarybook.LibraryBook;
import com.auca.student.Student;

import java.time.LocalDate;

public class BookLoan implements Identifiable, Registrable {
    private String loanId;
    private Student student;
    private LibraryBook book;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;

    public BookLoan(String loanId, Student student, LibraryBook book,
                   LocalDate borrowedDate, LocalDate dueDate, LocalDate returnedDate) {
        this.loanId = loanId;
        this.student = student;
        this.book = book;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnedDate = returnedDate;
    }

    @Override
    public String getId() {
        return loanId;
    }

    @Override
    public String getDisplayName() {
        return student == null ? "Unknown student" : student.getDisplayName();
    }

    @Override
    public boolean isActive() {
        return returnedDate == null;
    }

    @Override
    public void validate() throws ValidationException {
        if (loanId == null || loanId.isBlank()) {
            throw new ValidationException("Loan ID is required.");
        }
        if (student == null) {
            throw new ValidationException("Student is required.");
        }
        if (book == null) {
            throw new ValidationException("Book is required.");
        }
        if (borrowedDate == null || dueDate == null) {
            throw new ValidationException("Borrowed date and due date are required.");
        }
        if (dueDate.isBefore(borrowedDate) || dueDate.isEqual(borrowedDate)) {
            throw new ValidationException("Due date must be after borrowed date.");
        }
        if (returnedDate != null && (returnedDate.isBefore(borrowedDate) || returnedDate.isAfter(dueDate.plusDays(30)))) {
            throw new ValidationException("Returned date must not be before borrowed date or too far after due date.");
        }
    }

    @Override
    public void printDetails() {
        System.out.println("BookLoan: " + student.getDisplayName() + " borrowed " + book.getTitle());
    }

    @Override
    public String getRegistrationSummary() {
        return "BookLoan: " + student.getDisplayName() + " borrowed " + book.getTitle();
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LibraryBook getBook() {
        return book;
    }

    public void setBook(LibraryBook book) {
        this.book = book;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }
}
