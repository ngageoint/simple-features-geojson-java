package mil.nga.sf;

/**
 * A restricted form of CurvePolygon where each ring is defined as a simple,
 * closed LineString.
 * 
 * @author osbornb
 */
public class Polygon extends CurvePolygon<LineString> {

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

}
