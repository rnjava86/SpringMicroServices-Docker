package org.evoke.user.persistence.dao;

import org.evoke.user.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserDetails, Long>{
	
	UserDetails findByEmail(String email);
	
	@Query(value = "SELECT password FROM userdetails u WHERE u.email= ?1" , 
			nativeQuery = true)
	String getUserPassword(String email);
	
	@Query(value = "SELECT * FROM userdetails u WHERE u.email = ?1",
			nativeQuery = true)
	UserDetails getUser(String email);

}
