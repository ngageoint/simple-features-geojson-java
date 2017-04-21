package mil.nga.sf;

/**
 * A geometry consisting of a single position.
 * 
 * @author osbornb
 */
public class Point extends Geometry {

	private Position position;

	/**
	 * Constructor
	 * 
	 */
	public Point() {
		this(Double.NaN, Double.NaN);
	}

	/**
	 * Constructor
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 */
	public Point(double x, double y) {
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
	public Point(double x, double y, double z) {
		this(x, y, z, Double.NaN);
	}

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 */
	public Point(double x, double y, double z, double m) {
		super(GeometryType.POINT, !Double.isNaN(z), !Double.isNaN(m));
		position = new Position(x, y, z, m);
	}

	/**
	 * Get x
	 * 
	 * @return x
	 */
	public double getX() {
		return position.getX();
	}

	/**
	 * Get y
	 * 
	 * @return y
	 */
	public double getY() {
		return position.getY();
	}

	/**
	 * Get z
	 * 
	 * @return z
	 */
	public double getZ() {
		return position.getZ();
	}

	/**
	 * Get m
	 * 
	 * @return m
	 */
	public double getM() {
		return position.getM();
	}
}
