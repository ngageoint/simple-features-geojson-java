package mil.nga.sf;

public interface Position {

	/**
	 * Get x
	 * 
	 * @return x
	 */
	public Double getX();

	/**
	 * Get y
	 * 
	 * @return y
	 */
	public Double getY();

	/**
	 * Get z
	 * 
	 * @return z
	 */
	public Double getZ();

	/**
	 * Get m
	 * 
	 * @return m
	 */
	public Double getM();

	/**
	 * Has z
	 * 
	 * @return true: this position has a Z value
	 */
	public boolean hasZ();

	/**
	 * Has m
	 * 
	 * @return true: this position has a M value
	 */
	public boolean hasM();
}
