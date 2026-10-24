package main.model;

public class Ebook extends Book {
    private String downloadLink;

    public Ebook(String id, String title, String author, String downloadLink) {
        super(id, title, author);
        this.downloadLink = downloadLink;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    @Override
    public int getBorrowDurationInDays() {
        return 7; // ebook mượn ngắn hơn vì không giới hạn số bản
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(" (Always available) | Link: %s", downloadLink);
    }
}
