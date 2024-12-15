package kartik.app.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admin {
	
	@Id
    private String adminId;
    
    private String fName;
    private String lName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "mobile", unique = true)
    private String mobile;

    private String password;

    @Column(name = "userName", unique = true)
    private String AdminName;

	public Admin(String adminId, String fName, String lName, String email, String mobile, String password,
			String adminName) {
		super();
	    this.adminId = adminId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		AdminName = adminName;
	}

	public Admin() {
		super();
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
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

	public String getAdminName() {
		return AdminName;
	}

	public void setAdminName(String adminName) {
		AdminName = adminName;
	}
    

    
}
