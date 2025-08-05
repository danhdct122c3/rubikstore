package RubikStorecom.example.demo.controller;

import RubikStorecom.example.demo.dto.request.AuthenticationRequest;
import RubikStorecom.example.demo.dto.response.APIResponse;
import RubikStorecom.example.demo.dto.response.AuthenticationResponse;

import RubikStorecom.example.demo.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authenticate")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class AuthenticationController {

    AuthenticationService authenticationService;
    @PostMapping("/login")
    public APIResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
        APIResponse<AuthenticationResponse> response = new APIResponse<>();

        var result = authenticationService.authenticate(request);

        return APIResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }
}
