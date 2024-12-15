package kartik.app.Services;

import java.util.List;

import kartik.app.Entity.Payment;


public interface PaymentServiceInterface {

	public Payment addPayment(Payment item);
	
	public Payment getPaymentById(String id);
	
	public List<Payment> getAllPayments();
	
	public void deletePayment(String id);
}
