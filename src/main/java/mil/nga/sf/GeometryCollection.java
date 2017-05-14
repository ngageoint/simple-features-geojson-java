package mil.nga.sf;

import java.util.ArrayList;
import java.util.List;

public abstract class GeometryCollection<T extends Geometry> extends Geometry {

	private List<T> geometries = new ArrayList<T>();
	
	/**
	 * Constructor for overrides
	 * 
	 * @param geometryType
	 *            geometry type
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	protected GeometryCollection(GeometryType geometryType, boolean hasZ, boolean hasM) {
		super(geometryType, hasZ, hasM);
	}

	/**
	 * Get the list of geometries
	 * 
	 * @return geometries
	 */
	public List<T> getGeometries() {
		return geometries;
	}

	/**
	 * Set the geometries
	 * 
	 * @param geometries
	 *            geometries
	 */
	public void setGeometries(List<T> geometries) {
		this.geometries.clear();
		if(geometries != null){
			this.geometries.addAll(geometries);
		}
	}

	/**
	 * Add a geometry
	 * 
	 * @param geometry
	 *            geometry
	 */
	public void addGeometry(T geometry) {
		geometries.add(geometry);
	}

	/**
	 * Get the number of geometries in the collection
	 * 
	 * @return number of geometries
	 */
	public int numGeometries() {
		return geometries.size();
	}

	/**
	 * @return true if the geometry is empty
	 */
	public boolean isEmpty() {
		return geometries.isEmpty();
	}
}