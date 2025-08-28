package RubikStorecom.example.demo.mapper;

import RubikStorecom.example.demo.dto.request.AddToCartRequest;
import RubikStorecom.example.demo.dto.request.CategoryRequest;
import RubikStorecom.example.demo.dto.response.CartItemResponse;
import RubikStorecom.example.demo.dto.response.CartResponse;
import RubikStorecom.example.demo.dto.response.CategoryResponse;
import RubikStorecom.example.demo.entity.Cart;
import RubikStorecom.example.demo.entity.CartItem;
import RubikStorecom.example.demo.entity.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface CartMapper {

    Cart toCart(AddToCartRequest request);
    @Mapping(target = "cartId", source = "id")
    @Mapping(target = "userId", source = "user.id")

    @Mapping(target = "totalItems", expression = "java(cart.getItems() != null ? cart.getItems().size() : 0)")
    CartResponse toCartResponse(Cart cart);
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "products", ignore = true) // Bỏ qua products field
//    @Mapping(target = "id", ignore = true)
//    void updateCategory(@MappingTarget Category category, CategoryRequest request);
//


    @Mapping(target = "cartItemId", source = "id")

    CartItemResponse cartItemToCartItemResponse(CartItem cartItem);
    @Mapping(target = "cartId", source = "id")
    List<CartResponse> toCartResponse(List<Cart> carts);
}
