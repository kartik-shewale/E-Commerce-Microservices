package kartik.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kartik.app.Entity.Catagory;
import kartik.app.Repository.CatagoryRepo;

@Service
public class CatagoryService {
	
	@Autowired
	private CatagoryRepo catagoryRepo;
	
	public List<Catagory> getAllCatagories() {
		return catagoryRepo.findAll();		
	}
	
	public Catagory addCatagory(Catagory catagory) {
		return catagoryRepo.save(catagory);
	}
	
}
