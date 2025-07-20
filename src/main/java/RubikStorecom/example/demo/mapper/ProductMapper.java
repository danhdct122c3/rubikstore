package RubikStorecom.example.demo.mapper;

import RubikStorecom.example.demo.dto.request.ProductRequest;
import RubikStorecom.example.demo.dto.response.ProductResponse;
import RubikStorecom.example.demo.entity.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "created", ignore = true)
    Product toProduct(ProductRequest request);
    ProductResponse toProductResponse(Product product);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)// bỏ qua các trường null

    @Mapping(target = "created", ignore = true)//Nếu không đánh dấu @Mapping(target = "categories", ignore = true), MapStruct sẽ cố tự map List<String> → Set<Category> nhưng không có cách rõ ràng để làm điều đó → dẫn đến lỗi compilation
    @Mapping(target = "categories", ignore = true)//Nếu không đánh dấu @Mapping(target = "categories", ignore = true), MapStruct sẽ cố tự map List<String> → Set<Category> nhưng không có cách rõ ràng để làm điều đó → dẫn đến lỗi compilation
    void updateProduct(ProductRequest request, @MappingTarget Product product);

    List<ProductResponse> toProductResponse(List<Product> products);

}
