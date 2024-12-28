package kartik.app.Controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kartik.app.Entity.Order;
import kartik.app.Entity.OrderItem;
import kartik.app.Service.OrderService;



@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<Order> addOrder(@RequestBody Order order){
//		String id = UUID.randomUUID().toString();
//		order.setOrderId(id);
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
	
	@PostMapping("/addOrder")
	public ResponseEntity<OrderItem> saveOrderItem(@RequestBody Map<String, Object> map){
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId((String) map.get("itemId"));
		orderItem.setItemDesc((String) map.get("itemDesc"));
		orderItem.setItemName((String) map.get("itemName"));
		orderItem.setItemQuantity((Integer) map.get("itemQuantity"));
		orderItem.setUserId((String) map.get("userId"));
		orderItem.setOrderId((String) map.get("orderId"));
		orderItem.setItemPrice((Integer) map.get("itemPrice"));
		
//		System.out.println((String) map.get("itemId"));
//		System.out.println((String) map.get("itemDesc"));
//		System.out.println((String) map.get("itemName"));
//		System.out.println((Integer) map.get("itemQuantity"));
//		System.out.println((String) map.get("userId"));
//		System.out.println((String) map.get("orderId"));
//		System.out.println((Integer) map.get("itemPrice"));
		
		
		OrderItem orderItem2 = orderService.saveOrderItem(orderItem);
		return ResponseEntity.ok().body(orderItem2);
	}

}
