package RubikStorecom.example.demo.configuration;

import RubikStorecom.example.demo.dto.request.UserCreationRequest;
import RubikStorecom.example.demo.dto.response.UserResponse;
import RubikStorecom.example.demo.entity.Role;
import RubikStorecom.example.demo.entity.User;
import RubikStorecom.example.demo.exception.AppException;
import RubikStorecom.example.demo.exception.ErrorCode;
import RubikStorecom.example.demo.repository.RoleRepository;
import RubikStorecom.example.demo.repository.UserRepository;
import RubikStorecom.example.demo.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
@RequiredArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    RoleRepository roleRepository;
    RoleService roleService;

    @Bean
    @Transactional
    ApplicationRunner applicationRunner(UserRepository userRepository) {

        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Role roleAdmin= roleService.getAdminRole();
                Set<Role> role = new HashSet<>();
                role.add(roleAdmin);
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(role)
                        .build();
                userRepository.save(user);
                log.warn("user admin has been created with default password, please change it");
            }


//            if (!userRepository.findByUsername("admin").isEmpty()) {
//                // ✅ Lấy user admin hiện tại
//                User existingAdmin = userRepository.findByUsername("admin").get();
//
//                // ✅ Cập nhật mật khẩu
//                existingAdmin.setPassword(passwordEncoder.encode("admin"));
//
//                userRepository.save(existingAdmin);
//                log.warn("Admin password has been updated");
//            }
        };
    }




}

