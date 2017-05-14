package mil.nga.sf;

import java.util.List;

import mil.nga.sf.util.PointUtils;

public class LineString extends Curve {

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
	 * @param points
	 *            points describing this LineString
	 */
	public LineString(List<Point> points) {
		super(GeometryType.LINESTRING, PointUtils.hasZ(points), PointUtils.hasM(points));
		setPoints(points);
	}

	/**
	 * Constructor for overrides
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
}