package RubikStorecom.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data //tạo getter, setter, Tostring, hashcode, equal
@Builder // cho phép sử dụng setter trên 1 dòng không cần phải  tạo 1 object mới
@NoArgsConstructor // tạo contructor không tham số
@AllArgsConstructor// tạo constructor có tham  số
@FieldDefaults(level = AccessLevel.PRIVATE ) // gán AccessLevel mặc định không cần khai báo trước tên biến
public class CreationOrderRequest {
    String address; // Địa chỉ giao hàng (có thể khác user address)
    String note; // Ghi chú đơn hàng
    String paymentMethod; // COD, BANKING, etc.









}
