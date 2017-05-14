package mil.nga.sf;

/**
 * Triangle
 * 
 * @author osbornb
 */
public class Triangle extends Polygon {

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public Triangle(boolean hasZ, boolean hasM) {
		super(GeometryType.TRIANGLE, hasZ, hasM);
	}

}
