package kartik.app.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import jakarta.transaction.Transactional;
import kartik.app.Entity.Admin;
import kartik.app.Entity.Order;
import kartik.app.Exception.ResourceNotFoundException;
import kartik.app.Repository.AdminRepo;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Transactional
	public boolean resetAdminPass(@RequestBody Map<String, String> payload)
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
        	if(!adminRepo.existsByMobile(username)) {
        		throw new ResourceNotFoundException("Account with given mobile number "+ username+" does not exist ");
        	}else {
        		adminRepo.changeAdminPasswordByMobile(username, password);
			}
        	
        }else {
        	if(!adminRepo.existsByEmail(username)) {
        		throw new ResourceNotFoundException("Account with given email "+ username+" does not exist ");
        	}else {
        		adminRepo.changeAdminPasswordByEmail(username, password);
			}	
        }	
        return true;
		
	}

	public Admin findByUserName(String username) {
		return adminRepo.findByAdminName(username);
	}

	public List<Order> getAllOrder() {
		Order[] arr = restTemplate.getForObject("http://localhost:1004/order", Order[].class);
		return Arrays.stream(arr).toList();
	}
	
	public Order getOrderById(String id) {
		return restTemplate.getForObject("http://localhost:1004/order/"+id, Order.class);
	}


}
