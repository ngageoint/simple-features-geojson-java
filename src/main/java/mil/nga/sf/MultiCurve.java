package mil.nga.sf;

/**
 * A restricted form of GeometryCollection where each Geometry in the collection
 * must be of type Curve.
 * 
 * @author osbornb
 */
public abstract class MultiCurve<T extends Curve> extends GeometryCollection<T> {

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
	protected MultiCurve(GeometryType type, boolean hasZ, boolean hasM) {
		super(type, hasZ, hasM);
	}

}
