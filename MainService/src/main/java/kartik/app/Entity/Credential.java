package kartik.app.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Credential {
	
	
	@Id
	private String custId;
	private String userName;
	private String password;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Credential(String userName, String password, String custId) {
		super();
		this.userName = userName;
		this.password = password;
		this.custId=custId;
	}

	public Credential() {
		super();
	}
	
	

}
