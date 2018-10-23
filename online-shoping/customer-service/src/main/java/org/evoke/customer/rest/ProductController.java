package org.evoke.customer.rest;


import java.util.LinkedList;
import java.util.List;

import org.evoke.customer.domain.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private List<Product> products;

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = "application/json")
    public List<Product> getProduct() {

    	products = new LinkedList<>();
    	products.add(new Product(1, "Peter", 121));
    	products.add(new Product(2, "Peter",12121));
        return products;

    }

    @ResponseBody
	@RequestMapping("/")
	String entry() {
		
		return "Hello World";
	}
}