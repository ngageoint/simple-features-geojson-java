package mil.nga.sf;

import java.util.ArrayList;
import java.util.List;

/**
 * A Curve that connects two or more points in space.
 * 
 * @author osbornb
 */
public class LineString extends Curve {

	/**
	 * List of points
	 */
	private List<Point> points = new ArrayList<Point>();

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public LineString(boolean hasZ, boolean hasM) {
		super(GeometryType.LINESTRING, hasZ, hasM);
	}

	/**
	 * Constructor
	 * 
	 * @param type
	 *            geometry type
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	protected LineString(GeometryType type, boolean hasZ, boolean hasM) {
		super(type, hasZ, hasM);
	}

	/**
	 * Get the points
	 * 
	 * @return points
	 */
	public List<Point> getPoints() {
		return points;
	}

	/**
	 * Set the points
	 * 
	 * @param points
	 *            points
	 */
	public void setPoints(List<Point> points) {
		this.points = points;
	}

	/**
	 * Add a point
	 * 
	 * @param point
	 *            point
	 */
	public void addPoint(Point point) {
		points.add(point);
	}

	/**
	 * Get the number of points
	 * 
	 * @return number of points
	 */
	public int numPoints() {
		return points.size();
	}

}
