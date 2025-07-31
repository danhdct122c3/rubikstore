package RubikStorecom.example.demo.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @Size(min=6, message="PASSWORD_INVALID")
    String password;
    String firstName;
    String lastName;
    @Pattern(regexp = "^(0|\\+84)[0-9]{9}$", message = "PHONENUMBER_INVALID")
    String phoneNumber;
    LocalDate dob;
    String address;


}
