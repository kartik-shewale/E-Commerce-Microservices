package kartik.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kartik.app.Entity.Item;

@Repository
public interface CartRepo extends JpaRepository<Item, String>{
	
}
