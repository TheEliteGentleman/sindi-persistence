/**
 * 
 */
package za.co.sindi.persistence.exception;

/**
 * @author Bienfait Sindi
 * @since 31 January 2014
 *
 */
public class CacheException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6607262957583623090L;

//	/**
//	 * 
//	 */
//	public CacheException() {
//		// TODO Auto-generated constructor stub
//	}

	/**
	 * @param message
	 */
	public CacheException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public CacheException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CacheException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CacheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}
