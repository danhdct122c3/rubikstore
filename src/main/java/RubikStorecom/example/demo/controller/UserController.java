package RubikStorecom.example.demo.controller;

import RubikStorecom.example.demo.dto.request.UserCreationRequest;
import RubikStorecom.example.demo.dto.request.UserUpdateRequest;
import RubikStorecom.example.demo.dto.response.UserResponse;
import RubikStorecom.example.demo.entity.User;
import RubikStorecom.example.demo.mapper.UserMapper;
import RubikStorecom.example.demo.repository.UserRepository;
import RubikStorecom.example.demo.service.UserService;
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
    UserResponse createUser(@RequestBody UserCreationRequest request) {

        return userService.createUser(request);
    }

    @GetMapping
    List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/{userId}")
    UserResponse updateUser ( @PathVariable String userId ,@RequestBody UserUpdateRequest request) {

        User updated = userService.updateUser(request, userId);
        return userMapper.toUserResponse(updated);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        userService.delUser(userId);
       return "User deleted";
    }
}
