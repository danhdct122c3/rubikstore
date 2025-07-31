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

    @GetMapping
    APIResponse<List<UserResponse>> getUsers() {
        return APIResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }
    @GetMapping("/{userId}")
    APIResponse<UserResponse> getUser(@PathVariable String userId) {
        return APIResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @PutMapping("/{userId}")
    APIResponse<UserResponse> updateUser (@Valid @PathVariable String userId ,@RequestBody UserUpdateRequest request) {

        User updated = userService.updateUser(request, userId);
        return  APIResponse.<UserResponse>builder()
                .result(userMapper.toUserResponse(updated))
                .build();
    }

    @DeleteMapping("/{userId}")
    public APIResponse<Void> deleteUser(@PathVariable String userId) {
        userService.delUser(userId);
       return APIResponse.<Void>builder()
                .message("User deleted")
                .build();
    }
}
