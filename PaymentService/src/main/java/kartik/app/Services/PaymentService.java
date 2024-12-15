package kartik.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kartik.app.Entity.Payment;
import kartik.app.Exception.ResourceNotFoundException;
import kartik.app.Repository.PaymentRepo;

@Service
public class PaymentService implements PaymentServiceInterface{

	@Autowired
	private PaymentRepo paymentRepo;
	
	@Override
	public Payment addPayment(Payment payment) {
		return paymentRepo.save(payment);
	}

	@Override
	public Payment getPaymentById(String id) {
		return paymentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("No Payment Found With Given Id"));
	}

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepo.findAll();
	}

	@Override
	public void deletePayment(String id) {
		paymentRepo.deleteById(id);
		
	}

}
