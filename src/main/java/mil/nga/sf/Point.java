package mil.nga.sf;

public class Point extends Geometry implements Position {

	/**
	 * coordinate
	 */
	final private double[] coordinates;
	
	/**
	 * Default Constructor. It is best not to use this unless another process 
	 * intends to immediately call setPosition().
	 */
	public Point() {
		this(null, null, null, null);
	}
	
	/**
	 * Constructor for the Position interface
	 * 
	 * @param position
	 *                position
	 */
	public Point(Position position){
		this(position.getX(), position.getY(), position.getZ(), position.getM());
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
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param z
	 *            z coordinate
	 * @param m
	 *            m coordinate
	 */
	public Point(Double x, Double y, Double z, Double m) {
		super(GeometryType.POINT, (z != null), (m != null));
		
		if ((x == null) || (y == null)) {
			coordinates = new double[0];
		} else {
			if (m != null) {
				coordinates = new double[4];
			} else if (z != null) {
				coordinates = new double[3];
			} else {
				coordinates = new double[2];
			}
		}
		
		if (coordinates.length >= 1) {
			coordinates[0] = x;
		}
		if (coordinates.length >= 2) {
			coordinates[1] = y;
		}
		if (coordinates.length >= 3) {
			// In the rare event of M but no Z...
			if (hasZ()) {
				coordinates[2] = z;
			}
		}
		if (coordinates.length >= 4) {
			coordinates[3] = m;
		}
	}

	/**
	 * Get x
	 * 
	 * @return x
	 */
	public Double getX() {
		return (coordinates.length > 0) ? coordinates[0] : null;
	}

	/**
	 * Get y
	 * 
	 * @return y
	 */
	public Double getY() {
		return (coordinates.length > 1) ? coordinates[1] : null;
	}

	/**
	 * Get z
	 * 
	 * @return z
	 */
	public Double getZ() {
		return (hasZ() && (coordinates.length > 2)) ? coordinates[2] : null;
	}

	/**
	 * Get m
	 * 
	 * @return m
	 */
	public Double getM() {
		return (coordinates.length > 3) ? coordinates[3] : null;
	}

	@Override
	public boolean isEmpty() {
		return (getX() == null) || (getY() == null);
	}
		
	static private boolean compare(Double left, Double right) {
		if ((left == null) || (right == null)) {
			return (left == null) && (right == null);
		}
		return left.compareTo(right) == 0;
	}

	@Override
	public boolean equals(Object right){
		if (this == right) {
			return true;
		}
		if(right instanceof Point) {
			Point point = (Point)right;
			return compare(this.getM(), point.getM()) &&
					compare(this.getX(), point.getX()) && 
					compare(this.getY(), point.getY()) &&
					compare(this.getZ(), point.getZ());
		}
		return false;
	}
	
}