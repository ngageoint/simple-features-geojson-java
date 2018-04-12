package mil.nga.sf.geojson;

/**
 * Point
 * 
 * @author yutzlejp
 */
public class Point extends Geometry {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Simple point
	 */
	private mil.nga.sf.Point point;

	/**
	 * Position
	 */
	private Position position;

	/**
	 * Constructor
	 */
	public Point() {
		this(new mil.nga.sf.Point());
	}

	/**
	 * Constructor
	 * 
	 * @param position
	 *            position
	 */
	public Point(Position position) {
		setCoordinates(position);
		this.position = position;
	}

	/**
	 * Constructor
	 * 
	 * @param point
	 *            simple point
	 */
	public Point(mil.nga.sf.Point point) {
		this.point = point;
		position = new Position(point);
	}

	/**
	 * Returns coordinates as a GeoJSON Position object
	 * 
	 * @return the coordinates
	 */
	public Position getCoordinates() {
		return position;
	}

	/**
	 * Sets the new position (supporting deserialization)
	 * 
	 * @param position
	 *            point position
	 */
	public void setCoordinates(Position position) {
		// When we set new coordinates,
		// we need to completely replace the underlying geometry
		point = new mil.nga.sf.Point(position.getX(), position.getY(),
				position.getZ(), position.getM());
		this.position = position;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return point;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return "Point";
	}

}
