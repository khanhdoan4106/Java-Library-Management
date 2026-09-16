package main.model;

public class PrintedBook extends Book {
    private int shelfNumber;
    private boolean available; // chỉ có 1 số bản, nên có thể hết để mượn

    public PrintedBook(String id, String title, String author, int shelfNumber) {
        super(id, title, author);
        this.shelfNumber = shelfNumber;
        this.available = true;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public int getBorrowDurationInDays() {
        return 14; // sách giấy mượn tối đa 14 ngày
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(" | Shelf: %d", shelfNumber);
    }
}
