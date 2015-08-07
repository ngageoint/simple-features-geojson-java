package mil.nga.wkb.util;

/**
 * Well-Known Binary exception
 * 
 * @author osbornb
 */
public class WkbException extends RuntimeException {

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public WkbException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public WkbException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param throwable
	 */
	public WkbException(String message, Throwable throwable) {
		super(message, throwable);
	}

	/**
	 * Constructor
	 * 
	 * @param throwable
	 */
	public WkbException(Throwable throwable) {
		super(throwable);
	}

}
