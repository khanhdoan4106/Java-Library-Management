package main.model;

// Chỉ những loại sách có số bản giới hạn mới cần theo dõi trạng thái còn/hết.
// Ebook không implement interface này vì luôn có sẵn (Interface Segregation).
public interface Borrowable {
    boolean isAvailable();

    void setAvailable(boolean available);
}
