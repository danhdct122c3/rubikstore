package RubikStorecom.example.demo.mapper;

import RubikStorecom.example.demo.dto.request.CategoryRequest;
import RubikStorecom.example.demo.dto.response.CategoryResponse;
import RubikStorecom.example.demo.entity.Category;
import org.mapstruct.*;

@Mapper(componentModel = "Spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    Category toCategory(CategoryRequest request);
    CategoryResponse toCategoryResponse(Category category);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "products", ignore = true) // Bỏ qua products field
    @Mapping(target = "id", ignore = true)
    void updateCategory(@MappingTarget Category category, CategoryRequest request);

}
