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
	final private double x;

	/**
	 * Y coordinate
	 */
	final private double y;

	/**
	 * Z coordinate
	 */
	final private double z;

	/**
	 * M value
	 */
	final private double m;

	/**
	 * Constructor
	 * 
	 */
	public Position() {
		this(Double.NaN, Double.NaN, Double.NaN, Double.NaN);
	}

	/**
	 * Constructor
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 */
	public Position(double x, double y) {
		this(x, y, Double.NaN, Double.NaN);
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
	public Position(double x, double y, double z) {
		this(x, y, z, Double.NaN);
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
	public Position(double x, double y, double z, double m) {
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
	public double getX() {
		return x;
	}

	/**
	 * Get y
	 * 
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Get z
	 * 
	 * @return z
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Get m
	 * 
	 * @return m
	 */
	public double getM() {
		return m;
	}
}
