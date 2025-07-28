package RubikStorecom.example.demo.controller;


import RubikStorecom.example.demo.dto.request.ProductRequest;
import RubikStorecom.example.demo.dto.response.APIResponse;
import RubikStorecom.example.demo.dto.response.ProductResponse;
import RubikStorecom.example.demo.mapper.ProductMapper;
import RubikStorecom.example.demo.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductController {
    ProductService productService;


    @PostMapping
    public APIResponse<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        return APIResponse.<ProductResponse>builder()
                .result(productService.createProduct(request))
                .build();
    }

    @PutMapping("/{id}")
    public APIResponse<ProductResponse> updateProduct(@RequestBody ProductRequest request,  @PathVariable String id) {
        return APIResponse.<ProductResponse>builder()
                .result(productService.updateProduct(request, id))
                .build();
    }

    @DeleteMapping("/{id}")
    public APIResponse<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return APIResponse.<Void>builder()
                .message("product deleted")
                .build();
    }

    @GetMapping
    public APIResponse<List<ProductResponse>> getAllProducts() {

        return APIResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProducts())
                .build();
    }

    @GetMapping("/{id}")
    public APIResponse<ProductResponse> getProduct(@PathVariable String id) {

        return APIResponse.<ProductResponse>builder()
                .result(productService.getProduct(id))
                .build();
    }

}
