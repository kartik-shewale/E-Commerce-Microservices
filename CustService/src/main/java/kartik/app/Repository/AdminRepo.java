package kartik.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kartik.app.Entity.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, String>{
	
	@Modifying
	@Query("UPDATE Admin c SET c.password = :p WHERE c.mobile = :m")
	public int changeAdminPasswordByMobile(@Param("m") String mobile, @Param("p") String password);
	
	@Modifying
	@Query("UPDATE Admin c SET c.password = :p WHERE c.mobile = :m")
	public int changeAdminPasswordByEmail(@Param("m") String mobile, @Param("p") String password);
	
	public boolean existsByEmail(String email);

	public boolean existsByMobile(String mobile);
	
	public Admin  findByAdminName(String adminName);


}
