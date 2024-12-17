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
	private String catagory;
	
	private String catagaoryDesc;

	public Catagory(int catagoryId, String catagory, String catagaoryDesc) {
		super();
		this.catagoryId = catagoryId;
		this.catagory = catagory;
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

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getCatagaoryDesc() {
		return catagaoryDesc;
	}

	public void setCatagaoryDesc(String catagaoryDesc) {
		this.catagaoryDesc = catagaoryDesc;
	}
	
	
	
	

}
