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
	 * Constructor
	 * 
	 * @param geometryType
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
	 * Get the geometry type
	 * 
	 * @return geometry type
	 */
	public GeometryType getGeometryType() {
		return geometryType;
	}

	/**
	 * Does the geometry have z coordinates
	 * 
	 * @return true if has z coordinates
	 */
	public boolean hasZ() {
		return hasZ;
	}

	/**
	 * Does the geometry have m coordinates
	 * 
	 * @return true if has m coordinates
	 */
	public boolean hasM() {
		return hasM;
	}

}
