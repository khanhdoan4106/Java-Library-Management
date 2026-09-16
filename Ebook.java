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
    public boolean isAvailable() {
        return true; // không giới hạn số bản, luôn có thể mượn
    }

    @Override
    public void setAvailable(boolean available) {
        // không làm gì cả -- ebook không có khái niệm "hết bản"
    }

    @Override
    public int getBorrowDurationInDays() {
        return 7; // ebook mượn ngắn hơn vì không giới hạn số bản
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(" | Link: %s", downloadLink);
    }
}
