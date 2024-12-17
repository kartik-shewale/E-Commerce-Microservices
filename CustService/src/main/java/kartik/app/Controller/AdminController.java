package kartik.app.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kartik.app.Entity.Admin;
import kartik.app.Entity.Catagory;
import kartik.app.Entity.Customer;
import kartik.app.Service.AdminService;
import kartik.app.Service.CatagoryService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CatagoryService catagoryService;
	
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginPage(@RequestBody Map<String, String> credentials) {
	    String username = credentials.get("username");
	    String password = credentials.get("password");
	    
		Map<String, Object> response = new HashMap<>();
		Admin adminList = adminService.findByUserName(username);
		
			if(adminList.getAdminName().equals(username) && adminList.getPassword().equals(password)) {
				response.put("message", "Login successful");
		        response.put("statusText", "Admin authenticated");
		        response.put("customers", adminList);
		        response.put("redirect", "admin/adminpage");
		    return ResponseEntity.status(HttpStatus.OK).body(response);
		
			}
			
		response.put("message", "Invalid credentials");
        response.put("statusText", "Authentication failed");
        response.put("redirect", "MyShop");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	
	
	 @PostMapping("/forgotAdmin")
	 public ResponseEntity<Map<String, Object>> forgotAdmin(@RequestBody Map<String, String> payload) {
		 Map<String, Object> response = new HashMap<>();	
		    try {
		    	adminService.resetAdminPass(payload);
		   
		        response.put("message", "Password change succesfully");
		        response.put("statusText", "Now you can login with new password");
		        response.put("redirect", "MyShop");
		        return ResponseEntity.status(HttpStatus.OK).body(response);
		    } catch (RuntimeException e) {
		        throw e;
		    }
		}
	 
	 @GetMapping("/catagory")
	 public ResponseEntity<Map<String, Object>> getAllCatagory()
	 {
		 Map< String, Object> response = new HashMap<>();
		 List<Catagory> list = catagoryService.getAllCatagories();
		 	response.put("message", "Fetch Succesfully");
	        response.put("statusText", "success");
	        response.put("customers", list);
	        response.put("redirect", "admin");
	    return ResponseEntity.status(HttpStatus.OK).body(response);

	 }
	 
	 @PostMapping("/catagory")
	 public ResponseEntity<Map<String, Object>> addCatagory(@RequestBody Catagory catagory)
	 {
		 
		 Map< String, Object> response = new HashMap<>();
		 Catagory list = catagoryService.addCatagory(catagory);
		 	response.put("message", "Catagory Added");
	        response.put("statusText", "success");
	        response.put("customers", list);
	        response.put("redirect", "admin");
	    return ResponseEntity.status(HttpStatus.OK).body(response);
	 }
	 
	 
	 
	 
	@GetMapping("/adminpage")
	public String getProductPage(){
		System.out.print("Open Admin");
		return "admin";
	}
	

}
