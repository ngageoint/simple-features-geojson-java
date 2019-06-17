package mil.nga.sf.geojson;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * GeoJSON Object
 * 
 * @author yutzlejp
 */
@JsonTypeInfo(property = "type", use = Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({ @Type(Feature.class), @Type(FeatureCollection.class),
		@Type(Geometry.class) })
@JsonInclude(Include.NON_EMPTY)
public abstract class GeoJsonObject implements Serializable {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Bounding box
	 */
	private double[] bbox;

	/**
	 * Foreign members
	 */
	private Map<String, Object> foreignMembers = new HashMap<>();

	/**
	 * Get the bounding box
	 * 
	 * @return bounding box
	 */
	public double[] getBbox() {
		return bbox;
	}

	/**
	 * Set the bounding box
	 * 
	 * @param bbox
	 *            bounding box
	 */
	public void setBbox(double[] bbox) {
		this.bbox = bbox;
	}

	/**
	 * Get the GeoJSON object type
	 * 
	 * @return GeoJSON object type
	 */
	public abstract String getType();

	/**
	 * Get the foreign members
	 * 
	 * @return foreign members
	 */
	@JsonAnyGetter
	public Map<String, Object> getForeignMembers() {
		return foreignMembers;
	}

	/**
	 * Get the foreign member by name
	 * 
	 * @param name
	 *            name
	 * @return value
	 */
	public Object getForeignMember(String name) {
		return foreignMembers.get(name);
	}

	/**
	 * Check if has the foreign member
	 * 
	 * @param name
	 *            name
	 * @return true if has
	 */
	public Object hasForeignMember(String name) {
		return foreignMembers.containsKey(name);
	}

	/**
	 * Set a foreign member
	 * 
	 * @param name
	 *            name
	 * @param value
	 *            value
	 */
	@JsonAnySetter
	public void setForeignMember(String name, Object value) {
		foreignMembers.put(name, value);
	}

	/**
	 * Check if has foreign members
	 * 
	 * @return true if has
	 */
	public boolean hasForeignMembers() {
		return !foreignMembers.isEmpty();
	}

}
