package org.evoke.user.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.transaction.Transactional;

import org.evoke.user.model.BaseResponse;
import org.evoke.user.model.UserDetails;
import org.evoke.user.web.error.UserAlreadyExistException;


@Transactional
public interface UserService {
	
	BaseResponse registerUser(UserDetails accountDto) throws UserAlreadyExistException;

	BaseResponse getUser(int userId);

    void saveRegisteredUser(UserDetails user);

    void deleteUser(UserDetails user);

    void createVerificationTokenForUser(UserDetails user, String token);

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
