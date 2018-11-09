package org.evoke.user.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.evoke.user.model.Address;
import org.evoke.user.model.BaseResponse;
import org.evoke.user.model.Role;
import org.evoke.user.model.RoleEnum;
import org.evoke.user.model.User;
import org.evoke.user.persistence.dao.UserRepository;
import org.evoke.user.web.error.ErrorCode;
import org.evoke.user.web.error.ErrorDescription;
import org.evoke.user.web.error.ErrorType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository repository;

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	Session session;

	@Override
	public BaseResponse registerUser(final User user) {

		System.out.println("Checking if the user already exists");
		BaseResponse response = null;
		Map<String, Object> mapObject = null;
		if (emailExist(user.getEmail())) {

			response = new BaseResponse();
			response.setErrorCode(ErrorCode.EMAIL_ALREADY_EXISTS);
			response.setErrorDesc(ErrorDescription.USER_EMIAL_EXIST);
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

			return response;
		}

		System.out.println("Registering new user");

		try {
			final User newuser = new User();
			List<Role> roleLst = new ArrayList<Role>();
			Role role = new Role(RoleEnum.CUSTOMER);
			roleLst.add(role);
			newuser.setFirstName(user.getFirstName());
			newuser.setLastName(user.getLastName());
			newuser.setPassword(passwordEncoder.encode(user.getPassword()));

			newuser.setEmail(user.getEmail());
			newuser.setContactNumber(user.getContactNumber());
			newuser.setAddressLst(user.getAddressLst());
			newuser.setRoleLst(roleLst);
			newuser.setCreatedUser(user.getFirstName());
			newuser.setUpdatedUser(user.getFirstName());
			newuser.onCreate();
			if (null != newuser.getAddressLst() && newuser.getAddressLst().size() > 0) {
				Address address = newuser.getAddressLst().get(0);
				address.onCreate();
				address.setCreatedUser(user.getFirstName());
				address.setUpdatedUser(user.getFirstName());

			}
			session.saveOrUpdate(newuser);
			session.flush();
			response = new BaseResponse();
			mapObject = new HashMap<String, Object>();
			newuser.setPassword(null);
			mapObject.put("user", newuser);
			response.setResponse(mapObject);

		} catch (Exception ex) {
			System.out.println("Exception in UserServiceImpl.registerUser() " + ex.getMessage());
			response = new BaseResponse();
			response.setErrorCode(ErrorCode.EMAIL_ALREADY_EXISTS);
			response.setErrorDesc(ex.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

		}
		return response;
	}

	@SuppressWarnings("unchecked")
	public BaseResponse userLogin(User user) {
		BaseResponse response = null;
		Map<String, Object> mapObject = null;
		if (emailExist(user.getEmail())) {
			if (checkIfValidPassword(user)) {
				response = new BaseResponse();
				mapObject = new HashMap<String, Object>();
				Query query = session.createQuery("from User where email=:email");
				query.setParameter("email", user.getEmail());
				List<User> list = query.list();
				if (null != list && list.size() > 0) {
					user = list.get(0);
				}
				// user = repository.getUser(user.getEmail());
				if (null != user) {
					user.setPassword(null);
					mapObject.put("user", user);
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

	public boolean checkIfValidPassword(User user) {

		// System.out.println(passwordEncoder.encode(user.getPassword()));
		// System.out.println(repository.getUserPassword(user.getEmail()));
		CharSequence pass = user.getPassword();
		return passwordEncoder.matches(pass, repository.getUserPassword(user.getEmail()));
	}

	@Override
	public BaseResponse getUser(int userId) {
		// TODO Auto-generated method stub
		User userDetails = null;
		BaseResponse response = null;
		Map<String, Object> mapObject = null;
		try {

			userDetails = session.get(User.class, userId);

			if (null != userDetails) {
				mapObject = new HashMap<String, Object>();
				userDetails.setPassword(null);
				mapObject.put("user", userDetails);
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
	public void saveRegisteredUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createVerificationTokenForUser(User user, String token) {
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
	public void createPasswordResetTokenForUser(User user, String token) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByPasswordResetToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeUserPassword(User user, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkIfValidOldPassword(User user, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String validateVerificationToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateQRUrl(User user) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser2FA(boolean use2fa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getUsersFromSessionRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

}
