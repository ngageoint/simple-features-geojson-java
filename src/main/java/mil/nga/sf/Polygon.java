package mil.nga.sf;

import java.util.List;

import mil.nga.sf.util.PositionUtils;

/**
 * A restricted form of CurvePolygon where each ring is defined as a simple,
 * closed LineString.
 * 
 * @author osbornb
 */
public class Polygon extends CurvePolygon<LinearRing> {

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public Polygon(boolean hasZ, boolean hasM) {
		super(GeometryType.POLYGON, hasZ, hasM);
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
	protected Polygon(GeometryType type, boolean hasZ, boolean hasM) {
		super(type, hasZ, hasM);
	}

	public Polygon(List<LinearRing> rings) {
		super(GeometryType.POLYGON, PositionUtils.hasZ(rings), PositionUtils.hasM(rings));
		setRings(rings);
	}
}
