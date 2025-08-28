package RubikStorecom.example.demo.entity;


import RubikStorecom.example.demo.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts") // ✅ Thêm table name
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)  // Sử dụng IDENTITY để tự động tăng ID
    String id;
    @OneToOne(fetch = FetchType.LAZY) // ✅ Thêm relationship
    @JoinColumn(name = "user_id", unique = true)
    User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true) // ✅ Thêm relationship
    @Builder.Default
    List<CartItem> items = new ArrayList<>();

    @Column(precision = 10, scale=2)
    BigDecimal totalAmount= BigDecimal.ZERO;
    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;



}
