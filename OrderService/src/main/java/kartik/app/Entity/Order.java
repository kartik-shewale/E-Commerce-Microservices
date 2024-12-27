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
	private String addresId;
	private String customerId;
	private String paymentId;
	private int amount;
	private Date orderDate;
	private Date dilivaryDate;
	private String status;
	
	
	public Order() {
		super();
	}


	public Order(String orderId, String addresId, String customerId, String paymentId, int amount, Date orderDate,
			Date dilivaryDate,String status ) {
		super();
		this.orderId = orderId;
		this.addresId = addresId;
		this.customerId = customerId;
		this.paymentId = paymentId;
		this.amount = amount;
		this.orderDate = orderDate;
		this.dilivaryDate = dilivaryDate;
		this.status=status;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public Date getDilivaryDate() {
		return dilivaryDate;
	}


	public void setDilivaryDate(Date dilivaryDate) {
		this.dilivaryDate = dilivaryDate;
	}
	
	
}
