package RubikStorecom.example.demo.controller;


import RubikStorecom.example.demo.dto.request.PermissionRequest;
import RubikStorecom.example.demo.dto.response.APIResponse;
import RubikStorecom.example.demo.dto.response.PermissionResponse;
import RubikStorecom.example.demo.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permission")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping()
    public APIResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request){
        var result= permissionService.createPermission(request);
        return APIResponse.<PermissionResponse>builder()
                .result(result)
                .build();
    }

    @GetMapping()
    public APIResponse<List<PermissionResponse>> getAllPermission( ){

        return APIResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{name}")
    public APIResponse<Void> deletePermission(@PathVariable String name){
         permissionService.deletePermission(name);
        return APIResponse.<Void>builder()
                .message("Permission deleted")
                .build();
    }
}
