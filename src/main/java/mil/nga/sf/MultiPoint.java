package mil.nga.sf;

import java.util.List;

/**
 * A restricted form of GeometryCollection where each Geometry in the collection
 * must be of type Point.
 * 
 * @author osbornb
 */
public class MultiPoint extends GeometryCollection<Point> {

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public MultiPoint(boolean hasZ, boolean hasM) {
		super(GeometryType.MULTIPOINT, hasZ, hasM);
	}

	/**
	 * Get the points
	 * 
	 * @return points
	 */
	public List<Point> getPoints() {
		return getGeometries();
	}

	/**
	 * Set the points
	 * 
	 * @param points
	 *            points
	 */
	public void setPoints(List<Point> points) {
		setGeometries(points);
	}

	/**
	 * Add a point
	 * 
	 * @param point
	 *            point
	 */
	public void addPoint(Point point) {
		addGeometry(point);
	}

	/**
	 * Get the number of points
	 * 
	 * @return number of points
	 */
	public int numPoints() {
		return numGeometries();
	}

}
