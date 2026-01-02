package RubikStorecom.example.demo.repository;

import RubikStorecom.example.demo.dto.response.CartResponse;
import RubikStorecom.example.demo.entity.Cart;
import RubikStorecom.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Optional<Cart> findByUserId(String userId);

    @Query("SELECT c FROM Cart c JOIN c.items ci WHERE ci.productId = :productId")
    Optional<Cart> findItemByProductId(@Param("productId") String productId);

    // Hoặc nếu muốn lấy price từ CartItem của một Cart cụ thể:
    @Query("SELECT p.price FROM Cart c JOIN c.items ci JOIN Product p ON p.id = ci.productId WHERE c.id = :cartId AND ci.productId = :productId")
    BigDecimal getPriceFromCartItem(@Param("cartId") String cartId, @Param("productId") String productId);


}
