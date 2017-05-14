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
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public MultiSurface(boolean hasZ, boolean hasM) {
		super(GeometryType.MULTISURFACE, hasZ, hasM);
	}
}