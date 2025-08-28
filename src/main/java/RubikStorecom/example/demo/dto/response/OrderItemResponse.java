package RubikStorecom.example.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data //tạo getter, setter, Tostring, hashcode, equal
@Builder // cho phép sử dụng setter trên 1 dòng không cần phải  tạo 1 object mới
@NoArgsConstructor // tạo contructor không tham số
@AllArgsConstructor// tạo constructor có tham  số
@FieldDefaults(level = AccessLevel.PRIVATE ) // gán AccessLevel mặc định không cần khai báo trước tên biến
public class OrderItemResponse {
    String id;
    String productId;
    Integer quantity;
    BigDecimal price; // Giá tại thời điểm order
    BigDecimal subtotal; // price * quantity
    LocalDateTime createdAt;
}
