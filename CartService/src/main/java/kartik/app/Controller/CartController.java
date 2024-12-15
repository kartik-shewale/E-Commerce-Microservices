package kartik.app.Controller;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kartik.app.Entity.Item;
import kartik.app.Service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService service;
	
	
	@PostMapping
	public ResponseEntity<Item> addItem(@RequestBody Item item){
		String id = UUID.randomUUID().toString();
		item.setItemId(id);
		Item item2 = service.addItem(item);
		return ResponseEntity.status(HttpStatus.CREATED).body(item2);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable String id){
		Item item = service.getItemById(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(item);
	}
	
	@GetMapping
	public ResponseEntity<List<Item>> getAllItem(){
		List<Item> items = service.getAllItem();
		return ResponseEntity.status(HttpStatus.CREATED).body(items);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable String id) {
		service.deleteItem(id);
		return ResponseEntity.ok().build();
	}
	

}
