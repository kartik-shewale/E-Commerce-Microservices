package kartik.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kartik.app.Entity.Address;

public interface AddressRepo extends JpaRepository<Address, String>{
 public boolean existsByAddressId(String address);
 
 @Modifying 
 @Query("UPDATE Address SET firstLine =:a ,secondLine =:b,city =:c ,state =:d ,pincode =:e WHERE addressId =:f")
 public int updateAddress(@Param("a") String firstLine,@Param("b") String secondLine,@Param("c") String city,@Param("d") String state,@Param("e") String pincode,@Param("f") String addresId);
}
