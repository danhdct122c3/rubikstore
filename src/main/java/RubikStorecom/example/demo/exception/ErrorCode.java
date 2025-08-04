package RubikStorecom.example.demo.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized exception"),
    USERNAME_INVALID(1003,"Username at must be at least 5 characters"),
    PASSWORD_INVALID(1004,"Password at must be at least 6 characters"),
    PHONENUMBER_INVALID(1005,"Phone number invalid"),
    INVALID_KEY(1006,"Invalid message key"),
    NOT_NULL(1007,"Please fill in all fields"),
    QUANTITY_INVALID(1008,"Quantity must be at least 1"),
    PRICE_INVALID(1009,"Price must be Positive"),
    USER_EXISTED(1001,"User existed"),
    INVALID_DOB(1010,"Invalid Dob");
    int code;
    String message;
}
