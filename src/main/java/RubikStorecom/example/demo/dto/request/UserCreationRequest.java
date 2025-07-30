package RubikStorecom.example.demo.dto.request;

import RubikStorecom.example.demo.entity.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;


import java.time.LocalDate;
import java.util.Set;

@Data //tạo getter, setter, Tostring, hashcode, equal
@Builder // cho phép sử dụng setter trên 1 dòng không cần phải  tạo 1 object mới
@NoArgsConstructor // tạo contructor không tham số
@AllArgsConstructor// tạo constructor có tham  số
@FieldDefaults(level = AccessLevel.PRIVATE ) // gán AccessLevel mặc định không cần khai báo trước tên biến
public class UserCreationRequest {
    @Size(min=5, message = "password must be at least 5 character")
    String username;
    @Size(min=6, message="password must be at least 6 character")
    String password;
    String firstName;
    String lastName;
    @Pattern(regexp = "^(0|\\+84)[0-9]{9}$", message = "Số điện thoại không hợp lệ")
    String phoneNumber;
    LocalDate dob;
    String address;





//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)  // Sử dụng IDENTITY để tự động tăng ID
//
//    String id;
//    @Email
//    String username;
//    String password;
//    String phoneNumber;
//    String firstName;
//    String lastName;
//    LocalDate dob;
//    String address;
//    LocalDate created;
//
//    //    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
////    @Column(name = "role")
//    @ManyToMany
//    Set<Role> roles;

}
