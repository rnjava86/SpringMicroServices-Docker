package org.evoke.product.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.evoke.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class ProductDao {
	
	  @Autowired
	    private ServletContext servletContext;
	  
	  @Autowired
	  private HibernateTemplate ht;

	  @Transactional
	  public void addProduct(Product product,MultipartFile file) throws IOException {
		   
		  //save prodcut info
		   int id = (int) ht.save(product);
		
		   //save image
		 String UPLOADED_FOLDER = servletContext.getContextPath();
		 
		 byte[] bytes = file.getBytes();
		 Path path = Paths.get(UPLOADED_FOLDER +id+"_"+file.getOriginalFilename());
		 Files.write(path, bytes);
	            
		 //save img_path
		 product.setImg_path(id+"_"+file.getOriginalFilename());
		  ht.update(product);
		  
	  }
	 
	  
	  public List<Product> getProducts() {
         
		 List<Product> pl =(List<Product>) ht.find("from Product");
		  
		  return pl;
	  }
	  
	  
	  public Product getProductById(int id) {
	         
		  return ht.get(Product.class,id);
			  
		  }
	  
	
}
