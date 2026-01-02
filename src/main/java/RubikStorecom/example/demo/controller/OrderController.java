package RubikStorecom.example.demo.controller;

import RubikStorecom.example.demo.dto.request.OrderRequest;
import RubikStorecom.example.demo.dto.response.APIResponse;
import RubikStorecom.example.demo.dto.response.OrderResponse;
import RubikStorecom.example.demo.enums.OrderStatus;
import RubikStorecom.example.demo.service.OrderService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;

    @PostMapping()
    public APIResponse<OrderResponse> checkoutFromCart(@RequestBody OrderRequest request){
        return APIResponse.<OrderResponse>builder()
                .result(orderService.checkoutOrderFromCart(request))
                .build();
    }

    @PutMapping("/{id}")
    public APIResponse<OrderResponse> updateStatus(@PathVariable String id , @RequestBody OrderStatus update){
        return APIResponse.<OrderResponse>builder()
                .result(orderService.updateOrderStatus(id,update))
                .build();
    }

}
