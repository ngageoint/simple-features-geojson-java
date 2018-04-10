package mil.nga.sf.geojson;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Coordinate Reference System
 * 
 * @author yutzlejp
 */
public class Crs implements Serializable {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 2819273425539716507L;

	/**
	 * Coordinate Reference System Types
	 */
	public enum CrsType {

		/**
		 * Named CRS
		 */
		name,

		/**
		 * Linked CRS
		 */
		link;

	}

	/**
	 * Coordinate Reference System Type
	 */
	private CrsType type = CrsType.name;

	/**
	 * Properties map
	 */
	private Map<String, Object> properties = new HashMap<String, Object>();

	/**
	 * Get the CRS type
	 * 
	 * @return crs type
	 */
	public CrsType getType() {
		return type;
	}

	/**
	 * Set the CRS type
	 * 
	 * @param type
	 *            crs type
	 */
	public void setType(CrsType type) {
		this.type = type;
	}

	/**
	 * Get the properties
	 * 
	 * @return properties map
	 */
	public Map<String, Object> getProperties() {
		return properties;
	}

	/**
	 * Set the properties
	 * 
	 * @param properties
	 *            properties map
	 */
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Crs)) {
			return false;
		}
		Crs crs = (Crs) o;
		if (properties != null ? !properties.equals(crs.properties)
				: crs.properties != null) {
			return false;
		}
		return !(type != null ? !type.equals(crs.type) : crs.type != null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int result = type != null ? type.hashCode() : 0;
		result = 31 * result + (properties != null ? properties.hashCode() : 0);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Crs{" + "type='" + type + '\'' + ", properties=" + properties
				+ '}';
	}

}
