package kartik.app.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "OrderTable")
public class Order {
	
	@Id
	private String orderId;
	private String addresId;
	private String customerId;
	private String paymentId;
	private int amount;
	private LocalDateTime orderDate;
	private LocalDateTime dilivaryDate;
	private String status;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
	private List<OrderItem> list;


	public Order() {
		super();
	}


	public Order(String orderId, String addresId, String customerId, String paymentId, int amount, LocalDateTime orderDate,
			LocalDateTime dilivaryDate, String status,List<OrderItem> list) {
		super();
		this.orderId = orderId;
		this.addresId = addresId;
		this.customerId = customerId;
		this.paymentId = paymentId;
		this.amount = amount;
		this.orderDate = orderDate;
		this.dilivaryDate = dilivaryDate;
		this.status = status;
		this.list=list;
	}


	public List<OrderItem> getList() {
		return list;
	}


	public void setList(List<OrderItem> list) {
		this.list = list;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getAddresId() {
		return addresId;
	}


	public void setAddresId(String addresId) {
		this.addresId = addresId;
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


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public LocalDateTime getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}


	public LocalDateTime getDilivaryDate() {
		return dilivaryDate;
	}


	public void setDilivaryDate(LocalDateTime dilivaryDate) {
		this.dilivaryDate = dilivaryDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
}
