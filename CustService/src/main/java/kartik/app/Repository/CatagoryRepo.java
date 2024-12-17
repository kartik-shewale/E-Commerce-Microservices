package kartik.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kartik.app.Entity.Catagory;

@Repository
public interface CatagoryRepo extends JpaRepository<Catagory, Integer>{

}
