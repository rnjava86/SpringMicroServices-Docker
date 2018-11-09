package org.evoke.product.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.hibernate.validator.constraints.NotBlank;


public class ProductRequest implements Serializable{
	
	private int product_id;
	
	private int user_id;
	
	private int category_id;
	
	private String product_name;
	
	//private MultipartFile img;
	
	private double price ;
	
	
	private int year_of_purchase;
	
	private String posted_date;
	
	private String description;
	
	private String condition_product;

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getYear_of_purchase() {
		return year_of_purchase;
	}

	public void setYear_of_purchase(int year_of_purchase) {
		this.year_of_purchase = year_of_purchase;
	}

	public String getPosted_date() {
		return posted_date;
	}

	public void setPosted_date(String posted_date) {
		this.posted_date = posted_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCondition_product() {
		return condition_product;
	}

	public void setCondition_product(String condition_product) {
		this.condition_product = condition_product;
	}

	
	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	

}
