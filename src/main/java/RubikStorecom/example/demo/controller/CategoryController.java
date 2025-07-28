package RubikStorecom.example.demo.controller;

import RubikStorecom.example.demo.dto.request.CategoryRequest;
import RubikStorecom.example.demo.dto.response.APIResponse;
import RubikStorecom.example.demo.dto.response.CategoryResponse;
import RubikStorecom.example.demo.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("/categories")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    @PostMapping
    public APIResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        return APIResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(request))
                .build();
    }

    @PutMapping("/{id}")
    public APIResponse<CategoryResponse> updateCategory(@PathVariable String id, @RequestBody CategoryRequest request) {
        return APIResponse.<CategoryResponse>builder()
                .result(categoryService.updateCategory(request,id))
                .build();
    }

    @DeleteMapping("/{id}")
    public APIResponse<Void> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return APIResponse.<Void>builder()
                .message("Category deleted")
                .build();
    }

    @GetMapping("/{id}")
    public APIResponse<CategoryResponse> getCategory(@PathVariable String id) {
        return APIResponse.<CategoryResponse>builder()
                .result(categoryService.getCategory(id))
                .build();
    }

    @GetMapping
    public APIResponse<List<CategoryResponse>> getAllCategories() {
        return APIResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAllCategories())
                .build();
    }

    @PostMapping("{categoryId}/products/{productId}")
    public APIResponse<CategoryResponse> addProductToCategory(@PathVariable String categoryId, @PathVariable String productId)
    {
        return APIResponse.<CategoryResponse>builder()
                .result(categoryService.addProductToCategory(categoryId, productId))
                .build();
    }
}
