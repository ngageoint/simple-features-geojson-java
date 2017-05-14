package mil.nga.sf;

import java.util.List;

public interface GeometryCollection<T extends Geometry> {

	/**
	 * Get the list of geometries
	 * 
	 * @return geometries
	 */
	List<T> getGeometries();

	/**
	 * Set the geometries
	 * 
	 * @param geometries
	 *            geometries
	 */
	void setGeometries(List<T> geometries);

	/**
	 * Add a geometry
	 * 
	 * @param geometry
	 *            geometry
	 */
	void addGeometry(T geometry);

	/**
	 * Get the number of geometries in the collection
	 * 
	 * @return number of geometries
	 */
	int numGeometries();

	boolean isEmpty();

}