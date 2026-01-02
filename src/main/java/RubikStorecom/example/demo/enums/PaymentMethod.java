package RubikStorecom.example.demo.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public enum PaymentMethod
{
    CASH_ON_DELIVERY,
    CREDIT_CARD,
    BANK_TRANSFER,
    E_WALLET
}
