package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Multi Point
 * 
 * @author yutzlejp
 */
public class MultiPoint extends Geometry {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * List of points
	 */
	private List<Point> points = null;

	/**
	 * Create a multi point from coordinates
	 * 
	 * @param coordinates
	 *            coordinates
	 * @return multi point
	 * @since 3.0.0
	 */
	public static MultiPoint fromCoordinates(List<Position> coordinates) {
		MultiPoint multiPoint = new MultiPoint();
		multiPoint.setCoordinates(coordinates);
		return multiPoint;
	}

	/**
	 * Constructor
	 */
	public MultiPoint() {

	}

	/**
	 * Constructor
	 * 
	 * @param points
	 *            points list
	 * @since 3.0.0
	 */
	public MultiPoint(List<Point> points) {
		this.points = points;
	}

	/**
	 * Constructor
	 * 
	 * @param multiPoint
	 *            simple multi point
	 */
	public MultiPoint(mil.nga.sf.MultiPoint multiPoint) {
		setMultiPoint(multiPoint);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GeometryType getGeometryType() {
		return GeometryType.MULTIPOINT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return getMultiPoint();
	}

	/**
	 * Returns coordinates as a GeoJSON Position list
	 * 
	 * @return the coordinates
	 */
	public List<Position> getCoordinates() {
		List<Position> coordinates = new ArrayList<>();
		for (Point point : points) {
			coordinates.add(point.getCoordinates());
		}
		return coordinates;
	}

	/**
	 * Sets the coordinates from a GeoJSON Position list
	 * 
	 * @param coordinates
	 *            coordinates
	 * @since 3.0.0
	 */
	public void setCoordinates(List<Position> coordinates) {
		points = new ArrayList<>();
		for (Position position : coordinates) {
			points.add(Point.fromCoordinates(position));
		}
	}

	/**
	 * Get the points
	 * 
	 * @return list of points
	 * @since 3.0.0
	 */
	@JsonIgnore
	public List<Point> getPoints() {
		return points;
	}

	/**
	 * Set the points
	 * 
	 * @param points
	 *            list of points
	 * @since 3.0.0
	 */
	public void setPoints(List<Point> points) {
		this.points = points;
	}

	/**
	 * Get the simple features multi point
	 * 
	 * @return multi point
	 * @since 3.0.0
	 */
	@JsonIgnore
	public mil.nga.sf.MultiPoint getMultiPoint() {
		List<mil.nga.sf.Point> simplePoints = new ArrayList<>();
		for (Point point : points) {
			simplePoints.add(point.getPoint());
		}
		return new mil.nga.sf.MultiPoint(simplePoints);
	}

	/**
	 * Set the simple features multi point
	 * 
	 * @param multiPoint
	 *            multi point
	 * @since 3.0.0
	 */
	public void setMultiPoint(mil.nga.sf.MultiPoint multiPoint) {
		points = new ArrayList<>();
		for (mil.nga.sf.Point point : multiPoint.getPoints()) {
			points.add(new Point(point));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((points == null) ? 0 : points.hashCode());
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
		MultiPoint other = (MultiPoint) obj;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		return true;
	}

}
