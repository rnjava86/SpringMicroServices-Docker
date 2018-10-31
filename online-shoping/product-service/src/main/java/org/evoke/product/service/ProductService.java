package org.evoke.product.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.evoke.product.dao.ProductDao;
import org.evoke.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
		
	@Autowired
    private ProductDao productDao;
	
	  
	  public void addProduct(Product product,MultipartFile file) throws IOException {
		
		   productDao.addProduct(product,file);
		   
	  }
	  
	  
	  public List<Product> getProducts() {
		  return productDao.getProducts();
	  }
	  
	  public Product getProductById(int id) {
		  return productDao.getProductById(id);
	  }
	  
	  public List<Product> getProductsByUserId(int id) {
		  return productDao.getProductsByUserId(id);
	  }
	  
	
}
