package kartik.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kartik.app.Entity.Credential;
import kartik.app.Service.CredService;

@RestController
@RequestMapping("/MyShop")
public class MainController {
	
	
	@Autowired
	private CredService credService;
	
	@PostMapping
	public ResponseEntity<Credential> addUser(@RequestBody Credential credential)
	{
		Credential credential2 = credService.addUser(credential);
		return ResponseEntity.status(HttpStatus.CREATED).body(credential2);
	}
	
	@GetMapping
	public ResponseEntity<List<Credential>> getAllUser(){
		List<Credential> users = credService.getAllCredential();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	

}
