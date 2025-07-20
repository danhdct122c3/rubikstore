package RubikStorecom.example.demo.service;

import RubikStorecom.example.demo.dto.request.UserCreationRequest;
import RubikStorecom.example.demo.dto.request.UserUpdateRequest;
import RubikStorecom.example.demo.dto.response.UserResponse;
import RubikStorecom.example.demo.entity.User;
import RubikStorecom.example.demo.mapper.UserMapper;
import RubikStorecom.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // tự dộng tạo constructor cho cáo biến được khai báo final
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("Username already exists");
        User user = userMapper.toUser(request);

        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    public User updateUser(UserUpdateRequest request,String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user,request);
         return userRepository.save(user);
    }

    public List<UserResponse> getUsers(){
        List<User> users = userRepository.findAll();
        return userMapper.toUserResponse(users);

    }

    public void delUser(String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);

    }

}
