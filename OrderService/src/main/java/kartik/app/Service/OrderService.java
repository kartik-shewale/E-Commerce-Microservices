package kartik.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kartik.app.Entity.Order;
import kartik.app.Entity.OrderItem;
import kartik.app.Exception.ResourceNotFoundException;
import kartik.app.Repository.OrderItemRepo;
import kartik.app.Repository.OrderRepo;

@Service
public class OrderService implements OrderServiceInterface{

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private OrderItemRepo orderItemRepo;
	
	
	@Override
	public Order addOrder(Order order) {
		return orderRepo.save(order);
	}

	@Override
	public Order getOrderById(String id) {
		return orderRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("No Order Found With Id "+id));
	}

	@Override
	public List<Order> getAllOrder() {
		return orderRepo.findAll();
	}

	@Override
	public void deleteOrder(String id) {
		orderRepo.deleteById(id);
		
	}
	
	public List<Order> getOrderByCustomerId(String custId)
	{
		return orderRepo.findByCustomerId(custId);
	}

	@Transactional
	public boolean changeOrderStatus(String orderId,String status) {
		return (orderRepo.changeStatus(orderId, status) > 0);
	}
	
	public OrderItem saveOrderItem(OrderItem item) {
		return orderItemRepo.save(item);
		
	}

}
