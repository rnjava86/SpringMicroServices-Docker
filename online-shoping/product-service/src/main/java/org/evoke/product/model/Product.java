package org.evoke.product.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "product")
public class Product implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserDetails user;
	
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	@NotNull
	private String product_name;
	
	private String img_path;
	
	@NotNull
	private double price ;
	
	@NotNull
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

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
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

	
	//private Address sellar_address;
	

}
