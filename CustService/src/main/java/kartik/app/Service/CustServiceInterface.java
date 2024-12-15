package kartik.app.Service;

import java.util.List;

import kartik.app.Entity.Customer;

public interface CustServiceInterface {
	
	public Customer addCustomer(Customer customer);
	
	public Customer getCustomerById(String id);

	public List<Customer> getAllCustomers();
	
	public List<Customer> findByUserName(String s) ;
		
}
