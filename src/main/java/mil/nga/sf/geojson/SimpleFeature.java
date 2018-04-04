package mil.nga.sf.geojson;

import java.util.HashMap;
import java.util.Map;

import mil.nga.sf.Geometry;

/**
 * The a feature
 * 
 * @author yutzlejp
 */
public class SimpleFeature{

	/**
	 * Geometry type
	 */
	private Geometry geometry = null;

	/**
	 * Properties map
	 */
	private Map<String, Object> properties;

	public SimpleFeature() {
		properties = new HashMap<String, Object>();
	}
	
	public SimpleFeature(Geometry geometry, Map<String, Object> properties){
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
