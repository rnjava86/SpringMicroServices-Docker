package org.evoke.userapplication.web.controller;


import org.evoke.userapplication.model.UserDetails;
import org.evoke.userapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
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
		return userService.registerNewUserAccount(user);
		
	}
	
	/*@GetMapping(value = "/getUser/{userId}")
	public UserDetails add(@PathVariable("userId") String  userId ) {
		Address address = new Address();
		address.setCity("hyd");
		List<Address> addList = new ArrayList<Address>();
		addList.add(address);
		user.setAddress(addList);
		return userService.getUser(userId);
		
	}*/
	
	@PostMapping(value = "/login")
	public UserDetails loginUser(@RequestBody UserDetails user) {
		//System.out.println(user.getEmail()+": "+user.getPassword());
		return userService.userLogin(user);
		
	}
	

}
