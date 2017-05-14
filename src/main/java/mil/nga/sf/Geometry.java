package mil.nga.sf;

public interface Geometry {

	/**
	 * Get the geometry type
	 * 
	 * @return geometry type
	 */
	GeometryType getGeometryType();

	/**
	 * Does the geometry have z coordinates
	 * 
	 * @return true if has z coordinates
	 */
	boolean hasZ();

	/**
	 * Does the geometry have m coordinates
	 * 
	 * @return true if has m coordinates
	 */
	boolean hasM();

	/**
	 * Is the geometry empty?
	 * 
	 * @return true if it is empty
	 */
	boolean isEmpty();

}