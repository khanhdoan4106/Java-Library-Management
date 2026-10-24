package main.model;

import java.time.LocalDate;

public class BorrowRecord {
    private User user;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate; // null nếu chưa trả

    public BorrowRecord(User user, Book book, LocalDate borrowDate) {
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(book.getBorrowDurationInDays());
        this.returnDate = null;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void markReturned(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isOverdue() {
        LocalDate checkDate = (returnDate != null) ? returnDate : LocalDate.now();
        return checkDate.isAfter(dueDate);
    }

    @Override
    public String toString() {
        String status = (returnDate == null) ? "Not returned" : "Returned on " + returnDate;
        return String.format("%s borrowed '%s' on %s (due %s) - %s",
                user.getName(), book.getTitle(), borrowDate, dueDate, status);
    }
}
