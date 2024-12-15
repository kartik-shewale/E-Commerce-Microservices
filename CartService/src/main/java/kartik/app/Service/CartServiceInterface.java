package kartik.app.Service;
import java.util.List;
import kartik.app.Entity.Item;

public interface CartServiceInterface {
	
	public Item addItem(Item item);
	
	public Item getItemById(String item);
	
	public List<Item> getAllItem();
	
	public void deleteItem(String id);
}
