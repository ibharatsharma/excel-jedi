package com.basicutils.exceljedi;

/**
 * The exception for Processing failures. 
 * 
 * @author Bharat
 *
 */
public class ExcelException extends RuntimeException {

	private static final long serialVersionUID = 99655200244468538L;

	public ExcelException() {
		super();
	}
	
	public ExcelException(String message){
		super(message);
	}
	
	public ExcelException(String message, Throwable cause) {
        super(message, cause);
    }
}
