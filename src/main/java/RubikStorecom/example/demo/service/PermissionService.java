package RubikStorecom.example.demo.service;

import RubikStorecom.example.demo.dto.request.PermissionRequest;
import RubikStorecom.example.demo.dto.response.PermissionResponse;
import RubikStorecom.example.demo.entity.Permission;
import RubikStorecom.example.demo.exception.AppException;
import RubikStorecom.example.demo.exception.ErrorCode;
import RubikStorecom.example.demo.mapper.PermissionMapper;
import RubikStorecom.example.demo.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;

    PermissionMapper permissionMapper;
    public PermissionResponse createPermission(PermissionRequest request) {

        if (permissionRepository.existsById(request.getName())) {
            throw new AppException(ErrorCode.PERMISSION_EXISTED);
        }
        Permission permission= permissionMapper.toPermission(request);
        permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
         List<Permission> permissions= permissionRepository.findAll();
         return  permissionMapper.toPermissionResponse(permissions);
    }

    public void deletePermission(String name){
        Permission permission= permissionRepository.findById(name).orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_EXISTED));
        permissionRepository.delete(permission);

    }


}
