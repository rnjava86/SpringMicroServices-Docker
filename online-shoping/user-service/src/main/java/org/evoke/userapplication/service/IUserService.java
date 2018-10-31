package org.evoke.userapplication.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.evoke.userapplication.model.UserDetails;
//import org.evoke.userapplication.model.VerificationToken;
import org.evoke.userapplication.web.error.UserAlreadyExistException;



public interface IUserService {
	
	boolean registerNewUserAccount(UserDetails accountDto) throws UserAlreadyExistException;

	UserDetails getUser(String verificationToken);

    void saveRegisteredUser(UserDetails user);

    void deleteUser(UserDetails user);

    void createVerificationTokenForUser(UserDetails user, String token);

    //VerificationToken getVerificationToken(String VerificationToken);

    //VerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForUser(UserDetails user, String token);

    UserDetails findUserByEmail(String email);

    //PasswordResetToken getPasswordResetToken(String token);

    UserDetails getUserByPasswordResetToken(String token);

    //Optional<UserUserDetails> getUserByID(long id);

    void changeUserPassword(UserDetails user, String password);

    boolean checkIfValidOldPassword(UserDetails user, String password);

    String validateVerificationToken(String token);

    String generateQRUrl(UserDetails user) throws UnsupportedEncodingException;

    UserDetails updateUser2FA(boolean use2FA);

    List<String> getUsersFromSessionRegistry();

}
