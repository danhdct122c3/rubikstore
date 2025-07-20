package RubikStorecom.example.demo.dto.response;

import RubikStorecom.example.demo.entity.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse{
    String id;
    String name;
    Set<Product> products;
}
