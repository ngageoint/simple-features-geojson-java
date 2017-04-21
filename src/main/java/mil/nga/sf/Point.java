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
	public Point(Double x, Double y) {
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
	public Point(Double x, Double y, Double z) {
		this(x, y, z, null);
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
	public Point(Double x, Double y, Double z, Double m) {
		super(GeometryType.POINT, !(z == null), !(m == null));
		position = new Position(x, y, z, m);
	}

	/**
	 * Get x
	 * 
	 * @return x
	 */
	public Double getX() {
		return position.getX();
	}

	/**
	 * Get y
	 * 
	 * @return y
	 */
	public Double getY() {
		return position.getY();
	}

	/**
	 * Get z
	 * 
	 * @return z
	 */
	public Double getZ() {
		return position.getZ();
	}

	/**
	 * Get m
	 * 
	 * @return m
	 */
	public Double getM() {
		return position.getM();
	}
}
