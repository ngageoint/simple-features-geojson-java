package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Line String
 * 
 * @author yutzlejp
 */
public class LineString extends Geometry {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * List of points
	 */
	private List<Point> points = null;

	/**
	 * Create a line string from coordinates
	 * 
	 * @param coordinates
	 *            coordinates
	 * @return line string
	 * @since 3.0.0
	 */
	public static LineString fromCoordinates(List<Position> coordinates) {
		LineString lineString = new LineString();
		lineString.setCoordinates(coordinates);
		return lineString;
	}

	/**
	 * Constructor
	 */
	public LineString() {

	}

	/**
	 * Constructor
	 * 
	 * @param points
	 *            list of points
	 * @since 3.0.0
	 */
	public LineString(List<Point> points) {
		this.points = points;
	}

	/**
	 * Constructor
	 * 
	 * @param lineString
	 *            simple line string
	 */
	public LineString(mil.nga.sf.LineString lineString) {
		setLineString(lineString);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GeometryType getGeometryType() {
		return GeometryType.LINESTRING;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return getLineString();
	}

	/**
	 * Get the coordinates
	 * 
	 * @return list of positions
	 */
	public List<Position> getCoordinates() {
		List<Position> coordinates = new ArrayList<>();
		for (Point point : points) {
			coordinates.add(point.getCoordinates());
		}
		return coordinates;
	}

	/**
	 * Set the coordinates
	 * 
	 * @param coordinates
	 *            coordinates
	 */
	public void setCoordinates(List<Position> coordinates) {
		points = new ArrayList<>();
		for (Position position : coordinates) {
			points.add(Point.fromCoordinates(position));
		}
	}

	/**
	 * Get the simple features line string
	 * 
	 * @return line string
	 * @since 3.0.0
	 */
	@JsonIgnore
	public mil.nga.sf.LineString getLineString() {
		List<mil.nga.sf.Point> simplePoints = new ArrayList<>();
		for (Point point : points) {
			simplePoints.add(point.getPoint());
		}
		return new mil.nga.sf.LineString(simplePoints);
	}

	/**
	 * Set the simple features line string
	 * 
	 * @param lineString
	 *            line string
	 * @since 3.0.0
	 */
	public void setLineString(mil.nga.sf.LineString lineString) {
		points = new ArrayList<>();
		for (mil.nga.sf.Point point : lineString.getPoints()) {
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
		LineString other = (LineString) obj;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		return true;
	}

}
