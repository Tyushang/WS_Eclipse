package chapter11_exception;

public class WhateverException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WhateverException() {
		
	}
	public WhateverException(String message) {
		super(message);
	}
	public String WhateverGetMessage() {
		return super.getMessage();
	}
}
