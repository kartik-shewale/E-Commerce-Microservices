package kartik.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kartik.app.Entity.Credential;


@Repository
public interface CredRepo extends JpaRepository<Credential, String>{

	Credential findByUserName(String userName);
}
