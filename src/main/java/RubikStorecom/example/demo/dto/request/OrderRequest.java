package RubikStorecom.example.demo.dto.request;

import RubikStorecom.example.demo.enums.PaymentMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    String shippingAddress;
    String phoneNumber;
    String note;

    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;

}
