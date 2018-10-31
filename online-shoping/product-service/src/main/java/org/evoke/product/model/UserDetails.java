package org.evoke.product.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "user_details")
public class UserDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	
	@NotBlank(message = "Please enter first name!")
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank(message = "Please enter last name!")
	@Column(name = "last_name")
	private String lastName;
	
	@NotBlank(message = "Please enter email address!")	
	@Email
	private String email;
	
	@NotBlank(message = "Please enter contact number!")
	@Column(name = "contact_number")
	@Size(min =10, max= 10)
	//@Pattern(regexp="(^$|[0-9]{10})") 
	private String contactNumber;
	
	@NotBlank(message = "Please enter contact number!")
	private String password;
	
	@JoinColumn(name = "user_id")
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Address> address;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private List<Product> products;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	
	
	/*public void addAddressToUser(Address addr) {
		this.address.add(addr);
		addr.setUser(this);
	}*/
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNumber=" + contactNumber + "password=" + password 
				+ "]";
	}
	
	
	/*@OneToOne(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Cart cart;
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}*/
	
}