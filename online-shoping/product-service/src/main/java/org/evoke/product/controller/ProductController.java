package org.evoke.product.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.evoke.product.model.Address;
import org.evoke.product.model.Category;
import org.evoke.product.model.Product;
import org.evoke.product.model.ProductRequest;
import org.evoke.product.model.ProductResponse;
import org.evoke.product.model.UserDetails;
import org.evoke.product.service.ProductService;
import org.evoke.product.util.ProductMapper;
import org.evoke.product.util.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@Autowired
	ProductMapper productMapper;
	
  
    private Response response;
    
    @PostConstruct
    public void init() {
    	response = new Response();
    }
    
	 
	@PostMapping
	public Response add(@RequestBody ProductRequest pr) {
		
		  try {
			ps.addProduct(pr);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(e.getMessage());
			  return response; 
		}
		  
		  response.setMessage("Successful");
		  return response; 
	}
	
	
	@PutMapping
	public ResponseEntity<Product> update(){
		
		
		
		return null;
		
		
	}
	

	@GetMapping("/all")
	public @ResponseBody List<ProductResponse> getProducts() {
		
		return  ps.getProducts();
		
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
