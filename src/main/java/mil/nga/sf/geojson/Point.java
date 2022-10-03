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
		return getPosition();
	}

	/**
	 * Sets the coordinates
	 * 
	 * @param coordinates
	 *            coordinates
	 */
	public void setCoordinates(Position coordinates) {
		setPosition(coordinates);
	}

	/**
	 * Get the position
	 * 
	 * @return position
	 * @since 3.0.0
	 */
	@JsonIgnore
	public Position getPosition() {
		return position;
	}

	/**
	 * Set the position
	 * 
	 * @param position
	 *            position
	 * @since 3.0.0
	 */
	public void setPosition(Position position) {
		this.position = position;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

}
