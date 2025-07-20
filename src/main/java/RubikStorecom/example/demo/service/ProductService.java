package RubikStorecom.example.demo.service;


import RubikStorecom.example.demo.dto.request.ProductRequest;
import RubikStorecom.example.demo.dto.response.ProductResponse;
import RubikStorecom.example.demo.entity.Category;
import RubikStorecom.example.demo.entity.Product;
import RubikStorecom.example.demo.mapper.CategoryMapper;
import RubikStorecom.example.demo.mapper.ProductMapper;
import RubikStorecom.example.demo.repository.CategoryRepository;
import RubikStorecom.example.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    CategoryRepository categoryRepository;

    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest); // Maping request sang product

        var categories = categoryRepository.findAllById(productRequest.getCategories()); // lọc ra những category tồn tại đưa vào List

        product.setCategories(new HashSet<>(categories));

        product =productRepository.save(product); // insert product  vào database
        return productMapper.toProductResponse(product); // trả về Response
    }


    public ProductResponse updateProduct(ProductRequest productRequest, String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

//          product.setProductName(productRequest.getProductName());
//          product.setDescription(productRequest.getDescription());
//          product.setPrice(productRequest.getPrice());
//          product.setCategories(new HashSet<>(categoryRepository.findAllById(productRequest.getCategories())));
//          product.setQuantity(productRequest.getQuantity());
        productMapper.updateProduct(productRequest, product);
         return productMapper.toProductResponse(productRepository.save(product));
    }

    public ProductResponse deleteProduct(String id) {
        var product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.getCategories().clear(); // xóa liên kết
        productRepository.delete(product);
        return productMapper.toProductResponse(product);
    }
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).collect(Collectors.toList());
    }

    public ProductResponse getProduct(String id) {
       Product product= productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toProductResponse(product);
    }

}
