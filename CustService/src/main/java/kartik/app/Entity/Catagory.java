package kartik.app.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Catagory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int catagoryId;

	@Column(unique = true)
	private String category;

	private String catagaoryDesc;

	public Catagory(int catagoryId, String category, String catagaoryDesc) {
		super();
		this.catagoryId = catagoryId;
		this.category = category;
		this.catagaoryDesc = catagaoryDesc;
	}

	public Catagory() {
		super();
	}

	public int getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(int catagoryId) {
		this.catagoryId = catagoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCatagaoryDesc() {
		return catagaoryDesc;
	}

	public void setCatagaoryDesc(String catagaoryDesc) {
		this.catagaoryDesc = catagaoryDesc;
	}

}
