package kartik.app.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import jakarta.transaction.Transactional;
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
	
	
	@Transactional
	public boolean resetCustomerPass(@RequestBody Map<String, String> payload)
	{
		String username = payload.get("username");
        String password = payload.get("password");
        String mobileRegex = "^[7-9][0-9]{9}$";
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
//                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//        Pattern p = Pattern.compile(emailRegex);
//        return email != null && p.matcher(email).matches();
        if(username.matches(mobileRegex))
        {
        	if(!custRepo.existsByMobile(username)) {
        		throw new ResourceNotFoundException("Account with given mobile number "+ username+" does not exist ");
        	}else {
				custRepo.changeUserPasswordByMobile(username, password);
			}
        	
        }else {
        	if(!custRepo.existsByEmail(username)) {
        		throw new ResourceNotFoundException("Account with given email "+ username+" does not exist ");
        	}else {
				custRepo.changeUserPasswordByEmail(username, password);
			}	
        }	
        return true;
		
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
	
	public void deleteProductById(String id) {
	    try {
	        // Assuming you're making a REST call to delete the product
	        restTemplate.delete("http://localhost:1003/product/" + id);
	        System.out.println("Product deleted successfully with id: " + id);
	    } catch (Exception e) {
	        System.err.println("Error during delete: " + e.getMessage());
	    }
	}
	
	public Product getProductById(String id){
		Product list = restTemplate.getForObject("http://localhost:1003/product/"+id, Product.class);
		return list;
	}
	
	public Product addProduct(Product product)
	{
		return	restTemplate.postForObject("http://localhost:1003/product", product, Product.class);
	}

	
	

}
