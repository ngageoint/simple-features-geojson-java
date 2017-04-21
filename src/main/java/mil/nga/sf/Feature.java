package mil.nga.sf;

import java.util.HashMap;
import java.util.Map;

/**
 * The a feature
 * 
 * @author yutzlejp
 */
public class Feature {

	/**
	 * Geometry type
	 */
	private Geometry geometry = null;

	/**
	 * Properties map
	 */
	private Map<String, Object> properties;

	public Feature() {
		properties = new HashMap<String, Object>();
	}
	
	public Feature(Geometry geometry, Map<String, Object> properties){
		this.geometry = geometry;
		this.properties = properties;
	}

	public Geometry getGeometry() {
		return geometry;
	}
	
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
}
