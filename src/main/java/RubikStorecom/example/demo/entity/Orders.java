package RubikStorecom.example.demo.entity;


import RubikStorecom.example.demo.enums.OrderStatus;
import RubikStorecom.example.demo.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // ✅ Thêm table name
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)  // Sử dụng IDENTITY để tự động tăng ID
    String id;
    String userid;
    String shippingAddress;
    String PhoneNumber;
    String note;

    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;

    @CreationTimestamp
    LocalDateTime orderDate;

    @Column(name = "total_amount", precision = 15, scale = 2)
    BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderItem> items = new ArrayList<>();

    @UpdateTimestamp
    LocalDateTime updatedAt;

}
