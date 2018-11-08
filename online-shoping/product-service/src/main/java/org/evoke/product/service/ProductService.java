package org.evoke.product.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.evoke.product.dao.ProductDao;
import org.evoke.product.model.Category_product;
import org.evoke.product.model.Product;
import org.evoke.product.model.ProductRequest;
import org.evoke.product.model.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequestScope
public class ProductService {
		
	@Autowired
    private ProductDao productDao;
	
	private Product product;
	
	 @PostConstruct
	 public void init() {
	    	product = new Product();
	 }
	  
	 
	 public void addProduct(ProductRequest pr) {
		  
		  product.setProduct_id(pr.getProduct_id());
		  product.setProduct_name(pr.getProduct_name());
		  product.setDescription(pr.getDescription());
		  product.setPosted_date(pr.getPosted_date());
		  product.setPrice(pr.getPrice());
		  product.setCondition_product(pr.getCondition_product());
		  product.setYear_of_purchase(pr.getYear_of_purchase());
		  //product.setImgPath
		  
		   productDao.addProduct(product,pr.getCategory_id(),pr.getUser_id());
		   
	  }
	  
	  
	  public List<ProductResponse> getProducts() {
		  return productDao.getProducts();
	  }
	  
	  public Product getProductById(int id) {
		  return productDao.getProductById(id);
	  }
	  
	  public List<Product> getProductsByUserId(int id) {
		  return productDao.getProductsByUserId(id);
	  }
	  
	
}
