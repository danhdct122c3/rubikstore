package RubikStorecom.example.demo.service;

import RubikStorecom.example.demo.dto.request.CategoryRequest;
import RubikStorecom.example.demo.dto.response.CategoryResponse;
import RubikStorecom.example.demo.entity.Category;
import RubikStorecom.example.demo.entity.Permission;
import RubikStorecom.example.demo.entity.Product;
import RubikStorecom.example.demo.mapper.CategoryMapper;
import RubikStorecom.example.demo.repository.CategoryRepository;
import RubikStorecom.example.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    ProductRepository productRepository;


    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.toCategory(request);

        category = categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }

    public CategoryResponse updateCategory(CategoryRequest request, String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        categoryMapper.updateCategory(category, request);
        return categoryMapper.toCategoryResponse(category);

    }
    @Transactional
    public CategoryResponse deleteCategory(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        CategoryResponse response = categoryMapper.toCategoryResponse(category);
        categoryRepository.delete(category);
        return response;
    }

    public CategoryResponse getCategory(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryMapper.toCategoryResponse(category);
    }

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toCategoryResponse(categories);
    }

    public CategoryResponse addProductToCategory(String categoryId, String productId) {
        try{
        Product product= productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
            category.getProducts().add(product);  // get product để lấy kiểu dữ liệu  set sau đó dùng method add trong set để thêm product vào category
            product.getCategories().add(category);
            category = categoryRepository.save(category);
            // Kiểm tra xem đã tồn tại chưa
            if (!product.getCategories().contains(category)) {
                product.getCategories().add(category);
                category.getProducts().add(product);

                // Chỉ cần save owner side (Product)
                productRepository.save(product);
            }
            return categoryMapper.toCategoryResponse(category);

        } catch(RuntimeException e){
            log.error("Entity not found while adding product {} to category {}: {}",
                    productId, categoryId, e.getMessage());
            throw e;
        }

    }
}
