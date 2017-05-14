package mil.nga.sf;

import java.util.List;

import mil.nga.sf.util.PointUtils;
import mil.nga.sf.util.SFException;

public class LinearRing extends LineString {

	public LinearRing(boolean hasZ, boolean hasM) {
		super(hasZ, hasM);
	}

	/**
	 * Main constructor
	 * 
	 * @param curve the curve providing the positions
	 */
	public LinearRing(Curve curve) {
		super(curve.hasZ(), curve.hasM());
		setPoints(curve.getPoints());
	}

	/** 
	 * Constructor
	 * @param points a list of points
	 */
	public LinearRing(List<Point> points) {
		super(PointUtils.hasZ(points), PointUtils.hasM(points));
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
	protected LinearRing(GeometryType type, boolean hasZ, boolean hasM) {
		super(type, hasZ, hasM);
	}

	@Override
	public void setPoints(List<Point> points){
		super.setPoints(points);
		if (!isEmpty() && !isRing()){
			addPoint(points.get(0));
		}
		if (points.size() < 3) {
			throw new SFException ("A linear ring must have at least three positions.");
		}
	}
}