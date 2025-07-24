package RubikStorecom.example.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSummary {
    String id;
    String productName;
    Integer price;
    // Không có categories để tránh circular
}