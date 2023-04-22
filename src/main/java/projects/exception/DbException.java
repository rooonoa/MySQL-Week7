/**
 * 
 */
package projects.exception;

/**
 * This is an unchecked exception that will turn all the checked exceptions that are thrown by SQLExeption in the catch block 
 * to unchecked exception 
 *
 */
public class DbException extends RuntimeException {

	/**
	 * 
	 */
	public DbException() {
		
	}

	/**
	 * @param message
	 */
	public DbException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public DbException(Throwable cause) {
		super(cause);

	}

	/**
	 * @param message
	 * @param cause
	 */
	public DbException(String message, Throwable cause) {
		super(message, cause);
		
	}

	

}
