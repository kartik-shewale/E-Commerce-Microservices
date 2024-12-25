package kartik.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kartik.app.Entity.Address;

public interface AddressRepo extends JpaRepository<Address, String>{
 public boolean existsByAddressId(String address);
}
