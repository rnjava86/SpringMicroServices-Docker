package org.evoke.product.model;

import java.util.Map;

import org.evoke.product.error.ErrorMessage;

public class BaseResponse extends ErrorMessage{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,Object> response;

	public Map<String, Object> getResponse() {
		return response;
	}

	public void setResponse(Map<String, Object> response) {
		this.response = response;
	}
		
	
	
}
