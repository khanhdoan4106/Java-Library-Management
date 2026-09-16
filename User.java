package main.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private List<BorrowRecord> borrowHistory;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowHistory = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<BorrowRecord> getBorrowHistory() {
        return borrowHistory;
    }

    public void addRecord(BorrowRecord record) {
        borrowHistory.add(record);
    }

    // Đếm số sách đang mượn (chưa trả)
    public long getCurrentlyBorrowedCount() {
        return borrowHistory.stream()
                .filter(r -> r.getReturnDate() == null)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        return id.equals(((User) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
