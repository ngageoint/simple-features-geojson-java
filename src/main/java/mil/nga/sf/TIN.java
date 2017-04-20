package mil.nga.sf;

/**
 * A tetrahedron (4 triangular faces), corner at the origin and each unit
 * coordinate digit.
 * 
 * @author osbornb
 */
public class TIN extends PolyhedralSurface {

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public TIN(boolean hasZ, boolean hasM) {
		super(GeometryType.TIN, hasZ, hasM);
	}

}
