package org.evoke.product.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.evoke.product.model.Category_product;
import org.evoke.product.model.Product;
import org.evoke.product.model.ProductResponse;
import org.evoke.product.model.UserDetails;
import org.evoke.product.model.User_product;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.evoke.product.util.ProductMapper;

@Transactional
@Repository
public class ProductDao {
	
	  @Autowired
	    private ServletContext servletContext;
	  
	  @Autowired
	  private HibernateTemplate ht;
	  
	  @Autowired
	  private SessionFactory sf;
	  
	  private Category_product cp;
	  
	  private User_product up;
	  
	  private ProductMapper productMapper;
		
	  @PostConstruct
	  public void init() {
		    cp = new Category_product();
		    up = new User_product();
		    productMapper = new ProductMapper();
		}

	  @Transactional
	  public void addProduct(Product product,int category_id,int user_id/*,MultipartFile file*/) /*throws IOException*/ {
		   
		  //save prodcut info
		   int id = (int) ht.save(product);
		   
		   //save category_product
		   cp.setProduct_id(id);
		   cp.setCategory_id(category_id);
		   ht.save(cp);
		   
		   //save user_product
		   up.setProduct_id(id);
		   up.setUser_id(user_id);
		   ht.save(up);
		
		   /*//save image
		 String UPLOADED_FOLDER = servletContext.getContextPath();
		 
		 byte[] bytes = file.getBytes();
		 Path path = Paths.get(UPLOADED_FOLDER +id+"_"+file.getOriginalFilename());
		 Files.write(path, bytes);
	            
		 //save img_path
		 product.setImg_path(id+"_"+file.getOriginalFilename());
		  ht.update(product);*/
		  
	  }
	 
	  
	  public List<ProductResponse> getProducts() {
         
		 List<Product> productList =(List<Product>) ht.find("from Product");
		 
		 List<ProductResponse> productResList = new ArrayList<ProductResponse>();
			if(!CollectionUtils.isEmpty(productList)) {
				productMapper.map(productList, productResList,ht);
			}
         
		  return productResList;
	  }
	  
	  
	  public Product getProductById(int id) {
	         
		  return ht.get(Product.class,id);
			  
		  }
	  

	  public List<Product> getProductsByUserId(int id) {
	         
		  UserDetails ud = ht.get(UserDetails.class,id);
		 return null;//ud.getProducts();
			  
		  }
	  
	
}
