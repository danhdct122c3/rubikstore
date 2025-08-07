package RubikStorecom.example.demo.mapper;

import RubikStorecom.example.demo.dto.request.PermissionRequest;
import RubikStorecom.example.demo.dto.response.PermissionResponse;
import RubikStorecom.example.demo.entity.Permission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permission);
    PermissionResponse toPermissionResponse(Permission permission);
    List<PermissionResponse> toPermissionResponse(List<Permission> permissions);
}
