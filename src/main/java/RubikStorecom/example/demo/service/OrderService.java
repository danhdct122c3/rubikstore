package RubikStorecom.example.demo.service;

import RubikStorecom.example.demo.dto.request.OrderRequest;
import RubikStorecom.example.demo.dto.request.UpdateCartRequest;
import RubikStorecom.example.demo.dto.response.CartResponse;
import RubikStorecom.example.demo.dto.response.OrderResponse;
import RubikStorecom.example.demo.entity.*;
import RubikStorecom.example.demo.enums.OrderStatus;
import RubikStorecom.example.demo.exception.AppException;
import RubikStorecom.example.demo.exception.ErrorCode;
import RubikStorecom.example.demo.mapper.OrderMapper;
import RubikStorecom.example.demo.repository.CartRepository;
import RubikStorecom.example.demo.repository.OrderRepository;
import RubikStorecom.example.demo.repository.ProductRepository;
import RubikStorecom.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    CartService cartService;
    CartRepository cartRepository;
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    UserRepository userRepository;

    int countQuantity(List<OrderItem> items){
        int quantity=0;
        for(OrderItem item  : items){
            quantity+=item.getQuantity();
        }
        return quantity;
    }

    public Orders getUserOrder(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));


        return orderRepository.findByUserid(user.getId());

    }


    public OrderResponse checkoutOrderFromCart(OrderRequest request){
        Cart cart = cartService.getOrCreateUserCart();

        if(cart.getItems().isEmpty()){
            throw new AppException(ErrorCode.EMPTY_CART);
        }
        Orders orders= Orders.builder()
                .shippingAddress(request.getShippingAddress())
                .PhoneNumber(request.getPhoneNumber())
                .userid(cart.getUser().getId())
                .note(request.getNote())
                .status(OrderStatus.PENDING)
                .paymentMethod(request.getPaymentMethod())
                .totalAmount(cart.getTotalAmount())
                .build();

        List<OrderItem> orderItems= cart.getItems().stream()
                .map(cartItem -> OrderItem.builder()
                        .order(orders)
                        .price(cartRepository.getPriceFromCartItem(cart.getId(),cartItem.getProductId()))
                        .productId(cartItem.getProductId())
                        .quantity(cartItem.getQuantity())
                        .build())
                .toList();

        orders.setItems(orderItems);


        Orders saveOrder= orderRepository.save(orders);
        OrderResponse response =orderMapper.toOrderResponse(saveOrder);
        response.setTotalItems(countQuantity(orderItems));
        cartService.clearCart();
        return response;
    }

    @Transactional
    public OrderResponse updateOrderStatus(String OrderId, OrderStatus update){
        Orders orders= orderRepository.findById(OrderId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ORDER));

        orders.setStatus(update);




        Orders save= orderRepository.save(orders);
        OrderResponse response = orderMapper.toOrderResponse(save);
        response.setUserId(save.getUserid());
        response.setTotalItems(countQuantity(save.getItems()));
        response.setStatusDescription(update.getDescription());
        return response;

    }
}
