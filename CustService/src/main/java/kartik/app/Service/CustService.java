package kartik.app.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import kartik.app.Entity.Customer;
import kartik.app.Entity.Product;
import kartik.app.Exception.DuplicateEntryException;
import kartik.app.Exception.ResourceNotFoundException;
import kartik.app.Repository.CustRepo;

@Service
public class CustService implements CustServiceInterface{
	
	@Autowired
	private RestTemplate restTemplate;

	
	@Autowired
	private CustRepo custRepo;
	
	
	@Override
	public Customer addCustomer(Customer customer) {
		
		String iUuid = UUID.randomUUID().toString();
		customer.setCustId(iUuid);

        if (custRepo.existsByEmail(customer.getEmail())) {
            throw new DuplicateEntryException("Email already exists.");
        }
        if (custRepo.existsByUserName(customer.getUserName())) {
            throw new DuplicateEntryException("Username already exists."); 
        }
        if (custRepo.existsByMobile(customer.getMobile())) {
            throw new DuplicateEntryException("Mobile number already exists.");
        }
        return custRepo.save(customer);

	}

	@Override
	public Customer getCustomerById(String id) {
		return custRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource Not Found With Id : "+id));
	}

	@Override
	public List<Customer> getAllCustomers() {
		return custRepo.findAll();
	}

	@Override
	public List<Customer> findByUserName(String s) {
		return custRepo.findByUserName(s);
	}
	
	public List<Product> getAllProduct(){
		Product[] list = restTemplate.getForObject("http://localhost:1003/product", Product[].class);
		
			List<Product> products = Arrays.stream(list).toList();
			return products;
	}
	
	

}
