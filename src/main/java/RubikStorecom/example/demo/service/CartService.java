package RubikStorecom.example.demo.service;


import RubikStorecom.example.demo.dto.request.AddToCartRequest;
import RubikStorecom.example.demo.dto.request.UpdateCartRequest;
import RubikStorecom.example.demo.dto.response.CartResponse;
import RubikStorecom.example.demo.entity.*;
import RubikStorecom.example.demo.exception.AppException;
import RubikStorecom.example.demo.exception.ErrorCode;
import RubikStorecom.example.demo.mapper.CartMapper;
import RubikStorecom.example.demo.repository.CartRepository;
import RubikStorecom.example.demo.repository.ProductRepository;
import RubikStorecom.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {
    UserRepository userRepository;
    CartRepository cartRepository;
    CartMapper cartMapper;

    ProductRepository productRepository;


    public Cart getOrCreateUserCart(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));


        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .user(user)
                            .build();
                    return cartRepository.save(newCart);
                });
    }
    @Transactional
    public CartResponse addToCart(AddToCartRequest request){
        Cart cart= getOrCreateUserCart();


        // nếu chưa có thì trả về empty
        // nếu có thì trả về Item
        Optional<CartItem> existingItem= cart.getItems().stream()
                .filter(item -> item.getProductId() != null &&
                        item.getProductId().equals(request.getProductId()))
                .findFirst();

        if(existingItem.isPresent()){
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
            log.info(" update item {} with quantity: {}", item.getProductId(), item.getQuantity());
        }
        else {
            CartItem newItem= CartItem.builder()
                    .cart(cart)
                    .productId(request.getProductId())
                    .quantity(request.getQuantity())
                    .build();
            cart.getItems().add(newItem);

            log.info("new Item: {} with quantity: {}", newItem.getProductId(), newItem.getQuantity());

        }
        updateTotalAmount(cart);
        Cart saveCart= cartRepository.save(cart);

        return  cartMapper.toCartResponse(saveCart);
    }

    public List<CartResponse> getAll(){
        List<Cart> carts = cartRepository.findAll();
        return cartMapper.toCartResponse(carts);
    }


    public void removeCart(String id){
        Cart cart= cartRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_EXISTED)) ;
        cartRepository.delete(cart);
    }

    public CartResponse myCart(){

        // neu user chưa có gio hang thì sẽ tạo giỏ  hàng mơi
        Cart cartUser = getOrCreateUserCart();

        updateTotalAmount(cartUser);

        return cartMapper.toCartResponse(cartUser);
    }

    public  void delCartItem(String productId){
        Cart cart= getOrCreateUserCart();
        boolean remove= cart.getItems().removeIf(item -> item.getProductId().equals(productId));

        if(!remove) throw new AppException(ErrorCode.NOT_FOUND_ITEM);
        updateTotalAmount(cart);
        cartRepository.save(cart);
    }

    public CartResponse updateCart(String productId,UpdateCartRequest update){
        Cart cart= getOrCreateUserCart();

        Optional<CartItem> existingItem= cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if(existingItem.isPresent()){
            CartItem item=existingItem.get();
            item.setQuantity(update.getQuantity());
            log.info("new quantity: {}", item.getQuantity());
        }
        else throw  new AppException(ErrorCode.NOT_FOUND_ITEM);

        updateTotalAmount(cart);
        Cart saveCart= cartRepository.save(cart);
        return cartMapper.toCartResponse(saveCart);

    }

    public void clearCart(){
        Cart cart= getOrCreateUserCart();
        cart.getItems().clear();
        cart.setTotalAmount(BigDecimal.ZERO);
        cartRepository.save(cart);
        log.info("Cart cleared for user");
    }

    public void updateTotalAmount(Cart cart){
        List<CartItem> items =cart.getItems().stream().toList();
        BigDecimal total= BigDecimal.ZERO;

        for (CartItem item : items){
            Product product = productRepository.findById(item.getProductId()).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ITEM));
            BigDecimal price =BigDecimal.valueOf(product.getPrice());
            BigDecimal quantity = BigDecimal.valueOf(item.getQuantity()); // Chuyển quantity sang BigDecimal

            total = total.add(price.multiply(quantity)); // Cộng dồn vào total

        }
        cart.setTotalAmount(total); // Cập nhật lại tổng vào Cart
    }

}
