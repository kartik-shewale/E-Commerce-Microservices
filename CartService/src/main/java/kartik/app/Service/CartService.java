package kartik.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kartik.app.Entity.Item;
import kartik.app.Exception.ResourceNotFoundException;
import kartik.app.Repository.CartRepo;

@Service
public class CartService implements CartServiceInterface{

	@Autowired
	private CartRepo cartRepo;
	
	@Override
	public Item addItem(Item item) {
		return cartRepo.save(item);
	}

	@Override
	public Item getItemById(String id) {
		return cartRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Item Not Found With Id "+ id));
	}

	@Override
	public List<Item> getAllItem() {
		return cartRepo.findAll();
	}

	@Override
	public void deleteItem(String id) {
		cartRepo.deleteById(id);
	}
	
	

}
