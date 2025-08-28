package RubikStorecom.example.demo.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(1012,"Unauthenticated",HttpStatus.UNAUTHORIZED),
    USERNAME_INVALID(1003,"Username at must be at least 5 characters",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004,"Password at must be at least 6 characters",HttpStatus.BAD_REQUEST),
    PHONENUMBER_INVALID(1005,"Phone number invalid",HttpStatus.BAD_REQUEST),
    INVALID_KEY(1006,"Invalid message key",HttpStatus.BAD_REQUEST),
    NOT_NULL(1007,"Please fill in all fields",HttpStatus.BAD_REQUEST),
    QUANTITY_INVALID(1008,"Quantity must be at least 1",HttpStatus.BAD_REQUEST),
    PRICE_INVALID(1009,"Price must be Positive",HttpStatus.BAD_REQUEST),
    USER_EXISTED(1001,"User existed",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1011,"User existed",HttpStatus.NOT_FOUND),
    PERMISSION_EXISTED(1013,"Permission existed",HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_EXISTED(1014,"Permission not existed",HttpStatus.BAD_REQUEST),
    ROLE_EXISTED(1015,"Role existed",HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTED(1016,"Role not existed",HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1017,"You do not have permission",HttpStatus.FORBIDDEN),
    INVALID_TOKEN(1006,"Invalid token",HttpStatus.UNAUTHORIZED),
    CART_NOT_EXISTED(1018,"Cart not existed",HttpStatus.NOT_FOUND),
    PRODUCT_ID_REQUIRED(1019,"productId must be filled",HttpStatus.BAD_REQUEST),
    NOT_FOUND_ITEM(1021," Not found",HttpStatus.NOT_FOUND),
    EMPTY_CART(1020,"you haven't product in your  cart",HttpStatus.NOT_FOUND),
    INVALID_DOB(1010,"Invalid Dob",HttpStatus.BAD_REQUEST);



    int code;
    String message;
    HttpStatusCode statusCode;
}
