package RubikStorecom.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)  // Sử dụng IDENTITY để tự động tăng ID
    String id;
    String name;
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY) //Trong JPA (Hibernate), mappedBy cho phép bạn định nghĩa bên "inverse" (không sở hữu) trong một mối quan hệ

    Set<Product> products; // -> product là chủ  sở hữu

    // QUAN TRỌNG: Chỉ dùng ID trong hashCode và equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // CHỈ dùng ID, KHÔNG dùng products
    }
    }
