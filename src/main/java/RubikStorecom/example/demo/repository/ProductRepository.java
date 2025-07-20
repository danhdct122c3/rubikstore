package RubikStorecom.example.demo.repository;

import RubikStorecom.example.demo.dto.request.ProductRequest;
import RubikStorecom.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
