package mil.nga.sf.geojson;

import java.util.HashMap;
import java.util.Map;

import mil.nga.sf.Geometry;

/**
 * The simple feature
 * 
 * @author yutzlejp
 */
public class SimpleFeature {

	/**
	 * Geometry
	 */
	private Geometry geometry = null;

	/**
	 * Properties map
	 */
	private Map<String, Object> properties;

	/**
	 * Constructor
	 */
	public SimpleFeature() {
		properties = new HashMap<>();
	}

	/**
	 * Constructor
	 * 
	 * @param geometry
	 *            geometry
	 * @param properties
	 *            properties map
	 */
	public SimpleFeature(Geometry geometry, Map<String, Object> properties) {
		this.geometry = geometry;
		this.properties = properties;
	}

	/**
	 * Get the geometry
	 * 
	 * @return geometry
	 */
	public Geometry getGeometry() {
		return geometry;
	}

	/**
	 * Set the geometry
	 * 
	 * @param geometry
	 *            geometry
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	/**
	 * Get the properties map
	 * 
	 * @return properties map
	 */
	public Map<String, Object> getProperties() {
		return properties;
	}

	/**
	 * Set the properties map
	 * 
	 * @param properties
	 *            properties map
	 */
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}
