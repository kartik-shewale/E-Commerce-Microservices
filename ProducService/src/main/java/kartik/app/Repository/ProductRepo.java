package kartik.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kartik.app.Entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {

}
