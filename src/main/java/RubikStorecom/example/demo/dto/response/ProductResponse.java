package RubikStorecom.example.demo.dto.response;

import RubikStorecom.example.demo.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String id;
    String productName;
    String description;
    int quantity;
    int price;
    LocalDate created;
    HashSet<CategoryResponse> categories;
}
