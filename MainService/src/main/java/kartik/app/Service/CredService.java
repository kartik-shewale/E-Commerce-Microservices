package kartik.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kartik.app.Entity.Credential;
import kartik.app.Repository.CredRepo;

@Service
public class CredService {
	
	
	@Autowired
	private CredRepo credRepo;
	
	public List<Credential> getAllCredential() {
		return credRepo.findAll();
	}
	
	public Credential addUser(Credential credential)
	{
		return credRepo.save(credential);
	}
	
	public Credential findByUserName(String usrename) {
		return findByUserName(usrename);
	}
	

}
