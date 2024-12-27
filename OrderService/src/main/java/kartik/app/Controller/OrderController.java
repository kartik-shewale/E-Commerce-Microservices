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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kartik.app.Entity.Order;
import kartik.app.Service.OrderService;



@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<Order> addOrder(@RequestBody Order order){
		String id = UUID.randomUUID().toString();
		order.setOrderId(id);
		Order order2 = orderService.addOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(order2);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable String id){
		Order order = orderService.getOrderById(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}
	
	@GetMapping
	public ResponseEntity<List<Order>> getAllOrder(){
		List<Order> orders = orderService.getAllOrder();
		return ResponseEntity.status(HttpStatus.CREATED).body(orders);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
		orderService.deleteOrder(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/cust/{id}")
	public ResponseEntity<List<Order>> getOrderByCustId(@PathVariable String id)
	{
		List<Order> list = orderService.getOrderByCustomerId(id);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping("/status")
	public ResponseEntity<Boolean> changeOrderStatus(@RequestParam String id,@RequestParam String status)
	{
		boolean flag = orderService.changeOrderStatus(id,status);
		return ResponseEntity.ok(flag); 
	}
	

}
