package RubikStorecom.example.demo.controller;


import RubikStorecom.example.demo.dto.request.AddToCartRequest;
import RubikStorecom.example.demo.dto.request.UpdateCartRequest;
import RubikStorecom.example.demo.dto.response.APIResponse;
import RubikStorecom.example.demo.dto.response.CartResponse;
import RubikStorecom.example.demo.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class CartController {
    CartService cartService;

    @PostMapping
    public APIResponse<CartResponse> addToCart(@RequestBody AddToCartRequest request){
        return APIResponse.<CartResponse>builder()
                .result(cartService.addToCart(request))
                .build();
    }

    @GetMapping
    public APIResponse<List<CartResponse>> getAllCart(){
        return APIResponse.<List<CartResponse>>builder()
                .result(cartService.getAll())
                .build();
    }

    @DeleteMapping("/{id}")
    public APIResponse<Void> removeCart(@PathVariable String id){
        cartService.removeCart(id);
        return  APIResponse.<Void>builder()
                .message("Cart deleted")
                .build();
    }

    @GetMapping("/myCart")
    public APIResponse<CartResponse> myCart(){
        return APIResponse.<CartResponse>builder()
                .result(cartService.myCart())
                .build();
    }

    @PutMapping("/update/{productId}")
    public APIResponse<CartResponse> updateCart(@PathVariable String productId, @RequestBody UpdateCartRequest request){
        return APIResponse.<CartResponse>builder()
                .result(cartService.updateCart(productId,request))
                .build();
    }

    @DeleteMapping("/item/{productId}")
    public APIResponse<CartResponse> delCartItem(@PathVariable  String productId){
        cartService.delCartItem(productId);
        return  APIResponse.<CartResponse>builder()
                .result(cartService.myCart())
                .build();
    }

    @DeleteMapping("/clear")
    public APIResponse<CartResponse> clearCart(){
        cartService.clearCart();
        return  APIResponse.<CartResponse>builder()
                .result(cartService.myCart())
                .build();
    }
}
