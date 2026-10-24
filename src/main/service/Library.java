package main.service;

import main.model.Book;
import main.model.Borrowable;
import main.model.BorrowRecord;
import main.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<User> users;
    private List<BorrowRecord> records;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.records = new ArrayList<>();
    }

    public boolean addBook(Book book) {
        if (findBookById(book.getId()) != null) {
            System.out.println("ID sách '" + book.getId() + "' đã tồn tại.");
            return false;
        }
        books.add(book);
        return true;
    }

    public boolean addUser(User user) {
        if (findUserById(user.getId()) != null) {
            System.out.println("ID user '" + user.getId() + "' đã tồn tại.");
            return false;
        }
        users.add(user);
        return true;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public boolean removeBook(Book book) {
        boolean currentlyBorrowed = records.stream()
                .anyMatch(r -> r.getBook().equals(book) && r.getReturnDate() == null);
        if (currentlyBorrowed) {
            System.out.println("Không thể xóa '" + book.getTitle() + "' vì đang được mượn.");
            return false;
        }
        return books.remove(book);
    }

    public Book findBookById(String id) {
        for (Book b : books) {
            if (b.getId().equals(id)) return b;
        }
        return null;
    }

    public User findUserById(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) return u;
        }
        return null;
    }

    public boolean borrowBook(User user, Book book) {
        if (book instanceof Borrowable) {
            Borrowable b = (Borrowable) book;
            if (!b.isAvailable()) {
                System.out.println("'" + book.getTitle() + "' hiện không có sẵn để mượn.");
                return false;
            }
            b.setAvailable(false);
        }
        BorrowRecord record = new BorrowRecord(user, book, LocalDate.now());
        user.addRecord(record);
        records.add(record);
        System.out.println(user.getName() + " đã mượn '" + book.getTitle() + "', hạn trả: " + record.getDueDate());
        return true;
    }

    public boolean returnBook(User user, Book book) {
        for (BorrowRecord record : records) {
            if (record.getUser().equals(user) && record.getBook().equals(book) && record.getReturnDate() == null) {
                record.markReturned(LocalDate.now());
                if (book instanceof Borrowable) {
                    ((Borrowable) book).setAvailable(true);
                }
                boolean late = record.isOverdue();
                System.out.println(user.getName() + " đã trả '" + book.getTitle() + "'"
                        + (late ? " (TRỄ HẠN)" : " (đúng hạn)"));
                return true;
            }
        }
        System.out.println(user.getName() + " không có bản ghi mượn sách này.");
        return false;
    }

    public List<Book> searchByTitle(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    public void printAllBooks() {
        for (Book b : books) {
            System.out.println(b.getInfo());
        }
    }

    public void printOverdueRecords() {
        for (BorrowRecord r : records) {
            if (r.getReturnDate() == null && r.isOverdue()) {
                System.out.println("QUÁ HẠN: " + r);
            }
        }
    }
}
