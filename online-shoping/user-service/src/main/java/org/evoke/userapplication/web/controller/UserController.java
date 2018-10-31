package org.evoke.userapplication.web.controller;


import org.evoke.userapplication.model.UserDetails;
import org.evoke.userapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService uReg;
	
	/*@Autowired
	UserValidation uVal;*/
	
	@GetMapping("/check")
	public String check() {
		 return "successful";
	}
	
	@PostMapping(value = "/register")
	public boolean add(@RequestBody UserDetails user ) {
		/*Address address = new Address();
		address.setCity("hyd");
		List<Address> addList = new ArrayList<Address>();
		addList.add(address);
		user.setAddress(addList);*/
		return uReg.registerNewUserAccount(user);
		
	}
	
	
	/*@PostMapping(value = "/login")
	public boolean loginUser(@RequestParam String email,@RequestParam String password) {
		
		boolean res =  uVal.validateUser(email, password);
		
		return res;
		
	}*/
	

}
