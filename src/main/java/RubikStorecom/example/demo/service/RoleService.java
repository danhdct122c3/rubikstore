package RubikStorecom.example.demo.service;

import RubikStorecom.example.demo.dto.request.RoleRequest;
import RubikStorecom.example.demo.dto.response.PermissionResponse;
import RubikStorecom.example.demo.dto.response.RoleResponse;
import RubikStorecom.example.demo.entity.Permission;
import RubikStorecom.example.demo.entity.Role;
import RubikStorecom.example.demo.exception.AppException;
import RubikStorecom.example.demo.exception.ErrorCode;
import RubikStorecom.example.demo.mapper.RoleMapper;
import RubikStorecom.example.demo.repository.PermissionRepository;
import RubikStorecom.example.demo.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoleService {

    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleResponse createRole(RoleRequest request){

        if(roleRepository.existsById(request.getName())) throw  new AppException(ErrorCode.ROLE_EXISTED);
        Role role= roleMapper.toRole(request);
        List<Permission> permission= permissionRepository.findAllById(request.getPermissions()); // findAllById mặc định trả về list
        role.setPermissions(new HashSet<>(permission));// có thể chuyển từ list sang set vì contructor của hashset nhận một collection mà list extend collection
        role =roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public void deleteRole(String name){
        Role role= roleRepository.findById(name).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED)) ;
        roleRepository.delete(role);
    }


    public List<RoleResponse> getAll(){
        List<Role> roles= roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }




}
