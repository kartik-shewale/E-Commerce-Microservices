package kartik.app.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kartik.app.Entity.Payment;
import kartik.app.Services.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<Payment> addPayment(@RequestBody Payment payment){
//		String id = UUID.randomUUID().toString();
//		payment.setPaymentId(id);
		Payment payment2 = paymentService.addPayment(payment);
		return ResponseEntity.status(HttpStatus.CREATED).body(payment2);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable String id){
		Payment payment = paymentService.getPaymentById(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(payment);
	}
	
	@GetMapping
	public ResponseEntity<List<Payment>> getAllPayment(){
		List<Payment> payments = paymentService.getAllPayments();
		return ResponseEntity.status(HttpStatus.CREATED).body(payments);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePayment(@PathVariable String id) {
		paymentService.deletePayment(id);
		return ResponseEntity.ok().build();
	}

}
