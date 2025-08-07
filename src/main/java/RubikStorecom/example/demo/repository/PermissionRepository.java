package RubikStorecom.example.demo.repository;

import RubikStorecom.example.demo.entity.Permission;
import RubikStorecom.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,String> {


}
