package kartik.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kartik.app.Entity.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Integer>{

}
