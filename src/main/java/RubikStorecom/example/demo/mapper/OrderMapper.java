package RubikStorecom.example.demo.mapper;

import RubikStorecom.example.demo.dto.request.AddToCartRequest;
import RubikStorecom.example.demo.dto.request.OrderRequest;
import RubikStorecom.example.demo.dto.response.CartItemResponse;
import RubikStorecom.example.demo.dto.response.CartResponse;
import RubikStorecom.example.demo.dto.response.OrderResponse;
import RubikStorecom.example.demo.entity.Cart;
import RubikStorecom.example.demo.entity.CartItem;
import RubikStorecom.example.demo.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface OrderMapper {

    Orders toOrder(Orders orders);

    OrderResponse toOrderResponse(Orders orders);
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
