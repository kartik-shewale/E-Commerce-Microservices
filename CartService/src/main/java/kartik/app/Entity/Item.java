package kartik.app.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Item {

	@Id
	private	String itemId;
	private	String itemName;
	private	String itemDesc;
	private	int itemPrice;
	private	int itemQuantity;
	
	
	public Item() {
		super();
	}

	public Item(String itemId, String itemName, String itemDesc, int itemPrice, int itemQuantity) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	
	
	
	
	
	
}
