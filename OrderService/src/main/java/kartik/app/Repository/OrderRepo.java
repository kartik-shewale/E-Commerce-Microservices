package kartik.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kartik.app.Entity.Order;


@Repository
public interface OrderRepo extends JpaRepository<Order, String> {
	public List<Order> findByCustomerId(String customerId);

	public List<Order> findByStatus(String status);

	@Modifying
	@Query("UPDATE Order SET status =:b WHERE orderId =:a")
	public int changeStatus(@Param("a") String orderId, @Param("b") String status);
}
