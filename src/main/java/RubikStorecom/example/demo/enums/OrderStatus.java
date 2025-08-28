package RubikStorecom.example.demo.enums;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public enum OrderStatus {
    PENDING("Đang chờ xử lý"),
    CONFIRMED("Đã xác nhận"),
    PROCESSING("Đang xử lý"),
    SHIPPED("Đã giao vận chuyển"),
    DELIVERED("Đã giao hàng"),
    CANCELLED("Đã hủy"),
    RETURNED("Đã trả lại");

    String description;
}