package mil.nga.sf;

/**
 * The root of the geometry type hierarchy
 * 
 * @author osbornb
 */
public abstract class Geometry {

	/**
	 * Geometry type
	 */
	private final GeometryType geometryType;

	/**
	 * Has z coordinates
	 */
	private final boolean hasZ;

	/**
	 * Has m values
	 */
	private final boolean hasM;

	/**
	 * Constructor for overrides
	 * 
	 * @param type
	 *            geometry type
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	protected Geometry(GeometryType geometryType, boolean hasZ, boolean hasM) {
		this.geometryType = geometryType;
		this.hasZ = hasZ;
		this.hasM = hasM;
	}

	/**
	 * geometry type
	 * @return geometry type
	 */
	public GeometryType getGeometryType() {
		return geometryType;
	}

	/**
	 * 
	 * @return true: has Z value
	 */
	public boolean hasZ() {
		return hasZ;
	}

	/**
	 * 
	 * @return true: has M value
	 */
	public boolean hasM() {
		return hasM;
	}

	/**
	 * 
	 * @return true: geometry is empty
	 */
	public abstract boolean isEmpty();
}
