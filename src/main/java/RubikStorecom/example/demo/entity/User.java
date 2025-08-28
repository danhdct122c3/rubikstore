package RubikStorecom.example.demo.entity;


import RubikStorecom.example.demo.validator.DobConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)  // Sử dụng IDENTITY để tự động tăng
    String id;

    String username;
    String password;
    String phoneNumber;
    String firstName;
    String lastName;

    LocalDate dob;
    String address;
    @CreationTimestamp
    @Column(updatable = false)
    LocalDate created;

    //    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "role")
    @ManyToMany
    Set<Role> roles;





}
