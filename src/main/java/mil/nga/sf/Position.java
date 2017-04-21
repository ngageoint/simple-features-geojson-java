package mil.nga.sf;

/**
 * A single location in space. Each position has an X and Y coordinate. 
 * A point MAY optionally also have a Z and/or an M value.
 * 
 * @author jyutzler
 */
public class Position {

	/**
	 * X coordinate
	 */
	final private Double x;

	/**
	 * Y coordinate
	 */
	final private Double y;

	/**
	 * Z coordinate
	 */
	final private Double z;

	/**
	 * M value
	 */
	final private Double m;

	/**
	 * Constructor
	 * 
	 */
	public Position() {
		this(null, null, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 */
	public Position(Double x, Double y) {
		this(x, y, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param z
	 *            z coordinate
	 */
	public Position(Double x, Double y, Double z) {
		this(x, y, z, null);
	}

	/**
	 * Constructor
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param z
	 *            z coordinate
	 * @param m
	 *            m coordinate
	 */
	public Position(Double x, Double y, Double z, Double m) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.m = m;
	}

	/**
	 * Get x
	 * 
	 * @return x
	 */
	public Double getX() {
		return x;
	}

	/**
	 * Get y
	 * 
	 * @return y
	 */
	public Double getY() {
		return y;
	}

	/**
	 * Get z
	 * 
	 * @return z
	 */
	public Double getZ() {
		return z;
	}

	/**
	 * Get m
	 * 
	 * @return m
	 */
	public Double getM() {
		return m;
	}
}
