package kartik.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kartik.app.Entity.Payment;


@Repository
public interface PaymentRepo extends JpaRepository<Payment, String> {

}
