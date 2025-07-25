package RubikStorecom.example.demo.dto.response;

import RubikStorecom.example.demo.entity.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse{
    String id;
    String name;
    List<ProductSummary> products;
}
