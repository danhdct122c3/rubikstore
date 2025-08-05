package RubikStorecom.example.demo.service;


import RubikStorecom.example.demo.dto.request.AuthenticationRequest;
import RubikStorecom.example.demo.dto.request.IntrospectRequest;
import RubikStorecom.example.demo.dto.response.APIResponse;
import RubikStorecom.example.demo.dto.response.AuthenticationResponse;
import RubikStorecom.example.demo.dto.response.IntrospectResponse;
import RubikStorecom.example.demo.entity.User;
import RubikStorecom.example.demo.exception.AppException;
import RubikStorecom.example.demo.exception.ErrorCode;
import RubikStorecom.example.demo.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RequiredArgsConstructor
@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public AuthenticationResponse authenticate(AuthenticationRequest request){

        var user =userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!passwordMatches) throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token= generateToken(request.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticate(true)
                .build();
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token= request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT= SignedJWT.parse(token);
        Date expityTime= signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified=signedJWT.verify(verifier);

        return  IntrospectResponse.builder().
                valid(verified && expityTime.after(new Date()))
                .build();
    }

    private String generateToken(String username)  {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet= new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("rubik-store")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("CustomClaim","Custom")
                .build();

        Payload payload= new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject= new JWSObject(header,payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("cannot create token", e);
            throw new RuntimeException(e);
        }
    }

}
