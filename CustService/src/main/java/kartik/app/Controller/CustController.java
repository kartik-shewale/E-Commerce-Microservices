package kartik.app.Controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kartik.app.Entity.Address;
import kartik.app.Entity.Customer;
import kartik.app.Entity.Item;
import kartik.app.Entity.Order;
import kartik.app.Entity.Payment;
import kartik.app.Entity.Product;
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
	    	
	    	if(customer.getCustId()!= "" && customer.getCustId()!= null)
	    	{
		        Boolean flag = service.updateCustomerDetail(customer);
		       String msg = flag?"Update Succesfully":"Update Failure";
		        response.put("message", msg);
	    	}else {
		        Customer customer2 = service.addCustomer(customer);
		        
		        response.put("message", "Sign-Up successful");
		        response.put("statusText", "User created successfully");
		        response.put("customers", customer2);
		        response.put("redirect", "MyShop/products");
	    	}
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
		
	// All Releted to Product ================================================================================================
	
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
	
	@GetMapping("product/{id}")
	public ResponseEntity<Map<String, Object>> getProductById(@PathVariable String id){
		System.out.print("Getting products");
		 Map<String, Object> response = new HashMap<>();	
		Product list = service.getProductById(id);
		response.put("product", list);
		response.put("message", "Product Found");
		return	ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<Map<String, Object>>  deleteProduct(@PathVariable String id)
	{
		Map<String, Object> response = new HashMap<>();	
		System.out.println("Deleting product ..........................................");
		service.deleteProductById(id);
		response.put("message", "Delete Succesfully");
        response.put("statusText", "Deleted");
        return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/addUpdateProduct")
	public ResponseEntity<Map<String, Object>> addUpdateProduct(@RequestBody Product product)
	{
		Map<String, Object> response = new HashMap<>();	
		Product p = service.addProduct(product);
		
		if(p.getId().equals("success"))
		{
			response.put("message", "Updated Succesfully");
	        response.put("statusText", "Updated");
		}else if(p.getId().equals("Failed"))
		{
			response.put("message", "Update Failure");
	        response.put("statusText", "Failure");
		}else {
			response.put("product", p);
			response.put("message", "Added Succesfully");
	        response.put("statusText", "Added");
		}
        return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	// All Releted to Product ================================================================================================
	
	@GetMapping("/payment")
	public String getPaymentPage(){
		System.out.print("Getting products");
		return "payment";
	}
	
	@GetMapping("/cart")
	public String getCartPage(){
		System.out.print("Getting products");
		return "cart";
	}
	
	@PostMapping("/cart")
	public ResponseEntity<Map<String,Object>> addToCart(@RequestBody Item item)
	{
		Map<String, Object> response = new HashMap<>();	
		Item item2 = service.addToCart(item);
		response.put("Item", item2);
		response.put("message", "added succesfully");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/cart/{id}")
	public ResponseEntity<Map<String,Object>> getCartProdcut(@PathVariable String id)
	{
		Map<String, Object> response = new HashMap<>();	
		List<Item> items = service.getCartPreodcut(id);
		response.put("Item", items);
		response.put("message", "All Card Product");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/check/{itemId}/{userId}")
	public ResponseEntity<Map<String,Object>> isItemExistInCart(@PathVariable String itemId,@PathVariable String userId)
	{
		Map<String, Object> response = new HashMap<>();	

		boolean res = service.isItemExistInCart(itemId, userId);
		response.put("isExist", res);
		response.put("message", "Item Exist in Cart");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("IdByuserId/{id}")
	public ResponseEntity<Map<String,Object>> getAllItemIdByUserId(@PathVariable String id){
		Map<String, Object> response = new HashMap<>();
		
		List<String> itemIds = service.getAllItemIdByUserId(id);
		response.put("itemIds", itemIds);
		response.put("message", "Item Id List");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@DeleteMapping("cartItem/{id}")
	public ResponseEntity<Map<String,Object>> deleteCartItem(@PathVariable int id){
		Map<String, Object> response = new HashMap<>();
		 boolean res =  service.deleteCartItem(id);
		 
		 if(res)response.put("message", "Deleted");
		 else response.put("message", "Failure");
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PostMapping("/address")
	public ResponseEntity<Map<String,Object>> addAddress(@RequestBody Address address)
	{
		Map<String, Object> response = new HashMap<>();
		if(address.getAddressId()=="") {
			String id = UUID.randomUUID().toString();
			address.setAddressId(id);
			Address address2 =	service.addAddress(address);
		    response.put("address", address2);
		    response.put("message", "added succesfully");
		}else {
			int i =	service.updateAddress(address);
		    response.put("isUpdated", i);
		    response.put("message", "added succesfully");
			
		}
		    
	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/address/{id}")
	public ResponseEntity<Map<String,Object>> getAddressById(@PathVariable String id)
	{

		Map<String, Object> response = new HashMap<>();
	    Address address2 =	service.getAddressById(id);
	    response.put("address", address2);
	    response.put("message", "Fetch succesfully");
	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@DeleteMapping("/address/{id}")
	public ResponseEntity<Map<String,Object>> deleteAddressById(@PathVariable String id)
	{

		Map<String, Object> response = new HashMap<>();
	    service.deleteAddressById(id);
	    response.put("status", "success");
	    response.put("message", "Deleted");
	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@Autowired
	private Payment payment;
	@Autowired
	private Order order;
	@PostMapping("/payorder")
	public ResponseEntity<Map<String,Object>> paymentAndOrder(@RequestBody Map<String, Object> paymentDetails){
		
		String addresId = (String) paymentDetails.get("addressId");
		String userId = (String) paymentDetails.get("userId");
		int amount = (int)paymentDetails.get("finalAmount");
		String paymentMethod = (String) paymentDetails.get("paymentMethod");
		String orderId = UUID.randomUUID().toString();
		String paymentId = UUID.randomUUID().toString();
		
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime futureDate = currentDateTime.plusDays(5);
        
		payment.setAmount(amount);
		payment.setCustomerId(userId);
		payment.setOrderId(orderId);
		payment.setPaymentId(paymentId);
		payment.setPaymentMethod(paymentMethod);
		payment.setPaymentStatus("Success");
		payment.setTransactionTime(currentDateTime);

		order.setAddresId(addresId);
		order.setCustomerId(userId);
		order.setAmount(amount);
		order.setOrderId(orderId);
		order.setPaymentId(paymentId);
		order.setOrderDate(currentDateTime);
		order.setStatus("Order Confirm");
		order.setDilivaryDate(futureDate);
		
        Map<String, Object> response = new HashMap<>();
		
		Payment p = service.makePayment(payment);
		Order o = service.makeOrder(order);
		
		List<Item> orderItems = service.selectCartItemForOrder(userId);
		for(Item item : orderItems)
		{	
			System.out.println(item.getUserId());
			Map<String, Object> itemMap = new HashMap<>();
			itemMap.put("itemId", item.getItemId());
			itemMap.put("userId", item.getUserId());
			itemMap.put("itemName", item.getItemName());
			itemMap.put("itemDesc", item.getItemDesc());
			itemMap.put("itemPrice", item.getItemPrice());
			itemMap.put("itemQuantity", item.getItemQuantity());
			itemMap.put("orderId", orderId);
			
			service.addCartToOrder(itemMap);
			service.updateProductQuantity(itemMap);
		}
		service.deleteCartItemByUserId(userId);
		
		response.put("Payment", p);
		response.put("Order", o);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
}
