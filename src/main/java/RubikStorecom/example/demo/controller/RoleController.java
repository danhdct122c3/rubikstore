package RubikStorecom.example.demo.controller;


import RubikStorecom.example.demo.dto.request.RoleRequest;
import RubikStorecom.example.demo.dto.response.APIResponse;
import RubikStorecom.example.demo.dto.response.RoleResponse;
import RubikStorecom.example.demo.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("roles")
public class RoleController {
    RoleService roleService;
    @PostMapping()
    public APIResponse<RoleResponse> createRole(@RequestBody RoleRequest request){
        log.info("name: "+ request.getName());
        log.info("description: "+ request.getDescription());
        log.info("permission: "+ request.getPermissions().toString());
        return APIResponse.<RoleResponse>builder()
                .result(roleService.createRole(request))
                .build();
    }

    @DeleteMapping("/{name}")
    public APIResponse<Void> deleteRole(@PathVariable String name){
        roleService.deleteRole(name);
        return APIResponse.<Void>builder()
                .message("Role deleted")
                .build();
    }

    @GetMapping
    public APIResponse<List<RoleResponse>> getAllRole(){
        return APIResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }
}
