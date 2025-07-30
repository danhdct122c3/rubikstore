package RubikStorecom.example.demo.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final static String[] PUBLIC_ENDPOINTS = {"/users","/users/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // tắt để bỏ qua bước xác thực token
                .authorizeHttpRequests(authorize -> authorize

                .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS).permitAll() // cho phép truy cập không cần xác thực
                .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll() // cho phép truy cập không cần xác thực
                .requestMatchers(HttpMethod.PUT, PUBLIC_ENDPOINTS).permitAll() // cho phép truy cập không cần xác thực
                .requestMatchers(HttpMethod.DELETE, PUBLIC_ENDPOINTS).permitAll()
                .anyRequest()
                .authenticated());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
