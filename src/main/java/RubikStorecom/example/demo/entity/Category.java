package RubikStorecom.example.demo.entity;

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
    @ManyToMany //Trong JPA (Hibernate), mappedBy cho phép bạn định nghĩa bên "inverse" (không sở hữu) trong một mối quan hệ
    Set<Product> products; // -> product là chủ  sở hữu


    }
