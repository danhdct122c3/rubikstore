package RubikStorecom.example.demo.controller;

import RubikStorecom.example.demo.dto.request.AuthenticationRequest;
import RubikStorecom.example.demo.dto.request.IntrospectRequest;
import RubikStorecom.example.demo.dto.response.APIResponse;
import RubikStorecom.example.demo.dto.response.AuthenticationResponse;

import RubikStorecom.example.demo.dto.response.IntrospectResponse;
import RubikStorecom.example.demo.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authenticate")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class AuthenticationController {

    AuthenticationService authenticationService;
    @PostMapping("/token")
    public APIResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {


        return APIResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authenticate(request))
                .build();
    }

    @PostMapping("/introspect")
    public APIResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {



        return APIResponse.<IntrospectResponse>builder()
                .result(authenticationService.introspect(request))
                .build();
    }
}
