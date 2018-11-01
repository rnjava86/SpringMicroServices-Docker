package org.evoke.userapplication.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;


//import org.evoke.userapplication.model.Address;
import org.evoke.userapplication.model.UserDetails;
import org.evoke.userapplication.persistence.dao.UserRepository;
import org.evoke.userapplication.web.error.InvalidPasswordException;
import org.evoke.userapplication.web.error.UserAlreadyExistException;
import org.evoke.userapplication.web.error.UserNotFoundException;
import org.hibernate.Session;
//import org.evoke.userapplication.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserService implements IUserService {
	
	
	@Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	Session hibernateTemplate;
			
	
	@Override
	public boolean registerNewUserAccount(final UserDetails user) {
		
		System.out.println("Checking if the user already exists");
		
		if(emailExist(user.getEmail())) {
			throw new UserAlreadyExistException("Account already exists with this mail: " + user.getEmail());
		}
		
		System.out.println("Registering new user");
		
		try {
				final UserDetails newuser = new UserDetails();
				//final Address address = new Address();
				
				newuser.setFirstName(user.getFirstName());
				newuser.setLastName(user.getLastName());
				newuser.setPassword(passwordEncoder.encode(user.getPassword()));
				//System.out.println("Saving=>"+passwordEncoder.encode(user.getPassword()));
				newuser.setEmail(user.getEmail());
				newuser.setContactNumber(user.getContactNumber());
				newuser.setAddressLst(user.getAddressLst());	
				newuser.setRoleName("CUSTOMER");
				hibernateTemplate.saveOrUpdate(newuser);
				
				hibernateTemplate.flush();
				
				return true;
		
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
			
		}
	}
	
	
	public UserDetails userLogin(UserDetails user) {
		
		if(emailExist(user.getEmail())) {
			if(checkIfValidPassword(user)) {
				return repository.getUser(user.getEmail());
			}
			else
				throw new InvalidPasswordException("Entered passowrd is not valid..try again!!");
		}
		else
			throw new UserNotFoundException("Email id not registered..Please Register");
	}
	
	 public boolean emailExist(final String email) {
		 
		 
	        return repository.findByEmail(email) != null;
	 }
	 
	 
	 public boolean checkIfValidPassword(UserDetails user) {
		 
		 //System.out.println(passwordEncoder.encode(user.getPassword()));
		 //System.out.println(repository.getUserPassword(user.getEmail()));
		 CharSequence  pass = user.getPassword();
		 return passwordEncoder.matches(pass,
				 repository.getUserPassword(user.getEmail()));
	 }

	@Override
	public UserDetails getUser(String verificationToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRegisteredUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createVerificationTokenForUser(UserDetails user, String token) {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public VerificatlionToken getVerificationToken(String VerificationToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VerificationToken generateNewVerificationToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public void createPasswordResetTokenForUser(UserDetails user, String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDetails findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails getUserByPasswordResetToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeUserPassword(UserDetails user, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkIfValidOldPassword(UserDetails user, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String validateVerificationToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateQRUrl(UserDetails user) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails updateUser2FA(boolean use2fa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getUsersFromSessionRegistry() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}


