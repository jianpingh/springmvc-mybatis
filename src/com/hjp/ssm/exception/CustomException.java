package com.hjp.ssm.exception;

public class CustomException extends Exception {
	//异常信息
	public String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomException(String message) {
		super(message);
		this.message=message;
	}

	
	
	

	
}
