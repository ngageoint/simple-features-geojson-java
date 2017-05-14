package mil.nga.sf;

import java.util.HashMap;
import java.util.Map;

/**
 * The a feature
 * 
 * @author yutzlejp
 */
public class SimpleFeature implements Feature {

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

	/* (non-Javadoc)
	 * @see mil.nga.sf.Feature#getGeometry()
	 */
	@Override
	public Geometry getGeometry() {
		return geometry;
	}
	
	/* (non-Javadoc)
	 * @see mil.nga.sf.Feature#setGeometry(mil.nga.sf.Geometry)
	 */
	@Override
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.Feature#getProperties()
	 */
	@Override
	public Map<String, Object> getProperties() {
		return properties;
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.Feature#setProperties(java.util.Map)
	 */
	@Override
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
}
