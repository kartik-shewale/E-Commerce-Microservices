package kartik.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kartik.app.Entity.Customer;
import java.util.List;


@Repository
public interface CustRepo extends JpaRepository<Customer, String> {
	public  List<Customer> findByUserName(String userName);

	public boolean existsByEmail(String email);

	public boolean existsByMobile(String mobile);

	public boolean existsByUserName(String userName);
	
}
