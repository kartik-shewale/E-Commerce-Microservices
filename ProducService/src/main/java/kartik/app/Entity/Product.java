package kartik.app.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    private String id;

    private String name;

    private String description;

    private int price;

    private int quantity;

    private String category; // Ensure this matches the JSON field name "category"

    public Product() {
        super();
    }

    public Product(String id, String name, String description, int price, int quantity, String category) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category; // Ensure the constructor uses "category"
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {  // Getter method should match field name
        return category;
    }

    public void setCategory(String category) {  // Setter method should match field name
        this.category = category;
    }
}
