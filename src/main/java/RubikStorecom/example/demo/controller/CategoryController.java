package RubikStorecom.example.demo.controller;

import RubikStorecom.example.demo.dto.request.CategoryRequest;
import RubikStorecom.example.demo.dto.response.CategoryResponse;
import RubikStorecom.example.demo.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

@RequestMapping("/categories")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest request) {
        return categoryService.createCategory(request);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable String id, @RequestBody CategoryRequest request) {
        return categoryService.updateCategory(request,id);
    }

    @DeleteMapping("/{id}")
    public CategoryResponse deleteCategory(@PathVariable String id) {
        return categoryService.deleteCategory(id);
    }
}
