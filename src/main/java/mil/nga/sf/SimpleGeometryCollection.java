package mil.nga.sf;

/**
 * A collection of zero or more Geometry instances.
 * 
 * @author osbornb
 */
public class SimpleGeometryCollection extends GeometryCollection<Geometry> {

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public SimpleGeometryCollection(boolean hasZ, boolean hasM) {
		super(GeometryType.GEOMETRYCOLLECTION, hasZ, hasM);
	}
}
