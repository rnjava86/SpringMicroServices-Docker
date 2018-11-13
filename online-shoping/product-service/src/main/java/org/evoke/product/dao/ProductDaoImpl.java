package org.evoke.product.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.evoke.product.model.BaseResponse;
import org.evoke.product.model.Category_product;
import org.evoke.product.model.Product;
import org.evoke.product.model.ProductResponse;
import org.evoke.product.model.User;
import org.evoke.product.model.User_product;
import org.evoke.product.util.DateUtil;
import org.evoke.product.util.ProductMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Transactional
@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private HibernateTemplate ht;

	@Autowired
	private SessionFactory sf;

	private Category_product cp;

	private User_product up;

	private ProductMapper productMapper;

	@Autowired
	Session session;

	@PostConstruct
	public void init() {
		cp = new Category_product();
		up = new User_product();
		productMapper = new ProductMapper();
	}

	@Transactional
	public ProductResponse addProduct(Product product) {

		
		product.setCreatedUser(product.getUser().getFirstName());
		product.setUpdatedUser(product.getUser().getFirstName());
		product.setCreatedDate(DateUtil.getDDMMYYDate());
		product.setUpdatedDate(DateUtil.getDDMMYYDate());
		session.saveOrUpdate(product);
		session.flush();
		session.evict(product);
		ProductResponse productResponse = new ProductResponse();
		List<Product> products = new ArrayList<Product>();
		products.add(product);
		productResponse.setProductLst(products);
		return productResponse;
	}

	public ProductResponse getProducts() {

		ProductResponse response =  new ProductResponse();
		
		List<Product> productList = (List<Product>) ht.find("from Product");

		List<Product> productResList = new ArrayList<Product>();
		if (!CollectionUtils.isEmpty(productList)) {
			productMapper.map(productList, productResList, ht);
		}
		response.setProductLst(productResList);
		return response;
	}

	public Product getProductById(int id) {

		return ht.get(Product.class, id);

	}

	public List<Product> getProductsByUserId(int id) {

		User ud = ht.get(User.class, id);
		return null;// ud.getProducts();

	}

}
