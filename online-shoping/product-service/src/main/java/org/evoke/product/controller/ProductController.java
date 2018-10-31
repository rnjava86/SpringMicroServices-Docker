package org.evoke.product.controller;


import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.evoke.product.model.Address;
import org.evoke.product.model.Product;
import org.evoke.product.model.UserDetails;
import org.evoke.product.service.ProductService;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="/product")
@RequestScope
public class ProductController {
	
	@Autowired
	ProductService ps;

    private Product product;
    private UserDetails user;
    private Address address;
    
    @PostConstruct
    public void init() {
    	product = new Product(); // In case "new entry" is required.
    	user = new UserDetails();
    	address = new Address();
    }
    
	 
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Product> add(@FormDataParam("description") String description,@FormDataParam("user_id") int user_id,@FormDataParam("address_id") int address_id,@FormDataParam("file") MultipartFile  file,
			@FormDataParam("product_name") String product_name,@FormDataParam("price") double price,@FormDataParam("year_of_purchase") int year_of_purchase,
			@FormDataParam("posted_date") String posted_date,@FormDataParam("condition") String condition) {
		
	         user.setId(user_id);
	         address.setId(address_id);
	         
	         product.setUser(user);
		  product.setAddress(address); 
		  product.setCondition_product(condition);
		  product.setDescription(description);
		  product.setPosted_date(posted_date);
		  product.setPrice(price);
		  product.setProduct_name(product_name);
		  product.setYear_of_purchase(year_of_purchase);
		 
		  try {
			ps.addProduct(product,file);
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		  
		  return new ResponseEntity<>(product ,HttpStatus.OK); 
	}
	
	
	@PutMapping
	public ResponseEntity<Product> update(){
		
		
		
		return null;
		
		
	}
	

	@GetMapping("/all")
	public @ResponseBody List<Product> getProducts() {
		
		List<Product> pList =  ps.getProducts();
		return pList;
	}

	@GetMapping("{product_id}")
	public @ResponseBody Product getProductById(@PathVariable("product_id") int product_id) {
		
		return ps.getProductById(product_id);
	}
	
	@GetMapping("byUserId/{user_id}")
	public @ResponseBody List<Product> getProductsByUserId(@PathVariable("user_id") int user_id) {
		
		List<Product> pList =  (List<Product>) ps.getProductsByUserId(user_id);
		
		return pList;
	} 
	
	
}
