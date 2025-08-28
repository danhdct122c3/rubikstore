package RubikStorecom.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity

@Data
@Table(name = "order_item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)  // Sử dụng IDENTITY để tự động tăng ID
    String id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id") // ✅ Specify column name
    Orders order; // ✅ Đổi từ "orderId" thành "order"

    String productId;

    Integer quantity;

    @Column(name = "unit_price", precision = 10, scale = 2)
    BigDecimal price; // ✅ BigDecimal cho giá

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
}
