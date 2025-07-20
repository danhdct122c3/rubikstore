package RubikStorecom.example.demo.dto.request;

import RubikStorecom.example.demo.entity.Category;
import jakarta.persistence.*;
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
    Integer quantity;
    Integer price;
    Set<String> categories;
}
