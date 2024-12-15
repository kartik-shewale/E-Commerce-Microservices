package kartik.app.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kartik.app.Entity.Product;
import kartik.app.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService pService;
	
	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product)
	{
		
		System.out.print(product.getName());
		System.out.print(product.getCatagory());
		System.out.print(product.getDescription());
		System.out.print(product.getId());
		System.out.print(product.getQuantity());

		String idString = UUID.randomUUID().toString();
		product.setId(idString);
		Product product2 = pService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(product2);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable String id)
	{
		Product product2 = pService.getProductById(id);
		return ResponseEntity.status(HttpStatus.OK).body(product2);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProduct()
	{
		List<Product> product2 = pService.getAllProduct();
		return ResponseEntity.status(HttpStatus.CREATED).body(product2);
	}

}
