package kartik.app.Service;

import java.util.List;

import kartik.app.Entity.Order;

public interface OrderServiceInterface {
	
	public Order addOrder(Order order);
	
	public Order getOrderById(String id);
	
	public List<Order> getAllOrder();
	
	public void deleteOrder(String id);

}
