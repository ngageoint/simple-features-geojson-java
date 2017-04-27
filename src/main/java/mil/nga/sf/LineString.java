package mil.nga.sf;

/**
 * A Curve that connects two or more positions in space.
 * 
 * @author osbornb
 */
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
