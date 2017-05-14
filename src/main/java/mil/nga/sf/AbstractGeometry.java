package mil.nga.sf;

/**
 * The root of the geometry type hierarchy
 * 
 * @author osbornb
 */
abstract class AbstractGeometry implements Geometry {

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
	protected AbstractGeometry(GeometryType geometryType, boolean hasZ, boolean hasM) {
		this.geometryType = geometryType;
		this.hasZ = hasZ;
		this.hasM = hasM;
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.Geometry#getGeometryType()
	 */
	@Override
	public GeometryType getGeometryType() {
		return geometryType;
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.Geometry#hasZ()
	 */
	@Override
	public boolean hasZ() {
		return hasZ;
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.Geometry#hasM()
	 */
	@Override
	public boolean hasM() {
		return hasM;
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.Geometry#isEmpty()
	 */
	@Override
	public abstract boolean isEmpty();
}
