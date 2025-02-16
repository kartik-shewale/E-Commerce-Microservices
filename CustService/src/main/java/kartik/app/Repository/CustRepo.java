package kartik.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kartik.app.Entity.Customer;
import java.util.List;


@Repository
public interface CustRepo extends JpaRepository<Customer, String> {
	public  List<Customer> findByUserName(String userName);

	public boolean existsByEmail(String email);

	public boolean existsByMobile(String mobile);

	public boolean existsByUserName(String userName);
	
	
	@Modifying
	@Query("UPDATE Customer c SET c.password = :p WHERE c.mobile = :m")
	public int changeUserPasswordByMobile(@Param("m") String mobile, @Param("p") String password);
	
	@Modifying
	@Query("UPDATE Customer c SET c.password = :p WHERE c.email = :m")
	public int changeUserPasswordByEmail(@Param("m") String mobile, @Param("p") String password);
	
	@Modifying
	@Query("UPDATE Customer SET fName = :a,lName =:b , userName =:c WHERE custId = :d")
	public int updateCustomerDetail(@Param("a") String fName, @Param("b") String lName, @Param("c") String userName, @Param("d") String custId);
}
