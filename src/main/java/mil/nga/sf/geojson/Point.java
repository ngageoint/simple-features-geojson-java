package mil.nga.sf.geojson;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Point
 * 
 * @author yutzlejp
 */
public class Point extends Geometry {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * Position
	 */
	private Position position;

	/**
	 * Create a point from coordinates
	 * 
	 * @param coordinates
	 *            coordinates
	 * @return point
	 * @since 3.0.0
	 */
	public static Point fromCoordinates(Position coordinates) {
		return new Point(coordinates);
	}

	/**
	 * Constructor
	 */
	public Point() {

	}

	/**
	 * Constructor
	 * 
	 * @param position
	 *            position
	 */
	public Point(Position position) {
		this.position = position;
	}

	/**
	 * Constructor
	 * 
	 * @param point
	 *            simple point
	 */
	public Point(mil.nga.sf.Point point) {
		setPoint(point);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GeometryType getGeometryType() {
		return GeometryType.POINT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return getPoint();
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
	 * Sets the coordinates
	 * 
	 * @param coordinates
	 *            coordinates
	 */
	public void setCoordinates(Position coordinates) {
		this.position = coordinates;
	}

	/**
	 * Get the simple features point
	 * 
	 * @return point
	 * @since 3.0.0
	 */
	@JsonIgnore
	public mil.nga.sf.Point getPoint() {
		return position.toSimplePoint();
	}

	/**
	 * Set the simple features point
	 * 
	 * @param point
	 *            point
	 * @since 3.0.0
	 */
	public void setPoint(mil.nga.sf.Point point) {
		position = new Position(point);
	}

}
