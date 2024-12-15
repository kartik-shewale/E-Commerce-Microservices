package kartik.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kartik.app.Entity.Order;


@Repository
public interface OrderRepo extends JpaRepository<Order, String> {

}
