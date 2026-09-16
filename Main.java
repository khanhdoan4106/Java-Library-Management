package main;

import main.model.Book;
import main.model.Ebook;
import main.model.PrintedBook;
import main.model.User;
import main.service.Library;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedData(); // vài dữ liệu mẫu để test nhanh, không bắt buộc

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Chọn: ");
            switch (choice) {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> searchBook();
                case 4 -> library.printAllBooks();
                case 5 -> addUser();
                case 6 -> showUsers();
                case 7 -> borrowBook();
                case 8 -> returnBook();
                case 9 -> library.printOverdueRecords();
                case 0 -> running = false;
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
            System.out.println();
        }
        System.out.println("Tạm biệt!");
    }

    private static void printMenu() {
        System.out.println("===== LIBRARY MANAGEMENT =====");
        System.out.println("1. Add book");
        System.out.println("2. Remove book");
        System.out.println("3. Search book");
        System.out.println("4. Show all books");
        System.out.println("5. Add user");
        System.out.println("6. Show users");
        System.out.println("7. Borrow book");
        System.out.println("8. Return book");
        System.out.println("9. Show overdue books");
        System.out.println("0. Exit");
    }

    private static void addBook() {
        System.out.print("Loại sách (1-Printed / 2-Ebook): ");
        int type = readInt("");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Tên sách: ");
        String title = scanner.nextLine();
        System.out.print("Tác giả: ");
        String author = scanner.nextLine();

        if (type == 1) {
            System.out.print("Số kệ: ");
            int shelf = readInt("");
            library.addBook(new PrintedBook(id, title, author, shelf));
        } else {
            System.out.print("Link tải: ");
            String link = scanner.nextLine();
            library.addBook(new Ebook(id, title, author, link));
        }
        System.out.println("Đã thêm sách.");
    }

    private static void removeBook() {
        System.out.print("Nhập ID sách cần xóa: ");
        String id = scanner.nextLine();
        Book book = library.findBookById(id);
        if (book == null) {
            System.out.println("Không tìm thấy sách.");
            return;
        }
        library.removeBook(book);
        System.out.println("Đã xóa sách.");
    }

    private static void searchBook() {
        System.out.print("Nhập từ khóa: ");
        String keyword = scanner.nextLine();
        List<Book> results = library.searchByTitle(keyword);
        if (results.isEmpty()) {
            System.out.println("Không tìm thấy sách nào.");
        } else {
            results.forEach(b -> System.out.println(b.getInfo()));
        }
    }

    private static void addUser() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Tên: ");
        String name = scanner.nextLine();
        library.addUser(new User(id, name));
        System.out.println("Đã thêm user.");
    }

    private static void showUsers() {
        for (User u : library.getUsers()) {
            System.out.println(u.getId() + " - " + u.getName()
                    + " (đang mượn: " + u.getCurrentlyBorrowedCount() + ")");
        }
    }

    private static void borrowBook() {
        System.out.print("ID user: ");
        User user = library.findUserById(scanner.nextLine());
        System.out.print("ID sách: ");
        Book book = library.findBookById(scanner.nextLine());
        if (user == null || book == null) {
            System.out.println("Không tìm thấy user hoặc sách.");
            return;
        }
        library.borrowBook(user, book);
    }

    private static void returnBook() {
        System.out.print("ID user: ");
        User user = library.findUserById(scanner.nextLine());
        System.out.print("ID sách: ");
        Book book = library.findBookById(scanner.nextLine());
        if (user == null || book == null) {
            System.out.println("Không tìm thấy user hoặc sách.");
            return;
        }
        library.returnBook(user, book);
    }

    private static int readInt(String prompt) {
        if (!prompt.isEmpty()) System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Vui lòng nhập số: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline còn sót lại
        return value;
    }

    private static void seedData() {
        library.addBook(new PrintedBook("B001", "Clean Code", "Robert C. Martin", 12));
        library.addBook(new Ebook("B002", "Effective Java", "Joshua Bloch", "https://example.com/effective-java"));
        library.addUser(new User("U001", "Nguyen Van A"));
    }
}
