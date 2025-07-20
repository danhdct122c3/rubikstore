package RubikStorecom.example.demo.dto.request;

import RubikStorecom.example.demo.entity.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest{
    String name;
    Set<Product> products;
}
