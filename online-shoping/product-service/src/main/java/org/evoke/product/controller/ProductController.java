package org.evoke.product.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.evoke.product.error.ErrorCode;
import org.evoke.product.error.ErrorType;
import org.evoke.product.model.BaseResponse;
import org.evoke.product.model.LoginRequest;
import org.evoke.product.model.Product;
import org.evoke.product.model.ProductRequest;
import org.evoke.product.model.ProductResponse;
import org.evoke.product.model.User;
import org.evoke.product.model.UserResponse;
import org.evoke.product.service.ProductServiceImpl;
import org.evoke.product.util.ProductMapper;
import org.evoke.product.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping(value = "/product")
@RequestScope
public class ProductController {

	@Autowired
	ProductServiceImpl ps;

	@Autowired
	ProductMapper productMapper;

	private Response response;

	@PostConstruct
	public void init() {
		response = new Response();
	}

	@Autowired
	private UserService userService;

	@GetMapping("/check")
	public void getCheck() throws RestClientException, IOException {

		try {
			String str = userService.getCheck();
			System.out.println(str);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@GetMapping("/loginUser")
	public UserResponse getLoginUser() throws RestClientException, IOException {

		UserResponse response = null;
		try {

			User user = new User();
			user.setEmail("string@email.com");
			user.setPassword("password");
			LoginRequest loginRequest = new LoginRequest();
			loginRequest.setUser(user);
			response = userService.loginUser(loginRequest);

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return response;
	}

	@PostMapping
	public ProductResponse add(@RequestBody ProductRequest pRequest) {

		UserResponse userResponse = null;
		ProductResponse response = null ;
		
		try {
			
			
			User user =pRequest.getProduct().getUser();			
			LoginRequest loginRequest = new LoginRequest();
			loginRequest.setUser(user);
			userResponse = userService.getUser(loginRequest);
			
			if (null != userResponse && null != userResponse.getUserLst() && userResponse.getUserLst().size()>0) {

				pRequest.getProduct().setUser(userResponse.getUserLst().get(0));;
				response = ps.addProduct(pRequest);
			}
			

			
		} catch (Exception e) {
			response = new ProductResponse();
			response.setErrorCode(ErrorCode.PRODUCT_NOT_VALID);
			response.setErrorDesc(e.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
			return response;
		}

		return response;
	}

	@PutMapping
	public ResponseEntity<Product> update() {

		return null;

	}

	@GetMapping("/all")
	public @ResponseBody ProductResponse getProducts() {

		return ps.getProducts();

	}

	@GetMapping("{product_id}")
	public @ResponseBody Product getProductById(@PathVariable("product_id") int product_id) {

		return ps.getProductById(product_id);
	}

	@GetMapping("byUserId/{user_id}")
	public @ResponseBody List<Product> getProductsByUserId(@PathVariable("user_id") int user_id) {

		List<Product> pList = (List<Product>) ps.getProductsByUserId(user_id);

		return pList;
	}

}
