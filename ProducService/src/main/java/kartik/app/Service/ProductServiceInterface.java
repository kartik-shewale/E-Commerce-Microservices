package kartik.app.Service;

import java.util.List;

import kartik.app.Entity.Product;

public interface ProductServiceInterface {
	
	public Product addProduct(Product product);
	
	public Product getProductById(String id);
	
	public List<Product> getAllProduct();
	
	public void deleteProductById(String id);

}
