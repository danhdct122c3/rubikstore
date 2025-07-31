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
    @NotNull(message = "NOT_NULL")// not blank chỉ dùng được cho string
    @Min(value=1, message = "QUANTITY_INVALID")
    Integer quantity;
    @NotNull(message = "NOT_NULL")
    @Min(value=1, message = "PRICE_INVALID")
    Integer price;
    Set<String> categories;
}
