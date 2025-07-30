package RubikStorecom.example.demo.dto.request;

import RubikStorecom.example.demo.entity.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    String id;
    String productName;
    String description;
    @NotNull(message = "số lượng không được để trống")// not blank chỉ dùng được cho string
    @Min(value=1, message = "số lượng ít nhất là 1 ")
    Integer quantity;
    @NotNull(message = "giá không được để trống")
    @Min(value=1, message = "số giá ít nhất là 1 ")
    Integer price;
    Set<String> categories;
}
