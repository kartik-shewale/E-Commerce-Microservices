package kartik.app.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Address {

    @Id
    private String addressId;

    @Column(name = "cust_id", nullable = false) 
    private String custId;

    private String firstLine;
    private String secondLine;
    private String city;
    private String state;
    private String pincode;

    public Address() {
        super();
    }

    public Address(String addressId, String custId, String firstLine, String secondLine, String city, String state, String pincode) {
        this.addressId = addressId;
        this.custId = custId;
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }


    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public void setSecondLine(String secondLine) {
        this.secondLine = secondLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
