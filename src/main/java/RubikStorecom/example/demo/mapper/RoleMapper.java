package RubikStorecom.example.demo.mapper;

import RubikStorecom.example.demo.dto.request.RoleRequest;
import RubikStorecom.example.demo.dto.response.RoleResponse;
import RubikStorecom.example.demo.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.lang.annotation.Target;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);

}
