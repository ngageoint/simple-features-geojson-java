package mil.nga.sf;

/**
 * A restricted form of GeometryCollection where each Geometry in the collection
 * must be of type Surface.
 * 
 * @author osbornb
 */
public abstract class MultiSurface<T extends Surface> extends
		GeometryCollection<T> {

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
	protected MultiSurface(GeometryType type, boolean hasZ, boolean hasM) {
		super(type, hasZ, hasM);
	}

}
