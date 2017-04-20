package mil.nga.sf;

/**
 * Circular String, Curve sub type
 * 
 * @author osbornb
 */
public class CircularString extends LineString {

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public CircularString(boolean hasZ, boolean hasM) {
		super(GeometryType.CIRCULARSTRING, hasZ, hasM);
	}

}
