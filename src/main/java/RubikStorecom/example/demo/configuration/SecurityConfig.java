package RubikStorecom.example.demo.configuration;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;

@Configuration
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Value("${jwt.signerKey}")
    private String signerKey;

    @Autowired
    private CustomJWTDecoder customJwtDecoder;

    private final static String[] PUBLIC_ENDPOINTS = {"/users","/users/**"
//                                                        ,"/products","/products/**"
//                                                        ,"/categories","/categories/**"
                                                        ,"/authenticate/**",
//                                                        ,"/roles/**","/roles"
//                                                        ,"/permission", "permission/**,
                                                        "/css/**",
                                                        "/js/**",
                                                        "/images/**",
                                                        "/*.html",
                                                        "/*.css",
                                                        "/*.js"};


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // tắt để bỏ qua bước xác thực token
                .authorizeHttpRequests(authorize -> authorize

                .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS).permitAll() // cho phép truy cập không cần xác thực
                .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll() // cho phép truy cập không cần xác thực
                .requestMatchers(HttpMethod.PUT, PUBLIC_ENDPOINTS).permitAll() // cho phép truy cập không cần xác thực
                .requestMatchers(HttpMethod.DELETE, PUBLIC_ENDPOINTS).permitAll()
                .anyRequest().authenticated());

        http.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(customJwtDecoder)
                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

//    @Bean
//    JwtAuthenticationConverter jwtAuthenticationConverter(){
//        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
//
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//        return  jwtAuthenticationConverter;
//    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        log.info("dang dc goi");
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthoritiesClaimName("Scope"); // hoặc "scope"
        converter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            // Debug JWT claims
            System.out.println("🔍 All JWT claims: " + jwt.getClaims());
            String scope = jwt.getClaimAsString("Scope");
            System.out.println("🔍 JWT Scope claim: " + scope);

            // Convert authorities
            Collection<GrantedAuthority> authorities = converter.convert(jwt);
            System.out.println("🔍 Converted authorities: " + authorities);

            return authorities;
        });
        return jwtConverter;
    }
}
