package RubikStorecom.example.demo.dto.response;

import RubikStorecom.example.demo.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data //tạo getter, setter, Tostring, hashcode, equal
@Builder // cho phép sử dụng setter trên 1 dòng không cần phải  tạo 1 object mới
@NoArgsConstructor // tạo contructor không tham số
@AllArgsConstructor// tạo constructor có tham  số
@FieldDefaults(level = AccessLevel.PRIVATE ) // gán AccessLevel mặc định không cần khai báo trước tên biến
public class OrderResponse {
    String id;
    String userId;
    LocalDateTime orderDate;
    BigDecimal totalAmount;
    OrderStatus status;
    String statusDescription; // Từ OrderStatus.getDescription()
    List<OrderItemResponse> items;
    Integer totalItems; // Tổng số sản phẩm
    LocalDateTime updatedAt;
}
