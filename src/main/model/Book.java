package main.model;

public abstract class Book {
    private String id;
    private String title;
    private String author;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    // Mỗi loại sách có cách tính hạn mượn khác nhau -> thể hiện đa hình
    public abstract int getBorrowDurationInDays();

    public String getInfo() {
        return String.format("[%s] %s - %s", id, title, author);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        return id.equals(((Book) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
