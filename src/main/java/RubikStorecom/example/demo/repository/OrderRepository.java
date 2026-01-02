package RubikStorecom.example.demo.repository;

import RubikStorecom.example.demo.entity.Cart;
import RubikStorecom.example.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {


    @Query("SELECT c FROM Cart c JOIN c.items ci WHERE ci.productId = :productId")
    Optional<Cart> findItemByProductId(@Param("productId") String productId);



    Orders findByUserid(String userid);
}
