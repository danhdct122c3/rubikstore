package RubikStorecom.example.demo.controller;

import RubikStorecom.example.demo.dto.request.UserCreationRequest;
import RubikStorecom.example.demo.dto.request.UserUpdateRequest;
import RubikStorecom.example.demo.dto.response.APIResponse;
import RubikStorecom.example.demo.dto.response.UserResponse;
import RubikStorecom.example.demo.entity.User;
import RubikStorecom.example.demo.mapper.UserMapper;
import RubikStorecom.example.demo.repository.UserRepository;
import RubikStorecom.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {
    UserService userService;

  UserMapper userMapper;




    @PostMapping
    APIResponse<UserResponse> createUser(@Valid @RequestBody UserCreationRequest request) {

        return APIResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }
    //PreAuthorize kiểm tra role là admin thì mới dc truy  cập vào method
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    APIResponse<List<UserResponse>> getUsers() {
        return APIResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }
    //PostAuthorize kiểm tra userid là chính user đó thì mới dc trả về
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.name") // ✅ Admin hoặc chính user đó
    @GetMapping("/{userId}")
    APIResponse<UserResponse> getUser(@PathVariable String userId) {
        return APIResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @GetMapping("/myInfo")
    APIResponse<UserResponse> getMyInfo() {
        return APIResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.name") // ✅ Admin hoặc chính user đó
    @PutMapping("/{userId}")
    APIResponse<UserResponse> updateUser (@Valid @PathVariable String userId ,@RequestBody UserUpdateRequest request) {

        User updated = userService.updateUser(request, userId);
        return  APIResponse.<UserResponse>builder()
                .result(userMapper.toUserResponse(updated))
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public APIResponse<Void> deleteUser(@PathVariable String userId) {
        userService.delUser(userId);
       return APIResponse.<Void>builder()
                .message("User deleted")
                .build();
    }
}
