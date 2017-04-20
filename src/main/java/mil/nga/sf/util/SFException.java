package mil.nga.sf.util;

/**
 * Well-Known Binary exception
 * 
 * @author osbornb
 */
public class SFException extends RuntimeException {

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public SFException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public SFException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param throwable
	 */
	public SFException(String message, Throwable throwable) {
		super(message, throwable);
	}

	/**
	 * Constructor
	 * 
	 * @param throwable
	 */
	public SFException(Throwable throwable) {
		super(throwable);
	}

}
