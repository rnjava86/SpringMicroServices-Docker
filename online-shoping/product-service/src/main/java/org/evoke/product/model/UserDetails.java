package org.evoke.product.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "userdetails")
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
	@Column(unique = true)
	private String email;
	
	@NotBlank(message = "Please enter contact number!")
	@Column(name = "contact_number")
	@Size(min =10, max= 10)
	//@Pattern(regexp="(^$|[0-9]{10})") 
	private String contactNumber;
	
	@NotBlank(message = "Please enter password!")
	private String password;
	
	//private Role role;
	@Column(name = "role_name")
	private String roleName;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ADDRESS", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ADDRESS_ID") })
	private List<Address> addressLst;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_PRODUCT", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "PRODUCT_ID") })
	private List<Product> ProductLst;
	
	
	public int getId() {
		return id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	
/*	public List<Address> getAddressLst() {
		return addressLst;
	}
	public void setAddressLst(List<Address> addressLst) {
		this.addressLst = addressLst;
	}

	public List<Product> getProductLst() {
		return ProductLst;
	}
	public void setProductLst(List<Product> productLst) {
		ProductLst = productLst;
	}*/
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNumber=" + contactNumber + "password=" + password 
				+ "]";
	}
	
	
}
