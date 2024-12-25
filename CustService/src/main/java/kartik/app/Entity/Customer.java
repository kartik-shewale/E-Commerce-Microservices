package kartik.app.Entity;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	
	
	@Id
    private String custId;
    
    private String fName;
    private String lName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "mobile", unique = true)
    private String mobile;

    private String password;

    @Column(name = "userName", unique = true)
    private String userName;
    
    @Column(name = "userImage", columnDefinition = "LONGTEXT")
    private String userImage;
	
    @OneToMany
    @JoinColumn(name = "cust_id")
    private List<Address> address;

	
	
	public Customer() {
		super();
	}
	
	public Customer(String custId, String fName, String lName, String email, String mobile, String password,String userName,String userImage,List<Address> address) {
		super();
		this.custId = custId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.userName=userName;
		this.userImage=userImage;
		this.address=address;
	}
	
	
	
	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
