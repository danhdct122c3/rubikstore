package RubikStorecom.example.demo.controller;


import RubikStorecom.example.demo.dto.request.ProductRequest;
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
    public ProductResponse createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@RequestBody ProductRequest request,  @PathVariable String id) {
        return productService.updateProduct(request, id);
    }

    @DeleteMapping("/{id}")
    public ProductResponse deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable String id) {

        return productService.getProduct(id);
    }

}
