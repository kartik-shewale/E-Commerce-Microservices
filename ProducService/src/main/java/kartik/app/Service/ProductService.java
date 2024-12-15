package kartik.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kartik.app.Entity.Product;
import kartik.app.Exception.ResourceNotFoundException;
import kartik.app.Repository.ProductRepo;

@Service
public class ProductService implements ProductServiceInterface{

	
	@Autowired
	private ProductRepo productRepo;
	
	@Override
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Product getProductById(String id) {
		return productRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product Not Fount With Id "+id));
	}

	@Override
	public List<Product> getAllProduct() {
		return productRepo.findAll();
	}

	@Override
	public void deleteProductById(String id) {
		productRepo.deleteById(id);
	}

}
