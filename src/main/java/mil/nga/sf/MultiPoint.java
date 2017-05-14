package mil.nga.sf;

import java.util.List;

import mil.nga.sf.util.PointUtils;

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
	 * Constructor
	 * 
	 * @param points
	 *            A list of points, which will be used to determine hasZ and hasM
	 */
	public MultiPoint(List<Point> points) {
		super(GeometryType.MULTIPOINT, PointUtils.hasZ(points), PointUtils.hasM(points));
		setGeometries(points);
	}
}
