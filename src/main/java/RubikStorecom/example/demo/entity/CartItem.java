package RubikStorecom.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity

@Data
@Table(name = "cart_items", // ✅ Đổi thành "cart_items"
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"cart_id", "product_id"}) // ✅ Tránh duplicate
        })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)  // Sử dụng IDENTITY để tự động tăng ID
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    Cart cart;

    @Column(name = "product_id")
    String productId;

    Integer quantity;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
}
