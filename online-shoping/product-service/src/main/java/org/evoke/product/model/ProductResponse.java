package org.evoke.product.model;

public class ProductResponse {
	
	private int product_id;
	
	private UserDetails user;
	
	private Address address;
	
	private Category category;
	
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


	/**
	 * @return the user
	 */
	public UserDetails getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserDetails user) {
		this.user = user;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	

}
