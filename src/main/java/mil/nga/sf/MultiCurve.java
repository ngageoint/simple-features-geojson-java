package mil.nga.sf;

/**
 * A restricted form of GeometryCollection where each Geometry in the collection
 * must be of type Curve.
 * 
 * @author osbornb
 */
public class MultiCurve extends GeometryCollection<Curve> {

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public MultiCurve(boolean hasZ, boolean hasM) {
		super(GeometryType.MULTICURVE, hasZ, hasM);
	}
}
