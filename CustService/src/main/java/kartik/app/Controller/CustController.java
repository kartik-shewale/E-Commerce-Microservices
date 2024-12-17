package kartik.app.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import kartik.app.Entity.Customer;
import kartik.app.Entity.Product;
import kartik.app.Service.AdminService;
import kartik.app.Service.CustService;

@Controller
@RequestMapping("/MyShop")
public class CustController {
	
	@Autowired
	private CustService service;
	

	
	
	@GetMapping
	public String loadPage()
	{
		System.out.print("Calling Login Page");
		return "login";
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginPage(@RequestBody Map<String, String> credentials) {
	    String username = credentials.get("username");
	    String password = credentials.get("password");
	    
		Map<String, Object> response = new HashMap<>();
		List<Customer> customerList = service.findByUserName(username);
		
		for(Customer cust: customerList) {
			if(cust.getUserName().equals(username) && cust.getPassword().equals(password))
				response.put("message", "Login successful");
		        response.put("statusText", "Admin authenticated");
		        response.put("customers", cust);
		        response.put("redirect", "MyShop/products");
		    return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		response.put("message", "Invalid credentials");
        response.put("statusText", "Authentication failed");
        response.put("redirect", "MyShop");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Map<String, Object>> addCustomer(@RequestBody Customer customer)
	{

		
		Map<String, Object> response = new HashMap<>();
	    try {
	        Customer customer2 = service.addCustomer(customer);
	        
	        response.put("message", "Sign-Up successful");
	        response.put("statusText", "User created successfully");
	        response.put("customers", customer2);
	        response.put("redirect", "MyShop/products");
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    } catch (RuntimeException e) {
	        throw e;
	    }
		
	}
	
	@PostMapping("/forgotUser")
	public ResponseEntity<Map<String, Object>> forgotAdminPassword(@RequestBody Map<String, String> payload)
	{
        Map<String, Object> response = new HashMap<>();	
	    try {
	         service.resetCustomerPass(payload);
	        
	        response.put("message", "Password change succesfully");
	        response.put("statusText", "Now you can login with new password");
//	        response.put("customers", customer2);
	        response.put("redirect", "MyShop");
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    } catch (RuntimeException e) {
	        throw e;
	    }
		
	}
	
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> addCustomer(@PathVariable String id)
	{
		Customer customer2 = service.getCustomerById(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer2);
	}
	
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> addCustomer()
	{
		List<Customer> customer2 = service.getAllCustomers();
		return ResponseEntity.status(HttpStatus.CREATED).body(customer2);
	}
	
	@GetMapping("/products")
	public String getProductPage(){
		System.out.print("Getting products");
		return "products";
	}
	
	@GetMapping("/getProducts")
	public ResponseEntity<List<Product>> getAllProductData(){
		System.out.print("Getting products");
		List<Product> list = service.getAllProduct();
	return	ResponseEntity.status(HttpStatus.OK).body(list);
	}

}