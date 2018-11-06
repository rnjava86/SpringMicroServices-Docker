package org.evoke.user.model;

import java.util.List;

public class UserResponse extends BaseResponse {

	private List<UserDetails> userDetailsLst;

	public List<UserDetails> getUserDetailsLst() {
		return userDetailsLst;
	}

	public void setUserDetailsLst(List<UserDetails> userDetailsLst) {
		this.userDetailsLst = userDetailsLst;
	}

	@Override
	public String toString() {
		return "UserResponse [userDetailsLst=" + userDetailsLst + "]";
	}

	
		
	
}
