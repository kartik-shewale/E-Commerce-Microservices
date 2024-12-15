package kartik.app.Entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OrderTable")
public class Order {
	
	@Id
	private String orderId;
	private String productId;
	private String customerId;
	private String paymentId;
	private String address;
	private int amount;
	private Date orderDate;
	
	public Order() {
		super();
	}
	public Order(String orderId, String productId, String customerId, String paymentId, String address, int amount,
			Date orderDate) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.customerId = customerId;
		this.paymentId = paymentId;
		this.address = address;
		this.amount = amount;
		this.orderDate = orderDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
	


}
