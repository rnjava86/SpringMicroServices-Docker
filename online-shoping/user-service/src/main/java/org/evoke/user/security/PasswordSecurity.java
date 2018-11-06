package org.evoke.user.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordSecurity implements PasswordEncoder{

	
	private static final PasswordEncoder bCrypt = new BCryptPasswordEncoder();
	@Override
	public String encode(CharSequence rawPassword) {
	
		return bCrypt.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return bCrypt.matches(rawPassword, encodedPassword);
	}
	
	
	
}
