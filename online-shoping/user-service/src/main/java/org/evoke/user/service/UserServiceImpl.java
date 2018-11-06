package org.evoke.user.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.evoke.user.model.BaseResponse;
import org.evoke.user.model.UserDetails;
import org.evoke.user.persistence.dao.UserRepository;
import org.evoke.user.web.error.ErrorCode;
import org.evoke.user.web.error.ErrorDescription;
import org.evoke.user.web.error.ErrorType;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.evoke.user.model.Address;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository repository;

	@Autowired
	Session hibernateTemplate;

	@Override
	public BaseResponse registerUser(final UserDetails user) {

		System.out.println("Checking if the user already exists");
		BaseResponse response = null;
		Map<String, Object> mapObject = null;
		if (emailExist(user.getEmail())) {
			// throw new UserAlreadyExistException("Account already exists with this mail: "
			// + user.getEmail());

			response = new BaseResponse();
			response.setErrorCode(ErrorCode.EMAIL_ALREADY_EXISTS);
			response.setErrorDesc(ErrorDescription.USER_EMIAL_EXIST);
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

			return response;
		}

		System.out.println("Registering new user");

		try {
			final UserDetails newuser = new UserDetails();
			// final Address address = new Address();

			newuser.setFirstName(user.getFirstName());
			newuser.setLastName(user.getLastName());
			newuser.setPassword(passwordEncoder.encode(user.getPassword()));
			// System.out.println("Saving=>"+passwordEncoder.encode(user.getPassword()));
			newuser.setEmail(user.getEmail());
			newuser.setContactNumber(user.getContactNumber());
			newuser.setAddressLst(user.getAddressLst());
			newuser.setRoleName("CUSTOMER");
			newuser.setCreatedUser(user.getFirstName());
			newuser.setUpdatedUser(user.getFirstName());
			newuser.onCreate();
			if(null != newuser.getAddressLst() &&  newuser.getAddressLst().size()>0) {
				Address address = newuser.getAddressLst().get(0);
				address.onCreate();
				address.setCreatedUser(user.getFirstName());
				address.setUpdatedUser(user.getFirstName());
				
			}
			hibernateTemplate.saveOrUpdate(newuser);
			response = new BaseResponse();
			mapObject = new HashMap<String, Object>();
			//newuser.setPassword(null);
			mapObject.put("userDetails", newuser);
			response.setResponse(mapObject);
		//	hibernateTemplate.flush();

		} catch (Exception ex) {
			System.out.println("Exception in UserServiceImpl.registerUser() " + ex.getMessage());
			response = new BaseResponse();
			response.setErrorCode(ErrorCode.EMAIL_ALREADY_EXISTS);
			response.setErrorDesc(ex.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

		}
		return response;
	}

	public BaseResponse userLogin(UserDetails user) {
		BaseResponse response = null;
		Map<String, Object> mapObject = null;
		if (emailExist(user.getEmail())) {
			if (checkIfValidPassword(user)) {
				response = new BaseResponse();
				mapObject = new HashMap<String, Object>();
				user = repository.getUser(user.getEmail());
				if (null != user) {
					
					//user.setPassword(null);
					mapObject.put("userDetails", user);
					response.setResponse(mapObject);
				} else {

					response = new BaseResponse();
					response.setErrorCode(ErrorCode.USER_NOT_FOUND);
					response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
					response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				}

			} else {

				response = new BaseResponse();
				response.setErrorCode(ErrorCode.PASSWORD_NOT_VALID);
				response.setErrorDesc(ErrorDescription.PASSWORD_NOT_VALID);
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

			}

		} else {

			response = new BaseResponse();
			response.setErrorCode(ErrorCode.USER_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

		}
		return response;
	}

	public boolean emailExist(final String email) {

		return repository.findByEmail(email) != null;
	}

	public boolean checkIfValidPassword(UserDetails user) {

		// System.out.println(passwordEncoder.encode(user.getPassword()));
		// System.out.println(repository.getUserPassword(user.getEmail()));
		CharSequence pass = user.getPassword();
		return passwordEncoder.matches(pass, repository.getUserPassword(user.getEmail()));
	}

	@Override
	public BaseResponse getUser(int userId) {
		// TODO Auto-generated method stub
		UserDetails userDetails = null;
		BaseResponse response = null;
		Map<String, Object> mapObject = null;
		try {

			userDetails = hibernateTemplate.get(UserDetails.class, userId);

			if (null != userDetails) {
				mapObject = new HashMap<String, Object>();
				//userDetails.setPassword(null);
				mapObject.put("userDetails", userDetails);
				response = new BaseResponse();
				response.setResponse(mapObject);

			} else {

				response = new BaseResponse();
				response.setErrorCode(ErrorCode.USER_NOT_FOUND);
				response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

			}

		} catch (NumberFormatException ne) {

			response = new BaseResponse();
			response.setErrorCode(ErrorCode.VALID_NUMBER_REQUIRED);
			response.setErrorDesc(ErrorDescription.VALID_NUMBER_REQUIRED);
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

		} catch (Exception e) {
			response = new BaseResponse();
			response.setErrorCode(ErrorCode.USER_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

		}

		return response;
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

	/*
	 * @Override public VerificatlionToken getVerificationToken(String
	 * VerificationToken) { // TODO Auto-generated method stub return null; }
	 * 
	 * @Override public VerificationToken generateNewVerificationToken(String token)
	 * { // TODO Auto-generated method stub return null; }
	 */

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
