package RubikStorecom.example.demo.service;

import RubikStorecom.example.demo.dto.request.UserCreationRequest;
import RubikStorecom.example.demo.dto.request.UserUpdateRequest;
import RubikStorecom.example.demo.dto.response.UserResponse;
import RubikStorecom.example.demo.entity.Role;
import RubikStorecom.example.demo.entity.User;
import RubikStorecom.example.demo.exception.AppException;
import RubikStorecom.example.demo.exception.ErrorCode;
import RubikStorecom.example.demo.mapper.UserMapper;
import RubikStorecom.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor // tự dộng tạo constructor cho cáo biến được khai báo final
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleService roleService;


    public UserResponse createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        Role roleUser= roleService.getUserRole();
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles= new HashSet<>();
        roles.add(roleUser);
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    public User updateUser(UserUpdateRequest request,String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user,request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
         return userRepository.save(user);
    }

    public List<UserResponse> getUsers(){
        List<User> users = userRepository.findAll();
        return userMapper.toUserResponse(users);

    }


    public UserResponse getUser(String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);

    }

    public void delUser(String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);

    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);

    }

}
